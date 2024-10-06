// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.CString
import ffi.ArrayHolder
import ffi.C_LONG
import ffi.C_POINTER
import ffi.C_SHORT
import ffi.C_INT
import ffi.C_FLOAT
import ffi.C_DOUBLE
import ffi.CStructure
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout.structLayout

@JvmInline
actual value class WGPUAdapterInfo(actual override val handler: NativeAddress) : CStructure {
	actual var vendor: CString?
		get() = TODO()
		set(newValue) = set("vendor", vendorOffset, newValue?.handler)

	actual var architecture: CString?
		get() = TODO()
		set(newValue) = set("architecture", architectureOffset, newValue?.handler)

	actual var device: CString?
		get() = TODO()
		set(newValue) = set("device", deviceOffset, newValue?.handler)

	actual var description: CString?
		get() = TODO()
		set(newValue) = set("description", descriptionOffset, newValue?.handler)

	actual var backendType: WGPUBackendType
		get() = TODO()
		set(newValue) = set("backendType", backendTypeOffset, newValue)

	actual var adapterType: WGPUAdapterType
		get() = TODO()
		set(newValue) = set("adapterType", adapterTypeOffset, newValue)

	actual var vendorID: UInt
		get() = getUInt("vendorID", vendorIDOffset)
		set(newValue) = set("vendorID", vendorIDOffset, newValue)

	actual var deviceID: UInt
		get() = getUInt("deviceID", deviceIDOffset)
		set(newValue) = set("deviceID", deviceIDOffset, newValue)

	override fun getLayout(name: String)
		= WGPUAdapterInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("vendor"),
			C_POINTER.withName("architecture"),
			C_POINTER.withName("device"),
			C_POINTER.withName("description"),
			C_INT.withName("backendType"),
			C_INT.withName("adapterType"),
			C_INT.withName("vendorID"),
			C_INT.withName("deviceID")
		).withName("WGPUAdapterInfo")

		val vendorOffset = 0L
		val architectureOffset = 8L + vendorOffset
		val deviceOffset = 8L + architectureOffset
		val descriptionOffset = 8L + deviceOffset
		val backendTypeOffset = 8L + descriptionOffset
		val adapterTypeOffset = 4L + backendTypeOffset
		val vendorIDOffset = 4L + adapterTypeOffset
		val deviceIDOffset = 4L + vendorIDOffset
	}
}

@JvmInline
actual value class WGPUBindGroupDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var layout: WGPUBindGroupLayout?
		get() = TODO()
		set(newValue) = set("layout", layoutOffset, newValue?.handler)

	actual var entryCount: ULong
		get() = getULong("entryCount", entryCountOffset)
		set(newValue) = set("entryCount", entryCountOffset, newValue)

	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
		get() = TODO()
		set(newValue) = set("entries", entriesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUBindGroupDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupDescriptor")

		val labelOffset = 0L
		val layoutOffset = 8L + labelOffset
		val entryCountOffset = 8L + layoutOffset
		val entriesOffset = 8L + entryCountOffset
	}
}

@JvmInline
actual value class WGPUBindGroupEntry(actual override val handler: NativeAddress) : CStructure {
	actual var binding: UInt
		get() = getUInt("binding", bindingOffset)
		set(newValue) = set("binding", bindingOffset, newValue)

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = set("buffer", bufferOffset, newValue?.handler)

	actual var offset: ULong
		get() = getULong("offset", offsetOffset)
		set(newValue) = set("offset", offsetOffset, newValue)

	actual var size: ULong
		get() = getULong("size", sizeOffset)
		set(newValue) = set("size", sizeOffset, newValue)

	actual var sampler: WGPUSampler?
		get() = TODO()
		set(newValue) = set("sampler", samplerOffset, newValue?.handler)

	actual var textureView: WGPUTextureView?
		get() = TODO()
		set(newValue) = set("textureView", textureViewOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUBindGroupEntry.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("binding"),
			C_POINTER.withName("buffer"),
			C_LONG.withName("offset"),
			C_LONG.withName("size"),
			C_POINTER.withName("sampler"),
			C_POINTER.withName("textureView")
		).withName("WGPUBindGroupEntry")

		val bindingOffset = 0L
		val bufferOffset = 4L + bindingOffset
		val offsetOffset = 8L + bufferOffset
		val sizeOffset = 8L + offsetOffset
		val samplerOffset = 8L + sizeOffset
		val textureViewOffset = 8L + samplerOffset
	}
}

@JvmInline
actual value class WGPUBindGroupLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var entryCount: ULong
		get() = getULong("entryCount", entryCountOffset)
		set(newValue) = set("entryCount", entryCountOffset, newValue)

	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
		get() = TODO()
		set(newValue) = set("entries", entriesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUBindGroupLayoutDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupLayoutDescriptor")

		val labelOffset = 0L
		val entryCountOffset = 8L + labelOffset
		val entriesOffset = 8L + entryCountOffset
	}
}

@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual override val handler: NativeAddress) : CStructure {
	actual var binding: UInt
		get() = getUInt("binding", bindingOffset)
		set(newValue) = set("binding", bindingOffset, newValue)

	actual var visibility: ULong
		get() = getULong("visibility", visibilityOffset)
		set(newValue) = set("visibility", visibilityOffset, newValue)

	actual val buffer: WGPUBufferBindingLayout
		get() = get("buffer", bufferOffset).let(::WGPUBufferBindingLayout)

	actual val sampler: WGPUSamplerBindingLayout
		get() = get("sampler", samplerOffset).let(::WGPUSamplerBindingLayout)

	actual val texture: WGPUTextureBindingLayout
		get() = get("texture", textureOffset).let(::WGPUTextureBindingLayout)

	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = get("storageTexture", storageTextureOffset).let(::WGPUStorageTextureBindingLayout)

	override fun getLayout(name: String)
		= WGPUBindGroupLayoutEntry.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("binding"),
			C_LONG.withName("visibility"),
			WGPUBufferBindingLayout.LAYOUT.withName("buffer"),
			WGPUSamplerBindingLayout.LAYOUT.withName("sampler"),
			WGPUTextureBindingLayout.LAYOUT.withName("texture"),
			WGPUStorageTextureBindingLayout.LAYOUT.withName("storageTexture")
		).withName("WGPUBindGroupLayoutEntry")

		val bindingOffset = 0L
		val visibilityOffset = 4L + bindingOffset
		val bufferOffset = 8L + visibilityOffset
		val samplerOffset = LAYOUT.withName("buffer").byteSize() + bufferOffset
		val textureOffset = LAYOUT.withName("sampler").byteSize() + samplerOffset
		val storageTextureOffset = LAYOUT.withName("texture").byteSize() + textureOffset
	}
}

@JvmInline
actual value class WGPUBlendComponent(actual override val handler: NativeAddress) : CStructure {
	actual var operation: WGPUBlendOperation
		get() = TODO()
		set(newValue) = set("operation", operationOffset, newValue)

	actual var srcFactor: WGPUBlendFactor
		get() = TODO()
		set(newValue) = set("srcFactor", srcFactorOffset, newValue)

	actual var dstFactor: WGPUBlendFactor
		get() = TODO()
		set(newValue) = set("dstFactor", dstFactorOffset, newValue)

	override fun getLayout(name: String)
		= WGPUBlendComponent.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("operation"),
			C_INT.withName("srcFactor"),
			C_INT.withName("dstFactor")
		).withName("WGPUBlendComponent")

		val operationOffset = 0L
		val srcFactorOffset = 4L + operationOffset
		val dstFactorOffset = 4L + srcFactorOffset
	}
}

@JvmInline
actual value class WGPUBlendState(actual override val handler: NativeAddress) : CStructure {
	actual val color: WGPUBlendComponent
		get() = get("color", colorOffset).let(::WGPUBlendComponent)

	actual val alpha: WGPUBlendComponent
		get() = get("alpha", alphaOffset).let(::WGPUBlendComponent)

	override fun getLayout(name: String)
		= WGPUBlendState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPUBlendComponent.LAYOUT.withName("color"),
			WGPUBlendComponent.LAYOUT.withName("alpha")
		).withName("WGPUBlendState")

		val colorOffset = 0L
		val alphaOffset = LAYOUT.withName("color").byteSize() + colorOffset
	}
}

@JvmInline
actual value class WGPUBufferBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var type: WGPUBufferBindingType
		get() = TODO()
		set(newValue) = set("type", typeOffset, newValue)

	actual var hasDynamicOffset: Boolean
		get() = getInt("hasDynamicOffset", hasDynamicOffsetOffset).toBoolean()
		set(newValue) = set("hasDynamicOffset", hasDynamicOffsetOffset, newValue)

	actual var minBindingSize: ULong
		get() = getULong("minBindingSize", minBindingSizeOffset)
		set(newValue) = set("minBindingSize", minBindingSizeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUBufferBindingLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("type"),
			C_INT.withName("hasDynamicOffset"),
			C_LONG.withName("minBindingSize")
		).withName("WGPUBufferBindingLayout")

		val typeOffset = 0L
		val hasDynamicOffsetOffset = 4L + typeOffset
		val minBindingSizeOffset = 4L + hasDynamicOffsetOffset
	}
}

@JvmInline
actual value class WGPUBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var usage: ULong
		get() = getULong("usage", usageOffset)
		set(newValue) = set("usage", usageOffset, newValue)

	actual var size: ULong
		get() = getULong("size", sizeOffset)
		set(newValue) = set("size", sizeOffset, newValue)

	actual var mappedAtCreation: Boolean
		get() = getInt("mappedAtCreation", mappedAtCreationOffset).toBoolean()
		set(newValue) = set("mappedAtCreation", mappedAtCreationOffset, newValue)

	override fun getLayout(name: String)
		= WGPUBufferDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("usage"),
			C_LONG.withName("size"),
			C_INT.withName("mappedAtCreation")
		).withName("WGPUBufferDescriptor")

		val labelOffset = 0L
		val usageOffset = 8L + labelOffset
		val sizeOffset = 8L + usageOffset
		val mappedAtCreationOffset = 8L + sizeOffset
	}
}

