# Safe Generated Callback Registrations Design

**Date:** 2026-07-13

**Status:** Approved in design discussion

**Target branch:** `feature/add-gradle-kffi-subproject`

**Repositories involved:** `wgpu4k-native`, its `kextract` submodule, and a later follow-up in `wgpu4k`

## Summary

Replace generated callback objects that own short-lived native resources with an explicit, typed `CallbackRegistration`. Each callback type uses one process-lifetime native trampoline. Registrations are routed through an opaque token stored in the last compatible `userdata` argument and a thread-safe runtime registry.

The first implementation targets JVM and Kotlin/Native. It also generates safe WebGPU callback-info factories that require an explicit `WGPUCallbackMode` whenever the native structure defines a mode field. Android/JNA callback allocation remains unsupported in this iteration.

This change provides the native safety foundation required by `wgpu4k`. Coroutine dispatch, cancellation integration, and off-render-thread event pumping remain a later `wgpu4k` responsibility.

## Context and problem statement

The current generated callback implementation is unsafe for deferred native callbacks:

- On JVM, each generated callback owns an `Arena.ofShared()` and an upcall stub. Closing the callback invalidates the stub even if native code may still call it.
- On Kotlin/Native, each callback type stores one lambda in a top-level mutable variable and exposes one `staticCFunction`. A second active callback of the same type overwrites the first lambda, and `close()` clears the shared slot.
- The generated API does not couple the callback pointer with the `userdata` needed to route concurrent callbacks.
- Raw callback-info allocation permits the caller to leave `WGPUCallbackMode` at its invalid zero value.
- The safe lifetime of the callback-info allocation is easily confused with the longer lifetime of the callback and its context.

The feature branch already targets `wgpu-native` `v29.0.0.0` and exposes the relevant event APIs on JVM and Kotlin/Native:

- `wgpuInstanceProcessEvents`
- `wgpuInstanceWaitAny`
- `wgpuDevicePoll`

The missing native-layer primitive is therefore safe callback registration and routing, not another hidden event loop.

## Generator refresh prerequisite

As observed on 2026-07-13, the `kextract` submodule points to commit `a3bca986f3c3cdf074f1d2fb4e3761c276f6e5c3` on `feat/multiplatform`. Its default branch has moved to `master` commit `28c5ab6b278db663b91722ec6988bb7c24351d38`, tagged `v0.0.3`. The implementation must refresh the remote references again before choosing its exact baseline.

The branches diverge from `v0.0.2` (`ab37bd2d2723aa719ce20e34f4bcb9e7a572f122`):

- `feat/multiplatform` has three KMP-specific commits that are not on `master`;
- `master` has substantial generator, CLI, Windows, variadic, and Objective-C changes;
- `master` does not contain the `KotlinKmp*Builder` classes.

A direct submodule pointer update would therefore remove KMP generation. Before callback work begins:

1. Create a new `kextract` branch from the refreshed `master` baseline (`v0.0.3` at design time).
2. Forward-port the three KMP changes onto the current architecture without rewriting the historical branch.
3. Prove that the refreshed generator produces functionally equivalent JVM and Kotlin/Native bindings.
4. Implement callback registration on the refreshed branch.
5. Advance the `wgpu4k-native` submodule pointer only after the refreshed generator passes independently.

The refresh and callback changes should remain reviewable as separate commits or pull requests.

## Goals

- Keep every native trampoline valid for the process lifetime.
- Route multiple concurrent callbacks of the same type correctly when the C signature provides `userdata`.
- Make one-shot versus persistent delivery explicit.
- Make closure idempotent and safe in the presence of late native calls.
- Ensure a one-shot registration delivers at most once.
- Prevent Kotlin exceptions from crossing the FFI boundary.
- Require explicit WebGPU callback modes on the safe API path.
- Preserve raw bindings for low-level and unsupported use cases.
- Generate the behavior from `kextract`; do not patch generated WebGPU files manually.
- Test delayed invocation, cancellation, device loss, closure, concurrency, and many queue submissions.

