// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import com.sun.jna.Native

object Functions {

	init {
		Native.register(Functions::class.java, "wgpu4k")
	}

	external fun wgpuCreateInstance(descriptor: Long): Long
	external fun wgpuGetInstanceFeatures(features: Long): Unit
	external fun wgpuAdapterGetLimits(handler: Long, limits: Long): UInt
	external fun wgpuAdapterHasFeature(handler: Long, feature: UInt): UInt
	external fun wgpuAdapterEnumerateFeatures(handler: Long, features: UInt): ULong
	external fun wgpuAdapterGetInfo(handler: Long, info: Long): Unit
	external fun wgpuAdapterRequestDevice(handler: Long, descriptor: Long): Unit
	external fun wgpuBindGroupSetLabel(handler: Long, label: Long): Unit
	external fun wgpuBindGroupLayoutSetLabel(handler: Long, label: Long): Unit
	external fun wgpuBufferMapAsync(handler: Long, mode: ULong, offset: ULong, size: ULong): Unit
	external fun wgpuBufferGetMappedRange(handler: Long, offset: ULong, size: ULong): Unit
	external fun wgpuBufferGetConstMappedRange(handler: Long, offset: ULong, size: ULong): Unit
	external fun wgpuBufferSetLabel(handler: Long, label: Long): Unit
	external fun wgpuBufferGetUsage(handler: Long): ULong
	external fun wgpuBufferGetSize(handler: Long): ULong
	external fun wgpuBufferGetMapState(handler: Long): UInt
	external fun wgpuBufferUnmap(handler: Long): Unit
	external fun wgpuBufferDestroy(handler: Long): Unit
	external fun wgpuCommandBufferSetLabel(handler: Long, label: Long): Unit
	external fun wgpuCommandEncoderFinish(handler: Long, descriptor: Long): Long
	external fun wgpuCommandEncoderBeginComputePass(handler: Long, descriptor: Long): Long
	external fun wgpuCommandEncoderBeginRenderPass(handler: Long, descriptor: Long): Long
	external fun wgpuCommandEncoderCopyBufferToBuffer(handler: Long, source: Long, sourceOffset: ULong, destination: Long, destinationOffset: ULong, size: ULong): Unit
	external fun wgpuCommandEncoderCopyBufferToTexture(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	external fun wgpuCommandEncoderCopyTextureToBuffer(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	external fun wgpuCommandEncoderCopyTextureToTexture(handler: Long, source: Long, destination: Long, copySize: Long): Unit
	external fun wgpuCommandEncoderClearBuffer(handler: Long, buffer: Long, offset: ULong, size: ULong): Unit
	external fun wgpuCommandEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	external fun wgpuCommandEncoderPopDebugGroup(handler: Long): Unit
	external fun wgpuCommandEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	external fun wgpuCommandEncoderResolveQuerySet(handler: Long, querySet: Long, firstQuery: UInt, queryCount: UInt, destination: Long, destinationOffset: ULong): Unit
	external fun wgpuCommandEncoderWriteTimestamp(handler: Long, querySet: Long, queryIndex: UInt): Unit
	external fun wgpuCommandEncoderSetLabel(handler: Long, label: Long): Unit
	external fun wgpuComputePassEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	external fun wgpuComputePassEncoderPopDebugGroup(handler: Long): Unit
	external fun wgpuComputePassEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	external fun wgpuComputePassEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	external fun wgpuComputePassEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsets: Long): Unit
	external fun wgpuComputePassEncoderDispatchWorkgroups(handler: Long, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	external fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	external fun wgpuComputePassEncoderEnd(handler: Long): Unit
	external fun wgpuComputePassEncoderSetLabel(handler: Long, label: Long): Unit
	external fun wgpuComputePipelineGetBindGroupLayout(handler: Long, groupIndex: UInt): Long
	external fun wgpuComputePipelineSetLabel(handler: Long, label: Long): Unit
	external fun wgpuDeviceCreateBindGroup(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateBindGroupLayout(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateBuffer(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateCommandEncoder(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateComputePipeline(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateComputePipelineAsync(handler: Long, descriptor: Long): Unit
	external fun wgpuDeviceCreatePipelineLayout(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateQuerySet(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateRenderPipelineAsync(handler: Long, descriptor: Long): Unit
	external fun wgpuDeviceCreateRenderBundleEncoder(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateRenderPipeline(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateSampler(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateShaderModule(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceCreateTexture(handler: Long, descriptor: Long): Long
	external fun wgpuDeviceDestroy(handler: Long): Unit
	external fun wgpuDeviceGetLimits(handler: Long, limits: Long): UInt
	external fun wgpuDeviceHasFeature(handler: Long, feature: UInt): UInt
	external fun wgpuDeviceEnumerateFeatures(handler: Long, features: UInt): ULong
	external fun wgpuDeviceGetQueue(handler: Long): Long
	external fun wgpuDevicePushErrorScope(handler: Long, filter: UInt): Unit
	external fun wgpuDevicePopErrorScope(handler: Long): Unit
	external fun wgpuDeviceSetLabel(handler: Long, label: Long): Unit
	external fun wgpuInstanceCreateSurface(handler: Long, descriptor: Long): Long
	external fun wgpuInstanceHasWGSLLanguageFeature(handler: Long, feature: UInt): UInt
	external fun wgpuInstanceProcessEvents(handler: Long): Unit
	external fun wgpuInstanceRequestAdapter(handler: Long, options: Long): Unit
	external fun wgpuInstanceWaitAny(handler: Long, futureCount: ULong, futures: Long, timeoutNS: ULong): UInt
	external fun wgpuPipelineLayoutSetLabel(handler: Long, label: Long): Unit
	external fun wgpuQuerySetSetLabel(handler: Long, label: Long): Unit
	external fun wgpuQuerySetGetType(handler: Long): UInt
	external fun wgpuQuerySetGetCount(handler: Long): UInt
	external fun wgpuQuerySetDestroy(handler: Long): Unit
	external fun wgpuQueueSubmit(handler: Long, commands: Long): Unit
	external fun wgpuQueueOnSubmittedWorkDone(handler: Long): Unit
	external fun wgpuQueueWriteBuffer(handler: Long, buffer: Long, bufferOffset: ULong, data: Long, size: ULong): Unit
	external fun wgpuQueueWriteTexture(handler: Long, destination: Long, data: Long, dataSize: ULong, dataLayout: Long, writeSize: Long): Unit
	external fun wgpuQueueSetLabel(handler: Long, label: Long): Unit
	external fun wgpuRenderBundleSetLabel(handler: Long, label: Long): Unit
	external fun wgpuRenderBundleEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	external fun wgpuRenderBundleEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsets: Long): Unit
	external fun wgpuRenderBundleEncoderDraw(handler: Long, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	external fun wgpuRenderBundleEncoderDrawIndexed(handler: Long, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	external fun wgpuRenderBundleEncoderDrawIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	external fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	external fun wgpuRenderBundleEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	external fun wgpuRenderBundleEncoderPopDebugGroup(handler: Long): Unit
	external fun wgpuRenderBundleEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	external fun wgpuRenderBundleEncoderSetVertexBuffer(handler: Long, slot: UInt, buffer: Long, offset: ULong, size: ULong): Unit
	external fun wgpuRenderBundleEncoderSetIndexBuffer(handler: Long, buffer: Long, format: UInt, offset: ULong, size: ULong): Unit
	external fun wgpuRenderBundleEncoderFinish(handler: Long, descriptor: Long): Long
	external fun wgpuRenderBundleEncoderSetLabel(handler: Long, label: Long): Unit
	external fun wgpuRenderPassEncoderSetPipeline(handler: Long, pipeline: Long): Unit
	external fun wgpuRenderPassEncoderSetBindGroup(handler: Long, groupIndex: UInt, group: Long, dynamicOffsets: Long): Unit
	external fun wgpuRenderPassEncoderDraw(handler: Long, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	external fun wgpuRenderPassEncoderDrawIndexed(handler: Long, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	external fun wgpuRenderPassEncoderDrawIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	external fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: Long, indirectBuffer: Long, indirectOffset: ULong): Unit
	external fun wgpuRenderPassEncoderExecuteBundles(handler: Long, bundles: Long): Unit
	external fun wgpuRenderPassEncoderInsertDebugMarker(handler: Long, markerLabel: Long): Unit
	external fun wgpuRenderPassEncoderPopDebugGroup(handler: Long): Unit
	external fun wgpuRenderPassEncoderPushDebugGroup(handler: Long, groupLabel: Long): Unit
	external fun wgpuRenderPassEncoderSetStencilReference(handler: Long, reference: UInt): Unit
	external fun wgpuRenderPassEncoderSetBlendConstant(handler: Long, color: Long): Unit
	external fun wgpuRenderPassEncoderSetViewport(handler: Long, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	external fun wgpuRenderPassEncoderSetScissorRect(handler: Long, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	external fun wgpuRenderPassEncoderSetVertexBuffer(handler: Long, slot: UInt, buffer: Long, offset: ULong, size: ULong): Unit
	external fun wgpuRenderPassEncoderSetIndexBuffer(handler: Long, buffer: Long, format: UInt, offset: ULong, size: ULong): Unit
	external fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: Long, queryIndex: UInt): Unit
	external fun wgpuRenderPassEncoderEndOcclusionQuery(handler: Long): Unit
	external fun wgpuRenderPassEncoderEnd(handler: Long): Unit
	external fun wgpuRenderPassEncoderSetLabel(handler: Long, label: Long): Unit
	external fun wgpuRenderPipelineGetBindGroupLayout(handler: Long, groupIndex: UInt): Long
	external fun wgpuRenderPipelineSetLabel(handler: Long, label: Long): Unit
	external fun wgpuSamplerSetLabel(handler: Long, label: Long): Unit
	external fun wgpuShaderModuleGetCompilationInfo(handler: Long): Unit
	external fun wgpuShaderModuleSetLabel(handler: Long, label: Long): Unit
	external fun wgpuSurfaceConfigure(handler: Long, config: Long): Unit
	external fun wgpuSurfaceGetCapabilities(handler: Long, adapter: Long, capabilities: Long): UInt
	external fun wgpuSurfaceGetCurrentTexture(handler: Long, surfaceTexture: Long): Unit
	external fun wgpuSurfacePresent(handler: Long): Unit
	external fun wgpuSurfaceUnconfigure(handler: Long): Unit
	external fun wgpuSurfaceSetLabel(handler: Long, label: Long): Unit
	external fun wgpuTextureCreateView(handler: Long, descriptor: Long): Long
	external fun wgpuTextureSetLabel(handler: Long, label: Long): Unit
	external fun wgpuTextureGetWidth(handler: Long): UInt
	external fun wgpuTextureGetHeight(handler: Long): UInt
	external fun wgpuTextureGetDepthOrArrayLayers(handler: Long): UInt
	external fun wgpuTextureGetMipLevelCount(handler: Long): UInt
	external fun wgpuTextureGetSampleCount(handler: Long): UInt
	external fun wgpuTextureGetDimension(handler: Long): UInt
	external fun wgpuTextureGetFormat(handler: Long): UInt
	external fun wgpuTextureGetUsage(handler: Long): ULong
	external fun wgpuTextureDestroy(handler: Long): Unit
	external fun wgpuTextureViewSetLabel(handler: Long, label: Long): Unit
}
