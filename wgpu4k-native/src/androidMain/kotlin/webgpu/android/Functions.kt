// This file has been generated DO NOT EDIT !!!
package webgpu.android


internal interface FunctionsInterface: com.sun.jna.Library {
	fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuAdapterRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuAdapterGetLimits(handler: com.sun.jna.Pointer?, limits: WGPUSupportedLimits.ByReference?): UInt
	fun wgpuAdapterHasFeature(handler: com.sun.jna.Pointer?, feature: UInt): UInt
	fun wgpuAdapterGetFeatures(handler: com.sun.jna.Pointer?, features: WGPUSupportedFeatures.ByReference?): UInt
	fun wgpuAdapterGetInfo(handler: com.sun.jna.Pointer?, info: WGPUAdapterInfo.ByReference?): Unit
	fun wgpuAdapterRequestDevice(handler: com.sun.jna.Pointer?, descriptor: WGPUDeviceDescriptor.ByReference?, callbackInfo: WGPURequestDeviceCallbackInfo.ByValue): Unit
	fun wgpuBindGroupRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuBindGroupSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuBindGroupLayoutRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuBindGroupLayoutSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuBufferRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuBufferMapAsync(handler: com.sun.jna.Pointer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo.ByValue): Unit
	fun wgpuBufferGetMappedRange(handler: com.sun.jna.Pointer?, offset: ULong, size: ULong): com.sun.jna.Pointer?
	fun wgpuBufferGetConstMappedRange(handler: com.sun.jna.Pointer?, offset: ULong, size: ULong): com.sun.jna.Pointer?
	fun wgpuBufferSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuBufferGetUsage(handler: com.sun.jna.Pointer?): ULong
	fun wgpuBufferGetSize(handler: com.sun.jna.Pointer?): ULong
	fun wgpuBufferGetMapState(handler: com.sun.jna.Pointer?): UInt
	fun wgpuBufferUnmap(handler: com.sun.jna.Pointer?): Unit
	fun wgpuBufferDestroy(handler: com.sun.jna.Pointer?): Unit
	fun wgpuCommandBufferRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuCommandBufferSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuCommandEncoderRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuCommandEncoderFinish(handler: com.sun.jna.Pointer?, descriptor: WGPUCommandBufferDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuCommandEncoderBeginComputePass(handler: com.sun.jna.Pointer?, descriptor: WGPUComputePassDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuCommandEncoderBeginRenderPass(handler: com.sun.jna.Pointer?, descriptor: WGPURenderPassDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuCommandEncoderCopyBufferToBuffer(handler: com.sun.jna.Pointer?, source: com.sun.jna.Pointer?, sourceOffset: ULong, destination: com.sun.jna.Pointer?, destinationOffset: ULong, size: ULong): Unit
	fun wgpuCommandEncoderCopyBufferToTexture(handler: com.sun.jna.Pointer?, source: WGPUImageCopyBuffer.ByReference?, destination: WGPUImageCopyTexture.ByReference?, copySize: WGPUExtent3D.ByReference?): Unit
	fun wgpuCommandEncoderCopyTextureToBuffer(handler: com.sun.jna.Pointer?, source: WGPUImageCopyTexture.ByReference?, destination: WGPUImageCopyBuffer.ByReference?, copySize: WGPUExtent3D.ByReference?): Unit
	fun wgpuCommandEncoderCopyTextureToTexture(handler: com.sun.jna.Pointer?, source: WGPUImageCopyTexture.ByReference?, destination: WGPUImageCopyTexture.ByReference?, copySize: WGPUExtent3D.ByReference?): Unit
	fun wgpuCommandEncoderClearBuffer(handler: com.sun.jna.Pointer?, buffer: com.sun.jna.Pointer?, offset: ULong, size: ULong): Unit
	fun wgpuCommandEncoderInsertDebugMarker(handler: com.sun.jna.Pointer?, markerLabel: WGPUStringView.ByValue): Unit
	fun wgpuCommandEncoderPopDebugGroup(handler: com.sun.jna.Pointer?): Unit
	fun wgpuCommandEncoderPushDebugGroup(handler: com.sun.jna.Pointer?, groupLabel: WGPUStringView.ByValue): Unit
	fun wgpuCommandEncoderResolveQuerySet(handler: com.sun.jna.Pointer?, querySet: com.sun.jna.Pointer?, firstQuery: UInt, queryCount: UInt, destination: com.sun.jna.Pointer?, destinationOffset: ULong): Unit
	fun wgpuCommandEncoderWriteTimestamp(handler: com.sun.jna.Pointer?, querySet: com.sun.jna.Pointer?, queryIndex: UInt): Unit
	fun wgpuCommandEncoderSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuComputePassEncoderRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuComputePassEncoderInsertDebugMarker(handler: com.sun.jna.Pointer?, markerLabel: WGPUStringView.ByValue): Unit
	fun wgpuComputePassEncoderPopDebugGroup(handler: com.sun.jna.Pointer?): Unit
	fun wgpuComputePassEncoderPushDebugGroup(handler: com.sun.jna.Pointer?, groupLabel: WGPUStringView.ByValue): Unit
	fun wgpuComputePassEncoderSetPipeline(handler: com.sun.jna.Pointer?, pipeline: com.sun.jna.Pointer?): Unit
	fun wgpuComputePassEncoderSetBindGroup(handler: com.sun.jna.Pointer?, groupIndex: UInt, group: com.sun.jna.Pointer?, dynamicOffsetCount: ULong, dynamicOffsets: com.sun.jna.Pointer?): Unit
	fun wgpuComputePassEncoderDispatchWorkgroups(handler: com.sun.jna.Pointer?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: com.sun.jna.Pointer?, indirectBuffer: com.sun.jna.Pointer?, indirectOffset: ULong): Unit
	fun wgpuComputePassEncoderEnd(handler: com.sun.jna.Pointer?): Unit
	fun wgpuComputePassEncoderSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuComputePipelineRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuComputePipelineGetBindGroupLayout(handler: com.sun.jna.Pointer?, groupIndex: UInt): com.sun.jna.Pointer?
	fun wgpuComputePipelineSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuDeviceRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuDeviceCreateBindGroup(handler: com.sun.jna.Pointer?, descriptor: WGPUBindGroupDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateBindGroupLayout(handler: com.sun.jna.Pointer?, descriptor: WGPUBindGroupLayoutDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateBuffer(handler: com.sun.jna.Pointer?, descriptor: WGPUBufferDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateCommandEncoder(handler: com.sun.jna.Pointer?, descriptor: WGPUCommandEncoderDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateComputePipeline(handler: com.sun.jna.Pointer?, descriptor: WGPUComputePipelineDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateComputePipelineAsync(handler: com.sun.jna.Pointer?, descriptor: WGPUComputePipelineDescriptor.ByReference?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo.ByValue): Unit
	fun wgpuDeviceCreatePipelineLayout(handler: com.sun.jna.Pointer?, descriptor: WGPUPipelineLayoutDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateQuerySet(handler: com.sun.jna.Pointer?, descriptor: WGPUQuerySetDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateRenderPipelineAsync(handler: com.sun.jna.Pointer?, descriptor: WGPURenderPipelineDescriptor.ByReference?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo.ByValue): Unit
	fun wgpuDeviceCreateRenderBundleEncoder(handler: com.sun.jna.Pointer?, descriptor: WGPURenderBundleEncoderDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateRenderPipeline(handler: com.sun.jna.Pointer?, descriptor: WGPURenderPipelineDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateSampler(handler: com.sun.jna.Pointer?, descriptor: WGPUSamplerDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateShaderModule(handler: com.sun.jna.Pointer?, descriptor: WGPUShaderModuleDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceCreateTexture(handler: com.sun.jna.Pointer?, descriptor: WGPUTextureDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuDeviceDestroy(handler: com.sun.jna.Pointer?): Unit
	fun wgpuDeviceGetLimits(handler: com.sun.jna.Pointer?, limits: WGPUSupportedLimits.ByReference?): UInt
	fun wgpuDeviceHasFeature(handler: com.sun.jna.Pointer?, feature: UInt): UInt
	fun wgpuDeviceGetFeatures(handler: com.sun.jna.Pointer?, features: WGPUSupportedFeatures.ByReference?): UInt
	fun wgpuDeviceGetQueue(handler: com.sun.jna.Pointer?): com.sun.jna.Pointer?
	fun wgpuDevicePushErrorScope(handler: com.sun.jna.Pointer?, filter: UInt): Unit
	fun wgpuDevicePopErrorScope(handler: com.sun.jna.Pointer?, callbackInfo: WGPUPopErrorScopeCallbackInfo.ByValue): Unit
	fun wgpuDeviceSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuInstanceRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuInstanceCreateSurface(handler: com.sun.jna.Pointer?, descriptor: WGPUSurfaceDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuInstanceHasWGSLLanguageFeature(handler: com.sun.jna.Pointer?, feature: UInt): UInt
	fun wgpuInstanceProcessEvents(handler: com.sun.jna.Pointer?): Unit
	fun wgpuInstanceRequestAdapter(handler: com.sun.jna.Pointer?, options: WGPURequestAdapterOptions.ByReference?, callbackInfo: WGPURequestAdapterCallbackInfo.ByValue): Unit
	fun wgpuInstanceWaitAny(handler: com.sun.jna.Pointer?, futureCount: ULong, futures: WGPUFutureWaitInfo.ByReference?, timeoutNS: ULong): UInt
	fun wgpuPipelineLayoutRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuPipelineLayoutSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuQuerySetRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuQuerySetSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuQuerySetGetType(handler: com.sun.jna.Pointer?): UInt
	fun wgpuQuerySetGetCount(handler: com.sun.jna.Pointer?): UInt
	fun wgpuQuerySetDestroy(handler: com.sun.jna.Pointer?): Unit
	fun wgpuQueueRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuQueueSubmit(handler: com.sun.jna.Pointer?, commandCount: ULong, commands: com.sun.jna.Pointer?): Unit
	fun wgpuQueueOnSubmittedWorkDone(handler: com.sun.jna.Pointer?, callbackInfo: WGPUQueueWorkDoneCallbackInfo.ByValue): Unit
	fun wgpuQueueWriteBuffer(handler: com.sun.jna.Pointer?, buffer: com.sun.jna.Pointer?, bufferOffset: ULong, data: com.sun.jna.Pointer?, size: ULong): Unit
	fun wgpuQueueWriteTexture(handler: com.sun.jna.Pointer?, destination: WGPUImageCopyTexture.ByReference?, data: com.sun.jna.Pointer?, dataSize: ULong, dataLayout: WGPUTextureDataLayout.ByReference?, writeSize: WGPUExtent3D.ByReference?): Unit
	fun wgpuQueueSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuRenderBundleRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderBundleSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuRenderBundleEncoderRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderBundleEncoderSetPipeline(handler: com.sun.jna.Pointer?, pipeline: com.sun.jna.Pointer?): Unit
	fun wgpuRenderBundleEncoderSetBindGroup(handler: com.sun.jna.Pointer?, groupIndex: UInt, group: com.sun.jna.Pointer?, dynamicOffsetCount: ULong, dynamicOffsets: com.sun.jna.Pointer?): Unit
	fun wgpuRenderBundleEncoderDraw(handler: com.sun.jna.Pointer?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	fun wgpuRenderBundleEncoderDrawIndexed(handler: com.sun.jna.Pointer?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	fun wgpuRenderBundleEncoderDrawIndirect(handler: com.sun.jna.Pointer?, indirectBuffer: com.sun.jna.Pointer?, indirectOffset: ULong): Unit
	fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: com.sun.jna.Pointer?, indirectBuffer: com.sun.jna.Pointer?, indirectOffset: ULong): Unit
	fun wgpuRenderBundleEncoderInsertDebugMarker(handler: com.sun.jna.Pointer?, markerLabel: WGPUStringView.ByValue): Unit
	fun wgpuRenderBundleEncoderPopDebugGroup(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderBundleEncoderPushDebugGroup(handler: com.sun.jna.Pointer?, groupLabel: WGPUStringView.ByValue): Unit
	fun wgpuRenderBundleEncoderSetVertexBuffer(handler: com.sun.jna.Pointer?, slot: UInt, buffer: com.sun.jna.Pointer?, offset: ULong, size: ULong): Unit
	fun wgpuRenderBundleEncoderSetIndexBuffer(handler: com.sun.jna.Pointer?, buffer: com.sun.jna.Pointer?, format: UInt, offset: ULong, size: ULong): Unit
	fun wgpuRenderBundleEncoderFinish(handler: com.sun.jna.Pointer?, descriptor: WGPURenderBundleDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuRenderBundleEncoderSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuRenderPassEncoderRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderSetPipeline(handler: com.sun.jna.Pointer?, pipeline: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderSetBindGroup(handler: com.sun.jna.Pointer?, groupIndex: UInt, group: com.sun.jna.Pointer?, dynamicOffsetCount: ULong, dynamicOffsets: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderDraw(handler: com.sun.jna.Pointer?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	fun wgpuRenderPassEncoderDrawIndexed(handler: com.sun.jna.Pointer?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	fun wgpuRenderPassEncoderDrawIndirect(handler: com.sun.jna.Pointer?, indirectBuffer: com.sun.jna.Pointer?, indirectOffset: ULong): Unit
	fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: com.sun.jna.Pointer?, indirectBuffer: com.sun.jna.Pointer?, indirectOffset: ULong): Unit
	fun wgpuRenderPassEncoderExecuteBundles(handler: com.sun.jna.Pointer?, bundleCount: ULong, bundles: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderInsertDebugMarker(handler: com.sun.jna.Pointer?, markerLabel: WGPUStringView.ByValue): Unit
	fun wgpuRenderPassEncoderPopDebugGroup(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderPushDebugGroup(handler: com.sun.jna.Pointer?, groupLabel: WGPUStringView.ByValue): Unit
	fun wgpuRenderPassEncoderSetStencilReference(handler: com.sun.jna.Pointer?, reference: UInt): Unit
	fun wgpuRenderPassEncoderSetBlendConstant(handler: com.sun.jna.Pointer?, color: WGPUColor.ByReference?): Unit
	fun wgpuRenderPassEncoderSetViewport(handler: com.sun.jna.Pointer?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	fun wgpuRenderPassEncoderSetScissorRect(handler: com.sun.jna.Pointer?, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	fun wgpuRenderPassEncoderSetVertexBuffer(handler: com.sun.jna.Pointer?, slot: UInt, buffer: com.sun.jna.Pointer?, offset: ULong, size: ULong): Unit
	fun wgpuRenderPassEncoderSetIndexBuffer(handler: com.sun.jna.Pointer?, buffer: com.sun.jna.Pointer?, format: UInt, offset: ULong, size: ULong): Unit
	fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: com.sun.jna.Pointer?, queryIndex: UInt): Unit
	fun wgpuRenderPassEncoderEndOcclusionQuery(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderEnd(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPassEncoderSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuRenderPipelineRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuRenderPipelineGetBindGroupLayout(handler: com.sun.jna.Pointer?, groupIndex: UInt): com.sun.jna.Pointer?
	fun wgpuRenderPipelineSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuSamplerRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuSamplerSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuShaderModuleRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuShaderModuleGetCompilationInfo(handler: com.sun.jna.Pointer?, callbackInfo: WGPUCompilationInfoCallbackInfo.ByValue): Unit
	fun wgpuShaderModuleSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuSurfaceRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuSurfaceConfigure(handler: com.sun.jna.Pointer?, config: WGPUSurfaceConfiguration.ByReference?): Unit
	fun wgpuSurfaceGetCapabilities(handler: com.sun.jna.Pointer?, adapter: com.sun.jna.Pointer?, capabilities: WGPUSurfaceCapabilities.ByReference?): UInt
	fun wgpuSurfaceGetCurrentTexture(handler: com.sun.jna.Pointer?, surfaceTexture: WGPUSurfaceTexture.ByReference?): Unit
	fun wgpuSurfacePresent(handler: com.sun.jna.Pointer?): Unit
	fun wgpuSurfaceUnconfigure(handler: com.sun.jna.Pointer?): Unit
	fun wgpuSurfaceSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuTextureRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuTextureCreateView(handler: com.sun.jna.Pointer?, descriptor: WGPUTextureViewDescriptor.ByReference?): com.sun.jna.Pointer?
	fun wgpuTextureSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
	fun wgpuTextureGetWidth(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetHeight(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetDepthOrArrayLayers(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetMipLevelCount(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetSampleCount(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetDimension(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetFormat(handler: com.sun.jna.Pointer?): UInt
	fun wgpuTextureGetUsage(handler: com.sun.jna.Pointer?): ULong
	fun wgpuTextureDestroy(handler: com.sun.jna.Pointer?): Unit
	fun wgpuTextureViewRelease(handler: com.sun.jna.Pointer?): Unit
	fun wgpuTextureViewSetLabel(handler: com.sun.jna.Pointer?, label: WGPUStringView.ByValue): Unit
}
internal val Functions = com.sun.jna.Native.load("wgpu4k", FunctionsInterface::class.java)
