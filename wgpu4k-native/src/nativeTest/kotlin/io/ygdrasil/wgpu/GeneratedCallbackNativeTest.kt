@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.NativeAddress
import io.kotest.core.spec.style.FreeSpec
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.invoke
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class GeneratedCallbackNativeTest : FreeSpec({
    "mode bearing and mode free factories preserve callback userdata roles" {
        val allocator = MemoryAllocator()
        val applicationUserdata = allocator.allocate(1)
        val bufferMap = WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        val uncapturedError = WGPUUncapturedErrorCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        try {
            val modeBearing = WGPUBufferMapCallbackInfo.allocate(
                allocator,
                WGPUCallbackMode_AllowSpontaneous,
                bufferMap,
                applicationUserdata,
            )
            assertEquals(WGPUCallbackMode_AllowSpontaneous, modeBearing.mode)
            assertWiring(modeBearing.callback, modeBearing.userdata1, modeBearing.userdata2, bufferMap, applicationUserdata)

            val modeFree = WGPUUncapturedErrorCallbackInfo.allocate(
                allocator,
                uncapturedError,
                applicationUserdata,
            )
            assertWiring(modeFree.callback, modeFree.userdata1, modeFree.userdata2, uncapturedError, applicationUserdata)

            val closedAllocator = MemoryAllocator().also(MemoryAllocator::close)
            assertFailsWith<IllegalArgumentException> {
                WGPUBufferMapCallbackInfo.allocate(closedAllocator, 0u, bufferMap, null)
            }
        } finally {
            uncapturedError.close()
            bufferMap.close()
            allocator.close()
        }
    }

    "same type registrations route native trampoline deliveries by distinct tokens" {
        val allocator = MemoryAllocator()
        val deliveries = mutableListOf<Pair<String, Long?>>()
        val firstApplicationUserdata = allocator.allocate(1)
        val secondApplicationUserdata = allocator.allocate(1)
        val first = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, userdata1 ->
            deliveries += "first" to userdata1?.rawValue
        }
        val second = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, userdata1 ->
            deliveries += "second" to userdata1?.rawValue
        }
        try {
            dispatch(first, firstApplicationUserdata)
            dispatch(second, secondApplicationUserdata)
            assertEquals(
                listOf<Pair<String, Long?>>(
                    "first" to firstApplicationUserdata.rawValue,
                    "second" to secondApplicationUserdata.rawValue,
                ),
                deliveries,
            )
        } finally {
            second.close()
            first.close()
            allocator.close()
        }
    }
})

private fun dispatch(
    registration: CallbackRegistration<WGPUCompilationInfoCallback>,
    applicationUserdata: NativeAddress,
) {
    val trampoline = registration.callback.reinterpret<CFunction<(
        WGPUCompilationInfoRequestStatus,
        COpaquePointer?,
        COpaquePointer?,
        COpaquePointer?,
    ) -> Unit>>()
    trampoline(
        WGPUCompilationInfoRequestStatus_Success,
        null,
        applicationUserdata.pointer,
        assertNotNull(registration.userdata).pointer,
    )
}

private fun assertWiring(
    callback: NativeAddress?,
    userdata1: NativeAddress?,
    userdata2: NativeAddress?,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) {
    assertEquals(registration.callback.rawValue, assertNotNull(callback).rawValue)
    assertEquals(applicationUserdata.rawValue, assertNotNull(userdata1).rawValue)
    assertEquals(assertNotNull(registration.userdata).rawValue, assertNotNull(userdata2).rawValue)
}
