// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder

expect value class WGPUAdapterInfo(val handler: NativeAddress) {
	var vendor: String
	var architecture: String
	var device: String
	var description: String
	var backendType: WGPUBackendType?
	var adapterType: WGPUAdapterType?
	var vendorID: UInt
	var deviceID: UInt
}

expect value class WGPUBindGroupDescriptor(val handler: NativeAddress) {
	var label: String
	var layout: WGPUBindGroupLayout?
	var entries: Long
}

expect value class WGPUBindGroupEntry(val handler: NativeAddress) {
	var binding: UInt
	var buffer: WGPUBuffer?
	var offset: ULong
	var size: ULong
	var sampler: WGPUSampler?
	var textureView: WGPUTextureView?
}

expect value class WGPUBindGroupLayoutDescriptor(val handler: NativeAddress) {
	var label: String
	var entries: Long
}

expect value class WGPUBindGroupLayoutEntry(val handler: NativeAddress) {
	var binding: UInt
	var visibility: ULong
	var buffer: WGPUBufferBindingLayout?
	var sampler: WGPUSamplerBindingLayout?
	var texture: WGPUTextureBindingLayout?
	var storageTexture: WGPUStorageTextureBindingLayout?
}

expect value class WGPUBlendComponent(val handler: NativeAddress) {
	var operation: WGPUBlendOperation?
	var srcFactor: WGPUBlendFactor?
	var dstFactor: WGPUBlendFactor?
}

expect value class WGPUBlendState(val handler: NativeAddress) {
	var color: WGPUBlendComponent?
	var alpha: WGPUBlendComponent?
}

expect value class WGPUBufferBindingLayout(val handler: NativeAddress) {
	var type: WGPUBufferBindingType?
	var hasDynamicOffset: Boolean
	var minBindingSize: ULong
}

expect value class WGPUBufferDescriptor(val handler: NativeAddress) {
	var label: String
	var usage: ULong
	var size: ULong
	var mappedAtCreation: Boolean
}

expect value class WGPUColor(val handler: NativeAddress) {
	var r: Double
	var g: Double
	var b: Double
	var a: Double
}

expect value class WGPUColorTargetState(val handler: NativeAddress) {
	var format: WGPUTextureFormat?
	var blend: WGPUBlendState?
	var writeMask: ULong
}

expect value class WGPUCommandBufferDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPUCommandEncoderDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPUCompilationInfo(val handler: NativeAddress) {
	var messages: Long
}

expect value class WGPUCompilationMessage(val handler: NativeAddress) {
	var message: String
	var type: WGPUCompilationMessageType?
	var lineNum: ULong
	var linePos: ULong
	var offset: ULong
	var length: ULong
	var utf16LinePos: ULong
	var utf16Offset: ULong
	var utf16Length: ULong
}

expect value class WGPUComputePassDescriptor(val handler: NativeAddress) {
	var label: String
	var timestampWrites: WGPUComputePassTimestampWrites?
}

expect value class WGPUComputePassTimestampWrites(val handler: NativeAddress) {
	var querySet: WGPUQuerySet?
	var beginningOfPassWriteIndex: UInt
	var endOfPassWriteIndex: UInt
}

expect value class WGPUComputePipelineDescriptor(val handler: NativeAddress) {
	var label: String
	var layout: WGPUPipelineLayout?
	var compute: WGPUProgrammableStageDescriptor?
}

expect value class WGPUConstantEntry(val handler: NativeAddress) {
	var key: String
	var value: Double
}

expect value class WGPUDepthStencilState(val handler: NativeAddress) {
	var format: WGPUTextureFormat?
	var depthWriteEnabled: WGPUOptionalBool?
	var depthCompare: WGPUCompareFunction?
	var stencilFront: WGPUStencilFaceState?
	var stencilBack: WGPUStencilFaceState?
	var stencilReadMask: UInt
	var stencilWriteMask: UInt
	var depthBias: Int
	var depthBiasSlopeScale: Float
	var depthBiasClamp: Float
}

