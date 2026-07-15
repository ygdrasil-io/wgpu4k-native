package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackApiTest : FreeSpec({
    "callback registration exposes closed and quiescent lifecycle states" {
        val registration: CallbackRegistration<Callback> = object : CallbackRegistration<Callback> {
            override val callback: NativeAddress
                get() = error("Callback address is not part of this contract test")
            override val userdata: NativeAddress? = null
            override val policy: CallbackPolicy = CallbackPolicy.ONCE
            override val isClosed: Boolean = true
            override val isQuiescent: Boolean = false

            override fun close() = Unit
        }

        registration.isClosed shouldBe true
        registration.isQuiescent shouldBe false
    }

    "callback policy has no implicit third state" {
        CallbackPolicy.entries shouldBe listOf(CallbackPolicy.ONCE, CallbackPolicy.REPEATING)
    }

    "callback exception handler is a fun interface" {
        var observed: Throwable? = null
        val handler = CallbackExceptionHandler { observed = it }
        val failure = IllegalStateException("callback failure")
        handler.onException(failure)
        observed shouldBe failure
    }

    "fallback reporting contains callback and handler failures" {
        val callbackFailure = IllegalStateException("callback")
        val handlerFailure = IllegalArgumentException("handler")
        val combined = CallbackExceptionHandlerFailure(callbackFailure, handlerFailure)
        combined.callbackFailure shouldBe callbackFailure
        combined.handlerFailure shouldBe handlerFailure
    }
})
