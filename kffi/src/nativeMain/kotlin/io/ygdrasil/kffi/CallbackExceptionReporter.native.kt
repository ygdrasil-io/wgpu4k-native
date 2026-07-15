package io.ygdrasil.kffi

internal actual fun platformReportUnhandledCallbackException(error: Throwable) {
    try {
        error.printStackTrace()
    } catch (_: Throwable) {
        // Reporting must never escape a callback's FFI boundary.
    }
}
