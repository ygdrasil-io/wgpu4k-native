// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.CString

expect value class WGPUAdapterInfo(val handler: NativeAddress) {
	var vendor: CString?
	var architecture: CString?
	var device: CString?
	var description: CString?
	var backendType: WGPUBackendType
	var adapterType: WGPUAdapterType
	var vendorID: UInt
	var deviceID: UInt
}

expect value class WGPUBindGroupDescriptor(val handler: NativeAddress) {
	var label: CString?
	var layout: WGPUBindGroupLayout?
	var entryCount: ULong
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
	var label: CString?
	var entryCount: ULong
	var entries: Long
}

expect value class WGPUBindGroupLayoutEntry(val handler: NativeAddress) {
	var binding: UInt
	var visibility: ULong
	val buffer: WGPUBufferBindingLayout
	val sampler: WGPUSamplerBindingLayout
	val texture: WGPUTextureBindingLayout
	val storageTexture: WGPUStorageTextureBindingLayout
}

expect value class WGPUBlendComponent(val handler: NativeAddress) {
	var operation: WGPUBlendOperation
	var srcFactor: WGPUBlendFactor
	var dstFactor: WGPUBlendFactor
}

expect value class WGPUBlendState(val handler: NativeAddress) {
	val color: WGPUBlendComponent
	val alpha: WGPUBlendComponent
}

expect value class WGPUBufferBindingLayout(val handler: NativeAddress) {
	var type: WGPUBufferBindingType
	var hasDynamicOffset: Boolean
	var minBindingSize: ULong
}

expect value class WGPUBufferDescriptor(val handler: NativeAddress) {
	var label: CString?
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
	var format: WGPUTextureFormat
	var blend: WGPUBlendState?
	var writeMask: ULong
}

expect value class WGPUCommandBufferDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPUCommandEncoderDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPUCompilationInfo(val handler: NativeAddress) {
	var messageCount: ULong
	var messages: Long
}

expect value class WGPUCompilationMessage(val handler: NativeAddress) {
	var message: CString?
	var type: WGPUCompilationMessageType
	var lineNum: ULong
	var linePos: ULong
	var offset: ULong
	var length: ULong
	var utf16LinePos: ULong
	var utf16Offset: ULong
	var utf16Length: ULong
}

expect value class WGPUComputePassDescriptor(val handler: NativeAddress) {
	var label: CString?
	var timestampWrites: WGPUComputePassTimestampWrites?
}

expect value class WGPUComputePassTimestampWrites(val handler: NativeAddress) {
	var querySet: WGPUQuerySet?
	var beginningOfPassWriteIndex: UInt
	var endOfPassWriteIndex: UInt
}

expect value class WGPUComputePipelineDescriptor(val handler: NativeAddress) {
	var label: CString?
	var layout: WGPUPipelineLayout?
	val compute: WGPUProgrammableStageDescriptor
}

expect value class WGPUConstantEntry(val handler: NativeAddress) {
	var key: CString?
	var value: Double
}

expect value class WGPUDepthStencilState(val handler: NativeAddress) {
	var format: WGPUTextureFormat
	var depthWriteEnabled: WGPUOptionalBool
	var depthCompare: WGPUCompareFunction
	val stencilFront: WGPUStencilFaceState
	val stencilBack: WGPUStencilFaceState
	var stencilReadMask: UInt
	var stencilWriteMask: UInt
	var depthBias: Int
	var depthBiasSlopeScale: Float
	var depthBiasClamp: Float
}

expect value class WGPUDeviceDescriptor(val handler: NativeAddress) {
	var label: CString?
	var requiredFeatureCount: ULong
	var requiredFeatures: Long
	var requiredLimits: WGPURequiredLimits?
	val defaultQueue: WGPUQueueDescriptor
	val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
	val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
}

expect value class WGPUExtent3D(val handler: NativeAddress) {
	var width: UInt
	var height: UInt
	var depthOrArrayLayers: UInt
}

expect value class WGPUFragmentState(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: CString?
	var constantCount: ULong
	var constants: Long
	var targetCount: ULong
	var targets: Long
}

expect value class WGPUFuture(val handler: NativeAddress) {
	var id: ULong
}

expect value class WGPUFutureWaitInfo(val handler: NativeAddress) {
	val future: WGPUFuture
	var completed: Boolean
}

