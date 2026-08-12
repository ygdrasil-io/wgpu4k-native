package org.graphiks.kffi.benchmark.jvm

import com.google.gson.JsonParser
import java.io.File
import java.time.LocalDate
import java.util.Locale

private val benchmarkToScenario: Map<String, String> = mapOf(
    "DowncallBenchmarks.empty" to "downcall.empty",
    "DowncallBenchmarks.add4" to "downcall.add4",
    "DowncallBenchmarks.add8" to "downcall.add8",
    "DowncallBenchmarks.pairSum" to "downcall.struct_by_value_arg",
    "DowncallBenchmarks.makePair" to "downcall.struct_by_value_return",
    "UpcallBenchmarks.upcallFireOne" to "upcall.fire_one",
    "UpcallBenchmarks.upcallFire1000" to "upcall.fire_1000",
    "UpcallBenchmarks.upcallFireOneNoRouting" to "upcall.fire_one_no_routing",
    "UpcallBenchmarks.upcallFire1000NoRouting" to "upcall.fire_1000_no_routing",
    "MarshalingBenchmarks.writeReadIntScalar" to "marshaling.scalar_i32",
    "MarshalingBenchmarks.writeReadLongScalar" to "marshaling.scalar_i64",
    "MarshalingBenchmarks.copyInts16" to "marshaling.array_i32_16",
    "MarshalingBenchmarks.copyInts1024" to "marshaling.array_i32_1024",
    "MarshalingBenchmarks.copyDoubles1024" to "marshaling.array_f64_1024",
    "MarshalingBenchmarks.baselineByteArrayCopy" to "marshaling.baseline_copy",
    "ArenaBenchmarks.memoryScopeTenAllocs" to "arena.scope_10",
    "ArenaBenchmarks.memoryScopeHundredAllocs" to "arena.scope_100",
)

fun main(args: Array<String>) {
    val jsonPath = args.getOrNull(0) ?: error("usage: <jmh-results.json> <commit-sha>")
    val commit = args.getOrNull(1) ?: error("usage: <jmh-results.json> <commit-sha>")
    val root = JsonParser.parseString(File(jsonPath).readText()).asJsonArray
    val date = LocalDate.now().toString()
    val outBase = File("kffi/benchmarks/results/$date-$commit")
    File(jsonPath).copyTo(File("${outBase.path}-jvm-baseline.json"), overwrite = true)

    val lines = mutableListOf("# kffi JVM benchmark report (JMH)")
    lines.add("")
    lines.add("Date: $date")
    lines.add("Commit: $commit")
    lines.add("")
    lines.add("| Scenario | ns/op |")
    lines.add("|---|---|")
    root.forEach { bench ->
        val benchName = bench.asJsonObject["benchmark"].asString
        val simple = benchName.substringAfterLast('.')
        val enclosing = benchName.substringAfter("org.graphiks.kffi.benchmark.jvm.").substringBeforeLast('.')
        val scenario = benchmarkToScenario["$enclosing.$simple"] ?: benchName
        val score = bench.asJsonObject["primaryMetric"].asJsonObject["score"].asDouble
        lines.add("| $scenario | ${String.format(Locale.ROOT, "%.2f", score)} |")
    }
    File("${outBase.path}-jvm-baseline.md").parentFile.mkdirs()
    File("${outBase.path}-jvm-baseline.md").writeText(lines.joinToString("\n") + "\n")
    println("wrote ${outBase.path}-jvm-baseline.{md,json}")
}
