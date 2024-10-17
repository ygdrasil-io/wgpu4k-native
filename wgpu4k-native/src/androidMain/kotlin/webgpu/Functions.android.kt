// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.CString
import ffi.NativeAddress
import ffi.ArrayHolder
import ffi.adapt


actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?
	 = webgpu.android.Functions.wgpuCreateInstance(descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUInstance)

actual fun wgpuAdapterRelease(handler: WGPUAdapter?): Unit
	 = webgpu.android.Functions.wgpuAdapterRelease(handler?.handler.adapt())

actual fun wgpuAdapterGetLimits(handler: WGPUAdapter?, limits: WGPUSupportedLimits?): Boolean
	 = webgpu.android.Functions.wgpuAdapterGetLimits(handler?.handler.adapt(), limits?.handler.adapt())
	.toBoolean()

actual fun wgpuAdapterHasFeature(handler: WGPUAdapter?, feature: WGPUFeatureName): Boolean
	 = webgpu.android.Functions.wgpuAdapterHasFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuAdapterGetFeatures(handler: WGPUAdapter?, features: WGPUSupportedFeatures?): WGPUStatus
	 = webgpu.android.Functions.wgpuAdapterGetFeatures(handler?.handler.adapt(), features?.handler.adapt())

actual fun wgpuAdapterGetInfo(handler: WGPUAdapter?, info: WGPUAdapterInfo?): Unit
	 = webgpu.android.Functions.wgpuAdapterGetInfo(handler?.handler.adapt(), info?.handler.adapt())

