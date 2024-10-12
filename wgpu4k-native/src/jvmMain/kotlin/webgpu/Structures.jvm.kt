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
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout.structLayout

@JvmInline
actual value class WGPUAdapterInfo(actual override val handler: NativeAddress) : CStructure {
	actual val vendor: WGPUStringView
		get() = get(vendorLayout, vendorOffset).let(::WGPUStringView)

	actual val architecture: WGPUStringView
		get() = get(architectureLayout, architectureOffset).let(::WGPUStringView)

	actual val device: WGPUStringView
		get() = get(deviceLayout, deviceOffset).let(::WGPUStringView)

	actual val description: WGPUStringView
		get() = get(descriptionLayout, descriptionOffset).let(::WGPUStringView)

	actual var backendType: WGPUBackendType
		get() = getUInt(backendTypeLayout, backendTypeOffset)
		set(newValue) = set(backendTypeLayout, backendTypeOffset, newValue)

	actual var adapterType: WGPUAdapterType
		get() = getUInt(adapterTypeLayout, adapterTypeOffset)
		set(newValue) = set(adapterTypeLayout, adapterTypeOffset, newValue)

	actual var vendorID: UInt
		get() = getUInt(vendorIDLayout, vendorIDOffset)
		set(newValue) = set(vendorIDLayout, vendorIDOffset, newValue)

	actual var deviceID: UInt
		get() = getUInt(deviceIDLayout, deviceIDOffset)
		set(newValue) = set(deviceIDLayout, deviceIDOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUAdapterInfo)
		}
		internal val LAYOUT = structLayout(
			WGPUStringView.LAYOUT.withName("vendor"),
			WGPUStringView.LAYOUT.withName("architecture"),
			WGPUStringView.LAYOUT.withName("device"),
			WGPUStringView.LAYOUT.withName("description"),
			C_INT.withName("backendType"),
			C_INT.withName("adapterType"),
			C_INT.withName("vendorID"),
			C_INT.withName("deviceID")
		).withName("WGPUAdapterInfo")

		val vendorOffset = 0L
		val vendorLayout = LAYOUT.withName("vendor")

		val architectureOffset = LAYOUT.withName("vendor").byteSize() + vendorOffset
		val architectureLayout = LAYOUT.withName("architecture")

		val deviceOffset = LAYOUT.withName("architecture").byteSize() + architectureOffset
		val deviceLayout = LAYOUT.withName("device")

		val descriptionOffset = LAYOUT.withName("device").byteSize() + deviceOffset
		val descriptionLayout = LAYOUT.withName("description")

		val backendTypeOffset = LAYOUT.withName("description").byteSize() + descriptionOffset
		val backendTypeLayout = LAYOUT.withName("backendType")

		val adapterTypeOffset = 4L + backendTypeOffset
		val adapterTypeLayout = LAYOUT.withName("adapterType")

		val vendorIDOffset = 4L + adapterTypeOffset
		val vendorIDLayout = LAYOUT.withName("vendorID")

		val deviceIDOffset = 4L + vendorIDOffset
		val deviceIDLayout = LAYOUT.withName("deviceID")

	}
}

@JvmInline
actual value class WGPUBindGroupDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var layout: WGPUBindGroupLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUBindGroupLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual var entryCount: ULong
		get() = getULong(entryCountLayout, entryCountOffset)
		set(newValue) = set(entryCountLayout, entryCountOffset, newValue)

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
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val layoutOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val layoutLayout = LAYOUT.withName("layout")

		val entryCountOffset = 8L + layoutOffset
		val entryCountLayout = LAYOUT.withName("entryCount")

		val entriesOffset = 8L + entryCountOffset
		val entriesLayout = LAYOUT.withName("entries")

	}
}

@JvmInline
actual value class WGPUBindGroupEntry(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var binding: UInt
		get() = getUInt(bindingLayout, bindingOffset)
		set(newValue) = set(bindingLayout, bindingOffset, newValue)

	actual var buffer: WGPUBuffer?
		get() = get(bufferLayout, bufferOffset).let(::WGPUBuffer)
		set(newValue) = set(bufferLayout, bufferOffset, newValue?.handler)

	actual var offset: ULong
		get() = getULong(offsetLayout, offsetOffset)
		set(newValue) = set(offsetLayout, offsetOffset, newValue)

	actual var size: ULong
		get() = getULong(sizeLayout, sizeOffset)
		set(newValue) = set(sizeLayout, sizeOffset, newValue)

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
			C_POINTER.withName("textureView")
		).withName("WGPUBindGroupEntry")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val bindingOffset = 8L + nextInChainOffset
		val bindingLayout = LAYOUT.withName("binding")

		val bufferOffset = 4L + bindingOffset
		val bufferLayout = LAYOUT.withName("buffer")

		val offsetOffset = 8L + bufferOffset
		val offsetLayout = LAYOUT.withName("offset")

		val sizeOffset = 8L + offsetOffset
		val sizeLayout = LAYOUT.withName("size")

		val samplerOffset = 8L + sizeOffset
		val samplerLayout = LAYOUT.withName("sampler")

		val textureViewOffset = 8L + samplerOffset
		val textureViewLayout = LAYOUT.withName("textureView")

	}
}

@JvmInline
actual value class WGPUBindGroupLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var entryCount: ULong
		get() = getULong(entryCountLayout, entryCountOffset)
		set(newValue) = set(entryCountLayout, entryCountOffset, newValue)

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
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupLayoutDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val entryCountOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val entryCountLayout = LAYOUT.withName("entryCount")

		val entriesOffset = 8L + entryCountOffset
		val entriesLayout = LAYOUT.withName("entries")

	}
}

@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var binding: UInt
		get() = getUInt(bindingLayout, bindingOffset)
		set(newValue) = set(bindingLayout, bindingOffset, newValue)

	actual var visibility: ULong
		get() = getULong(visibilityLayout, visibilityOffset)
		set(newValue) = set(visibilityLayout, visibilityOffset, newValue)

	actual val buffer: WGPUBufferBindingLayout
		get() = get(bufferLayout, bufferOffset).let(::WGPUBufferBindingLayout)

	actual val sampler: WGPUSamplerBindingLayout
		get() = get(samplerLayout, samplerOffset).let(::WGPUSamplerBindingLayout)

	actual val texture: WGPUTextureBindingLayout
		get() = get(textureLayout, textureOffset).let(::WGPUTextureBindingLayout)

	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = get(storageTextureLayout, storageTextureOffset).let(::WGPUStorageTextureBindingLayout)

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
			WGPUStorageTextureBindingLayout.LAYOUT.withName("storageTexture")
		).withName("WGPUBindGroupLayoutEntry")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val bindingOffset = 8L + nextInChainOffset
		val bindingLayout = LAYOUT.withName("binding")

		val visibilityOffset = 4L + bindingOffset
		val visibilityLayout = LAYOUT.withName("visibility")

		val bufferOffset = 8L + visibilityOffset
		val bufferLayout = LAYOUT.withName("buffer")

		val samplerOffset = LAYOUT.withName("buffer").byteSize() + bufferOffset
		val samplerLayout = LAYOUT.withName("sampler")

		val textureOffset = LAYOUT.withName("sampler").byteSize() + samplerOffset
		val textureLayout = LAYOUT.withName("texture")

		val storageTextureOffset = LAYOUT.withName("texture").byteSize() + textureOffset
		val storageTextureLayout = LAYOUT.withName("storageTexture")

	}
}

@JvmInline
actual value class WGPUBlendComponent(actual override val handler: NativeAddress) : CStructure {
	actual var operation: WGPUBlendOperation
		get() = getUInt(operationLayout, operationOffset)
		set(newValue) = set(operationLayout, operationOffset, newValue)

	actual var srcFactor: WGPUBlendFactor
		get() = getUInt(srcFactorLayout, srcFactorOffset)
		set(newValue) = set(srcFactorLayout, srcFactorOffset, newValue)

	actual var dstFactor: WGPUBlendFactor
		get() = getUInt(dstFactorLayout, dstFactorOffset)
		set(newValue) = set(dstFactorLayout, dstFactorOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBlendComponent)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("operation"),
			C_INT.withName("srcFactor"),
			C_INT.withName("dstFactor")
		).withName("WGPUBlendComponent")

		val operationOffset = 0L
		val operationLayout = LAYOUT.withName("operation")

		val srcFactorOffset = 4L + operationOffset
		val srcFactorLayout = LAYOUT.withName("srcFactor")

		val dstFactorOffset = 4L + srcFactorOffset
		val dstFactorLayout = LAYOUT.withName("dstFactor")

	}
}

@JvmInline
actual value class WGPUBlendState(actual override val handler: NativeAddress) : CStructure {
	actual val color: WGPUBlendComponent
		get() = get(colorLayout, colorOffset).let(::WGPUBlendComponent)

	actual val alpha: WGPUBlendComponent
		get() = get(alphaLayout, alphaOffset).let(::WGPUBlendComponent)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBlendState)
		}
		internal val LAYOUT = structLayout(
			WGPUBlendComponent.LAYOUT.withName("color"),
			WGPUBlendComponent.LAYOUT.withName("alpha")
		).withName("WGPUBlendState")

		val colorOffset = 0L
		val colorLayout = LAYOUT.withName("color")

		val alphaOffset = LAYOUT.withName("color").byteSize() + colorOffset
		val alphaLayout = LAYOUT.withName("alpha")

	}
}

@JvmInline
actual value class WGPUBufferBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var type: WGPUBufferBindingType
		get() = getUInt(typeLayout, typeOffset)
		set(newValue) = set(typeLayout, typeOffset, newValue)

	actual var hasDynamicOffset: Boolean
		get() = getInt(hasDynamicOffsetLayout, hasDynamicOffsetOffset).toBoolean()
		set(newValue) = set(hasDynamicOffsetLayout, hasDynamicOffsetOffset, newValue)

	actual var minBindingSize: ULong
		get() = getULong(minBindingSizeLayout, minBindingSizeOffset)
		set(newValue) = set(minBindingSizeLayout, minBindingSizeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUBufferBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("type"),
			C_INT.withName("hasDynamicOffset"),
			C_LONG.withName("minBindingSize")
		).withName("WGPUBufferBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val typeOffset = 8L + nextInChainOffset
		val typeLayout = LAYOUT.withName("type")

		val hasDynamicOffsetOffset = 4L + typeOffset
		val hasDynamicOffsetLayout = LAYOUT.withName("hasDynamicOffset")

		val minBindingSizeOffset = 4L + hasDynamicOffsetOffset
		val minBindingSizeLayout = LAYOUT.withName("minBindingSize")

	}
}

@JvmInline
actual value class WGPUBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var usage: ULong
		get() = getULong(usageLayout, usageOffset)
		set(newValue) = set(usageLayout, usageOffset, newValue)

	actual var size: ULong
		get() = getULong(sizeLayout, sizeOffset)
		set(newValue) = set(sizeLayout, sizeOffset, newValue)

	actual var mappedAtCreation: Boolean
		get() = getInt(mappedAtCreationLayout, mappedAtCreationOffset).toBoolean()
		set(newValue) = set(mappedAtCreationLayout, mappedAtCreationOffset, newValue)

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
			C_INT.withName("mappedAtCreation")
		).withName("WGPUBufferDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val usageOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val usageLayout = LAYOUT.withName("usage")

		val sizeOffset = 8L + usageOffset
		val sizeLayout = LAYOUT.withName("size")

		val mappedAtCreationOffset = 8L + sizeOffset
		val mappedAtCreationLayout = LAYOUT.withName("mappedAtCreation")

	}
}

@JvmInline
actual value class WGPUColor(actual override val handler: NativeAddress) : CStructure {
	actual var r: Double
		get() = getDouble(rLayout, rOffset)
		set(newValue) = set(rLayout, rOffset, newValue)

	actual var g: Double
		get() = getDouble(gLayout, gOffset)
		set(newValue) = set(gLayout, gOffset, newValue)

	actual var b: Double
		get() = getDouble(bLayout, bOffset)
		set(newValue) = set(bLayout, bOffset, newValue)

	actual var a: Double
		get() = getDouble(aLayout, aOffset)
		set(newValue) = set(aLayout, aOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUColor)
		}
		internal val LAYOUT = structLayout(
			C_DOUBLE.withName("r"),
			C_DOUBLE.withName("g"),
			C_DOUBLE.withName("b"),
			C_DOUBLE.withName("a")
		).withName("WGPUColor")

		val rOffset = 0L
		val rLayout = LAYOUT.withName("r")

		val gOffset = 8L + rOffset
		val gLayout = LAYOUT.withName("g")

		val bOffset = 8L + gOffset
		val bLayout = LAYOUT.withName("b")

		val aOffset = 8L + bOffset
		val aLayout = LAYOUT.withName("a")

	}
}

