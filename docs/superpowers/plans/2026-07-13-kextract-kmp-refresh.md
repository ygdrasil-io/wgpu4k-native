# Kextract KMP Refresh Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Forward-port the existing Kotlin Multiplatform generator from `feat/multiplatform` onto current `kextract` `master` without regressing the non-KMP generator, then update `wgpu4k-native` to the refreshed submodule commit.

**Architecture:** Keep the current `master` pipeline and its split-output, variadic, Win32, Objective-C, and deferred-init features as the base. Add KMP as an explicit generation mode that selects the four existing KMP builders and uses an explicit source-root field instead of filename suffix inference. Preserve the KMP comment extraction and WebGPU static-constant support from the historical branch.

**Tech Stack:** Kotlin 2.3.21, JDK 25, Gradle, Clikt 5, libclang 22.1.6, Kotest/JUnit 5, Git submodules.

## Global Constraints

- Refresh `origin/master` immediately before branching; `28c5ab6b`/`v0.0.3` is the observed 2026-07-13 baseline, not a permanent pin.
- Preserve all non-KMP `master` parameters: `splitOutput`, `variadicArgs`, `includeFrameworks`, `win32Mode`, `dllMap`, and `useInitMethod`.
- Port KMP commits `d4ae07d` and `fde4e6f`; do not replay `a3bca98`, because current `master` already resolves JDK home from `jdk_home`, `JAVA_HOME`, and `java.home`.
- Keep the four KMP builders in `org.graphiks.kextract.kotlin.builders` and keep generated sources under `commonMain`, `jvmMain`, `nativeMain`, and `androidMain`.
- Do not introduce callback-registration behavior in this plan; generated callback output must remain byte-for-byte compatible with the pre-refresh generator.
- Do not hand-edit the generated `wgpu4k-native/src/*/wgpu_h*.kt` files.
- Prefix every shell command with `rtk`.

---

### Task 1: Forward-port the KMP generator onto current master

**Files:**
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpRefreshIntegrationTest.kt`
- Create from historical commit: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Create from historical commit: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Create from historical commit: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Create from historical commit: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/Options.kt`
- Modify through the historical patch: `kextract/src/main/kotlin/org/graphiks/kextract/clang/Cursor.kt`
- Modify through the historical patch: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/LayoutUtils.kt`
- Modify through the historical patch: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/NameMangler.kt`
- Modify through the historical patch: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/TreeMaker.kt`
- Modify through the historical patch: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/Utils.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/GeneratorIntegrationTest.kt`

**Interfaces:**
- Consumes: the current-master `KotlinGenerator.generate` overload ending in `useInitMethod: Boolean`.
- Produces: `Options.multiplatform: Boolean`, CLI flag `-m/--multiplatform`, and a final `multiplatform: Boolean = false` parameter on `KotlinGenerator.generate`.
- Produces: four KMP builders returning `List<KotlinSourceFile>` through `getFiles()`.

- [ ] **Step 1: Refresh the submodule remote and create the forward-port branch**

Run from the `wgpu4k-native` root:

```bash
rtk git -C kextract fetch origin master
rtk git -C kextract switch -c feature/kmp-refresh-v0.0.3 FETCH_HEAD
rtk git -C kextract status --short --branch
```

Expected: `kextract` is on `feature/kmp-refresh-v0.0.3`, based on the freshly fetched `master`, with a clean worktree.

- [ ] **Step 2: Write the failing KMP contract test**

Create `KmpRefreshIntegrationTest.kt` with a focused end-to-end helper and assertions for all four source sets:

```kotlin
package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpRefreshIntegrationTest : FreeSpec({
    fun generateKmp(header: String): Map<String, String> {
        val input = Files.createTempFile("kextract-kmp-refresh", ".h")
        val output = Files.createTempDirectory("kextract-kmp-refresh-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(
                    targetPackage = "sample.bindings",
                    outputDir = output.toString(),
                    multiplatform = true,
                ),
            ) shouldBe KextractTool.SUCCESS

            listOf("commonMain", "jvmMain", "nativeMain", "androidMain").associateWith { sourceSet ->
                Files.walk(output.resolve(sourceSet)).use { paths ->
                    paths.filter { it.fileName.toString().endsWith(".kt") }
                        .map { it.toFile().readText() }
                        .toList()
                        .joinToString("\n")
                }
            }
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    "multiplatform mode emits common, JVM, Native, and Android bindings" {
        val generated = generateKmp(
            """
            typedef struct WGPUDeviceImpl* WGPUDevice;
            WGPUDevice wgpuGetDevice(void);
            """.trimIndent(),
        )

        generated.keys shouldBe setOf("commonMain", "jvmMain", "nativeMain", "androidMain")
        generated.getValue("commonMain") shouldContain "expect value class WGPUDevice"
        generated.getValue("jvmMain") shouldContain "actual value class WGPUDevice"
        generated.getValue("nativeMain") shouldContain "actual value class WGPUDevice"
        generated.getValue("androidMain") shouldContain "actual value class WGPUDevice"
    }
})
```

- [ ] **Step 3: Run the test to verify it fails on master**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
```

Expected: compilation fails because `Options` has no `multiplatform` parameter.

- [ ] **Step 4: Apply the historical KMP commit without committing it**

Run:

```bash
rtk git -C kextract cherry-pick --no-commit d4ae07d
```

Expected: conflicts only in these four files:

```text
src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt
src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt
src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt
src/main/kotlin/org/graphiks/kextract/pipeline/Options.kt
```

- [ ] **Step 5: Resolve `Options` and `KextractCommand` while retaining master features**

The final tail of `Options` must include every current-master field plus `multiplatform`:

```kotlin
data class Options(
    val clangArgs: List<String> = emptyList(),
    val libraries: List<Library> = emptyList(),
    val useSystemLoadLibrary: Boolean = false,
    val targetPackage: String = "",
    val outputDir: String = ".",
    val sharedClassName: String? = null,
    val splitOutput: Boolean = false,
    val variadicArgs: Map<String, Int> = emptyMap(),
    val includeFrameworks: List<String> = emptyList(),
    val includeHelper: IncludeHelper = IncludeHelper(),
    val win32Mode: Boolean = false,
    val dllMap: DllMap? = null,
    val useInitMethod: Boolean = false,
    val multiplatform: Boolean = false,
)
```

Add the CLI flag after `initMethod` and pass it into `Options` without removing any master arguments:

```kotlin
val multiplatform by option("-m", "--multiplatform",
    help = "Generate Kotlin Multiplatform bindings using the kffi runtime",
).flag()

