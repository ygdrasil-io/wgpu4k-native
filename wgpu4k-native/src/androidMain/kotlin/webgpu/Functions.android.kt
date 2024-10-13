// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.CString
import ffi.NativeAddress
import ffi.ArrayHolder
import ffi.adapt


actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?
	 = Functions.wgpuCreateInstance(descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUInstance)

actual fun wgpuAdapterRelease(handler: WGPUAdapter?): Unit
	 = Functions.wgpuAdapterRelease(handler?.handler.adapt())

actual fun wgpuAdapterGetLimits(handler: WGPUAdapter?, limits: WGPUSupportedLimits?): Boolean
	 = Functions.wgpuAdapterGetLimits(handler?.handler.adapt(), limits?.handler.adapt())
	.toBoolean()

actual fun wgpuAdapterHasFeature(handler: WGPUAdapter?, feature: WGPUFeatureName): Boolean
	 = Functions.wgpuAdapterHasFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuAdapterGetFeatures(handler: WGPUAdapter?, features: WGPUSupportedFeatures?): WGPUStatus
	 = Functions.wgpuAdapterGetFeatures(handler?.handler.adapt(), features?.handler.adapt())

actual fun wgpuAdapterGetInfo(handler: WGPUAdapter?, info: WGPUAdapterInfo?): Unit
	 = Functions.wgpuAdapterGetInfo(handler?.handler.adapt(), info?.handler.adapt())

