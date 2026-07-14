# Make Demo Callback Consumption Deterministic Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace racy spontaneous request callbacks and potentially blocking stress polling with bounded, atomic, explicitly pumped callback consumption whose final diagnostics are emitted only after registration quiescence.

**Architecture:** A common coordination helper publishes callback payload before status, pumps `WaitAnyOnly` futures with non-blocking waits and a monotonic deadline, and transfers owned handles once. Stress teardown closes the repeating error registration and waits for the KFFI runtime's `isQuiescent` signal before exact assertions; all stress polling is non-blocking.

**Tech Stack:** Kotlin Multiplatform, Kotlin atomic APIs, generated WebGPU bindings, KFFI callback registrations, Gradle JVM/Native process verification.

## Global Constraints

- The approved design is `docs/superpowers/specs/2026-07-14-external-review-remediation-design.md`.
- Tasks in `2026-07-14-harden-kffi-callback-fixture.md` must already provide `CallbackRegistration.isQuiescent`.
- Do not modify, fork, upgrade, or replace `wgpu-native`.
- Do not restore device-loss stress, an opt-in probe, allowed failure, ignored assertion, or hidden skip.
- Adapter and device requests must use `WGPUCallbackMode_WaitAnyOnly`, atomics, zero-timeout `wgpuInstanceWaitAny`, and a 20-second monotonic deadline.
- A zero future remains rejected by default. The named v29 compatibility opt-in may accept it only
  when the callback is already complete and its registration is quiescent; it must never pump a
  zero future or accept incomplete/non-quiescent state.
- Publish a completed request snapshot only after the owned handle and copied message are
  available; never expose completion without the corresponding payload.
- Transfer or release each adapter/device handle exactly once, including failure and late-cleanup paths.
- Retain handle ownership in request state until registration close and runtime quiescence both
  succeed. Cleanup failure must atomically dispose the state and release both published and late
  handles exactly once.
- Keep blocking or repeated event pumping outside graphical render loops.
- Keep the stress invariants: 64 real submissions, 1,000 concurrent registrations per mode, 900/100 deterministic delivery/suppression for `WaitAnyOnly` and `AllowProcessEvents`, 1,000/0 for `AllowSpontaneous`, no duplicates, and exactly two validation errors.
- Print `pending=0` only after every relevant registration is closed and quiescent.
- Android/JNA is compile-verified only because its generated WebGPU request and wait functions remain pre-existing runtime stubs.
- Use test-driven development, preserve unrelated changes, and do not push.

## File Map

- `demo/common/src/commonMain/kotlin/CallbackCoordination.kt`: request publication, deadlines, first-failure diagnostics, and close/quiescence coordination.
- `demo/common/src/commonTest/kotlin/CallbackCoordinationTest.kt`: deterministic tests with injected non-blocking pumps.
- `demo/common/src/commonMain/kotlin/ext.kt`: safe adapter/device request helpers and result ownership.
- `demo/common/src/commonTest/kotlin/CallbackResultTest.kt`: adapter/device/map failure and release contracts.
- `demo/common/src/commonMain/kotlin/CallbackStress.kt`: non-blocking queue/error stress and truthful teardown.
- `demo/common/src/commonMain/kotlin/HeadlessTriangle.kt`: atomic first map result and new device signature.
- `demo/desktop-and-ios/src/jvmMain/kotlin/Capture.jvm.kt`: first map status/message diagnostic and new device signature.
- Remaining platform entry points: pass their instance to `getDevice`.

---

### Task 1: Await adapter and device requests deterministically

**Files:**
- Create: `demo/common/src/commonMain/kotlin/CallbackCoordination.kt`
- Create: `demo/common/src/commonTest/kotlin/CallbackCoordinationTest.kt`
- Modify: `demo/common/src/commonMain/kotlin/ext.kt`
- Modify: `demo/common/src/commonTest/kotlin/CallbackResultTest.kt`
- Modify: `demo/common/src/commonMain/kotlin/HeadlessTriangle.kt`
- Modify: `demo/desktop-and-ios/src/jvmMain/kotlin/Capture.jvm.kt`
- Modify: `demo/desktop-and-ios/src/jvmMain/kotlin/main.kt`
- Modify: `demo/desktop-and-ios/src/desktopMain/kotlin/main.kt`
- Modify: `demo/desktop-and-ios/src/iosMain/kotlin/AppDelegate.kt`
- Modify: `demo/android/src/androidMain/kotlin/MainActivity.kt`
- Modify: `demo/common/src/androidNativeMain/kotlin/main.kt`

