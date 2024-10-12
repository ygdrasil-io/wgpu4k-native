import ffi.ArrayHolder
import ffi.memoryScope
import webgpu.WGPUChainedStruct
import webgpu.WGPUColorTargetState
import webgpu.WGPUDevice
import webgpu.WGPUFragmentState
import webgpu.WGPURenderPassColorAttachment
import webgpu.WGPURenderPassDescriptor
import webgpu.WGPURenderPipeline
import webgpu.WGPURenderPipelineDescriptor
import webgpu.WGPUShaderModuleDescriptor
import webgpu.WGPUShaderSourceWGSL
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceTexture
import webgpu.wgpuCommandBufferRelease
import webgpu.wgpuCommandEncoderBeginRenderPass
import webgpu.wgpuCommandEncoderFinish
import webgpu.wgpuCommandEncoderRelease
import webgpu.wgpuDeviceCreateCommandEncoder
import webgpu.wgpuDeviceCreateRenderPipeline
import webgpu.wgpuDeviceCreateShaderModule
import webgpu.wgpuDeviceGetQueue
import webgpu.wgpuQueueSubmit
import webgpu.wgpuRenderPassEncoderDraw
import webgpu.wgpuRenderPassEncoderEnd
import webgpu.wgpuRenderPassEncoderRelease
import webgpu.wgpuRenderPassEncoderSetPipeline
import webgpu.wgpuSurfaceGetCurrentTexture
import webgpu.wgpuSurfacePresent
import webgpu.wgpuTextureCreateView
import webgpu.wgpuTextureRelease
import webgpu.wgpuTextureViewRelease


class HelloTriangleScene(val device: WGPUDevice, val renderingContextFormat: UInt, val surface: WGPUSurface) {

    val queue = wgpuDeviceGetQueue(device) ?: error("fail to get queue")
    var renderPipeline: WGPURenderPipeline? = null

    fun initialize() = memoryScope { scope ->
        renderPipeline = WGPURenderPipelineDescriptor.allocate(scope).apply {
            vertex.module = WGPUShaderModuleDescriptor.allocate(scope).apply {
                nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                    code.length = triangleVertexShader.length.toULong()
                    code.data = scope.allocateFrom(triangleVertexShader)
                    chain.sType = 0x00000002u
                }.let { WGPUChainedStruct(it.handler) }
            }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create shader module")

            vertex.entryPoint.data = scope.allocateFrom("main")
            vertex.entryPoint.length = "main".length.toULong()

            fragment = WGPUFragmentState.allocate(scope).apply {
                entryPoint.data = scope.allocateFrom("main")
                entryPoint.length = "main".length.toULong()
                module = WGPUShaderModuleDescriptor.allocate(scope).apply {
                    nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                        code.length = redFragmentShader.length.toULong()
                        code.data = scope.allocateFrom(redFragmentShader)
                        chain.sType = 0x00000002u
                    }.let { WGPUChainedStruct(it.handler) }
                }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create shader module")

                targetCount = 1u
                targets = WGPUColorTargetState.allocate(scope).apply {
                    format = renderingContextFormat
                }.let { ArrayHolder(it.handler) }
            }

            primitive.topology = 0x00000003u
            multisample.count = 1u
            multisample.mask = 0xFFFFFFFFu
        }.let { wgpuDeviceCreateRenderPipeline(device, it) }
    }

    fun render() = memoryScope { scope ->

        val surfaceTexture = WGPUSurfaceTexture.allocate(scope)
        wgpuSurfaceGetCurrentTexture(surface, surfaceTexture)

        if (surfaceTexture.status > 1u) error("surface status is KO with status ${surfaceTexture.status}")
        surfaceTexture.texture ?: error("fail to get texture")

        val frame = wgpuTextureCreateView(surfaceTexture.texture, null) ?: error("fail to create view")

        val renderPipeline = renderPipeline ?: error("fail to create get pipeline")

        // Clear the canvas with a render pass
        val commandEncoder = wgpuDeviceCreateCommandEncoder(device, null) ?: error("fail to create command encoder")


        val renderPassEncoder = WGPURenderPassDescriptor.allocate(scope).apply {
            colorAttachmentCount = 1u
            colorAttachments = WGPURenderPassColorAttachment.allocate(scope).apply {
                view = frame
                loadOp = 1u
                storeOp = 1u
                clearValue.r = .0
                clearValue.g = .0
                clearValue.b = .0
                clearValue.a = 1.0
            }.let { ArrayHolder(it.handler) }
        }.let { wgpuCommandEncoderBeginRenderPass(commandEncoder, it) }

        wgpuRenderPassEncoderSetPipeline(renderPassEncoder, renderPipeline)
        wgpuRenderPassEncoderDraw(renderPassEncoder, 3u, 1u, 0u, 0u)
        wgpuRenderPassEncoderEnd(renderPassEncoder)
        wgpuRenderPassEncoderRelease(renderPassEncoder)

        val commandBuffer = wgpuCommandEncoderFinish(commandEncoder, null) ?: error("fail to get finish command buffer")

        wgpuQueueSubmit(queue, 1u, scope.bufferOfAddress(commandBuffer.handler).let { ArrayHolder(it.handler) })

        wgpuSurfacePresent(surface)

        wgpuCommandBufferRelease(commandBuffer)
        wgpuCommandEncoderRelease(commandEncoder)
        wgpuTextureViewRelease(frame)
        wgpuTextureRelease(surfaceTexture.texture)

    }
}

// language=wgsl
const val triangleVertexShader = """
@vertex
fn main(
  @builtin(vertex_index) VertexIndex : u32
) -> @builtin(position) vec4f {
  var pos = array<vec2f, 3>(
    vec2(0.0, 0.5),
    vec2(-0.5, -0.5),
    vec2(0.5, -0.5)
  );

  return vec4f(pos[VertexIndex], 0.0, 1.0);
}
"""

// language=wgsl
const val redFragmentShader = """
@fragment
fn main() -> @location(0) vec4f {
  return vec4(1.0, 0.0, 0.0, 1.0);
}
"""