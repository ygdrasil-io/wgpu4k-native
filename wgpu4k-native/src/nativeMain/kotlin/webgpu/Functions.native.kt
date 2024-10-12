@file:OptIn(ExperimentalForeignApi::class)
// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.CString
import ffi.NativeAddress
import ffi.ArrayHolder
import ffi.adapt
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.toCPointer


actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance? {
	 return webgpu.native.wgpuCreateInstance(descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUInstance)
}
actual fun wgpuGetInstanceFeatures(features: WGPUInstanceFeatures?): Unit {
	 webgpu.native.wgpuGetInstanceFeatures(features?.handler?.toCPointer())
}
actual fun wgpuAdapterGetLimits(handler: WGPUAdapter?, limits: WGPUSupportedLimits?): Boolean {
	 return webgpu.native.wgpuAdapterGetLimits(handler?.handler?.toCPointer(), limits?.handler?.toCPointer())
		.toBoolean()
}
actual fun wgpuAdapterHasFeature(handler: WGPUAdapter?, feature: WGPUFeatureName): Boolean {
	 return webgpu.native.wgpuAdapterHasFeature(handler?.handler?.toCPointer(), feature)
		.toBoolean()
}
actual fun wgpuAdapterGetFeatures(handler: WGPUAdapter?, features: WGPUSupportedFeatures?): WGPUStatus {
	 return webgpu.native.wgpuAdapterGetFeatures(handler?.handler?.toCPointer(), features?.handler?.toCPointer())
}
actual fun wgpuAdapterGetInfo(handler: WGPUAdapter?, info: WGPUAdapterInfo?): Unit {
	 webgpu.native.wgpuAdapterGetInfo(handler?.handler?.toCPointer(), info?.handler?.toCPointer())
}
actual fun wgpuAdapterRequestDevice(handler: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?, callbackInfo: WGPURequestDeviceCallbackInfo): Unit {
	 webgpu.native.wgpuAdapterRequestDevice(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuBindGroupSetLabel(handler: WGPUBindGroup?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuBindGroupSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuBindGroupLayoutSetLabel(handler: WGPUBindGroupLayout?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuBindGroupLayoutSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuBufferMapAsync(handler: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo): Unit {
	 webgpu.native.wgpuBufferMapAsync(handler?.handler?.toCPointer(), mode, offset, size, callbackInfo.toCValue())
}
actual fun wgpuBufferGetMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress? {
	 return webgpu.native.wgpuBufferGetMappedRange(handler?.handler?.toCPointer(), offset, size)
		?.rawValue?.toLong()
}
actual fun wgpuBufferGetConstMappedRange(handler: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress? {
	 return webgpu.native.wgpuBufferGetConstMappedRange(handler?.handler?.toCPointer(), offset, size)
		?.rawValue?.toLong()
}
actual fun wgpuBufferSetLabel(handler: WGPUBuffer?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuBufferSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuBufferGetUsage(handler: WGPUBuffer?): ULong {
	 return webgpu.native.wgpuBufferGetUsage(handler?.handler?.toCPointer())
}
actual fun wgpuBufferGetSize(handler: WGPUBuffer?): ULong {
	 return webgpu.native.wgpuBufferGetSize(handler?.handler?.toCPointer())
}
actual fun wgpuBufferGetMapState(handler: WGPUBuffer?): WGPUBufferMapState {
	 return webgpu.native.wgpuBufferGetMapState(handler?.handler?.toCPointer())
}
actual fun wgpuBufferUnmap(handler: WGPUBuffer?): Unit {
	 webgpu.native.wgpuBufferUnmap(handler?.handler?.toCPointer())
}
actual fun wgpuBufferDestroy(handler: WGPUBuffer?): Unit {
	 webgpu.native.wgpuBufferDestroy(handler?.handler?.toCPointer())
}
actual fun wgpuCommandBufferSetLabel(handler: WGPUCommandBuffer?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuCommandBufferSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuCommandEncoderFinish(handler: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer? {
	 return webgpu.native.wgpuCommandEncoderFinish(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUCommandBuffer)
}
actual fun wgpuCommandEncoderBeginComputePass(handler: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder? {
	 return webgpu.native.wgpuCommandEncoderBeginComputePass(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUComputePassEncoder)
}
actual fun wgpuCommandEncoderBeginRenderPass(handler: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder? {
	 return webgpu.native.wgpuCommandEncoderBeginRenderPass(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPURenderPassEncoder)
}
actual fun wgpuCommandEncoderCopyBufferToBuffer(handler: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuCommandEncoderCopyBufferToBuffer(handler?.handler?.toCPointer(), source?.handler?.toCPointer(), sourceOffset, destination?.handler?.toCPointer(), destinationOffset, size)
}
actual fun wgpuCommandEncoderCopyBufferToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyBuffer?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit {
	 webgpu.native.wgpuCommandEncoderCopyBufferToTexture(handler?.handler?.toCPointer(), source?.handler?.toCPointer(), destination?.handler?.toCPointer(), copySize?.handler?.toCPointer())
}
actual fun wgpuCommandEncoderCopyTextureToBuffer(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyBuffer?, copySize: WGPUExtent3D?): Unit {
	 webgpu.native.wgpuCommandEncoderCopyTextureToBuffer(handler?.handler?.toCPointer(), source?.handler?.toCPointer(), destination?.handler?.toCPointer(), copySize?.handler?.toCPointer())
}
actual fun wgpuCommandEncoderCopyTextureToTexture(handler: WGPUCommandEncoder?, source: WGPUImageCopyTexture?, destination: WGPUImageCopyTexture?, copySize: WGPUExtent3D?): Unit {
	 webgpu.native.wgpuCommandEncoderCopyTextureToTexture(handler?.handler?.toCPointer(), source?.handler?.toCPointer(), destination?.handler?.toCPointer(), copySize?.handler?.toCPointer())
}
actual fun wgpuCommandEncoderClearBuffer(handler: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuCommandEncoderClearBuffer(handler?.handler?.toCPointer(), buffer?.handler?.toCPointer(), offset, size)
}
actual fun wgpuCommandEncoderInsertDebugMarker(handler: WGPUCommandEncoder?, markerLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuCommandEncoderInsertDebugMarker(handler?.handler?.toCPointer(), markerLabel.toCValue())
}
actual fun wgpuCommandEncoderPopDebugGroup(handler: WGPUCommandEncoder?): Unit {
	 webgpu.native.wgpuCommandEncoderPopDebugGroup(handler?.handler?.toCPointer())
}
actual fun wgpuCommandEncoderPushDebugGroup(handler: WGPUCommandEncoder?, groupLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuCommandEncoderPushDebugGroup(handler?.handler?.toCPointer(), groupLabel.toCValue())
}
actual fun wgpuCommandEncoderResolveQuerySet(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit {
	 webgpu.native.wgpuCommandEncoderResolveQuerySet(handler?.handler?.toCPointer(), querySet?.handler?.toCPointer(), firstQuery, queryCount, destination?.handler?.toCPointer(), destinationOffset)
}
actual fun wgpuCommandEncoderWriteTimestamp(handler: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit {
	 webgpu.native.wgpuCommandEncoderWriteTimestamp(handler?.handler?.toCPointer(), querySet?.handler?.toCPointer(), queryIndex)
}
actual fun wgpuCommandEncoderSetLabel(handler: WGPUCommandEncoder?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuCommandEncoderSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuComputePassEncoderInsertDebugMarker(handler: WGPUComputePassEncoder?, markerLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuComputePassEncoderInsertDebugMarker(handler?.handler?.toCPointer(), markerLabel.toCValue())
}
actual fun wgpuComputePassEncoderPopDebugGroup(handler: WGPUComputePassEncoder?): Unit {
	 webgpu.native.wgpuComputePassEncoderPopDebugGroup(handler?.handler?.toCPointer())
}
actual fun wgpuComputePassEncoderPushDebugGroup(handler: WGPUComputePassEncoder?, groupLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuComputePassEncoderPushDebugGroup(handler?.handler?.toCPointer(), groupLabel.toCValue())
}
actual fun wgpuComputePassEncoderSetPipeline(handler: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit {
	 webgpu.native.wgpuComputePassEncoderSetPipeline(handler?.handler?.toCPointer(), pipeline?.handler?.toCPointer())
}
actual fun wgpuComputePassEncoderSetBindGroup(handler: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit {
	 webgpu.native.wgpuComputePassEncoderSetBindGroup(handler?.handler?.toCPointer(), groupIndex, group?.handler?.toCPointer(), dynamicOffsetCount, dynamicOffsets?.handler?.toCPointer())
}
actual fun wgpuComputePassEncoderDispatchWorkgroups(handler: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit {
	 webgpu.native.wgpuComputePassEncoderDispatchWorkgroups(handler?.handler?.toCPointer(), workgroupCountX, workgroupCountY, workgroupCountZ)
}
actual fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit {
	 webgpu.native.wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler?.handler?.toCPointer(), indirectBuffer?.handler?.toCPointer(), indirectOffset)
}
actual fun wgpuComputePassEncoderEnd(handler: WGPUComputePassEncoder?): Unit {
	 webgpu.native.wgpuComputePassEncoderEnd(handler?.handler?.toCPointer())
}
actual fun wgpuComputePassEncoderSetLabel(handler: WGPUComputePassEncoder?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuComputePassEncoderSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuComputePipelineGetBindGroupLayout(handler: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout? {
	 return webgpu.native.wgpuComputePipelineGetBindGroupLayout(handler?.handler?.toCPointer(), groupIndex)
		?.rawValue?.toLong()?.let(::WGPUBindGroupLayout)
}
actual fun wgpuComputePipelineSetLabel(handler: WGPUComputePipeline?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuComputePipelineSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuDeviceCreateBindGroup(handler: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup? {
	 return webgpu.native.wgpuDeviceCreateBindGroup(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUBindGroup)
}
actual fun wgpuDeviceCreateBindGroupLayout(handler: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout? {
	 return webgpu.native.wgpuDeviceCreateBindGroupLayout(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUBindGroupLayout)
}
actual fun wgpuDeviceCreateBuffer(handler: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer? {
	 return webgpu.native.wgpuDeviceCreateBuffer(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUBuffer)
}
actual fun wgpuDeviceCreateCommandEncoder(handler: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder? {
	 return webgpu.native.wgpuDeviceCreateCommandEncoder(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUCommandEncoder)
}
actual fun wgpuDeviceCreateComputePipeline(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline? {
	 return webgpu.native.wgpuDeviceCreateComputePipeline(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUComputePipeline)
}
actual fun wgpuDeviceCreateComputePipelineAsync(handler: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo): Unit {
	 webgpu.native.wgpuDeviceCreateComputePipelineAsync(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuDeviceCreatePipelineLayout(handler: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout? {
	 return webgpu.native.wgpuDeviceCreatePipelineLayout(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUPipelineLayout)
}
actual fun wgpuDeviceCreateQuerySet(handler: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet? {
	 return webgpu.native.wgpuDeviceCreateQuerySet(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUQuerySet)
}
actual fun wgpuDeviceCreateRenderPipelineAsync(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo): Unit {
	 webgpu.native.wgpuDeviceCreateRenderPipelineAsync(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuDeviceCreateRenderBundleEncoder(handler: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder? {
	 return webgpu.native.wgpuDeviceCreateRenderBundleEncoder(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPURenderBundleEncoder)
}
actual fun wgpuDeviceCreateRenderPipeline(handler: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline? {
	 return webgpu.native.wgpuDeviceCreateRenderPipeline(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPURenderPipeline)
}
actual fun wgpuDeviceCreateSampler(handler: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler? {
	 return webgpu.native.wgpuDeviceCreateSampler(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUSampler)
}
actual fun wgpuDeviceCreateShaderModule(handler: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule? {
	 return webgpu.native.wgpuDeviceCreateShaderModule(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUShaderModule)
}
actual fun wgpuDeviceCreateTexture(handler: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture? {
	 return webgpu.native.wgpuDeviceCreateTexture(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUTexture)
}
actual fun wgpuDeviceDestroy(handler: WGPUDevice?): Unit {
	 webgpu.native.wgpuDeviceDestroy(handler?.handler?.toCPointer())
}
actual fun wgpuDeviceGetLimits(handler: WGPUDevice?, limits: WGPUSupportedLimits?): Boolean {
	 return webgpu.native.wgpuDeviceGetLimits(handler?.handler?.toCPointer(), limits?.handler?.toCPointer())
		.toBoolean()
}
actual fun wgpuDeviceHasFeature(handler: WGPUDevice?, feature: WGPUFeatureName): Boolean {
	 return webgpu.native.wgpuDeviceHasFeature(handler?.handler?.toCPointer(), feature)
		.toBoolean()
}
actual fun wgpuDeviceGetFeatures(handler: WGPUDevice?, features: WGPUSupportedFeatures?): WGPUStatus {
	 return webgpu.native.wgpuDeviceGetFeatures(handler?.handler?.toCPointer(), features?.handler?.toCPointer())
}
actual fun wgpuDeviceGetQueue(handler: WGPUDevice?): WGPUQueue? {
	 return webgpu.native.wgpuDeviceGetQueue(handler?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUQueue)
}
actual fun wgpuDevicePushErrorScope(handler: WGPUDevice?, filter: WGPUErrorFilter): Unit {
	 webgpu.native.wgpuDevicePushErrorScope(handler?.handler?.toCPointer(), filter)
}
actual fun wgpuDevicePopErrorScope(handler: WGPUDevice?, callbackInfo: WGPUPopErrorScopeCallbackInfo): Unit {
	 webgpu.native.wgpuDevicePopErrorScope(handler?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuDeviceSetLabel(handler: WGPUDevice?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuDeviceSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuInstanceCreateSurface(handler: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface? {
	 return webgpu.native.wgpuInstanceCreateSurface(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUSurface)
}
actual fun wgpuInstanceHasWGSLLanguageFeature(handler: WGPUInstance?, feature: WGPUWGSLFeatureName): Boolean {
	 return webgpu.native.wgpuInstanceHasWGSLLanguageFeature(handler?.handler?.toCPointer(), feature)
		.toBoolean()
}
actual fun wgpuInstanceProcessEvents(handler: WGPUInstance?): Unit {
	 webgpu.native.wgpuInstanceProcessEvents(handler?.handler?.toCPointer())
}
actual fun wgpuInstanceRequestAdapter(handler: WGPUInstance?, options: WGPURequestAdapterOptions?, callbackInfo: WGPURequestAdapterCallbackInfo): Unit {
	 webgpu.native.wgpuInstanceRequestAdapter(handler?.handler?.toCPointer(), options?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuInstanceWaitAny(handler: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus {
	 return webgpu.native.wgpuInstanceWaitAny(handler?.handler?.toCPointer(), futureCount, futures?.handler?.toCPointer(), timeoutNS)
}
actual fun wgpuPipelineLayoutSetLabel(handler: WGPUPipelineLayout?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuPipelineLayoutSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuQuerySetSetLabel(handler: WGPUQuerySet?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuQuerySetSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuQuerySetGetType(handler: WGPUQuerySet?): WGPUQueryType {
	 return webgpu.native.wgpuQuerySetGetType(handler?.handler?.toCPointer())
}
actual fun wgpuQuerySetGetCount(handler: WGPUQuerySet?): UInt {
	 return webgpu.native.wgpuQuerySetGetCount(handler?.handler?.toCPointer())
}
actual fun wgpuQuerySetDestroy(handler: WGPUQuerySet?): Unit {
	 webgpu.native.wgpuQuerySetDestroy(handler?.handler?.toCPointer())
}
actual fun wgpuQueueSubmit(handler: WGPUQueue?, commandCount: ULong, commands: ArrayHolder<WGPUCommandBuffer>?): Unit {
	 webgpu.native.wgpuQueueSubmit(handler?.handler?.toCPointer(), commandCount, commands?.handler?.toCPointer())
}
actual fun wgpuQueueOnSubmittedWorkDone(handler: WGPUQueue?, callbackInfo: WGPUQueueWorkDoneCallbackInfo): Unit {
	 webgpu.native.wgpuQueueOnSubmittedWorkDone(handler?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuQueueWriteBuffer(handler: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit {
	 webgpu.native.wgpuQueueWriteBuffer(handler?.handler?.toCPointer(), buffer?.handler?.toCPointer(), bufferOffset, data?.adapt<UIntVar>(), size)
}
actual fun wgpuQueueWriteTexture(handler: WGPUQueue?, destination: WGPUImageCopyTexture?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTextureDataLayout?, writeSize: WGPUExtent3D?): Unit {
	 webgpu.native.wgpuQueueWriteTexture(handler?.handler?.toCPointer(), destination?.handler?.toCPointer(), data?.adapt<UIntVar>(), dataSize, dataLayout?.handler?.toCPointer(), writeSize?.handler?.toCPointer())
}
actual fun wgpuQueueSetLabel(handler: WGPUQueue?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuQueueSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuRenderBundleSetLabel(handler: WGPURenderBundle?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderBundleSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuRenderBundleEncoderSetPipeline(handler: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit {
	 webgpu.native.wgpuRenderBundleEncoderSetPipeline(handler?.handler?.toCPointer(), pipeline?.handler?.toCPointer())
}
actual fun wgpuRenderBundleEncoderSetBindGroup(handler: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit {
	 webgpu.native.wgpuRenderBundleEncoderSetBindGroup(handler?.handler?.toCPointer(), groupIndex, group?.handler?.toCPointer(), dynamicOffsetCount, dynamicOffsets?.handler?.toCPointer())
}
actual fun wgpuRenderBundleEncoderDraw(handler: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit {
	 webgpu.native.wgpuRenderBundleEncoderDraw(handler?.handler?.toCPointer(), vertexCount, instanceCount, firstVertex, firstInstance)
}
actual fun wgpuRenderBundleEncoderDrawIndexed(handler: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit {
	 webgpu.native.wgpuRenderBundleEncoderDrawIndexed(handler?.handler?.toCPointer(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
}
actual fun wgpuRenderBundleEncoderDrawIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit {
	 webgpu.native.wgpuRenderBundleEncoderDrawIndirect(handler?.handler?.toCPointer(), indirectBuffer?.handler?.toCPointer(), indirectOffset)
}
actual fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit {
	 webgpu.native.wgpuRenderBundleEncoderDrawIndexedIndirect(handler?.handler?.toCPointer(), indirectBuffer?.handler?.toCPointer(), indirectOffset)
}
actual fun wgpuRenderBundleEncoderInsertDebugMarker(handler: WGPURenderBundleEncoder?, markerLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderBundleEncoderInsertDebugMarker(handler?.handler?.toCPointer(), markerLabel.toCValue())
}
actual fun wgpuRenderBundleEncoderPopDebugGroup(handler: WGPURenderBundleEncoder?): Unit {
	 webgpu.native.wgpuRenderBundleEncoderPopDebugGroup(handler?.handler?.toCPointer())
}
actual fun wgpuRenderBundleEncoderPushDebugGroup(handler: WGPURenderBundleEncoder?, groupLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderBundleEncoderPushDebugGroup(handler?.handler?.toCPointer(), groupLabel.toCValue())
}
actual fun wgpuRenderBundleEncoderSetVertexBuffer(handler: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuRenderBundleEncoderSetVertexBuffer(handler?.handler?.toCPointer(), slot, buffer?.handler?.toCPointer(), offset, size)
}
actual fun wgpuRenderBundleEncoderSetIndexBuffer(handler: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuRenderBundleEncoderSetIndexBuffer(handler?.handler?.toCPointer(), buffer?.handler?.toCPointer(), format, offset, size)
}
actual fun wgpuRenderBundleEncoderFinish(handler: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle? {
	 return webgpu.native.wgpuRenderBundleEncoderFinish(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPURenderBundle)
}
actual fun wgpuRenderBundleEncoderSetLabel(handler: WGPURenderBundleEncoder?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderBundleEncoderSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuRenderPassEncoderSetPipeline(handler: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetPipeline(handler?.handler?.toCPointer(), pipeline?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderSetBindGroup(handler: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: ArrayHolder<UInt>?): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetBindGroup(handler?.handler?.toCPointer(), groupIndex, group?.handler?.toCPointer(), dynamicOffsetCount, dynamicOffsets?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderDraw(handler: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit {
	 webgpu.native.wgpuRenderPassEncoderDraw(handler?.handler?.toCPointer(), vertexCount, instanceCount, firstVertex, firstInstance)
}
actual fun wgpuRenderPassEncoderDrawIndexed(handler: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit {
	 webgpu.native.wgpuRenderPassEncoderDrawIndexed(handler?.handler?.toCPointer(), indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
}
actual fun wgpuRenderPassEncoderDrawIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit {
	 webgpu.native.wgpuRenderPassEncoderDrawIndirect(handler?.handler?.toCPointer(), indirectBuffer?.handler?.toCPointer(), indirectOffset)
}
actual fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit {
	 webgpu.native.wgpuRenderPassEncoderDrawIndexedIndirect(handler?.handler?.toCPointer(), indirectBuffer?.handler?.toCPointer(), indirectOffset)
}
actual fun wgpuRenderPassEncoderExecuteBundles(handler: WGPURenderPassEncoder?, bundleCount: ULong, bundles: ArrayHolder<WGPURenderBundle>?): Unit {
	 webgpu.native.wgpuRenderPassEncoderExecuteBundles(handler?.handler?.toCPointer(), bundleCount, bundles?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderInsertDebugMarker(handler: WGPURenderPassEncoder?, markerLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderPassEncoderInsertDebugMarker(handler?.handler?.toCPointer(), markerLabel.toCValue())
}
actual fun wgpuRenderPassEncoderPopDebugGroup(handler: WGPURenderPassEncoder?): Unit {
	 webgpu.native.wgpuRenderPassEncoderPopDebugGroup(handler?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderPushDebugGroup(handler: WGPURenderPassEncoder?, groupLabel: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderPassEncoderPushDebugGroup(handler?.handler?.toCPointer(), groupLabel.toCValue())
}
actual fun wgpuRenderPassEncoderSetStencilReference(handler: WGPURenderPassEncoder?, reference: UInt): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetStencilReference(handler?.handler?.toCPointer(), reference)
}
actual fun wgpuRenderPassEncoderSetBlendConstant(handler: WGPURenderPassEncoder?, color: WGPUColor?): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetBlendConstant(handler?.handler?.toCPointer(), color?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderSetViewport(handler: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetViewport(handler?.handler?.toCPointer(), x, y, width, height, minDepth, maxDepth)
}
actual fun wgpuRenderPassEncoderSetScissorRect(handler: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetScissorRect(handler?.handler?.toCPointer(), x, y, width, height)
}
actual fun wgpuRenderPassEncoderSetVertexBuffer(handler: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetVertexBuffer(handler?.handler?.toCPointer(), slot, buffer?.handler?.toCPointer(), offset, size)
}
actual fun wgpuRenderPassEncoderSetIndexBuffer(handler: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetIndexBuffer(handler?.handler?.toCPointer(), buffer?.handler?.toCPointer(), format, offset, size)
}
actual fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: WGPURenderPassEncoder?, queryIndex: UInt): Unit {
	 webgpu.native.wgpuRenderPassEncoderBeginOcclusionQuery(handler?.handler?.toCPointer(), queryIndex)
}
actual fun wgpuRenderPassEncoderEndOcclusionQuery(handler: WGPURenderPassEncoder?): Unit {
	 webgpu.native.wgpuRenderPassEncoderEndOcclusionQuery(handler?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderEnd(handler: WGPURenderPassEncoder?): Unit {
	 webgpu.native.wgpuRenderPassEncoderEnd(handler?.handler?.toCPointer())
}
actual fun wgpuRenderPassEncoderSetLabel(handler: WGPURenderPassEncoder?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderPassEncoderSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuRenderPipelineGetBindGroupLayout(handler: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout? {
	 return webgpu.native.wgpuRenderPipelineGetBindGroupLayout(handler?.handler?.toCPointer(), groupIndex)
		?.rawValue?.toLong()?.let(::WGPUBindGroupLayout)
}
actual fun wgpuRenderPipelineSetLabel(handler: WGPURenderPipeline?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuRenderPipelineSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuSamplerSetLabel(handler: WGPUSampler?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuSamplerSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuShaderModuleGetCompilationInfo(handler: WGPUShaderModule?, callbackInfo: WGPUCompilationInfoCallbackInfo): Unit {
	 webgpu.native.wgpuShaderModuleGetCompilationInfo(handler?.handler?.toCPointer(), callbackInfo.toCValue())
}
actual fun wgpuShaderModuleSetLabel(handler: WGPUShaderModule?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuShaderModuleSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuSurfaceConfigure(handler: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit {
	 webgpu.native.wgpuSurfaceConfigure(handler?.handler?.toCPointer(), config?.handler?.toCPointer())
}
actual fun wgpuSurfaceGetCapabilities(handler: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): Boolean {
	 return webgpu.native.wgpuSurfaceGetCapabilities(handler?.handler?.toCPointer(), adapter?.handler?.toCPointer(), capabilities?.handler?.toCPointer())
		.toBoolean()
}
actual fun wgpuSurfaceGetCurrentTexture(handler: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit {
	 webgpu.native.wgpuSurfaceGetCurrentTexture(handler?.handler?.toCPointer(), surfaceTexture?.handler?.toCPointer())
}
actual fun wgpuSurfacePresent(handler: WGPUSurface?): Unit {
	 webgpu.native.wgpuSurfacePresent(handler?.handler?.toCPointer())
}
actual fun wgpuSurfaceUnconfigure(handler: WGPUSurface?): Unit {
	 webgpu.native.wgpuSurfaceUnconfigure(handler?.handler?.toCPointer())
}
actual fun wgpuSurfaceSetLabel(handler: WGPUSurface?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuSurfaceSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuTextureCreateView(handler: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView? {
	 return webgpu.native.wgpuTextureCreateView(handler?.handler?.toCPointer(), descriptor?.handler?.toCPointer())
		?.rawValue?.toLong()?.let(::WGPUTextureView)
}
actual fun wgpuTextureSetLabel(handler: WGPUTexture?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuTextureSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
actual fun wgpuTextureGetWidth(handler: WGPUTexture?): UInt {
	 return webgpu.native.wgpuTextureGetWidth(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetHeight(handler: WGPUTexture?): UInt {
	 return webgpu.native.wgpuTextureGetHeight(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetDepthOrArrayLayers(handler: WGPUTexture?): UInt {
	 return webgpu.native.wgpuTextureGetDepthOrArrayLayers(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetMipLevelCount(handler: WGPUTexture?): UInt {
	 return webgpu.native.wgpuTextureGetMipLevelCount(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetSampleCount(handler: WGPUTexture?): UInt {
	 return webgpu.native.wgpuTextureGetSampleCount(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetDimension(handler: WGPUTexture?): WGPUTextureDimension {
	 return webgpu.native.wgpuTextureGetDimension(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetFormat(handler: WGPUTexture?): WGPUTextureFormat {
	 return webgpu.native.wgpuTextureGetFormat(handler?.handler?.toCPointer())
}
actual fun wgpuTextureGetUsage(handler: WGPUTexture?): ULong {
	 return webgpu.native.wgpuTextureGetUsage(handler?.handler?.toCPointer())
}
actual fun wgpuTextureDestroy(handler: WGPUTexture?): Unit {
	 webgpu.native.wgpuTextureDestroy(handler?.handler?.toCPointer())
}
actual fun wgpuTextureViewSetLabel(handler: WGPUTextureView?, label: WGPUStringView): Unit {
	 webgpu.native.wgpuTextureViewSetLabel(handler?.handler?.toCPointer(), label.toCValue())
}
