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

internal class CallbackRequestState<S : Any, H : Any> {
    private val status = AtomicReference<S?>(null)
    private val handle = AtomicReference<H?>(null)
    private val message = AtomicReference<String?>(null)

    val isComplete: Boolean get() = status.load() != null

    fun publish(status: S, handle: H?, message: String?) {
        this.handle.store(handle)
        this.message.store(message)
        this.status.store(status)
    }

    fun snapshot(): CallbackRequestSnapshot<S> =
        CallbackRequestSnapshot(status.load(), message.load())

    fun takeHandle(): H? = handle.exchange(null)
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
