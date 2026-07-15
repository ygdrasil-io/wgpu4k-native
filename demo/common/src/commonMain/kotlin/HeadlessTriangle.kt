@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.MemoryBuffer
import io.ygdrasil.kffi.memoryScope

data class HeadlessTriangleImage(
    val width: Int,
    val height: Int,
    val rgba: ByteArray
)

fun renderHeadlessTriangle(width: Int = 64, height: Int = 64): HeadlessTriangleImage {
    val cleanup = GpuCleanupStack()
    var primaryFailure: Throwable? = null
    var instance: WGPUInstance? = null
    var adapter: WGPUAdapter? = null
    var device: WGPUDevice? = null
    var queue: WGPUQueue? = null
    var texture: WGPUTexture? = null
    var textureView: WGPUTextureView? = null
    var pipeline: WGPURenderPipeline? = null
    var commandEncoder: WGPUCommandEncoder? = null
    var renderPassEncoder: WGPURenderPassEncoder? = null
    var readbackBuffer: WGPUBuffer? = null
    var commandBuffer: WGPUCommandBuffer? = null
    var mapped = false

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

        val format = WGPUTextureFormat_RGBA8Unorm
        val mapResult = CallbackOutcomeState<CallbackDiagnostic>()
        val pixels = memoryScope { scope ->
            val ownedTexture = WGPUTextureDescriptor.allocate(scope).apply {
                usage = WGPUTextureUsage_RenderAttachment or WGPUTextureUsage_CopySrc
                dimension = WGPUTextureDimension_2D
                size.width = width.toUInt()
                size.height = height.toUInt()
                size.depthOrArrayLayers = 1u
                this.format = format
                mipLevelCount = 1u
                sampleCount = 1u
            }.let { wgpuDeviceCreateTexture(ownedDevice, it) } ?: error("fail to create texture")
            texture = ownedTexture
            cleanup.defer {
                texture?.let(::wgpuTextureRelease)
                texture = null
            }

            val ownedTextureView = WGPUTextureViewDescriptor.allocate(scope).apply {
                this.format = format
                dimension = WGPUTextureViewDimension_2D
                baseMipLevel = 0u
                mipLevelCount = 1u
                baseArrayLayer = 0u
                arrayLayerCount = 1u
                aspect = WGPUTextureAspect_All
                usage = WGPUTextureUsage_RenderAttachment
            }.let { wgpuTextureCreateView(ownedTexture, it) } ?: error("fail to create texture view")
            textureView = ownedTextureView
            cleanup.defer {
                textureView?.let(::wgpuTextureViewRelease)
                textureView = null
            }

            val ownedPipeline = createHeadlessTrianglePipeline(ownedDevice, format, scope)
            pipeline = ownedPipeline
            cleanup.defer {
                pipeline?.let(::wgpuRenderPipelineRelease)
                pipeline = null
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

            val bytesPerPixel = 4u
            val unpaddedBytesPerRow = width.toUInt() * bytesPerPixel
            val bytesPerRow = alignTo(unpaddedBytesPerRow, 256u)
            val bufferSize = bytesPerRow.toULong() * height.toULong()
            val ownedReadbackBuffer = WGPUBufferDescriptor.allocate(scope).apply {
                usage = WGPUBufferUsage_MapRead or WGPUBufferUsage_CopyDst
                size = bufferSize
                mappedAtCreation = 0u
            }.let { wgpuDeviceCreateBuffer(ownedDevice, it) } ?: error("fail to create readback buffer")
            readbackBuffer = ownedReadbackBuffer
            cleanup.defer {
                readbackBuffer?.let { buffer ->
                    if (mapped) wgpuBufferUnmap(buffer)
                    wgpuBufferRelease(buffer)
                }
                readbackBuffer = null
            }

            wgpuCommandEncoderCopyTextureToBuffer(
                ownedCommandEncoder,
                WGPURenderSourceTexture(scope, ownedTexture),
                WGPURenderDestinationBuffer(scope, ownedReadbackBuffer, bytesPerRow, height.toUInt()),
                WGPUExtent3D.allocate(scope).apply {
                    this.width = width.toUInt()
                    this.height = height.toUInt()
                    depthOrArrayLayers = 1u
                },
            )

            val ownedCommandBuffer = wgpuCommandEncoderFinish(ownedCommandEncoder, null)
                ?: error("fail to finish command buffer")
            commandBuffer = ownedCommandBuffer
            cleanup.defer {
                commandBuffer?.let(::wgpuCommandBufferRelease)
                commandBuffer = null
            }
            wgpuQueueSubmit(ownedQueue, 1u, scope.bufferOfAddress(ownedCommandBuffer.handler).handler)

            val mapCallback = WGPUBufferMapCallback.register(CallbackPolicy.ONCE) { status, message, _ ->
                mapResult.publishCatching {
                    CallbackDiagnostic(status, message.data?.toKString(message.length))
                }
            }
            cleanup.defer {
                closeAndAwaitCallbackQuiescence(
                    phase = "headless-buffer-map-final-close",
                    close = mapCallback::close,
                    isClosed = { mapCallback.isClosed },
                    isQuiescent = { mapCallback.isQuiescent },
                    applicationInFlight = { 0 },
                    pump = { waitForHeadlessMapEvent(ownedInstance) },
                )
            }
            val callbackInfo = WGPUBufferMapCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_AllowProcessEvents,
                mapCallback,
            )

            wgpuBufferMapAsync(ownedReadbackBuffer, WGPUMapMode_Read, 0uL, bufferSize, callbackInfo)

            val result = awaitMapCallbackResult(
                phase = "headless-buffer-map",
                result = mapResult::outcome,
                close = mapCallback::close,
                isClosed = { mapCallback.isClosed },
                isQuiescent = { mapCallback.isQuiescent },
                pump = { waitForHeadlessMapEvent(ownedInstance) },
            )
            requireSuccessfulMapResult(result.status, result.message)
            mapped = true

            val mappedAddress = wgpuBufferGetConstMappedRange(ownedReadbackBuffer, 0uL, bufferSize)
                ?: error("fail to get mapped range")
            val mappedBuffer = MemoryBuffer(mappedAddress, bufferSize)
            ByteArray(width * height * 4).also { output ->
                for (row in 0 until height) {
                    mappedBuffer.readBytes(
                        output,
                        arrayIndex = (row * width * 4).toULong(),
                        bufferOffset = (row.toUInt() * bytesPerRow).toULong(),
                        size = (width * 4).toULong(),
                    )
                }
            }
        }
        return HeadlessTriangleImage(width, height, pixels)
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        cleanup.close(primaryFailure)
    }
}

