# Safe Generated Callback Registrations Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace per-registration native callbacks with process-lifetime trampolines and typed, closeable registrations; generate safe direct-function overloads and WebGPU callback-info factories; and prove delayed, concurrent, and late callback behavior on JVM and Kotlin/Native.

**Architecture:** `kffi` owns a platform-neutral registration state machine, an allowlisted token codec, and a non-throwing FFI exception barrier. Refreshed `kextract` emits one permanent trampoline per callback typedef and routes through the last compatible `userdata`; typed YAML metadata enables transactional direct-function overloads and callback-info factories. `wgpu4k-native` supplies the WebGPU metadata, regenerates all bindings, and keeps event pumping explicit at the caller level.

**Tech Stack:** Kotlin 2.3.21 common atomics, JDK 25 / JVM target 24 FFM, Kotlin/Native `staticCFunction`, Gradle, Clikt 5, Jackson YAML, Kotest/JUnit 5, C11/pthreads, `wgpu-native` v29.

## Global Constraints

- Complete `docs/superpowers/plans/2026-07-13-kextract-kmp-refresh.md` first and start this plan from its clean root and submodule commits.
- Treat this as an intentionally breaking API change: remove `CallbackHolder` and generated `allocate(callback)`; retain raw C bindings.
- Support JVM and all configured 64-bit Kotlin/Native targets first. Android/JNA registration must fail immediately with a clear `UnsupportedOperationException`; Android Native continues through `nativeMain`.
- Allocate one JVM `Arena.global()` upcall stub or one Kotlin/Native `staticCFunction` per callback type for the process lifetime, never per registration.
- Allocate non-zero `ULong` routing tokens monotonically, never reuse them, and fail before wraparound.
- Reserve only the last compatible callback `userdata`; keep earlier compatible userdata parameters in the public callback signature.
- Require `CallbackPolicy.ONCE` or `CallbackPolicy.REPEATING` explicitly; neither has a default.
- Keep the safe no-userdata lifecycle `UNUSED -> ACTIVE -> RETIRED`. Re-arm only through `rearmAfterNativeQuiescence` annotated with error-level `UnsafeCallbackRearmApi`.
- Wrap the complete native trampoline body in `try/catch(Throwable)` and ensure the final fallback reporter cannot throw across FFI.
- Require explicit valid `WGPUCallbackMode` values on safe mode-bearing callback-info factories. Keep raw allocation unchanged.
- Do not add coroutines, threads, hidden polling, `processEvents`, `waitAny`, or `device.poll()` loops to `kffi` or generated bindings.
- Do not hand-edit `wgpu4k-native/src/*/wgpu_h*.kt`; modify the generator/configuration and regenerate.
- Write a failing test before each implementation change and use bounded timeouts for every asynchronous test.
- Prefix every shell command with `rtk`.

---

### Task 1: Define the public `kffi` callback contract and failure reporting

**Files:**
- Modify: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/Callback.kt`
- Create: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/CallbackExceptionReporter.kt`
- Create: `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/CallbackExceptionReporter.jvm.kt`
- Create: `kffi/src/nativeMain/kotlin/io/ygdrasil/kffi/CallbackExceptionReporter.native.kt`
- Create: `kffi/src/androidMain/kotlin/io/ygdrasil/kffi/CallbackExceptionReporter.android.kt`
- Delete: `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/Callback.jvm.kt`
- Delete: `kffi/src/nativeMain/kotlin/io/ygdrasil/kffi/Callback.native.kt`
- Delete: `kffi/src/androidMain/kotlin/io/ygdrasil/kffi/Callback.android.kt`
- Create: `kffi/src/commonTest/kotlin/io/ygdrasil/kffi/CallbackApiTest.kt`

**Interfaces:**
- Produces: `Callback`, `CallbackPolicy`, `CallbackExceptionHandler`, `CallbackRegistration<C>`.
- Produces: error-level `UnsafeCallbackRearmApi` and internal-facing error-level `CallbackRuntimeApi`.
- Produces: guarded platform fallback reporting for failures that cannot be routed to a registration.
- Removes: `CallbackHolder`, native `callbackMap`, `registerCallback`, `findCallback`, and `removeCallback`.

- [ ] **Step 1: Add the failing public API contract test**

Create `CallbackApiTest.kt` with compile-time use plus behavioral assertions for the default reporter wrapper:

```kotlin
package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackApiTest : FreeSpec({
    "callback policy has no implicit third state" {
        CallbackPolicy.entries shouldBe listOf(CallbackPolicy.ONCE, CallbackPolicy.REPEATING)
    }

    "callback exception handler is a fun interface" {
        var observed: Throwable? = null
        val handler = CallbackExceptionHandler { observed = it }
        val failure = IllegalStateException("callback failure")
        handler.onException(failure)
        observed shouldBe failure
    }

    "fallback reporting contains callback and handler failures" {
        val callbackFailure = IllegalStateException("callback")
        val handlerFailure = IllegalArgumentException("handler")
        val combined = CallbackExceptionHandlerFailure(callbackFailure, handlerFailure)
        combined.callbackFailure shouldBe callbackFailure
        combined.handlerFailure shouldBe handlerFailure
    }
})
```

- [ ] **Step 2: Run the test and verify the old API cannot satisfy it**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackApiTest
```

Expected: test compilation fails because the new policy, handler, registration, and failure types do not exist.

- [ ] **Step 3: Replace `CallbackHolder` with the normative public contract**

Make `Callback.kt` expose exactly this public shape:

```kotlin
package io.ygdrasil.kffi

interface Callback

enum class CallbackPolicy {
    ONCE,
    REPEATING,
}

fun interface CallbackExceptionHandler {
    fun onException(error: Throwable)

    companion object {
        val Default: CallbackExceptionHandler = CallbackExceptionHandler(::reportUnhandledCallbackException)
    }
}

interface CallbackRegistration<C : Callback> : AutoCloseable {
    val callback: NativeAddress
    val userdata: NativeAddress?
    val policy: CallbackPolicy
    val isClosed: Boolean

