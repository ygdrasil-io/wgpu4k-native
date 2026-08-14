@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.graphiks.kffi.Callback
import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.CallbackRuntime
import org.graphiks.kffi.CallbackRuntimeApi
import org.graphiks.kffi.CallbackType
import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.engine.NativeEngine
import org.graphiks.kffi.engine.UpcallEngine
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.graphiks.kffi.benchmark.BenchmarkScenario
import org.graphiks.kffi.memoryScope
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureNanoTime

private const val BACKEND = "android-device"

/** Mirrors the kffi engine upcall dispatcher so the benchmark can route its own callback. */
@OptIn(CallbackRuntimeApi::class)
object BenchmarkUpcallDispatcher {

    fun interface BenchCallback : Callback {
        fun invoke(value: UInt)
    }

    private val type: CallbackType<BenchCallback> = CallbackType(
        canonicalId = "bench:BenchmarkUpcallDispatcher",
        hasRoutingUserdata = true,
    )

    @Volatile
    var lastValue: Int = -1

    fun register(): CallbackRegistration<BenchCallback> =
        CallbackRuntime.register(
            type = type,
            trampoline = NativeAddress(0L),
            policy = CallbackPolicy.REPEATING,
            callback = BenchCallback { lastValue = it.toInt() },
        )

    @JvmStatic
    fun dispatch(token: Long, value: Int) {
        CallbackRuntime.dispatchSafely(type, NativeAddress(token)) { cb ->
            cb.invoke(value.toUInt())
        }
    }
}

@RunWith(AndroidJUnit4::class)
class AndroidHarness {

    private val fixtureHandle: Long by lazy {
        val nativeLibraryDir = InstrumentationRegistry.getInstrumentation()
            .targetContext.applicationInfo.nativeLibraryDir
        val handle = NativeEngine.loadNativeLibrary("$nativeLibraryDir/libkffi_bench_fixture.so")
        check(handle != 0L) { "kffi: failed to dlopen libkffi_bench_fixture.so" }
        handle
    }

    private fun resolve(name: String): Long = NativeEngine.resolveSymbolIn(fixtureHandle, name)

    @Test
    fun emitAndroidDeviceBakeoff() {
        val results = mutableListOf<BenchmarkResult>()
        listOf(
            measure("marshaling.array_i32_16") { measureMarshaling() },
            measure("downcall.empty") { measureDowncallEmpty() },
            measure("downcall.add4") { measureDowncallAdd4() },
            measure("upcall.fire_one") { measureUpcallFireOne() },
        ).forEach { it?.let { result -> results += result } }
        println(BenchmarkReport.toMarkdown(BACKEND, results))
        println("RESULT_JSON=" + results.toJson())
    }

    private fun measure(scenario: String, block: () -> BenchmarkResult): BenchmarkResult? {
        return try {
            block()
        } catch (failure: Throwable) {
            println("RESULT_ERROR scenario=$scenario failure=${failure::class.simpleName}: ${failure.message}")
            null
        }
    }

    private fun measureMarshaling(): BenchmarkResult {
        repeat(100) {
            memoryScope { allocator ->
                val buffer = allocator.allocateBuffer(4096uL)
                val values = IntArray(16) { it }
                buffer.writeInts(values)
            }
        } // warmup with the measured workload
        val ns = measureNanoTime {
            repeat(1000) {
                memoryScope { allocator ->
                    val buffer = allocator.allocateBuffer(4096uL)
                    val values = IntArray(16) { it }
                    buffer.writeInts(values)
                }
            }
        }
        return BenchmarkResult(
            BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis,
            BenchmarkScenario.MARSHAL_ARRAY_I32_16.id,
            BACKEND,
            ns / 1000.0,
        )
    }

    private fun measureDowncallEmpty(): BenchmarkResult {
        val fn = resolve("bench_empty")
        repeat(1000) { NativeEngine.callI0(fn) } // warmup with the measured workload
        val ns = measureNanoTime {
            repeat(10_000) { NativeEngine.callI0(fn) }
        }
        return BenchmarkResult(
            BenchmarkScenario.DOWN_EMPTY.axis,
            BenchmarkScenario.DOWN_EMPTY.id,
            BACKEND,
            ns / 10_000.0,
        )
    }

    private fun measureDowncallAdd4(): BenchmarkResult {
        val fn = resolve("bench_add4")
        repeat(1000) { NativeEngine.callI4IIII(fn, 1, 2, 3, 4) } // warmup with the measured workload
        val ns = measureNanoTime {
            repeat(10_000) { NativeEngine.callI4IIII(fn, 1, 2, 3, 4) }
        }
        return BenchmarkResult(
            BenchmarkScenario.DOWN_ADD4.axis,
            BenchmarkScenario.DOWN_ADD4.id,
            BACKEND,
            ns / 10_000.0,
        )
    }

    private fun measureUpcallFireOne(): BenchmarkResult {
        val registration = BenchmarkUpcallDispatcher.register()
        try {
            val trampoline = UpcallEngine.allocateTrampoline(
                dispatcherClass = BenchmarkUpcallDispatcher::class.java,
                dispatchMethod = "dispatch",
                dispatchSig = "(JI)V",
            )
            check(trampoline != 0L) { "kffi: allocateTrampoline returned null trampoline" }
            val setFn = resolve("bench_set_callback")
            val fireFn = resolve("bench_fire_one")
            NativeEngine.callV2PP(setFn, trampoline, registration.userdata!!.rawValue)
            repeat(1000) { NativeEngine.callV1I(fireFn, 42) } // warmup with the measured workload
            check(BenchmarkUpcallDispatcher.lastValue == 42) { "kffi: callback did not deliver during warmup" }
            val ns = measureNanoTime {
                repeat(10_000) { NativeEngine.callV1I(fireFn, 42) }
            }
            return BenchmarkResult(
                BenchmarkScenario.UP_FIRE_ONE.axis,
                BenchmarkScenario.UP_FIRE_ONE.id,
                BACKEND,
                ns / 10_000.0,
            )
        } finally {
            BenchmarkUpcallDispatcher.lastValue = -1
            registration.close()
        }
    }

    private fun List<BenchmarkResult>.toJson(): String {
        val json = StringBuilder()
        json.append('[')
        for ((index, result) in withIndex()) {
            if (index > 0) json.append(',')
            json.append("{\"axis\":\"").append(result.axis).append("\",\"scenario\":\"")
                .append(result.scenario).append("\",\"backend\":\"").append(result.backend)
                .append("\",\"nsPerOp\":").append(result.nsPerOp).append('}')
        }
        json.append(']')
        return json.toString()
    }
}
