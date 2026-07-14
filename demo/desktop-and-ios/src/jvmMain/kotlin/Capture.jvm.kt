package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.MemoryBuffer
import ffi.LibraryLoader
import io.ygdrasil.kffi.memoryScope
import java.awt.image.BufferedImage
import java.io.File
import java.util.concurrent.atomic.AtomicReference
import javax.imageio.ImageIO

private const val CaptureWidth = 640
private const val CaptureHeight = 480
private const val BytesPerPixel = 4
private const val BytesPerRowAlignment = 256

fun captureFrame(path: String) = memoryScope { scope ->
    LibraryLoader.load()
    configureLogs(WGPULogLevel_Warn)

    val alignedBytesPerRow = align(CaptureWidth * BytesPerPixel, BytesPerRowAlignment)
    val bufferSize = (alignedBytesPerRow * CaptureHeight).toULong()

    val instance = wgpuCreateInstance(null) ?: error("fail to create instance")
    val adapter = getAdapter(surface = null, instance = instance)
    val device = getDevice(adapter)
    val queue = wgpuDeviceGetQueue(device) ?: error("fail to get queue")

    val texture = WGPUTextureDescriptor.allocate(scope).apply {
        usage = WGPUTextureUsage_RenderAttachment or WGPUTextureUsage_CopySrc
        dimension = WGPUTextureDimension_2D
        size.width = CaptureWidth.toUInt()
        size.height = CaptureHeight.toUInt()
        size.depthOrArrayLayers = 1u
        format = WGPUTextureFormat_RGBA8Unorm
        mipLevelCount = 1u
        sampleCount = 1u
    }.let { wgpuDeviceCreateTexture(device, it) } ?: error("fail to create capture texture")

    val textureView = wgpuTextureCreateView(texture, null) ?: error("fail to create capture texture view")
    val pipeline = createCapturePipeline(device)

    val readbackBuffer = WGPUBufferDescriptor.allocate(scope).apply {
        usage = WGPUBufferUsage_MapRead or WGPUBufferUsage_CopyDst
        size = bufferSize
        mappedAtCreation = 0u
    }.let { wgpuDeviceCreateBuffer(device, it) } ?: error("fail to create readback buffer")

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

    val source = WGPUTexelCopyTextureInfo.allocate(scope).apply {
        this.texture = texture
        mipLevel = 0u
        origin.x = 0u
        origin.y = 0u
        origin.z = 0u
        aspect = WGPUTextureAspect_All
    }
    val destination = WGPUTexelCopyBufferInfo.allocate(scope).apply {
        buffer = readbackBuffer
        layout.offset = 0u
        layout.bytesPerRow = alignedBytesPerRow.toUInt()
        layout.rowsPerImage = CaptureHeight.toUInt()
    }
    val copySize = WGPUExtent3D.allocate(scope).apply {
        width = CaptureWidth.toUInt()
        height = CaptureHeight.toUInt()
        depthOrArrayLayers = 1u
    }
    wgpuCommandEncoderCopyTextureToBuffer(commandEncoder, source, destination, copySize)

    val commandBuffer = wgpuCommandEncoderFinish(commandEncoder, null) ?: error("fail to finish command encoder")
    wgpuQueueSubmit(queue, 1u, scope.bufferOfAddress(commandBuffer.handler).let { WGPUCommandBuffer(it.handler) })

    mapBufferForRead(device, readbackBuffer, bufferSize)
    val mapped = wgpuBufferGetConstMappedRange(readbackBuffer, 0u, bufferSize)
        ?: error("fail to get mapped readback range")
    val bytes = ByteArray(bufferSize.toInt())
    MemoryBuffer(mapped, bufferSize).readBytes(bytes)
    writePng(bytes, alignedBytesPerRow, path)

    wgpuBufferUnmap(readbackBuffer)
    wgpuCommandBufferRelease(commandBuffer)
    wgpuCommandEncoderRelease(commandEncoder)
    wgpuBufferRelease(readbackBuffer)
    wgpuRenderPipelineRelease(pipeline)
    wgpuTextureViewRelease(textureView)
    wgpuTextureRelease(texture)
    wgpuDeviceRelease(device)
    wgpuAdapterRelease(adapter)
    wgpuInstanceRelease(instance)
}