    override fun close()
}

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "Re-arming a callback without userdata can route a delayed native call to the wrong Kotlin lambda unless native quiescence has already been established.",
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class UnsafeCallbackRearmApi

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API is reserved for generated callback bindings.",
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class CallbackRuntimeApi
```

- [ ] **Step 4: Add a two-level, non-throwing exception fallback**

In common code, represent both failures and guard the platform call:

```kotlin
internal class CallbackExceptionHandlerFailure(
    val callbackFailure: Throwable,
    val handlerFailure: Throwable,
) : RuntimeException("Callback and CallbackExceptionHandler both failed", callbackFailure)

internal expect fun platformReportUnhandledCallbackException(error: Throwable)

internal fun reportUnhandledCallbackException(error: Throwable) {
    try {
        CallbackFallbackReporter.report(error)
    } catch (_: Throwable) {
        // Last-resort FFI barrier: never propagate reporting failure.
    }
}
```

`CallbackFallbackReporter` keeps an `AtomicReference<CallbackExceptionHandler?>` test override; `report` invokes the override when installed and otherwise calls `platformReportUnhandledCallbackException`. Its internal `installForTest` returns an `AutoCloseable` that restores the previous handler in `close()`. Use the current thread's uncaught-exception handler on JVM and Android. Use `Throwable.printStackTrace()` on Kotlin/Native. Guard each actual implementation locally as well as through the common wrapper.

- [ ] **Step 5: Remove the old platform callback holders and map**

Delete all three `Callback.*.kt` actual files. Confirm no handwritten source still imports `CallbackHolder` before generated code is migrated:

```bash
rtk rg -n "CallbackHolder|callbackMap|registerCallback|findCallback|removeCallback" kffi/src
```

Expected: no matches.

- [ ] **Step 6: Run the focused test and commit**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackApiTest
rtk git add kffi/src
rtk git commit -m "feat(kffi): define callback registration contract"
```

Expected: the test passes and the commit contains only the public contract, reporters, tests, and old-holder removal.

---

### Task 2: Implement and verify the opaque token codec

**Files:**
- Create: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/TokenCodec.kt`
- Create: `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/TokenCodec.jvm.kt`
- Create: `kffi/src/nativeMain/kotlin/io/ygdrasil/kffi/TokenCodec.native.kt`
- Create: `kffi/src/androidMain/kotlin/io/ygdrasil/kffi/TokenCodec.android.kt`
- Create: `kffi/src/nativeInterop/cinterop/callback_token_codec.h`
- Create: `kffi/src/nativeInterop/cinterop/callbackTokenCodec.def`
- Modify: `kffi/build.gradle.kts`
- Create: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/TokenCodecJvmTest.kt`
- Create: `kffi/src/nativeTest/kotlin/io/ygdrasil/kffi/TokenCodecNativeTest.kt`

**Interfaces:**
- Produces: internal `TokenCodec` and `PlatformTokenCodec`.
- Supports: JVM FFM addresses and configured 64-bit Kotlin/Native ABIs.
- Rejects: zero, values above `Long.MAX_VALUE.toULong()`, non-64-bit Native ABIs, and Android/JNA.

- [ ] **Step 1: Write failing platform codec tests**

Test these exact cases on JVM and Native:

```kotlin
listOf(1uL, 2uL, Int.MAX_VALUE.toULong(), Long.MAX_VALUE.toULong()).forEach { token ->
    PlatformTokenCodec.decode(PlatformTokenCodec.encode(token)) shouldBe token
}
PlatformTokenCodec.decode(null) shouldBe null
shouldThrow<IllegalArgumentException> { PlatformTokenCodec.encode(0uL) }
shouldThrow<IllegalArgumentException> { PlatformTokenCodec.encode(Long.MAX_VALUE.toULong() + 1uL) }
PlatformTokenCodec.pointerBits shouldBe 64
PlatformTokenCodec.maxToken shouldBe Long.MAX_VALUE.toULong()
```

On Android/JNA, add an `androidUnitTest` assertion that `encode(1uL)` throws `UnsupportedOperationException` containing `Android/JNA callback registration is not supported`.

- [ ] **Step 2: Run JVM codec tests and verify they fail**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.TokenCodecJvmTest
```

Expected: test compilation fails because `PlatformTokenCodec` does not exist.

- [ ] **Step 3: Add the common codec contract**

```kotlin
internal interface TokenCodec {
    val pointerBits: Int
    val maxToken: ULong

    fun encode(token: ULong): NativeAddress
    fun decode(address: NativeAddress?): ULong?
}

internal expect object PlatformTokenCodec : TokenCodec
```

- [ ] **Step 4: Add a C-verified token codec for every Kotlin/Native target**

Create a header-only cinterop with these conversions:

```c
#include <stdint.h>

_Static_assert(sizeof(void *) == sizeof(uintptr_t), "unsupported callback token ABI");
_Static_assert(sizeof(uintptr_t) == 8, "callback tokens require a 64-bit uintptr_t");

static inline void *kffi_callback_token_encode(uintptr_t token) {
    return (void *)token;
}

static inline uintptr_t kffi_callback_token_decode(void *userdata) {
    return (uintptr_t)userdata;
}
```

Configure `callbackTokenCodec` on the `main` compilation of every item in `nativeTargets`. This makes Clang evaluate the ABI assertions while compiling macOS, iOS, Linux, MinGW, Android Native, and simulator klibs.

- [ ] **Step 5: Implement the JVM and Native codecs**

JVM uses `MemorySegment.ofAddress(token.toLong())` wrapped in `JvmNativeAddress`; decode uses `address.handler.address().toULong()`. Native calls the generated `kffi_callback_token_encode`/`decode` helpers and wraps the returned opaque pointer. Both implementations call a shared range check before conversion and expose `pointerBits = 64`, `maxToken = Long.MAX_VALUE.toULong()`.

In the Native actual initializer, also require `sizeOf<COpaquePointerVar>() * Byte.SIZE_BITS == 64` so a corrupted or unexpected runtime target fails before registration.

- [ ] **Step 6: Make Android/JNA explicitly unsupported**

The Android actual exposes `pointerBits = Native.POINTER_SIZE * Byte.SIZE_BITS`, `maxToken = 0uL`, and throws the documented `UnsupportedOperationException` from `encode`. `decode(null)` returns null; non-null decode throws the same exception.

- [ ] **Step 7: Run JVM, Android, and host Native verification**

Run on the current Apple Silicon host:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.TokenCodecJvmTest
rtk ./gradlew :kffi:testDebugUnitTest
rtk ./gradlew :kffi:macosArm64Test
```

