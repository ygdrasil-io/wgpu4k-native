// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.CString
import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.ArrayHolder
import ffi.adapt


actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?
	 = webgpu.android.Functions.wgpuCreateInstance(descriptor?.toReference())
	?.let(::WGPUInstance)

actual fun wgpuGetInstanceCapabilities(capabilities: WGPUInstanceCapabilities?): WGPUStatus
	 = webgpu.android.Functions.wgpuGetInstanceCapabilities(capabilities?.toReference())

actual fun wgpuAdapterRelease(handler: WGPUAdapter?): Unit
	 = webgpu.android.Functions.wgpuAdapterRelease(handler?.handler)

actual fun wgpuAdapterGetLimits(handler: WGPUAdapter?, limits: WGPULimits?): WGPUStatus
	 = webgpu.android.Functions.wgpuAdapterGetLimits(handler?.handler, limits?.toReference())

actual fun wgpuAdapterHasFeature(handler: WGPUAdapter?, feature: WGPUFeatureName): Boolean
	 = webgpu.android.Functions.wgpuAdapterHasFeature(handler?.handler, feature)
	.toBoolean()

actual fun wgpuAdapterGetFeatures(handler: WGPUAdapter?, features: WGPUSupportedFeatures?): Unit
	 = webgpu.android.Functions.wgpuAdapterGetFeatures(handler?.handler, features?.toReference())

actual fun wgpuAdapterGetInfo(handler: WGPUAdapter?, info: WGPUAdapterInfo?): WGPUStatus
	 = webgpu.android.Functions.wgpuAdapterGetInfo(handler?.handler, info?.toReference())

