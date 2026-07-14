# Complete Callback Stress Without Device Loss Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Finish the bounded JVM and Kotlin/Native WebGPU callback stress and CI gates while removing the end-to-end device-loss phase that bundled `wgpu-native` v29 cannot deliver.

**Architecture:** Keep one common headless stress entry point that owns a dedicated instance, adapter, device, queue, and registrations. Exercise queue-work-done callbacks through all three callback modes and retain repeating uncaptured-error delivery; remove only device-lost registration, destruction, polling, and assertions. Keep the prepared Gradle process isolation and cross-host CI gates unchanged except for diagnostics that mention the amended scope.

**Tech Stack:** Kotlin Multiplatform, Kotlin atomic APIs, generated WebGPU bindings, Gradle Kotlin DSL, GitHub Actions, JVM FFM, Kotlin/Native cinterop.

## Global Constraints

- The approved scope amendment is `docs/superpowers/specs/2026-07-14-device-loss-stress-scope-amendment-design.md`.
- Do not modify, fork, upgrade, or replace `wgpu-native`.
- Do not retain a device-loss probe, opt-in task, allowed-failure CI job, ignored assertion, or hidden skip.
- Keep 64 real queue submissions and 1,000 concurrent `ONCE` registrations per callback mode.
- Keep `WaitAnyOnly`, `AllowProcessEvents`, and `AllowSpontaneous` with their matching headless event strategies.
- Keep deterministic close-before-delivery suppression for `WaitAnyOnly` and `AllowProcessEvents` and at-most-once delivery for every mode.
- Close each callback-info allocation scope before deferred delivery.
- Observe exactly two repeating uncaptured validation errors and zero pending registrations.
- Blocking wait or poll calls may run only in the headless stress process, never in the graphical render loop.
- The JVM and Native verification tasks have a two-minute process deadline and must preserve stdout and stderr on failure.
- Preserve all unrelated user changes and the current `kextract` submodule revision.
- Do not push.

## File Map

- `demo/common/src/commonMain/kotlin/CallbackStress.kt`: common stress ownership, queue phases, validation errors, event pumping, deadlines, and diagnostics.
- `demo/desktop-and-ios/src/jvmMain/kotlin/HeadlessMain.kt`: JVM `--callback-stress` entry point.
- `demo/desktop-and-ios/src/desktopMain/kotlin/main.kt`: host Native `--callback-stress` entry point.
- `demo/desktop-and-ios/build.gradle.kts`: bounded JVM and host Native stress processes.
- `.github/workflows/test.yml`: cross-host build, callback stress, Native ABI compile/test, and clean-regeneration gates.
- `.superpowers/sdd/task-11-report.md`: verification evidence and explicit upstream limitation.

---

### Task 1: Finish the amended callback stress and CI gate

**Files:**
- Modify: `demo/common/src/commonMain/kotlin/CallbackStress.kt`
- Verify: `demo/desktop-and-ios/src/jvmMain/kotlin/HeadlessMain.kt`
- Verify: `demo/desktop-and-ios/src/desktopMain/kotlin/main.kt`
- Verify: `demo/desktop-and-ios/build.gradle.kts`
- Verify: `.github/workflows/test.yml`
- Modify: `.superpowers/sdd/task-11-report.md`

**Interfaces:**
- Consumes: `runCallbackStress(): Unit`, generated safe callback registration/factory APIs, `wgpuInstanceWaitAny`, `wgpuInstanceProcessEvents`, and `wgpuDevicePoll`.
- Produces: strict `verifyJvmCallbackStress` and `verifyNativeCallbackStress` Gradle tasks whose successful output covers all three queue callback modes, 64 submissions per mode, 1,000 registrations per mode, two validation errors, and `pending=0`.

- [ ] **Step 1: Reproduce the pre-amendment RED result**

Run:

```bash
rtk ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress
rtk ./gradlew :demo:desktop-and-ios:verifyNativeCallbackStress
```

Expected: each task proves all queue phases and two validation errors, then fails after the bounded deadline with:

```text
callback-stress timeout mode=AllowSpontaneous phase=device-loss pending=[0]
```

- [ ] **Step 2: Remove only device-lost ownership and delivery from the common scenario**

