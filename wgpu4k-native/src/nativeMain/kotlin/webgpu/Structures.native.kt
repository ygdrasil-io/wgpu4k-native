// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress

actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	val vendor: String
		get() = TODO()

	val architecture: String
		get() = TODO()

	val device: String
		get() = TODO()

	val description: String
		get() = TODO()

	val backendType: WGPUBackendType
		get() = TODO()

	val adapterType: WGPUAdapterType
		get() = TODO()

	val vendorID: UInt
		get() = TODO()

	val deviceID: UInt
		get() = TODO()

}

actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val layout: WGPUBindGroupLayout
		get() = TODO()

	val entries: Long
		get() = TODO()

}

actual value class WGPUBindGroupEntry(actual val handler: NativeAddress) {
	val binding: UInt
		get() = TODO()

	val buffer: WGPUBuffer
		get() = TODO()

	val offset: ULong
		get() = TODO()

	val size: ULong
		get() = TODO()

	val sampler: WGPUSampler
		get() = TODO()

	val textureView: WGPUTextureView
		get() = TODO()

}

actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val entries: Long
		get() = TODO()

}

actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	val binding: UInt
		get() = TODO()

	val visibility: ULong
		get() = TODO()

	val buffer: WGPUBufferBindingLayout
		get() = TODO()

	val sampler: WGPUSamplerBindingLayout
		get() = TODO()

	val texture: WGPUTextureBindingLayout
		get() = TODO()

	val storageTexture: WGPUStorageTextureBindingLayout
		get() = TODO()

}

actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	val operation: WGPUBlendOperation
		get() = TODO()

	val srcFactor: WGPUBlendFactor
		get() = TODO()

	val dstFactor: WGPUBlendFactor
		get() = TODO()

}

actual value class WGPUBlendState(actual val handler: NativeAddress) {
	val color: WGPUBlendComponent
		get() = TODO()

	val alpha: WGPUBlendComponent
		get() = TODO()

}

actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	val type: WGPUBufferBindingType
		get() = TODO()

	val hasDynamicOffset: Boolean
		get() = TODO()

	val minBindingSize: ULong
		get() = TODO()

}

actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val usage: ULong
		get() = TODO()

	val size: ULong
		get() = TODO()

	val mappedAtCreation: Boolean
		get() = TODO()

}

actual value class WGPUColor(actual val handler: NativeAddress) {
	val r: Double
		get() = TODO()

	val g: Double
		get() = TODO()

	val b: Double
		get() = TODO()

	val a: Double
		get() = TODO()

}

actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	val format: WGPUTextureFormat
		get() = TODO()

	val blend: WGPUBlendState
		get() = TODO()

	val writeMask: ULong
		get() = TODO()

}

actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	val messages: Long
		get() = TODO()

}

actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	val message: String
		get() = TODO()

	val type: WGPUCompilationMessageType
		get() = TODO()

	val lineNum: ULong
		get() = TODO()

	val linePos: ULong
		get() = TODO()

	val offset: ULong
		get() = TODO()

	val length: ULong
		get() = TODO()

	val utf16LinePos: ULong
		get() = TODO()

	val utf16Offset: ULong
		get() = TODO()

	val utf16Length: ULong
		get() = TODO()

}

actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val timestampWrites: WGPUComputePassTimestampWrites
		get() = TODO()

}

actual value class WGPUComputePassTimestampWrites(actual val handler: NativeAddress) {
	val querySet: WGPUQuerySet
		get() = TODO()

	val beginningOfPassWriteIndex: UInt
		get() = TODO()

	val endOfPassWriteIndex: UInt
		get() = TODO()

}

actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val layout: WGPUPipelineLayout
		get() = TODO()

	val compute: WGPUProgrammableStageDescriptor
		get() = TODO()

}

actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	val key: String
		get() = TODO()

	val value: Double
		get() = TODO()

}

actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	val format: WGPUTextureFormat
		get() = TODO()

	val depthWriteEnabled: WGPUOptionalBool
		get() = TODO()

	val depthCompare: WGPUCompareFunction
		get() = TODO()

	val stencilFront: WGPUStencilFaceState
		get() = TODO()

	val stencilBack: WGPUStencilFaceState
		get() = TODO()

	val stencilReadMask: UInt
		get() = TODO()

	val stencilWriteMask: UInt
		get() = TODO()

	val depthBias: Int
		get() = TODO()

	val depthBiasSlopeScale: Float
		get() = TODO()

	val depthBiasClamp: Float
		get() = TODO()

}

actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val requiredFeatures: Long
		get() = TODO()

	val requiredLimits: WGPURequiredLimits
		get() = TODO()

	val defaultQueue: WGPUQueueDescriptor
		get() = TODO()

	val deviceLostCallbackInfo: WGPUDeviceLostCallback
		get() = TODO()

	val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallback
		get() = TODO()

}

actual value class WGPUExtent3D(actual val handler: NativeAddress) {
	val width: UInt
		get() = TODO()

	val height: UInt
		get() = TODO()

	val depthOrArrayLayers: UInt
		get() = TODO()

}

actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	val module: WGPUShaderModule
		get() = TODO()

	val entryPoint: String
		get() = TODO()

	val constants: Long
		get() = TODO()

	val targets: Long
		get() = TODO()

}

actual value class WGPUFuture(actual val handler: NativeAddress) {
	val id: ULong
		get() = TODO()

}

actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	val future: WGPUFuture
		get() = TODO()

	val completed: Boolean
		get() = TODO()

}

actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	val layout: WGPUTextureDataLayout
		get() = TODO()

	val buffer: WGPUBuffer
		get() = TODO()

}

actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	val texture: WGPUTexture
		get() = TODO()

	val mipLevel: UInt
		get() = TODO()

	val origin: WGPUOrigin3D
		get() = TODO()

	val aspect: WGPUTextureAspect
		get() = TODO()

}

actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	val features: WGPUInstanceFeatures
		get() = TODO()

}

actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	val timedWaitAnyEnable: Boolean
		get() = TODO()

	val timedWaitAnyMaxCount: ULong
		get() = TODO()

}

actual value class WGPULimits(actual val handler: NativeAddress) {
	val maxTextureDimension1D: UInt
		get() = TODO()

	val maxTextureDimension2D: UInt
		get() = TODO()

	val maxTextureDimension3D: UInt
		get() = TODO()

	val maxTextureArrayLayers: UInt
		get() = TODO()

	val maxBindGroups: UInt
		get() = TODO()

	val maxBindGroupsPlusVertexBuffers: UInt
		get() = TODO()

	val maxBindingsPerBindGroup: UInt
		get() = TODO()

	val maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = TODO()

	val maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = TODO()

	val maxSampledTexturesPerShaderStage: UInt
		get() = TODO()

	val maxSamplersPerShaderStage: UInt
		get() = TODO()

	val maxStorageBuffersPerShaderStage: UInt
		get() = TODO()

	val maxStorageTexturesPerShaderStage: UInt
		get() = TODO()

	val maxUniformBuffersPerShaderStage: UInt
		get() = TODO()

	val maxUniformBufferBindingSize: ULong
		get() = TODO()

	val maxStorageBufferBindingSize: ULong
		get() = TODO()

	val minUniformBufferOffsetAlignment: UInt
		get() = TODO()

	val minStorageBufferOffsetAlignment: UInt
		get() = TODO()

	val maxVertexBuffers: UInt
		get() = TODO()

	val maxBufferSize: ULong
		get() = TODO()

	val maxVertexAttributes: UInt
		get() = TODO()

	val maxVertexBufferArrayStride: UInt
		get() = TODO()

	val maxInterStageShaderVariables: UInt
		get() = TODO()

	val maxColorAttachments: UInt
		get() = TODO()

	val maxColorAttachmentBytesPerSample: UInt
		get() = TODO()

	val maxComputeWorkgroupStorageSize: UInt
		get() = TODO()

	val maxComputeInvocationsPerWorkgroup: UInt
		get() = TODO()

	val maxComputeWorkgroupSizeX: UInt
		get() = TODO()

	val maxComputeWorkgroupSizeY: UInt
		get() = TODO()

	val maxComputeWorkgroupSizeZ: UInt
		get() = TODO()

	val maxComputeWorkgroupsPerDimension: UInt
		get() = TODO()

}

actual value class WGPUMultisampleState(actual val handler: NativeAddress) {
	val count: UInt
		get() = TODO()

	val mask: UInt
		get() = TODO()

	val alphaToCoverageEnabled: Boolean
		get() = TODO()

}

