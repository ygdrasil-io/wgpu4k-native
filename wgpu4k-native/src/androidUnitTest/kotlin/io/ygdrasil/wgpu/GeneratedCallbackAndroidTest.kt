package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GeneratedCallbackAndroidTest : FreeSpec({
    "Android JNA creates a routable safe callback registration" {
        val registration = WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        try {
            assertNotNull(registration.userdata)
            assertFalse(registration.isClosed)
        } finally {
            registration.close()
        }
        assertTrue(registration.isClosed)
    }
})
