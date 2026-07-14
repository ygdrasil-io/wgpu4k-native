# Correct Kextract Callback Generation Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Restore legacy Kextract compatibility, make KMP callback and aggregate output ABI-safe and collision-free, generate a real pre-publication native-call transaction, and make WebGPU regeneration portable and freshness-complete.

**Architecture:** Callback analysis is gated to multiplatform generation, and target-variable callback scalars are rejected rather than guessed from the generator host. Identifier allocation and pointer depth become explicit generator models; direct callback helpers receive a platform `preparedCall` lambda that captures final native carriers before KFFI publication. Gradle owns the full generator/header input graph and verifies both tracked and untracked generated output.

**Tech Stack:** Kotlin/JVM, libclang declaration model, Kotlin source generation, JVM FFM, Kotlin/Native cinterop, Gradle Kotlin DSL, GitHub Actions.

## Global Constraints

- The approved design is `docs/superpowers/specs/2026-07-14-external-review-remediation-design.md`.
- `kextract` is a Git submodule: commit each task inside `kextract`, then commit the root gitlink so every reviewed task has an exact root boundary.
- Do not modify, fork, upgrade, or replace `wgpu-native`.
- Non-multiplatform generation must not analyze or reject unrelated function-pointer typedefs.
- `--callback-bindings` without `--multiplatform` fails with the exact diagnostic `--callback-bindings requires --multiplatform`.
- Multiplatform callbacks reject C `long`, `unsigned long`, and `long double`; `long long`, fixed-width integers, `float`, and `double` remain supported.
- Never use the generator host OS to choose a multiplatform callback ABI.
- Preserve every pointer layer; `WGPUAdapter` remains a handle while `WGPUAdapter*` becomes `NativeAddress?`.
- JVM C unions use `MemoryLayout.unionLayout` without struct offset padding.
- Canonical C IDs and native symbol lookup strings remain unchanged; only emitted Kotlin identifiers are mangled and uniquified.
- Every application-argument conversion that can throw must finish before `prepare`, activation, or native publication.
- Generate bindings only after all Kextract tests pass, then compile and test the regenerated JVM and Native outputs.
- Generation must select `kextract.bat` on Windows and `kextract` on Unix hosts, depend directly on header acquisition and the Kextract image, and declare both as inputs.
- Generated-source freshness must detect tracked modifications and untracked files.
- Use test-driven development, preserve unrelated changes and submodule revisions, and do not push.

## File Map

