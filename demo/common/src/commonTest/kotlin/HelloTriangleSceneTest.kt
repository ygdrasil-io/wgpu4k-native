package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HelloTriangleSceneTest {
    @Test
    fun closeReleasesPipelineBeforeQueueExactlyOnce() {
        val handle = testNativeAddress()
        val releases = mutableListOf<String>()
        val scene = HelloTriangleScene(
            device = WGPUDevice(handle),
            renderingContextFormat = WGPUTextureFormat_RGBA8Unorm,
            surface = WGPUSurface(handle),
            queue = WGPUQueue(handle),
            releaseQueue = { releases += "queue" },
            releaseRenderPipeline = { releases += "pipeline" },
        )

        scene.ownRenderPipeline(WGPURenderPipeline(handle))
        scene.close()
        scene.close()

        assertEquals(listOf("pipeline", "queue"), releases)
        assertNull(scene.renderPipeline)
    }
}
