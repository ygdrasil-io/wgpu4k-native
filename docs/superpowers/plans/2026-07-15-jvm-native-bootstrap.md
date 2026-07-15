# JVM Native Bootstrap Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Make every generated public JVM downcall load the bundled wgpu-native library automatically before its first symbol lookup.

**Architecture:** A wgpu-specific resolver calls a retryable, thread-safe module loader before delegating to generic `kffi.findOrThrow`. The generated JVM file aliases that resolver as `findOrThrow`, while a generation-task postprocessor preserves the alias across regeneration. The historical `ffi` loader is removed because binary compatibility is explicitly out of scope.

**Tech Stack:** Kotlin 2.3.21, Kotlin Multiplatform, Java 25 Foreign Function and Memory API, Kotest, Gradle 9.5, Maven Local.

## Global Constraints

- Do not modify any Android source file.
- Do not execute any Android task; every Gradle verification command must exclude the six Android download/extraction tasks listed below.
- Keep wgpu-native loading inside `wgpu4k-native-jvm`; do not add wgpu-specific behavior to `kffi`.
- Reuse the current extraction and `System.load*` mechanism.
- Preserve callback code and lifecycle unchanged.
- After a failed load, propagate the original failure, keep the state unloaded, and retry on the next call.
- Do not publish or push to a remote repository.
- Maintain one clean final commit by amending the already-created design commit after each green checkpoint.

Use these exclusions for every Gradle command that can process native resources:

```bash
-x :wgpu4k-native:downloadFile-wgpu-android-aarch64-release.zip \
-x :wgpu4k-native:downloadFile-wgpu-android-x86_64-release.zip \
-x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-aarch64-release.zip \
-x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-x86_64-release.zip \
-x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-aarch64-release.zip \
-x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-x86_64-release.zip
```

---

### Task 1: Capture the isolated first-downcall regression

**Files:**
- Create: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/JvmBootstrapProbe.kt`
- Create: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/JvmBootstrapIsolatedTest.kt`

**Interfaces:**
- Consumes: generated `wgpuSetLogLevel(WGPULogLevel)`.
- Produces: `JvmBootstrapProbe.main(Array<String>)` and a child-process test harness reusable by callback and concurrency probes.

- [ ] **Step 1: Add the child probe with only the failing downcall mode**

```kotlin
package io.ygdrasil.wgpu

internal object JvmBootstrapProbe {
    @JvmStatic
    fun main(args: Array<String>) {
        require(args.contentEquals(arrayOf("downcall"))) { "Unknown probe arguments: ${args.toList()}" }
        wgpuSetLogLevel(WGPULogLevel_Warn)
    }
}
```

- [ ] **Step 2: Add the isolated-process assertion**

```kotlin
package io.ygdrasil.wgpu

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit

class JvmBootstrapIsolatedTest : FreeSpec({
    "first generated downcall loads wgpu-native in a fresh JVM" {
        runBootstrapProbe("downcall").shouldSucceed()
    }
})

private data class ProbeResult(
    val completed: Boolean,
    val exitCode: Int,
    val output: String,
)

private fun runBootstrapProbe(mode: String): ProbeResult {
    val javaExecutable = Path.of(
        System.getProperty("java.home"),
        "bin",
        if (System.getProperty("os.name").startsWith("Windows")) "java.exe" else "java",
    )
    val libraryDirectory = Files.createTempDirectory("wgpu4k-bootstrap-")
    return try {
        val process = ProcessBuilder(
            javaExecutable.toString(),
            "--enable-native-access=ALL-UNNAMED",
            "-Djava.library.path=$libraryDirectory",
            "-cp",
            System.getProperty("java.class.path"),
            JvmBootstrapProbe::class.java.name,
            mode,
        ).redirectErrorStream(true).start()
        val completed = process.waitFor(30, TimeUnit.SECONDS)
        if (!completed) process.destroyForcibly().waitFor()
        ProbeResult(
            completed = completed,
            exitCode = if (completed) process.exitValue() else -1,
            output = process.inputStream.bufferedReader().readText(),
        )
    } finally {
        libraryDirectory.toFile().deleteRecursively()
    }
}

private fun ProbeResult.shouldSucceed() {
    withClue(output) {
        completed shouldBe true
        exitCode shouldBe 0
    }
}
```

- [ ] **Step 3: Run the isolated test and record RED**

Run:

```bash
rtk ./gradlew :wgpu4k-native:jvmTest \
  --tests io.ygdrasil.wgpu.JvmBootstrapIsolatedTest \
  -x :wgpu4k-native:downloadFile-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:downloadFile-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-x86_64-release.zip
```