**Interfaces:**
- Produces: `internal const val CallbackWaitPhaseTimeoutSeconds = 20`.
- Produces: `CallbackRequestState<S : Any, H : Any>`, whose completed snapshot contains status,
  handle, and copied message, and whose disposed state releases published or late handles.
- Produces: `awaitCallbackCondition(phase, timeout, isComplete, pendingDiagnostic, pump)`.
- Produces: `awaitCallbackFuture(futureId, phase, timeout, isComplete, waitOnce)`.
- Produces: `ZeroFuturePolicy`, defaulting to strict rejection with an explicit
  `ALLOW_COMPLETED_SYNCHRONOUSLY` compatibility mode.
- Produces: `waitAnyOnce(instance: WGPUInstance, futureId: ULong): WGPUWaitStatus`.
- Changes: `fun getDevice(adapter: WGPUAdapter, instance: WGPUInstance): WGPUDevice`.
- Preserves: `fun getAdapter(surface, instance, backendType): WGPUAdapter`.

- [ ] **Step 1: Add failing coordination and adapter-result tests**

Create `CallbackCoordinationTest.kt` with deterministic fake pumps:

```kotlin
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration

class CallbackCoordinationTest {
    @Test
    fun callbackFuturePumpsUntilStatusIsPublished() {
        val state = CallbackRequestState<UInt, String>(release = {})
        var calls = 0
        awaitCallbackFuture(
            futureId = 41uL,
            phase = "request-test",
            isComplete = { state.isComplete },
            waitOnce = { id ->
                assertEquals(41uL, id)
                calls += 1
                if (calls == 2) state.publish(7u, "handle", "ready")
                if (calls == 1) WGPUWaitStatus_TimedOut else WGPUWaitStatus_Success
            },
        )
        assertEquals(2, calls)
        assertEquals(7u, state.snapshot().status)
        assertEquals("handle", state.takeHandle())
        assertNull(state.takeHandle())
    }

    @Test
    fun zeroFutureIsRejectedWithoutPumping() {
        var pumped = false
        val failure = assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(0uL, "request-zero", isComplete = { false }) {
                pumped = true
                WGPUWaitStatus_Success
            }
        }
        assertFalse(pumped)
        assertTrue(failure.message.orEmpty().contains("request-zero"))
    }

    @Test
    fun synchronousZeroFutureRequiresExplicitCompletedAndQuiescentOptIn() {
        var pumped = false
        awaitCallbackFuture(
            futureId = 0uL,
            phase = "request-sync-zero",
            zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
            isComplete = { true },
            isQuiescent = { true },
            waitOnce = {
                pumped = true
                WGPUWaitStatus_Success
            },
        )
        assertFalse(pumped)
    }

    @Test
    fun synchronousZeroFutureRejectsIncompleteOrNonQuiescentState() {
        listOf(false to true, true to false).forEach { (complete, quiescent) ->
            assertFailsWith<IllegalStateException> {
                awaitCallbackFuture(
                    futureId = 0uL,
                    phase = "request-invalid-sync-zero",
                    zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
                    isComplete = { complete },
                    isQuiescent = { quiescent },
                    waitOnce = { error("zero future must not be pumped") },
                )
            }
        }
    }

    @Test
    fun cleanupFailureDisposesAndReleasesAPublishedHandleExactlyOnce() {
        var releases = 0
        val state = CallbackRequestState<UInt, String> { releases += 1 }
        state.publish(7u, "owned", "ready")

        state.dispose()
        state.dispose()

        assertEquals(1, releases)
        assertFalse(state.isComplete)
        assertNull(state.takeHandle())
    }

    @Test
    fun latePublicationAfterDisposalReleasesItsHandleAndStaysIncomplete() {
        var released: String? = null
        val state = CallbackRequestState<UInt, String> { released = it }
        state.dispose()

        state.publish(7u, "late", "too late")

        assertEquals("late", released)
        assertFalse(state.isComplete)
        assertNull(state.snapshot().status)
        assertNull(state.takeHandle())
    }

    @Test
    fun unexpectedWaitStatusIncludesThePhase() {
        val failure = assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(1uL, "request-error", isComplete = { false }) {
                WGPUWaitStatus_Error
            }
        }
        assertTrue(failure.message.orEmpty().contains("request-error"))
    }

    @Test
    fun zeroDeadlineFailsAfterOneNonBlockingPump() {
        var calls = 0
        assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(
                1uL,
                "request-timeout",
                Duration.ZERO,
                isComplete = { false },
            ) {
                calls += 1
                WGPUWaitStatus_TimedOut
            }
        }
        assertEquals(1, calls)
    }
}
```