@JvmInline
actual value class WGPUColorTargetState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var blend: WGPUBlendState?
		get() = get(blendLayout, blendOffset).let(::WGPUBlendState)
		set(newValue) = set(blendLayout, blendOffset, newValue?.handler)

	actual var writeMask: ULong
		get() = getULong(writeMaskLayout, writeMaskOffset)
		set(newValue) = set(writeMaskLayout, writeMaskOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUColorTargetState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("format"),
			C_POINTER.withName("blend"),
			C_LONG.withName("writeMask")
		).withName("WGPUColorTargetState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val formatOffset = 8L + nextInChainOffset
		val formatLayout = LAYOUT.withName("format")

		val blendOffset = 4L + formatOffset
		val blendLayout = LAYOUT.withName("blend")

		val writeMaskOffset = 8L + blendOffset
		val writeMaskLayout = LAYOUT.withName("writeMask")

	}
}

@JvmInline
actual value class WGPUCommandBufferDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCommandBufferDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPUCommandBufferDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPUCommandEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUCommandEncoderDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPUCommandEncoderDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPUCompilationInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var messageCount: ULong
		get() = getULong(messageCountLayout, messageCountOffset)
		set(newValue) = set(messageCountLayout, messageCountOffset, newValue)

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
			C_POINTER.withName("messages")
		).withName("WGPUCompilationInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val messageCountOffset = 8L + nextInChainOffset
		val messageCountLayout = LAYOUT.withName("messageCount")

		val messagesOffset = 8L + messageCountOffset
		val messagesLayout = LAYOUT.withName("messages")

	}
}

@JvmInline
actual value class WGPUCompilationMessage(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val message: WGPUStringView
		get() = get(messageLayout, messageOffset).let(::WGPUStringView)

	actual var type: WGPUCompilationMessageType
		get() = getUInt(typeLayout, typeOffset)
		set(newValue) = set(typeLayout, typeOffset, newValue)

	actual var lineNum: ULong
		get() = getULong(lineNumLayout, lineNumOffset)
		set(newValue) = set(lineNumLayout, lineNumOffset, newValue)

	actual var linePos: ULong
		get() = getULong(linePosLayout, linePosOffset)
		set(newValue) = set(linePosLayout, linePosOffset, newValue)

	actual var offset: ULong
		get() = getULong(offsetLayout, offsetOffset)
		set(newValue) = set(offsetLayout, offsetOffset, newValue)

	actual var length: ULong
		get() = getULong(lengthLayout, lengthOffset)
		set(newValue) = set(lengthLayout, lengthOffset, newValue)

	actual var utf16LinePos: ULong
		get() = getULong(utf16LinePosLayout, utf16LinePosOffset)
		set(newValue) = set(utf16LinePosLayout, utf16LinePosOffset, newValue)

	actual var utf16Offset: ULong
		get() = getULong(utf16OffsetLayout, utf16OffsetOffset)
		set(newValue) = set(utf16OffsetLayout, utf16OffsetOffset, newValue)

	actual var utf16Length: ULong
		get() = getULong(utf16LengthLayout, utf16LengthOffset)
		set(newValue) = set(utf16LengthLayout, utf16LengthOffset, newValue)

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
			C_LONG.withName("utf16Length")
		).withName("WGPUCompilationMessage")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val messageOffset = 8L + nextInChainOffset
		val messageLayout = LAYOUT.withName("message")

		val typeOffset = LAYOUT.withName("message").byteSize() + messageOffset
		val typeLayout = LAYOUT.withName("type")

		val lineNumOffset = 4L + typeOffset
		val lineNumLayout = LAYOUT.withName("lineNum")

		val linePosOffset = 8L + lineNumOffset
		val linePosLayout = LAYOUT.withName("linePos")

		val offsetOffset = 8L + linePosOffset
		val offsetLayout = LAYOUT.withName("offset")

		val lengthOffset = 8L + offsetOffset
		val lengthLayout = LAYOUT.withName("length")

		val utf16LinePosOffset = 8L + lengthOffset
		val utf16LinePosLayout = LAYOUT.withName("utf16LinePos")

		val utf16OffsetOffset = 8L + utf16LinePosOffset
		val utf16OffsetLayout = LAYOUT.withName("utf16Offset")

		val utf16LengthOffset = 8L + utf16OffsetOffset
		val utf16LengthLayout = LAYOUT.withName("utf16Length")

	}
}

@JvmInline
actual value class WGPUComputePassDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

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
			C_POINTER.withName("timestampWrites")
		).withName("WGPUComputePassDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val timestampWritesOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val timestampWritesLayout = LAYOUT.withName("timestampWrites")

	}
}

@JvmInline
actual value class WGPUComputePassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	actual var querySet: WGPUQuerySet?
		get() = get(querySetLayout, querySetOffset).let(::WGPUQuerySet)
		set(newValue) = set(querySetLayout, querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt(beginningOfPassWriteIndexLayout, beginningOfPassWriteIndexOffset)
		set(newValue) = set(beginningOfPassWriteIndexLayout, beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt(endOfPassWriteIndexLayout, endOfPassWriteIndexOffset)
		set(newValue) = set(endOfPassWriteIndexLayout, endOfPassWriteIndexOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassTimestampWrites {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUComputePassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPUComputePassTimestampWrites")

		val querySetOffset = 0L
		val querySetLayout = LAYOUT.withName("querySet")

		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val beginningOfPassWriteIndexLayout = LAYOUT.withName("beginningOfPassWriteIndex")

		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
		val endOfPassWriteIndexLayout = LAYOUT.withName("endOfPassWriteIndex")

	}
}

@JvmInline
actual value class WGPUComputePipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var layout: WGPUPipelineLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUPipelineLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual val compute: WGPUProgrammableStageDescriptor
		get() = get(computeLayout, computeOffset).let(::WGPUProgrammableStageDescriptor)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUComputePipelineDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("layout"),
			WGPUProgrammableStageDescriptor.LAYOUT.withName("compute")
		).withName("WGPUComputePipelineDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val layoutOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val layoutLayout = LAYOUT.withName("layout")

		val computeOffset = 8L + layoutOffset
		val computeLayout = LAYOUT.withName("compute")

	}
}

@JvmInline
actual value class WGPUConstantEntry(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val key: WGPUStringView
		get() = get(keyLayout, keyOffset).let(::WGPUStringView)

	actual var value: Double
		get() = getDouble(valueLayout, valueOffset)
		set(newValue) = set(valueLayout, valueOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUConstantEntry)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("key"),
			C_DOUBLE.withName("value")
		).withName("WGPUConstantEntry")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val keyOffset = 8L + nextInChainOffset
		val keyLayout = LAYOUT.withName("key")

		val valueOffset = LAYOUT.withName("key").byteSize() + keyOffset
		val valueLayout = LAYOUT.withName("value")

	}
}

@JvmInline
actual value class WGPUDepthStencilState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var depthWriteEnabled: WGPUOptionalBool
		get() = getUInt(depthWriteEnabledLayout, depthWriteEnabledOffset)
		set(newValue) = set(depthWriteEnabledLayout, depthWriteEnabledOffset, newValue)

	actual var depthCompare: WGPUCompareFunction
		get() = getUInt(depthCompareLayout, depthCompareOffset)
		set(newValue) = set(depthCompareLayout, depthCompareOffset, newValue)

	actual val stencilFront: WGPUStencilFaceState
		get() = get(stencilFrontLayout, stencilFrontOffset).let(::WGPUStencilFaceState)

	actual val stencilBack: WGPUStencilFaceState
		get() = get(stencilBackLayout, stencilBackOffset).let(::WGPUStencilFaceState)

	actual var stencilReadMask: UInt
		get() = getUInt(stencilReadMaskLayout, stencilReadMaskOffset)
		set(newValue) = set(stencilReadMaskLayout, stencilReadMaskOffset, newValue)

	actual var stencilWriteMask: UInt
		get() = getUInt(stencilWriteMaskLayout, stencilWriteMaskOffset)
		set(newValue) = set(stencilWriteMaskLayout, stencilWriteMaskOffset, newValue)

	actual var depthBias: Int
		get() = getInt(depthBiasLayout, depthBiasOffset)
		set(newValue) = set(depthBiasLayout, depthBiasOffset, newValue)

	actual var depthBiasSlopeScale: Float
		get() = getFloat(depthBiasSlopeScaleLayout, depthBiasSlopeScaleOffset)
		set(newValue) = set(depthBiasSlopeScaleLayout, depthBiasSlopeScaleOffset, newValue)

	actual var depthBiasClamp: Float
		get() = getFloat(depthBiasClampLayout, depthBiasClampOffset)
		set(newValue) = set(depthBiasClampLayout, depthBiasClampOffset, newValue)

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
			C_FLOAT.withName("depthBiasClamp")
		).withName("WGPUDepthStencilState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val formatOffset = 8L + nextInChainOffset
		val formatLayout = LAYOUT.withName("format")

		val depthWriteEnabledOffset = 4L + formatOffset
		val depthWriteEnabledLayout = LAYOUT.withName("depthWriteEnabled")

		val depthCompareOffset = 4L + depthWriteEnabledOffset
		val depthCompareLayout = LAYOUT.withName("depthCompare")

		val stencilFrontOffset = 4L + depthCompareOffset
		val stencilFrontLayout = LAYOUT.withName("stencilFront")

		val stencilBackOffset = LAYOUT.withName("stencilFront").byteSize() + stencilFrontOffset
		val stencilBackLayout = LAYOUT.withName("stencilBack")

		val stencilReadMaskOffset = LAYOUT.withName("stencilBack").byteSize() + stencilBackOffset
		val stencilReadMaskLayout = LAYOUT.withName("stencilReadMask")

		val stencilWriteMaskOffset = 4L + stencilReadMaskOffset
		val stencilWriteMaskLayout = LAYOUT.withName("stencilWriteMask")

		val depthBiasOffset = 4L + stencilWriteMaskOffset
		val depthBiasLayout = LAYOUT.withName("depthBias")

		val depthBiasSlopeScaleOffset = 4L + depthBiasOffset
		val depthBiasSlopeScaleLayout = LAYOUT.withName("depthBiasSlopeScale")

		val depthBiasClampOffset = 4L + depthBiasSlopeScaleOffset
		val depthBiasClampLayout = LAYOUT.withName("depthBiasClamp")

	}
}

@JvmInline
actual value class WGPUDeviceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var requiredFeatureCount: ULong
		get() = getULong(requiredFeatureCountLayout, requiredFeatureCountOffset)
		set(newValue) = set(requiredFeatureCountLayout, requiredFeatureCountOffset, newValue)

	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
		get() = get(requiredFeaturesLayout, requiredFeaturesOffset).let(::ArrayHolder)
		set(newValue) = set(requiredFeaturesLayout, requiredFeaturesOffset, newValue?.handler)

	actual var requiredLimits: WGPURequiredLimits?
		get() = get(requiredLimitsLayout, requiredLimitsOffset).let(::WGPURequiredLimits)
		set(newValue) = set(requiredLimitsLayout, requiredLimitsOffset, newValue?.handler)

	actual val defaultQueue: WGPUQueueDescriptor
		get() = get(defaultQueueLayout, defaultQueueOffset).let(::WGPUQueueDescriptor)

	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
		get() = get(deviceLostCallbackInfoLayout, deviceLostCallbackInfoOffset).let(::WGPUDeviceLostCallbackInfo)

	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
		get() = get(uncapturedErrorCallbackInfoLayout, uncapturedErrorCallbackInfoOffset).let(::WGPUUncapturedErrorCallbackInfo)

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
			WGPUUncapturedErrorCallbackInfo.LAYOUT.withName("uncapturedErrorCallbackInfo")
		).withName("WGPUDeviceDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val requiredFeatureCountOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val requiredFeatureCountLayout = LAYOUT.withName("requiredFeatureCount")

		val requiredFeaturesOffset = 8L + requiredFeatureCountOffset
		val requiredFeaturesLayout = LAYOUT.withName("requiredFeatures")

		val requiredLimitsOffset = 8L + requiredFeaturesOffset
		val requiredLimitsLayout = LAYOUT.withName("requiredLimits")

		val defaultQueueOffset = 8L + requiredLimitsOffset
		val defaultQueueLayout = LAYOUT.withName("defaultQueue")

		val deviceLostCallbackInfoOffset = LAYOUT.withName("defaultQueue").byteSize() + defaultQueueOffset
		val deviceLostCallbackInfoLayout = LAYOUT.withName("deviceLostCallbackInfo")

		val uncapturedErrorCallbackInfoOffset = LAYOUT.withName("deviceLostCallbackInfo").byteSize() + deviceLostCallbackInfoOffset
		val uncapturedErrorCallbackInfoLayout = LAYOUT.withName("uncapturedErrorCallbackInfo")

	}
}

