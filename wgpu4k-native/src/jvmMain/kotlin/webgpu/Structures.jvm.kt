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
import java.lang.foreign.MemoryLayout
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.lang.foreign.MemoryLayout.structLayout

@JvmInline
actual value class WGPUStringView(actual override val handler: NativeAddress) : CStructure {
	actual var data: CString?
		get() = get(dataLayout, dataOffset).let(::CString)
		set(newValue) = set(dataLayout, dataOffset, newValue?.handler)
	actual var length: ULong
		get() = getULong(lengthOffset)
		set(newValue) = set(lengthOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
			return allocator.allocate(16L)
				.let(::WGPUStringView)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("data"),
			C_LONG.withName("length"),
		).withName("WGPUStringView")

		val dataOffset = 0L
		val dataLayout = C_POINTER
		val lengthOffset = 8L
		val lengthLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUAdapterInfo(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)
	actual val vendor: WGPUStringView
		get() = get(vendorLayout, vendorOffset).let(::WGPUStringView)
	actual val architecture: WGPUStringView
		get() = get(architectureLayout, architectureOffset).let(::WGPUStringView)
	actual val device: WGPUStringView
		get() = get(deviceLayout, deviceOffset).let(::WGPUStringView)
	actual val description: WGPUStringView
		get() = get(descriptionLayout, descriptionOffset).let(::WGPUStringView)
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
			return allocator.allocate(88L)
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
		val vendorOffset = 8L
		val vendorLayout = WGPUStringView.LAYOUT
		val architectureOffset = 0L
		val architectureLayout = WGPUStringView.LAYOUT
		val deviceOffset = 0L
		val deviceLayout = WGPUStringView.LAYOUT
		val descriptionOffset = 0L
		val descriptionLayout = WGPUStringView.LAYOUT
		val backendTypeOffset = 0L
		val backendTypeLayout = C_INT
		val adapterTypeOffset = 4L
		val adapterTypeLayout = C_INT
		val vendorIDOffset = 4L
		val vendorIDLayout = C_INT
		val deviceIDOffset = 4L
		val deviceIDLayout = C_INT
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
		get() = getULong(entryCountOffset)
		set(newValue) = set(entryCountOffset, newValue)
	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
		get() = get(entriesLayout, entriesOffset).let(::ArrayHolder)
		set(newValue) = set(entriesLayout, entriesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor {
			return allocator.allocate(48L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = 0L
		val layoutLayout = C_POINTER
		val entryCountOffset = 8L
		val entryCountLayout = C_LONG
		val entriesOffset = 8L
		val entriesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUBindGroupEntry(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(56L)
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
		val bindingOffset = 8L
		val bindingLayout = C_INT
		val bufferOffset = 4L
		val bufferLayout = C_POINTER
		val offsetOffset = 8L
		val offsetLayout = C_LONG
		val sizeOffset = 8L
		val sizeLayout = C_LONG
		val samplerOffset = 8L
		val samplerLayout = C_POINTER
		val textureViewOffset = 8L
		val textureViewLayout = C_POINTER
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
		get() = getULong(entryCountOffset)
		set(newValue) = set(entryCountOffset, newValue)
	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
		get() = get(entriesLayout, entriesOffset).let(::ArrayHolder)
		set(newValue) = set(entriesLayout, entriesOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor {
			return allocator.allocate(40L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val entryCountOffset = 0L
		val entryCountLayout = C_LONG
		val entriesOffset = 8L
		val entriesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUBufferBindingLayout(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
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
		val typeOffset = 8L
		val typeLayout = C_INT
		val hasDynamicOffsetOffset = 4L
		val hasDynamicOffsetLayout = C_INT
		val minBindingSizeOffset = 4L
		val minBindingSizeLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUSamplerBindingLayout(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStruct?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)
	actual var type: WGPUSamplerBindingType
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
			return allocator.allocate(16L)
				.let(::WGPUSamplerBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("type"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUSamplerBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val typeOffset = 8L
		val typeLayout = C_INT
	}
}
@JvmInline
actual value class WGPUTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("sampleType"),
			C_INT.withName("viewDimension"),
			C_INT.withName("multisampled"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUTextureBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val sampleTypeOffset = 8L
		val sampleTypeLayout = C_INT
		val viewDimensionOffset = 4L
		val viewDimensionLayout = C_INT
		val multisampledOffset = 4L
		val multisampledLayout = C_INT
	}
}
@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUStorageTextureBindingLayout)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("access"),
			C_INT.withName("format"),
			C_INT.withName("viewDimension"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUStorageTextureBindingLayout")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val accessOffset = 8L
		val accessLayout = C_INT
		val formatOffset = 4L
		val formatLayout = C_INT
		val viewDimensionOffset = 4L
		val viewDimensionLayout = C_INT
	}
}
@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual override val handler: NativeAddress) : CStructure {
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
		get() = get(bufferLayout, bufferOffset).let(::WGPUBufferBindingLayout)
	actual val sampler: WGPUSamplerBindingLayout
		get() = get(samplerLayout, samplerOffset).let(::WGPUSamplerBindingLayout)
	actual val texture: WGPUTextureBindingLayout
		get() = get(textureLayout, textureOffset).let(::WGPUTextureBindingLayout)
	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = get(storageTextureLayout, storageTextureOffset).let(::WGPUStorageTextureBindingLayout)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry {
			return allocator.allocate(112L)
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
		val bindingOffset = 8L
		val bindingLayout = C_INT
		val visibilityOffset = 4L
		val visibilityLayout = C_LONG
		val bufferOffset = 8L
		val bufferLayout = WGPUBufferBindingLayout.LAYOUT
		val samplerOffset = 0L
		val samplerLayout = WGPUSamplerBindingLayout.LAYOUT
		val textureOffset = 0L
		val textureLayout = WGPUTextureBindingLayout.LAYOUT
		val storageTextureOffset = 0L
		val storageTextureLayout = WGPUStorageTextureBindingLayout.LAYOUT
	}
}
@JvmInline
actual value class WGPUBlendComponent(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(12L)
				.let(::WGPUBlendComponent)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("operation"),
			C_INT.withName("srcFactor"),
			C_INT.withName("dstFactor"),
		).withName("WGPUBlendComponent")

		val operationOffset = 0L
		val operationLayout = C_INT
		val srcFactorOffset = 4L
		val srcFactorLayout = C_INT
		val dstFactorOffset = 4L
		val dstFactorLayout = C_INT
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
			return allocator.allocate(24L)
				.let(::WGPUBlendState)
		}
		internal val LAYOUT = structLayout(
			WGPUBlendComponent.LAYOUT.withName("color"),
			WGPUBlendComponent.LAYOUT.withName("alpha"),
		).withName("WGPUBlendState")

		val colorOffset = 0L
		val colorLayout = WGPUBlendComponent.LAYOUT
		val alphaOffset = 0L
		val alphaLayout = WGPUBlendComponent.LAYOUT
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
			return allocator.allocate(48L)
				.let(::WGPUBufferDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_LONG.withName("usage"),
			C_LONG.withName("size"),
			C_INT.withName("mappedAtCreation"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUBufferDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val usageOffset = 0L
		val usageLayout = C_LONG
		val sizeOffset = 8L
		val sizeLayout = C_LONG
		val mappedAtCreationOffset = 8L
		val mappedAtCreationLayout = C_INT
	}
}
@JvmInline
actual value class WGPUColor(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(32L)
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
		val gOffset = 8L
		val gLayout = C_DOUBLE
		val bOffset = 8L
		val bLayout = C_DOUBLE
		val aOffset = 8L
		val aLayout = C_DOUBLE
	}
}
@JvmInline
actual value class WGPUColorTargetState(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(32L)
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
		val formatOffset = 8L
		val formatLayout = C_INT
		val blendOffset = 4L
		val blendLayout = C_POINTER
		val writeMaskOffset = 8L
		val writeMaskLayout = C_LONG
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
			return allocator.allocate(24L)
				.let(::WGPUCommandBufferDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUCommandBufferDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
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
			return allocator.allocate(24L)
				.let(::WGPUCommandEncoderDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUCommandEncoderDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUCompilationInfo(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUCompilationInfo)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("messageCount"),
			C_POINTER.withName("messages"),
		).withName("WGPUCompilationInfo")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val messageCountOffset = 8L
		val messageCountLayout = C_LONG
		val messagesOffset = 8L
		val messagesLayout = C_POINTER
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
			return allocator.allocate(88L)
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
		val messageOffset = 8L
		val messageLayout = WGPUStringView.LAYOUT
		val typeOffset = 0L
		val typeLayout = C_INT
		val lineNumOffset = 4L
		val lineNumLayout = C_LONG
		val linePosOffset = 8L
		val linePosLayout = C_LONG
		val offsetOffset = 8L
		val offsetLayout = C_LONG
		val lengthOffset = 8L
		val lengthLayout = C_LONG
		val utf16LinePosOffset = 8L
		val utf16LinePosLayout = C_LONG
		val utf16OffsetOffset = 8L
		val utf16OffsetLayout = C_LONG
		val utf16LengthOffset = 8L
		val utf16LengthLayout = C_LONG
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
			return allocator.allocate(32L)
				.let(::WGPUComputePassDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
			C_POINTER.withName("timestampWrites"),
		).withName("WGPUComputePassDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val timestampWritesOffset = 0L
		val timestampWritesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUComputePassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(16L)
				.let(::WGPUComputePassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex"),
		).withName("WGPUComputePassTimestampWrites")

		val querySetOffset = 0L
		val querySetLayout = C_POINTER
		val beginningOfPassWriteIndexOffset = 8L
		val beginningOfPassWriteIndexLayout = C_INT
		val endOfPassWriteIndexOffset = 4L
		val endOfPassWriteIndexLayout = C_INT
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
		get() = getULong(constantCountOffset)
		set(newValue) = set(constantCountOffset, newValue)
	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = get(constantsLayout, constantsOffset).let(::ArrayHolder)
		set(newValue) = set(constantsLayout, constantsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUProgrammableStageDescriptor {
			return allocator.allocate(48L)
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
		val moduleOffset = 8L
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = 0L
		val constantCountLayout = C_LONG
		val constantsOffset = 8L
		val constantsLayout = C_POINTER
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
			return allocator.allocate(80L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = 0L
		val layoutLayout = C_POINTER
		val computeOffset = 8L
		val computeLayout = WGPUProgrammableStageDescriptor.LAYOUT
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
		get() = getDouble(valueOffset)
		set(newValue) = set(valueOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(32L)
				.let(::WGPUConstantEntry)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("key"),
			C_DOUBLE.withName("value"),
		).withName("WGPUConstantEntry")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val keyOffset = 8L
		val keyLayout = WGPUStringView.LAYOUT
		val valueOffset = 0L
		val valueLayout = C_DOUBLE
	}
}
@JvmInline
actual value class WGPUStencilFaceState(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(16L)
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
		val failOpOffset = 4L
		val failOpLayout = C_INT
		val depthFailOpOffset = 4L
		val depthFailOpLayout = C_INT
		val passOpOffset = 4L
		val passOpLayout = C_INT
	}
}
@JvmInline
actual value class WGPUDepthStencilState(actual override val handler: NativeAddress) : CStructure {
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
		get() = get(stencilFrontLayout, stencilFrontOffset).let(::WGPUStencilFaceState)
	actual val stencilBack: WGPUStencilFaceState
		get() = get(stencilBackLayout, stencilBackOffset).let(::WGPUStencilFaceState)
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
			return allocator.allocate(72L)
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
		val formatOffset = 8L
		val formatLayout = C_INT
		val depthWriteEnabledOffset = 4L
		val depthWriteEnabledLayout = C_INT
		val depthCompareOffset = 4L
		val depthCompareLayout = C_INT
		val stencilFrontOffset = 4L
		val stencilFrontLayout = WGPUStencilFaceState.LAYOUT
		val stencilBackOffset = 0L
		val stencilBackLayout = WGPUStencilFaceState.LAYOUT
		val stencilReadMaskOffset = 0L
		val stencilReadMaskLayout = C_INT
		val stencilWriteMaskOffset = 4L
		val stencilWriteMaskLayout = C_INT
		val depthBiasOffset = 4L
		val depthBiasLayout = C_INT
		val depthBiasSlopeScaleOffset = 4L
		val depthBiasSlopeScaleLayout = C_FLOAT
		val depthBiasClampOffset = 4L
		val depthBiasClampLayout = C_FLOAT
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
			return allocator.allocate(24L)
				.let(::WGPUQueueDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUQueueDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
		get() = getULong(requiredFeatureCountOffset)
		set(newValue) = set(requiredFeatureCountOffset, newValue)
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
			return allocator.allocate(136L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val requiredFeatureCountOffset = 0L
		val requiredFeatureCountLayout = C_LONG
		val requiredFeaturesOffset = 8L
		val requiredFeaturesLayout = C_POINTER
		val requiredLimitsOffset = 8L
		val requiredLimitsLayout = C_POINTER
		val defaultQueueOffset = 8L
		val defaultQueueLayout = WGPUQueueDescriptor.LAYOUT
		val deviceLostCallbackInfoOffset = 0L
		val deviceLostCallbackInfoLayout = WGPUDeviceLostCallbackInfo.LAYOUT
		val uncapturedErrorCallbackInfoOffset = 0L
		val uncapturedErrorCallbackInfoLayout = WGPUUncapturedErrorCallbackInfo.LAYOUT
	}
}
@JvmInline
actual value class WGPUExtent3D(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(12L)
				.let(::WGPUExtent3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_INT.withName("depthOrArrayLayers"),
		).withName("WGPUExtent3D")

		val widthOffset = 0L
		val widthLayout = C_INT
		val heightOffset = 4L
		val heightLayout = C_INT
		val depthOrArrayLayersOffset = 4L
		val depthOrArrayLayersLayout = C_INT
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
			return allocator.allocate(64L)
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
		val moduleOffset = 8L
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = 0L
		val constantCountLayout = C_LONG
		val constantsOffset = 8L
		val constantsLayout = C_POINTER
		val targetCountOffset = 8L
		val targetCountLayout = C_LONG
		val targetsOffset = 8L
		val targetsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUFuture(actual override val handler: NativeAddress) : CStructure {
	actual var id: ULong
		get() = getULong(idOffset)
		set(newValue) = set(idOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
			return allocator.allocate(8L)
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
	actual val future: WGPUFuture
		get() = get(futureLayout, futureOffset).let(::WGPUFuture)
	actual var completed: Boolean
		get() = getInt(completedOffset).toBoolean()
		set(newValue) = set(completedOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
			return allocator.allocate(16L)
				.let(::WGPUFutureWaitInfo)
		}
		internal val LAYOUT = structLayout(
			WGPUFuture.LAYOUT.withName("future"),
			C_INT.withName("completed"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUFutureWaitInfo")

		val futureOffset = 0L
		val futureLayout = WGPUFuture.LAYOUT
		val completedOffset = 0L
		val completedLayout = C_INT
	}
}
@JvmInline
actual value class WGPUTextureDataLayout(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
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
		val offsetOffset = 8L
		val offsetLayout = C_LONG
		val bytesPerRowOffset = 8L
		val bytesPerRowLayout = C_INT
		val rowsPerImageOffset = 4L
		val rowsPerImageLayout = C_INT
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
			return allocator.allocate(40L)
				.let(::WGPUImageCopyBuffer)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUTextureDataLayout.LAYOUT.withName("layout"),
			C_POINTER.withName("buffer"),
		).withName("WGPUImageCopyBuffer")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val layoutOffset = 8L
		val layoutLayout = WGPUTextureDataLayout.LAYOUT
		val bufferOffset = 0L
		val bufferLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUOrigin3D(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(12L)
				.let(::WGPUOrigin3D)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("x"),
			C_INT.withName("y"),
			C_INT.withName("z"),
		).withName("WGPUOrigin3D")

		val xOffset = 0L
		val xLayout = C_INT
		val yOffset = 4L
		val yLayout = C_INT
		val zOffset = 4L
		val zLayout = C_INT
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
		get() = getUInt(mipLevelOffset)
		set(newValue) = set(mipLevelOffset, newValue)
	actual val origin: WGPUOrigin3D
		get() = get(originLayout, originOffset).let(::WGPUOrigin3D)
	actual var aspect: WGPUTextureAspect
		get() = getUInt(aspectOffset)
		set(newValue) = set(aspectOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyTexture {
			return allocator.allocate(40L)
				.let(::WGPUImageCopyTexture)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("texture"),
			C_INT.withName("mipLevel"),
			WGPUOrigin3D.LAYOUT.withName("origin"),
			C_INT.withName("aspect"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUImageCopyTexture")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val textureOffset = 8L
		val textureLayout = C_POINTER
		val mipLevelOffset = 8L
		val mipLevelLayout = C_INT
		val originOffset = 4L
		val originLayout = WGPUOrigin3D.LAYOUT
		val aspectOffset = 0L
		val aspectLayout = C_INT
	}
}
@JvmInline
actual value class WGPUInstanceFeatures(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUInstanceFeatures)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("timedWaitAnyEnable"),
			C_LONG.withName("timedWaitAnyMaxCount"),
		).withName("WGPUInstanceFeatures")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val timedWaitAnyEnableOffset = 8L
		val timedWaitAnyEnableLayout = C_INT
		val timedWaitAnyMaxCountOffset = 4L
		val timedWaitAnyMaxCountLayout = C_LONG
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
			return allocator.allocate(32L)
				.let(::WGPUInstanceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUInstanceFeatures.LAYOUT.withName("features"),
		).withName("WGPUInstanceDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val featuresOffset = 8L
		val featuresLayout = WGPUInstanceFeatures.LAYOUT
	}
}
@JvmInline
actual value class WGPULimits(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(144L)
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
			MemoryLayout.paddingLayout(4)
		).withName("WGPULimits")

		val maxTextureDimension1DOffset = 0L
		val maxTextureDimension1DLayout = C_INT
		val maxTextureDimension2DOffset = 4L
		val maxTextureDimension2DLayout = C_INT
		val maxTextureDimension3DOffset = 4L
		val maxTextureDimension3DLayout = C_INT
		val maxTextureArrayLayersOffset = 4L
		val maxTextureArrayLayersLayout = C_INT
		val maxBindGroupsOffset = 4L
		val maxBindGroupsLayout = C_INT
		val maxBindGroupsPlusVertexBuffersOffset = 4L
		val maxBindGroupsPlusVertexBuffersLayout = C_INT
		val maxBindingsPerBindGroupOffset = 4L
		val maxBindingsPerBindGroupLayout = C_INT
		val maxDynamicUniformBuffersPerPipelineLayoutOffset = 4L
		val maxDynamicUniformBuffersPerPipelineLayoutLayout = C_INT
		val maxDynamicStorageBuffersPerPipelineLayoutOffset = 4L
		val maxDynamicStorageBuffersPerPipelineLayoutLayout = C_INT
		val maxSampledTexturesPerShaderStageOffset = 4L
		val maxSampledTexturesPerShaderStageLayout = C_INT
		val maxSamplersPerShaderStageOffset = 4L
		val maxSamplersPerShaderStageLayout = C_INT
		val maxStorageBuffersPerShaderStageOffset = 4L
		val maxStorageBuffersPerShaderStageLayout = C_INT
		val maxStorageTexturesPerShaderStageOffset = 4L
		val maxStorageTexturesPerShaderStageLayout = C_INT
		val maxUniformBuffersPerShaderStageOffset = 4L
		val maxUniformBuffersPerShaderStageLayout = C_INT
		val maxUniformBufferBindingSizeOffset = 4L
		val maxUniformBufferBindingSizeLayout = C_LONG
		val maxStorageBufferBindingSizeOffset = 8L
		val maxStorageBufferBindingSizeLayout = C_LONG
		val minUniformBufferOffsetAlignmentOffset = 8L
		val minUniformBufferOffsetAlignmentLayout = C_INT
		val minStorageBufferOffsetAlignmentOffset = 4L
		val minStorageBufferOffsetAlignmentLayout = C_INT
		val maxVertexBuffersOffset = 4L
		val maxVertexBuffersLayout = C_INT
		val maxBufferSizeOffset = 4L
		val maxBufferSizeLayout = C_LONG
		val maxVertexAttributesOffset = 8L
		val maxVertexAttributesLayout = C_INT
		val maxVertexBufferArrayStrideOffset = 4L
		val maxVertexBufferArrayStrideLayout = C_INT
		val maxInterStageShaderVariablesOffset = 4L
		val maxInterStageShaderVariablesLayout = C_INT
		val maxColorAttachmentsOffset = 4L
		val maxColorAttachmentsLayout = C_INT
		val maxColorAttachmentBytesPerSampleOffset = 4L
		val maxColorAttachmentBytesPerSampleLayout = C_INT
		val maxComputeWorkgroupStorageSizeOffset = 4L
		val maxComputeWorkgroupStorageSizeLayout = C_INT
		val maxComputeInvocationsPerWorkgroupOffset = 4L
		val maxComputeInvocationsPerWorkgroupLayout = C_INT
		val maxComputeWorkgroupSizeXOffset = 4L
		val maxComputeWorkgroupSizeXLayout = C_INT
		val maxComputeWorkgroupSizeYOffset = 4L
		val maxComputeWorkgroupSizeYLayout = C_INT
		val maxComputeWorkgroupSizeZOffset = 4L
		val maxComputeWorkgroupSizeZLayout = C_INT
		val maxComputeWorkgroupsPerDimensionOffset = 4L
		val maxComputeWorkgroupsPerDimensionLayout = C_INT
	}
}
@JvmInline
actual value class WGPUMultisampleState(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUMultisampleState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("count"),
			C_INT.withName("mask"),
			C_INT.withName("alphaToCoverageEnabled"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUMultisampleState")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val countOffset = 8L
		val countLayout = C_INT
		val maskOffset = 4L
		val maskLayout = C_INT
		val alphaToCoverageEnabledOffset = 4L
		val alphaToCoverageEnabledLayout = C_INT
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
		get() = getULong(bindGroupLayoutCountOffset)
		set(newValue) = set(bindGroupLayoutCountOffset, newValue)
	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
		get() = get(bindGroupLayoutsLayout, bindGroupLayoutsOffset).let(::ArrayHolder)
		set(newValue) = set(bindGroupLayoutsLayout, bindGroupLayoutsOffset, newValue?.handler)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor {
			return allocator.allocate(40L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val bindGroupLayoutCountOffset = 0L
		val bindGroupLayoutCountLayout = C_LONG
		val bindGroupLayoutsOffset = 8L
		val bindGroupLayoutsLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUPrimitiveState(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(32L)
				.let(::WGPUPrimitiveState)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_INT.withName("topology"),
			C_INT.withName("stripIndexFormat"),
			C_INT.withName("frontFace"),
			C_INT.withName("cullMode"),
			C_INT.withName("unclippedDepth"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUPrimitiveState")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val topologyOffset = 8L
		val topologyLayout = C_INT
		val stripIndexFormatOffset = 4L
		val stripIndexFormatLayout = C_INT
		val frontFaceOffset = 4L
		val frontFaceLayout = C_INT
		val cullModeOffset = 4L
		val cullModeLayout = C_INT
		val unclippedDepthOffset = 4L
		val unclippedDepthLayout = C_INT
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
		get() = getUInt(typeOffset)
		set(newValue) = set(typeOffset, newValue)
	actual var count: UInt
		get() = getUInt(countOffset)
		set(newValue) = set(countOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
			return allocator.allocate(32L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val typeOffset = 0L
		val typeLayout = C_INT
		val countOffset = 4L
		val countLayout = C_INT
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
			return allocator.allocate(24L)
				.let(::WGPURenderBundleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPURenderBundleDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
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
			return allocator.allocate(56L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val colorFormatCountOffset = 0L
		val colorFormatCountLayout = C_LONG
		val colorFormatsOffset = 8L
		val colorFormatsLayout = C_POINTER
		val depthStencilFormatOffset = 8L
		val depthStencilFormatLayout = C_INT
		val sampleCountOffset = 4L
		val sampleCountLayout = C_INT
		val depthReadOnlyOffset = 4L
		val depthReadOnlyLayout = C_INT
		val stencilReadOnlyOffset = 4L
		val stencilReadOnlyLayout = C_INT
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
		get() = get(clearValueLayout, clearValueOffset).let(::WGPUColor)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment {
			return allocator.allocate(72L)
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
		val viewOffset = 8L
		val viewLayout = C_POINTER
		val depthSliceOffset = 8L
		val depthSliceLayout = C_INT
		val resolveTargetOffset = 4L
		val resolveTargetLayout = C_POINTER
		val loadOpOffset = 8L
		val loadOpLayout = C_INT
		val storeOpOffset = 4L
		val storeOpLayout = C_INT
		val clearValueOffset = 4L
		val clearValueLayout = WGPUColor.LAYOUT
	}
}
@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(40L)
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
		val depthLoadOpOffset = 8L
		val depthLoadOpLayout = C_INT
		val depthStoreOpOffset = 4L
		val depthStoreOpLayout = C_INT
		val depthClearValueOffset = 4L
		val depthClearValueLayout = C_FLOAT
		val depthReadOnlyOffset = 4L
		val depthReadOnlyLayout = C_INT
		val stencilLoadOpOffset = 4L
		val stencilLoadOpLayout = C_INT
		val stencilStoreOpOffset = 4L
		val stencilStoreOpLayout = C_INT
		val stencilClearValueOffset = 4L
		val stencilClearValueLayout = C_INT
		val stencilReadOnlyOffset = 4L
		val stencilReadOnlyLayout = C_INT
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
			return allocator.allocate(64L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val colorAttachmentCountOffset = 0L
		val colorAttachmentCountLayout = C_LONG
		val colorAttachmentsOffset = 8L
		val colorAttachmentsLayout = C_POINTER
		val depthStencilAttachmentOffset = 8L
		val depthStencilAttachmentLayout = C_POINTER
		val occlusionQuerySetOffset = 8L
		val occlusionQuerySetLayout = C_POINTER
		val timestampWritesOffset = 8L
		val timestampWritesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUChainedStruct(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStruct?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStruct)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)
	actual var sType: WGPUSType
		get() = getUInt(sTypeOffset)
		set(newValue) = set(sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
			return allocator.allocate(16L)
				.let(::WGPUChainedStruct)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUChainedStruct")

		val nextOffset = 0L
		val nextLayout = C_POINTER
		val sTypeOffset = 8L
		val sTypeLayout = C_INT
	}
}
@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)
	actual var maxDrawCount: ULong
		get() = getULong(maxDrawCountOffset)
		set(newValue) = set(maxDrawCountOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
			return allocator.allocate(24L)
				.let(::WGPURenderPassMaxDrawCount)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_LONG.withName("maxDrawCount"),
		).withName("WGPURenderPassMaxDrawCount")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val maxDrawCountOffset = 0L
		val maxDrawCountLayout = C_LONG
	}
}
@JvmInline
actual value class WGPURenderPassTimestampWrites(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(16L)
				.let(::WGPURenderPassTimestampWrites)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex"),
		).withName("WGPURenderPassTimestampWrites")

		val querySetOffset = 0L
		val querySetLayout = C_POINTER
		val beginningOfPassWriteIndexOffset = 8L
		val beginningOfPassWriteIndexLayout = C_INT
		val endOfPassWriteIndexOffset = 4L
		val endOfPassWriteIndexLayout = C_INT
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
			return allocator.allocate(64L)
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
		val moduleOffset = 8L
		val moduleLayout = C_POINTER
		val entryPointOffset = 8L
		val entryPointLayout = WGPUStringView.LAYOUT
		val constantCountOffset = 0L
		val constantCountLayout = C_LONG
		val constantsOffset = 8L
		val constantsLayout = C_POINTER
		val bufferCountOffset = 8L
		val bufferCountLayout = C_LONG
		val buffersOffset = 8L
		val buffersLayout = C_POINTER
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
			return allocator.allocate(168L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val layoutOffset = 0L
		val layoutLayout = C_POINTER
		val vertexOffset = 8L
		val vertexLayout = WGPUVertexState.LAYOUT
		val primitiveOffset = 0L
		val primitiveLayout = WGPUPrimitiveState.LAYOUT
		val depthStencilOffset = 0L
		val depthStencilLayout = C_POINTER
		val multisampleOffset = 8L
		val multisampleLayout = WGPUMultisampleState.LAYOUT
		val fragmentOffset = 0L
		val fragmentLayout = C_POINTER
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
			return allocator.allocate(32L)
				.let(::WGPURequestAdapterOptions)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("compatibleSurface"),
			C_INT.withName("powerPreference"),
			C_INT.withName("backendType"),
			C_INT.withName("forceFallbackAdapter"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPURequestAdapterOptions")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val compatibleSurfaceOffset = 8L
		val compatibleSurfaceLayout = C_POINTER
		val powerPreferenceOffset = 8L
		val powerPreferenceLayout = C_INT
		val backendTypeOffset = 4L
		val backendTypeLayout = C_INT
		val forceFallbackAdapterOffset = 4L
		val forceFallbackAdapterLayout = C_INT
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
			return allocator.allocate(152L)
				.let(::WGPURequiredLimits)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPULimits.LAYOUT.withName("limits"),
		).withName("WGPURequiredLimits")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val limitsOffset = 8L
		val limitsLayout = WGPULimits.LAYOUT
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
			return allocator.allocate(68L)
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
			MemoryLayout.paddingLayout(6)
		).withName("WGPUSamplerDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val addressModeUOffset = 0L
		val addressModeULayout = C_INT
		val addressModeVOffset = 4L
		val addressModeVLayout = C_INT
		val addressModeWOffset = 4L
		val addressModeWLayout = C_INT
		val magFilterOffset = 4L
		val magFilterLayout = C_INT
		val minFilterOffset = 4L
		val minFilterLayout = C_INT
		val mipmapFilterOffset = 4L
		val mipmapFilterLayout = C_INT
		val lodMinClampOffset = 4L
		val lodMinClampLayout = C_FLOAT
		val lodMaxClampOffset = 4L
		val lodMaxClampLayout = C_FLOAT
		val compareOffset = 4L
		val compareLayout = C_INT
		val maxAnisotropyOffset = 4L
		val maxAnisotropyLayout = C_SHORT
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
			return allocator.allocate(24L)
				.let(::WGPUShaderModuleDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUShaderModuleDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUShaderSourceSPIRV(actual override val handler: NativeAddress) : CStructure {
	actual val chain: WGPUChainedStruct
		get() = get(chainLayout, chainOffset).let(::WGPUChainedStruct)
	actual var codeSize: UInt
		get() = getUInt(codeSizeOffset)
		set(newValue) = set(codeSizeOffset, newValue)
	actual var code: NativeAddress?
		get() = get(codeLayout, codeOffset)
		set(newValue) = set(codeLayout, codeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV {
			return allocator.allocate(32L)
				.let(::WGPUShaderSourceSPIRV)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_INT.withName("codeSize"),
			C_POINTER.withName("code"),
		).withName("WGPUShaderSourceSPIRV")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val codeSizeOffset = 0L
		val codeSizeLayout = C_INT
		val codeOffset = 4L
		val codeLayout = C_POINTER
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
			return allocator.allocate(32L)
				.let(::WGPUShaderSourceWGSL)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			WGPUStringView.LAYOUT.withName("code"),
		).withName("WGPUShaderSourceWGSL")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val codeOffset = 0L
		val codeLayout = WGPUStringView.LAYOUT
	}
}
@JvmInline
actual value class WGPUSupportedFeatures(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUSupportedFeatures)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			C_LONG.withName("featureCount"),
			C_POINTER.withName("features"),
		).withName("WGPUSupportedFeatures")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val featureCountOffset = 8L
		val featureCountLayout = C_LONG
		val featuresOffset = 8L
		val featuresLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUSupportedLimits(actual override val handler: NativeAddress) : CStructure {
	actual var nextInChain: WGPUChainedStructOut?
		get() = get(nextInChainLayout, nextInChainOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextInChainLayout, nextInChainOffset, newValue?.handler)
	actual val limits: WGPULimits
		get() = get(limitsLayout, limitsOffset).let(::WGPULimits)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits {
			return allocator.allocate(152L)
				.let(::WGPUSupportedLimits)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPULimits.LAYOUT.withName("limits"),
		).withName("WGPUSupportedLimits")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val limitsOffset = 8L
		val limitsLayout = WGPULimits.LAYOUT
	}
}
@JvmInline
actual value class WGPUSurfaceCapabilities(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(64L)
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
		val usagesOffset = 8L
		val usagesLayout = C_LONG
		val formatCountOffset = 8L
		val formatCountLayout = C_LONG
		val formatsOffset = 8L
		val formatsLayout = C_POINTER
		val presentModeCountOffset = 8L
		val presentModeCountLayout = C_LONG
		val presentModesOffset = 8L
		val presentModesLayout = C_POINTER
		val alphaModeCountOffset = 8L
		val alphaModeCountLayout = C_LONG
		val alphaModesOffset = 8L
		val alphaModesLayout = C_POINTER
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
			return allocator.allocate(64L)
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
		val deviceOffset = 8L
		val deviceLayout = C_POINTER
		val formatOffset = 8L
		val formatLayout = C_INT
		val usageOffset = 4L
		val usageLayout = C_LONG
		val widthOffset = 8L
		val widthLayout = C_INT
		val heightOffset = 4L
		val heightLayout = C_INT
		val viewFormatCountOffset = 4L
		val viewFormatCountLayout = C_LONG
		val viewFormatsOffset = 8L
		val viewFormatsLayout = C_POINTER
		val alphaModeOffset = 8L
		val alphaModeLayout = C_INT
		val presentModeOffset = 4L
		val presentModeLayout = C_INT
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
			return allocator.allocate(24L)
				.let(::WGPUSurfaceDescriptor)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("nextInChain"),
			WGPUStringView.LAYOUT.withName("label"),
		).withName("WGPUSurfaceDescriptor")

		val nextInChainOffset = 0L
		val nextInChainLayout = C_POINTER
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
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
			return allocator.allocate(24L)
				.let(::WGPUSurfaceSourceAndroidNativeWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("window"),
		).withName("WGPUSurfaceSourceAndroidNativeWindow")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val windowOffset = 0L
		val windowLayout = C_POINTER
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
			return allocator.allocate(24L)
				.let(::WGPUSurfaceSourceMetalLayer)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("layer"),
		).withName("WGPUSurfaceSourceMetalLayer")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val layerOffset = 0L
		val layerLayout = C_POINTER
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
			return allocator.allocate(32L)
				.let(::WGPUSurfaceSourceWaylandSurface)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("display"),
			C_POINTER.withName("surface"),
		).withName("WGPUSurfaceSourceWaylandSurface")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val displayOffset = 0L
		val displayLayout = C_POINTER
		val surfaceOffset = 8L
		val surfaceLayout = C_POINTER
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
			return allocator.allocate(32L)
				.let(::WGPUSurfaceSourceWindowsHWND)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("hinstance"),
			C_POINTER.withName("hwnd"),
		).withName("WGPUSurfaceSourceWindowsHWND")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val hinstanceOffset = 0L
		val hinstanceLayout = C_POINTER
		val hwndOffset = 8L
		val hwndLayout = C_POINTER
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
		get() = getUInt(windowOffset)
		set(newValue) = set(windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
			return allocator.allocate(32L)
				.let(::WGPUSurfaceSourceXCBWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("connection"),
			C_INT.withName("window"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUSurfaceSourceXCBWindow")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val connectionOffset = 0L
		val connectionLayout = C_POINTER
		val windowOffset = 8L
		val windowLayout = C_INT
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
		get() = getULong(windowOffset)
		set(newValue) = set(windowOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
			return allocator.allocate(32L)
				.let(::WGPUSurfaceSourceXlibWindow)
		}
		internal val LAYOUT = structLayout(
			WGPUChainedStruct.LAYOUT.withName("chain"),
			C_POINTER.withName("display"),
			C_LONG.withName("window"),
		).withName("WGPUSurfaceSourceXlibWindow")

		val chainOffset = 0L
		val chainLayout = WGPUChainedStruct.LAYOUT
		val displayOffset = 0L
		val displayLayout = C_POINTER
		val windowOffset = 8L
		val windowLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUSurfaceTexture(actual override val handler: NativeAddress) : CStructure {
	actual var texture: WGPUTexture?
		get() = get(textureLayout, textureOffset).let(::WGPUTexture)
		set(newValue) = set(textureLayout, textureOffset, newValue?.handler)
	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = getUInt(statusOffset)
		set(newValue) = set(statusOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
			return allocator.allocate(16L)
				.let(::WGPUSurfaceTexture)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("status"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUSurfaceTexture")

		val textureOffset = 0L
		val textureLayout = C_POINTER
		val statusOffset = 8L
		val statusLayout = C_INT
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
		get() = getULong(usageOffset)
		set(newValue) = set(usageOffset, newValue)
	actual var dimension: WGPUTextureDimension
		get() = getUInt(dimensionOffset)
		set(newValue) = set(dimensionOffset, newValue)
	actual val size: WGPUExtent3D
		get() = get(sizeLayout, sizeOffset).let(::WGPUExtent3D)
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
			return allocator.allocate(80L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val usageOffset = 0L
		val usageLayout = C_LONG
		val dimensionOffset = 8L
		val dimensionLayout = C_INT
		val sizeOffset = 4L
		val sizeLayout = WGPUExtent3D.LAYOUT
		val formatOffset = 0L
		val formatLayout = C_INT
		val mipLevelCountOffset = 4L
		val mipLevelCountLayout = C_INT
		val sampleCountOffset = 4L
		val sampleCountLayout = C_INT
		val viewFormatCountOffset = 4L
		val viewFormatCountLayout = C_LONG
		val viewFormatsOffset = 8L
		val viewFormatsLayout = C_POINTER
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
			return allocator.allocate(64L)
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
		val labelOffset = 8L
		val labelLayout = WGPUStringView.LAYOUT
		val formatOffset = 0L
		val formatLayout = C_INT
		val dimensionOffset = 4L
		val dimensionLayout = C_INT
		val baseMipLevelOffset = 4L
		val baseMipLevelLayout = C_INT
		val mipLevelCountOffset = 4L
		val mipLevelCountLayout = C_INT
		val baseArrayLayerOffset = 4L
		val baseArrayLayerLayout = C_INT
		val arrayLayerCountOffset = 4L
		val arrayLayerCountLayout = C_INT
		val aspectOffset = 4L
		val aspectLayout = C_INT
		val usageOffset = 4L
		val usageLayout = C_LONG
	}
}
@JvmInline
actual value class WGPUVertexAttribute(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(24L)
				.let(::WGPUVertexAttribute)
		}
		internal val LAYOUT = structLayout(
			C_INT.withName("format"),
			C_LONG.withName("offset"),
			C_INT.withName("shaderLocation"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUVertexAttribute")

		val formatOffset = 0L
		val formatLayout = C_INT
		val offsetOffset = 4L
		val offsetLayout = C_LONG
		val shaderLocationOffset = 8L
		val shaderLocationLayout = C_INT
	}
}
@JvmInline
actual value class WGPUVertexBufferLayout(actual override val handler: NativeAddress) : CStructure {
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
			return allocator.allocate(32L)
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
		val stepModeOffset = 8L
		val stepModeLayout = C_INT
		val attributeCountOffset = 4L
		val attributeCountLayout = C_LONG
		val attributesOffset = 8L
		val attributesLayout = C_POINTER
	}
}
@JvmInline
actual value class WGPUChainedStructOut(actual override val handler: NativeAddress) : CStructure {
	actual var next: WGPUChainedStructOut?
		get() = get(nextLayout, nextOffset).let(::WGPUChainedStructOut)
		set(newValue) = set(nextLayout, nextOffset, newValue?.handler)
	actual var sType: WGPUSType
		get() = getUInt(sTypeOffset)
		set(newValue) = set(sTypeOffset, newValue)

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut {
			return allocator.allocate(16L)
				.let(::WGPUChainedStructOut)
		}
		internal val LAYOUT = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType"),
			MemoryLayout.paddingLayout(4)
		).withName("WGPUChainedStructOut")

		val nextOffset = 0L
		val nextLayout = C_POINTER
		val sTypeOffset = 8L
		val sTypeLayout = C_INT
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
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
			return allocator.allocate(32L)
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
		val callbackOffset = 8L
		val callbackLayout = C_POINTER
		val userdata1Offset = 8L
		val userdata1Layout = C_POINTER
		val userdata2Offset = 8L
		val userdata2Layout = C_POINTER
	}
}