Extend `CallbackResultTest.kt` with `resolveAdapterRequestResult` cases: non-success releases a
non-null handle exactly once and retains the backend message; success with no handle fails without a
release.

- [ ] **Step 2: Run the common JVM tests and verify RED**

Run:

```bash
rtk ./gradlew :demo:common:jvmTest
```

Expected: compilation fails because the coordination types and adapter resolver do not exist.

- [ ] **Step 3: Implement atomic publication and bounded future pumping**

Create `CallbackCoordination.kt`:

```kotlin
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.memoryScope
import kotlin.concurrent.atomics.AtomicReference
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

internal const val CallbackWaitPhaseTimeoutSeconds = 20
private val CallbackWaitPhaseTimeout = CallbackWaitPhaseTimeoutSeconds.seconds

internal data class CallbackRequestSnapshot<S : Any>(val status: S?, val message: String?)

internal enum class ZeroFuturePolicy {
    REJECT,
    ALLOW_COMPLETED_SYNCHRONOUSLY,
}

private sealed interface CallbackRequestValue<out S : Any, out H : Any> {
    data object Pending : CallbackRequestValue<Nothing, Nothing>
    data class Completed<S : Any, H : Any>(
        val status: S,
        val handle: H?,
        val message: String?,
    ) : CallbackRequestValue<S, H>
    data class Transferred<S : Any>(
        val status: S,
        val message: String?,
    ) : CallbackRequestValue<S, Nothing>
    data object Disposed : CallbackRequestValue<Nothing, Nothing>
}

internal class CallbackRequestState<S : Any, H : Any>(
    private val release: (H) -> Unit,
) {
    private val value = AtomicReference<CallbackRequestValue<S, H>>(CallbackRequestValue.Pending)

    val isComplete: Boolean
        get() = when (value.load()) {
            is CallbackRequestValue.Completed,
            is CallbackRequestValue.Transferred,
            -> true
            CallbackRequestValue.Pending,
            CallbackRequestValue.Disposed,
            -> false
        }

    fun publish(status: S, handle: H?, message: String?) {
        val completed = CallbackRequestValue.Completed(status, handle, message)
        if (!value.compareAndSet(CallbackRequestValue.Pending, completed)) {
            handle?.let(release)
        }
    }

    fun snapshot(): CallbackRequestSnapshot<S> = when (val current = value.load()) {
        is CallbackRequestValue.Completed -> CallbackRequestSnapshot(current.status, current.message)
        is CallbackRequestValue.Transferred -> CallbackRequestSnapshot(current.status, current.message)
        CallbackRequestValue.Pending,
        CallbackRequestValue.Disposed,
        -> CallbackRequestSnapshot(null, null)
    }

    fun takeHandle(): H? {
        while (true) {
            when (val current = value.load()) {
                is CallbackRequestValue.Completed -> {
                    if (
                        value.compareAndSet(
                            current,
                            CallbackRequestValue.Transferred(current.status, current.message),
                        )
                    ) return current.handle
                }
                is CallbackRequestValue.Transferred,
                CallbackRequestValue.Pending,
                CallbackRequestValue.Disposed,
                -> return null
            }
        }
    }

    fun dispose() {
        while (true) {
            when (val current = value.load()) {
                CallbackRequestValue.Disposed -> return
                CallbackRequestValue.Pending,
                is CallbackRequestValue.Transferred,
                -> if (value.compareAndSet(current, CallbackRequestValue.Disposed)) return
                is CallbackRequestValue.Completed -> {
                    if (value.compareAndSet(current, CallbackRequestValue.Disposed)) {
                        current.handle?.let(release)
                        return
                    }
                }
            }
        }
    }
}

internal fun awaitCallbackFuture(
    futureId: ULong,
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    zeroFuturePolicy: ZeroFuturePolicy = ZeroFuturePolicy.REJECT,
    isComplete: () -> Boolean,
    isQuiescent: () -> Boolean = { false },
    waitOnce: (ULong) -> WGPUWaitStatus,
) {
    if (futureId == 0uL) {
        check(
            zeroFuturePolicy == ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY &&
                isComplete() &&
                isQuiescent(),
        ) { "$phase future-id=0 without a completed quiescent synchronous callback" }
        return
    }
    awaitCallbackCondition(
        phase = phase,
        timeout = timeout,
        isComplete = isComplete,
        pendingDiagnostic = { "future-id=$futureId" },
    ) {
        val status = waitOnce(futureId)
        check(status == WGPUWaitStatus_Success || status == WGPUWaitStatus_TimedOut) {
            "$phase waitAny status=$status future-id=$futureId"
        }
    }
}

internal fun awaitCallbackCondition(
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    isComplete: () -> Boolean,
    pendingDiagnostic: () -> String,
    pump: () -> Unit,
) {
    val deadline = TimeSource.Monotonic.markNow() + timeout
    while (!isComplete()) {
        pump()
        if (deadline.hasPassedNow()) error("$phase timeout ${pendingDiagnostic()}")
    }
}

internal fun waitAnyOnce(instance: WGPUInstance, futureId: ULong): WGPUWaitStatus =
    memoryScope { scope ->
        val waitInfo = WGPUFutureWaitInfo.allocate(scope).apply {
            future = WGPUFuture.allocate(scope).apply { id = futureId }
            completed = 0u
        }
        wgpuInstanceWaitAny(instance, 1uL, waitInfo, 0uL)
    }
```