@JvmInline
actual value class WGPUColor(actual override val handler: NativeAddress) : CStructure {
	actual var r: Double
		get() = getDouble("r", rOffset)
		set(newValue) = set("r", rOffset, newValue)

	actual var g: Double
		get() = getDouble("g", gOffset)
		set(newValue) = set("g", gOffset, newValue)

	actual var b: Double
		get() = getDouble("b", bOffset)
		set(newValue) = set("b", bOffset, newValue)

	actual var a: Double
		get() = getDouble("a", aOffset)
		set(newValue) = set("a", aOffset, newValue)

	override fun getLayout(name: String)
		= WGPUColor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_DOUBLE.withName("r"),
			C_DOUBLE.withName("g"),
			C_DOUBLE.withName("b"),
			C_DOUBLE.withName("a")
		).withName("WGPUColor")

		val rOffset = 0L
		val gOffset = 8L + rOffset
		val bOffset = 8L + gOffset
		val aOffset = 8L + bOffset
	}
}

@JvmInline
actual value class WGPUColorTargetState(actual override val handler: NativeAddress) : CStructure {
	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var blend: WGPUBlendState?
		get() = TODO()
		set(newValue) = set("blend", blendOffset, newValue?.handler)

	actual var writeMask: ULong
		get() = getULong("writeMask", writeMaskOffset)
		set(newValue) = set("writeMask", writeMaskOffset, newValue)

	override fun getLayout(name: String)
		= WGPUColorTargetState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_POINTER.withName("blend"),
			C_LONG.withName("writeMask")
		).withName("WGPUColorTargetState")

		val formatOffset = 0L
		val blendOffset = 4L + formatOffset
		val writeMaskOffset = 8L + blendOffset
	}
}

@JvmInline
actual value class WGPUCommandBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUCommandBufferDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUCommandBufferDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPUCommandEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUCommandEncoderDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUCommandEncoderDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPUCompilationInfo(actual override val handler: NativeAddress) : CStructure {
	actual var messageCount: ULong
		get() = getULong("messageCount", messageCountOffset)
		set(newValue) = set("messageCount", messageCountOffset, newValue)

	actual var messages: ArrayHolder<WGPUCompilationMessage>?
		get() = TODO()
		set(newValue) = set("messages", messagesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUCompilationInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("messageCount"),
			C_POINTER.withName("messages")
		).withName("WGPUCompilationInfo")

		val messageCountOffset = 0L
		val messagesOffset = 8L + messageCountOffset
	}
}

@JvmInline
actual value class WGPUCompilationMessage(actual override val handler: NativeAddress) : CStructure {
	actual var message: CString?
		get() = TODO()
		set(newValue) = set("message", messageOffset, newValue?.handler)

	actual var type: WGPUCompilationMessageType
		get() = TODO()
		set(newValue) = set("type", typeOffset, newValue)

	actual var lineNum: ULong
		get() = getULong("lineNum", lineNumOffset)
		set(newValue) = set("lineNum", lineNumOffset, newValue)

	actual var linePos: ULong
		get() = getULong("linePos", linePosOffset)
		set(newValue) = set("linePos", linePosOffset, newValue)

	actual var offset: ULong
		get() = getULong("offset", offsetOffset)
		set(newValue) = set("offset", offsetOffset, newValue)

	actual var length: ULong
		get() = getULong("length", lengthOffset)
		set(newValue) = set("length", lengthOffset, newValue)

	actual var utf16LinePos: ULong
		get() = getULong("utf16LinePos", utf16LinePosOffset)
		set(newValue) = set("utf16LinePos", utf16LinePosOffset, newValue)

	actual var utf16Offset: ULong
		get() = getULong("utf16Offset", utf16OffsetOffset)
		set(newValue) = set("utf16Offset", utf16OffsetOffset, newValue)

	actual var utf16Length: ULong
		get() = getULong("utf16Length", utf16LengthOffset)
		set(newValue) = set("utf16Length", utf16LengthOffset, newValue)

	override fun getLayout(name: String)
		= WGPUCompilationMessage.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("message"),
			C_INT.withName("type"),
			C_LONG.withName("lineNum"),
			C_LONG.withName("linePos"),
			C_LONG.withName("offset"),
			C_LONG.withName("length"),
			C_LONG.withName("utf16LinePos"),
			C_LONG.withName("utf16Offset"),
			C_LONG.withName("utf16Length")
		).withName("WGPUCompilationMessage")

		val messageOffset = 0L
		val typeOffset = 8L + messageOffset
		val lineNumOffset = 4L + typeOffset
		val linePosOffset = 8L + lineNumOffset
		val offsetOffset = 8L + linePosOffset
		val lengthOffset = 8L + offsetOffset
		val utf16LinePosOffset = 8L + lengthOffset
		val utf16OffsetOffset = 8L + utf16LinePosOffset
		val utf16LengthOffset = 8L + utf16OffsetOffset
	}
}

@JvmInline
actual value class WGPUComputePassDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = TODO()
		set(newValue) = set("timestampWrites", timestampWritesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUComputePassDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("timestampWrites")
		).withName("WGPUComputePassDescriptor")

		val labelOffset = 0L
		val timestampWritesOffset = 8L + labelOffset
	}
}

@JvmInline
actual value class WGPUComputePassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = set("querySet", querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt("beginningOfPassWriteIndex", beginningOfPassWriteIndexOffset)
		set(newValue) = set("beginningOfPassWriteIndex", beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt("endOfPassWriteIndex", endOfPassWriteIndexOffset)
		set(newValue) = set("endOfPassWriteIndex", endOfPassWriteIndexOffset, newValue)

	override fun getLayout(name: String)
		= WGPUComputePassTimestampWrites.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPUComputePassTimestampWrites")

		val querySetOffset = 0L
		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
	}
}

@JvmInline
actual value class WGPUComputePipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = set("layout", layoutOffset, newValue?.handler)

	actual val compute: WGPUProgrammableStageDescriptor
		get() = get("compute", computeOffset).let(::WGPUProgrammableStageDescriptor)

	override fun getLayout(name: String)
		= WGPUComputePipelineDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			WGPUProgrammableStageDescriptor.LAYOUT.withName("compute")
		).withName("WGPUComputePipelineDescriptor")

		val labelOffset = 0L
		val layoutOffset = 8L + labelOffset
		val computeOffset = 8L + layoutOffset
	}
}

@JvmInline
actual value class WGPUConstantEntry(actual override val handler: NativeAddress) : CStructure {
	actual var key: CString?
		get() = TODO()
		set(newValue) = set("key", keyOffset, newValue?.handler)

	actual var value: Double
		get() = getDouble("value", valueOffset)
		set(newValue) = set("value", valueOffset, newValue)

	override fun getLayout(name: String)
		= WGPUConstantEntry.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("key"),
			C_DOUBLE.withName("value")
		).withName("WGPUConstantEntry")

		val keyOffset = 0L
		val valueOffset = 8L + keyOffset
	}
}

@JvmInline
actual value class WGPUDepthStencilState(actual override val handler: NativeAddress) : CStructure {
	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var depthWriteEnabled: WGPUOptionalBool
		get() = TODO()
		set(newValue) = set("depthWriteEnabled", depthWriteEnabledOffset, newValue)

	actual var depthCompare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = set("depthCompare", depthCompareOffset, newValue)

	actual val stencilFront: WGPUStencilFaceState
		get() = get("stencilFront", stencilFrontOffset).let(::WGPUStencilFaceState)

	actual val stencilBack: WGPUStencilFaceState
		get() = get("stencilBack", stencilBackOffset).let(::WGPUStencilFaceState)

	actual var stencilReadMask: UInt
		get() = getUInt("stencilReadMask", stencilReadMaskOffset)
		set(newValue) = set("stencilReadMask", stencilReadMaskOffset, newValue)

	actual var stencilWriteMask: UInt
		get() = getUInt("stencilWriteMask", stencilWriteMaskOffset)
		set(newValue) = set("stencilWriteMask", stencilWriteMaskOffset, newValue)

	actual var depthBias: Int
		get() = getInt("depthBias", depthBiasOffset)
		set(newValue) = set("depthBias", depthBiasOffset, newValue)

	actual var depthBiasSlopeScale: Float
		get() = getFloat("depthBiasSlopeScale", depthBiasSlopeScaleOffset)
		set(newValue) = set("depthBiasSlopeScale", depthBiasSlopeScaleOffset, newValue)

	actual var depthBiasClamp: Float
		get() = getFloat("depthBiasClamp", depthBiasClampOffset)
		set(newValue) = set("depthBiasClamp", depthBiasClampOffset, newValue)

	override fun getLayout(name: String)
		= WGPUDepthStencilState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_INT.withName("depthWriteEnabled"),
			C_INT.withName("depthCompare"),
			WGPUStencilFaceState.LAYOUT.withName("stencilFront"),
			WGPUStencilFaceState.LAYOUT.withName("stencilBack"),
			C_INT.withName("stencilReadMask"),
			C_INT.withName("stencilWriteMask"),
			C_INT.withName("depthBias"),
			C_FLOAT.withName("depthBiasSlopeScale"),
			C_FLOAT.withName("depthBiasClamp")
		).withName("WGPUDepthStencilState")

		val formatOffset = 0L
		val depthWriteEnabledOffset = 4L + formatOffset
		val depthCompareOffset = 4L + depthWriteEnabledOffset
		val stencilFrontOffset = 4L + depthCompareOffset
		val stencilBackOffset = LAYOUT.withName("stencilFront").byteSize() + stencilFrontOffset
		val stencilReadMaskOffset = LAYOUT.withName("stencilBack").byteSize() + stencilBackOffset
		val stencilWriteMaskOffset = 4L + stencilReadMaskOffset
		val depthBiasOffset = 4L + stencilWriteMaskOffset
		val depthBiasSlopeScaleOffset = 4L + depthBiasOffset
		val depthBiasClampOffset = 4L + depthBiasSlopeScaleOffset
	}
}

