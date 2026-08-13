package org.graphiks.kffi.engine

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UpcallEngineTest {

    private companion object {
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

    private fun allocateDispatchTrampoline(): Long {
        val trampoline = UpcallEngine.allocateTrampoline(
            dispatcherClass = UpcallDispatcher::class.java,
            dispatchMethod = "dispatch",
            dispatchSig = "(JI)V",
        )
        check(trampoline != 0L) { "kffi: allocateTrampoline returned null trampoline" }
        return trampoline
    }

    @Test
    fun callbackFiresFromNativeIntoKotlinLambda() {
        val registration = UpcallDispatcher.register()
        try {
            val trampoline = allocateDispatchTrampoline()
            val setFn = resolve("bench_set_callback")
            val fireFn = resolve("bench_fire_one")
            // bench_set_callback(callback_fn, routing_userdata) — userdata carries the token.
            NativeEngine.callV2PP(setFn, trampoline, registration.userdata!!.rawValue)
            NativeEngine.callV1I(fireFn, 42)
            assertEquals(42, UpcallDispatcher.lastValue)
        } finally {
            UpcallDispatcher.lastValue = -1
            registration.close()
        }
    }

    @Test
    fun callbackFiresFromDetachedNativeThread() {
        val registration = UpcallDispatcher.register()
        try {
            val trampoline = allocateDispatchTrampoline()
            val setFn = resolve("bench_set_callback")
            val fireFromThreadFn = resolve("bench_fire_one_from_thread")
            NativeEngine.callV2PP(setFn, trampoline, registration.userdata!!.rawValue)
            // The fixture spawns a detached pthread that waits on a gate, then fires.
            NativeEngine.callV1I(fireFromThreadFn, 7)
            assertEquals(7, UpcallDispatcher.lastValue)
        } finally {
            UpcallDispatcher.lastValue = -1
            registration.close()
        }
    }
}
