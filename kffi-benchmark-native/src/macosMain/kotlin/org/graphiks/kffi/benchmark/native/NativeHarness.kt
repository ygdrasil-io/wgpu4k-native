@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class, kotlin.experimental.ExperimentalNativeApi::class)

package org.graphiks.kffi.benchmark.native

import benchFixture.bench_empty
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.graphiks.kffi.benchmark.BenchmarkScenario
import kotlin.native.CpuArchitecture
import kotlin.native.Platform
import kotlin.time.measureTime

private val backendLabel: String =
    if (Platform.cpuArchitecture == CpuArchitecture.ARM64) "native-macosArm64" else "native-macosX64"

private fun measureDowncallEmpty(): Double {
    repeat(1000) { bench_empty() } // warmup
    val elapsed = measureTime {
        repeat(100_000) { bench_empty() }
    }
    return elapsed.inWholeNanoseconds / 100_000.0
}

private fun measureMarshaling(): Double {
    repeat(100) {
        org.graphiks.kffi.memoryScope { allocator ->
            allocator.allocateBuffer(4096uL)
        }
    } // warmup
    val elapsed = measureTime {
        repeat(10_000) {
            org.graphiks.kffi.memoryScope { allocator ->
                val buffer = allocator.allocateBuffer(4096uL)
                val values = IntArray(16) { it }
                buffer.writeInts(values)
            }
        }
    }
    return elapsed.inWholeNanoseconds / 10_000.0
}

fun main() {
    val results = listOf(
        BenchmarkResult(BenchmarkScenario.DOWN_EMPTY.axis, BenchmarkScenario.DOWN_EMPTY.id, backendLabel, measureDowncallEmpty()),
        BenchmarkResult(BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis, BenchmarkScenario.MARSHAL_ARRAY_I32_16.id, backendLabel, measureMarshaling()),
    )
    println(BenchmarkReport.toMarkdown(backendLabel, results))
}
