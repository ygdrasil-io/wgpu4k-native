// This file has been generated DO NOT EDIT !!!
package webgpu.android

import ffi.NativeAddress

internal sealed class WGPUStringView(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var data: com.sun.jna.Pointer? = null
	@JvmField var length: Long = 0L
	override fun getFieldOrder() = listOf("data", "length")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStringView(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStringView(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUAdapterInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStructOut.ByReference? = null
	@JvmField val vendor: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField val architecture: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField val device: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField val description: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var backendType: com.sun.jna.Pointer? = null
	@JvmField var adapterType: com.sun.jna.Pointer? = null
	@JvmField var vendorID: Int = 0
	@JvmField var deviceID: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "vendor", "architecture", "device", "description", "backendType", "adapterType", "vendorID", "deviceID")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUAdapterInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUAdapterInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBindGroupDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "entryCount", "entries")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBindGroupEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var binding: Int = 0
	@JvmField var buffer: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var sampler: com.sun.jna.Pointer? = null
	@JvmField var textureView: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "binding", "buffer", "offset", "size", "sampler", "textureView")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupEntry(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupEntry(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBindGroupLayoutDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "entryCount", "entries")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBufferBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var hasDynamicOffset: Int = 0
	@JvmField var minBindingSize: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "type", "hasDynamicOffset", "minBindingSize")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferBindingLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferBindingLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSamplerBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var type: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "type")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerBindingLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerBindingLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUTextureBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var sampleType: com.sun.jna.Pointer? = null
	@JvmField var viewDimension: com.sun.jna.Pointer? = null
	@JvmField var multisampled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "sampleType", "viewDimension", "multisampled")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureBindingLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureBindingLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUStorageTextureBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var access: com.sun.jna.Pointer? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var viewDimension: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "access", "format", "viewDimension")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStorageTextureBindingLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStorageTextureBindingLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBindGroupLayoutEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var binding: Int = 0
	@JvmField var visibility: Long = 0L
	@JvmField val buffer: WGPUBufferBindingLayout.ByValue = WGPUBufferBindingLayout.ByValue()
	@JvmField val sampler: WGPUSamplerBindingLayout.ByValue = WGPUSamplerBindingLayout.ByValue()
	@JvmField val texture: WGPUTextureBindingLayout.ByValue = WGPUTextureBindingLayout.ByValue()
	@JvmField val storageTexture: WGPUStorageTextureBindingLayout.ByValue = WGPUStorageTextureBindingLayout.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "binding", "visibility", "buffer", "sampler", "texture", "storageTexture")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutEntry(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutEntry(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBlendComponent(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var operation: com.sun.jna.Pointer? = null
	@JvmField var srcFactor: com.sun.jna.Pointer? = null
	@JvmField var dstFactor: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("operation", "srcFactor", "dstFactor")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBlendComponent(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBlendComponent(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBlendState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val color: WGPUBlendComponent.ByValue = WGPUBlendComponent.ByValue()
	@JvmField val alpha: WGPUBlendComponent.ByValue = WGPUBlendComponent.ByValue()
	override fun getFieldOrder() = listOf("color", "alpha")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBlendState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBlendState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBufferDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var usage: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var mappedAtCreation: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "size", "mappedAtCreation")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUColor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var r: Double = 0.0
	@JvmField var g: Double = 0.0
	@JvmField var b: Double = 0.0
	@JvmField var a: Double = 0.0
	override fun getFieldOrder() = listOf("r", "g", "b", "a")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUColor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUColor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUColorTargetState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var blend: WGPUBlendState.ByReference? = null
	@JvmField var writeMask: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "format", "blend", "writeMask")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUColorTargetState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUColorTargetState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCommandBufferDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCommandBufferDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCommandBufferDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCommandEncoderDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCommandEncoderDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCommandEncoderDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCompilationInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var messageCount: Long = 0L
	@JvmField var messages: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "messageCount", "messages")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCompilationMessage(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val message: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var lineNum: Long = 0L
	@JvmField var linePos: Long = 0L
	@JvmField var offset: Long = 0L
	@JvmField var length: Long = 0L
	@JvmField var utf16LinePos: Long = 0L
	@JvmField var utf16Offset: Long = 0L
	@JvmField var utf16Length: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "message", "type", "lineNum", "linePos", "offset", "length", "utf16LinePos", "utf16Offset", "utf16Length")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationMessage(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationMessage(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUComputePassDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var timestampWrites: WGPUComputePassTimestampWrites.ByReference? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "timestampWrites")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUComputePassTimestampWrites(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassTimestampWrites(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassTimestampWrites(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUProgrammableStageDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUProgrammableStageDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUProgrammableStageDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUComputePipelineDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField val compute: WGPUProgrammableStageDescriptor.ByValue = WGPUProgrammableStageDescriptor.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "compute")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePipelineDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePipelineDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUConstantEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val key: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var value: Double = 0.0
	override fun getFieldOrder() = listOf("nextInChain", "key", "value")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUConstantEntry(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUConstantEntry(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUStencilFaceState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var compare: com.sun.jna.Pointer? = null
	@JvmField var failOp: com.sun.jna.Pointer? = null
	@JvmField var depthFailOp: com.sun.jna.Pointer? = null
	@JvmField var passOp: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("compare", "failOp", "depthFailOp", "passOp")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStencilFaceState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStencilFaceState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUDepthStencilState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var depthWriteEnabled: com.sun.jna.Pointer? = null
	@JvmField var depthCompare: com.sun.jna.Pointer? = null
	@JvmField val stencilFront: WGPUStencilFaceState.ByValue = WGPUStencilFaceState.ByValue()
	@JvmField val stencilBack: WGPUStencilFaceState.ByValue = WGPUStencilFaceState.ByValue()
	@JvmField var stencilReadMask: Int = 0
	@JvmField var stencilWriteMask: Int = 0
	@JvmField var depthBias: Int = 0
	@JvmField var depthBiasSlopeScale: Float = 0f
	@JvmField var depthBiasClamp: Float = 0f
	override fun getFieldOrder() = listOf("nextInChain", "format", "depthWriteEnabled", "depthCompare", "stencilFront", "stencilBack", "stencilReadMask", "stencilWriteMask", "depthBias", "depthBiasSlopeScale", "depthBiasClamp")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDepthStencilState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDepthStencilState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUQueueDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQueueDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQueueDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUDeviceLostCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceLostCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceLostCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUUncapturedErrorCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUUncapturedErrorCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUUncapturedErrorCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUDeviceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var requiredFeatureCount: Long = 0L
	@JvmField var requiredFeatures: com.sun.jna.Pointer? = null
	@JvmField var requiredLimits: WGPURequiredLimits.ByReference? = null
	@JvmField val defaultQueue: WGPUQueueDescriptor.ByValue = WGPUQueueDescriptor.ByValue()
	@JvmField val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo.ByValue = WGPUDeviceLostCallbackInfo.ByValue()
	@JvmField val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo.ByValue = WGPUUncapturedErrorCallbackInfo.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label", "requiredFeatureCount", "requiredFeatures", "requiredLimits", "defaultQueue", "deviceLostCallbackInfo", "uncapturedErrorCallbackInfo")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUExtent3D(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var width: Int = 0
	@JvmField var height: Int = 0
	@JvmField var depthOrArrayLayers: Int = 0
	override fun getFieldOrder() = listOf("width", "height", "depthOrArrayLayers")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUExtent3D(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUExtent3D(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUFragmentState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var targetCount: Long = 0L
	@JvmField var targets: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "targetCount", "targets")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFragmentState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFragmentState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUFuture(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var id: Long = 0L
	override fun getFieldOrder() = listOf("id")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFuture(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFuture(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUFutureWaitInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val future: WGPUFuture.ByValue = WGPUFuture.ByValue()
	@JvmField var completed: Int = 0
	override fun getFieldOrder() = listOf("future", "completed")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFutureWaitInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFutureWaitInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUTextureDataLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var offset: Long = 0L
	@JvmField var bytesPerRow: Int = 0
	@JvmField var rowsPerImage: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "offset", "bytesPerRow", "rowsPerImage")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDataLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDataLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUImageCopyBuffer(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val layout: WGPUTextureDataLayout.ByValue = WGPUTextureDataLayout.ByValue()
	@JvmField var buffer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "layout", "buffer")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUImageCopyBuffer(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUImageCopyBuffer(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUOrigin3D(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var x: Int = 0
	@JvmField var y: Int = 0
	@JvmField var z: Int = 0
	override fun getFieldOrder() = listOf("x", "y", "z")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUOrigin3D(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUOrigin3D(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUImageCopyTexture(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var mipLevel: Int = 0
	@JvmField val origin: WGPUOrigin3D.ByValue = WGPUOrigin3D.ByValue()
	@JvmField var aspect: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "texture", "mipLevel", "origin", "aspect")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUImageCopyTexture(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUImageCopyTexture(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUInstanceFeatures(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var timedWaitAnyEnable: Int = 0
	@JvmField var timedWaitAnyMaxCount: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "timedWaitAnyEnable", "timedWaitAnyMaxCount")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceFeatures(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceFeatures(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUInstanceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val features: WGPUInstanceFeatures.ByValue = WGPUInstanceFeatures.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "features")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPULimits(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var maxTextureDimension1D: Int = 0
	@JvmField var maxTextureDimension2D: Int = 0
	@JvmField var maxTextureDimension3D: Int = 0
	@JvmField var maxTextureArrayLayers: Int = 0
	@JvmField var maxBindGroups: Int = 0
	@JvmField var maxBindGroupsPlusVertexBuffers: Int = 0
	@JvmField var maxBindingsPerBindGroup: Int = 0
	@JvmField var maxDynamicUniformBuffersPerPipelineLayout: Int = 0
	@JvmField var maxDynamicStorageBuffersPerPipelineLayout: Int = 0
	@JvmField var maxSampledTexturesPerShaderStage: Int = 0
	@JvmField var maxSamplersPerShaderStage: Int = 0
	@JvmField var maxStorageBuffersPerShaderStage: Int = 0
	@JvmField var maxStorageTexturesPerShaderStage: Int = 0
	@JvmField var maxUniformBuffersPerShaderStage: Int = 0
	@JvmField var maxUniformBufferBindingSize: Long = 0L
	@JvmField var maxStorageBufferBindingSize: Long = 0L
	@JvmField var minUniformBufferOffsetAlignment: Int = 0
	@JvmField var minStorageBufferOffsetAlignment: Int = 0
	@JvmField var maxVertexBuffers: Int = 0
	@JvmField var maxBufferSize: Long = 0L
	@JvmField var maxVertexAttributes: Int = 0
	@JvmField var maxVertexBufferArrayStride: Int = 0
	@JvmField var maxInterStageShaderVariables: Int = 0
	@JvmField var maxColorAttachments: Int = 0
	@JvmField var maxColorAttachmentBytesPerSample: Int = 0
	@JvmField var maxComputeWorkgroupStorageSize: Int = 0
	@JvmField var maxComputeInvocationsPerWorkgroup: Int = 0
	@JvmField var maxComputeWorkgroupSizeX: Int = 0
	@JvmField var maxComputeWorkgroupSizeY: Int = 0
	@JvmField var maxComputeWorkgroupSizeZ: Int = 0
	@JvmField var maxComputeWorkgroupsPerDimension: Int = 0
	override fun getFieldOrder() = listOf("maxTextureDimension1D", "maxTextureDimension2D", "maxTextureDimension3D", "maxTextureArrayLayers", "maxBindGroups", "maxBindGroupsPlusVertexBuffers", "maxBindingsPerBindGroup", "maxDynamicUniformBuffersPerPipelineLayout", "maxDynamicStorageBuffersPerPipelineLayout", "maxSampledTexturesPerShaderStage", "maxSamplersPerShaderStage", "maxStorageBuffersPerShaderStage", "maxStorageTexturesPerShaderStage", "maxUniformBuffersPerShaderStage", "maxUniformBufferBindingSize", "maxStorageBufferBindingSize", "minUniformBufferOffsetAlignment", "minStorageBufferOffsetAlignment", "maxVertexBuffers", "maxBufferSize", "maxVertexAttributes", "maxVertexBufferArrayStride", "maxInterStageShaderVariables", "maxColorAttachments", "maxColorAttachmentBytesPerSample", "maxComputeWorkgroupStorageSize", "maxComputeInvocationsPerWorkgroup", "maxComputeWorkgroupSizeX", "maxComputeWorkgroupSizeY", "maxComputeWorkgroupSizeZ", "maxComputeWorkgroupsPerDimension")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPULimits(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPULimits(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUMultisampleState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var count: Int = 0
	@JvmField var mask: Int = 0
	@JvmField var alphaToCoverageEnabled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "count", "mask", "alphaToCoverageEnabled")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUMultisampleState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUMultisampleState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUPipelineLayoutDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var bindGroupLayoutCount: Long = 0L
	@JvmField var bindGroupLayouts: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "bindGroupLayoutCount", "bindGroupLayouts")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPipelineLayoutDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPipelineLayoutDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUPrimitiveState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var topology: com.sun.jna.Pointer? = null
	@JvmField var stripIndexFormat: com.sun.jna.Pointer? = null
	@JvmField var frontFace: com.sun.jna.Pointer? = null
	@JvmField var cullMode: com.sun.jna.Pointer? = null
	@JvmField var unclippedDepth: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "topology", "stripIndexFormat", "frontFace", "cullMode", "unclippedDepth")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPrimitiveState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPrimitiveState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUQuerySetDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var count: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "type", "count")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQuerySetDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQuerySetDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderBundleDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderBundleEncoderDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var colorFormatCount: Long = 0L
	@JvmField var colorFormats: com.sun.jna.Pointer? = null
	@JvmField var depthStencilFormat: com.sun.jna.Pointer? = null
	@JvmField var sampleCount: Int = 0
	@JvmField var depthReadOnly: Int = 0
	@JvmField var stencilReadOnly: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorFormatCount", "colorFormats", "depthStencilFormat", "sampleCount", "depthReadOnly", "stencilReadOnly")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleEncoderDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleEncoderDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPassColorAttachment(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var view: com.sun.jna.Pointer? = null
	@JvmField var depthSlice: Int = 0
	@JvmField var resolveTarget: com.sun.jna.Pointer? = null
	@JvmField var loadOp: com.sun.jna.Pointer? = null
	@JvmField var storeOp: com.sun.jna.Pointer? = null
	@JvmField val clearValue: WGPUColor.ByValue = WGPUColor.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "view", "depthSlice", "resolveTarget", "loadOp", "storeOp", "clearValue")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassColorAttachment(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassColorAttachment(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPassDepthStencilAttachment(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var view: com.sun.jna.Pointer? = null
	@JvmField var depthLoadOp: com.sun.jna.Pointer? = null
	@JvmField var depthStoreOp: com.sun.jna.Pointer? = null
	@JvmField var depthClearValue: Float = 0f
	@JvmField var depthReadOnly: Int = 0
	@JvmField var stencilLoadOp: com.sun.jna.Pointer? = null
	@JvmField var stencilStoreOp: com.sun.jna.Pointer? = null
	@JvmField var stencilClearValue: Int = 0
	@JvmField var stencilReadOnly: Int = 0
	override fun getFieldOrder() = listOf("view", "depthLoadOp", "depthStoreOp", "depthClearValue", "depthReadOnly", "stencilLoadOp", "stencilStoreOp", "stencilClearValue", "stencilReadOnly")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDepthStencilAttachment(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDepthStencilAttachment(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPassDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var colorAttachmentCount: Long = 0L
	@JvmField var colorAttachments: com.sun.jna.Pointer? = null
	@JvmField var depthStencilAttachment: WGPURenderPassDepthStencilAttachment.ByReference? = null
	@JvmField var occlusionQuerySet: com.sun.jna.Pointer? = null
	@JvmField var timestampWrites: WGPURenderPassTimestampWrites.ByReference? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorAttachmentCount", "colorAttachments", "depthStencilAttachment", "occlusionQuerySet", "timestampWrites")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUChainedStruct(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var next: WGPUChainedStruct.ByReference? = null
	@JvmField var sType: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("next", "sType")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStruct(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStruct(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPassMaxDrawCount(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var maxDrawCount: Long = 0L
	override fun getFieldOrder() = listOf("chain", "maxDrawCount")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassMaxDrawCount(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassMaxDrawCount(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPassTimestampWrites(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassTimestampWrites(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassTimestampWrites(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUVertexState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var bufferCount: Long = 0L
	@JvmField var buffers: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "bufferCount", "buffers")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexState(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexState(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURenderPipelineDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField val vertex: WGPUVertexState.ByValue = WGPUVertexState.ByValue()
	@JvmField val primitive: WGPUPrimitiveState.ByValue = WGPUPrimitiveState.ByValue()
	@JvmField var depthStencil: WGPUDepthStencilState.ByReference? = null
	@JvmField val multisample: WGPUMultisampleState.ByValue = WGPUMultisampleState.ByValue()
	@JvmField var fragment: WGPUFragmentState.ByReference? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "vertex", "primitive", "depthStencil", "multisample", "fragment")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPipelineDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPipelineDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURequestAdapterOptions(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var compatibleSurface: com.sun.jna.Pointer? = null
	@JvmField var powerPreference: com.sun.jna.Pointer? = null
	@JvmField var backendType: com.sun.jna.Pointer? = null
	@JvmField var forceFallbackAdapter: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "compatibleSurface", "powerPreference", "backendType", "forceFallbackAdapter")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterOptions(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterOptions(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURequiredLimits(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val limits: WGPULimits.ByValue = WGPULimits.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "limits")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequiredLimits(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequiredLimits(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSamplerDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var addressModeU: com.sun.jna.Pointer? = null
	@JvmField var addressModeV: com.sun.jna.Pointer? = null
	@JvmField var addressModeW: com.sun.jna.Pointer? = null
	@JvmField var magFilter: com.sun.jna.Pointer? = null
	@JvmField var minFilter: com.sun.jna.Pointer? = null
	@JvmField var mipmapFilter: com.sun.jna.Pointer? = null
	@JvmField var lodMinClamp: Float = 0f
	@JvmField var lodMaxClamp: Float = 0f
	@JvmField var compare: com.sun.jna.Pointer? = null
	@JvmField var maxAnisotropy: Short = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "addressModeU", "addressModeV", "addressModeW", "magFilter", "minFilter", "mipmapFilter", "lodMinClamp", "lodMaxClamp", "compare", "maxAnisotropy")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUShaderModuleDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderModuleDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderModuleDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUShaderSourceSPIRV(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var codeSize: Int = 0
	@JvmField var code: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "codeSize", "code")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceSPIRV(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceSPIRV(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUShaderSourceWGSL(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField val code: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("chain", "code")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceWGSL(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceWGSL(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSupportedFeatures(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStructOut.ByReference? = null
	@JvmField var featureCount: Long = 0L
	@JvmField var features: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "featureCount", "features")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedFeatures(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedFeatures(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSupportedLimits(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStructOut.ByReference? = null
	@JvmField val limits: WGPULimits.ByValue = WGPULimits.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "limits")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedLimits(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedLimits(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceCapabilities(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStructOut.ByReference? = null
	@JvmField var usages: Long = 0L
	@JvmField var formatCount: Long = 0L
	@JvmField var formats: com.sun.jna.Pointer? = null
	@JvmField var presentModeCount: Long = 0L
	@JvmField var presentModes: com.sun.jna.Pointer? = null
	@JvmField var alphaModeCount: Long = 0L
	@JvmField var alphaModes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "usages", "formatCount", "formats", "presentModeCount", "presentModes", "alphaModeCount", "alphaModes")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceCapabilities(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceCapabilities(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceConfiguration(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var device: com.sun.jna.Pointer? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var usage: Long = 0L
	@JvmField var width: Int = 0
	@JvmField var height: Int = 0
	@JvmField var viewFormatCount: Long = 0L
	@JvmField var viewFormats: com.sun.jna.Pointer? = null
	@JvmField var alphaMode: com.sun.jna.Pointer? = null
	@JvmField var presentMode: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "device", "format", "usage", "width", "height", "viewFormatCount", "viewFormats", "alphaMode", "presentMode")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceConfiguration(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceConfiguration(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceAndroidNativeWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var window: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "window")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceAndroidNativeWindow(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceAndroidNativeWindow(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceMetalLayer(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var layer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "layer")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceMetalLayer(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceMetalLayer(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceWaylandSurface(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var surface: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "display", "surface")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWaylandSurface(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWaylandSurface(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceWindowsHWND(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var hinstance: com.sun.jna.Pointer? = null
	@JvmField var hwnd: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "hinstance", "hwnd")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWindowsHWND(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWindowsHWND(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceXCBWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var connection: com.sun.jna.Pointer? = null
	@JvmField var window: Int = 0
	override fun getFieldOrder() = listOf("chain", "connection", "window")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXCBWindow(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXCBWindow(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceSourceXlibWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField val chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var window: Long = 0L
	override fun getFieldOrder() = listOf("chain", "display", "window")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXlibWindow(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXlibWindow(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUSurfaceTexture(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var status: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("texture", "status")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceTexture(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceTexture(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUTextureDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var usage: Long = 0L
	@JvmField var dimension: com.sun.jna.Pointer? = null
	@JvmField val size: WGPUExtent3D.ByValue = WGPUExtent3D.ByValue()
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var mipLevelCount: Int = 0
	@JvmField var sampleCount: Int = 0
	@JvmField var viewFormatCount: Long = 0L
	@JvmField var viewFormats: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "dimension", "size", "format", "mipLevelCount", "sampleCount", "viewFormatCount", "viewFormats")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUTextureViewDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField val label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var dimension: com.sun.jna.Pointer? = null
	@JvmField var baseMipLevel: Int = 0
	@JvmField var mipLevelCount: Int = 0
	@JvmField var baseArrayLayer: Int = 0
	@JvmField var arrayLayerCount: Int = 0
	@JvmField var aspect: com.sun.jna.Pointer? = null
	@JvmField var usage: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "label", "format", "dimension", "baseMipLevel", "mipLevelCount", "baseArrayLayer", "arrayLayerCount", "aspect", "usage")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureViewDescriptor(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureViewDescriptor(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUVertexAttribute(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var shaderLocation: Int = 0
	override fun getFieldOrder() = listOf("format", "offset", "shaderLocation")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexAttribute(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexAttribute(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUVertexBufferLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var arrayStride: Long = 0L
	@JvmField var stepMode: com.sun.jna.Pointer? = null
	@JvmField var attributeCount: Long = 0L
	@JvmField var attributes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("arrayStride", "stepMode", "attributeCount", "attributes")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexBufferLayout(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexBufferLayout(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUChainedStructOut(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var next: WGPUChainedStructOut.ByReference? = null
	@JvmField var sType: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("next", "sType")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStructOut(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStructOut(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUBufferMapCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferMapCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferMapCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCompilationInfoCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfoCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfoCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCreateComputePipelineAsyncCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCreateComputePipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCreateComputePipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUCreateRenderPipelineAsyncCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCreateRenderPipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCreateRenderPipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUPopErrorScopeCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPopErrorScopeCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPopErrorScopeCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPUQueueWorkDoneCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQueueWorkDoneCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQueueWorkDoneCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURequestAdapterCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
internal sealed class WGPURequestDeviceCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")

	internal class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestDeviceCallbackInfo(pointer), com.sun.jna.Structure.ByReference
	internal class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestDeviceCallbackInfo(pointer), com.sun.jna.Structure.ByValue
}