Expected: all codec tests pass. The Native test proves the real Kotlin pointer conversion on a supported ABI.

- [ ] **Step 8: Commit the codec**

Run:

```bash
rtk git add kffi/src kffi/build.gradle.kts
rtk git commit -m "feat(kffi): add allowlisted callback token codec"
```

---

### Task 3: Build the thread-safe registration state machine

**Files:**
- Create: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/CallbackRuntime.kt`
- Create: `kffi/src/commonTest/kotlin/io/ygdrasil/kffi/CallbackStateMachineTest.kt`
- Create: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/CallbackRuntimeJvmTest.kt`
- Create: `kffi/src/nativeTest/kotlin/io/ygdrasil/kffi/CallbackRuntimeNativeTest.kt`

**Interfaces:**
- Produces: generated-binding API `CallbackType<C>`, `PreparedCallbackRegistration<C>`, and `CallbackRuntime` under `CallbackRuntimeApi`.
- Produces: `register`, `prepare`, `activateForNativeCall`, `dispatchSafely`, `reportUnroutedFailure`, and unsafe `rearmAfterNativeQuiescence` operations.
- Implements: token-backed concurrent registry and no-userdata `UNUSED/ACTIVE/RETIRED` slot.

- [ ] **Step 1: Write failing pure state-machine tests**

Cover these transitions in `CallbackStateMachineTest` without native addresses:

```text
ONCE: ACTIVE -> CLAIMED, with exactly one successful concurrent claim
ONCE close race: exactly one of close or delivery owns the callback
REPEATING: ACTIVE -> CLOSED, no new entry afterward
REPEATING in-flight: a started invocation may finish after close
No userdata: UNUSED -> ACTIVE -> RETIRED
No userdata: ACTIVE and RETIRED reject normal registration
No userdata unsafe re-arm: RETIRED -> ACTIVE only through the annotated operation
PREPARED: PREPARED -> ACTIVE or PREPARED -> ABORTED
Published failure: token-backed ACTIVE -> CLOSED; no-userdata ACTIVE -> RETIRED
```

Use `kotlin.concurrent.atomics.AtomicInt`, `AtomicLong`, and `AtomicReference`; do not add `kotlinx.atomicfu`.

- [ ] **Step 2: Write failing JVM concurrency tests**

In `CallbackRuntimeJvmTest`, use 32 executor threads and barriers to assert:

- 10,000 created tokens are non-zero, unique, and never reused after close;
- 32 simultaneous `ONCE` dispatches invoke the lambda once;
- close-versus-delivery produces either one callback or zero, never more;
- `REPEATING` accepts calls before close, rejects new calls after close, and permits a blocked in-flight call to finish;
- callback exception reaches `onError`;
- throwing `onError` produces `CallbackExceptionHandlerFailure` at the fallback reporter;
- unknown, null, and cross-type tokens are contained and do not invoke user code;
- closing inside a repeating callback does not deadlock;
- active registry diagnostics return to their baseline after every case.

Use `ExecutorService.awaitTermination(10, TimeUnit.SECONDS)` and fail on timeout.

- [ ] **Step 3: Run the focused tests and verify they fail**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackStateMachineTest --tests io.ygdrasil.kffi.CallbackRuntimeJvmTest
```

Expected: tests do not compile because the runtime types are absent.

- [ ] **Step 4: Implement monotonic token allocation and the registry**

Use an `AtomicLong` storing the last allocated token, initially zero. Allocate with a CAS loop; when the stored value equals `Long.MAX_VALUE`, throw `IllegalStateException("Callback token space exhausted")`. Never decrement or reuse it.

Use 64 registry buckets, each an `AtomicReference<Map<ULong, RegistryEntry<*>>>`. Insert, claim, and remove by immutable-map CAS so lookup and close are thread-safe without a platform lock. Select the bucket with `(token % 64uL).toInt()`.

Each `RegistryEntry<C>` stores:

```kotlin
val type: CallbackType<C>
val callback: C
val policy: CallbackPolicy
val onError: CallbackExceptionHandler
val state: AtomicReference<DeliveryState>
val inFlight: AtomicInt
```

`CallbackType<C>` owns the no-userdata slot so two callback typedefs cannot collide. Its public constructor is guarded by `CallbackRuntimeApi` and accepts the canonical type ID plus `hasRoutingUserdata`.

- [ ] **Step 5: Implement exact delivery and close semantics**

- `ONCE` claims and removes the entry before executing native-to-Kotlin conversions or user code.
- `REPEATING` increments `inFlight` only after observing `ACTIVE`; close removes the mapping and prevents later increments, but never waits for `inFlight` to reach zero.
- After incrementing `inFlight`, `REPEATING` rechecks `ACTIVE`; if close won the race, it decrements and skips invocation. This gives close a precise linearization point while preserving already-started work.
- `close()` is idempotent and sets `isClosed` after either explicit close or successful `ONCE` claim.
- Unknown, late, null, or type-mismatched userdata is a no-op after calling the guarded unrouted reporter only for malformed/type-mismatched values; a token absent because it was closed or already consumed is a silent late-call no-op.
- No-userdata close and one-shot completion retire the `CallbackType` permanently by default.
- `rearmAfterNativeQuiescence` performs only `RETIRED -> ACTIVE`, carries `@UnsafeCallbackRearmApi`, and documents that it neither performs nor verifies native quiescence.

- [ ] **Step 6: Implement transactional direct-call publication**

Expose this generated-binding operation under `CallbackRuntimeApi`:

```kotlin
fun <C : Callback> activateForNativeCall(
    prepared: PreparedCallbackRegistration<C>,
    downcall: (CallbackRegistration<C>) -> Unit,
): CallbackRegistration<C>
```

It must publish `ACTIVE` immediately before invoking `downcall`, so reentrant native delivery is valid. If `downcall` throws, close/remove a token-backed entry and retire a published no-userdata entry before rethrowing. An unactivated prepared entry becomes `ABORTED` and publishes no callback. Allocated tokens may be discarded but never reused.

- [ ] **Step 7: Implement the complete exception barrier**

`dispatchSafely` catches token decoding, type validation, delivery claim, argument conversion inside the generated `invoke` lambda, user callback execution, and error-handler execution. If the registration handler throws, report `CallbackExceptionHandlerFailure(original, handlerFailure)`. `reportUnroutedFailure` delegates only to the final guarded fallback.

The generated trampoline will add a second outer `try/catch(Throwable)` in Task 6, so an implementation error in `dispatchSafely` still cannot cross FFI.

- [ ] **Step 8: Add Native state and concurrency coverage**

Mirror the ONCE duplicate, repeating-close, no-userdata retirement/re-arm, exception containment, and baseline registry-count cases in `CallbackRuntimeNativeTest`. Use `Worker` only in test code for concurrent delivery and bound each wait to 10 seconds.

- [ ] **Step 9: Run all runtime tests and commit**

Run:

```bash
rtk ./gradlew :kffi:jvmTest :kffi:macosArm64Test
rtk git add kffi/src
rtk git commit -m "feat(kffi): add safe callback registration runtime"
```

Expected: all tests pass without hangs; the runtime registry count returns to its initial value.

---

### Task 4: Add typed callback metadata and canonical validation to `kextract`

**Files:**
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/callbacks/CallbackBindingsConfig.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/callbacks/CallbackBindingsLoader.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/callbacks/CanonicalDeclarationIndex.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/callbacks/CallbackAnalyzer.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/Options.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/callbacks/CallbackBindingsConfigTest.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/callbacks/CallbackAnalyzerTest.kt`

