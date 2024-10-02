// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder

expect value class WGPUAdapterInfo(val handler: NativeAddress) {
	val vendor: String
	val architecture: String
	val device: String
	val description: String
	val backendType: WGPUBackendType?
	val adapterType: WGPUAdapterType?
	val vendorID: UInt
	val deviceID: UInt
}

expect value class WGPUBindGroupDescriptor(val handler: NativeAddress) {
	val label: String
	val layout: WGPUBindGroupLayout?
	val entries: Long
}

expect value class WGPUBindGroupEntry(val handler: NativeAddress) {
	val binding: UInt
	val buffer: WGPUBuffer?
	val offset: ULong
	val size: ULong
	val sampler: WGPUSampler?
	val textureView: WGPUTextureView?
}

expect value class WGPUBindGroupLayoutDescriptor(val handler: NativeAddress) {
	val label: String
	val entries: Long
}

expect value class WGPUBindGroupLayoutEntry(val handler: NativeAddress) {
	val binding: UInt
	val visibility: ULong
	val buffer: WGPUBufferBindingLayout?
	val sampler: WGPUSamplerBindingLayout?
	val texture: WGPUTextureBindingLayout?
	val storageTexture: WGPUStorageTextureBindingLayout?
}

expect value class WGPUBlendComponent(val handler: NativeAddress) {
	val operation: WGPUBlendOperation?
	val srcFactor: WGPUBlendFactor?
	val dstFactor: WGPUBlendFactor?
}

expect value class WGPUBlendState(val handler: NativeAddress) {
	val color: WGPUBlendComponent?
	val alpha: WGPUBlendComponent?
}

expect value class WGPUBufferBindingLayout(val handler: NativeAddress) {
	val type: WGPUBufferBindingType?
	val hasDynamicOffset: Boolean
	val minBindingSize: ULong
}

expect value class WGPUBufferDescriptor(val handler: NativeAddress) {
	val label: String
	val usage: ULong
	val size: ULong
	val mappedAtCreation: Boolean
}

expect value class WGPUColor(val handler: NativeAddress) {
	val r: Double
	val g: Double
	val b: Double
	val a: Double
}

expect value class WGPUColorTargetState(val handler: NativeAddress) {
	val format: WGPUTextureFormat?
	val blend: WGPUBlendState?
	val writeMask: ULong
}

expect value class WGPUCommandBufferDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPUCommandEncoderDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPUCompilationInfo(val handler: NativeAddress) {
	val messages: Long
}

expect value class WGPUCompilationMessage(val handler: NativeAddress) {
	val message: String
	val type: WGPUCompilationMessageType?
	val lineNum: ULong
	val linePos: ULong
	val offset: ULong
	val length: ULong
	val utf16LinePos: ULong
	val utf16Offset: ULong
	val utf16Length: ULong
}

expect value class WGPUComputePassDescriptor(val handler: NativeAddress) {
	val label: String
	val timestampWrites: WGPUComputePassTimestampWrites?
}

expect value class WGPUComputePassTimestampWrites(val handler: NativeAddress) {
	val querySet: WGPUQuerySet?
	val beginningOfPassWriteIndex: UInt
	val endOfPassWriteIndex: UInt
}

expect value class WGPUComputePipelineDescriptor(val handler: NativeAddress) {
	val label: String
	val layout: WGPUPipelineLayout?
	val compute: WGPUProgrammableStageDescriptor?
}

expect value class WGPUConstantEntry(val handler: NativeAddress) {
	val key: String
	val value: Double
}

expect value class WGPUDepthStencilState(val handler: NativeAddress) {
	val format: WGPUTextureFormat?
	val depthWriteEnabled: WGPUOptionalBool?
	val depthCompare: WGPUCompareFunction?
	val stencilFront: WGPUStencilFaceState?
	val stencilBack: WGPUStencilFaceState?
	val stencilReadMask: UInt
	val stencilWriteMask: UInt
	val depthBias: Int
	val depthBiasSlopeScale: Float
	val depthBiasClamp: Float
}