@JvmInline
actual value class WGPUExtent3D(actual override val handler: NativeAddress) : CStructure {
	actual var width: UInt
		get() = getUInt(widthLayout, widthOffset)
		set(newValue) = set(widthLayout, widthOffset, newValue)

	actual var height: UInt
		get() = getUInt(heightLayout, heightOffset)
		set(newValue) = set(heightLayout, heightOffset, newValue)

	actual var depthOrArrayLayers: UInt
		get() = getUInt(depthOrArrayLayersLayout, depthOrArrayLayersOffset)
		set(newValue) = set(depthOrArrayLayersLayout, depthOrArrayLayersOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUExtent3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_INT.withName("depthOrArrayLayers")
		).withName("WGPUExtent3D")

		val widthOffset = 0L
		val widthLayout = LAYOUT.withName("width")

		val heightOffset = 4L + widthOffset
		val heightLayout = LAYOUT.withName("height")

		val depthOrArrayLayersOffset = 4L + heightOffset
		val depthOrArrayLayersLayout = LAYOUT.withName("depthOrArrayLayers")

	}
}

@JvmInline
actual value class WGPUFragmentState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = get(entryPointLayout, entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountLayout, constantCountOffset)
		set(newValue) = set(constantCountLayout, constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual var targetCount: ULong
		get() = getULong(targetCountLayout, targetCountOffset)
		set(newValue) = set(targetCountLayout, targetCountOffset, newValue)

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
			C_POINTER.withName("targets")
		).withName("WGPUFragmentState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = LAYOUT.withName("module")

		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = LAYOUT.withName("entryPoint")

		val constantCountOffset = LAYOUT.withName("entryPoint").byteSize() + entryPointOffset
		val constantCountLayout = LAYOUT.withName("constantCount")

		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = LAYOUT.withName("constants")

		val targetCountOffset = 8L + constantsOffset
		val targetCountLayout = LAYOUT.withName("targetCount")

		val targetsOffset = 8L + targetCountOffset
		val targetsLayout = LAYOUT.withName("targets")

	}
}

@JvmInline
actual value class WGPUFuture(actual override val handler: NativeAddress) : CStructure {
	actual var id: ULong
		get() = getULong(idLayout, idOffset)
		set(newValue) = set(idLayout, idOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUFuture)
		}
		internal val LAYOUT = structLayout(
			C_LONG.withName("id")
		).withName("WGPUFuture")

		val idOffset = 0L
		val idLayout = LAYOUT.withName("id")

	}
}

@JvmInline
actual value class WGPUFutureWaitInfo(actual override val handler: NativeAddress) : CStructure {
	actual val future: WGPUFuture
		get() = get(futureLayout, futureOffset).let(::WGPUFuture)

	actual var completed: Boolean
		get() = getInt(completedLayout, completedOffset).toBoolean()
		set(newValue) = set(completedLayout, completedOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUFutureWaitInfo)
		}
		internal val LAYOUT = structLayout(
			WGPUFuture.LAYOUT.withName("future"),
			C_INT.withName("completed")
		).withName("WGPUFutureWaitInfo")

		val futureOffset = 0L
		val futureLayout = LAYOUT.withName("future")

		val completedOffset = LAYOUT.withName("future").byteSize() + futureOffset
		val completedLayout = LAYOUT.withName("completed")

	}
}

@JvmInline
actual value class WGPUImageCopyBuffer(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val layout: WGPUTextureDataLayout
		get() = get(layoutLayout, layoutOffset).let(::WGPUTextureDataLayout)

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
			C_POINTER.withName("buffer")
		).withName("WGPUImageCopyBuffer")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val layoutOffset = 8L + nextInChainOffset
		val layoutLayout = LAYOUT.withName("layout")

		val bufferOffset = LAYOUT.withName("layout").byteSize() + layoutOffset
		val bufferLayout = LAYOUT.withName("buffer")

	}
}

@JvmInline
actual value class WGPUImageCopyTexture(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var texture: WGPUTexture?
		get() = get(textureLayout, textureOffset).let(::WGPUTexture)
		set(newValue) = set(textureLayout, textureOffset, newValue?.handler)

	actual var mipLevel: UInt
		get() = getUInt(mipLevelLayout, mipLevelOffset)
		set(newValue) = set(mipLevelLayout, mipLevelOffset, newValue)

	actual val origin: WGPUOrigin3D
		get() = get(originLayout, originOffset).let(::WGPUOrigin3D)

	actual var aspect: WGPUTextureAspect
		get() = getUInt(aspectLayout, aspectOffset)
		set(newValue) = set(aspectLayout, aspectOffset, newValue)

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
			C_INT.withName("aspect")
		).withName("WGPUImageCopyTexture")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val textureOffset = 8L + nextInChainOffset
		val textureLayout = LAYOUT.withName("texture")

		val mipLevelOffset = 8L + textureOffset
		val mipLevelLayout = LAYOUT.withName("mipLevel")

		val originOffset = 4L + mipLevelOffset
		val originLayout = LAYOUT.withName("origin")

		val aspectOffset = LAYOUT.withName("origin").byteSize() + originOffset
		val aspectLayout = LAYOUT.withName("aspect")

	}
}

@JvmInline
actual value class WGPUInstanceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val features: WGPUInstanceFeatures
		get() = get(featuresLayout, featuresOffset).let(::WGPUInstanceFeatures)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUInstanceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUInstanceFeatures.LAYOUT.withName("features")
		).withName("WGPUInstanceDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val featuresOffset = 8L + nextInChainOffset
		val featuresLayout = LAYOUT.withName("features")

	}
}

@JvmInline
actual value class WGPUInstanceFeatures(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var timedWaitAnyEnable: Boolean
		get() = getInt(timedWaitAnyEnableLayout, timedWaitAnyEnableOffset).toBoolean()
		set(newValue) = set(timedWaitAnyEnableLayout, timedWaitAnyEnableOffset, newValue)

	actual var timedWaitAnyMaxCount: ULong
		get() = getULong(timedWaitAnyMaxCountLayout, timedWaitAnyMaxCountOffset)
		set(newValue) = set(timedWaitAnyMaxCountLayout, timedWaitAnyMaxCountOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceFeatures {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUInstanceFeatures)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("timedWaitAnyEnable"),
			C_LONG.withName("timedWaitAnyMaxCount")
		).withName("WGPUInstanceFeatures")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val timedWaitAnyEnableOffset = 8L + nextInChainOffset
		val timedWaitAnyEnableLayout = LAYOUT.withName("timedWaitAnyEnable")

		val timedWaitAnyMaxCountOffset = 4L + timedWaitAnyEnableOffset
		val timedWaitAnyMaxCountLayout = LAYOUT.withName("timedWaitAnyMaxCount")

	}
}

