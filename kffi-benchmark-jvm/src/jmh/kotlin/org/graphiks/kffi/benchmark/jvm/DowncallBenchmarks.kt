package org.graphiks.kffi.benchmark.jvm

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class DowncallBenchmarks {

    private val linker = Linker.nativeLinker()

    private lateinit var emptyHandle: MethodHandle
    private lateinit var add4Handle: MethodHandle
    private lateinit var add8Handle: MethodHandle
    private lateinit var pairSumHandle: MethodHandle
    private lateinit var makePairHandle: MethodHandle
    private lateinit var arena: Arena
    private lateinit var pairArgSegment: MemorySegment
    private lateinit var fixedAllocator: SegmentAllocator

    @Setup
    fun setup() {
        arena = Arena.ofConfined()
        pairArgSegment = arena.allocate(16L)
        fixedAllocator = object : SegmentAllocator {
            override fun allocate(byteSize: Long): MemorySegment = pairArgSegment.asSlice(0, byteSize)
            override fun allocate(byteSize: Long, byteAlignment: Long): MemorySegment =
                pairArgSegment.asSlice(0, byteSize)
        }
        val l = FixtureLoader.lookup
        emptyHandle = linker.downcallHandle(l.find("bench_empty").orElseThrow(), FunctionDescriptor.of(ValueLayout.JAVA_LONG))
        add4Handle = linker.downcallHandle(
            l.find("bench_add4").orElseThrow(),
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
            ),
        )
        add8Handle = linker.downcallHandle(
            l.find("bench_add8").orElseThrow(),
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
            ),
        )
        val pairLayout = MemoryLayout.structLayout(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
        pairSumHandle = linker.downcallHandle(
            l.find("bench_pair_sum").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, pairLayout),
        )
        makePairHandle = linker.downcallHandle(
            l.find("bench_make_pair").orElseThrow(),
            FunctionDescriptor.of(pairLayout, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG),
        )
    }

    @TearDown(Level.Trial)
    fun teardown() {
        arena.close()
    }

    @Benchmark
    fun empty(bh: Blackhole) {
        bh.consume(emptyHandle.invokeExact() as Long)
    }

    @Benchmark
    fun add4(bh: Blackhole) {
        bh.consume(add4Handle.invokeExact(1L, 2L, 3L, 4L) as Long)
    }

    @Benchmark
    fun add8(bh: Blackhole) {
        bh.consume(add8Handle.invokeExact(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L) as Long)
    }

    @Benchmark
    fun pairSum(bh: Blackhole) {
        pairArgSegment.set(ValueLayout.JAVA_LONG, 0L, 7L)
        pairArgSegment.set(ValueLayout.JAVA_LONG, 8L, 9L)
        bh.consume(pairSumHandle.invokeExact(pairArgSegment) as Long)
    }

    /**
     * Struct-return downcall. The linker writes the returned struct into the
     * [SegmentAllocator]-supplied buffer, so this path includes the return-buffer copy.
     */
    @Benchmark
    fun makePair(bh: Blackhole) {
        bh.consume(makePairHandle.invokeExact(fixedAllocator, 7L, 9L) as MemorySegment)
    }
}
