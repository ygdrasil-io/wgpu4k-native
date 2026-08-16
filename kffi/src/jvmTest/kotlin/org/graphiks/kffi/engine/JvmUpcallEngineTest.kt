package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.longs.shouldBeGreaterThan
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

private const val USERDATA_TOKEN = 42L

private fun invokeV2PPStub(stub: Long, userdata: Long, a1: Long, a2: Long) {
    val handle = Linker.nativeLinker().downcallHandle(
        MemorySegment.ofAddress(stub),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )
    handle.invokeExact(
        MemorySegment.ofAddress(userdata),
        MemorySegment.ofAddress(a1),
        MemorySegment.ofAddress(a2),
    )
}

class JvmUpcallEngineTest : FreeSpec({

    "V2PP trampoline routes native invocation to the registered handler" {
        var calls = 0
        var receivedA1 = 0L
        var receivedA2 = 0L
        val stub = JvmUpcallEngine.trampolineV2PP(USERDATA_TOKEN) { a1, a2 ->
            calls += 1
            receivedA1 = a1
            receivedA2 = a2
        }
        stub.rawValue shouldBeGreaterThan 0L

        invokeV2PPStub(stub.rawValue, USERDATA_TOKEN, 0x1111L, 0x2222L)

        calls shouldBe 1
        receivedA1 shouldBe 0x1111L
        receivedA2 shouldBe 0x2222L
    }

    "V2PP trampoline ignores invocation with an unknown userdata token" {
        var calls = 0
        val stub = JvmUpcallEngine.trampolineV2PP(USERDATA_TOKEN) { _, _ ->
            calls += 1
        }

        invokeV2PPStub(stub.rawValue, USERDATA_TOKEN + 1L, 0x1111L, 0x2222L)

        calls shouldBe 0
    }
})
