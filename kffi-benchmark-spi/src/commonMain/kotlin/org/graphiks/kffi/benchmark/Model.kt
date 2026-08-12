package org.graphiks.kffi.benchmark

import kotlin.math.roundToLong

enum class BenchmarkAxis {
    DOWNCALL,
    UPCALL,
    MARSHALING,
    ARENA,
}

enum class BenchmarkScenario(val axis: BenchmarkAxis, val id: String) {
    DOWN_EMPTY(BenchmarkAxis.DOWNCALL, "downcall.empty"),
    DOWN_ADD4(BenchmarkAxis.DOWNCALL, "downcall.add4"),
    DOWN_ADD8(BenchmarkAxis.DOWNCALL, "downcall.add8"),
    DOWN_STRUCT_ARG(BenchmarkAxis.DOWNCALL, "downcall.struct_by_value_arg"),
    DOWN_STRUCT_RETURN(BenchmarkAxis.DOWNCALL, "downcall.struct_by_value_return"),
    DOWN_ROUNDTRIP_PTR(BenchmarkAxis.DOWNCALL, "downcall.roundtrip_ptr"),
    UP_FIRE_ONE(BenchmarkAxis.UPCALL, "upcall.fire_one"),
    UP_FIRE_1000(BenchmarkAxis.UPCALL, "upcall.fire_1000"),
    UP_FIRE_ONE_NO_ROUTING(BenchmarkAxis.UPCALL, "upcall.fire_one_no_routing"),
    UP_FIRE_1000_NO_ROUTING(BenchmarkAxis.UPCALL, "upcall.fire_1000_no_routing"),
    MARSHAL_SCALAR_I32(BenchmarkAxis.MARSHALING, "marshaling.scalar_i32"),
    MARSHAL_SCALAR_I64(BenchmarkAxis.MARSHALING, "marshaling.scalar_i64"),
    MARSHAL_ARRAY_I32_16(BenchmarkAxis.MARSHALING, "marshaling.array_i32_16"),
    MARSHAL_ARRAY_I32_1024(BenchmarkAxis.MARSHALING, "marshaling.array_i32_1024"),
    MARSHAL_ARRAY_F64_1024(BenchmarkAxis.MARSHALING, "marshaling.array_f64_1024"),
    MARSHAL_BASELINE_COPY(BenchmarkAxis.MARSHALING, "marshaling.baseline_copy"),
    ARENA_SCOPE_10(BenchmarkAxis.ARENA, "arena.scope_10"),
    ARENA_SCOPE_100(BenchmarkAxis.ARENA, "arena.scope_100"),
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
