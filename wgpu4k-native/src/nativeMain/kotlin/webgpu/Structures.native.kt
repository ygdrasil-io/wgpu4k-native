// This file has been generated DO NOT EDIT !!!
@file:OptIn(ExperimentalForeignApi::class)
package webgpu
    
import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.CString
import ffi.toCString
import ffi.ArrayHolder
import ffi.MemoryAllocator
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKString
import kotlinx.cinterop.toLong
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.CValue
import kotlinx.cinterop.cValue

actual interface WGPUStringView {
	value class ByReference(override val handler: NativeAddress) : WGPUStringView {
		override var data: CString?
			get() = handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.data?.toCString()
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.let { it.data = newValue?.handler?.toCPointer() } } 

		override var length: ULong
			get() = handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.length ?: error("pointer of WGPUStringView is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.let { it.length = newValue } } 

	}

	actual var data: CString?
	actual var length: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUStringView {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStringView>())
				.let { WGPUStringView(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUStringView) -> Unit): ArrayHolder<WGPUStringView> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStringView>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUStringView>())
							.let { WGPUStringView(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUStringView> {
		return cValue<webgpu.native.WGPUStringView> {
			data = this@WGPUStringView.data?.handler?.toCPointer()
			length = this@WGPUStringView.length
		}
	}
}

fun webgpu.native.WGPUStringView.adapt(structure: WGPUStringView) {
	data = structure.data?.handler?.toCPointer()
	length = structure.length
}

actual interface WGPUAdapterInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUAdapterInfo {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val vendor: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendor?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

		override val architecture: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.architecture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

		override val device: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.device?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

		override val description: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.description?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

		override var backendType: WGPUBackendType
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.backendType ?: error("pointer of WGPUAdapterInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.backendType = newValue } } 

		override var adapterType: WGPUAdapterType
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.adapterType ?: error("pointer of WGPUAdapterInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.adapterType = newValue } } 

		override var vendorID: UInt
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendorID ?: error("pointer of WGPUAdapterInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.vendorID = newValue } } 

		override var deviceID: UInt
			get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.deviceID ?: error("pointer of WGPUAdapterInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.deviceID = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val vendor: WGPUStringView
	actual val architecture: WGPUStringView
	actual val device: WGPUStringView
	actual val description: WGPUStringView
	actual var backendType: WGPUBackendType
	actual var adapterType: WGPUAdapterType
	actual var vendorID: UInt
	actual var deviceID: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUAdapterInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUAdapterInfo>())
				.let { WGPUAdapterInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUAdapterInfo) -> Unit): ArrayHolder<WGPUAdapterInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUAdapterInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUAdapterInfo>())
							.let { WGPUAdapterInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUAdapterInfo> {
		return cValue<webgpu.native.WGPUAdapterInfo> {
			vendor.adapt(this@WGPUAdapterInfo.vendor)
			architecture.adapt(this@WGPUAdapterInfo.architecture)
			device.adapt(this@WGPUAdapterInfo.device)
			description.adapt(this@WGPUAdapterInfo.description)
			nextInChain = this@WGPUAdapterInfo.nextInChain?.toCPointer()
			backendType = this@WGPUAdapterInfo.backendType
			adapterType = this@WGPUAdapterInfo.adapterType
			vendorID = this@WGPUAdapterInfo.vendorID
			deviceID = this@WGPUAdapterInfo.deviceID
		}
	}
}

fun webgpu.native.WGPUAdapterInfo.adapt(structure: WGPUAdapterInfo) {
	vendor.adapt(structure.vendor)
	architecture.adapt(structure.architecture)
	device.adapt(structure.device)
	description.adapt(structure.description)
	nextInChain = structure.nextInChain?.toCPointer()
	backendType = structure.backendType
	adapterType = structure.adapterType
	vendorID = structure.vendorID
	deviceID = structure.deviceID
}

actual interface WGPUBindGroupDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUBindGroupDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBindGroupDescriptor is null")

		override var layout: WGPUBindGroupLayout?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUBindGroupLayout(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

		override var entryCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.entryCount ?: error("pointer of WGPUBindGroupDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.entryCount = newValue } } 

		override var entries: ArrayHolder<WGPUBindGroupEntry>?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.entries?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupEntry>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.entries = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var layout: WGPUBindGroupLayout?
	actual var entryCount: ULong
	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBindGroupDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupDescriptor>())
				.let { WGPUBindGroupDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBindGroupDescriptor) -> Unit): ArrayHolder<WGPUBindGroupDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBindGroupDescriptor>())
							.let { WGPUBindGroupDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupDescriptor> {
		return cValue<webgpu.native.WGPUBindGroupDescriptor> {
			label.adapt(this@WGPUBindGroupDescriptor.label)
			nextInChain = this@WGPUBindGroupDescriptor.nextInChain?.toCPointer()
			layout = this@WGPUBindGroupDescriptor.layout?.handler?.toCPointer()
			entryCount = this@WGPUBindGroupDescriptor.entryCount
			entries = this@WGPUBindGroupDescriptor.entries?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBindGroupDescriptor.adapt(structure: WGPUBindGroupDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	layout = structure.layout?.handler?.toCPointer()
	entryCount = structure.entryCount
	entries = structure.entries?.handler?.toCPointer()
}

actual interface WGPUBindGroupEntry {
	value class ByReference(override val handler: NativeAddress) : WGPUBindGroupEntry {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var binding: UInt
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.binding ?: error("pointer of WGPUBindGroupEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.binding = newValue } } 

		override var buffer: WGPUBuffer?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.buffer = newValue?.handler?.toCPointer() } } 

		override var offset: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.offset ?: error("pointer of WGPUBindGroupEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.offset = newValue } } 

		override var size: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.size ?: error("pointer of WGPUBindGroupEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.size = newValue } } 

		override var sampler: WGPUSampler?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.sampler?.toLong()?.takeIf {it != 0L}?.let { WGPUSampler(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.sampler = newValue?.handler?.toCPointer() } } 

		override var textureView: WGPUTextureView?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.textureView?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.textureView = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var binding: UInt
	actual var buffer: WGPUBuffer?
	actual var offset: ULong
	actual var size: ULong
	actual var sampler: WGPUSampler?
	actual var textureView: WGPUTextureView?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBindGroupEntry {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupEntry>())
				.let { WGPUBindGroupEntry(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBindGroupEntry) -> Unit): ArrayHolder<WGPUBindGroupEntry> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupEntry>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBindGroupEntry>())
							.let { WGPUBindGroupEntry(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupEntry> {
		return cValue<webgpu.native.WGPUBindGroupEntry> {
			nextInChain = this@WGPUBindGroupEntry.nextInChain?.toCPointer()
			binding = this@WGPUBindGroupEntry.binding
			buffer = this@WGPUBindGroupEntry.buffer?.handler?.toCPointer()
			offset = this@WGPUBindGroupEntry.offset
			size = this@WGPUBindGroupEntry.size
			sampler = this@WGPUBindGroupEntry.sampler?.handler?.toCPointer()
			textureView = this@WGPUBindGroupEntry.textureView?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBindGroupEntry.adapt(structure: WGPUBindGroupEntry) {
	nextInChain = structure.nextInChain?.toCPointer()
	binding = structure.binding
	buffer = structure.buffer?.handler?.toCPointer()
	offset = structure.offset
	size = structure.size
	sampler = structure.sampler?.handler?.toCPointer()
	textureView = structure.textureView?.handler?.toCPointer()
}

actual interface WGPUBindGroupLayoutDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBindGroupLayoutDescriptor is null")

		override var entryCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.entryCount ?: error("pointer of WGPUBindGroupLayoutDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.entryCount = newValue } } 

		override var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.entries?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupLayoutEntry>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.entries = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var entryCount: ULong
	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutDescriptor>())
				.let { WGPUBindGroupLayoutDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBindGroupLayoutDescriptor) -> Unit): ArrayHolder<WGPUBindGroupLayoutDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBindGroupLayoutDescriptor>())
							.let { WGPUBindGroupLayoutDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupLayoutDescriptor> {
		return cValue<webgpu.native.WGPUBindGroupLayoutDescriptor> {
			label.adapt(this@WGPUBindGroupLayoutDescriptor.label)
			nextInChain = this@WGPUBindGroupLayoutDescriptor.nextInChain?.toCPointer()
			entryCount = this@WGPUBindGroupLayoutDescriptor.entryCount
			entries = this@WGPUBindGroupLayoutDescriptor.entries?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBindGroupLayoutDescriptor.adapt(structure: WGPUBindGroupLayoutDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	entryCount = structure.entryCount
	entries = structure.entries?.handler?.toCPointer()
}

actual interface WGPUBufferBindingLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUBufferBindingLayout {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var type: WGPUBufferBindingType
			get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.type ?: error("pointer of WGPUBufferBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.type = newValue } } 

		override var hasDynamicOffset: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.hasDynamicOffset?.toBoolean() ?: error("pointer of WGPUBufferBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.hasDynamicOffset = newValue.toUInt() } } 

		override var minBindingSize: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.minBindingSize ?: error("pointer of WGPUBufferBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.minBindingSize = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var type: WGPUBufferBindingType
	actual var hasDynamicOffset: Boolean
	actual var minBindingSize: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBufferBindingLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferBindingLayout>())
				.let { WGPUBufferBindingLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBufferBindingLayout) -> Unit): ArrayHolder<WGPUBufferBindingLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferBindingLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBufferBindingLayout>())
							.let { WGPUBufferBindingLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBufferBindingLayout> {
		return cValue<webgpu.native.WGPUBufferBindingLayout> {
			nextInChain = this@WGPUBufferBindingLayout.nextInChain?.toCPointer()
			type = this@WGPUBufferBindingLayout.type
			hasDynamicOffset = this@WGPUBufferBindingLayout.hasDynamicOffset.toUInt()
			minBindingSize = this@WGPUBufferBindingLayout.minBindingSize
		}
	}
}

fun webgpu.native.WGPUBufferBindingLayout.adapt(structure: WGPUBufferBindingLayout) {
	nextInChain = structure.nextInChain?.toCPointer()
	type = structure.type
	hasDynamicOffset = structure.hasDynamicOffset.toUInt()
	minBindingSize = structure.minBindingSize
}

actual interface WGPUSamplerBindingLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUSamplerBindingLayout {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var type: WGPUSamplerBindingType
			get() = handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.type ?: error("pointer of WGPUSamplerBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.let { it.type = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var type: WGPUSamplerBindingType
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSamplerBindingLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerBindingLayout>())
				.let { WGPUSamplerBindingLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSamplerBindingLayout) -> Unit): ArrayHolder<WGPUSamplerBindingLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerBindingLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSamplerBindingLayout>())
							.let { WGPUSamplerBindingLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSamplerBindingLayout> {
		return cValue<webgpu.native.WGPUSamplerBindingLayout> {
			nextInChain = this@WGPUSamplerBindingLayout.nextInChain?.toCPointer()
			type = this@WGPUSamplerBindingLayout.type
		}
	}
}

fun webgpu.native.WGPUSamplerBindingLayout.adapt(structure: WGPUSamplerBindingLayout) {
	nextInChain = structure.nextInChain?.toCPointer()
	type = structure.type
}

actual interface WGPUTextureBindingLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUTextureBindingLayout {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var sampleType: WGPUTextureSampleType
			get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.sampleType ?: error("pointer of WGPUTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.sampleType = newValue } } 

		override var viewDimension: WGPUTextureViewDimension
			get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.viewDimension ?: error("pointer of WGPUTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.viewDimension = newValue } } 

		override var multisampled: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.multisampled?.toBoolean() ?: error("pointer of WGPUTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.multisampled = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var sampleType: WGPUTextureSampleType
	actual var viewDimension: WGPUTextureViewDimension
	actual var multisampled: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUTextureBindingLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureBindingLayout>())
				.let { WGPUTextureBindingLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUTextureBindingLayout) -> Unit): ArrayHolder<WGPUTextureBindingLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureBindingLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUTextureBindingLayout>())
							.let { WGPUTextureBindingLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureBindingLayout> {
		return cValue<webgpu.native.WGPUTextureBindingLayout> {
			nextInChain = this@WGPUTextureBindingLayout.nextInChain?.toCPointer()
			sampleType = this@WGPUTextureBindingLayout.sampleType
			viewDimension = this@WGPUTextureBindingLayout.viewDimension
			multisampled = this@WGPUTextureBindingLayout.multisampled.toUInt()
		}
	}
}

fun webgpu.native.WGPUTextureBindingLayout.adapt(structure: WGPUTextureBindingLayout) {
	nextInChain = structure.nextInChain?.toCPointer()
	sampleType = structure.sampleType
	viewDimension = structure.viewDimension
	multisampled = structure.multisampled.toUInt()
}

actual interface WGPUStorageTextureBindingLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUStorageTextureBindingLayout {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var access: WGPUStorageTextureAccess
			get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.access ?: error("pointer of WGPUStorageTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.access = newValue } } 

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.format ?: error("pointer of WGPUStorageTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.format = newValue } } 

		override var viewDimension: WGPUTextureViewDimension
			get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.viewDimension ?: error("pointer of WGPUStorageTextureBindingLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.viewDimension = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var access: WGPUStorageTextureAccess
	actual var format: WGPUTextureFormat
	actual var viewDimension: WGPUTextureViewDimension
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUStorageTextureBindingLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStorageTextureBindingLayout>())
				.let { WGPUStorageTextureBindingLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUStorageTextureBindingLayout) -> Unit): ArrayHolder<WGPUStorageTextureBindingLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStorageTextureBindingLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUStorageTextureBindingLayout>())
							.let { WGPUStorageTextureBindingLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUStorageTextureBindingLayout> {
		return cValue<webgpu.native.WGPUStorageTextureBindingLayout> {
			nextInChain = this@WGPUStorageTextureBindingLayout.nextInChain?.toCPointer()
			access = this@WGPUStorageTextureBindingLayout.access
			format = this@WGPUStorageTextureBindingLayout.format
			viewDimension = this@WGPUStorageTextureBindingLayout.viewDimension
		}
	}
}

fun webgpu.native.WGPUStorageTextureBindingLayout.adapt(structure: WGPUStorageTextureBindingLayout) {
	nextInChain = structure.nextInChain?.toCPointer()
	access = structure.access
	format = structure.format
	viewDimension = structure.viewDimension
}

actual interface WGPUBindGroupLayoutEntry {
	value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutEntry {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var binding: UInt
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.binding ?: error("pointer of WGPUBindGroupLayoutEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.binding = newValue } } 

		override var visibility: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.visibility ?: error("pointer of WGPUBindGroupLayoutEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.visibility = newValue } } 

		override val buffer: WGPUBufferBindingLayout
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.buffer?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBufferBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

		override val sampler: WGPUSamplerBindingLayout
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.sampler?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUSamplerBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

		override val texture: WGPUTextureBindingLayout
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.texture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

		override val storageTexture: WGPUStorageTextureBindingLayout
			get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.storageTexture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStorageTextureBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

	}

	actual var nextInChain: NativeAddress?
	actual var binding: UInt
	actual var visibility: ULong
	actual val buffer: WGPUBufferBindingLayout
	actual val sampler: WGPUSamplerBindingLayout
	actual val texture: WGPUTextureBindingLayout
	actual val storageTexture: WGPUStorageTextureBindingLayout
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntry {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutEntry>())
				.let { WGPUBindGroupLayoutEntry(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBindGroupLayoutEntry) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntry> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutEntry>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBindGroupLayoutEntry>())
							.let { WGPUBindGroupLayoutEntry(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupLayoutEntry> {
		return cValue<webgpu.native.WGPUBindGroupLayoutEntry> {
			buffer.adapt(this@WGPUBindGroupLayoutEntry.buffer)
			sampler.adapt(this@WGPUBindGroupLayoutEntry.sampler)
			texture.adapt(this@WGPUBindGroupLayoutEntry.texture)
			storageTexture.adapt(this@WGPUBindGroupLayoutEntry.storageTexture)
			nextInChain = this@WGPUBindGroupLayoutEntry.nextInChain?.toCPointer()
			binding = this@WGPUBindGroupLayoutEntry.binding
			visibility = this@WGPUBindGroupLayoutEntry.visibility
		}
	}
}

fun webgpu.native.WGPUBindGroupLayoutEntry.adapt(structure: WGPUBindGroupLayoutEntry) {
	buffer.adapt(structure.buffer)
	sampler.adapt(structure.sampler)
	texture.adapt(structure.texture)
	storageTexture.adapt(structure.storageTexture)
	nextInChain = structure.nextInChain?.toCPointer()
	binding = structure.binding
	visibility = structure.visibility
}

actual interface WGPUBlendComponent {
	value class ByReference(override val handler: NativeAddress) : WGPUBlendComponent {
		override var operation: WGPUBlendOperation
			get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.operation ?: error("pointer of WGPUBlendComponent is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.operation = newValue } } 

		override var srcFactor: WGPUBlendFactor
			get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.srcFactor ?: error("pointer of WGPUBlendComponent is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.srcFactor = newValue } } 

		override var dstFactor: WGPUBlendFactor
			get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.dstFactor ?: error("pointer of WGPUBlendComponent is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.dstFactor = newValue } } 

	}

	actual var operation: WGPUBlendOperation
	actual var srcFactor: WGPUBlendFactor
	actual var dstFactor: WGPUBlendFactor
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBlendComponent {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendComponent>())
				.let { WGPUBlendComponent(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBlendComponent) -> Unit): ArrayHolder<WGPUBlendComponent> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendComponent>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBlendComponent>())
							.let { WGPUBlendComponent(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBlendComponent> {
		return cValue<webgpu.native.WGPUBlendComponent> {
			operation = this@WGPUBlendComponent.operation
			srcFactor = this@WGPUBlendComponent.srcFactor
			dstFactor = this@WGPUBlendComponent.dstFactor
		}
	}
}

fun webgpu.native.WGPUBlendComponent.adapt(structure: WGPUBlendComponent) {
	operation = structure.operation
	srcFactor = structure.srcFactor
	dstFactor = structure.dstFactor
}

actual interface WGPUBlendState {
	value class ByReference(override val handler: NativeAddress) : WGPUBlendState {
		override val color: WGPUBlendComponent
			get() = handler.toCPointer<webgpu.native.WGPUBlendState>()?.pointed?.color?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendComponent(it) } ?: error("pointer of WGPUBlendState is null")

		override val alpha: WGPUBlendComponent
			get() = handler.toCPointer<webgpu.native.WGPUBlendState>()?.pointed?.alpha?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendComponent(it) } ?: error("pointer of WGPUBlendState is null")

	}

	actual val color: WGPUBlendComponent
	actual val alpha: WGPUBlendComponent
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBlendState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBlendState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendState>())
				.let { WGPUBlendState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBlendState) -> Unit): ArrayHolder<WGPUBlendState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBlendState>())
							.let { WGPUBlendState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBlendState> {
		return cValue<webgpu.native.WGPUBlendState> {
			color.adapt(this@WGPUBlendState.color)
			alpha.adapt(this@WGPUBlendState.alpha)
		}
	}
}

fun webgpu.native.WGPUBlendState.adapt(structure: WGPUBlendState) {
	color.adapt(structure.color)
	alpha.adapt(structure.alpha)
}

actual interface WGPUBufferDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUBufferDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBufferDescriptor is null")

		override var usage: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.usage ?: error("pointer of WGPUBufferDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.usage = newValue } } 

		override var size: ULong
			get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.size ?: error("pointer of WGPUBufferDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.size = newValue } } 

		override var mappedAtCreation: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.mappedAtCreation?.toBoolean() ?: error("pointer of WGPUBufferDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.mappedAtCreation = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var usage: ULong
	actual var size: ULong
	actual var mappedAtCreation: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBufferDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferDescriptor>())
				.let { WGPUBufferDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBufferDescriptor) -> Unit): ArrayHolder<WGPUBufferDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBufferDescriptor>())
							.let { WGPUBufferDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBufferDescriptor> {
		return cValue<webgpu.native.WGPUBufferDescriptor> {
			label.adapt(this@WGPUBufferDescriptor.label)
			nextInChain = this@WGPUBufferDescriptor.nextInChain?.toCPointer()
			usage = this@WGPUBufferDescriptor.usage
			size = this@WGPUBufferDescriptor.size
			mappedAtCreation = this@WGPUBufferDescriptor.mappedAtCreation.toUInt()
		}
	}
}

fun webgpu.native.WGPUBufferDescriptor.adapt(structure: WGPUBufferDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	usage = structure.usage
	size = structure.size
	mappedAtCreation = structure.mappedAtCreation.toUInt()
}

actual interface WGPUColor {
	value class ByReference(override val handler: NativeAddress) : WGPUColor {
		override var r: Double
			get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.r ?: error("pointer of WGPUColor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.r = newValue } } 

		override var g: Double
			get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.g ?: error("pointer of WGPUColor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.g = newValue } } 

		override var b: Double
			get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.b ?: error("pointer of WGPUColor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.b = newValue } } 

		override var a: Double
			get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.a ?: error("pointer of WGPUColor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.a = newValue } } 

	}

	actual var r: Double
	actual var g: Double
	actual var b: Double
	actual var a: Double
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUColor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUColor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColor>())
				.let { WGPUColor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUColor) -> Unit): ArrayHolder<WGPUColor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUColor>())
							.let { WGPUColor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUColor> {
		return cValue<webgpu.native.WGPUColor> {
			r = this@WGPUColor.r
			g = this@WGPUColor.g
			b = this@WGPUColor.b
			a = this@WGPUColor.a
		}
	}
}

fun webgpu.native.WGPUColor.adapt(structure: WGPUColor) {
	r = structure.r
	g = structure.g
	b = structure.b
	a = structure.a
}

actual interface WGPUColorTargetState {
	value class ByReference(override val handler: NativeAddress) : WGPUColorTargetState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.format ?: error("pointer of WGPUColorTargetState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.format = newValue } } 

		override var blend: WGPUBlendState?
			get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.blend?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendState(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.blend = newValue?.handler?.toCPointer() } } 

		override var writeMask: ULong
			get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.writeMask ?: error("pointer of WGPUColorTargetState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.writeMask = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var format: WGPUTextureFormat
	actual var blend: WGPUBlendState?
	actual var writeMask: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUColorTargetState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColorTargetState>())
				.let { WGPUColorTargetState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUColorTargetState) -> Unit): ArrayHolder<WGPUColorTargetState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColorTargetState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUColorTargetState>())
							.let { WGPUColorTargetState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUColorTargetState> {
		return cValue<webgpu.native.WGPUColorTargetState> {
			nextInChain = this@WGPUColorTargetState.nextInChain?.toCPointer()
			format = this@WGPUColorTargetState.format
			blend = this@WGPUColorTargetState.blend?.handler?.toCPointer()
			writeMask = this@WGPUColorTargetState.writeMask
		}
	}
}

fun webgpu.native.WGPUColorTargetState.adapt(structure: WGPUColorTargetState) {
	nextInChain = structure.nextInChain?.toCPointer()
	format = structure.format
	blend = structure.blend?.handler?.toCPointer()
	writeMask = structure.writeMask
}

actual interface WGPUCommandBufferDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUCommandBufferDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCommandBufferDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCommandBufferDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandBufferDescriptor>())
				.let { WGPUCommandBufferDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCommandBufferDescriptor) -> Unit): ArrayHolder<WGPUCommandBufferDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandBufferDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCommandBufferDescriptor>())
							.let { WGPUCommandBufferDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCommandBufferDescriptor> {
		return cValue<webgpu.native.WGPUCommandBufferDescriptor> {
			label.adapt(this@WGPUCommandBufferDescriptor.label)
			nextInChain = this@WGPUCommandBufferDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCommandBufferDescriptor.adapt(structure: WGPUCommandBufferDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUCommandEncoderDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUCommandEncoderDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCommandEncoderDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCommandEncoderDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandEncoderDescriptor>())
				.let { WGPUCommandEncoderDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCommandEncoderDescriptor) -> Unit): ArrayHolder<WGPUCommandEncoderDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandEncoderDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCommandEncoderDescriptor>())
							.let { WGPUCommandEncoderDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCommandEncoderDescriptor> {
		return cValue<webgpu.native.WGPUCommandEncoderDescriptor> {
			label.adapt(this@WGPUCommandEncoderDescriptor.label)
			nextInChain = this@WGPUCommandEncoderDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCommandEncoderDescriptor.adapt(structure: WGPUCommandEncoderDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUCompilationInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUCompilationInfo {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var messageCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.messageCount ?: error("pointer of WGPUCompilationInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.let { it.messageCount = newValue } } 

		override var messages: ArrayHolder<WGPUCompilationMessage>?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.messages?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUCompilationMessage>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.let { it.messages = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var messageCount: ULong
	actual var messages: ArrayHolder<WGPUCompilationMessage>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCompilationInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfo>())
				.let { WGPUCompilationInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCompilationInfo) -> Unit): ArrayHolder<WGPUCompilationInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCompilationInfo>())
							.let { WGPUCompilationInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCompilationInfo> {
		return cValue<webgpu.native.WGPUCompilationInfo> {
			nextInChain = this@WGPUCompilationInfo.nextInChain?.toCPointer()
			messageCount = this@WGPUCompilationInfo.messageCount
			messages = this@WGPUCompilationInfo.messages?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCompilationInfo.adapt(structure: WGPUCompilationInfo) {
	nextInChain = structure.nextInChain?.toCPointer()
	messageCount = structure.messageCount
	messages = structure.messages?.handler?.toCPointer()
}

actual interface WGPUCompilationMessage {
	value class ByReference(override val handler: NativeAddress) : WGPUCompilationMessage {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val message: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.message?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCompilationMessage is null")

		override var type: WGPUCompilationMessageType
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.type ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.type = newValue } } 

		override var lineNum: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.lineNum ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.lineNum = newValue } } 

		override var linePos: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.linePos ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.linePos = newValue } } 

		override var offset: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.offset ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.offset = newValue } } 

		override var length: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.length ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.length = newValue } } 

		override var utf16LinePos: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16LinePos ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16LinePos = newValue } } 

		override var utf16Offset: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16Offset ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16Offset = newValue } } 

		override var utf16Length: ULong
			get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16Length ?: error("pointer of WGPUCompilationMessage is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16Length = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val message: WGPUStringView
	actual var type: WGPUCompilationMessageType
	actual var lineNum: ULong
	actual var linePos: ULong
	actual var offset: ULong
	actual var length: ULong
	actual var utf16LinePos: ULong
	actual var utf16Offset: ULong
	actual var utf16Length: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCompilationMessage {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationMessage>())
				.let { WGPUCompilationMessage(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCompilationMessage) -> Unit): ArrayHolder<WGPUCompilationMessage> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationMessage>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCompilationMessage>())
							.let { WGPUCompilationMessage(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCompilationMessage> {
		return cValue<webgpu.native.WGPUCompilationMessage> {
			message.adapt(this@WGPUCompilationMessage.message)
			nextInChain = this@WGPUCompilationMessage.nextInChain?.toCPointer()
			type = this@WGPUCompilationMessage.type
			lineNum = this@WGPUCompilationMessage.lineNum
			linePos = this@WGPUCompilationMessage.linePos
			offset = this@WGPUCompilationMessage.offset
			length = this@WGPUCompilationMessage.length
			utf16LinePos = this@WGPUCompilationMessage.utf16LinePos
			utf16Offset = this@WGPUCompilationMessage.utf16Offset
			utf16Length = this@WGPUCompilationMessage.utf16Length
		}
	}
}

fun webgpu.native.WGPUCompilationMessage.adapt(structure: WGPUCompilationMessage) {
	message.adapt(structure.message)
	nextInChain = structure.nextInChain?.toCPointer()
	type = structure.type
	lineNum = structure.lineNum
	linePos = structure.linePos
	offset = structure.offset
	length = structure.length
	utf16LinePos = structure.utf16LinePos
	utf16Offset = structure.utf16Offset
	utf16Length = structure.utf16Length
}

actual interface WGPUComputePassDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUComputePassDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUComputePassDescriptor is null")

		override var timestampWrites: WGPUComputePassTimestampWrites?
			get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.timestampWrites?.toLong()?.takeIf {it != 0L}?.let { WGPUComputePassTimestampWrites(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.timestampWrites = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var timestampWrites: WGPUComputePassTimestampWrites?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUComputePassDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassDescriptor>())
				.let { WGPUComputePassDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUComputePassDescriptor) -> Unit): ArrayHolder<WGPUComputePassDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUComputePassDescriptor>())
							.let { WGPUComputePassDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUComputePassDescriptor> {
		return cValue<webgpu.native.WGPUComputePassDescriptor> {
			label.adapt(this@WGPUComputePassDescriptor.label)
			nextInChain = this@WGPUComputePassDescriptor.nextInChain?.toCPointer()
			timestampWrites = this@WGPUComputePassDescriptor.timestampWrites?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUComputePassDescriptor.adapt(structure: WGPUComputePassDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	timestampWrites = structure.timestampWrites?.handler?.toCPointer()
}

actual interface WGPUComputePassTimestampWrites {
	value class ByReference(override val handler: NativeAddress) : WGPUComputePassTimestampWrites {
		override var querySet: WGPUQuerySet?
			get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.querySet = newValue?.handler?.toCPointer() } } 

		override var beginningOfPassWriteIndex: UInt
			get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.beginningOfPassWriteIndex ?: error("pointer of WGPUComputePassTimestampWrites is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.beginningOfPassWriteIndex = newValue } } 

		override var endOfPassWriteIndex: UInt
			get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.endOfPassWriteIndex ?: error("pointer of WGPUComputePassTimestampWrites is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.endOfPassWriteIndex = newValue } } 

	}

	actual var querySet: WGPUQuerySet?
	actual var beginningOfPassWriteIndex: UInt
	actual var endOfPassWriteIndex: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUComputePassTimestampWrites {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassTimestampWrites {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassTimestampWrites>())
				.let { WGPUComputePassTimestampWrites(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUComputePassTimestampWrites) -> Unit): ArrayHolder<WGPUComputePassTimestampWrites> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassTimestampWrites>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUComputePassTimestampWrites>())
							.let { WGPUComputePassTimestampWrites(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUComputePassTimestampWrites> {
		return cValue<webgpu.native.WGPUComputePassTimestampWrites> {
			querySet = this@WGPUComputePassTimestampWrites.querySet?.handler?.toCPointer()
			beginningOfPassWriteIndex = this@WGPUComputePassTimestampWrites.beginningOfPassWriteIndex
			endOfPassWriteIndex = this@WGPUComputePassTimestampWrites.endOfPassWriteIndex
		}
	}
}

fun webgpu.native.WGPUComputePassTimestampWrites.adapt(structure: WGPUComputePassTimestampWrites) {
	querySet = structure.querySet?.handler?.toCPointer()
	beginningOfPassWriteIndex = structure.beginningOfPassWriteIndex
	endOfPassWriteIndex = structure.endOfPassWriteIndex
}

actual interface WGPUProgrammableStageDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUProgrammableStageDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var module: WGPUShaderModule?
			get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

		override val entryPoint: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUProgrammableStageDescriptor is null")

		override var constantCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.constantCount ?: error("pointer of WGPUProgrammableStageDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.constantCount = newValue } } 

		override var constants: ArrayHolder<WGPUConstantEntry>?
			get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var module: WGPUShaderModule?
	actual val entryPoint: WGPUStringView
	actual var constantCount: ULong
	actual var constants: ArrayHolder<WGPUConstantEntry>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUProgrammableStageDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUProgrammableStageDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUProgrammableStageDescriptor>())
				.let { WGPUProgrammableStageDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUProgrammableStageDescriptor) -> Unit): ArrayHolder<WGPUProgrammableStageDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUProgrammableStageDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUProgrammableStageDescriptor>())
							.let { WGPUProgrammableStageDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUProgrammableStageDescriptor> {
		return cValue<webgpu.native.WGPUProgrammableStageDescriptor> {
			entryPoint.adapt(this@WGPUProgrammableStageDescriptor.entryPoint)
			nextInChain = this@WGPUProgrammableStageDescriptor.nextInChain?.toCPointer()
			module = this@WGPUProgrammableStageDescriptor.module?.handler?.toCPointer()
			constantCount = this@WGPUProgrammableStageDescriptor.constantCount
			constants = this@WGPUProgrammableStageDescriptor.constants?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUProgrammableStageDescriptor.adapt(structure: WGPUProgrammableStageDescriptor) {
	entryPoint.adapt(structure.entryPoint)
	nextInChain = structure.nextInChain?.toCPointer()
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
}

actual interface WGPUComputePipelineDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUComputePipelineDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUComputePipelineDescriptor is null")

		override var layout: WGPUPipelineLayout?
			get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

		override val compute: WGPUProgrammableStageDescriptor
			get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.compute?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUProgrammableStageDescriptor(it) } ?: error("pointer of WGPUComputePipelineDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var layout: WGPUPipelineLayout?
	actual val compute: WGPUProgrammableStageDescriptor
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUComputePipelineDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePipelineDescriptor>())
				.let { WGPUComputePipelineDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUComputePipelineDescriptor) -> Unit): ArrayHolder<WGPUComputePipelineDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePipelineDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUComputePipelineDescriptor>())
							.let { WGPUComputePipelineDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUComputePipelineDescriptor> {
		return cValue<webgpu.native.WGPUComputePipelineDescriptor> {
			label.adapt(this@WGPUComputePipelineDescriptor.label)
			compute.adapt(this@WGPUComputePipelineDescriptor.compute)
			nextInChain = this@WGPUComputePipelineDescriptor.nextInChain?.toCPointer()
			layout = this@WGPUComputePipelineDescriptor.layout?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUComputePipelineDescriptor.adapt(structure: WGPUComputePipelineDescriptor) {
	label.adapt(structure.label)
	compute.adapt(structure.compute)
	nextInChain = structure.nextInChain?.toCPointer()
	layout = structure.layout?.handler?.toCPointer()
}

actual interface WGPUConstantEntry {
	value class ByReference(override val handler: NativeAddress) : WGPUConstantEntry {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val key: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.key?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUConstantEntry is null")

		override var value: Double
			get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.value ?: error("pointer of WGPUConstantEntry is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.value = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val key: WGPUStringView
	actual var value: Double
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUConstantEntry {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUConstantEntry>())
				.let { WGPUConstantEntry(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUConstantEntry) -> Unit): ArrayHolder<WGPUConstantEntry> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUConstantEntry>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUConstantEntry>())
							.let { WGPUConstantEntry(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUConstantEntry> {
		return cValue<webgpu.native.WGPUConstantEntry> {
			key.adapt(this@WGPUConstantEntry.key)
			nextInChain = this@WGPUConstantEntry.nextInChain?.toCPointer()
			value = this@WGPUConstantEntry.value
		}
	}
}

fun webgpu.native.WGPUConstantEntry.adapt(structure: WGPUConstantEntry) {
	key.adapt(structure.key)
	nextInChain = structure.nextInChain?.toCPointer()
	value = structure.value
}

actual interface WGPUStencilFaceState {
	value class ByReference(override val handler: NativeAddress) : WGPUStencilFaceState {
		override var compare: WGPUCompareFunction
			get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.compare ?: error("pointer of WGPUStencilFaceState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.compare = newValue } } 

		override var failOp: WGPUStencilOperation
			get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.failOp ?: error("pointer of WGPUStencilFaceState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.failOp = newValue } } 

		override var depthFailOp: WGPUStencilOperation
			get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.depthFailOp ?: error("pointer of WGPUStencilFaceState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.depthFailOp = newValue } } 

		override var passOp: WGPUStencilOperation
			get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.passOp ?: error("pointer of WGPUStencilFaceState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.passOp = newValue } } 

	}

	actual var compare: WGPUCompareFunction
	actual var failOp: WGPUStencilOperation
	actual var depthFailOp: WGPUStencilOperation
	actual var passOp: WGPUStencilOperation
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUStencilFaceState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStencilFaceState>())
				.let { WGPUStencilFaceState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUStencilFaceState) -> Unit): ArrayHolder<WGPUStencilFaceState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStencilFaceState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUStencilFaceState>())
							.let { WGPUStencilFaceState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUStencilFaceState> {
		return cValue<webgpu.native.WGPUStencilFaceState> {
			compare = this@WGPUStencilFaceState.compare
			failOp = this@WGPUStencilFaceState.failOp
			depthFailOp = this@WGPUStencilFaceState.depthFailOp
			passOp = this@WGPUStencilFaceState.passOp
		}
	}
}

fun webgpu.native.WGPUStencilFaceState.adapt(structure: WGPUStencilFaceState) {
	compare = structure.compare
	failOp = structure.failOp
	depthFailOp = structure.depthFailOp
	passOp = structure.passOp
}

actual interface WGPUDepthStencilState {
	value class ByReference(override val handler: NativeAddress) : WGPUDepthStencilState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.format ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.format = newValue } } 

		override var depthWriteEnabled: WGPUOptionalBool
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthWriteEnabled ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthWriteEnabled = newValue } } 

		override var depthCompare: WGPUCompareFunction
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthCompare ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthCompare = newValue } } 

		override val stencilFront: WGPUStencilFaceState
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilFront?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStencilFaceState(it) } ?: error("pointer of WGPUDepthStencilState is null")

		override val stencilBack: WGPUStencilFaceState
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilBack?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStencilFaceState(it) } ?: error("pointer of WGPUDepthStencilState is null")

		override var stencilReadMask: UInt
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilReadMask ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.stencilReadMask = newValue } } 

		override var stencilWriteMask: UInt
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilWriteMask ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.stencilWriteMask = newValue } } 

		override var depthBias: Int
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBias ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBias = newValue } } 

		override var depthBiasSlopeScale: Float
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBiasSlopeScale ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBiasSlopeScale = newValue } } 

		override var depthBiasClamp: Float
			get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBiasClamp ?: error("pointer of WGPUDepthStencilState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBiasClamp = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var format: WGPUTextureFormat
	actual var depthWriteEnabled: WGPUOptionalBool
	actual var depthCompare: WGPUCompareFunction
	actual val stencilFront: WGPUStencilFaceState
	actual val stencilBack: WGPUStencilFaceState
	actual var stencilReadMask: UInt
	actual var stencilWriteMask: UInt
	actual var depthBias: Int
	actual var depthBiasSlopeScale: Float
	actual var depthBiasClamp: Float
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUDepthStencilState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDepthStencilState>())
				.let { WGPUDepthStencilState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUDepthStencilState) -> Unit): ArrayHolder<WGPUDepthStencilState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDepthStencilState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUDepthStencilState>())
							.let { WGPUDepthStencilState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUDepthStencilState> {
		return cValue<webgpu.native.WGPUDepthStencilState> {
			stencilFront.adapt(this@WGPUDepthStencilState.stencilFront)
			stencilBack.adapt(this@WGPUDepthStencilState.stencilBack)
			nextInChain = this@WGPUDepthStencilState.nextInChain?.toCPointer()
			format = this@WGPUDepthStencilState.format
			depthWriteEnabled = this@WGPUDepthStencilState.depthWriteEnabled
			depthCompare = this@WGPUDepthStencilState.depthCompare
			stencilReadMask = this@WGPUDepthStencilState.stencilReadMask
			stencilWriteMask = this@WGPUDepthStencilState.stencilWriteMask
			depthBias = this@WGPUDepthStencilState.depthBias
			depthBiasSlopeScale = this@WGPUDepthStencilState.depthBiasSlopeScale
			depthBiasClamp = this@WGPUDepthStencilState.depthBiasClamp
		}
	}
}

fun webgpu.native.WGPUDepthStencilState.adapt(structure: WGPUDepthStencilState) {
	stencilFront.adapt(structure.stencilFront)
	stencilBack.adapt(structure.stencilBack)
	nextInChain = structure.nextInChain?.toCPointer()
	format = structure.format
	depthWriteEnabled = structure.depthWriteEnabled
	depthCompare = structure.depthCompare
	stencilReadMask = structure.stencilReadMask
	stencilWriteMask = structure.stencilWriteMask
	depthBias = structure.depthBias
	depthBiasSlopeScale = structure.depthBiasSlopeScale
	depthBiasClamp = structure.depthBiasClamp
}

actual interface WGPUQueueDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUQueueDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUQueueDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUQueueDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueDescriptor>())
				.let { WGPUQueueDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUQueueDescriptor) -> Unit): ArrayHolder<WGPUQueueDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUQueueDescriptor>())
							.let { WGPUQueueDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUQueueDescriptor> {
		return cValue<webgpu.native.WGPUQueueDescriptor> {
			label.adapt(this@WGPUQueueDescriptor.label)
			nextInChain = this@WGPUQueueDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUQueueDescriptor.adapt(structure: WGPUQueueDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUDeviceLostCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUDeviceLostCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUDeviceLostCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUDeviceLostCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUDeviceLostCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceLostCallbackInfo>())
				.let { WGPUDeviceLostCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUDeviceLostCallbackInfo) -> Unit): ArrayHolder<WGPUDeviceLostCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceLostCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUDeviceLostCallbackInfo>())
							.let { WGPUDeviceLostCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUDeviceLostCallbackInfo> {
		return cValue<webgpu.native.WGPUDeviceLostCallbackInfo> {
			nextInChain = this@WGPUDeviceLostCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUDeviceLostCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUDeviceLostCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUDeviceLostCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUDeviceLostCallbackInfo.adapt(structure: WGPUDeviceLostCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUUncapturedErrorCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUUncapturedErrorCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUUncapturedErrorCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUUncapturedErrorCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUUncapturedErrorCallbackInfo>())
				.let { WGPUUncapturedErrorCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUUncapturedErrorCallbackInfo) -> Unit): ArrayHolder<WGPUUncapturedErrorCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUUncapturedErrorCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUUncapturedErrorCallbackInfo>())
							.let { WGPUUncapturedErrorCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUUncapturedErrorCallbackInfo> {
		return cValue<webgpu.native.WGPUUncapturedErrorCallbackInfo> {
			nextInChain = this@WGPUUncapturedErrorCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUUncapturedErrorCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUUncapturedErrorCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUUncapturedErrorCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUUncapturedErrorCallbackInfo.adapt(structure: WGPUUncapturedErrorCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUDeviceDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUDeviceDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

		override var requiredFeatureCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredFeatureCount ?: error("pointer of WGPUDeviceDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredFeatureCount = newValue } } 

		override var requiredFeatures: ArrayHolder<WGPUFeatureName>?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredFeatures?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUFeatureName>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredFeatures = newValue?.handler?.toCPointer() } } 

		override var requiredLimits: WGPURequiredLimits?
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredLimits?.toLong()?.takeIf {it != 0L}?.let { WGPURequiredLimits(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredLimits = newValue?.handler?.toCPointer() } } 

		override val defaultQueue: WGPUQueueDescriptor
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.defaultQueue?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUQueueDescriptor(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

		override val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.deviceLostCallbackInfo?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUDeviceLostCallbackInfo(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

		override val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
			get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.uncapturedErrorCallbackInfo?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUUncapturedErrorCallbackInfo(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var requiredFeatureCount: ULong
	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
	actual var requiredLimits: WGPURequiredLimits?
	actual val defaultQueue: WGPUQueueDescriptor
	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUDeviceDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceDescriptor>())
				.let { WGPUDeviceDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUDeviceDescriptor) -> Unit): ArrayHolder<WGPUDeviceDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUDeviceDescriptor>())
							.let { WGPUDeviceDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUDeviceDescriptor> {
		return cValue<webgpu.native.WGPUDeviceDescriptor> {
			label.adapt(this@WGPUDeviceDescriptor.label)
			defaultQueue.adapt(this@WGPUDeviceDescriptor.defaultQueue)
			deviceLostCallbackInfo.adapt(this@WGPUDeviceDescriptor.deviceLostCallbackInfo)
			uncapturedErrorCallbackInfo.adapt(this@WGPUDeviceDescriptor.uncapturedErrorCallbackInfo)
			nextInChain = this@WGPUDeviceDescriptor.nextInChain?.toCPointer()
			requiredFeatureCount = this@WGPUDeviceDescriptor.requiredFeatureCount
			requiredFeatures = this@WGPUDeviceDescriptor.requiredFeatures?.handler?.toCPointer()
			requiredLimits = this@WGPUDeviceDescriptor.requiredLimits?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUDeviceDescriptor.adapt(structure: WGPUDeviceDescriptor) {
	label.adapt(structure.label)
	defaultQueue.adapt(structure.defaultQueue)
	deviceLostCallbackInfo.adapt(structure.deviceLostCallbackInfo)
	uncapturedErrorCallbackInfo.adapt(structure.uncapturedErrorCallbackInfo)
	nextInChain = structure.nextInChain?.toCPointer()
	requiredFeatureCount = structure.requiredFeatureCount
	requiredFeatures = structure.requiredFeatures?.handler?.toCPointer()
	requiredLimits = structure.requiredLimits?.handler?.toCPointer()
}

actual interface WGPUExtent3D {
	value class ByReference(override val handler: NativeAddress) : WGPUExtent3D {
		override var width: UInt
			get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.width ?: error("pointer of WGPUExtent3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.width = newValue } } 

		override var height: UInt
			get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.height ?: error("pointer of WGPUExtent3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.height = newValue } } 

		override var depthOrArrayLayers: UInt
			get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.depthOrArrayLayers ?: error("pointer of WGPUExtent3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.depthOrArrayLayers = newValue } } 

	}

	actual var width: UInt
	actual var height: UInt
	actual var depthOrArrayLayers: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUExtent3D {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D {
			return allocator.allocate(sizeOf<webgpu.native.WGPUExtent3D>())
				.let { WGPUExtent3D(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUExtent3D) -> Unit): ArrayHolder<WGPUExtent3D> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUExtent3D>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUExtent3D>())
							.let { WGPUExtent3D(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUExtent3D> {
		return cValue<webgpu.native.WGPUExtent3D> {
			width = this@WGPUExtent3D.width
			height = this@WGPUExtent3D.height
			depthOrArrayLayers = this@WGPUExtent3D.depthOrArrayLayers
		}
	}
}

fun webgpu.native.WGPUExtent3D.adapt(structure: WGPUExtent3D) {
	width = structure.width
	height = structure.height
	depthOrArrayLayers = structure.depthOrArrayLayers
}

actual interface WGPUFragmentState {
	value class ByReference(override val handler: NativeAddress) : WGPUFragmentState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var module: WGPUShaderModule?
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

		override val entryPoint: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUFragmentState is null")

		override var constantCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.constantCount ?: error("pointer of WGPUFragmentState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.constantCount = newValue } } 

		override var constants: ArrayHolder<WGPUConstantEntry>?
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

		override var targetCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.targetCount ?: error("pointer of WGPUFragmentState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.targetCount = newValue } } 

		override var targets: ArrayHolder<WGPUColorTargetState>?
			get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.targets?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUColorTargetState>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.targets = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var module: WGPUShaderModule?
	actual val entryPoint: WGPUStringView
	actual var constantCount: ULong
	actual var constants: ArrayHolder<WGPUConstantEntry>?
	actual var targetCount: ULong
	actual var targets: ArrayHolder<WGPUColorTargetState>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUFragmentState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUFragmentState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFragmentState>())
				.let { WGPUFragmentState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUFragmentState) -> Unit): ArrayHolder<WGPUFragmentState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFragmentState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUFragmentState>())
							.let { WGPUFragmentState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUFragmentState> {
		return cValue<webgpu.native.WGPUFragmentState> {
			entryPoint.adapt(this@WGPUFragmentState.entryPoint)
			nextInChain = this@WGPUFragmentState.nextInChain?.toCPointer()
			module = this@WGPUFragmentState.module?.handler?.toCPointer()
			constantCount = this@WGPUFragmentState.constantCount
			constants = this@WGPUFragmentState.constants?.handler?.toCPointer()
			targetCount = this@WGPUFragmentState.targetCount
			targets = this@WGPUFragmentState.targets?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUFragmentState.adapt(structure: WGPUFragmentState) {
	entryPoint.adapt(structure.entryPoint)
	nextInChain = structure.nextInChain?.toCPointer()
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
	targetCount = structure.targetCount
	targets = structure.targets?.handler?.toCPointer()
}

actual interface WGPUFuture {
	value class ByReference(override val handler: NativeAddress) : WGPUFuture {
		override var id: ULong
			get() = handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.id ?: error("pointer of WGPUFuture is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.let { it.id = newValue } } 

	}

	actual var id: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUFuture {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFuture>())
				.let { WGPUFuture(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUFuture) -> Unit): ArrayHolder<WGPUFuture> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFuture>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUFuture>())
							.let { WGPUFuture(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUFuture> {
		return cValue<webgpu.native.WGPUFuture> {
			id = this@WGPUFuture.id
		}
	}
}

fun webgpu.native.WGPUFuture.adapt(structure: WGPUFuture) {
	id = structure.id
}

actual interface WGPUFutureWaitInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUFutureWaitInfo {
		override val future: WGPUFuture
			get() = handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.future?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUFuture(it) } ?: error("pointer of WGPUFutureWaitInfo is null")

		override var completed: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.completed?.toBoolean() ?: error("pointer of WGPUFutureWaitInfo is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.let { it.completed = newValue.toUInt() } } 

	}

	actual val future: WGPUFuture
	actual var completed: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUFutureWaitInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFutureWaitInfo>())
				.let { WGPUFutureWaitInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUFutureWaitInfo) -> Unit): ArrayHolder<WGPUFutureWaitInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFutureWaitInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUFutureWaitInfo>())
							.let { WGPUFutureWaitInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUFutureWaitInfo> {
		return cValue<webgpu.native.WGPUFutureWaitInfo> {
			future.adapt(this@WGPUFutureWaitInfo.future)
			completed = this@WGPUFutureWaitInfo.completed.toUInt()
		}
	}
}

fun webgpu.native.WGPUFutureWaitInfo.adapt(structure: WGPUFutureWaitInfo) {
	future.adapt(structure.future)
	completed = structure.completed.toUInt()
}

actual interface WGPUTextureDataLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUTextureDataLayout {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var offset: ULong
			get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.offset ?: error("pointer of WGPUTextureDataLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.offset = newValue } } 

		override var bytesPerRow: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.bytesPerRow ?: error("pointer of WGPUTextureDataLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.bytesPerRow = newValue } } 

		override var rowsPerImage: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.rowsPerImage ?: error("pointer of WGPUTextureDataLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.rowsPerImage = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var offset: ULong
	actual var bytesPerRow: UInt
	actual var rowsPerImage: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUTextureDataLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDataLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDataLayout>())
				.let { WGPUTextureDataLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUTextureDataLayout) -> Unit): ArrayHolder<WGPUTextureDataLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDataLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUTextureDataLayout>())
							.let { WGPUTextureDataLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureDataLayout> {
		return cValue<webgpu.native.WGPUTextureDataLayout> {
			nextInChain = this@WGPUTextureDataLayout.nextInChain?.toCPointer()
			offset = this@WGPUTextureDataLayout.offset
			bytesPerRow = this@WGPUTextureDataLayout.bytesPerRow
			rowsPerImage = this@WGPUTextureDataLayout.rowsPerImage
		}
	}
}

fun webgpu.native.WGPUTextureDataLayout.adapt(structure: WGPUTextureDataLayout) {
	nextInChain = structure.nextInChain?.toCPointer()
	offset = structure.offset
	bytesPerRow = structure.bytesPerRow
	rowsPerImage = structure.rowsPerImage
}

actual interface WGPUImageCopyBuffer {
	value class ByReference(override val handler: NativeAddress) : WGPUImageCopyBuffer {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val layout: WGPUTextureDataLayout
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.layout?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureDataLayout(it) } ?: error("pointer of WGPUImageCopyBuffer is null")

		override var buffer: WGPUBuffer?
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.let { it.buffer = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val layout: WGPUTextureDataLayout
	actual var buffer: WGPUBuffer?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUImageCopyBuffer {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyBuffer {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyBuffer>())
				.let { WGPUImageCopyBuffer(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUImageCopyBuffer) -> Unit): ArrayHolder<WGPUImageCopyBuffer> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyBuffer>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUImageCopyBuffer>())
							.let { WGPUImageCopyBuffer(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUImageCopyBuffer> {
		return cValue<webgpu.native.WGPUImageCopyBuffer> {
			layout.adapt(this@WGPUImageCopyBuffer.layout)
			nextInChain = this@WGPUImageCopyBuffer.nextInChain?.toCPointer()
			buffer = this@WGPUImageCopyBuffer.buffer?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUImageCopyBuffer.adapt(structure: WGPUImageCopyBuffer) {
	layout.adapt(structure.layout)
	nextInChain = structure.nextInChain?.toCPointer()
	buffer = structure.buffer?.handler?.toCPointer()
}

actual interface WGPUOrigin3D {
	value class ByReference(override val handler: NativeAddress) : WGPUOrigin3D {
		override var x: UInt
			get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.x ?: error("pointer of WGPUOrigin3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.x = newValue } } 

		override var y: UInt
			get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.y ?: error("pointer of WGPUOrigin3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.y = newValue } } 

		override var z: UInt
			get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.z ?: error("pointer of WGPUOrigin3D is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.z = newValue } } 

	}

	actual var x: UInt
	actual var y: UInt
	actual var z: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUOrigin3D {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D {
			return allocator.allocate(sizeOf<webgpu.native.WGPUOrigin3D>())
				.let { WGPUOrigin3D(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUOrigin3D) -> Unit): ArrayHolder<WGPUOrigin3D> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUOrigin3D>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUOrigin3D>())
							.let { WGPUOrigin3D(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUOrigin3D> {
		return cValue<webgpu.native.WGPUOrigin3D> {
			x = this@WGPUOrigin3D.x
			y = this@WGPUOrigin3D.y
			z = this@WGPUOrigin3D.z
		}
	}
}

fun webgpu.native.WGPUOrigin3D.adapt(structure: WGPUOrigin3D) {
	x = structure.x
	y = structure.y
	z = structure.z
}

actual interface WGPUImageCopyTexture {
	value class ByReference(override val handler: NativeAddress) : WGPUImageCopyTexture {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var texture: WGPUTexture?
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

		override var mipLevel: UInt
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.mipLevel ?: error("pointer of WGPUImageCopyTexture is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.mipLevel = newValue } } 

		override val origin: WGPUOrigin3D
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.origin?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUOrigin3D(it) } ?: error("pointer of WGPUImageCopyTexture is null")

		override var aspect: WGPUTextureAspect
			get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.aspect ?: error("pointer of WGPUImageCopyTexture is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.aspect = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var texture: WGPUTexture?
	actual var mipLevel: UInt
	actual val origin: WGPUOrigin3D
	actual var aspect: WGPUTextureAspect
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUImageCopyTexture {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyTexture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyTexture>())
				.let { WGPUImageCopyTexture(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUImageCopyTexture) -> Unit): ArrayHolder<WGPUImageCopyTexture> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyTexture>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUImageCopyTexture>())
							.let { WGPUImageCopyTexture(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUImageCopyTexture> {
		return cValue<webgpu.native.WGPUImageCopyTexture> {
			origin.adapt(this@WGPUImageCopyTexture.origin)
			nextInChain = this@WGPUImageCopyTexture.nextInChain?.toCPointer()
			texture = this@WGPUImageCopyTexture.texture?.handler?.toCPointer()
			mipLevel = this@WGPUImageCopyTexture.mipLevel
			aspect = this@WGPUImageCopyTexture.aspect
		}
	}
}

fun webgpu.native.WGPUImageCopyTexture.adapt(structure: WGPUImageCopyTexture) {
	origin.adapt(structure.origin)
	nextInChain = structure.nextInChain?.toCPointer()
	texture = structure.texture?.handler?.toCPointer()
	mipLevel = structure.mipLevel
	aspect = structure.aspect
}

actual interface WGPUInstanceFeatures {
	value class ByReference(override val handler: NativeAddress) : WGPUInstanceFeatures {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var timedWaitAnyEnable: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyEnable?.toBoolean() ?: error("pointer of WGPUInstanceFeatures is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyEnable = newValue.toUInt() } } 

		override var timedWaitAnyMaxCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyMaxCount ?: error("pointer of WGPUInstanceFeatures is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyMaxCount = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var timedWaitAnyEnable: Boolean
	actual var timedWaitAnyMaxCount: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUInstanceFeatures {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceFeatures {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceFeatures>())
				.let { WGPUInstanceFeatures(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUInstanceFeatures) -> Unit): ArrayHolder<WGPUInstanceFeatures> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceFeatures>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUInstanceFeatures>())
							.let { WGPUInstanceFeatures(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUInstanceFeatures> {
		return cValue<webgpu.native.WGPUInstanceFeatures> {
			nextInChain = this@WGPUInstanceFeatures.nextInChain?.toCPointer()
			timedWaitAnyEnable = this@WGPUInstanceFeatures.timedWaitAnyEnable.toUInt()
			timedWaitAnyMaxCount = this@WGPUInstanceFeatures.timedWaitAnyMaxCount
		}
	}
}

fun webgpu.native.WGPUInstanceFeatures.adapt(structure: WGPUInstanceFeatures) {
	nextInChain = structure.nextInChain?.toCPointer()
	timedWaitAnyEnable = structure.timedWaitAnyEnable.toUInt()
	timedWaitAnyMaxCount = structure.timedWaitAnyMaxCount
}

actual interface WGPUInstanceDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUInstanceDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUInstanceDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val features: WGPUInstanceFeatures
			get() = handler.toCPointer<webgpu.native.WGPUInstanceDescriptor>()?.pointed?.features?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUInstanceFeatures(it) } ?: error("pointer of WGPUInstanceDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val features: WGPUInstanceFeatures
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUInstanceDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceDescriptor>())
				.let { WGPUInstanceDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUInstanceDescriptor) -> Unit): ArrayHolder<WGPUInstanceDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUInstanceDescriptor>())
							.let { WGPUInstanceDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUInstanceDescriptor> {
		return cValue<webgpu.native.WGPUInstanceDescriptor> {
			features.adapt(this@WGPUInstanceDescriptor.features)
			nextInChain = this@WGPUInstanceDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUInstanceDescriptor.adapt(structure: WGPUInstanceDescriptor) {
	features.adapt(structure.features)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPULimits {
	value class ByReference(override val handler: NativeAddress) : WGPULimits {
		override var maxTextureDimension1D: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension1D ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension1D = newValue } } 

		override var maxTextureDimension2D: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension2D ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension2D = newValue } } 

		override var maxTextureDimension3D: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension3D ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension3D = newValue } } 

		override var maxTextureArrayLayers: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureArrayLayers ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureArrayLayers = newValue } } 

		override var maxBindGroups: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindGroups ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindGroups = newValue } } 

		override var maxBindGroupsPlusVertexBuffers: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindGroupsPlusVertexBuffers ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindGroupsPlusVertexBuffers = newValue } } 

		override var maxBindingsPerBindGroup: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindingsPerBindGroup ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindingsPerBindGroup = newValue } } 

		override var maxDynamicUniformBuffersPerPipelineLayout: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxDynamicUniformBuffersPerPipelineLayout ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxDynamicUniformBuffersPerPipelineLayout = newValue } } 

		override var maxDynamicStorageBuffersPerPipelineLayout: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxDynamicStorageBuffersPerPipelineLayout ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxDynamicStorageBuffersPerPipelineLayout = newValue } } 

		override var maxSampledTexturesPerShaderStage: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxSampledTexturesPerShaderStage ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxSampledTexturesPerShaderStage = newValue } } 

		override var maxSamplersPerShaderStage: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxSamplersPerShaderStage ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxSamplersPerShaderStage = newValue } } 

		override var maxStorageBuffersPerShaderStage: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageBuffersPerShaderStage ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageBuffersPerShaderStage = newValue } } 

		override var maxStorageTexturesPerShaderStage: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageTexturesPerShaderStage ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageTexturesPerShaderStage = newValue } } 

		override var maxUniformBuffersPerShaderStage: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxUniformBuffersPerShaderStage ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxUniformBuffersPerShaderStage = newValue } } 

		override var maxUniformBufferBindingSize: ULong
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxUniformBufferBindingSize ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxUniformBufferBindingSize = newValue } } 

		override var maxStorageBufferBindingSize: ULong
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageBufferBindingSize ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageBufferBindingSize = newValue } } 

		override var minUniformBufferOffsetAlignment: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.minUniformBufferOffsetAlignment ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.minUniformBufferOffsetAlignment = newValue } } 

		override var minStorageBufferOffsetAlignment: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.minStorageBufferOffsetAlignment ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.minStorageBufferOffsetAlignment = newValue } } 

		override var maxVertexBuffers: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexBuffers ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexBuffers = newValue } } 

		override var maxBufferSize: ULong
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBufferSize ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBufferSize = newValue } } 

		override var maxVertexAttributes: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexAttributes ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexAttributes = newValue } } 

		override var maxVertexBufferArrayStride: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexBufferArrayStride ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexBufferArrayStride = newValue } } 

		override var maxInterStageShaderVariables: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxInterStageShaderVariables ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxInterStageShaderVariables = newValue } } 

		override var maxColorAttachments: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxColorAttachments ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxColorAttachments = newValue } } 

		override var maxColorAttachmentBytesPerSample: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxColorAttachmentBytesPerSample ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxColorAttachmentBytesPerSample = newValue } } 

		override var maxComputeWorkgroupStorageSize: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupStorageSize ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupStorageSize = newValue } } 

		override var maxComputeInvocationsPerWorkgroup: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeInvocationsPerWorkgroup ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeInvocationsPerWorkgroup = newValue } } 

		override var maxComputeWorkgroupSizeX: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeX ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeX = newValue } } 

		override var maxComputeWorkgroupSizeY: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeY ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeY = newValue } } 

		override var maxComputeWorkgroupSizeZ: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeZ ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeZ = newValue } } 

		override var maxComputeWorkgroupsPerDimension: UInt
			get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupsPerDimension ?: error("pointer of WGPULimits is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupsPerDimension = newValue } } 

	}

	actual var maxTextureDimension1D: UInt
	actual var maxTextureDimension2D: UInt
	actual var maxTextureDimension3D: UInt
	actual var maxTextureArrayLayers: UInt
	actual var maxBindGroups: UInt
	actual var maxBindGroupsPlusVertexBuffers: UInt
	actual var maxBindingsPerBindGroup: UInt
	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
	actual var maxSampledTexturesPerShaderStage: UInt
	actual var maxSamplersPerShaderStage: UInt
	actual var maxStorageBuffersPerShaderStage: UInt
	actual var maxStorageTexturesPerShaderStage: UInt
	actual var maxUniformBuffersPerShaderStage: UInt
	actual var maxUniformBufferBindingSize: ULong
	actual var maxStorageBufferBindingSize: ULong
	actual var minUniformBufferOffsetAlignment: UInt
	actual var minStorageBufferOffsetAlignment: UInt
	actual var maxVertexBuffers: UInt
	actual var maxBufferSize: ULong
	actual var maxVertexAttributes: UInt
	actual var maxVertexBufferArrayStride: UInt
	actual var maxInterStageShaderVariables: UInt
	actual var maxColorAttachments: UInt
	actual var maxColorAttachmentBytesPerSample: UInt
	actual var maxComputeWorkgroupStorageSize: UInt
	actual var maxComputeInvocationsPerWorkgroup: UInt
	actual var maxComputeWorkgroupSizeX: UInt
	actual var maxComputeWorkgroupSizeY: UInt
	actual var maxComputeWorkgroupSizeZ: UInt
	actual var maxComputeWorkgroupsPerDimension: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPULimits {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPULimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPULimits>())
				.let { WGPULimits(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPULimits) -> Unit): ArrayHolder<WGPULimits> {
			return allocator.allocate(sizeOf<webgpu.native.WGPULimits>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPULimits>())
							.let { WGPULimits(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPULimits> {
		return cValue<webgpu.native.WGPULimits> {
			maxTextureDimension1D = this@WGPULimits.maxTextureDimension1D
			maxTextureDimension2D = this@WGPULimits.maxTextureDimension2D
			maxTextureDimension3D = this@WGPULimits.maxTextureDimension3D
			maxTextureArrayLayers = this@WGPULimits.maxTextureArrayLayers
			maxBindGroups = this@WGPULimits.maxBindGroups
			maxBindGroupsPlusVertexBuffers = this@WGPULimits.maxBindGroupsPlusVertexBuffers
			maxBindingsPerBindGroup = this@WGPULimits.maxBindingsPerBindGroup
			maxDynamicUniformBuffersPerPipelineLayout = this@WGPULimits.maxDynamicUniformBuffersPerPipelineLayout
			maxDynamicStorageBuffersPerPipelineLayout = this@WGPULimits.maxDynamicStorageBuffersPerPipelineLayout
			maxSampledTexturesPerShaderStage = this@WGPULimits.maxSampledTexturesPerShaderStage
			maxSamplersPerShaderStage = this@WGPULimits.maxSamplersPerShaderStage
			maxStorageBuffersPerShaderStage = this@WGPULimits.maxStorageBuffersPerShaderStage
			maxStorageTexturesPerShaderStage = this@WGPULimits.maxStorageTexturesPerShaderStage
			maxUniformBuffersPerShaderStage = this@WGPULimits.maxUniformBuffersPerShaderStage
			maxUniformBufferBindingSize = this@WGPULimits.maxUniformBufferBindingSize
			maxStorageBufferBindingSize = this@WGPULimits.maxStorageBufferBindingSize
			minUniformBufferOffsetAlignment = this@WGPULimits.minUniformBufferOffsetAlignment
			minStorageBufferOffsetAlignment = this@WGPULimits.minStorageBufferOffsetAlignment
			maxVertexBuffers = this@WGPULimits.maxVertexBuffers
			maxBufferSize = this@WGPULimits.maxBufferSize
			maxVertexAttributes = this@WGPULimits.maxVertexAttributes
			maxVertexBufferArrayStride = this@WGPULimits.maxVertexBufferArrayStride
			maxInterStageShaderVariables = this@WGPULimits.maxInterStageShaderVariables
			maxColorAttachments = this@WGPULimits.maxColorAttachments
			maxColorAttachmentBytesPerSample = this@WGPULimits.maxColorAttachmentBytesPerSample
			maxComputeWorkgroupStorageSize = this@WGPULimits.maxComputeWorkgroupStorageSize
			maxComputeInvocationsPerWorkgroup = this@WGPULimits.maxComputeInvocationsPerWorkgroup
			maxComputeWorkgroupSizeX = this@WGPULimits.maxComputeWorkgroupSizeX
			maxComputeWorkgroupSizeY = this@WGPULimits.maxComputeWorkgroupSizeY
			maxComputeWorkgroupSizeZ = this@WGPULimits.maxComputeWorkgroupSizeZ
			maxComputeWorkgroupsPerDimension = this@WGPULimits.maxComputeWorkgroupsPerDimension
		}
	}
}

fun webgpu.native.WGPULimits.adapt(structure: WGPULimits) {
	maxTextureDimension1D = structure.maxTextureDimension1D
	maxTextureDimension2D = structure.maxTextureDimension2D
	maxTextureDimension3D = structure.maxTextureDimension3D
	maxTextureArrayLayers = structure.maxTextureArrayLayers
	maxBindGroups = structure.maxBindGroups
	maxBindGroupsPlusVertexBuffers = structure.maxBindGroupsPlusVertexBuffers
	maxBindingsPerBindGroup = structure.maxBindingsPerBindGroup
	maxDynamicUniformBuffersPerPipelineLayout = structure.maxDynamicUniformBuffersPerPipelineLayout
	maxDynamicStorageBuffersPerPipelineLayout = structure.maxDynamicStorageBuffersPerPipelineLayout
	maxSampledTexturesPerShaderStage = structure.maxSampledTexturesPerShaderStage
	maxSamplersPerShaderStage = structure.maxSamplersPerShaderStage
	maxStorageBuffersPerShaderStage = structure.maxStorageBuffersPerShaderStage
	maxStorageTexturesPerShaderStage = structure.maxStorageTexturesPerShaderStage
	maxUniformBuffersPerShaderStage = structure.maxUniformBuffersPerShaderStage
	maxUniformBufferBindingSize = structure.maxUniformBufferBindingSize
	maxStorageBufferBindingSize = structure.maxStorageBufferBindingSize
	minUniformBufferOffsetAlignment = structure.minUniformBufferOffsetAlignment
	minStorageBufferOffsetAlignment = structure.minStorageBufferOffsetAlignment
	maxVertexBuffers = structure.maxVertexBuffers
	maxBufferSize = structure.maxBufferSize
	maxVertexAttributes = structure.maxVertexAttributes
	maxVertexBufferArrayStride = structure.maxVertexBufferArrayStride
	maxInterStageShaderVariables = structure.maxInterStageShaderVariables
	maxColorAttachments = structure.maxColorAttachments
	maxColorAttachmentBytesPerSample = structure.maxColorAttachmentBytesPerSample
	maxComputeWorkgroupStorageSize = structure.maxComputeWorkgroupStorageSize
	maxComputeInvocationsPerWorkgroup = structure.maxComputeInvocationsPerWorkgroup
	maxComputeWorkgroupSizeX = structure.maxComputeWorkgroupSizeX
	maxComputeWorkgroupSizeY = structure.maxComputeWorkgroupSizeY
	maxComputeWorkgroupSizeZ = structure.maxComputeWorkgroupSizeZ
	maxComputeWorkgroupsPerDimension = structure.maxComputeWorkgroupsPerDimension
}

actual interface WGPUMultisampleState {
	value class ByReference(override val handler: NativeAddress) : WGPUMultisampleState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var count: UInt
			get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.count ?: error("pointer of WGPUMultisampleState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.count = newValue } } 

		override var mask: UInt
			get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.mask ?: error("pointer of WGPUMultisampleState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.mask = newValue } } 

		override var alphaToCoverageEnabled: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.alphaToCoverageEnabled?.toBoolean() ?: error("pointer of WGPUMultisampleState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.alphaToCoverageEnabled = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var count: UInt
	actual var mask: UInt
	actual var alphaToCoverageEnabled: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUMultisampleState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUMultisampleState>())
				.let { WGPUMultisampleState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUMultisampleState) -> Unit): ArrayHolder<WGPUMultisampleState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUMultisampleState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUMultisampleState>())
							.let { WGPUMultisampleState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUMultisampleState> {
		return cValue<webgpu.native.WGPUMultisampleState> {
			nextInChain = this@WGPUMultisampleState.nextInChain?.toCPointer()
			count = this@WGPUMultisampleState.count
			mask = this@WGPUMultisampleState.mask
			alphaToCoverageEnabled = this@WGPUMultisampleState.alphaToCoverageEnabled.toUInt()
		}
	}
}

fun webgpu.native.WGPUMultisampleState.adapt(structure: WGPUMultisampleState) {
	nextInChain = structure.nextInChain?.toCPointer()
	count = structure.count
	mask = structure.mask
	alphaToCoverageEnabled = structure.alphaToCoverageEnabled.toUInt()
}

actual interface WGPUPipelineLayoutDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUPipelineLayoutDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUPipelineLayoutDescriptor is null")

		override var bindGroupLayoutCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.bindGroupLayoutCount ?: error("pointer of WGPUPipelineLayoutDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.bindGroupLayoutCount = newValue } } 

		override var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
			get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.bindGroupLayouts?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupLayout>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.bindGroupLayouts = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var bindGroupLayoutCount: ULong
	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPipelineLayoutDescriptor>())
				.let { WGPUPipelineLayoutDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUPipelineLayoutDescriptor) -> Unit): ArrayHolder<WGPUPipelineLayoutDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPipelineLayoutDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUPipelineLayoutDescriptor>())
							.let { WGPUPipelineLayoutDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUPipelineLayoutDescriptor> {
		return cValue<webgpu.native.WGPUPipelineLayoutDescriptor> {
			label.adapt(this@WGPUPipelineLayoutDescriptor.label)
			nextInChain = this@WGPUPipelineLayoutDescriptor.nextInChain?.toCPointer()
			bindGroupLayoutCount = this@WGPUPipelineLayoutDescriptor.bindGroupLayoutCount
			bindGroupLayouts = this@WGPUPipelineLayoutDescriptor.bindGroupLayouts?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUPipelineLayoutDescriptor.adapt(structure: WGPUPipelineLayoutDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	bindGroupLayoutCount = structure.bindGroupLayoutCount
	bindGroupLayouts = structure.bindGroupLayouts?.handler?.toCPointer()
}

actual interface WGPUPrimitiveState {
	value class ByReference(override val handler: NativeAddress) : WGPUPrimitiveState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var topology: WGPUPrimitiveTopology
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.topology ?: error("pointer of WGPUPrimitiveState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.topology = newValue } } 

		override var stripIndexFormat: WGPUIndexFormat
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.stripIndexFormat ?: error("pointer of WGPUPrimitiveState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.stripIndexFormat = newValue } } 

		override var frontFace: WGPUFrontFace
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.frontFace ?: error("pointer of WGPUPrimitiveState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.frontFace = newValue } } 

		override var cullMode: WGPUCullMode
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.cullMode ?: error("pointer of WGPUPrimitiveState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.cullMode = newValue } } 

		override var unclippedDepth: Boolean
			get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.unclippedDepth?.toBoolean() ?: error("pointer of WGPUPrimitiveState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.unclippedDepth = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var topology: WGPUPrimitiveTopology
	actual var stripIndexFormat: WGPUIndexFormat
	actual var frontFace: WGPUFrontFace
	actual var cullMode: WGPUCullMode
	actual var unclippedDepth: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUPrimitiveState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPrimitiveState>())
				.let { WGPUPrimitiveState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUPrimitiveState) -> Unit): ArrayHolder<WGPUPrimitiveState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPrimitiveState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUPrimitiveState>())
							.let { WGPUPrimitiveState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUPrimitiveState> {
		return cValue<webgpu.native.WGPUPrimitiveState> {
			nextInChain = this@WGPUPrimitiveState.nextInChain?.toCPointer()
			topology = this@WGPUPrimitiveState.topology
			stripIndexFormat = this@WGPUPrimitiveState.stripIndexFormat
			frontFace = this@WGPUPrimitiveState.frontFace
			cullMode = this@WGPUPrimitiveState.cullMode
			unclippedDepth = this@WGPUPrimitiveState.unclippedDepth.toUInt()
		}
	}
}

fun webgpu.native.WGPUPrimitiveState.adapt(structure: WGPUPrimitiveState) {
	nextInChain = structure.nextInChain?.toCPointer()
	topology = structure.topology
	stripIndexFormat = structure.stripIndexFormat
	frontFace = structure.frontFace
	cullMode = structure.cullMode
	unclippedDepth = structure.unclippedDepth.toUInt()
}

actual interface WGPUQuerySetDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUQuerySetDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUQuerySetDescriptor is null")

		override var type: WGPUQueryType
			get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.type ?: error("pointer of WGPUQuerySetDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.type = newValue } } 

		override var count: UInt
			get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.count ?: error("pointer of WGPUQuerySetDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.count = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var type: WGPUQueryType
	actual var count: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQuerySetDescriptor>())
				.let { WGPUQuerySetDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUQuerySetDescriptor) -> Unit): ArrayHolder<WGPUQuerySetDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQuerySetDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUQuerySetDescriptor>())
							.let { WGPUQuerySetDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUQuerySetDescriptor> {
		return cValue<webgpu.native.WGPUQuerySetDescriptor> {
			label.adapt(this@WGPUQuerySetDescriptor.label)
			nextInChain = this@WGPUQuerySetDescriptor.nextInChain?.toCPointer()
			type = this@WGPUQuerySetDescriptor.type
			count = this@WGPUQuerySetDescriptor.count
		}
	}
}

fun webgpu.native.WGPUQuerySetDescriptor.adapt(structure: WGPUQuerySetDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	type = structure.type
	count = structure.count
}

actual interface WGPURenderBundleDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPURenderBundleDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderBundleDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderBundleDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleDescriptor>())
				.let { WGPURenderBundleDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderBundleDescriptor) -> Unit): ArrayHolder<WGPURenderBundleDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderBundleDescriptor>())
							.let { WGPURenderBundleDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderBundleDescriptor> {
		return cValue<webgpu.native.WGPURenderBundleDescriptor> {
			label.adapt(this@WGPURenderBundleDescriptor.label)
			nextInChain = this@WGPURenderBundleDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURenderBundleDescriptor.adapt(structure: WGPURenderBundleDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPURenderBundleEncoderDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPURenderBundleEncoderDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")

		override var colorFormatCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.colorFormatCount ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.colorFormatCount = newValue } } 

		override var colorFormats: ArrayHolder<WGPUTextureFormat>?
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.colorFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.colorFormats = newValue?.handler?.toCPointer() } } 

		override var depthStencilFormat: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.depthStencilFormat ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.depthStencilFormat = newValue } } 

		override var sampleCount: UInt
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

		override var depthReadOnly: Boolean
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

		override var stencilReadOnly: Boolean
			get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var colorFormatCount: ULong
	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
	actual var depthStencilFormat: WGPUTextureFormat
	actual var sampleCount: UInt
	actual var depthReadOnly: Boolean
	actual var stencilReadOnly: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleEncoderDescriptor>())
				.let { WGPURenderBundleEncoderDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderBundleEncoderDescriptor) -> Unit): ArrayHolder<WGPURenderBundleEncoderDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleEncoderDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderBundleEncoderDescriptor>())
							.let { WGPURenderBundleEncoderDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderBundleEncoderDescriptor> {
		return cValue<webgpu.native.WGPURenderBundleEncoderDescriptor> {
			label.adapt(this@WGPURenderBundleEncoderDescriptor.label)
			nextInChain = this@WGPURenderBundleEncoderDescriptor.nextInChain?.toCPointer()
			colorFormatCount = this@WGPURenderBundleEncoderDescriptor.colorFormatCount
			colorFormats = this@WGPURenderBundleEncoderDescriptor.colorFormats?.handler?.toCPointer()
			depthStencilFormat = this@WGPURenderBundleEncoderDescriptor.depthStencilFormat
			sampleCount = this@WGPURenderBundleEncoderDescriptor.sampleCount
			depthReadOnly = this@WGPURenderBundleEncoderDescriptor.depthReadOnly.toUInt()
			stencilReadOnly = this@WGPURenderBundleEncoderDescriptor.stencilReadOnly.toUInt()
		}
	}
}

