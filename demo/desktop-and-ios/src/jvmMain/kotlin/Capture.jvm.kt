package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.MemoryBuffer
import io.ygdrasil.kffi.memoryScope
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

private const val CaptureWidth = 640
private const val CaptureHeight = 480
private const val BytesPerPixel = 4
private const val BytesPerRowAlignment = 256

fun captureFrame(path: String) = memoryScope { scope ->
    configureLogs(WGPULogLevel_Warn)

    val alignedBytesPerRow = align(CaptureWidth * BytesPerPixel, BytesPerRowAlignment)
    val bufferSize = (alignedBytesPerRow * CaptureHeight).toULong()

    val cleanup = GpuCleanupStack()
    var primaryFailure: Throwable? = null
    var instance: WGPUInstance? = null
    var adapter: WGPUAdapter? = null
    var device: WGPUDevice? = null
    var queue: WGPUQueue? = null
    var texture: WGPUTexture? = null
    var textureView: WGPUTextureView? = null
    var pipeline: WGPURenderPipeline? = null
    var readbackBuffer: WGPUBuffer? = null
    var readbackBufferMapped = false
    var commandEncoder: WGPUCommandEncoder? = null
    var renderPassEncoder: WGPURenderPassEncoder? = null
    var commandBuffer: WGPUCommandBuffer? = null

    try {
        val ownedInstance = wgpuCreateInstance(null) ?: error("fail to create instance")
        instance = ownedInstance
        cleanup.defer {
            instance?.let(::wgpuInstanceRelease)
            instance = null
        }

        val ownedAdapter = getAdapter(surface = null, instance = ownedInstance)
        adapter = ownedAdapter
        cleanup.defer {
            adapter?.let(::wgpuAdapterRelease)
            adapter = null
        }

        val ownedDevice = getDevice(ownedAdapter, ownedInstance)
        device = ownedDevice
        cleanup.defer {
            device?.let(::wgpuDeviceRelease)
            device = null
        }

        val ownedQueue = wgpuDeviceGetQueue(ownedDevice) ?: error("fail to get queue")
        queue = ownedQueue
        cleanup.defer {
            queue?.let(::wgpuQueueRelease)
            queue = null
        }

        val ownedTexture = WGPUTextureDescriptor.allocate(scope).apply {
            usage = WGPUTextureUsage_RenderAttachment or WGPUTextureUsage_CopySrc
            dimension = WGPUTextureDimension_2D
            size.width = CaptureWidth.toUInt()
            size.height = CaptureHeight.toUInt()
            size.depthOrArrayLayers = 1u
            format = WGPUTextureFormat_RGBA8Unorm
            mipLevelCount = 1u
            sampleCount = 1u
        }.let { wgpuDeviceCreateTexture(ownedDevice, it) } ?: error("fail to create capture texture")
        texture = ownedTexture
        cleanup.defer {
            texture?.let(::wgpuTextureRelease)
            texture = null
        }

        val ownedTextureView = wgpuTextureCreateView(ownedTexture, null)
            ?: error("fail to create capture texture view")
        textureView = ownedTextureView
        cleanup.defer {
            textureView?.let(::wgpuTextureViewRelease)
            textureView = null
        }

        val ownedPipeline = createCapturePipeline(ownedDevice)
        pipeline = ownedPipeline
        cleanup.defer {
            pipeline?.let(::wgpuRenderPipelineRelease)
            pipeline = null
        }

        val ownedReadbackBuffer = WGPUBufferDescriptor.allocate(scope).apply {
            usage = WGPUBufferUsage_MapRead or WGPUBufferUsage_CopyDst
            size = bufferSize
            mappedAtCreation = 0u
        }.let { wgpuDeviceCreateBuffer(ownedDevice, it) } ?: error("fail to create readback buffer")
        readbackBuffer = ownedReadbackBuffer
        cleanup.defer {
            if (readbackBufferMapped) {
                readbackBuffer?.let(::wgpuBufferUnmap)
                readbackBufferMapped = false
            }
            readbackBuffer?.let(::wgpuBufferRelease)
            readbackBuffer = null
        }

        val ownedCommandEncoder = wgpuDeviceCreateCommandEncoder(ownedDevice, null)
            ?: error("fail to create command encoder")
        commandEncoder = ownedCommandEncoder
        cleanup.defer {
            commandEncoder?.let(::wgpuCommandEncoderRelease)
            commandEncoder = null
        }

        val ownedRenderPassEncoder = WGPURenderPassDescriptor.allocate(scope).apply {
            colorAttachmentCount = 1u
            colorAttachments = WGPURenderPassColorAttachment.allocateArray(scope, 1u) { _, attachment ->
                attachment.view = ownedTextureView
                attachment.loadOp = WGPULoadOp_Clear
                attachment.storeOp = WGPUStoreOp_Store
                attachment.depthSlice = UInt.MAX_VALUE
                attachment.clearValue.r = 0.0
                attachment.clearValue.g = 1.0
                attachment.clearValue.b = 0.0
                attachment.clearValue.a = 1.0
            }.let { WGPURenderPassColorAttachment(it.handler) }
        }.let { wgpuCommandEncoderBeginRenderPass(ownedCommandEncoder, it) }
        renderPassEncoder = ownedRenderPassEncoder
        cleanup.defer {
            renderPassEncoder?.let(::wgpuRenderPassEncoderRelease)
            renderPassEncoder = null
        }

        wgpuRenderPassEncoderSetPipeline(ownedRenderPassEncoder, ownedPipeline)
        wgpuRenderPassEncoderDraw(ownedRenderPassEncoder, 3u, 1u, 0u, 0u)
        wgpuRenderPassEncoderEnd(ownedRenderPassEncoder)

        val source = WGPUTexelCopyTextureInfo.allocate(scope).apply {
            this.texture = ownedTexture
            mipLevel = 0u
            origin.x = 0u
            origin.y = 0u
            origin.z = 0u
            aspect = WGPUTextureAspect_All
        }
        val destination = WGPUTexelCopyBufferInfo.allocate(scope).apply {
            buffer = ownedReadbackBuffer
            layout.offset = 0u
            layout.bytesPerRow = alignedBytesPerRow.toUInt()
            layout.rowsPerImage = CaptureHeight.toUInt()
        }
        val copySize = WGPUExtent3D.allocate(scope).apply {
            width = CaptureWidth.toUInt()
            height = CaptureHeight.toUInt()
            depthOrArrayLayers = 1u
        }
        wgpuCommandEncoderCopyTextureToBuffer(ownedCommandEncoder, source, destination, copySize)

        val ownedCommandBuffer = wgpuCommandEncoderFinish(ownedCommandEncoder, null)
            ?: error("fail to finish command encoder")
        commandBuffer = ownedCommandBuffer
        cleanup.defer {
            commandBuffer?.let(::wgpuCommandBufferRelease)
            commandBuffer = null
        }
        wgpuQueueSubmit(ownedQueue, 1u, scope.bufferOfAddress(ownedCommandBuffer.handler).handler)

        mapBufferForRead(ownedDevice, ownedReadbackBuffer, bufferSize)
        readbackBufferMapped = true
        val mapped = wgpuBufferGetConstMappedRange(ownedReadbackBuffer, 0u, bufferSize)
            ?: error("fail to get mapped readback range")
        val bytes = ByteArray(bufferSize.toInt())
        MemoryBuffer(mapped, bufferSize).readBytes(bytes)
        writePng(bytes, alignedBytesPerRow, path)
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        cleanup.close(primaryFailure)
    }
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
    val cleanup = GpuCleanupStack()
    var primaryFailure: Throwable? = null
    var vertexShaderModule: WGPUShaderModule? = null
    var fragmentShaderModule: WGPUShaderModule? = null

    try {
        val ownedVertexShaderModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
            nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                code.length = triangleVertexShader.length.toULong()
                code.data = scope.allocateFrom(triangleVertexShader)
                chain.sType = WGPUSType_ShaderSourceWGSL
            }.chain
        }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create vertex shader module")
        vertexShaderModule = ownedVertexShaderModule
        cleanup.defer {
            vertexShaderModule?.let(::wgpuShaderModuleRelease)
            vertexShaderModule = null
        }

        val ownedFragmentShaderModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
            nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                code.length = redFragmentShader.length.toULong()
                code.data = scope.allocateFrom(redFragmentShader)
                chain.sType = WGPUSType_ShaderSourceWGSL
            }.chain
        }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create fragment shader module")
        fragmentShaderModule = ownedFragmentShaderModule
        cleanup.defer {
            fragmentShaderModule?.let(::wgpuShaderModuleRelease)
            fragmentShaderModule = null
        }

        WGPURenderPipelineDescriptor.allocate(scope).apply {
            vertex.module = ownedVertexShaderModule
            vertex.entryPoint.data = scope.allocateFrom("main")
            vertex.entryPoint.length = "main".length.toULong()

            fragment = WGPUFragmentState.allocate(scope).apply {
                entryPoint.data = scope.allocateFrom("main")
                entryPoint.length = "main".length.toULong()
                module = ownedFragmentShaderModule

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
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        cleanup.close(primaryFailure)
    }
}

