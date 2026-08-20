package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AndroidBackendSelectionTest {
    @Test
    fun selectsVulkanWhenTheProbeFindsAnAdapter() {
        val selectedBackend = selectAndroidBackend(
            probeVulkan = { true },
            onFallback = {},
        )

        assertEquals(WGPUBackendType_Vulkan, selectedBackend)
    }

    @Test
    fun fallsBackToOpenGLESWhenVulkanIsUnavailableWithAnExplanation() {
        val messages = mutableListOf<String>()

        val selectedBackend = selectAndroidBackend(
            probeVulkan = { false },
            onFallback = messages::add,
        )

        assertEquals(WGPUBackendType_OpenGLES, selectedBackend)
        assertTrue(messages.single().contains("Vulkan unavailable"))
        assertTrue(messages.single().contains("Android surface"))
    }
}
