package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.longs.shouldBeGreaterThan
import org.graphiks.kffi.engine.upcallfixture.TestUpcallDispatchersBridge
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class JvmUpcallEngineTest : FreeSpec({

    "trampoline routes native invocation to the static dispatcher (userdata last, cross-package private object)" {
        TestUpcallDispatchersBridge.captured.clear()
        val stub = JvmUpcallEngine.allocateTrampoline(
            dispatcherClass = TestUpcallDispatchersBridge.dispatcherClass,
            dispatchMethod = "captureStatusValueUserdata",
            dispatchSig = "(IIJ)V",
        )
        stub.rawValue shouldBeGreaterThan 0L

        val handle = Linker.nativeLinker().downcallHandle(
            MemorySegment.ofAddress(stub.rawValue),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG),
        )
        handle.invokeExact(7, 42, 0x1111L)

        TestUpcallDispatchersBridge.captured.toList() shouldBe listOf(Triple(7, 42, 0x1111L))
    }

    "trampoline supports non-void return via the dispatchSig return carrier" {
        TestUpcallDispatchersBridge.captured.clear()
        val stub = JvmUpcallEngine.allocateTrampoline(
            dispatcherClass = TestUpcallDispatchersBridge.dispatcherClass,
            dispatchMethod = "captureReturningInt",
            dispatchSig = "(IJ)I",
        )

        val handle = Linker.nativeLinker().downcallHandle(
            MemorySegment.ofAddress(stub.rawValue),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG),
        )
        val result = handle.invokeExact(5, 0x2222L) as Int

        result shouldBe 6
        TestUpcallDispatchersBridge.captured.toList() shouldBe listOf(Triple(5, -1, 0x2222L))
    }
})
