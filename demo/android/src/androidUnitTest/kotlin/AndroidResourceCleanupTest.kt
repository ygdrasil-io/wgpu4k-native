package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidResourceCleanupTest {
    @Test
    fun sceneClosesBeforeParentGpuHandles() {
        val handle = Pointer(1L)
        val releases = mutableListOf<String>()

        releaseAndroidResources(
            scene = null,
            device = WGPUDevice(handle),
            adapter = WGPUAdapter(handle),
            surface = WGPUSurface(handle),
            instance = WGPUInstance(handle),
            releaseScene = {
                releases += "pipeline"
                releases += "queue"
            },
            releaseDevice = { releases += "device" },
            releaseAdapter = { releases += "adapter" },
            releaseSurface = { releases += "surface" },
            releaseInstance = { releases += "instance" },
        )

        assertEquals(
            listOf("pipeline", "queue", "device", "adapter", "surface", "instance"),
            releases,
        )
    }
}