actual fun wgpuAdapterRequestDevice(handler: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?, callbackInfo: WGPURequestDeviceCallbackInfo): Unit
	 = Functions.wgpuAdapterRequestDevice(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuBindGroupRelease(handler: WGPUBindGroup?): Unit
	 = Functions.wgpuBindGroupRelease(handler?.handler.adapt())

actual fun wgpuBindGroupSetLabel(handler: WGPUBindGroup?, label: WGPUStringView): Unit
	 = Functions.wgpuBindGroupSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBindGroupLayoutRelease(handler: WGPUBindGroupLayout?): Unit
	 = Functions.wgpuBindGroupLayoutRelease(handler?.handler.adapt())

actual fun wgpuBindGroupLayoutSetLabel(handler: WGPUBindGroupLayout?, label: WGPUStringView): Unit
	 = Functions.wgpuBindGroupLayoutSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBufferRelease(handler: WGPUBuffer?): Unit
	 = Functions.wgpuBufferRelease(handler?.handler.adapt())

actual fun wgpuBufferMapAsync(handler: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo): Unit
	 = Functions.wgpuBufferMapAsync(handler?.handler.adapt(), mode, offset, size, callbackInfo.toCValue())

actual fun wgpuBufferGetMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = Functions.wgpuBufferGetMappedRange(handler?.handler.adapt(), offset, size)
	?.let(::NativeAddress)

actual fun wgpuBufferGetConstMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = Functions.wgpuBufferGetConstMappedRange(handler?.handler.adapt(), offset, size)
	?.let(::NativeAddress)

actual fun wgpuBufferSetLabel(handler: WGPUBuffer?, label: WGPUStringView): Unit
	 = Functions.wgpuBufferSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBufferGetUsage(handler: WGPUBuffer?): ULong
	 = Functions.wgpuBufferGetUsage(handler?.handler.adapt())

actual fun wgpuBufferGetSize(handler: WGPUBuffer?): ULong
	 = Functions.wgpuBufferGetSize(handler?.handler.adapt())

actual fun wgpuBufferGetMapState(handler: WGPUBuffer?): WGPUBufferMapState
	 = Functions.wgpuBufferGetMapState(handler?.handler.adapt())

actual fun wgpuBufferUnmap(handler: WGPUBuffer?): Unit
	 = Functions.wgpuBufferUnmap(handler?.handler.adapt())

actual fun wgpuBufferDestroy(handler: WGPUBuffer?): Unit
	 = Functions.wgpuBufferDestroy(handler?.handler.adapt())

actual fun wgpuCommandBufferRelease(handler: WGPUCommandBuffer?): Unit
	 = Functions.wgpuCommandBufferRelease(handler?.handler.adapt())

actual fun wgpuCommandBufferSetLabel(handler: WGPUCommandBuffer?, label: WGPUStringView): Unit
	 = Functions.wgpuCommandBufferSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuCommandEncoderRelease(handler: WGPUCommandEncoder?): Unit
	 = Functions.wgpuCommandEncoderRelease(handler?.handler.adapt())

actual fun wgpuCommandEncoderFinish(handler: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer?
	 = Functions.wgpuCommandEncoderFinish(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUCommandBuffer)

actual fun wgpuCommandEncoderBeginComputePass(handler: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder?
	 = Functions.wgpuCommandEncoderBeginComputePass(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUComputePassEncoder)

actual fun wgpuCommandEncoderBeginRenderPass(handler: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder?
	 = Functions.wgpuCommandEncoderBeginRenderPass(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderPassEncoder)

actual fun wgpuCommandEncoderCopyBufferToBuffer(handler: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit
	 = Functions.wgpuCommandEncoderCopyBufferToBuffer(handler?.handler.adapt(), source?.handler.adapt(), sourceOffset, destination?.handler.adapt(), destinationOffset, size)

actual fun wgpuCommandEncoderCopyBufferToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyBuffer?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit
	 = Functions.wgpuCommandEncoderCopyBufferToTexture(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderCopyTextureToBuffer(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyBuffer?, copySize: WGPUExtent3D?): Unit
	 = Functions.wgpuCommandEncoderCopyTextureToBuffer(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderCopyTextureToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit
	 = Functions.wgpuCommandEncoderCopyTextureToTexture(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderClearBuffer(handler: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = Functions.wgpuCommandEncoderClearBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), offset, size)

actual fun wgpuCommandEncoderInsertDebugMarker(handler: WGPUCommandEncoder?, markerLabel: WGPUStringView): Unit
	 = Functions.wgpuCommandEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuCommandEncoderPopDebugGroup(handler: WGPUCommandEncoder?): Unit
	 = Functions.wgpuCommandEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuCommandEncoderPushDebugGroup(handler: WGPUCommandEncoder?, groupLabel: WGPUStringView): Unit
	 = Functions.wgpuCommandEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuCommandEncoderResolveQuerySet(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit
	 = Functions.wgpuCommandEncoderResolveQuerySet(handler?.handler.adapt(), querySet?.handler.adapt(), firstQuery, queryCount, destination?.handler.adapt(), destinationOffset)

actual fun wgpuCommandEncoderWriteTimestamp(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit
	 = Functions.wgpuCommandEncoderWriteTimestamp(handler?.handler.adapt(), querySet?.handler.adapt(), queryIndex)

actual fun wgpuCommandEncoderSetLabel(handler: WGPUCommandEncoder?, label: WGPUStringView): Unit
	 = Functions.wgpuCommandEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuComputePassEncoderRelease(handler: WGPUComputePassEncoder?): Unit
	 = Functions.wgpuComputePassEncoderRelease(handler?.handler.adapt())

actual fun wgpuComputePassEncoderInsertDebugMarker(handler: WGPUComputePassEncoder?, markerLabel: WGPUStringView): Unit
	 = Functions.wgpuComputePassEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuComputePassEncoderPopDebugGroup(handler: WGPUComputePassEncoder?): Unit
	 = Functions.wgpuComputePassEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuComputePassEncoderPushDebugGroup(handler: WGPUComputePassEncoder?, groupLabel: WGPUStringView): Unit
	 = Functions.wgpuComputePassEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuComputePassEncoderSetPipeline(handler: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit
	 = Functions.wgpuComputePassEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuComputePassEncoderSetBindGroup(handler: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = Functions.wgpuComputePassEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuComputePassEncoderDispatchWorkgroups(handler: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	 = Functions.wgpuComputePassEncoderDispatchWorkgroups(handler?.handler.adapt(), workgroupCountX, workgroupCountY, workgroupCountZ)

actual fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = Functions.wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuComputePassEncoderEnd(handler: WGPUComputePassEncoder?): Unit
	 = Functions.wgpuComputePassEncoderEnd(handler?.handler.adapt())

actual fun wgpuComputePassEncoderSetLabel(handler: WGPUComputePassEncoder?, label: WGPUStringView): Unit
	 = Functions.wgpuComputePassEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuComputePipelineRelease(handler: WGPUComputePipeline?): Unit
	 = Functions.wgpuComputePipelineRelease(handler?.handler.adapt())

actual fun wgpuComputePipelineGetBindGroupLayout(handler: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = Functions.wgpuComputePipelineGetBindGroupLayout(handler?.handler.adapt(), groupIndex)
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuComputePipelineSetLabel(handler: WGPUComputePipeline?, label: WGPUStringView): Unit
	 = Functions.wgpuComputePipelineSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuDeviceRelease(handler: WGPUDevice?): Unit
	 = Functions.wgpuDeviceRelease(handler?.handler.adapt())

actual fun wgpuDeviceCreateBindGroup(handler: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup?
	 = Functions.wgpuDeviceCreateBindGroup(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBindGroup)

actual fun wgpuDeviceCreateBindGroupLayout(handler: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout?
	 = Functions.wgpuDeviceCreateBindGroupLayout(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuDeviceCreateBuffer(handler: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer?
	 = Functions.wgpuDeviceCreateBuffer(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBuffer)

actual fun wgpuDeviceCreateCommandEncoder(handler: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder?
	 = Functions.wgpuDeviceCreateCommandEncoder(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUCommandEncoder)

actual fun wgpuDeviceCreateComputePipeline(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline?
	 = Functions.wgpuDeviceCreateComputePipeline(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUComputePipeline)

actual fun wgpuDeviceCreateComputePipelineAsync(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo): Unit
	 = Functions.wgpuDeviceCreateComputePipelineAsync(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceCreatePipelineLayout(handler: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout?
	 = Functions.wgpuDeviceCreatePipelineLayout(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUPipelineLayout)

actual fun wgpuDeviceCreateQuerySet(handler: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet?
	 = Functions.wgpuDeviceCreateQuerySet(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUQuerySet)

actual fun wgpuDeviceCreateRenderPipelineAsync(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo): Unit
	 = Functions.wgpuDeviceCreateRenderPipelineAsync(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceCreateRenderBundleEncoder(handler: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder?
	 = Functions.wgpuDeviceCreateRenderBundleEncoder(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderBundleEncoder)

actual fun wgpuDeviceCreateRenderPipeline(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline?
	 = Functions.wgpuDeviceCreateRenderPipeline(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderPipeline)

actual fun wgpuDeviceCreateSampler(handler: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler?
	 = Functions.wgpuDeviceCreateSampler(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUSampler)

actual fun wgpuDeviceCreateShaderModule(handler: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?
	 = Functions.wgpuDeviceCreateShaderModule(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUShaderModule)

actual fun wgpuDeviceCreateTexture(handler: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture?
	 = Functions.wgpuDeviceCreateTexture(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUTexture)

actual fun wgpuDeviceDestroy(handler: WGPUDevice?): Unit
	 = Functions.wgpuDeviceDestroy(handler?.handler.adapt())

actual fun wgpuDeviceGetLimits(handler: WGPUDevice?, limits: WGPUSupportedLimits?): Boolean
	 = Functions.wgpuDeviceGetLimits(handler?.handler.adapt(), limits?.handler.adapt())
	.toBoolean()

actual fun wgpuDeviceHasFeature(handler: WGPUDevice?, feature: WGPUFeatureName): Boolean
	 = Functions.wgpuDeviceHasFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuDeviceGetFeatures(handler: WGPUDevice?, features: WGPUSupportedFeatures?): WGPUStatus
	 = Functions.wgpuDeviceGetFeatures(handler?.handler.adapt(), features?.handler.adapt())

actual fun wgpuDeviceGetQueue(handler: WGPUDevice?): WGPUQueue?
	 = Functions.wgpuDeviceGetQueue(handler?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUQueue)

actual fun wgpuDevicePushErrorScope(handler: WGPUDevice?, filter: WGPUErrorFilter): Unit
	 = Functions.wgpuDevicePushErrorScope(handler?.handler.adapt(), filter)

actual fun wgpuDevicePopErrorScope(handler: WGPUDevice?, callbackInfo: WGPUPopErrorScopeCallbackInfo): Unit
	 = Functions.wgpuDevicePopErrorScope(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceSetLabel(handler: WGPUDevice?, label: WGPUStringView): Unit
	 = Functions.wgpuDeviceSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuInstanceRelease(handler: WGPUInstance?): Unit
	 = Functions.wgpuInstanceRelease(handler?.handler.adapt())

actual fun wgpuInstanceCreateSurface(handler: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface?
	 = Functions.wgpuInstanceCreateSurface(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUSurface)

actual fun wgpuInstanceHasWGSLLanguageFeature(handler: WGPUInstance?, feature: WGPUWGSLFeatureName): Boolean
	 = Functions.wgpuInstanceHasWGSLLanguageFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuInstanceProcessEvents(handler: WGPUInstance?): Unit
	 = Functions.wgpuInstanceProcessEvents(handler?.handler.adapt())

actual fun wgpuInstanceRequestAdapter(handler: WGPUInstance?, options: WGPURequestAdapterOptions?, callbackInfo: WGPURequestAdapterCallbackInfo): Unit
	 = Functions.wgpuInstanceRequestAdapter(handler?.handler.adapt(), options?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuInstanceWaitAny(handler: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus
	 = Functions.wgpuInstanceWaitAny(handler?.handler.adapt(), futureCount, futures?.handler.adapt(), timeoutNS)

actual fun wgpuPipelineLayoutRelease(handler: WGPUPipelineLayout?): Unit
	 = Functions.wgpuPipelineLayoutRelease(handler?.handler.adapt())

actual fun wgpuPipelineLayoutSetLabel(handler: WGPUPipelineLayout?, label: WGPUStringView): Unit
	 = Functions.wgpuPipelineLayoutSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuQuerySetRelease(handler: WGPUQuerySet?): Unit
	 = Functions.wgpuQuerySetRelease(handler?.handler.adapt())

actual fun wgpuQuerySetSetLabel(handler: WGPUQuerySet?, label: WGPUStringView): Unit
	 = Functions.wgpuQuerySetSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuQuerySetGetType(handler: WGPUQuerySet?): WGPUQueryType
	 = Functions.wgpuQuerySetGetType(handler?.handler.adapt())

actual fun wgpuQuerySetGetCount(handler: WGPUQuerySet?): UInt
	 = Functions.wgpuQuerySetGetCount(handler?.handler.adapt())

actual fun wgpuQuerySetDestroy(handler: WGPUQuerySet?): Unit
	 = Functions.wgpuQuerySetDestroy(handler?.handler.adapt())

actual fun wgpuQueueRelease(handler: WGPUQueue?): Unit
	 = Functions.wgpuQueueRelease(handler?.handler.adapt())

actual fun wgpuQueueSubmit(handler: WGPUQueue?, commandCount: ULong, commands: ArrayHolder<WGPUCommandBuffer>?): Unit
	 = Functions.wgpuQueueSubmit(handler?.handler.adapt(), commandCount, commands?.handler.adapt())

actual fun wgpuQueueOnSubmittedWorkDone(handler: WGPUQueue?, callbackInfo: WGPUQueueWorkDoneCallbackInfo): Unit
	 = Functions.wgpuQueueOnSubmittedWorkDone(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuQueueWriteBuffer(handler: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit
	 = Functions.wgpuQueueWriteBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), bufferOffset, data.adapt(), size)

actual fun wgpuQueueWriteTexture(handler: WGPUQueue?, destination: WGPUImageCopyTexture?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTextureDataLayout?, writeSize: WGPUExtent3D?): Unit
	 = Functions.wgpuQueueWriteTexture(handler?.handler.adapt(), destination?.handler.adapt(), data.adapt(), dataSize, dataLayout?.handler.adapt(), writeSize?.handler.adapt())

actual fun wgpuQueueSetLabel(handler: WGPUQueue?, label: WGPUStringView): Unit
	 = Functions.wgpuQueueSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderBundleRelease(handler: WGPURenderBundle?): Unit
	 = Functions.wgpuRenderBundleRelease(handler?.handler.adapt())

actual fun wgpuRenderBundleSetLabel(handler: WGPURenderBundle?, label: WGPUStringView): Unit
	 = Functions.wgpuRenderBundleSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderBundleEncoderRelease(handler: WGPURenderBundleEncoder?): Unit
	 = Functions.wgpuRenderBundleEncoderRelease(handler?.handler.adapt())

actual fun wgpuRenderBundleEncoderSetPipeline(handler: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = Functions.wgpuRenderBundleEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuRenderBundleEncoderSetBindGroup(handler: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = Functions.wgpuRenderBundleEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuRenderBundleEncoderDraw(handler: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = Functions.wgpuRenderBundleEncoderDraw(handler?.handler.adapt(), vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndexed(handler: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = Functions.wgpuRenderBundleEncoderDrawIndexed(handler?.handler.adapt(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = Functions.wgpuRenderBundleEncoderDrawIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = Functions.wgpuRenderBundleEncoderDrawIndexedIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderBundleEncoderInsertDebugMarker(handler: WGPURenderBundleEncoder?, markerLabel: WGPUStringView): Unit
	 = Functions.wgpuRenderBundleEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuRenderBundleEncoderPopDebugGroup(handler: WGPURenderBundleEncoder?): Unit
	 = Functions.wgpuRenderBundleEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuRenderBundleEncoderPushDebugGroup(handler: WGPURenderBundleEncoder?, groupLabel: WGPUStringView): Unit
	 = Functions.wgpuRenderBundleEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuRenderBundleEncoderSetVertexBuffer(handler: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = Functions.wgpuRenderBundleEncoderSetVertexBuffer(handler?.handler.adapt(), slot, buffer?.handler.adapt(), offset, size)

actual fun wgpuRenderBundleEncoderSetIndexBuffer(handler: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = Functions.wgpuRenderBundleEncoderSetIndexBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), format, offset, size)

actual fun wgpuRenderBundleEncoderFinish(handler: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle?
	 = Functions.wgpuRenderBundleEncoderFinish(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderBundle)

actual fun wgpuRenderBundleEncoderSetLabel(handler: WGPURenderBundleEncoder?, label: WGPUStringView): Unit
	 = Functions.wgpuRenderBundleEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderPassEncoderRelease(handler: WGPURenderPassEncoder?): Unit
	 = Functions.wgpuRenderPassEncoderRelease(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderSetPipeline(handler: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = Functions.wgpuRenderPassEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuRenderPassEncoderSetBindGroup(handler: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = Functions.wgpuRenderPassEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuRenderPassEncoderDraw(handler: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = Functions.wgpuRenderPassEncoderDraw(handler?.handler.adapt(), vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndexed(handler: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = Functions.wgpuRenderPassEncoderDrawIndexed(handler?.handler.adapt(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = Functions.wgpuRenderPassEncoderDrawIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = Functions.wgpuRenderPassEncoderDrawIndexedIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderPassEncoderExecuteBundles(handler: WGPURenderPassEncoder?, bundleCount: ULong, bundles: ArrayHolder<WGPURenderBundle>?): Unit
	 = Functions.wgpuRenderPassEncoderExecuteBundles(handler?.handler.adapt(), bundleCount, bundles?.handler.adapt())

actual fun wgpuRenderPassEncoderInsertDebugMarker(handler: WGPURenderPassEncoder?, markerLabel: WGPUStringView): Unit
	 = Functions.wgpuRenderPassEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuRenderPassEncoderPopDebugGroup(handler: WGPURenderPassEncoder?): Unit
	 = Functions.wgpuRenderPassEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderPushDebugGroup(handler: WGPURenderPassEncoder?, groupLabel: WGPUStringView): Unit
	 = Functions.wgpuRenderPassEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuRenderPassEncoderSetStencilReference(handler: WGPURenderPassEncoder?, reference: UInt): Unit
	 = Functions.wgpuRenderPassEncoderSetStencilReference(handler?.handler.adapt(), reference)

actual fun wgpuRenderPassEncoderSetBlendConstant(handler: WGPURenderPassEncoder?, color: WGPUColor?): Unit
	 = Functions.wgpuRenderPassEncoderSetBlendConstant(handler?.handler.adapt(), color?.handler.adapt())

actual fun wgpuRenderPassEncoderSetViewport(handler: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	 = Functions.wgpuRenderPassEncoderSetViewport(handler?.handler.adapt(), x, y, width, height, minDepth, maxDepth)

actual fun wgpuRenderPassEncoderSetScissorRect(handler: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	 = Functions.wgpuRenderPassEncoderSetScissorRect(handler?.handler.adapt(), x, y, width, height)

actual fun wgpuRenderPassEncoderSetVertexBuffer(handler: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = Functions.wgpuRenderPassEncoderSetVertexBuffer(handler?.handler.adapt(), slot, buffer?.handler.adapt(), offset, size)

actual fun wgpuRenderPassEncoderSetIndexBuffer(handler: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = Functions.wgpuRenderPassEncoderSetIndexBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), format, offset, size)

actual fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: WGPURenderPassEncoder?, queryIndex: UInt): Unit
	 = Functions.wgpuRenderPassEncoderBeginOcclusionQuery(handler?.handler.adapt(), queryIndex)

actual fun wgpuRenderPassEncoderEndOcclusionQuery(handler: WGPURenderPassEncoder?): Unit
	 = Functions.wgpuRenderPassEncoderEndOcclusionQuery(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderEnd(handler: WGPURenderPassEncoder?): Unit
	 = Functions.wgpuRenderPassEncoderEnd(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderSetLabel(handler: WGPURenderPassEncoder?, label: WGPUStringView): Unit
	 = Functions.wgpuRenderPassEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderPipelineRelease(handler: WGPURenderPipeline?): Unit
	 = Functions.wgpuRenderPipelineRelease(handler?.handler.adapt())

actual fun wgpuRenderPipelineGetBindGroupLayout(handler: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = Functions.wgpuRenderPipelineGetBindGroupLayout(handler?.handler.adapt(), groupIndex)
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuRenderPipelineSetLabel(handler: WGPURenderPipeline?, label: WGPUStringView): Unit
	 = Functions.wgpuRenderPipelineSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuSamplerRelease(handler: WGPUSampler?): Unit
	 = Functions.wgpuSamplerRelease(handler?.handler.adapt())

actual fun wgpuSamplerSetLabel(handler: WGPUSampler?, label: WGPUStringView): Unit
	 = Functions.wgpuSamplerSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuShaderModuleRelease(handler: WGPUShaderModule?): Unit
	 = Functions.wgpuShaderModuleRelease(handler?.handler.adapt())

actual fun wgpuShaderModuleGetCompilationInfo(handler: WGPUShaderModule?, callbackInfo: WGPUCompilationInfoCallbackInfo): Unit
	 = Functions.wgpuShaderModuleGetCompilationInfo(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuShaderModuleSetLabel(handler: WGPUShaderModule?, label: WGPUStringView): Unit
	 = Functions.wgpuShaderModuleSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuSurfaceRelease(handler: WGPUSurface?): Unit
	 = Functions.wgpuSurfaceRelease(handler?.handler.adapt())

actual fun wgpuSurfaceConfigure(handler: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit
	 = Functions.wgpuSurfaceConfigure(handler?.handler.adapt(), config?.handler.adapt())

actual fun wgpuSurfaceGetCapabilities(handler: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): Boolean
	 = Functions.wgpuSurfaceGetCapabilities(handler?.handler.adapt(), adapter?.handler.adapt(), capabilities?.handler.adapt())
	.toBoolean()

actual fun wgpuSurfaceGetCurrentTexture(handler: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit
	 = Functions.wgpuSurfaceGetCurrentTexture(handler?.handler.adapt(), surfaceTexture?.handler.adapt())

actual fun wgpuSurfacePresent(handler: WGPUSurface?): Unit
	 = Functions.wgpuSurfacePresent(handler?.handler.adapt())

actual fun wgpuSurfaceUnconfigure(handler: WGPUSurface?): Unit
	 = Functions.wgpuSurfaceUnconfigure(handler?.handler.adapt())

actual fun wgpuSurfaceSetLabel(handler: WGPUSurface?, label: WGPUStringView): Unit
	 = Functions.wgpuSurfaceSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuTextureRelease(handler: WGPUTexture?): Unit
	 = Functions.wgpuTextureRelease(handler?.handler.adapt())

actual fun wgpuTextureCreateView(handler: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView?
	 = Functions.wgpuTextureCreateView(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUTextureView)

actual fun wgpuTextureSetLabel(handler: WGPUTexture?, label: WGPUStringView): Unit
	 = Functions.wgpuTextureSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuTextureGetWidth(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetWidth(handler?.handler.adapt())

actual fun wgpuTextureGetHeight(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetHeight(handler?.handler.adapt())

actual fun wgpuTextureGetDepthOrArrayLayers(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetDepthOrArrayLayers(handler?.handler.adapt())

actual fun wgpuTextureGetMipLevelCount(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetMipLevelCount(handler?.handler.adapt())

actual fun wgpuTextureGetSampleCount(handler: WGPUTexture?): UInt
	 = Functions.wgpuTextureGetSampleCount(handler?.handler.adapt())

actual fun wgpuTextureGetDimension(handler: WGPUTexture?): WGPUTextureDimension
	 = Functions.wgpuTextureGetDimension(handler?.handler.adapt())

actual fun wgpuTextureGetFormat(handler: WGPUTexture?): WGPUTextureFormat
	 = Functions.wgpuTextureGetFormat(handler?.handler.adapt())

actual fun wgpuTextureGetUsage(handler: WGPUTexture?): ULong
	 = Functions.wgpuTextureGetUsage(handler?.handler.adapt())

actual fun wgpuTextureDestroy(handler: WGPUTexture?): Unit
	 = Functions.wgpuTextureDestroy(handler?.handler.adapt())

actual fun wgpuTextureViewRelease(handler: WGPUTextureView?): Unit
	 = Functions.wgpuTextureViewRelease(handler?.handler.adapt())

actual fun wgpuTextureViewSetLabel(handler: WGPUTextureView?, label: WGPUStringView): Unit
	 = Functions.wgpuTextureViewSetLabel(handler?.handler.adapt(), label.toCValue())