- [ ] **Step 4: Convert `getAdapter` and `getDevice` to `WaitAnyOnly`**

Add a symmetric adapter resolver to `ext.kt`:

```kotlin
internal fun <A : Any> resolveAdapterRequestResult(
    status: WGPURequestAdapterStatus?,
    adapter: A?,
    message: String?,
    release: (A) -> Unit,
): A {
    if (status != WGPURequestAdapterStatus_Success) {
        adapter?.let(release)
        error("fail to get adapter with status $status${callbackMessageSuffix(message)}")
    }
    return adapter ?: error("fail to get adapter: success status returned no adapter")
}
```

Implement both helpers with the same ownership transaction. For device, the complete shape is:

```kotlin
fun getDevice(adapter: WGPUAdapter, instance: WGPUInstance): WGPUDevice {
    val state = CallbackRequestState<WGPURequestDeviceStatus, WGPUDevice>(::wgpuDeviceRelease)
    val registration = WGPURequestDeviceCallback.register(CallbackPolicy.ONCE) { status, device, message, _ ->
        state.publish(status, device, message.data?.toKString(message.length))
    }
    var futureId = 0uL
    var requestCompleted = false
    var cleanupCompleted = false
    try {
        futureId = memoryScope { scope ->
            val info = WGPURequestDeviceCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_WaitAnyOnly,
                registration,
            )
            wgpuAdapterRequestDevice(adapter, null, info).id
        }
        awaitCallbackFuture(
            futureId = futureId,
            phase = "request-device",
            zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
            isComplete = { state.isComplete },
            isQuiescent = { registration.isQuiescent },
            waitOnce = { waitAnyOnce(instance, it) },
        )
        requestCompleted = true
    } finally {
        try {
            registration.close()
            awaitCallbackCondition(
                phase = "request-device-cleanup",
                isComplete = { registration.isQuiescent },
                pendingDiagnostic = { "future-id=$futureId registrationQuiescent=false" },
                pump = {
                    if (futureId != 0uL) {
                        val status = waitAnyOnce(instance, futureId)
                        check(status == WGPUWaitStatus_Success || status == WGPUWaitStatus_TimedOut) {
                            "request-device-cleanup waitAny status=$status future-id=$futureId"
                        }
                    }
                },
            )
            cleanupCompleted = true
        } finally {
            if (!requestCompleted || !cleanupCompleted) state.dispose()
        }
    }
    val snapshot = state.snapshot()
    return resolveDeviceRequestResult(
        snapshot.status,
        state.takeHandle(),
        snapshot.message,
        ::wgpuDeviceRelease,
    )
}
```

