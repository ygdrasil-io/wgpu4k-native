package org.graphiks.kffi.engine

import org.junit.Assert.assertEquals
import org.junit.Test

class DowncallEngineTest {

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
}