// In the Options constructor call
useInitMethod = initMethod,
multiplatform = multiplatform,
```

- [ ] **Step 6: Resolve `KotlinGenerator` with a KMP branch and an unchanged master branch**

Keep all master parameters and append `multiplatform`:

```kotlin
fun generate(
    scoped: Declaration.Scoped,
    headerName: String,
    targetPackage: String,
    libraries: List<Options.Library> = emptyList(),
    useSystemLoadLibrary: Boolean = false,
    splitOutput: Boolean = false,
    variadicArgs: Map<String, Int> = emptyMap(),
    win32Mode: Boolean = false,
    dllMap: DllMap? = null,
    useInitMethod: Boolean = false,
    multiplatform: Boolean = false,
): List<KotlinSourceFile> {
    val className = sanitizeClassName(headerName)
    if (multiplatform) return generateKmp(scoped, targetPackage, className)

    val toplevel = KotlinToplevelBuilder(
        targetPackage, className, headerName, libraries, useSystemLoadLibrary,
        splitOutput, variadicArgs, win32Mode, dllMap, useInitMethod,
    )
    scoped.accept(toplevel)
    return toplevel.getFiles().toMutableList().apply {
        if (toplevel.needsObjCRuntime) {
            add(ObjCRuntimeTemplate.generate(targetPackage))
            add(ObjCSubclassingTemplate.generate(targetPackage))
        }
    }
}
```

Because the four builders have distinct types, introduce this small private adapter:

```kotlin
private fun generateKmp(
    scoped: Declaration.Scoped,
    targetPackage: String,
    className: String,
): List<KotlinSourceFile> = buildList {
    KotlinKmpCommonBuilder(targetPackage, className).also { scoped.accept(it); addAll(it.getFiles()) }
    KotlinKmpJvmBuilder(targetPackage, className).also { scoped.accept(it); addAll(it.getFiles()) }
    KotlinKmpAndroidBuilder(targetPackage, className).also { scoped.accept(it); addAll(it.getFiles()) }
    KotlinKmpNativeBuilder(targetPackage, className).also { scoped.accept(it); addAll(it.getFiles()) }
}
```

- [ ] **Step 7: Resolve `KextractTool` without losing current-master behavior**

Preserve current `Throwable` parse handling, framework path resolution, and all master generator arguments. Append `options.multiplatform` to the generator call:

```kotlin
return KotlinGenerator().generate(
    transformed,
    headerName,
    options.targetPackage,
    options.libraries,
    options.useSystemLoadLibrary,
    options.splitOutput,
    options.variadicArgs,
    options.win32Mode,
    options.dllMap,
    options.useInitMethod,
    options.multiplatform,
)
```

Keep the historical `withStaticConstDeclarations(headers)` scan because the KMP WebGPU output relies on `static const` bit flags. For this first task, retain the historical source-set routing in `writeKotlin(results, outputDir, options)`; Task 3 replaces it with explicit source roots.

- [ ] **Step 8: Finish the conflict resolution and verify no master option was dropped**

Run:

```bash
rtk git -C kextract add build.gradle.kts src/main src/test
rtk git -C kextract diff --cached --check
rtk rg -n "splitOutput|variadicArgs|includeFrameworks|win32Mode|dllMap|useInitMethod|multiplatform" kextract/src/main/kotlin/org/graphiks/kextract
```

Expected: every master option and `multiplatform` appears in the pipeline; no conflict markers remain.

- [ ] **Step 9: Run the focused KMP and master regression tests**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.GeneratorIntegrationTest
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.impl.OptionsTest
```

Expected: all three commands pass. The normal generator still passes its variadic and file-layout tests.

- [ ] **Step 10: Commit the forward-port in the submodule**

Run:

```bash
rtk git -C kextract add .
rtk git -C kextract commit -m "feat: forward-port Kotlin multiplatform generation"
```

Expected: one `kextract` commit containing the KMP builders, the pipeline integration, the static-constant scan, and the focused regression test.

---

### Task 2: Restore KDoc extraction for KMP output

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/Declaration.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/clang/Cursor.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/clang/LibClang.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/TreeMaker.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpRefreshIntegrationTest.kt`

**Interfaces:**
- Consumes: `Declaration.Attribute` and libclang cursor comment functions.
- Produces: `DeclarationImpl.SourceComment` and emitted common-source KDoc.

- [ ] **Step 1: Add a failing KDoc preservation test**

Append to `KmpRefreshIntegrationTest`:

```kotlin
"KMP common output preserves C documentation" {
    val generated = generateKmp(
        """
        /** Completes all work submitted before this call. */
        void wgpuQueueDone(void);
        """.trimIndent(),
    )

    generated.getValue("commonMain") shouldContain
        "Completes all work submitted before this call."
}
```

- [ ] **Step 2: Run the test to verify the comment is missing**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
```

Expected: the KDoc assertion fails.

- [ ] **Step 3: Apply the historical KDoc commit without committing it**

Run:

```bash
rtk git -C kextract cherry-pick --no-commit fde4e6f
rtk git -C kextract diff --check
```

Expected: the patch adds `SourceComment`, cursor comment accessors, null-safe `CXStrToString`, TreeMaker attribute attachment, and KMP common KDoc emission. Resolve any context-only conflict by retaining current-master anonymous-field protections and adding the comment attachment at the beginning of `TreeMaker.addAttributes`.