expect value class WGPUDeviceDescriptor(val handler: NativeAddress) {
	var label: String
	var requiredFeatures: Long
	var requiredLimits: WGPURequiredLimits?
	var defaultQueue: WGPUQueueDescriptor?
	var deviceLostCallbackInfo: CallbackHolder<WGPUDeviceLostCallbackInfo>?
	var uncapturedErrorCallbackInfo: CallbackHolder<WGPUUncapturedErrorCallbackInfo>?
}

expect value class WGPUExtent3D(val handler: NativeAddress) {
	var width: UInt
	var height: UInt
	var depthOrArrayLayers: UInt
}

expect value class WGPUFragmentState(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: String
	var constants: Long
	var targets: Long
}

expect value class WGPUFuture(val handler: NativeAddress) {
	var id: ULong
}

expect value class WGPUFutureWaitInfo(val handler: NativeAddress) {
	var future: WGPUFuture?
	var completed: Boolean
}

expect value class WGPUImageCopyBuffer(val handler: NativeAddress) {
	var layout: WGPUTextureDataLayout?
	var buffer: WGPUBuffer?
}

expect value class WGPUImageCopyTexture(val handler: NativeAddress) {
	var texture: WGPUTexture?
	var mipLevel: UInt
	var origin: WGPUOrigin3D?
	var aspect: WGPUTextureAspect?
}

expect value class WGPUInstanceDescriptor(val handler: NativeAddress) {
	var features: WGPUInstanceFeatures?
}

expect value class WGPUInstanceFeatures(val handler: NativeAddress) {
	var timedWaitAnyEnable: Boolean
	var timedWaitAnyMaxCount: ULong
}

expect value class WGPULimits(val handler: NativeAddress) {
	var maxTextureDimension1D: UInt
	var maxTextureDimension2D: UInt
	var maxTextureDimension3D: UInt
	var maxTextureArrayLayers: UInt
	var maxBindGroups: UInt
	var maxBindGroupsPlusVertexBuffers: UInt
	var maxBindingsPerBindGroup: UInt
	var maxDynamicUniformBuffersPerPipelineLayout: UInt
	var maxDynamicStorageBuffersPerPipelineLayout: UInt
	var maxSampledTexturesPerShaderStage: UInt
	var maxSamplersPerShaderStage: UInt
	var maxStorageBuffersPerShaderStage: UInt
	var maxStorageTexturesPerShaderStage: UInt
	var maxUniformBuffersPerShaderStage: UInt
	var maxUniformBufferBindingSize: ULong
	var maxStorageBufferBindingSize: ULong
	var minUniformBufferOffsetAlignment: UInt
	var minStorageBufferOffsetAlignment: UInt
	var maxVertexBuffers: UInt
	var maxBufferSize: ULong
	var maxVertexAttributes: UInt
	var maxVertexBufferArrayStride: UInt
	var maxInterStageShaderVariables: UInt
	var maxColorAttachments: UInt
	var maxColorAttachmentBytesPerSample: UInt
	var maxComputeWorkgroupStorageSize: UInt
	var maxComputeInvocationsPerWorkgroup: UInt
	var maxComputeWorkgroupSizeX: UInt
	var maxComputeWorkgroupSizeY: UInt
	var maxComputeWorkgroupSizeZ: UInt
	var maxComputeWorkgroupsPerDimension: UInt
}

expect value class WGPUMultisampleState(val handler: NativeAddress) {
	var count: UInt
	var mask: UInt
	var alphaToCoverageEnabled: Boolean
}

expect value class WGPUOrigin3D(val handler: NativeAddress) {
	var x: UInt
	var y: UInt
	var z: UInt
}

expect value class WGPUPipelineLayoutDescriptor(val handler: NativeAddress) {
	var label: String
	var bindGroupLayouts: Long
}

expect value class WGPUPrimitiveState(val handler: NativeAddress) {
	var topology: WGPUPrimitiveTopology?
	var stripIndexFormat: WGPUIndexFormat?
	var frontFace: WGPUFrontFace?
	var cullMode: WGPUCullMode?
	var unclippedDepth: Boolean
}