fun requireSuccessfulMapResult(status: WGPUMapAsyncStatus?, message: String?) {
    when {
        status == null -> error("buffer map did not complete")
        status != WGPUMapAsyncStatus_Success ->
            error("map failed with status $status${message?.takeIf { it.isNotEmpty() }?.let { ": $it" }.orEmpty()}")
    }
}

expect fun waitForHeadlessMapEvent(instance: WGPUInstance)

private fun createHeadlessTrianglePipeline(
    device: WGPUDevice,
    format: WGPUTextureFormat,
    scope: MemoryAllocator
): WGPURenderPipeline {
    val cleanup = GpuCleanupStack()
    var primaryFailure: Throwable? = null
    try {
        val vertexModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
            nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                code.length = triangleVertexShader.length.toULong()
                code.data = scope.allocateFrom(triangleVertexShader)
                chain.sType = WGPUSType_ShaderSourceWGSL
            }.chain
        }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create vertex shader module")
        cleanup.defer { wgpuShaderModuleRelease(vertexModule) }

        val fragmentModule = WGPUShaderModuleDescriptor.allocate(scope).apply {
            nextInChain = WGPUShaderSourceWGSL.allocate(scope).apply {
                code.length = redFragmentShader.length.toULong()
                code.data = scope.allocateFrom(redFragmentShader)
                chain.sType = WGPUSType_ShaderSourceWGSL
            }.chain
        }.let { wgpuDeviceCreateShaderModule(device, it) } ?: error("fail to create fragment shader module")
        cleanup.defer { wgpuShaderModuleRelease(fragmentModule) }

        return WGPURenderPipelineDescriptor.allocate(scope).apply {
            vertex.module = vertexModule
            vertex.entryPoint.data = scope.allocateFrom("main")
            vertex.entryPoint.length = "main".length.toULong()

            fragment = WGPUFragmentState.allocate(scope).apply {
                entryPoint.data = scope.allocateFrom("main")
                entryPoint.length = "main".length.toULong()
                module = fragmentModule
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
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        cleanup.close(primaryFailure)
    }
}

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
