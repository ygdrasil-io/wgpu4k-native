# Android Restoration Design

## Objective

Restore both supported Android paths after the header-based binding refactor:

- Android/JVM through generated JNA bindings.
- Android Native through Kotlin/Native shared libraries packaged by the Android application.

The result must be reproducible from a clean checkout, must not rely on manually invoking hidden Gradle tasks, and must remain valid after regenerating the WebGPU bindings.

## Scope

The implementation covers:

1. JNA function generation for every supported C function emitted in the Android KMP source set.
2. JNA callback trampolines and safe callback registration through the existing `kffi` callback runtime.
3. Variant-aware Android Native JNI packaging for debug and release.
4. Android/JVM resource cleanup and ABI declaration.
5. Generator, build, APK-content, and Android runtime regression tests.
6. CI changes needed to stop excluding the complete Android unit-test suite and to validate Android artifacts.

Unrelated WebGPU API redesigns, replacement of JNA with JNI, and migration to AGP 10 are outside this change.

## Repository and Branch Boundaries

The parent `wgpu4k-native` repository is developed on `fix/android-restoration`.

`kextract` is a Git submodule and its generator changes must be developed on a dedicated `fix/android-jna-bindings` branch. The submodule branch is intended for a separate pull request later. This work must not create that pull request. The parent repository will record the resulting `kextract` submodule commit together with its Android application and CI changes.

## Chosen Architecture

The Android/JVM path remains JNA-based. `kextract` will generate the JNA function interface and the KMP-to-JNA bridge from the same normalized C ABI model already used by the JVM FFM and Kotlin/Native emitters.

The Android Native path remains Kotlin/Native-based. Gradle will expose variant-specific generated JNI directories and wire their production into the matching Android application variant.

Maintaining both paths is intentional: Android/JVM provides the regular Kotlin Android integration, while Android Native proves and supports the Kotlin/Native route.

## Android/JVM Components

### Raw JNA API

`KotlinKmpAndroidBuilder` will emit a raw JNA library interface next to the generated JNA structures. The interface is loaded lazily with `Native.load("wgpu4k", ...)`, so compiling or loading unrelated generated classes does not require the native library.

Each raw method uses a carrier derived from the normalized C ABI:

- C addresses and opaque handles become nullable JNA `Pointer` values.
- Integer, floating-point, and Boolean ABI scalars become the corresponding signed JVM primitive required by JNA, with unsigned conversion in the bridge.
- Records passed by value become the generated JNA `Structure.ByValue` or `Union.ByValue` type.
- `void` becomes Kotlin `Unit`.

If an ABI type cannot be represented, generation fails with the function name, parameter or return position, and unsupported C type. The generator must never replace an unsupported function with a runtime stub.

### KMP Bridge

The generated Android `actual` function performs three steps:

1. Convert public KMP values into raw JNA carriers, writing structure storage before passing its pointer.
2. Invoke the corresponding raw JNA library method.
3. Convert the result into the public KMP representation, including unsigned scalars, nullable handles, structure values, and `Unit`.

Output structures continue to synchronize lazily through their generated JNA-backed getters. Returned null pointers become Kotlin `null`; non-null opaque pointers are wrapped in `NativeAddress` and the public handle type.

### Callbacks

Each generated callback type receives a singleton JNA callback trampoline with the exact raw ABI signature. Its function pointer is obtained through JNA and registered with the existing `CallbackRuntime`.

The singleton owns a strong reference to the JNA callback object for process lifetime, preventing garbage collection while native code can call it. `CallbackRuntime` remains responsible for:

- `ONCE` and `REPEATING` policy enforcement.
- Routing through `userdata` when available.
- Closing registrations and waiting for in-flight delivery to quiesce.
- Catching Kotlin exceptions and forwarding them to `CallbackExceptionHandler` without crossing the native boundary.

Direct callback bindings and callback-info factories use the same prepared-registration flow as the JVM and Native emitters. Android-specific generation supplies the JNA trampoline pointer and raw call conversion.

## Android Native Packaging

The existing shared output directory is replaced with two variant-specific generated JNI roots:

- `build/androidJniLibs/debug/<abi>/libwgpu4k_demo.so`
- `build/androidJniLibs/release/<abi>/libwgpu4k_demo.so`

For each variant, one synchronization task:

- Depends explicitly on the ARM64 and x86_64 Kotlin/Native shared-library link tasks for the same build type.
- Copies only those two outputs into its own generated JNI root.
- Declares all inputs and outputs to Gradle.
- Is wired to the matching Android variant before native-library merging.

Consequently, `assembleDebug` cannot package release binaries or omit the libraries, and parallel debug/release builds cannot overwrite one another.

The Android/JVM application declares only `arm64-v8a` and `x86_64`, matching the ABIs for which both `libwgpu4k.so` and the helper library are packaged.

## Error Handling and Resource Lifetime

Native library loading stays lazy. A missing library or ABI produces the original `UnsatisfiedLinkError` at the first native call rather than an unrelated initialization failure.

Generated callbacks catch every `Throwable` at the native entry point. Routed failures are delivered to the registration handler; failures that cannot be routed use the existing global reporting path.

Android/JVM teardown closes `HelloTriangleScene` before releasing device, adapter, surface, and instance. This releases the scene-owned render pipeline and queue in the same order as the desktop implementation.

No generated Android function or supported callback registration may contain a deliberate `error(...)` or `UnsupportedOperationException` fallback.

## Test Strategy

Implementation follows red-green-refactor cycles.

### Generator Tests

An integration fixture first demonstrates the current failure by asserting that a generated Android function has a callable JNA bridge. The initial assertion fails because the bridge contains the current `not implemented` stub.

Subsequent fixtures cover:

- Nullable and non-null pointers.
- Opaque handle parameters and results.
- Signed and unsigned scalar parameters and results.
- Records and unions passed by value.
- Output structures passed by pointer.
- `void` functions.
- Direct callbacks and callback-info bindings.

Generated common, Android bridge, and raw JNA sources are compiled together with the existing Kotlin compiler test harness. Assertions also guarantee that generated Android functions and supported callback operations contain no unsupported fallback text.

### Gradle and APK Tests

From a state with no generated JNI directory:

1. `assembleDebug` must run both debug Kotlin/Native link tasks.
2. The debug APK must contain ARM64 and x86_64 `libwgpu4k_demo.so` files.
3. `assembleRelease` must run both release link tasks.
4. The release APK must contain both release libraries.
5. Debug and release source paths must be distinct and remain stable when both variants build in parallel.
6. APK alignment and 16 KiB ELF segment alignment checks must continue to pass.

### Android Runtime Tests

An instrumentation smoke test loads the packaged library, calls `wgpuGetVersion`, creates a WebGPU instance, and releases it. This validates loading, a scalar return, a nullable structure pointer argument, an opaque handle result, and cleanup.

The currently excluded `:demo:common:testDebugUnitTest` suite is restored. Tests that fundamentally require Android native execution are moved or filtered narrowly instead of excluding the complete suite. Pure callback-coordination and cleanup tests continue to run on the host JVM.

### CI

CI builds both Android applications from clean generated JNI directories and inspects their APK contents. The Android runtime smoke test runs on an emulator where the workflow environment supports hardware or software acceleration. Generator tests run in the `kextract` repository and in the parent build through the pinned submodule commit.

## Acceptance Criteria

The change is complete when all of the following hold:

1. Regenerating WebGPU bindings produces no Android function stub.
2. Every generated Android function has a raw JNA declaration and a compiling KMP bridge.
3. Supported safe callback registration is generated for Android/JNA.
4. Android/JVM starts far enough to create and release a WebGPU instance on an emulator.
5. `assembleDebug` and `assembleRelease` independently produce Android Native APKs containing both expected libraries.
6. Debug and release JNI outputs cannot overwrite one another.
7. Android/JVM APK metadata advertises only ARM64 and x86_64.
8. The Android unit-test suite is no longer wholly excluded from CI.
9. Targeted generator, Android unit, instrumentation, packaging, alignment, and signature checks pass.
10. The parent worktree and the `kextract` submodule contain only intentional changes on their respective branches.
