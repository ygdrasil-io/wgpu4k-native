@file:OptIn(
    CallbackRuntimeApi::class,
    UnsafeCallbackRearmApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
)

package io.ygdrasil.kffi

import callbackFixture.fixture_active_native_slots
import callbackFixture.fixture_fire_after_ms
import callbackFixture.fixture_fire_many_shuffled
import callbackFixture.fixture_fire_no_userdata_after_ms
import callbackFixture.fixture_fire_now
import callbackFixture.fixture_fire_twice
import callbackFixture.fixture_roundtrip_userdata
import callbackFixture.fixture_store
import callbackFixture.fixture_store_many
import callbackFixture.fixture_store_no_userdata
import callbackFixture.fixture_unregister_and_join
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toLong
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.AtomicReference
import kotlin.time.Duration.Companion.seconds
import platform.posix.pthread_self

private const val NATIVE_FFI_MANY_CALLBACK_COUNT = 1_000

private fun interface NativeFfiCallback : Callback {
    fun invoke(value: UInt)
}

private fun interface OtherNativeFfiCallback : Callback {
    fun invoke(value: UInt)
}

private val nativeFfiRoutedType =
    CallbackType<NativeFfiCallback>("ffi-native-routed", hasRoutingUserdata = true)
private val nativeFfiRetiredNoUserdataType =
    CallbackType<NativeFfiCallback>("ffi-native-no-userdata-retired", hasRoutingUserdata = false)
private val nativeFfiRearmedNoUserdataType =
    CallbackType<NativeFfiCallback>("ffi-native-no-userdata-rearmed", hasRoutingUserdata = false)

private fun nativeFfiRoute(
    value: UInt,
    @Suppress("UNUSED_PARAMETER") applicationUserdata: COpaquePointer?,
    routingUserdata: COpaquePointer?,
) {
    CallbackRuntime.dispatchSafely(
        nativeFfiRoutedType,
        routingUserdata?.let(::Pointer),
    ) { it.invoke(value) }
}

private fun nativeFfiRouteRetiredNoUserdata(value: UInt) {
    CallbackRuntime.dispatchSafely(nativeFfiRetiredNoUserdataType, null) { it.invoke(value) }
}

private fun nativeFfiRouteRearmedNoUserdata(value: UInt) {
    CallbackRuntime.dispatchSafely(nativeFfiRearmedNoUserdataType, null) { it.invoke(value) }
}

private val nativeFfiRoutedStub = staticCFunction(::nativeFfiRoute)
private val nativeFfiRetiredNoUserdataStub = staticCFunction(::nativeFfiRouteRetiredNoUserdata)
private val nativeFfiRearmedNoUserdataStub = staticCFunction(::nativeFfiRouteRearmedNoUserdata)

