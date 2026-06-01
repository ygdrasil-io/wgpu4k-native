package io.ygdrasil.wgpu

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.MemoryBuffer
import io.ygdrasil.kffi.memoryScope

data class HeadlessTriangleImage(
    val width: Int,
    val height: Int,
    val rgba: ByteArray
)

fun renderHeadlessTriangle(width: Int = 64, height: Int = 64): HeadlessTriangleImage {
    val instance = wgpuCreateInstance(null) ?: error("fail to create instance")
    val adapter = getAdapter(surface = null, instance = instance)
    val device = getDevice(adapter)
    val format = WGPUTextureFormat_RGBA8Unorm
    val queue = wgpuDeviceGetQueue(device) ?: error("fail to get queue")
    var mapStatus: WGPUMapAsyncStatus? = null

    val pixels = memoryScope { scope ->
        val texture = WGPUTextureDescriptor.allocate(scope).apply {
            usage = WGPUTextureUsage_RenderAttachment or WGPUTextureUsage_CopySrc
            dimension = WGPUTextureDimension_2D
            size.width = width.toUInt()
            size.height = height.toUInt()
            size.depthOrArrayLayers = 1u
            this.format = format
            mipLevelCount = 1u
            sampleCount = 1u
        }.let { wgpuDeviceCreateTexture(device, it) } ?: error("fail to create texture")

        val textureView = WGPUTextureViewDescriptor.allocate(scope).apply {
            this.format = format
            dimension = WGPUTextureViewDimension_2D
            baseMipLevel = 0u
            mipLevelCount = 1u
            baseArrayLayer = 0u
            arrayLayerCount = 1u
            aspect = WGPUTextureAspect_All
            usage = WGPUTextureUsage_RenderAttachment
        }.let { wgpuTextureCreateView(texture, it) } ?: error("fail to create texture view")

        val pipeline = createHeadlessTrianglePipeline(device, format, scope)
        val commandEncoder = wgpuDeviceCreateCommandEncoder(device, null) ?: error("fail to create command encoder")

        val renderPassEncoder = WGPURenderPassDescriptor.allocate(scope).apply {
            colorAttachmentCount = 1u
            colorAttachments = WGPURenderPassColorAttachment.allocateArray(scope, 1u) { _, attachment ->
                attachment.view = textureView
                attachment.loadOp = WGPULoadOp_Clear
                attachment.storeOp = WGPUStoreOp_Store
                attachment.depthSlice = UInt.MAX_VALUE
                attachment.clearValue.r = 0.0
                attachment.clearValue.g = 1.0
                attachment.clearValue.b = 0.0
                attachment.clearValue.a = 1.0
            }.let { WGPURenderPassColorAttachment(it.handler) }
        }.let { wgpuCommandEncoderBeginRenderPass(commandEncoder, it) }

        wgpuRenderPassEncoderSetPipeline(renderPassEncoder, pipeline)
        wgpuRenderPassEncoderDraw(renderPassEncoder, 3u, 1u, 0u, 0u)
        wgpuRenderPassEncoderEnd(renderPassEncoder)
        wgpuRenderPassEncoderRelease(renderPassEncoder)

        val bytesPerPixel = 4u
        val unpaddedBytesPerRow = width.toUInt() * bytesPerPixel
        val bytesPerRow = alignTo(unpaddedBytesPerRow, 256u)
        val bufferSize = bytesPerRow.toULong() * height.toULong()
        val readbackBuffer = WGPUBufferDescriptor.allocate(scope).apply {
            usage = WGPUBufferUsage_MapRead or WGPUBufferUsage_CopyDst
            size = bufferSize
            mappedAtCreation = 0u
        }.let { wgpuDeviceCreateBuffer(device, it) } ?: error("fail to create readback buffer")

        wgpuCommandEncoderCopyTextureToBuffer(
            commandEncoder,
            WGPURenderSourceTexture(scope, texture),
            WGPURenderDestinationBuffer(scope, readbackBuffer, bytesPerRow, height.toUInt()),
            WGPUExtent3D.allocate(scope).apply {
                this.width = width.toUInt()
                this.height = height.toUInt()
                depthOrArrayLayers = 1u
            }
        )

        val commandBuffer = wgpuCommandEncoderFinish(commandEncoder, null) ?: error("fail to finish command buffer")
        wgpuQueueSubmit(queue, 1u, scope.bufferOfAddress(commandBuffer.handler).let { WGPUCommandBuffer(it.handler) })

        val mapCallback = WGPUBufferMapCallback.allocate { status, message, _, _ ->
            if (status != WGPUMapAsyncStatus_Success) {
                val details = message.data?.toKString(message.length)
                error("map failed with status $status: $details")
            }
            mapStatus = status
        }
        val callbackInfo = WGPUBufferMapCallbackInfo.allocate(scope).apply {
            mode = WGPUCallbackMode_AllowProcessEvents
            callback = mapCallback.handler
            userdata2 = scope.bufferOfAddress(mapCallback.handler).handler
        }

        wgpuBufferMapAsync(readbackBuffer, WGPUMapMode_Read, 0uL, bufferSize, callbackInfo)

        var attempts = 0
        while (mapStatus == null && attempts++ < 10_000) {
            wgpuInstanceProcessEvents(instance)
        }
        check(mapStatus == WGPUMapAsyncStatus_Success) { "buffer map did not complete" }

        val mapped = wgpuBufferGetConstMappedRange(readbackBuffer, 0uL, bufferSize)
            ?: error("fail to get mapped range")
        val mappedBuffer = MemoryBuffer(mapped, bufferSize)
        ByteArray(width * height * 4).also { pixels ->
            for (row in 0 until height) {
                mappedBuffer.readBytes(
                    pixels,
                    arrayIndex = (row * width * 4).toULong(),
                    bufferOffset = (row.toUInt() * bytesPerRow).toULong(),
                    size = (width * 4).toULong()
                )
            }
            wgpuBufferUnmap(readbackBuffer)
            wgpuBufferRelease(readbackBuffer)
            wgpuCommandBufferRelease(commandBuffer)
            wgpuCommandEncoderRelease(commandEncoder)
            mapCallback.close()
            wgpuRenderPipelineRelease(pipeline)
            wgpuTextureViewRelease(textureView)
            wgpuTextureRelease(texture)
        }
    }

    return HeadlessTriangleImage(width, height, pixels)
}