**Interfaces:**
- Produces: CLI `--callback-bindings FILE` and `Options.callbackBindings`.
- Produces: typed `DirectFunctionBinding` and `CallbackInfoBinding` metadata.
- Produces: canonical declaration IDs `typedef:`, `struct:`, `function:`, and `constant:`.

- [ ] **Step 1: Write failing YAML schema tests**

Use a minimal fixture header containing a userdata callback, a no-userdata callback, a direct setter, a callback-info struct, a mode typedef/constants, and an owning function. Test successful loading plus failures for:

- unknown YAML property;
- duplicate canonical binding;
- missing owner or `CONSUMED_DURING_CALL` lifetime;
- stale canonical ID;
- callback typedef mismatch;
- routing/application userdata overlap;
- reserved userdata that is not the last compatible callback argument;
- invalid mode field/type/constant;
- non-void callback return;
- callback userdata name with a non-opaque-pointer type;
- ambiguous callback parameters in an owner.

Every diagnostic assertion must include the canonical declaration ID and the reason.

- [ ] **Step 2: Run the tests and verify the option is absent**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.callbacks.CallbackBindingsConfigTest --tests org.graphiks.kextract.callbacks.CallbackAnalyzerTest
```

Expected: test compilation fails because callback configuration types do not exist.

- [ ] **Step 3: Implement a strict mutable Jackson schema**

Use Jackson-friendly classes with initialized mutable properties and this exact top-level shape:

```kotlin
class CallbackBindingsConfig {
    var directFunctionBindings: List<DirectFunctionBinding> = emptyList()
    var callbackInfoBindings: List<CallbackInfoBinding> = emptyList()
}

enum class CallbackInfoLifetime { CONSUMED_DURING_CALL }
```

`DirectFunctionBinding` contains `function`, `callbackParameter`, `callbackType`, and optional `routingUserdataParameter`. `CallbackInfoBinding` contains `struct`, `owner`, `callbackField`, `callbackType`, `routingUserdataField`, `applicationUserdataFields`, and optional `mode`. `owner` contains `function`, `parameterPath`, and `lifetime`; `mode` contains `field`, `type`, and `allowedConstants`.

Configure `ObjectMapper(YAMLFactory())` with unknown properties rejected. Reject empty canonical IDs before AST analysis.

- [ ] **Step 4: Add canonical indexing and exact-type validation**

Index declarations after filtering and before `NameMangler`, so canonical IDs always use the original C names rather than Kotlin aliases. Resolve every configured ID exactly once, validate normalized C types, callback ownership, field/parameter paths, userdata distinctness, mode constants, and the explicit lifetime, then carry the validated callback model alongside the mangled AST into Kotlin generation.

Automatic signature analysis remains generic: accept only `void` callbacks, identify opaque-pointer parameters named `userdata`, `userdata1`, `userdata2`, and so on, reserve the highest numeric suffix, and fail conflicting name/type combinations. Configuration never overrides a structurally invalid signature.

- [ ] **Step 5: Wire the CLI and pipeline**

Add:

```kotlin
val callbackBindingsPath by option(
    "--callback-bindings",
    metavar = "FILE",
    help = "YAML metadata for generated safe callback bindings",
)
```

Load the file once in `KextractCommand`, store the typed object in `Options.callbackBindings`, validate it against the transformed AST in `KextractTool`, and pass the validated callback model to `KotlinGenerator`. Preserve every master/KMP option from the refresh plan.

- [ ] **Step 6: Run tests and commit in the submodule**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.callbacks.CallbackBindingsConfigTest --tests org.graphiks.kextract.callbacks.CallbackAnalyzerTest
rtk git -C kextract add src
rtk git -C kextract commit -m "feat: validate typed callback binding metadata"
```

---

### Task 5: Generate the common typed callback API

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackModel.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCommonEmitter.kt`
- Create: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`

**Interfaces:**
- Produces: one typed `fun interface` extending `Callback`, plus a companion, per supported callback typedef.
- Produces: typed `register` plus opt-in `rearmAfterNativeQuiescence` for no-userdata callbacks.
- Changes: raw callback-valued fields and parameters map to `NativeAddress?`.
- Removes: generated callback `expect class`, `handler`, `close`, and `allocate(callback)` APIs.

- [ ] **Step 1: Write failing generic generation tests**

Generate from callbacks named `SampleCallback` and `NoUserdataCallback`, not `WGPU*`, to enforce generic behavior. Assert the common output contains:

```kotlin
fun interface SampleCallback : Callback {
    fun invoke(value: UInt, userdata1: NativeAddress?)
    companion object
}

expect fun SampleCallback.Companion.register(
    policy: CallbackPolicy,
    onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
    callback: SampleCallback,
): CallbackRegistration<SampleCallback>
```