@JvmInline
actual value class WGPUDeviceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var requiredFeatureCount: ULong
		get() = getULong("requiredFeatureCount", requiredFeatureCountOffset)
		set(newValue) = set("requiredFeatureCount", requiredFeatureCountOffset, newValue)

	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
		get() = TODO()
		set(newValue) = set("requiredFeatures", requiredFeaturesOffset, newValue?.handler)

	actual var requiredLimits: WGPURequiredLimits?
		get() = TODO()
		set(newValue) = set("requiredLimits", requiredLimitsOffset, newValue?.handler)

	actual val defaultQueue: WGPUQueueDescriptor
		get() = get("defaultQueue", defaultQueueOffset).let(::WGPUQueueDescriptor)

	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
		get() = get("deviceLostCallbackInfo", deviceLostCallbackInfoOffset).let(::WGPUDeviceLostCallbackInfo)

	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
		get() = get("uncapturedErrorCallbackInfo", uncapturedErrorCallbackInfoOffset).let(::WGPUUncapturedErrorCallbackInfo)

	override fun getLayout(name: String)
		= WGPUDeviceDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("requiredFeatureCount"),
			C_POINTER.withName("requiredFeatures"),
			C_POINTER.withName("requiredLimits"),
			WGPUQueueDescriptor.LAYOUT.withName("defaultQueue"),
			WGPUDeviceLostCallbackInfo.LAYOUT.withName("deviceLostCallbackInfo"),
			WGPUUncapturedErrorCallbackInfo.LAYOUT.withName("uncapturedErrorCallbackInfo")
		).withName("WGPUDeviceDescriptor")

		val labelOffset = 0L
		val requiredFeatureCountOffset = 8L + labelOffset
		val requiredFeaturesOffset = 8L + requiredFeatureCountOffset
		val requiredLimitsOffset = 8L + requiredFeaturesOffset
		val defaultQueueOffset = 8L + requiredLimitsOffset
		val deviceLostCallbackInfoOffset = LAYOUT.withName("defaultQueue").byteSize() + defaultQueueOffset
		val uncapturedErrorCallbackInfoOffset = LAYOUT.withName("deviceLostCallbackInfo").byteSize() + deviceLostCallbackInfoOffset
	}
}

@JvmInline
actual value class WGPUExtent3D(actual override val handler: NativeAddress) : CStructure {
	actual var width: UInt
		get() = getUInt("width", widthOffset)
		set(newValue) = set("width", widthOffset, newValue)

	actual var height: UInt
		get() = getUInt("height", heightOffset)
		set(newValue) = set("height", heightOffset, newValue)

	actual var depthOrArrayLayers: UInt
		get() = getUInt("depthOrArrayLayers", depthOrArrayLayersOffset)
		set(newValue) = set("depthOrArrayLayers", depthOrArrayLayersOffset, newValue)

	override fun getLayout(name: String)
		= WGPUExtent3D.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_INT.withName("depthOrArrayLayers")
		).withName("WGPUExtent3D")

		val widthOffset = 0L
		val heightOffset = 4L + widthOffset
		val depthOrArrayLayersOffset = 4L + heightOffset
	}
}

@JvmInline
actual value class WGPUFragmentState(actual override val handler: NativeAddress) : CStructure {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = set("module", moduleOffset, newValue?.handler)

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = set("entryPoint", entryPointOffset, newValue?.handler)

	actual var constantCount: ULong
		get() = getULong("constantCount", constantCountOffset)
		set(newValue) = set("constantCount", constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = set("constants", constantsOffset, newValue?.handler)

	actual var targetCount: ULong
		get() = getULong("targetCount", targetCountOffset)
		set(newValue) = set("targetCount", targetCountOffset, newValue)

	actual var targets: ArrayHolder<WGPUColorTargetState>?
		get() = TODO()
		set(newValue) = set("targets", targetsOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUFragmentState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("targetCount"),
			C_POINTER.withName("targets")
		).withName("WGPUFragmentState")

		val moduleOffset = 0L
		val entryPointOffset = 8L + moduleOffset
		val constantCountOffset = 8L + entryPointOffset
		val constantsOffset = 8L + constantCountOffset
		val targetCountOffset = 8L + constantsOffset
		val targetsOffset = 8L + targetCountOffset
	}
}

@JvmInline
actual value class WGPUFuture(actual override val handler: NativeAddress) : CStructure {
	actual var id: ULong
		get() = getULong("id", idOffset)
		set(newValue) = set("id", idOffset, newValue)

	override fun getLayout(name: String)
		= WGPUFuture.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("id")
		).withName("WGPUFuture")

		val idOffset = 0L
	}
}

@JvmInline
actual value class WGPUFutureWaitInfo(actual override val handler: NativeAddress) : CStructure {
	actual val future: WGPUFuture
		get() = get("future", futureOffset).let(::WGPUFuture)

	actual var completed: Boolean
		get() = getInt("completed", completedOffset).toBoolean()
		set(newValue) = set("completed", completedOffset, newValue)

	override fun getLayout(name: String)
		= WGPUFutureWaitInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPUFuture.LAYOUT.withName("future"),
			C_INT.withName("completed")
		).withName("WGPUFutureWaitInfo")

		val futureOffset = 0L
		val completedOffset = LAYOUT.withName("future").byteSize() + futureOffset
	}
}

@JvmInline
actual value class WGPUImageCopyBuffer(actual override val handler: NativeAddress) : CStructure {
	actual val layout: WGPUTextureDataLayout
		get() = get("layout", layoutOffset).let(::WGPUTextureDataLayout)

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = set("buffer", bufferOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUImageCopyBuffer.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPUTextureDataLayout.LAYOUT.withName("layout"),
			C_POINTER.withName("buffer")
		).withName("WGPUImageCopyBuffer")

		val layoutOffset = 0L
		val bufferOffset = LAYOUT.withName("layout").byteSize() + layoutOffset
	}
}

@JvmInline
actual value class WGPUImageCopyTexture(actual override val handler: NativeAddress) : CStructure {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = set("texture", textureOffset, newValue?.handler)

	actual var mipLevel: UInt
		get() = getUInt("mipLevel", mipLevelOffset)
		set(newValue) = set("mipLevel", mipLevelOffset, newValue)

	actual val origin: WGPUOrigin3D
		get() = get("origin", originOffset).let(::WGPUOrigin3D)

	actual var aspect: WGPUTextureAspect
		get() = TODO()
		set(newValue) = set("aspect", aspectOffset, newValue)

	override fun getLayout(name: String)
		= WGPUImageCopyTexture.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("mipLevel"),
			WGPUOrigin3D.LAYOUT.withName("origin"),
			C_INT.withName("aspect")
		).withName("WGPUImageCopyTexture")

		val textureOffset = 0L
		val mipLevelOffset = 8L + textureOffset
		val originOffset = 4L + mipLevelOffset
		val aspectOffset = LAYOUT.withName("origin").byteSize() + originOffset
	}
}

@JvmInline
actual value class WGPUInstanceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual val features: WGPUInstanceFeatures
		get() = get("features", featuresOffset).let(::WGPUInstanceFeatures)

	override fun getLayout(name: String)
		= WGPUInstanceDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPUInstanceFeatures.LAYOUT.withName("features")
		).withName("WGPUInstanceDescriptor")

		val featuresOffset = 0L
	}
}

@JvmInline
actual value class WGPUInstanceFeatures(actual override val handler: NativeAddress) : CStructure {
	actual var timedWaitAnyEnable: Boolean
		get() = getInt("timedWaitAnyEnable", timedWaitAnyEnableOffset).toBoolean()
		set(newValue) = set("timedWaitAnyEnable", timedWaitAnyEnableOffset, newValue)

	actual var timedWaitAnyMaxCount: ULong
		get() = getULong("timedWaitAnyMaxCount", timedWaitAnyMaxCountOffset)
		set(newValue) = set("timedWaitAnyMaxCount", timedWaitAnyMaxCountOffset, newValue)

	override fun getLayout(name: String)
		= WGPUInstanceFeatures.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("timedWaitAnyEnable"),
			C_LONG.withName("timedWaitAnyMaxCount")
		).withName("WGPUInstanceFeatures")

		val timedWaitAnyEnableOffset = 0L
		val timedWaitAnyMaxCountOffset = 4L + timedWaitAnyEnableOffset
	}
}

