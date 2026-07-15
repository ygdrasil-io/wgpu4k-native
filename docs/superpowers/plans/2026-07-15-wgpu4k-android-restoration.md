# wgpu4k Android Restoration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Integrate the fixed `kextract` generator, restore Android/JVM execution, make Android Native packaging variant-safe, and enforce both paths in CI.

**Architecture:** Pin the tested `kextract` submodule commit and regenerate all WebGPU sources. Keep Android/JVM on JNA with two explicit ABIs, and wire each Android Native application variant to matching Kotlin/Native link outputs through variant-specific JNI directories.

**Tech Stack:** Kotlin Multiplatform 2.3.21, JNA, Kotlin/Native, Android Gradle Plugin 9.0, Gradle 9.5, AndroidX Test, GitHub Actions.

## Global Constraints

- Perform parent-repository work on branch `fix/android-restoration`.
- Consume the `kextract` commit produced on `fix/android-jna-bindings`; do not create the `kextract` PR in this plan.
- Preserve both Android/JVM and Android Native targets.
- Package only `arm64-v8a` and `x86_64`.
- Keep debug and release JNI outputs in distinct directories.
- Preserve 16 KiB ELF segment alignment.
- Do not suppress the complete Android unit-test suite.
- Every production-code change must follow a test observed failing for the intended reason.

---

### Task 1: Pin `kextract` and regenerate callable Android bindings

**Files:**
- Modify: `kextract` submodule pointer
- Modify: `wgpu4k-native/build.gradle.kts`
- Regenerate: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt`
- Regenerate: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt`
- Regenerate: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt`
- Regenerate: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt`
- Regenerate: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/android/wgpu_h.kt`

**Interfaces:**
- Consumes: tested `kextract` Android/JNA emitter.
- Produces: 226 callable Android `actual` functions and safe Android callback registration.

- [ ] **Step 1: Verify the current generated source is RED**

Run:

```bash
test "$(rg -c 'not implemented for Android/JNA generated bindings' wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt)" -eq 0
```

Expected: FAIL because the current count is 226.

- [ ] **Step 2: Confirm the submodule commit and branch**

Run:

```bash
git -C kextract status --short --branch
git -C kextract rev-parse HEAD
```

Expected: clean branch `fix/android-jna-bindings` at the tested commit from the generator plan.

- [ ] **Step 3: Configure the Android library name used by generated JNA**

Change the generation argument in `wgpu4k-native/build.gradle.kts`:

```kotlin
"--library", "wgpu4k",
```

The JVM KMP emitter uses `kffi.findOrThrow` and is unaffected by this Android JNA library name; the packaged Android file is `libwgpu4k.so`.

- [ ] **Step 4: Regenerate bindings**

Run:

```bash
./gradlew :wgpu4k-native:generateBindingsFromHeader
```

Expected: `BUILD SUCCESSFUL`; tracked generated Android sources change from stubs to JNA calls.

- [ ] **Step 5: Verify generated source and compile both Android variants**

Run:

```bash
test "$(rg -c 'not implemented for Android/JNA generated bindings' wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt || true)" -eq 0
test "$(rg -c 'Android/JNA callback registration is not supported' wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt || true)" -eq 0
./gradlew :wgpu4k-native:compileDebugKotlinAndroid :wgpu4k-native:compileReleaseKotlinAndroid
```

Expected: both counts are zero and both compilation tasks pass.

- [ ] **Step 6: Commit the submodule pin and generated bindings**

Run:

```bash
git add kextract wgpu4k-native/build.gradle.kts wgpu4k-native/src
git commit -m "fix: restore generated Android JNA bindings"
```

---

### Task 2: Restore the complete Android host-unit suite

**Files:**
- Modify: `demo/common/src/commonMain/kotlin/ext.kt`
- Modify: `demo/common/src/commonTest/kotlin/HelloTriangleSceneTest.kt`
- Create: `demo/common/src/commonTest/kotlin/TestNativeAddress.kt`
- Create: `demo/common/src/androidUnitTest/kotlin/TestNativeAddress.android.kt`
- Create: `demo/common/src/jvmTest/kotlin/TestNativeAddress.jvm.kt`
- Create: `demo/common/src/nativeTest/kotlin/TestNativeAddress.native.kt`

**Interfaces:**
- Produces: `expect fun testNativeAddress(): NativeAddress` for allocation-free test handles.

- [ ] **Step 1: Re-run the existing failing suite and confirm RED**

Run:

```bash
./gradlew :demo:common:testDebugUnitTest --rerun-tasks
```

Expected: 36 tests run and four fail with `InstantiationError: java.lang.foreign.SegmentAllocator` or its initialization cascade.

- [ ] **Step 2: Remove the unused eager allocator**

Delete both the unused import and declaration from `ext.kt`:

```kotlin
import io.ygdrasil.kffi.MemoryAllocator
val allocator = MemoryAllocator()
```

This prevents pure callback-result tests from initializing the Android memory shim.

- [ ] **Step 3: Add a platform test-address abstraction**

Create the common declaration:

```kotlin
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress

expect fun testNativeAddress(): NativeAddress
```

Create Android actual:

```kotlin
package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.kffi.NativeAddress

actual fun testNativeAddress(): NativeAddress = Pointer(1L)
```

Create JVM actual:

```kotlin
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import java.lang.foreign.MemorySegment

actual fun testNativeAddress(): NativeAddress = NativeAddress(MemorySegment.ofAddress(1L))
```

Create Native actual:

```kotlin
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress

actual fun testNativeAddress(): NativeAddress = NativeAddress(1L)
```

- [ ] **Step 4: Make `HelloTriangleSceneTest` allocation-free**

Replace its `memoryScope` wrapper and allocation with:

```kotlin
@Test
fun closeReleasesPipelineBeforeQueueExactlyOnce() {
    val handle = testNativeAddress()
    val releases = mutableListOf<String>()
    val scene = HelloTriangleScene(
        device = WGPUDevice(handle),
        renderingContextFormat = WGPUTextureFormat_RGBA8Unorm,
        surface = WGPUSurface(handle),
        queue = WGPUQueue(handle),
        releaseQueue = { releases += "queue" },
        releaseRenderPipeline = { releases += "pipeline" },
    )

    scene.ownRenderPipeline(WGPURenderPipeline(handle))
    scene.close()
    scene.close()

    assertEquals(listOf("pipeline", "queue"), releases)
    assertNull(scene.renderPipeline)
}
```

- [ ] **Step 5: Verify GREEN on Android and JVM**

Run:

```bash
./gradlew :demo:common:testDebugUnitTest :demo:common:jvmTest --rerun-tasks
```

Expected: all 36 Android host-unit tests pass and the JVM suite passes.

- [ ] **Step 6: Commit the test portability fix**

Run:

```bash
git add demo/common/src
git commit -m "test: make common GPU cleanup tests Android-safe"
```

---

### Task 3: Enforce Android/JVM cleanup, ABI scope, and runtime smoke coverage

**Files:**
- Create: `demo/android/src/androidUnitTest/kotlin/AndroidResourceCleanupTest.kt`
- Create: `demo/android/src/androidInstrumentedTest/kotlin/AndroidJnaSmokeTest.kt`
- Modify: `demo/android/src/androidMain/kotlin/MainActivity.kt`
- Modify: `demo/android/build.gradle.kts`
- Modify: `gradle/libs.versions.toml`

**Interfaces:**
- Produces: `releaseAndroidResources(...)` with injectable release functions for deterministic cleanup testing.

- [ ] **Step 1: Write the failing resource-order unit test**

Create:

```kotlin
package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidResourceCleanupTest {
    @Test
    fun sceneClosesBeforeParentGpuHandles() {
        val handle = Pointer(1L)
        val releases = mutableListOf<String>()
        val scene = HelloTriangleScene(
            device = WGPUDevice(handle),
            renderingContextFormat = WGPUTextureFormat_RGBA8Unorm,
            surface = WGPUSurface(handle),
            queue = WGPUQueue(handle),
            releaseQueue = { releases += "queue" },
            releaseRenderPipeline = { releases += "pipeline" },
        ).also { it.ownRenderPipeline(WGPURenderPipeline(handle)) }

        releaseAndroidResources(
            scene = scene,
            device = WGPUDevice(handle),
            adapter = WGPUAdapter(handle),
            surface = WGPUSurface(handle),
            instance = WGPUInstance(handle),
            releaseDevice = { releases += "device" },
            releaseAdapter = { releases += "adapter" },
            releaseSurface = { releases += "surface" },
            releaseInstance = { releases += "instance" },
        )

        assertEquals(
            listOf("pipeline", "queue", "device", "adapter", "surface", "instance"),
            releases,
        )
    }
}
```

- [ ] **Step 2: Run the cleanup test and verify RED**

Run:

```bash
./gradlew :demo:android:testDebugUnitTest --tests '*AndroidResourceCleanupTest*'
```

Expected: compilation fails because `releaseAndroidResources` does not exist.

- [ ] **Step 3: Implement cleanup and call it from the handler**

Add this internal function beside the handler:

```kotlin
internal fun releaseAndroidResources(
    scene: HelloTriangleScene?,
    device: WGPUDevice?,
    adapter: WGPUAdapter?,
    surface: WGPUSurface?,
    instance: WGPUInstance?,
    releaseDevice: (WGPUDevice) -> Unit = ::wgpuDeviceRelease,
    releaseAdapter: (WGPUAdapter) -> Unit = ::wgpuAdapterRelease,
    releaseSurface: (WGPUSurface) -> Unit = ::wgpuSurfaceRelease,
    releaseInstance: (WGPUInstance) -> Unit = ::wgpuInstanceRelease,
) {
    scene?.close()
    device?.let(releaseDevice)
    adapter?.let(releaseAdapter)
    surface?.let(releaseSurface)
    instance?.let(releaseInstance)
}
```

Call it before nulling fields in `releaseResources`.

- [ ] **Step 4: Add an APK ABI verifier and observe RED**

Add to `demo/android/build.gradle.kts`:

```kotlin
tasks.register("verifyAndroidJvmApk") {
    dependsOn("assembleDebug")
    doLast {
        val apk = layout.buildDirectory.file("outputs/apk/debug/android-debug.apk").get().asFile
        require(apk.isFile) { "Missing APK: $apk" }
        java.util.zip.ZipFile(apk).use { zip ->
            val actualAbis = zip.entries().asSequence()
                .map { it.name }
                .filter { it.startsWith("lib/") && it.endsWith(".so") }
                .map { it.substringAfter("lib/").substringBefore('/') }
                .toSet()
            require(actualAbis == setOf("arm64-v8a", "x86_64")) {
                "Unexpected Android/JVM APK ABIs: $actualAbis"
            }
        }
    }
}
```

Run:

```bash
./gradlew :demo:android:verifyAndroidJvmApk
```

Expected: FAIL because the JNA dependency currently contributes unsupported ABI directories.

- [ ] **Step 5: Restrict Android/JVM to packaged ABIs and verify GREEN**

Inside `defaultConfig` add:

```kotlin
ndk {
    abiFilters += setOf("arm64-v8a", "x86_64")
}
```

Run:

```bash
./gradlew :demo:android:verifyAndroidJvmApk
```

Expected: PASS with exactly `arm64-v8a` and `x86_64`.

- [ ] **Step 6: Add AndroidX test dependencies and the smoke test**

Add to `gradle/libs.versions.toml`:

```toml
androidx-test-ext-junit = { module = "androidx.test.ext:junit", version = "1.2.1" }
androidx-test-runner = { module = "androidx.test:runner", version = "1.6.2" }
```

Add source-set dependencies:

```kotlin
androidInstrumentedTest.dependencies {
    implementation(kotlin("test"))
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.runner)
}
```

Create the smoke test:

```kotlin
package io.ygdrasil.wgpu

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidJnaSmokeTest {
    @Test
    fun loadsVersionAndCreatesInstance() {
        assertNotEquals(0u, wgpuGetVersion())
        val instance = assertNotNull(wgpuCreateInstance(null))
        wgpuInstanceRelease(instance)
    }
}
```

- [ ] **Step 7: Verify host cleanup and instrumentation compilation**

Run:

```bash
./gradlew :demo:android:testDebugUnitTest :demo:android:assembleDebugAndroidTest :demo:android:verifyAndroidJvmApk
```

Expected: both tasks pass.

- [ ] **Step 8: Commit Android/JVM lifecycle and smoke coverage**

Run:

```bash
git add demo/android gradle/libs.versions.toml
git commit -m "fix: validate Android JNA startup and cleanup"
```

---

### Task 4: Make Android Native JNI packaging variant-safe

**Files:**
- Modify: `demo/common/build.gradle.kts`
- Modify: `demo/android-native/build.gradle.kts`

**Interfaces:**
- Produces: `androidJniLibs/debug/<abi>` and `androidJniLibs/release/<abi>`, plus `verifyAndroidNativeApks`.

- [ ] **Step 1: Add an APK-content verification task before changing packaging**

Add to `demo/android-native/build.gradle.kts`:

```kotlin
fun verifyNativeLibraries(apk: File) {
    require(apk.isFile) { "Missing APK: $apk" }
    java.util.zip.ZipFile(apk).use { zip ->
        listOf(
            "lib/arm64-v8a/libwgpu4k_demo.so",
            "lib/x86_64/libwgpu4k_demo.so",
        ).forEach { entry ->
            require(zip.getEntry(entry) != null) { "$apk is missing $entry" }
        }
    }
}