@JvmInline
actual value class WGPULimits(actual override val handler: NativeAddress) : CStructure {
	actual var maxTextureDimension1D: UInt
		get() = getUInt(maxTextureDimension1DLayout, maxTextureDimension1DOffset)
		set(newValue) = set(maxTextureDimension1DLayout, maxTextureDimension1DOffset, newValue)

	actual var maxTextureDimension2D: UInt
		get() = getUInt(maxTextureDimension2DLayout, maxTextureDimension2DOffset)
		set(newValue) = set(maxTextureDimension2DLayout, maxTextureDimension2DOffset, newValue)

	actual var maxTextureDimension3D: UInt
		get() = getUInt(maxTextureDimension3DLayout, maxTextureDimension3DOffset)
		set(newValue) = set(maxTextureDimension3DLayout, maxTextureDimension3DOffset, newValue)

	actual var maxTextureArrayLayers: UInt
		get() = getUInt(maxTextureArrayLayersLayout, maxTextureArrayLayersOffset)
		set(newValue) = set(maxTextureArrayLayersLayout, maxTextureArrayLayersOffset, newValue)

	actual var maxBindGroups: UInt
		get() = getUInt(maxBindGroupsLayout, maxBindGroupsOffset)
		set(newValue) = set(maxBindGroupsLayout, maxBindGroupsOffset, newValue)

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = getUInt(maxBindGroupsPlusVertexBuffersLayout, maxBindGroupsPlusVertexBuffersOffset)
		set(newValue) = set(maxBindGroupsPlusVertexBuffersLayout, maxBindGroupsPlusVertexBuffersOffset, newValue)

	actual var maxBindingsPerBindGroup: UInt
		get() = getUInt(maxBindingsPerBindGroupLayout, maxBindingsPerBindGroupOffset)
		set(newValue) = set(maxBindingsPerBindGroupLayout, maxBindingsPerBindGroupOffset, newValue)

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = getUInt(maxDynamicUniformBuffersPerPipelineLayoutLayout, maxDynamicUniformBuffersPerPipelineLayoutOffset)
		set(newValue) = set(maxDynamicUniformBuffersPerPipelineLayoutLayout, maxDynamicUniformBuffersPerPipelineLayoutOffset, newValue)

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = getUInt(maxDynamicStorageBuffersPerPipelineLayoutLayout, maxDynamicStorageBuffersPerPipelineLayoutOffset)
		set(newValue) = set(maxDynamicStorageBuffersPerPipelineLayoutLayout, maxDynamicStorageBuffersPerPipelineLayoutOffset, newValue)

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = getUInt(maxSampledTexturesPerShaderStageLayout, maxSampledTexturesPerShaderStageOffset)
		set(newValue) = set(maxSampledTexturesPerShaderStageLayout, maxSampledTexturesPerShaderStageOffset, newValue)

	actual var maxSamplersPerShaderStage: UInt
		get() = getUInt(maxSamplersPerShaderStageLayout, maxSamplersPerShaderStageOffset)
		set(newValue) = set(maxSamplersPerShaderStageLayout, maxSamplersPerShaderStageOffset, newValue)

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = getUInt(maxStorageBuffersPerShaderStageLayout, maxStorageBuffersPerShaderStageOffset)
		set(newValue) = set(maxStorageBuffersPerShaderStageLayout, maxStorageBuffersPerShaderStageOffset, newValue)

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = getUInt(maxStorageTexturesPerShaderStageLayout, maxStorageTexturesPerShaderStageOffset)
		set(newValue) = set(maxStorageTexturesPerShaderStageLayout, maxStorageTexturesPerShaderStageOffset, newValue)

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = getUInt(maxUniformBuffersPerShaderStageLayout, maxUniformBuffersPerShaderStageOffset)
		set(newValue) = set(maxUniformBuffersPerShaderStageLayout, maxUniformBuffersPerShaderStageOffset, newValue)

	actual var maxUniformBufferBindingSize: ULong
		get() = getULong(maxUniformBufferBindingSizeLayout, maxUniformBufferBindingSizeOffset)
		set(newValue) = set(maxUniformBufferBindingSizeLayout, maxUniformBufferBindingSizeOffset, newValue)

	actual var maxStorageBufferBindingSize: ULong
		get() = getULong(maxStorageBufferBindingSizeLayout, maxStorageBufferBindingSizeOffset)
		set(newValue) = set(maxStorageBufferBindingSizeLayout, maxStorageBufferBindingSizeOffset, newValue)

	actual var minUniformBufferOffsetAlignment: UInt
		get() = getUInt(minUniformBufferOffsetAlignmentLayout, minUniformBufferOffsetAlignmentOffset)
		set(newValue) = set(minUniformBufferOffsetAlignmentLayout, minUniformBufferOffsetAlignmentOffset, newValue)

	actual var minStorageBufferOffsetAlignment: UInt
		get() = getUInt(minStorageBufferOffsetAlignmentLayout, minStorageBufferOffsetAlignmentOffset)
		set(newValue) = set(minStorageBufferOffsetAlignmentLayout, minStorageBufferOffsetAlignmentOffset, newValue)

	actual var maxVertexBuffers: UInt
		get() = getUInt(maxVertexBuffersLayout, maxVertexBuffersOffset)
		set(newValue) = set(maxVertexBuffersLayout, maxVertexBuffersOffset, newValue)

	actual var maxBufferSize: ULong
		get() = getULong(maxBufferSizeLayout, maxBufferSizeOffset)
		set(newValue) = set(maxBufferSizeLayout, maxBufferSizeOffset, newValue)

	actual var maxVertexAttributes: UInt
		get() = getUInt(maxVertexAttributesLayout, maxVertexAttributesOffset)
		set(newValue) = set(maxVertexAttributesLayout, maxVertexAttributesOffset, newValue)

	actual var maxVertexBufferArrayStride: UInt
		get() = getUInt(maxVertexBufferArrayStrideLayout, maxVertexBufferArrayStrideOffset)
		set(newValue) = set(maxVertexBufferArrayStrideLayout, maxVertexBufferArrayStrideOffset, newValue)

	actual var maxInterStageShaderVariables: UInt
		get() = getUInt(maxInterStageShaderVariablesLayout, maxInterStageShaderVariablesOffset)
		set(newValue) = set(maxInterStageShaderVariablesLayout, maxInterStageShaderVariablesOffset, newValue)

	actual var maxColorAttachments: UInt
		get() = getUInt(maxColorAttachmentsLayout, maxColorAttachmentsOffset)
		set(newValue) = set(maxColorAttachmentsLayout, maxColorAttachmentsOffset, newValue)

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = getUInt(maxColorAttachmentBytesPerSampleLayout, maxColorAttachmentBytesPerSampleOffset)
		set(newValue) = set(maxColorAttachmentBytesPerSampleLayout, maxColorAttachmentBytesPerSampleOffset, newValue)

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = getUInt(maxComputeWorkgroupStorageSizeLayout, maxComputeWorkgroupStorageSizeOffset)
		set(newValue) = set(maxComputeWorkgroupStorageSizeLayout, maxComputeWorkgroupStorageSizeOffset, newValue)

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = getUInt(maxComputeInvocationsPerWorkgroupLayout, maxComputeInvocationsPerWorkgroupOffset)
		set(newValue) = set(maxComputeInvocationsPerWorkgroupLayout, maxComputeInvocationsPerWorkgroupOffset, newValue)

	actual var maxComputeWorkgroupSizeX: UInt
		get() = getUInt(maxComputeWorkgroupSizeXLayout, maxComputeWorkgroupSizeXOffset)
		set(newValue) = set(maxComputeWorkgroupSizeXLayout, maxComputeWorkgroupSizeXOffset, newValue)

	actual var maxComputeWorkgroupSizeY: UInt
		get() = getUInt(maxComputeWorkgroupSizeYLayout, maxComputeWorkgroupSizeYOffset)
		set(newValue) = set(maxComputeWorkgroupSizeYLayout, maxComputeWorkgroupSizeYOffset, newValue)

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = getUInt(maxComputeWorkgroupSizeZLayout, maxComputeWorkgroupSizeZOffset)
		set(newValue) = set(maxComputeWorkgroupSizeZLayout, maxComputeWorkgroupSizeZOffset, newValue)

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = getUInt(maxComputeWorkgroupsPerDimensionLayout, maxComputeWorkgroupsPerDimensionOffset)
		set(newValue) = set(maxComputeWorkgroupsPerDimensionLayout, maxComputeWorkgroupsPerDimensionOffset, newValue)

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
			C_INT.withName("maxComputeWorkgroupsPerDimension")
		).withName("WGPULimits")

		val maxTextureDimension1DOffset = 0L
		val maxTextureDimension1DLayout = LAYOUT.withName("maxTextureDimension1D")

		val maxTextureDimension2DOffset = 4L + maxTextureDimension1DOffset
		val maxTextureDimension2DLayout = LAYOUT.withName("maxTextureDimension2D")

		val maxTextureDimension3DOffset = 4L + maxTextureDimension2DOffset
		val maxTextureDimension3DLayout = LAYOUT.withName("maxTextureDimension3D")

		val maxTextureArrayLayersOffset = 4L + maxTextureDimension3DOffset
		val maxTextureArrayLayersLayout = LAYOUT.withName("maxTextureArrayLayers")

		val maxBindGroupsOffset = 4L + maxTextureArrayLayersOffset
		val maxBindGroupsLayout = LAYOUT.withName("maxBindGroups")

		val maxBindGroupsPlusVertexBuffersOffset = 4L + maxBindGroupsOffset
		val maxBindGroupsPlusVertexBuffersLayout = LAYOUT.withName("maxBindGroupsPlusVertexBuffers")

		val maxBindingsPerBindGroupOffset = 4L + maxBindGroupsPlusVertexBuffersOffset
		val maxBindingsPerBindGroupLayout = LAYOUT.withName("maxBindingsPerBindGroup")

		val maxDynamicUniformBuffersPerPipelineLayoutOffset = 4L + maxBindingsPerBindGroupOffset
		val maxDynamicUniformBuffersPerPipelineLayoutLayout = LAYOUT.withName("maxDynamicUniformBuffersPerPipelineLayout")

		val maxDynamicStorageBuffersPerPipelineLayoutOffset = 4L + maxDynamicUniformBuffersPerPipelineLayoutOffset
		val maxDynamicStorageBuffersPerPipelineLayoutLayout = LAYOUT.withName("maxDynamicStorageBuffersPerPipelineLayout")

		val maxSampledTexturesPerShaderStageOffset = 4L + maxDynamicStorageBuffersPerPipelineLayoutOffset
		val maxSampledTexturesPerShaderStageLayout = LAYOUT.withName("maxSampledTexturesPerShaderStage")

		val maxSamplersPerShaderStageOffset = 4L + maxSampledTexturesPerShaderStageOffset
		val maxSamplersPerShaderStageLayout = LAYOUT.withName("maxSamplersPerShaderStage")

		val maxStorageBuffersPerShaderStageOffset = 4L + maxSamplersPerShaderStageOffset
		val maxStorageBuffersPerShaderStageLayout = LAYOUT.withName("maxStorageBuffersPerShaderStage")

		val maxStorageTexturesPerShaderStageOffset = 4L + maxStorageBuffersPerShaderStageOffset
		val maxStorageTexturesPerShaderStageLayout = LAYOUT.withName("maxStorageTexturesPerShaderStage")

		val maxUniformBuffersPerShaderStageOffset = 4L + maxStorageTexturesPerShaderStageOffset
		val maxUniformBuffersPerShaderStageLayout = LAYOUT.withName("maxUniformBuffersPerShaderStage")

		val maxUniformBufferBindingSizeOffset = 4L + maxUniformBuffersPerShaderStageOffset
		val maxUniformBufferBindingSizeLayout = LAYOUT.withName("maxUniformBufferBindingSize")

		val maxStorageBufferBindingSizeOffset = 8L + maxUniformBufferBindingSizeOffset
		val maxStorageBufferBindingSizeLayout = LAYOUT.withName("maxStorageBufferBindingSize")

		val minUniformBufferOffsetAlignmentOffset = 8L + maxStorageBufferBindingSizeOffset
		val minUniformBufferOffsetAlignmentLayout = LAYOUT.withName("minUniformBufferOffsetAlignment")

		val minStorageBufferOffsetAlignmentOffset = 4L + minUniformBufferOffsetAlignmentOffset
		val minStorageBufferOffsetAlignmentLayout = LAYOUT.withName("minStorageBufferOffsetAlignment")

		val maxVertexBuffersOffset = 4L + minStorageBufferOffsetAlignmentOffset
		val maxVertexBuffersLayout = LAYOUT.withName("maxVertexBuffers")

		val maxBufferSizeOffset = 4L + maxVertexBuffersOffset
		val maxBufferSizeLayout = LAYOUT.withName("maxBufferSize")

		val maxVertexAttributesOffset = 8L + maxBufferSizeOffset
		val maxVertexAttributesLayout = LAYOUT.withName("maxVertexAttributes")

		val maxVertexBufferArrayStrideOffset = 4L + maxVertexAttributesOffset
		val maxVertexBufferArrayStrideLayout = LAYOUT.withName("maxVertexBufferArrayStride")

		val maxInterStageShaderVariablesOffset = 4L + maxVertexBufferArrayStrideOffset
		val maxInterStageShaderVariablesLayout = LAYOUT.withName("maxInterStageShaderVariables")

		val maxColorAttachmentsOffset = 4L + maxInterStageShaderVariablesOffset
		val maxColorAttachmentsLayout = LAYOUT.withName("maxColorAttachments")

		val maxColorAttachmentBytesPerSampleOffset = 4L + maxColorAttachmentsOffset
		val maxColorAttachmentBytesPerSampleLayout = LAYOUT.withName("maxColorAttachmentBytesPerSample")

		val maxComputeWorkgroupStorageSizeOffset = 4L + maxColorAttachmentBytesPerSampleOffset
		val maxComputeWorkgroupStorageSizeLayout = LAYOUT.withName("maxComputeWorkgroupStorageSize")

		val maxComputeInvocationsPerWorkgroupOffset = 4L + maxComputeWorkgroupStorageSizeOffset
		val maxComputeInvocationsPerWorkgroupLayout = LAYOUT.withName("maxComputeInvocationsPerWorkgroup")

		val maxComputeWorkgroupSizeXOffset = 4L + maxComputeInvocationsPerWorkgroupOffset
		val maxComputeWorkgroupSizeXLayout = LAYOUT.withName("maxComputeWorkgroupSizeX")

		val maxComputeWorkgroupSizeYOffset = 4L + maxComputeWorkgroupSizeXOffset
		val maxComputeWorkgroupSizeYLayout = LAYOUT.withName("maxComputeWorkgroupSizeY")

		val maxComputeWorkgroupSizeZOffset = 4L + maxComputeWorkgroupSizeYOffset
		val maxComputeWorkgroupSizeZLayout = LAYOUT.withName("maxComputeWorkgroupSizeZ")

		val maxComputeWorkgroupsPerDimensionOffset = 4L + maxComputeWorkgroupSizeZOffset
		val maxComputeWorkgroupsPerDimensionLayout = LAYOUT.withName("maxComputeWorkgroupsPerDimension")

	}
}

@JvmInline
actual value class WGPUMultisampleState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var count: UInt
		get() = getUInt(countLayout, countOffset)
		set(newValue) = set(countLayout, countOffset, newValue)

	actual var mask: UInt
		get() = getUInt(maskLayout, maskOffset)
		set(newValue) = set(maskLayout, maskOffset, newValue)

	actual var alphaToCoverageEnabled: Boolean
		get() = getInt(alphaToCoverageEnabledLayout, alphaToCoverageEnabledOffset).toBoolean()
		set(newValue) = set(alphaToCoverageEnabledLayout, alphaToCoverageEnabledOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUMultisampleState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("count"),
			C_INT.withName("mask"),
			C_INT.withName("alphaToCoverageEnabled")
		).withName("WGPUMultisampleState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val countOffset = 8L + nextInChainOffset
		val countLayout = LAYOUT.withName("count")

		val maskOffset = 4L + countOffset
		val maskLayout = LAYOUT.withName("mask")

		val alphaToCoverageEnabledOffset = 4L + maskOffset
		val alphaToCoverageEnabledLayout = LAYOUT.withName("alphaToCoverageEnabled")

	}
}