@JvmInline
actual value class WGPULimits(actual override val handler: NativeAddress) : CStructure {
	actual var maxTextureDimension1D: UInt
		get() = getUInt("maxTextureDimension1D", maxTextureDimension1DOffset)
		set(newValue) = set("maxTextureDimension1D", maxTextureDimension1DOffset, newValue)

	actual var maxTextureDimension2D: UInt
		get() = getUInt("maxTextureDimension2D", maxTextureDimension2DOffset)
		set(newValue) = set("maxTextureDimension2D", maxTextureDimension2DOffset, newValue)

	actual var maxTextureDimension3D: UInt
		get() = getUInt("maxTextureDimension3D", maxTextureDimension3DOffset)
		set(newValue) = set("maxTextureDimension3D", maxTextureDimension3DOffset, newValue)

	actual var maxTextureArrayLayers: UInt
		get() = getUInt("maxTextureArrayLayers", maxTextureArrayLayersOffset)
		set(newValue) = set("maxTextureArrayLayers", maxTextureArrayLayersOffset, newValue)

	actual var maxBindGroups: UInt
		get() = getUInt("maxBindGroups", maxBindGroupsOffset)
		set(newValue) = set("maxBindGroups", maxBindGroupsOffset, newValue)

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = getUInt("maxBindGroupsPlusVertexBuffers", maxBindGroupsPlusVertexBuffersOffset)
		set(newValue) = set("maxBindGroupsPlusVertexBuffers", maxBindGroupsPlusVertexBuffersOffset, newValue)

	actual var maxBindingsPerBindGroup: UInt
		get() = getUInt("maxBindingsPerBindGroup", maxBindingsPerBindGroupOffset)
		set(newValue) = set("maxBindingsPerBindGroup", maxBindingsPerBindGroupOffset, newValue)

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = getUInt("maxDynamicUniformBuffersPerPipelineLayout", maxDynamicUniformBuffersPerPipelineLayoutOffset)
		set(newValue) = set("maxDynamicUniformBuffersPerPipelineLayout", maxDynamicUniformBuffersPerPipelineLayoutOffset, newValue)

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = getUInt("maxDynamicStorageBuffersPerPipelineLayout", maxDynamicStorageBuffersPerPipelineLayoutOffset)
		set(newValue) = set("maxDynamicStorageBuffersPerPipelineLayout", maxDynamicStorageBuffersPerPipelineLayoutOffset, newValue)

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = getUInt("maxSampledTexturesPerShaderStage", maxSampledTexturesPerShaderStageOffset)
		set(newValue) = set("maxSampledTexturesPerShaderStage", maxSampledTexturesPerShaderStageOffset, newValue)

	actual var maxSamplersPerShaderStage: UInt
		get() = getUInt("maxSamplersPerShaderStage", maxSamplersPerShaderStageOffset)
		set(newValue) = set("maxSamplersPerShaderStage", maxSamplersPerShaderStageOffset, newValue)

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = getUInt("maxStorageBuffersPerShaderStage", maxStorageBuffersPerShaderStageOffset)
		set(newValue) = set("maxStorageBuffersPerShaderStage", maxStorageBuffersPerShaderStageOffset, newValue)

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = getUInt("maxStorageTexturesPerShaderStage", maxStorageTexturesPerShaderStageOffset)
		set(newValue) = set("maxStorageTexturesPerShaderStage", maxStorageTexturesPerShaderStageOffset, newValue)

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = getUInt("maxUniformBuffersPerShaderStage", maxUniformBuffersPerShaderStageOffset)
		set(newValue) = set("maxUniformBuffersPerShaderStage", maxUniformBuffersPerShaderStageOffset, newValue)

	actual var maxUniformBufferBindingSize: ULong
		get() = getULong("maxUniformBufferBindingSize", maxUniformBufferBindingSizeOffset)
		set(newValue) = set("maxUniformBufferBindingSize", maxUniformBufferBindingSizeOffset, newValue)

	actual var maxStorageBufferBindingSize: ULong
		get() = getULong("maxStorageBufferBindingSize", maxStorageBufferBindingSizeOffset)
		set(newValue) = set("maxStorageBufferBindingSize", maxStorageBufferBindingSizeOffset, newValue)

	actual var minUniformBufferOffsetAlignment: UInt
		get() = getUInt("minUniformBufferOffsetAlignment", minUniformBufferOffsetAlignmentOffset)
		set(newValue) = set("minUniformBufferOffsetAlignment", minUniformBufferOffsetAlignmentOffset, newValue)

	actual var minStorageBufferOffsetAlignment: UInt
		get() = getUInt("minStorageBufferOffsetAlignment", minStorageBufferOffsetAlignmentOffset)
		set(newValue) = set("minStorageBufferOffsetAlignment", minStorageBufferOffsetAlignmentOffset, newValue)

	actual var maxVertexBuffers: UInt
		get() = getUInt("maxVertexBuffers", maxVertexBuffersOffset)
		set(newValue) = set("maxVertexBuffers", maxVertexBuffersOffset, newValue)

	actual var maxBufferSize: ULong
		get() = getULong("maxBufferSize", maxBufferSizeOffset)
		set(newValue) = set("maxBufferSize", maxBufferSizeOffset, newValue)

	actual var maxVertexAttributes: UInt
		get() = getUInt("maxVertexAttributes", maxVertexAttributesOffset)
		set(newValue) = set("maxVertexAttributes", maxVertexAttributesOffset, newValue)

	actual var maxVertexBufferArrayStride: UInt
		get() = getUInt("maxVertexBufferArrayStride", maxVertexBufferArrayStrideOffset)
		set(newValue) = set("maxVertexBufferArrayStride", maxVertexBufferArrayStrideOffset, newValue)

	actual var maxInterStageShaderVariables: UInt
		get() = getUInt("maxInterStageShaderVariables", maxInterStageShaderVariablesOffset)
		set(newValue) = set("maxInterStageShaderVariables", maxInterStageShaderVariablesOffset, newValue)

	actual var maxColorAttachments: UInt
		get() = getUInt("maxColorAttachments", maxColorAttachmentsOffset)
		set(newValue) = set("maxColorAttachments", maxColorAttachmentsOffset, newValue)

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = getUInt("maxColorAttachmentBytesPerSample", maxColorAttachmentBytesPerSampleOffset)
		set(newValue) = set("maxColorAttachmentBytesPerSample", maxColorAttachmentBytesPerSampleOffset, newValue)

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = getUInt("maxComputeWorkgroupStorageSize", maxComputeWorkgroupStorageSizeOffset)
		set(newValue) = set("maxComputeWorkgroupStorageSize", maxComputeWorkgroupStorageSizeOffset, newValue)

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = getUInt("maxComputeInvocationsPerWorkgroup", maxComputeInvocationsPerWorkgroupOffset)
		set(newValue) = set("maxComputeInvocationsPerWorkgroup", maxComputeInvocationsPerWorkgroupOffset, newValue)

	actual var maxComputeWorkgroupSizeX: UInt
		get() = getUInt("maxComputeWorkgroupSizeX", maxComputeWorkgroupSizeXOffset)
		set(newValue) = set("maxComputeWorkgroupSizeX", maxComputeWorkgroupSizeXOffset, newValue)

	actual var maxComputeWorkgroupSizeY: UInt
		get() = getUInt("maxComputeWorkgroupSizeY", maxComputeWorkgroupSizeYOffset)
		set(newValue) = set("maxComputeWorkgroupSizeY", maxComputeWorkgroupSizeYOffset, newValue)

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = getUInt("maxComputeWorkgroupSizeZ", maxComputeWorkgroupSizeZOffset)
		set(newValue) = set("maxComputeWorkgroupSizeZ", maxComputeWorkgroupSizeZOffset, newValue)

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = getUInt("maxComputeWorkgroupsPerDimension", maxComputeWorkgroupsPerDimensionOffset)
		set(newValue) = set("maxComputeWorkgroupsPerDimension", maxComputeWorkgroupsPerDimensionOffset, newValue)

	override fun getLayout(name: String)
		= WGPULimits.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("maxTextureDimension1D"),
			C_INT.withName("maxTextureDimension2D"),
			C_INT.withName("maxTextureDimension3D"),
			C_INT.withName("maxTextureArrayLayers"),
			C_INT.withName("maxBindGroups"),
			C_INT.withName("maxBindGroupsPlusVertexBuffers"),
			C_INT.withName("maxBindingsPerBindGroup"),
			C_INT.withName("maxDynamicUniformBuffersPerPipelineLayout"),
			C_INT.withName("maxDynamicStorageBuffersPerPipelineLayout"),
			C_INT.withName("maxSampledTexturesPerShaderStage"),
			C_INT.withName("maxSamplersPerShaderStage"),
			C_INT.withName("maxStorageBuffersPerShaderStage"),
			C_INT.withName("maxStorageTexturesPerShaderStage"),
			C_INT.withName("maxUniformBuffersPerShaderStage"),
			C_LONG.withName("maxUniformBufferBindingSize"),
			C_LONG.withName("maxStorageBufferBindingSize"),
			C_INT.withName("minUniformBufferOffsetAlignment"),
			C_INT.withName("minStorageBufferOffsetAlignment"),
			C_INT.withName("maxVertexBuffers"),
			C_LONG.withName("maxBufferSize"),
			C_INT.withName("maxVertexAttributes"),
			C_INT.withName("maxVertexBufferArrayStride"),
			C_INT.withName("maxInterStageShaderVariables"),
			C_INT.withName("maxColorAttachments"),
			C_INT.withName("maxColorAttachmentBytesPerSample"),
			C_INT.withName("maxComputeWorkgroupStorageSize"),
			C_INT.withName("maxComputeInvocationsPerWorkgroup"),
			C_INT.withName("maxComputeWorkgroupSizeX"),
			C_INT.withName("maxComputeWorkgroupSizeY"),
			C_INT.withName("maxComputeWorkgroupSizeZ"),
			C_INT.withName("maxComputeWorkgroupsPerDimension")
		).withName("WGPULimits")

		val maxTextureDimension1DOffset = 0L
		val maxTextureDimension2DOffset = 4L + maxTextureDimension1DOffset
		val maxTextureDimension3DOffset = 4L + maxTextureDimension2DOffset
		val maxTextureArrayLayersOffset = 4L + maxTextureDimension3DOffset
		val maxBindGroupsOffset = 4L + maxTextureArrayLayersOffset
		val maxBindGroupsPlusVertexBuffersOffset = 4L + maxBindGroupsOffset
		val maxBindingsPerBindGroupOffset = 4L + maxBindGroupsPlusVertexBuffersOffset
		val maxDynamicUniformBuffersPerPipelineLayoutOffset = 4L + maxBindingsPerBindGroupOffset
		val maxDynamicStorageBuffersPerPipelineLayoutOffset = 4L + maxDynamicUniformBuffersPerPipelineLayoutOffset
		val maxSampledTexturesPerShaderStageOffset = 4L + maxDynamicStorageBuffersPerPipelineLayoutOffset
		val maxSamplersPerShaderStageOffset = 4L + maxSampledTexturesPerShaderStageOffset
		val maxStorageBuffersPerShaderStageOffset = 4L + maxSamplersPerShaderStageOffset
		val maxStorageTexturesPerShaderStageOffset = 4L + maxStorageBuffersPerShaderStageOffset
		val maxUniformBuffersPerShaderStageOffset = 4L + maxStorageTexturesPerShaderStageOffset
		val maxUniformBufferBindingSizeOffset = 4L + maxUniformBuffersPerShaderStageOffset
		val maxStorageBufferBindingSizeOffset = 8L + maxUniformBufferBindingSizeOffset
		val minUniformBufferOffsetAlignmentOffset = 8L + maxStorageBufferBindingSizeOffset
		val minStorageBufferOffsetAlignmentOffset = 4L + minUniformBufferOffsetAlignmentOffset
		val maxVertexBuffersOffset = 4L + minStorageBufferOffsetAlignmentOffset
		val maxBufferSizeOffset = 4L + maxVertexBuffersOffset
		val maxVertexAttributesOffset = 8L + maxBufferSizeOffset
		val maxVertexBufferArrayStrideOffset = 4L + maxVertexAttributesOffset
		val maxInterStageShaderVariablesOffset = 4L + maxVertexBufferArrayStrideOffset
		val maxColorAttachmentsOffset = 4L + maxInterStageShaderVariablesOffset
		val maxColorAttachmentBytesPerSampleOffset = 4L + maxColorAttachmentsOffset
		val maxComputeWorkgroupStorageSizeOffset = 4L + maxColorAttachmentBytesPerSampleOffset
		val maxComputeInvocationsPerWorkgroupOffset = 4L + maxComputeWorkgroupStorageSizeOffset
		val maxComputeWorkgroupSizeXOffset = 4L + maxComputeInvocationsPerWorkgroupOffset
		val maxComputeWorkgroupSizeYOffset = 4L + maxComputeWorkgroupSizeXOffset
		val maxComputeWorkgroupSizeZOffset = 4L + maxComputeWorkgroupSizeYOffset
		val maxComputeWorkgroupsPerDimensionOffset = 4L + maxComputeWorkgroupSizeZOffset
	}
}