Expected: FAIL; child output contains `UnsatisfiedLinkError: unresolved symbol: wgpuSetLogLevel`.

---

### Task 2: Implement and test retryable load state

**Files:**
- Create: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/LibraryLoader.kt`
- Create: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/NativeLibraryLoadStateTest.kt`

**Interfaces:**
- Consumes: the existing resource layout `/darwin-*`, `/linux-*`, and `/win32-*`; generic `io.ygdrasil.kffi.findOrThrow`.
- Produces: public `io.ygdrasil.wgpu.LibraryLoader.load(): Unit`, internal `NativeLibraryLoadState.load(): Unit`, internal `NativeLibraryLoadState.isLoaded: Boolean`, and internal `findWgpuSymbol(String): MemorySegment`.

- [ ] **Step 1: Add RED tests for concurrency, failure identity, and retry**

```kotlin
package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class NativeLibraryLoadStateTest : FreeSpec({
    "concurrent first callers execute the load operation once" {
        val executions = AtomicInteger()
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val state = NativeLibraryLoadState {
            executions.incrementAndGet()
            entered.countDown()
            check(release.await(10, TimeUnit.SECONDS))
        }
        val executor = Executors.newFixedThreadPool(8)
        try {
            val futures = List(8) { executor.submit(state::load) }
            entered.await(10, TimeUnit.SECONDS) shouldBe true
            release.countDown()
            futures.forEach { it.get(10, TimeUnit.SECONDS) }
            executions.get() shouldBe 1
            state.isLoaded shouldBe true
        } finally {
            release.countDown()
            executor.shutdownNow()
        }
    }

    "failure preserves its identity and the next call retries" {
        val original = IllegalStateException("simulated load failure")
        var attempts = 0
        val state = NativeLibraryLoadState {
            attempts += 1
            if (attempts == 1) throw original
        }

        val observed = assertFailsWith<IllegalStateException> { state.load() }
        assertSame(original, observed)
        state.isLoaded shouldBe false

        state.load()
        attempts shouldBe 2
        state.isLoaded shouldBe true
    }
})
```

- [ ] **Step 2: Run the controller test and verify compilation is RED**

Run the Task 1 Gradle command with `--tests io.ygdrasil.wgpu.NativeLibraryLoadStateTest`.

Expected: FAIL during test compilation because `NativeLibraryLoadState` does not exist.

- [ ] **Step 3: Add the minimal loader and module resolver**

Create `LibraryLoader.kt` with the current extraction logic moved intact and the state publication fixed:

```kotlin
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.findOrThrow as findKffiSymbol
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.foreign.MemorySegment
import java.nio.file.Files
import java.nio.file.StandardCopyOption

private const val WGPU_NATIVE_VERSION = "v29.0.0.0"

internal class NativeLibraryLoadState(
    private val loadOperation: () -> Unit,
) {
    @Volatile
    var isLoaded: Boolean = false
        private set

    fun load() {
        if (isLoaded) return
        synchronized(this) {
            if (isLoaded) return
            loadOperation()
            isLoaded = true
        }
    }
}

object LibraryLoader {
    private val state = NativeLibraryLoadState(::exportAndLoadLibrary)

    fun load() = state.load()

    internal val isLoaded: Boolean
        get() = state.isLoaded
}

internal fun findWgpuSymbol(symbol: String): MemorySegment {
    LibraryLoader.load()
    return findKffiSymbol(symbol)
}

private fun exportAndLoadLibrary() {
    val libraryPath = findLibraryPath()
    when (WgpuPlatform.os) {
        WgpuOs.Windows -> loadFromLibraryPath(libraryPath, "", "dll")
        WgpuOs.MacOs -> loadFromLibraryPath(libraryPath, "lib", "dylib")
        WgpuOs.Linux -> {
            val libraryFile = Files.createTempFile("wgpu", "lib").toFile()
            println(libraryFile.absolutePath)
            extractResourceToTemp(libraryPath, libraryFile)
            println("will load library at path ${libraryFile.absolutePath}")
            System.load(libraryFile.absolutePath)
        }
    }
}

private fun loadFromLibraryPath(libraryPath: String, prefix: String, extension: String) {
    val libraryFiles = System.getProperty("java.library.path")
        .split(File.pathSeparator)
        .map(::File)
        .map { it.resolve("${prefix}WGPU-$WGPU_NATIVE_VERSION.$extension") }
    val libraryFile = libraryFiles.firstOrNull(File::exists) ?: libraryFiles.firstOrNull { path ->
        extractResourceToTemp(libraryPath, path)
    } ?: error("Could not find temporary resource for path: $libraryPath")
    println("will load library at path ${libraryFile.absolutePath}")
    System.loadLibrary(libraryFile.nameWithoutExtension.removePrefix("lib"))
}

private fun findLibraryPath(): String {
    val os = when (WgpuPlatform.os) {
        WgpuOs.Linux -> "linux"
        WgpuOs.Windows -> "win32"
        WgpuOs.MacOs -> "darwin"
    }
    val libraryName = when (WgpuPlatform.os) {
        WgpuOs.Linux -> "libWGPU.so"
        WgpuOs.Windows -> "WGPU.dll"
        WgpuOs.MacOs -> "libWGPU.dylib"
    }
    val architecture = when (WgpuPlatform.architecture) {
        WgpuArchitecture.X86_64 -> "x86-64"
        WgpuArchitecture.AARCH64 -> when (WgpuPlatform.os) {
            WgpuOs.Windows -> error("aarch64 not supported on windows")
            else -> "aarch64"
        }
    }
    return "/$os-$architecture/$libraryName"
}

private fun extractResourceToTemp(fileOnClasspath: String, target: File): Boolean {
    println("will extract library to path ${target.absolutePath}")
    try {
        val resource: InputStream = LibraryLoader::class.java.getResourceAsStream(fileOnClasspath)
            ?: error("Could not find file $fileOnClasspath on the classpath")
        resource.use {
            Files.copy(it, target.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
    } catch (exception: IOException) {
        println("fail to extract to path ${target.absolutePath} with reason ${exception.message}")
        return false
    }
    return true
}

private enum class WgpuOs { Linux, Windows, MacOs }
private enum class WgpuArchitecture { X86_64, AARCH64 }

private object WgpuPlatform {
    val os: WgpuOs
        get() = when {
            arrayOf("Linux", "SunOS", "Unit").any(System.getProperty("os.name")::startsWith) -> WgpuOs.Linux
            arrayOf("Mac OS X", "Darwin").any(System.getProperty("os.name")::startsWith) -> WgpuOs.MacOs
            System.getProperty("os.name").startsWith("Windows") -> WgpuOs.Windows
            else -> error("Unrecognized or unsupported operating system.")
        }

    val architecture: WgpuArchitecture
        get() = when (val architecture = System.getProperty("os.arch")) {
            "aarch64" -> WgpuArchitecture.AARCH64
            "x86_64", "amd64" -> WgpuArchitecture.X86_64
            else -> error("Unrecognized or unsupported architecture $architecture.")
        }
}
```

- [ ] **Step 4: Run the controller tests and verify GREEN**

Run the Task 1 Gradle command with `--tests io.ygdrasil.wgpu.NativeLibraryLoadStateTest`.

Expected: both tests PASS; no Android task executes.

- [ ] **Step 5: Amend the single work commit**

```bash
rtk git add wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/LibraryLoader.kt \
  wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/NativeLibraryLoadStateTest.kt
rtk git commit --amend --no-edit
```

---

### Task 3: Connect every generated JVM symbol and cover callbacks/concurrency

**Files:**
- Modify: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt:15`
- Modify: `wgpu4k-native/build.gradle.kts:280-360`
- Delete: `wgpu4k-native/src/jvmMain/kotlin/ffi/LibraryLoader.kt`
- Delete: `wgpu4k-native/src/jvmMain/kotlin/ffi/Platform.kt`
- Modify: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/JvmBootstrapProbe.kt`
- Modify: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/JvmBootstrapIsolatedTest.kt`

**Interfaces:**
- Consumes: `findWgpuSymbol(String)`, `LibraryLoader.isLoaded`, and registration-based `wgpuSetLogCallback`.
- Produces: automatic load coverage for every generated JVM `_ADDR` lazy initializer and regeneration protection.

- [ ] **Step 1: Extend the child probe with callback and concurrent first-access modes**

Replace the probe body with:

```kotlin
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

internal object JvmBootstrapProbe {
    private const val THREAD_COUNT = 16

    @JvmStatic
    fun main(args: Array<String>) {
        require(args.size == 1) { "Expected one probe mode" }
        when (args.single()) {
            "downcall" -> wgpuSetLogLevel(WGPULogLevel_Warn)
            "callback" -> wgpuSetLogCallback(CallbackPolicy.REPEATING) { _, _ -> }.close()
            "concurrent" -> runConcurrentFirstAccess()
            else -> error("Unknown probe mode: ${args.single()}")
        }
    }

