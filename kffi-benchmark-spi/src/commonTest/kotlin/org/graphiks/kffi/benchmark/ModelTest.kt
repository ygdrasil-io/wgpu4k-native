package org.graphiks.kffi.benchmark

import kotlin.test.Test
import kotlin.test.assertContains

class ModelTest {

    @Test
    fun emptyResultsProducesHeader() {
        val md = BenchmarkReport.toMarkdown("jvm", emptyList())
        assertContains(md, "jvm")
        assertContains(md, "| Axis | Scenario | ns/op |")
    }

    @Test
    fun nsPerOpFormattedWithTwoDecimals() {
        val results = listOf(BenchmarkResult(BenchmarkAxis.DOWNCALL, "empty", "jvm", 1.5))
        val md = BenchmarkReport.toMarkdown("jvm", results)
        assertContains(md, "1.50")
    }

    @Test
    fun resultsSortedByAxisThenScenario() {
        val results = listOf(
            BenchmarkResult(BenchmarkAxis.ARENA, "b", "jvm", 1.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "z", "jvm", 2.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "a", "jvm", 3.0),
        )
        val md = BenchmarkReport.toMarkdown("jvm", results)
        val downcallA = md.indexOf("| DOWNCALL | a |")
        val downcallZ = md.indexOf("| DOWNCALL | z |")
        val arenaRow = md.indexOf("| ARENA | b |")
        require(downcallA != -1 && downcallZ != -1 && arenaRow != -1) { "rows missing" }
        check(downcallA < downcallZ && downcallZ < arenaRow) { "sort order wrong" }
    }
}