@JvmInline
actual value class WGPUMultisampleState(actual override val handler: NativeAddress) : CStructure {
	actual var count: UInt
		get() = getUInt("count", countOffset)
		set(newValue) = set("count", countOffset, newValue)

	actual var mask: UInt
		get() = getUInt("mask", maskOffset)
		set(newValue) = set("mask", maskOffset, newValue)

	actual var alphaToCoverageEnabled: Boolean
		get() = getInt("alphaToCoverageEnabled", alphaToCoverageEnabledOffset).toBoolean()
		set(newValue) = set("alphaToCoverageEnabled", alphaToCoverageEnabledOffset, newValue)

	override fun getLayout(name: String)
		= WGPUMultisampleState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("count"),
			C_INT.withName("mask"),
			C_INT.withName("alphaToCoverageEnabled")
		).withName("WGPUMultisampleState")

		val countOffset = 0L
		val maskOffset = 4L + countOffset
		val alphaToCoverageEnabledOffset = 4L + maskOffset
	}
}

@JvmInline
actual value class WGPUOrigin3D(actual override val handler: NativeAddress) : CStructure {
	actual var x: UInt
		get() = getUInt("x", xOffset)
		set(newValue) = set("x", xOffset, newValue)

	actual var y: UInt
		get() = getUInt("y", yOffset)
		set(newValue) = set("y", yOffset, newValue)

	actual var z: UInt
		get() = getUInt("z", zOffset)
		set(newValue) = set("z", zOffset, newValue)

	override fun getLayout(name: String)
		= WGPUOrigin3D.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("x"),
			C_INT.withName("y"),
			C_INT.withName("z")
		).withName("WGPUOrigin3D")

		val xOffset = 0L
		val yOffset = 4L + xOffset
		val zOffset = 4L + yOffset
	}
}

@JvmInline
actual value class WGPUPipelineLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var bindGroupLayoutCount: ULong
		get() = getULong("bindGroupLayoutCount", bindGroupLayoutCountOffset)
		set(newValue) = set("bindGroupLayoutCount", bindGroupLayoutCountOffset, newValue)

	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
		get() = TODO()
		set(newValue) = set("bindGroupLayouts", bindGroupLayoutsOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUPipelineLayoutDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("bindGroupLayoutCount"),
			C_POINTER.withName("bindGroupLayouts")
		).withName("WGPUPipelineLayoutDescriptor")

		val labelOffset = 0L
		val bindGroupLayoutCountOffset = 8L + labelOffset
		val bindGroupLayoutsOffset = 8L + bindGroupLayoutCountOffset
	}
}

@JvmInline
actual value class WGPUPrimitiveState(actual override val handler: NativeAddress) : CStructure {
	actual var topology: WGPUPrimitiveTopology
		get() = TODO()
		set(newValue) = set("topology", topologyOffset, newValue)

	actual var stripIndexFormat: WGPUIndexFormat
		get() = TODO()
		set(newValue) = set("stripIndexFormat", stripIndexFormatOffset, newValue)

	actual var frontFace: WGPUFrontFace
		get() = TODO()
		set(newValue) = set("frontFace", frontFaceOffset, newValue)

	actual var cullMode: WGPUCullMode
		get() = TODO()
		set(newValue) = set("cullMode", cullModeOffset, newValue)

	actual var unclippedDepth: Boolean
		get() = getInt("unclippedDepth", unclippedDepthOffset).toBoolean()
		set(newValue) = set("unclippedDepth", unclippedDepthOffset, newValue)

	override fun getLayout(name: String)
		= WGPUPrimitiveState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("topology"),
			C_INT.withName("stripIndexFormat"),
			C_INT.withName("frontFace"),
			C_INT.withName("cullMode"),
			C_INT.withName("unclippedDepth")
		).withName("WGPUPrimitiveState")

		val topologyOffset = 0L
		val stripIndexFormatOffset = 4L + topologyOffset
		val frontFaceOffset = 4L + stripIndexFormatOffset
		val cullModeOffset = 4L + frontFaceOffset
		val unclippedDepthOffset = 4L + cullModeOffset
	}
}

@JvmInline
actual value class WGPUProgrammableStageDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = set("module", moduleOffset, newValue?.handler)

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = set("entryPoint", entryPointOffset, newValue?.handler)

	actual var constantCount: ULong
		get() = getULong("constantCount", constantCountOffset)
		set(newValue) = set("constantCount", constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = set("constants", constantsOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUProgrammableStageDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants")
		).withName("WGPUProgrammableStageDescriptor")

		val moduleOffset = 0L
		val entryPointOffset = 8L + moduleOffset
		val constantCountOffset = 8L + entryPointOffset
		val constantsOffset = 8L + constantCountOffset
	}
}

@JvmInline
actual value class WGPUQuerySetDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var type: WGPUQueryType
		get() = TODO()
		set(newValue) = set("type", typeOffset, newValue)

	actual var count: UInt
		get() = getUInt("count", countOffset)
		set(newValue) = set("count", countOffset, newValue)

	override fun getLayout(name: String)
		= WGPUQuerySetDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_INT.withName("type"),
			C_INT.withName("count")
		).withName("WGPUQuerySetDescriptor")

		val labelOffset = 0L
		val typeOffset = 8L + labelOffset
		val countOffset = 4L + typeOffset
	}
}

@JvmInline
actual value class WGPUQueueDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUQueueDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUQueueDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPURenderBundleDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPURenderBundleDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPURenderBundleDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPURenderBundleEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var colorFormatCount: ULong
		get() = getULong("colorFormatCount", colorFormatCountOffset)
		set(newValue) = set("colorFormatCount", colorFormatCountOffset, newValue)

	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = set("colorFormats", colorFormatsOffset, newValue?.handler)

	actual var depthStencilFormat: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("depthStencilFormat", depthStencilFormatOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt("sampleCount", sampleCountOffset)
		set(newValue) = set("sampleCount", sampleCountOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt("depthReadOnly", depthReadOnlyOffset).toBoolean()
		set(newValue) = set("depthReadOnly", depthReadOnlyOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt("stencilReadOnly", stencilReadOnlyOffset).toBoolean()
		set(newValue) = set("stencilReadOnly", stencilReadOnlyOffset, newValue)

	override fun getLayout(name: String)
		= WGPURenderBundleEncoderDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("colorFormatCount"),
			C_POINTER.withName("colorFormats"),
			C_INT.withName("depthStencilFormat"),
			C_INT.withName("sampleCount"),
			C_INT.withName("depthReadOnly"),
			C_INT.withName("stencilReadOnly")
		).withName("WGPURenderBundleEncoderDescriptor")

		val labelOffset = 0L
		val colorFormatCountOffset = 8L + labelOffset
		val colorFormatsOffset = 8L + colorFormatCountOffset
		val depthStencilFormatOffset = 8L + colorFormatsOffset
		val sampleCountOffset = 4L + depthStencilFormatOffset
		val depthReadOnlyOffset = 4L + sampleCountOffset
		val stencilReadOnlyOffset = 4L + depthReadOnlyOffset
	}
}

@JvmInline
actual value class WGPURenderPassColorAttachment(actual override val handler: NativeAddress) : CStructure {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = set("view", viewOffset, newValue?.handler)

	actual var depthSlice: UInt
		get() = getUInt("depthSlice", depthSliceOffset)
		set(newValue) = set("depthSlice", depthSliceOffset, newValue)

	actual var resolveTarget: WGPUTextureView?
		get() = TODO()
		set(newValue) = set("resolveTarget", resolveTargetOffset, newValue?.handler)

	actual var loadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = set("loadOp", loadOpOffset, newValue)

	actual var storeOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = set("storeOp", storeOpOffset, newValue)

	actual val clearValue: WGPUColor
		get() = get("clearValue", clearValueOffset).let(::WGPUColor)

	override fun getLayout(name: String)
		= WGPURenderPassColorAttachment.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("view"),
			C_INT.withName("depthSlice"),
			C_POINTER.withName("resolveTarget"),
			C_INT.withName("loadOp"),
			C_INT.withName("storeOp"),
			WGPUColor.LAYOUT.withName("clearValue")
		).withName("WGPURenderPassColorAttachment")

		val viewOffset = 0L
		val depthSliceOffset = 8L + viewOffset
		val resolveTargetOffset = 4L + depthSliceOffset
		val loadOpOffset = 8L + resolveTargetOffset
		val storeOpOffset = 4L + loadOpOffset
		val clearValueOffset = 4L + storeOpOffset
	}
}

@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual override val handler: NativeAddress) : CStructure {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = set("view", viewOffset, newValue?.handler)

	actual var depthLoadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = set("depthLoadOp", depthLoadOpOffset, newValue)

	actual var depthStoreOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = set("depthStoreOp", depthStoreOpOffset, newValue)

	actual var depthClearValue: Float
		get() = getFloat("depthClearValue", depthClearValueOffset)
		set(newValue) = set("depthClearValue", depthClearValueOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt("depthReadOnly", depthReadOnlyOffset).toBoolean()
		set(newValue) = set("depthReadOnly", depthReadOnlyOffset, newValue)

	actual var stencilLoadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = set("stencilLoadOp", stencilLoadOpOffset, newValue)

	actual var stencilStoreOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = set("stencilStoreOp", stencilStoreOpOffset, newValue)

	actual var stencilClearValue: UInt
		get() = getUInt("stencilClearValue", stencilClearValueOffset)
		set(newValue) = set("stencilClearValue", stencilClearValueOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt("stencilReadOnly", stencilReadOnlyOffset).toBoolean()
		set(newValue) = set("stencilReadOnly", stencilReadOnlyOffset, newValue)

	override fun getLayout(name: String)
		= WGPURenderPassDepthStencilAttachment.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("view"),
			C_INT.withName("depthLoadOp"),
			C_INT.withName("depthStoreOp"),
			C_FLOAT.withName("depthClearValue"),
			C_INT.withName("depthReadOnly"),
			C_INT.withName("stencilLoadOp"),
			C_INT.withName("stencilStoreOp"),
			C_INT.withName("stencilClearValue"),
			C_INT.withName("stencilReadOnly")
		).withName("WGPURenderPassDepthStencilAttachment")

		val viewOffset = 0L
		val depthLoadOpOffset = 8L + viewOffset
		val depthStoreOpOffset = 4L + depthLoadOpOffset
		val depthClearValueOffset = 4L + depthStoreOpOffset
		val depthReadOnlyOffset = 4L + depthClearValueOffset
		val stencilLoadOpOffset = 4L + depthReadOnlyOffset
		val stencilStoreOpOffset = 4L + stencilLoadOpOffset
		val stencilClearValueOffset = 4L + stencilStoreOpOffset
		val stencilReadOnlyOffset = 4L + stencilClearValueOffset
	}
}

