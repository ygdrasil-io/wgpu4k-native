# External Review Remediation Design

**Date:** 2026-07-14  
**Status:** Approved design  
**Extends:** `2026-07-13-safe-generated-callback-registrations-design.md`  
**Preserves:** `2026-07-14-device-loss-stress-scope-amendment-design.md`

## Goal

Resolve every confirmed Important finding from the final external reviews of the safe callback
work, without modifying or forking `wgpu-native`. The remediation covers the KFFI delayed-callback
fixture, demo and stress callback consumption, Kextract callback generation, binding regeneration,
and CI freshness checks.

The bundled `wgpu-native` v29 device-loss limitation remains explicitly out of scope. No opt-in
probe, allowed failure, ignored assertion, or replacement native artifact is introduced.

## Decisions

### 1. Bound native fixture teardown independently of the test framework

Kotest timeouts cannot interrupt a thread blocked inside a native downcall. The delayed-callback C
fixture will therefore bound its own teardown. `fixture_unregister_and_join` will arm a native
watchdog before entering synchronization or `pthread_join`; failure to complete within the fixture
deadline terminates the test process instead of leaving Gradle suspended indefinitely. Successful
teardown disarms and joins the watchdog.

Every relevant pthread operation will have its return value checked. The JVM token codec will also
reject non-64-bit FFM address layouts explicitly, matching the callback-token ABI contract. Tests
will cover the supported pointer width and the fixture's bounded teardown mechanism without making
production runtime code depend on test-only synchronization.

This is preferred to relying only on an outer Gradle timeout: the fixture owns the native resources
and is the only layer that can bound a deadlock after the downcall has started.

### 2. Make demo adapter and device requests deterministic

`getAdapter` and `getDevice` will no longer use `AllowSpontaneous` with unsynchronized local
variables. Both helpers will:

- register an `ONCE` callback whose completed payload is atomically published only after the
  owned handle and copied message are available;
- allocate callback info in a short-lived scope to preserve the lifetime stress contract;
- use `WaitAnyOnly` and pump the returned future through `wgpuInstanceWaitAny` with non-blocking
  waits and a monotonic deadline;
- close the registration in `finally` and release any late or unsuccessful owned handle exactly
  once.

Request state retains handle ownership until close and runtime quiescence have both completed. If
waiting or cleanup fails, the state atomically enters a disposed mode: an already-published handle
is released once, and any callback that publishes after disposal releases its incoming handle
instead of making a result observable. Result resolution and ownership transfer happen only after
successful cleanup. Before publication, the callback temporarily owns the incoming handle while it
copies the native message. If message conversion fails, it releases that handle exactly once before
propagating the original exception object to the runtime. If release itself fails, that secondary
failure is attached as a suppressed exception rather than replacing the conversion failure. The
helper is non-inline so a callback cannot escape through a non-local return before publication or
rethrow.

`wgpu-native` v29 currently does not implement futures for adapter/device requests: both functions
ignore the requested callback mode, invoke the callback synchronously, and return `NULL_FUTURE`
with ID zero. The strict coordination helper will continue to reject a zero future by default. The
two initialization helpers will use a named compatibility opt-in that accepts zero only when the
callback has already published a complete result and the `ONCE` registration is quiescent. This
path performs no pumping. A zero future with an incomplete or non-quiescent callback remains an
immediate error. The explicit `WaitAnyOnly` mode is retained so conforming implementations use the
normal bounded wait path and the upstream limitation stays visible rather than becoming a silent
global relaxation.

`getDevice` will accept the instance used for waiting, and every desktop, JVM, iOS, Android,
Android Native, capture, and headless caller will be migrated.

The graphical render loop will remain free of blocking waits. The bounded wait occurs only during
adapter or device initialization.

### 3. Make stress completion and diagnostics truthful

Stress event pumping will use non-blocking `wgpuDevicePoll(..., 0u, ...)` calls so the Kotlin
deadline remains authoritative. The repeating uncaptured-error registration will be closed before
the scenario asserts exact callback counts or prints `pending=0`.

`wgpu-native` v29 also leaves queue futures unimplemented: `wgpuQueueOnSubmittedWorkDone` ignores
the requested mode, registers the completion closure, and returns `NULL_FUTURE`. Queue stress will
therefore keep strict zero-future rejection by default and use a named v29-only opt-in for an
all-zero batch. Under that opt-in, progress uses repeated non-blocking device polls and never calls
`waitAny` with zero. A mixed zero/nonzero batch is always an error; an all-nonzero batch uses the
normal mode-specific pumping. Stress diagnostics explicitly report the poll-only compatibility
path and `upstreamModeValidation=unavailable`: the delivery/suppression totals validate KFFI
registration ownership under each configured mode, but do not claim that v29 honored the upstream
mode scheduling contract.

`CallbackRegistration` will expose a read-only `isQuiescent` state backed by runtime acquisition
tracking for both `ONCE` and `REPEATING` deliveries. It becomes true only when the registration is
closed or claimed and every delivery that successfully entered the runtime has returned. Teardown
will close the error registration, then wait with the existing bounded event strategy for runtime
quiescence. An application-level in-flight counter remains as a stress diagnostic, but is not the
race-sensitive ownership authority. Only after both values are quiescent may the scenario assert
exactly two validation errors and print its final completion diagnostic.

