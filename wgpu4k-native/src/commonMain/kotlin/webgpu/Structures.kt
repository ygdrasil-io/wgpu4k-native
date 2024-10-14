// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.CString
import ffi.ArrayHolder
import ffi.MemoryAllocator

expect value class WGPUStringView(val handler: NativeAddress) {
	var data: CString?
	var length: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUStringView
	}
}

expect value class WGPUAdapterInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStructOut?
	val vendor: WGPUStringView
	val architecture: WGPUStringView
	val device: WGPUStringView
	val description: WGPUStringView
	var backendType: WGPUBackendType
	var adapterType: WGPUAdapterType
	var vendorID: UInt
	var deviceID: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo
	}
}

expect value class WGPUBindGroupDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var layout: WGPUBindGroupLayout?
	var entryCount: ULong
	var entries: ArrayHolder<WGPUBindGroupEntry>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor
	}
}

expect value class WGPUBindGroupEntry(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var binding: UInt
	var buffer: WGPUBuffer?
	var offset: ULong
	var size: ULong
	var sampler: WGPUSampler?
	var textureView: WGPUTextureView?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry
	}
}

expect value class WGPUBindGroupLayoutDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var entryCount: ULong
	var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor
	}
}

expect value class WGPUBufferBindingLayout(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var type: WGPUBufferBindingType
	var hasDynamicOffset: Boolean
	var minBindingSize: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout
	}
}

expect value class WGPUSamplerBindingLayout(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var type: WGPUSamplerBindingType
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout
	}
}

expect value class WGPUTextureBindingLayout(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var sampleType: WGPUTextureSampleType
	var viewDimension: WGPUTextureViewDimension
	var multisampled: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout
	}
}

expect value class WGPUStorageTextureBindingLayout(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var access: WGPUStorageTextureAccess
	var format: WGPUTextureFormat
	var viewDimension: WGPUTextureViewDimension
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout
	}
}

expect value class WGPUBindGroupLayoutEntry(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var binding: UInt
	var visibility: ULong
	val buffer: WGPUBufferBindingLayout
	val sampler: WGPUSamplerBindingLayout
	val texture: WGPUTextureBindingLayout
	val storageTexture: WGPUStorageTextureBindingLayout
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry
	}
}

expect value class WGPUBlendComponent(val handler: NativeAddress) {
	var operation: WGPUBlendOperation
	var srcFactor: WGPUBlendFactor
	var dstFactor: WGPUBlendFactor
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBlendComponent
	}
}

expect value class WGPUBlendState(val handler: NativeAddress) {
	val color: WGPUBlendComponent
	val alpha: WGPUBlendComponent
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBlendState
	}
}

expect value class WGPUBufferDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var usage: ULong
	var size: ULong
	var mappedAtCreation: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor
	}
}

expect value class WGPUColor(val handler: NativeAddress) {
	var r: Double
	var g: Double
	var b: Double
	var a: Double
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUColor
	}
}

expect value class WGPUColorTargetState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var format: WGPUTextureFormat
	var blend: WGPUBlendState?
	var writeMask: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUColorTargetState
	}
}

expect value class WGPUCommandBufferDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor
	}
}

expect value class WGPUCommandEncoderDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor
	}
}

expect value class WGPUCompilationInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var messageCount: ULong
	var messages: ArrayHolder<WGPUCompilationMessage>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo
	}
}

expect value class WGPUCompilationMessage(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val message: WGPUStringView
	var type: WGPUCompilationMessageType
	var lineNum: ULong
	var linePos: ULong
	var offset: ULong
	var length: ULong
	var utf16LinePos: ULong
	var utf16Offset: ULong
	var utf16Length: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage
	}
}

expect value class WGPUComputePassDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var timestampWrites: WGPUComputePassTimestampWrites?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor
	}
}

expect value class WGPUComputePassTimestampWrites(val handler: NativeAddress) {
	var querySet: WGPUQuerySet?
	var beginningOfPassWriteIndex: UInt
	var endOfPassWriteIndex: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUComputePassTimestampWrites
	}
}

expect value class WGPUProgrammableStageDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var module: WGPUShaderModule?
	val entryPoint: WGPUStringView
	var constantCount: ULong
	var constants: ArrayHolder<WGPUConstantEntry>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUProgrammableStageDescriptor
	}
}

expect value class WGPUComputePipelineDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var layout: WGPUPipelineLayout?
	val compute: WGPUProgrammableStageDescriptor
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor
	}
}

expect value class WGPUConstantEntry(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val key: WGPUStringView
	var value: Double
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUConstantEntry
	}
}

expect value class WGPUStencilFaceState(val handler: NativeAddress) {
	var compare: WGPUCompareFunction
	var failOp: WGPUStencilOperation
	var depthFailOp: WGPUStencilOperation
	var passOp: WGPUStencilOperation
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState
	}
}

expect value class WGPUDepthStencilState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
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
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState
	}
}

expect value class WGPUQueueDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor
	}
}