@JvmInline
actual value class WGPURenderPassDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var colorAttachmentCount: ULong
		get() = getULong("colorAttachmentCount", colorAttachmentCountOffset)
		set(newValue) = set("colorAttachmentCount", colorAttachmentCountOffset, newValue)

	actual var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
		get() = TODO()
		set(newValue) = set("colorAttachments", colorAttachmentsOffset, newValue?.handler)

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = TODO()
		set(newValue) = set("depthStencilAttachment", depthStencilAttachmentOffset, newValue?.handler)

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = set("occlusionQuerySet", occlusionQuerySetOffset, newValue?.handler)

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = TODO()
		set(newValue) = set("timestampWrites", timestampWritesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPURenderPassDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("colorAttachmentCount"),
			C_POINTER.withName("colorAttachments"),
			C_POINTER.withName("depthStencilAttachment"),
			C_POINTER.withName("occlusionQuerySet"),
			C_POINTER.withName("timestampWrites")
		).withName("WGPURenderPassDescriptor")

		val labelOffset = 0L
		val colorAttachmentCountOffset = 8L + labelOffset
		val colorAttachmentsOffset = 8L + colorAttachmentCountOffset
		val depthStencilAttachmentOffset = 8L + colorAttachmentsOffset
		val occlusionQuerySetOffset = 8L + depthStencilAttachmentOffset
		val timestampWritesOffset = 8L + occlusionQuerySetOffset
	}
}

@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual override val handler: NativeAddress) : CStructure {
	actual var maxDrawCount: ULong
		get() = getULong("maxDrawCount", maxDrawCountOffset)
		set(newValue) = set("maxDrawCount", maxDrawCountOffset, newValue)

	override fun getLayout(name: String)
		= WGPURenderPassMaxDrawCount.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("maxDrawCount")
		).withName("WGPURenderPassMaxDrawCount")

		val maxDrawCountOffset = 0L
	}
}

@JvmInline
actual value class WGPURenderPassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = set("querySet", querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt("beginningOfPassWriteIndex", beginningOfPassWriteIndexOffset)
		set(newValue) = set("beginningOfPassWriteIndex", beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt("endOfPassWriteIndex", endOfPassWriteIndexOffset)
		set(newValue) = set("endOfPassWriteIndex", endOfPassWriteIndexOffset, newValue)

	override fun getLayout(name: String)
		= WGPURenderPassTimestampWrites.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPURenderPassTimestampWrites")

		val querySetOffset = 0L
		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
	}
}

@JvmInline
actual value class WGPURenderPipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = set("layout", layoutOffset, newValue?.handler)

	actual val vertex: WGPUVertexState
		get() = get("vertex", vertexOffset).let(::WGPUVertexState)

	actual val primitive: WGPUPrimitiveState
		get() = get("primitive", primitiveOffset).let(::WGPUPrimitiveState)

	actual var depthStencil: WGPUDepthStencilState?
		get() = TODO()
		set(newValue) = set("depthStencil", depthStencilOffset, newValue?.handler)

	actual val multisample: WGPUMultisampleState
		get() = get("multisample", multisampleOffset).let(::WGPUMultisampleState)

	actual var fragment: WGPUFragmentState?
		get() = TODO()
		set(newValue) = set("fragment", fragmentOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPURenderPipelineDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			WGPUVertexState.LAYOUT.withName("vertex"),
			WGPUPrimitiveState.LAYOUT.withName("primitive"),
			C_POINTER.withName("depthStencil"),
			WGPUMultisampleState.LAYOUT.withName("multisample"),
			C_POINTER.withName("fragment")
		).withName("WGPURenderPipelineDescriptor")

		val labelOffset = 0L
		val layoutOffset = 8L + labelOffset
		val vertexOffset = 8L + layoutOffset
		val primitiveOffset = LAYOUT.withName("vertex").byteSize() + vertexOffset
		val depthStencilOffset = LAYOUT.withName("primitive").byteSize() + primitiveOffset
		val multisampleOffset = 8L + depthStencilOffset
		val fragmentOffset = LAYOUT.withName("multisample").byteSize() + multisampleOffset
	}
}

@JvmInline
actual value class WGPURequestAdapterOptions(actual override val handler: NativeAddress) : CStructure {
	actual var compatibleSurface: WGPUSurface?
		get() = TODO()
		set(newValue) = set("compatibleSurface", compatibleSurfaceOffset, newValue?.handler)

	actual var powerPreference: WGPUPowerPreference
		get() = TODO()
		set(newValue) = set("powerPreference", powerPreferenceOffset, newValue)

	actual var backendType: WGPUBackendType
		get() = TODO()
		set(newValue) = set("backendType", backendTypeOffset, newValue)

	actual var forceFallbackAdapter: Boolean
		get() = getInt("forceFallbackAdapter", forceFallbackAdapterOffset).toBoolean()
		set(newValue) = set("forceFallbackAdapter", forceFallbackAdapterOffset, newValue)

	override fun getLayout(name: String)
		= WGPURequestAdapterOptions.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("compatibleSurface"),
			C_INT.withName("powerPreference"),
			C_INT.withName("backendType"),
			C_INT.withName("forceFallbackAdapter")
		).withName("WGPURequestAdapterOptions")

		val compatibleSurfaceOffset = 0L
		val powerPreferenceOffset = 8L + compatibleSurfaceOffset
		val backendTypeOffset = 4L + powerPreferenceOffset
		val forceFallbackAdapterOffset = 4L + backendTypeOffset
	}
}

@JvmInline
actual value class WGPURequiredLimits(actual override val handler: NativeAddress) : CStructure {
	actual val limits: WGPULimits
		get() = get("limits", limitsOffset).let(::WGPULimits)

	override fun getLayout(name: String)
		= WGPURequiredLimits.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPULimits.LAYOUT.withName("limits")
		).withName("WGPURequiredLimits")

		val limitsOffset = 0L
	}
}

@JvmInline
actual value class WGPUSamplerBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var type: WGPUSamplerBindingType
		get() = TODO()
		set(newValue) = set("type", typeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSamplerBindingLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("type")
		).withName("WGPUSamplerBindingLayout")

		val typeOffset = 0L
	}
}

@JvmInline
actual value class WGPUSamplerDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var addressModeU: WGPUAddressMode
		get() = TODO()
		set(newValue) = set("addressModeU", addressModeUOffset, newValue)

	actual var addressModeV: WGPUAddressMode
		get() = TODO()
		set(newValue) = set("addressModeV", addressModeVOffset, newValue)

	actual var addressModeW: WGPUAddressMode
		get() = TODO()
		set(newValue) = set("addressModeW", addressModeWOffset, newValue)

	actual var magFilter: WGPUFilterMode
		get() = TODO()
		set(newValue) = set("magFilter", magFilterOffset, newValue)

	actual var minFilter: WGPUFilterMode
		get() = TODO()
		set(newValue) = set("minFilter", minFilterOffset, newValue)

	actual var mipmapFilter: WGPUMipmapFilterMode
		get() = TODO()
		set(newValue) = set("mipmapFilter", mipmapFilterOffset, newValue)

	actual var lodMinClamp: Float
		get() = getFloat("lodMinClamp", lodMinClampOffset)
		set(newValue) = set("lodMinClamp", lodMinClampOffset, newValue)

	actual var lodMaxClamp: Float
		get() = getFloat("lodMaxClamp", lodMaxClampOffset)
		set(newValue) = set("lodMaxClamp", lodMaxClampOffset, newValue)

	actual var compare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = set("compare", compareOffset, newValue)

	actual var maxAnisotropy: UShort
		get() = getUShort("maxAnisotropy", maxAnisotropyOffset)
		set(newValue) = set("maxAnisotropy", maxAnisotropyOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSamplerDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_INT.withName("addressModeU"),
			C_INT.withName("addressModeV"),
			C_INT.withName("addressModeW"),
			C_INT.withName("magFilter"),
			C_INT.withName("minFilter"),
			C_INT.withName("mipmapFilter"),
			C_FLOAT.withName("lodMinClamp"),
			C_FLOAT.withName("lodMaxClamp"),
			C_INT.withName("compare"),
			C_SHORT.withName("maxAnisotropy")
		).withName("WGPUSamplerDescriptor")

		val labelOffset = 0L
		val addressModeUOffset = 8L + labelOffset
		val addressModeVOffset = 4L + addressModeUOffset
		val addressModeWOffset = 4L + addressModeVOffset
		val magFilterOffset = 4L + addressModeWOffset
		val minFilterOffset = 4L + magFilterOffset
		val mipmapFilterOffset = 4L + minFilterOffset
		val lodMinClampOffset = 4L + mipmapFilterOffset
		val lodMaxClampOffset = 4L + lodMinClampOffset
		val compareOffset = 4L + lodMaxClampOffset
		val maxAnisotropyOffset = 4L + compareOffset
	}
}

@JvmInline
actual value class WGPUShaderModuleDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUShaderModuleDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUShaderModuleDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPUShaderSourceSPIRV(actual override val handler: NativeAddress) : CStructure {
	actual var codeSize: UInt
		get() = getUInt("codeSize", codeSizeOffset)
		set(newValue) = set("codeSize", codeSizeOffset, newValue)

	actual var code: NativeAddress?
		get() = TODO()
		set(newValue) = set("code", codeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUShaderSourceSPIRV.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("codeSize"),
			C_POINTER.withName("code")
		).withName("WGPUShaderSourceSPIRV")

		val codeSizeOffset = 0L
		val codeOffset = 4L + codeSizeOffset
	}
}

