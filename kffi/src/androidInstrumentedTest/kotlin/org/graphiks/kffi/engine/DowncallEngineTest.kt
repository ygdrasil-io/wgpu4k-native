package org.graphiks.kffi.engine

import androidx.test.platform.app.InstrumentationRegistry
import java.nio.ByteBuffer
import java.nio.ByteOrder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DowncallEngineTest {

    companion object {
        private var fixtureHandle = 0L
    }

    @Before
    fun setUp() {
        if (fixtureHandle == 0L) {
            System.loadLibrary("kffi")
            val nativeLibraryDir = InstrumentationRegistry.getInstrumentation()
                .targetContext.applicationInfo.nativeLibraryDir
            fixtureHandle =
                NativeEngine.loadNativeLibrary("$nativeLibraryDir/libkffi_bench_fixture.so")
            check(fixtureHandle != 0L) { "kffi: failed to dlopen libkffi_bench_fixture.so" }
        }
    }

    private fun resolve(name: String): Long = NativeEngine.resolveSymbolIn(fixtureHandle, name)

    @Test
    fun resolvesSymbolsAndCallsVoid0AndInt0() {
        val fn = resolve("bench_empty")
        assertEquals(42L, NativeEngine.callI0(fn))
        val voidFn = resolve("bench_void_takes_void")
        NativeEngine.callV0(voidFn)
    }

    @Test
    fun callI4IIIIAddsIntegers() {
        val fn = resolve("bench_add4")
        assertEquals(10L, NativeEngine.callI4IIII(fn, 1, 2, 3, 4))
    }

    @Test
    fun structByValueArgAndReturn() {
        val sum = resolve("bench_pair_sum")
        val buf = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
        buf.putLong(0, 7L); buf.putLong(8, 9L)
        val out = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
        val sumResult = NativeEngine.callStructArgL(sum, 16, NativeEngine.directBufferAddress(buf), 0L)
        assertEquals(16L, sumResult)

        val makePair = resolve("bench_make_pair")
        NativeEngine.callStructReturn(makePair, 7L, 9L, 16, NativeEngine.directBufferAddress(out))
        assertEquals(7L, out.getLong(0))
        assertEquals(9L, out.getLong(8))
    }
}