Use the identical flow, including close-then-quiesce cleanup, for adapter, allocating `WGPURequestAdapterOptions` and
`WGPURequestAdapterCallbackInfo` in the downcall scope and releasing through
`wgpuAdapterRelease`. Neither helper may call `takeHandle()` or resolve a result until cleanup has
completed successfully. Any request or cleanup failure calls `dispose()` so an already-published
or later-published handle is released once. The callback-info allocation scope closes before
deferred delivery on conforming implementations. Both helpers must opt in to
`ALLOW_COMPLETED_SYNCHRONOUSLY` explicitly for `wgpu-native` v29, which invokes these two callbacks
during the downcall and returns `NULL_FUTURE`. The opt-in succeeds only after both atomic completion
and runtime quiescence have been observed; all nonzero futures follow the normal bounded `waitAny`
path.

- [ ] **Step 5: Migrate every device-request caller**

Change all seven call sites:

```kotlin
getDevice(adapter, instance)
getDevice(createdAdapter, createdInstance)
```

The required files are `HeadlessTriangle.kt`, `Capture.jvm.kt`, JVM `main.kt`, desktop `main.kt`,
`AppDelegate.kt`, `MainActivity.kt`, and Android Native `main.kt`. Verify there is no old call:

```bash
rtk rg -n 'getDevice\([^,()]+\)' demo
```

Expected: no match.

- [ ] **Step 6: Run platform compilation, headless checks, and commit**

Run:

```bash
rtk ./gradlew :demo:common:jvmTest :demo:common:macosArm64Test
rtk ./gradlew \
  :demo:common:compileKotlinMetadata \
  :demo:common:compileDebugKotlinAndroid \
  :demo:common:compileKotlinAndroidNativeArm64 \
  :demo:common:compileKotlinAndroidNativeX64 \
  :demo:desktop-and-ios:compileKotlinJvm \
  :demo:desktop-and-ios:compileKotlinMacosArm64 \
  :demo:desktop-and-ios:compileKotlinIosArm64 \
  :demo:android:compileDebugKotlinAndroid
rtk ./gradlew \
  :demo:desktop-and-ios:verifyJvmHeadlessRender \
  :demo:desktop-and-ios:verifyNativeHeadlessRender
rtk git diff --check
```

Expected: every available target compiles and both host headless renders pass. Android/JNA is not
executed because its generated request/wait functions remain existing stubs.

Commit:

```bash
rtk git add demo
rtk git commit -m "fix(demo): await device requests deterministically"
```

---

### Task 2: Make stress polling, quiescence, and diagnostics truthful

**Files:**
- Modify: `demo/common/src/commonMain/kotlin/CallbackCoordination.kt`
- Modify: `demo/common/src/commonTest/kotlin/CallbackCoordinationTest.kt`
- Modify: `demo/common/src/commonMain/kotlin/CallbackStress.kt`
- Modify: `demo/common/src/commonMain/kotlin/HeadlessTriangle.kt`
- Modify: `demo/desktop-and-ios/src/jvmMain/kotlin/Capture.jvm.kt`

**Interfaces:**
- Produces: `CallbackDiagnostic(status: UInt, message: String?)` and atomic `recordFirst`.
- Produces: `closeAndAwaitCallbackQuiescence(...)` using the Task 1 `awaitCallbackCondition` helper.
- Consumes: `CallbackRegistration.isQuiescent` from the KFFI plan.
- Preserves: all stress counts and callback modes from the amended Task 11 design.

