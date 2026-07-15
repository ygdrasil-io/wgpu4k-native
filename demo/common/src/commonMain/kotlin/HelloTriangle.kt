package io.ygdrasil.wgpu

import io.ygdrasil.kffi.MemoryAllocator
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
                    val vertexModule = createWgslShaderModule(device, scope, triangleVertexShader)
                    shaderCleanup.defer { wgpuShaderModuleRelease(vertexModule) }

                    val fragmentModule = createWgslShaderModule(device, scope, redFragmentShader)
                    shaderCleanup.defer { wgpuShaderModuleRelease(fragmentModule) }

                    // JNA caches by-value fields, so complete child structures before copying them into the descriptor.
                    val vertex = WGPUVertexState.allocate(scope).apply {
                        module = vertexModule
                        entryPoint = stringView(scope, "main")
                    }
                    val fragment = WGPUFragmentState.allocate(scope).apply {
                        entryPoint = stringView(scope, "main")
                        module = fragmentModule
                        targetCount = 1u
                        var target: WGPUColorTargetState? = null
                        // Keep the initialized array element: wrapping its pointer would write a fresh zeroed JNA structure.
                        WGPUColorTargetState.allocateArray(scope, 1u) { _, structure ->
                            structure.format = renderingContextFormat
                            structure.writeMask = WGPUColorWriteMask_All
                            target = structure
                        }
                        targets = checkNotNull(target)
                    }
                    val primitive = WGPUPrimitiveState.allocate(scope).apply {
                        topology = WGPUPrimitiveTopology_TriangleList
                    }
                    val multisample = WGPUMultisampleState.allocate(scope).apply {
                        count = 1u
                        mask = 0xFFFFFFFFu
                    }
                    val pipeline = WGPURenderPipelineDescriptor.allocate(scope).apply {
                        this.vertex = vertex
                        this.fragment = fragment
                        this.primitive = primitive
                        this.multisample = multisample
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


        val clearValue = WGPUColor.allocate(scope).apply {
            r = .0
            g = 1.0
            b = .0
            a = 1.0
        }
        var colorAttachment: WGPURenderPassColorAttachment? = null
        // Keep the initialized array element for the same JNA pointer-caching reason as the pipeline target.
        WGPURenderPassColorAttachment.allocateArray(scope, 1u) { _, structure ->
            structure.view = frame
            structure.loadOp = WGPULoadOp_Clear
            structure.storeOp = WGPUStoreOp_Store
            structure.depthSlice = UInt.MAX_VALUE
            structure.clearValue = clearValue
            colorAttachment = structure
        }
        val renderPassEncoder = WGPURenderPassDescriptor.allocate(scope).apply {
            colorAttachmentCount = 1u
            colorAttachments = checkNotNull(colorAttachment)
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

private fun createWgslShaderModule(
    device: WGPUDevice,
    scope: MemoryAllocator,
    shader: String,
): WGPUShaderModule {
    val source = WGPUShaderSourceWGSL.allocate(scope).apply {
        code = stringView(scope, shader)
        chain.sType = WGPUSType_ShaderSourceWGSL
    }
    // Commit both the WGSL string view and the embedded chain before exposing the chain pointer.
    source.handler

    val descriptor = WGPUShaderModuleDescriptor.allocate(scope).apply {
        nextInChain = source.chain
    }
    return wgpuDeviceCreateShaderModule(device, descriptor)
        ?: error("fail to create shader module")
}

private fun stringView(scope: MemoryAllocator, value: String): WGPUStringView =
    WGPUStringView.allocate(scope).apply {
        data = scope.allocateFrom(value)
        length = value.length.toULong()
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
