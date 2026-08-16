@file:OptIn(
    CallbackRuntimeApi::class,
    ExperimentalAtomicApi::class,
)

package org.graphiks.kffi.benchmark.jvm

import org.graphiks.kffi.Callback
import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.CallbackRuntime
import org.graphiks.kffi.CallbackRuntimeApi
import org.graphiks.kffi.CallbackType
import org.graphiks.kffi.NativeAddress
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
        NativeAddress(Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global()).address())
    }

    @JvmStatic
    private fun invoke(value: Int, userdata: MemorySegment) {
        CallbackRuntime.dispatchSafely(
            BenchCallbackType,
            if (userdata == MemorySegment.NULL) null else NativeAddress(userdata.address()),
        ) { callback ->
            callback.invoke(value.toUInt())
        }
    }
}

private fun interface BenchCallbackNoUserdata : Callback {
    fun invoke(value: UInt)
}

private val BenchCallbackNoUserdataType: CallbackType<BenchCallbackNoUserdata> = CallbackType(
    canonicalId = "bench:BenchCallbackNoUserdata",
    hasRoutingUserdata = false,
)

private object BenchCallbackNoUserdataTrampoline {
    private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)
    private val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findStatic(
            BenchCallbackNoUserdataTrampoline::class.java,
            "invoke",
            descriptor.toMethodType(),
        )
    }
    val address: NativeAddress by lazy {
        NativeAddress(Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global()).address())
    }

    @JvmStatic
    private fun invoke(value: Int) {
        CallbackRuntime.dispatchSafely(BenchCallbackNoUserdataType, null) { callback ->
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
    private lateinit var setCallbackNoUserdataHandle: MethodHandle
    private lateinit var fireNoUserdataHandle: MethodHandle
    private lateinit var noUserdataRegistration: CallbackRegistration<BenchCallbackNoUserdata>

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
        setCallbackHandle.invokeExact(
            MemorySegment.ofAddress(registration.callback.rawValue),
            MemorySegment.ofAddress(requireNotNull(registration.userdata).rawValue),
        )
        fireOneHandle.invokeExact(1)
        check(counter.load() == 1L) { "upcall did not dispatch" }
        counter.store(0)
        setCallbackNoUserdataHandle = linker.downcallHandle(
            l.find("bench_set_callback_no_userdata").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
        )
        fireNoUserdataHandle = linker.downcallHandle(
            l.find("bench_fire_no_userdata").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
        )
        noUserdataRegistration = CallbackRuntime.register(
            type = BenchCallbackNoUserdataType,
            trampoline = BenchCallbackNoUserdataTrampoline.address,
            policy = CallbackPolicy.REPEATING,
            callback = BenchCallbackNoUserdata { _ -> counter.fetchAndAdd(1) },
        )
        setCallbackNoUserdataHandle.invokeExact(MemorySegment.ofAddress(noUserdataRegistration.callback.rawValue))
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

    @Benchmark
    fun upcallFireOneNoRouting(bh: Blackhole) {
        fireNoUserdataHandle.invokeExact(1)
        bh.consume(counter.load())
    }

    @Benchmark
    fun upcallFire1000NoRouting(bh: Blackhole) {
        fireNoUserdataHandle.invokeExact(1000)
        bh.consume(counter.load())
    }

    @TearDown
    fun teardown() {
        registration.close()
        noUserdataRegistration.close()
    }
}
