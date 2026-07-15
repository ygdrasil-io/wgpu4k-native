# kextract Android/JNA Bindings Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Generate callable Android/JNA functions and callback trampolines in KMP mode instead of runtime stubs.

**Architecture:** Extend the existing normalized KMP C ABI pipeline so the Android emitter produces a raw JNA `Library` interface plus a public KMP bridge. Generate one strongly held JNA callback trampoline per callback type and route it through the existing `CallbackRuntime`.

**Tech Stack:** Kotlin 2.3, libclang AST, JNA, Kotest, Kotlin compiler integration tests, Gradle 9.5.

## Global Constraints

- Perform all work inside the `kextract` submodule on branch `fix/android-jna-bindings`.
- Do not create or push a pull request; the branch is prepared for a separate PR later.
- Preserve common, JVM FFM, and Kotlin/Native generated output.
- Use the normalized `KotlinKmpCAbiType`/`KotlinKmpAbiIndex`; do not build a second ABI model.
- An unsupported Android ABI shape must fail generation with context and must never emit a runtime stub.
- Keep native loading lazy.
- Every production change must follow a test that was observed failing for the intended reason.

---

### Task 1: Generate raw JNA functions and callable KMP bridges

**Files:**
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpAndroidJnaAbiTest.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpRuntimeSymbol.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`

**Interfaces:**
- Consumes: `Options.libraries`, `KotlinKmpAbiIndex`, `KmpTypeMapper`, generated JNA record wrappers.
- Produces: a lazy raw JNA library instance and a callable Android `actual fun` for every supported C function.

- [ ] **Step 1: Create the dedicated submodule branch**

Run:

```bash
git -C kextract switch -c fix/android-jna-bindings
```

Expected: `kextract` reports branch `fix/android-jna-bindings` with a clean worktree.

- [ ] **Step 2: Write the failing scalar/pointer/record generation test**

Extend the Android fixture header with these declarations and pass an explicit fixture library to `Options`:

```kotlin
private val FUNCTION_ABI_HEADER = """
    typedef unsigned int WGPUFlags;
    typedef struct WGPUValue { unsigned int value; } WGPUValue;
    typedef struct WGPUDeviceImpl* WGPUDevice;

    unsigned int sample_version(void);
    WGPUDevice sample_create(const WGPUValue* descriptor);
    void sample_release(WGPUDevice device);
    WGPUValue sample_round_trip(WGPUValue value);
""".trimIndent()
```

Update `generateAndroidSources`:

```kotlin
libraries = listOf(
    Options.Library("fixture", Options.Library.SpecKind.NAME),
),
```

Add this test:

```kotlin
"Android functions use a lazy raw JNA library and contain no runtime stubs" {
    val generated = generateAndroidSources(FUNCTION_ABI_HEADER)

    generated.jna shouldContain "interface wgpu_hLibrary : Library"
    generated.jna shouldContain "Native.load(\"fixture\", wgpu_hLibrary::class.java)"
    generated.jna shouldContain "fun sample_version(): Int"
    generated.jna shouldContain "fun sample_create(descriptor: Pointer?): Pointer?"
    generated.jna shouldContain "fun sample_release(device: Pointer?): Unit"
    generated.jna shouldContain
        "fun sample_round_trip(value: sample.bindings.android.WGPUValue.ByValue): sample.bindings.android.WGPUValue.ByValue"

    generated.bridge shouldContain "actual fun sample_version(): UInt"
    generated.bridge shouldContain "actual fun sample_create(descriptor: WGPUValue?): WGPUDevice?"
    generated.bridge shouldContain "actual fun sample_release(device: WGPUDevice?): Unit"
    generated.bridge shouldContain "actual fun sample_round_trip(value: WGPUValue): WGPUValue"
    generated.bridge shouldNotContain "not implemented for Android/JNA"
}
```

- [ ] **Step 3: Run the test and verify RED**

Run:

```bash
./gradlew :kextract:test --tests '*KmpAndroidJnaAbiTest*Android functions use a lazy raw JNA library*'
```

Expected: FAIL because the raw source has no `Library` interface and the bridge still contains `not implemented for Android/JNA`.

- [ ] **Step 4: Add the JNA runtime names and pass ABI/library configuration to Android**

Add Android runtime symbols:

```kotlin
JNA_CALLBACK("com.sun.jna.Callback", android()),
JNA_LIBRARY("com.sun.jna.Library", android()),
JNA_NATIVE("com.sun.jna.Native", android()),
```

Pass `libraries` and `abiIndex` from `KotlinGenerator.generateKmp` into `KotlinKmpAndroidBuilder`. Derive the Android library name with this rule:

```kotlin
private fun androidLibraryName(
    libraries: List<Options.Library>,
    className: String,
): String = when (libraries.size) {
    0 -> className.removeSuffix("_h")
    1 -> libraries.single().libSpec
    else -> error("Android/JNA KMP generation requires zero or one library; found ${libraries.size}")
}
```

- [ ] **Step 5: Replace function stubs with raw declarations and conversions**

In the raw JNA source, emit the interface and lazy instance before its functions:

```kotlin
internal interface wgpu_hLibrary : Library {
    fun sample_version(): Int
    fun sample_create(descriptor: Pointer?): Pointer?
    fun sample_release(device: Pointer?): Unit
    fun sample_round_trip(
        value: sample.bindings.android.WGPUValue.ByValue,
    ): sample.bindings.android.WGPUValue.ByValue
}