expect value class WGPUDeviceLostCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUDeviceLostCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo
	}
}

expect value class WGPUUncapturedErrorCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo
	}
}

expect value class WGPUDeviceDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var requiredFeatureCount: ULong
	var requiredFeatures: ArrayHolder<WGPUFeatureName>?
	var requiredLimits: WGPURequiredLimits?
	val defaultQueue: WGPUQueueDescriptor
	val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
	val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor
	}
}

expect value class WGPUExtent3D(val handler: NativeAddress) {
	var width: UInt
	var height: UInt
	var depthOrArrayLayers: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUExtent3D
	}
}

expect value class WGPUFragmentState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var module: WGPUShaderModule?
	val entryPoint: WGPUStringView
	var constantCount: ULong
	var constants: ArrayHolder<WGPUConstantEntry>?
	var targetCount: ULong
	var targets: ArrayHolder<WGPUColorTargetState>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUFragmentState
	}
}

expect value class WGPUFuture(val handler: NativeAddress) {
	var id: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUFuture
	}
}

expect value class WGPUFutureWaitInfo(val handler: NativeAddress) {
	val future: WGPUFuture
	var completed: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo
	}
}

expect value class WGPUTextureDataLayout(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var offset: ULong
	var bytesPerRow: UInt
	var rowsPerImage: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUTextureDataLayout
	}
}

expect value class WGPUImageCopyBuffer(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val layout: WGPUTextureDataLayout
	var buffer: WGPUBuffer?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUImageCopyBuffer
	}
}

expect value class WGPUOrigin3D(val handler: NativeAddress) {
	var x: UInt
	var y: UInt
	var z: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUOrigin3D
	}
}

expect value class WGPUImageCopyTexture(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var texture: WGPUTexture?
	var mipLevel: UInt
	val origin: WGPUOrigin3D
	var aspect: WGPUTextureAspect
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUImageCopyTexture
	}
}

expect value class WGPUInstanceFeatures(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var timedWaitAnyEnable: Boolean
	var timedWaitAnyMaxCount: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUInstanceFeatures
	}
}

expect value class WGPUInstanceDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val features: WGPUInstanceFeatures
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor
	}
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
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPULimits
	}
}

expect value class WGPUMultisampleState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var count: UInt
	var mask: UInt
	var alphaToCoverageEnabled: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUMultisampleState
	}
}

expect value class WGPUPipelineLayoutDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var bindGroupLayoutCount: ULong
	var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor
	}
}

expect value class WGPUPrimitiveState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var topology: WGPUPrimitiveTopology
	var stripIndexFormat: WGPUIndexFormat
	var frontFace: WGPUFrontFace
	var cullMode: WGPUCullMode
	var unclippedDepth: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState
	}
}

expect value class WGPUQuerySetDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var type: WGPUQueryType
	var count: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor
	}
}

expect value class WGPURenderBundleDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor
	}
}

expect value class WGPURenderBundleEncoderDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var colorFormatCount: ULong
	var colorFormats: ArrayHolder<WGPUTextureFormat>?
	var depthStencilFormat: WGPUTextureFormat
	var sampleCount: UInt
	var depthReadOnly: Boolean
	var stencilReadOnly: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor
	}
}

expect value class WGPURenderPassColorAttachment(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var view: WGPUTextureView?
	var depthSlice: UInt
	var resolveTarget: WGPUTextureView?
	var loadOp: WGPULoadOp
	var storeOp: WGPUStoreOp
	val clearValue: WGPUColor
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment
	}
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
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment
	}
}

expect value class WGPURenderPassDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var colorAttachmentCount: ULong
	var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
	var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
	var occlusionQuerySet: WGPUQuerySet?
	var timestampWrites: WGPURenderPassTimestampWrites?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor
	}
}

expect value class WGPUChainedStruct(val handler: NativeAddress) {
	var next: WGPUChainedStruct?
	var sType: WGPUSType
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUChainedStruct
	}
}

expect value class WGPURenderPassMaxDrawCount(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var maxDrawCount: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount
	}
}

expect value class WGPURenderPassTimestampWrites(val handler: NativeAddress) {
	var querySet: WGPUQuerySet?
	var beginningOfPassWriteIndex: UInt
	var endOfPassWriteIndex: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPassTimestampWrites
	}
}

expect value class WGPUVertexState(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var module: WGPUShaderModule?
	val entryPoint: WGPUStringView
	var constantCount: ULong
	var constants: ArrayHolder<WGPUConstantEntry>?
	var bufferCount: ULong
	var buffers: ArrayHolder<WGPUVertexBufferLayout>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUVertexState
	}
}

expect value class WGPURenderPipelineDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var layout: WGPUPipelineLayout?
	val vertex: WGPUVertexState
	val primitive: WGPUPrimitiveState
	var depthStencil: WGPUDepthStencilState?
	val multisample: WGPUMultisampleState
	var fragment: WGPUFragmentState?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor
	}
}