expect value class WGPUDeviceDescriptor(val handler: NativeAddress) {
	val label: String
	val requiredFeatures: Long
	val requiredLimits: WGPURequiredLimits?
	val defaultQueue: WGPUQueueDescriptor?
	val deviceLostCallbackInfo: CallbackHolder<WGPUDeviceLostCallbackInfo>?
	val uncapturedErrorCallbackInfo: CallbackHolder<WGPUUncapturedErrorCallbackInfo>?
}

expect value class WGPUExtent3D(val handler: NativeAddress) {
	val width: UInt
	val height: UInt
	val depthOrArrayLayers: UInt
}

expect value class WGPUFragmentState(val handler: NativeAddress) {
	val module: WGPUShaderModule?
	val entryPoint: String
	val constants: Long
	val targets: Long
}

expect value class WGPUFuture(val handler: NativeAddress) {
	val id: ULong
}

expect value class WGPUFutureWaitInfo(val handler: NativeAddress) {
	val future: WGPUFuture?
	val completed: Boolean
}

expect value class WGPUImageCopyBuffer(val handler: NativeAddress) {
	val layout: WGPUTextureDataLayout?
	val buffer: WGPUBuffer?
}

expect value class WGPUImageCopyTexture(val handler: NativeAddress) {
	val texture: WGPUTexture?
	val mipLevel: UInt
	val origin: WGPUOrigin3D?
	val aspect: WGPUTextureAspect?
}

expect value class WGPUInstanceDescriptor(val handler: NativeAddress) {
	val features: WGPUInstanceFeatures?
}

expect value class WGPUInstanceFeatures(val handler: NativeAddress) {
	val timedWaitAnyEnable: Boolean
	val timedWaitAnyMaxCount: ULong
}

expect value class WGPULimits(val handler: NativeAddress) {
	val maxTextureDimension1D: UInt
	val maxTextureDimension2D: UInt
	val maxTextureDimension3D: UInt
	val maxTextureArrayLayers: UInt
	val maxBindGroups: UInt
	val maxBindGroupsPlusVertexBuffers: UInt
	val maxBindingsPerBindGroup: UInt
	val maxDynamicUniformBuffersPerPipelineLayout: UInt
	val maxDynamicStorageBuffersPerPipelineLayout: UInt
	val maxSampledTexturesPerShaderStage: UInt
	val maxSamplersPerShaderStage: UInt
	val maxStorageBuffersPerShaderStage: UInt
	val maxStorageTexturesPerShaderStage: UInt
	val maxUniformBuffersPerShaderStage: UInt
	val maxUniformBufferBindingSize: ULong
	val maxStorageBufferBindingSize: ULong
	val minUniformBufferOffsetAlignment: UInt
	val minStorageBufferOffsetAlignment: UInt
	val maxVertexBuffers: UInt
	val maxBufferSize: ULong
	val maxVertexAttributes: UInt
	val maxVertexBufferArrayStride: UInt
	val maxInterStageShaderVariables: UInt
	val maxColorAttachments: UInt
	val maxColorAttachmentBytesPerSample: UInt
	val maxComputeWorkgroupStorageSize: UInt
	val maxComputeInvocationsPerWorkgroup: UInt
	val maxComputeWorkgroupSizeX: UInt
	val maxComputeWorkgroupSizeY: UInt
	val maxComputeWorkgroupSizeZ: UInt
	val maxComputeWorkgroupsPerDimension: UInt
}

expect value class WGPUMultisampleState(val handler: NativeAddress) {
	val count: UInt
	val mask: UInt
	val alphaToCoverageEnabled: Boolean
}

expect value class WGPUOrigin3D(val handler: NativeAddress) {
	val x: UInt
	val y: UInt
	val z: UInt
}

expect value class WGPUPipelineLayoutDescriptor(val handler: NativeAddress) {
	val label: String
	val bindGroupLayouts: Long
}

expect value class WGPUPrimitiveState(val handler: NativeAddress) {
	val topology: WGPUPrimitiveTopology?
	val stripIndexFormat: WGPUIndexFormat?
	val frontFace: WGPUFrontFace?
	val cullMode: WGPUCullMode?
	val unclippedDepth: Boolean
}