@JvmInline
actual value class WGPUOrigin3D(actual override val handler: NativeAddress) : CStructure {
	actual var x: UInt
		get() = getUInt(xLayout, xOffset)
		set(newValue) = set(xLayout, xOffset, newValue)

	actual var y: UInt
		get() = getUInt(yLayout, yOffset)
		set(newValue) = set(yLayout, yOffset, newValue)

	actual var z: UInt
		get() = getUInt(zLayout, zOffset)
		set(newValue) = set(zLayout, zOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUOrigin3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("x"),
			C_INT.withName("y"),
			C_INT.withName("z")
		).withName("WGPUOrigin3D")

		val xOffset = 0L
		val xLayout = LAYOUT.withName("x")

		val yOffset = 4L + xOffset
		val yLayout = LAYOUT.withName("y")

		val zOffset = 4L + yOffset
		val zLayout = LAYOUT.withName("z")

	}
}

@JvmInline
actual value class WGPUPipelineLayoutDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var bindGroupLayoutCount: ULong
		get() = getULong(bindGroupLayoutCountLayout, bindGroupLayoutCountOffset)
		set(newValue) = set(bindGroupLayoutCountLayout, bindGroupLayoutCountOffset, newValue)

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
			C_POINTER.withName("bindGroupLayouts")
		).withName("WGPUPipelineLayoutDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val bindGroupLayoutCountOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val bindGroupLayoutCountLayout = LAYOUT.withName("bindGroupLayoutCount")

		val bindGroupLayoutsOffset = 8L + bindGroupLayoutCountOffset
		val bindGroupLayoutsLayout = LAYOUT.withName("bindGroupLayouts")

	}
}

@JvmInline
actual value class WGPUPrimitiveState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var topology: WGPUPrimitiveTopology
		get() = getUInt(topologyLayout, topologyOffset)
		set(newValue) = set(topologyLayout, topologyOffset, newValue)

	actual var stripIndexFormat: WGPUIndexFormat
		get() = getUInt(stripIndexFormatLayout, stripIndexFormatOffset)
		set(newValue) = set(stripIndexFormatLayout, stripIndexFormatOffset, newValue)

	actual var frontFace: WGPUFrontFace
		get() = getUInt(frontFaceLayout, frontFaceOffset)
		set(newValue) = set(frontFaceLayout, frontFaceOffset, newValue)

	actual var cullMode: WGPUCullMode
		get() = getUInt(cullModeLayout, cullModeOffset)
		set(newValue) = set(cullModeLayout, cullModeOffset, newValue)

	actual var unclippedDepth: Boolean
		get() = getInt(unclippedDepthLayout, unclippedDepthOffset).toBoolean()
		set(newValue) = set(unclippedDepthLayout, unclippedDepthOffset, newValue)

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
			C_INT.withName("unclippedDepth")
		).withName("WGPUPrimitiveState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val topologyOffset = 8L + nextInChainOffset
		val topologyLayout = LAYOUT.withName("topology")

		val stripIndexFormatOffset = 4L + topologyOffset
		val stripIndexFormatLayout = LAYOUT.withName("stripIndexFormat")

		val frontFaceOffset = 4L + stripIndexFormatOffset
		val frontFaceLayout = LAYOUT.withName("frontFace")

		val cullModeOffset = 4L + frontFaceOffset
		val cullModeLayout = LAYOUT.withName("cullMode")

		val unclippedDepthOffset = 4L + cullModeOffset
		val unclippedDepthLayout = LAYOUT.withName("unclippedDepth")

	}
}

@JvmInline
actual value class WGPUProgrammableStageDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = get(entryPointLayout, entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountLayout, constantCountOffset)
		set(newValue) = set(constantCountLayout, constantCountOffset, newValue)

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
			C_POINTER.withName("constants")
		).withName("WGPUProgrammableStageDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = LAYOUT.withName("module")

		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = LAYOUT.withName("entryPoint")

		val constantCountOffset = LAYOUT.withName("entryPoint").byteSize() + entryPointOffset
		val constantCountLayout = LAYOUT.withName("constantCount")

		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = LAYOUT.withName("constants")

	}
}

@JvmInline
actual value class WGPUQuerySetDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var type: WGPUQueryType
		get() = getUInt(typeLayout, typeOffset)
		set(newValue) = set(typeLayout, typeOffset, newValue)

	actual var count: UInt
		get() = getUInt(countLayout, countOffset)
		set(newValue) = set(countLayout, countOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUQuerySetDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_INT.withName("type"),
			C_INT.withName("count")
		).withName("WGPUQuerySetDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val typeOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val typeLayout = LAYOUT.withName("type")

		val countOffset = 4L + typeOffset
		val countLayout = LAYOUT.withName("count")

	}
}

@JvmInline
actual value class WGPUQueueDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUQueueDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPUQueueDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPURenderBundleDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderBundleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPURenderBundleDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPURenderBundleEncoderDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var colorFormatCount: ULong
		get() = getULong(colorFormatCountLayout, colorFormatCountOffset)
		set(newValue) = set(colorFormatCountLayout, colorFormatCountOffset, newValue)

	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
		get() = get(colorFormatsLayout, colorFormatsOffset).let(::ArrayHolder)
		set(newValue) = set(colorFormatsLayout, colorFormatsOffset, newValue?.handler)

	actual var depthStencilFormat: WGPUTextureFormat
		get() = getUInt(depthStencilFormatLayout, depthStencilFormatOffset)
		set(newValue) = set(depthStencilFormatLayout, depthStencilFormatOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt(sampleCountLayout, sampleCountOffset)
		set(newValue) = set(sampleCountLayout, sampleCountOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt(depthReadOnlyLayout, depthReadOnlyOffset).toBoolean()
		set(newValue) = set(depthReadOnlyLayout, depthReadOnlyOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt(stencilReadOnlyLayout, stencilReadOnlyOffset).toBoolean()
		set(newValue) = set(stencilReadOnlyLayout, stencilReadOnlyOffset, newValue)

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
			C_INT.withName("stencilReadOnly")
		).withName("WGPURenderBundleEncoderDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val colorFormatCountOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val colorFormatCountLayout = LAYOUT.withName("colorFormatCount")

		val colorFormatsOffset = 8L + colorFormatCountOffset
		val colorFormatsLayout = LAYOUT.withName("colorFormats")

		val depthStencilFormatOffset = 8L + colorFormatsOffset
		val depthStencilFormatLayout = LAYOUT.withName("depthStencilFormat")

		val sampleCountOffset = 4L + depthStencilFormatOffset
		val sampleCountLayout = LAYOUT.withName("sampleCount")

		val depthReadOnlyOffset = 4L + sampleCountOffset
		val depthReadOnlyLayout = LAYOUT.withName("depthReadOnly")

		val stencilReadOnlyOffset = 4L + depthReadOnlyOffset
		val stencilReadOnlyLayout = LAYOUT.withName("stencilReadOnly")

	}
}

@JvmInline
actual value class WGPURenderPassColorAttachment(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var view: WGPUTextureView?
		get() = get(viewLayout, viewOffset).let(::WGPUTextureView)
		set(newValue) = set(viewLayout, viewOffset, newValue?.handler)

	actual var depthSlice: UInt
		get() = getUInt(depthSliceLayout, depthSliceOffset)
		set(newValue) = set(depthSliceLayout, depthSliceOffset, newValue)

	actual var resolveTarget: WGPUTextureView?
		get() = get(resolveTargetLayout, resolveTargetOffset).let(::WGPUTextureView)
		set(newValue) = set(resolveTargetLayout, resolveTargetOffset, newValue?.handler)

	actual var loadOp: WGPULoadOp
		get() = getUInt(loadOpLayout, loadOpOffset)
		set(newValue) = set(loadOpLayout, loadOpOffset, newValue)

	actual var storeOp: WGPUStoreOp
		get() = getUInt(storeOpLayout, storeOpOffset)
		set(newValue) = set(storeOpLayout, storeOpOffset, newValue)

	actual val clearValue: WGPUColor
		get() = get(clearValueLayout, clearValueOffset).let(::WGPUColor)

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
			WGPUColor.LAYOUT.withName("clearValue")
		).withName("WGPURenderPassColorAttachment")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val viewOffset = 8L + nextInChainOffset
		val viewLayout = LAYOUT.withName("view")

		val depthSliceOffset = 8L + viewOffset
		val depthSliceLayout = LAYOUT.withName("depthSlice")

		val resolveTargetOffset = 4L + depthSliceOffset
		val resolveTargetLayout = LAYOUT.withName("resolveTarget")

		val loadOpOffset = 8L + resolveTargetOffset
		val loadOpLayout = LAYOUT.withName("loadOp")

		val storeOpOffset = 4L + loadOpOffset
		val storeOpLayout = LAYOUT.withName("storeOp")

		val clearValueOffset = 4L + storeOpOffset
		val clearValueLayout = LAYOUT.withName("clearValue")

	}
}

@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual override val handler: NativeAddress) : CStructure {
	actual var view: WGPUTextureView?
		get() = get(viewLayout, viewOffset).let(::WGPUTextureView)
		set(newValue) = set(viewLayout, viewOffset, newValue?.handler)

	actual var depthLoadOp: WGPULoadOp
		get() = getUInt(depthLoadOpLayout, depthLoadOpOffset)
		set(newValue) = set(depthLoadOpLayout, depthLoadOpOffset, newValue)

	actual var depthStoreOp: WGPUStoreOp
		get() = getUInt(depthStoreOpLayout, depthStoreOpOffset)
		set(newValue) = set(depthStoreOpLayout, depthStoreOpOffset, newValue)

	actual var depthClearValue: Float
		get() = getFloat(depthClearValueLayout, depthClearValueOffset)
		set(newValue) = set(depthClearValueLayout, depthClearValueOffset, newValue)

	actual var depthReadOnly: Boolean
		get() = getInt(depthReadOnlyLayout, depthReadOnlyOffset).toBoolean()
		set(newValue) = set(depthReadOnlyLayout, depthReadOnlyOffset, newValue)

	actual var stencilLoadOp: WGPULoadOp
		get() = getUInt(stencilLoadOpLayout, stencilLoadOpOffset)
		set(newValue) = set(stencilLoadOpLayout, stencilLoadOpOffset, newValue)

	actual var stencilStoreOp: WGPUStoreOp
		get() = getUInt(stencilStoreOpLayout, stencilStoreOpOffset)
		set(newValue) = set(stencilStoreOpLayout, stencilStoreOpOffset, newValue)

	actual var stencilClearValue: UInt
		get() = getUInt(stencilClearValueLayout, stencilClearValueOffset)
		set(newValue) = set(stencilClearValueLayout, stencilClearValueOffset, newValue)

	actual var stencilReadOnly: Boolean
		get() = getInt(stencilReadOnlyLayout, stencilReadOnlyOffset).toBoolean()
		set(newValue) = set(stencilReadOnlyLayout, stencilReadOnlyOffset, newValue)

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
			C_INT.withName("stencilReadOnly")
		).withName("WGPURenderPassDepthStencilAttachment")

		val viewOffset = 0L
		val viewLayout = LAYOUT.withName("view")

		val depthLoadOpOffset = 8L + viewOffset
		val depthLoadOpLayout = LAYOUT.withName("depthLoadOp")

		val depthStoreOpOffset = 4L + depthLoadOpOffset
		val depthStoreOpLayout = LAYOUT.withName("depthStoreOp")

		val depthClearValueOffset = 4L + depthStoreOpOffset
		val depthClearValueLayout = LAYOUT.withName("depthClearValue")

		val depthReadOnlyOffset = 4L + depthClearValueOffset
		val depthReadOnlyLayout = LAYOUT.withName("depthReadOnly")

		val stencilLoadOpOffset = 4L + depthReadOnlyOffset
		val stencilLoadOpLayout = LAYOUT.withName("stencilLoadOp")

		val stencilStoreOpOffset = 4L + stencilLoadOpOffset
		val stencilStoreOpLayout = LAYOUT.withName("stencilStoreOp")

		val stencilClearValueOffset = 4L + stencilStoreOpOffset
		val stencilClearValueLayout = LAYOUT.withName("stencilClearValue")

		val stencilReadOnlyOffset = 4L + stencilClearValueOffset
		val stencilReadOnlyLayout = LAYOUT.withName("stencilReadOnly")

	}
}