    private fun runConcurrentFirstAccess() {
        val ready = CountDownLatch(THREAD_COUNT)
        val start = CountDownLatch(1)
        val executor = Executors.newFixedThreadPool(THREAD_COUNT)
        try {
            val futures = List(THREAD_COUNT) { index ->
                executor.submit {
                    ready.countDown()
                    check(start.await(10, TimeUnit.SECONDS))
                    if (index % 2 == 0) {
                        wgpuSetLogLevel(WGPULogLevel_Warn)
                    } else {
                        wgpuGetVersion()
                    }
                }
            }
            check(ready.await(10, TimeUnit.SECONDS))
            start.countDown()
            futures.forEach { it.get(10, TimeUnit.SECONDS) }
            check(LibraryLoader.isLoaded)
        } finally {
            start.countDown()
            executor.shutdownNow()
        }
    }
}
```

- [ ] **Step 2: Add isolated callback and concurrency assertions**

Append inside `JvmBootstrapIsolatedTest`:

```kotlin
"registration-based log callback loads wgpu-native before explicit bootstrap" {
    runBootstrapProbe("callback").shouldSucceed()
}

"concurrent generated first accesses load wgpu-native once" {
    val result = runBootstrapProbe("concurrent")
    result.shouldSucceed()
    result.output.lineSequence().count { it.startsWith("will load library at path ") } shouldBe 1
}
```

- [ ] **Step 3: Run all isolated tests and record RED**

Run the Task 1 Gradle command.

Expected: the original downcall and callback probes fail with unresolved symbols because the generated import still targets kffi directly.

- [ ] **Step 4: Route generated lookups through the module resolver**

Change the single generated import:

```kotlin
import io.ygdrasil.wgpu.findWgpuSymbol as findOrThrow
```

Define these values before `generateBindingsFromHeader` in `wgpu4k-native/build.gradle.kts`:

```kotlin
val generatedJvmBinding = project.file(
    "src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt",
)
val genericJvmLookupImport = "import io.ygdrasil.kffi.findOrThrow"
val wgpuJvmLookupImport = "import io.ygdrasil.wgpu.findWgpuSymbol as findOrThrow"
```

Add this `doLast` to `generateBindingsFromHeader`:

```kotlin
doLast {
    val source = generatedJvmBinding.readText()
    require(source.lineSequence().count { it == genericJvmLookupImport } == 1) {
        "Expected exactly one generic JVM lookup import in $generatedJvmBinding"
    }
    generatedJvmBinding.writeText(source.replace(genericJvmLookupImport, wgpuJvmLookupImport))
}
```

Add a read-only verification task and attach it to `jvmTest`:

```kotlin
tasks.register("verifyJvmBootstrapBinding") {
    group = "verification"
    inputs.file(generatedJvmBinding)
    doLast {
        val source = generatedJvmBinding.readText()
        require(wgpuJvmLookupImport in source) {
            "$generatedJvmBinding must route symbol lookup through findWgpuSymbol"
        }
        require(genericJvmLookupImport !in source) {
            "$generatedJvmBinding must not bypass the wgpu-native bootstrap"
        }
    }
}
```

Add `dependsOn("verifyJvmBootstrapBinding")` to the existing `jvmTest` configuration.

- [ ] **Step 5: Remove the historical JVM `ffi` package**

Delete both `wgpu4k-native/src/jvmMain/kotlin/ffi/LibraryLoader.kt` and
`wgpu4k-native/src/jvmMain/kotlin/ffi/Platform.kt`. Confirm no production or
test source imports `ffi.LibraryLoader`.

- [ ] **Step 6: Run isolated tests and verify GREEN**

Run the Task 1 Gradle command.

Expected: all three probes PASS; callback registration closes normally; the concurrent probe reports one effective load.

- [ ] **Step 7: Run the whole JVM suite**

Run the Task 1 Gradle command without `--tests`.

Expected: `:wgpu4k-native:jvmTest` PASS and no task whose name contains `android` executes.

- [ ] **Step 8: Amend the single work commit**

```bash
rtk git add wgpu4k-native/build.gradle.kts \
  wgpu4k-native/src/jvmMain/kotlin \
  wgpu4k-native/src/jvmTest/kotlin
