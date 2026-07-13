package io.ygdrasil.kffi

internal actual fun platformReportUnhandledCallbackException(error: Throwable) {
    try {
        val thread = Thread.currentThread()
        thread.uncaughtExceptionHandler?.uncaughtException(thread, error)
    } catch (_: Throwable) {
        // Reporting must never escape a callback's FFI boundary.
    }
}