fun webgpu.native.WGPURenderBundleEncoderDescriptor.adapt(structure: WGPURenderBundleEncoderDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	colorFormatCount = structure.colorFormatCount
	colorFormats = structure.colorFormats?.handler?.toCPointer()
	depthStencilFormat = structure.depthStencilFormat
	sampleCount = structure.sampleCount
	depthReadOnly = structure.depthReadOnly.toUInt()
	stencilReadOnly = structure.stencilReadOnly.toUInt()
}

actual interface WGPURenderPassColorAttachment {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPassColorAttachment {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var view: WGPUTextureView?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.view = newValue?.handler?.toCPointer() } } 

		override var depthSlice: UInt
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.depthSlice ?: error("pointer of WGPURenderPassColorAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.depthSlice = newValue } } 

		override var resolveTarget: WGPUTextureView?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.resolveTarget?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.resolveTarget = newValue?.handler?.toCPointer() } } 

		override var loadOp: WGPULoadOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.loadOp ?: error("pointer of WGPURenderPassColorAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.loadOp = newValue } } 

		override var storeOp: WGPUStoreOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.storeOp ?: error("pointer of WGPURenderPassColorAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.storeOp = newValue } } 

		override val clearValue: WGPUColor
			get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.clearValue?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUColor(it) } ?: error("pointer of WGPURenderPassColorAttachment is null")

	}

	actual var nextInChain: NativeAddress?
	actual var view: WGPUTextureView?
	actual var depthSlice: UInt
	actual var resolveTarget: WGPUTextureView?
	actual var loadOp: WGPULoadOp
	actual var storeOp: WGPUStoreOp
	actual val clearValue: WGPUColor
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPassColorAttachment {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassColorAttachment>())
				.let { WGPURenderPassColorAttachment(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPassColorAttachment) -> Unit): ArrayHolder<WGPURenderPassColorAttachment> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassColorAttachment>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPassColorAttachment>())
							.let { WGPURenderPassColorAttachment(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassColorAttachment> {
		return cValue<webgpu.native.WGPURenderPassColorAttachment> {
			clearValue.adapt(this@WGPURenderPassColorAttachment.clearValue)
			nextInChain = this@WGPURenderPassColorAttachment.nextInChain?.toCPointer()
			view = this@WGPURenderPassColorAttachment.view?.handler?.toCPointer()
			depthSlice = this@WGPURenderPassColorAttachment.depthSlice
			resolveTarget = this@WGPURenderPassColorAttachment.resolveTarget?.handler?.toCPointer()
			loadOp = this@WGPURenderPassColorAttachment.loadOp
			storeOp = this@WGPURenderPassColorAttachment.storeOp
		}
	}
}

fun webgpu.native.WGPURenderPassColorAttachment.adapt(structure: WGPURenderPassColorAttachment) {
	clearValue.adapt(structure.clearValue)
	nextInChain = structure.nextInChain?.toCPointer()
	view = structure.view?.handler?.toCPointer()
	depthSlice = structure.depthSlice
	resolveTarget = structure.resolveTarget?.handler?.toCPointer()
	loadOp = structure.loadOp
	storeOp = structure.storeOp
}

actual interface WGPURenderPassDepthStencilAttachment {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPassDepthStencilAttachment {
		override var view: WGPUTextureView?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.view = newValue?.handler?.toCPointer() } } 

		override var depthLoadOp: WGPULoadOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthLoadOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthLoadOp = newValue } } 

		override var depthStoreOp: WGPUStoreOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthStoreOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthStoreOp = newValue } } 

		override var depthClearValue: Float
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthClearValue = newValue } } 

		override var depthReadOnly: Boolean
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

		override var stencilLoadOp: WGPULoadOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilLoadOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilLoadOp = newValue } } 

		override var stencilStoreOp: WGPUStoreOp
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilStoreOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilStoreOp = newValue } } 

		override var stencilClearValue: UInt
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilClearValue = newValue } } 

		override var stencilReadOnly: Boolean
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

	}

	actual var view: WGPUTextureView?
	actual var depthLoadOp: WGPULoadOp
	actual var depthStoreOp: WGPUStoreOp
	actual var depthClearValue: Float
	actual var depthReadOnly: Boolean
	actual var stencilLoadOp: WGPULoadOp
	actual var stencilStoreOp: WGPUStoreOp
	actual var stencilClearValue: UInt
	actual var stencilReadOnly: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPassDepthStencilAttachment {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDepthStencilAttachment>())
				.let { WGPURenderPassDepthStencilAttachment(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPassDepthStencilAttachment) -> Unit): ArrayHolder<WGPURenderPassDepthStencilAttachment> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDepthStencilAttachment>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPassDepthStencilAttachment>())
							.let { WGPURenderPassDepthStencilAttachment(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassDepthStencilAttachment> {
		return cValue<webgpu.native.WGPURenderPassDepthStencilAttachment> {
			view = this@WGPURenderPassDepthStencilAttachment.view?.handler?.toCPointer()
			depthLoadOp = this@WGPURenderPassDepthStencilAttachment.depthLoadOp
			depthStoreOp = this@WGPURenderPassDepthStencilAttachment.depthStoreOp
			depthClearValue = this@WGPURenderPassDepthStencilAttachment.depthClearValue
			depthReadOnly = this@WGPURenderPassDepthStencilAttachment.depthReadOnly.toUInt()
			stencilLoadOp = this@WGPURenderPassDepthStencilAttachment.stencilLoadOp
			stencilStoreOp = this@WGPURenderPassDepthStencilAttachment.stencilStoreOp
			stencilClearValue = this@WGPURenderPassDepthStencilAttachment.stencilClearValue
			stencilReadOnly = this@WGPURenderPassDepthStencilAttachment.stencilReadOnly.toUInt()
		}
	}
}

fun webgpu.native.WGPURenderPassDepthStencilAttachment.adapt(structure: WGPURenderPassDepthStencilAttachment) {
	view = structure.view?.handler?.toCPointer()
	depthLoadOp = structure.depthLoadOp
	depthStoreOp = structure.depthStoreOp
	depthClearValue = structure.depthClearValue
	depthReadOnly = structure.depthReadOnly.toUInt()
	stencilLoadOp = structure.stencilLoadOp
	stencilStoreOp = structure.stencilStoreOp
	stencilClearValue = structure.stencilClearValue
	stencilReadOnly = structure.stencilReadOnly.toUInt()
}

actual interface WGPURenderPassDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPassDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderPassDescriptor is null")

		override var colorAttachmentCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.colorAttachmentCount ?: error("pointer of WGPURenderPassDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.colorAttachmentCount = newValue } } 

		override var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.colorAttachments?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPURenderPassColorAttachment>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.colorAttachments = newValue?.handler?.toCPointer() } } 

		override var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.depthStencilAttachment?.toLong()?.takeIf {it != 0L}?.let { WGPURenderPassDepthStencilAttachment(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.depthStencilAttachment = newValue?.handler?.toCPointer() } } 

		override var occlusionQuerySet: WGPUQuerySet?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.occlusionQuerySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.occlusionQuerySet = newValue?.handler?.toCPointer() } } 

		override var timestampWrites: WGPURenderPassTimestampWrites?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.timestampWrites?.toLong()?.takeIf {it != 0L}?.let { WGPURenderPassTimestampWrites(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.timestampWrites = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var colorAttachmentCount: ULong
	actual var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
	actual var occlusionQuerySet: WGPUQuerySet?
	actual var timestampWrites: WGPURenderPassTimestampWrites?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPassDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDescriptor>())
				.let { WGPURenderPassDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPassDescriptor) -> Unit): ArrayHolder<WGPURenderPassDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPassDescriptor>())
							.let { WGPURenderPassDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassDescriptor> {
		return cValue<webgpu.native.WGPURenderPassDescriptor> {
			label.adapt(this@WGPURenderPassDescriptor.label)
			nextInChain = this@WGPURenderPassDescriptor.nextInChain?.toCPointer()
			colorAttachmentCount = this@WGPURenderPassDescriptor.colorAttachmentCount
			colorAttachments = this@WGPURenderPassDescriptor.colorAttachments?.handler?.toCPointer()
			depthStencilAttachment = this@WGPURenderPassDescriptor.depthStencilAttachment?.handler?.toCPointer()
			occlusionQuerySet = this@WGPURenderPassDescriptor.occlusionQuerySet?.handler?.toCPointer()
			timestampWrites = this@WGPURenderPassDescriptor.timestampWrites?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURenderPassDescriptor.adapt(structure: WGPURenderPassDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	colorAttachmentCount = structure.colorAttachmentCount
	colorAttachments = structure.colorAttachments?.handler?.toCPointer()
	depthStencilAttachment = structure.depthStencilAttachment?.handler?.toCPointer()
	occlusionQuerySet = structure.occlusionQuerySet?.handler?.toCPointer()
	timestampWrites = structure.timestampWrites?.handler?.toCPointer()
}

actual interface WGPUChainedStruct {
	value class ByReference(override val handler: NativeAddress) : WGPUChainedStruct {
		override var next: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.next?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.let { it.next = newValue?.handler?.toCPointer() } } 

		override var sType: WGPUSType
			get() = handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.sType ?: error("pointer of WGPUChainedStruct is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.let { it.sType = newValue } } 

	}

	actual var next: WGPUChainedStruct?
	actual var sType: WGPUSType
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUChainedStruct {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStruct>())
				.let { WGPUChainedStruct(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUChainedStruct) -> Unit): ArrayHolder<WGPUChainedStruct> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStruct>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUChainedStruct>())
							.let { WGPUChainedStruct(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUChainedStruct> {
		return cValue<webgpu.native.WGPUChainedStruct> {
			next = this@WGPUChainedStruct.next?.handler?.toCPointer()
			sType = this@WGPUChainedStruct.sType
		}
	}
}

fun webgpu.native.WGPUChainedStruct.adapt(structure: WGPUChainedStruct) {
	next = structure.next?.handler?.toCPointer()
	sType = structure.sType
}

actual interface WGPURenderPassMaxDrawCount {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPassMaxDrawCount {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPURenderPassMaxDrawCount is null")

		override var maxDrawCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.maxDrawCount ?: error("pointer of WGPURenderPassMaxDrawCount is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.let { it.maxDrawCount = newValue } } 

	}

	actual val chain: WGPUChainedStruct
	actual var maxDrawCount: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPassMaxDrawCount {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassMaxDrawCount>())
				.let { WGPURenderPassMaxDrawCount(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPassMaxDrawCount) -> Unit): ArrayHolder<WGPURenderPassMaxDrawCount> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassMaxDrawCount>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPassMaxDrawCount>())
							.let { WGPURenderPassMaxDrawCount(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassMaxDrawCount> {
		return cValue<webgpu.native.WGPURenderPassMaxDrawCount> {
			chain.adapt(this@WGPURenderPassMaxDrawCount.chain)
			maxDrawCount = this@WGPURenderPassMaxDrawCount.maxDrawCount
		}
	}
}

fun webgpu.native.WGPURenderPassMaxDrawCount.adapt(structure: WGPURenderPassMaxDrawCount) {
	chain.adapt(structure.chain)
	maxDrawCount = structure.maxDrawCount
}

actual interface WGPURenderPassTimestampWrites {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPassTimestampWrites {
		override var querySet: WGPUQuerySet?
			get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.querySet = newValue?.handler?.toCPointer() } } 

		override var beginningOfPassWriteIndex: UInt
			get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.beginningOfPassWriteIndex ?: error("pointer of WGPURenderPassTimestampWrites is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.beginningOfPassWriteIndex = newValue } } 

		override var endOfPassWriteIndex: UInt
			get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.endOfPassWriteIndex ?: error("pointer of WGPURenderPassTimestampWrites is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.endOfPassWriteIndex = newValue } } 

	}

	actual var querySet: WGPUQuerySet?
	actual var beginningOfPassWriteIndex: UInt
	actual var endOfPassWriteIndex: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPassTimestampWrites {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassTimestampWrites {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassTimestampWrites>())
				.let { WGPURenderPassTimestampWrites(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPassTimestampWrites) -> Unit): ArrayHolder<WGPURenderPassTimestampWrites> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassTimestampWrites>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPassTimestampWrites>())
							.let { WGPURenderPassTimestampWrites(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassTimestampWrites> {
		return cValue<webgpu.native.WGPURenderPassTimestampWrites> {
			querySet = this@WGPURenderPassTimestampWrites.querySet?.handler?.toCPointer()
			beginningOfPassWriteIndex = this@WGPURenderPassTimestampWrites.beginningOfPassWriteIndex
			endOfPassWriteIndex = this@WGPURenderPassTimestampWrites.endOfPassWriteIndex
		}
	}
}

fun webgpu.native.WGPURenderPassTimestampWrites.adapt(structure: WGPURenderPassTimestampWrites) {
	querySet = structure.querySet?.handler?.toCPointer()
	beginningOfPassWriteIndex = structure.beginningOfPassWriteIndex
	endOfPassWriteIndex = structure.endOfPassWriteIndex
}

actual interface WGPUVertexState {
	value class ByReference(override val handler: NativeAddress) : WGPUVertexState {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var module: WGPUShaderModule?
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

		override val entryPoint: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUVertexState is null")

		override var constantCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.constantCount ?: error("pointer of WGPUVertexState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.constantCount = newValue } } 

		override var constants: ArrayHolder<WGPUConstantEntry>?
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

		override var bufferCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.bufferCount ?: error("pointer of WGPUVertexState is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.bufferCount = newValue } } 

		override var buffers: ArrayHolder<WGPUVertexBufferLayout>?
			get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.buffers?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUVertexBufferLayout>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.buffers = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var module: WGPUShaderModule?
	actual val entryPoint: WGPUStringView
	actual var constantCount: ULong
	actual var constants: ArrayHolder<WGPUConstantEntry>?
	actual var bufferCount: ULong
	actual var buffers: ArrayHolder<WGPUVertexBufferLayout>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUVertexState {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUVertexState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexState>())
				.let { WGPUVertexState(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUVertexState) -> Unit): ArrayHolder<WGPUVertexState> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexState>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUVertexState>())
							.let { WGPUVertexState(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUVertexState> {
		return cValue<webgpu.native.WGPUVertexState> {
			entryPoint.adapt(this@WGPUVertexState.entryPoint)
			nextInChain = this@WGPUVertexState.nextInChain?.toCPointer()
			module = this@WGPUVertexState.module?.handler?.toCPointer()
			constantCount = this@WGPUVertexState.constantCount
			constants = this@WGPUVertexState.constants?.handler?.toCPointer()
			bufferCount = this@WGPUVertexState.bufferCount
			buffers = this@WGPUVertexState.buffers?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUVertexState.adapt(structure: WGPUVertexState) {
	entryPoint.adapt(structure.entryPoint)
	nextInChain = structure.nextInChain?.toCPointer()
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
	bufferCount = structure.bufferCount
	buffers = structure.buffers?.handler?.toCPointer()
}

actual interface WGPURenderPipelineDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPURenderPipelineDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

		override var layout: WGPUPipelineLayout?
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

		override val vertex: WGPUVertexState
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.vertex?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUVertexState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

		override val primitive: WGPUPrimitiveState
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.primitive?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUPrimitiveState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

		override var depthStencil: WGPUDepthStencilState?
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.depthStencil?.toLong()?.takeIf {it != 0L}?.let { WGPUDepthStencilState(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.depthStencil = newValue?.handler?.toCPointer() } } 

		override val multisample: WGPUMultisampleState
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.multisample?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUMultisampleState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

		override var fragment: WGPUFragmentState?
			get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.fragment?.toLong()?.takeIf {it != 0L}?.let { WGPUFragmentState(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.fragment = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var layout: WGPUPipelineLayout?
	actual val vertex: WGPUVertexState
	actual val primitive: WGPUPrimitiveState
	actual var depthStencil: WGPUDepthStencilState?
	actual val multisample: WGPUMultisampleState
	actual var fragment: WGPUFragmentState?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURenderPipelineDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPipelineDescriptor>())
				.let { WGPURenderPipelineDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURenderPipelineDescriptor) -> Unit): ArrayHolder<WGPURenderPipelineDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPipelineDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURenderPipelineDescriptor>())
							.let { WGPURenderPipelineDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPipelineDescriptor> {
		return cValue<webgpu.native.WGPURenderPipelineDescriptor> {
			label.adapt(this@WGPURenderPipelineDescriptor.label)
			vertex.adapt(this@WGPURenderPipelineDescriptor.vertex)
			primitive.adapt(this@WGPURenderPipelineDescriptor.primitive)
			multisample.adapt(this@WGPURenderPipelineDescriptor.multisample)
			nextInChain = this@WGPURenderPipelineDescriptor.nextInChain?.toCPointer()
			layout = this@WGPURenderPipelineDescriptor.layout?.handler?.toCPointer()
			depthStencil = this@WGPURenderPipelineDescriptor.depthStencil?.handler?.toCPointer()
			fragment = this@WGPURenderPipelineDescriptor.fragment?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURenderPipelineDescriptor.adapt(structure: WGPURenderPipelineDescriptor) {
	label.adapt(structure.label)
	vertex.adapt(structure.vertex)
	primitive.adapt(structure.primitive)
	multisample.adapt(structure.multisample)
	nextInChain = structure.nextInChain?.toCPointer()
	layout = structure.layout?.handler?.toCPointer()
	depthStencil = structure.depthStencil?.handler?.toCPointer()
	fragment = structure.fragment?.handler?.toCPointer()
}

actual interface WGPURequestAdapterOptions {
	value class ByReference(override val handler: NativeAddress) : WGPURequestAdapterOptions {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var compatibleSurface: WGPUSurface?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.compatibleSurface?.toLong()?.takeIf {it != 0L}?.let { WGPUSurface(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.compatibleSurface = newValue?.handler?.toCPointer() } } 

		override var powerPreference: WGPUPowerPreference
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.powerPreference ?: error("pointer of WGPURequestAdapterOptions is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.powerPreference = newValue } } 

		override var backendType: WGPUBackendType
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.backendType ?: error("pointer of WGPURequestAdapterOptions is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.backendType = newValue } } 

		override var forceFallbackAdapter: Boolean
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.forceFallbackAdapter?.toBoolean() ?: error("pointer of WGPURequestAdapterOptions is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.forceFallbackAdapter = newValue.toUInt() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var compatibleSurface: WGPUSurface?
	actual var powerPreference: WGPUPowerPreference
	actual var backendType: WGPUBackendType
	actual var forceFallbackAdapter: Boolean
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURequestAdapterOptions {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterOptions>())
				.let { WGPURequestAdapterOptions(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURequestAdapterOptions) -> Unit): ArrayHolder<WGPURequestAdapterOptions> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterOptions>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURequestAdapterOptions>())
							.let { WGPURequestAdapterOptions(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequestAdapterOptions> {
		return cValue<webgpu.native.WGPURequestAdapterOptions> {
			nextInChain = this@WGPURequestAdapterOptions.nextInChain?.toCPointer()
			compatibleSurface = this@WGPURequestAdapterOptions.compatibleSurface?.handler?.toCPointer()
			powerPreference = this@WGPURequestAdapterOptions.powerPreference
			backendType = this@WGPURequestAdapterOptions.backendType
			forceFallbackAdapter = this@WGPURequestAdapterOptions.forceFallbackAdapter.toUInt()
		}
	}
}

fun webgpu.native.WGPURequestAdapterOptions.adapt(structure: WGPURequestAdapterOptions) {
	nextInChain = structure.nextInChain?.toCPointer()
	compatibleSurface = structure.compatibleSurface?.handler?.toCPointer()
	powerPreference = structure.powerPreference
	backendType = structure.backendType
	forceFallbackAdapter = structure.forceFallbackAdapter.toUInt()
}

actual interface WGPURequiredLimits {
	value class ByReference(override val handler: NativeAddress) : WGPURequiredLimits {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequiredLimits>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequiredLimits>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val limits: WGPULimits
			get() = handler.toCPointer<webgpu.native.WGPURequiredLimits>()?.pointed?.limits?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPULimits(it) } ?: error("pointer of WGPURequiredLimits is null")

	}

	actual var nextInChain: NativeAddress?
	actual val limits: WGPULimits
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURequiredLimits {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURequiredLimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequiredLimits>())
				.let { WGPURequiredLimits(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURequiredLimits) -> Unit): ArrayHolder<WGPURequiredLimits> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequiredLimits>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURequiredLimits>())
							.let { WGPURequiredLimits(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequiredLimits> {
		return cValue<webgpu.native.WGPURequiredLimits> {
			limits.adapt(this@WGPURequiredLimits.limits)
			nextInChain = this@WGPURequiredLimits.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURequiredLimits.adapt(structure: WGPURequiredLimits) {
	limits.adapt(structure.limits)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUSamplerDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUSamplerDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUSamplerDescriptor is null")

		override var addressModeU: WGPUAddressMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeU ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeU = newValue } } 

		override var addressModeV: WGPUAddressMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeV ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeV = newValue } } 

		override var addressModeW: WGPUAddressMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeW ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeW = newValue } } 

		override var magFilter: WGPUFilterMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.magFilter ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.magFilter = newValue } } 

		override var minFilter: WGPUFilterMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.minFilter ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.minFilter = newValue } } 

		override var mipmapFilter: WGPUMipmapFilterMode
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.mipmapFilter ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.mipmapFilter = newValue } } 

		override var lodMinClamp: Float
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMinClamp ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMinClamp = newValue } } 

		override var lodMaxClamp: Float
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMaxClamp ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMaxClamp = newValue } } 

		override var compare: WGPUCompareFunction
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.compare ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.compare = newValue } } 

		override var maxAnisotropy: UShort
			get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.maxAnisotropy ?: error("pointer of WGPUSamplerDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.maxAnisotropy = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var addressModeU: WGPUAddressMode
	actual var addressModeV: WGPUAddressMode
	actual var addressModeW: WGPUAddressMode
	actual var magFilter: WGPUFilterMode
	actual var minFilter: WGPUFilterMode
	actual var mipmapFilter: WGPUMipmapFilterMode
	actual var lodMinClamp: Float
	actual var lodMaxClamp: Float
	actual var compare: WGPUCompareFunction
	actual var maxAnisotropy: UShort
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSamplerDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerDescriptor>())
				.let { WGPUSamplerDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSamplerDescriptor) -> Unit): ArrayHolder<WGPUSamplerDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSamplerDescriptor>())
							.let { WGPUSamplerDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSamplerDescriptor> {
		return cValue<webgpu.native.WGPUSamplerDescriptor> {
			label.adapt(this@WGPUSamplerDescriptor.label)
			nextInChain = this@WGPUSamplerDescriptor.nextInChain?.toCPointer()
			addressModeU = this@WGPUSamplerDescriptor.addressModeU
			addressModeV = this@WGPUSamplerDescriptor.addressModeV
			addressModeW = this@WGPUSamplerDescriptor.addressModeW
			magFilter = this@WGPUSamplerDescriptor.magFilter
			minFilter = this@WGPUSamplerDescriptor.minFilter
			mipmapFilter = this@WGPUSamplerDescriptor.mipmapFilter
			lodMinClamp = this@WGPUSamplerDescriptor.lodMinClamp
			lodMaxClamp = this@WGPUSamplerDescriptor.lodMaxClamp
			compare = this@WGPUSamplerDescriptor.compare
			maxAnisotropy = this@WGPUSamplerDescriptor.maxAnisotropy
		}
	}
}

fun webgpu.native.WGPUSamplerDescriptor.adapt(structure: WGPUSamplerDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	addressModeU = structure.addressModeU
	addressModeV = structure.addressModeV
	addressModeW = structure.addressModeW
	magFilter = structure.magFilter
	minFilter = structure.minFilter
	mipmapFilter = structure.mipmapFilter
	lodMinClamp = structure.lodMinClamp
	lodMaxClamp = structure.lodMaxClamp
	compare = structure.compare
	maxAnisotropy = structure.maxAnisotropy
}

actual interface WGPUShaderModuleDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUShaderModuleDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUShaderModuleDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderModuleDescriptor>())
				.let { WGPUShaderModuleDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUShaderModuleDescriptor) -> Unit): ArrayHolder<WGPUShaderModuleDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderModuleDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUShaderModuleDescriptor>())
							.let { WGPUShaderModuleDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderModuleDescriptor> {
		return cValue<webgpu.native.WGPUShaderModuleDescriptor> {
			label.adapt(this@WGPUShaderModuleDescriptor.label)
			nextInChain = this@WGPUShaderModuleDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUShaderModuleDescriptor.adapt(structure: WGPUShaderModuleDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUShaderSourceSPIRV {
	value class ByReference(override val handler: NativeAddress) : WGPUShaderSourceSPIRV {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUShaderSourceSPIRV is null")

		override var codeSize: UInt
			get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.codeSize ?: error("pointer of WGPUShaderSourceSPIRV is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.codeSize = newValue } } 

		override var code: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.code?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.code = newValue?.toCPointer() } } 

	}

	actual val chain: WGPUChainedStruct
	actual var codeSize: UInt
	actual var code: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUShaderSourceSPIRV {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceSPIRV>())
				.let { WGPUShaderSourceSPIRV(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUShaderSourceSPIRV) -> Unit): ArrayHolder<WGPUShaderSourceSPIRV> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceSPIRV>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUShaderSourceSPIRV>())
							.let { WGPUShaderSourceSPIRV(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderSourceSPIRV> {
		return cValue<webgpu.native.WGPUShaderSourceSPIRV> {
			chain.adapt(this@WGPUShaderSourceSPIRV.chain)
			codeSize = this@WGPUShaderSourceSPIRV.codeSize
			code = this@WGPUShaderSourceSPIRV.code?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUShaderSourceSPIRV.adapt(structure: WGPUShaderSourceSPIRV) {
	chain.adapt(structure.chain)
	codeSize = structure.codeSize
	code = structure.code?.toCPointer()
}

actual interface WGPUShaderSourceWGSL {
	value class ByReference(override val handler: NativeAddress) : WGPUShaderSourceWGSL {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUShaderSourceWGSL is null")

		override val code: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.code?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUShaderSourceWGSL is null")

	}

	actual val chain: WGPUChainedStruct
	actual val code: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUShaderSourceWGSL {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceWGSL>())
				.let { WGPUShaderSourceWGSL(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUShaderSourceWGSL) -> Unit): ArrayHolder<WGPUShaderSourceWGSL> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceWGSL>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUShaderSourceWGSL>())
							.let { WGPUShaderSourceWGSL(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderSourceWGSL> {
		return cValue<webgpu.native.WGPUShaderSourceWGSL> {
			chain.adapt(this@WGPUShaderSourceWGSL.chain)
			code.adapt(this@WGPUShaderSourceWGSL.code)
		}
	}
}

fun webgpu.native.WGPUShaderSourceWGSL.adapt(structure: WGPUShaderSourceWGSL) {
	chain.adapt(structure.chain)
	code.adapt(structure.code)
}

actual interface WGPUSupportedFeatures {
	value class ByReference(override val handler: NativeAddress) : WGPUSupportedFeatures {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var featureCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.featureCount ?: error("pointer of WGPUSupportedFeatures is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.let { it.featureCount = newValue } } 

		override var features: ArrayHolder<WGPUFeatureName>?
			get() = handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.features?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUFeatureName>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.let { it.features = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var featureCount: ULong
	actual var features: ArrayHolder<WGPUFeatureName>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSupportedFeatures {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedFeatures>())
				.let { WGPUSupportedFeatures(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSupportedFeatures) -> Unit): ArrayHolder<WGPUSupportedFeatures> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedFeatures>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSupportedFeatures>())
							.let { WGPUSupportedFeatures(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSupportedFeatures> {
		return cValue<webgpu.native.WGPUSupportedFeatures> {
			nextInChain = this@WGPUSupportedFeatures.nextInChain?.toCPointer()
			featureCount = this@WGPUSupportedFeatures.featureCount
			features = this@WGPUSupportedFeatures.features?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSupportedFeatures.adapt(structure: WGPUSupportedFeatures) {
	nextInChain = structure.nextInChain?.toCPointer()
	featureCount = structure.featureCount
	features = structure.features?.handler?.toCPointer()
}

actual interface WGPUSupportedLimits {
	value class ByReference(override val handler: NativeAddress) : WGPUSupportedLimits {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSupportedLimits>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedLimits>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val limits: WGPULimits
			get() = handler.toCPointer<webgpu.native.WGPUSupportedLimits>()?.pointed?.limits?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPULimits(it) } ?: error("pointer of WGPUSupportedLimits is null")

	}

	actual var nextInChain: NativeAddress?
	actual val limits: WGPULimits
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSupportedLimits {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedLimits>())
				.let { WGPUSupportedLimits(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSupportedLimits) -> Unit): ArrayHolder<WGPUSupportedLimits> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedLimits>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSupportedLimits>())
							.let { WGPUSupportedLimits(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSupportedLimits> {
		return cValue<webgpu.native.WGPUSupportedLimits> {
			limits.adapt(this@WGPUSupportedLimits.limits)
			nextInChain = this@WGPUSupportedLimits.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSupportedLimits.adapt(structure: WGPUSupportedLimits) {
	limits.adapt(structure.limits)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUSurfaceCapabilities {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceCapabilities {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var usages: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.usages ?: error("pointer of WGPUSurfaceCapabilities is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.usages = newValue } } 

		override var formatCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.formatCount ?: error("pointer of WGPUSurfaceCapabilities is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.formatCount = newValue } } 

		override var formats: ArrayHolder<WGPUTextureFormat>?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.formats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.formats = newValue?.handler?.toCPointer() } } 

		override var presentModeCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.presentModeCount ?: error("pointer of WGPUSurfaceCapabilities is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.presentModeCount = newValue } } 

		override var presentModes: ArrayHolder<WGPUPresentMode>?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.presentModes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUPresentMode>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.presentModes = newValue?.handler?.toCPointer() } } 

		override var alphaModeCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.alphaModeCount ?: error("pointer of WGPUSurfaceCapabilities is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.alphaModeCount = newValue } } 

		override var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.alphaModes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUCompositeAlphaMode>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.alphaModes = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual var usages: ULong
	actual var formatCount: ULong
	actual var formats: ArrayHolder<WGPUTextureFormat>?
	actual var presentModeCount: ULong
	actual var presentModes: ArrayHolder<WGPUPresentMode>?
	actual var alphaModeCount: ULong
	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceCapabilities {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceCapabilities>())
				.let { WGPUSurfaceCapabilities(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceCapabilities) -> Unit): ArrayHolder<WGPUSurfaceCapabilities> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceCapabilities>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceCapabilities>())
							.let { WGPUSurfaceCapabilities(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceCapabilities> {
		return cValue<webgpu.native.WGPUSurfaceCapabilities> {
			nextInChain = this@WGPUSurfaceCapabilities.nextInChain?.toCPointer()
			usages = this@WGPUSurfaceCapabilities.usages
			formatCount = this@WGPUSurfaceCapabilities.formatCount
			formats = this@WGPUSurfaceCapabilities.formats?.handler?.toCPointer()
			presentModeCount = this@WGPUSurfaceCapabilities.presentModeCount
			presentModes = this@WGPUSurfaceCapabilities.presentModes?.handler?.toCPointer()
			alphaModeCount = this@WGPUSurfaceCapabilities.alphaModeCount
			alphaModes = this@WGPUSurfaceCapabilities.alphaModes?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceCapabilities.adapt(structure: WGPUSurfaceCapabilities) {
	nextInChain = structure.nextInChain?.toCPointer()
	usages = structure.usages
	formatCount = structure.formatCount
	formats = structure.formats?.handler?.toCPointer()
	presentModeCount = structure.presentModeCount
	presentModes = structure.presentModes?.handler?.toCPointer()
	alphaModeCount = structure.alphaModeCount
	alphaModes = structure.alphaModes?.handler?.toCPointer()
}

actual interface WGPUSurfaceConfiguration {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceConfiguration {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override var device: WGPUDevice?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.device?.toLong()?.takeIf {it != 0L}?.let { WGPUDevice(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.device = newValue?.handler?.toCPointer() } } 

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.format ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.format = newValue } } 

		override var usage: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.usage ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.usage = newValue } } 

		override var width: UInt
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.width ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.width = newValue } } 

		override var height: UInt
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.height ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.height = newValue } } 

		override var viewFormatCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.viewFormatCount ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.viewFormatCount = newValue } } 

		override var viewFormats: ArrayHolder<WGPUTextureFormat>?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.viewFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.viewFormats = newValue?.handler?.toCPointer() } } 

		override var alphaMode: WGPUCompositeAlphaMode
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.alphaMode ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.alphaMode = newValue } } 

		override var presentMode: WGPUPresentMode
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.presentMode ?: error("pointer of WGPUSurfaceConfiguration is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.presentMode = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual var device: WGPUDevice?
	actual var format: WGPUTextureFormat
	actual var usage: ULong
	actual var width: UInt
	actual var height: UInt
	actual var viewFormatCount: ULong
	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
	actual var alphaMode: WGPUCompositeAlphaMode
	actual var presentMode: WGPUPresentMode
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceConfiguration {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceConfiguration>())
				.let { WGPUSurfaceConfiguration(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceConfiguration) -> Unit): ArrayHolder<WGPUSurfaceConfiguration> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceConfiguration>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceConfiguration>())
							.let { WGPUSurfaceConfiguration(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceConfiguration> {
		return cValue<webgpu.native.WGPUSurfaceConfiguration> {
			nextInChain = this@WGPUSurfaceConfiguration.nextInChain?.toCPointer()
			device = this@WGPUSurfaceConfiguration.device?.handler?.toCPointer()
			format = this@WGPUSurfaceConfiguration.format
			usage = this@WGPUSurfaceConfiguration.usage
			width = this@WGPUSurfaceConfiguration.width
			height = this@WGPUSurfaceConfiguration.height
			viewFormatCount = this@WGPUSurfaceConfiguration.viewFormatCount
			viewFormats = this@WGPUSurfaceConfiguration.viewFormats?.handler?.toCPointer()
			alphaMode = this@WGPUSurfaceConfiguration.alphaMode
			presentMode = this@WGPUSurfaceConfiguration.presentMode
		}
	}
}

fun webgpu.native.WGPUSurfaceConfiguration.adapt(structure: WGPUSurfaceConfiguration) {
	nextInChain = structure.nextInChain?.toCPointer()
	device = structure.device?.handler?.toCPointer()
	format = structure.format
	usage = structure.usage
	width = structure.width
	height = structure.height
	viewFormatCount = structure.viewFormatCount
	viewFormats = structure.viewFormats?.handler?.toCPointer()
	alphaMode = structure.alphaMode
	presentMode = structure.presentMode
}

actual interface WGPUSurfaceDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUSurfaceDescriptor is null")

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceDescriptor>())
				.let { WGPUSurfaceDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceDescriptor) -> Unit): ArrayHolder<WGPUSurfaceDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceDescriptor>())
							.let { WGPUSurfaceDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceDescriptor> {
		return cValue<webgpu.native.WGPUSurfaceDescriptor> {
			label.adapt(this@WGPUSurfaceDescriptor.label)
			nextInChain = this@WGPUSurfaceDescriptor.nextInChain?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceDescriptor.adapt(structure: WGPUSurfaceDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
}

actual interface WGPUSurfaceSourceAndroidNativeWindow {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceAndroidNativeWindow {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceAndroidNativeWindow is null")

		override var window: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.window?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.let { it.window = newValue?.toCPointer() } } 

	}

	actual val chain: WGPUChainedStruct
	actual var window: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceAndroidNativeWindow {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>())
				.let { WGPUSurfaceSourceAndroidNativeWindow(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceAndroidNativeWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceAndroidNativeWindow> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>())
							.let { WGPUSurfaceSourceAndroidNativeWindow(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow> {
			chain.adapt(this@WGPUSurfaceSourceAndroidNativeWindow.chain)
			window = this@WGPUSurfaceSourceAndroidNativeWindow.window?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceAndroidNativeWindow.adapt(structure: WGPUSurfaceSourceAndroidNativeWindow) {
	chain.adapt(structure.chain)
	window = structure.window?.toCPointer()
}

actual interface WGPUSurfaceSourceMetalLayer {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceMetalLayer {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceMetalLayer is null")

		override var layer: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.layer?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.let { it.layer = newValue?.toCPointer() } } 

	}

	actual val chain: WGPUChainedStruct
	actual var layer: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceMetalLayer {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceMetalLayer>())
				.let { WGPUSurfaceSourceMetalLayer(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceMetalLayer) -> Unit): ArrayHolder<WGPUSurfaceSourceMetalLayer> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceMetalLayer>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceMetalLayer>())
							.let { WGPUSurfaceSourceMetalLayer(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceMetalLayer> {
		return cValue<webgpu.native.WGPUSurfaceSourceMetalLayer> {
			chain.adapt(this@WGPUSurfaceSourceMetalLayer.chain)
			layer = this@WGPUSurfaceSourceMetalLayer.layer?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceMetalLayer.adapt(structure: WGPUSurfaceSourceMetalLayer) {
	chain.adapt(structure.chain)
	layer = structure.layer?.toCPointer()
}

actual interface WGPUSurfaceSourceWaylandSurface {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceWaylandSurface {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceWaylandSurface is null")

		override var display: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.display?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.display = newValue?.toCPointer() } } 

		override var surface: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.surface?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.surface = newValue?.toCPointer() } } 

	}

	actual val chain: WGPUChainedStruct
	actual var display: NativeAddress?
	actual var surface: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWaylandSurface {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWaylandSurface>())
				.let { WGPUSurfaceSourceWaylandSurface(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceWaylandSurface) -> Unit): ArrayHolder<WGPUSurfaceSourceWaylandSurface> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWaylandSurface>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceWaylandSurface>())
							.let { WGPUSurfaceSourceWaylandSurface(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceWaylandSurface> {
		return cValue<webgpu.native.WGPUSurfaceSourceWaylandSurface> {
			chain.adapt(this@WGPUSurfaceSourceWaylandSurface.chain)
			display = this@WGPUSurfaceSourceWaylandSurface.display?.toCPointer()
			surface = this@WGPUSurfaceSourceWaylandSurface.surface?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceWaylandSurface.adapt(structure: WGPUSurfaceSourceWaylandSurface) {
	chain.adapt(structure.chain)
	display = structure.display?.toCPointer()
	surface = structure.surface?.toCPointer()
}

actual interface WGPUSurfaceSourceWindowsHWND {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceWindowsHWND {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceWindowsHWND is null")

		override var hinstance: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hinstance?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hinstance = newValue?.toCPointer() } } 

		override var hwnd: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hwnd?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hwnd = newValue?.toCPointer() } } 

	}

	actual val chain: WGPUChainedStruct
	actual var hinstance: NativeAddress?
	actual var hwnd: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWindowsHWND {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWindowsHWND>())
				.let { WGPUSurfaceSourceWindowsHWND(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceWindowsHWND) -> Unit): ArrayHolder<WGPUSurfaceSourceWindowsHWND> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWindowsHWND>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceWindowsHWND>())
							.let { WGPUSurfaceSourceWindowsHWND(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceWindowsHWND> {
		return cValue<webgpu.native.WGPUSurfaceSourceWindowsHWND> {
			chain.adapt(this@WGPUSurfaceSourceWindowsHWND.chain)
			hinstance = this@WGPUSurfaceSourceWindowsHWND.hinstance?.toCPointer()
			hwnd = this@WGPUSurfaceSourceWindowsHWND.hwnd?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceWindowsHWND.adapt(structure: WGPUSurfaceSourceWindowsHWND) {
	chain.adapt(structure.chain)
	hinstance = structure.hinstance?.toCPointer()
	hwnd = structure.hwnd?.toCPointer()
}

actual interface WGPUSurfaceSourceXCBWindow {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceXCBWindow {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceXCBWindow is null")

		override var connection: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.connection?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.connection = newValue?.toCPointer() } } 

		override var window: UInt
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXCBWindow is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.window = newValue } } 

	}

	actual val chain: WGPUChainedStruct
	actual var connection: NativeAddress?
	actual var window: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXCBWindow {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXCBWindow>())
				.let { WGPUSurfaceSourceXCBWindow(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceXCBWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXCBWindow> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXCBWindow>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceXCBWindow>())
							.let { WGPUSurfaceSourceXCBWindow(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceXCBWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceXCBWindow> {
			chain.adapt(this@WGPUSurfaceSourceXCBWindow.chain)
			connection = this@WGPUSurfaceSourceXCBWindow.connection?.toCPointer()
			window = this@WGPUSurfaceSourceXCBWindow.window
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceXCBWindow.adapt(structure: WGPUSurfaceSourceXCBWindow) {
	chain.adapt(structure.chain)
	connection = structure.connection?.toCPointer()
	window = structure.window
}

actual interface WGPUSurfaceSourceXlibWindow {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceXlibWindow {
		override val chain: WGPUChainedStruct
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.chain?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) } ?: error("pointer of WGPUSurfaceSourceXlibWindow is null")

		override var display: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.display?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.display = newValue?.toCPointer() } } 

		override var window: ULong
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXlibWindow is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.window = newValue } } 

	}

	actual val chain: WGPUChainedStruct
	actual var display: NativeAddress?
	actual var window: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXlibWindow {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXlibWindow>())
				.let { WGPUSurfaceSourceXlibWindow(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceSourceXlibWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXlibWindow> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXlibWindow>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceSourceXlibWindow>())
							.let { WGPUSurfaceSourceXlibWindow(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceXlibWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceXlibWindow> {
			chain.adapt(this@WGPUSurfaceSourceXlibWindow.chain)
			display = this@WGPUSurfaceSourceXlibWindow.display?.toCPointer()
			window = this@WGPUSurfaceSourceXlibWindow.window
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceXlibWindow.adapt(structure: WGPUSurfaceSourceXlibWindow) {
	chain.adapt(structure.chain)
	display = structure.display?.toCPointer()
	window = structure.window
}

actual interface WGPUSurfaceTexture {
	value class ByReference(override val handler: NativeAddress) : WGPUSurfaceTexture {
		override var texture: WGPUTexture?
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

		override var status: WGPUSurfaceGetCurrentTextureStatus
			get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.status ?: error("pointer of WGPUSurfaceTexture is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.let { it.status = newValue } } 

	}

	actual var texture: WGPUTexture?
	actual var status: WGPUSurfaceGetCurrentTextureStatus
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUSurfaceTexture {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceTexture>())
				.let { WGPUSurfaceTexture(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUSurfaceTexture) -> Unit): ArrayHolder<WGPUSurfaceTexture> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceTexture>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUSurfaceTexture>())
							.let { WGPUSurfaceTexture(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceTexture> {
		return cValue<webgpu.native.WGPUSurfaceTexture> {
			texture = this@WGPUSurfaceTexture.texture?.handler?.toCPointer()
			status = this@WGPUSurfaceTexture.status
		}
	}
}

fun webgpu.native.WGPUSurfaceTexture.adapt(structure: WGPUSurfaceTexture) {
	texture = structure.texture?.handler?.toCPointer()
	status = structure.status
}

actual interface WGPUTextureDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUTextureDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUTextureDescriptor is null")

		override var usage: ULong
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.usage = newValue } } 

		override var dimension: WGPUTextureDimension
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.dimension ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.dimension = newValue } } 

		override val size: WGPUExtent3D
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.size?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUExtent3D(it) } ?: error("pointer of WGPUTextureDescriptor is null")

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.format ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.format = newValue } } 

		override var mipLevelCount: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.mipLevelCount ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.mipLevelCount = newValue } } 

		override var sampleCount: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

		override var viewFormatCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.viewFormatCount ?: error("pointer of WGPUTextureDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.viewFormatCount = newValue } } 

		override var viewFormats: ArrayHolder<WGPUTextureFormat>?
			get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.viewFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.viewFormats = newValue?.handler?.toCPointer() } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var usage: ULong
	actual var dimension: WGPUTextureDimension
	actual val size: WGPUExtent3D
	actual var format: WGPUTextureFormat
	actual var mipLevelCount: UInt
	actual var sampleCount: UInt
	actual var viewFormatCount: ULong
	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUTextureDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDescriptor>())
				.let { WGPUTextureDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUTextureDescriptor) -> Unit): ArrayHolder<WGPUTextureDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUTextureDescriptor>())
							.let { WGPUTextureDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureDescriptor> {
		return cValue<webgpu.native.WGPUTextureDescriptor> {
			label.adapt(this@WGPUTextureDescriptor.label)
			size.adapt(this@WGPUTextureDescriptor.size)
			nextInChain = this@WGPUTextureDescriptor.nextInChain?.toCPointer()
			usage = this@WGPUTextureDescriptor.usage
			dimension = this@WGPUTextureDescriptor.dimension
			format = this@WGPUTextureDescriptor.format
			mipLevelCount = this@WGPUTextureDescriptor.mipLevelCount
			sampleCount = this@WGPUTextureDescriptor.sampleCount
			viewFormatCount = this@WGPUTextureDescriptor.viewFormatCount
			viewFormats = this@WGPUTextureDescriptor.viewFormats?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUTextureDescriptor.adapt(structure: WGPUTextureDescriptor) {
	label.adapt(structure.label)
	size.adapt(structure.size)
	nextInChain = structure.nextInChain?.toCPointer()
	usage = structure.usage
	dimension = structure.dimension
	format = structure.format
	mipLevelCount = structure.mipLevelCount
	sampleCount = structure.sampleCount
	viewFormatCount = structure.viewFormatCount
	viewFormats = structure.viewFormats?.handler?.toCPointer()
}

actual interface WGPUTextureViewDescriptor {
	value class ByReference(override val handler: NativeAddress) : WGPUTextureViewDescriptor {
		override var nextInChain: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.nextInChain = newValue?.toCPointer() } } 

		override val label: WGPUStringView
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUTextureViewDescriptor is null")

		override var format: WGPUTextureFormat
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.format ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.format = newValue } } 

		override var dimension: WGPUTextureViewDimension
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.dimension ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.dimension = newValue } } 

		override var baseMipLevel: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.baseMipLevel ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.baseMipLevel = newValue } } 

		override var mipLevelCount: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.mipLevelCount ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.mipLevelCount = newValue } } 

		override var baseArrayLayer: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.baseArrayLayer ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.baseArrayLayer = newValue } } 

		override var arrayLayerCount: UInt
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.arrayLayerCount ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.arrayLayerCount = newValue } } 

		override var aspect: WGPUTextureAspect
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.aspect ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.aspect = newValue } } 

		override var usage: ULong
			get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureViewDescriptor is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.usage = newValue } } 

	}

	actual var nextInChain: NativeAddress?
	actual val label: WGPUStringView
	actual var format: WGPUTextureFormat
	actual var dimension: WGPUTextureViewDimension
	actual var baseMipLevel: UInt
	actual var mipLevelCount: UInt
	actual var baseArrayLayer: UInt
	actual var arrayLayerCount: UInt
	actual var aspect: WGPUTextureAspect
	actual var usage: ULong
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUTextureViewDescriptor {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureViewDescriptor>())
				.let { WGPUTextureViewDescriptor(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUTextureViewDescriptor) -> Unit): ArrayHolder<WGPUTextureViewDescriptor> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureViewDescriptor>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUTextureViewDescriptor>())
							.let { WGPUTextureViewDescriptor(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureViewDescriptor> {
		return cValue<webgpu.native.WGPUTextureViewDescriptor> {
			label.adapt(this@WGPUTextureViewDescriptor.label)
			nextInChain = this@WGPUTextureViewDescriptor.nextInChain?.toCPointer()
			format = this@WGPUTextureViewDescriptor.format
			dimension = this@WGPUTextureViewDescriptor.dimension
			baseMipLevel = this@WGPUTextureViewDescriptor.baseMipLevel
			mipLevelCount = this@WGPUTextureViewDescriptor.mipLevelCount
			baseArrayLayer = this@WGPUTextureViewDescriptor.baseArrayLayer
			arrayLayerCount = this@WGPUTextureViewDescriptor.arrayLayerCount
			aspect = this@WGPUTextureViewDescriptor.aspect
			usage = this@WGPUTextureViewDescriptor.usage
		}
	}
}

fun webgpu.native.WGPUTextureViewDescriptor.adapt(structure: WGPUTextureViewDescriptor) {
	label.adapt(structure.label)
	nextInChain = structure.nextInChain?.toCPointer()
	format = structure.format
	dimension = structure.dimension
	baseMipLevel = structure.baseMipLevel
	mipLevelCount = structure.mipLevelCount
	baseArrayLayer = structure.baseArrayLayer
	arrayLayerCount = structure.arrayLayerCount
	aspect = structure.aspect
	usage = structure.usage
}

actual interface WGPUVertexAttribute {
	value class ByReference(override val handler: NativeAddress) : WGPUVertexAttribute {
		override var format: WGPUVertexFormat
			get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.format ?: error("pointer of WGPUVertexAttribute is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.format = newValue } } 

		override var offset: ULong
			get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.offset ?: error("pointer of WGPUVertexAttribute is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.offset = newValue } } 

		override var shaderLocation: UInt
			get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.shaderLocation ?: error("pointer of WGPUVertexAttribute is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.shaderLocation = newValue } } 

	}

	actual var format: WGPUVertexFormat
	actual var offset: ULong
	actual var shaderLocation: UInt
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUVertexAttribute {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexAttribute>())
				.let { WGPUVertexAttribute(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUVertexAttribute) -> Unit): ArrayHolder<WGPUVertexAttribute> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexAttribute>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUVertexAttribute>())
							.let { WGPUVertexAttribute(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUVertexAttribute> {
		return cValue<webgpu.native.WGPUVertexAttribute> {
			format = this@WGPUVertexAttribute.format
			offset = this@WGPUVertexAttribute.offset
			shaderLocation = this@WGPUVertexAttribute.shaderLocation
		}
	}
}

fun webgpu.native.WGPUVertexAttribute.adapt(structure: WGPUVertexAttribute) {
	format = structure.format
	offset = structure.offset
	shaderLocation = structure.shaderLocation
}

actual interface WGPUVertexBufferLayout {
	value class ByReference(override val handler: NativeAddress) : WGPUVertexBufferLayout {
		override var arrayStride: ULong
			get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.arrayStride ?: error("pointer of WGPUVertexBufferLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.arrayStride = newValue } } 

		override var stepMode: WGPUVertexStepMode
			get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.stepMode ?: error("pointer of WGPUVertexBufferLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.stepMode = newValue } } 

		override var attributeCount: ULong
			get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.attributeCount ?: error("pointer of WGPUVertexBufferLayout is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.attributeCount = newValue } } 

		override var attributes: ArrayHolder<WGPUVertexAttribute>?
			get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.attributes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUVertexAttribute>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.attributes = newValue?.handler?.toCPointer() } } 

	}

	actual var arrayStride: ULong
	actual var stepMode: WGPUVertexStepMode
	actual var attributeCount: ULong
	actual var attributes: ArrayHolder<WGPUVertexAttribute>?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUVertexBufferLayout {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexBufferLayout>())
				.let { WGPUVertexBufferLayout(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUVertexBufferLayout) -> Unit): ArrayHolder<WGPUVertexBufferLayout> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexBufferLayout>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUVertexBufferLayout>())
							.let { WGPUVertexBufferLayout(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUVertexBufferLayout> {
		return cValue<webgpu.native.WGPUVertexBufferLayout> {
			arrayStride = this@WGPUVertexBufferLayout.arrayStride
			stepMode = this@WGPUVertexBufferLayout.stepMode
			attributeCount = this@WGPUVertexBufferLayout.attributeCount
			attributes = this@WGPUVertexBufferLayout.attributes?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUVertexBufferLayout.adapt(structure: WGPUVertexBufferLayout) {
	arrayStride = structure.arrayStride
	stepMode = structure.stepMode
	attributeCount = structure.attributeCount
	attributes = structure.attributes?.handler?.toCPointer()
}

actual interface WGPUChainedStructOut {
	value class ByReference(override val handler: NativeAddress) : WGPUChainedStructOut {
		override var next: WGPUChainedStructOut?
			get() = handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.next?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStructOut(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.let { it.next = newValue?.handler?.toCPointer() } } 

		override var sType: WGPUSType
			get() = handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.sType ?: error("pointer of WGPUChainedStructOut is null")
			set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.let { it.sType = newValue } } 

	}

	actual var next: WGPUChainedStructOut?
	actual var sType: WGPUSType
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUChainedStructOut {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStructOut>())
				.let { WGPUChainedStructOut(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUChainedStructOut) -> Unit): ArrayHolder<WGPUChainedStructOut> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStructOut>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUChainedStructOut>())
							.let { WGPUChainedStructOut(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUChainedStructOut> {
		return cValue<webgpu.native.WGPUChainedStructOut> {
			next = this@WGPUChainedStructOut.next?.handler?.toCPointer()
			sType = this@WGPUChainedStructOut.sType
		}
	}
}

fun webgpu.native.WGPUChainedStructOut.adapt(structure: WGPUChainedStructOut) {
	next = structure.next?.handler?.toCPointer()
	sType = structure.sType
}

actual interface WGPUBufferMapCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUBufferMapCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUBufferMapCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUBufferMapCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUBufferMapCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferMapCallbackInfo>())
				.let { WGPUBufferMapCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUBufferMapCallbackInfo) -> Unit): ArrayHolder<WGPUBufferMapCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferMapCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUBufferMapCallbackInfo>())
							.let { WGPUBufferMapCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBufferMapCallbackInfo> {
		return cValue<webgpu.native.WGPUBufferMapCallbackInfo> {
			nextInChain = this@WGPUBufferMapCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUBufferMapCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUBufferMapCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUBufferMapCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBufferMapCallbackInfo.adapt(structure: WGPUBufferMapCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUCompilationInfoCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUCompilationInfoCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUCompilationInfoCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCompilationInfoCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCompilationInfoCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfoCallbackInfo>())
				.let { WGPUCompilationInfoCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCompilationInfoCallbackInfo) -> Unit): ArrayHolder<WGPUCompilationInfoCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfoCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCompilationInfoCallbackInfo>())
							.let { WGPUCompilationInfoCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCompilationInfoCallbackInfo> {
		return cValue<webgpu.native.WGPUCompilationInfoCallbackInfo> {
			nextInChain = this@WGPUCompilationInfoCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUCompilationInfoCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUCompilationInfoCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUCompilationInfoCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCompilationInfoCallbackInfo.adapt(structure: WGPUCompilationInfoCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUCreateComputePipelineAsyncCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUCreateComputePipelineAsyncCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCreateComputePipelineAsyncCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCreateComputePipelineAsyncCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>())
				.let { WGPUCreateComputePipelineAsyncCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCreateComputePipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateComputePipelineAsyncCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>())
							.let { WGPUCreateComputePipelineAsyncCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo> {
		return cValue<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo> {
			nextInChain = this@WGPUCreateComputePipelineAsyncCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUCreateComputePipelineAsyncCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUCreateComputePipelineAsyncCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUCreateComputePipelineAsyncCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo.adapt(structure: WGPUCreateComputePipelineAsyncCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUCreateRenderPipelineAsyncCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUCreateRenderPipelineAsyncCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUCreateRenderPipelineAsyncCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>())
				.let { WGPUCreateRenderPipelineAsyncCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUCreateRenderPipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateRenderPipelineAsyncCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>())
							.let { WGPUCreateRenderPipelineAsyncCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo> {
		return cValue<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo> {
			nextInChain = this@WGPUCreateRenderPipelineAsyncCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUCreateRenderPipelineAsyncCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUCreateRenderPipelineAsyncCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUCreateRenderPipelineAsyncCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo.adapt(structure: WGPUCreateRenderPipelineAsyncCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUPopErrorScopeCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUPopErrorScopeCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUPopErrorScopeCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUPopErrorScopeCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPopErrorScopeCallbackInfo>())
				.let { WGPUPopErrorScopeCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUPopErrorScopeCallbackInfo) -> Unit): ArrayHolder<WGPUPopErrorScopeCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPopErrorScopeCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUPopErrorScopeCallbackInfo>())
							.let { WGPUPopErrorScopeCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUPopErrorScopeCallbackInfo> {
		return cValue<webgpu.native.WGPUPopErrorScopeCallbackInfo> {
			nextInChain = this@WGPUPopErrorScopeCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUPopErrorScopeCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUPopErrorScopeCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUPopErrorScopeCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUPopErrorScopeCallbackInfo.adapt(structure: WGPUPopErrorScopeCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPUQueueWorkDoneCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPUQueueWorkDoneCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
			get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUQueueWorkDoneCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPUQueueWorkDoneCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueWorkDoneCallbackInfo>())
				.let { WGPUQueueWorkDoneCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPUQueueWorkDoneCallbackInfo) -> Unit): ArrayHolder<WGPUQueueWorkDoneCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueWorkDoneCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPUQueueWorkDoneCallbackInfo>())
							.let { WGPUQueueWorkDoneCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUQueueWorkDoneCallbackInfo> {
		return cValue<webgpu.native.WGPUQueueWorkDoneCallbackInfo> {
			nextInChain = this@WGPUQueueWorkDoneCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPUQueueWorkDoneCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPUQueueWorkDoneCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPUQueueWorkDoneCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUQueueWorkDoneCallbackInfo.adapt(structure: WGPUQueueWorkDoneCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPURequestAdapterCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPURequestAdapterCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPURequestAdapterCallback>?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPURequestAdapterCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURequestAdapterCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterCallbackInfo>())
				.let { WGPURequestAdapterCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURequestAdapterCallbackInfo) -> Unit): ArrayHolder<WGPURequestAdapterCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURequestAdapterCallbackInfo>())
							.let { WGPURequestAdapterCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequestAdapterCallbackInfo> {
		return cValue<webgpu.native.WGPURequestAdapterCallbackInfo> {
			nextInChain = this@WGPURequestAdapterCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPURequestAdapterCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPURequestAdapterCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPURequestAdapterCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURequestAdapterCallbackInfo.adapt(structure: WGPURequestAdapterCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

actual interface WGPURequestDeviceCallbackInfo {
	value class ByReference(override val handler: NativeAddress) : WGPURequestDeviceCallbackInfo {
		override var nextInChain: WGPUChainedStruct?
			get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

		override var callback: CallbackHolder<WGPURequestDeviceCallback>?
			get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPURequestDeviceCallback>(it) }
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

		override var userdata1: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

		override var userdata2: NativeAddress?
			get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
			set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	}

	actual var nextInChain: WGPUChainedStruct?
	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
	actual var userdata1: NativeAddress?
	actual var userdata2: NativeAddress?
	actual val handler: NativeAddress

	actual companion object {
		actual operator fun invoke(address: NativeAddress): WGPURequestDeviceCallbackInfo {
			return ByReference(address)
		}

		actual fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestDeviceCallbackInfo>())
				.let { WGPURequestDeviceCallbackInfo(it) }
		}

		actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  WGPURequestDeviceCallbackInfo) -> Unit): ArrayHolder<WGPURequestDeviceCallbackInfo> {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestDeviceCallbackInfo>() * size.toLong())
				.also {
					(0u until size).forEach { index ->
						(it + index.toLong() * sizeOf<webgpu.native.WGPURequestDeviceCallbackInfo>())
							.let { WGPURequestDeviceCallbackInfo(it) }
							.let { provider(index, it) }
					}
				}
				.let(::ArrayHolder)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequestDeviceCallbackInfo> {
		return cValue<webgpu.native.WGPURequestDeviceCallbackInfo> {
			nextInChain = this@WGPURequestDeviceCallbackInfo.nextInChain?.handler?.toCPointer()
			callback = this@WGPURequestDeviceCallbackInfo.callback?.handler?.toCPointer()
			userdata1 = this@WGPURequestDeviceCallbackInfo.userdata1?.toCPointer()
			userdata2 = this@WGPURequestDeviceCallbackInfo.userdata2?.toCPointer()
		}
	}
}

fun webgpu.native.WGPURequestDeviceCallbackInfo.adapt(structure: WGPURequestDeviceCallbackInfo) {
	nextInChain = structure.nextInChain?.handler?.toCPointer()
	callback = structure.callback?.handler?.toCPointer()
	userdata1 = structure.userdata1?.toCPointer()
	userdata2 = structure.userdata2?.toCPointer()
}