## Non-goals

- Implement coroutine suspension or dispatch in `wgpu4k-native`.
- Start threads or event loops from `kffi` or generated bindings.
- Hide `wgpuInstanceProcessEvents`, `wgpuInstanceWaitAny`, or `wgpuDevicePoll` behind a single policy.
- Implement Android/JNA upcalls in this iteration.
- Support C callbacks with non-`void` return values in this iteration.
- Guarantee safe reuse of a callback type without `userdata` unless native quiescence is explicitly configured.
- Preserve source or binary compatibility with `CallbackHolder` and `allocate(callback)`.

## Chosen architecture

### `kffi` runtime

`kffi` owns the platform-neutral registration contract and platform-specific registry implementation:

- `CallbackPolicy`
- `CallbackRegistration<C>`
- `CallbackExceptionHandler`
- opaque token allocation
- registration state transitions
- thread-safe lookup, claim, close, and retirement
- safe conversion between token values and `NativeAddress`

It does not know about WebGPU callback modes or event pumping.

### `kextract` generator

`kextract` analyzes each callback signature and generates:

- one permanent trampoline per callback type;
- a typed `register` factory;
- routing through the last compatible `userdata` parameter;
- safe overloads for direct `callback + userdata` functions;
- opt-in safe factories for callback-info structures;
- diagnostics for ambiguous or unsupported callback signatures;
- an explicit no-userdata policy.

WebGPU-specific callback-info semantics are supplied through consumer configuration rather than hard-coded names.

### Generated `wgpu4k-native` bindings

The generated bindings expose both:

- raw C-level allocation and function entry points;
- safe typed registration and callback-info factories.

The safe WebGPU configuration requires a valid `WGPUCallbackMode` and reserves `userdata2` for routing. `userdata1` remains available to the application.

### Later `wgpu4k` integration

The high-level library will own:

- coroutine suspension and resumption;
- dispatcher selection;
- cancellation and device-loss propagation;
- off-render-thread calls to blocking wait or poll functions;
- selection of callback mode and event-pumping strategy.

## Public API contract

The normative API shape is:

```kotlin
enum class CallbackPolicy {
    ONCE,
    REPEATING,
}

fun interface CallbackExceptionHandler {
    fun onException(error: Throwable)

    companion object {
        val Default: CallbackExceptionHandler
    }
}

interface CallbackRegistration<C : Callback> : AutoCloseable {
    val callback: NativeAddress
    val userdata: NativeAddress?
    val policy: CallbackPolicy
    val isClosed: Boolean

    override fun close()
}
```

The exact `expect`/`actual` split is an implementation concern, but these semantics are required.

Each generated callback type exposes a typed factory. The reserved last `userdata` parameter is omitted from the public lambda:

```kotlin
val registration = WGPUQueueWorkDoneCallback.register(
    policy = CallbackPolicy.ONCE,
    onError = CallbackExceptionHandler.Default,
) { status, userdata1 ->
    // userdata2 is reserved for registration routing.
}
```

`policy` has no default. `onError` defaults to `CallbackExceptionHandler.Default`.

The registration owns the registry entry, not the process-lifetime trampoline. There is no garbage-collector-driven close. A `REPEATING` registration must be closed by its logical owner.

## Token and trampoline design

For callbacks with compatible `userdata`:

1. A process-wide atomic counter allocates a non-zero token.
2. Tokens are never reused during the process lifetime.
3. The token must fit the target `uintptr_t`; registration fails on exhaustion instead of wrapping.
4. The token is represented as an opaque `NativeAddress` and must never be dereferenced.
5. The registry entry stores the callback-type descriptor, delivery policy, state, lambda, and error handler.
6. The trampoline supplies its expected callback-type descriptor during lookup, preventing cross-type dispatch.