In `runCallbackStress`, retain only the uncaptured-error registration and call an error-only final phase:

```kotlin
fun runCallbackStress() {
    val instance = wgpuCreateInstance(null) ?: error("callback-stress: failed to create instance")
    var adapter: WGPUAdapter? = null
    var device: WGPUDevice? = null
    var queue: WGPUQueue? = null
    var errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>? = null

    val uncapturedErrorCalls = AtomicInt(0)
    val validationErrorCalls = AtomicInt(0)

    try {
        adapter = getAdapter(surface = null, instance = instance)
        errorRegistration = WGPUUncapturedErrorCallback.register(CallbackPolicy.REPEATING) { _, type, message, _ ->
            uncapturedErrorCalls.fetchAndAdd(1)
            if (type == WGPUErrorType_Validation) validationErrorCalls.fetchAndAdd(1)
            println(
                "callback-stress uncaptured-error type=$type " +
                    "message=${message.data?.toKString(message.length).orEmpty()}",
            )
        }

        device = requestStressDevice(adapter, instance, errorRegistration)
        queue = wgpuDeviceGetQueue(device) ?: error("callback-stress: failed to get queue")

        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_WaitAnyOnly)
        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_AllowProcessEvents)
        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_AllowSpontaneous)
        runUncapturedErrorPhase(device, queue, uncapturedErrorCalls, validationErrorCalls)

        check(!errorRegistration.isClosed) {
            "callback-stress: repeating uncaptured-error registration closed before teardown"
        }
        println("callback-stress complete pending=0")
    } finally {
        errorRegistration?.close()
        queue?.let(::wgpuQueueRelease)
        device?.let(::wgpuDeviceRelease)
        adapter?.let(::wgpuAdapterRelease)
        wgpuInstanceRelease(instance)
    }
}
```

Change `requestStressDevice` to accept only the error registration and populate only `uncapturedErrorCallbackInfo`:

```kotlin
private fun requestStressDevice(
    adapter: WGPUAdapter,
    instance: WGPUInstance,
    errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>,
): WGPUDevice {
    val requestStatus = AtomicReference<WGPURequestDeviceStatus?>(null)
    val requestedDevice = AtomicReference<WGPUDevice?>(null)
    val requestMessage = AtomicReference<String?>(null)
    val requestRegistration = WGPURequestDeviceCallback.register(CallbackPolicy.ONCE) { status, device, message, _ ->
        requestedDevice.store(device)
        requestMessage.store(message.data?.toKString(message.length))
        requestStatus.store(status)
    }

    try {
        val futureId = memoryScope { scope ->
            val descriptor = WGPUDeviceDescriptor.allocate(scope).apply {
                uncapturedErrorCallbackInfo = WGPUUncapturedErrorCallbackInfo.allocate(
                    scope,
                    errorRegistration,
                )
            }
            val requestInfo = WGPURequestDeviceCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_WaitAnyOnly,
                requestRegistration,
            )
            wgpuAdapterRequestDevice(adapter, descriptor, requestInfo).id
        }

        awaitSingleFuture(
            instance = instance,
            futureId = futureId,
            modeName = "WaitAnyOnly",
            phase = "request-device",
            isComplete = { requestStatus.load() != null },
            pendingIds = { listOf(0) },
        )

        return resolveDeviceRequestResult(
            status = requestStatus.load(),
            device = requestedDevice.load(),
            message = requestMessage.load(),
            release = ::wgpuDeviceRelease,
        )
    } finally {
        requestRegistration.close()
    }
}
```

Replace `runDeviceCallbackPhase` with an error-only phase. It must not destroy the device or mention device loss:

```kotlin
private fun runUncapturedErrorPhase(
    device: WGPUDevice,
    queue: WGPUQueue,
    uncapturedErrorCalls: AtomicInt,
    validationErrorCalls: AtomicInt,
) {
    val buffer = memoryScope { scope ->
        WGPUBufferDescriptor.allocate(scope).apply {
            usage = WGPUBufferUsage_CopyDst
            size = 4uL
        }.let { wgpuDeviceCreateBuffer(device, it) }
    } ?: error("callback-stress: failed to create validation buffer")

    try {
        memoryScope { scope ->
            val invalidData = scope.allocateBuffer(1uL).apply { writeByte(1) }
            repeat(2) {
                wgpuQueueWriteBuffer(queue, buffer, 0uL, invalidData.handler, 1uL)
            }
        }
        awaitAtomicCount(
            modeName = "AllowSpontaneous",
            phase = "uncaptured-errors",
            counter = uncapturedErrorCalls,
            expected = 2,
            pump = { wgpuDevicePoll(device, 0u, null) },
        )
        check(uncapturedErrorCalls.load() == 2) {
            "callback-stress uncaptured-errors=${uncapturedErrorCalls.load()} expected=2"
        }
        check(validationErrorCalls.load() == 2) {
            "callback-stress validation-errors=${validationErrorCalls.load()} expected=2"
        }
        println("callback-stress uncaptured-errors=2 validation-errors=2 pending=0")
    } finally {
        wgpuBufferRelease(buffer)
    }
}
```

Remove the unused `WGPUDeviceLostCallback` registration, counters, reason, descriptor field,
checks, cleanup, imports, and diagnostic strings. Do not edit `runQueueCallbackPhase`,
`submitBoundedCopyWork`, `pumpWaitAnyBatches`, `awaitSingleFuture`, `awaitAtomicCount`,
`failQueuePhase`, the two entry points, or `runBoundedCallbackStressProcess`.

- [ ] **Step 3: Compile the common stress on JVM and host Native**

Run:

```bash
rtk ./gradlew \
  :demo:common:compileKotlinJvm \
  :demo:desktop-and-ios:compileKotlinJvm \
  :demo:common:compileKotlinMacosArm64 \
  :demo:desktop-and-ios:compileKotlinMacosArm64
```

Expected: `BUILD SUCCESSFUL` with no unresolved device-lost references.

- [ ] **Step 4: Verify the amended stress is GREEN on JVM and Native**

Run:

```bash
rtk ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress
rtk ./gradlew :demo:desktop-and-ios:verifyNativeCallbackStress
```

Expected from each task:

```text
callback-stress mode=WaitAnyOnly registrations=1000 submissions=64 delivered=900 suppressed=100 duplicates=0 memoryScope=closed pending=0
callback-stress mode=AllowProcessEvents registrations=1000 submissions=64 delivered=900 suppressed=100 duplicates=0 memoryScope=closed pending=0
callback-stress mode=AllowSpontaneous registrations=1000 submissions=64 delivered=1000 suppressed=0 duplicates=0 memoryScope=closed pending=0
callback-stress uncaptured-errors=2 validation-errors=2 pending=0
callback-stress complete pending=0
```

- [ ] **Step 5: Update the durable Task 11 report**

Set the report status to `DONE under the approved device-loss scope amendment`. Record:

- the RED device-loss timeout and `wgpuDeviceGetLostFuture` abort as upstream evidence;
- the user decision to remove, rather than skip or tolerate, the device-loss scenario;
- the three GREEN queue-mode counts on JVM and Native;
- exactly two uncaptured validation errors and zero pending registrations;
- process-timeout kill-path evidence;
- complete suite, ABI task-graph, headless-render, and clean-regeneration results;
- the explicit statement that end-to-end device-loss delivery remains unverified with bundled `wgpu-native` v29.

Do not claim device-loss conformance or a `wgpu4k-native` fix for the upstream limitation.

Use this report summary and preserve the already-recorded command-level evidence below it:

```markdown
# Task 11 report — bounded WebGPU callback stress and CI gates

## Result

Status: DONE under the approved device-loss scope amendment.

The strict JVM and host Kotlin/Native stress tasks pass all retained requirements: three
queue-work-done callback modes, 64 real submissions and 1,000 concurrent ONCE registrations per
mode, deterministic suppression where scheduling permits it, at-most-once delivery, two repeating
uncaptured validation errors, closed callback-info scopes, and zero pending registrations.

End-to-end device-loss delivery is not covered. Bundled wgpu-native v29 stores but never wires the
device-lost callback into wgpu-core, while wgpuDeviceGetLostFuture aborts as unimplemented. The
approved scope amendment removes that scenario rather than skipping an assertion, tolerating an
error, or modifying the external dependency.

## Successful scenario

- JVM: WaitAnyOnly 900 delivered/100 suppressed, AllowProcessEvents 900/100, and
  AllowSpontaneous 1,000/0; duplicates=0 and pending=0.
- Host Native: the same counts and invariants.
- Both runtimes observe exactly two uncaptured errors, both validation errors.
- Every registration and WebGPU handle is closed or released from finally paths.

## Upstream evidence retained for future work

Before the amendment, destroy followed by ProcessEvents, DevicePoll(wait=0), and
DevicePoll(wait=1) left device-loss pending until the 20-second diagnostic deadline on JVM and
Native. Calling wgpuDeviceGetLostFuture aborted in src/unimplemented.rs with exit 134. No probe or
device-loss path remains in the committed stress scenario.

## Scope statement

This task does not claim device-loss conformance and does not fix wgpu-native. Such coverage needs
a separately approved ABI-compatible fork patch or coordinated native-artifact and binding refresh.
```

- [ ] **Step 6: Run the complete verification suite**

Run:

```bash
rtk ./gradlew :kextract:test :kffi:jvmTest :kffi:macosArm64Test :wgpu4k-native:jvmTest :wgpu4k-native:macosArm64Test
rtk ./gradlew :demo:desktop-and-ios:verifyJvmHeadlessRender :demo:desktop-and-ios:verifyNativeHeadlessRender
rtk ./gradlew :demo:desktop-and-ios:verifyJvmCallbackStress :demo:desktop-and-ios:verifyNativeCallbackStress
```

Expected: every command reports `BUILD SUCCESSFUL`; no asynchronous test hangs.

- [ ] **Step 7: Verify CI task names, regeneration, and repository hygiene**

Run:

```bash
rtk ./gradlew \
  :kffi:compileKotlinIosX64 :kffi:compileKotlinIosArm64 :kffi:compileKotlinIosSimulatorArm64 \
  :kffi:compileKotlinMacosArm64 :kffi:compileKotlinMacosX64 \
  :kffi:compileKotlinLinuxX64 :kffi:compileKotlinLinuxArm64 \
  :kffi:compileKotlinAndroidNativeArm64 :kffi:compileKotlinAndroidNativeX64 \
  :kffi:compileKotlinMingwX64 \
  :wgpu4k-native:compileKotlinIosX64 :wgpu4k-native:compileKotlinIosArm64 \
  :wgpu4k-native:compileKotlinIosSimulatorArm64 \
  :wgpu4k-native:compileKotlinMacosArm64 :wgpu4k-native:compileKotlinMacosX64 \
  :wgpu4k-native:compileKotlinLinuxX64 :wgpu4k-native:compileKotlinLinuxArm64 \
  :wgpu4k-native:compileKotlinAndroidNativeArm64 :wgpu4k-native:compileKotlinAndroidNativeX64 \
  :wgpu4k-native:compileKotlinMingwX64 --dry-run
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader
rtk git diff --exit-code -- wgpu4k-native/src
rtk git diff --check
```

Expected: all task names resolve, generated sources remain unchanged, and whitespace checks pass. Confirm no build artifact or native binary is staged.

- [ ] **Step 8: Commit the completed Task 11**

Run:

```bash
rtk git add \
  demo/common/src/commonMain/kotlin/CallbackStress.kt \
  demo/desktop-and-ios/src/jvmMain/kotlin/HeadlessMain.kt \
  demo/desktop-and-ios/src/desktopMain/kotlin/main.kt \
  demo/desktop-and-ios/build.gradle.kts \
  .github/workflows/test.yml
rtk git add -f .superpowers/sdd/task-11-report.md
rtk git commit -m "test: stress safe WebGPU callback delivery"
```

Expected: one Task 11 commit immediately after this implementation-plan commit; the plan commit's
parent is `86df9409`. The worktree is clean and nothing is pushed.

---

## Review Gate

After the commit, request an independent review over `HEAD^..HEAD`. The reviewer must verify
the approved scope amendment, all retained stress invariants, resource and registration cleanup,
thread-safe counters, mode-specific event pumping, bounded process termination, cross-host Gradle
configuration, CI task availability, report accuracy, and absence of hidden device-loss probes or
claims.