fun verifyCapture(path: String) {
    val image = ImageIO.read(File(path)) ?: error("fail to read capture at $path")
    require(image.width == CaptureWidth && image.height == CaptureHeight) {
        "capture size mismatch: ${image.width}x${image.height}, expected ${CaptureWidth}x${CaptureHeight}"
    }

    val greenSamples = listOf(
        32 to 32,
        CaptureWidth - 33 to 32,
        32 to CaptureHeight / 2,
        CaptureWidth - 33 to CaptureHeight / 2,
    )
    val redSamples = listOf(
        CaptureWidth / 2 to CaptureHeight / 4,
        CaptureWidth / 2 to CaptureHeight / 2,
        CaptureWidth / 3 to (CaptureHeight * 3 / 4),
        CaptureWidth * 2 / 3 to (CaptureHeight * 3 / 4),
    )

    greenSamples.forEach { (x, y) ->
        val color = image.getRGB(x, y)
        require(isGreen(color)) { "expected green at ($x,$y), got ${color.toUInt().toString(16)}" }
    }
    redSamples.forEach { (x, y) ->
        val color = image.getRGB(x, y)
        require(isRed(color)) { "expected red at ($x,$y), got ${color.toUInt().toString(16)}" }
    }
}

private fun createCapturePipeline(device: WGPUDevice) = memoryScope { scope ->
    WGPURenderPipelineDescriptor.allocate(scope).apply {
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
                target.format = WGPUTextureFormat_RGBA8Unorm
                target.writeMask = WGPUColorWriteMask_All
            }.let { WGPUColorTargetState(it.handler) }
        }

        primitive.topology = WGPUPrimitiveTopology_TriangleList
        multisample.count = 1u
        multisample.mask = 0xFFFFFFFFu
    }.let { wgpuDeviceCreateRenderPipeline(device, it) } ?: error("fail to create render pipeline")
}

private fun mapBufferForRead(device: WGPUDevice, buffer: WGPUBuffer, size: ULong) = memoryScope { scope ->
    val status = AtomicReference<WGPUMapAsyncStatus?>()
    val callback = WGPUBufferMapCallback.register(CallbackPolicy.ONCE) { mapStatus, _, _ ->
        status.set(mapStatus)
    }

    try {
        val callbackInfo = WGPUBufferMapCallbackInfo.allocate(
            scope,
            WGPUCallbackMode_AllowSpontaneous,
            callback,
        )

        wgpuBufferMapAsync(buffer, WGPUMapMode_Read, 0u, size, callbackInfo)
        while (status.get() == null) {
            wgpuDevicePoll(device, 1u, null)
        }

        val result = status.get()
        if (result != WGPUMapAsyncStatus_Success) {
            error("mapAsync failed with status $result")
        }
    } finally {
        callback.close()
    }
}

private fun writePng(bytes: ByteArray, bytesPerRow: Int, path: String) {
    val image = BufferedImage(CaptureWidth, CaptureHeight, BufferedImage.TYPE_INT_ARGB)
    for (y in 0 until CaptureHeight) {
        val rowStart = y * bytesPerRow
        for (x in 0 until CaptureWidth) {
            val i = rowStart + x * BytesPerPixel
            val r = bytes[i].toInt() and 0xFF
            val g = bytes[i + 1].toInt() and 0xFF
            val b = bytes[i + 2].toInt() and 0xFF
            val a = bytes[i + 3].toInt() and 0xFF
            image.setRGB(x, y, (a shl 24) or (r shl 16) or (g shl 8) or b)
        }
    }

    val output = File(path)
    output.parentFile?.mkdirs()
    ImageIO.write(image, "png", output)
}

private fun align(value: Int, alignment: Int): Int =
    ((value + alignment - 1) / alignment) * alignment

private fun isGreen(argb: Int): Boolean {
    val r = (argb ushr 16) and 0xFF
    val g = (argb ushr 8) and 0xFF
    val b = argb and 0xFF
    return r <= 8 && g >= 240 && b <= 8
}

private fun isRed(argb: Int): Boolean {
    val r = (argb ushr 16) and 0xFF
    val g = (argb ushr 8) and 0xFF
    val b = argb and 0xFF
    return r >= 240 && g <= 8 && b <= 8
}