@JvmInline
actual value class WGPUShaderSourceWGSL(actual override val handler: NativeAddress) : CStructure {
	actual var code: CString?
		get() = TODO()
		set(newValue) = set("code", codeOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUShaderSourceWGSL.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("code")
		).withName("WGPUShaderSourceWGSL")

		val codeOffset = 0L
	}
}

@JvmInline
actual value class WGPUStencilFaceState(actual override val handler: NativeAddress) : CStructure {
	actual var compare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = set("compare", compareOffset, newValue)

	actual var failOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = set("failOp", failOpOffset, newValue)

	actual var depthFailOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = set("depthFailOp", depthFailOpOffset, newValue)

	actual var passOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = set("passOp", passOpOffset, newValue)

	override fun getLayout(name: String)
		= WGPUStencilFaceState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("compare"),
			C_INT.withName("failOp"),
			C_INT.withName("depthFailOp"),
			C_INT.withName("passOp")
		).withName("WGPUStencilFaceState")

		val compareOffset = 0L
		val failOpOffset = 4L + compareOffset
		val depthFailOpOffset = 4L + failOpOffset
		val passOpOffset = 4L + depthFailOpOffset
	}
}

@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var access: WGPUStorageTextureAccess
		get() = TODO()
		set(newValue) = set("access", accessOffset, newValue)

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = set("viewDimension", viewDimensionOffset, newValue)

	override fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("access"),
			C_INT.withName("format"),
			C_INT.withName("viewDimension")
		).withName("WGPUStorageTextureBindingLayout")

		val accessOffset = 0L
		val formatOffset = 4L + accessOffset
		val viewDimensionOffset = 4L + formatOffset
	}
}

@JvmInline
actual value class WGPUSupportedLimits(actual override val handler: NativeAddress) : CStructure {
	actual val limits: WGPULimits
		get() = get("limits", limitsOffset).let(::WGPULimits)

	override fun getLayout(name: String)
		= WGPUSupportedLimits.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			WGPULimits.LAYOUT.withName("limits")
		).withName("WGPUSupportedLimits")

		val limitsOffset = 0L
	}
}

@JvmInline
actual value class WGPUSurfaceCapabilities(actual override val handler: NativeAddress) : CStructure {
	actual var usages: ULong
		get() = getULong("usages", usagesOffset)
		set(newValue) = set("usages", usagesOffset, newValue)

	actual var formatCount: ULong
		get() = getULong("formatCount", formatCountOffset)
		set(newValue) = set("formatCount", formatCountOffset, newValue)

	actual var formats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = set("formats", formatsOffset, newValue?.handler)

	actual var presentModeCount: ULong
		get() = getULong("presentModeCount", presentModeCountOffset)
		set(newValue) = set("presentModeCount", presentModeCountOffset, newValue)

	actual var presentModes: ArrayHolder<WGPUPresentMode>?
		get() = TODO()
		set(newValue) = set("presentModes", presentModesOffset, newValue?.handler)

	actual var alphaModeCount: ULong
		get() = getULong("alphaModeCount", alphaModeCountOffset)
		set(newValue) = set("alphaModeCount", alphaModeCountOffset, newValue)

	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
		get() = TODO()
		set(newValue) = set("alphaModes", alphaModesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUSurfaceCapabilities.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("usages"),
			C_LONG.withName("formatCount"),
			C_POINTER.withName("formats"),
			C_LONG.withName("presentModeCount"),
			C_POINTER.withName("presentModes"),
			C_LONG.withName("alphaModeCount"),
			C_POINTER.withName("alphaModes")
		).withName("WGPUSurfaceCapabilities")

		val usagesOffset = 0L
		val formatCountOffset = 8L + usagesOffset
		val formatsOffset = 8L + formatCountOffset
		val presentModeCountOffset = 8L + formatsOffset
		val presentModesOffset = 8L + presentModeCountOffset
		val alphaModeCountOffset = 8L + presentModesOffset
		val alphaModesOffset = 8L + alphaModeCountOffset
	}
}

@JvmInline
actual value class WGPUSurfaceConfiguration(actual override val handler: NativeAddress) : CStructure {
	actual var device: WGPUDevice?
		get() = TODO()
		set(newValue) = set("device", deviceOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var usage: ULong
		get() = getULong("usage", usageOffset)
		set(newValue) = set("usage", usageOffset, newValue)

	actual var width: UInt
		get() = getUInt("width", widthOffset)
		set(newValue) = set("width", widthOffset, newValue)

	actual var height: UInt
		get() = getUInt("height", heightOffset)
		set(newValue) = set("height", heightOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong("viewFormatCount", viewFormatCountOffset)
		set(newValue) = set("viewFormatCount", viewFormatCountOffset, newValue)

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = set("viewFormats", viewFormatsOffset, newValue?.handler)

	actual var alphaMode: WGPUCompositeAlphaMode
		get() = TODO()
		set(newValue) = set("alphaMode", alphaModeOffset, newValue)

	actual var presentMode: WGPUPresentMode
		get() = TODO()
		set(newValue) = set("presentMode", presentModeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceConfiguration.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("device"),
			C_INT.withName("format"),
			C_LONG.withName("usage"),
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_LONG.withName("viewFormatCount"),
			C_POINTER.withName("viewFormats"),
			C_INT.withName("alphaMode"),
			C_INT.withName("presentMode")
		).withName("WGPUSurfaceConfiguration")

		val deviceOffset = 0L
		val formatOffset = 8L + deviceOffset
		val usageOffset = 4L + formatOffset
		val widthOffset = 8L + usageOffset
		val heightOffset = 4L + widthOffset
		val viewFormatCountOffset = 4L + heightOffset
		val viewFormatsOffset = 8L + viewFormatCountOffset
		val alphaModeOffset = 8L + viewFormatsOffset
		val presentModeOffset = 4L + alphaModeOffset
	}
}

@JvmInline
actual value class WGPUSurfaceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUSurfaceDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUSurfaceDescriptor")

		val labelOffset = 0L
	}
}

@JvmInline
actual value class WGPUSurfaceSourceAndroidNativeWindow(actual override val handler: NativeAddress) : CStructure {
	actual var window: NativeAddress?
		get() = TODO()
		set(newValue) = set("window", windowOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceAndroidNativeWindow.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("window")
		).withName("WGPUSurfaceSourceAndroidNativeWindow")

		val windowOffset = 0L
	}
}

@JvmInline
actual value class WGPUSurfaceSourceMetalLayer(actual override val handler: NativeAddress) : CStructure {
	actual var layer: NativeAddress?
		get() = TODO()
		set(newValue) = set("layer", layerOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceMetalLayer.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("layer")
		).withName("WGPUSurfaceSourceMetalLayer")

		val layerOffset = 0L
	}
}

@JvmInline
actual value class WGPUSurfaceSourceWaylandSurface(actual override val handler: NativeAddress) : CStructure {
	actual var display: NativeAddress?
		get() = TODO()
		set(newValue) = set("display", displayOffset, newValue)

	actual var surface: NativeAddress?
		get() = TODO()
		set(newValue) = set("surface", surfaceOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceWaylandSurface.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("display"),
			C_POINTER.withName("surface")
		).withName("WGPUSurfaceSourceWaylandSurface")

		val displayOffset = 0L
		val surfaceOffset = 8L + displayOffset
	}
}

@JvmInline
actual value class WGPUSurfaceSourceWindowsHWND(actual override val handler: NativeAddress) : CStructure {
	actual var hinstance: NativeAddress?
		get() = TODO()
		set(newValue) = set("hinstance", hinstanceOffset, newValue)

	actual var hwnd: NativeAddress?
		get() = TODO()
		set(newValue) = set("hwnd", hwndOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceWindowsHWND.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("hinstance"),
			C_POINTER.withName("hwnd")
		).withName("WGPUSurfaceSourceWindowsHWND")

		val hinstanceOffset = 0L
		val hwndOffset = 8L + hinstanceOffset
	}
}

@JvmInline
actual value class WGPUSurfaceSourceXCBWindow(actual override val handler: NativeAddress) : CStructure {
	actual var connection: NativeAddress?
		get() = TODO()
		set(newValue) = set("connection", connectionOffset, newValue)

	actual var window: UInt
		get() = getUInt("window", windowOffset)
		set(newValue) = set("window", windowOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceXCBWindow.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("connection"),
			C_INT.withName("window")
		).withName("WGPUSurfaceSourceXCBWindow")

		val connectionOffset = 0L
		val windowOffset = 8L + connectionOffset
	}
}

@JvmInline
actual value class WGPUSurfaceSourceXlibWindow(actual override val handler: NativeAddress) : CStructure {
	actual var display: NativeAddress?
		get() = TODO()
		set(newValue) = set("display", displayOffset, newValue)

	actual var window: ULong
		get() = getULong("window", windowOffset)
		set(newValue) = set("window", windowOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceSourceXlibWindow.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("display"),
			C_LONG.withName("window")
		).withName("WGPUSurfaceSourceXlibWindow")

		val displayOffset = 0L
		val windowOffset = 8L + displayOffset
	}
}

@JvmInline
actual value class WGPUSurfaceTexture(actual override val handler: NativeAddress) : CStructure {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = set("texture", textureOffset, newValue?.handler)

	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = TODO()
		set(newValue) = set("status", statusOffset, newValue)

	override fun getLayout(name: String)
		= WGPUSurfaceTexture.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("status")
		).withName("WGPUSurfaceTexture")

		val textureOffset = 0L
		val statusOffset = 8L + textureOffset
	}
}

@JvmInline
actual value class WGPUTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var sampleType: WGPUTextureSampleType
		get() = TODO()
		set(newValue) = set("sampleType", sampleTypeOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = set("viewDimension", viewDimensionOffset, newValue)

	actual var multisampled: Boolean
		get() = getInt("multisampled", multisampledOffset).toBoolean()
		set(newValue) = set("multisampled", multisampledOffset, newValue)

	override fun getLayout(name: String)
		= WGPUTextureBindingLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("sampleType"),
			C_INT.withName("viewDimension"),
			C_INT.withName("multisampled")
		).withName("WGPUTextureBindingLayout")

		val sampleTypeOffset = 0L
		val viewDimensionOffset = 4L + sampleTypeOffset
		val multisampledOffset = 4L + viewDimensionOffset
	}
}

