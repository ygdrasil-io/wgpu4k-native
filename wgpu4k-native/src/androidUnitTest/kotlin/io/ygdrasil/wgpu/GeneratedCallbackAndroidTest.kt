package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.assertContains
import kotlin.test.assertFailsWith

class GeneratedCallbackAndroidTest : FreeSpec({
    "Android JNA rejects safe callback registration before allocation" {
        val failure = assertFailsWith<UnsupportedOperationException> {
            WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        }

        assertContains(failure.message.orEmpty(), "Android/JNA callback registration is not supported")
    }
})