@JvmInline
actual value class WGPURenderPassDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var colorAttachmentCount: ULong
		get() = getULong(colorAttachmentCountLayout, colorAttachmentCountOffset)
		set(newValue) = set(colorAttachmentCountLayout, colorAttachmentCountOffset, newValue)

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
			C_POINTER.withName("timestampWrites")
		).withName("WGPURenderPassDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val colorAttachmentCountOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val colorAttachmentCountLayout = LAYOUT.withName("colorAttachmentCount")

		val colorAttachmentsOffset = 8L + colorAttachmentCountOffset
		val colorAttachmentsLayout = LAYOUT.withName("colorAttachments")

		val depthStencilAttachmentOffset = 8L + colorAttachmentsOffset
		val depthStencilAttachmentLayout = LAYOUT.withName("depthStencilAttachment")

		val occlusionQuerySetOffset = 8L + depthStencilAttachmentOffset
		val occlusionQuerySetLayout = LAYOUT.withName("occlusionQuerySet")

		val timestampWritesOffset = 8L + occlusionQuerySetOffset
		val timestampWritesLayout = LAYOUT.withName("timestampWrites")

	}
}

@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

	actual var maxDrawCount: ULong
		get() = getULong(maxDrawCountLayout, maxDrawCountOffset)
		set(newValue) = set(maxDrawCountLayout, maxDrawCountOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassMaxDrawCount)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_LONG.withName("maxDrawCount")
		).withName("WGPURenderPassMaxDrawCount")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val maxDrawCountOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val maxDrawCountLayout = LAYOUT.withName("maxDrawCount")

	}
}

@JvmInline
actual value class WGPURenderPassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
	actual var querySet: WGPUQuerySet?
		get() = get(querySetLayout, querySetOffset).let(::WGPUQuerySet)
		set(newValue) = set(querySetLayout, querySetOffset, newValue?.handler)

	actual var beginningOfPassWriteIndex: UInt
		get() = getUInt(beginningOfPassWriteIndexLayout, beginningOfPassWriteIndexOffset)
		set(newValue) = set(beginningOfPassWriteIndexLayout, beginningOfPassWriteIndexOffset, newValue)

	actual var endOfPassWriteIndex: UInt
		get() = getUInt(endOfPassWriteIndexLayout, endOfPassWriteIndexOffset)
		set(newValue) = set(endOfPassWriteIndexLayout, endOfPassWriteIndexOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassTimestampWrites {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURenderPassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPURenderPassTimestampWrites")

		val querySetOffset = 0L
		val querySetLayout = LAYOUT.withName("querySet")

		val beginningOfPassWriteIndexOffset = 8L + querySetOffset
		val beginningOfPassWriteIndexLayout = LAYOUT.withName("beginningOfPassWriteIndex")

		val endOfPassWriteIndexOffset = 4L + beginningOfPassWriteIndexOffset
		val endOfPassWriteIndexLayout = LAYOUT.withName("endOfPassWriteIndex")

	}
}

@JvmInline
actual value class WGPURenderPipelineDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var layout: WGPUPipelineLayout?
		get() = get(layoutLayout, layoutOffset).let(::WGPUPipelineLayout)
		set(newValue) = set(layoutLayout, layoutOffset, newValue?.handler)

	actual val vertex: WGPUVertexState
		get() = get(vertexLayout, vertexOffset).let(::WGPUVertexState)

	actual val primitive: WGPUPrimitiveState
		get() = get(primitiveLayout, primitiveOffset).let(::WGPUPrimitiveState)

	actual var depthStencil: WGPUDepthStencilState?
		get() = get(depthStencilLayout, depthStencilOffset).let(::WGPUDepthStencilState)
		set(newValue) = set(depthStencilLayout, depthStencilOffset, newValue?.handler)

	actual val multisample: WGPUMultisampleState
		get() = get(multisampleLayout, multisampleOffset).let(::WGPUMultisampleState)

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
			C_POINTER.withName("fragment")
		).withName("WGPURenderPipelineDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val layoutOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val layoutLayout = LAYOUT.withName("layout")

		val vertexOffset = 8L + layoutOffset
		val vertexLayout = LAYOUT.withName("vertex")

		val primitiveOffset = LAYOUT.withName("vertex").byteSize() + vertexOffset
		val primitiveLayout = LAYOUT.withName("primitive")

		val depthStencilOffset = LAYOUT.withName("primitive").byteSize() + primitiveOffset
		val depthStencilLayout = LAYOUT.withName("depthStencil")

		val multisampleOffset = 8L + depthStencilOffset
		val multisampleLayout = LAYOUT.withName("multisample")

		val fragmentOffset = LAYOUT.withName("multisample").byteSize() + multisampleOffset
		val fragmentLayout = LAYOUT.withName("fragment")

	}
}

@JvmInline
actual value class WGPURequestAdapterOptions(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var compatibleSurface: WGPUSurface?
		get() = get(compatibleSurfaceLayout, compatibleSurfaceOffset).let(::WGPUSurface)
		set(newValue) = set(compatibleSurfaceLayout, compatibleSurfaceOffset, newValue?.handler)

	actual var powerPreference: WGPUPowerPreference
		get() = getUInt(powerPreferenceLayout, powerPreferenceOffset)
		set(newValue) = set(powerPreferenceLayout, powerPreferenceOffset, newValue)

	actual var backendType: WGPUBackendType
		get() = getUInt(backendTypeLayout, backendTypeOffset)
		set(newValue) = set(backendTypeLayout, backendTypeOffset, newValue)

	actual var forceFallbackAdapter: Boolean
		get() = getInt(forceFallbackAdapterLayout, forceFallbackAdapterOffset).toBoolean()
		set(newValue) = set(forceFallbackAdapterLayout, forceFallbackAdapterOffset, newValue)

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
			C_INT.withName("forceFallbackAdapter")
		).withName("WGPURequestAdapterOptions")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val compatibleSurfaceOffset = 8L + nextInChainOffset
		val compatibleSurfaceLayout = LAYOUT.withName("compatibleSurface")

		val powerPreferenceOffset = 8L + compatibleSurfaceOffset
		val powerPreferenceLayout = LAYOUT.withName("powerPreference")

		val backendTypeOffset = 4L + powerPreferenceOffset
		val backendTypeLayout = LAYOUT.withName("backendType")

		val forceFallbackAdapterOffset = 4L + backendTypeOffset
		val forceFallbackAdapterLayout = LAYOUT.withName("forceFallbackAdapter")

	}
}

@JvmInline
actual value class WGPURequiredLimits(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val limits: WGPULimits
		get() = get(limitsLayout, limitsOffset).let(::WGPULimits)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequiredLimits {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPURequiredLimits)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPULimits.LAYOUT.withName("limits")
		).withName("WGPURequiredLimits")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val limitsOffset = 8L + nextInChainOffset
		val limitsLayout = LAYOUT.withName("limits")

	}
}

@JvmInline
actual value class WGPUSamplerBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var type: WGPUSamplerBindingType
		get() = getUInt(typeLayout, typeOffset)
		set(newValue) = set(typeLayout, typeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSamplerBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("type")
		).withName("WGPUSamplerBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val typeOffset = 8L + nextInChainOffset
		val typeLayout = LAYOUT.withName("type")

	}
}

@JvmInline
actual value class WGPUSamplerDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var addressModeU: WGPUAddressMode
		get() = getUInt(addressModeULayout, addressModeUOffset)
		set(newValue) = set(addressModeULayout, addressModeUOffset, newValue)

	actual var addressModeV: WGPUAddressMode
		get() = getUInt(addressModeVLayout, addressModeVOffset)
		set(newValue) = set(addressModeVLayout, addressModeVOffset, newValue)

	actual var addressModeW: WGPUAddressMode
		get() = getUInt(addressModeWLayout, addressModeWOffset)
		set(newValue) = set(addressModeWLayout, addressModeWOffset, newValue)

	actual var magFilter: WGPUFilterMode
		get() = getUInt(magFilterLayout, magFilterOffset)
		set(newValue) = set(magFilterLayout, magFilterOffset, newValue)

	actual var minFilter: WGPUFilterMode
		get() = getUInt(minFilterLayout, minFilterOffset)
		set(newValue) = set(minFilterLayout, minFilterOffset, newValue)

	actual var mipmapFilter: WGPUMipmapFilterMode
		get() = getUInt(mipmapFilterLayout, mipmapFilterOffset)
		set(newValue) = set(mipmapFilterLayout, mipmapFilterOffset, newValue)

	actual var lodMinClamp: Float
		get() = getFloat(lodMinClampLayout, lodMinClampOffset)
		set(newValue) = set(lodMinClampLayout, lodMinClampOffset, newValue)

	actual var lodMaxClamp: Float
		get() = getFloat(lodMaxClampLayout, lodMaxClampOffset)
		set(newValue) = set(lodMaxClampLayout, lodMaxClampOffset, newValue)

	actual var compare: WGPUCompareFunction
		get() = getUInt(compareLayout, compareOffset)
		set(newValue) = set(compareLayout, compareOffset, newValue)

	actual var maxAnisotropy: UShort
		get() = getUShort(maxAnisotropyLayout, maxAnisotropyOffset)
		set(newValue) = set(maxAnisotropyLayout, maxAnisotropyOffset, newValue)

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
			C_SHORT.withName("maxAnisotropy")
		).withName("WGPUSamplerDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val addressModeUOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val addressModeULayout = LAYOUT.withName("addressModeU")

		val addressModeVOffset = 4L + addressModeUOffset
		val addressModeVLayout = LAYOUT.withName("addressModeV")

		val addressModeWOffset = 4L + addressModeVOffset
		val addressModeWLayout = LAYOUT.withName("addressModeW")

		val magFilterOffset = 4L + addressModeWOffset
		val magFilterLayout = LAYOUT.withName("magFilter")

		val minFilterOffset = 4L + magFilterOffset
		val minFilterLayout = LAYOUT.withName("minFilter")

		val mipmapFilterOffset = 4L + minFilterOffset
		val mipmapFilterLayout = LAYOUT.withName("mipmapFilter")

		val lodMinClampOffset = 4L + mipmapFilterOffset
		val lodMinClampLayout = LAYOUT.withName("lodMinClamp")

		val lodMaxClampOffset = 4L + lodMinClampOffset
		val lodMaxClampLayout = LAYOUT.withName("lodMaxClamp")

		val compareOffset = 4L + lodMaxClampOffset
		val compareLayout = LAYOUT.withName("compare")

		val maxAnisotropyOffset = 4L + compareOffset
		val maxAnisotropyLayout = LAYOUT.withName("maxAnisotropy")

	}
}

@JvmInline
actual value class WGPUShaderModuleDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUShaderModuleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPUShaderModuleDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPUShaderSourceSPIRV(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

	actual var codeSize: UInt
		get() = getUInt(codeSizeLayout, codeSizeOffset)
		set(newValue) = set(codeSizeLayout, codeSizeOffset, newValue)

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
			C_POINTER.withName("code")
		).withName("WGPUShaderSourceSPIRV")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val codeSizeOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val codeSizeLayout = LAYOUT.withName("codeSize")

		val codeOffset = 4L + codeSizeOffset
		val codeLayout = LAYOUT.withName("code")

	}
}

@JvmInline
actual value class WGPUShaderSourceWGSL(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

	actual val code: WGPUStringView
		get() = get(codeLayout, codeOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUShaderSourceWGSL)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			WGPUStringView.LAYOUT.withName("code")
		).withName("WGPUShaderSourceWGSL")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val codeOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val codeLayout = LAYOUT.withName("code")

	}
}

@JvmInline
actual value class WGPUStencilFaceState(actual override val handler: NativeAddress) : CStructure {
	actual var compare: WGPUCompareFunction
		get() = getUInt(compareLayout, compareOffset)
		set(newValue) = set(compareLayout, compareOffset, newValue)

	actual var failOp: WGPUStencilOperation
		get() = getUInt(failOpLayout, failOpOffset)
		set(newValue) = set(failOpLayout, failOpOffset, newValue)

	actual var depthFailOp: WGPUStencilOperation
		get() = getUInt(depthFailOpLayout, depthFailOpOffset)
		set(newValue) = set(depthFailOpLayout, depthFailOpOffset, newValue)

	actual var passOp: WGPUStencilOperation
		get() = getUInt(passOpLayout, passOpOffset)
		set(newValue) = set(passOpLayout, passOpOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStencilFaceState)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("compare"),
			C_INT.withName("failOp"),
			C_INT.withName("depthFailOp"),
			C_INT.withName("passOp")
		).withName("WGPUStencilFaceState")

		val compareOffset = 0L
		val compareLayout = LAYOUT.withName("compare")

		val failOpOffset = 4L + compareOffset
		val failOpLayout = LAYOUT.withName("failOp")

		val depthFailOpOffset = 4L + failOpOffset
		val depthFailOpLayout = LAYOUT.withName("depthFailOp")

		val passOpOffset = 4L + depthFailOpOffset
		val passOpLayout = LAYOUT.withName("passOp")

	}
}