@JvmInline
actual value class WGPUTextureDataLayout(actual override val handler: NativeAddress) : CStructure {
	actual var offset: ULong
		get() = getULong("offset", offsetOffset)
		set(newValue) = set("offset", offsetOffset, newValue)

	actual var bytesPerRow: UInt
		get() = getUInt("bytesPerRow", bytesPerRowOffset)
		set(newValue) = set("bytesPerRow", bytesPerRowOffset, newValue)

	actual var rowsPerImage: UInt
		get() = getUInt("rowsPerImage", rowsPerImageOffset)
		set(newValue) = set("rowsPerImage", rowsPerImageOffset, newValue)

	override fun getLayout(name: String)
		= WGPUTextureDataLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("offset"),
			C_INT.withName("bytesPerRow"),
			C_INT.withName("rowsPerImage")
		).withName("WGPUTextureDataLayout")

		val offsetOffset = 0L
		val bytesPerRowOffset = 8L + offsetOffset
		val rowsPerImageOffset = 4L + bytesPerRowOffset
	}
}

@JvmInline
actual value class WGPUTextureDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var usage: ULong
		get() = getULong("usage", usageOffset)
		set(newValue) = set("usage", usageOffset, newValue)

	actual var dimension: WGPUTextureDimension
		get() = TODO()
		set(newValue) = set("dimension", dimensionOffset, newValue)

	actual val size: WGPUExtent3D
		get() = get("size", sizeOffset).let(::WGPUExtent3D)

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt("mipLevelCount", mipLevelCountOffset)
		set(newValue) = set("mipLevelCount", mipLevelCountOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt("sampleCount", sampleCountOffset)
		set(newValue) = set("sampleCount", sampleCountOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong("viewFormatCount", viewFormatCountOffset)
		set(newValue) = set("viewFormatCount", viewFormatCountOffset, newValue)

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = set("viewFormats", viewFormatsOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUTextureDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("usage"),
			C_INT.withName("dimension"),
			WGPUExtent3D.LAYOUT.withName("size"),
			C_INT.withName("format"),
			C_INT.withName("mipLevelCount"),
			C_INT.withName("sampleCount"),
			C_LONG.withName("viewFormatCount"),
			C_POINTER.withName("viewFormats")
		).withName("WGPUTextureDescriptor")

		val labelOffset = 0L
		val usageOffset = 8L + labelOffset
		val dimensionOffset = 8L + usageOffset
		val sizeOffset = 4L + dimensionOffset
		val formatOffset = LAYOUT.withName("size").byteSize() + sizeOffset
		val mipLevelCountOffset = 4L + formatOffset
		val sampleCountOffset = 4L + mipLevelCountOffset
		val viewFormatCountOffset = 4L + sampleCountOffset
		val viewFormatsOffset = 8L + viewFormatCountOffset
	}
}

@JvmInline
actual value class WGPUTextureViewDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var label: CString?
		get() = TODO()
		set(newValue) = set("label", labelOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var dimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = set("dimension", dimensionOffset, newValue)

	actual var baseMipLevel: UInt
		get() = getUInt("baseMipLevel", baseMipLevelOffset)
		set(newValue) = set("baseMipLevel", baseMipLevelOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt("mipLevelCount", mipLevelCountOffset)
		set(newValue) = set("mipLevelCount", mipLevelCountOffset, newValue)

	actual var baseArrayLayer: UInt
		get() = getUInt("baseArrayLayer", baseArrayLayerOffset)
		set(newValue) = set("baseArrayLayer", baseArrayLayerOffset, newValue)

	actual var arrayLayerCount: UInt
		get() = getUInt("arrayLayerCount", arrayLayerCountOffset)
		set(newValue) = set("arrayLayerCount", arrayLayerCountOffset, newValue)

	actual var aspect: WGPUTextureAspect
		get() = TODO()
		set(newValue) = set("aspect", aspectOffset, newValue)

	actual var usage: ULong
		get() = getULong("usage", usageOffset)
		set(newValue) = set("usage", usageOffset, newValue)

	override fun getLayout(name: String)
		= WGPUTextureViewDescriptor.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("label"),
			C_INT.withName("format"),
			C_INT.withName("dimension"),
			C_INT.withName("baseMipLevel"),
			C_INT.withName("mipLevelCount"),
			C_INT.withName("baseArrayLayer"),
			C_INT.withName("arrayLayerCount"),
			C_INT.withName("aspect"),
			C_LONG.withName("usage")
		).withName("WGPUTextureViewDescriptor")

		val labelOffset = 0L
		val formatOffset = 8L + labelOffset
		val dimensionOffset = 4L + formatOffset
		val baseMipLevelOffset = 4L + dimensionOffset
		val mipLevelCountOffset = 4L + baseMipLevelOffset
		val baseArrayLayerOffset = 4L + mipLevelCountOffset
		val arrayLayerCountOffset = 4L + baseArrayLayerOffset
		val aspectOffset = 4L + arrayLayerCountOffset
		val usageOffset = 4L + aspectOffset
	}
}

@JvmInline
actual value class WGPUVertexAttribute(actual override val handler: NativeAddress) : CStructure {
	actual var format: WGPUVertexFormat
		get() = TODO()
		set(newValue) = set("format", formatOffset, newValue)

	actual var offset: ULong
		get() = getULong("offset", offsetOffset)
		set(newValue) = set("offset", offsetOffset, newValue)

	actual var shaderLocation: UInt
		get() = getUInt("shaderLocation", shaderLocationOffset)
		set(newValue) = set("shaderLocation", shaderLocationOffset, newValue)

	override fun getLayout(name: String)
		= WGPUVertexAttribute.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_LONG.withName("offset"),
			C_INT.withName("shaderLocation")
		).withName("WGPUVertexAttribute")

		val formatOffset = 0L
		val offsetOffset = 4L + formatOffset
		val shaderLocationOffset = 8L + offsetOffset
	}
}

@JvmInline
actual value class WGPUVertexBufferLayout(actual override val handler: NativeAddress) : CStructure {
	actual var arrayStride: ULong
		get() = getULong("arrayStride", arrayStrideOffset)
		set(newValue) = set("arrayStride", arrayStrideOffset, newValue)

	actual var stepMode: WGPUVertexStepMode
		get() = TODO()
		set(newValue) = set("stepMode", stepModeOffset, newValue)

	actual var attributeCount: ULong
		get() = getULong("attributeCount", attributeCountOffset)
		set(newValue) = set("attributeCount", attributeCountOffset, newValue)

	actual var attributes: ArrayHolder<WGPUVertexAttribute>?
		get() = TODO()
		set(newValue) = set("attributes", attributesOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUVertexBufferLayout.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_LONG.withName("arrayStride"),
			C_INT.withName("stepMode"),
			C_LONG.withName("attributeCount"),
			C_POINTER.withName("attributes")
		).withName("WGPUVertexBufferLayout")

		val arrayStrideOffset = 0L
		val stepModeOffset = 8L + arrayStrideOffset
		val attributeCountOffset = 4L + stepModeOffset
		val attributesOffset = 8L + attributeCountOffset
	}
}

@JvmInline
actual value class WGPUVertexState(actual override val handler: NativeAddress) : CStructure {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = set("module", moduleOffset, newValue?.handler)

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = set("entryPoint", entryPointOffset, newValue?.handler)

	actual var constantCount: ULong
		get() = getULong("constantCount", constantCountOffset)
		set(newValue) = set("constantCount", constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = set("constants", constantsOffset, newValue?.handler)

	actual var bufferCount: ULong
		get() = getULong("bufferCount", bufferCountOffset)
		set(newValue) = set("bufferCount", bufferCountOffset, newValue)

	actual var buffers: ArrayHolder<WGPUVertexBufferLayout>?
		get() = TODO()
		set(newValue) = set("buffers", buffersOffset, newValue?.handler)

	override fun getLayout(name: String)
		= WGPUVertexState.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("bufferCount"),
			C_POINTER.withName("buffers")
		).withName("WGPUVertexState")

		val moduleOffset = 0L
		val entryPointOffset = 8L + moduleOffset
		val constantCountOffset = 8L + entryPointOffset
		val constantsOffset = 8L + constantCountOffset
		val bufferCountOffset = 8L + constantsOffset
		val buffersOffset = 8L + bufferCountOffset
	}
}

@JvmInline
actual value class WGPUChainedStruct(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("next", nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = set("sType", sTypeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUChainedStruct.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStruct")

		val nextOffset = 0L
		val sTypeOffset = 8L + nextOffset
	}
}

@JvmInline
actual value class WGPUChainedStructOut(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStructOut?
		get() = TODO()
		set(newValue) = set("next", nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = set("sType", sTypeOffset, newValue)

	override fun getLayout(name: String)
		= WGPUChainedStructOut.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStructOut")

		val nextOffset = 0L
		val sTypeOffset = 8L + nextOffset
	}
}

@JvmInline
actual value class WGPUBufferMapCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUBufferMapCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUBufferMapCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUCompilationInfoCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUCompilationInfoCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCompilationInfoCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUCreateComputePipelineAsyncCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateComputePipelineAsyncCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUCreateRenderPipelineAsyncCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateRenderPipelineAsyncCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUDeviceLostCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUDeviceLostCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUDeviceLostCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUPopErrorScopeCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUPopErrorScopeCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUPopErrorScopeCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUQueueWorkDoneCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUQueueWorkDoneCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUQueueWorkDoneCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPURequestAdapterCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPURequestAdapterCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPURequestAdapterCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPURequestDeviceCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPURequestDeviceCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPURequestDeviceCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

@JvmInline
actual value class WGPUUncapturedErrorCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = set("nextInChain", nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
		get() = TODO()
		set(newValue) = set("callback", callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata1", userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = set("userdata2", userdata2Offset, newValue)

	override fun getLayout(name: String)
		= WGPUUncapturedErrorCallbackInfo.LAYOUT.withName(name) as AddressLayout

	companion object {
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUUncapturedErrorCallbackInfo")

		val nextInChainOffset = 0L
		val callbackOffset = 8L + nextInChainOffset
		val userdata1Offset = 8L + callbackOffset
		val userdata2Offset = 8L + userdata1Offset
	}
}

