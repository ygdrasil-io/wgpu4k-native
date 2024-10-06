// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.CString
import ffi.NativeAddress
import ffi.ArrayHolder


actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?
	 = Functions.wgpuCreateInstance(descriptor?.handler)
		?.let(::WGPUInstance)

actual fun wgpuGetInstanceFeatures(features: WGPUInstanceFeatures?): Unit?
	 = Functions.wgpuGetInstanceFeatures(features?.handler)

actual fun wgpuAdapterGetLimits(handler: WGPUAdapter?, limits: WGPUSupportedLimits?): Boolean
	 = Functions.wgpuAdapterGetLimits(handler?.handler, limits?.handler)
		.toBoolean()


actual fun wgpuAdapterHasFeature(handler: WGPUAdapter?, feature: WGPUFeatureName): Boolean
	 = Functions.wgpuAdapterHasFeature(handler?.handler, feature)
		.toBoolean()


actual fun wgpuAdapterEnumerateFeatures(handler: WGPUAdapter?, features: WGPUFeatureName): ULong
	 = Functions.wgpuAdapterEnumerateFeatures(handler?.handler, features)

actual fun wgpuAdapterGetInfo(handler: WGPUAdapter?, info: WGPUAdapterInfo?): Unit?
	 = Functions.wgpuAdapterGetInfo(handler?.handler, info?.handler)

actual fun wgpuAdapterRequestDevice(handler: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?): Unit?
	 = Functions.wgpuAdapterRequestDevice(handler?.handler, descriptor?.handler)

actual fun wgpuBindGroupSetLabel(handler: WGPUBindGroup?, label: CString?): Unit?
	 = Functions.wgpuBindGroupSetLabel(handler?.handler, label?.handler)

actual fun wgpuBindGroupLayoutSetLabel(handler: WGPUBindGroupLayout?, label: CString?): Unit?
	 = Functions.wgpuBindGroupLayoutSetLabel(handler?.handler, label?.handler)

