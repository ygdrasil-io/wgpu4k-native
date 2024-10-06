// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import com.sun.jna.Native

object Functions {

	init {
		Native.register(Functions::class.java, "wgpu4k")
	}

	external fun wgpuCreateInstance(descriptor: NativeAddress): NativeAddress
	external fun wgpuGetInstanceFeatures(features: NativeAddress): Unit
	external fun wgpuAdapterGetLimits(handler: NativeAddress, limits: NativeAddress): Boolean
	external fun wgpuAdapterHasFeature(handler: NativeAddress, feature: UInt): Boolean
	external fun wgpuAdapterEnumerateFeatures(handler: NativeAddress, features: UInt): ULong
	external fun wgpuAdapterGetInfo(handler: NativeAddress, info: NativeAddress): Unit
	external fun wgpuAdapterRequestDevice(handler: NativeAddress, descriptor: NativeAddress): Unit
	external fun wgpuBindGroupSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuBindGroupLayoutSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuBufferMapAsync(handler: NativeAddress, mode: ULong, offset: ULong, size: ULong): Unit
	external fun wgpuBufferGetMappedRange(handler: NativeAddress, offset: ULong, size: ULong): Unit
	external fun wgpuBufferGetConstMappedRange(handler: NativeAddress, offset: ULong, size: ULong): Unit
	external fun wgpuBufferSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuBufferGetUsage(handler: NativeAddress): ULong
	external fun wgpuBufferGetSize(handler: NativeAddress): ULong
	external fun wgpuBufferGetMapState(handler: NativeAddress): UInt
	external fun wgpuBufferUnmap(handler: NativeAddress): Unit
	external fun wgpuBufferDestroy(handler: NativeAddress): Unit
	external fun wgpuCommandBufferSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuCommandEncoderFinish(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuCommandEncoderBeginComputePass(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuCommandEncoderBeginRenderPass(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuCommandEncoderCopyBufferToBuffer(handler: NativeAddress, source: NativeAddress, sourceOffset: ULong, destination: NativeAddress, destinationOffset: ULong, size: ULong): Unit
	external fun wgpuCommandEncoderCopyBufferToTexture(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit
	external fun wgpuCommandEncoderCopyTextureToBuffer(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit
	external fun wgpuCommandEncoderCopyTextureToTexture(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit
	external fun wgpuCommandEncoderClearBuffer(handler: NativeAddress, buffer: NativeAddress, offset: ULong, size: ULong): Unit
	external fun wgpuCommandEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit
	external fun wgpuCommandEncoderPopDebugGroup(handler: NativeAddress): Unit
	external fun wgpuCommandEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit
	external fun wgpuCommandEncoderResolveQuerySet(handler: NativeAddress, querySet: NativeAddress, firstQuery: UInt, queryCount: UInt, destination: NativeAddress, destinationOffset: ULong): Unit
	external fun wgpuCommandEncoderWriteTimestamp(handler: NativeAddress, querySet: NativeAddress, queryIndex: UInt): Unit
	external fun wgpuCommandEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuComputePassEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit
	external fun wgpuComputePassEncoderPopDebugGroup(handler: NativeAddress): Unit
	external fun wgpuComputePassEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit
	external fun wgpuComputePassEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit
	external fun wgpuComputePassEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit
	external fun wgpuComputePassEncoderDispatchWorkgroups(handler: NativeAddress, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit
	external fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit
	external fun wgpuComputePassEncoderEnd(handler: NativeAddress): Unit
	external fun wgpuComputePassEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuComputePipelineGetBindGroupLayout(handler: NativeAddress, groupIndex: UInt): NativeAddress
	external fun wgpuComputePipelineSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuDeviceCreateBindGroup(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateBindGroupLayout(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateBuffer(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateCommandEncoder(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateComputePipeline(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateComputePipelineAsync(handler: NativeAddress, descriptor: NativeAddress): Unit
	external fun wgpuDeviceCreatePipelineLayout(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateQuerySet(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateRenderPipelineAsync(handler: NativeAddress, descriptor: NativeAddress): Unit
	external fun wgpuDeviceCreateRenderBundleEncoder(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateRenderPipeline(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateSampler(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateShaderModule(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceCreateTexture(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuDeviceDestroy(handler: NativeAddress): Unit
	external fun wgpuDeviceGetLimits(handler: NativeAddress, limits: NativeAddress): Boolean
	external fun wgpuDeviceHasFeature(handler: NativeAddress, feature: UInt): Boolean
	external fun wgpuDeviceEnumerateFeatures(handler: NativeAddress, features: UInt): ULong
	external fun wgpuDeviceGetQueue(handler: NativeAddress): NativeAddress
	external fun wgpuDevicePushErrorScope(handler: NativeAddress, filter: UInt): Unit
	external fun wgpuDevicePopErrorScope(handler: NativeAddress): Unit
	external fun wgpuDeviceSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuInstanceCreateSurface(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuInstanceHasWGSLLanguageFeature(handler: NativeAddress, feature: UInt): Boolean
	external fun wgpuInstanceProcessEvents(handler: NativeAddress): Unit
	external fun wgpuInstanceRequestAdapter(handler: NativeAddress, options: NativeAddress): Unit
	external fun wgpuInstanceWaitAny(handler: NativeAddress, futureCount: ULong, futures: NativeAddress, timeoutNS: ULong): UInt
	external fun wgpuPipelineLayoutSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuQuerySetSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuQuerySetGetType(handler: NativeAddress): UInt
	external fun wgpuQuerySetGetCount(handler: NativeAddress): UInt
	external fun wgpuQuerySetDestroy(handler: NativeAddress): Unit
	external fun wgpuQueueSubmit(handler: NativeAddress, commands: NativeAddress): Unit
	external fun wgpuQueueOnSubmittedWorkDone(handler: NativeAddress): Unit
	external fun wgpuQueueWriteBuffer(handler: NativeAddress, buffer: NativeAddress, bufferOffset: ULong, data: NativeAddress, size: ULong): Unit
	external fun wgpuQueueWriteTexture(handler: NativeAddress, destination: NativeAddress, data: NativeAddress, dataSize: ULong, dataLayout: NativeAddress, writeSize: NativeAddress): Unit
	external fun wgpuQueueSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuRenderBundleSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderDraw(handler: NativeAddress, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	external fun wgpuRenderBundleEncoderDrawIndexed(handler: NativeAddress, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	external fun wgpuRenderBundleEncoderDrawIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit
	external fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit
	external fun wgpuRenderBundleEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderPopDebugGroup(handler: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit
	external fun wgpuRenderBundleEncoderSetVertexBuffer(handler: NativeAddress, slot: UInt, buffer: NativeAddress, offset: ULong, size: ULong): Unit
	external fun wgpuRenderBundleEncoderSetIndexBuffer(handler: NativeAddress, buffer: NativeAddress, format: UInt, offset: ULong, size: ULong): Unit
	external fun wgpuRenderBundleEncoderFinish(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuRenderBundleEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuRenderPassEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit
	external fun wgpuRenderPassEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit
	external fun wgpuRenderPassEncoderDraw(handler: NativeAddress, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit
	external fun wgpuRenderPassEncoderDrawIndexed(handler: NativeAddress, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit
	external fun wgpuRenderPassEncoderDrawIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit
	external fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit
	external fun wgpuRenderPassEncoderExecuteBundles(handler: NativeAddress, bundles: NativeAddress): Unit
	external fun wgpuRenderPassEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit
	external fun wgpuRenderPassEncoderPopDebugGroup(handler: NativeAddress): Unit
	external fun wgpuRenderPassEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit
	external fun wgpuRenderPassEncoderSetStencilReference(handler: NativeAddress, reference: UInt): Unit
	external fun wgpuRenderPassEncoderSetBlendConstant(handler: NativeAddress, color: NativeAddress): Unit
	external fun wgpuRenderPassEncoderSetViewport(handler: NativeAddress, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit
	external fun wgpuRenderPassEncoderSetScissorRect(handler: NativeAddress, x: UInt, y: UInt, width: UInt, height: UInt): Unit
	external fun wgpuRenderPassEncoderSetVertexBuffer(handler: NativeAddress, slot: UInt, buffer: NativeAddress, offset: ULong, size: ULong): Unit
	external fun wgpuRenderPassEncoderSetIndexBuffer(handler: NativeAddress, buffer: NativeAddress, format: UInt, offset: ULong, size: ULong): Unit
	external fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: NativeAddress, queryIndex: UInt): Unit
	external fun wgpuRenderPassEncoderEndOcclusionQuery(handler: NativeAddress): Unit
	external fun wgpuRenderPassEncoderEnd(handler: NativeAddress): Unit
	external fun wgpuRenderPassEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuRenderPipelineGetBindGroupLayout(handler: NativeAddress, groupIndex: UInt): NativeAddress
	external fun wgpuRenderPipelineSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuSamplerSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuShaderModuleGetCompilationInfo(handler: NativeAddress): Unit
	external fun wgpuShaderModuleSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuSurfaceConfigure(handler: NativeAddress, config: NativeAddress): Unit
	external fun wgpuSurfaceGetCapabilities(handler: NativeAddress, adapter: NativeAddress, capabilities: NativeAddress): Boolean
	external fun wgpuSurfaceGetCurrentTexture(handler: NativeAddress, surfaceTexture: NativeAddress): Unit
	external fun wgpuSurfacePresent(handler: NativeAddress): Unit
	external fun wgpuSurfaceUnconfigure(handler: NativeAddress): Unit
	external fun wgpuSurfaceSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuTextureCreateView(handler: NativeAddress, descriptor: NativeAddress): NativeAddress
	external fun wgpuTextureSetLabel(handler: NativeAddress, label: NativeAddress): Unit
	external fun wgpuTextureGetWidth(handler: NativeAddress): UInt
	external fun wgpuTextureGetHeight(handler: NativeAddress): UInt
	external fun wgpuTextureGetDepthOrArrayLayers(handler: NativeAddress): UInt
	external fun wgpuTextureGetMipLevelCount(handler: NativeAddress): UInt
	external fun wgpuTextureGetSampleCount(handler: NativeAddress): UInt
	external fun wgpuTextureGetDimension(handler: NativeAddress): UInt
	external fun wgpuTextureGetFormat(handler: NativeAddress): UInt
	external fun wgpuTextureGetUsage(handler: NativeAddress): ULong
	external fun wgpuTextureDestroy(handler: NativeAddress): Unit
	external fun wgpuTextureViewSetLabel(handler: NativeAddress, label: NativeAddress): Unit
}
