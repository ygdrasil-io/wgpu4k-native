@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.memoryScope
import kotlin.concurrent.atomics.AtomicReference
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

internal const val CallbackWaitPhaseTimeoutSeconds = 20
private val CallbackWaitPhaseTimeout = CallbackWaitPhaseTimeoutSeconds.seconds

class GpuCleanupStack {
    private val actions = mutableListOf<() -> Unit>()
    private var closed = false

    fun defer(action: () -> Unit) {
        check(!closed) { "GPU cleanup stack is already closed" }
        actions += action
    }

    fun close(primaryFailure: Throwable? = null) {
        if (closed) return
        closed = true
        var failure = primaryFailure
        actions.asReversed().forEach { action ->
            try {
                action()
            } catch (cleanupFailure: Throwable) {
                val current = failure
                if (current == null) failure = cleanupFailure
                else if (cleanupFailure !== current) current.addSuppressed(cleanupFailure)
            }
        }
        actions.clear()
        if (primaryFailure == null) failure?.let { throw it }
    }
}

internal data class CallbackRequestSnapshot<S : Any>(val status: S?, val message: String?)

data class CallbackDiagnostic(val status: UInt, val message: String?)

sealed interface CallbackOutcome<out T> {
    data class Success<T>(val value: T) : CallbackOutcome<T>
    data class Failure(val failure: Throwable) : CallbackOutcome<Nothing>
}

class CallbackOutcomeState<T> {
    private val value = AtomicReference<CallbackOutcome<T>?>(null)

    val isComplete: Boolean
        get() = value.load() != null

    fun publish(result: T): Boolean = value.compareAndSet(null, CallbackOutcome.Success(result))

    fun publishFailure(failure: Throwable): Boolean =
        value.compareAndSet(null, CallbackOutcome.Failure(failure))

    fun publishCatching(block: () -> T): Boolean {
        val outcome = try {
            CallbackOutcome.Success(block())
        } catch (failure: Throwable) {
            CallbackOutcome.Failure(failure)
        }
        return value.compareAndSet(null, outcome)
    }

    fun outcome(): CallbackOutcome<T>? = value.load()

    fun failureOrNull(): Throwable? = (value.load() as? CallbackOutcome.Failure)?.failure
}

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
    data class Failed(val failure: Throwable) : CallbackRequestValue<Nothing, Nothing>
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
            is CallbackRequestValue.Failed,
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

    fun publishCopied(status: S, handle: H?, copyMessage: () -> String?) {
        val outcome = try {
            CallbackRequestValue.Completed(status, handle, copyMessage())
        } catch (failure: Throwable) {
            releaseAfterFailure(handle, failure, release)
            CallbackRequestValue.Failed(failure)
        }
        if (!value.compareAndSet(CallbackRequestValue.Pending, outcome)) {
            if (outcome is CallbackRequestValue.Completed) outcome.handle?.let(release)
        }
    }

    fun failureOrNull(): Throwable? =
        (value.load() as? CallbackRequestValue.Failed)?.failure

    fun snapshot(): CallbackRequestSnapshot<S> = when (val current = value.load()) {
        is CallbackRequestValue.Completed -> CallbackRequestSnapshot(current.status, current.message)
        is CallbackRequestValue.Transferred -> CallbackRequestSnapshot(current.status, current.message)
        is CallbackRequestValue.Failed -> CallbackRequestSnapshot(null, null)
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
                is CallbackRequestValue.Failed,
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
                is CallbackRequestValue.Failed -> return
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
    releaseAfterFailure(handle, failure, release)
    throw failure
}

private fun <H : Any> releaseAfterFailure(
    handle: H?,
    failure: Throwable,
    release: (H) -> Unit,
) {
    try {
        handle?.let(release)
    } catch (releaseFailure: Throwable) {
        if (releaseFailure !== failure) failure.addSuppressed(releaseFailure)
    }
}

