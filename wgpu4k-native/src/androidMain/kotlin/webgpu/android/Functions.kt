// This file has been generated DO NOT EDIT !!!
package webgpu


internal interface FunctionsInterface: com.sun.jna.Library {
	fun wgpuCreateInstance(descriptor: Long): Long
	fun wgpuGetInstanceFeatures(features: Long): Unit
	fun wgpuAdapterGetLimits(handler: Long, limits: Long): UInt
	fun wgpuAdapterHasFeature(handler: Long, feature: UInt): UInt
	fun wgpuAdapterEnumerateFeatures(handler: Long, features: Long): ULong
	fun wgpuAdapterGetInfo(handler: Long, info: Long): Unit
	fun wgpuAdapterRequestDevice(handler: Long, descriptor: Long, callbackInfo: WGPURequestDeviceCallbackInfoByValue): Unit
	fun wgpuBindGroupSetLabel(handler: Long, label: Long): Unit
	fun wgpuBindGroupLayoutSetLabel(handler: Long, label: Long): Unit
	fun wgpuBufferMapAsync(handler: Long, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfoByValue): Unit
	fun wgpuBufferGetMappedRange(handler: Long, offset: ULong, size: ULong): Long
	fun wgpuBufferGetConstMappedRange(handler: Long, offset: ULong, size: ULong): Long
	fun wgpuBufferSetLabel(handler: Long, label: Long): Unit
	fun wgpuBufferGetUsage(handler: Long): ULong
	fun wgpuBufferGetSize(handler: Long): ULong
	fun wgpuBufferGetMapState(handler: Long): UInt
	fun wgpuBufferUnmap(handler: Long): Unit
	fun wgpuBufferDestroy(handler: Long): Unit
	fun wgpuCommandBufferSetLabel(handler: Long, label: Long): Unit
	fun wgpuCommandEncoderFinish(handler: Long, descriptor: Long): Long
	fun wgpuCommandEncoderBeginComputePass(handler: Long, descriptor: Long): Long
	fun wgpuCommandEncoderBeginRenderPass(handler: Long, descriptor: Long): Long
	fun wgpuCommandEncoderCopyBufferToBuffer(handler: Long, source: Long, sourceOffset: ULong, destination: Long, destinationOffset: ULong, size: ULong): Unit
	fun wgpuCommandEncoderCopyBufferToTexture(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	fun wgpuCommandEncoderCopyTextureToBuffer(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	fun wgpuCommandEncoderCopyTextureToTexture(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	fun wgpuCommandEncoderClearBuffer(handler: Long, buffer: Long, offset: ULong, size: ULong): Unit
	fun wgpuCommandEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	fun wgpuCommandEncoderPopDebugGroup(handler: Long): Unit
	fun wgpuCommandEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	fun wgpuCommandEncoderResolveQuerySet(handler: Long, querySet: Long, firstQuery: UInt, queryCount: UInt, destination: Long, destinationOffset: ULong): Unit
	fun wgpuCommandEncoderWriteTimestamp(handler: Long, querySet: Long, queryIndex: UInt): Unit
	fun wgpuCommandEncoderSetLabel(handler: Long, label: Long): Unit
	fun wgpuComputePassEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	fun wgpuComputePassEncoderPopDebugGroup(handler: Long): Unit
	fun wgpuComputePassEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	fun wgpuComputePassEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	fun wgpuComputePassEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsetCount: ULong, dynamicOffsets: Long): Unit
	fun wgpuComputePassEncoderDispatchWorkgroups(handler: Long, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	fun wgpuComputePassEncoderEnd(handler: Long): Unit
	fun wgpuComputePassEncoderSetLabel(handler: Long, label: Long): Unit
	fun wgpuComputePipelineGetBindGroupLayout(handler: Long, groupIndex: UInt): Long
	fun wgpuComputePipelineSetLabel(handler: Long, label: Long): Unit
	fun wgpuDeviceCreateBindGroup(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateBindGroupLayout(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateBuffer(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateCommandEncoder(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateComputePipeline(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateComputePipelineAsync(handler: Long, descriptor: Long, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfoByValue): Unit
	fun wgpuDeviceCreatePipelineLayout(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateQuerySet(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateRenderPipelineAsync(handler: Long, descriptor: Long, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfoByValue): Unit
	fun wgpuDeviceCreateRenderBundleEncoder(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateRenderPipeline(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateSampler(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateShaderModule(handler: Long, descriptor: Long): Long
	fun wgpuDeviceCreateTexture(handler: Long, descriptor: Long): Long
	fun wgpuDeviceDestroy(handler: Long): Unit
	fun wgpuDeviceGetLimits(handler: Long, limits: Long): UInt
	fun wgpuDeviceHasFeature(handler: Long, feature: UInt): UInt
	fun wgpuDeviceEnumerateFeatures(handler: Long, features: Long): ULong
	fun wgpuDeviceGetQueue(handler: Long): Long
	fun wgpuDevicePushErrorScope(handler: Long, filter: UInt): Unit
	fun wgpuDevicePopErrorScope(handler: Long, callbackInfo: WGPUPopErrorScopeCallbackInfoByValue): Unit
	fun wgpuDeviceSetLabel(handler: Long, label: Long): Unit
	fun wgpuInstanceCreateSurface(handler: Long, descriptor: Long): Long
	fun wgpuInstanceHasWGSLLanguageFeature(handler: Long, feature: UInt): UInt
	fun wgpuInstanceProcessEvents(handler: Long): Unit
	fun wgpuInstanceRequestAdapter(handler: Long, options: Long, callbackInfo: WGPURequestAdapterCallbackInfoByValue): Unit
	fun wgpuInstanceWaitAny(handler: Long, futureCount: ULong, futures: Long, timeoutNS: ULong): UInt
	fun wgpuPipelineLayoutSetLabel(handler: Long, label: Long): Unit
	fun wgpuQuerySetSetLabel(handler: Long, label: Long): Unit
	fun wgpuQuerySetGetType(handler: Long): UInt
	fun wgpuQuerySetGetCount(handler: Long): UInt
	fun wgpuQuerySetDestroy(handler: Long): Unit
	fun wgpuQueueSubmit(handler: Long, commandCount: ULong, commands: Long): Unit
	fun wgpuQueueOnSubmittedWorkDone(handler: Long, callbackInfo: WGPUQueueWorkDoneCallbackInfoByValue): Unit
	fun wgpuQueueWriteBuffer(handler: Long, buffer: Long, bufferOffset: ULong, data: Long, size: ULong): Unit
	fun wgpuQueueWriteTexture(handler: Long, destination: Long, data: Long, dataSize: ULong, dataLayout: Long, writeSize: Long): Unit
	fun wgpuQueueSetLabel(handler: Long, label: Long): Unit
	fun wgpuRenderBundleSetLabel(handler: Long, label: Long): Unit
	fun wgpuRenderBundleEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	fun wgpuRenderBundleEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsetCount: ULong, dynamicOffsets: Long): Unit
	fun wgpuRenderBundleEncoderDraw(handler: Long, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	fun wgpuRenderBundleEncoderDrawIndexed(handler: Long, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	fun wgpuRenderBundleEncoderDrawIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	fun wgpuRenderBundleEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	fun wgpuRenderBundleEncoderPopDebugGroup(handler: Long): Unit
	fun wgpuRenderBundleEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	fun wgpuRenderBundleEncoderSetVertexBuffer(handler: Long, slot: UInt, buffer: Long, offset: ULong, size: ULong): Unit
	fun wgpuRenderBundleEncoderSetIndexBuffer(handler: Long, buffer: Long, format: UInt, offset: ULong, size: ULong): Unit
	fun wgpuRenderBundleEncoderFinish(handler: Long, descriptor: Long): Long
	fun wgpuRenderBundleEncoderSetLabel(handler: Long, label: Long): Unit
	fun wgpuRenderPassEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	fun wgpuRenderPassEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsetCount: ULong, dynamicOffsets: Long): Unit
	fun wgpuRenderPassEncoderDraw(handler: Long, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	fun wgpuRenderPassEncoderDrawIndexed(handler: Long, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	fun wgpuRenderPassEncoderDrawIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	fun wgpuRenderPassEncoderExecuteBundles(handler: Long, bundleCount: ULong, bundles: Long): Unit
	fun wgpuRenderPassEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	fun wgpuRenderPassEncoderPopDebugGroup(handler: Long): Unit
	fun wgpuRenderPassEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	fun wgpuRenderPassEncoderSetStencilReference(handler: Long, reference: UInt): Unit
	fun wgpuRenderPassEncoderSetBlendConstant(handler: Long, color: Long): Unit
	fun wgpuRenderPassEncoderSetViewport(handler: Long, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	fun wgpuRenderPassEncoderSetScissorRect(handler: Long, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	fun wgpuRenderPassEncoderSetVertexBuffer(handler: Long, slot: UInt, buffer: Long, offset: ULong, size: ULong): Unit
	fun wgpuRenderPassEncoderSetIndexBuffer(handler: Long, buffer: Long, format: UInt, offset: ULong, size: ULong): Unit
	fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: Long, queryIndex: UInt): Unit
	fun wgpuRenderPassEncoderEndOcclusionQuery(handler: Long): Unit
	fun wgpuRenderPassEncoderEnd(handler: Long): Unit
	fun wgpuRenderPassEncoderSetLabel(handler: Long, label: Long): Unit
	fun wgpuRenderPipelineGetBindGroupLayout(handler: Long, groupIndex: UInt): Long
	fun wgpuRenderPipelineSetLabel(handler: Long, label: Long): Unit
	fun wgpuSamplerSetLabel(handler: Long, label: Long): Unit
	fun wgpuShaderModuleGetCompilationInfo(handler: Long, callbackInfo: WGPUCompilationInfoCallbackInfoByValue): Unit
	fun wgpuShaderModuleSetLabel(handler: Long, label: Long): Unit
	fun wgpuSurfaceConfigure(handler: Long, config: Long): Unit
	fun wgpuSurfaceGetCapabilities(handler: Long, adapter: Long, capabilities: Long): UInt
	fun wgpuSurfaceGetCurrentTexture(handler: Long, surfaceTexture: Long): Unit
	fun wgpuSurfacePresent(handler: Long): Unit
	fun wgpuSurfaceUnconfigure(handler: Long): Unit
	fun wgpuSurfaceSetLabel(handler: Long, label: Long): Unit
	fun wgpuTextureCreateView(handler: Long, descriptor: Long): Long
	fun wgpuTextureSetLabel(handler: Long, label: Long): Unit
	fun wgpuTextureGetWidth(handler: Long): UInt
	fun wgpuTextureGetHeight(handler: Long): UInt
	fun wgpuTextureGetDepthOrArrayLayers(handler: Long): UInt
	fun wgpuTextureGetMipLevelCount(handler: Long): UInt
	fun wgpuTextureGetSampleCount(handler: Long): UInt
	fun wgpuTextureGetDimension(handler: Long): UInt
	fun wgpuTextureGetFormat(handler: Long): UInt
	fun wgpuTextureGetUsage(handler: Long): ULong
	fun wgpuTextureDestroy(handler: Long): Unit
	fun wgpuTextureViewSetLabel(handler: Long, label: Long): Unit
}
internal val Functions = com.sun.jna.Native.load("wgpu4k", FunctionsInterface::class.java)
