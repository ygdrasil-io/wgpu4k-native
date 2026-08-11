@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class, kotlin.experimental.ExperimentalNativeApi::class)

package org.graphiks.kffi.benchmark.native

import benchFixture.bench_empty
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
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
        io.ygdrasil.kffi.memoryScope { allocator ->
            allocator.allocateBuffer(4096uL)
        }
    } // warmup
    val elapsed = measureTime {
        repeat(10_000) {
            io.ygdrasil.kffi.memoryScope { allocator ->
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
        BenchmarkResult(BenchmarkAxis.DOWNCALL, "empty", backendLabel, measureDowncallEmpty()),
        BenchmarkResult(BenchmarkAxis.MARSHALING, "memoryScope_writeInts16", backendLabel, measureMarshaling()),
    )
    println(BenchmarkReport.toMarkdown(backendLabel, results))
}
