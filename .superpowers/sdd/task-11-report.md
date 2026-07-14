# Task 11 report — bounded WebGPU callback stress and CI gates

## Result

Status: DONE under the approved device-loss scope amendment.

The strict JVM and host Kotlin/Native stress tasks pass all retained requirements: three
queue-work-done callback modes, 64 real submissions and 1,000 concurrent `ONCE` registrations per
mode, deterministic suppression where scheduling permits it, at-most-once delivery, two repeating
uncaptured validation errors, closed callback-info scopes, and zero pending registrations.

End-to-end device-loss delivery is not covered. Bundled `wgpu-native` v29 stores but never wires the
device-lost callback into `wgpu-core`, while `wgpuDeviceGetLostFuture` aborts as unimplemented. The
approved scope amendment removes that scenario rather than skipping an assertion, tolerating an
error, or modifying the external dependency.

## Successful scenario

- JVM: `WaitAnyOnly` reports 900 delivered/100 suppressed, `AllowProcessEvents` 900/100, and
  `AllowSpontaneous` 1,000/0; every mode has 1,000 registrations, 64 submissions, zero duplicates,
  closed callback-info memory scopes, and zero pending registrations.
- Host macOS arm64 Native: the same counts and invariants.
- Both runtimes observe exactly two uncaptured errors, both validation errors, followed by
  `callback-stress complete pending=0`.
- Every mode creates and verifies all 1,000 open callback registrations before submitting work or
  making the first `wgpuQueueOnSubmittedWorkDone` downcall. The partial-construction `finally` path
  still closes every registration created before a failure.
- The `WaitAnyOnly` and `AllowProcessEvents` phases deterministically close every tenth
  registration before delivery. `AllowSpontaneous` does not pre-close registrations because its
  callback may run inside the `wgpuQueueOnSubmittedWorkDone` downcall.
- Per-ID and aggregate callback counters use common atomic types so spontaneous callbacks may
  arrive on arbitrary native threads without data races.
- Queue and uncaptured-error callbacks publish dedicated atomic completion counters in `finally` as
  their last operation. The driving thread waits for those counters and verifies their exact values
  before reading callback results, closing registrations, or releasing WebGPU handles; no callback
  accesses a message after publishing completion.
- Adapter acquisition is local to the stress harness: an `ONCE` request callback uses
  `WaitAnyOnly`, publishes status last, retains only the future ID outside the closed descriptor
  scope, waits with the shared 20-second diagnostics, and releases any adapter returned with a
  non-success status.
- WaitAny phases retain opaque future IDs and reconstruct `WGPUFuture` structs inside each wait
  scope; they never reuse a Kotlin/Native by-value wrapper through its unavailable handler.
- Every remaining registration and WebGPU handle is closed or released from `finally` paths.
- Each phase uses a `TimeSource.Monotonic` 20-second internal deadline with mode, phase, and pending
  IDs in failure diagnostics.

## Entry points and bounded Gradle processes

- JVM and desktop Native entry points accept `--callback-stress` without changing graphical or
  existing headless-render paths.
- `verifyJvmCallbackStress` and `verifyNativeCallbackStress` depend on
  `:wgpu4k-native:fetch-native-dependencies` and their required classes or executable.
- Each task launches an isolated process with a two-minute outer deadline, consumes stdout and
  stderr concurrently, preserves both streams on failure, terminates a timed-out process
  gracefully and then forcibly if necessary, and fails on non-zero exit.
- A temporary one-second timeout exercised the kill path before the approved scope amendment. The
  task emitted `JVM callback stress timed out after 2 minutes; stdout/stderr above are preserved`;
  process inspection afterward found no remaining `HeadlessMainKt --callback-stress` child. The
  production two-minute threshold was restored before the final implementation.

## CI gates

- The matrix keeps macOS, Ubuntu, and Windows and runs `build`, complete `:kextract:test`, targeted
  JVM callback tests, both strict callback stress tasks, and both existing headless renders.
- Native compile gates cover iOS x64/arm64/simulator arm64, macOS arm64/x64, Linux x64/arm64,
  Android Native arm64/x64, and MinGW x64 for both `kffi` and `wgpu4k-native`.
- CI is configured to run real host Native tests on macOS, Linux, and Windows; the macOS test task
  follows the runner architecture.
- macOS regenerates WebGPU bindings and requires a clean `wgpu4k-native/src` diff.
- A combined `--dry-run` resolves every newly named Native compile task.
- Local execution evidence is limited to JVM and macOS arm64 Native. Linux and Windows task graphs
  and workflow shell branches were inspected locally, but their real execution remains an expected
  CI result rather than a claim in this report.

