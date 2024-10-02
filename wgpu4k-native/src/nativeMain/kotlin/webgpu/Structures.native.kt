// This file has been generated DO NOT EDIT !!!
@file:OptIn(ExperimentalForeignApi::class)
package webgpu
    
import ffi.NativeAddress
import ffi.CallbackHolder
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong

actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	actual val vendor: String
		get() = TODO()

	actual val architecture: String
		get() = TODO()

	actual val device: String
		get() = TODO()

	actual val description: String
		get() = TODO()

	actual val backendType: WGPUBackendType?
		get() = TODO()

	actual val adapterType: WGPUAdapterType?
		get() = TODO()

	actual val vendorID: UInt
		get() = TODO()

	actual val deviceID: UInt
		get() = TODO()

}

actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val layout: WGPUBindGroupLayout?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUBindGroupLayout(it) }

	actual val entries: Long
		get() = TODO()

}

actual value class WGPUBindGroupEntry(actual val handler: NativeAddress) {
	actual val binding: UInt
		get() = TODO()

	actual val buffer: WGPUBuffer?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }

	actual val offset: ULong
		get() = TODO()

	actual val size: ULong
		get() = TODO()

	actual val sampler: WGPUSampler?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.sampler?.toLong()?.takeIf {it != 0L}?.let { WGPUSampler(it) }

	actual val textureView: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.textureView?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }

}

actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val entries: Long
		get() = TODO()

}

actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	actual val binding: UInt
		get() = TODO()

	actual val visibility: ULong
		get() = TODO()

	actual val buffer: WGPUBufferBindingLayout?
		get() = TODO()

	actual val sampler: WGPUSamplerBindingLayout?
		get() = TODO()

	actual val texture: WGPUTextureBindingLayout?
		get() = TODO()

	actual val storageTexture: WGPUStorageTextureBindingLayout?
		get() = TODO()

}

actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	actual val operation: WGPUBlendOperation?
		get() = TODO()

	actual val srcFactor: WGPUBlendFactor?
		get() = TODO()

	actual val dstFactor: WGPUBlendFactor?
		get() = TODO()

}

actual value class WGPUBlendState(actual val handler: NativeAddress) {
	actual val color: WGPUBlendComponent?
		get() = TODO()

	actual val alpha: WGPUBlendComponent?
		get() = TODO()

}

actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	actual val type: WGPUBufferBindingType?
		get() = TODO()

	actual val hasDynamicOffset: Boolean
		get() = TODO()

	actual val minBindingSize: ULong
		get() = TODO()

}

actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val usage: ULong
		get() = TODO()

	actual val size: ULong
		get() = TODO()

	actual val mappedAtCreation: Boolean
		get() = TODO()

}

actual value class WGPUColor(actual val handler: NativeAddress) {
	actual val r: Double
		get() = TODO()

	actual val g: Double
		get() = TODO()

	actual val b: Double
		get() = TODO()

	actual val a: Double
		get() = TODO()

}

actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val blend: WGPUBlendState?
		get() = TODO()

	actual val writeMask: ULong
		get() = TODO()

}

actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	actual val messages: Long
		get() = TODO()

}

actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual val message: String
		get() = TODO()

	actual val type: WGPUCompilationMessageType?
		get() = TODO()

	actual val lineNum: ULong
		get() = TODO()

	actual val linePos: ULong
		get() = TODO()

	actual val offset: ULong
		get() = TODO()

	actual val length: ULong
		get() = TODO()

	actual val utf16LinePos: ULong
		get() = TODO()

	actual val utf16Offset: ULong
		get() = TODO()

	actual val utf16Length: ULong
		get() = TODO()

}

actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val timestampWrites: WGPUComputePassTimestampWrites?
		get() = TODO()

}

actual value class WGPUComputePassTimestampWrites(actual val handler: NativeAddress) {
	actual val querySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }

	actual val beginningOfPassWriteIndex: UInt
		get() = TODO()

	actual val endOfPassWriteIndex: UInt
		get() = TODO()

}

actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }

	actual val compute: WGPUProgrammableStageDescriptor?
		get() = TODO()

}

actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual val key: String
		get() = TODO()

	actual val value: Double
		get() = TODO()

}

actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val depthWriteEnabled: WGPUOptionalBool?
		get() = TODO()

	actual val depthCompare: WGPUCompareFunction?
		get() = TODO()

	actual val stencilFront: WGPUStencilFaceState?
		get() = TODO()

	actual val stencilBack: WGPUStencilFaceState?
		get() = TODO()

	actual val stencilReadMask: UInt
		get() = TODO()

	actual val stencilWriteMask: UInt
		get() = TODO()

	actual val depthBias: Int
		get() = TODO()

	actual val depthBiasSlopeScale: Float
		get() = TODO()

	actual val depthBiasClamp: Float
		get() = TODO()

}

actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val requiredFeatures: Long
		get() = TODO()

	actual val requiredLimits: WGPURequiredLimits?
		get() = TODO()

	actual val defaultQueue: WGPUQueueDescriptor?
		get() = TODO()

	actual val deviceLostCallbackInfo: CallbackHolder<WGPUDeviceLostCallbackInfo>?
		get() = TODO()

	actual val uncapturedErrorCallbackInfo: CallbackHolder<WGPUUncapturedErrorCallbackInfo>?
		get() = TODO()

}

actual value class WGPUExtent3D(actual val handler: NativeAddress) {
	actual val width: UInt
		get() = TODO()

	actual val height: UInt
		get() = TODO()

	actual val depthOrArrayLayers: UInt
		get() = TODO()

}

actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual val module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }

	actual val entryPoint: String
		get() = TODO()

	actual val constants: Long
		get() = TODO()

	actual val targets: Long
		get() = TODO()

}

actual value class WGPUFuture(actual val handler: NativeAddress) {
	actual val id: ULong
		get() = TODO()

}

actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	actual val future: WGPUFuture?
		get() = TODO()

	actual val completed: Boolean
		get() = TODO()

}

actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	actual val layout: WGPUTextureDataLayout?
		get() = TODO()

	actual val buffer: WGPUBuffer?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }

}

actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	actual val texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }

	actual val mipLevel: UInt
		get() = TODO()

	actual val origin: WGPUOrigin3D?
		get() = TODO()

	actual val aspect: WGPUTextureAspect?
		get() = TODO()

}

actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	actual val features: WGPUInstanceFeatures?
		get() = TODO()

}

actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	actual val timedWaitAnyEnable: Boolean
		get() = TODO()

	actual val timedWaitAnyMaxCount: ULong
		get() = TODO()

}

actual value class WGPULimits(actual val handler: NativeAddress) {
	actual val maxTextureDimension1D: UInt
		get() = TODO()

	actual val maxTextureDimension2D: UInt
		get() = TODO()

	actual val maxTextureDimension3D: UInt
		get() = TODO()

	actual val maxTextureArrayLayers: UInt
		get() = TODO()

	actual val maxBindGroups: UInt
		get() = TODO()

	actual val maxBindGroupsPlusVertexBuffers: UInt
		get() = TODO()

	actual val maxBindingsPerBindGroup: UInt
		get() = TODO()

	actual val maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = TODO()

	actual val maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = TODO()

	actual val maxSampledTexturesPerShaderStage: UInt
		get() = TODO()

	actual val maxSamplersPerShaderStage: UInt
		get() = TODO()

	actual val maxStorageBuffersPerShaderStage: UInt
		get() = TODO()

	actual val maxStorageTexturesPerShaderStage: UInt
		get() = TODO()

	actual val maxUniformBuffersPerShaderStage: UInt
		get() = TODO()

	actual val maxUniformBufferBindingSize: ULong
		get() = TODO()

	actual val maxStorageBufferBindingSize: ULong
		get() = TODO()

	actual val minUniformBufferOffsetAlignment: UInt
		get() = TODO()

	actual val minStorageBufferOffsetAlignment: UInt
		get() = TODO()

	actual val maxVertexBuffers: UInt
		get() = TODO()

	actual val maxBufferSize: ULong
		get() = TODO()

	actual val maxVertexAttributes: UInt
		get() = TODO()

	actual val maxVertexBufferArrayStride: UInt
		get() = TODO()

	actual val maxInterStageShaderVariables: UInt
		get() = TODO()

	actual val maxColorAttachments: UInt
		get() = TODO()

	actual val maxColorAttachmentBytesPerSample: UInt
		get() = TODO()

	actual val maxComputeWorkgroupStorageSize: UInt
		get() = TODO()

	actual val maxComputeInvocationsPerWorkgroup: UInt
		get() = TODO()

	actual val maxComputeWorkgroupSizeX: UInt
		get() = TODO()

	actual val maxComputeWorkgroupSizeY: UInt
		get() = TODO()

	actual val maxComputeWorkgroupSizeZ: UInt
		get() = TODO()

	actual val maxComputeWorkgroupsPerDimension: UInt
		get() = TODO()

}