expect value class WGPURequestAdapterOptions(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var compatibleSurface: WGPUSurface?
	var powerPreference: WGPUPowerPreference
	var backendType: WGPUBackendType
	var forceFallbackAdapter: Boolean
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions
	}
}

expect value class WGPURequiredLimits(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val limits: WGPULimits
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURequiredLimits
	}
}

expect value class WGPUSamplerDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
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
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor
	}
}

expect value class WGPUShaderModuleDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor
	}
}

expect value class WGPUShaderSourceSPIRV(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var codeSize: UInt
	var code: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV
	}
}

expect value class WGPUShaderSourceWGSL(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	val code: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL
	}
}

expect value class WGPUSupportedFeatures(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStructOut?
	var featureCount: ULong
	var features: ArrayHolder<WGPUFeatureName>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures
	}
}

expect value class WGPUSupportedLimits(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStructOut?
	val limits: WGPULimits
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits
	}
}

expect value class WGPUSurfaceCapabilities(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStructOut?
	var usages: ULong
	var formatCount: ULong
	var formats: ArrayHolder<WGPUTextureFormat>?
	var presentModeCount: ULong
	var presentModes: ArrayHolder<WGPUPresentMode>?
	var alphaModeCount: ULong
	var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities
	}
}

expect value class WGPUSurfaceConfiguration(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var device: WGPUDevice?
	var format: WGPUTextureFormat
	var usage: ULong
	var width: UInt
	var height: UInt
	var viewFormatCount: ULong
	var viewFormats: ArrayHolder<WGPUTextureFormat>?
	var alphaMode: WGPUCompositeAlphaMode
	var presentMode: WGPUPresentMode
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration
	}
}

expect value class WGPUSurfaceDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor
	}
}

expect value class WGPUSurfaceSourceAndroidNativeWindow(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var window: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow
	}
}

expect value class WGPUSurfaceSourceMetalLayer(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var layer: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer
	}
}

expect value class WGPUSurfaceSourceWaylandSurface(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var display: NativeAddress?
	var surface: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface
	}
}

expect value class WGPUSurfaceSourceWindowsHWND(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var hinstance: NativeAddress?
	var hwnd: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND
	}
}

expect value class WGPUSurfaceSourceXCBWindow(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var connection: NativeAddress?
	var window: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow
	}
}

expect value class WGPUSurfaceSourceXlibWindow(val handler: NativeAddress) {
	val chain: WGPUChainedStruct
	var display: NativeAddress?
	var window: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow
	}
}

expect value class WGPUSurfaceTexture(val handler: NativeAddress) {
	var texture: WGPUTexture?
	var status: WGPUSurfaceGetCurrentTextureStatus
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture
	}
}

expect value class WGPUTextureDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var usage: ULong
	var dimension: WGPUTextureDimension
	val size: WGPUExtent3D
	var format: WGPUTextureFormat
	var mipLevelCount: UInt
	var sampleCount: UInt
	var viewFormatCount: ULong
	var viewFormats: ArrayHolder<WGPUTextureFormat>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor
	}
}

expect value class WGPUTextureViewDescriptor(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	val label: WGPUStringView
	var format: WGPUTextureFormat
	var dimension: WGPUTextureViewDimension
	var baseMipLevel: UInt
	var mipLevelCount: UInt
	var baseArrayLayer: UInt
	var arrayLayerCount: UInt
	var aspect: WGPUTextureAspect
	var usage: ULong
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor
	}
}

expect value class WGPUVertexAttribute(val handler: NativeAddress) {
	var format: WGPUVertexFormat
	var offset: ULong
	var shaderLocation: UInt
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute
	}
}

expect value class WGPUVertexBufferLayout(val handler: NativeAddress) {
	var arrayStride: ULong
	var stepMode: WGPUVertexStepMode
	var attributeCount: ULong
	var attributes: ArrayHolder<WGPUVertexAttribute>?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout
	}
}

expect value class WGPUChainedStructOut(val handler: NativeAddress) {
	var next: WGPUChainedStructOut?
	var sType: WGPUSType
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut
	}
}

expect value class WGPUBufferMapCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUBufferMapCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo
	}
}

expect value class WGPUCompilationInfoCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCompilationInfoCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo
	}
}

expect value class WGPUCreateComputePipelineAsyncCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo
	}
}

expect value class WGPUCreateRenderPipelineAsyncCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo
	}
}

expect value class WGPUPopErrorScopeCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo
	}
}

expect value class WGPUQueueWorkDoneCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo
	}
}

expect value class WGPURequestAdapterCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPURequestAdapterCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo
	}
}

expect value class WGPURequestDeviceCallbackInfo(val handler: NativeAddress) {
	var nextInChain: WGPUChainedStruct?
	var callback: CallbackHolder<WGPURequestDeviceCallback>?
	var userdata1: NativeAddress?
	var userdata2: NativeAddress?
	companion object {
		fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo
	}
}