Assert the reserved final userdata is absent from `invoke`, the earlier userdata remains, `policy` has no default, and raw function-pointer fields use `NativeAddress?`. For `NoUserdataCallback`, assert a normal `register` plus error-level annotated `rearmAfterNativeQuiescence` are emitted. Assert a non-void callback fails generation.

- [ ] **Step 2: Run the test and verify current generated callback classes fail it**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
```

Expected: assertions fail because the generator emits per-registration callback classes and filters to `WGPU`/`wgpu` names.

- [ ] **Step 3: Remove WebGPU-only KMP filters**

Remove `startsWith("WGPU")` and `startsWith("wgpu")` guards from all four KMP builders. Rely on the existing include filters for consumer selection. Add a regression case proving an ordinary non-callback `sample_get_value` function is still generated in every KMP target.

- [ ] **Step 4: Emit the common callback model**

Create one normalized `KotlinCallbackModel` from `CallbackAnalyzer` and feed the same model to every platform emitter. Emit the public SAM interface, canonical `CallbackType`, `expect register`, internal `expect prepare`, and—only for no-userdata callbacks—the annotated re-arm operations.

Use this exact WebGPU-equivalent shape for the queue callback:

```kotlin
fun interface WGPUQueueWorkDoneCallback : Callback {
    fun invoke(
        status: WGPUQueueWorkDoneStatus,
        message: WGPUStringView,
        userdata1: NativeAddress?,
    )

    companion object
}
```

`userdata2` is reserved for routing and therefore never reaches application code.

- [ ] **Step 5: Map callback typedefs to raw addresses outside the typed API**

When a callback typedef appears as a C function parameter or struct field, emit `NativeAddress?` and adapt it as an address in JVM/Native downcalls and struct accessors. The callback SAM exists only for registration; it is not itself a raw function pointer.

- [ ] **Step 6: Run generator regressions and commit**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.KmpRefreshIntegrationTest
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.GeneratorIntegrationTest
rtk git -C kextract add src
rtk git -C kextract commit -m "feat: generate typed callback registrations"
```

---

### Task 6: Generate permanent JVM/Native trampolines and explicit Android failure

**Files:**
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackNativeEmitter.kt`
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackAndroidEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`

**Interfaces:**
- JVM: one lazy `Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global())` per callback type.
- Native: one top-level `staticCFunction` per callback type.
- Android/JNA: actual registration methods that throw before allocating resources.

- [ ] **Step 1: Add failing platform-output assertions**

Assert for each callback type:

- JVM contains one `Arena.global()`, one upcall stub, static method handle binding, and no `Arena.ofShared()`;
- Native contains one `staticCFunction`, no mutable `*_callback` lambda slot, and no trampoline-address keyed map;
- both trampolines call `CallbackRuntime.dispatchSafely` using the analyzed routing userdata;
- both wrap their entire body in an outer `try/catch(Throwable)` calling `CallbackRuntime.reportUnroutedFailure`;
- Android register/prepare/re-arm actuals throw the documented unsupported exception;
- the no-userdata trampoline routes through its `CallbackType` without inventing userdata.

- [ ] **Step 2: Run the focused test and verify it fails**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
```

Expected: platform trampoline assertions fail.

- [ ] **Step 3: Emit the permanent JVM trampoline**

Generate a private per-type object whose lazy address is built by `Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global())`. The method handle targets a static method; it never binds a registration object. `register` and `prepare` pass this permanent address to `CallbackRuntime`.

Put native argument adaptation inside the `dispatchSafely` invocation so conversion failures use the registration's handler after an ONCE claim. Put an additional outer `try/catch(Throwable)` around token adaptation and the `dispatchSafely` call.

- [ ] **Step 4: Emit the permanent Kotlin/Native trampoline**

Generate one top-level `staticCFunction` with the exact C signature. Convert callback arguments and invoke the typed SAM only inside `dispatchSafely`. Remove all generated mutable lambda variables and all per-trampoline callback maps.

- [ ] **Step 5: Emit Android/JNA failure actuals**

Every generated Android `register`, `prepare`, and unsafe re-arm actual throws:

```kotlin
UnsupportedOperationException(
    "Android/JNA callback registration is not supported; use raw bindings or an Android Native target",
)
```

No JNA callback object, arena, registry token, or userdata is allocated before this throw.

- [ ] **Step 6: Run tests and commit**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest
rtk ./gradlew :kextract:test
rtk git -C kextract add src
rtk git -C kextract commit -m "feat: generate permanent callback trampolines"
```

---

### Task 7: Generate transactional direct overloads and safe callback-info factories

