@file:OptIn(
    CallbackRuntimeApi::class,
    UnsafeCallbackRearmApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
)

package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicReference
import kotlin.native.concurrent.Future
import kotlin.native.concurrent.FutureState
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

private const val NATIVE_THREAD_COUNT = 32

private fun interface NativeTestCallback : Callback {
    fun invoke()
}

private data class NativeDispatch(
    val type: CallbackType<NativeTestCallback>,
    val userdata: NativeAddress?,
)

private data class NativeGatedDispatch(
    val type: CallbackType<NativeTestCallback>,
    val userdata: NativeAddress?,
    val ready: AtomicInt,
    val start: AtomicInt,
)

class CallbackRuntimeNativeTest : FreeSpec({
    "concurrent ONCE delivery invokes once" {
        withNativeRegistryBaseline {
            val calls = AtomicInt(0)
            val ready = AtomicInt(0)
            val start = AtomicInt(0)
            val type = CallbackType<NativeTestCallback>("native-once", hasRoutingUserdata = true)
            val registration = nativeRegister(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            val workers = List(NATIVE_THREAD_COUNT) { Worker.start() }
            try {
                val futures = workers.map { worker ->
                    worker.execute(
                        TransferMode.SAFE,
                        { NativeGatedDispatch(type, registration.userdata, ready, start) },
                    ) { dispatch ->
                        dispatch.ready.fetchAndAdd(1)
                        while (dispatch.start.load() == 0) {
                            // All 32 Workers start dispatching from the same gate.
                        }
                        CallbackRuntime.dispatchSafely(dispatch.type, dispatch.userdata) { it.invoke() }
                    }
                }
                awaitCondition { ready.load() == NATIVE_THREAD_COUNT }
                start.store(1)
                futures.forEach { it.awaitWithinTimeout() }
            } finally {
                start.store(1)
                registration.close()
                workers.terminateWithinTimeout()
            }

            calls.load() shouldBe 1
            registration.isClosed shouldBe true
        }
    }

    "REPEATING close rejects new work and permits in-flight completion" {
        withNativeRegistryBaseline {
            val entered = AtomicInt(0)
            val release = AtomicInt(0)
            val finished = AtomicInt(0)
            val calls = AtomicInt(0)
            val type = CallbackType<NativeTestCallback>("native-repeating", hasRoutingUserdata = true)
            val registration = nativeRegister(type, CallbackPolicy.REPEATING) {
                if (calls.fetchAndAdd(1) == 0) {
                    entered.store(1)
                    while (release.load() == 0) {
                        // The main test thread releases this bounded in-flight delivery.
                    }
                    finished.store(1)
                }
            }
            val worker = Worker.start()
            try {
                val future = worker.execute(
                    TransferMode.SAFE,
                    { NativeDispatch(type, registration.userdata) },
                ) { dispatch ->
                    CallbackRuntime.dispatchSafely(dispatch.type, dispatch.userdata) { it.invoke() }
                }
                awaitCondition { entered.load() == 1 }
                registration.close()
                CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                release.store(1)
                future.awaitWithinTimeout()
            } finally {
                release.store(1)
                registration.close()
                listOf(worker).terminateWithinTimeout()
            }

            finished.load() shouldBe 1
            calls.load() shouldBe 1
        }
    }

    "no-userdata completion retires until unsafe re-arm" {
        withNativeRegistryBaseline {
            val calls = AtomicInt(0)
            val type = CallbackType<NativeTestCallback>("native-no-userdata", hasRoutingUserdata = false)
            val first = nativeRegister(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            CallbackRuntime.dispatchSafely(type, null) { it.invoke() }
            runCatching {
                nativeRegister(type, CallbackPolicy.ONCE) { calls.fetchAndAdd(1) }
            }.isFailure shouldBe true

            val second = CallbackRuntime.rearmAfterNativeQuiescence(
                type = type,
                trampoline = nativeTrampoline,
                policy = CallbackPolicy.ONCE,
                callback = NativeTestCallback { calls.fetchAndAdd(1) },
            )
            CallbackRuntime.dispatchSafely(type, null) { it.invoke() }

            calls.load() shouldBe 2
            first.isClosed shouldBe true
            second.isClosed shouldBe true
        }
    }

    "callback and handler failures remain contained" {
        withNativeRegistryBaseline {
            val callbackFailure = IllegalStateException("native callback")
            val handlerFailure = IllegalArgumentException("native handler")
            val reported = AtomicReference<Throwable?>(null)
            val type = CallbackType<NativeTestCallback>("native-failure", hasRoutingUserdata = true)
            CallbackFallbackReporter.installForTest { reported.store(it) }.use {
                val registration = CallbackRuntime.register(
                    type = type,
                    trampoline = nativeTrampoline,
                    policy = CallbackPolicy.ONCE,
                    onError = CallbackExceptionHandler { throw handlerFailure },
                    callback = NativeTestCallback { throw callbackFailure },
                )
                CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
            }

            val combined = reported.load().shouldBeInstanceOf<CallbackExceptionHandlerFailure>()
            combined.callbackFailure shouldBe callbackFailure
            combined.handlerFailure shouldBe handlerFailure
        }
    }
})

private val nativeTrampoline: NativeAddress = PlatformCallbackTokenAddressCodec.encode(1uL)

private fun nativeRegister(
    type: CallbackType<NativeTestCallback>,
    policy: CallbackPolicy,
    callback: NativeTestCallback,
): CallbackRegistration<NativeTestCallback> = CallbackRuntime.register(
    type = type,
    trampoline = nativeTrampoline,
    policy = policy,
    callback = callback,
)

private inline fun withNativeRegistryBaseline(test: () -> Unit) {
    val baseline = CallbackRuntime.activeRegistrationCountForTest()
    try {
        test()
    } finally {
        CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
    }
}

private fun awaitCondition(condition: () -> Boolean) {
    val started = TimeSource.Monotonic.markNow()
    while (!condition()) {
        check(started.elapsedNow() < 10.seconds) { "Condition did not complete within 10 seconds" }
        Worker.current.park(1_000, process = false)
    }
}

private fun <T> Future<T>.awaitWithinTimeout(): T {
    val started = TimeSource.Monotonic.markNow()
    while (state == FutureState.SCHEDULED) {
        check(started.elapsedNow() < 10.seconds) { "Worker future did not complete within 10 seconds" }
        Worker.current.park(1_000, process = false)
    }
    return result
}

private fun List<Worker>.terminateWithinTimeout() {
    map { it.requestTermination(processScheduledJobs = false) }
        .forEach { it.awaitWithinTimeout() }
}
