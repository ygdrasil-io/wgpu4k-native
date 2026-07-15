@file:OptIn(
    CallbackRuntimeApi::class,
    UnsafeCallbackRearmApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.time.Duration.Companion.seconds

private const val MANY_CALLBACK_COUNT = 1_000

private fun interface JvmFfiCallback : Callback {
    fun invoke(value: Int)
}

private fun interface OtherJvmFfiCallback : Callback {
    fun invoke(value: Int)
}

private object JvmFfiTrampolines {
    val routedType = CallbackType<JvmFfiCallback>("ffi-jvm-routed", hasRoutingUserdata = true)
    val retiredNoUserdataType =
        CallbackType<JvmFfiCallback>("ffi-jvm-no-userdata-retired", hasRoutingUserdata = false)
    val rearmedNoUserdataType =
        CallbackType<JvmFfiCallback>("ffi-jvm-no-userdata-rearmed", hasRoutingUserdata = false)

    private val linker = Linker.nativeLinker()
    private val lookup = MethodHandles.lookup()
    private val callbackDescriptor = FunctionDescriptor.ofVoid(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
    )
    private val noUserdataDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)

    val routedStub: MemorySegment = linker.upcallStub(
        lookup.findStatic(
            JvmFfiTrampolines::class.java,
            "route",
            MethodType.methodType(
                Void.TYPE,
                Integer.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        ),
        callbackDescriptor,
        Arena.global(),
    )

    val retiredNoUserdataStub: MemorySegment = linker.upcallStub(
        lookup.findStatic(
            JvmFfiTrampolines::class.java,
            "routeRetiredNoUserdata",
            MethodType.methodType(Void.TYPE, Integer.TYPE),
        ),
        noUserdataDescriptor,
        Arena.global(),
    )

    val rearmedNoUserdataStub: MemorySegment = linker.upcallStub(
        lookup.findStatic(
            JvmFfiTrampolines::class.java,
            "routeRearmedNoUserdata",
            MethodType.methodType(Void.TYPE, Integer.TYPE),
        ),
        noUserdataDescriptor,
        Arena.global(),
    )

    @JvmStatic
    fun route(
        value: Int,
        @Suppress("UNUSED_PARAMETER") applicationUserdata: MemorySegment,
        routingUserdata: MemorySegment,
    ) {
        CallbackRuntime.dispatchSafely(
            routedType,
            JvmNativeAddress(routingUserdata),
        ) { it.invoke(value) }
    }

    @JvmStatic
    fun routeRetiredNoUserdata(value: Int) {
        CallbackRuntime.dispatchSafely(retiredNoUserdataType, null) { it.invoke(value) }
    }

    @JvmStatic
    fun routeRearmedNoUserdata(value: Int) {
        CallbackRuntime.dispatchSafely(rearmedNoUserdataType, null) { it.invoke(value) }
    }
}

private object JvmCallbackFixture {
    private val linker = Linker.nativeLinker()
    private val arena = Arena.global()
    private val lookup = SymbolLookup.libraryLookup(
        requireNotNull(System.getProperty("kffi.callback.fixture.library")) {
            "kffi.callback.fixture.library must point at the compiled callback fixture"
        },
        arena,
    )

    private fun downcall(name: String, descriptor: FunctionDescriptor): MethodHandle =
        linker.downcallHandle(
            lookup.find(name).orElseThrow { UnsatisfiedLinkError("unresolved fixture symbol: $name") },
            descriptor,
        )

    private val storeHandle = downcall(
        "fixture_store",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )
    private val fireNowHandle = downcall(
        "fixture_fire_now",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
    )
    private val fireTwiceHandle = downcall(
        "fixture_fire_twice",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
    )
    private val fireAfterMsHandle = downcall(
        "fixture_fire_after_ms",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
    )
    private val storeManyHandle = downcall(
        "fixture_store_many",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )
    private val fireManyShuffledHandle = downcall(
        "fixture_fire_many_shuffled",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
    )
    private val storeNoUserdataHandle = downcall(
        "fixture_store_no_userdata",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
    )
    private val fireNoUserdataAfterMsHandle = downcall(
        "fixture_fire_no_userdata_after_ms",
        FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
    )
    private val unregisterAndJoinHandle = downcall(
        "fixture_unregister_and_join",
        FunctionDescriptor.ofVoid(),
    )
    private val roundtripUserdataHandle = downcall(
        "fixture_roundtrip_userdata",
        FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS),
    )
    private val activeNativeSlotsHandle = downcall(
        "fixture_active_native_slots",
        FunctionDescriptor.of(ValueLayout.JAVA_INT),
    )

    fun store(callback: MemorySegment, applicationUserdata: MemorySegment, routingUserdata: MemorySegment) {
        storeHandle.invokeExact(callback, applicationUserdata, routingUserdata)
    }

    fun fireNow(value: Int) {
        fireNowHandle.invokeExact(value)
    }

    fun fireTwice(value: Int) {
        fireTwiceHandle.invokeExact(value)
    }

    fun fireAfterMs(value: Int, delayMs: Int) {
        fireAfterMsHandle.invokeExact(value, delayMs)
    }

    fun storeMany(index: Int, callback: MemorySegment, routingUserdata: MemorySegment) {
        storeManyHandle.invokeExact(index, callback, routingUserdata)
    }

    fun fireManyShuffled(count: Int) {
        fireManyShuffledHandle.invokeExact(count)
    }

    fun storeNoUserdata(callback: MemorySegment) {
        storeNoUserdataHandle.invokeExact(callback)
    }

    fun fireNoUserdataAfterMs(value: Int, delayMs: Int) {
        fireNoUserdataAfterMsHandle.invokeExact(value, delayMs)
    }

    fun unregisterAndJoin() {
        unregisterAndJoinHandle.invokeExact()
    }

    fun roundtripUserdata(userdata: MemorySegment): Long =
        roundtripUserdataHandle.invokeExact(userdata) as Long

    fun activeNativeSlots(): Int = activeNativeSlotsHandle.invokeExact() as Int
}