expect value class WGPUProgrammableStageDescriptor(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: String
	var constants: Long
}

expect value class WGPUQuerySetDescriptor(val handler: NativeAddress) {
	var label: String
	var type: WGPUQueryType?
	var count: UInt
}

expect value class WGPUQueueDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPURenderBundleDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPURenderBundleEncoderDescriptor(val handler: NativeAddress) {
	var label: String
	var colorFormats: Long
	var depthStencilFormat: WGPUTextureFormat?
	var sampleCount: UInt
	var depthReadOnly: Boolean
	var stencilReadOnly: Boolean
}

expect value class WGPURenderPassColorAttachment(val handler: NativeAddress) {
	var view: WGPUTextureView?
	var depthSlice: UInt
	var resolveTarget: WGPUTextureView?
	var loadOp: WGPULoadOp?
	var storeOp: WGPUStoreOp?
	var clearValue: WGPUColor?
}

expect value class WGPURenderPassDepthStencilAttachment(val handler: NativeAddress) {
	var view: WGPUTextureView?
	var depthLoadOp: WGPULoadOp?
	var depthStoreOp: WGPUStoreOp?
	var depthClearValue: Float
	var depthReadOnly: Boolean
	var stencilLoadOp: WGPULoadOp?
	var stencilStoreOp: WGPUStoreOp?
	var stencilClearValue: UInt
	var stencilReadOnly: Boolean
}

expect value class WGPURenderPassDescriptor(val handler: NativeAddress) {
	var label: String
	var colorAttachments: Long
	var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
	var occlusionQuerySet: WGPUQuerySet?
	var timestampWrites: WGPURenderPassTimestampWrites?
}

expect value class WGPURenderPassMaxDrawCount(val handler: NativeAddress) {
	var maxDrawCount: ULong
}

expect value class WGPURenderPassTimestampWrites(val handler: NativeAddress) {
	var querySet: WGPUQuerySet?
	var beginningOfPassWriteIndex: UInt
	var endOfPassWriteIndex: UInt
}

expect value class WGPURenderPipelineDescriptor(val handler: NativeAddress) {
	var label: String
	var layout: WGPUPipelineLayout?
	var vertex: WGPUVertexState?
	var primitive: WGPUPrimitiveState?
	var depthStencil: WGPUDepthStencilState?
	var multisample: WGPUMultisampleState?
	var fragment: WGPUFragmentState?
}

expect value class WGPURequestAdapterOptions(val handler: NativeAddress) {
	var compatibleSurface: WGPUSurface?
	var powerPreference: WGPUPowerPreference?
	var backendType: WGPUBackendType?
	var forceFallbackAdapter: Boolean
}

expect value class WGPURequiredLimits(val handler: NativeAddress) {
	var limits: WGPULimits?
}

expect value class WGPUSamplerBindingLayout(val handler: NativeAddress) {
	var type: WGPUSamplerBindingType?
}

expect value class WGPUSamplerDescriptor(val handler: NativeAddress) {
	var label: String
	var addressModeU: WGPUAddressMode?
	var addressModeV: WGPUAddressMode?
	var addressModeW: WGPUAddressMode?
	var magFilter: WGPUFilterMode?
	var minFilter: WGPUFilterMode?
	var mipmapFilter: WGPUMipmapFilterMode?
	var lodMinClamp: Float
	var lodMaxClamp: Float
	var compare: WGPUCompareFunction?
	var maxAnisotropy: UShort
}

expect value class WGPUShaderModuleDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPUShaderSourceSPIRV(val handler: NativeAddress) {
	var codeSize: UInt
	var code: UInt
}

expect value class WGPUShaderSourceWGSL(val handler: NativeAddress) {
	var code: String
}

expect value class WGPUStencilFaceState(val handler: NativeAddress) {
	var compare: WGPUCompareFunction?
	var failOp: WGPUStencilOperation?
	var depthFailOp: WGPUStencilOperation?
	var passOp: WGPUStencilOperation?
}