actual value class WGPUOrigin3D(actual val handler: NativeAddress) {
	val x: UInt
		get() = TODO()

	val y: UInt
		get() = TODO()

	val z: UInt
		get() = TODO()

}

actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val bindGroupLayouts: Long
		get() = TODO()

}

actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	val topology: WGPUPrimitiveTopology
		get() = TODO()

	val stripIndexFormat: WGPUIndexFormat
		get() = TODO()

	val frontFace: WGPUFrontFace
		get() = TODO()

	val cullMode: WGPUCullMode
		get() = TODO()

	val unclippedDepth: Boolean
		get() = TODO()

}

actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	val module: WGPUShaderModule
		get() = TODO()

	val entryPoint: String
		get() = TODO()

	val constants: Long
		get() = TODO()

}

actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val type: WGPUQueryType
		get() = TODO()

	val count: UInt
		get() = TODO()

}

actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val colorFormats: Long
		get() = TODO()

	val depthStencilFormat: WGPUTextureFormat
		get() = TODO()

	val sampleCount: UInt
		get() = TODO()

	val depthReadOnly: Boolean
		get() = TODO()

	val stencilReadOnly: Boolean
		get() = TODO()

}

actual value class WGPURenderPassColorAttachment(actual val handler: NativeAddress) {
	val view: WGPUTextureView
		get() = TODO()

	val depthSlice: UInt
		get() = TODO()

	val resolveTarget: WGPUTextureView
		get() = TODO()

	val loadOp: WGPULoadOp
		get() = TODO()

	val storeOp: WGPUStoreOp
		get() = TODO()

	val clearValue: WGPUColor
		get() = TODO()

}

actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	val view: WGPUTextureView
		get() = TODO()

	val depthLoadOp: WGPULoadOp
		get() = TODO()

	val depthStoreOp: WGPUStoreOp
		get() = TODO()

	val depthClearValue: Float
		get() = TODO()

	val depthReadOnly: Boolean
		get() = TODO()

	val stencilLoadOp: WGPULoadOp
		get() = TODO()

	val stencilStoreOp: WGPUStoreOp
		get() = TODO()

	val stencilClearValue: UInt
		get() = TODO()

	val stencilReadOnly: Boolean
		get() = TODO()

}

actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val colorAttachments: Long
		get() = TODO()

	val depthStencilAttachment: WGPURenderPassDepthStencilAttachment
		get() = TODO()

	val occlusionQuerySet: WGPUQuerySet
		get() = TODO()

	val timestampWrites: WGPURenderPassTimestampWrites
		get() = TODO()

}

actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	val maxDrawCount: ULong
		get() = TODO()

}

actual value class WGPURenderPassTimestampWrites(actual val handler: NativeAddress) {
	val querySet: WGPUQuerySet
		get() = TODO()

	val beginningOfPassWriteIndex: UInt
		get() = TODO()

	val endOfPassWriteIndex: UInt
		get() = TODO()

}

actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val layout: WGPUPipelineLayout
		get() = TODO()

	val vertex: WGPUVertexState
		get() = TODO()

	val primitive: WGPUPrimitiveState
		get() = TODO()

	val depthStencil: WGPUDepthStencilState
		get() = TODO()

	val multisample: WGPUMultisampleState
		get() = TODO()

	val fragment: WGPUFragmentState
		get() = TODO()

}

actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	val compatibleSurface: WGPUSurface
		get() = TODO()

	val powerPreference: WGPUPowerPreference
		get() = TODO()

	val backendType: WGPUBackendType
		get() = TODO()

	val forceFallbackAdapter: Boolean
		get() = TODO()

}

actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	val limits: WGPULimits
		get() = TODO()

}

actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	val type: WGPUSamplerBindingType
		get() = TODO()

}

actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val addressModeU: WGPUAddressMode
		get() = TODO()

	val addressModeV: WGPUAddressMode
		get() = TODO()

	val addressModeW: WGPUAddressMode
		get() = TODO()

	val magFilter: WGPUFilterMode
		get() = TODO()

	val minFilter: WGPUFilterMode
		get() = TODO()

	val mipmapFilter: WGPUMipmapFilterMode
		get() = TODO()

	val lodMinClamp: Float
		get() = TODO()

	val lodMaxClamp: Float
		get() = TODO()

	val compare: WGPUCompareFunction
		get() = TODO()

	val maxAnisotropy: UShort
		get() = TODO()

}

actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	val codeSize: UInt
		get() = TODO()

	val code: UInt
		get() = TODO()

}

actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	val code: String
		get() = TODO()

}

actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	val compare: WGPUCompareFunction
		get() = TODO()

	val failOp: WGPUStencilOperation
		get() = TODO()

	val depthFailOp: WGPUStencilOperation
		get() = TODO()

	val passOp: WGPUStencilOperation
		get() = TODO()

}

actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	val access: WGPUStorageTextureAccess
		get() = TODO()

	val format: WGPUTextureFormat
		get() = TODO()

	val viewDimension: WGPUTextureViewDimension
		get() = TODO()

}

actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	val limits: WGPULimits
		get() = TODO()

}

actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	val usages: ULong
		get() = TODO()

	val formats: Long
		get() = TODO()

	val presentModes: Long
		get() = TODO()

	val alphaModes: Long
		get() = TODO()

}

actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	val device: WGPUDevice
		get() = TODO()

	val format: WGPUTextureFormat
		get() = TODO()

	val usage: ULong
		get() = TODO()

	val width: UInt
		get() = TODO()

	val height: UInt
		get() = TODO()

	val viewFormats: Long
		get() = TODO()

	val alphaMode: WGPUCompositeAlphaMode
		get() = TODO()

	val presentMode: WGPUPresentMode
		get() = TODO()

}

actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

}

actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	val window: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	val layer: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	val display: Unit
		get() = TODO()

	val surface: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	val hinstance: Unit
		get() = TODO()

	val hwnd: Unit
		get() = TODO()

}

actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	val connection: Unit
		get() = TODO()

	val window: UInt
		get() = TODO()

}

actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	val display: Unit
		get() = TODO()

	val window: ULong
		get() = TODO()

}

actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	val texture: WGPUTexture
		get() = TODO()

	val status: WGPUSurfaceGetCurrentTextureStatus
		get() = TODO()

}

actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	val sampleType: WGPUTextureSampleType
		get() = TODO()

	val viewDimension: WGPUTextureViewDimension
		get() = TODO()

	val multisampled: Boolean
		get() = TODO()

}

actual value class WGPUTextureDataLayout(actual val handler: NativeAddress) {
	val offset: ULong
		get() = TODO()

	val bytesPerRow: UInt
		get() = TODO()

	val rowsPerImage: UInt
		get() = TODO()

}

actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val usage: ULong
		get() = TODO()

	val dimension: WGPUTextureDimension
		get() = TODO()

	val size: WGPUExtent3D
		get() = TODO()

	val format: WGPUTextureFormat
		get() = TODO()

	val mipLevelCount: UInt
		get() = TODO()

	val sampleCount: UInt
		get() = TODO()

	val viewFormats: Long
		get() = TODO()

}

actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	val label: String
		get() = TODO()

	val format: WGPUTextureFormat
		get() = TODO()

	val dimension: WGPUTextureViewDimension
		get() = TODO()

	val baseMipLevel: UInt
		get() = TODO()

	val mipLevelCount: UInt
		get() = TODO()

	val baseArrayLayer: UInt
		get() = TODO()

	val arrayLayerCount: UInt
		get() = TODO()

	val aspect: WGPUTextureAspect
		get() = TODO()

	val usage: ULong
		get() = TODO()

}

actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	val format: WGPUVertexFormat
		get() = TODO()

	val offset: ULong
		get() = TODO()

	val shaderLocation: UInt
		get() = TODO()

}

actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	val arrayStride: ULong
		get() = TODO()

	val stepMode: WGPUVertexStepMode
		get() = TODO()

	val attributes: Long
		get() = TODO()

}

actual value class WGPUVertexState(actual val handler: NativeAddress) {
	val module: WGPUShaderModule
		get() = TODO()

	val entryPoint: String
		get() = TODO()

	val constants: Long
		get() = TODO()

	val buffers: Long
		get() = TODO()

}

actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	val next: WGPUChainedStruct
		get() = TODO()

	val sType: WGPUSType
		get() = TODO()

}

actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	val next: WGPUChainedStruct
		get() = TODO()

	val sType: WGPUSType
		get() = TODO()

}