JVM generates one lazily initialized FFM upcall stub per callback type in `Arena.global()`. Kotlin/Native generates one `staticCFunction` per callback type. Neither resource is tied to a registration or memory scope.

The integer-to-pointer representation is deliberately internal. It is supported only on targets where an opaque `void*` round trip through `uintptr_t` is valid. Unsupported pointer models must fail at build or registration time rather than falling back to dereferenceable short-lived context memory.

## Lifecycle and concurrency semantics

### Callbacks with `userdata`

A registration starts in `ACTIVE`.

For `ONCE`:

- trampoline entry atomically claims and removes the registry entry before invoking user code;
- exactly one of callback delivery and `close()` wins the claim;
- duplicate or late native calls become no-ops;
- `isClosed` becomes true once claimed or explicitly closed;
- an exception from the lambda does not re-arm delivery.

For `REPEATING`:

- each trampoline entry may start only while the registration is active;
- `close()` prevents new entries and removes the registry mapping;
- an invocation that already started may complete after `close()` returns;
- `close()` never waits for in-flight callbacks, so it is legal inside the callback itself.

`close()` is idempotent for both policies.

### Callbacks without `userdata`

Without native context, an old delayed callback cannot be distinguished from a new registration. The safe default state machine is therefore:

```text
UNUSED -> ACTIVE -> RETIRED
```

- only one registration may ever become active by default;
- `close()` or one-shot completion moves the callback type to `RETIRED`;
- a retired type rejects all later safe registrations for the process lifetime;
- late calls hit the permanent trampoline and are ignored;
- raw APIs remain available for expert-managed lifetimes.

Consumer configuration may opt a no-userdata callback into re-arming only when the native API explicitly guarantees that no earlier callback can occur after unregistration or replacement. That guarantee must be documented next to the configuration.

## Exception handling

No Kotlin exception may cross the FFI boundary.

Every generated trampoline catches `Throwable` around user code:

- the registration remains consumed for `ONCE`;
- the exception is passed to the registration's `CallbackExceptionHandler`;
- if the error handler also throws, a platform fallback reports both failures without returning an exception to native code.

The default handler uses the platform uncaught-exception mechanism when one is safely available, otherwise `stderr`. It must not silently discard the error.

## Generator analysis and configuration

### Signature analysis

A parameter is a routing candidate only when:

- its normalized name matches `userdata`, `userdata1`, `userdata2`, and so on;
- its C type is a compatible opaque pointer;
- the callback returns `void`.

The highest-index compatible candidate is reserved. Earlier userdata parameters remain in the public lambda. A conflicting name/type combination or ambiguous model produces a generation error with the declaration name and reason.

Callbacks returning a value remain explicitly unsupported.

### Direct callback functions

For a function that accepts a callback and its routing userdata directly, the raw binding remains available. A safe overload:

1. creates the typed registration;
2. passes `registration.callback` and `registration.userdata` to the raw function;
3. returns the registration to the caller.

The application cannot accidentally substitute unrelated userdata on this safe path.

### Callback-info structures

Safe callback-info generation is opt-in because a generic C generator cannot assume that every native API copies a structure or assigns the same meaning to a field named `mode`.

The `wgpu4k-native` generator configuration declares:

- the callback-info structures eligible for safe allocation;
- the callback field;
- the reserved routing userdata field;
- the application userdata fields;
- an optional mode field;
- accepted mode constants when that field exists;
- that the owning native API consumes or copies the structure contents during the call and does not retain the allocation address.

For mode-bearing WebGPU callback-info structures, the accepted modes are:

- `WGPUCallbackMode_WaitAnyOnly`
- `WGPUCallbackMode_AllowProcessEvents`
- `WGPUCallbackMode_AllowSpontaneous`

Zero, `WGPUCallbackMode_Force32`, and unknown values are rejected by the safe factory. A structure such as `WGPUUncapturedErrorCallbackInfo`, which has no mode field, still receives a safe registration-wiring factory but does not invent or require a mode argument.