expect value class WGPUStorageTextureBindingLayout(val handler: NativeAddress) {
	var access: WGPUStorageTextureAccess?
	var format: WGPUTextureFormat?
	var viewDimension: WGPUTextureViewDimension?
}

expect value class WGPUSupportedLimits(val handler: NativeAddress) {
	var limits: WGPULimits?
}

expect value class WGPUSurfaceCapabilities(val handler: NativeAddress) {
	var usages: ULong
	var formats: Long
	var presentModes: Long
	var alphaModes: Long
}

expect value class WGPUSurfaceConfiguration(val handler: NativeAddress) {
	var device: WGPUDevice?
	var format: WGPUTextureFormat?
	var usage: ULong
	var width: UInt
	var height: UInt
	var viewFormats: Long
	var alphaMode: WGPUCompositeAlphaMode?
	var presentMode: WGPUPresentMode?
}

expect value class WGPUSurfaceDescriptor(val handler: NativeAddress) {
	var label: String
}

expect value class WGPUSurfaceSourceAndroidNativeWindow(val handler: NativeAddress) {
	var window: Unit
}

expect value class WGPUSurfaceSourceMetalLayer(val handler: NativeAddress) {
	var layer: Unit
}

expect value class WGPUSurfaceSourceWaylandSurface(val handler: NativeAddress) {
	var display: Unit
	var surface: Unit
}

expect value class WGPUSurfaceSourceWindowsHWND(val handler: NativeAddress) {
	var hinstance: Unit
	var hwnd: Unit
}

expect value class WGPUSurfaceSourceXCBWindow(val handler: NativeAddress) {
	var connection: Unit
	var window: UInt
}

expect value class WGPUSurfaceSourceXlibWindow(val handler: NativeAddress) {
	var display: Unit
	var window: ULong
}

expect value class WGPUSurfaceTexture(val handler: NativeAddress) {
	var texture: WGPUTexture?
	var status: WGPUSurfaceGetCurrentTextureStatus?
}

expect value class WGPUTextureBindingLayout(val handler: NativeAddress) {
	var sampleType: WGPUTextureSampleType?
	var viewDimension: WGPUTextureViewDimension?
	var multisampled: Boolean
}

expect value class WGPUTextureDataLayout(val handler: NativeAddress) {
	var offset: ULong
	var bytesPerRow: UInt
	var rowsPerImage: UInt
}

expect value class WGPUTextureDescriptor(val handler: NativeAddress) {
	var label: String
	var usage: ULong
	var dimension: WGPUTextureDimension?
	var size: WGPUExtent3D?
	var format: WGPUTextureFormat?
	var mipLevelCount: UInt
	var sampleCount: UInt
	var viewFormats: Long
}

expect value class WGPUTextureViewDescriptor(val handler: NativeAddress) {
	var label: String
	var format: WGPUTextureFormat?
	var dimension: WGPUTextureViewDimension?
	var baseMipLevel: UInt
	var mipLevelCount: UInt
	var baseArrayLayer: UInt
	var arrayLayerCount: UInt
	var aspect: WGPUTextureAspect?
	var usage: ULong
}

expect value class WGPUVertexAttribute(val handler: NativeAddress) {
	var format: WGPUVertexFormat?
	var offset: ULong
	var shaderLocation: UInt
}

expect value class WGPUVertexBufferLayout(val handler: NativeAddress) {
	var arrayStride: ULong
	var stepMode: WGPUVertexStepMode?
	var attributes: Long
}

expect value class WGPUVertexState(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: String
	var constants: Long
	var buffers: Long
}

expect value class WGPUChainedStruct(val handler: NativeAddress) {
	var next: WGPUChainedStruct?
	var sType: WGPUSType
}

expect value class WGPUChainedStructOut(val handler: NativeAddress) {
	var next: WGPUChainedStructOut?
	var sType: WGPUSType
}

