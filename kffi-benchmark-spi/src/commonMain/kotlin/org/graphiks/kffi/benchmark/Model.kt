package org.graphiks.kffi.benchmark

import kotlin.math.roundToLong

enum class BenchmarkAxis {
    DOWNCALL,
    UPCALL,
    MARSHALING,
    ARENA,
}

data class BenchmarkResult(
    val axis: BenchmarkAxis,
    val scenario: String,
    val backend: String,
    val nsPerOp: Double,
)

object BenchmarkReport {

    fun toMarkdown(backend: String, results: List<BenchmarkResult>): String = buildString {
        appendLine("# kffi benchmark report — backend: $backend")
        appendLine()
        appendLine("| Axis | Scenario | ns/op |")
        appendLine("|---|---|---|")
        results
            .sortedWith(compareBy<BenchmarkResult> { it.axis.ordinal }.thenBy { it.scenario })
            .forEach { result ->
                val ns = (result.nsPerOp * 100).roundToLong().let {
                    "${it / 100}.${(it % 100).toString().padStart(2, '0')}"
                }
                appendLine("| ${result.axis} | ${result.scenario} | $ns |")
            }
    }
}