Example:

```kotlin
val info = WGPUQueueWorkDoneCallbackInfo.allocate(
    allocator = scope,
    mode = WGPUCallbackMode_AllowProcessEvents,
    registration = registration,
    userdata1 = applicationData,
)
```

The factory fills `callback` and `userdata2`. It does not own or close the registration.

For the configured WebGPU APIs, callback-info values are either passed by value or copied from their owning descriptor during the call. The allocation scope therefore only needs to survive that native call. The trampoline and token remain valid independently.

## Event processing contract

The safe binding requires the caller to select a mode but does not select or run an event strategy:

- `WaitAnyOnly` requires the returned `WGPUFuture` to be passed to `wgpuInstanceWaitAny`.
- `AllowProcessEvents` additionally permits delivery during `wgpuInstanceProcessEvents`.
- `AllowSpontaneous` additionally permits delivery at an arbitrary time and on an arbitrary native thread.
- `wgpuDevicePoll` remains the `wgpu-native` extension for device progress and optional blocking wait.

The generated callback registry is thread-safe for all modes. It does not dispatch to a particular thread.

Examples must not call blocking `wgpuInstanceWaitAny` or `wgpuDevicePoll(wait = true)` on a render thread. The later `wgpu4k` coroutine layer will pair each `WGPUFuture` with its `CallbackRegistration`, close the registration on cancellation, and perform blocking progress work on an appropriate dispatcher.

## Android/JNA behavior

The common declarations continue to compile for Android, but registration creation fails immediately with a clear unsupported-operation message. Callback-info raw allocation may remain available, but the safe callback path must not imply that Android upcalls work.

Android support requires a separate design covering JNA callback lifetime, threading, and registry integration.

## Migration

This is an intentionally incompatible change:

- remove `CallbackHolder`;
- remove generated `allocate(callback)` APIs;
- remove per-registration JVM arenas and upcall stubs;
- remove the Kotlin/Native global lambda variable and callback map keyed only by trampoline address;
- regenerate common, JVM, Kotlin/Native, and Android bindings;
- update demonstrations to retain and close registrations explicitly;
- use safe direct-function overloads and callback-info factories in demonstrations;
- do not hand-edit generated binding files.

The raw C-level path remains available for unsupported or expert-managed cases.

## Test strategy

### `kffi` runtime tests

- Tokens are non-zero, unique, and not reused.
- Token allocation fails instead of wrapping.
- `close()` is idempotent.
- Concurrent `ONCE` delivery invokes at most one lambda.
- A race between close and delivery has one observable winner.
- `REPEATING` permits multiple calls before close and none starting afterward.
- An already-started repeating invocation may finish after close.
- Closing from inside the callback does not deadlock.
- Exceptions reach `onError` and never escape the trampoline.
- Failure in `onError` reaches the fallback reporter.
- A no-userdata second registration is rejected while active.
- A no-userdata type is retired after close or one-shot completion.
- Re-arming is tested only for explicitly quiescent configured callbacks.

### `kextract` generation tests

- JVM output contains one global-lifetime trampoline per callback type.
- Kotlin/Native output contains one `staticCFunction` and no single-lambda global variable.
- The last userdata argument is absent from the public lambda and used for lookup.
- Earlier userdata arguments remain public.
- Direct callback overloads wire callback and userdata automatically.
- Callback-info factories require a registration and require a mode only for structures that define one.
- Invalid WebGPU modes are rejected for mode-bearing structures.
- No-userdata callbacks generate retirement behavior.
- Ambiguous signatures fail generation with useful diagnostics.
- Android output clearly reports unsupported registration.
- Existing KMP, variadic, Windows, and Objective-C generator coverage remains green after the refresh.

New generator tests must run in CI. Historical unstable tests may be isolated individually, but the complete `:kextract:test` task must not be blanket-excluded merely to bypass the new coverage.