actual value class WGPUMultisampleState(actual val handler: NativeAddress) {
	actual val count: UInt
		get() = TODO()

	actual val mask: UInt
		get() = TODO()

	actual val alphaToCoverageEnabled: Boolean
		get() = TODO()

}

actual value class WGPUOrigin3D(actual val handler: NativeAddress) {
	actual val x: UInt
		get() = TODO()

	actual val y: UInt
		get() = TODO()

	actual val z: UInt
		get() = TODO()

}

actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val bindGroupLayouts: Long
		get() = TODO()

}

actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	actual val topology: WGPUPrimitiveTopology?
		get() = TODO()

	actual val stripIndexFormat: WGPUIndexFormat?
		get() = TODO()

	actual val frontFace: WGPUFrontFace?
		get() = TODO()

	actual val cullMode: WGPUCullMode?
		get() = TODO()

	actual val unclippedDepth: Boolean
		get() = TODO()

}

actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual val module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }

	actual val entryPoint: String
		get() = TODO()

	actual val constants: Long
		get() = TODO()

}

actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val type: WGPUQueryType?
		get() = TODO()

	actual val count: UInt
		get() = TODO()

}

actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val colorFormats: Long
		get() = TODO()

	actual val depthStencilFormat: WGPUTextureFormat?
		get() = TODO()

	actual val sampleCount: UInt
		get() = TODO()

	actual val depthReadOnly: Boolean
		get() = TODO()

	actual val stencilReadOnly: Boolean
		get() = TODO()

}

actual value class WGPURenderPassColorAttachment(actual val handler: NativeAddress) {
	actual val view: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }

	actual val depthSlice: UInt
		get() = TODO()

	actual val resolveTarget: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.resolveTarget?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }

	actual val loadOp: WGPULoadOp?
		get() = TODO()

	actual val storeOp: WGPUStoreOp?
		get() = TODO()

	actual val clearValue: WGPUColor?
		get() = TODO()

}

actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	actual val view: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }

	actual val depthLoadOp: WGPULoadOp?
		get() = TODO()

	actual val depthStoreOp: WGPUStoreOp?
		get() = TODO()

	actual val depthClearValue: Float
		get() = TODO()

	actual val depthReadOnly: Boolean
		get() = TODO()

	actual val stencilLoadOp: WGPULoadOp?
		get() = TODO()

	actual val stencilStoreOp: WGPUStoreOp?
		get() = TODO()

	actual val stencilClearValue: UInt
		get() = TODO()

	actual val stencilReadOnly: Boolean
		get() = TODO()

}

actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val colorAttachments: Long
		get() = TODO()

	actual val depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = TODO()

	actual val occlusionQuerySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.occlusionQuerySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }

	actual val timestampWrites: WGPURenderPassTimestampWrites?
		get() = TODO()

}

actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	actual val maxDrawCount: ULong
		get() = TODO()

}

actual value class WGPURenderPassTimestampWrites(actual val handler: NativeAddress) {
	actual val querySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }

	actual val beginningOfPassWriteIndex: UInt
		get() = TODO()

	actual val endOfPassWriteIndex: UInt
		get() = TODO()

}

actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }

	actual val vertex: WGPUVertexState?
		get() = TODO()

	actual val primitive: WGPUPrimitiveState?
		get() = TODO()

	actual val depthStencil: WGPUDepthStencilState?
		get() = TODO()

	actual val multisample: WGPUMultisampleState?
		get() = TODO()

	actual val fragment: WGPUFragmentState?
		get() = TODO()

}

actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	actual val compatibleSurface: WGPUSurface?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.compatibleSurface?.toLong()?.takeIf {it != 0L}?.let { WGPUSurface(it) }

	actual val powerPreference: WGPUPowerPreference?
		get() = TODO()

	actual val backendType: WGPUBackendType?
		get() = TODO()

	actual val forceFallbackAdapter: Boolean
		get() = TODO()

}

actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits?
		get() = TODO()

}

actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	actual val type: WGPUSamplerBindingType?
		get() = TODO()

}

actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val addressModeU: WGPUAddressMode?
		get() = TODO()

	actual val addressModeV: WGPUAddressMode?
		get() = TODO()

	actual val addressModeW: WGPUAddressMode?
		get() = TODO()

	actual val magFilter: WGPUFilterMode?
		get() = TODO()

	actual val minFilter: WGPUFilterMode?
		get() = TODO()

	actual val mipmapFilter: WGPUMipmapFilterMode?
		get() = TODO()

	actual val lodMinClamp: Float
		get() = TODO()

	actual val lodMaxClamp: Float
		get() = TODO()

	actual val compare: WGPUCompareFunction?
		get() = TODO()

	actual val maxAnisotropy: UShort
		get() = TODO()

}

actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	actual val codeSize: UInt
		get() = TODO()

	actual val code: UInt
		get() = TODO()

}

actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual val code: String
		get() = TODO()

}

actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	actual val compare: WGPUCompareFunction?
		get() = TODO()

	actual val failOp: WGPUStencilOperation?
		get() = TODO()

	actual val depthFailOp: WGPUStencilOperation?
		get() = TODO()

	actual val passOp: WGPUStencilOperation?
		get() = TODO()

}

actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	actual val access: WGPUStorageTextureAccess?
		get() = TODO()

	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val viewDimension: WGPUTextureViewDimension?
		get() = TODO()

}

actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits?
		get() = TODO()

}

actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	actual val usages: ULong
		get() = TODO()

	actual val formats: Long
		get() = TODO()

	actual val presentModes: Long
		get() = TODO()

	actual val alphaModes: Long
		get() = TODO()

}

actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	actual val device: WGPUDevice?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.device?.toLong()?.takeIf {it != 0L}?.let { WGPUDevice(it) }

	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val usage: ULong
		get() = TODO()

	actual val width: UInt
		get() = TODO()

	actual val height: UInt
		get() = TODO()

	actual val viewFormats: Long
		get() = TODO()

	actual val alphaMode: WGPUCompositeAlphaMode?
		get() = TODO()

	actual val presentMode: WGPUPresentMode?
		get() = TODO()

}

actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

}

actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	actual val window: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	actual val layer: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	actual val display: Unit
		get() = TODO()

	actual val surface: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	actual val hinstance: Unit
		get() = TODO()

	actual val hwnd: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	actual val connection: Unit
		get() = TODO()

	actual val window: UInt
		get() = TODO()

}

actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	actual val display: Unit
		get() = TODO()

	actual val window: ULong
		get() = TODO()

}

actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	actual val texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }

	actual val status: WGPUSurfaceGetCurrentTextureStatus?
		get() = TODO()

}

actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	actual val sampleType: WGPUTextureSampleType?
		get() = TODO()

	actual val viewDimension: WGPUTextureViewDimension?
		get() = TODO()

	actual val multisampled: Boolean
		get() = TODO()

}

actual value class WGPUTextureDataLayout(actual val handler: NativeAddress) {
	actual val offset: ULong
		get() = TODO()

	actual val bytesPerRow: UInt
		get() = TODO()

	actual val rowsPerImage: UInt
		get() = TODO()

}

actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val usage: ULong
		get() = TODO()

	actual val dimension: WGPUTextureDimension?
		get() = TODO()

	actual val size: WGPUExtent3D?
		get() = TODO()

	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val mipLevelCount: UInt
		get() = TODO()

	actual val sampleCount: UInt
		get() = TODO()

	actual val viewFormats: Long
		get() = TODO()

}

actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual val label: String
		get() = TODO()

	actual val format: WGPUTextureFormat?
		get() = TODO()

	actual val dimension: WGPUTextureViewDimension?
		get() = TODO()

	actual val baseMipLevel: UInt
		get() = TODO()

	actual val mipLevelCount: UInt
		get() = TODO()

	actual val baseArrayLayer: UInt
		get() = TODO()

	actual val arrayLayerCount: UInt
		get() = TODO()

	actual val aspect: WGPUTextureAspect?
		get() = TODO()

	actual val usage: ULong
		get() = TODO()

}

actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	actual val format: WGPUVertexFormat?
		get() = TODO()

	actual val offset: ULong
		get() = TODO()

	actual val shaderLocation: UInt
		get() = TODO()

}

actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	actual val arrayStride: ULong
		get() = TODO()

	actual val stepMode: WGPUVertexStepMode?
		get() = TODO()

	actual val attributes: Long
		get() = TODO()

}

actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual val module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }

	actual val entryPoint: String
		get() = TODO()

	actual val constants: Long
		get() = TODO()

	actual val buffers: Long
		get() = TODO()

}

actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	actual val next: WGPUChainedStruct?
		get() = TODO()

	actual val sType: WGPUSType
		get() = TODO()

}

actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	actual val next: WGPUChainedStructOut?
		get() = TODO()

	actual val sType: WGPUSType
		get() = TODO()

}