expect value class WGPUProgrammableStageDescriptor(val handler: NativeAddress) {
	val module: WGPUShaderModule?
	val entryPoint: String
	val constants: Long
}

expect value class WGPUQuerySetDescriptor(val handler: NativeAddress) {
	val label: String
	val type: WGPUQueryType?
	val count: UInt
}

expect value class WGPUQueueDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPURenderBundleDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPURenderBundleEncoderDescriptor(val handler: NativeAddress) {
	val label: String
	val colorFormats: Long
	val depthStencilFormat: WGPUTextureFormat?
	val sampleCount: UInt
	val depthReadOnly: Boolean
	val stencilReadOnly: Boolean
}

expect value class WGPURenderPassColorAttachment(val handler: NativeAddress) {
	val view: WGPUTextureView?
	val depthSlice: UInt
	val resolveTarget: WGPUTextureView?
	val loadOp: WGPULoadOp?
	val storeOp: WGPUStoreOp?
	val clearValue: WGPUColor?
}

expect value class WGPURenderPassDepthStencilAttachment(val handler: NativeAddress) {
	val view: WGPUTextureView?
	val depthLoadOp: WGPULoadOp?
	val depthStoreOp: WGPUStoreOp?
	val depthClearValue: Float
	val depthReadOnly: Boolean
	val stencilLoadOp: WGPULoadOp?
	val stencilStoreOp: WGPUStoreOp?
	val stencilClearValue: UInt
	val stencilReadOnly: Boolean
}

expect value class WGPURenderPassDescriptor(val handler: NativeAddress) {
	val label: String
	val colorAttachments: Long
	val depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
	val occlusionQuerySet: WGPUQuerySet?
	val timestampWrites: WGPURenderPassTimestampWrites?
}

expect value class WGPURenderPassMaxDrawCount(val handler: NativeAddress) {
	val maxDrawCount: ULong
}

expect value class WGPURenderPassTimestampWrites(val handler: NativeAddress) {
	val querySet: WGPUQuerySet?
	val beginningOfPassWriteIndex: UInt
	val endOfPassWriteIndex: UInt
}

expect value class WGPURenderPipelineDescriptor(val handler: NativeAddress) {
	val label: String
	val layout: WGPUPipelineLayout?
	val vertex: WGPUVertexState?
	val primitive: WGPUPrimitiveState?
	val depthStencil: WGPUDepthStencilState?
	val multisample: WGPUMultisampleState?
	val fragment: WGPUFragmentState?
}

expect value class WGPURequestAdapterOptions(val handler: NativeAddress) {
	val compatibleSurface: WGPUSurface?
	val powerPreference: WGPUPowerPreference?
	val backendType: WGPUBackendType?
	val forceFallbackAdapter: Boolean
}

expect value class WGPURequiredLimits(val handler: NativeAddress) {
	val limits: WGPULimits?
}

expect value class WGPUSamplerBindingLayout(val handler: NativeAddress) {
	val type: WGPUSamplerBindingType?
}

expect value class WGPUSamplerDescriptor(val handler: NativeAddress) {
	val label: String
	val addressModeU: WGPUAddressMode?
	val addressModeV: WGPUAddressMode?
	val addressModeW: WGPUAddressMode?
	val magFilter: WGPUFilterMode?
	val minFilter: WGPUFilterMode?
	val mipmapFilter: WGPUMipmapFilterMode?
	val lodMinClamp: Float
	val lodMaxClamp: Float
	val compare: WGPUCompareFunction?
	val maxAnisotropy: UShort
}

expect value class WGPUShaderModuleDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPUShaderSourceSPIRV(val handler: NativeAddress) {
	val codeSize: UInt
	val code: UInt
}

expect value class WGPUShaderSourceWGSL(val handler: NativeAddress) {
	val code: String
}

expect value class WGPUStencilFaceState(val handler: NativeAddress) {
	val compare: WGPUCompareFunction?
	val failOp: WGPUStencilOperation?
	val depthFailOp: WGPUStencilOperation?
	val passOp: WGPUStencilOperation?
}