class CallbackFfiNativeTest : FreeSpec({
    "callback survives allocation scope and registering function return".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val registeringFunctionReturned = AtomicInt(0)
            val registration = scheduleNativeFfiAfterRegisteringFunctionReturns {
                if (registeringFunctionReturned.load() == 1) calls.fetchAndAdd(1)
            }
            registeringFunctionReturned.store(1)
            try {
                fixture_unregister_and_join()
            } finally {
                fixture_unregister_and_join()
                registration.close()
            }
            calls.load() shouldBe 1
        }
    }

    "callback is delivered from a C-created thread".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val callingThread = pthread_self().toLong()
            val callbackThread = AtomicLong(0)
            val calls = AtomicInt(0)
            val registration = registerNativeFfiRouted(CallbackPolicy.ONCE) {
                calls.fetchAndAdd(1)
                callbackThread.store(pthread_self().toLong())
            }
            try {
                storeNativeFfiRouted(registration)
                fixture_fire_after_ms(12u, 1u)
                fixture_unregister_and_join()
            } finally {
                registration.close()
            }
            calls.load() shouldBe 1
            val deliveredThread = callbackThread.load()
            (deliveredThread != 0L) shouldBe true
            (deliveredThread == callingThread) shouldBe false
        }
    }

    "duplicate native invocation reaches ONCE at most once".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val registration = registerNativeFfiRouted(CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            try {
                storeNativeFfiRouted(registration)
                fixture_fire_twice(13u)
            } finally {
                registration.close()
            }
            calls.load() shouldBe 1
        }
    }

    "1,000 registrations fire shuffled to their matching lambdas".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val observed = Array(NATIVE_FFI_MANY_CALLBACK_COUNT) { AtomicInt(0) }
            val deliveryOrder = mutableListOf<Int>()
            val registrations = List(NATIVE_FFI_MANY_CALLBACK_COUNT) { index ->
                registerNativeFfiRouted(CallbackPolicy.ONCE) { value ->
                    deliveryOrder += value.toInt()
                    if (value.toInt() == index) observed[index].fetchAndAdd(1)
                }
            }
            try {
                registrations.forEachIndexed { index, registration ->
                    fixture_store_many(
                        index.toUInt(),
                        registration.callback.pointer.reinterpret(),
                        requireNotNull(registration.userdata).pointer,
                    )
                }
                fixture_active_native_slots() shouldBe NATIVE_FFI_MANY_CALLBACK_COUNT.toUInt()
                fixture_fire_many_shuffled(NATIVE_FFI_MANY_CALLBACK_COUNT.toUInt())
            } finally {
                registrations.forEach(CallbackRegistration<NativeFfiCallback>::close)
            }
            observed.map(AtomicInt::load) shouldContainExactly
                List(NATIVE_FFI_MANY_CALLBACK_COUNT) { 1 }
            (deliveryOrder == List(NATIVE_FFI_MANY_CALLBACK_COUNT) { it }) shouldBe false
        }
    }

    "callback after close is ignored".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val registration = registerNativeFfiRouted(CallbackPolicy.REPEATING) {
                calls.fetchAndAdd(1)
            }
            try {
                storeNativeFfiRouted(registration)
                registration.close()
                fixture_fire_now(14u)
            } finally {
                registration.close()
            }
            calls.load() shouldBe 0
        }
    }

    "repeated create deliver close loops restore native and runtime counts".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val runtimeBaseline = CallbackRuntime.activeRegistrationCountForTest()
            val nativeBaseline = fixture_active_native_slots()
            repeat(100) { index ->
                val registration = registerNativeFfiRouted(CallbackPolicy.ONCE) {}
                try {
                    storeNativeFfiRouted(registration)
                    fixture_fire_now(index.toUInt())
                } finally {
                    registration.close()
                    fixture_unregister_and_join()
                }
                CallbackRuntime.activeRegistrationCountForTest() shouldBe runtimeBaseline
                fixture_active_native_slots() shouldBe nativeBaseline
            }
        }
    }

    "unknown and cross-type routing tokens never invoke application code".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val reported = AtomicReference<Throwable?>(null)
            val otherType =
                CallbackType<OtherNativeFfiCallback>("ffi-native-other", hasRoutingUserdata = true)
            val other = CallbackRuntime.register(
                type = otherType,
                trampoline = Pointer(nativeFfiRoutedStub),
                policy = CallbackPolicy.REPEATING,
                callback = OtherNativeFfiCallback { calls.fetchAndAdd(1) },
            )
            try {
                CallbackFallbackReporter.installForTest { reported.store(it) }.use {
                    fixture_store(
                        nativeFfiRoutedStub,
                        null,
                        PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong()).pointer,
                    )
                    fixture_fire_now(15u)
                    fixture_store(
                        nativeFfiRoutedStub,
                        null,
                        requireNotNull(other.userdata).pointer,
                    )
                    fixture_fire_now(16u)
                }
            } finally {
                other.close()
            }
            calls.load() shouldBe 0
            reported.load().shouldBeInstanceOf<IllegalArgumentException>()
        }
    }

    "callback and error-handler exceptions stay contained on the C thread".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val callbackFailure = IllegalStateException("callback failure")
            val handlerFailure = IllegalArgumentException("handler failure")
            val reported = AtomicReference<Throwable?>(null)
            val registration = CallbackRuntime.register(
                type = nativeFfiRoutedType,
                trampoline = Pointer(nativeFfiRoutedStub),
                policy = CallbackPolicy.ONCE,
                onError = CallbackExceptionHandler { throw handlerFailure },
                callback = NativeFfiCallback { throw callbackFailure },
            )
            try {
                CallbackFallbackReporter.installForTest { reported.store(it) }.use {
                    storeNativeFfiRouted(registration)
                    fixture_fire_after_ms(17u, 1u)
                    fixture_unregister_and_join()
                }
            } finally {
                registration.close()
            }
            val combined = reported.load().shouldBeInstanceOf<CallbackExceptionHandlerFailure>()
            combined.callbackFailure shouldBe callbackFailure
            combined.handlerFailure shouldBe handlerFailure
        }
    }

    "direct-call failure before publication leaves no runtime entry".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val baseline = CallbackRuntime.activeRegistrationCountForTest()
            val calls = AtomicInt(0)
            val prepared = CallbackRuntime.prepare(
                type = nativeFfiRoutedType,
                trampoline = Pointer(nativeFfiRoutedStub),
                policy = CallbackPolicy.REPEATING,
                callback = NativeFfiCallback { calls.fetchAndAdd(1) },
            )
            shouldThrow<IllegalStateException> {
                try {
                    fixture_store(
                        prepared.callback.pointer.reinterpret(),
                        null,
                        requireNotNull(prepared.userdata).pointer,
                    )
                    throw IllegalStateException("direct downcall failed")
                } finally {
                    prepared.close()
                }
            }
            fixture_fire_now(18u)
            calls.load() shouldBe 0
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
        }
    }

    "throw after C stores callback closes token-backed entry".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val prepared = CallbackRuntime.prepare(
                type = nativeFfiRoutedType,
                trampoline = Pointer(nativeFfiRoutedStub),
                policy = CallbackPolicy.REPEATING,
                callback = NativeFfiCallback { calls.fetchAndAdd(1) },
            )
            try {
                shouldThrow<IllegalStateException> {
                    CallbackRuntime.activateForNativeCall(prepared) { registration ->
                        storeNativeFfiRouted(registration)
                        throw IllegalStateException("failure after native publication")
                    }
                }
                fixture_fire_now(19u)
                calls.load() shouldBe 0
            } finally {
                prepared.close()
            }
        }
    }

    "no-userdata normal re-registration fails after retirement".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val calls = AtomicInt(0)
            val first = CallbackRuntime.register(
                type = nativeFfiRetiredNoUserdataType,
                trampoline = Pointer(nativeFfiRetiredNoUserdataStub),
                policy = CallbackPolicy.ONCE,
                callback = NativeFfiCallback { calls.fetchAndAdd(1) },
            )
            try {
                fixture_store_no_userdata(nativeFfiRetiredNoUserdataStub)
                fixture_fire_no_userdata_after_ms(20u, 0u)
                fixture_unregister_and_join()
            } finally {
                first.close()
            }
            shouldThrow<IllegalStateException> {
                CallbackRuntime.register(
                    type = nativeFfiRetiredNoUserdataType,
                    trampoline = Pointer(nativeFfiRetiredNoUserdataStub),
                    policy = CallbackPolicy.ONCE,
                    callback = NativeFfiCallback {},
                )
            }
            calls.load() shouldBe 1
        }
    }

    "native quiescence permits rearm and only new calls reach the new lambda".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val oldCalls = AtomicInt(0)
            val newCalls = AtomicInt(0)
            val first = CallbackRuntime.register(
                type = nativeFfiRearmedNoUserdataType,
                trampoline = Pointer(nativeFfiRearmedNoUserdataStub),
                policy = CallbackPolicy.REPEATING,
                callback = NativeFfiCallback { oldCalls.fetchAndAdd(1) },
            )
            try {
                fixture_store_no_userdata(nativeFfiRearmedNoUserdataStub)
                fixture_fire_no_userdata_after_ms(21u, 1u)
                fixture_unregister_and_join()
            } finally {
                fixture_unregister_and_join()
                first.close()
            }

            val second = CallbackRuntime.rearmAfterNativeQuiescence(
                type = nativeFfiRearmedNoUserdataType,
                trampoline = Pointer(nativeFfiRearmedNoUserdataStub),
                policy = CallbackPolicy.ONCE,
                callback = NativeFfiCallback { newCalls.fetchAndAdd(1) },
            )
            try {
                fixture_store_no_userdata(nativeFfiRearmedNoUserdataStub)
                fixture_fire_no_userdata_after_ms(22u, 0u)
                fixture_unregister_and_join()
            } finally {
                second.close()
            }

            oldCalls.load() shouldBe 1
            newCalls.load() shouldBe 1
        }
    }

    "userdata roundtrip preserves exact token bits".config(timeout = 10.seconds) {
        withNativeFfiBaselines {
            val registration = registerNativeFfiRouted(CallbackPolicy.REPEATING) {}
            try {
                val userdata = requireNotNull(registration.userdata)
                fixture_roundtrip_userdata(userdata.pointer) shouldBe PlatformCallbackTokenAddressCodec.decode(userdata)
            } finally {
                registration.close()
            }
        }
    }
})