**Files:**
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/integration/CallbackGeneratorIntegrationTest.kt`
- Modify: `kextract/src/test/kotlin/org/graphiks/kextract/callbacks/CallbackAnalyzerTest.kt`

**Interfaces:**
- Produces: safe direct callback overloads returning `CallbackRegistration<C>`.
- Produces: safe `CallbackInfo.allocate` overloads that wire callback/routing userdata and require valid modes.
- Preserves: raw direct functions and raw `allocate(allocator)` factories.

- [ ] **Step 1: Add failing direct-overload and callback-info tests**

Assert a configured direct setter emits a platform `expect`/`actual` downcall preflight, then a safe overload that calls the preflight, internal `prepare`, `CallbackRuntime.activateForNativeCall`, and finally the raw function with `registration.callback` and `registration.userdata`. Assert it cannot accept independent routing userdata.

Assert a configured mode-bearing info struct emits:

```kotlin
fun WGPUQueueWorkDoneCallbackInfo.Companion.allocate(
    allocator: MemoryAllocator,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<WGPUQueueWorkDoneCallback>,
    userdata1: NativeAddress? = null,
): WGPUQueueWorkDoneCallbackInfo
```

and accepts only `WaitAnyOnly`, `AllowProcessEvents`, and `AllowSpontaneous`. Assert zero, `Force32`, and unknown values throw `IllegalArgumentException`. Assert a configured info struct without a mode has no mode parameter.

- [ ] **Step 2: Run the focused tests and verify they fail**

Run:

```bash
rtk ./gradlew :kextract:test --tests org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest --tests org.graphiks.kextract.callbacks.CallbackAnalyzerTest
```

Expected: safe overload/factory assertions fail.

- [ ] **Step 3: Generate transactional direct-function overloads**

Generate an internal preflight `expect`/`actual` for each direct callback binding. The JVM actual eagerly evaluates the lazy symbol address and downcall handle; Native is empty because the symbol is statically linked; Android/JNA registration fails before the raw call. Invoke this preflight and validate non-callback arguments before `prepare`.

Generate the common safe overload using `activateForNativeCall`; publish immediately before the raw call and return the active registration only after the call returns normally.

For a configured no-userdata direct function, emit a separate `rearmAfterNativeQuiescence` convenience overload carrying `@UnsafeCallbackRearmApi`; do not add a boolean or configuration flag that bypasses compiler opt-in.

- [ ] **Step 4: Generate safe callback-info factories**

Validate mode before calling the existing raw allocator. Then set the callback field from `registration.callback`, set the routing userdata from `registration.userdata`, and expose only configured application userdata parameters. The factory does not own or close the registration.

Document `CONSUMED_DURING_CALL`: the owning native call copies the callback-info value or containing descriptor, so the allocator scope may close after the call while the registration remains live.

- [ ] **Step 5: Run the full generator suite and commit**

Run:

```bash
rtk ./gradlew :kextract:test
rtk git -C kextract add src
rtk git -C kextract commit -m "feat: generate safe callback binding helpers"
```

---

### Task 8: Configure WebGPU callbacks and regenerate `wgpu4k-native`

**Files:**
- Create: `wgpu4k-native-specs/src/jvmMain/resources/callback-bindings.yml`
- Modify: `wgpu4k-native/build.gradle.kts`
- Regenerate: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt`
- Regenerate: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt`
- Regenerate: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt`
- Regenerate: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt`
- Modify submodule pointer: `kextract`
- Create: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/CallbackInfoFactoryJvmTest.kt`
- Create: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/GeneratedCallbackJvmTest.kt`
- Create: `wgpu4k-native/src/nativeTest/kotlin/io/ygdrasil/wgpu/GeneratedCallbackNativeTest.kt`

**Interfaces:**
- Configures: ten WebGPU callback-info structures and direct `wgpuSetLogCallback`.
- Reserves: `userdata2` for every two-userdata WebGPU callback; preserves `userdata1` for the application.
- Requires: explicit callback mode for all configured mode-bearing structures.

- [ ] **Step 1: Add the complete WebGPU metadata file**

The direct binding is:

```yaml
directFunctionBindings:
  - function: function:wgpuSetLogCallback
    callbackParameter: callback
    callbackType: typedef:WGPULogCallback
    routingUserdataParameter: userdata
```

Add these exact callback-info mappings, each with `callbackField: callback`, `routingUserdataField: userdata2`, `applicationUserdataFields: [userdata1]`, and `lifetime: CONSUMED_DURING_CALL`:

| Struct | Callback typedef | Owner function | Parameter path | Mode |
|---|---|---|---|---|
| `WGPUBufferMapCallbackInfo` | `WGPUBufferMapCallback` | `wgpuBufferMapAsync` | `callbackInfo` | required |
| `WGPUCompilationInfoCallbackInfo` | `WGPUCompilationInfoCallback` | `wgpuShaderModuleGetCompilationInfo` | `callbackInfo` | required |
| `WGPUCreateComputePipelineAsyncCallbackInfo` | `WGPUCreateComputePipelineAsyncCallback` | `wgpuDeviceCreateComputePipelineAsync` | `callbackInfo` | required |
| `WGPUCreateRenderPipelineAsyncCallbackInfo` | `WGPUCreateRenderPipelineAsyncCallback` | `wgpuDeviceCreateRenderPipelineAsync` | `callbackInfo` | required |
| `WGPUDeviceLostCallbackInfo` | `WGPUDeviceLostCallback` | `wgpuAdapterRequestDevice` | `descriptor.deviceLostCallbackInfo` | required |
| `WGPUPopErrorScopeCallbackInfo` | `WGPUPopErrorScopeCallback` | `wgpuDevicePopErrorScope` | `callbackInfo` | required |
| `WGPUQueueWorkDoneCallbackInfo` | `WGPUQueueWorkDoneCallback` | `wgpuQueueOnSubmittedWorkDone` | `callbackInfo` | required |
| `WGPURequestAdapterCallbackInfo` | `WGPURequestAdapterCallback` | `wgpuInstanceRequestAdapter` | `callbackInfo` | required |
| `WGPURequestDeviceCallbackInfo` | `WGPURequestDeviceCallback` | `wgpuAdapterRequestDevice` | `callbackInfo` | required |
| `WGPUUncapturedErrorCallbackInfo` | `WGPUUncapturedErrorCallback` | `wgpuAdapterRequestDevice` | `descriptor.uncapturedErrorCallbackInfo` | absent |

For every required mode, set `field: mode`, `type: typedef:WGPUCallbackMode`, and these exact canonical constants:

```yaml
allowedConstants:
  - constant:WGPUCallbackMode_WaitAnyOnly
  - constant:WGPUCallbackMode_AllowProcessEvents
  - constant:WGPUCallbackMode_AllowSpontaneous
