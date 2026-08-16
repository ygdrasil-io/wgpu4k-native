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

// NOTE P3 : l'overhead des bornes-check native est mesuré par l'écart entre ce
// scénario (borné, KFFI_NATIVE_UNSAFE=false) et la variante unsafe (compiler le
// module avec la constante à true). Le rapport P3 documente les deux mesures.
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

/*
 * Variante unsafe (KFFI_NATIVE_UNSAFE=true) — structure documentée, non
 * compilée par défaut : la constante build-time vit dans le module kffi
 * (MemoryBuffer.native.kt) et ne peut pas être basculée à l'exécution (I3/P3).
 * Pour mesurer l'overhead des bornes-check : compiler kffi avec
 * KFFI_NATIVE_UNSAFE=true puis exécuter CE scénario — le code est identique à
 * measureMarshaling, seule la constante compile-time change. Non branchée dans
 * main() : la build par défaut doit rester bornée.
 *
 * private fun measureMarshalingUnsafeVariant(): Double {
 *     repeat(100) {
 *         org.graphiks.kffi.memoryScope { allocator ->
 *             allocator.allocateBuffer(4096uL)
 *         }
 *     } // warmup
 *     val elapsed = measureTime {
 *         repeat(10_000) {
 *             org.graphiks.kffi.memoryScope { allocator ->
 *                 val buffer = allocator.allocateBuffer(4096uL)
 *                 val values = IntArray(16) { it }
 *                 buffer.writeInts(values)
 *             }
 *         }
 *     }
 *     return elapsed.inWholeNanoseconds / 10_000.0
 * }
 */

fun main() {
    val results = listOf(
        BenchmarkResult(BenchmarkScenario.DOWN_EMPTY.axis, BenchmarkScenario.DOWN_EMPTY.id, backendLabel, measureDowncallEmpty()),
        BenchmarkResult(BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis, BenchmarkScenario.MARSHAL_ARRAY_I32_16.id, backendLabel, measureMarshaling()),
    )
    println(BenchmarkReport.toMarkdown(backendLabel, results))
}