actual fun wgpuAdapterRequestDevice(handler: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?, callbackInfo: WGPURequestDeviceCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuAdapterRequestDevice(handler?.handler, descriptor?.toReference(), callbackInfo.toCValue())

actual fun wgpuBindGroupRelease(handler: WGPUBindGroup?): Unit
	 = webgpu.android.Functions.wgpuBindGroupRelease(handler?.handler)

actual fun wgpuBindGroupSetLabel(handler: WGPUBindGroup?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBindGroupSetLabel(handler?.handler, label.toCValue())

actual fun wgpuBindGroupLayoutRelease(handler: WGPUBindGroupLayout?): Unit
	 = webgpu.android.Functions.wgpuBindGroupLayoutRelease(handler?.handler)

actual fun wgpuBindGroupLayoutSetLabel(handler: WGPUBindGroupLayout?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBindGroupLayoutSetLabel(handler?.handler, label.toCValue())

actual fun wgpuBufferRelease(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferRelease(handler?.handler)

actual fun wgpuBufferMapAsync(handler: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuBufferMapAsync(handler?.handler, mode, offset, size, callbackInfo.toCValue())

actual fun wgpuBufferGetMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = webgpu.android.Functions.wgpuBufferGetMappedRange(handler?.handler, offset, size)

actual fun wgpuBufferGetConstMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = webgpu.android.Functions.wgpuBufferGetConstMappedRange(handler?.handler, offset, size)

actual fun wgpuBufferSetLabel(handler: WGPUBuffer?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBufferSetLabel(handler?.handler, label.toCValue())

actual fun wgpuBufferGetUsage(handler: WGPUBuffer?): ULong
	 = webgpu.android.Functions.wgpuBufferGetUsage(handler?.handler)

actual fun wgpuBufferGetSize(handler: WGPUBuffer?): ULong
	 = webgpu.android.Functions.wgpuBufferGetSize(handler?.handler)

actual fun wgpuBufferGetMapState(handler: WGPUBuffer?): WGPUBufferMapState
	 = webgpu.android.Functions.wgpuBufferGetMapState(handler?.handler)

actual fun wgpuBufferUnmap(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferUnmap(handler?.handler)

actual fun wgpuBufferDestroy(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferDestroy(handler?.handler)

actual fun wgpuCommandBufferRelease(handler: WGPUCommandBuffer?): Unit
	 = webgpu.android.Functions.wgpuCommandBufferRelease(handler?.handler)

actual fun wgpuCommandBufferSetLabel(handler: WGPUCommandBuffer?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandBufferSetLabel(handler?.handler, label.toCValue())

actual fun wgpuCommandEncoderRelease(handler: WGPUCommandEncoder?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderRelease(handler?.handler)

actual fun wgpuCommandEncoderFinish(handler: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer?
	 = webgpu.android.Functions.wgpuCommandEncoderFinish(handler?.handler, descriptor?.toReference())
	?.let(::WGPUCommandBuffer)

actual fun wgpuCommandEncoderBeginComputePass(handler: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder?
	 = webgpu.android.Functions.wgpuCommandEncoderBeginComputePass(handler?.handler, descriptor?.toReference())
	?.let(::WGPUComputePassEncoder)

actual fun wgpuCommandEncoderBeginRenderPass(handler: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder?
	 = webgpu.android.Functions.wgpuCommandEncoderBeginRenderPass(handler?.handler, descriptor?.toReference())
	?.let(::WGPURenderPassEncoder)

actual fun wgpuCommandEncoderCopyBufferToBuffer(handler: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyBufferToBuffer(handler?.handler, source?.handler, sourceOffset, destination?.handler, destinationOffset, size)

actual fun wgpuCommandEncoderCopyBufferToTexture(handler: WGPUCommandEncoder?, source: WGPUTexelCopyBufferInfo?, destination: WGPUTexelCopyTextureInfo?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyBufferToTexture(handler?.handler, source?.toReference(), destination?.toReference(), copySize?.toReference())

actual fun wgpuCommandEncoderCopyTextureToBuffer(handler: WGPUCommandEncoder?, source: WGPUTexelCopyTextureInfo?, destination: WGPUTexelCopyBufferInfo?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyTextureToBuffer(handler?.handler, source?.toReference(), destination?.toReference(), copySize?.toReference())

actual fun wgpuCommandEncoderCopyTextureToTexture(handler: WGPUCommandEncoder?, source: WGPUTexelCopyTextureInfo?, destination: WGPUTexelCopyTextureInfo?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyTextureToTexture(handler?.handler, source?.toReference(), destination?.toReference(), copySize?.toReference())

actual fun wgpuCommandEncoderClearBuffer(handler: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderClearBuffer(handler?.handler, buffer?.handler, offset, size)

actual fun wgpuCommandEncoderInsertDebugMarker(handler: WGPUCommandEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderInsertDebugMarker(handler?.handler, markerLabel.toCValue())

actual fun wgpuCommandEncoderPopDebugGroup(handler: WGPUCommandEncoder?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderPopDebugGroup(handler?.handler)

actual fun wgpuCommandEncoderPushDebugGroup(handler: WGPUCommandEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderPushDebugGroup(handler?.handler, groupLabel.toCValue())

actual fun wgpuCommandEncoderResolveQuerySet(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderResolveQuerySet(handler?.handler, querySet?.handler, firstQuery, queryCount, destination?.handler, destinationOffset)

actual fun wgpuCommandEncoderWriteTimestamp(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderWriteTimestamp(handler?.handler, querySet?.handler, queryIndex)

actual fun wgpuCommandEncoderSetLabel(handler: WGPUCommandEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderSetLabel(handler?.handler, label.toCValue())

actual fun wgpuComputePassEncoderRelease(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderRelease(handler?.handler)

actual fun wgpuComputePassEncoderInsertDebugMarker(handler: WGPUComputePassEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderInsertDebugMarker(handler?.handler, markerLabel.toCValue())

actual fun wgpuComputePassEncoderPopDebugGroup(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderPopDebugGroup(handler?.handler)

actual fun wgpuComputePassEncoderPushDebugGroup(handler: WGPUComputePassEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderPushDebugGroup(handler?.handler, groupLabel.toCValue())

actual fun wgpuComputePassEncoderSetPipeline(handler: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuComputePassEncoderSetBindGroup(handler: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsetCount, dynamicOffsets?.handler)

actual fun wgpuComputePassEncoderDispatchWorkgroups(handler: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderDispatchWorkgroups(handler?.handler, workgroupCountX, workgroupCountY, workgroupCountZ)

actual fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuComputePassEncoderEnd(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderEnd(handler?.handler)

actual fun wgpuComputePassEncoderSetLabel(handler: WGPUComputePassEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetLabel(handler?.handler, label.toCValue())

actual fun wgpuComputePipelineRelease(handler: WGPUComputePipeline?): Unit
	 = webgpu.android.Functions.wgpuComputePipelineRelease(handler?.handler)

actual fun wgpuComputePipelineGetBindGroupLayout(handler: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuComputePipelineGetBindGroupLayout(handler?.handler, groupIndex)
	?.let(::WGPUBindGroupLayout)

actual fun wgpuComputePipelineSetLabel(handler: WGPUComputePipeline?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePipelineSetLabel(handler?.handler, label.toCValue())

actual fun wgpuDeviceRelease(handler: WGPUDevice?): Unit
	 = webgpu.android.Functions.wgpuDeviceRelease(handler?.handler)

actual fun wgpuDeviceCreateBindGroup(handler: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup?
	 = webgpu.android.Functions.wgpuDeviceCreateBindGroup(handler?.handler, descriptor?.toReference())
	?.let(::WGPUBindGroup)

actual fun wgpuDeviceCreateBindGroupLayout(handler: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuDeviceCreateBindGroupLayout(handler?.handler, descriptor?.toReference())
	?.let(::WGPUBindGroupLayout)

actual fun wgpuDeviceCreateBuffer(handler: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer?
	 = webgpu.android.Functions.wgpuDeviceCreateBuffer(handler?.handler, descriptor?.toReference())
	?.let(::WGPUBuffer)

actual fun wgpuDeviceCreateCommandEncoder(handler: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder?
	 = webgpu.android.Functions.wgpuDeviceCreateCommandEncoder(handler?.handler, descriptor?.toReference())
	?.let(::WGPUCommandEncoder)

actual fun wgpuDeviceCreateComputePipeline(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline?
	 = webgpu.android.Functions.wgpuDeviceCreateComputePipeline(handler?.handler, descriptor?.toReference())
	?.let(::WGPUComputePipeline)

actual fun wgpuDeviceCreateComputePipelineAsync(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDeviceCreateComputePipelineAsync(handler?.handler, descriptor?.toReference(), callbackInfo.toCValue())

actual fun wgpuDeviceCreatePipelineLayout(handler: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout?
	 = webgpu.android.Functions.wgpuDeviceCreatePipelineLayout(handler?.handler, descriptor?.toReference())
	?.let(::WGPUPipelineLayout)

actual fun wgpuDeviceCreateQuerySet(handler: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet?
	 = webgpu.android.Functions.wgpuDeviceCreateQuerySet(handler?.handler, descriptor?.toReference())
	?.let(::WGPUQuerySet)

actual fun wgpuDeviceCreateRenderPipelineAsync(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDeviceCreateRenderPipelineAsync(handler?.handler, descriptor?.toReference(), callbackInfo.toCValue())

actual fun wgpuDeviceCreateRenderBundleEncoder(handler: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder?
	 = webgpu.android.Functions.wgpuDeviceCreateRenderBundleEncoder(handler?.handler, descriptor?.toReference())
	?.let(::WGPURenderBundleEncoder)

actual fun wgpuDeviceCreateRenderPipeline(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline?
	 = webgpu.android.Functions.wgpuDeviceCreateRenderPipeline(handler?.handler, descriptor?.toReference())
	?.let(::WGPURenderPipeline)

actual fun wgpuDeviceCreateSampler(handler: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler?
	 = webgpu.android.Functions.wgpuDeviceCreateSampler(handler?.handler, descriptor?.toReference())
	?.let(::WGPUSampler)

actual fun wgpuDeviceCreateShaderModule(handler: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?
	 = webgpu.android.Functions.wgpuDeviceCreateShaderModule(handler?.handler, descriptor?.toReference())
	?.let(::WGPUShaderModule)

actual fun wgpuDeviceCreateTexture(handler: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture?
	 = webgpu.android.Functions.wgpuDeviceCreateTexture(handler?.handler, descriptor?.toReference())
	?.let(::WGPUTexture)

actual fun wgpuDeviceDestroy(handler: WGPUDevice?): Unit
	 = webgpu.android.Functions.wgpuDeviceDestroy(handler?.handler)

actual fun wgpuDeviceGetLostFuture(handler: WGPUDevice?): WGPUFuture
	 = webgpu.android.Functions.wgpuDeviceGetLostFuture(handler?.handler)
	.let(WGPUFuture::ByValue)

actual fun wgpuDeviceGetLimits(handler: WGPUDevice?, limits: WGPULimits?): WGPUStatus
	 = webgpu.android.Functions.wgpuDeviceGetLimits(handler?.handler, limits?.toReference())

actual fun wgpuDeviceHasFeature(handler: WGPUDevice?, feature: WGPUFeatureName): Boolean
	 = webgpu.android.Functions.wgpuDeviceHasFeature(handler?.handler, feature)
	.toBoolean()

actual fun wgpuDeviceGetFeatures(handler: WGPUDevice?, features: WGPUSupportedFeatures?): Unit
	 = webgpu.android.Functions.wgpuDeviceGetFeatures(handler?.handler, features?.toReference())

actual fun wgpuDeviceGetAdapterInfo(handler: WGPUDevice?): WGPUAdapterInfo
	 = webgpu.android.Functions.wgpuDeviceGetAdapterInfo(handler?.handler)
	.let(WGPUAdapterInfo::ByValue)

actual fun wgpuDeviceGetQueue(handler: WGPUDevice?): WGPUQueue?
	 = webgpu.android.Functions.wgpuDeviceGetQueue(handler?.handler)
	?.let(::WGPUQueue)

actual fun wgpuDevicePushErrorScope(handler: WGPUDevice?, filter: WGPUErrorFilter): Unit
	 = webgpu.android.Functions.wgpuDevicePushErrorScope(handler?.handler, filter)

actual fun wgpuDevicePopErrorScope(handler: WGPUDevice?, callbackInfo: WGPUPopErrorScopeCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDevicePopErrorScope(handler?.handler, callbackInfo.toCValue())

actual fun wgpuDeviceSetLabel(handler: WGPUDevice?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuDeviceSetLabel(handler?.handler, label.toCValue())

actual fun wgpuInstanceRelease(handler: WGPUInstance?): Unit
	 = webgpu.android.Functions.wgpuInstanceRelease(handler?.handler)

actual fun wgpuInstanceCreateSurface(handler: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface?
	 = webgpu.android.Functions.wgpuInstanceCreateSurface(handler?.handler, descriptor?.toReference())
	?.let(::WGPUSurface)

actual fun wgpuInstanceGetWGSLLanguageFeatures(handler: WGPUInstance?, features: WGPUSupportedWGSLLanguageFeatures?): WGPUStatus
	 = webgpu.android.Functions.wgpuInstanceGetWGSLLanguageFeatures(handler?.handler, features?.toReference())

actual fun wgpuInstanceHasWGSLLanguageFeature(handler: WGPUInstance?, feature: WGPUWGSLLanguageFeatureName): Boolean
	 = webgpu.android.Functions.wgpuInstanceHasWGSLLanguageFeature(handler?.handler, feature)
	.toBoolean()

actual fun wgpuInstanceProcessEvents(handler: WGPUInstance?): Unit
	 = webgpu.android.Functions.wgpuInstanceProcessEvents(handler?.handler)

actual fun wgpuInstanceRequestAdapter(handler: WGPUInstance?, options: WGPURequestAdapterOptions?, callbackInfo: WGPURequestAdapterCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuInstanceRequestAdapter(handler?.handler, options?.toReference(), callbackInfo.toCValue())

actual fun wgpuInstanceWaitAny(handler: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus
	 = webgpu.android.Functions.wgpuInstanceWaitAny(handler?.handler, futureCount, futures?.toReference(), timeoutNS)

actual fun wgpuPipelineLayoutRelease(handler: WGPUPipelineLayout?): Unit
	 = webgpu.android.Functions.wgpuPipelineLayoutRelease(handler?.handler)

actual fun wgpuPipelineLayoutSetLabel(handler: WGPUPipelineLayout?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuPipelineLayoutSetLabel(handler?.handler, label.toCValue())

actual fun wgpuQuerySetRelease(handler: WGPUQuerySet?): Unit
	 = webgpu.android.Functions.wgpuQuerySetRelease(handler?.handler)

actual fun wgpuQuerySetSetLabel(handler: WGPUQuerySet?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuQuerySetSetLabel(handler?.handler, label.toCValue())

actual fun wgpuQuerySetGetType(handler: WGPUQuerySet?): WGPUQueryType
	 = webgpu.android.Functions.wgpuQuerySetGetType(handler?.handler)

actual fun wgpuQuerySetGetCount(handler: WGPUQuerySet?): UInt
	 = webgpu.android.Functions.wgpuQuerySetGetCount(handler?.handler)

actual fun wgpuQuerySetDestroy(handler: WGPUQuerySet?): Unit
	 = webgpu.android.Functions.wgpuQuerySetDestroy(handler?.handler)

actual fun wgpuQueueRelease(handler: WGPUQueue?): Unit
	 = webgpu.android.Functions.wgpuQueueRelease(handler?.handler)

actual fun wgpuQueueSubmit(handler: WGPUQueue?, commandCount: ULong, commands: ArrayHolder<WGPUCommandBuffer>?): Unit
	 = webgpu.android.Functions.wgpuQueueSubmit(handler?.handler, commandCount, commands?.handler)

actual fun wgpuQueueOnSubmittedWorkDone(handler: WGPUQueue?, callbackInfo: WGPUQueueWorkDoneCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuQueueOnSubmittedWorkDone(handler?.handler, callbackInfo.toCValue())

actual fun wgpuQueueWriteBuffer(handler: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit
	 = webgpu.android.Functions.wgpuQueueWriteBuffer(handler?.handler, buffer?.handler, bufferOffset, data, size)

actual fun wgpuQueueWriteTexture(handler: WGPUQueue?, destination: WGPUTexelCopyTextureInfo?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTexelCopyBufferLayout?, writeSize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuQueueWriteTexture(handler?.handler, destination?.toReference(), data, dataSize, dataLayout?.toReference(), writeSize?.toReference())

actual fun wgpuQueueSetLabel(handler: WGPUQueue?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuQueueSetLabel(handler?.handler, label.toCValue())

actual fun wgpuRenderBundleRelease(handler: WGPURenderBundle?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleRelease(handler?.handler)

actual fun wgpuRenderBundleSetLabel(handler: WGPURenderBundle?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleSetLabel(handler?.handler, label.toCValue())

actual fun wgpuRenderBundleEncoderRelease(handler: WGPURenderBundleEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderRelease(handler?.handler)

actual fun wgpuRenderBundleEncoderSetPipeline(handler: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuRenderBundleEncoderSetBindGroup(handler: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsetCount, dynamicOffsets?.handler)

actual fun wgpuRenderBundleEncoderDraw(handler: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDraw(handler?.handler, vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndexed(handler: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndexed(handler?.handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndexedIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderBundleEncoderInsertDebugMarker(handler: WGPURenderBundleEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderInsertDebugMarker(handler?.handler, markerLabel.toCValue())

actual fun wgpuRenderBundleEncoderPopDebugGroup(handler: WGPURenderBundleEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderPopDebugGroup(handler?.handler)

actual fun wgpuRenderBundleEncoderPushDebugGroup(handler: WGPURenderBundleEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderPushDebugGroup(handler?.handler, groupLabel.toCValue())

actual fun wgpuRenderBundleEncoderSetVertexBuffer(handler: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetVertexBuffer(handler?.handler, slot, buffer?.handler, offset, size)

actual fun wgpuRenderBundleEncoderSetIndexBuffer(handler: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetIndexBuffer(handler?.handler, buffer?.handler, format, offset, size)

actual fun wgpuRenderBundleEncoderFinish(handler: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle?
	 = webgpu.android.Functions.wgpuRenderBundleEncoderFinish(handler?.handler, descriptor?.toReference())
	?.let(::WGPURenderBundle)

actual fun wgpuRenderBundleEncoderSetLabel(handler: WGPURenderBundleEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetLabel(handler?.handler, label.toCValue())

actual fun wgpuRenderPassEncoderRelease(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderRelease(handler?.handler)

actual fun wgpuRenderPassEncoderSetPipeline(handler: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetPipeline(handler?.handler, pipeline?.handler)

actual fun wgpuRenderPassEncoderSetBindGroup(handler: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetBindGroup(handler?.handler, groupIndex, group?.handler, dynamicOffsetCount, dynamicOffsets?.handler)

actual fun wgpuRenderPassEncoderDraw(handler: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDraw(handler?.handler, vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndexed(handler: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndexed(handler?.handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndexedIndirect(handler?.handler, indirectBuffer?.handler, indirectOffset)

actual fun wgpuRenderPassEncoderExecuteBundles(handler: WGPURenderPassEncoder?, bundleCount: ULong, bundles: ArrayHolder<WGPURenderBundle>?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderExecuteBundles(handler?.handler, bundleCount, bundles?.handler)

actual fun wgpuRenderPassEncoderInsertDebugMarker(handler: WGPURenderPassEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderInsertDebugMarker(handler?.handler, markerLabel.toCValue())

actual fun wgpuRenderPassEncoderPopDebugGroup(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderPopDebugGroup(handler?.handler)

actual fun wgpuRenderPassEncoderPushDebugGroup(handler: WGPURenderPassEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderPushDebugGroup(handler?.handler, groupLabel.toCValue())

actual fun wgpuRenderPassEncoderSetStencilReference(handler: WGPURenderPassEncoder?, reference: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetStencilReference(handler?.handler, reference)

actual fun wgpuRenderPassEncoderSetBlendConstant(handler: WGPURenderPassEncoder?, color: WGPUColor?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetBlendConstant(handler?.handler, color?.toReference())

actual fun wgpuRenderPassEncoderSetViewport(handler: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetViewport(handler?.handler, x, y, width, height, minDepth, maxDepth)

actual fun wgpuRenderPassEncoderSetScissorRect(handler: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetScissorRect(handler?.handler, x, y, width, height)

actual fun wgpuRenderPassEncoderSetVertexBuffer(handler: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetVertexBuffer(handler?.handler, slot, buffer?.handler, offset, size)

actual fun wgpuRenderPassEncoderSetIndexBuffer(handler: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetIndexBuffer(handler?.handler, buffer?.handler, format, offset, size)

actual fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: WGPURenderPassEncoder?, queryIndex: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderBeginOcclusionQuery(handler?.handler, queryIndex)

actual fun wgpuRenderPassEncoderEndOcclusionQuery(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderEndOcclusionQuery(handler?.handler)

actual fun wgpuRenderPassEncoderEnd(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderEnd(handler?.handler)

actual fun wgpuRenderPassEncoderSetLabel(handler: WGPURenderPassEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetLabel(handler?.handler, label.toCValue())

actual fun wgpuRenderPipelineRelease(handler: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderPipelineRelease(handler?.handler)

actual fun wgpuRenderPipelineGetBindGroupLayout(handler: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuRenderPipelineGetBindGroupLayout(handler?.handler, groupIndex)
	?.let(::WGPUBindGroupLayout)

actual fun wgpuRenderPipelineSetLabel(handler: WGPURenderPipeline?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPipelineSetLabel(handler?.handler, label.toCValue())

actual fun wgpuSamplerRelease(handler: WGPUSampler?): Unit
	 = webgpu.android.Functions.wgpuSamplerRelease(handler?.handler)

actual fun wgpuSamplerSetLabel(handler: WGPUSampler?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuSamplerSetLabel(handler?.handler, label.toCValue())

actual fun wgpuShaderModuleRelease(handler: WGPUShaderModule?): Unit
	 = webgpu.android.Functions.wgpuShaderModuleRelease(handler?.handler)

actual fun wgpuShaderModuleGetCompilationInfo(handler: WGPUShaderModule?, callbackInfo: WGPUCompilationInfoCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuShaderModuleGetCompilationInfo(handler?.handler, callbackInfo.toCValue())

actual fun wgpuShaderModuleSetLabel(handler: WGPUShaderModule?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuShaderModuleSetLabel(handler?.handler, label.toCValue())

actual fun wgpuSurfaceRelease(handler: WGPUSurface?): Unit
	 = webgpu.android.Functions.wgpuSurfaceRelease(handler?.handler)

actual fun wgpuSurfaceConfigure(handler: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit
	 = webgpu.android.Functions.wgpuSurfaceConfigure(handler?.handler, config?.toReference())

actual fun wgpuSurfaceGetCapabilities(handler: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): WGPUStatus
	 = webgpu.android.Functions.wgpuSurfaceGetCapabilities(handler?.handler, adapter?.handler, capabilities?.toReference())

actual fun wgpuSurfaceGetCurrentTexture(handler: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit
	 = webgpu.android.Functions.wgpuSurfaceGetCurrentTexture(handler?.handler, surfaceTexture?.toReference())

actual fun wgpuSurfacePresent(handler: WGPUSurface?): WGPUStatus
	 = webgpu.android.Functions.wgpuSurfacePresent(handler?.handler)

actual fun wgpuSurfaceUnconfigure(handler: WGPUSurface?): Unit
	 = webgpu.android.Functions.wgpuSurfaceUnconfigure(handler?.handler)

actual fun wgpuSurfaceSetLabel(handler: WGPUSurface?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuSurfaceSetLabel(handler?.handler, label.toCValue())

actual fun wgpuTextureRelease(handler: WGPUTexture?): Unit
	 = webgpu.android.Functions.wgpuTextureRelease(handler?.handler)

actual fun wgpuTextureCreateView(handler: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView?
	 = webgpu.android.Functions.wgpuTextureCreateView(handler?.handler, descriptor?.toReference())
	?.let(::WGPUTextureView)

actual fun wgpuTextureSetLabel(handler: WGPUTexture?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuTextureSetLabel(handler?.handler, label.toCValue())

actual fun wgpuTextureGetWidth(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetWidth(handler?.handler)

actual fun wgpuTextureGetHeight(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetHeight(handler?.handler)

actual fun wgpuTextureGetDepthOrArrayLayers(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetDepthOrArrayLayers(handler?.handler)

actual fun wgpuTextureGetMipLevelCount(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetMipLevelCount(handler?.handler)

actual fun wgpuTextureGetSampleCount(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetSampleCount(handler?.handler)

actual fun wgpuTextureGetDimension(handler: WGPUTexture?): WGPUTextureDimension
	 = webgpu.android.Functions.wgpuTextureGetDimension(handler?.handler)

actual fun wgpuTextureGetFormat(handler: WGPUTexture?): WGPUTextureFormat
	 = webgpu.android.Functions.wgpuTextureGetFormat(handler?.handler)

actual fun wgpuTextureGetUsage(handler: WGPUTexture?): ULong
	 = webgpu.android.Functions.wgpuTextureGetUsage(handler?.handler)

actual fun wgpuTextureDestroy(handler: WGPUTexture?): Unit
	 = webgpu.android.Functions.wgpuTextureDestroy(handler?.handler)

actual fun wgpuTextureViewRelease(handler: WGPUTextureView?): Unit
	 = webgpu.android.Functions.wgpuTextureViewRelease(handler?.handler)

actual fun wgpuTextureViewSetLabel(handler: WGPUTextureView?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuTextureViewSetLabel(handler?.handler, label.toCValue())

actual fun wgpuSetLogLevel(level: WGPULogLevel): Unit
	 = webgpu.android.Functions.wgpuSetLogLevel(level)

actual fun wgpuSetLogCallback(callback: CallbackHolder<WGPULogCallback>?, userdata: NativeAddress?): Unit
	 = webgpu.android.Functions.wgpuSetLogCallback(callback?.callback, userdata)

