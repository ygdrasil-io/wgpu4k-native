package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CallbackResultTest {
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
