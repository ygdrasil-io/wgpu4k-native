package org.graphiks.kffi.engine

import java.nio.ByteBuffer
import java.nio.ByteOrder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DowncallEngineTest {

    @Before
    fun setUp() {
        System.loadLibrary("kffi_bench_fixture")
    }

    @Test
    fun resolvesSymbolsAndCallsVoid0AndInt0() {
        val fn = NativeEngine.resolveSymbol("bench_empty")
        assertEquals(42L, NativeEngine.callI0(fn))
        val voidFn = NativeEngine.resolveSymbol("bench_void_takes_void")
        NativeEngine.callV0(voidFn)
    }

    @Test
    fun callI4IIIIAddsIntegers() {
        val fn = NativeEngine.resolveSymbol("bench_add4")
        assertEquals(10L, NativeEngine.callI4IIII(fn, 1, 2, 3, 4))
    }

    @Test
    fun structByValueArgAndReturn() {
        val sum = NativeEngine.resolveSymbol("bench_pair_sum")
        val buf = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
        buf.putLong(0, 7L); buf.putLong(8, 9L)
        val out = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
        val sumResult = NativeEngine.callStructArgL(sum, 16, NativeEngine.directBufferAddress(buf), 0L)
        assertEquals(16L, sumResult)

        val makePair = NativeEngine.resolveSymbol("bench_make_pair")
        NativeEngine.callStructReturn(makePair, 7L, 9L, 16, NativeEngine.directBufferAddress(out))
        assertEquals(7L, out.getLong(0))
        assertEquals(9L, out.getLong(8))
    }
}
