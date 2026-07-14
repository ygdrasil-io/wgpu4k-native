package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CallbackResultTest {
    @Test
    fun nonSuccessAdapterStatusRejectsAndReleasesNonNullHandleExactlyOnce() {
        var releaseCalls = 0
        var released: String? = null

        val failure = assertFailsWith<IllegalStateException> {
            resolveAdapterRequestResult(
                status = WGPURequestAdapterStatus_Error,
                adapter = "unexpected-adapter",
                message = "backend rejected adapter",
                release = {
                    releaseCalls += 1
                    released = it
                },
            )
        }

        assertEquals(1, releaseCalls)
        assertEquals("unexpected-adapter", released)
        assertTrue(failure.message.orEmpty().contains("backend rejected adapter"))
    }

    @Test
    fun successfulAdapterStatusWithoutHandleFailsWithoutRelease() {
        var releaseCalls = 0

        val failure = assertFailsWith<IllegalStateException> {
            resolveAdapterRequestResult<String>(
                status = WGPURequestAdapterStatus_Success,
                adapter = null,
                message = "backend returned no adapter",
                release = { releaseCalls += 1 },
            )
        }

        assertEquals(0, releaseCalls)
        assertTrue(failure.message.orEmpty().contains("success status returned no adapter"))
    }

    @Test
    fun nonSuccessDeviceStatusRejectsAndReleasesNonNullHandle() {
        var released: String? = null

        val failure = assertFailsWith<IllegalStateException> {
            resolveDeviceRequestResult(
                status = WGPURequestDeviceStatus_Error,
                device = "unexpected-device",
                message = "backend rejected request",
                release = { released = it },
            )
        }

        assertEquals("unexpected-device", released)
        assertTrue(failure.message.orEmpty().contains("backend rejected request"))
    }

    @Test
    fun mapFailurePreservesStatusAndDiagnosticOnCaller() {
        val failure = assertFailsWith<IllegalStateException> {
            requireSuccessfulMapResult(
                status = WGPUMapAsyncStatus_Error,
                message = "mapping rejected",
            )
        }

        assertTrue(failure.message.orEmpty().contains("mapping rejected"))
        assertTrue(failure.message.orEmpty().contains(WGPUMapAsyncStatus_Error.toString()))
    }
}