tasks.register("verifyAndroidNativeDebugApk") {
    dependsOn("assembleDebug")
    doLast {
        verifyNativeLibraries(layout.buildDirectory.file("outputs/apk/debug/android-native-debug.apk").get().asFile)
    }
}

tasks.register("verifyAndroidNativeReleaseApk") {
    dependsOn("assembleRelease")
    doLast {
        verifyNativeLibraries(layout.buildDirectory.file("outputs/apk/release/android-native-release-unsigned.apk").get().asFile)
    }
}

tasks.register("verifyAndroidNativeApks") {
    dependsOn("verifyAndroidNativeDebugApk", "verifyAndroidNativeReleaseApk")
}
```

- [ ] **Step 2: Delete generated JNI output and verify RED**

Run:

```bash
rm -rf demo/common/build/androidJniLibs demo/android-native/build
./gradlew :demo:android-native:verifyAndroidNativeDebugApk
```

Expected: FAIL because `assembleDebug` does not run either Kotlin/Native link task and the APK lacks `libwgpu4k_demo.so`.

- [ ] **Step 3: Separate debug and release copy destinations**

In `demo/common/build.gradle.kts`, derive the output directory from each binary's build type:

```kotlin
val variant = buildType.name.lowercase()
val destDir = project.layout.buildDirectory
    .dir("androidJniLibs/$variant/$androidArch")
    .get()
    .asFile