@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var access: WGPUStorageTextureAccess
		get() = getUInt(accessLayout, accessOffset)
		set(newValue) = set(accessLayout, accessOffset, newValue)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = getUInt(viewDimensionLayout, viewDimensionOffset)
		set(newValue) = set(viewDimensionLayout, viewDimensionOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStorageTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("access"),
			C_INT.withName("format"),
			C_INT.withName("viewDimension")
		).withName("WGPUStorageTextureBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val accessOffset = 8L + nextInChainOffset
		val accessLayout = LAYOUT.withName("access")

		val formatOffset = 4L + accessOffset
		val formatLayout = LAYOUT.withName("format")

		val viewDimensionOffset = 4L + formatOffset
		val viewDimensionLayout = LAYOUT.withName("viewDimension")

	}
}

@JvmInline
actual value class WGPUSupportedFeatures(actual override val handler: NativeAddress) : CStructure {
	actual var featureCount: ULong
		get() = getULong(featureCountLayout, featureCountOffset)
		set(newValue) = set(featureCountLayout, featureCountOffset, newValue)

	actual var features: ArrayHolder<WGPUFeatureName>?
		get() = get(featuresLayout, featuresOffset).let(::ArrayHolder)
		set(newValue) = set(featuresLayout, featuresOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSupportedFeatures)
		}
		internal val LAYOUT = structLayout(
			C_LONG.withName("featureCount"),
			C_POINTER.withName("features")
		).withName("WGPUSupportedFeatures")

		val featureCountOffset = 0L
		val featureCountLayout = LAYOUT.withName("featureCount")

		val featuresOffset = 8L + featureCountOffset
		val featuresLayout = LAYOUT.withName("features")

	}
}

@JvmInline
actual value class WGPUSupportedLimits(actual override val handler: NativeAddress) : CStructure {
	actual val limits: WGPULimits
		get() = get(limitsLayout, limitsOffset).let(::WGPULimits)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSupportedLimits)
		}
		internal val LAYOUT = structLayout(
			WGPULimits.LAYOUT.withName("limits")
		).withName("WGPUSupportedLimits")

		val limitsOffset = 0L
		val limitsLayout = LAYOUT.withName("limits")

	}
}

@JvmInline
actual value class WGPUSurfaceCapabilities(actual override val handler: NativeAddress) : CStructure {
	actual var usages: ULong
		get() = getULong(usagesLayout, usagesOffset)
		set(newValue) = set(usagesLayout, usagesOffset, newValue)

	actual var formatCount: ULong
		get() = getULong(formatCountLayout, formatCountOffset)
		set(newValue) = set(formatCountLayout, formatCountOffset, newValue)

	actual var formats: ArrayHolder<WGPUTextureFormat>?
		get() = get(formatsLayout, formatsOffset).let(::ArrayHolder)
		set(newValue) = set(formatsLayout, formatsOffset, newValue?.handler)

	actual var presentModeCount: ULong
		get() = getULong(presentModeCountLayout, presentModeCountOffset)
		set(newValue) = set(presentModeCountLayout, presentModeCountOffset, newValue)

	actual var presentModes: ArrayHolder<WGPUPresentMode>?
		get() = get(presentModesLayout, presentModesOffset).let(::ArrayHolder)
		set(newValue) = set(presentModesLayout, presentModesOffset, newValue?.handler)

	actual var alphaModeCount: ULong
		get() = getULong(alphaModeCountLayout, alphaModeCountOffset)
		set(newValue) = set(alphaModeCountLayout, alphaModeCountOffset, newValue)

	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
		get() = get(alphaModesLayout, alphaModesOffset).let(::ArrayHolder)
		set(newValue) = set(alphaModesLayout, alphaModesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceCapabilities)
		}
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
		val usagesLayout = LAYOUT.withName("usages")

		val formatCountOffset = 8L + usagesOffset
		val formatCountLayout = LAYOUT.withName("formatCount")

		val formatsOffset = 8L + formatCountOffset
		val formatsLayout = LAYOUT.withName("formats")

		val presentModeCountOffset = 8L + formatsOffset
		val presentModeCountLayout = LAYOUT.withName("presentModeCount")

		val presentModesOffset = 8L + presentModeCountOffset
		val presentModesLayout = LAYOUT.withName("presentModes")

		val alphaModeCountOffset = 8L + presentModesOffset
		val alphaModeCountLayout = LAYOUT.withName("alphaModeCount")

		val alphaModesOffset = 8L + alphaModeCountOffset
		val alphaModesLayout = LAYOUT.withName("alphaModes")

	}
}

@JvmInline
actual value class WGPUSurfaceConfiguration(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var device: WGPUDevice?
		get() = get(deviceLayout, deviceOffset).let(::WGPUDevice)
		set(newValue) = set(deviceLayout, deviceOffset, newValue?.handler)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var usage: ULong
		get() = getULong(usageLayout, usageOffset)
		set(newValue) = set(usageLayout, usageOffset, newValue)

	actual var width: UInt
		get() = getUInt(widthLayout, widthOffset)
		set(newValue) = set(widthLayout, widthOffset, newValue)

	actual var height: UInt
		get() = getUInt(heightLayout, heightOffset)
		set(newValue) = set(heightLayout, heightOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong(viewFormatCountLayout, viewFormatCountOffset)
		set(newValue) = set(viewFormatCountLayout, viewFormatCountOffset, newValue)

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = get(viewFormatsLayout, viewFormatsOffset).let(::ArrayHolder)
		set(newValue) = set(viewFormatsLayout, viewFormatsOffset, newValue?.handler)

	actual var alphaMode: WGPUCompositeAlphaMode
		get() = getUInt(alphaModeLayout, alphaModeOffset)
		set(newValue) = set(alphaModeLayout, alphaModeOffset, newValue)

	actual var presentMode: WGPUPresentMode
		get() = getUInt(presentModeLayout, presentModeOffset)
		set(newValue) = set(presentModeLayout, presentModeOffset, newValue)

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
			C_INT.withName("presentMode")
		).withName("WGPUSurfaceConfiguration")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val deviceOffset = 8L + nextInChainOffset
		val deviceLayout = LAYOUT.withName("device")

		val formatOffset = 8L + deviceOffset
		val formatLayout = LAYOUT.withName("format")

		val usageOffset = 4L + formatOffset
		val usageLayout = LAYOUT.withName("usage")

		val widthOffset = 8L + usageOffset
		val widthLayout = LAYOUT.withName("width")

		val heightOffset = 4L + widthOffset
		val heightLayout = LAYOUT.withName("height")

		val viewFormatCountOffset = 4L + heightOffset
		val viewFormatCountLayout = LAYOUT.withName("viewFormatCount")

		val viewFormatsOffset = 8L + viewFormatCountOffset
		val viewFormatsLayout = LAYOUT.withName("viewFormats")

		val alphaModeOffset = 8L + viewFormatsOffset
		val alphaModeLayout = LAYOUT.withName("alphaMode")

		val presentModeOffset = 4L + alphaModeOffset
		val presentModeLayout = LAYOUT.withName("presentMode")

	}
}

@JvmInline
actual value class WGPUSurfaceDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label")
		).withName("WGPUSurfaceDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceAndroidNativeWindow(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

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
			C_POINTER.withName("window")
		).withName("WGPUSurfaceSourceAndroidNativeWindow")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val windowOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val windowLayout = LAYOUT.withName("window")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceMetalLayer(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

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
			C_POINTER.withName("layer")
		).withName("WGPUSurfaceSourceMetalLayer")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val layerOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val layerLayout = LAYOUT.withName("layer")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceWaylandSurface(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

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
			C_POINTER.withName("surface")
		).withName("WGPUSurfaceSourceWaylandSurface")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val displayOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val displayLayout = LAYOUT.withName("display")

		val surfaceOffset = 8L + displayOffset
		val surfaceLayout = LAYOUT.withName("surface")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceWindowsHWND(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

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
			C_POINTER.withName("hwnd")
		).withName("WGPUSurfaceSourceWindowsHWND")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val hinstanceOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val hinstanceLayout = LAYOUT.withName("hinstance")

		val hwndOffset = 8L + hinstanceOffset
		val hwndLayout = LAYOUT.withName("hwnd")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceXCBWindow(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

	actual var connection: NativeAddress?
		get() = get(connectionLayout, connectionOffset)
		set(newValue) = set(connectionLayout, connectionOffset, newValue)

	actual var window: UInt
		get() = getUInt(windowLayout, windowOffset)
		set(newValue) = set(windowLayout, windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceXCBWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("connection"),
			C_INT.withName("window")
		).withName("WGPUSurfaceSourceXCBWindow")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val connectionOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val connectionLayout = LAYOUT.withName("connection")

		val windowOffset = 8L + connectionOffset
		val windowLayout = LAYOUT.withName("window")

	}
}

@JvmInline
actual value class WGPUSurfaceSourceXlibWindow(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)

	actual var display: NativeAddress?
		get() = get(displayLayout, displayOffset)
		set(newValue) = set(displayLayout, displayOffset, newValue)

	actual var window: ULong
		get() = getULong(windowLayout, windowOffset)
		set(newValue) = set(windowLayout, windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceSourceXlibWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("display"),
			C_LONG.withName("window")
		).withName("WGPUSurfaceSourceXlibWindow")

		val chainOffset = 0L
		val chainLayout = LAYOUT.withName("chain")

		val displayOffset = LAYOUT.withName("chain").byteSize() + chainOffset
		val displayLayout = LAYOUT.withName("display")

		val windowOffset = 8L + displayOffset
		val windowLayout = LAYOUT.withName("window")

	}
}

@JvmInline
actual value class WGPUSurfaceTexture(actual override val handler: NativeAddress) : CStructure {
	actual var texture: WGPUTexture?
		get() = get(textureLayout, textureOffset).let(::WGPUTexture)
		set(newValue) = set(textureLayout, textureOffset, newValue?.handler)

	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = getUInt(statusLayout, statusOffset)
		set(newValue) = set(statusLayout, statusOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUSurfaceTexture)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("status")
		).withName("WGPUSurfaceTexture")

		val textureOffset = 0L
		val textureLayout = LAYOUT.withName("texture")

		val statusOffset = 8L + textureOffset
		val statusLayout = LAYOUT.withName("status")

	}
}

@JvmInline
actual value class WGPUTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var sampleType: WGPUTextureSampleType
		get() = getUInt(sampleTypeLayout, sampleTypeOffset)
		set(newValue) = set(sampleTypeLayout, sampleTypeOffset, newValue)

	actual var viewDimension: WGPUTextureViewDimension
		get() = getUInt(viewDimensionLayout, viewDimensionOffset)
		set(newValue) = set(viewDimensionLayout, viewDimensionOffset, newValue)

	actual var multisampled: Boolean
		get() = getInt(multisampledLayout, multisampledOffset).toBoolean()
		set(newValue) = set(multisampledLayout, multisampledOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("sampleType"),
			C_INT.withName("viewDimension"),
			C_INT.withName("multisampled")
		).withName("WGPUTextureBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val sampleTypeOffset = 8L + nextInChainOffset
		val sampleTypeLayout = LAYOUT.withName("sampleType")

		val viewDimensionOffset = 4L + sampleTypeOffset
		val viewDimensionLayout = LAYOUT.withName("viewDimension")

		val multisampledOffset = 4L + viewDimensionOffset
		val multisampledLayout = LAYOUT.withName("multisampled")

	}
}

