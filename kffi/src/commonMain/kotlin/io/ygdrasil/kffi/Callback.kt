package io.ygdrasil.kffi

interface Callback

enum class CallbackPolicy {
    ONCE,
    REPEATING,
}

fun interface CallbackExceptionHandler {
    fun onException(error: Throwable)

    companion object {
        val Default: CallbackExceptionHandler = CallbackExceptionHandler(::reportUnhandledCallbackException)
    }
}

interface CallbackRegistration<C : Callback> : AutoCloseable {
    val callback: NativeAddress
    val userdata: NativeAddress?
    val policy: CallbackPolicy
    val isClosed: Boolean

    override fun close()
}

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "Re-arming a callback without userdata can route a delayed native call to the wrong Kotlin lambda unless native quiescence has already been established.",
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class UnsafeCallbackRearmApi

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API is reserved for generated callback bindings.",
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class CallbackRuntimeApi