- [ ] **Step 1: Add failing coordination tests**

Extend `CallbackCoordinationTest.kt` with tests that prove:

```kotlin
@Test
fun firstDiagnosticWins() {
    val first = AtomicReference<CallbackDiagnostic?>(null)
    first.recordFirst(7u, "first")
    first.recordFirst(8u, "second")
    assertEquals(CallbackDiagnostic(7u, "first"), first.load())
}

@Test
fun closePrecedesQuiescencePumping() {
    var closed = false
    var inFlight = 1
    var pumps = 0
    closeAndAwaitCallbackQuiescence(
        phase = "error-close",
        close = { closed = true },
        isClosed = { closed },
        isQuiescent = { closed && inFlight == 0 },
        applicationInFlight = { inFlight },
        pump = {
            assertTrue(closed)
            pumps += 1
            inFlight = 0
        },
    )
    assertEquals(1, pumps)
}

@Test
fun quiescenceTimeoutReportsThePhaseAndInFlightCount() {
    val failure = assertFailsWith<IllegalStateException> {
        closeAndAwaitCallbackQuiescence(
            phase = "error-timeout",
            timeout = Duration.ZERO,
            close = {},
            isClosed = { true },
            isQuiescent = { false },
            applicationInFlight = { 3 },
            pump = {},
        )
    }
    assertTrue(failure.message.orEmpty().contains("error-timeout"))
    assertTrue(failure.message.orEmpty().contains("inFlight=3"))
}
```

Also test that `awaitCallbackCondition` performs no pump when its condition is already true.

- [ ] **Step 2: Run the common JVM tests and verify RED**

Run:

```bash
rtk ./gradlew :demo:common:jvmTest
```

Expected: compilation fails because diagnostics and quiescence helpers do not exist.

- [ ] **Step 3: Implement first-diagnostic and close/quiescence helpers**

Append first-diagnostic and close/quiescence support to `CallbackCoordination.kt`:

```kotlin
internal data class CallbackDiagnostic(val status: UInt, val message: String?)

internal fun AtomicReference<CallbackDiagnostic?>.recordFirst(status: UInt, message: String?) {
    compareAndSet(null, CallbackDiagnostic(status, message))
}

internal fun closeAndAwaitCallbackQuiescence(
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    close: () -> Unit,
    isClosed: () -> Boolean,
    isQuiescent: () -> Boolean,
    applicationInFlight: () -> Int,
    pump: () -> Unit,
) {
    close()
    check(isClosed()) { "$phase registration did not close" }
    awaitCallbackCondition(
        phase = phase,
        timeout = timeout,
        isComplete = { isQuiescent() && applicationInFlight() == 0 },
        pendingDiagnostic = {
            "registrationQuiescent=${isQuiescent()} inFlight=${applicationInFlight()}"
        },
        pump = pump,
    )
}
```

Keep the injected lambdas so common tests can prove ordering without constructing a generated
registration. Production passes `errorRegistration::close`, `errorRegistration::isClosed`, and
`errorRegistration::isQuiescent`.

- [ ] **Step 4: Remove blocking stress polls and retain the first queue failure**

In `runQueueCallbackPhase`, add `AtomicReference<CallbackDiagnostic?>(null)` for the first failing
queue result. Copy the callback message and call `recordFirst(status, message)` when status is not
`WGPUQueueWorkDoneStatus_Success`; include that object in the failed-status assertion.

Replace both `wgpuDevicePoll(device, 1u, null)` calls. The `WaitAnyOnly` loop becomes:

```kotlin
while (queueCallbacksCompleted.load() < expectedDeliveries) {
    wgpuDevicePoll(device, 0u, null)
    pumpWaitAnyBatches(instance, futureIds, modeName)
    if (deadline.hasPassedNow()) failQueuePhase(modeName, "delivery", calls, suppressBeforeDelivery)
}
```

The `AllowSpontaneous` loop retains only `wgpuDevicePoll(device, 0u, null)`. Do not introduce any
other wait-one poll in `CallbackStress.kt`.