```

- [ ] **Step 2: Make the generation task consume the metadata**

Add these arguments before the header path in `generateBindingsFromHeader`:

```kotlin
"--callback-bindings",
project(":wgpu4k-native-specs").file("src/jvmMain/resources/callback-bindings.yml").absolutePath,
```

Declare the YAML file and fetched `wgpu.h` as task inputs and all four generated source roots as outputs.

- [ ] **Step 3: Regenerate only through Gradle**

Run:

```bash
rtk ./gradlew fetch-native-dependencies
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
rtk git diff --check
```

Expected: all four generated files change; `Arena.ofShared`, generated mutable callback lambdas, and `CallbackHolder` disappear.

- [ ] **Step 4: Add generated API and mode tests**

In JVM tests, allocate each of the ten info structs with a typed registration, assert callback and userdata wiring, and assert all invalid modes fail before allocation. Mirror a representative mode-bearing and mode-free factory case in the Native test. In both platforms, register two simultaneous callbacks of the same type, dispatch their trampolines with different tokens, and assert correct routing. Assert Android/JNA failure in `androidUnitTest`.

- [ ] **Step 5: Compile every source set and run host tests**

Run:

```bash
rtk ./gradlew :wgpu4k-native:jvmTest :wgpu4k-native:macosArm64Test :wgpu4k-native:compileDebugKotlinAndroid
rtk ./gradlew :kextract:test
```

Expected: common/JVM/Native/Android generated sources compile; JVM and Native generated callback tests pass.

- [ ] **Step 6: Commit the generated binding transition**

Run:

```bash
rtk git add kextract wgpu4k-native-specs/src/jvmMain/resources/callback-bindings.yml wgpu4k-native/build.gradle.kts wgpu4k-native/src
rtk git commit -m "feat: generate safe WebGPU callback registrations"
```

---

### Task 9: Migrate demonstrations to explicit registration ownership

**Files:**
- Modify: `demo/common/src/commonMain/kotlin/ext.kt`
- Modify: `demo/common/src/commonMain/kotlin/HeadlessTriangle.kt`
- Modify: `demo/desktop-and-ios/src/jvmMain/kotlin/Capture.jvm.kt`
- Modify: `demo/common/src/androidNativeMain/kotlin/main.kt`

**Interfaces:**
- Uses: explicit `CallbackPolicy`, typed callback-info factories, and deterministic `close()`.
- Preserves: existing event processing choices and rendering behavior.

- [ ] **Step 1: Compile demos to capture all breaking call sites**

Run:

```bash
rtk ./gradlew :demo:desktop-and-ios:compileKotlinJvm :demo:common:compileKotlinMetadata
```

Expected: compilation fails at every old `WGPU*Callback.allocate`, `.handler`, and raw callback-info wiring call.

- [ ] **Step 2: Migrate logging to the transactional direct overload**

Change both log callback owners from `WGPULogCallback?` to `CallbackRegistration<WGPULogCallback>?`. Close the previous registration, call the safe `wgpuSetLogCallback(policy = REPEATING)`, and retain the returned registration for application lifetime. The public log callback receives only `level` and `message`; routing userdata is hidden.

- [ ] **Step 3: Migrate adapter, device, map, and capture callbacks**

- Use `ONCE` for request adapter, request device, buffer map, and queue completion.
- Use `AllowSpontaneous` only where the current code intentionally accepts arbitrary-thread delivery.
- Use `AllowProcessEvents` for loops that call `wgpuInstanceProcessEvents`.
- Use `WaitAnyOnly` only when the returned `WGPUFuture` is passed to `wgpuInstanceWaitAny`.
- Close each registration in `finally`, including failure and timeout paths.
- Remove every `scope.bufferOfAddress(callback.handler)` workaround; the safe factory supplies routing userdata.

- [ ] **Step 4: Compile and run existing smoke tests**

Run:

```bash
rtk ./gradlew :demo:desktop-and-ios:verifyJvmHeadlessRender
rtk ./gradlew :demo:desktop-and-ios:verifyNativeHeadlessRender
```

Expected: both headless renders pass with the same pixel assertions.

- [ ] **Step 5: Commit the migration**

Run:

```bash
rtk git add demo
rtk git commit -m "refactor(demo): own callback registrations explicitly"
```

---

### Task 10: Exercise the runtime through a real delayed C fixture

**Files:**
- Create: `kffi/src/ffiTest/resources/callback_fixture.h`
- Create: `kffi/src/ffiTest/resources/callback_fixture.c`
- Create: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/CallbackFfiJvmTest.kt`
- Create: `kffi/src/nativeTest/kotlin/io/ygdrasil/kffi/CallbackFfiNativeTest.kt`
- Create: `kffi/src/nativeInterop/cinterop/callbackFixture.def`
- Modify: `kffi/build.gradle.kts`

**Interfaces:**
- C fixture stores callback/userdata and supports delayed, threaded, duplicate, shuffled, post-close, and synchronous-quiescence invocation.
- Verifies: real `void*`/`uintptr_t` round trip and process-lifetime trampoline behavior.

- [ ] **Step 1: Add the C fixture API**

Expose these operations from the header:

```c
typedef void (*fixture_callback)(uint32_t value, void *application_userdata, void *routing_userdata);
typedef void (*fixture_no_userdata_callback)(uint32_t value);

void fixture_store(fixture_callback callback, void *application_userdata, void *routing_userdata);
void fixture_fire_now(uint32_t value);
void fixture_fire_twice(uint32_t value);
void fixture_fire_after_ms(uint32_t value, uint32_t delay_ms);
void fixture_store_many(uint32_t index, fixture_callback callback, void *routing_userdata);
void fixture_fire_many_shuffled(uint32_t count);
void fixture_store_no_userdata(fixture_no_userdata_callback callback);
void fixture_fire_no_userdata_after_ms(uint32_t value, uint32_t delay_ms);
void fixture_unregister_and_join(void);
uintptr_t fixture_roundtrip_userdata(void *userdata);
uint32_t fixture_active_native_slots(void);
```

Protect fixture state with a mutex, use joinable pthreads, and make `fixture_unregister_and_join` clear stored pointers only after every worker joins. Add `_Static_assert(sizeof(void *) == sizeof(uintptr_t), "unsupported callback token ABI")`.

- [ ] **Step 2: Wire fixture compilation for JVM and host Native tests**

In Gradle, compile a shared library for JVM FFM and a static archive for the `macosArm64Test` cinterop with `cc -std=c11 -fPIC -pthread`. Make `jvmTest` and the host Native cinterop/test tasks depend on fixture compilation. Put artifacts under `kffi/build/callback-fixture`; do not commit binaries.

- [ ] **Step 3: Write failing real-FFI tests**

Test all of the following with a 10-second deadline per case:

