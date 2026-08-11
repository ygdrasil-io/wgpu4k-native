@file:OptIn(
    CallbackRuntimeApi::class,
    ExperimentalAtomicApi::class,
)

package org.graphiks.kffi.benchmark.jvm

import io.ygdrasil.kffi.Callback
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.CallbackRuntime
import io.ygdrasil.kffi.CallbackRuntimeApi
import io.ygdrasil.kffi.CallbackType
import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.adapt
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.annotations.Threads
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.util.concurrent.TimeUnit
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi

private fun interface BenchCallback : Callback {
    fun invoke(value: UInt)
}

private val BenchCallbackType: CallbackType<BenchCallback> = CallbackType(
    canonicalId = "bench:BenchCallback",
    hasRoutingUserdata = true,
)

private object BenchCallbackTrampoline {
    private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
    private val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findStatic(
            BenchCallbackTrampoline::class.java,
            "invoke",
            descriptor.toMethodType(),
        )
    }
    val address: NativeAddress by lazy {
        NativeAddress(Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global()))
    }

    @JvmStatic
    private fun invoke(value: Int, userdata: MemorySegment) {
        CallbackRuntime.dispatchSafely(BenchCallbackType, NativeAddress(userdata)) { callback ->
            callback.invoke(value.toUInt())
        }
    }
}

@State(Scope.Thread)
// Mandatory: bench_fixture.c stores the callback in a shared non-atomic global; JMH must not run multiple workers.
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class UpcallBenchmarks {

    private val linker = Linker.nativeLinker()
    private val counter = AtomicLong(0)

    private lateinit var setCallbackHandle: MethodHandle
    private lateinit var fireHandle: MethodHandle
    private lateinit var fireOneHandle: MethodHandle
    private lateinit var registration: CallbackRegistration<BenchCallback>

    @Setup
    fun setup() {
        val l = FixtureLoader.lookup
        setCallbackHandle = linker.downcallHandle(
            l.find("bench_set_callback").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        )
        fireHandle = linker.downcallHandle(
            l.find("bench_fire").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
        )
        fireOneHandle = linker.downcallHandle(
            l.find("bench_fire_one").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
        )
        registration = CallbackRuntime.register(
            type = BenchCallbackType,
            trampoline = BenchCallbackTrampoline.address,
            policy = CallbackPolicy.REPEATING,
            callback = BenchCallback { _ -> counter.fetchAndAdd(1) },
        )
        setCallbackHandle.invokeExact(registration.callback.handler, registration.userdata.adapt()!!)
        fireOneHandle.invokeExact(1)
        check(counter.load() == 1L) { "upcall did not dispatch" }
        counter.store(0)
    }

    @Benchmark
    fun upcallFireOne(bh: Blackhole) {
        fireOneHandle.invokeExact(1)
        bh.consume(counter.load())
    }

    @Benchmark
    fun upcallFire1000(bh: Blackhole) {
        fireHandle.invokeExact(1000)
        bh.consume(counter.load())
    }

    @TearDown
    fun teardown() {
        registration.close()
    }
}
