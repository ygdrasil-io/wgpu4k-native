package org.graphiks.kffi.benchmark.jvm

import com.google.gson.JsonParser
import java.io.File
import java.util.Locale

fun main(args: Array<String>) {
    val jsonPath = args.getOrNull(0) ?: error("usage: <jmh-results.json> [output.md]")
    val outPath = args.getOrNull(1) ?: error("usage: <jmh-results.json> [output.md]")
    val root = JsonParser.parseString(File(jsonPath).readText()).asJsonArray
    val lines = mutableListOf("# kffi JVM benchmark report (JMH)")
    lines.add("")
    lines.add("| Benchmark | Score (ns/op) | Error (±) |")
    lines.add("|---|---|---|")
    root.forEach { bench ->
        val benchName = bench.asJsonObject["benchmark"].asString
        val primary = bench.asJsonObject["primaryMetric"].asJsonObject
        val score = primary["score"].asDouble
        val err = primary["scoreError"].asDouble
        lines.add("| $benchName | ${String.format(Locale.ROOT, "%.2f", score)} | ${String.format(Locale.ROOT, "%.2f", err)} |")
    }
    File(outPath).parentFile.mkdirs()
    File(outPath).writeText(lines.joinToString("\n") + "\n")
}
