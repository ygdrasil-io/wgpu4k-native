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
import ffi.MemoryAllocator
import ffi.toAddress
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout.Companion.structLayout

@JvmInline
actual value class WGPUAdapterInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUAdapterInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val vendor: WGPUStringView
		get() = handler.asSlice(vendorOffset).let(::WGPUStringView)

	actual val architecture: WGPUStringView
		get() = handler.asSlice(architectureOffset).let(::WGPUStringView)

	actual val device: WGPUStringView
		get() = handler.asSlice(deviceOffset).let(::WGPUStringView)

	actual val description: WGPUStringView
		get() = handler.asSlice(descriptionOffset).let(::WGPUStringView)

	actual var backendType: WGPUBackendType
		get() = getUInt(backendTypeOffset)
		set(newValue) = set(backendTypeOffset, newValue)

	actual var adapterType: WGPUAdapterType
		get() = getUInt(adapterTypeOffset)
		set(newValue) = set(adapterTypeOffset, newValue)

	actual var vendorID: UInt
		get() = getUInt(vendorIDOffset)
		set(newValue) = set(vendorIDOffset, newValue)

	actual var deviceID: UInt
		get() = getUInt(deviceIDOffset)
		set(newValue) = set(deviceIDOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUAdapterInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("vendor"),
			WGPUStringView.LAYOUT.withName("architecture"),
			WGPUStringView.LAYOUT.withName("device"),
			WGPUStringView.LAYOUT.withName("description"),
			C_INT.withName("backendType"),
			C_INT.withName("adapterType"),
			C_INT.withName("vendorID"),
			C_INT.withName("deviceID"),
		).withName("WGPUAdapterInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val vendorOffset = 8L + nextInChainOffset
		val vendorLayout = WGPUStringView.LAYOUT
		val architectureOffset = vendorLayout.byteSize() + vendorOffset
		val architectureLayout = WGPUStringView.LAYOUT
		val deviceOffset = architectureLayout.byteSize() + architectureOffset
		val deviceLayout = WGPUStringView.LAYOUT
		val descriptionOffset = deviceLayout.byteSize() + deviceOffset
		val descriptionLayout = WGPUStringView.LAYOUT
		val backendTypeOffset = descriptionLayout.byteSize() + descriptionOffset
		val backendTypeLayout = C_INT
		val adapterTypeOffset = 4L + backendTypeOffset
		val adapterTypeLayout = C_INT
		val vendorIDOffset = 4L + adapterTypeOffset
		val vendorIDLayout = C_INT
		val deviceIDOffset = 4L + vendorIDOffset
		val deviceIDLayout = C_INT
	}
}
@JvmInline
actual value class WGPUBindGroupDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBindGroupDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var layout: WGPUBindGroupLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUBindGroupLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual var entryCount: ULong
		get() = getULong(entryCountOffset)
		set(newValue) = set(entryCountOffset, newValue)

	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
		get() = get(entriesLayout, entriesOffset).let(::ArrayHolder)
		set(newValue) = set(entriesLayout, entriesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBindGroupDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("layout"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries"),
		).withName("WGPUBindGroupDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = labelLayout.byteSize() + labelOffset
		val layoutLayout = C_POINTER
		val entryCountOffset = 8L + layoutOffset
		val entryCountLayout = C_LONG
		val entriesOffset = 8L + entryCountOffset
		val entriesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUBindGroupEntry(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBindGroupEntryByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var binding: UInt
		get() = getUInt(bindingOffset)
		set(newValue) = set(bindingOffset, newValue)

	actual var buffer: WGPUBuffer?
		get() = get(bufferLayout, bufferOffset).let(::WGPUBuffer)
		set(newValue) = set(bufferLayout, bufferOffset, newValue?.handler)

	actual var offset: ULong
		get() = getULong(offsetOffset)
		set(newValue) = set(offsetOffset, newValue)

	actual var size: ULong
		get() = getULong(sizeOffset)
		set(newValue) = set(sizeOffset, newValue)

	actual var sampler: WGPUSampler?
		get() = get(samplerLayout, samplerOffset).let(::WGPUSampler)
		set(newValue) = set(samplerLayout, samplerOffset, newValue?.handler)

	actual var textureView: WGPUTextureView?
		get() = get(textureViewLayout, textureViewOffset).let(::WGPUTextureView)
		set(newValue) = set(textureViewLayout, textureViewOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBindGroupEntry)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("binding"),
			C_POINTER.withName("buffer"),
			C_LONG.withName("offset"),
			C_LONG.withName("size"),
			C_POINTER.withName("sampler"),
			C_POINTER.withName("textureView"),
		).withName("WGPUBindGroupEntry")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val bindingOffset = 8L + nextInChainOffset
		val bindingLayout = C_INT
		val bufferOffset = 4L + bindingOffset
		val bufferLayout = C_POINTER
		val offsetOffset = 8L + bufferOffset
		val offsetLayout = C_LONG
		val sizeOffset = 8L + offsetOffset
		val sizeLayout = C_LONG
		val samplerOffset = 8L + sizeOffset
		val samplerLayout = C_POINTER
		val textureViewOffset = 8L + samplerOffset
		val textureViewLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUBindGroupLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBindGroupLayoutDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var entryCount: ULong
		get() = getULong(entryCountOffset)
		set(newValue) = set(entryCountOffset, newValue)

	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
		get() = get(entriesLayout, entriesOffset).let(::ArrayHolder)
		set(newValue) = set(entriesLayout, entriesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBindGroupLayoutDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries"),
		).withName("WGPUBindGroupLayoutDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val entryCountOffset = labelLayout.byteSize() + labelOffset
		val entryCountLayout = C_LONG
		val entriesOffset = 8L + entryCountOffset
		val entriesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBindGroupLayoutEntryByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var binding: UInt
		get() = getUInt(bindingOffset)
		set(newValue) = set(bindingOffset, newValue)

	actual var visibility: ULong
		get() = getULong(visibilityOffset)
		set(newValue) = set(visibilityOffset, newValue)

	actual val buffer: WGPUBufferBindingLayout
		get() = handler.asSlice(bufferOffset).let(::WGPUBufferBindingLayout)

	actual val sampler: WGPUSamplerBindingLayout
		get() = handler.asSlice(samplerOffset).let(::WGPUSamplerBindingLayout)

	actual val texture: WGPUTextureBindingLayout
		get() = handler.asSlice(textureOffset).let(::WGPUTextureBindingLayout)

	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = handler.asSlice(storageTextureOffset).let(::WGPUStorageTextureBindingLayout)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBindGroupLayoutEntry)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("binding"),
			C_LONG.withName("visibility"),
			WGPUBufferBindingLayout.LAYOUT.withName("buffer"),
			WGPUSamplerBindingLayout.LAYOUT.withName("sampler"),
			WGPUTextureBindingLayout.LAYOUT.withName("texture"),
			WGPUStorageTextureBindingLayout.LAYOUT.withName("storageTexture"),
		).withName("WGPUBindGroupLayoutEntry")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val bindingOffset = 8L + nextInChainOffset
		val bindingLayout = C_INT
		val visibilityOffset = 4L + bindingOffset
		val visibilityLayout = C_LONG
		val bufferOffset = 8L + visibilityOffset
		val bufferLayout = WGPUBufferBindingLayout.LAYOUT
		val samplerOffset = bufferLayout.byteSize() + bufferOffset
		val samplerLayout = WGPUSamplerBindingLayout.LAYOUT
		val textureOffset = samplerLayout.byteSize() + samplerOffset
		val textureLayout = WGPUTextureBindingLayout.LAYOUT
		val storageTextureOffset = textureLayout.byteSize() + textureOffset
		val storageTextureLayout = WGPUStorageTextureBindingLayout.LAYOUT
	}
}
@JvmInline
actual value class WGPUBlendComponent(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBlendComponentByValue(handler.pointer.toAddress())
	actual var operation: WGPUBlendOperation
		get() = getUInt(operationOffset)
		set(newValue) = set(operationOffset, newValue)

	actual var srcFactor: WGPUBlendFactor
		get() = getUInt(srcFactorOffset)
		set(newValue) = set(srcFactorOffset, newValue)

	actual var dstFactor: WGPUBlendFactor
		get() = getUInt(dstFactorOffset)
		set(newValue) = set(dstFactorOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBlendComponent)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("operation"),
			C_INT.withName("srcFactor"),
			C_INT.withName("dstFactor"),
		).withName("WGPUBlendComponent")
		val operationOffset = 0L
		val operationLayout = C_INT
		val srcFactorOffset = 4L + operationOffset
		val srcFactorLayout = C_INT
		val dstFactorOffset = 4L + srcFactorOffset
		val dstFactorLayout = C_INT
	}
}
@JvmInline
actual value class WGPUBlendState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBlendStateByValue(handler.pointer.toAddress())
	actual val color: WGPUBlendComponent
		get() = handler.asSlice(colorOffset).let(::WGPUBlendComponent)

	actual val alpha: WGPUBlendComponent
		get() = handler.asSlice(alphaOffset).let(::WGPUBlendComponent)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBlendState)
		}
		internal val LAYOUT = structLayout(
			WGPUBlendComponent.LAYOUT.withName("color"),
			WGPUBlendComponent.LAYOUT.withName("alpha"),
		).withName("WGPUBlendState")
		val colorOffset = 0L
		val colorLayout = WGPUBlendComponent.LAYOUT
		val alphaOffset = colorLayout.byteSize() + colorOffset
		val alphaLayout = WGPUBlendComponent.LAYOUT
	}
}
@JvmInline
actual value class WGPUBufferBindingLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBufferBindingLayoutByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var type: WGPUBufferBindingType
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)

	actual var hasDynamicOffset: Boolean
		get() = getInt(hasDynamicOffsetOffset).toBoolean()
		set(newValue) = set(hasDynamicOffsetOffset, newValue)

	actual var minBindingSize: ULong
		get() = getULong(minBindingSizeOffset)
		set(newValue) = set(minBindingSizeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBufferBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("type"),
			C_INT.withName("hasDynamicOffset"),
			C_LONG.withName("minBindingSize"),
		).withName("WGPUBufferBindingLayout")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val typeOffset = 8L + nextInChainOffset
		val typeLayout = C_INT
		val hasDynamicOffsetOffset = 4L + typeOffset
		val hasDynamicOffsetLayout = C_INT
		val minBindingSizeOffset = 4L + hasDynamicOffsetOffset
		val minBindingSizeLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBufferDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var usage: ULong
		get() = getULong(usageOffset)
		set(newValue) = set(usageOffset, newValue)

	actual var size: ULong
		get() = getULong(sizeOffset)
		set(newValue) = set(sizeOffset, newValue)

	actual var mappedAtCreation: Boolean
		get() = getInt(mappedAtCreationOffset).toBoolean()
		set(newValue) = set(mappedAtCreationOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBufferDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("usage"),
			C_LONG.withName("size"),
			C_INT.withName("mappedAtCreation"),
		).withName("WGPUBufferDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val usageOffset = labelLayout.byteSize() + labelOffset
		val usageLayout = C_LONG
		val sizeOffset = 8L + usageOffset
		val sizeLayout = C_LONG
		val mappedAtCreationOffset = 8L + sizeOffset
		val mappedAtCreationLayout = C_INT
	}
}
@JvmInline
actual value class WGPUColor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUColorByValue(handler.pointer.toAddress())
	actual var r: Double
		get() = getDouble(rOffset)
		set(newValue) = set(rOffset, newValue)

	actual var g: Double
		get() = getDouble(gOffset)
		set(newValue) = set(gOffset, newValue)

	actual var b: Double
		get() = getDouble(bOffset)
		set(newValue) = set(bOffset, newValue)

	actual var a: Double
		get() = getDouble(aOffset)
		set(newValue) = set(aOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUColor)
		}
		internal val LAYOUT = structLayout(
			C_DOUBLE.withName("r"),
			C_DOUBLE.withName("g"),
			C_DOUBLE.withName("b"),
			C_DOUBLE.withName("a"),
		).withName("WGPUColor")
		val rOffset = 0L
		val rLayout = C_DOUBLE
		val gOffset = 8L + rOffset
		val gLayout = C_DOUBLE
		val bOffset = 8L + gOffset
		val bLayout = C_DOUBLE
		val aOffset = 8L + bOffset
		val aLayout = C_DOUBLE
	}
}
@JvmInline
actual value class WGPUColorTargetState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUColorTargetStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var blend: WGPUBlendState?
		get() = get(blendLayout, blendOffset).let(::WGPUBlendState)
		set(newValue) = set(blendLayout, blendOffset, newValue?.handler)

	actual var writeMask: ULong
		get() = getULong(writeMaskOffset)
		set(newValue) = set(writeMaskOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUColorTargetState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("format"),
			C_POINTER.withName("blend"),
			C_LONG.withName("writeMask"),
		).withName("WGPUColorTargetState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val formatOffset = 8L + nextInChainOffset
		val formatLayout = C_INT
		val blendOffset = 4L + formatOffset
		val blendLayout = C_POINTER
		val writeMaskOffset = 8L + blendOffset
		val writeMaskLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUCommandBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCommandBufferDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCommandBufferDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUCommandBufferDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUCommandEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCommandEncoderDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCommandEncoderDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUCommandEncoderDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUCompilationInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCompilationInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var messageCount: ULong
		get() = getULong(messageCountOffset)
		set(newValue) = set(messageCountOffset, newValue)

	actual var messages: ArrayHolder<WGPUCompilationMessage>?
		get() = get(messagesLayout, messagesOffset).let(::ArrayHolder)
		set(newValue) = set(messagesLayout, messagesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCompilationInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("messageCount"),
			C_POINTER.withName("messages"),
		).withName("WGPUCompilationInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val messageCountOffset = 8L + nextInChainOffset
		val messageCountLayout = C_LONG
		val messagesOffset = 8L + messageCountOffset
		val messagesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUCompilationMessage(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCompilationMessageByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val message: WGPUStringView
		get() = handler.asSlice(messageOffset).let(::WGPUStringView)

	actual var type: WGPUCompilationMessageType
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)

	actual var lineNum: ULong
		get() = getULong(lineNumOffset)
		set(newValue) = set(lineNumOffset, newValue)

	actual var linePos: ULong
		get() = getULong(linePosOffset)
		set(newValue) = set(linePosOffset, newValue)

	actual var offset: ULong
		get() = getULong(offsetOffset)
		set(newValue) = set(offsetOffset, newValue)

	actual var length: ULong
		get() = getULong(lengthOffset)
		set(newValue) = set(lengthOffset, newValue)

	actual var utf16LinePos: ULong
		get() = getULong(utf16LinePosOffset)
		set(newValue) = set(utf16LinePosOffset, newValue)

	actual var utf16Offset: ULong
		get() = getULong(utf16OffsetOffset)
		set(newValue) = set(utf16OffsetOffset, newValue)

	actual var utf16Length: ULong
		get() = getULong(utf16LengthOffset)
		set(newValue) = set(utf16LengthOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCompilationMessage)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("message"),
			C_INT.withName("type"),
			C_LONG.withName("lineNum"),
			C_LONG.withName("linePos"),
			C_LONG.withName("offset"),
			C_LONG.withName("length"),
			C_LONG.withName("utf16LinePos"),
			C_LONG.withName("utf16Offset"),
			C_LONG.withName("utf16Length"),
		).withName("WGPUCompilationMessage")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val messageOffset = 8L + nextInChainOffset
		val messageLayout = WGPUStringView.LAYOUT
		val typeOffset = messageLayout.byteSize() + messageOffset
		val typeLayout = C_INT
		val lineNumOffset = 4L + typeOffset
		val lineNumLayout = C_LONG
		val linePosOffset = 8L + lineNumOffset
		val linePosLayout = C_LONG
		val offsetOffset = 8L + linePosOffset
		val offsetLayout = C_LONG
		val lengthOffset = 8L + offsetOffset
		val lengthLayout = C_LONG
		val utf16LinePosOffset = 8L + lengthOffset
		val utf16LinePosLayout = C_LONG
		val utf16OffsetOffset = 8L + utf16LinePosOffset
		val utf16OffsetLayout = C_LONG
		val utf16LengthOffset = 8L + utf16OffsetOffset
		val utf16LengthLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUComputePassDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUComputePassDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = get(timestampWritesLayout, timestampWritesOffset).let(::WGPUComputePassTimestampWrites)
		set(newValue) = set(timestampWritesLayout, timestampWritesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUComputePassDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("timestampWrites"),
		).withName("WGPUComputePassDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val timestampWritesOffset = labelLayout.byteSize() + labelOffset
		val timestampWritesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUComputePassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUComputePassTimestampWritesByValue(handler.pointer.toAddress())
	actual var querySet: WGPUQuerySet?
		get() = get(querySetLayout, querySetOffset).let(::WGPUQuerySet)
		set(newValue) = set(querySetLayout, querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt(beginningOfPassWriteIndexOffset)
		set(newValue) = set(beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt(endOfPassWriteIndexOffset)
		set(newValue) = set(endOfPassWriteIndexOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassTimestampWrites {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUComputePassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex"),
		).withName("WGPUComputePassTimestampWrites")
		val querySetOffset = 0L
		val querySetLayout = C_POINTER
		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val beginningOfPassWriteIndexLayout = C_INT
		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
		val endOfPassWriteIndexLayout = C_INT
	}
}
@JvmInline
actual value class WGPUComputePipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUComputePipelineDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var layout: WGPUPipelineLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUPipelineLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual val compute: WGPUProgrammableStageDescriptor
		get() = handler.asSlice(computeOffset).let(::WGPUProgrammableStageDescriptor)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUComputePipelineDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("layout"),
			WGPUProgrammableStageDescriptor.LAYOUT.withName("compute"),
		).withName("WGPUComputePipelineDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = labelLayout.byteSize() + labelOffset
		val layoutLayout = C_POINTER
		val computeOffset = 8L + layoutOffset
		val computeLayout = WGPUProgrammableStageDescriptor.LAYOUT
	}
}
@JvmInline
actual value class WGPUConstantEntry(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUConstantEntryByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val key: WGPUStringView
		get() = handler.asSlice(keyOffset).let(::WGPUStringView)

	actual var value: Double
		get() = getDouble(valueOffset)
		set(newValue) = set(valueOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUConstantEntry)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("key"),
			C_DOUBLE.withName("value"),
		).withName("WGPUConstantEntry")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val keyOffset = 8L + nextInChainOffset
		val keyLayout = WGPUStringView.LAYOUT
		val valueOffset = keyLayout.byteSize() + keyOffset
		val valueLayout = C_DOUBLE
	}
}
@JvmInline
actual value class WGPUDepthStencilState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUDepthStencilStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var depthWriteEnabled: WGPUOptionalBool
		get() = getUInt(depthWriteEnabledOffset)
		set(newValue) = set(depthWriteEnabledOffset, newValue)

	actual var depthCompare: WGPUCompareFunction
		get() = getUInt(depthCompareOffset)
		set(newValue) = set(depthCompareOffset, newValue)

	actual val stencilFront: WGPUStencilFaceState
		get() = handler.asSlice(stencilFrontOffset).let(::WGPUStencilFaceState)

	actual val stencilBack: WGPUStencilFaceState
		get() = handler.asSlice(stencilBackOffset).let(::WGPUStencilFaceState)

	actual var stencilReadMask: UInt
		get() = getUInt(stencilReadMaskOffset)
		set(newValue) = set(stencilReadMaskOffset, newValue)

	actual var stencilWriteMask: UInt
		get() = getUInt(stencilWriteMaskOffset)
		set(newValue) = set(stencilWriteMaskOffset, newValue)

	actual var depthBias: Int
		get() = getInt(depthBiasOffset)
		set(newValue) = set(depthBiasOffset, newValue)

	actual var depthBiasSlopeScale: Float
		get() = getFloat(depthBiasSlopeScaleOffset)
		set(newValue) = set(depthBiasSlopeScaleOffset, newValue)

	actual var depthBiasClamp: Float
		get() = getFloat(depthBiasClampOffset)
		set(newValue) = set(depthBiasClampOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUDepthStencilState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("format"),
			C_INT.withName("depthWriteEnabled"),
			C_INT.withName("depthCompare"),
			WGPUStencilFaceState.LAYOUT.withName("stencilFront"),
			WGPUStencilFaceState.LAYOUT.withName("stencilBack"),
			C_INT.withName("stencilReadMask"),
			C_INT.withName("stencilWriteMask"),
			C_INT.withName("depthBias"),
			C_FLOAT.withName("depthBiasSlopeScale"),
			C_FLOAT.withName("depthBiasClamp"),
		).withName("WGPUDepthStencilState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val formatOffset = 8L + nextInChainOffset
		val formatLayout = C_INT
		val depthWriteEnabledOffset = 4L + formatOffset
		val depthWriteEnabledLayout = C_INT
		val depthCompareOffset = 4L + depthWriteEnabledOffset
		val depthCompareLayout = C_INT
		val stencilFrontOffset = 4L + depthCompareOffset
		val stencilFrontLayout = WGPUStencilFaceState.LAYOUT
		val stencilBackOffset = stencilFrontLayout.byteSize() + stencilFrontOffset
		val stencilBackLayout = WGPUStencilFaceState.LAYOUT
		val stencilReadMaskOffset = stencilBackLayout.byteSize() + stencilBackOffset
		val stencilReadMaskLayout = C_INT
		val stencilWriteMaskOffset = 4L + stencilReadMaskOffset
		val stencilWriteMaskLayout = C_INT
		val depthBiasOffset = 4L + stencilWriteMaskOffset
		val depthBiasLayout = C_INT
		val depthBiasSlopeScaleOffset = 4L + depthBiasOffset
		val depthBiasSlopeScaleLayout = C_FLOAT
		val depthBiasClampOffset = 4L + depthBiasSlopeScaleOffset
		val depthBiasClampLayout = C_FLOAT
	}
}
@JvmInline
actual value class WGPUDeviceDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUDeviceDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var requiredFeatureCount: ULong
		get() = getULong(requiredFeatureCountOffset)
		set(newValue) = set(requiredFeatureCountOffset, newValue)

	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
		get() = get(requiredFeaturesLayout, requiredFeaturesOffset).let(::ArrayHolder)
		set(newValue) = set(requiredFeaturesLayout, requiredFeaturesOffset, newValue?.handler)

	actual var requiredLimits: WGPURequiredLimits?
		get() = get(requiredLimitsLayout, requiredLimitsOffset).let(::WGPURequiredLimits)
		set(newValue) = set(requiredLimitsLayout, requiredLimitsOffset, newValue?.handler)

	actual val defaultQueue: WGPUQueueDescriptor
		get() = handler.asSlice(defaultQueueOffset).let(::WGPUQueueDescriptor)

	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
		get() = handler.asSlice(deviceLostCallbackInfoOffset).let(::WGPUDeviceLostCallbackInfo)

	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
		get() = handler.asSlice(uncapturedErrorCallbackInfoOffset).let(::WGPUUncapturedErrorCallbackInfo)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUDeviceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("requiredFeatureCount"),
			C_POINTER.withName("requiredFeatures"),
			C_POINTER.withName("requiredLimits"),
			WGPUQueueDescriptor.LAYOUT.withName("defaultQueue"),
			WGPUDeviceLostCallbackInfo.LAYOUT.withName("deviceLostCallbackInfo"),
			WGPUUncapturedErrorCallbackInfo.LAYOUT.withName("uncapturedErrorCallbackInfo"),
		).withName("WGPUDeviceDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val requiredFeatureCountOffset = labelLayout.byteSize() + labelOffset
		val requiredFeatureCountLayout = C_LONG
		val requiredFeaturesOffset = 8L + requiredFeatureCountOffset
		val requiredFeaturesLayout = C_POINTER
		val requiredLimitsOffset = 8L + requiredFeaturesOffset
		val requiredLimitsLayout = C_POINTER
		val defaultQueueOffset = 8L + requiredLimitsOffset
		val defaultQueueLayout = WGPUQueueDescriptor.LAYOUT
		val deviceLostCallbackInfoOffset = defaultQueueLayout.byteSize() + defaultQueueOffset
		val deviceLostCallbackInfoLayout = WGPUDeviceLostCallbackInfo.LAYOUT
		val uncapturedErrorCallbackInfoOffset = deviceLostCallbackInfoLayout.byteSize() + deviceLostCallbackInfoOffset
		val uncapturedErrorCallbackInfoLayout = WGPUUncapturedErrorCallbackInfo.LAYOUT
	}
}
@JvmInline
actual value class WGPUExtent3D(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUExtent3DByValue(handler.pointer.toAddress())
	actual var width: UInt
		get() = getUInt(widthOffset)
		set(newValue) = set(widthOffset, newValue)

	actual var height: UInt
		get() = getUInt(heightOffset)
		set(newValue) = set(heightOffset, newValue)

	actual var depthOrArrayLayers: UInt
		get() = getUInt(depthOrArrayLayersOffset)
		set(newValue) = set(depthOrArrayLayersOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUExtent3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_INT.withName("depthOrArrayLayers"),
		).withName("WGPUExtent3D")
		val widthOffset = 0L
		val widthLayout = C_INT
		val heightOffset = 4L + widthOffset
		val heightLayout = C_INT
		val depthOrArrayLayersOffset = 4L + heightOffset
		val depthOrArrayLayersLayout = C_INT
	}
}
@JvmInline
actual value class WGPUFragmentState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUFragmentStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = handler.asSlice(entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountOffset)
		set(newValue) = set(constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual var targetCount: ULong
		get() = getULong(targetCountOffset)
		set(newValue) = set(targetCountOffset, newValue)

	actual var targets: ArrayHolder<WGPUColorTargetState>?
		get() = get(targetsLayout, targetsOffset).let(::ArrayHolder)
		set(newValue) = set(targetsLayout, targetsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFragmentState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUFragmentState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("module"),
			WGPUStringView.LAYOUT.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("targetCount"),
			C_POINTER.withName("targets"),
		).withName("WGPUFragmentState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = entryPointLayout.byteSize() + entryPointOffset
		val constantCountLayout = C_LONG
		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = C_POINTER
		val targetCountOffset = 8L + constantsOffset
		val targetCountLayout = C_LONG
		val targetsOffset = 8L + targetCountOffset
		val targetsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUFuture(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUFutureByValue(handler.pointer.toAddress())
	actual var id: ULong
		get() = getULong(idOffset)
		set(newValue) = set(idOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUFuture)
		}
		internal val LAYOUT = structLayout(
			C_LONG.withName("id"),
		).withName("WGPUFuture")
		val idOffset = 0L
		val idLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUFutureWaitInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUFutureWaitInfoByValue(handler.pointer.toAddress())
	actual val future: WGPUFuture
		get() = handler.asSlice(futureOffset).let(::WGPUFuture)

	actual var completed: Boolean
		get() = getInt(completedOffset).toBoolean()
		set(newValue) = set(completedOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUFutureWaitInfo)
		}
		internal val LAYOUT = structLayout(
			WGPUFuture.LAYOUT.withName("future"),
			C_INT.withName("completed"),
		).withName("WGPUFutureWaitInfo")
		val futureOffset = 0L
		val futureLayout = WGPUFuture.LAYOUT
		val completedOffset = futureLayout.byteSize() + futureOffset
		val completedLayout = C_INT
	}
}
@JvmInline
actual value class WGPUImageCopyBuffer(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUImageCopyBufferByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val layout: WGPUTextureDataLayout
		get() = handler.asSlice(layoutOffset).let(::WGPUTextureDataLayout)

	actual var buffer: WGPUBuffer?
		get() = get(bufferLayout, bufferOffset).let(::WGPUBuffer)
		set(newValue) = set(bufferLayout, bufferOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyBuffer {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUImageCopyBuffer)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUTextureDataLayout.LAYOUT.withName("layout"),
			C_POINTER.withName("buffer"),
		).withName("WGPUImageCopyBuffer")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val layoutOffset = 8L + nextInChainOffset
		val layoutLayout = WGPUTextureDataLayout.LAYOUT
		val bufferOffset = layoutLayout.byteSize() + layoutOffset
		val bufferLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUImageCopyTexture(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUImageCopyTextureByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var texture: WGPUTexture?
		get() = get(textureLayout, textureOffset).let(::WGPUTexture)
		set(newValue) = set(textureLayout, textureOffset, newValue?.handler)

	actual var mipLevel: UInt
		get() = getUInt(mipLevelOffset)
		set(newValue) = set(mipLevelOffset, newValue)

	actual val origin: WGPUOrigin3D
		get() = handler.asSlice(originOffset).let(::WGPUOrigin3D)

	actual var aspect: WGPUTextureAspect
		get() = getUInt(aspectOffset)
		set(newValue) = set(aspectOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyTexture {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUImageCopyTexture)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("texture"),
			C_INT.withName("mipLevel"),
			WGPUOrigin3D.LAYOUT.withName("origin"),
			C_INT.withName("aspect"),
		).withName("WGPUImageCopyTexture")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val textureOffset = 8L + nextInChainOffset
		val textureLayout = C_POINTER
		val mipLevelOffset = 8L + textureOffset
		val mipLevelLayout = C_INT
		val originOffset = 4L + mipLevelOffset
		val originLayout = WGPUOrigin3D.LAYOUT
		val aspectOffset = originLayout.byteSize() + originOffset
		val aspectLayout = C_INT
	}
}
@JvmInline
actual value class WGPUInstanceDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUInstanceDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val features: WGPUInstanceFeatures
		get() = handler.asSlice(featuresOffset).let(::WGPUInstanceFeatures)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUInstanceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUInstanceFeatures.LAYOUT.withName("features"),
		).withName("WGPUInstanceDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val featuresOffset = 8L + nextInChainOffset
		val featuresLayout = WGPUInstanceFeatures.LAYOUT
	}
}
@JvmInline
actual value class WGPUInstanceFeatures(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUInstanceFeaturesByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var timedWaitAnyEnable: Boolean
		get() = getInt(timedWaitAnyEnableOffset).toBoolean()
		set(newValue) = set(timedWaitAnyEnableOffset, newValue)

	actual var timedWaitAnyMaxCount: ULong
		get() = getULong(timedWaitAnyMaxCountOffset)
		set(newValue) = set(timedWaitAnyMaxCountOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceFeatures {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUInstanceFeatures)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("timedWaitAnyEnable"),
			C_LONG.withName("timedWaitAnyMaxCount"),
		).withName("WGPUInstanceFeatures")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val timedWaitAnyEnableOffset = 8L + nextInChainOffset
		val timedWaitAnyEnableLayout = C_INT
		val timedWaitAnyMaxCountOffset = 4L + timedWaitAnyEnableOffset
		val timedWaitAnyMaxCountLayout = C_LONG
	}
}
@JvmInline
actual value class WGPULimits(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPULimitsByValue(handler.pointer.toAddress())
	actual var maxTextureDimension1D: UInt
		get() = getUInt(maxTextureDimension1DOffset)
		set(newValue) = set(maxTextureDimension1DOffset, newValue)

	actual var maxTextureDimension2D: UInt
		get() = getUInt(maxTextureDimension2DOffset)
		set(newValue) = set(maxTextureDimension2DOffset, newValue)

	actual var maxTextureDimension3D: UInt
		get() = getUInt(maxTextureDimension3DOffset)
		set(newValue) = set(maxTextureDimension3DOffset, newValue)

	actual var maxTextureArrayLayers: UInt
		get() = getUInt(maxTextureArrayLayersOffset)
		set(newValue) = set(maxTextureArrayLayersOffset, newValue)

	actual var maxBindGroups: UInt
		get() = getUInt(maxBindGroupsOffset)
		set(newValue) = set(maxBindGroupsOffset, newValue)

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = getUInt(maxBindGroupsPlusVertexBuffersOffset)
		set(newValue) = set(maxBindGroupsPlusVertexBuffersOffset, newValue)

	actual var maxBindingsPerBindGroup: UInt
		get() = getUInt(maxBindingsPerBindGroupOffset)
		set(newValue) = set(maxBindingsPerBindGroupOffset, newValue)

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = getUInt(maxDynamicUniformBuffersPerPipelineLayoutOffset)
		set(newValue) = set(maxDynamicUniformBuffersPerPipelineLayoutOffset, newValue)

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = getUInt(maxDynamicStorageBuffersPerPipelineLayoutOffset)
		set(newValue) = set(maxDynamicStorageBuffersPerPipelineLayoutOffset, newValue)

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = getUInt(maxSampledTexturesPerShaderStageOffset)
		set(newValue) = set(maxSampledTexturesPerShaderStageOffset, newValue)

	actual var maxSamplersPerShaderStage: UInt
		get() = getUInt(maxSamplersPerShaderStageOffset)
		set(newValue) = set(maxSamplersPerShaderStageOffset, newValue)

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = getUInt(maxStorageBuffersPerShaderStageOffset)
		set(newValue) = set(maxStorageBuffersPerShaderStageOffset, newValue)

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = getUInt(maxStorageTexturesPerShaderStageOffset)
		set(newValue) = set(maxStorageTexturesPerShaderStageOffset, newValue)

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = getUInt(maxUniformBuffersPerShaderStageOffset)
		set(newValue) = set(maxUniformBuffersPerShaderStageOffset, newValue)

	actual var maxUniformBufferBindingSize: ULong
		get() = getULong(maxUniformBufferBindingSizeOffset)
		set(newValue) = set(maxUniformBufferBindingSizeOffset, newValue)

	actual var maxStorageBufferBindingSize: ULong
		get() = getULong(maxStorageBufferBindingSizeOffset)
		set(newValue) = set(maxStorageBufferBindingSizeOffset, newValue)

	actual var minUniformBufferOffsetAlignment: UInt
		get() = getUInt(minUniformBufferOffsetAlignmentOffset)
		set(newValue) = set(minUniformBufferOffsetAlignmentOffset, newValue)

	actual var minStorageBufferOffsetAlignment: UInt
		get() = getUInt(minStorageBufferOffsetAlignmentOffset)
		set(newValue) = set(minStorageBufferOffsetAlignmentOffset, newValue)

	actual var maxVertexBuffers: UInt
		get() = getUInt(maxVertexBuffersOffset)
		set(newValue) = set(maxVertexBuffersOffset, newValue)

	actual var maxBufferSize: ULong
		get() = getULong(maxBufferSizeOffset)
		set(newValue) = set(maxBufferSizeOffset, newValue)

	actual var maxVertexAttributes: UInt
		get() = getUInt(maxVertexAttributesOffset)
		set(newValue) = set(maxVertexAttributesOffset, newValue)

	actual var maxVertexBufferArrayStride: UInt
		get() = getUInt(maxVertexBufferArrayStrideOffset)
		set(newValue) = set(maxVertexBufferArrayStrideOffset, newValue)

	actual var maxInterStageShaderVariables: UInt
		get() = getUInt(maxInterStageShaderVariablesOffset)
		set(newValue) = set(maxInterStageShaderVariablesOffset, newValue)

	actual var maxColorAttachments: UInt
		get() = getUInt(maxColorAttachmentsOffset)
		set(newValue) = set(maxColorAttachmentsOffset, newValue)

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = getUInt(maxColorAttachmentBytesPerSampleOffset)
		set(newValue) = set(maxColorAttachmentBytesPerSampleOffset, newValue)

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = getUInt(maxComputeWorkgroupStorageSizeOffset)
		set(newValue) = set(maxComputeWorkgroupStorageSizeOffset, newValue)

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = getUInt(maxComputeInvocationsPerWorkgroupOffset)
		set(newValue) = set(maxComputeInvocationsPerWorkgroupOffset, newValue)

	actual var maxComputeWorkgroupSizeX: UInt
		get() = getUInt(maxComputeWorkgroupSizeXOffset)
		set(newValue) = set(maxComputeWorkgroupSizeXOffset, newValue)

	actual var maxComputeWorkgroupSizeY: UInt
		get() = getUInt(maxComputeWorkgroupSizeYOffset)
		set(newValue) = set(maxComputeWorkgroupSizeYOffset, newValue)

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = getUInt(maxComputeWorkgroupSizeZOffset)
		set(newValue) = set(maxComputeWorkgroupSizeZOffset, newValue)

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = getUInt(maxComputeWorkgroupsPerDimensionOffset)
		set(newValue) = set(maxComputeWorkgroupsPerDimensionOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPULimits {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPULimits)
		}
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
			C_INT.withName("maxComputeWorkgroupsPerDimension"),
		).withName("WGPULimits")
		val maxTextureDimension1DOffset = 0L
		val maxTextureDimension1DLayout = C_INT
		val maxTextureDimension2DOffset = 4L + maxTextureDimension1DOffset
		val maxTextureDimension2DLayout = C_INT
		val maxTextureDimension3DOffset = 4L + maxTextureDimension2DOffset
		val maxTextureDimension3DLayout = C_INT
		val maxTextureArrayLayersOffset = 4L + maxTextureDimension3DOffset
		val maxTextureArrayLayersLayout = C_INT
		val maxBindGroupsOffset = 4L + maxTextureArrayLayersOffset
		val maxBindGroupsLayout = C_INT
		val maxBindGroupsPlusVertexBuffersOffset = 4L + maxBindGroupsOffset
		val maxBindGroupsPlusVertexBuffersLayout = C_INT
		val maxBindingsPerBindGroupOffset = 4L + maxBindGroupsPlusVertexBuffersOffset
		val maxBindingsPerBindGroupLayout = C_INT
		val maxDynamicUniformBuffersPerPipelineLayoutOffset = 4L + maxBindingsPerBindGroupOffset
		val maxDynamicUniformBuffersPerPipelineLayoutLayout = C_INT
		val maxDynamicStorageBuffersPerPipelineLayoutOffset = 4L + maxDynamicUniformBuffersPerPipelineLayoutOffset
		val maxDynamicStorageBuffersPerPipelineLayoutLayout = C_INT
		val maxSampledTexturesPerShaderStageOffset = 4L + maxDynamicStorageBuffersPerPipelineLayoutOffset
		val maxSampledTexturesPerShaderStageLayout = C_INT
		val maxSamplersPerShaderStageOffset = 4L + maxSampledTexturesPerShaderStageOffset
		val maxSamplersPerShaderStageLayout = C_INT
		val maxStorageBuffersPerShaderStageOffset = 4L + maxSamplersPerShaderStageOffset
		val maxStorageBuffersPerShaderStageLayout = C_INT
		val maxStorageTexturesPerShaderStageOffset = 4L + maxStorageBuffersPerShaderStageOffset
		val maxStorageTexturesPerShaderStageLayout = C_INT
		val maxUniformBuffersPerShaderStageOffset = 4L + maxStorageTexturesPerShaderStageOffset
		val maxUniformBuffersPerShaderStageLayout = C_INT
		val maxUniformBufferBindingSizeOffset = 4L + maxUniformBuffersPerShaderStageOffset
		val maxUniformBufferBindingSizeLayout = C_LONG
		val maxStorageBufferBindingSizeOffset = 8L + maxUniformBufferBindingSizeOffset
		val maxStorageBufferBindingSizeLayout = C_LONG
		val minUniformBufferOffsetAlignmentOffset = 8L + maxStorageBufferBindingSizeOffset
		val minUniformBufferOffsetAlignmentLayout = C_INT
		val minStorageBufferOffsetAlignmentOffset = 4L + minUniformBufferOffsetAlignmentOffset
		val minStorageBufferOffsetAlignmentLayout = C_INT
		val maxVertexBuffersOffset = 4L + minStorageBufferOffsetAlignmentOffset
		val maxVertexBuffersLayout = C_INT
		val maxBufferSizeOffset = 4L + maxVertexBuffersOffset
		val maxBufferSizeLayout = C_LONG
		val maxVertexAttributesOffset = 8L + maxBufferSizeOffset
		val maxVertexAttributesLayout = C_INT
		val maxVertexBufferArrayStrideOffset = 4L + maxVertexAttributesOffset
		val maxVertexBufferArrayStrideLayout = C_INT
		val maxInterStageShaderVariablesOffset = 4L + maxVertexBufferArrayStrideOffset
		val maxInterStageShaderVariablesLayout = C_INT
		val maxColorAttachmentsOffset = 4L + maxInterStageShaderVariablesOffset
		val maxColorAttachmentsLayout = C_INT
		val maxColorAttachmentBytesPerSampleOffset = 4L + maxColorAttachmentsOffset
		val maxColorAttachmentBytesPerSampleLayout = C_INT
		val maxComputeWorkgroupStorageSizeOffset = 4L + maxColorAttachmentBytesPerSampleOffset
		val maxComputeWorkgroupStorageSizeLayout = C_INT
		val maxComputeInvocationsPerWorkgroupOffset = 4L + maxComputeWorkgroupStorageSizeOffset
		val maxComputeInvocationsPerWorkgroupLayout = C_INT
		val maxComputeWorkgroupSizeXOffset = 4L + maxComputeInvocationsPerWorkgroupOffset
		val maxComputeWorkgroupSizeXLayout = C_INT
		val maxComputeWorkgroupSizeYOffset = 4L + maxComputeWorkgroupSizeXOffset
		val maxComputeWorkgroupSizeYLayout = C_INT
		val maxComputeWorkgroupSizeZOffset = 4L + maxComputeWorkgroupSizeYOffset
		val maxComputeWorkgroupSizeZLayout = C_INT
		val maxComputeWorkgroupsPerDimensionOffset = 4L + maxComputeWorkgroupSizeZOffset
		val maxComputeWorkgroupsPerDimensionLayout = C_INT
	}
}
@JvmInline
actual value class WGPUMultisampleState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUMultisampleStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var count: UInt
		get() = getUInt(countOffset)
		set(newValue) = set(countOffset, newValue)

	actual var mask: UInt
		get() = getUInt(maskOffset)
		set(newValue) = set(maskOffset, newValue)

	actual var alphaToCoverageEnabled: Boolean
		get() = getInt(alphaToCoverageEnabledOffset).toBoolean()
		set(newValue) = set(alphaToCoverageEnabledOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUMultisampleState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("count"),
			C_INT.withName("mask"),
			C_INT.withName("alphaToCoverageEnabled"),
		).withName("WGPUMultisampleState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val countOffset = 8L + nextInChainOffset
		val countLayout = C_INT
		val maskOffset = 4L + countOffset
		val maskLayout = C_INT
		val alphaToCoverageEnabledOffset = 4L + maskOffset
		val alphaToCoverageEnabledLayout = C_INT
	}
}
@JvmInline
actual value class WGPUOrigin3D(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUOrigin3DByValue(handler.pointer.toAddress())
	actual var x: UInt
		get() = getUInt(xOffset)
		set(newValue) = set(xOffset, newValue)

	actual var y: UInt
		get() = getUInt(yOffset)
		set(newValue) = set(yOffset, newValue)

	actual var z: UInt
		get() = getUInt(zOffset)
		set(newValue) = set(zOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUOrigin3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("x"),
			C_INT.withName("y"),
			C_INT.withName("z"),
		).withName("WGPUOrigin3D")
		val xOffset = 0L
		val xLayout = C_INT
		val yOffset = 4L + xOffset
		val yLayout = C_INT
		val zOffset = 4L + yOffset
		val zLayout = C_INT
	}
}
@JvmInline
actual value class WGPUPipelineLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUPipelineLayoutDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var bindGroupLayoutCount: ULong
		get() = getULong(bindGroupLayoutCountOffset)
		set(newValue) = set(bindGroupLayoutCountOffset, newValue)

	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
		get() = get(bindGroupLayoutsLayout, bindGroupLayoutsOffset).let(::ArrayHolder)
		set(newValue) = set(bindGroupLayoutsLayout, bindGroupLayoutsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUPipelineLayoutDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("bindGroupLayoutCount"),
			C_POINTER.withName("bindGroupLayouts"),
		).withName("WGPUPipelineLayoutDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val bindGroupLayoutCountOffset = labelLayout.byteSize() + labelOffset
		val bindGroupLayoutCountLayout = C_LONG
		val bindGroupLayoutsOffset = 8L + bindGroupLayoutCountOffset
		val bindGroupLayoutsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUPrimitiveState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUPrimitiveStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var topology: WGPUPrimitiveTopology
		get() = getUInt(topologyOffset)
		set(newValue) = set(topologyOffset, newValue)

	actual var stripIndexFormat: WGPUIndexFormat
		get() = getUInt(stripIndexFormatOffset)
		set(newValue) = set(stripIndexFormatOffset, newValue)

	actual var frontFace: WGPUFrontFace
		get() = getUInt(frontFaceOffset)
		set(newValue) = set(frontFaceOffset, newValue)

	actual var cullMode: WGPUCullMode
		get() = getUInt(cullModeOffset)
		set(newValue) = set(cullModeOffset, newValue)

	actual var unclippedDepth: Boolean
		get() = getInt(unclippedDepthOffset).toBoolean()
		set(newValue) = set(unclippedDepthOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUPrimitiveState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("topology"),
			C_INT.withName("stripIndexFormat"),
			C_INT.withName("frontFace"),
			C_INT.withName("cullMode"),
			C_INT.withName("unclippedDepth"),
		).withName("WGPUPrimitiveState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val topologyOffset = 8L + nextInChainOffset
		val topologyLayout = C_INT
		val stripIndexFormatOffset = 4L + topologyOffset
		val stripIndexFormatLayout = C_INT
		val frontFaceOffset = 4L + stripIndexFormatOffset
		val frontFaceLayout = C_INT
		val cullModeOffset = 4L + frontFaceOffset
		val cullModeLayout = C_INT
		val unclippedDepthOffset = 4L + cullModeOffset
		val unclippedDepthLayout = C_INT
	}
}
@JvmInline
actual value class WGPUProgrammableStageDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUProgrammableStageDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = handler.asSlice(entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountOffset)
		set(newValue) = set(constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUProgrammableStageDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUProgrammableStageDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("module"),
			WGPUStringView.LAYOUT.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
		).withName("WGPUProgrammableStageDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = entryPointLayout.byteSize() + entryPointOffset
		val constantCountLayout = C_LONG
		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUQuerySetDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUQuerySetDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var type: WGPUQueryType
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)

	actual var count: UInt
		get() = getUInt(countOffset)
		set(newValue) = set(countOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUQuerySetDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_INT.withName("type"),
			C_INT.withName("count"),
		).withName("WGPUQuerySetDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val typeOffset = labelLayout.byteSize() + labelOffset
		val typeLayout = C_INT
		val countOffset = 4L + typeOffset
		val countLayout = C_INT
	}
}
@JvmInline
actual value class WGPUQueueDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUQueueDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUQueueDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUQueueDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPURenderBundleDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderBundleDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderBundleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPURenderBundleDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPURenderBundleEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderBundleEncoderDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var colorFormatCount: ULong
		get() = getULong(colorFormatCountOffset)
		set(newValue) = set(colorFormatCountOffset, newValue)

	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
		get() = get(colorFormatsLayout, colorFormatsOffset).let(::ArrayHolder)
		set(newValue) = set(colorFormatsLayout, colorFormatsOffset, newValue?.handler)

	actual var depthStencilFormat: WGPUTextureFormat
		get() = getUInt(depthStencilFormatOffset)
		set(newValue) = set(depthStencilFormatOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt(sampleCountOffset)
		set(newValue) = set(sampleCountOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt(depthReadOnlyOffset).toBoolean()
		set(newValue) = set(depthReadOnlyOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt(stencilReadOnlyOffset).toBoolean()
		set(newValue) = set(stencilReadOnlyOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderBundleEncoderDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("colorFormatCount"),
			C_POINTER.withName("colorFormats"),
			C_INT.withName("depthStencilFormat"),
			C_INT.withName("sampleCount"),
			C_INT.withName("depthReadOnly"),
			C_INT.withName("stencilReadOnly"),
		).withName("WGPURenderBundleEncoderDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val colorFormatCountOffset = labelLayout.byteSize() + labelOffset
		val colorFormatCountLayout = C_LONG
		val colorFormatsOffset = 8L + colorFormatCountOffset
		val colorFormatsLayout = C_POINTER
		val depthStencilFormatOffset = 8L + colorFormatsOffset
		val depthStencilFormatLayout = C_INT
		val sampleCountOffset = 4L + depthStencilFormatOffset
		val sampleCountLayout = C_INT
		val depthReadOnlyOffset = 4L + sampleCountOffset
		val depthReadOnlyLayout = C_INT
		val stencilReadOnlyOffset = 4L + depthReadOnlyOffset
		val stencilReadOnlyLayout = C_INT
	}
}
@JvmInline
actual value class WGPURenderPassColorAttachment(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPassColorAttachmentByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var view: WGPUTextureView?
		get() = get(viewLayout, viewOffset).let(::WGPUTextureView)
		set(newValue) = set(viewLayout, viewOffset, newValue?.handler)

	actual var depthSlice: UInt
		get() = getUInt(depthSliceOffset)
		set(newValue) = set(depthSliceOffset, newValue)

	actual var resolveTarget: WGPUTextureView?
		get() = get(resolveTargetLayout, resolveTargetOffset).let(::WGPUTextureView)
		set(newValue) = set(resolveTargetLayout, resolveTargetOffset, newValue?.handler)

	actual var loadOp: WGPULoadOp
		get() = getUInt(loadOpOffset)
		set(newValue) = set(loadOpOffset, newValue)

	actual var storeOp: WGPUStoreOp
		get() = getUInt(storeOpOffset)
		set(newValue) = set(storeOpOffset, newValue)

	actual val clearValue: WGPUColor
		get() = handler.asSlice(clearValueOffset).let(::WGPUColor)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassColorAttachment)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("view"),
			C_INT.withName("depthSlice"),
			C_POINTER.withName("resolveTarget"),
			C_INT.withName("loadOp"),
			C_INT.withName("storeOp"),
			WGPUColor.LAYOUT.withName("clearValue"),
		).withName("WGPURenderPassColorAttachment")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val viewOffset = 8L + nextInChainOffset
		val viewLayout = C_POINTER
		val depthSliceOffset = 8L + viewOffset
		val depthSliceLayout = C_INT
		val resolveTargetOffset = 4L + depthSliceOffset
		val resolveTargetLayout = C_POINTER
		val loadOpOffset = 8L + resolveTargetOffset
		val loadOpLayout = C_INT
		val storeOpOffset = 4L + loadOpOffset
		val storeOpLayout = C_INT
		val clearValueOffset = 4L + storeOpOffset
		val clearValueLayout = WGPUColor.LAYOUT
	}
}
@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPassDepthStencilAttachmentByValue(handler.pointer.toAddress())
	actual var view: WGPUTextureView?
		get() = get(viewLayout, viewOffset).let(::WGPUTextureView)
		set(newValue) = set(viewLayout, viewOffset, newValue?.handler)

	actual var depthLoadOp: WGPULoadOp
		get() = getUInt(depthLoadOpOffset)
		set(newValue) = set(depthLoadOpOffset, newValue)

	actual var depthStoreOp: WGPUStoreOp
		get() = getUInt(depthStoreOpOffset)
		set(newValue) = set(depthStoreOpOffset, newValue)

	actual var depthClearValue: Float
		get() = getFloat(depthClearValueOffset)
		set(newValue) = set(depthClearValueOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt(depthReadOnlyOffset).toBoolean()
		set(newValue) = set(depthReadOnlyOffset, newValue)

	actual var stencilLoadOp: WGPULoadOp
		get() = getUInt(stencilLoadOpOffset)
		set(newValue) = set(stencilLoadOpOffset, newValue)

	actual var stencilStoreOp: WGPUStoreOp
		get() = getUInt(stencilStoreOpOffset)
		set(newValue) = set(stencilStoreOpOffset, newValue)

	actual var stencilClearValue: UInt
		get() = getUInt(stencilClearValueOffset)
		set(newValue) = set(stencilClearValueOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt(stencilReadOnlyOffset).toBoolean()
		set(newValue) = set(stencilReadOnlyOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassDepthStencilAttachment)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("view"),
			C_INT.withName("depthLoadOp"),
			C_INT.withName("depthStoreOp"),
			C_FLOAT.withName("depthClearValue"),
			C_INT.withName("depthReadOnly"),
			C_INT.withName("stencilLoadOp"),
			C_INT.withName("stencilStoreOp"),
			C_INT.withName("stencilClearValue"),
			C_INT.withName("stencilReadOnly"),
		).withName("WGPURenderPassDepthStencilAttachment")
		val viewOffset = 0L
		val viewLayout = C_POINTER
		val depthLoadOpOffset = 8L + viewOffset
		val depthLoadOpLayout = C_INT
		val depthStoreOpOffset = 4L + depthLoadOpOffset
		val depthStoreOpLayout = C_INT
		val depthClearValueOffset = 4L + depthStoreOpOffset
		val depthClearValueLayout = C_FLOAT
		val depthReadOnlyOffset = 4L + depthClearValueOffset
		val depthReadOnlyLayout = C_INT
		val stencilLoadOpOffset = 4L + depthReadOnlyOffset
		val stencilLoadOpLayout = C_INT
		val stencilStoreOpOffset = 4L + stencilLoadOpOffset
		val stencilStoreOpLayout = C_INT
		val stencilClearValueOffset = 4L + stencilStoreOpOffset
		val stencilClearValueLayout = C_INT
		val stencilReadOnlyOffset = 4L + stencilClearValueOffset
		val stencilReadOnlyLayout = C_INT
	}
}
@JvmInline
actual value class WGPURenderPassDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPassDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var colorAttachmentCount: ULong
		get() = getULong(colorAttachmentCountOffset)
		set(newValue) = set(colorAttachmentCountOffset, newValue)

	actual var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
		get() = get(colorAttachmentsLayout, colorAttachmentsOffset).let(::ArrayHolder)
		set(newValue) = set(colorAttachmentsLayout, colorAttachmentsOffset, newValue?.handler)

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = get(depthStencilAttachmentLayout, depthStencilAttachmentOffset).let(::WGPURenderPassDepthStencilAttachment)
		set(newValue) = set(depthStencilAttachmentLayout, depthStencilAttachmentOffset, newValue?.handler)

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = get(occlusionQuerySetLayout, occlusionQuerySetOffset).let(::WGPUQuerySet)
		set(newValue) = set(occlusionQuerySetLayout, occlusionQuerySetOffset, newValue?.handler)

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = get(timestampWritesLayout, timestampWritesOffset).let(::WGPURenderPassTimestampWrites)
		set(newValue) = set(timestampWritesLayout, timestampWritesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("colorAttachmentCount"),
			C_POINTER.withName("colorAttachments"),
			C_POINTER.withName("depthStencilAttachment"),
			C_POINTER.withName("occlusionQuerySet"),
			C_POINTER.withName("timestampWrites"),
		).withName("WGPURenderPassDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val colorAttachmentCountOffset = labelLayout.byteSize() + labelOffset
		val colorAttachmentCountLayout = C_LONG
		val colorAttachmentsOffset = 8L + colorAttachmentCountOffset
		val colorAttachmentsLayout = C_POINTER
		val depthStencilAttachmentOffset = 8L + colorAttachmentsOffset
		val depthStencilAttachmentLayout = C_POINTER
		val occlusionQuerySetOffset = 8L + depthStencilAttachmentOffset
		val occlusionQuerySetLayout = C_POINTER
		val timestampWritesOffset = 8L + occlusionQuerySetOffset
		val timestampWritesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPassMaxDrawCountByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var maxDrawCount: ULong
		get() = getULong(maxDrawCountOffset)
		set(newValue) = set(maxDrawCountOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassMaxDrawCount)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_LONG.withName("maxDrawCount"),
		).withName("WGPURenderPassMaxDrawCount")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val maxDrawCountOffset = chainLayout.byteSize() + chainOffset
		val maxDrawCountLayout = C_LONG
	}
}
@JvmInline
actual value class WGPURenderPassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPassTimestampWritesByValue(handler.pointer.toAddress())
	actual var querySet: WGPUQuerySet?
		get() = get(querySetLayout, querySetOffset).let(::WGPUQuerySet)
		set(newValue) = set(querySetLayout, querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt(beginningOfPassWriteIndexOffset)
		set(newValue) = set(beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt(endOfPassWriteIndexOffset)
		set(newValue) = set(endOfPassWriteIndexOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassTimestampWrites {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex"),
		).withName("WGPURenderPassTimestampWrites")
		val querySetOffset = 0L
		val querySetLayout = C_POINTER
		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val beginningOfPassWriteIndexLayout = C_INT
		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
		val endOfPassWriteIndexLayout = C_INT
	}
}
@JvmInline
actual value class WGPURenderPipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURenderPipelineDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var layout: WGPUPipelineLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUPipelineLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual val vertex: WGPUVertexState
		get() = handler.asSlice(vertexOffset).let(::WGPUVertexState)

	actual val primitive: WGPUPrimitiveState
		get() = handler.asSlice(primitiveOffset).let(::WGPUPrimitiveState)

	actual var depthStencil: WGPUDepthStencilState?
		get() = get(depthStencilLayout, depthStencilOffset).let(::WGPUDepthStencilState)
		set(newValue) = set(depthStencilLayout, depthStencilOffset, newValue?.handler)

	actual val multisample: WGPUMultisampleState
		get() = handler.asSlice(multisampleOffset).let(::WGPUMultisampleState)

	actual var fragment: WGPUFragmentState?
		get() = get(fragmentLayout, fragmentOffset).let(::WGPUFragmentState)
		set(newValue) = set(fragmentLayout, fragmentOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPipelineDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("layout"),
			WGPUVertexState.LAYOUT.withName("vertex"),
			WGPUPrimitiveState.LAYOUT.withName("primitive"),
			C_POINTER.withName("depthStencil"),
			WGPUMultisampleState.LAYOUT.withName("multisample"),
			C_POINTER.withName("fragment"),
		).withName("WGPURenderPipelineDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = labelLayout.byteSize() + labelOffset
		val layoutLayout = C_POINTER
		val vertexOffset = 8L + layoutOffset
		val vertexLayout = WGPUVertexState.LAYOUT
		val primitiveOffset = vertexLayout.byteSize() + vertexOffset
		val primitiveLayout = WGPUPrimitiveState.LAYOUT
		val depthStencilOffset = primitiveLayout.byteSize() + primitiveOffset
		val depthStencilLayout = C_POINTER
		val multisampleOffset = 8L + depthStencilOffset
		val multisampleLayout = WGPUMultisampleState.LAYOUT
		val fragmentOffset = multisampleLayout.byteSize() + multisampleOffset
		val fragmentLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPURequestAdapterOptions(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURequestAdapterOptionsByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var compatibleSurface: WGPUSurface?
		get() = get(compatibleSurfaceLayout, compatibleSurfaceOffset).let(::WGPUSurface)
		set(newValue) = set(compatibleSurfaceLayout, compatibleSurfaceOffset, newValue?.handler)

	actual var powerPreference: WGPUPowerPreference
		get() = getUInt(powerPreferenceOffset)
		set(newValue) = set(powerPreferenceOffset, newValue)

	actual var backendType: WGPUBackendType
		get() = getUInt(backendTypeOffset)
		set(newValue) = set(backendTypeOffset, newValue)

	actual var forceFallbackAdapter: Boolean
		get() = getInt(forceFallbackAdapterOffset).toBoolean()
		set(newValue) = set(forceFallbackAdapterOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURequestAdapterOptions)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("compatibleSurface"),
			C_INT.withName("powerPreference"),
			C_INT.withName("backendType"),
			C_INT.withName("forceFallbackAdapter"),
		).withName("WGPURequestAdapterOptions")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val compatibleSurfaceOffset = 8L + nextInChainOffset
		val compatibleSurfaceLayout = C_POINTER
		val powerPreferenceOffset = 8L + compatibleSurfaceOffset
		val powerPreferenceLayout = C_INT
		val backendTypeOffset = 4L + powerPreferenceOffset
		val backendTypeLayout = C_INT
		val forceFallbackAdapterOffset = 4L + backendTypeOffset
		val forceFallbackAdapterLayout = C_INT
	}
}
@JvmInline
actual value class WGPURequiredLimits(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURequiredLimitsByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val limits: WGPULimits
		get() = handler.asSlice(limitsOffset).let(::WGPULimits)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequiredLimits {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURequiredLimits)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPULimits.LAYOUT.withName("limits"),
		).withName("WGPURequiredLimits")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val limitsOffset = 8L + nextInChainOffset
		val limitsLayout = WGPULimits.LAYOUT
	}
}
@JvmInline
actual value class WGPUSamplerBindingLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSamplerBindingLayoutByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var type: WGPUSamplerBindingType
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSamplerBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("type"),
		).withName("WGPUSamplerBindingLayout")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val typeOffset = 8L + nextInChainOffset
		val typeLayout = C_INT
	}
}
@JvmInline
actual value class WGPUSamplerDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSamplerDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var addressModeU: WGPUAddressMode
		get() = getUInt(addressModeUOffset)
		set(newValue) = set(addressModeUOffset, newValue)

	actual var addressModeV: WGPUAddressMode
		get() = getUInt(addressModeVOffset)
		set(newValue) = set(addressModeVOffset, newValue)

	actual var addressModeW: WGPUAddressMode
		get() = getUInt(addressModeWOffset)
		set(newValue) = set(addressModeWOffset, newValue)

	actual var magFilter: WGPUFilterMode
		get() = getUInt(magFilterOffset)
		set(newValue) = set(magFilterOffset, newValue)

	actual var minFilter: WGPUFilterMode
		get() = getUInt(minFilterOffset)
		set(newValue) = set(minFilterOffset, newValue)

	actual var mipmapFilter: WGPUMipmapFilterMode
		get() = getUInt(mipmapFilterOffset)
		set(newValue) = set(mipmapFilterOffset, newValue)

	actual var lodMinClamp: Float
		get() = getFloat(lodMinClampOffset)
		set(newValue) = set(lodMinClampOffset, newValue)

	actual var lodMaxClamp: Float
		get() = getFloat(lodMaxClampOffset)
		set(newValue) = set(lodMaxClampOffset, newValue)

	actual var compare: WGPUCompareFunction
		get() = getUInt(compareOffset)
		set(newValue) = set(compareOffset, newValue)

	actual var maxAnisotropy: UShort
		get() = getUShort(maxAnisotropyOffset)
		set(newValue) = set(maxAnisotropyOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSamplerDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_INT.withName("addressModeU"),
			C_INT.withName("addressModeV"),
			C_INT.withName("addressModeW"),
			C_INT.withName("magFilter"),
			C_INT.withName("minFilter"),
			C_INT.withName("mipmapFilter"),
			C_FLOAT.withName("lodMinClamp"),
			C_FLOAT.withName("lodMaxClamp"),
			C_INT.withName("compare"),
			C_SHORT.withName("maxAnisotropy"),
		).withName("WGPUSamplerDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val addressModeUOffset = labelLayout.byteSize() + labelOffset
		val addressModeULayout = C_INT
		val addressModeVOffset = 4L + addressModeUOffset
		val addressModeVLayout = C_INT
		val addressModeWOffset = 4L + addressModeVOffset
		val addressModeWLayout = C_INT
		val magFilterOffset = 4L + addressModeWOffset
		val magFilterLayout = C_INT
		val minFilterOffset = 4L + magFilterOffset
		val minFilterLayout = C_INT
		val mipmapFilterOffset = 4L + minFilterOffset
		val mipmapFilterLayout = C_INT
		val lodMinClampOffset = 4L + mipmapFilterOffset
		val lodMinClampLayout = C_FLOAT
		val lodMaxClampOffset = 4L + lodMinClampOffset
		val lodMaxClampLayout = C_FLOAT
		val compareOffset = 4L + lodMaxClampOffset
		val compareLayout = C_INT
		val maxAnisotropyOffset = 4L + compareOffset
		val maxAnisotropyLayout = C_SHORT
	}
}
@JvmInline
actual value class WGPUShaderModuleDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUShaderModuleDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUShaderModuleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUShaderModuleDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUShaderSourceSPIRV(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUShaderSourceSPIRVByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var codeSize: UInt
		get() = getUInt(codeSizeOffset)
		set(newValue) = set(codeSizeOffset, newValue)

	actual var code: NativeAddress?
		get() = get(codeLayout, codeOffset)
		set(newValue) = set(codeLayout, codeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUShaderSourceSPIRV)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_INT.withName("codeSize"),
			C_POINTER.withName("code"),
		).withName("WGPUShaderSourceSPIRV")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val codeSizeOffset = chainLayout.byteSize() + chainOffset
		val codeSizeLayout = C_INT
		val codeOffset = 4L + codeSizeOffset
		val codeLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUShaderSourceWGSL(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUShaderSourceWGSLByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual val code: WGPUStringView
		get() = handler.asSlice(codeOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUShaderSourceWGSL)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			WGPUStringView.LAYOUT.withName("code"),
		).withName("WGPUShaderSourceWGSL")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val codeOffset = chainLayout.byteSize() + chainOffset
		val codeLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUStencilFaceState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUStencilFaceStateByValue(handler.pointer.toAddress())
	actual var compare: WGPUCompareFunction
		get() = getUInt(compareOffset)
		set(newValue) = set(compareOffset, newValue)

	actual var failOp: WGPUStencilOperation
		get() = getUInt(failOpOffset)
		set(newValue) = set(failOpOffset, newValue)

	actual var depthFailOp: WGPUStencilOperation
		get() = getUInt(depthFailOpOffset)
		set(newValue) = set(depthFailOpOffset, newValue)

	actual var passOp: WGPUStencilOperation
		get() = getUInt(passOpOffset)
		set(newValue) = set(passOpOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStencilFaceState)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("compare"),
			C_INT.withName("failOp"),
			C_INT.withName("depthFailOp"),
			C_INT.withName("passOp"),
		).withName("WGPUStencilFaceState")
		val compareOffset = 0L
		val compareLayout = C_INT
		val failOpOffset = 4L + compareOffset
		val failOpLayout = C_INT
		val depthFailOpOffset = 4L + failOpOffset
		val depthFailOpLayout = C_INT
		val passOpOffset = 4L + depthFailOpOffset
		val passOpLayout = C_INT
	}
}
@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUStorageTextureBindingLayoutByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var access: WGPUStorageTextureAccess
		get() = getUInt(accessOffset)
		set(newValue) = set(accessOffset, newValue)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = getUInt(viewDimensionOffset)
		set(newValue) = set(viewDimensionOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStorageTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("access"),
			C_INT.withName("format"),
			C_INT.withName("viewDimension"),
		).withName("WGPUStorageTextureBindingLayout")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val accessOffset = 8L + nextInChainOffset
		val accessLayout = C_INT
		val formatOffset = 4L + accessOffset
		val formatLayout = C_INT
		val viewDimensionOffset = 4L + formatOffset
		val viewDimensionLayout = C_INT
	}
}
@JvmInline
actual value class WGPUSupportedFeatures(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSupportedFeaturesByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var featureCount: ULong
		get() = getULong(featureCountOffset)
		set(newValue) = set(featureCountOffset, newValue)

	actual var features: ArrayHolder<WGPUFeatureName>?
		get() = get(featuresLayout, featuresOffset).let(::ArrayHolder)
		set(newValue) = set(featuresLayout, featuresOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSupportedFeatures)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("featureCount"),
			C_POINTER.withName("features"),
		).withName("WGPUSupportedFeatures")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val featureCountOffset = 8L + nextInChainOffset
		val featureCountLayout = C_LONG
		val featuresOffset = 8L + featureCountOffset
		val featuresLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSupportedLimits(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSupportedLimitsByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val limits: WGPULimits
		get() = handler.asSlice(limitsOffset).let(::WGPULimits)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSupportedLimits)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPULimits.LAYOUT.withName("limits"),
		).withName("WGPUSupportedLimits")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val limitsOffset = 8L + nextInChainOffset
		val limitsLayout = WGPULimits.LAYOUT
	}
}
@JvmInline
actual value class WGPUSurfaceCapabilities(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceCapabilitiesByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var usages: ULong
		get() = getULong(usagesOffset)
		set(newValue) = set(usagesOffset, newValue)

	actual var formatCount: ULong
		get() = getULong(formatCountOffset)
		set(newValue) = set(formatCountOffset, newValue)

	actual var formats: ArrayHolder<WGPUTextureFormat>?
		get() = get(formatsLayout, formatsOffset).let(::ArrayHolder)
		set(newValue) = set(formatsLayout, formatsOffset, newValue?.handler)

	actual var presentModeCount: ULong
		get() = getULong(presentModeCountOffset)
		set(newValue) = set(presentModeCountOffset, newValue)

	actual var presentModes: ArrayHolder<WGPUPresentMode>?
		get() = get(presentModesLayout, presentModesOffset).let(::ArrayHolder)
		set(newValue) = set(presentModesLayout, presentModesOffset, newValue?.handler)

	actual var alphaModeCount: ULong
		get() = getULong(alphaModeCountOffset)
		set(newValue) = set(alphaModeCountOffset, newValue)

	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
		get() = get(alphaModesLayout, alphaModesOffset).let(::ArrayHolder)
		set(newValue) = set(alphaModesLayout, alphaModesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceCapabilities)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("usages"),
			C_LONG.withName("formatCount"),
			C_POINTER.withName("formats"),
			C_LONG.withName("presentModeCount"),
			C_POINTER.withName("presentModes"),
			C_LONG.withName("alphaModeCount"),
			C_POINTER.withName("alphaModes"),
		).withName("WGPUSurfaceCapabilities")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val usagesOffset = 8L + nextInChainOffset
		val usagesLayout = C_LONG
		val formatCountOffset = 8L + usagesOffset
		val formatCountLayout = C_LONG
		val formatsOffset = 8L + formatCountOffset
		val formatsLayout = C_POINTER
		val presentModeCountOffset = 8L + formatsOffset
		val presentModeCountLayout = C_LONG
		val presentModesOffset = 8L + presentModeCountOffset
		val presentModesLayout = C_POINTER
		val alphaModeCountOffset = 8L + presentModesOffset
		val alphaModeCountLayout = C_LONG
		val alphaModesOffset = 8L + alphaModeCountOffset
		val alphaModesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSurfaceConfiguration(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceConfigurationByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var device: WGPUDevice?
		get() = get(deviceLayout, deviceOffset).let(::WGPUDevice)
		set(newValue) = set(deviceLayout, deviceOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var usage: ULong
		get() = getULong(usageOffset)
		set(newValue) = set(usageOffset, newValue)

	actual var width: UInt
		get() = getUInt(widthOffset)
		set(newValue) = set(widthOffset, newValue)

	actual var height: UInt
		get() = getUInt(heightOffset)
		set(newValue) = set(heightOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong(viewFormatCountOffset)
		set(newValue) = set(viewFormatCountOffset, newValue)

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = get(viewFormatsLayout, viewFormatsOffset).let(::ArrayHolder)
		set(newValue) = set(viewFormatsLayout, viewFormatsOffset, newValue?.handler)

	actual var alphaMode: WGPUCompositeAlphaMode
		get() = getUInt(alphaModeOffset)
		set(newValue) = set(alphaModeOffset, newValue)

	actual var presentMode: WGPUPresentMode
		get() = getUInt(presentModeOffset)
		set(newValue) = set(presentModeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceConfiguration)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("device"),
			C_INT.withName("format"),
			C_LONG.withName("usage"),
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_LONG.withName("viewFormatCount"),
			C_POINTER.withName("viewFormats"),
			C_INT.withName("alphaMode"),
			C_INT.withName("presentMode"),
		).withName("WGPUSurfaceConfiguration")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val deviceOffset = 8L + nextInChainOffset
		val deviceLayout = C_POINTER
		val formatOffset = 8L + deviceOffset
		val formatLayout = C_INT
		val usageOffset = 4L + formatOffset
		val usageLayout = C_LONG
		val widthOffset = 8L + usageOffset
		val widthLayout = C_INT
		val heightOffset = 4L + widthOffset
		val heightLayout = C_INT
		val viewFormatCountOffset = 4L + heightOffset
		val viewFormatCountLayout = C_LONG
		val viewFormatsOffset = 8L + viewFormatCountOffset
		val viewFormatsLayout = C_POINTER
		val alphaModeOffset = 8L + viewFormatsOffset
		val alphaModeLayout = C_INT
		val presentModeOffset = 4L + alphaModeOffset
		val presentModeLayout = C_INT
	}
}
@JvmInline
actual value class WGPUSurfaceDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUSurfaceDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUSurfaceSourceAndroidNativeWindow(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceAndroidNativeWindowByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var window: NativeAddress?
		get() = get(windowLayout, windowOffset)
		set(newValue) = set(windowLayout, windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceAndroidNativeWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("window"),
		).withName("WGPUSurfaceSourceAndroidNativeWindow")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val windowOffset = chainLayout.byteSize() + chainOffset
		val windowLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSurfaceSourceMetalLayer(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceMetalLayerByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var layer: NativeAddress?
		get() = get(layerLayout, layerOffset)
		set(newValue) = set(layerLayout, layerOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceMetalLayer)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("layer"),
		).withName("WGPUSurfaceSourceMetalLayer")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val layerOffset = chainLayout.byteSize() + chainOffset
		val layerLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSurfaceSourceWaylandSurface(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceWaylandSurfaceByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var display: NativeAddress?
		get() = get(displayLayout, displayOffset)
		set(newValue) = set(displayLayout, displayOffset, newValue)

	actual var surface: NativeAddress?
		get() = get(surfaceLayout, surfaceOffset)
		set(newValue) = set(surfaceLayout, surfaceOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceWaylandSurface)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("display"),
			C_POINTER.withName("surface"),
		).withName("WGPUSurfaceSourceWaylandSurface")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val displayOffset = chainLayout.byteSize() + chainOffset
		val displayLayout = C_POINTER
		val surfaceOffset = 8L + displayOffset
		val surfaceLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSurfaceSourceWindowsHWND(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceWindowsHWNDByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var hinstance: NativeAddress?
		get() = get(hinstanceLayout, hinstanceOffset)
		set(newValue) = set(hinstanceLayout, hinstanceOffset, newValue)

	actual var hwnd: NativeAddress?
		get() = get(hwndLayout, hwndOffset)
		set(newValue) = set(hwndLayout, hwndOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceWindowsHWND)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("hinstance"),
			C_POINTER.withName("hwnd"),
		).withName("WGPUSurfaceSourceWindowsHWND")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val hinstanceOffset = chainLayout.byteSize() + chainOffset
		val hinstanceLayout = C_POINTER
		val hwndOffset = 8L + hinstanceOffset
		val hwndLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSurfaceSourceXCBWindow(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceXCBWindowByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var connection: NativeAddress?
		get() = get(connectionLayout, connectionOffset)
		set(newValue) = set(connectionLayout, connectionOffset, newValue)

	actual var window: UInt
		get() = getUInt(windowOffset)
		set(newValue) = set(windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceXCBWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("connection"),
			C_INT.withName("window"),
		).withName("WGPUSurfaceSourceXCBWindow")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val connectionOffset = chainLayout.byteSize() + chainOffset
		val connectionLayout = C_POINTER
		val windowOffset = 8L + connectionOffset
		val windowLayout = C_INT
	}
}
@JvmInline
actual value class WGPUSurfaceSourceXlibWindow(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceSourceXlibWindowByValue(handler.pointer.toAddress())
	actual val chain: WGPUChainedStruct
		get() = handler.asSlice(chainOffset).let(::WGPUChainedStruct)

	actual var display: NativeAddress?
		get() = get(displayLayout, displayOffset)
		set(newValue) = set(displayLayout, displayOffset, newValue)

	actual var window: ULong
		get() = getULong(windowOffset)
		set(newValue) = set(windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceXlibWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("display"),
			C_LONG.withName("window"),
		).withName("WGPUSurfaceSourceXlibWindow")
		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val displayOffset = chainLayout.byteSize() + chainOffset
		val displayLayout = C_POINTER
		val windowOffset = 8L + displayOffset
		val windowLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUSurfaceTexture(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUSurfaceTextureByValue(handler.pointer.toAddress())
	actual var texture: WGPUTexture?
		get() = get(textureLayout, textureOffset).let(::WGPUTexture)
		set(newValue) = set(textureLayout, textureOffset, newValue?.handler)

	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = getUInt(statusOffset)
		set(newValue) = set(statusOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceTexture)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("status"),
		).withName("WGPUSurfaceTexture")
		val textureOffset = 0L
		val textureLayout = C_POINTER
		val statusOffset = 8L + textureOffset
		val statusLayout = C_INT
	}
}
@JvmInline
actual value class WGPUTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUTextureBindingLayoutByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var sampleType: WGPUTextureSampleType
		get() = getUInt(sampleTypeOffset)
		set(newValue) = set(sampleTypeOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = getUInt(viewDimensionOffset)
		set(newValue) = set(viewDimensionOffset, newValue)

	actual var multisampled: Boolean
		get() = getInt(multisampledOffset).toBoolean()
		set(newValue) = set(multisampledOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("sampleType"),
			C_INT.withName("viewDimension"),
			C_INT.withName("multisampled"),
		).withName("WGPUTextureBindingLayout")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val sampleTypeOffset = 8L + nextInChainOffset
		val sampleTypeLayout = C_INT
		val viewDimensionOffset = 4L + sampleTypeOffset
		val viewDimensionLayout = C_INT
		val multisampledOffset = 4L + viewDimensionOffset
		val multisampledLayout = C_INT
	}
}
@JvmInline
actual value class WGPUTextureDataLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUTextureDataLayoutByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var offset: ULong
		get() = getULong(offsetOffset)
		set(newValue) = set(offsetOffset, newValue)

	actual var bytesPerRow: UInt
		get() = getUInt(bytesPerRowOffset)
		set(newValue) = set(bytesPerRowOffset, newValue)

	actual var rowsPerImage: UInt
		get() = getUInt(rowsPerImageOffset)
		set(newValue) = set(rowsPerImageOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDataLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureDataLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("offset"),
			C_INT.withName("bytesPerRow"),
			C_INT.withName("rowsPerImage"),
		).withName("WGPUTextureDataLayout")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val offsetOffset = 8L + nextInChainOffset
		val offsetLayout = C_LONG
		val bytesPerRowOffset = 8L + offsetOffset
		val bytesPerRowLayout = C_INT
		val rowsPerImageOffset = 4L + bytesPerRowOffset
		val rowsPerImageLayout = C_INT
	}
}
@JvmInline
actual value class WGPUTextureDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUTextureDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var usage: ULong
		get() = getULong(usageOffset)
		set(newValue) = set(usageOffset, newValue)

	actual var dimension: WGPUTextureDimension
		get() = getUInt(dimensionOffset)
		set(newValue) = set(dimensionOffset, newValue)

	actual val size: WGPUExtent3D
		get() = handler.asSlice(sizeOffset).let(::WGPUExtent3D)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt(mipLevelCountOffset)
		set(newValue) = set(mipLevelCountOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt(sampleCountOffset)
		set(newValue) = set(sampleCountOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong(viewFormatCountOffset)
		set(newValue) = set(viewFormatCountOffset, newValue)

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = get(viewFormatsLayout, viewFormatsOffset).let(::ArrayHolder)
		set(newValue) = set(viewFormatsLayout, viewFormatsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("usage"),
			C_INT.withName("dimension"),
			WGPUExtent3D.LAYOUT.withName("size"),
			C_INT.withName("format"),
			C_INT.withName("mipLevelCount"),
			C_INT.withName("sampleCount"),
			C_LONG.withName("viewFormatCount"),
			C_POINTER.withName("viewFormats"),
		).withName("WGPUTextureDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val usageOffset = labelLayout.byteSize() + labelOffset
		val usageLayout = C_LONG
		val dimensionOffset = 8L + usageOffset
		val dimensionLayout = C_INT
		val sizeOffset = 4L + dimensionOffset
		val sizeLayout = WGPUExtent3D.LAYOUT
		val formatOffset = sizeLayout.byteSize() + sizeOffset
		val formatLayout = C_INT
		val mipLevelCountOffset = 4L + formatOffset
		val mipLevelCountLayout = C_INT
		val sampleCountOffset = 4L + mipLevelCountOffset
		val sampleCountLayout = C_INT
		val viewFormatCountOffset = 4L + sampleCountOffset
		val viewFormatCountLayout = C_LONG
		val viewFormatsOffset = 8L + viewFormatCountOffset
		val viewFormatsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUTextureViewDescriptor(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUTextureViewDescriptorByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = handler.asSlice(labelOffset).let(::WGPUStringView)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var dimension: WGPUTextureViewDimension
		get() = getUInt(dimensionOffset)
		set(newValue) = set(dimensionOffset, newValue)

	actual var baseMipLevel: UInt
		get() = getUInt(baseMipLevelOffset)
		set(newValue) = set(baseMipLevelOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt(mipLevelCountOffset)
		set(newValue) = set(mipLevelCountOffset, newValue)

	actual var baseArrayLayer: UInt
		get() = getUInt(baseArrayLayerOffset)
		set(newValue) = set(baseArrayLayerOffset, newValue)

	actual var arrayLayerCount: UInt
		get() = getUInt(arrayLayerCountOffset)
		set(newValue) = set(arrayLayerCountOffset, newValue)

	actual var aspect: WGPUTextureAspect
		get() = getUInt(aspectOffset)
		set(newValue) = set(aspectOffset, newValue)

	actual var usage: ULong
		get() = getULong(usageOffset)
		set(newValue) = set(usageOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureViewDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_INT.withName("format"),
			C_INT.withName("dimension"),
			C_INT.withName("baseMipLevel"),
			C_INT.withName("mipLevelCount"),
			C_INT.withName("baseArrayLayer"),
			C_INT.withName("arrayLayerCount"),
			C_INT.withName("aspect"),
			C_LONG.withName("usage"),
		).withName("WGPUTextureViewDescriptor")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L + nextInChainOffset
		val labelLayout = WGPUStringView.LAYOUT
		val formatOffset = labelLayout.byteSize() + labelOffset
		val formatLayout = C_INT
		val dimensionOffset = 4L + formatOffset
		val dimensionLayout = C_INT
		val baseMipLevelOffset = 4L + dimensionOffset
		val baseMipLevelLayout = C_INT
		val mipLevelCountOffset = 4L + baseMipLevelOffset
		val mipLevelCountLayout = C_INT
		val baseArrayLayerOffset = 4L + mipLevelCountOffset
		val baseArrayLayerLayout = C_INT
		val arrayLayerCountOffset = 4L + baseArrayLayerOffset
		val arrayLayerCountLayout = C_INT
		val aspectOffset = 4L + arrayLayerCountOffset
		val aspectLayout = C_INT
		val usageOffset = 4L + aspectOffset
		val usageLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUVertexAttribute(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUVertexAttributeByValue(handler.pointer.toAddress())
	actual var format: WGPUVertexFormat
		get() = getUInt(formatOffset)
		set(newValue) = set(formatOffset, newValue)

	actual var offset: ULong
		get() = getULong(offsetOffset)
		set(newValue) = set(offsetOffset, newValue)

	actual var shaderLocation: UInt
		get() = getUInt(shaderLocationOffset)
		set(newValue) = set(shaderLocationOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUVertexAttribute)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_LONG.withName("offset"),
			C_INT.withName("shaderLocation"),
		).withName("WGPUVertexAttribute")
		val formatOffset = 0L
		val formatLayout = C_INT
		val offsetOffset = 4L + formatOffset
		val offsetLayout = C_LONG
		val shaderLocationOffset = 8L + offsetOffset
		val shaderLocationLayout = C_INT
	}
}
@JvmInline
actual value class WGPUVertexBufferLayout(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUVertexBufferLayoutByValue(handler.pointer.toAddress())
	actual var arrayStride: ULong
		get() = getULong(arrayStrideOffset)
		set(newValue) = set(arrayStrideOffset, newValue)

	actual var stepMode: WGPUVertexStepMode
		get() = getUInt(stepModeOffset)
		set(newValue) = set(stepModeOffset, newValue)

	actual var attributeCount: ULong
		get() = getULong(attributeCountOffset)
		set(newValue) = set(attributeCountOffset, newValue)

	actual var attributes: ArrayHolder<WGPUVertexAttribute>?
		get() = get(attributesLayout, attributesOffset).let(::ArrayHolder)
		set(newValue) = set(attributesLayout, attributesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUVertexBufferLayout)
		}
		internal val LAYOUT = structLayout(
			C_LONG.withName("arrayStride"),
			C_INT.withName("stepMode"),
			C_LONG.withName("attributeCount"),
			C_POINTER.withName("attributes"),
		).withName("WGPUVertexBufferLayout")
		val arrayStrideOffset = 0L
		val arrayStrideLayout = C_LONG
		val stepModeOffset = 8L + arrayStrideOffset
		val stepModeLayout = C_INT
		val attributeCountOffset = 4L + stepModeOffset
		val attributeCountLayout = C_LONG
		val attributesOffset = 8L + attributeCountOffset
		val attributesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUVertexState(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUVertexStateByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = handler.asSlice(entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountOffset)
		set(newValue) = set(constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual var bufferCount: ULong
		get() = getULong(bufferCountOffset)
		set(newValue) = set(bufferCountOffset, newValue)

	actual var buffers: ArrayHolder<WGPUVertexBufferLayout>?
		get() = get(buffersLayout, buffersOffset).let(::ArrayHolder)
		set(newValue) = set(buffersLayout, buffersOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUVertexState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("module"),
			WGPUStringView.LAYOUT.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("bufferCount"),
			C_POINTER.withName("buffers"),
		).withName("WGPUVertexState")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = entryPointLayout.byteSize() + entryPointOffset
		val constantCountLayout = C_LONG
		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = C_POINTER
		val bufferCountOffset = 8L + constantsOffset
		val bufferCountLayout = C_LONG
		val buffersOffset = 8L + bufferCountOffset
		val buffersLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUChainedStruct(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUChainedStructByValue(handler.pointer.toAddress())
	actual var next: WGPUChainedStruct?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = getUInt(sTypeOffset)
		set(newValue) = set(sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUChainedStruct)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType"),
		).withName("WGPUChainedStruct")
		val nextOffset = 0L
		val nextLayout = C_POINTER
		val sTypeOffset = 8L + nextOffset
		val sTypeLayout = C_INT
	}
}
@JvmInline
actual value class WGPUChainedStructOut(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUChainedStructOutByValue(handler.pointer.toAddress())
	actual var next: WGPUChainedStructOut?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = getUInt(sTypeOffset)
		set(newValue) = set(sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUChainedStructOut)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType"),
		).withName("WGPUChainedStructOut")
		val nextOffset = 0L
		val nextLayout = C_POINTER
		val sTypeOffset = 8L + nextOffset
		val sTypeLayout = C_INT
	}
}
@JvmInline
actual value class WGPUStringView(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUStringViewByValue(handler.pointer.toAddress())
	actual var data: CString?
		get() = get(dataLayout, dataOffset).let(::CString)
		set(newValue) = set(dataLayout, dataOffset, newValue?.handler)

	actual var length: ULong
		get() = getULong(lengthOffset)
		set(newValue) = set(lengthOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStringView)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("data"),
			C_LONG.withName("length"),
		).withName("WGPUStringView")
		val dataOffset = 0L
		val dataLayout = C_POINTER
		val lengthOffset = 8L + dataOffset
		val lengthLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUBufferMapCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUBufferMapCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBufferMapCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUBufferMapCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUCompilationInfoCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCompilationInfoCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCompilationInfoCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUCompilationInfoCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCreateComputePipelineAsyncCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCreateComputePipelineAsyncCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUCreateComputePipelineAsyncCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUCreateRenderPipelineAsyncCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCreateRenderPipelineAsyncCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUCreateRenderPipelineAsyncCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUDeviceLostCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUDeviceLostCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUDeviceLostCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUDeviceLostCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUPopErrorScopeCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUPopErrorScopeCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUPopErrorScopeCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUPopErrorScopeCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUQueueWorkDoneCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUQueueWorkDoneCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUQueueWorkDoneCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUQueueWorkDoneCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPURequestAdapterCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURequestAdapterCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURequestAdapterCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPURequestAdapterCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPURequestDeviceCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPURequestDeviceCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURequestDeviceCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPURequestDeviceCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
@JvmInline
actual value class WGPUUncapturedErrorCallbackInfo(actual override val handler: NativeAddress) : CStructure {
	internal fun toCValue() = WGPUUncapturedErrorCallbackInfoByValue(handler.pointer.toAddress())
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
		get() = get(callbackLayout, callbackOffset).let(::CallbackHolder)
		set(newValue) = set(callbackLayout, callbackOffset, newValue?.handler)

	actual var userdata1: NativeAddress?
		get() = get(userdata1Layout, userdata1Offset)
		set(newValue) = set(userdata1Layout, userdata1Offset, newValue)

	actual var userdata2: NativeAddress?
		get() = get(userdata2Layout, userdata2Offset)
		set(newValue) = set(userdata2Layout, userdata2Offset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUUncapturedErrorCallbackInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2"),
		).withName("WGPUUncapturedErrorCallbackInfo")
		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = C_POINTER
	}
}
internal class WGPUAdapterInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val vendor: WGPUStringViewByValue = WGPUStringViewByValue(WGPUAdapterInfo(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val architecture: WGPUStringViewByValue = WGPUStringViewByValue(WGPUAdapterInfo(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val device: WGPUStringViewByValue = WGPUStringViewByValue(WGPUAdapterInfo(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val description: WGPUStringViewByValue = WGPUStringViewByValue(WGPUAdapterInfo(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var backendType: com.sun.jna.Pointer? = null
	@JvmField var adapterType: com.sun.jna.Pointer? = null
	@JvmField var vendorID: Int = 0
	@JvmField var deviceID: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "vendor", "architecture", "device", "description", "backendType", "adapterType", "vendorID", "deviceID")
}
internal class WGPUBindGroupDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUBindGroupDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "entryCount", "entries")
}
internal class WGPUBindGroupEntryByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var binding: Int = 0
	@JvmField var buffer: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var sampler: com.sun.jna.Pointer? = null
	@JvmField var textureView: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "binding", "buffer", "offset", "size", "sampler", "textureView")
}
internal class WGPUBindGroupLayoutDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUBindGroupLayoutDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "entryCount", "entries")
}
internal class WGPUBindGroupLayoutEntryByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var binding: Int = 0
	@JvmField var visibility: Long = 0L
	@JvmField val buffer: WGPUBufferBindingLayoutByValue = WGPUBufferBindingLayoutByValue(WGPUBindGroupLayoutEntry(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val sampler: WGPUSamplerBindingLayoutByValue = WGPUSamplerBindingLayoutByValue(WGPUBindGroupLayoutEntry(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val texture: WGPUTextureBindingLayoutByValue = WGPUTextureBindingLayoutByValue(WGPUBindGroupLayoutEntry(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val storageTexture: WGPUStorageTextureBindingLayoutByValue = WGPUStorageTextureBindingLayoutByValue(WGPUBindGroupLayoutEntry(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "binding", "visibility", "buffer", "sampler", "texture", "storageTexture")
}
internal class WGPUBlendComponentByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var operation: com.sun.jna.Pointer? = null
	@JvmField var srcFactor: com.sun.jna.Pointer? = null
	@JvmField var dstFactor: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("operation", "srcFactor", "dstFactor")
}
internal class WGPUBlendStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val color: WGPUBlendComponentByValue = WGPUBlendComponentByValue(WGPUBlendState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val alpha: WGPUBlendComponentByValue = WGPUBlendComponentByValue(WGPUBlendState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("color", "alpha")
}
internal class WGPUBufferBindingLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var hasDynamicOffset: Int = 0
	@JvmField var minBindingSize: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "type", "hasDynamicOffset", "minBindingSize")
}
internal class WGPUBufferDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUBufferDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var usage: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var mappedAtCreation: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "size", "mappedAtCreation")
}
internal class WGPUColorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var r: Double = 0.0
	@JvmField var g: Double = 0.0
	@JvmField var b: Double = 0.0
	@JvmField var a: Double = 0.0
	override fun getFieldOrder() = listOf("r", "g", "b", "a")
}
internal class WGPUColorTargetStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var blend: com.sun.jna.Pointer? = null
	@JvmField var writeMask: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "format", "blend", "writeMask")
}
internal class WGPUCommandBufferDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUCommandBufferDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPUCommandEncoderDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUCommandEncoderDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPUCompilationInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var messageCount: Long = 0L
	@JvmField var messages: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "messageCount", "messages")
}
internal class WGPUCompilationMessageByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val message: WGPUStringViewByValue = WGPUStringViewByValue(WGPUCompilationMessage(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var lineNum: Long = 0L
	@JvmField var linePos: Long = 0L
	@JvmField var offset: Long = 0L
	@JvmField var length: Long = 0L
	@JvmField var utf16LinePos: Long = 0L
	@JvmField var utf16Offset: Long = 0L
	@JvmField var utf16Length: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "message", "type", "lineNum", "linePos", "offset", "length", "utf16LinePos", "utf16Offset", "utf16Length")
}
internal class WGPUComputePassDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUComputePassDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var timestampWrites: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "timestampWrites")
}
internal class WGPUComputePassTimestampWritesByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
}
internal class WGPUComputePipelineDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUComputePipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField val compute: WGPUProgrammableStageDescriptorByValue = WGPUProgrammableStageDescriptorByValue(WGPUComputePipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "compute")
}
internal class WGPUConstantEntryByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val key: WGPUStringViewByValue = WGPUStringViewByValue(WGPUConstantEntry(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var value: Double = 0.0
	override fun getFieldOrder() = listOf("nextInChain", "key", "value")
}
internal class WGPUDepthStencilStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var depthWriteEnabled: com.sun.jna.Pointer? = null
	@JvmField var depthCompare: com.sun.jna.Pointer? = null
	@JvmField val stencilFront: WGPUStencilFaceStateByValue = WGPUStencilFaceStateByValue(WGPUDepthStencilState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val stencilBack: WGPUStencilFaceStateByValue = WGPUStencilFaceStateByValue(WGPUDepthStencilState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var stencilReadMask: Int = 0
	@JvmField var stencilWriteMask: Int = 0
	@JvmField var depthBias: Int = 0
	@JvmField var depthBiasSlopeScale: Float = 0f
	@JvmField var depthBiasClamp: Float = 0f
	override fun getFieldOrder() = listOf("nextInChain", "format", "depthWriteEnabled", "depthCompare", "stencilFront", "stencilBack", "stencilReadMask", "stencilWriteMask", "depthBias", "depthBiasSlopeScale", "depthBiasClamp")
}
internal class WGPUDeviceDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUDeviceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var requiredFeatureCount: Long = 0L
	@JvmField var requiredFeatures: com.sun.jna.Pointer? = null
	@JvmField var requiredLimits: com.sun.jna.Pointer? = null
	@JvmField val defaultQueue: WGPUQueueDescriptorByValue = WGPUQueueDescriptorByValue(WGPUDeviceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfoByValue = WGPUDeviceLostCallbackInfoByValue(WGPUDeviceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfoByValue = WGPUUncapturedErrorCallbackInfoByValue(WGPUDeviceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label", "requiredFeatureCount", "requiredFeatures", "requiredLimits", "defaultQueue", "deviceLostCallbackInfo", "uncapturedErrorCallbackInfo")
}
internal class WGPUExtent3DByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var width: Int = 0
	@JvmField var height: Int = 0
	@JvmField var depthOrArrayLayers: Int = 0
	override fun getFieldOrder() = listOf("width", "height", "depthOrArrayLayers")
}
internal class WGPUFragmentStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringViewByValue = WGPUStringViewByValue(WGPUFragmentState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var targetCount: Long = 0L
	@JvmField var targets: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "targetCount", "targets")
}
internal class WGPUFutureByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var id: Long = 0L
	override fun getFieldOrder() = listOf("id")
}
internal class WGPUFutureWaitInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val future: WGPUFutureByValue = WGPUFutureByValue(WGPUFutureWaitInfo(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var completed: Int = 0
	override fun getFieldOrder() = listOf("future", "completed")
}
internal class WGPUImageCopyBufferByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val layout: WGPUTextureDataLayoutByValue = WGPUTextureDataLayoutByValue(WGPUImageCopyBuffer(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var buffer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "layout", "buffer")
}
internal class WGPUImageCopyTextureByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var mipLevel: Int = 0
	@JvmField val origin: WGPUOrigin3DByValue = WGPUOrigin3DByValue(WGPUImageCopyTexture(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var aspect: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "texture", "mipLevel", "origin", "aspect")
}
internal class WGPUInstanceDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val features: WGPUInstanceFeaturesByValue = WGPUInstanceFeaturesByValue(WGPUInstanceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "features")
}
internal class WGPUInstanceFeaturesByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var timedWaitAnyEnable: Int = 0
	@JvmField var timedWaitAnyMaxCount: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "timedWaitAnyEnable", "timedWaitAnyMaxCount")
}
internal class WGPULimitsByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
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
}
internal class WGPUMultisampleStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var count: Int = 0
	@JvmField var mask: Int = 0
	@JvmField var alphaToCoverageEnabled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "count", "mask", "alphaToCoverageEnabled")
}
internal class WGPUOrigin3DByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var x: Int = 0
	@JvmField var y: Int = 0
	@JvmField var z: Int = 0
	override fun getFieldOrder() = listOf("x", "y", "z")
}
internal class WGPUPipelineLayoutDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUPipelineLayoutDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var bindGroupLayoutCount: Long = 0L
	@JvmField var bindGroupLayouts: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "bindGroupLayoutCount", "bindGroupLayouts")
}
internal class WGPUPrimitiveStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var topology: com.sun.jna.Pointer? = null
	@JvmField var stripIndexFormat: com.sun.jna.Pointer? = null
	@JvmField var frontFace: com.sun.jna.Pointer? = null
	@JvmField var cullMode: com.sun.jna.Pointer? = null
	@JvmField var unclippedDepth: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "topology", "stripIndexFormat", "frontFace", "cullMode", "unclippedDepth")
}
internal class WGPUProgrammableStageDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringViewByValue = WGPUStringViewByValue(WGPUProgrammableStageDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants")
}
internal class WGPUQuerySetDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUQuerySetDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var type: com.sun.jna.Pointer? = null
	@JvmField var count: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "type", "count")
}
internal class WGPUQueueDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUQueueDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPURenderBundleDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPURenderBundleDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPURenderBundleEncoderDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPURenderBundleEncoderDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var colorFormatCount: Long = 0L
	@JvmField var colorFormats: com.sun.jna.Pointer? = null
	@JvmField var depthStencilFormat: com.sun.jna.Pointer? = null
	@JvmField var sampleCount: Int = 0
	@JvmField var depthReadOnly: Int = 0
	@JvmField var stencilReadOnly: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorFormatCount", "colorFormats", "depthStencilFormat", "sampleCount", "depthReadOnly", "stencilReadOnly")
}
internal class WGPURenderPassColorAttachmentByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var view: com.sun.jna.Pointer? = null
	@JvmField var depthSlice: Int = 0
	@JvmField var resolveTarget: com.sun.jna.Pointer? = null
	@JvmField var loadOp: com.sun.jna.Pointer? = null
	@JvmField var storeOp: com.sun.jna.Pointer? = null
	@JvmField val clearValue: WGPUColorByValue = WGPUColorByValue(WGPURenderPassColorAttachment(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "view", "depthSlice", "resolveTarget", "loadOp", "storeOp", "clearValue")
}
internal class WGPURenderPassDepthStencilAttachmentByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
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
}
internal class WGPURenderPassDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPURenderPassDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var colorAttachmentCount: Long = 0L
	@JvmField var colorAttachments: com.sun.jna.Pointer? = null
	@JvmField var depthStencilAttachment: com.sun.jna.Pointer? = null
	@JvmField var occlusionQuerySet: com.sun.jna.Pointer? = null
	@JvmField var timestampWrites: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorAttachmentCount", "colorAttachments", "depthStencilAttachment", "occlusionQuerySet", "timestampWrites")
}
internal class WGPURenderPassMaxDrawCountByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPURenderPassMaxDrawCount(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var maxDrawCount: Long = 0L
	override fun getFieldOrder() = listOf("chain", "maxDrawCount")
}
internal class WGPURenderPassTimestampWritesByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
}
internal class WGPURenderPipelineDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPURenderPipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField val vertex: WGPUVertexStateByValue = WGPUVertexStateByValue(WGPURenderPipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val primitive: WGPUPrimitiveStateByValue = WGPUPrimitiveStateByValue(WGPURenderPipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var depthStencil: com.sun.jna.Pointer? = null
	@JvmField val multisample: WGPUMultisampleStateByValue = WGPUMultisampleStateByValue(WGPURenderPipelineDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var fragment: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "vertex", "primitive", "depthStencil", "multisample", "fragment")
}
internal class WGPURequestAdapterOptionsByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var compatibleSurface: com.sun.jna.Pointer? = null
	@JvmField var powerPreference: com.sun.jna.Pointer? = null
	@JvmField var backendType: com.sun.jna.Pointer? = null
	@JvmField var forceFallbackAdapter: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "compatibleSurface", "powerPreference", "backendType", "forceFallbackAdapter")
}
internal class WGPURequiredLimitsByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val limits: WGPULimitsByValue = WGPULimitsByValue(WGPURequiredLimits(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "limits")
}
internal class WGPUSamplerBindingLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var type: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "type")
}
internal class WGPUSamplerDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUSamplerDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
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
}
internal class WGPUShaderModuleDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUShaderModuleDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPUShaderSourceSPIRVByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUShaderSourceSPIRV(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var codeSize: Int = 0
	@JvmField var code: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "codeSize", "code")
}
internal class WGPUShaderSourceWGSLByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUShaderSourceWGSL(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField val code: WGPUStringViewByValue = WGPUStringViewByValue(WGPUShaderSourceWGSL(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("chain", "code")
}
internal class WGPUStencilFaceStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var compare: com.sun.jna.Pointer? = null
	@JvmField var failOp: com.sun.jna.Pointer? = null
	@JvmField var depthFailOp: com.sun.jna.Pointer? = null
	@JvmField var passOp: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("compare", "failOp", "depthFailOp", "passOp")
}
internal class WGPUStorageTextureBindingLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var access: com.sun.jna.Pointer? = null
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var viewDimension: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "access", "format", "viewDimension")
}
internal class WGPUSupportedFeaturesByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var featureCount: Long = 0L
	@JvmField var features: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "featureCount", "features")
}
internal class WGPUSupportedLimitsByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val limits: WGPULimitsByValue = WGPULimitsByValue(WGPUSupportedLimits(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "limits")
}
internal class WGPUSurfaceCapabilitiesByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var usages: Long = 0L
	@JvmField var formatCount: Long = 0L
	@JvmField var formats: com.sun.jna.Pointer? = null
	@JvmField var presentModeCount: Long = 0L
	@JvmField var presentModes: com.sun.jna.Pointer? = null
	@JvmField var alphaModeCount: Long = 0L
	@JvmField var alphaModes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "usages", "formatCount", "formats", "presentModeCount", "presentModes", "alphaModeCount", "alphaModes")
}
internal class WGPUSurfaceConfigurationByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
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
}
internal class WGPUSurfaceDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUSurfaceDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	override fun getFieldOrder() = listOf("nextInChain", "label")
}
internal class WGPUSurfaceSourceAndroidNativeWindowByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceAndroidNativeWindow(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var window: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "window")
}
internal class WGPUSurfaceSourceMetalLayerByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceMetalLayer(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var layer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "layer")
}
internal class WGPUSurfaceSourceWaylandSurfaceByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceWaylandSurface(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var surface: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "display", "surface")
}
internal class WGPUSurfaceSourceWindowsHWNDByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceWindowsHWND(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var hinstance: com.sun.jna.Pointer? = null
	@JvmField var hwnd: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "hinstance", "hwnd")
}
internal class WGPUSurfaceSourceXCBWindowByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceXCBWindow(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var connection: com.sun.jna.Pointer? = null
	@JvmField var window: Int = 0
	override fun getFieldOrder() = listOf("chain", "connection", "window")
}
internal class WGPUSurfaceSourceXlibWindowByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField val chain: WGPUChainedStructByValue = WGPUChainedStructByValue(WGPUSurfaceSourceXlibWindow(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var window: Long = 0L
	override fun getFieldOrder() = listOf("chain", "display", "window")
}
internal class WGPUSurfaceTextureByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var status: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("texture", "status")
}
internal class WGPUTextureBindingLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var sampleType: com.sun.jna.Pointer? = null
	@JvmField var viewDimension: com.sun.jna.Pointer? = null
	@JvmField var multisampled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "sampleType", "viewDimension", "multisampled")
}
internal class WGPUTextureDataLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var bytesPerRow: Int = 0
	@JvmField var rowsPerImage: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "offset", "bytesPerRow", "rowsPerImage")
}
internal class WGPUTextureDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUTextureDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var usage: Long = 0L
	@JvmField var dimension: com.sun.jna.Pointer? = null
	@JvmField val size: WGPUExtent3DByValue = WGPUExtent3DByValue(WGPUTextureDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var mipLevelCount: Int = 0
	@JvmField var sampleCount: Int = 0
	@JvmField var viewFormatCount: Long = 0L
	@JvmField var viewFormats: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "dimension", "size", "format", "mipLevelCount", "sampleCount", "viewFormatCount", "viewFormats")
}
internal class WGPUTextureViewDescriptorByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField val label: WGPUStringViewByValue = WGPUStringViewByValue(WGPUTextureViewDescriptor(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var dimension: com.sun.jna.Pointer? = null
	@JvmField var baseMipLevel: Int = 0
	@JvmField var mipLevelCount: Int = 0
	@JvmField var baseArrayLayer: Int = 0
	@JvmField var arrayLayerCount: Int = 0
	@JvmField var aspect: com.sun.jna.Pointer? = null
	@JvmField var usage: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "label", "format", "dimension", "baseMipLevel", "mipLevelCount", "baseArrayLayer", "arrayLayerCount", "aspect", "usage")
}
internal class WGPUVertexAttributeByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var format: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var shaderLocation: Int = 0
	override fun getFieldOrder() = listOf("format", "offset", "shaderLocation")
}
internal class WGPUVertexBufferLayoutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var arrayStride: Long = 0L
	@JvmField var stepMode: com.sun.jna.Pointer? = null
	@JvmField var attributeCount: Long = 0L
	@JvmField var attributes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("arrayStride", "stepMode", "attributeCount", "attributes")
}
internal class WGPUVertexStateByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField val entryPoint: WGPUStringViewByValue = WGPUStringViewByValue(WGPUVertexState(handler.let{com.sun.jna.Pointer(it)}.let(::NativeAddress)).handler.pointer.toAddress())
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var bufferCount: Long = 0L
	@JvmField var buffers: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "bufferCount", "buffers")
}
internal class WGPUChainedStructByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var next: com.sun.jna.Pointer? = null
	@JvmField var sType: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("next", "sType")
}
internal class WGPUChainedStructOutByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var next: com.sun.jna.Pointer? = null
	@JvmField var sType: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("next", "sType")
}
internal class WGPUStringViewByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var data: com.sun.jna.Pointer? = null
	@JvmField var length: Long = 0L
	override fun getFieldOrder() = listOf("data", "length")
}
internal class WGPUBufferMapCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUCompilationInfoCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUCreateComputePipelineAsyncCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUCreateRenderPipelineAsyncCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUDeviceLostCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUPopErrorScopeCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUQueueWorkDoneCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPURequestAdapterCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPURequestDeviceCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
internal class WGPUUncapturedErrorCallbackInfoByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var callback: com.sun.jna.Pointer? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "callback", "userdata1", "userdata2")
}