expect value class WGPUImageCopyBuffer(val handler: NativeAddress) {
	val layout: WGPUTextureDataLayout
	var buffer: WGPUBuffer?
}

expect value class WGPUImageCopyTexture(val handler: NativeAddress) {
	var texture: WGPUTexture?
	var mipLevel: UInt
	val origin: WGPUOrigin3D
	var aspect: WGPUTextureAspect
}

expect value class WGPUInstanceDescriptor(val handler: NativeAddress) {
	val features: WGPUInstanceFeatures
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
	var label: CString?
	var bindGroupLayoutCount: ULong
	var bindGroupLayouts: Long
}

expect value class WGPUPrimitiveState(val handler: NativeAddress) {
	var topology: WGPUPrimitiveTopology
	var stripIndexFormat: WGPUIndexFormat
	var frontFace: WGPUFrontFace
	var cullMode: WGPUCullMode
	var unclippedDepth: Boolean
}

expect value class WGPUProgrammableStageDescriptor(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: CString?
	var constantCount: ULong
	var constants: Long
}

expect value class WGPUQuerySetDescriptor(val handler: NativeAddress) {
	var label: CString?
	var type: WGPUQueryType
	var count: UInt
}

expect value class WGPUQueueDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPURenderBundleDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPURenderBundleEncoderDescriptor(val handler: NativeAddress) {
	var label: CString?
	var colorFormatCount: ULong
	var colorFormats: Long
	var depthStencilFormat: WGPUTextureFormat
	var sampleCount: UInt
	var depthReadOnly: Boolean
	var stencilReadOnly: Boolean
}

expect value class WGPURenderPassColorAttachment(val handler: NativeAddress) {
	var view: WGPUTextureView?
	var depthSlice: UInt
	var resolveTarget: WGPUTextureView?
	var loadOp: WGPULoadOp
	var storeOp: WGPUStoreOp
	val clearValue: WGPUColor
}

expect value class WGPURenderPassDepthStencilAttachment(val handler: NativeAddress) {
	var view: WGPUTextureView?
	var depthLoadOp: WGPULoadOp
	var depthStoreOp: WGPUStoreOp
	var depthClearValue: Float
	var depthReadOnly: Boolean
	var stencilLoadOp: WGPULoadOp
	var stencilStoreOp: WGPUStoreOp
	var stencilClearValue: UInt
	var stencilReadOnly: Boolean
}

expect value class WGPURenderPassDescriptor(val handler: NativeAddress) {
	var label: CString?
	var colorAttachmentCount: ULong
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
	var label: CString?
	var layout: WGPUPipelineLayout?
	val vertex: WGPUVertexState
	val primitive: WGPUPrimitiveState
	var depthStencil: WGPUDepthStencilState?
	val multisample: WGPUMultisampleState
	var fragment: WGPUFragmentState?
}

expect value class WGPURequestAdapterOptions(val handler: NativeAddress) {
	var compatibleSurface: WGPUSurface?
	var powerPreference: WGPUPowerPreference
	var backendType: WGPUBackendType
	var forceFallbackAdapter: Boolean
}

expect value class WGPURequiredLimits(val handler: NativeAddress) {
	val limits: WGPULimits
}

expect value class WGPUSamplerBindingLayout(val handler: NativeAddress) {
	var type: WGPUSamplerBindingType
}

expect value class WGPUSamplerDescriptor(val handler: NativeAddress) {
	var label: CString?
	var addressModeU: WGPUAddressMode
	var addressModeV: WGPUAddressMode
	var addressModeW: WGPUAddressMode
	var magFilter: WGPUFilterMode
	var minFilter: WGPUFilterMode
	var mipmapFilter: WGPUMipmapFilterMode
	var lodMinClamp: Float
	var lodMaxClamp: Float
	var compare: WGPUCompareFunction
	var maxAnisotropy: UShort
}

expect value class WGPUShaderModuleDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPUShaderSourceSPIRV(val handler: NativeAddress) {
	var codeSize: UInt
	var code: NativeAddress?
}

expect value class WGPUShaderSourceWGSL(val handler: NativeAddress) {
	var code: CString?
}

expect value class WGPUStencilFaceState(val handler: NativeAddress) {
	var compare: WGPUCompareFunction
	var failOp: WGPUStencilOperation
	var depthFailOp: WGPUStencilOperation
	var passOp: WGPUStencilOperation
}

