@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.jvm

import org.graphiks.kffi.memoryScope
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class ArenaBenchmarks {

    @Benchmark
    fun memoryScopeTenAllocs(bh: Blackhole) {
        memoryScope { allocator ->
            var sum = 0L
            repeat(10) {
                sum += allocator.allocate(64L).hashCode()
            }
            bh.consume(sum)
        }
    }

    @Benchmark
    fun memoryScopeHundredAllocs(bh: Blackhole) {
        memoryScope { allocator ->
            var sum = 0L
            repeat(100) {
                sum += allocator.allocate(64L).hashCode()
            }
            bh.consume(sum)
        }
    }
}