```

Keep the copy attached to the link task, retain overwrite behavior, and preserve the linker options:

```kotlin
linkerOpts("-Wl,-z,max-page-size=16384", "-Wl,-z,common-page-size=16384")
```

- [ ] **Step 4: Wire source sets and link dependencies by variant**

Replace the `main` JNI directory with:

```kotlin
sourceSets {
    getByName("debug") {
        jniLibs.srcDirs(file("../common/build/androidJniLibs/debug"))
    }
    getByName("release") {
        jniLibs.srcDirs(file("../common/build/androidJniLibs/release"))
    }
}
```

Then wire the Android merge tasks:

```kotlin
tasks.named("mergeDebugJniLibFolders") {
    dependsOn(
        ":demo:common:linkDebugSharedAndroidNativeArm64",
        ":demo:common:linkDebugSharedAndroidNativeX64",
    )
}
tasks.named("mergeReleaseJniLibFolders") {
    dependsOn(
        ":demo:common:linkReleaseSharedAndroidNativeArm64",
        ":demo:common:linkReleaseSharedAndroidNativeX64",
    )
}
```

- [ ] **Step 5: Verify GREEN from empty output directories**

Run:

```bash
rm -rf demo/common/build/androidJniLibs demo/android-native/build
./gradlew :demo:android-native:verifyAndroidNativeApks --parallel
```

Expected: both variants pass and all four variant/ABI link tasks execute.

- [ ] **Step 6: Confirm independent JNI sources**

Run:

```bash
test -f demo/common/build/androidJniLibs/debug/arm64-v8a/libwgpu4k_demo.so
test -f demo/common/build/androidJniLibs/debug/x86_64/libwgpu4k_demo.so
test -f demo/common/build/androidJniLibs/release/arm64-v8a/libwgpu4k_demo.so
test -f demo/common/build/androidJniLibs/release/x86_64/libwgpu4k_demo.so
```

Expected: all checks pass.

- [ ] **Step 7: Commit variant-safe packaging**

Run:

```bash
git add demo/common/build.gradle.kts demo/android-native/build.gradle.kts
git commit -m "fix: wire Android Native libraries by variant"
```

---

### Task 5: Enforce Android coverage in CI

**Files:**
- Modify: `.github/workflows/test.yml`
- Modify: `.github/workflows/snapshot.yml`

**Interfaces:**
- Consumes: `verifyAndroidNativeApks`, `connectedDebugAndroidTest`, restored host-unit suite.
- Produces: blocking artifact checks and Android/JVM emulator smoke coverage.

- [ ] **Step 1: Remove complete-suite exclusions**

Delete this argument from both workflows:

```text
-x :demo:common:testDebugUnitTest
```

- [ ] **Step 2: Add explicit Android artifact verification on Ubuntu**

Add to the existing test job:

```yaml
      - name: Verify Android unit tests and APK native libraries
        if: matrix.os == 'ubuntu-latest'
        run: >-
          ./gradlew
          :demo:common:testDebugUnitTest
          :demo:android:verifyAndroidJvmApk
          :demo:android-native:verifyAndroidNativeApks