expect value class WGPUStorageTextureBindingLayout(val handler: NativeAddress) {
	var access: WGPUStorageTextureAccess
	var format: WGPUTextureFormat
	var viewDimension: WGPUTextureViewDimension
}

expect value class WGPUSupportedLimits(val handler: NativeAddress) {
	val limits: WGPULimits
}

expect value class WGPUSurfaceCapabilities(val handler: NativeAddress) {
	var usages: ULong
	var formatCount: ULong
	var formats: Long
	var presentModeCount: ULong
	var presentModes: Long
	var alphaModeCount: ULong
	var alphaModes: Long
}

expect value class WGPUSurfaceConfiguration(val handler: NativeAddress) {
	var device: WGPUDevice?
	var format: WGPUTextureFormat
	var usage: ULong
	var width: UInt
	var height: UInt
	var viewFormatCount: ULong
	var viewFormats: Long
	var alphaMode: WGPUCompositeAlphaMode
	var presentMode: WGPUPresentMode
}

expect value class WGPUSurfaceDescriptor(val handler: NativeAddress) {
	var label: CString?
}

expect value class WGPUSurfaceSourceAndroidNativeWindow(val handler: NativeAddress) {
	var window: NativeAddress?
}

expect value class WGPUSurfaceSourceMetalLayer(val handler: NativeAddress) {
	var layer: NativeAddress?
}

expect value class WGPUSurfaceSourceWaylandSurface(val handler: NativeAddress) {
	var display: NativeAddress?
	var surface: NativeAddress?
}

expect value class WGPUSurfaceSourceWindowsHWND(val handler: NativeAddress) {
	var hinstance: NativeAddress?
	var hwnd: NativeAddress?
}

expect value class WGPUSurfaceSourceXCBWindow(val handler: NativeAddress) {
	var connection: NativeAddress?
	var window: UInt
}

expect value class WGPUSurfaceSourceXlibWindow(val handler: NativeAddress) {
	var display: NativeAddress?
	var window: ULong
}

expect value class WGPUSurfaceTexture(val handler: NativeAddress) {
	var texture: WGPUTexture?
	var status: WGPUSurfaceGetCurrentTextureStatus
}

expect value class WGPUTextureBindingLayout(val handler: NativeAddress) {
	var sampleType: WGPUTextureSampleType
	var viewDimension: WGPUTextureViewDimension
	var multisampled: Boolean
}

expect value class WGPUTextureDataLayout(val handler: NativeAddress) {
	var offset: ULong
	var bytesPerRow: UInt
	var rowsPerImage: UInt
}

expect value class WGPUTextureDescriptor(val handler: NativeAddress) {
	var label: CString?
	var usage: ULong
	var dimension: WGPUTextureDimension
	val size: WGPUExtent3D
	var format: WGPUTextureFormat
	var mipLevelCount: UInt
	var sampleCount: UInt
	var viewFormatCount: ULong
	var viewFormats: Long
}

expect value class WGPUTextureViewDescriptor(val handler: NativeAddress) {
	var label: CString?
	var format: WGPUTextureFormat
	var dimension: WGPUTextureViewDimension
	var baseMipLevel: UInt
	var mipLevelCount: UInt
	var baseArrayLayer: UInt
	var arrayLayerCount: UInt
	var aspect: WGPUTextureAspect
	var usage: ULong
}

expect value class WGPUVertexAttribute(val handler: NativeAddress) {
	var format: WGPUVertexFormat
	var offset: ULong
	var shaderLocation: UInt
}

expect value class WGPUVertexBufferLayout(val handler: NativeAddress) {
	var arrayStride: ULong
	var stepMode: WGPUVertexStepMode
	var attributeCount: ULong
	var attributes: Long
}

expect value class WGPUVertexState(val handler: NativeAddress) {
	var module: WGPUShaderModule?
	var entryPoint: CString?
	var constantCount: ULong
	var constants: Long
	var bufferCount: ULong
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

expect value class WGPUBufferMapCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUBufferMapCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUCompilationInfoCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCompilationInfoCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUCreateComputePipelineAsyncCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUCreateRenderPipelineAsyncCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUDeviceLostCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUDeviceLostCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUPopErrorScopeCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUQueueWorkDoneCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPURequestAdapterCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPURequestAdapterCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPURequestDeviceCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPURequestDeviceCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

expect value class WGPUUncapturedErrorCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
}