- [ ] **Step 4: Run focused and full generator tests**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
rtk ./gradlew :kextract:test
```

Expected: both commands pass with no libclang shutdown crash and no Objective-C regression.

- [ ] **Step 5: Commit KDoc support in the submodule**

Run:

```bash
rtk git -C kextract add .
rtk git -C kextract commit -m "feat: preserve C documentation in KMP bindings"
```

---

### Task 3: Replace suffix-based output routing with explicit source roots

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/models/KotlinSourceFile.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpRefreshIntegrationTest.kt`

**Interfaces:**
- Produces: `KotlinSourceFile.sourceRoot: String` prepended before package and `subDirectory`.
- Preserves: `KotlinSourceFile.subDirectory` semantics for split Objective-C output.

- [ ] **Step 1: Add failing model and output-path assertions**

Add this independent path-layout test to `KmpRefreshIntegrationTest`:

```kotlin
"multiplatform output uses explicit source roots" {
    val workspace = Files.createTempDirectory("kextract-kmp-paths")
    val input = workspace.resolve("wgpu_fixture.h")
    val output = workspace.resolve("out")
    try {
        input.toFile().writeText(
            """
            typedef struct WGPUDeviceImpl* WGPUDevice;
            WGPUDevice wgpuGetDevice(void);
            """.trimIndent(),
        )
        KextractTool(Logger.DEFAULT).runGeneration(
            listOf(input.toString()),
            Options(
                targetPackage = "sample.bindings",
                outputDir = output.toString(),
                multiplatform = true,
            ),
        ) shouldBe KextractTool.SUCCESS

        Files.walk(output).use { paths ->
            paths.filter { it.fileName.toString().endsWith(".kt") }
                .map { output.relativize(it).toString().replace('\\', '/') }
                .toList()
                .toSet() shouldBe setOf(
                    "commonMain/kotlin/sample/bindings/wgpu_fixture_hCommon.kt",
                    "jvmMain/kotlin/sample/bindings/wgpu_fixture_hJvm.kt",
                    "nativeMain/kotlin/sample/bindings/wgpu_fixture_hNative.kt",
                    "androidMain/kotlin/sample/bindings/wgpu_fixture_hAndroid.kt",
                    "androidMain/kotlin/sample/bindings/android/wgpu_fixture_h.kt",
                )
        }
    } finally {
        workspace.toFile().deleteRecursively()
    }
}
```

Import `org.graphiks.kextract.kotlin.models.KotlinSourceFile` at the top of the test file. Then add a model assertion proving `sourceRoot` and `subDirectory` compose in the correct order:

```kotlin
KotlinSourceFile(
    packageName = "sample.bindings",
    className = "Types",
    contents = "",
    subDirectory = "generated",
    sourceRoot = "commonMain/kotlin",
).getPath().toString().replace('\\', '/') shouldBe
    "commonMain/kotlin/sample/bindings/generated/Types.kt"
```

- [ ] **Step 2: Run the test to verify `sourceRoot` is absent**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
```

Expected: test compilation fails because `KotlinSourceFile` has no `sourceRoot` parameter.

- [ ] **Step 3: Add `sourceRoot` to `KotlinSourceFile`**

Use this path implementation:

```kotlin
data class KotlinSourceFile(
    val packageName: String,
    val className: String,
    val contents: String,
    val subDirectory: String = "",
    val sourceRoot: String = "",
) {
    fun getPath(): Path {
        var path = Path.of(packageName.replace('.', '/'))
        if (subDirectory.isNotEmpty()) path = path.resolve(subDirectory)
        path = path.resolve("$className.kt")
        return if (sourceRoot.isEmpty()) path else Path.of(sourceRoot).resolve(path)
    }

    fun getQualifiedName(): String = "$packageName.$className"
}
```

- [ ] **Step 4: Make every KMP builder declare its source root**

Change the final `KotlinSourceFile` creation in each builder to the matching explicit root. The Android builder emits both the KMP actual file and its raw JNA support file, so set the root on both:

```kotlin
// Common
KotlinSourceFile(targetPackage, className + "Common", builder.toString(), sourceRoot = "commonMain/kotlin")