private fun registerNativeFfiRouted(
    policy: CallbackPolicy,
    callback: (UInt) -> Unit,
): CallbackRegistration<NativeFfiCallback> = CallbackRuntime.register(
    type = nativeFfiRoutedType,
    trampoline = Pointer(nativeFfiRoutedStub),
    policy = policy,
    callback = NativeFfiCallback(callback),
)

private fun scheduleNativeFfiAfterRegisteringFunctionReturns(
    callback: (UInt) -> Unit,
): CallbackRegistration<NativeFfiCallback> {
    val registration = registerNativeFfiRouted(CallbackPolicy.ONCE, callback)
    try {
        memScoped {
            val applicationUserdata = alloc<ByteVar>().ptr.reinterpret<COpaque>()
            fixture_store(
                registration.callback.pointer.reinterpret(),
                applicationUserdata,
                requireNotNull(registration.userdata).pointer,
            )
            fixture_fire_after_ms(11u, 10u)
        }
        return registration
    } catch (failure: Throwable) {
        registration.close()
        throw failure
    }
}

private fun storeNativeFfiRouted(registration: CallbackRegistration<NativeFfiCallback>) {
    fixture_store(
        registration.callback.pointer.reinterpret(),
        null,
        requireNotNull(registration.userdata).pointer,
    )
}

private inline fun withNativeFfiBaselines(test: () -> Unit) {
    fixture_unregister_and_join()
    val runtimeBaseline = CallbackRuntime.activeRegistrationCountForTest()
    val nativeBaseline = fixture_active_native_slots()
    try {
        test()
    } finally {
        fixture_unregister_and_join()
        CallbackRuntime.activeRegistrationCountForTest() shouldBe runtimeBaseline
        fixture_active_native_slots() shouldBe nativeBaseline
    }
}
