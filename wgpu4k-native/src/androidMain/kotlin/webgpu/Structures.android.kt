// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder

@JvmInline
actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	actual var vendor: String
		get() = TODO()
		set(newValue) = TODO()

	actual var architecture: String
		get() = TODO()
		set(newValue) = TODO()

	actual var device: String
		get() = TODO()
		set(newValue) = TODO()

	actual var description: String
		get() = TODO()
		set(newValue) = TODO()

	actual var backendType: WGPUBackendType?
		get() = TODO()
		set(newValue) = TODO()

	actual var adapterType: WGPUAdapterType?
		get() = TODO()
		set(newValue) = TODO()

	actual var vendorID: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var deviceID: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUBindGroupLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var entries: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBindGroupEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var size: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var sampler: WGPUSampler?
		get() = TODO()
		set(newValue) = TODO()

	actual var textureView: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var entries: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var visibility: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var buffer: WGPUBufferBindingLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var sampler: WGPUSamplerBindingLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var texture: WGPUTextureBindingLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var storageTexture: WGPUStorageTextureBindingLayout?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	actual var operation: WGPUBlendOperation?
		get() = TODO()
		set(newValue) = TODO()

	actual var srcFactor: WGPUBlendFactor?
		get() = TODO()
		set(newValue) = TODO()

	actual var dstFactor: WGPUBlendFactor?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBlendState(actual val handler: NativeAddress) {
	actual var color: WGPUBlendComponent?
		get() = TODO()
		set(newValue) = TODO()

	actual var alpha: WGPUBlendComponent?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUBufferBindingType?
		get() = TODO()
		set(newValue) = TODO()

	actual var hasDynamicOffset: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var minBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var size: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var mappedAtCreation: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUColor(actual val handler: NativeAddress) {
	actual var r: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var g: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var b: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var a: Double
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var blend: WGPUBlendState?
		get() = TODO()
		set(newValue) = TODO()

	actual var writeMask: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	actual var messages: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual var message: String
		get() = TODO()
		set(newValue) = TODO()

	actual var type: WGPUCompilationMessageType?
		get() = TODO()
		set(newValue) = TODO()

	actual var lineNum: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var linePos: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var length: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16LinePos: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16Offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16Length: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUComputePassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var beginningOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var endOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var compute: WGPUProgrammableStageDescriptor?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual var key: String
		get() = TODO()
		set(newValue) = TODO()

	actual var value: Double
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthWriteEnabled: WGPUOptionalBool?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthCompare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilFront: WGPUStencilFaceState?
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilBack: WGPUStencilFaceState?
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilReadMask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilWriteMask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBias: Int
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBiasSlopeScale: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBiasClamp: Float
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var requiredFeatures: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var requiredLimits: WGPURequiredLimits?
		get() = TODO()
		set(newValue) = TODO()

	actual var defaultQueue: WGPUQueueDescriptor?
		get() = TODO()
		set(newValue) = TODO()

	actual var deviceLostCallbackInfo: CallbackHolder<WGPUDeviceLostCallbackInfo>?
		get() = TODO()
		set(newValue) = TODO()

	actual var uncapturedErrorCallbackInfo: CallbackHolder<WGPUUncapturedErrorCallbackInfo>?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUExtent3D(actual val handler: NativeAddress) {
	actual var width: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var height: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthOrArrayLayers: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: String
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var targets: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUFuture(actual val handler: NativeAddress) {
	actual var id: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	actual var future: WGPUFuture?
		get() = TODO()
		set(newValue) = TODO()

	actual var completed: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	actual var layout: WGPUTextureDataLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevel: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var origin: WGPUOrigin3D?
		get() = TODO()
		set(newValue) = TODO()

	actual var aspect: WGPUTextureAspect?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	actual var features: WGPUInstanceFeatures?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	actual var timedWaitAnyEnable: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var timedWaitAnyMaxCount: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPULimits(actual val handler: NativeAddress) {
	actual var maxTextureDimension1D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureDimension2D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureDimension3D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureArrayLayers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindGroups: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindingsPerBindGroup: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxSamplersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxUniformBufferBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageBufferBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var minUniformBufferOffsetAlignment: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var minStorageBufferOffsetAlignment: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexBuffers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBufferSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexAttributes: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexBufferArrayStride: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxInterStageShaderVariables: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxColorAttachments: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeX: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeY: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUMultisampleState(actual val handler: NativeAddress) {
	actual var count: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var mask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaToCoverageEnabled: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUOrigin3D(actual val handler: NativeAddress) {
	actual var x: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var y: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var z: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var bindGroupLayouts: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	actual var topology: WGPUPrimitiveTopology?
		get() = TODO()
		set(newValue) = TODO()

	actual var stripIndexFormat: WGPUIndexFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var frontFace: WGPUFrontFace?
		get() = TODO()
		set(newValue) = TODO()

	actual var cullMode: WGPUCullMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var unclippedDepth: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: String
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var type: WGPUQueryType?
		get() = TODO()
		set(newValue) = TODO()

	actual var count: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var colorFormats: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStencilFormat: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var sampleCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPassColorAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthSlice: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var resolveTarget: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var loadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var storeOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var clearValue: WGPUColor?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthLoadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStoreOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthClearValue: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var depthReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilLoadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilStoreOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilClearValue: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var colorAttachments: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = TODO()
		set(newValue) = TODO()

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	actual var maxDrawCount: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var beginningOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var endOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var vertex: WGPUVertexState?
		get() = TODO()
		set(newValue) = TODO()

	actual var primitive: WGPUPrimitiveState?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStencil: WGPUDepthStencilState?
		get() = TODO()
		set(newValue) = TODO()

	actual var multisample: WGPUMultisampleState?
		get() = TODO()
		set(newValue) = TODO()

	actual var fragment: WGPUFragmentState?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	actual var compatibleSurface: WGPUSurface?
		get() = TODO()
		set(newValue) = TODO()

	actual var powerPreference: WGPUPowerPreference?
		get() = TODO()
		set(newValue) = TODO()

	actual var backendType: WGPUBackendType?
		get() = TODO()
		set(newValue) = TODO()

	actual var forceFallbackAdapter: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	actual var limits: WGPULimits?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUSamplerBindingType?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeU: WGPUAddressMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeV: WGPUAddressMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeW: WGPUAddressMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var magFilter: WGPUFilterMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var minFilter: WGPUFilterMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var mipmapFilter: WGPUMipmapFilterMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var lodMinClamp: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var lodMaxClamp: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var compare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) = TODO()

	actual var maxAnisotropy: UShort
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	actual var codeSize: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var code: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual var code: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	actual var compare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) = TODO()

	actual var failOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthFailOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) = TODO()

	actual var passOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	actual var access: WGPUStorageTextureAccess?
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var viewDimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	actual var limits: WGPULimits?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	actual var usages: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var formats: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var presentModes: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaModes: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	actual var device: WGPUDevice?
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var width: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var height: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormats: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaMode: WGPUCompositeAlphaMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var presentMode: WGPUPresentMode?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	actual var window: Unit
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	actual var layer: Unit
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	actual var display: Unit
		get() = TODO()
		set(newValue) = TODO()

	actual var surface: Unit
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	actual var hinstance: Unit
		get() = TODO()
		set(newValue) = TODO()

	actual var hwnd: Unit
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	actual var connection: Unit
		get() = TODO()
		set(newValue) = TODO()

	actual var window: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	actual var display: Unit
		get() = TODO()
		set(newValue) = TODO()

	actual var window: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = TODO()

	actual var status: WGPUSurfaceGetCurrentTextureStatus?
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	actual var sampleType: WGPUTextureSampleType?
		get() = TODO()
		set(newValue) = TODO()

	actual var viewDimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) = TODO()

	actual var multisampled: Boolean
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUTextureDataLayout(actual val handler: NativeAddress) {
	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var bytesPerRow: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var rowsPerImage: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var dimension: WGPUTextureDimension?
		get() = TODO()
		set(newValue) = TODO()

	actual var size: WGPUExtent3D?
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevelCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var sampleCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormats: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual var label: String
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var dimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) = TODO()

	actual var baseMipLevel: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevelCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var baseArrayLayer: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var arrayLayerCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var aspect: WGPUTextureAspect?
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	actual var format: WGPUVertexFormat?
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var shaderLocation: UInt
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	actual var arrayStride: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var stepMode: WGPUVertexStepMode?
		get() = TODO()
		set(newValue) = TODO()

	actual var attributes: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: String
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: Long
		get() = TODO()
		set(newValue) = TODO()

	actual var buffers: Long
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = TODO()

}

@JvmInline
actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStructOut?
		get() = TODO()
		set(newValue) = TODO()

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = TODO()

}