expect value class WGPUStorageTextureBindingLayout(val handler: NativeAddress) {
	val access: WGPUStorageTextureAccess?
	val format: WGPUTextureFormat?
	val viewDimension: WGPUTextureViewDimension?
}

expect value class WGPUSupportedLimits(val handler: NativeAddress) {
	val limits: WGPULimits?
}

expect value class WGPUSurfaceCapabilities(val handler: NativeAddress) {
	val usages: ULong
	val formats: Long
	val presentModes: Long
	val alphaModes: Long
}

expect value class WGPUSurfaceConfiguration(val handler: NativeAddress) {
	val device: WGPUDevice?
	val format: WGPUTextureFormat?
	val usage: ULong
	val width: UInt
	val height: UInt
	val viewFormats: Long
	val alphaMode: WGPUCompositeAlphaMode?
	val presentMode: WGPUPresentMode?
}

expect value class WGPUSurfaceDescriptor(val handler: NativeAddress) {
	val label: String
}

expect value class WGPUSurfaceSourceAndroidNativeWindow(val handler: NativeAddress) {
	val window: Unit
}

expect value class WGPUSurfaceSourceMetalLayer(val handler: NativeAddress) {
	val layer: Unit
}

expect value class WGPUSurfaceSourceWaylandSurface(val handler: NativeAddress) {
	val display: Unit
	val surface: Unit
}

expect value class WGPUSurfaceSourceWindowsHWND(val handler: NativeAddress) {
	val hinstance: Unit
	val hwnd: Unit
}

expect value class WGPUSurfaceSourceXCBWindow(val handler: NativeAddress) {
	val connection: Unit
	val window: UInt
}

expect value class WGPUSurfaceSourceXlibWindow(val handler: NativeAddress) {
	val display: Unit
	val window: ULong
}

expect value class WGPUSurfaceTexture(val handler: NativeAddress) {
	val texture: WGPUTexture?
	val status: WGPUSurfaceGetCurrentTextureStatus?
}

expect value class WGPUTextureBindingLayout(val handler: NativeAddress) {
	val sampleType: WGPUTextureSampleType?
	val viewDimension: WGPUTextureViewDimension?
	val multisampled: Boolean
}

expect value class WGPUTextureDataLayout(val handler: NativeAddress) {
	val offset: ULong
	val bytesPerRow: UInt
	val rowsPerImage: UInt
}

expect value class WGPUTextureDescriptor(val handler: NativeAddress) {
	val label: String
	val usage: ULong
	val dimension: WGPUTextureDimension?
	val size: WGPUExtent3D?
	val format: WGPUTextureFormat?
	val mipLevelCount: UInt
	val sampleCount: UInt
	val viewFormats: Long
}

expect value class WGPUTextureViewDescriptor(val handler: NativeAddress) {
	val label: String
	val format: WGPUTextureFormat?
	val dimension: WGPUTextureViewDimension?
	val baseMipLevel: UInt
	val mipLevelCount: UInt
	val baseArrayLayer: UInt
	val arrayLayerCount: UInt
	val aspect: WGPUTextureAspect?
	val usage: ULong
}

expect value class WGPUVertexAttribute(val handler: NativeAddress) {
	val format: WGPUVertexFormat?
	val offset: ULong
	val shaderLocation: UInt
}

expect value class WGPUVertexBufferLayout(val handler: NativeAddress) {
	val arrayStride: ULong
	val stepMode: WGPUVertexStepMode?
	val attributes: Long
}

expect value class WGPUVertexState(val handler: NativeAddress) {
	val module: WGPUShaderModule?
	val entryPoint: String
	val constants: Long
	val buffers: Long
}

expect value class WGPUChainedStruct(val handler: NativeAddress) {
	val next: WGPUChainedStruct?
	val sType: WGPUSType
}

expect value class WGPUChainedStructOut(val handler: NativeAddress) {
	val next: WGPUChainedStructOut?
	val sType: WGPUSType
}