rtk git commit --amend --no-edit
```

---

### Task 4: Verify published consumer and non-Android native compilation

**Files:**
- Create: `gradle/jvm-bootstrap-consumer/settings.gradle.kts`
- Create: `gradle/jvm-bootstrap-consumer/build.gradle.kts`
- Create: `gradle/jvm-bootstrap-consumer/src/main/kotlin/Main.kt`

**Interfaces:**
- Consumes: Maven Local coordinate `io.ygdrasil:wgpu4k-native-jvm:v29.0.0-SNAPSHOT`.
- Produces: an isolated consumer whose declared library dependency is only the new JVM artifact and whose first operation is `wgpuSetLogLevel`.

- [ ] **Step 1: Add the standalone consumer**

`settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "wgpu4k-jvm-bootstrap-consumer"
```

`build.gradle.kts`:

```kotlin
plugins {
    kotlin("jvm") version "2.3.21"
    application
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation("io.ygdrasil:wgpu4k-native-jvm:v29.0.0-SNAPSHOT")
}

application {
    mainClass = "MainKt"
}

tasks.named<JavaExec>("run") {
    val nativeLibraryDirectory = layout.buildDirectory.dir("native-library")
    doFirst {
        nativeLibraryDirectory.get().asFile.mkdirs()
    }
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    jvmArgs("-Djava.library.path=${nativeLibraryDirectory.get().asFile.absolutePath}")
}
```

`Main.kt`:

```kotlin
import io.ygdrasil.wgpu.WGPULogLevel_Warn
import io.ygdrasil.wgpu.wgpuSetLogLevel

fun main() {
    wgpuSetLogLevel(WGPULogLevel_Warn)
    println("FIRST_DOWNCALL_OK")
}
```

- [ ] **Step 2: Publish only JVM artifacts to Maven Local**

Run:

```bash
rtk ./gradlew \
  :kffi:publishJvmPublicationToMavenLocal \
  :wgpu4k-native:publishJvmPublicationToMavenLocal \
  -x :kffi:dokkaGeneratePublicationHtml \
  -x :wgpu4k-native:dokkaGeneratePublicationHtml \
  -x :kffi:cinteropCallbackTokenCodecAndroidNativeArm64 \
  -x :kffi:cinteropCallbackTokenCodecAndroidNativeX64 \
  -x :wgpu4k-native:downloadFile-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:downloadFile-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-x86_64-release.zip
```

Publish the root `kffi` metadata required by Gradle module metadata separately:

```bash
rtk ./gradlew \
  :kffi:publishKotlinMultiplatformPublicationToMavenLocal \
  -x :kffi:dokkaGeneratePublicationHtml \
  -x :kffi:cinteropCallbackTokenCodecAndroidNativeArm64 \
  -x :kffi:cinteropCallbackTokenCodecAndroidNativeX64 \
  -x :kffi:transformAndroidNativeMainCInteropDependenciesMetadata \
  -x :kffi:transformAndroidNativeMainDependenciesMetadata \
  -x :kffi:compileAndroidNativeMainKotlinMetadata \
  -x :kffi:metadataAndroidNativeMainProcessResources \
  -x :kffi:metadataAndroidNativeMainClasses
```

Expected: both JVM publications and the required root `kffi` metadata succeed in Maven Local at version `v29.0.0-SNAPSHOT`; no remote publication and no Android task.

- [ ] **Step 3: Compile and execute the isolated consumer**

Run:

```bash
rtk ./gradlew -p gradle/jvm-bootstrap-consumer clean run --console=plain
```

Expected: `FIRST_DOWNCALL_OK` and `BUILD SUCCESSFUL`; the consumer contains no loader import or bootstrap call.

- [ ] **Step 4: Compile host Kotlin/Native targets without Android tasks**

Run:

```bash
rtk ./gradlew \
  :wgpu4k-native:compileKotlinMacosArm64 \
  :wgpu4k-native:compileKotlinMacosX64 \
  -x :wgpu4k-native:downloadFile-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:downloadFile-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.a-from-wgpu-android-x86_64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-aarch64-release.zip \
  -x :wgpu4k-native:unzip-libwgpu_native.so-from-wgpu-android-x86_64-release.zip
```

Expected: both non-Android native compilations succeed.

- [ ] **Step 5: Run final repository checks**

```bash
rtk git diff --check
rtk git status --short
rtk git diff --name-only HEAD^
```

Expected: no whitespace errors; inspect the complete filename list and confirm
that it contains no Android file.

- [ ] **Step 6: Amend the final single commit**

```bash
rtk git add docs/superpowers gradle/jvm-bootstrap-consumer wgpu4k-native
rtk git commit --amend -m "fix: auto-load wgpu-native on JVM"
```

- [ ] **Step 7: Record final evidence**

Record the RED exception, GREEN test counts, native compilation results, Maven
Local coordinate, consumer output, `git diff --check`, final commit id, and the
fact that no remote snapshot was published. Because Maven Local snapshots are
not timestamped, report the exact coordinate without inventing a remote
timestamp.