- `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`: mode-gated callback analysis.
- `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt`: callback CLI option diagnostics.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCAbiType.kt`: target-independent callback carrier constraints.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/utils/KotlinIdentifierAllocator.kt`: valid unique Kotlin identifiers.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackModel.kt`: allocated callback/type/trampoline/parameter names.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`: transactional common direct helpers and callback-info names.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`: JVM preconversion and union layouts.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`: Native preconversion and pointer carriers.
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`: pre-publication Android rejection.
- `wgpu4k-native/build.gradle.kts`: portable, input-complete generation and freshness verification.
- `.github/workflows/test.yml`: cross-host generation configuration and clean generated-source gates.
- `wgpu4k-native/src/*/kotlin/io/ygdrasil/wgpu/wgpu_h*.kt`: regenerated corrected bindings.

---

### Task 1: Gate callback analysis and reject target-variable callback scalars

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/UnsupportedFilter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCAbiType.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/impl/KextractToolTest.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/impl/CommandLineTest.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCAbiTypeTest.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`

**Interfaces:**
- Consumes: `ValidatedCallbackBindings.EMPTY` for legacy generation.
- Produces: the exact CLI diagnostic above.
- Produces: target-independent `KotlinCallbackCAbiType.from` failures for `long` and `long double`.

- [ ] **Step 1: Replace the legacy regression test and add the CLI failure**

Replace `pipeline rejects an invalid automatic callback without metadata` in `KextractToolTest` with
a test that writes this header and expects `KextractTool.SUCCESS` with default options:

```c
typedef int (*LegacyComparator)(const void *, const void *);
int legacy_sort(LegacyComparator comparator);
```

In `CommandLineTest`, write a minimal header and YAML path, invoke `KextractCommand.main` with
`--callback-bindings` but no multiplatform flag, and assert:

```kotlin
val failure = assertFailsWith<IllegalArgumentException> {
    KextractCommand(logger).main(
        listOf(
            "--callback-bindings", bindings.toString(),
            "--output", output.toString(),
            header.toString(),
        ),
    )
}
assertEquals("--callback-bindings requires --multiplatform", failure.message)
```

Add `--multiplatform` to every existing CLI fixture that intentionally loads callback YAML.

- [ ] **Step 2: Add failing target-variable ABI tests**

Create `KotlinCallbackCAbiTypeTest.kt` and add integration headers covering:

```c
typedef void (*LongCallback)(long value);
typedef void (*UnsignedLongCallback)(unsigned long value);
typedef void (*LongDoubleCallback)(long double value);
typedef void (*StableCallback)(long long signed_value,
                               unsigned long long unsigned_value,
                               double floating_value);
```

Assert the first three fail independently with these messages:

```text
Unsupported multiplatform callback C ABI scalar 'long': target-dependent width (LP64 vs LLP64); use a fixed-width C integer type
Unsupported multiplatform callback C ABI scalar 'long double': target-dependent size and format; use double or an explicit fixed-width representation
```

Assert `StableCallback` generates `I64`, unsigned `I64`, and `F64` carriers.

- [ ] **Step 3: Run focused tests and verify RED**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.pipeline.KextractToolTest \
  --tests org.graphiks.kextract.pipeline.CommandLineTest \
  --tests org.graphiks.kextract.kotlin.callbacks.KotlinCallbackCAbiTypeTest \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
```

Expected: legacy generation still rejects the comparator, CLI accepts the invalid option pairing,
and the callback carrier still depends on `TypeImpl.IS_WINDOWS`.

- [ ] **Step 4: Gate validation and remove host-dependent carriers**

In `KextractCommand.run`, before YAML loading:

```kotlin
if (callbackBindingsPath != null && !multiplatform) {
    throw IllegalArgumentException("--callback-bindings requires --multiplatform")
}
```

In `KextractTool.generate`, reject a programmatic callback config without multiplatform mode. Pass
`allowVariableWidthCallbackScalars = options.multiplatform` to `UnsupportedFilter`, so a configured
callback reaches the callback ABI validator instead of being host-dependently skipped; keep the
existing filtering behavior for every non-callback declaration. Then select the validated model:

```kotlin
require(options.multiplatform || options.callbackBindings == null) {
    "callbackBindings requires multiplatform generation"
}
val callbackBindings = if (options.multiplatform) {
    CallbackAnalyzer.validate(
        CanonicalDeclarationIndex(d),
        options.callbackBindings ?: CallbackBindingsConfig(),
    )
} else {
    ValidatedCallbackBindings.EMPTY
}
```

Remove `TypeImpl` from `KotlinCallbackCAbiType`. Replace its variable-width branches:

```kotlin
Type.Primitive.Kind.Long -> unsupportedVariableWidthScalar(
    "long",
    "target-dependent width (LP64 vs LLP64); use a fixed-width C integer type",
)
Type.Primitive.Kind.LongDouble -> unsupportedVariableWidthScalar(
    "long double",
    "target-dependent size and format; use double or an explicit fixed-width representation",
)
```

The helper throws `UnsupportedOperationException` with
`Unsupported multiplatform callback C ABI scalar '$name': $reason`. The unsigned wrapper must reach
the same `Long` branch.

In `UnsupportedFilter`, add an `allowVariableWidthCallbackScalars` constructor flag defaulting to
false and a third parameter on `firstUnsupportedType`. Only `checkFunctionTypeSupported` passes the
flag. In that callback-only traversal, `LongDouble` returns null so
`KotlinCallbackCAbiType.from` emits the target-independent diagnostic. All ordinary functions,
fields, and typedefs retain the existing host-specific unsupported filtering.

- [ ] **Step 5: Verify and commit both repositories**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.pipeline.KextractToolTest \
  --tests org.graphiks.kextract.pipeline.CommandLineTest \
  --tests org.graphiks.kextract.kotlin.callbacks.KotlinCallbackCAbiTypeTest \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
rtk git -C kextract diff --check
```

Commit:

```bash
rtk git -C kextract add src
rtk git -C kextract commit -m "fix: scope callback analysis to KMP generation"
rtk git add kextract
rtk git commit -m "chore: update kextract callback validation"
```

---

### Task 2: Allocate valid, collision-free callback identifiers

**Files:**
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/utils/KotlinIdentifierAllocator.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/kotlin/utils/KotlinIdentifierAllocatorTest.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/utils/KotlinNameMangler.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/kotlin/utils/KotlinNameManglerTest.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackModel.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCommonEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackNativeEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackAndroidEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`

**Interfaces:**
- Produces: `KotlinIdentifierAllocator.allocate(rawName, fallback)`.
- Extends: the shared mangler keyword set with Kotlin's reserved future keyword `typeof`.
- Adds: `runtimeTypeName` and `trampolineName` to `KotlinCallbackModel`.
- Provides: a callback model lookup by canonical ID to the binding emitter.
- Preserves: raw `canonicalId` and raw C symbol lookup strings.

- [ ] **Step 1: Add allocator and generated-source RED tests**

Create tests for this exact behavior:

```kotlin
val names = KotlinIdentifierAllocator(listOf("callback", "failure"))
names.allocate("class", "arg0") shouldBe "class_"
names.allocate("foo\$bar", "arg1") shouldBe "foo_bar"
names.allocate("foo_bar", "arg2") shouldBe "foo_bar_2"
names.allocate("callback", "arg3") shouldBe "callback_2"
names.allocate("", "arg4") shouldBe "arg4"
```

Add an integration header:

```c
typedef void (*class)(int callback,
                      int failure,
                      int policy,
                      int onError,
                      int fun,
                      int fun_,
                      void *userdata);
```

Configure a direct binding whose application parameter is named `policy`. Assert generated common,
JVM, Native, and Android sources contain a valid `class_` callback, unique `fun_`/`fun__2` names,
and `policy_2` rather than duplicate synthetic parameters. Compile the generated source fixture.

- [ ] **Step 2: Run focused tests and verify RED**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocatorTest \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
```

Expected: the allocator is missing and raw callback names collide with Kotlin keywords and helper
parameters.

- [ ] **Step 3: Implement the allocator**

Create:

```kotlin
package org.graphiks.kextract.kotlin.utils

internal class KotlinIdentifierAllocator(reserved: Iterable<String> = emptyList()) {
    private val used = reserved.mapTo(linkedSetOf(), KotlinNameMangler::mangle)

    fun allocate(rawName: String, fallback: String): String {
        val base = KotlinNameMangler.mangle(rawName).ifBlank {
            KotlinNameMangler.mangle(fallback).ifBlank { "generated" }
        }
        if (used.add(base)) return base
        var suffix = 2
        while (true) {
            val candidate = "${base}_${suffix++}"
            if (used.add(candidate)) return candidate
        }
    }
}
```

- [ ] **Step 4: Allocate model and binding names once**

Change `KotlinCallbackModel.from` to accept a generator-wide allocator. Allocate `typeName`,
`${typeName}Type`, and `${typeName}Trampoline` globally. Build all raw parameters with one local
allocator before splitting out routing userdata, reserving:

```kotlin
setOf(
    "callback", "failure", "policy", "onError", "registration", "prepared",
    "preparedCall", "allocator", "mode",
)
```

The model shape is:

```kotlin
data class KotlinCallbackModel(
    val canonicalId: String,
    val typeName: String,
    val runtimeTypeName: String,
    val trampolineName: String,
    val documentation: String?,
    val parameters: List<KotlinCallbackParameter>,
    val routingUserdataParameter: KotlinCallbackParameter?,
)
```

Create one global allocator in `KotlinGenerator`; build `Map<String, KotlinCallbackModel>` by
canonical ID and give it to the binding emitter. Emitters consume `runtimeTypeName` and
`trampolineName` directly and never reconstruct them.

For direct helper and callback-info parameters, use a new local allocator with all synthetic
parameters reserved. Keep each `Declaration.Variable` in the rendered model so `rawCall` still maps
the original C argument order. Callback type references come from the canonical-ID map, not
`typedef.name()`.

- [ ] **Step 4a: Close review gaps for reserved outputs and imported runtime names**

Add RED tests before changing production:

- allocator inputs `_`, `__`, and `___` must use their supplied fallback rather than returning the
  Kotlin-reserved underscore-only identifier;
- `typeof` must become `typeof_` through `KotlinNameMangler` and the allocator;
- a generated callback typedef named `Callback` must compile as an allocated name such as
  `Callback_2`, and its SAM supertype must remain the imported KFFI `Callback` rather than itself;
- representative `CallbackType`, `CallbackPolicy`, and `CallbackRegistration` typedef collisions
  must also allocate away from their imported KFFI/runtime names.

Treat a mangled candidate as unusable when it is blank or consists only of underscores, then retry
with the mangled fallback; if the fallback is also unusable, use `generated`. Add `typeof` to the
shared reserved keyword set so it receives the normal trailing underscore.

Initialize the generator-wide callback allocator with the complete set of unqualified KFFI/runtime
type identifiers referenced by generated callback common/JVM/Native/Android sources, including at
least `Callback`, `CallbackType`, `CallbackPolicy`, `CallbackRegistration`,
`PreparedCallbackRegistration`, `CallbackExceptionHandler`, `CallbackRuntime`,
`CallbackRuntimeApi`, `UnsafeCallbackRearmApi`, `NativeAddress`, `MemoryAllocator`, `CString`, and
`ArrayHolder`. Keep this list centralized and tested. Do not rewrite canonical IDs, native symbol
strings, or raw C argument mapping.

Run the allocator, mangler, and callback integration tests with `--rerun-tasks`; capture the initial
compiler/assertion failures and the final GREEN result. Then rerun the full Kextract suite.

- [ ] **Step 5: Verify and commit both repositories**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocatorTest \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
rtk git -C kextract diff --check
```

Commit:

```bash
rtk git -C kextract add src
rtk git -C kextract commit -m "fix: allocate safe Kotlin callback names"
rtk git add kextract
rtk git commit -m "chore: update kextract callback naming"
```

---

### Task 3: Correct JVM unions and pointer-to-handle indirection

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KmpTypeMapper.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/GeneratorIntegrationTest.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpRefreshIntegrationTest.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmFfmAbiTest.kt`

**Interfaces:**
- Produces: `KmpTypeMapper.pointerDepth(type: Type): Int`.
- Maps: one-level opaque handles to wrappers and any outer pointer to an already-pointer handle to `NativeAddress?`.
- Emits: `MemoryLayout.unionLayout` for `Declaration.Scoped.Kind.UNION`.

- [ ] **Step 1: Add failing union source and storage tests**

Generate from:

```c
typedef union WGPUScalar {
    unsigned int u32;
    float f32;
    unsigned long long u64;
} WGPUScalar;
```

Assert the JVM source contains `MemoryLayout.unionLayout(` inside `WGPUScalar` and does not contain
`MemoryLayout.structLayout(` in that declaration.

In `KmpJvmFfmAbiTest`, generate the same source and verify the chosen FFM layout's overlapping
storage directly:

```kotlin
val layout = MemoryLayout.unionLayout(
    ValueLayout.JAVA_INT.withName("u32"),
    ValueLayout.JAVA_FLOAT.withName("f32"),
    ValueLayout.JAVA_LONG.withName("u64"),
)
Arena.ofConfined().use { arena ->
    val segment = arena.allocate(layout)
    val u32 = layout.varHandle(groupElement("u32"))
    val f32 = layout.varHandle(groupElement("f32"))
    u32.set(segment, 0L, 0x3f800000)
    (f32.get(segment, 0L) as Float) shouldBe 1.0f
    f32.set(segment, 0L, 2.0f)
    (u32.get(segment, 0L) as Int) shouldBe 0x40000000
}
```

- [ ] **Step 2: Add the pointer-depth RED fixture**

Generate all source sets from:

```c
typedef struct WGPUInstanceImpl *WGPUInstance;
typedef struct WGPUAdapterImpl *WGPUAdapter;
unsigned long long wgpuInstanceEnumerateAdapters(
    WGPUInstance instance,
    void const *options,
    WGPUAdapter *adapters
);
```

Assert common and Android use `adapters: NativeAddress?`, JVM passes
`adapters?.handler ?: MemorySegment.NULL`, Native emits a pointer reinterpretation, and no source
contains `adapters: WGPUAdapter?`.

- [ ] **Step 3: Run focused tests and verify RED**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.integration.GeneratorIntegrationTest \
  --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest \
  --tests org.graphiks.kextract.integration.KmpJvmFfmAbiTest
```

Expected: unions emit `structLayout` and the outer adapter pointer collapses to the handle wrapper.

- [ ] **Step 4: Split struct and union layout emission**

Factor JVM aggregate layout emission:

```kotlin
private fun emitGroupLayout(
    decl: Declaration.Scoped,
    fields: List<Declaration.Variable>,
) {
    when (decl.kind()) {
        Declaration.Scoped.Kind.STRUCT -> emitStructLayout(decl, fields)
        Declaration.Scoped.Kind.UNION -> emitUnionLayout(decl, fields)
        else -> error("Expected struct or union, found ${decl.kind()}")
    }
}
```

The union branch is exactly:

```kotlin
builder.appendLine("val layout: GroupLayout = MemoryLayout.unionLayout(")
builder.indent()
fields.forEachIndexed { index, field ->
    val comma = if (index < fields.lastIndex) "," else ""
    builder.appendLine(
        "${LayoutUtils.layoutString(field.type())}.withName(\"${field.name()}\")$comma",
    )
}
builder.unindent()
builder.appendLine(").withName(\"${decl.name()}\")")
```

Do not reuse struct offsets or padding for unions. Preserve the existing struct algorithm unchanged.

- [ ] **Step 5: Preserve pointer depth through typedefs**

Add:

```kotlin
fun pointerDepth(type: Type): Int = when {
    type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER ->
        1 + pointerDepth(type.type())
    type is Type.Delegated -> pointerDepth(type.type())
    else -> 0
}
```

Make the first pointer-mapping branch `pointerDepth(pointee) > 0 -> "NativeAddress?"`, before the
generated-reference-typedef branch. This keeps `WGPUAdapter` as a wrapper but maps the outer pointer
in `WGPUAdapter*` to an address buffer; it also maps `char**` to `NativeAddress?` while preserving
`char*` as `CString?`.

In Native argument conversion, when the mapped type is `NativeAddress?`, primitive `*Var` inference
returns null, and total pointer depth exceeds one, emit:

```kotlin
"$name?.pointer?.takeIf { $name.rawValue != 0L }?.reinterpret()"
```

- [ ] **Step 6: Verify and commit both repositories**

Run the focused tests from Step 3, then:

```bash
rtk ./gradlew :kextract:test
rtk git -C kextract diff --check
rtk git -C kextract add src
rtk git -C kextract commit -m "fix: preserve KMP aggregate ABI"
rtk git add kextract
rtk git commit -m "chore: update kextract KMP ABI generation"
```

---

### Task 4: Capture final native carriers before callback publication

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmDirectCallbackTransactionTest.kt`

**Interfaces:**
- Changes: each generated platform preflight returns a prepared invocation lambda.
- Produces: a lambda whose captured application arguments are final JVM or Native carriers.
- Preserves: raw public downcalls and the KFFI `activateForNativeCall` transaction boundary.

- [ ] **Step 1: Add generated-order and throwing-conversion RED tests**

Use this binding fixture:

```c
typedef struct SamplePayload { int value; } SamplePayload;
typedef void (*SampleCallback)(void *userdata);
void sample_set_callback(
    SamplePayload payload,
    SampleCallback callback,
    void *userdata
);
```

Assert common output contains:

```kotlin
internal expect fun sample_set_callbackCallbackBindingPreflight(
    payload: SamplePayload,
): (NativeAddress?, NativeAddress?) -> Unit
```

and that `val preparedCall = sample_set_callbackCallbackBindingPreflight(payload)` occurs before `SampleCallback.prepare`, while
the final activation invokes only `preparedCall(registration.callback, registration.userdata)`.
Assert no `val validatedPayload = payload` remains.

`KmpJvmDirectCallbackTransactionTest` must compile and execute the generated common/JVM fixture with
minimal test-local KFFI expect/actual stubs. Its probe supplies a `SamplePayload` whose `handler`
getter throws `SentinelConversionFailure`, calls the safe overload, and asserts the stub
`CallbackRuntime.prepareCount == 0`. The preflight converts the payload before symbol resolution,
so no native symbol or downcall can be reached.

- [ ] **Step 2: Run focused tests and verify RED**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest \
  --tests org.graphiks.kextract.integration.KmpJvmDirectCallbackTransactionTest
```

Expected: current output copies the Kotlin value, returns `Unit` from preflight, and the throwing
carrier access is not performed before publication.

- [ ] **Step 3: Generate a prepared-call lambda in common code**

For bindings with routing userdata, emit an expect preflight returning
`(NativeAddress?, NativeAddress?) -> Unit`; without routing userdata, return
`(NativeAddress?) -> Unit`.

The safe overload and unsafe rearm overload start with:

```kotlin
val preparedCall = sample_set_callbackCallbackBindingPreflight(payload)
```

Then perform callback `prepare` or rearm. The published call uses only registration-owned callback
addresses:

```kotlin
return CallbackRuntime.activateForNativeCall(prepared) { registration ->
    preparedCall(registration.callback, registration.userdata)
}
```

Remove `emitValidatedLocals`; the rendered parameter model's unique names from Task 2 remain the
source names passed into platform preflight.

- [ ] **Step 4: Capture concrete carriers in every platform actual**

JVM output converts application arguments before touching the lazy symbol and handle, then captures
them:

```kotlin
internal actual fun sample_set_callbackCallbackBindingPreflight(
    payload: SamplePayload,
): (NativeAddress?, NativeAddress?) -> Unit {
    val preparedPayload = payload.handler.handler
    val address = sample_set_callback_ADDR
    val handle = sample_set_callback_HANDLE
    return { callback, userdata ->
        handle.invokeExact(
            preparedPayload,
            callback?.handler ?: MemorySegment.NULL,
            userdata?.handler ?: MemorySegment.NULL,
        )
    }
}
```

Native output captures `payload.toCValue()` and returns a lambda that calls
`webgpu.native.sample_set_callback` with the captured value plus the registration addresses. Use the
existing `toRawJvmArgument` and `toNativeArgument` logic at preflight-generation time so every
application parameter receives exactly the same conversion as the raw downcall.

Android returns `Nothing` by throwing the existing unsupported safe-callback diagnostic before any
registration is prepared. Callback and routing-userdata address adaptation may remain inside the
lambda because those addresses are created by KFFI and cannot be user-supplied throwing carriers.

- [ ] **Step 5: Verify the transaction and commit both repositories**

Run:

```bash
rtk ./gradlew :kextract:test \
  --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest \
  --tests org.graphiks.kextract.integration.KmpJvmDirectCallbackTransactionTest
rtk ./gradlew :kextract:test
rtk git -C kextract diff --check
```

Commit:

```bash
rtk git -C kextract add src
rtk git -C kextract commit -m "fix: prepare callback downcalls transactionally"
rtk git add kextract
rtk git commit -m "chore: update transactional callback generation"
```

---

### Task 5: Make binding regeneration portable and freshness-complete

**Files:**
- Modify: `wgpu4k-native/build.gradle.kts`
- Modify: `.github/workflows/test.yml`

**Interfaces:**
- Produces: Gradle property `wgpu4k.bindingGeneration.hostForTest` accepting `macos`, `linux`, or `windows` for configuration-only verification.
- Produces: `verifyBindingGenerationConfiguration` and `verifyGeneratedBindingsClean` tasks.
- Changes: `generateBindingsFromHeader` depends directly on `:kextract:createKextractImage` and `fetch-native-dependencies`.

- [ ] **Step 1: Add a failing configuration-verification task**

Factor the configured host:

```kotlin
val bindingGenerationHost = providers.gradleProperty("wgpu4k.bindingGeneration.hostForTest")
    .orNull
    ?.lowercase()
    ?: when (Platform.os) {
        Os.MacOs -> "macos"
        Os.Linux -> "linux"
        Os.Windows -> "windows"
    }
require(bindingGenerationHost in setOf("macos", "linux", "windows"))
```

Register `verifyBindingGenerationConfiguration` to inspect the generation task and require:

- direct task dependencies `:kextract:createKextractImage` and
  `:wgpu4k-native:fetch-native-dependencies`;
- launcher suffix `bin/kextract.bat` for Windows or `bin/kextract` otherwise;
- callback YAML, both headers, the launcher, and Kextract distribution among its declared inputs.

Run before changing the generation task:

```bash
rtk ./gradlew :wgpu4k-native:verifyBindingGenerationConfiguration -Pwgpu4k.bindingGeneration.hostForTest=macos
rtk ./gradlew :wgpu4k-native:verifyBindingGenerationConfiguration -Pwgpu4k.bindingGeneration.hostForTest=linux
rtk ./gradlew :wgpu4k-native:verifyBindingGenerationConfiguration -Pwgpu4k.bindingGeneration.hostForTest=windows
```

Expected: all fail because the current direct dependency is `:kextract:assemble`, the distribution
is not an input, headers are not acquired by the task, and the launcher is always Unix.

- [ ] **Step 2: Correct the generation dependency and input graph**

Use:

```kotlin
val kextractDistribution = project(":kextract").layout.buildDirectory.dir("kextract")
val kextractLauncher = kextractDistribution.map { distribution ->
    distribution.file(if (bindingGenerationHost == "windows") "bin/kextract.bat" else "bin/kextract")
}
```

Configure the `Exec` task:

```kotlin
dependsOn(":kextract:createKextractImage", "fetch-native-dependencies")
inputs.dir(kextractDistribution)
    .withPropertyName("kextractDistribution")
    .withPathSensitivity(PathSensitivity.RELATIVE)
inputs.file(kextractLauncher)
    .withPropertyName("kextractLauncher")
    .withPathSensitivity(PathSensitivity.RELATIVE)
inputs.file(callbackBindings).withPropertyName("callbackBindings")
inputs.file(nativeHeader).withPropertyName("nativeHeader")
inputs.file(webGpuHeader).withPropertyName("webGpuHeader")
executable = kextractLauncher.get().asFile.absolutePath
```

Keep existing outputs and arguments. The host override is for configuration/dry-run tests only;
never execute a foreign launcher on the wrong host.

- [ ] **Step 3: Add a portable tracked-and-untracked cleanliness task**

Register `verifyGeneratedBindingsClean`. In `doLast`, run `ProcessBuilder` from `rootDir` for:

```text
git diff --exit-code -- wgpu4k-native/src
git ls-files --others --exclude-standard -- wgpu4k-native/src
```

Capture merged stdout/stderr and check each exit code. The first failure reports the diff command's
output. The second must also require blank stdout and throw
`GradleException("Untracked generated WebGPU sources:\n$output")` otherwise.

Create an untracked sentinel with `apply_patch` at
`wgpu4k-native/src/commonMain/kotlin/__UntrackedGeneratedProbe.kt`; prove the old `git diff` returns
zero and the new task fails while listing the sentinel. Remove the sentinel with `apply_patch`, then
prove the task succeeds.

- [ ] **Step 4: Update cross-host CI and verify configuration**

Add an unconditional matrix step:

```yaml
- name: Verify binding-generation configuration
  run: ./gradlew :wgpu4k-native:verifyBindingGenerationConfiguration
```

Replace the macOS-only diff step with:

```yaml
- name: Regenerate and verify WebGPU bindings
  if: matrix.os == 'macos-latest'
  run: >-
    ./gradlew
    :wgpu4k-native:generateBindingsFromHeader
    :wgpu4k-native:verifyGeneratedBindingsClean
```

Run all three local configuration overrides again, plus:

```bash
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader --dry-run
rtk git diff --check
```

Expected: all configuration checks pass and the dry-run includes both direct prerequisite tasks.

- [ ] **Step 5: Commit the portable generation gate**

```bash
rtk git add wgpu4k-native/build.gradle.kts .github/workflows/test.yml
rtk git commit -m "build: make WebGPU regeneration portable"
```

---

### Task 6: Regenerate and verify corrected WebGPU bindings

**Files:**
- Modify generated: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt`
- Modify generated: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt`
- Modify generated: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt`
- Modify generated: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt`

**Interfaces:**
- Produces: `wgpuInstanceEnumerateAdapters(..., adapters: NativeAddress?): ULong` on every platform.
- Preserves: all configured safe callback registration/factory APIs and raw C symbol names.

- [ ] **Step 1: Prove the complete generator suite is green before regeneration**

Run:

```bash
rtk ./gradlew :kextract:test
rtk git -C kextract status --short
```

Expected: all tests pass and the submodule worktree is clean at the pointer committed by Task 4.

- [ ] **Step 2: Regenerate bindings**

Run:

```bash
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
```

Inspect:

```bash
rtk rg -n -C 2 'wgpuInstanceEnumerateAdapters' \
  wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt \
  wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt \
  wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt \
  wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt
```

Expected common signature:

```kotlin
expect fun wgpuInstanceEnumerateAdapters(
    instance: WGPUInstance?,
    options: WGPUInstanceEnumerateAdapterOptions?,
    adapters: NativeAddress?,
): ULong
```

- [ ] **Step 3: Compile and test regenerated platforms**

Run:

```bash
rtk ./gradlew :wgpu4k-native:jvmTest :wgpu4k-native:macosArm64Test
rtk ./gradlew \
  :wgpu4k-native:compileKotlinLinuxX64 \
  :wgpu4k-native:compileKotlinMingwX64 \
  :wgpu4k-native:compileKotlinAndroidNativeArm64
rtk ./gradlew :wgpu4k-native:verifyGeneratedBindingsClean
rtk git diff --check
```

Expected: all generated targets compile and tests pass. `verifyGeneratedBindingsClean` is expected
to fail before the intended generated diff is committed; inspect it to ensure only the four
generated binding roots changed and no untracked generated file exists.

- [ ] **Step 4: Commit generated output and prove freshness**

```bash
rtk git add wgpu4k-native/src
rtk git commit -m "chore: regenerate corrected WebGPU bindings"
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
rtk ./gradlew :wgpu4k-native:verifyGeneratedBindingsClean
rtk git diff --check
```

Expected: the second regeneration produces no tracked or untracked change.

---

## Review Gate

Review every task from its recorded submodule and root base commits. The final Kextract reviewer
must verify legacy non-KMP compatibility, exact option diagnostics, target-independent scalar
rejection, union overlap semantics, pointer-depth preservation, generated-source compilation,
collision-free names, real carrier capture before callback preparation, portable launcher/input
configuration, tracked-and-untracked freshness, deterministic regeneration, and no change to raw C
canonical IDs or symbols.
