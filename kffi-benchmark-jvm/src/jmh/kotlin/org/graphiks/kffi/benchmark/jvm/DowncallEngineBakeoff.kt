package org.graphiks.kffi.benchmark.jvm

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.graphiks.kffi.engine.JvmDowncallEngine
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.util.concurrent.TimeUnit

/**
 * Downcall engine bake-off (JVM signal for the P1 Android engine decision).
 *
 * - [fmmExact] is the steady-state engine floor: a cached MethodHandle.invokeExact
 *   (the current kffi JVM path), fully inlined by the JIT.
 * - [fmmExactDereferencedLookup] is a worst-case ceiling: per-call symbol lookup +
 *   downcallHandle construction + cold-path dispatch. It bounds the headroom gained
 *   by caching, and is NOT a head-to-head competitor.
 * - [jvmEngineAdd4] / [jvmEngineEmpty] measure [JvmDowncallEngine] as-is (M2.1):
 *   symbol resolved once, per-call handle construction inside the wrapper. They
 *   bound the current engine headroom vs [fmmExact]; the wrap-once cache lands
 *   after the bake-off verdict (M5).
 * - [noOpFloor] measures the Blackhole/loop overhead floor.
 *
 * The definitive Android-device bake-off (wrap-once typed vs JNI pur vs libffi)
 * runs in P1 once the engine exists.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class DowncallEngineBakeoff {

    private val linker = Linker.nativeLinker()
    private lateinit var exactHandle: MethodHandle
    private lateinit var emptyDescriptor: FunctionDescriptor

    @Setup
    fun setup() {
        val l = FixtureLoader.lookup
        emptyDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
        exactHandle = linker.downcallHandle(l.find("bench_empty").orElseThrow(), emptyDescriptor)
    }

    @Benchmark
    fun noOpFloor(bh: Blackhole) {
        bh.consume(0L)
    }

    @Benchmark
    fun fmmExact(bh: Blackhole) {
        bh.consume(exactHandle.invokeExact() as Long)
    }

    @Benchmark
    fun fmmExactDereferencedLookup(bh: Blackhole) {
        val handle = linker.downcallHandle(
            FixtureLoader.lookup.find("bench_empty").orElseThrow(),
            emptyDescriptor,
        )
        bh.consume(handle.invokeExact() as Long)
    }

    @Benchmark
    fun jvmEngineAdd4(state: EngineState, blackhole: Blackhole): Unit =
        blackhole.consume(JvmDowncallEngine.callI4IIII(state.add4Addr, 1, 2, 3, 4))

    @Benchmark
    fun jvmEngineEmpty(state: EngineState, blackhole: Blackhole): Unit =
        blackhole.consume(JvmDowncallEngine.callI0(state.emptyAddr))
}

/**
 * JvmDowncallEngine axis state: symbols resolved once, per-call cost measured.
 *
 * The fixture library is loaded through [FixtureLoader] (global namespace), so
 * [JvmDowncallEngine.resolveSymbol]'s defaultLookup finds the bench symbols.
 */
@State(Scope.Thread)
open class EngineState {
    var emptyAddr: Long = 0L
    var add4Addr: Long = 0L

    @Setup
    fun setup() {
        FixtureLoader.lookup // force fixture library load before resolving
        emptyAddr = JvmDowncallEngine.resolveSymbol("bench_empty")
        add4Addr = JvmDowncallEngine.resolveSymbol("bench_add4")
    }
}
