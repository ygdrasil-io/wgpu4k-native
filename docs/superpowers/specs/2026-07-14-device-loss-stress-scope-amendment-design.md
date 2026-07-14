# Device-loss Stress Scope Amendment

**Date:** 2026-07-14  
**Status:** Approved design amendment  
**Amends:** `2026-07-13-safe-generated-callback-registrations-design.md`

## Decision

The bounded WebGPU stress scenario will not exercise end-to-end device-loss delivery.
It will retain all queue-work-done callback-mode coverage and repeating uncaptured-error
coverage. The JVM and host Kotlin/Native stress tasks remain mandatory, strict, and green in
CI.

This amendment removes only the end-to-end device-loss stress requirement. It does not remove
the generated `WGPUDeviceLostCallback` API, its safe registration/factory support, trampoline
generation, or lower-level runtime tests.

## Reason

The bundled `wgpu-native` v29 implementation cannot deliver the requested scenario:

- `wgpuAdapterRequestDevice` stores the C device-lost callback but does not install the
  corresponding `wgpu-core` device-lost closure;
- `wgpuDeviceDestroy` therefore marks the device lost without leaving a callback for polling to
  invoke;
- `wgpuDeviceGetLostFuture` is an `unimplemented!()` stub and aborts the process.

JVM and host Native probes confirmed that `wgpuDeviceDestroy` followed by process events,
non-blocking polling, or blocking polling leaves device-loss delivery pending. The official
lost-future API aborts before returning a future. Changing `wgpu-native`, using a fork, or
refreshing its ABI is outside the user-approved scope.

## Retained Stress Scenario

The common headless scenario must:

1. create a dedicated instance, adapter, device, and queue;
2. install a `REPEATING` uncaptured-error registration in the device descriptor;
3. make 64 bounded real queue submissions per callback mode;
4. keep at least 1,000 `ONCE` queue-work-done registrations active concurrently per mode;
5. exercise `WaitAnyOnly`, `AllowProcessEvents`, and `AllowSpontaneous` with their matching
   event strategies;
6. close a deterministic subset before delivery for modes whose scheduling makes that proof
   deterministic, then verify suppression;
7. verify at-most-once delivery and zero pending registrations;
8. close each callback-info allocation scope before deferred delivery;
9. trigger and observe exactly two uncaptured validation errors;
10. close every remaining registration and release every WebGPU handle in `finally` paths.

Blocking wait or poll calls remain restricted to the headless stress process and must never be
introduced into the graphical render loop.

## Gradle and CI Contract

`verifyJvmCallbackStress` and `verifyNativeCallbackStress` remain strict verification tasks.
Each uses a two-minute outer process deadline, preserves stdout and stderr on failure, terminates
a timed-out child process, and fails on non-zero exit.

CI continues to require:

- the complete project build and `:kextract:test`;
- targeted JVM callback tests;
- host-appropriate Native compilation and tests;
- both callback stress tasks on supported hosts;
- clean binding regeneration with no diff under `wgpu4k-native/src`.

No device-loss probe, allowed-failure job, ignored assertion, or opt-in task is retained.

## Diagnostics and Documentation

Successful stress output must report each callback mode, submission and delivery counts,
suppressed registrations, both uncaptured validation errors, and zero pending registrations.

The Task 11 report must preserve the observed upstream evidence and clearly state that
end-to-end device-loss delivery remains unverified with bundled `wgpu-native` v29. It must not
describe this limitation as a `wgpu4k-native` fix or claim full device-loss conformance.

## Acceptance Criteria Amendment

The original Task 11 and final-acceptance clauses requiring one delivered device-loss callback
are superseded by this document. All other Task 11 and final-acceptance requirements remain in
force.

Future device-loss integration coverage requires a separate, explicitly approved project that
either patches an ABI-compatible `wgpu-native` v29 fork or refreshes the bindings and native
artifacts together.