// JVM
KotlinSourceFile(targetPackage, className + "Jvm", builder.toString(), sourceRoot = "jvmMain/kotlin")

// Native
KotlinSourceFile(targetPackage, className + "Native", builder.toString(), sourceRoot = "nativeMain/kotlin")

// Android
KotlinSourceFile(targetPackage, className + "Android", builder.toString(), sourceRoot = "androidMain/kotlin")
KotlinSourceFile(targetPackage + ".android", className, jnaBuilder.toString(), sourceRoot = "androidMain/kotlin")
```

- [ ] **Step 5: Simplify `KextractTool.writeKotlin`**

Remove the class-name suffix switch and write the model path directly:

```kotlin
private fun writeKotlin(results: List<KotlinSourceFile>, outputDir: Path): Int = try {
    for (result in results) {
        val outputPath = outputDir.resolve(result.getPath())
        outputPath.parent.createDirectories()
        outputPath.writeText(result.contents)
    }
    SUCCESS
} catch (e: Exception) {
    System.err.println("Error writing Kotlin files: ${e.message}")
    OUTPUT_ERROR
}
```

Update the caller to `writeKotlin(results, outputDir)`.

- [ ] **Step 6: Run KMP, split-output, and full tests**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.ObjCGeneratorIntegrationTest
rtk ./gradlew :kextract:test
```

Expected: all commands pass; KMP paths start with their source set, while Objective-C `subDirectory` remains below the package.

- [ ] **Step 7: Commit explicit output routing**

Run:

```bash
rtk git -C kextract add .
rtk git -C kextract commit -m "refactor: model KMP source roots explicitly"
```

---

### Task 4: Validate the refreshed generator in `wgpu4k-native`

**Files:**
- Modify submodule pointer: `kextract`
- Modify: `.github/workflows/test.yml`
- Verify unchanged: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt`
- Verify unchanged: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt`
- Verify unchanged: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt`
- Verify unchanged: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt`

**Interfaces:**
- Consumes: refreshed `kextract` branch from Tasks 1-3.
- Produces: a root commit pointing to the refreshed submodule and running `:kextract:test` in CI.

- [ ] **Step 1: Run the complete submodule test suite from the root build**

Run:

```bash
rtk ./gradlew :kextract:test
```

Expected: all `kextract` tests pass. If an old unstable test fails, isolate that named test with a Kotest/JUnit tag and document the exact upstream defect; do not exclude the entire `:kextract:test` task.

- [ ] **Step 2: Regenerate WebGPU bindings and require a clean generated diff**

Run:

```bash
rtk ./gradlew fetch-native-dependencies
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
rtk git diff --exit-code -- wgpu4k-native/src
```

Expected: no diff. Callback generation changes belong to the second implementation plan, not this refresh.

- [ ] **Step 3: Enable `kextract` tests in CI**

Change the build step in `.github/workflows/test.yml` from:

```yaml
./gradlew build -x :kextract:test
```

to:

```yaml
./gradlew build
```

- [ ] **Step 4: Run root compilation and headless smoke tests**

Run:

```bash
rtk ./gradlew :kffi:jvmTest :wgpu4k-native:jvmTest :kextract:test
rtk ./gradlew :demo:desktop-and-ios:verifyJvmHeadlessRender
rtk ./gradlew :demo:desktop-and-ios:verifyNativeHeadlessRender
```

Expected: every command passes on the host platform.

- [ ] **Step 5: Commit the submodule refresh in `wgpu4k-native`**

Run:

```bash
rtk git add kextract .github/workflows/test.yml
rtk git commit -m "build: refresh kextract KMP generator"
```

Expected: the root commit changes only the submodule pointer and CI exclusion; generated bindings remain unchanged.
