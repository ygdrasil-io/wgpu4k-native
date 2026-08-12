@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.jvm

import org.graphiks.kffi.MemoryBuffer
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
open class MarshalingBenchmarks {

    private val ints16 = IntArray(16) { it }
    private val ints1024 = IntArray(1024) { it }
    private val doubles1024 = DoubleArray(1024) { it.toDouble() }

    @Benchmark
    fun writeReadIntScalar(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(16uL)
            buffer.writeInt(42, 0uL)
            bh.consume(buffer.readInt(0uL))
        }
    }

    @Benchmark
    fun writeReadLongScalar(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(16uL)
            buffer.writeLong(42L, 0uL)
            bh.consume(buffer.readLong(0uL))
        }
    }

    @Benchmark
    fun copyInts16(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(64uL)
            buffer.writeInts(ints16)
            val out = IntArray(16)
            buffer.readInts(out)
            var checksum = 0
            for (i in out.indices) {
                checksum = checksum xor out[i]
            }
            bh.consume(checksum)
        }
    }

    @Benchmark
    fun copyInts1024(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(4096uL)
            buffer.writeInts(ints1024)
            val out = IntArray(1024)
            buffer.readInts(out)
            var checksum = 0
            for (i in out.indices) {
                checksum = checksum xor out[i]
            }
            bh.consume(checksum)
        }
    }

    @Benchmark
    fun copyDoubles1024(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(8192uL)
            buffer.writeDoubles(doubles1024)
            val out = DoubleArray(1024)
            buffer.readDoubles(out)
            var checksum = 0L
            for (i in out.indices) {
                checksum = checksum xor out[i].toRawBits()
            }
            bh.consume(checksum)
        }
    }

    // Full checksum consume: consuming only out[0] would let the JIT fold the copy
    // (out is fresh, src is final).
    @Benchmark
    fun baselineByteArrayCopy(bh: Blackhole) {
        val src = ints1024
        val out = IntArray(1024)
        src.copyInto(out)
        var checksum = 0
        for (i in out.indices) {
            checksum = checksum xor out[i]
        }
        bh.consume(checksum)
    }
}
