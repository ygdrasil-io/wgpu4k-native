package io.ygdrasil.kffi

import kotlin.concurrent.atomics.AtomicReference
import kotlin.concurrent.atomics.ExperimentalAtomicApi

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

@OptIn(ExperimentalAtomicApi::class)
internal object CallbackFallbackReporter {
    private val testOverride = AtomicReference<CallbackExceptionHandler?>(null)

    fun report(error: Throwable) {
        val override = testOverride.load()
        if (override != null) {
            override.onException(error)
        } else {
            platformReportUnhandledCallbackException(error)
        }
    }

    internal fun installForTest(handler: CallbackExceptionHandler): AutoCloseable {
        val previous = testOverride.exchange(handler)
        return AutoCloseable { testOverride.store(previous) }
    }
}