### Deferred C fixture

A small native fixture stores a callback and userdata, then can:

- invoke later;
- invoke from another thread;
- invoke twice;
- invoke many registrations in shuffled order;
- invoke after registration close.

The fixture runs on JVM and at least one Kotlin/Native target. It proves the real FFI path rather than only generated text.

### Headless `wgpu-native` integration

- Close the callback-info `memoryScope` before deferred delivery.
- Submit and register many queue-work-done callbacks concurrently.
- Assert at-most-once delivery for every `ONCE` registration.
- Close registrations before delivery and assert suppression.
- Exercise `WaitAnyOnly`, `AllowProcessEvents`, and `AllowSpontaneous`.
- Destroy a device while a `DeviceLost` registration is active.
- Retain a `REPEATING` uncaptured-error registration through multiple calls and close it at teardown.
- Use explicit timeouts so a missed callback fails with diagnostics instead of hanging CI.

CI regenerates bindings and fails if the generation leaves an uncommitted diff.

## Acceptance criteria

The `wgpu4k-native` portion is complete when:

1. The refreshed `kextract` branch preserves current master behavior and KMP generation.
2. JVM and Kotlin/Native use permanent per-type trampolines.
3. Concurrent same-type callbacks route to the correct lambdas when userdata exists.
4. Closing a registration cannot invalidate a trampoline or dereference freed callback context.
5. Late callbacks after close are safe no-ops.
6. `ONCE` delivers at most once under duplicate and concurrent native calls.
7. `REPEATING` remains active until explicitly closed.
8. No-userdata callbacks cannot be unsafely reused without a configured quiescence guarantee.
9. Safe WebGPU callback-info creation requires a valid explicit mode whenever the structure defines a mode field.
10. Delayed fixture and headless WebGPU tests pass on JVM and Kotlin/Native with bounded timeouts.
11. Generated sources are reproducible and require no manual patching.
12. Android/JNA limitations are explicit at compile-time documentation and runtime failure points.

The original coroutine-level issue is fully resolved only after the later `wgpu4k` change adds structured cancellation, exactly-once continuation resumption, and off-render-thread event pumping.

## Alternatives considered

### Per-registration native allocation

Use a JVM shared arena/upcall and Kotlin/Native `StableRef` for each registration. This is conventional but cannot safely reclaim resources on close while native code may still issue a late callback. It either requires a native quiescence guarantee or retains tombstones indefinitely.

Rejected because it does not fully solve the reported lifetime hazard.

### WebGPU-specific wrappers only

Wrap each `WGPU*CallbackInfo`, mode, future, and callback in a WebGPU-specific owner.

Rejected as the foundation because it leaves generic generated callbacks unsafe and does not satisfy the selected `kffi`/`kextract` scope. A future high-level `wgpu4k` wrapper may still compose the generic registrations.

### Bounded trampoline pool

Generate several static trampolines per callback type and allocate a free slot for each registration.

Rejected because it introduces an arbitrary concurrency limit and still risks misrouting delayed callbacks when slots are reused.

## Risks and mitigations

- **`kextract` refresh conflicts:** keep the forward-port separate and verify equivalent generated output before callback changes.
- **Opaque token portability:** support only pointer models with a verified `uintptr_t` round trip and fail fast elsewhere.
- **Token exhaustion:** never wrap or reuse; fail registration with a diagnostic.
- **Registry leaks:** `ONCE` removes itself; `REPEATING` ownership and close requirements are documented and tested.
- **No-userdata late calls:** retire the type by default; permit re-arming only with configured native quiescence.
- **Exceptions at the FFI boundary:** catch all `Throwable`, invoke an error handler, then use a non-throwing platform fallback.
- **Event-thread surprises:** document that spontaneous callbacks may occur on arbitrary threads; keep dispatch in `wgpu4k`.
- **Generated API drift:** run generator tests and a clean-regeneration CI check.
