@file:OptIn(
    CallbackRuntimeApi::class,
    UnsafeCallbackRearmApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.lang.foreign.MemorySegment
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicReference

private const val THREAD_COUNT = 32
private const val TIMEOUT_SECONDS = 10L

private fun interface JvmTestCallback : Callback {
    fun invoke()
}

private fun interface OtherJvmTestCallback : Callback {
    fun invoke()
}

class CallbackRuntimeJvmTest : FreeSpec({
    "10,000 callback tokens allocated by 32 threads are non-zero unique and never reused" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("concurrent-tokens", hasRoutingUserdata = true)
            val tokens = ConcurrentLinkedQueue<ULong>()
            val executor = Executors.newFixedThreadPool(THREAD_COUNT)
            val barrier = CyclicBarrier(THREAD_COUNT + 1)
            try {
                val futures = List(THREAD_COUNT) { threadIndex ->
                    executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        val registrationCount = 10_000 / THREAD_COUNT +
                            if (threadIndex < 10_000 % THREAD_COUNT) 1 else 0
                        repeat(registrationCount) {
                            val registration = register(type, CallbackPolicy.REPEATING) {}
                            try {
                                tokens.add(requireNotNull(PlatformTokenCodec.decode(registration.userdata)))
                            } finally {
                                registration.close()
                            }
                        }
                    }
                }
                barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                futures.forEach { it.get(TIMEOUT_SECONDS, TimeUnit.SECONDS) }
            } finally {
                shutdown(executor)
            }

            tokens shouldHaveSize 10_000
            tokens.all { it > 0uL } shouldBe true
            val seen = tokens.toHashSet()
            seen shouldHaveSize 10_000
        }
    }

    "32 simultaneous ONCE deliveries invoke once" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("once-concurrent", hasRoutingUserdata = true)
            val calls = AtomicInt(0)
            val registration = register(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            val executor = Executors.newFixedThreadPool(THREAD_COUNT)
            val barrier = CyclicBarrier(THREAD_COUNT + 1)
            try {
                val futures = List(THREAD_COUNT) {
                    executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                    }
                }
                barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                futures.forEach { it.get(TIMEOUT_SECONDS, TimeUnit.SECONDS) }
            } finally {
                registration.close()
                shutdown(executor)
            }
            calls.load() shouldBe 1
            registration.isClosed shouldBe true
        }
    }

    "close racing ONCE delivery invokes zero or one times" {
        withRegistryBaseline {
            repeat(100) { index ->
                val type = CallbackType<JvmTestCallback>("once-close-race-$index", hasRoutingUserdata = true)
                val calls = AtomicInt(0)
                val registration = register(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
                val executor = Executors.newFixedThreadPool(2)
                val barrier = CyclicBarrier(3)
                try {
                    val close = executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        registration.close()
                    }
                    val deliver = executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                    }
                    barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    close.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    deliver.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                } finally {
                    registration.close()
                    shutdown(executor)
                }
                (calls.load() in 0..1) shouldBe true
                registration.isClosed shouldBe true
            }
        }
    }

    "REPEATING rejects calls after close and lets in-flight work finish" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("repeating-close", hasRoutingUserdata = true)
            val calls = AtomicInt(0)
            val entered = CountDownLatch(1)
            val release = CountDownLatch(1)
            val registration = register(type, CallbackPolicy.REPEATING) {
                calls.fetchAndAdd(1)
                entered.countDown()
                check(release.await(TIMEOUT_SECONDS, TimeUnit.SECONDS))
            }
            val executor = Executors.newSingleThreadExecutor()
            try {
                val inFlight = executor.submit {
                    CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                }
                check(entered.await(TIMEOUT_SECONDS, TimeUnit.SECONDS))
                registration.close()
                CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                calls.load() shouldBe 1
                release.countDown()
                inFlight.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            } finally {
                release.countDown()
                registration.close()
                shutdown(executor)
            }
            calls.load() shouldBe 1
        }
    }

    "callback failures reach the registration error handler" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("callback-failure", hasRoutingUserdata = true)
            val failure = IllegalStateException("callback")
            var observed: Throwable? = null
            val registration = register(
                type = type,
                policy = CallbackPolicy.ONCE,
                onError = CallbackExceptionHandler { observed = it },
            ) { throw failure }

            CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }

            observed shouldBe failure
            registration.isClosed shouldBe true
        }
    }

    "a throwing error handler sends the combined failure to fallback" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("handler-failure", hasRoutingUserdata = true)
            val callbackFailure = IllegalStateException("callback")
            val handlerFailure = IllegalArgumentException("handler")
            var reported: Throwable? = null
            CallbackFallbackReporter.installForTest { reported = it }.use {
                val registration = register(
                    type = type,
                    policy = CallbackPolicy.ONCE,
                    onError = CallbackExceptionHandler { throw handlerFailure },
                ) { throw callbackFailure }
                CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
            }

            val combined = reported.shouldBeInstanceOf<CallbackExceptionHandlerFailure>()
            combined.callbackFailure shouldBe callbackFailure
            combined.handlerFailure shouldBe handlerFailure
        }
    }

    "unknown null and malformed tokens are contained" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("routing-a", hasRoutingUserdata = true)
            val calls = AtomicInt(0)
            val reported = mutableListOf<Throwable>()
            CallbackFallbackReporter.installForTest { reported += it }.use {
                val registration = register(type, CallbackPolicy.REPEATING) { calls.fetchAndAdd(1) }
                try {
                    CallbackRuntime.dispatchSafely(type, PlatformTokenCodec.encode(Long.MAX_VALUE.toULong())) { it.invoke() }
                    CallbackRuntime.dispatchSafely(type, null) { it.invoke() }
                    CallbackRuntime.dispatchSafely(
                        type,
                        JvmNativeAddress(MemorySegment.ofAddress(0)),
                    ) { it.invoke() }
                    CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                } finally {
                    registration.close()
                }
            }

            calls.load() shouldBe 1
            reported shouldHaveSize 2
            reported.all { it is IllegalArgumentException } shouldBe true
        }
    }

    "cross-type token with the same canonical ID is rejected after generic erasure" {
        withRegistryBaseline {
            val canonicalId = "same-canonical-id"
            val firstType = CallbackType<JvmTestCallback>(canonicalId, hasRoutingUserdata = true)
            val secondType = CallbackType<OtherJvmTestCallback>(canonicalId, hasRoutingUserdata = true)
            val firstCalls = AtomicInt(0)
            val secondCalls = AtomicInt(0)
            val reported = mutableListOf<Throwable>()
            val registration = register(firstType, CallbackPolicy.REPEATING) { firstCalls.fetchAndAdd(1) }
            try {
                CallbackFallbackReporter.installForTest { reported += it }.use {
                    CallbackRuntime.dispatchSafely(secondType, registration.userdata) {
                        secondCalls.fetchAndAdd(1)
                        it.invoke()
                    }
                }
            } finally {
                registration.close()
            }

            firstCalls.load() shouldBe 0
            secondCalls.load() shouldBe 0
            reported shouldHaveSize 1
            reported.single().shouldBeInstanceOf<IllegalArgumentException>()
        }
    }

    "closing inside a REPEATING callback does not deadlock" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("self-close", hasRoutingUserdata = true)
            lateinit var registration: CallbackRegistration<JvmTestCallback>
            registration = register(type, CallbackPolicy.REPEATING) { registration.close() }
            val executor = Executors.newSingleThreadExecutor()
            try {
                executor.submit {
                    CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                }.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            } finally {
                registration.close()
                shutdown(executor)
            }
            registration.isClosed shouldBe true
        }
    }

    "no-userdata registrations retire and re-arm only through unsafe API" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("no-userdata", hasRoutingUserdata = false)
            val calls = AtomicInt(0)
            val first = register(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            first.userdata shouldBe null
            shouldThrow<IllegalStateException> {
                register(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            }
            CallbackRuntime.dispatchSafely(type, null) { it.invoke() }
            shouldThrow<IllegalStateException> {
                register(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            }

            val second = CallbackRuntime.rearmAfterNativeQuiescence(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.ONCE,
                callback = JvmTestCallback { calls.fetchAndAdd(1) },
            )
            CallbackRuntime.dispatchSafely(type, null) { it.invoke() }

            calls.load() shouldBe 2
            second.isClosed shouldBe true
        }
    }

    "prepared publication supports reentrant delivery and rollback" {
        withRegistryBaseline {
            val type = CallbackType<JvmTestCallback>("prepared", hasRoutingUserdata = true)
            val calls = AtomicInt(0)
            val prepared = CallbackRuntime.prepare(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.REPEATING,
                callback = JvmTestCallback { calls.fetchAndAdd(1) },
            )
            val registration = CallbackRuntime.activateForNativeCall(prepared) { active ->
                CallbackRuntime.dispatchSafely(type, active.userdata) { it.invoke() }
            }
            calls.load() shouldBe 1
            registration.close()

            val failed = CallbackRuntime.prepare(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.REPEATING,
                callback = JvmTestCallback { calls.fetchAndAdd(1) },
            )
            shouldThrow<IllegalStateException> {
                CallbackRuntime.activateForNativeCall(failed) { active ->
                    CallbackRuntime.dispatchSafely(type, active.userdata) { it.invoke() }
                    throw IllegalStateException("downcall")
                }
            }
            CallbackRuntime.dispatchSafely(type, failed.userdata) { it.invoke() }
            calls.load() shouldBe 2
        }
    }

    "prepared token is unroutable before activation and after abort" {
        withRegistryBaseline {
            val baseline = CallbackRuntime.activeRegistrationCountForTest()
            val calls = AtomicInt(0)
            val type = CallbackType<JvmTestCallback>("prepared-abort", hasRoutingUserdata = true)
            val prepared = CallbackRuntime.prepare(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.ONCE,
                callback = JvmTestCallback { calls.fetchAndAdd(1) },
            )

            CallbackRuntime.dispatchSafely(type, prepared.userdata) { it.invoke() }
            calls.load() shouldBe 0
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline

            prepared.close()
            prepared.isAborted shouldBe true
            CallbackRuntime.dispatchSafely(type, prepared.userdata) { it.invoke() }
            calls.load() shouldBe 0
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
        }
    }

    "prepared close racing activation has exactly one winner" {
        withRegistryBaseline {
            val executor = Executors.newFixedThreadPool(2)
            try {
                repeat(100) { index ->
                    val type = CallbackType<JvmTestCallback>(
                        "prepared-race-$index",
                        hasRoutingUserdata = true,
                    )
                    val downcalls = AtomicInt(0)
                    val prepared = CallbackRuntime.prepare(
                        type = type,
                        trampoline = trampoline,
                        policy = CallbackPolicy.REPEATING,
                        callback = JvmTestCallback {},
                    )
                    val activated = AtomicReference<CallbackRegistration<JvmTestCallback>?>(null)
                    val activationFailure = AtomicReference<Throwable?>(null)
                    val barrier = CyclicBarrier(3)
                    val closeFuture = executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        prepared.close()
                    }
                    val activateFuture = executor.submit {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        try {
                            activated.store(
                                CallbackRuntime.activateForNativeCall(prepared) {
                                    downcalls.fetchAndAdd(1)
                                },
                            )
                        } catch (failure: Throwable) {
                            activationFailure.store(failure)
                        }
                    }
                    try {
                        barrier.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        closeFuture.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        activateFuture.get(TIMEOUT_SECONDS, TimeUnit.SECONDS)

                        val registration = activated.load()
                        ((registration != null) xor prepared.isAborted) shouldBe true
                        if (registration == null) {
                            activationFailure.load().shouldBeInstanceOf<IllegalStateException>()
                            downcalls.load() shouldBe 0
                        } else {
                            activationFailure.load() shouldBe null
                            downcalls.load() shouldBe 1
                        }
                    } finally {
                        activated.load()?.close()
                        prepared.close()
                    }
                }
            } finally {
                shutdown(executor)
            }
        }
    }

    "no-userdata prepared conflict never calls downcall or leaks" {
        withRegistryBaseline {
            val baseline = CallbackRuntime.activeRegistrationCountForTest()
            val type = CallbackType<JvmTestCallback>("prepared-conflict", hasRoutingUserdata = false)
            val active = register(type, CallbackPolicy.REPEATING) {}
            val prepared = CallbackRuntime.prepare(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.REPEATING,
                callback = JvmTestCallback {},
            )
            val downcalls = AtomicInt(0)
            try {
                shouldThrow<IllegalStateException> {
                    CallbackRuntime.activateForNativeCall(prepared) {
                        downcalls.fetchAndAdd(1)
                    }
                }
                downcalls.load() shouldBe 0
                CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline + 1
            } finally {
                prepared.close()
                active.close()
            }
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
        }
    }

    "throwing no-userdata reentrant downcall retires the published slot" {
        withRegistryBaseline {
            val baseline = CallbackRuntime.activeRegistrationCountForTest()
            val calls = AtomicInt(0)
            val type = CallbackType<JvmTestCallback>("prepared-no-userdata", hasRoutingUserdata = false)
            val prepared = CallbackRuntime.prepare(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.REPEATING,
                callback = JvmTestCallback { calls.fetchAndAdd(1) },
            )
            val downcallFailure = IllegalStateException("no-userdata downcall")

            val observed = shouldThrow<IllegalStateException> {
                CallbackRuntime.activateForNativeCall(prepared) { active ->
                    active.userdata shouldBe null
                    CallbackRuntime.dispatchSafely(type, null) { it.invoke() }
                    throw downcallFailure
                }
            }

            observed shouldBe downcallFailure
            calls.load() shouldBe 1
            CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
            shouldThrow<IllegalStateException> {
                register(type, CallbackPolicy.REPEATING) {}
            }
            val rearmed = CallbackRuntime.rearmAfterNativeQuiescence(
                type = type,
                trampoline = trampoline,
                policy = CallbackPolicy.REPEATING,
                callback = JvmTestCallback {},
            )
            rearmed.close()
        }
    }
})

private val trampoline = JvmNativeAddress(MemorySegment.ofAddress(0xCAFE))

private fun register(
    type: CallbackType<JvmTestCallback>,
    policy: CallbackPolicy,
    onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
    callback: JvmTestCallback,
): CallbackRegistration<JvmTestCallback> = CallbackRuntime.register(
    type = type,
    trampoline = trampoline,
    policy = policy,
    onError = onError,
    callback = callback,
)

private inline fun withRegistryBaseline(test: () -> Unit) {
    val baseline = CallbackRuntime.activeRegistrationCountForTest()
    try {
        test()
    } finally {
        CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
    }
}

private fun shutdown(executor: ExecutorService) {
    executor.shutdownNow()
    check(executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
        "Executor did not terminate within $TIMEOUT_SECONDS seconds"
    }
}