## Upstream evidence retained for future work

Before the amendment, the scenario configured an `AllowSpontaneous` device-lost callback in the
device descriptor, observed exactly two validation errors, destroyed the device, and attempted
bounded event delivery. On JVM and host macOS arm64 Native, destroy followed by process events,
non-blocking device polling, blocking device polling, and combined poll/process-event strategies
left device loss pending until the 20-second diagnostic deadline:

```text
callback-stress uncaptured-errors=2 validation-errors=2 pending=0
callback-stress timeout mode=AllowSpontaneous phase=device-loss pending=[0]
```

The official lost-future path was also tested by calling `wgpuDeviceGetLostFuture(device)` before
destruction and preparing to inspect `WGPUFutureWaitInfo.completed` through
`wgpuInstanceWaitAny`. Bundled `libWGPU-v29.0.0.0` aborted before returning a future:

```text
thread '<unnamed>' panicked at src/unimplemented.rs:94:5:
not implemented
...
_wgpuDeviceGetLostFuture
thread caused non-unwinding panic. aborting.
```

The JVM process exited 134 before a future ID, wait status, or completed flag existed. A rejected
`mapAsync` probe after destroy returned status `3` with `Parent device is lost`, delivered zero
device-lost callbacks, and became an unwanted third uncaptured validation error.

The user approved the scope amendment in
`docs/superpowers/specs/2026-07-14-device-loss-stress-scope-amendment-design.md`. All device-lost
registration, descriptor ownership, counters, destruction, polling, assertions, diagnostics, and
probes were removed from the committed stress scenario. No allowed-failure job, hidden skip, or
opt-in probe remains.

## Verification evidence

- Original task RED: both requested stress task names were absent and Gradle failed task
  selection.
- Pre-amendment device-loss RED: JVM and Native passed every retained queue/error invariant, then
  failed after 20 seconds with `device-loss pending=[0]`.
- Review RED for simultaneous registration: adding the final pre-downcall invariant to the original
  interleaved loop failed immediately with
  `active registrations=1 expected=1000 before first queue downcall`. Separating registration
  creation from work submission and future creation made the same JVM stress command pass.
- Review RED for bounded adapter acquisition: switching the harness call site to the intended local
  `requestStressAdapter(instance)` API before defining it made `:demo:common:compileKotlinJvm` fail
  specifically with `Unresolved reference 'requestStressAdapter'`. The helper then compiled and
  completed real JVM and macOS arm64 Native adapter requests.
- Review RED for queue callback completion: waiting on an unpublished completion counter after the
  observable callback counts had advanced forced another WaitAny path and failed with
  `waitAny encountered future-id=0`. Publishing the counter in each callback's final `finally`
  operation restored all three queue modes and their exact counts.
- Review RED for error callback completion: both validation messages were printed, then the
  unpublished completion counter hit the 20-second deadline with
  `phase=uncaptured-errors pending=[0, 1]`. Final completion publication made the same phase pass
  with completed/total/validation counts all exactly two.
- Common/JVM/host Native compilation:
  `:demo:common:compileKotlinJvm`, `:demo:desktop-and-ios:compileKotlinJvm`,
  `:demo:common:compileKotlinMacosArm64`, and
  `:demo:desktop-and-ios:compileKotlinMacosArm64` — PASS after the amendment.
- `:demo:desktop-and-ios:verifyJvmCallbackStress` — PASS with counts 900/100, 900/100, and
  1,000/0; validation errors 2/2; zero duplicates and pending registrations.
- `:demo:desktop-and-ios:verifyNativeCallbackStress` — PASS with the same exact counts and
  invariants.
- `:demo:desktop-and-ios:verifyJvmHeadlessRender` and
  `:demo:desktop-and-ios:verifyNativeHeadlessRender` — PASS.
- `:kextract:test :kffi:jvmTest :kffi:macosArm64Test :wgpu4k-native:jvmTest
  :wgpu4k-native:macosArm64Test` — PASS.
- The complete ABI compile task graph for every configured target resolves with `--dry-run`.
- `:wgpu4k-native:generateBindingsFromHeader` followed by
  `git diff --exit-code -- wgpu4k-native/src` — PASS and clean.
- `git diff --check` — PASS.

## Scope statement

This task does not claim device-loss conformance and does not fix `wgpu-native`. End-to-end
device-loss delivery remains unverified with bundled `wgpu-native` v29. Such coverage requires a
separately approved ABI-compatible fork patch or a coordinated native-artifact and binding refresh.