internal val wgpu_hLibraryInstance: wgpu_hLibrary by lazy {
    Native.load("fixture", wgpu_hLibrary::class.java)
}
```

Implement Android carrier selection from `KotlinKmpCAbiType`:

```kotlin
private fun rawJnaFunctionType(type: Type): String {
    if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void) return "Unit"
    return when (val abi = KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT)) {
        is KotlinKmpCAbiType.Address -> jnaPointer + "?"
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL -> "Int"
            else -> abi.jvmCarrier
        }
        is KotlinKmpCAbiType.StructValue ->
            "$androidPackage.${namePlan.declaration(abi.declaration)}.${namePlan.jnaByValue(abi.declaration)}"
    }
}
```

Use the existing enum/scalar conversion rules and these pointer/record rules in the public bridge:

```kotlin
// Pointer or handle argument
value?.handler

// Record passed by value
sample.bindings.android.WGPUValue.ByValue(value.handler).apply { read() }

// Nullable pointer/handle result
rawResult?.let { WGPUDevice(it) }

// Record returned by value
WGPUValue.ByValue(rawResult)
```

Emit `Unit` calls as a statement followed by `return`. Wrap generation failures with the C function name and whether the unsupported type is a parameter or return value.

- [ ] **Step 6: Run the focused test and the Android compilation fixture**

Run:

```bash
./gradlew :kextract:test --tests '*KmpAndroidJnaAbiTest*'
```

Expected: PASS, including `representative general union common raw and Android bridge sources compile together`.

- [ ] **Step 7: Commit the function bridge**

Run:

```bash
git -C kextract add src/main src/test
git -C kextract commit -m "fix: generate Android JNA function bindings"
```

---

### Task 2: Generate safe JNA callback trampolines

**Files:**
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpAndroidJnaAbiTest.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackAndroidEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`

**Interfaces:**
- Consumes: `KotlinCallbackModel`, `CallbackRuntime.register/prepare/rearmAfterNativeQuiescence`, raw JNA carriers.
- Produces: one strongly held JNA trampoline address per callback type.

- [ ] **Step 1: Replace the unsupported-callback expectation with a failing supported-callback expectation**

Replace the existing `Android callback registration fails before allocating unsupported resources` test with:

```kotlin
"Android callback registration uses a strongly held JNA trampoline" {
    val android = generateKmp(genericCallbacks).getValue("androidMain")

    android shouldContain "private fun interface SampleCallbackJna : com.sun.jna.Callback"
    android shouldContain "private val callback: SampleCallbackJna = SampleCallbackJna"
    android shouldContain "Native.getFunctionPointer(callback)"
    android shouldContain "CallbackRuntime.register("
    android shouldContain "CallbackRuntime.prepare("
    android shouldContain "CallbackRuntime.rearmAfterNativeQuiescence("
    android shouldContain "CallbackRuntime.dispatchSafely("
    android shouldNotContain "Android/JNA callback registration is not supported"
}
```

- [ ] **Step 2: Run the callback test and verify RED**

Run:

```bash
./gradlew :kextract:test --tests '*CallbackGeneratorIntegrationTest*Android callback registration uses*'
```

Expected: FAIL because Android currently throws `UnsupportedOperationException`.

- [ ] **Step 3: Generate the JNA trampoline**

For each callback, generate this shape with its ABI-specific parameters:

```kotlin
private fun interface SampleCallbackJna : com.sun.jna.Callback {
    fun invoke(value: Int, userdata: com.sun.jna.Pointer?)
}

@OptIn(CallbackRuntimeApi::class)
private object SampleCallbackTrampoline {
    private val callback: SampleCallbackJna = SampleCallbackJna { value, userdata ->
        try {
            CallbackRuntime.dispatchSafely(
                type = SampleCallbackType,
                userdata = userdata,
            ) { callback ->
                callback.invoke(value.toUInt())
            }
        } catch (failure: Throwable) {
            CallbackRuntime.reportUnroutedFailure(failure)
        }
    }

    val address: NativeAddress by lazy {
        Native.getFunctionPointer(callback)
    }
}
```

Generate raw callback parameters with the same address/scalar/record carrier mapping as functions. Preserve the analyzed routing-userdata position rather than assuming it is last.

- [ ] **Step 4: Implement registration operations through `CallbackRuntime`**

Generate Android `register`, `prepare`, and no-userdata `rearmAfterNativeQuiescence` with the same parameter and return types as the JVM emitter:

```kotlin
actual fun SampleCallback.Companion.register(
    policy: CallbackPolicy,
    onError: CallbackExceptionHandler,
    callback: SampleCallback,
): CallbackRegistration<SampleCallback> = CallbackRuntime.register(
    type = SampleCallbackType,
    trampoline = SampleCallbackTrampoline.address,
    policy = policy,
    onError = onError,
    callback = callback,
)
```

The generated singleton retains the JNA callback object for process lifetime; registration closure continues to be owned by `CallbackRuntime`.

- [ ] **Step 5: Compile generated callback sources and run callback tests**

Extend the Android compiler fixture's `kffiAndroid.kt` with the `CallbackRuntime` methods used by generated code, then run:

```bash
./gradlew :kextract:test --tests '*CallbackGeneratorIntegrationTest*' --tests '*KmpAndroidJnaAbiTest*'
```

Expected: PASS with no Android callback unsupported message.

- [ ] **Step 6: Commit callback support**

Run:

```bash
git -C kextract add src/main src/test
git -C kextract commit -m "fix: generate Android JNA callback trampolines"
```

---

### Task 3: Enable direct safe-callback bindings on Android