```

- [ ] **Step 3: Add a blocking emulator smoke job**

Add this separate blocking Ubuntu job:

```yaml
  android-smoke:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          submodules: recursive
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 25
          cache: gradle
      - name: Enable KVM
        run: |
          echo 'KERNEL=="kvm", GROUP="kvm", MODE="0666", OPTIONS+="static_node=kvm"' \
            | sudo tee /etc/udev/rules.d/99-kvm4all.rules
          sudo udevadm control --reload-rules
          sudo udevadm trigger --name-match=kvm
      - name: Fetch native dependencies
        run: ./gradlew fetch-native-dependencies
      - name: Run Android JNA smoke test
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 34
          target: google_apis
          arch: x86_64
          force-avd-creation: false
          emulator-options: -no-window -gpu swiftshader_indirect -no-snapshot -no-audio -no-boot-anim
          disable-animations: true
          script: ./gradlew :demo:android:connectedDebugAndroidTest --no-daemon --stacktrace
```

Do not add `continue-on-error`; the smoke test protects the restored Android/JNA runtime.

- [ ] **Step 4: Validate workflow syntax and local task graph**

Run:

```bash
./gradlew :demo:common:testDebugUnitTest :demo:android-native:verifyAndroidNativeApks
./gradlew :demo:android:assembleDebugAndroidTest
```

Expected: all tasks pass.

- [ ] **Step 5: Commit CI enforcement**

Run:

```bash
git add .github/workflows/test.yml .github/workflows/snapshot.yml
git commit -m "ci: enforce Android runtime and packaging checks"
```

---

### Task 6: Run final Android verification

**Files:**
- Inspect: files changed in Tasks 1–5.

**Interfaces:**
- Produces: evidence that both Android paths are releasable from a clean generated state.

- [ ] **Step 1: Run generator and host tests**

Run:

```bash
./gradlew :kextract:test :kffi:testDebugUnitTest :wgpu4k-native:testDebugUnitTest :demo:common:testDebugUnitTest :demo:common:jvmTest
```

Expected: `BUILD SUCCESSFUL` and no failed tests.

- [ ] **Step 2: Rebuild both Android applications**

Run:

```bash
rm -rf demo/common/build/androidJniLibs demo/android-native/build
./gradlew :demo:android:assembleDebug :demo:android:assembleRelease :demo:android-native:verifyAndroidNativeApks --parallel
```

Expected: all APK tasks pass; Android Native link tasks are present in the task execution graph.

- [ ] **Step 3: Verify generated source and ABI declarations**

Run:

```bash
test "$(rg -c 'not implemented for Android/JNA generated bindings' wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt || true)" -eq 0
test "$(rg -c 'Android/JNA callback registration is not supported' wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt || true)" -eq 0
apkanalyzer manifest native-code demo/android/build/outputs/apk/debug/android-debug.apk
```

Expected: both stub counts are zero and native code is limited to `arm64-v8a` and `x86_64`.

- [ ] **Step 4: Verify 16 KiB alignment and APK alignment**

Run:

```bash
zipalign -c -P 16 -v 4 demo/android/build/outputs/apk/debug/android-debug.apk
zipalign -c -P 16 -v 4 demo/android-native/build/outputs/apk/debug/android-native-debug.apk
llvm-readelf -lW demo/common/build/androidJniLibs/debug/arm64-v8a/libwgpu4k_demo.so
llvm-readelf -lW demo/common/build/androidJniLibs/debug/x86_64/libwgpu4k_demo.so
llvm-readelf -lW wgpu4k-native/build/native/libs/arm64-v8a/libwgpu4k.so
llvm-readelf -lW wgpu4k-native/build/native/libs/x86_64/libwgpu4k.so
```

Expected: `zipalign -c -P 16 -v 4` succeeds and each ELF `LOAD` segment reports alignment `0x4000`.

- [ ] **Step 5: Run the connected smoke test**

Run:

```bash
./gradlew :demo:android:connectedDebugAndroidTest
```

Expected: `AndroidJnaSmokeTest` passes on the connected x86_64 emulator.

- [ ] **Step 6: Verify repository boundaries**

Run:

```bash
git status --short --branch
git log --oneline --decorate -8
git -C kextract status --short --branch
git diff main...HEAD --check
```

Expected: parent branch `fix/android-restoration`, submodule branch `fix/android-jna-bindings`, clean worktrees, and no whitespace errors.