actual fun wgpuAdapterRequestDevice(handler: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?, callbackInfo: WGPURequestDeviceCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuAdapterRequestDevice(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuBindGroupRelease(handler: WGPUBindGroup?): Unit
	 = webgpu.android.Functions.wgpuBindGroupRelease(handler?.handler.adapt())

actual fun wgpuBindGroupSetLabel(handler: WGPUBindGroup?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBindGroupSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBindGroupLayoutRelease(handler: WGPUBindGroupLayout?): Unit
	 = webgpu.android.Functions.wgpuBindGroupLayoutRelease(handler?.handler.adapt())

actual fun wgpuBindGroupLayoutSetLabel(handler: WGPUBindGroupLayout?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBindGroupLayoutSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBufferRelease(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferRelease(handler?.handler.adapt())

actual fun wgpuBufferMapAsync(handler: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuBufferMapAsync(handler?.handler.adapt(), mode, offset, size, callbackInfo.toCValue())

actual fun wgpuBufferGetMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = webgpu.android.Functions.wgpuBufferGetMappedRange(handler?.handler.adapt(), offset, size)
	?.let(::NativeAddress)

actual fun wgpuBufferGetConstMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?
	 = webgpu.android.Functions.wgpuBufferGetConstMappedRange(handler?.handler.adapt(), offset, size)
	?.let(::NativeAddress)

actual fun wgpuBufferSetLabel(handler: WGPUBuffer?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuBufferSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuBufferGetUsage(handler: WGPUBuffer?): ULong
	 = webgpu.android.Functions.wgpuBufferGetUsage(handler?.handler.adapt())

actual fun wgpuBufferGetSize(handler: WGPUBuffer?): ULong
	 = webgpu.android.Functions.wgpuBufferGetSize(handler?.handler.adapt())

actual fun wgpuBufferGetMapState(handler: WGPUBuffer?): WGPUBufferMapState
	 = webgpu.android.Functions.wgpuBufferGetMapState(handler?.handler.adapt())

actual fun wgpuBufferUnmap(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferUnmap(handler?.handler.adapt())

actual fun wgpuBufferDestroy(handler: WGPUBuffer?): Unit
	 = webgpu.android.Functions.wgpuBufferDestroy(handler?.handler.adapt())

actual fun wgpuCommandBufferRelease(handler: WGPUCommandBuffer?): Unit
	 = webgpu.android.Functions.wgpuCommandBufferRelease(handler?.handler.adapt())

actual fun wgpuCommandBufferSetLabel(handler: WGPUCommandBuffer?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandBufferSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuCommandEncoderRelease(handler: WGPUCommandEncoder?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderRelease(handler?.handler.adapt())

actual fun wgpuCommandEncoderFinish(handler: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer?
	 = webgpu.android.Functions.wgpuCommandEncoderFinish(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUCommandBuffer)

actual fun wgpuCommandEncoderBeginComputePass(handler: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder?
	 = webgpu.android.Functions.wgpuCommandEncoderBeginComputePass(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUComputePassEncoder)

actual fun wgpuCommandEncoderBeginRenderPass(handler: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder?
	 = webgpu.android.Functions.wgpuCommandEncoderBeginRenderPass(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderPassEncoder)

actual fun wgpuCommandEncoderCopyBufferToBuffer(handler: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyBufferToBuffer(handler?.handler.adapt(), source?.handler.adapt(), sourceOffset, destination?.handler.adapt(), destinationOffset, size)

actual fun wgpuCommandEncoderCopyBufferToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyBuffer?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyBufferToTexture(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderCopyTextureToBuffer(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyBuffer?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyTextureToBuffer(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderCopyTextureToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderCopyTextureToTexture(handler?.handler.adapt(), source?.handler.adapt(), destination?.handler.adapt(), copySize?.handler.adapt())

actual fun wgpuCommandEncoderClearBuffer(handler: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderClearBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), offset, size)

actual fun wgpuCommandEncoderInsertDebugMarker(handler: WGPUCommandEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuCommandEncoderPopDebugGroup(handler: WGPUCommandEncoder?): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuCommandEncoderPushDebugGroup(handler: WGPUCommandEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuCommandEncoderResolveQuerySet(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderResolveQuerySet(handler?.handler.adapt(), querySet?.handler.adapt(), firstQuery, queryCount, destination?.handler.adapt(), destinationOffset)

actual fun wgpuCommandEncoderWriteTimestamp(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderWriteTimestamp(handler?.handler.adapt(), querySet?.handler.adapt(), queryIndex)

actual fun wgpuCommandEncoderSetLabel(handler: WGPUCommandEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuCommandEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuComputePassEncoderRelease(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderRelease(handler?.handler.adapt())

actual fun wgpuComputePassEncoderInsertDebugMarker(handler: WGPUComputePassEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuComputePassEncoderPopDebugGroup(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuComputePassEncoderPushDebugGroup(handler: WGPUComputePassEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuComputePassEncoderSetPipeline(handler: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuComputePassEncoderSetBindGroup(handler: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuComputePassEncoderDispatchWorkgroups(handler: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderDispatchWorkgroups(handler?.handler.adapt(), workgroupCountX, workgroupCountY, workgroupCountZ)

actual fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuComputePassEncoderEnd(handler: WGPUComputePassEncoder?): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderEnd(handler?.handler.adapt())

actual fun wgpuComputePassEncoderSetLabel(handler: WGPUComputePassEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePassEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuComputePipelineRelease(handler: WGPUComputePipeline?): Unit
	 = webgpu.android.Functions.wgpuComputePipelineRelease(handler?.handler.adapt())

actual fun wgpuComputePipelineGetBindGroupLayout(handler: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuComputePipelineGetBindGroupLayout(handler?.handler.adapt(), groupIndex)
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuComputePipelineSetLabel(handler: WGPUComputePipeline?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuComputePipelineSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuDeviceRelease(handler: WGPUDevice?): Unit
	 = webgpu.android.Functions.wgpuDeviceRelease(handler?.handler.adapt())

actual fun wgpuDeviceCreateBindGroup(handler: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup?
	 = webgpu.android.Functions.wgpuDeviceCreateBindGroup(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBindGroup)

actual fun wgpuDeviceCreateBindGroupLayout(handler: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuDeviceCreateBindGroupLayout(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuDeviceCreateBuffer(handler: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer?
	 = webgpu.android.Functions.wgpuDeviceCreateBuffer(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUBuffer)

actual fun wgpuDeviceCreateCommandEncoder(handler: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder?
	 = webgpu.android.Functions.wgpuDeviceCreateCommandEncoder(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUCommandEncoder)

actual fun wgpuDeviceCreateComputePipeline(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline?
	 = webgpu.android.Functions.wgpuDeviceCreateComputePipeline(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUComputePipeline)

actual fun wgpuDeviceCreateComputePipelineAsync(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDeviceCreateComputePipelineAsync(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceCreatePipelineLayout(handler: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout?
	 = webgpu.android.Functions.wgpuDeviceCreatePipelineLayout(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUPipelineLayout)

actual fun wgpuDeviceCreateQuerySet(handler: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet?
	 = webgpu.android.Functions.wgpuDeviceCreateQuerySet(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUQuerySet)

actual fun wgpuDeviceCreateRenderPipelineAsync(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDeviceCreateRenderPipelineAsync(handler?.handler.adapt(), descriptor?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceCreateRenderBundleEncoder(handler: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder?
	 = webgpu.android.Functions.wgpuDeviceCreateRenderBundleEncoder(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderBundleEncoder)

actual fun wgpuDeviceCreateRenderPipeline(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline?
	 = webgpu.android.Functions.wgpuDeviceCreateRenderPipeline(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderPipeline)

actual fun wgpuDeviceCreateSampler(handler: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler?
	 = webgpu.android.Functions.wgpuDeviceCreateSampler(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUSampler)

actual fun wgpuDeviceCreateShaderModule(handler: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?
	 = webgpu.android.Functions.wgpuDeviceCreateShaderModule(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUShaderModule)

actual fun wgpuDeviceCreateTexture(handler: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture?
	 = webgpu.android.Functions.wgpuDeviceCreateTexture(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUTexture)

actual fun wgpuDeviceDestroy(handler: WGPUDevice?): Unit
	 = webgpu.android.Functions.wgpuDeviceDestroy(handler?.handler.adapt())

actual fun wgpuDeviceGetLimits(handler: WGPUDevice?, limits: WGPUSupportedLimits?): Boolean
	 = webgpu.android.Functions.wgpuDeviceGetLimits(handler?.handler.adapt(), limits?.handler.adapt())
	.toBoolean()

actual fun wgpuDeviceHasFeature(handler: WGPUDevice?, feature: WGPUFeatureName): Boolean
	 = webgpu.android.Functions.wgpuDeviceHasFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuDeviceGetFeatures(handler: WGPUDevice?, features: WGPUSupportedFeatures?): WGPUStatus
	 = webgpu.android.Functions.wgpuDeviceGetFeatures(handler?.handler.adapt(), features?.handler.adapt())

actual fun wgpuDeviceGetQueue(handler: WGPUDevice?): WGPUQueue?
	 = webgpu.android.Functions.wgpuDeviceGetQueue(handler?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUQueue)

actual fun wgpuDevicePushErrorScope(handler: WGPUDevice?, filter: WGPUErrorFilter): Unit
	 = webgpu.android.Functions.wgpuDevicePushErrorScope(handler?.handler.adapt(), filter)

actual fun wgpuDevicePopErrorScope(handler: WGPUDevice?, callbackInfo: WGPUPopErrorScopeCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuDevicePopErrorScope(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuDeviceSetLabel(handler: WGPUDevice?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuDeviceSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuInstanceRelease(handler: WGPUInstance?): Unit
	 = webgpu.android.Functions.wgpuInstanceRelease(handler?.handler.adapt())

actual fun wgpuInstanceCreateSurface(handler: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface?
	 = webgpu.android.Functions.wgpuInstanceCreateSurface(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUSurface)

actual fun wgpuInstanceHasWGSLLanguageFeature(handler: WGPUInstance?, feature: WGPUWGSLFeatureName): Boolean
	 = webgpu.android.Functions.wgpuInstanceHasWGSLLanguageFeature(handler?.handler.adapt(), feature)
	.toBoolean()

actual fun wgpuInstanceProcessEvents(handler: WGPUInstance?): Unit
	 = webgpu.android.Functions.wgpuInstanceProcessEvents(handler?.handler.adapt())

actual fun wgpuInstanceRequestAdapter(handler: WGPUInstance?, options: WGPURequestAdapterOptions?, callbackInfo: WGPURequestAdapterCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuInstanceRequestAdapter(handler?.handler.adapt(), options?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuInstanceWaitAny(handler: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus
	 = webgpu.android.Functions.wgpuInstanceWaitAny(handler?.handler.adapt(), futureCount, futures?.handler.adapt(), timeoutNS)

actual fun wgpuPipelineLayoutRelease(handler: WGPUPipelineLayout?): Unit
	 = webgpu.android.Functions.wgpuPipelineLayoutRelease(handler?.handler.adapt())

actual fun wgpuPipelineLayoutSetLabel(handler: WGPUPipelineLayout?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuPipelineLayoutSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuQuerySetRelease(handler: WGPUQuerySet?): Unit
	 = webgpu.android.Functions.wgpuQuerySetRelease(handler?.handler.adapt())

actual fun wgpuQuerySetSetLabel(handler: WGPUQuerySet?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuQuerySetSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuQuerySetGetType(handler: WGPUQuerySet?): WGPUQueryType
	 = webgpu.android.Functions.wgpuQuerySetGetType(handler?.handler.adapt())

actual fun wgpuQuerySetGetCount(handler: WGPUQuerySet?): UInt
	 = webgpu.android.Functions.wgpuQuerySetGetCount(handler?.handler.adapt())

actual fun wgpuQuerySetDestroy(handler: WGPUQuerySet?): Unit
	 = webgpu.android.Functions.wgpuQuerySetDestroy(handler?.handler.adapt())

actual fun wgpuQueueRelease(handler: WGPUQueue?): Unit
	 = webgpu.android.Functions.wgpuQueueRelease(handler?.handler.adapt())

actual fun wgpuQueueSubmit(handler: WGPUQueue?, commandCount: ULong, commands: ArrayHolder<WGPUCommandBuffer>?): Unit
	 = webgpu.android.Functions.wgpuQueueSubmit(handler?.handler.adapt(), commandCount, commands?.handler.adapt())

actual fun wgpuQueueOnSubmittedWorkDone(handler: WGPUQueue?, callbackInfo: WGPUQueueWorkDoneCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuQueueOnSubmittedWorkDone(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuQueueWriteBuffer(handler: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit
	 = webgpu.android.Functions.wgpuQueueWriteBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), bufferOffset, data.adapt(), size)

actual fun wgpuQueueWriteTexture(handler: WGPUQueue?, destination: WGPUImageCopyTexture?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTextureDataLayout?, writeSize: WGPUExtent3D?): Unit
	 = webgpu.android.Functions.wgpuQueueWriteTexture(handler?.handler.adapt(), destination?.handler.adapt(), data.adapt(), dataSize, dataLayout?.handler.adapt(), writeSize?.handler.adapt())

actual fun wgpuQueueSetLabel(handler: WGPUQueue?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuQueueSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderBundleRelease(handler: WGPURenderBundle?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleRelease(handler?.handler.adapt())

actual fun wgpuRenderBundleSetLabel(handler: WGPURenderBundle?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderBundleEncoderRelease(handler: WGPURenderBundleEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderRelease(handler?.handler.adapt())

actual fun wgpuRenderBundleEncoderSetPipeline(handler: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuRenderBundleEncoderSetBindGroup(handler: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuRenderBundleEncoderDraw(handler: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDraw(handler?.handler.adapt(), vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndexed(handler: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndexed(handler?.handler.adapt(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderBundleEncoderDrawIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderDrawIndexedIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderBundleEncoderInsertDebugMarker(handler: WGPURenderBundleEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuRenderBundleEncoderPopDebugGroup(handler: WGPURenderBundleEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuRenderBundleEncoderPushDebugGroup(handler: WGPURenderBundleEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuRenderBundleEncoderSetVertexBuffer(handler: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetVertexBuffer(handler?.handler.adapt(), slot, buffer?.handler.adapt(), offset, size)

actual fun wgpuRenderBundleEncoderSetIndexBuffer(handler: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetIndexBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), format, offset, size)

actual fun wgpuRenderBundleEncoderFinish(handler: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle?
	 = webgpu.android.Functions.wgpuRenderBundleEncoderFinish(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPURenderBundle)

actual fun wgpuRenderBundleEncoderSetLabel(handler: WGPURenderBundleEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderBundleEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderPassEncoderRelease(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderRelease(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderSetPipeline(handler: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetPipeline(handler?.handler.adapt(), pipeline?.handler.adapt())

actual fun wgpuRenderPassEncoderSetBindGroup(handler: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetBindGroup(handler?.handler.adapt(), groupIndex, group?.handler.adapt(), dynamicOffsetCount, dynamicOffsets?.handler.adapt())

actual fun wgpuRenderPassEncoderDraw(handler: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDraw(handler?.handler.adapt(), vertexCount, instanceCount, firstVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndexed(handler: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndexed(handler?.handler.adapt(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)

actual fun wgpuRenderPassEncoderDrawIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderDrawIndexedIndirect(handler?.handler.adapt(), indirectBuffer?.handler.adapt(), indirectOffset)

actual fun wgpuRenderPassEncoderExecuteBundles(handler: WGPURenderPassEncoder?, bundleCount: ULong, bundles: ArrayHolder<WGPURenderBundle>?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderExecuteBundles(handler?.handler.adapt(), bundleCount, bundles?.handler.adapt())

actual fun wgpuRenderPassEncoderInsertDebugMarker(handler: WGPURenderPassEncoder?, markerLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderInsertDebugMarker(handler?.handler.adapt(), markerLabel.toCValue())

actual fun wgpuRenderPassEncoderPopDebugGroup(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderPopDebugGroup(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderPushDebugGroup(handler: WGPURenderPassEncoder?, groupLabel: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderPushDebugGroup(handler?.handler.adapt(), groupLabel.toCValue())

actual fun wgpuRenderPassEncoderSetStencilReference(handler: WGPURenderPassEncoder?, reference: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetStencilReference(handler?.handler.adapt(), reference)

actual fun wgpuRenderPassEncoderSetBlendConstant(handler: WGPURenderPassEncoder?, color: WGPUColor?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetBlendConstant(handler?.handler.adapt(), color?.handler.adapt())

actual fun wgpuRenderPassEncoderSetViewport(handler: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetViewport(handler?.handler.adapt(), x, y, width, height, minDepth, maxDepth)

actual fun wgpuRenderPassEncoderSetScissorRect(handler: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetScissorRect(handler?.handler.adapt(), x, y, width, height)

actual fun wgpuRenderPassEncoderSetVertexBuffer(handler: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetVertexBuffer(handler?.handler.adapt(), slot, buffer?.handler.adapt(), offset, size)

actual fun wgpuRenderPassEncoderSetIndexBuffer(handler: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetIndexBuffer(handler?.handler.adapt(), buffer?.handler.adapt(), format, offset, size)

actual fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: WGPURenderPassEncoder?, queryIndex: UInt): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderBeginOcclusionQuery(handler?.handler.adapt(), queryIndex)

actual fun wgpuRenderPassEncoderEndOcclusionQuery(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderEndOcclusionQuery(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderEnd(handler: WGPURenderPassEncoder?): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderEnd(handler?.handler.adapt())

actual fun wgpuRenderPassEncoderSetLabel(handler: WGPURenderPassEncoder?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPassEncoderSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuRenderPipelineRelease(handler: WGPURenderPipeline?): Unit
	 = webgpu.android.Functions.wgpuRenderPipelineRelease(handler?.handler.adapt())

actual fun wgpuRenderPipelineGetBindGroupLayout(handler: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout?
	 = webgpu.android.Functions.wgpuRenderPipelineGetBindGroupLayout(handler?.handler.adapt(), groupIndex)
	?.let(::NativeAddress)?.let(::WGPUBindGroupLayout)

actual fun wgpuRenderPipelineSetLabel(handler: WGPURenderPipeline?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuRenderPipelineSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuSamplerRelease(handler: WGPUSampler?): Unit
	 = webgpu.android.Functions.wgpuSamplerRelease(handler?.handler.adapt())

actual fun wgpuSamplerSetLabel(handler: WGPUSampler?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuSamplerSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuShaderModuleRelease(handler: WGPUShaderModule?): Unit
	 = webgpu.android.Functions.wgpuShaderModuleRelease(handler?.handler.adapt())

actual fun wgpuShaderModuleGetCompilationInfo(handler: WGPUShaderModule?, callbackInfo: WGPUCompilationInfoCallbackInfo): Unit
	 = webgpu.android.Functions.wgpuShaderModuleGetCompilationInfo(handler?.handler.adapt(), callbackInfo.toCValue())

actual fun wgpuShaderModuleSetLabel(handler: WGPUShaderModule?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuShaderModuleSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuSurfaceRelease(handler: WGPUSurface?): Unit
	 = webgpu.android.Functions.wgpuSurfaceRelease(handler?.handler.adapt())

actual fun wgpuSurfaceConfigure(handler: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit
	 = webgpu.android.Functions.wgpuSurfaceConfigure(handler?.handler.adapt(), config?.handler.adapt())

actual fun wgpuSurfaceGetCapabilities(handler: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): Boolean
	 = webgpu.android.Functions.wgpuSurfaceGetCapabilities(handler?.handler.adapt(), adapter?.handler.adapt(), capabilities?.handler.adapt())
	.toBoolean()

actual fun wgpuSurfaceGetCurrentTexture(handler: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit
	 = webgpu.android.Functions.wgpuSurfaceGetCurrentTexture(handler?.handler.adapt(), surfaceTexture?.handler.adapt())

actual fun wgpuSurfacePresent(handler: WGPUSurface?): Unit
	 = webgpu.android.Functions.wgpuSurfacePresent(handler?.handler.adapt())

actual fun wgpuSurfaceUnconfigure(handler: WGPUSurface?): Unit
	 = webgpu.android.Functions.wgpuSurfaceUnconfigure(handler?.handler.adapt())

actual fun wgpuSurfaceSetLabel(handler: WGPUSurface?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuSurfaceSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuTextureRelease(handler: WGPUTexture?): Unit
	 = webgpu.android.Functions.wgpuTextureRelease(handler?.handler.adapt())

actual fun wgpuTextureCreateView(handler: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView?
	 = webgpu.android.Functions.wgpuTextureCreateView(handler?.handler.adapt(), descriptor?.handler.adapt())
	?.let(::NativeAddress)?.let(::WGPUTextureView)

actual fun wgpuTextureSetLabel(handler: WGPUTexture?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuTextureSetLabel(handler?.handler.adapt(), label.toCValue())

actual fun wgpuTextureGetWidth(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetWidth(handler?.handler.adapt())

actual fun wgpuTextureGetHeight(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetHeight(handler?.handler.adapt())

actual fun wgpuTextureGetDepthOrArrayLayers(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetDepthOrArrayLayers(handler?.handler.adapt())

actual fun wgpuTextureGetMipLevelCount(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetMipLevelCount(handler?.handler.adapt())

actual fun wgpuTextureGetSampleCount(handler: WGPUTexture?): UInt
	 = webgpu.android.Functions.wgpuTextureGetSampleCount(handler?.handler.adapt())

actual fun wgpuTextureGetDimension(handler: WGPUTexture?): WGPUTextureDimension
	 = webgpu.android.Functions.wgpuTextureGetDimension(handler?.handler.adapt())

actual fun wgpuTextureGetFormat(handler: WGPUTexture?): WGPUTextureFormat
	 = webgpu.android.Functions.wgpuTextureGetFormat(handler?.handler.adapt())

actual fun wgpuTextureGetUsage(handler: WGPUTexture?): ULong
	 = webgpu.android.Functions.wgpuTextureGetUsage(handler?.handler.adapt())

actual fun wgpuTextureDestroy(handler: WGPUTexture?): Unit
	 = webgpu.android.Functions.wgpuTextureDestroy(handler?.handler.adapt())

actual fun wgpuTextureViewRelease(handler: WGPUTextureView?): Unit
	 = webgpu.android.Functions.wgpuTextureViewRelease(handler?.handler.adapt())

actual fun wgpuTextureViewSetLabel(handler: WGPUTextureView?, label: WGPUStringView): Unit
	 = webgpu.android.Functions.wgpuTextureViewSetLabel(handler?.handler.adapt(), label.toCValue())