**Files:**
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`

**Interfaces:**
- Consumes: generated raw JNA library instance and `PreparedCallbackRegistration`.
- Produces: callable Android preflight lambdas for direct callback functions and callback-info factories.

- [ ] **Step 1: Add the failing Android direct-binding assertion**

Add:

```kotlin
"Android direct callback preflight invokes the raw JNA function" {
    val config = CallbackBindingsConfig().also { bindings ->
        bindings.directFunctionBindings = listOf(
            DirectFunctionBinding().also { binding ->
                binding.function = "function:sample_request"
                binding.callbackParameter = "callback"
                binding.callbackType = "typedef:SampleCallback"
                binding.routingUserdataParameter = "userdata"
            },
        )
    }
    val android = generateKmp(
        """
            typedef void (*SampleCallback)(unsigned int value, void * userdata);
            void sample_request(int input, SampleCallback callback, void * userdata);
        """.trimIndent(),
        config,
    ).getValue("androidMain")

    android shouldContain "actual fun sample_requestCallbackBindingPreflight("
    android shouldContain "sample.bindings.android.wgpu_hLibraryInstance.sample_request("
    android shouldContain "return { callback, userdata ->"
    android shouldNotContain "Android/JNA safe callback bindings are not supported"
}
```

- [ ] **Step 2: Run the test and verify RED**

Run:

```bash
./gradlew :kextract:test --tests '*CallbackGeneratorIntegrationTest*Android direct callback preflight*'
```

Expected: FAIL on the current Android unsupported fallback.

- [ ] **Step 3: Make `emitAndroid` mirror the JVM prepared-call flow**

Change `KotlinCallbackBindingEmitter.emitAndroid` to accept these additional parameters:

```kotlin
toRawArgument: (String, Type) -> String,
rawFunction: (Declaration.Function) -> String,
```

Render the prepared lambda with the existing `preparedPlatformArguments` helper:

```kotlin
parameters.forEach { parameter ->
    builder.appendLine(
        "val ${parameter.preparedName} = ${toRawArgument(parameter.name, parameter.variable.type())}",
    )
}
builder.appendLine("return { ${preparedCallLambdaParameters(binding)} ->")
builder.indent()
builder.appendLine("${rawFunction(binding.function)}(")
builder.indent()
preparedPlatformArguments(binding, parameters, toRawArgument).forEach { argument ->
    builder.appendLine("$argument,")
}
builder.unindent()
builder.appendLine(")")
builder.unindent()
builder.appendLine("}")
```

The raw call must insert `registration.callback` and `registration.userdata` at the indices supplied by the validated binding model and convert all application arguments before the registration is activated.

- [ ] **Step 4: Run callback and compilation tests**

Run:

```bash
./gradlew :kextract:test --tests '*CallbackGeneratorIntegrationTest*' --tests '*KmpAndroidJnaAbiTest*'
```

Expected: PASS.

- [ ] **Step 5: Commit direct callback bindings**

Run:

```bash
git -C kextract add src/main src/test
git -C kextract commit -m "fix: support safe callback calls on Android JNA"
```

---

### Task 4: Verify the complete generator and prepare the submodule commit

**Files:**
- Inspect: files changed in Tasks 1–3.

**Interfaces:**
- Produces: a tested `kextract` commit for the parent repository to pin.

- [ ] **Step 1: Run focused KMP generation tests**

Run:

```bash
./gradlew :kextract:test --tests '*KmpAndroidJnaAbiTest*' --tests '*CallbackGeneratorIntegrationTest*' --tests '*KmpRefreshIntegrationTest*'
```

Expected: PASS.

- [ ] **Step 2: Run the complete `kextract` suite**

Run:

```bash
rm -rf kextract/build/test-results/test
rm -f kextract/build/test-plan-completion/test.properties
./gradlew :kextract:test
```

Expected: `BUILD SUCCESSFUL` and no failed tests.

- [ ] **Step 3: Verify branch scope**

Run:

```bash
git -C kextract status --short --branch
git -C kextract log --oneline --decorate -4
git -C kextract diff 4609c920da64ccfabce9cd5fab259d73a3b893b2 --check
```

Expected: branch `fix/android-jna-bindings`, clean worktree, only intentional generator/test commits, and no whitespace errors.

- [ ] **Step 4: Record the commit hash for parent integration**

Run:

```bash
git -C kextract rev-parse HEAD
```

Expected: one commit hash to pin in `wgpu4k-native`; do not push and do not open a PR.
