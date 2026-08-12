package org.graphiks.kffi.benchmark

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScenarioTest : FreeSpec({
    "canonical scenario ids are unique across axes" {
        val ids = BenchmarkScenario.entries.map { it.id }
        ids.toSet().size shouldBe ids.size
    }
    "every scenario declares its axis" {
        BenchmarkScenario.entries.forEach { it.axis shouldBe BenchmarkAxis.DOWNCALL.takeIf { _ -> it.name.startsWith("DOWN_") }
            .let { d ->
                d ?: BenchmarkAxis.UPCALL.takeIf { _ -> it.name.startsWith("UP_") }
                    ?: BenchmarkAxis.MARSHALING.takeIf { _ -> it.name.startsWith("MARSHAL_") }
                    ?: BenchmarkAxis.ARENA
            }
        }
    }
})
