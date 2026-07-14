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

internal fun <H : Any> copyCallbackMessageOrRelease(
    handle: H?,
    release: (H) -> Unit,
    copy: () -> String?,
): String? = try {
    copy()
} catch (failure: Throwable) {
    try {
        handle?.let(release)
    } catch (releaseFailure: Throwable) {
        if (releaseFailure !== failure) failure.addSuppressed(releaseFailure)
    }
    throw failure
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