- [ ] **Step 5: Close and quiesce the repeating error callback before exact assertions**

In `runCallbackStress`, add:

```kotlin
val errorCallbacksInFlight = AtomicInt(0)
val firstUnexpectedError = AtomicReference<CallbackDiagnostic?>(null)
```

The repeating callback must copy the message and use this order:

```kotlin
errorCallbacksInFlight.fetchAndAdd(1)
try {
    val messageText = message.data?.toKString(message.length)
    val ordinal = uncapturedErrorCalls.fetchAndAdd(1) + 1
    if (type == WGPUErrorType_Validation) validationErrorCalls.fetchAndAdd(1)
    if (type != WGPUErrorType_Validation || ordinal > 2) {
        firstUnexpectedError.recordFirst(type, messageText)
    }
    println("callback-stress uncaptured-error type=$type message=${messageText.orEmpty()}")
} finally {
    errorCallbacksCompleted.fetchAndAdd(1)
    errorCallbacksInFlight.fetchAndAdd(-1)
}
```

Pass the registration, application in-flight counter, and first diagnostic to
`runUncapturedErrorPhase`. After two completed callbacks are observed, execute:

```kotlin
closeAndAwaitCallbackQuiescence(
    phase = "uncaptured-error-close",
    close = errorRegistration::close,
    isClosed = { errorRegistration.isClosed },
    isQuiescent = { errorRegistration.isQuiescent },
    applicationInFlight = errorCallbacksInFlight::load,
    pump = { wgpuDevicePoll(device, 0u, null) },
)
```

Only then assert exactly `2` completed, uncaptured, and validation callbacks, require the first
unexpected diagnostic to be null, and print:

```text
callback-stress uncaptured-errors=2 validation-errors=2 registration=closed inFlight=0 pending=0
```

Remove the old assertion that the registration remains open. Keep the outer `finally` close as an
idempotent fallback. Print `callback-stress complete pending=0` only after this phase returns.

- [ ] **Step 6: Preserve first map status and message atomically**

In `HeadlessTriangle.kt`, replace `mapStatus` and `mapMessage` with
`AtomicReference<CallbackDiagnostic?>(null)`. The callback calls `recordFirst` with the copied
message; the pump loop reads one diagnostic snapshot and passes its fields to
`requireSuccessfulMapResult`.

In JVM `Capture.jvm.kt`, change its Java `AtomicReference<WGPUMapAsyncStatus?>` to
`AtomicReference<CallbackDiagnostic?>`. The callback must copy the message and use
`compareAndSet(null, CallbackDiagnostic(mapStatus, copiedMessage))`; after polling, call
`requireSuccessfulMapResult(result?.status, result?.message)`.

- [ ] **Step 7: Run unit, static, stress, and render verification**

Run:

```bash
rtk ./gradlew :demo:common:jvmTest :demo:common:macosArm64Test
rtk rg -n 'wgpuDevicePoll\(device, 1u' demo/common/src/commonMain/kotlin/CallbackStress.kt
rtk ./gradlew \
  :demo:desktop-and-ios:verifyJvmCallbackStress \
  :demo:desktop-and-ios:verifyNativeCallbackStress
rtk ./gradlew \
  :demo:desktop-and-ios:verifyJvmHeadlessRender \
  :demo:desktop-and-ios:verifyNativeHeadlessRender
rtk git diff --check
```

Expected: the `rg` command returns no match; JVM and Native stress report 900/100, 900/100, and
1,000/0 with no duplicates, exactly two validation errors, `registration=closed`, `inFlight=0`,
and `pending=0`; both renders pass.

- [ ] **Step 8: Commit the deterministic stress teardown**

```bash
rtk git add demo
rtk git commit -m "test(demo): make callback stress teardown deterministic"
```

---

## Review Gate

Review each task from its recorded base commit. The reviewer must verify spec compliance and code
quality, including status-last atomic publication, single handle transfer, explicit callback mode,
bounded zero-timeout future pumping, every migrated caller, absence of blocking stress poll calls,
runtime-backed quiescence before exact counts, first-failure messages, unchanged stress totals, and
the documented Android/JNA compile-only limitation.