private fun createHeadlessTrianglePipeline(
    device: WGPUDevice,
    format: WGPUTextureFormat,
    scope: MemoryAllocator
): WGPURenderPipeline = WGPURenderPipelineDescriptor.allocate(scope).apply {
    vertex.module = WGPUShaderModuleDescriptor.allocate(scope).apply {
        nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
            code.length = triangleVertexShader.length.toULong()
            code.data = scope.allocateFrom(triangleVertexShader)
            chain.sType = WGPUSType_ShaderSourceWGSL
        }.chain
    }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create vertex shader module")

    vertex.entryPoint.data = scope.allocateFrom("main")
    vertex.entryPoint.length = "main".length.toULong()

    fragment = WGPUFragmentState.allocate(scope).apply {
        entryPoint.data = scope.allocateFrom("main")
        entryPoint.length = "main".length.toULong()
        module = WGPUShaderModuleDescriptor.allocate(scope).apply {
            nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                code.length = redFragmentShader.length.toULong()
                code.data = scope.allocateFrom(redFragmentShader)
                chain.sType = WGPUSType_ShaderSourceWGSL
            }.chain
        }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create fragment shader module")

        targetCount = 1u
        targets = WGPUColorTargetState.allocateArray(scope, 1u) { _, target ->
            target.format = format
            target.writeMask = WGPUColorWriteMask_All
        }.let { WGPUColorTargetState(it.handler) }
    }

    primitive.topology = WGPUPrimitiveTopology_TriangleList
    multisample.count = 1u
    multisample.mask = 0xFFFFFFFFu
}.let { wgpuDeviceCreateRenderPipeline(device, it) ?: error("fail to create render pipeline") }

private fun WGPURenderSourceTexture(scope: MemoryAllocator, texture: WGPUTexture): WGPUTexelCopyTextureInfo =
    WGPUTexelCopyTextureInfo.allocate(scope).apply {
        this.texture = texture
        mipLevel = 0u
        origin.x = 0u
        origin.y = 0u
        origin.z = 0u
        aspect = WGPUTextureAspect_All
    }

private fun WGPURenderDestinationBuffer(
    scope: MemoryAllocator,
    buffer: WGPUBuffer,
    bytesPerRow: UInt,
    rowsPerImage: UInt
): WGPUTexelCopyBufferInfo = WGPUTexelCopyBufferInfo.allocate(scope).apply {
    this.buffer = buffer
    layout.offset = 0uL
    layout.bytesPerRow = bytesPerRow
    layout.rowsPerImage = rowsPerImage
}

private fun alignTo(value: UInt, alignment: UInt): UInt =
    ((value + alignment - 1u) / alignment) * alignment