private fun mapBufferForRead(device: WGPUDevice, buffer: WGPUBuffer, size: ULong) = memoryScope { scope ->
    val result = CallbackOutcomeState<CallbackDiagnostic>()
    val callback = WGPUBufferMapCallback.register(CallbackPolicy.ONCE) { mapStatus, message, _ ->
        result.publishCatching {
            CallbackDiagnostic(mapStatus, message.data?.toKString(message.length))
        }
    }
    val cleanup = GpuCleanupStack().apply {
        defer {
            closeAndAwaitCallbackQuiescence(
                phase = "capture-buffer-map-finalize",
                close = callback::close,
                isClosed = { callback.isClosed },
                isQuiescent = { callback.isQuiescent },
                applicationInFlight = { 0 },
                pump = { wgpuDevicePoll(device, 0u, null) },
            )
        }
    }
    var primaryFailure: Throwable? = null

    try {
        val callbackInfo = WGPUBufferMapCallbackInfo.allocate(
            scope,
            WGPUCallbackMode_AllowSpontaneous,
            callback,
        )

        wgpuBufferMapAsync(buffer, WGPUMapMode_Read, 0u, size, callbackInfo)
        val snapshot = awaitMapCallbackResult(
            phase = "capture-buffer-map",
            result = result::outcome,
            close = callback::close,
            isClosed = { callback.isClosed },
            isQuiescent = { callback.isQuiescent },
            pump = { wgpuDevicePoll(device, 0u, null) },
        )
        requireSuccessfulMapResult(snapshot.status, snapshot.message)
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        cleanup.close(primaryFailure)
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