class CallbackFfiJvmTest : FreeSpec({
    "callback survives allocation scope and registering function return".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val registeringFunctionReturned = AtomicInteger(0)
            val registration = scheduleJvmFfiAfterRegisteringFunctionReturns {
                if (registeringFunctionReturned.get() == 1) calls.incrementAndGet()
            }
            registeringFunctionReturned.set(1)
            try {
                JvmCallbackFixture.unregisterAndJoin()
            } finally {
                JvmCallbackFixture.unregisterAndJoin()
                registration.close()
            }
            calls.get() shouldBe 1
        }
    }

    "callback is delivered from a C-created thread".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val callingThread = Thread.currentThread()
            val callbackThread = AtomicReference<Thread?>()
            val calls = AtomicInteger(0)
            val registration = registerRouted(CallbackPolicy.ONCE) {
                calls.incrementAndGet()
                callbackThread.set(Thread.currentThread())
            }
            try {
                storeRouted(registration)
                JvmCallbackFixture.fireAfterMs(12, 1)
                JvmCallbackFixture.unregisterAndJoin()
            } finally {
                registration.close()
            }
            calls.get() shouldBe 1
            val deliveredThread = callbackThread.get()
            deliveredThread shouldNotBe null
            (deliveredThread === callingThread) shouldBe false
        }
    }

    "duplicate native invocation reaches ONCE at most once".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val registration = registerRouted(CallbackPolicy.ONCE) { calls.incrementAndGet() }
            try {
                storeRouted(registration)
                JvmCallbackFixture.fireTwice(13)
            } finally {
                registration.close()
            }
            calls.get() shouldBe 1
        }
    }

    "1,000 registrations fire shuffled to their matching lambdas".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val observed = Array(MANY_CALLBACK_COUNT) { AtomicInteger(0) }
            val deliveryOrder = ConcurrentLinkedQueue<Int>()
            val registrations = List(MANY_CALLBACK_COUNT) { index ->
                registerRouted(CallbackPolicy.ONCE) { value ->
                    deliveryOrder += value
                    if (value == index) observed[index].incrementAndGet()
                }
            }
            try {
                registrations.forEachIndexed { index, registration ->
                    JvmCallbackFixture.storeMany(
                        index,
                        registration.callback.handler,
                        requireNotNull(registration.userdata).handler,
                    )
                }
                JvmCallbackFixture.activeNativeSlots() shouldBe MANY_CALLBACK_COUNT
                JvmCallbackFixture.fireManyShuffled(MANY_CALLBACK_COUNT)
            } finally {
                registrations.forEach(CallbackRegistration<JvmFfiCallback>::close)
            }
            observed.map(AtomicInteger::get) shouldContainExactly List(MANY_CALLBACK_COUNT) { 1 }
            (deliveryOrder.toList() == List(MANY_CALLBACK_COUNT) { it }) shouldBe false
        }
    }

    "callback after close is ignored".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val registration = registerRouted(CallbackPolicy.REPEATING) { calls.incrementAndGet() }
            try {
                storeRouted(registration)
                registration.close()
                JvmCallbackFixture.fireNow(14)
            } finally {
                registration.close()
            }
            calls.get() shouldBe 0
        }
    }

    "repeated create deliver close loops restore native and runtime counts".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val runtimeBaseline = CallbackRuntime.activeRegistrationCountForTest()
            val nativeBaseline = JvmCallbackFixture.activeNativeSlots()
            repeat(100) { index ->
                val registration = registerRouted(CallbackPolicy.ONCE) {}
                try {
                    storeRouted(registration)
                    JvmCallbackFixture.fireNow(index)
                } finally {
                    registration.close()
                    JvmCallbackFixture.unregisterAndJoin()
                }
                CallbackRuntime.activeRegistrationCountForTest() shouldBe runtimeBaseline
                JvmCallbackFixture.activeNativeSlots() shouldBe nativeBaseline
            }
        }
    }

    "unknown and cross-type routing tokens never invoke application code".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val reported = ConcurrentLinkedQueue<Throwable>()
            val otherType = CallbackType<OtherJvmFfiCallback>("ffi-jvm-other", hasRoutingUserdata = true)
            val other = CallbackRuntime.register(
                type = otherType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.routedStub),
                policy = CallbackPolicy.REPEATING,
                callback = OtherJvmFfiCallback { calls.incrementAndGet() },
            )
            try {
                CallbackFallbackReporter.installForTest { reported += it }.use {
                    JvmCallbackFixture.store(
                        JvmFfiTrampolines.routedStub,
                        MemorySegment.NULL,
                        PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong()).handler,
                    )
                    JvmCallbackFixture.fireNow(15)
                    JvmCallbackFixture.store(
                        JvmFfiTrampolines.routedStub,
                        MemorySegment.NULL,
                        requireNotNull(other.userdata).handler,
                    )
                    JvmCallbackFixture.fireNow(16)
                }
            } finally {
                other.close()
            }
            calls.get() shouldBe 0
            reported.single().shouldBeInstanceOf<IllegalArgumentException>()
        }
    }

    "callback and error-handler exceptions stay contained on the C thread".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val callbackFailure = IllegalStateException("callback failure")
            val handlerFailure = IllegalArgumentException("handler failure")
            val reported = AtomicReference<Throwable?>()
            val registration = CallbackRuntime.register(
                type = JvmFfiTrampolines.routedType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.routedStub),
                policy = CallbackPolicy.ONCE,
                onError = CallbackExceptionHandler { throw handlerFailure },
                callback = JvmFfiCallback { throw callbackFailure },
            )
            try {
                CallbackFallbackReporter.installForTest { reported.set(it) }.use {
                    storeRouted(registration)
                    JvmCallbackFixture.fireAfterMs(17, 1)
                    JvmCallbackFixture.unregisterAndJoin()
                }
            } finally {
                registration.close()
            }
            val combined = reported.get().shouldBeInstanceOf<CallbackExceptionHandlerFailure>()
            combined.callbackFailure shouldBe callbackFailure
            combined.handlerFailure shouldBe handlerFailure
        }
    }

    "direct-call failure before publication leaves no runtime entry".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val baseline = CallbackRuntime.activeRegistrationCountForTest()
            val calls = AtomicInteger(0)
            val prepared = CallbackRuntime.prepare(
                type = JvmFfiTrampolines.routedType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.routedStub),
                policy = CallbackPolicy.REPEATING,
                callback = JvmFfiCallback { calls.incrementAndGet() },
            )
            shouldThrow<IllegalStateException> {
                try {
                    JvmCallbackFixture.store(
                        prepared.callback.handler,
                        MemorySegment.NULL,
                        requireNotNull(prepared.userdata).handler,
                    )
                    throw IllegalStateException("direct downcall failed")
                } finally {
                    prepared.close()
                }
            }
            JvmCallbackFixture.fireNow(18)
            calls.get() shouldBe 0
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
        }
    }

    "throw after C stores callback closes token-backed entry".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val prepared = CallbackRuntime.prepare(
                type = JvmFfiTrampolines.routedType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.routedStub),
                policy = CallbackPolicy.REPEATING,
                callback = JvmFfiCallback { calls.incrementAndGet() },
            )
            try {
                shouldThrow<IllegalStateException> {
                    CallbackRuntime.activateForNativeCall(prepared) { registration ->
                        storeRouted(registration)
                        throw IllegalStateException("failure after native publication")
                    }
                }
                JvmCallbackFixture.fireNow(19)
                calls.get() shouldBe 0
            } finally {
                prepared.close()
            }
        }
    }

    "no-userdata normal re-registration fails after retirement".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val calls = AtomicInteger(0)
            val first = CallbackRuntime.register(
                type = JvmFfiTrampolines.retiredNoUserdataType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.retiredNoUserdataStub),
                policy = CallbackPolicy.ONCE,
                callback = JvmFfiCallback { calls.incrementAndGet() },
            )
            try {
                JvmCallbackFixture.storeNoUserdata(JvmFfiTrampolines.retiredNoUserdataStub)
                JvmCallbackFixture.fireNoUserdataAfterMs(20, 0)
                JvmCallbackFixture.unregisterAndJoin()
            } finally {
                first.close()
            }
            shouldThrow<IllegalStateException> {
                CallbackRuntime.register(
                    type = JvmFfiTrampolines.retiredNoUserdataType,
                    trampoline = JvmNativeAddress(JvmFfiTrampolines.retiredNoUserdataStub),
                    policy = CallbackPolicy.ONCE,
                    callback = JvmFfiCallback {},
                )
            }
            calls.get() shouldBe 1
        }
    }

    "native quiescence permits rearm and only new calls reach the new lambda".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val oldCalls = AtomicInteger(0)
            val newCalls = AtomicInteger(0)
            val first = CallbackRuntime.register(
                type = JvmFfiTrampolines.rearmedNoUserdataType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.rearmedNoUserdataStub),
                policy = CallbackPolicy.REPEATING,
                callback = JvmFfiCallback { oldCalls.incrementAndGet() },
            )
            try {
                JvmCallbackFixture.storeNoUserdata(JvmFfiTrampolines.rearmedNoUserdataStub)
                JvmCallbackFixture.fireNoUserdataAfterMs(21, 1)
                JvmCallbackFixture.unregisterAndJoin()
            } finally {
                JvmCallbackFixture.unregisterAndJoin()
                first.close()
            }

            val second = CallbackRuntime.rearmAfterNativeQuiescence(
                type = JvmFfiTrampolines.rearmedNoUserdataType,
                trampoline = JvmNativeAddress(JvmFfiTrampolines.rearmedNoUserdataStub),
                policy = CallbackPolicy.ONCE,
                callback = JvmFfiCallback { newCalls.incrementAndGet() },
            )
            try {
                JvmCallbackFixture.storeNoUserdata(JvmFfiTrampolines.rearmedNoUserdataStub)
                JvmCallbackFixture.fireNoUserdataAfterMs(22, 0)
                JvmCallbackFixture.unregisterAndJoin()
            } finally {
                second.close()
            }

            oldCalls.get() shouldBe 1
            newCalls.get() shouldBe 1
        }
    }

    "userdata roundtrip preserves exact token bits".config(timeout = 10.seconds) {
        withJvmFfiBaselines {
            val registration = registerRouted(CallbackPolicy.REPEATING) {}
            try {
                val userdata = requireNotNull(registration.userdata)
                JvmCallbackFixture.roundtripUserdata(userdata.handler).toULong() shouldBe
                    PlatformCallbackTokenAddressCodec.decode(userdata)
            } finally {
                registration.close()
            }
        }
    }
})