actual fun wgpuBufferMapAsync(handler: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuBufferMapAsync(handler?.handler, mode, offset, size)

actual fun wgpuBufferGetMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuBufferGetMappedRange(handler?.handler, offset, size)

actual fun wgpuBufferGetConstMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuBufferGetConstMappedRange(handler?.handler, offset, size)

actual fun wgpuBufferSetLabel(handler: WGPUBuffer?, label: CString?): Unit?
	 = Functions.wgpuBufferSetLabel(handler?.handler, label?.handler)

actual fun wgpuBufferGetUsage(handler: WGPUBuffer?): ULong
	 = Functions.wgpuBufferGetUsage(handler?.handler)

actual fun wgpuBufferGetSize(handler: WGPUBuffer?): ULong
	 = Functions.wgpuBufferGetSize(handler?.handler)

actual fun wgpuBufferGetMapState(handler: WGPUBuffer?): WGPUBufferMapState
	 = Functions.wgpuBufferGetMapState(handler?.handler)

actual fun wgpuBufferUnmap(handler: WGPUBuffer?): Unit?
	 = Functions.wgpuBufferUnmap(handler?.handler)

actual fun wgpuBufferDestroy(handler: WGPUBuffer?): Unit?
	 = Functions.wgpuBufferDestroy(handler?.handler)

actual fun wgpuCommandBufferSetLabel(handler: WGPUCommandBuffer?, label: CString?): Unit?
	 = Functions.wgpuCommandBufferSetLabel(handler?.handler, label?.handler)

actual fun wgpuCommandEncoderFinish(handler: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer?
	 = Functions.wgpuCommandEncoderFinish(handler?.handler, descriptor?.handler)
		?.let(::WGPUCommandBuffer)

actual fun wgpuCommandEncoderBeginComputePass(handler: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder?
	 = Functions.wgpuCommandEncoderBeginComputePass(handler?.handler, descriptor?.handler)
		?.let(::WGPUComputePassEncoder)

actual fun wgpuCommandEncoderBeginRenderPass(handler: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder?
	 = Functions.wgpuCommandEncoderBeginRenderPass(handler?.handler, descriptor?.handler)
		?.let(::WGPURenderPassEncoder)

actual fun wgpuCommandEncoderCopyBufferToBuffer(handler: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit?
	 = Functions.wgpuCommandEncoderCopyBufferToBuffer(handler?.handler, source?.handler, sourceOffset, destination?.handler, destinationOffset, size)

actual fun wgpuCommandEncoderCopyBufferToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyBuffer?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit?
	 = Functions.wgpuCommandEncoderCopyBufferToTexture(handler?.handler, source?.handler, destination?.handler, copySize?.handler)

actual fun wgpuCommandEncoderCopyTextureToBuffer(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyBuffer?, copySize: WGPUExtent3D?): Unit?
	 = Functions.wgpuCommandEncoderCopyTextureToBuffer(handler?.handler, source?.handler, destination?.handler, copySize?.handler)

actual fun wgpuCommandEncoderCopyTextureToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit?
	 = Functions.wgpuCommandEncoderCopyTextureToTexture(handler?.handler, source?.handler, destination?.handler, copySize?.handler)

actual fun wgpuCommandEncoderClearBuffer(handler: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuCommandEncoderClearBuffer(handler?.handler, buffer?.handler, offset, size)

actual fun wgpuCommandEncoderInsertDebugMarker(handler: WGPUCommandEncoder?, markerLabel: CString?): Unit?
	 = Functions.wgpuCommandEncoderInsertDebugMarker(handler?.handler, markerLabel?.handler)

actual fun wgpuCommandEncoderPopDebugGroup(handler: WGPUCommandEncoder?): Unit?
	 = Functions.wgpuCommandEncoderPopDebugGroup(handler?.handler)

actual fun wgpuCommandEncoderPushDebugGroup(handler: WGPUCommandEncoder?, groupLabel: CString?): Unit?
	 = Functions.wgpuCommandEncoderPushDebugGroup(handler?.handler, groupLabel?.handler)

actual fun wgpuCommandEncoderResolveQuerySet(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit?
	 = Functions.wgpuCommandEncoderResolveQuerySet(handler?.handler, querySet?.handler, firstQuery, queryCount, destination?.handler, destinationOffset)

actual fun wgpuCommandEncoderWriteTimestamp(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit?
	 = Functions.wgpuCommandEncoderWriteTimestamp(handler?.handler, querySet?.handler, queryIndex)

actual fun wgpuCommandEncoderSetLabel(handler: WGPUCommandEncoder?, label: CString?): Unit?
	 = Functions.wgpuCommandEncoderSetLabel(handler?.handler, label?.handler)

actual fun wgpuComputePassEncoderInsertDebugMarker(handler: WGPUComputePassEncoder?, markerLabel: CString?): Unit?
	 = Functions.wgpuComputePassEncoderInsertDebugMarker(handler?.handler, markerLabel?.handler)

actual fun wgpuComputePassEncoderPopDebugGroup(handler: WGPUComputePassEncoder?): Unit?
	 = Functions.wgpuComputePassEncoderPopDebugGroup(handler?.handler)

actual fun wgpuComputePassEncoderPushDebugGroup(handler: WGPUComputePassEncoder?, groupLabel: CString?): Unit?
	 = Functions.wgpuComputePassEncoderPushDebugGroup(handler?.handler, groupLabel?.handler)

actual fun wgpuComputePassEncoderSetPipeline(handler: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit?
	 = Functions.wgpuComputePassEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuComputePassEncoderSetBindGroup(handler: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsets: ArrayHolder<UInt>?): Unit?
	 = Functions.wgpuComputePassEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsets?.handler)

actual fun wgpuComputePassEncoderDispatchWorkgroups(handler: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit?
	 = Functions.wgpuComputePassEncoderDispatchWorkgroups(handler?.handler, workgroupCountX, workgroupCountY, workgroupCountZ)

actual fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit?
	 = Functions.wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuComputePassEncoderEnd(handler: WGPUComputePassEncoder?): Unit?
	 = Functions.wgpuComputePassEncoderEnd(handler?.handler)

actual fun wgpuComputePassEncoderSetLabel(handler: WGPUComputePassEncoder?, label: CString?): Unit?
	 = Functions.wgpuComputePassEncoderSetLabel(handler?.handler, label?.handler)

actual fun wgpuComputePipelineGetBindGroupLayout(handler: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = Functions.wgpuComputePipelineGetBindGroupLayout(handler?.handler, groupIndex)
		?.let(::WGPUBindGroupLayout)

actual fun wgpuComputePipelineSetLabel(handler: WGPUComputePipeline?, label: CString?): Unit?
	 = Functions.wgpuComputePipelineSetLabel(handler?.handler, label?.handler)

actual fun wgpuDeviceCreateBindGroup(handler: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup?
	 = Functions.wgpuDeviceCreateBindGroup(handler?.handler, descriptor?.handler)
		?.let(::WGPUBindGroup)

actual fun wgpuDeviceCreateBindGroupLayout(handler: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout?
	 = Functions.wgpuDeviceCreateBindGroupLayout(handler?.handler, descriptor?.handler)
		?.let(::WGPUBindGroupLayout)

actual fun wgpuDeviceCreateBuffer(handler: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer?
	 = Functions.wgpuDeviceCreateBuffer(handler?.handler, descriptor?.handler)
		?.let(::WGPUBuffer)

actual fun wgpuDeviceCreateCommandEncoder(handler: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder?
	 = Functions.wgpuDeviceCreateCommandEncoder(handler?.handler, descriptor?.handler)
		?.let(::WGPUCommandEncoder)

actual fun wgpuDeviceCreateComputePipeline(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline?
	 = Functions.wgpuDeviceCreateComputePipeline(handler?.handler, descriptor?.handler)
		?.let(::WGPUComputePipeline)

actual fun wgpuDeviceCreateComputePipelineAsync(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): Unit?
	 = Functions.wgpuDeviceCreateComputePipelineAsync(handler?.handler, descriptor?.handler)

actual fun wgpuDeviceCreatePipelineLayout(handler: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout?
	 = Functions.wgpuDeviceCreatePipelineLayout(handler?.handler, descriptor?.handler)
		?.let(::WGPUPipelineLayout)

actual fun wgpuDeviceCreateQuerySet(handler: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet?
	 = Functions.wgpuDeviceCreateQuerySet(handler?.handler, descriptor?.handler)
		?.let(::WGPUQuerySet)

actual fun wgpuDeviceCreateRenderPipelineAsync(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): Unit?
	 = Functions.wgpuDeviceCreateRenderPipelineAsync(handler?.handler, descriptor?.handler)

actual fun wgpuDeviceCreateRenderBundleEncoder(handler: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder?
	 = Functions.wgpuDeviceCreateRenderBundleEncoder(handler?.handler, descriptor?.handler)
		?.let(::WGPURenderBundleEncoder)

actual fun wgpuDeviceCreateRenderPipeline(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline?
	 = Functions.wgpuDeviceCreateRenderPipeline(handler?.handler, descriptor?.handler)
		?.let(::WGPURenderPipeline)

actual fun wgpuDeviceCreateSampler(handler: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler?
	 = Functions.wgpuDeviceCreateSampler(handler?.handler, descriptor?.handler)
		?.let(::WGPUSampler)

actual fun wgpuDeviceCreateShaderModule(handler: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?
	 = Functions.wgpuDeviceCreateShaderModule(handler?.handler, descriptor?.handler)
		?.let(::WGPUShaderModule)

actual fun wgpuDeviceCreateTexture(handler: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture?
	 = Functions.wgpuDeviceCreateTexture(handler?.handler, descriptor?.handler)
		?.let(::WGPUTexture)

actual fun wgpuDeviceDestroy(handler: WGPUDevice?): Unit?
	 = Functions.wgpuDeviceDestroy(handler?.handler)

actual fun wgpuDeviceGetLimits(handler: WGPUDevice?, limits: WGPUSupportedLimits?): Boolean
	 = Functions.wgpuDeviceGetLimits(handler?.handler, limits?.handler)
		.toBoolean()


actual fun wgpuDeviceHasFeature(handler: WGPUDevice?, feature: WGPUFeatureName): Boolean
	 = Functions.wgpuDeviceHasFeature(handler?.handler, feature)
		.toBoolean()


actual fun wgpuDeviceEnumerateFeatures(handler: WGPUDevice?, features: WGPUFeatureName): ULong
	 = Functions.wgpuDeviceEnumerateFeatures(handler?.handler, features)

actual fun wgpuDeviceGetQueue(handler: WGPUDevice?): WGPUQueue?
	 = Functions.wgpuDeviceGetQueue(handler?.handler)
		?.let(::WGPUQueue)

actual fun wgpuDevicePushErrorScope(handler: WGPUDevice?, filter: WGPUErrorFilter): Unit?
	 = Functions.wgpuDevicePushErrorScope(handler?.handler, filter)

actual fun wgpuDevicePopErrorScope(handler: WGPUDevice?): Unit?
	 = Functions.wgpuDevicePopErrorScope(handler?.handler)

actual fun wgpuDeviceSetLabel(handler: WGPUDevice?, label: CString?): Unit?
	 = Functions.wgpuDeviceSetLabel(handler?.handler, label?.handler)

actual fun wgpuInstanceCreateSurface(handler: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface?
	 = Functions.wgpuInstanceCreateSurface(handler?.handler, descriptor?.handler)
		?.let(::WGPUSurface)

actual fun wgpuInstanceHasWGSLLanguageFeature(handler: WGPUInstance?, feature: WGPUWGSLFeatureName): Boolean
	 = Functions.wgpuInstanceHasWGSLLanguageFeature(handler?.handler, feature)
		.toBoolean()


actual fun wgpuInstanceProcessEvents(handler: WGPUInstance?): Unit?
	 = Functions.wgpuInstanceProcessEvents(handler?.handler)

actual fun wgpuInstanceRequestAdapter(handler: WGPUInstance?, options: WGPURequestAdapterOptions?): Unit?
	 = Functions.wgpuInstanceRequestAdapter(handler?.handler, options?.handler)

actual fun wgpuInstanceWaitAny(handler: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus
	 = Functions.wgpuInstanceWaitAny(handler?.handler, futureCount, futures?.handler, timeoutNS)

actual fun wgpuPipelineLayoutSetLabel(handler: WGPUPipelineLayout?, label: CString?): Unit?
	 = Functions.wgpuPipelineLayoutSetLabel(handler?.handler, label?.handler)

actual fun wgpuQuerySetSetLabel(handler: WGPUQuerySet?, label: CString?): Unit?
	 = Functions.wgpuQuerySetSetLabel(handler?.handler, label?.handler)

actual fun wgpuQuerySetGetType(handler: WGPUQuerySet?): WGPUQueryType
	 = Functions.wgpuQuerySetGetType(handler?.handler)

actual fun wgpuQuerySetGetCount(handler: WGPUQuerySet?): UInt
	 = Functions.wgpuQuerySetGetCount(handler?.handler)

actual fun wgpuQuerySetDestroy(handler: WGPUQuerySet?): Unit?
	 = Functions.wgpuQuerySetDestroy(handler?.handler)

actual fun wgpuQueueSubmit(handler: WGPUQueue?, commands: ArrayHolder<WGPUCommandBuffer>?): Unit?
	 = Functions.wgpuQueueSubmit(handler?.handler, commands?.handler)

actual fun wgpuQueueOnSubmittedWorkDone(handler: WGPUQueue?): Unit?
	 = Functions.wgpuQueueOnSubmittedWorkDone(handler?.handler)

actual fun wgpuQueueWriteBuffer(handler: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit?
	 = Functions.wgpuQueueWriteBuffer(handler?.handler, buffer?.handler, bufferOffset, data, size)

actual fun wgpuQueueWriteTexture(handler: WGPUQueue?, destination: WGPUImageCopyTexture?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTextureDataLayout?, writeSize: WGPUExtent3D?): Unit?
	 = Functions.wgpuQueueWriteTexture(handler?.handler, destination?.handler, data, dataSize, dataLayout?.handler, writeSize?.handler)

actual fun wgpuQueueSetLabel(handler: WGPUQueue?, label: CString?): Unit?
	 = Functions.wgpuQueueSetLabel(handler?.handler, label?.handler)

actual fun wgpuRenderBundleSetLabel(handler: WGPURenderBundle?, label: CString?): Unit?
	 = Functions.wgpuRenderBundleSetLabel(handler?.handler, label?.handler)

actual fun wgpuRenderBundleEncoderSetPipeline(handler: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit?
	 = Functions.wgpuRenderBundleEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuRenderBundleEncoderSetBindGroup(handler: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsets: ArrayHolder<UInt>?): Unit?
	 = Functions.wgpuRenderBundleEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsets?.handler)

actual fun wgpuRenderBundleEncoderDraw(handler: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit?
	 = Functions.wgpuRenderBundleEncoderDraw(handler?.handler, vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndexed(handler: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit?
	 = Functions.wgpuRenderBundleEncoderDrawIndexed(handler?.handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit?
	 = Functions.wgpuRenderBundleEncoderDrawIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit?
	 = Functions.wgpuRenderBundleEncoderDrawIndexedIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderBundleEncoderInsertDebugMarker(handler: WGPURenderBundleEncoder?, markerLabel: CString?): Unit?
	 = Functions.wgpuRenderBundleEncoderInsertDebugMarker(handler?.handler, markerLabel?.handler)

actual fun wgpuRenderBundleEncoderPopDebugGroup(handler: WGPURenderBundleEncoder?): Unit?
	 = Functions.wgpuRenderBundleEncoderPopDebugGroup(handler?.handler)

actual fun wgpuRenderBundleEncoderPushDebugGroup(handler: WGPURenderBundleEncoder?, groupLabel: CString?): Unit?
	 = Functions.wgpuRenderBundleEncoderPushDebugGroup(handler?.handler, groupLabel?.handler)

actual fun wgpuRenderBundleEncoderSetVertexBuffer(handler: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuRenderBundleEncoderSetVertexBuffer(handler?.handler, slot, buffer?.handler, offset, size)

actual fun wgpuRenderBundleEncoderSetIndexBuffer(handler: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuRenderBundleEncoderSetIndexBuffer(handler?.handler, buffer?.handler, format, offset, size)

actual fun wgpuRenderBundleEncoderFinish(handler: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle?
	 = Functions.wgpuRenderBundleEncoderFinish(handler?.handler, descriptor?.handler)
		?.let(::WGPURenderBundle)

actual fun wgpuRenderBundleEncoderSetLabel(handler: WGPURenderBundleEncoder?, label: CString?): Unit?
	 = Functions.wgpuRenderBundleEncoderSetLabel(handler?.handler, label?.handler)

actual fun wgpuRenderPassEncoderSetPipeline(handler: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit?
	 = Functions.wgpuRenderPassEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuRenderPassEncoderSetBindGroup(handler: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsets: ArrayHolder<UInt>?): Unit?
	 = Functions.wgpuRenderPassEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsets?.handler)

actual fun wgpuRenderPassEncoderDraw(handler: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit?
	 = Functions.wgpuRenderPassEncoderDraw(handler?.handler, vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndexed(handler: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit?
	 = Functions.wgpuRenderPassEncoderDrawIndexed(handler?.handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit?
	 = Functions.wgpuRenderPassEncoderDrawIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit?
	 = Functions.wgpuRenderPassEncoderDrawIndexedIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderPassEncoderExecuteBundles(handler: WGPURenderPassEncoder?, bundles: ArrayHolder<WGPURenderBundle>?): Unit?
	 = Functions.wgpuRenderPassEncoderExecuteBundles(handler?.handler, bundles?.handler)

actual fun wgpuRenderPassEncoderInsertDebugMarker(handler: WGPURenderPassEncoder?, markerLabel: CString?): Unit?
	 = Functions.wgpuRenderPassEncoderInsertDebugMarker(handler?.handler, markerLabel?.handler)

actual fun wgpuRenderPassEncoderPopDebugGroup(handler: WGPURenderPassEncoder?): Unit?
	 = Functions.wgpuRenderPassEncoderPopDebugGroup(handler?.handler)

actual fun wgpuRenderPassEncoderPushDebugGroup(handler: WGPURenderPassEncoder?, groupLabel: CString?): Unit?
	 = Functions.wgpuRenderPassEncoderPushDebugGroup(handler?.handler, groupLabel?.handler)

actual fun wgpuRenderPassEncoderSetStencilReference(handler: WGPURenderPassEncoder?, reference: UInt): Unit?
	 = Functions.wgpuRenderPassEncoderSetStencilReference(handler?.handler, reference)

actual fun wgpuRenderPassEncoderSetBlendConstant(handler: WGPURenderPassEncoder?, color: WGPUColor?): Unit?
	 = Functions.wgpuRenderPassEncoderSetBlendConstant(handler?.handler, color?.handler)

actual fun wgpuRenderPassEncoderSetViewport(handler: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit?
	 = Functions.wgpuRenderPassEncoderSetViewport(handler?.handler, x, y, width, height, minDepth, maxDepth)

actual fun wgpuRenderPassEncoderSetScissorRect(handler: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit?
	 = Functions.wgpuRenderPassEncoderSetScissorRect(handler?.handler, x, y, width, height)

actual fun wgpuRenderPassEncoderSetVertexBuffer(handler: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuRenderPassEncoderSetVertexBuffer(handler?.handler, slot, buffer?.handler, offset, size)

actual fun wgpuRenderPassEncoderSetIndexBuffer(handler: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit?
	 = Functions.wgpuRenderPassEncoderSetIndexBuffer(handler?.handler, buffer?.handler, format, offset, size)

actual fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: WGPURenderPassEncoder?, queryIndex: UInt): Unit?
	 = Functions.wgpuRenderPassEncoderBeginOcclusionQuery(handler?.handler, queryIndex)

actual fun wgpuRenderPassEncoderEndOcclusionQuery(handler: WGPURenderPassEncoder?): Unit?
	 = Functions.wgpuRenderPassEncoderEndOcclusionQuery(handler?.handler)

actual fun wgpuRenderPassEncoderEnd(handler: WGPURenderPassEncoder?): Unit?
	 = Functions.wgpuRenderPassEncoderEnd(handler?.handler)

actual fun wgpuRenderPassEncoderSetLabel(handler: WGPURenderPassEncoder?, label: CString?): Unit?
	 = Functions.wgpuRenderPassEncoderSetLabel(handler?.handler, label?.handler)

actual fun wgpuRenderPipelineGetBindGroupLayout(handler: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = Functions.wgpuRenderPipelineGetBindGroupLayout(handler?.handler, groupIndex)
		?.let(::WGPUBindGroupLayout)

actual fun wgpuRenderPipelineSetLabel(handler: WGPURenderPipeline?, label: CString?): Unit?
	 = Functions.wgpuRenderPipelineSetLabel(handler?.handler, label?.handler)

actual fun wgpuSamplerSetLabel(handler: WGPUSampler?, label: CString?): Unit?
	 = Functions.wgpuSamplerSetLabel(handler?.handler, label?.handler)

actual fun wgpuShaderModuleGetCompilationInfo(handler: WGPUShaderModule?): Unit?
	 = Functions.wgpuShaderModuleGetCompilationInfo(handler?.handler)

actual fun wgpuShaderModuleSetLabel(handler: WGPUShaderModule?, label: CString?): Unit?
	 = Functions.wgpuShaderModuleSetLabel(handler?.handler, label?.handler)

actual fun wgpuSurfaceConfigure(handler: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit?
	 = Functions.wgpuSurfaceConfigure(handler?.handler, config?.handler)

actual fun wgpuSurfaceGetCapabilities(handler: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): Boolean
	 = Functions.wgpuSurfaceGetCapabilities(handler?.handler, adapter?.handler, capabilities?.handler)
		.toBoolean()


actual fun wgpuSurfaceGetCurrentTexture(handler: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit?
	 = Functions.wgpuSurfaceGetCurrentTexture(handler?.handler, surfaceTexture?.handler)

actual fun wgpuSurfacePresent(handler: WGPUSurface?): Unit?
	 = Functions.wgpuSurfacePresent(handler?.handler)

actual fun wgpuSurfaceUnconfigure(handler: WGPUSurface?): Unit?
	 = Functions.wgpuSurfaceUnconfigure(handler?.handler)

actual fun wgpuSurfaceSetLabel(handler: WGPUSurface?, label: CString?): Unit?
	 = Functions.wgpuSurfaceSetLabel(handler?.handler, label?.handler)

actual fun wgpuTextureCreateView(handler: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView?
	 = Functions.wgpuTextureCreateView(handler?.handler, descriptor?.handler)
		?.let(::WGPUTextureView)

actual fun wgpuTextureSetLabel(handler: WGPUTexture?, label: CString?): Unit?
	 = Functions.wgpuTextureSetLabel(handler?.handler, label?.handler)

actual fun wgpuTextureGetWidth(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetWidth(handler?.handler)

actual fun wgpuTextureGetHeight(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetHeight(handler?.handler)

actual fun wgpuTextureGetDepthOrArrayLayers(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetDepthOrArrayLayers(handler?.handler)

actual fun wgpuTextureGetMipLevelCount(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetMipLevelCount(handler?.handler)

actual fun wgpuTextureGetSampleCount(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetSampleCount(handler?.handler)

actual fun wgpuTextureGetDimension(handler: WGPUTexture?): WGPUTextureDimension
	 = Functions.wgpuTextureGetDimension(handler?.handler)

actual fun wgpuTextureGetFormat(handler: WGPUTexture?): WGPUTextureFormat
	 = Functions.wgpuTextureGetFormat(handler?.handler)

actual fun wgpuTextureGetUsage(handler: WGPUTexture?): ULong
	 = Functions.wgpuTextureGetUsage(handler?.handler)

actual fun wgpuTextureDestroy(handler: WGPUTexture?): Unit?
	 = Functions.wgpuTextureDestroy(handler?.handler)

actual fun wgpuTextureViewSetLabel(handler: WGPUTextureView?, label: CString?): Unit?
	 = Functions.wgpuTextureViewSetLabel(handler?.handler, label?.handler)