Diagnostics will retain the first unexpected queue, map, or error status and message so a failure
reports its cause rather than only aggregate counts.

All demo buffer-map waits, including the standalone JVM capture path, will use a monotonic deadline
whose pump is non-blocking. In particular, no demo may call `wgpuDevicePoll(..., 1u, ...)`: JVM
capture progresses with repeated `wgpuDevicePoll(..., 0u, ...)`, while the existing headless path
uses its non-blocking process-events pump. A shared map-coordination boundary waits for the first
atomic diagnostic, closes the `ONCE` registration in every exit path, and then waits for runtime
quiescence before the mapped buffer or its owning device can be released. A deterministic timeout
test will cover a map callback that never completes, and a bounded JVM capture process will exercise
the real `--verify-capture` path because the headless render does not execute `Capture.jvm.kt`.

### 4. Preserve legacy Kextract behavior outside multiplatform generation

Callback binding validation and callback analysis are part of the multiplatform callback feature,
not a new constraint on legacy generation. When `multiplatform` is false, Kextract will use an empty
validated callback model and will not reject unrelated non-void function-pointer typedefs.

Passing `--callback-bindings` without `--multiplatform` will fail early with a focused diagnostic.
Compatibility tests will include a legacy non-KMP header containing a non-void callback typedef.

### 5. Correct JVM layouts and pointer indirection

JVM aggregate generation will emit `MemoryLayout.unionLayout` for C unions and
`MemoryLayout.structLayout` for C structs. Tests will verify both emitted source and union storage
round trips.

KMP type mapping will preserve every pointer layer. In particular, a pointer to a typedef that is
itself a native handle pointer, such as `WGPUAdapter*`, must remain an address/output-buffer carrier
and must not collapse into `WGPUAdapter?`. Generated common and platform implementations will use a
`NativeAddress?`-compatible carrier for the extra pointer layer, with focused generator tests and a
regenerated `wgpuInstanceEnumerateAdapters` signature proving the correction.

### 6. Complete all throwing conversions before callback publication

Generated direct callback helpers will convert every parameter that can throw into its final native
carrier before calling `prepare`, allocating callback info, or making the native downcall. A local
copy alone is not validation. Tests will use a deliberately throwing conversion and prove that the
registration is never published and the native call is never reached.

This retains the transaction boundary defined by the original callback design: failure before
publication is ordinary Kotlin failure; after publication, cleanup is governed by the registration
state machine.

### 7. Make callback ABI constraints target-independent

A single multiplatform Kotlin callback signature cannot safely model C `long` or `long double`
across LP64 and LLP64 targets. Until Kextract has a target-specific ABI model, callback bindings
that expose either type will be rejected with a precise diagnostic explaining the width variance.

The generator will no longer consult the host operating system to choose their callback carrier.
Fixed-width integer and floating-point types remain supported. This bounded rejection is preferred
to a larger target-ABI redesign in the current branch.

### 8. Generate valid, collision-free Kotlin names

All callback type names, parameter names, and generated references will pass through the existing
Kotlin name-mangling policy. Each emitted function will allocate unique identifiers while reserving
synthetic names such as `callback`, `policy`, `onError`, registration locals, and prepared carriers.

Tests will cover Kotlin keywords, invalid C identifier characters where representable by the model,
duplicate normalized names, and collisions with every synthetic parameter introduced by direct and
factory helpers.

### 9. Make regeneration portable and complete

The WebGPU generation task will:

- depend on header acquisition as well as the Kextract distribution;
- declare the generator distribution or executable, callback YAML, headers, and generation inputs;
- select the Unix launcher or Windows `.bat` launcher from the current host;
- keep outputs declared so Gradle invalidates stale generated bindings correctly.

CI freshness verification will fail for both tracked diffs and untracked files below generated
output roots. Cross-host task-graph tests will inspect Linux, macOS, and Windows command selection;
host-executable tests remain mandatory on the available JVM and macOS Native environments.

## Implementation Boundaries

- Do not modify, fork, upgrade, or replace `wgpu-native`.
- Do not restore end-to-end device-loss stress.
- Keep the public callback registration state machine and permanent trampoline ownership model.
- Keep blocking or repeated event pumping outside graphical render loops.
- Keep every demo device poll non-blocking; callback waits must be bounded by a monotonic deadline
  and registration quiescence must precede release of callback-adjacent resources.
- Preserve all unrelated changes and the current submodule revisions.
- Use test-first changes for each defect and regenerate bindings only after generator tests pass.
- Each implementation block receives an independent specification review and code-quality review.

## Verification

The remediation is complete only when all of the following pass from a clean generated state:

1. KFFI common, JVM, and host Native callback tests, including the delayed C fixture.
2. Kextract unit and integration tests for legacy mode, unions, pointer depth, preflight conversion,
   ABI rejection, name allocation, and generation task configuration.
3. JVM and host Native demo compilation plus both callback stress tasks.
4. Both headless render checks.
5. Cross-host ABI and generation task dry-runs or task-graph assertions.
6. Binding regeneration followed by a clean tracked-and-untracked generated-output check.
7. A fresh whole-branch external review of runtime/FFI, demos/stress/CI, and Kextract/generation.

Device-loss delivery is not part of these acceptance criteria and remains documented as an upstream
v29 limitation.