private fun registerRouted(
    policy: CallbackPolicy,
    callback: (Int) -> Unit,
): CallbackRegistration<JvmFfiCallback> = CallbackRuntime.register(
    type = JvmFfiTrampolines.routedType,
    trampoline = JvmNativeAddress(JvmFfiTrampolines.routedStub),
    policy = policy,
    callback = JvmFfiCallback(callback),
)

private fun scheduleJvmFfiAfterRegisteringFunctionReturns(
    callback: (Int) -> Unit,
): CallbackRegistration<JvmFfiCallback> {
    val registration = registerRouted(CallbackPolicy.ONCE, callback)
    try {
        Arena.ofConfined().use { scope ->
            JvmCallbackFixture.store(
                registration.callback.handler,
                scope.allocate(1),
                requireNotNull(registration.userdata).handler,
            )
            JvmCallbackFixture.fireAfterMs(11, 10)
        }
        return registration
    } catch (failure: Throwable) {
        registration.close()
        throw failure
    }
}

private fun storeRouted(registration: CallbackRegistration<JvmFfiCallback>) {
    JvmCallbackFixture.store(
        registration.callback.handler,
        MemorySegment.NULL,
        requireNotNull(registration.userdata).handler,
    )
}

private inline fun withJvmFfiBaselines(test: () -> Unit) {
    JvmCallbackFixture.unregisterAndJoin()
    val runtimeBaseline = CallbackRuntime.activeRegistrationCountForTest()
    val nativeBaseline = JvmCallbackFixture.activeNativeSlots()
    try {
        test()
    } finally {
        JvmCallbackFixture.unregisterAndJoin()
        CallbackRuntime.activeRegistrationCountForTest() shouldBe runtimeBaseline
        JvmCallbackFixture.activeNativeSlots() shouldBe nativeBaseline
    }
}