internal fun <S : Any, H : Any> awaitCallbackRequestResult(
    state: CallbackRequestState<S, H>,
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    await: () -> Unit,
    close: () -> Unit,
    isClosed: () -> Boolean,
    isQuiescent: () -> Boolean,
    pump: () -> Unit,
): CallbackRequestSnapshot<S> {
    var primaryFailure: Throwable? = null
    try {
        await()
    } catch (failure: Throwable) {
        primaryFailure = failure
    }

    state.failureOrNull()?.let { callbackFailure ->
        val primary = primaryFailure
        if (primary == null) primaryFailure = callbackFailure
        else if (callbackFailure !== primary) primary.addSuppressed(callbackFailure)
    }

    try {
        closeAndAwaitCallbackQuiescence(
            phase = "$phase-close",
            timeout = timeout,
            close = close,
            isClosed = isClosed,
            isQuiescent = isQuiescent,
            applicationInFlight = { 0 },
            pump = pump,
        )
    } catch (cleanupFailure: Throwable) {
        val primary = primaryFailure
        if (primary == null) primaryFailure = cleanupFailure
        else if (cleanupFailure !== primary) primary.addSuppressed(cleanupFailure)
    }

    primaryFailure?.let { primary ->
        try {
            state.dispose()
        } catch (disposeFailure: Throwable) {
            if (disposeFailure !== primary) primary.addSuppressed(disposeFailure)
        }
        throw primary
    }

    return state.snapshot()
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

internal fun awaitCallbackFutureOrPump(
    futureId: ULong,
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    isComplete: () -> Boolean,
    pumpWithoutFuture: () -> Unit,
    waitOnce: (ULong) -> WGPUWaitStatus,
) {
    if (futureId == 0uL) {
        awaitCallbackCondition(
            phase = phase,
            timeout = timeout,
            isComplete = isComplete,
            pendingDiagnostic = { "future-id=0 explicit-event-pump" },
            pump = pumpWithoutFuture,
        )
        return
    }
    awaitCallbackFuture(
        futureId = futureId,
        phase = phase,
        timeout = timeout,
        isComplete = isComplete,
        waitOnce = waitOnce,
    )
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

fun awaitMapCallbackResult(
    phase: String,
    timeout: Duration = CallbackWaitPhaseTimeout,
    result: () -> CallbackOutcome<CallbackDiagnostic>?,
    close: () -> Unit,
    isClosed: () -> Boolean,
    isQuiescent: () -> Boolean,
    pump: () -> Unit,
): CallbackDiagnostic {
    var snapshot: CallbackDiagnostic? = null
    var primaryFailure: Throwable? = null
    try {
        awaitCallbackCondition(
            phase = phase,
            timeout = timeout,
            isComplete = {
                when (val outcome = result()) {
                    is CallbackOutcome.Success -> snapshot = outcome.value
                    is CallbackOutcome.Failure -> primaryFailure = outcome.failure
                    null -> Unit
                }
                snapshot != null || primaryFailure != null
            },
            pendingDiagnostic = { "result=pending" },
            pump = pump,
        )
    } catch (failure: Throwable) {
        primaryFailure = failure
    }

    try {
        closeAndAwaitCallbackQuiescence(
            phase = "$phase-close",
            timeout = timeout,
            close = close,
            isClosed = isClosed,
            isQuiescent = isQuiescent,
            applicationInFlight = { 0 },
            pump = pump,
        )
    } catch (cleanupFailure: Throwable) {
        val primary = primaryFailure
        if (primary == null) primaryFailure = cleanupFailure
        else if (cleanupFailure !== primary) primary.addSuppressed(cleanupFailure)
    }

    primaryFailure?.let { throw it }
    return snapshot ?: error("$phase completed without a diagnostic")
}

fun closeAndAwaitCallbackQuiescence(
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

internal fun waitAnyOnce(instance: WGPUInstance, futureId: ULong): WGPUWaitStatus =
    memoryScope { scope ->
        val waitInfo = WGPUFutureWaitInfo.allocate(scope).apply {
            future = WGPUFuture.allocate(scope).apply { id = futureId }
            completed = 0u
        }
        wgpuInstanceWaitAny(instance, 1uL, waitInfo, 0uL)
    }
