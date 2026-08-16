package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.longs.shouldBeGreaterThan
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Dispatcher statique de test : accessible (objet top-level + @JvmStatic),
 * comme le sera le dispatcher généré par kextract en M4.2.
 */
private object JvmUpcallTestDispatcher {
    val captured = ConcurrentLinkedQueue<Triple<Int, Int, Long>>()

    @JvmStatic
    fun capture(status: Int, value: Int, userdata: Long) {
        captured.add(Triple(status, value, userdata))
    }

    @JvmStatic
    fun sum(status: Int, userdata: Long): Int = status + userdata.toInt()
}

class JvmUpcallEngineTest : FreeSpec({

    "trampoline routes native invocation to the static dispatcher (userdata last)" {
        JvmUpcallTestDispatcher.captured.clear()
        val stub = JvmUpcallEngine.allocateTrampoline(
            dispatcherClass = JvmUpcallTestDispatcher::class.java,
            dispatchMethod = "capture",
            dispatchSig = "(IIJ)V",
        )
        stub.rawValue shouldBeGreaterThan 0L

        val handle = Linker.nativeLinker().downcallHandle(
            MemorySegment.ofAddress(stub.rawValue),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG),
        )
        handle.invokeExact(7, 42, 0x1111L)

        JvmUpcallTestDispatcher.captured.toList() shouldBe listOf(Triple(7, 42, 0x1111L))
    }

    "trampoline supports non-void return via the dispatchSig return carrier" {
        val stub = JvmUpcallEngine.allocateTrampoline(
            dispatcherClass = JvmUpcallTestDispatcher::class.java,
            dispatchMethod = "sum",
            dispatchSig = "(IJ)I",
        )

        val handle = Linker.nativeLinker().downcallHandle(
            MemorySegment.ofAddress(stub.rawValue),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG),
        )
        val result = handle.invokeExact(5, 0x2222L) as Int

        result shouldBe 5 + 0x2222
    }
})