- callback delivered after the allocation scope and registering function return;
- callback delivered from a C-created thread;
- duplicate native invocation reaches `ONCE` at most once;
- 1,000 simultaneous registrations fire in shuffled order to the correct lambdas;
- callback after close is ignored;
- repeated create/deliver/close loops restore native-slot and runtime-registry counts;
- unknown and cross-type routing tokens never invoke application code;
- callback and error-handler exceptions stay contained on the C thread;
- a direct-call failure before publication leaves no entry;
- a simulated throw after C stores the callback closes a token-backed entry;
- no-userdata normal re-registration fails after retirement;
- after `fixture_unregister_and_join`, opt-in `rearmAfterNativeQuiescence` succeeds and receives only new calls;
- `fixture_roundtrip_userdata` returns the exact token bits.

- [ ] **Step 4: Run JVM and Native fixture tests**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFfiJvmTest
rtk ./gradlew :kffi:macosArm64Test
```

Expected: both real-FFI suites pass without a crash, hang, registry leak, or native slot leak.

- [ ] **Step 5: Commit the fixture**

Run:

```bash
rtk git add kffi
rtk git commit -m "test(kffi): cover delayed callbacks through C FFI"
```

---

### Task 11: Add bounded WebGPU stress coverage and CI regeneration checks

**Files:**
- Create: `demo/common/src/commonMain/kotlin/CallbackStress.kt`
- Modify: `demo/desktop-and-ios/src/jvmMain/kotlin/HeadlessMain.kt`
- Modify: `demo/desktop-and-ios/src/desktopMain/kotlin/main.kt`
- Modify: `demo/desktop-and-ios/build.gradle.kts`
- Modify: `.github/workflows/test.yml`

**Interfaces:**
- Adds: headless callback stress entry point for JVM and host Kotlin/Native.
- Exercises: `WaitAnyOnly`, `AllowProcessEvents`, `AllowSpontaneous`, device loss, uncaptured errors, close-before-delivery, and many queue submissions.
- Adds: clean regeneration and complete kextract tests to CI.

- [ ] **Step 1: Implement a common bounded callback stress scenario**

Use `TimeSource.Monotonic` deadlines and fail each phase after 20 seconds with pending registration IDs and selected callback mode. The scenario must:

1. submit work and create at least 1,000 concurrent `ONCE` queue-work-done registrations;
2. verify each ID is delivered at most once;
3. close a deterministic subset before delivery and verify suppression;
4. exercise each callback mode with its matching event strategy;
5. close callback-info `memoryScope` before deferred callback delivery;
6. request a dedicated device whose descriptor contains both a `REPEATING` uncaptured-error registration and an `ONCE` device-lost registration;
7. trigger two validation errors on that device, observe both uncaptured-error calls, then destroy the device and observe one device-lost delivery;
8. close every remaining registration in `finally`.

Run blocking `wgpuInstanceWaitAny` or `wgpuDevicePoll(wait = 1u)` only in the headless stress process, never in the graphical render loop.

- [ ] **Step 2: Add dedicated JVM and Native Gradle verification tasks**

Add `verifyJvmCallbackStress` and `verifyNativeCallbackStress` tasks that invoke the headless entry point with `--callback-stress`, set a two-minute process timeout, and depend on `fetch-native-dependencies`. A timeout must fail the task and preserve stdout/stderr diagnostics.

- [ ] **Step 3: Run the stress tasks locally**

Run:

```bash
rtk ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress
rtk ./gradlew :demo:desktop-and-ios:verifyNativeCallbackStress
```

Expected: both tasks report all modes, device-loss delivery, repeating uncaptured-error delivery, and zero pending registrations.

- [ ] **Step 4: Add CI tests and a reproducible-generation gate**

Ensure `.github/workflows/test.yml` runs:

```yaml
- run: ./gradlew build
- run: ./gradlew :kextract:test
- run: ./gradlew :kffi:jvmTest :wgpu4k-native:jvmTest
- run: ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress
- run: ./gradlew :demo:desktop-and-ios:verifyNativeCallbackStress
- run: ./gradlew :wgpu4k-native:generateBindingsFromHeader
- run: git diff --exit-code -- wgpu4k-native/src
```

Do not restore `-x :kextract:test`. Add Native compile jobs for every configured target so the `callbackTokenCodec` C interop and its 64-bit pointer assertions compile for iOS, Linux, MinGW, Android Native, and macOS; run real Native tests on available macOS/Linux/Windows hosts.

- [ ] **Step 5: Run the final verification suite**

Run:

```bash
rtk ./gradlew :kextract:test :kffi:jvmTest :kffi:macosArm64Test :wgpu4k-native:jvmTest :wgpu4k-native:macosArm64Test
rtk ./gradlew :demo:desktop-and-ios:verifyJvmHeadlessRender :demo:desktop-and-ios:verifyNativeHeadlessRender
rtk ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress :demo:desktop-and-ios:verifyNativeCallbackStress
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
rtk git diff --exit-code -- wgpu4k-native/src
rtk git diff --check
```

Expected: every command passes, regeneration is clean, and no asynchronous test hangs.

- [ ] **Step 6: Commit CI and stress coverage**

Run:

```bash
rtk git add demo .github/workflows/test.yml
rtk git commit -m "test: stress safe WebGPU callback delivery"
```

---

## Final acceptance review

- [ ] JVM and Kotlin/Native trampolines have process lifetime and are created once per callback type.
- [ ] Multiple concurrent registrations of the same callback type route correctly.
- [ ] `ONCE` delivers at most once; `REPEATING` stops new entry after close without deadlocking in-flight work.
- [ ] All close paths are idempotent; late callbacks do not dereference released callback context.
- [ ] No-userdata callbacks retire by default; re-arm requires `UnsafeCallbackRearmApi` and explicit native quiescence.
- [ ] Complete trampoline conversion and user code are inside a non-throwing exception barrier.
- [ ] Direct safe overload rollback leaves no live registry entry.
- [ ] Safe mode-bearing WebGPU factories reject zero, `Force32`, and unknown modes.
- [ ] Android/JNA failure is immediate and explicit; Android Native uses the Native implementation.
- [ ] The C fixture and WebGPU stress tests pass with bounded timeouts on JVM and host Native.
- [ ] All configured target ABIs compile the pointer-width assertion.
- [ ] `:kextract:test` runs in CI and generated WebGPU sources reproduce with a clean diff.
- [ ] Coroutine cancellation, exactly-once continuation resumption, and off-render-thread event pumping remain documented as a separate future `wgpu4k` plan.
