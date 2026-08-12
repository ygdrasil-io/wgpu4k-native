@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.ygdrasil.kffi.memoryScope
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.graphiks.kffi.benchmark.BenchmarkScenario
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureNanoTime

@RunWith(AndroidJUnit4::class)
class AndroidHarness {

    @Test
    fun emitMarshalingBaseline() {
        val results = mutableListOf<BenchmarkResult>()
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
        results += BenchmarkResult(
            BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis,
            BenchmarkScenario.MARSHAL_ARRAY_I32_16.id,
            "android-device",
            ns / 1000.0,
        )
        println(BenchmarkReport.toMarkdown("android-device", results))
    }
}
