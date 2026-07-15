package io.ygdrasil.wgpu

import io.ygdrasil.kffi.memoryScope


class HelloTriangleScene internal constructor(
    val device: WGPUDevice,
    val renderingContextFormat: UInt,
    val surface: WGPUSurface,
    val queue: WGPUQueue,
    private val releaseQueue: (WGPUQueue?) -> Unit,
    private val releaseRenderPipeline: (WGPURenderPipeline?) -> Unit,
) {
    constructor(
        device: WGPUDevice,
        renderingContextFormat: UInt,
        surface: WGPUSurface,
    ) : this(
        device = device,
        renderingContextFormat = renderingContextFormat,
        surface = surface,
        queue = wgpuDeviceGetQueue(device) ?: error("fail to get queue"),
        releaseQueue = ::wgpuQueueRelease,
        releaseRenderPipeline = ::wgpuRenderPipelineRelease,
    )

    private val cleanup = GpuCleanupStack().apply {
        defer { releaseQueue(queue) }
    }
    private var closed = false
    var renderPipeline: WGPURenderPipeline? = null
        private set

    fun initialize() {
        check(!closed) { "HelloTriangleScene is already closed" }
        check(renderPipeline == null) { "HelloTriangleScene is already initialized" }

        try {
            memoryScope { scope ->
                val shaderCleanup = GpuCleanupStack()
                var primaryFailure: Throwable? = null
                try {
                    val vertexModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
                        nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                            code.length = triangleVertexShader.length.toULong()
                            code.data = scope.allocateFrom(triangleVertexShader)
                            chain.sType = WGPUSType_ShaderSourceWGSL
                        }.chain
                    }.let { wgpuDeviceCreateShaderModule(device, it) }
                        ?: error("fail to create vertex shader module")
                    shaderCleanup.defer { wgpuShaderModuleRelease(vertexModule) }

                    val fragmentModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
                        nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                            code.length = redFragmentShader.length.toULong()
                            code.data = scope.allocateFrom(redFragmentShader)
                            chain.sType = WGPUSType_ShaderSourceWGSL
                        }.chain
                    }.let { wgpuDeviceCreateShaderModule(device, it) }
                        ?: error("fail to create fragment shader module")
                    shaderCleanup.defer { wgpuShaderModuleRelease(fragmentModule) }

                    val pipeline = WGPURenderPipelineDescriptor.allocate(scope).apply {
                        vertex.module = vertexModule
                        vertex.entryPoint.data = scope.allocateFrom("main")
                        vertex.entryPoint.length = "main".length.toULong()

                        fragment = WGPUFragmentState.allocate(scope).apply {
                            entryPoint.data = scope.allocateFrom("main")
                            entryPoint.length = "main".length.toULong()
                            module = fragmentModule
                            targetCount = 1u
                            targets = WGPUColorTargetState.allocateArray(scope, 1u) { _, structure ->
                                structure.format = renderingContextFormat
                                structure.writeMask = WGPUColorWriteMask_All
                            }.let { WGPUColorTargetState(it.handler) }
                        }

                        primitive.topology = WGPUPrimitiveTopology_TriangleList
                        multisample.count = 1u
                        multisample.mask = 0xFFFFFFFFu
                    }.let { wgpuDeviceCreateRenderPipeline(device, it) }
                        ?: error("fail to create render pipeline")
                    ownRenderPipeline(pipeline)
                } catch (failure: Throwable) {
                    primaryFailure = failure
                    throw failure
                } finally {
                    shaderCleanup.close(primaryFailure)
                }
            }
        } catch (failure: Throwable) {
            close(failure)
            throw failure
        }
    }

    internal fun ownRenderPipeline(pipeline: WGPURenderPipeline) {
        check(!closed) { "HelloTriangleScene is already closed" }
        check(renderPipeline == null) { "HelloTriangleScene already owns a render pipeline" }
        renderPipeline = pipeline
        cleanup.defer {
            val ownedPipeline = renderPipeline
            renderPipeline = null
            releaseRenderPipeline(ownedPipeline)
        }
    }

    fun close() {
        close(primaryFailure = null)
    }

    private fun close(primaryFailure: Throwable?) {
        if (closed) return
        closed = true
        cleanup.close(primaryFailure)
    }

    fun render() = memoryScope { scope ->

        val surfaceTexture = WGPUSurfaceTexture.allocate(scope)
        wgpuSurfaceGetCurrentTexture(surface, surfaceTexture)

        when (surfaceTexture.status) {
            WGPUSurfaceGetCurrentTextureStatus_SuccessOptimal,
            WGPUSurfaceGetCurrentTextureStatus_SuccessSuboptimal -> Unit
            WGPUSurfaceGetCurrentTextureStatus_Timeout,
            WGPUSurfaceGetCurrentTextureStatus_Outdated,
            WGPUSurfaceGetCurrentTextureStatus_Lost,
            WGPUSurfaceGetCurrentTextureStatus_Occluded -> return@memoryScope
            else -> error("surface status is KO with status ${surfaceTexture.status}")
        }

        val texture = surfaceTexture.texture ?: return@memoryScope

        val frame = wgpuTextureCreateView(texture, null) ?: error("fail to create view")

        val renderPipeline = renderPipeline ?: error("fail to create get pipeline")

        // Clear the canvas with a render pass
        val commandEncoder = wgpuDeviceCreateCommandEncoder(device, null) ?: error("fail to create command encoder")


        val renderPassEncoder = WGPURenderPassDescriptor.allocate(scope).apply {
            colorAttachmentCount = 1u
            colorAttachments = WGPURenderPassColorAttachment.allocateArray(scope, 1u) { index, structure ->
                structure.view = frame
                structure.loadOp = WGPULoadOp_Clear
                structure.storeOp = WGPUStoreOp_Store
                structure.depthSlice = UInt.MAX_VALUE
                structure.clearValue.r = .0
                structure.clearValue.g = 1.0
                structure.clearValue.b = .0
                structure.clearValue.a = 1.0
            }.let { WGPURenderPassColorAttachment(it.handler) }
        }.let { wgpuCommandEncoderBeginRenderPass(commandEncoder, it) }

        wgpuRenderPassEncoderSetPipeline(renderPassEncoder, renderPipeline)
        wgpuRenderPassEncoderDraw(renderPassEncoder, 3u, 1u, 0u, 0u)
        wgpuRenderPassEncoderEnd(renderPassEncoder)
        wgpuRenderPassEncoderRelease(renderPassEncoder)

        val commandBuffer = wgpuCommandEncoderFinish(commandEncoder, null) ?: error("fail to get finish command buffer")

        wgpuQueueSubmit(queue, 1u, scope.bufferOfAddress(commandBuffer.handler).handler)

        wgpuSurfacePresent(surface)

        wgpuCommandBufferRelease(commandBuffer)
        wgpuCommandEncoderRelease(commandEncoder)
        wgpuTextureViewRelease(frame)
        wgpuTextureRelease(texture)

    }
}

// language=wgsl
const val triangleVertexShader = """
@vertex
fn main(@builtin(vertex_index) in_vertex_index: u32) -> @builtin(position) vec4<f32> {
    let x = f32(i32(in_vertex_index) - 1);
    let y = f32(i32(in_vertex_index & 1u) * 2 - 1);
    return vec4<f32>(x, y, 0.0, 1.0);
}
"""

// language=wgsl
const val redFragmentShader = """
@fragment
fn main() -> @location(0) vec4f {
  return vec4(1.0, 0.0, 0.0, 1.0);
}
"""