@JvmInline
actual value class WGPUTextureDataLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var offset: ULong
		get() = getULong(offsetLayout, offsetOffset)
		set(newValue) = set(offsetLayout, offsetOffset, newValue)

	actual var bytesPerRow: UInt
		get() = getUInt(bytesPerRowLayout, bytesPerRowOffset)
		set(newValue) = set(bytesPerRowLayout, bytesPerRowOffset, newValue)

	actual var rowsPerImage: UInt
		get() = getUInt(rowsPerImageLayout, rowsPerImageOffset)
		set(newValue) = set(rowsPerImageLayout, rowsPerImageOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDataLayout {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUTextureDataLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("offset"),
			C_INT.withName("bytesPerRow"),
			C_INT.withName("rowsPerImage")
		).withName("WGPUTextureDataLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val offsetOffset = 8L + nextInChainOffset
		val offsetLayout = LAYOUT.withName("offset")

		val bytesPerRowOffset = 8L + offsetOffset
		val bytesPerRowLayout = LAYOUT.withName("bytesPerRow")

		val rowsPerImageOffset = 4L + bytesPerRowOffset
		val rowsPerImageLayout = LAYOUT.withName("rowsPerImage")

	}
}

@JvmInline
actual value class WGPUTextureDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var usage: ULong
		get() = getULong(usageLayout, usageOffset)
		set(newValue) = set(usageLayout, usageOffset, newValue)

	actual var dimension: WGPUTextureDimension
		get() = getUInt(dimensionLayout, dimensionOffset)
		set(newValue) = set(dimensionLayout, dimensionOffset, newValue)

	actual val size: WGPUExtent3D
		get() = get(sizeLayout, sizeOffset).let(::WGPUExtent3D)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt(mipLevelCountLayout, mipLevelCountOffset)
		set(newValue) = set(mipLevelCountLayout, mipLevelCountOffset, newValue)

	actual var sampleCount: UInt
		get() = getUInt(sampleCountLayout, sampleCountOffset)
		set(newValue) = set(sampleCountLayout, sampleCountOffset, newValue)

	actual var viewFormatCount: ULong
		get() = getULong(viewFormatCountLayout, viewFormatCountOffset)
		set(newValue) = set(viewFormatCountLayout, viewFormatCountOffset, newValue)

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
			C_POINTER.withName("viewFormats")
		).withName("WGPUTextureDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val usageOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val usageLayout = LAYOUT.withName("usage")

		val dimensionOffset = 8L + usageOffset
		val dimensionLayout = LAYOUT.withName("dimension")

		val sizeOffset = 4L + dimensionOffset
		val sizeLayout = LAYOUT.withName("size")

		val formatOffset = LAYOUT.withName("size").byteSize() + sizeOffset
		val formatLayout = LAYOUT.withName("format")

		val mipLevelCountOffset = 4L + formatOffset
		val mipLevelCountLayout = LAYOUT.withName("mipLevelCount")

		val sampleCountOffset = 4L + mipLevelCountOffset
		val sampleCountLayout = LAYOUT.withName("sampleCount")

		val viewFormatCountOffset = 4L + sampleCountOffset
		val viewFormatCountLayout = LAYOUT.withName("viewFormatCount")

		val viewFormatsOffset = 8L + viewFormatCountOffset
		val viewFormatsLayout = LAYOUT.withName("viewFormats")

	}
}

@JvmInline
actual value class WGPUTextureViewDescriptor(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual val label: WGPUStringView
		get() = get(labelLayout, labelOffset).let(::WGPUStringView)

	actual var format: WGPUTextureFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var dimension: WGPUTextureViewDimension
		get() = getUInt(dimensionLayout, dimensionOffset)
		set(newValue) = set(dimensionLayout, dimensionOffset, newValue)

	actual var baseMipLevel: UInt
		get() = getUInt(baseMipLevelLayout, baseMipLevelOffset)
		set(newValue) = set(baseMipLevelLayout, baseMipLevelOffset, newValue)

	actual var mipLevelCount: UInt
		get() = getUInt(mipLevelCountLayout, mipLevelCountOffset)
		set(newValue) = set(mipLevelCountLayout, mipLevelCountOffset, newValue)

	actual var baseArrayLayer: UInt
		get() = getUInt(baseArrayLayerLayout, baseArrayLayerOffset)
		set(newValue) = set(baseArrayLayerLayout, baseArrayLayerOffset, newValue)

	actual var arrayLayerCount: UInt
		get() = getUInt(arrayLayerCountLayout, arrayLayerCountOffset)
		set(newValue) = set(arrayLayerCountLayout, arrayLayerCountOffset, newValue)

	actual var aspect: WGPUTextureAspect
		get() = getUInt(aspectLayout, aspectOffset)
		set(newValue) = set(aspectLayout, aspectOffset, newValue)

	actual var usage: ULong
		get() = getULong(usageLayout, usageOffset)
		set(newValue) = set(usageLayout, usageOffset, newValue)

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
			C_LONG.withName("usage")
		).withName("WGPUTextureViewDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val labelOffset = 8L + nextInChainOffset
		val labelLayout = LAYOUT.withName("label")

		val formatOffset = LAYOUT.withName("label").byteSize() + labelOffset
		val formatLayout = LAYOUT.withName("format")

		val dimensionOffset = 4L + formatOffset
		val dimensionLayout = LAYOUT.withName("dimension")

		val baseMipLevelOffset = 4L + dimensionOffset
		val baseMipLevelLayout = LAYOUT.withName("baseMipLevel")

		val mipLevelCountOffset = 4L + baseMipLevelOffset
		val mipLevelCountLayout = LAYOUT.withName("mipLevelCount")

		val baseArrayLayerOffset = 4L + mipLevelCountOffset
		val baseArrayLayerLayout = LAYOUT.withName("baseArrayLayer")

		val arrayLayerCountOffset = 4L + baseArrayLayerOffset
		val arrayLayerCountLayout = LAYOUT.withName("arrayLayerCount")

		val aspectOffset = 4L + arrayLayerCountOffset
		val aspectLayout = LAYOUT.withName("aspect")

		val usageOffset = 4L + aspectOffset
		val usageLayout = LAYOUT.withName("usage")

	}
}

@JvmInline
actual value class WGPUVertexAttribute(actual override val handler: NativeAddress) : CStructure {
	actual var format: WGPUVertexFormat
		get() = getUInt(formatLayout, formatOffset)
		set(newValue) = set(formatLayout, formatOffset, newValue)

	actual var offset: ULong
		get() = getULong(offsetLayout, offsetOffset)
		set(newValue) = set(offsetLayout, offsetOffset, newValue)

	actual var shaderLocation: UInt
		get() = getUInt(shaderLocationLayout, shaderLocationOffset)
		set(newValue) = set(shaderLocationLayout, shaderLocationOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUVertexAttribute)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_LONG.withName("offset"),
			C_INT.withName("shaderLocation")
		).withName("WGPUVertexAttribute")

		val formatOffset = 0L
		val formatLayout = LAYOUT.withName("format")

		val offsetOffset = 4L + formatOffset
		val offsetLayout = LAYOUT.withName("offset")

		val shaderLocationOffset = 8L + offsetOffset
		val shaderLocationLayout = LAYOUT.withName("shaderLocation")

	}
}

@JvmInline
actual value class WGPUVertexBufferLayout(actual override val handler: NativeAddress) : CStructure {
	actual var arrayStride: ULong
		get() = getULong(arrayStrideLayout, arrayStrideOffset)
		set(newValue) = set(arrayStrideLayout, arrayStrideOffset, newValue)

	actual var stepMode: WGPUVertexStepMode
		get() = getUInt(stepModeLayout, stepModeOffset)
		set(newValue) = set(stepModeLayout, stepModeOffset, newValue)

	actual var attributeCount: ULong
		get() = getULong(attributeCountLayout, attributeCountOffset)
		set(newValue) = set(attributeCountLayout, attributeCountOffset, newValue)

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
			C_POINTER.withName("attributes")
		).withName("WGPUVertexBufferLayout")

		val arrayStrideOffset = 0L
		val arrayStrideLayout = LAYOUT.withName("arrayStride")

		val stepModeOffset = 8L + arrayStrideOffset
		val stepModeLayout = LAYOUT.withName("stepMode")

		val attributeCountOffset = 4L + stepModeOffset
		val attributeCountLayout = LAYOUT.withName("attributeCount")

		val attributesOffset = 8L + attributeCountOffset
		val attributesLayout = LAYOUT.withName("attributes")

	}
}

@JvmInline
actual value class WGPUVertexState(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)

	actual var module: WGPUShaderModule?
		get() = get(moduleLayout, moduleOffset).let(::WGPUShaderModule)
		set(newValue) = set(moduleLayout, moduleOffset, newValue?.handler)

	actual val entryPoint: WGPUStringView
		get() = get(entryPointLayout, entryPointOffset).let(::WGPUStringView)

	actual var constantCount: ULong
		get() = getULong(constantCountLayout, constantCountOffset)
		set(newValue) = set(constantCountLayout, constantCountOffset, newValue)

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual var bufferCount: ULong
		get() = getULong(bufferCountLayout, bufferCountOffset)
		set(newValue) = set(bufferCountLayout, bufferCountOffset, newValue)

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
			C_POINTER.withName("buffers")
		).withName("WGPUVertexState")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val moduleOffset = 8L + nextInChainOffset
		val moduleLayout = LAYOUT.withName("module")

		val entryPointOffset = 8L + moduleOffset
		val entryPointLayout = LAYOUT.withName("entryPoint")

		val constantCountOffset = LAYOUT.withName("entryPoint").byteSize() + entryPointOffset
		val constantCountLayout = LAYOUT.withName("constantCount")

		val constantsOffset = 8L + constantCountOffset
		val constantsLayout = LAYOUT.withName("constants")

		val bufferCountOffset = 8L + constantsOffset
		val bufferCountLayout = LAYOUT.withName("bufferCount")

		val buffersOffset = 8L + bufferCountOffset
		val buffersLayout = LAYOUT.withName("buffers")

	}
}

@JvmInline
actual value class WGPUChainedStruct(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStruct?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = getUInt(sTypeLayout, sTypeOffset)
		set(newValue) = set(sTypeLayout, sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUChainedStruct)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStruct")

		val nextOffset = 0L
		val nextLayout = LAYOUT.withName("next")

		val sTypeOffset = 8L + nextOffset
		val sTypeLayout = LAYOUT.withName("sType")

	}
}

@JvmInline
actual value class WGPUChainedStructOut(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStructOut?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)

	actual var sType: WGPUSType
		get() = getUInt(sTypeLayout, sTypeOffset)
		set(newValue) = set(sTypeLayout, sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUChainedStructOut)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStructOut")

		val nextOffset = 0L
		val nextLayout = LAYOUT.withName("next")

		val sTypeOffset = 8L + nextOffset
		val sTypeLayout = LAYOUT.withName("sType")

	}
}

@JvmInline
actual value class WGPUStringView(actual override val handler: NativeAddress) : CStructure {
	actual var data: CString?
		get() = get(dataLayout, dataOffset).let(::CString)
		set(newValue) = set(dataLayout, dataOffset, newValue?.handler)

	actual var length: ULong
		get() = getULong(lengthLayout, lengthOffset)
		set(newValue) = set(lengthLayout, lengthOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
			return allocator.allocate(LAYOUT.byteSize())
				.let(::WGPUStringView)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("data"),
			C_LONG.withName("length")
		).withName("WGPUStringView")

		val dataOffset = 0L
		val dataLayout = LAYOUT.withName("data")

		val lengthOffset = 8L + dataOffset
		val lengthLayout = LAYOUT.withName("length")

	}
}

@JvmInline
actual value class WGPUBufferMapCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUBufferMapCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUCompilationInfoCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUCompilationInfoCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateComputePipelineAsyncCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateRenderPipelineAsyncCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUDeviceLostCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUDeviceLostCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUPopErrorScopeCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUPopErrorScopeCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUQueueWorkDoneCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUQueueWorkDoneCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPURequestAdapterCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPURequestAdapterCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPURequestDeviceCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPURequestDeviceCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

@JvmInline
actual value class WGPUUncapturedErrorCallbackInfo(actual override val handler: NativeAddress) : CStructure {
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
			C_POINTER.withName("userdata2")
		).withName("WGPUUncapturedErrorCallbackInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = LAYOUT.withName("nextInChain")

		val callbackOffset = 8L + nextInChainOffset
		val callbackLayout = LAYOUT.withName("callback")

		val userdata1Offset = 8L + callbackOffset
		val userdata1Layout = LAYOUT.withName("userdata1")

		val userdata2Offset = 8L + userdata1Offset
		val userdata2Layout = LAYOUT.withName("userdata2")

	}
}

