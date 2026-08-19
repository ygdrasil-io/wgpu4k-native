package io.ygdrasil.wgpu

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.graphiks.kffi.CallbackPolicy
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GeneratedCallbackAndroidTest {
    @Test
    fun kffiCreatesARoutableSafeCallbackRegistration() {
        val registration = WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        try {
            assertNotNull(registration.userdata)
            assertFalse(registration.isClosed)
        } finally {
            registration.close()
        }
        assertTrue(registration.isClosed)
    }
}
