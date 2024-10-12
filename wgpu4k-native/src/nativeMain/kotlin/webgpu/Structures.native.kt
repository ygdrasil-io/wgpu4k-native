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

actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	actual val vendor: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendor?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

	actual val architecture: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.architecture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

	actual val device: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.device?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

	actual val description: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.description?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUAdapterInfo is null")

	actual var backendType: WGPUBackendType
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.backendType ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.backendType = newValue } } 

	actual var adapterType: WGPUAdapterType
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.adapterType ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.adapterType = newValue } } 

	actual var vendorID: UInt
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendorID ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.vendorID = newValue } } 

	actual var deviceID: UInt
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.deviceID ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.deviceID = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUAdapterInfo>())
				.let(::WGPUAdapterInfo)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUAdapterInfo> {
		return cValue<webgpu.native.WGPUAdapterInfo> {
			vendor.adapt(this@WGPUAdapterInfo.vendor)
			architecture.adapt(this@WGPUAdapterInfo.architecture)
			device.adapt(this@WGPUAdapterInfo.device)
			description.adapt(this@WGPUAdapterInfo.description)
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
	backendType = structure.backendType
	adapterType = structure.adapterType
	vendorID = structure.vendorID
	deviceID = structure.deviceID
}

actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBindGroupDescriptor is null")

	actual var layout: WGPUBindGroupLayout?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUBindGroupLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual var entryCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.entryCount ?: error("pointer of WGPUBindGroupDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.entryCount = newValue } } 

	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.entries?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupEntry>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.entries = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupDescriptor>())
				.let(::WGPUBindGroupDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupDescriptor> {
		return cValue<webgpu.native.WGPUBindGroupDescriptor> {
			label.adapt(this@WGPUBindGroupDescriptor.label)
			layout = this@WGPUBindGroupDescriptor.layout?.handler?.toCPointer()
			entryCount = this@WGPUBindGroupDescriptor.entryCount
			entries = this@WGPUBindGroupDescriptor.entries?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBindGroupDescriptor.adapt(structure: WGPUBindGroupDescriptor) {
	label.adapt(structure.label)
	layout = structure.layout?.handler?.toCPointer()
	entryCount = structure.entryCount
	entries = structure.entries?.handler?.toCPointer()
}

actual value class WGPUBindGroupEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.binding ?: error("pointer of WGPUBindGroupEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.binding = newValue } } 

	actual var buffer: WGPUBuffer?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.buffer = newValue?.handler?.toCPointer() } } 

	actual var offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.offset ?: error("pointer of WGPUBindGroupEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.offset = newValue } } 

	actual var size: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.size ?: error("pointer of WGPUBindGroupEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.size = newValue } } 

	actual var sampler: WGPUSampler?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.sampler?.toLong()?.takeIf {it != 0L}?.let { WGPUSampler(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.sampler = newValue?.handler?.toCPointer() } } 

	actual var textureView: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.textureView?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupEntry>()?.pointed?.let { it.textureView = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupEntry>())
				.let(::WGPUBindGroupEntry)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupEntry> {
		return cValue<webgpu.native.WGPUBindGroupEntry> {
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
	binding = structure.binding
	buffer = structure.buffer?.handler?.toCPointer()
	offset = structure.offset
	size = structure.size
	sampler = structure.sampler?.handler?.toCPointer()
	textureView = structure.textureView?.handler?.toCPointer()
}

actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBindGroupLayoutDescriptor is null")

	actual var entryCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.entryCount ?: error("pointer of WGPUBindGroupLayoutDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.entryCount = newValue } } 

	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.entries?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupLayoutEntry>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.entries = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutDescriptor>())
				.let(::WGPUBindGroupLayoutDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupLayoutDescriptor> {
		return cValue<webgpu.native.WGPUBindGroupLayoutDescriptor> {
			label.adapt(this@WGPUBindGroupLayoutDescriptor.label)
			entryCount = this@WGPUBindGroupLayoutDescriptor.entryCount
			entries = this@WGPUBindGroupLayoutDescriptor.entries?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUBindGroupLayoutDescriptor.adapt(structure: WGPUBindGroupLayoutDescriptor) {
	label.adapt(structure.label)
	entryCount = structure.entryCount
	entries = structure.entries?.handler?.toCPointer()
}

actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.binding ?: error("pointer of WGPUBindGroupLayoutEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.binding = newValue } } 

	actual var visibility: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.visibility ?: error("pointer of WGPUBindGroupLayoutEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.visibility = newValue } } 

	actual val buffer: WGPUBufferBindingLayout
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.buffer?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBufferBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

	actual val sampler: WGPUSamplerBindingLayout
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.sampler?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUSamplerBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

	actual val texture: WGPUTextureBindingLayout
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.texture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.storageTexture?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStorageTextureBindingLayout(it) } ?: error("pointer of WGPUBindGroupLayoutEntry is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBindGroupLayoutEntry>())
				.let(::WGPUBindGroupLayoutEntry)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBindGroupLayoutEntry> {
		return cValue<webgpu.native.WGPUBindGroupLayoutEntry> {
			buffer.adapt(this@WGPUBindGroupLayoutEntry.buffer)
			sampler.adapt(this@WGPUBindGroupLayoutEntry.sampler)
			texture.adapt(this@WGPUBindGroupLayoutEntry.texture)
			storageTexture.adapt(this@WGPUBindGroupLayoutEntry.storageTexture)
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
	binding = structure.binding
	visibility = structure.visibility
}

actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	actual var operation: WGPUBlendOperation
		get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.operation ?: error("pointer of WGPUBlendComponent is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.operation = newValue } } 

	actual var srcFactor: WGPUBlendFactor
		get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.srcFactor ?: error("pointer of WGPUBlendComponent is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.srcFactor = newValue } } 

	actual var dstFactor: WGPUBlendFactor
		get() = handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.dstFactor ?: error("pointer of WGPUBlendComponent is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBlendComponent>()?.pointed?.let { it.dstFactor = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendComponent>())
				.let(::WGPUBlendComponent)
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

actual value class WGPUBlendState(actual val handler: NativeAddress) {
	actual val color: WGPUBlendComponent
		get() = handler.toCPointer<webgpu.native.WGPUBlendState>()?.pointed?.color?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendComponent(it) } ?: error("pointer of WGPUBlendState is null")

	actual val alpha: WGPUBlendComponent
		get() = handler.toCPointer<webgpu.native.WGPUBlendState>()?.pointed?.alpha?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendComponent(it) } ?: error("pointer of WGPUBlendState is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBlendState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBlendState>())
				.let(::WGPUBlendState)
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

actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUBufferBindingType
		get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.type ?: error("pointer of WGPUBufferBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.type = newValue } } 

	actual var hasDynamicOffset: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.hasDynamicOffset?.toBoolean() ?: error("pointer of WGPUBufferBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.hasDynamicOffset = newValue.toUInt() } } 

	actual var minBindingSize: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.minBindingSize ?: error("pointer of WGPUBufferBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.minBindingSize = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferBindingLayout>())
				.let(::WGPUBufferBindingLayout)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBufferBindingLayout> {
		return cValue<webgpu.native.WGPUBufferBindingLayout> {
			type = this@WGPUBufferBindingLayout.type
			hasDynamicOffset = this@WGPUBufferBindingLayout.hasDynamicOffset.toUInt()
			minBindingSize = this@WGPUBufferBindingLayout.minBindingSize
		}
	}
}

fun webgpu.native.WGPUBufferBindingLayout.adapt(structure: WGPUBufferBindingLayout) {
	type = structure.type
	hasDynamicOffset = structure.hasDynamicOffset.toUInt()
	minBindingSize = structure.minBindingSize
}

actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUBufferDescriptor is null")

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.usage ?: error("pointer of WGPUBufferDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.usage = newValue } } 

	actual var size: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.size ?: error("pointer of WGPUBufferDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.size = newValue } } 

	actual var mappedAtCreation: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.mappedAtCreation?.toBoolean() ?: error("pointer of WGPUBufferDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.mappedAtCreation = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferDescriptor>())
				.let(::WGPUBufferDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUBufferDescriptor> {
		return cValue<webgpu.native.WGPUBufferDescriptor> {
			label.adapt(this@WGPUBufferDescriptor.label)
			usage = this@WGPUBufferDescriptor.usage
			size = this@WGPUBufferDescriptor.size
			mappedAtCreation = this@WGPUBufferDescriptor.mappedAtCreation.toUInt()
		}
	}
}

fun webgpu.native.WGPUBufferDescriptor.adapt(structure: WGPUBufferDescriptor) {
	label.adapt(structure.label)
	usage = structure.usage
	size = structure.size
	mappedAtCreation = structure.mappedAtCreation.toUInt()
}

actual value class WGPUColor(actual val handler: NativeAddress) {
	actual var r: Double
		get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.r ?: error("pointer of WGPUColor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.r = newValue } } 

	actual var g: Double
		get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.g ?: error("pointer of WGPUColor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.g = newValue } } 

	actual var b: Double
		get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.b ?: error("pointer of WGPUColor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.b = newValue } } 

	actual var a: Double
		get() = handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.a ?: error("pointer of WGPUColor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColor>()?.pointed?.let { it.a = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColor>())
				.let(::WGPUColor)
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

actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.format ?: error("pointer of WGPUColorTargetState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.format = newValue } } 

	actual var blend: WGPUBlendState?
		get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.blend?.toLong()?.takeIf {it != 0L}?.let { WGPUBlendState(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.blend = newValue?.handler?.toCPointer() } } 

	actual var writeMask: ULong
		get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.writeMask ?: error("pointer of WGPUColorTargetState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.writeMask = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUColorTargetState>())
				.let(::WGPUColorTargetState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUColorTargetState> {
		return cValue<webgpu.native.WGPUColorTargetState> {
			format = this@WGPUColorTargetState.format
			blend = this@WGPUColorTargetState.blend?.handler?.toCPointer()
			writeMask = this@WGPUColorTargetState.writeMask
		}
	}
}

fun webgpu.native.WGPUColorTargetState.adapt(structure: WGPUColorTargetState) {
	format = structure.format
	blend = structure.blend?.handler?.toCPointer()
	writeMask = structure.writeMask
}

actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCommandBufferDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandBufferDescriptor>())
				.let(::WGPUCommandBufferDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCommandBufferDescriptor> {
		return cValue<webgpu.native.WGPUCommandBufferDescriptor> {
			label.adapt(this@WGPUCommandBufferDescriptor.label)
		}
	}
}

fun webgpu.native.WGPUCommandBufferDescriptor.adapt(structure: WGPUCommandBufferDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCommandEncoderDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandEncoderDescriptor>())
				.let(::WGPUCommandEncoderDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCommandEncoderDescriptor> {
		return cValue<webgpu.native.WGPUCommandEncoderDescriptor> {
			label.adapt(this@WGPUCommandEncoderDescriptor.label)
		}
	}
}

fun webgpu.native.WGPUCommandEncoderDescriptor.adapt(structure: WGPUCommandEncoderDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	actual var messageCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.messageCount ?: error("pointer of WGPUCompilationInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.let { it.messageCount = newValue } } 

	actual var messages: ArrayHolder<WGPUCompilationMessage>?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.messages?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUCompilationMessage>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfo>()?.pointed?.let { it.messages = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfo>())
				.let(::WGPUCompilationInfo)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCompilationInfo> {
		return cValue<webgpu.native.WGPUCompilationInfo> {
			messageCount = this@WGPUCompilationInfo.messageCount
			messages = this@WGPUCompilationInfo.messages?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUCompilationInfo.adapt(structure: WGPUCompilationInfo) {
	messageCount = structure.messageCount
	messages = structure.messages?.handler?.toCPointer()
}

actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual val message: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.message?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUCompilationMessage is null")

	actual var type: WGPUCompilationMessageType
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.type ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.type = newValue } } 

	actual var lineNum: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.lineNum ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.lineNum = newValue } } 

	actual var linePos: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.linePos ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.linePos = newValue } } 

	actual var offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.offset ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.offset = newValue } } 

	actual var length: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.length ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.length = newValue } } 

	actual var utf16LinePos: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16LinePos ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16LinePos = newValue } } 

	actual var utf16Offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16Offset ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16Offset = newValue } } 

	actual var utf16Length: ULong
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.utf16Length ?: error("pointer of WGPUCompilationMessage is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.utf16Length = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationMessage>())
				.let(::WGPUCompilationMessage)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUCompilationMessage> {
		return cValue<webgpu.native.WGPUCompilationMessage> {
			message.adapt(this@WGPUCompilationMessage.message)
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
	type = structure.type
	lineNum = structure.lineNum
	linePos = structure.linePos
	offset = structure.offset
	length = structure.length
	utf16LinePos = structure.utf16LinePos
	utf16Offset = structure.utf16Offset
	utf16Length = structure.utf16Length
}

actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUComputePassDescriptor is null")

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.timestampWrites?.toLong()?.takeIf {it != 0L}?.let { WGPUComputePassTimestampWrites(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.timestampWrites = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassDescriptor>())
				.let(::WGPUComputePassDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUComputePassDescriptor> {
		return cValue<webgpu.native.WGPUComputePassDescriptor> {
			label.adapt(this@WGPUComputePassDescriptor.label)
			timestampWrites = this@WGPUComputePassDescriptor.timestampWrites?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUComputePassDescriptor.adapt(structure: WGPUComputePassDescriptor) {
	label.adapt(structure.label)
	timestampWrites = structure.timestampWrites?.handler?.toCPointer()
}

actual value class WGPUComputePassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.querySet = newValue?.handler?.toCPointer() } } 

	actual var beginningOfPassWriteIndex: UInt
		get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.beginningOfPassWriteIndex ?: error("pointer of WGPUComputePassTimestampWrites is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.beginningOfPassWriteIndex = newValue } } 

	actual var endOfPassWriteIndex: UInt
		get() = handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.endOfPassWriteIndex ?: error("pointer of WGPUComputePassTimestampWrites is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassTimestampWrites>()?.pointed?.let { it.endOfPassWriteIndex = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassTimestampWrites {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassTimestampWrites>())
				.let(::WGPUComputePassTimestampWrites)
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

actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUComputePipelineDescriptor is null")

	actual var layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual val compute: WGPUProgrammableStageDescriptor
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.compute?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUProgrammableStageDescriptor(it) } ?: error("pointer of WGPUComputePipelineDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePipelineDescriptor>())
				.let(::WGPUComputePipelineDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUComputePipelineDescriptor> {
		return cValue<webgpu.native.WGPUComputePipelineDescriptor> {
			label.adapt(this@WGPUComputePipelineDescriptor.label)
			compute.adapt(this@WGPUComputePipelineDescriptor.compute)
			layout = this@WGPUComputePipelineDescriptor.layout?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUComputePipelineDescriptor.adapt(structure: WGPUComputePipelineDescriptor) {
	label.adapt(structure.label)
	compute.adapt(structure.compute)
	layout = structure.layout?.handler?.toCPointer()
}

actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual val key: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.key?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUConstantEntry is null")

	actual var value: Double
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.value ?: error("pointer of WGPUConstantEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.value = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUConstantEntry>())
				.let(::WGPUConstantEntry)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUConstantEntry> {
		return cValue<webgpu.native.WGPUConstantEntry> {
			key.adapt(this@WGPUConstantEntry.key)
			value = this@WGPUConstantEntry.value
		}
	}
}

fun webgpu.native.WGPUConstantEntry.adapt(structure: WGPUConstantEntry) {
	key.adapt(structure.key)
	value = structure.value
}

actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.format ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.format = newValue } } 

	actual var depthWriteEnabled: WGPUOptionalBool
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthWriteEnabled ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthWriteEnabled = newValue } } 

	actual var depthCompare: WGPUCompareFunction
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthCompare ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthCompare = newValue } } 

	actual val stencilFront: WGPUStencilFaceState
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilFront?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStencilFaceState(it) } ?: error("pointer of WGPUDepthStencilState is null")

	actual val stencilBack: WGPUStencilFaceState
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilBack?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStencilFaceState(it) } ?: error("pointer of WGPUDepthStencilState is null")

	actual var stencilReadMask: UInt
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilReadMask ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.stencilReadMask = newValue } } 

	actual var stencilWriteMask: UInt
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.stencilWriteMask ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.stencilWriteMask = newValue } } 

	actual var depthBias: Int
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBias ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBias = newValue } } 

	actual var depthBiasSlopeScale: Float
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBiasSlopeScale ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBiasSlopeScale = newValue } } 

	actual var depthBiasClamp: Float
		get() = handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.depthBiasClamp ?: error("pointer of WGPUDepthStencilState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDepthStencilState>()?.pointed?.let { it.depthBiasClamp = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDepthStencilState>())
				.let(::WGPUDepthStencilState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUDepthStencilState> {
		return cValue<webgpu.native.WGPUDepthStencilState> {
			stencilFront.adapt(this@WGPUDepthStencilState.stencilFront)
			stencilBack.adapt(this@WGPUDepthStencilState.stencilBack)
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
	format = structure.format
	depthWriteEnabled = structure.depthWriteEnabled
	depthCompare = structure.depthCompare
	stencilReadMask = structure.stencilReadMask
	stencilWriteMask = structure.stencilWriteMask
	depthBias = structure.depthBias
	depthBiasSlopeScale = structure.depthBiasSlopeScale
	depthBiasClamp = structure.depthBiasClamp
}

actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

	actual var requiredFeatureCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredFeatureCount ?: error("pointer of WGPUDeviceDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredFeatureCount = newValue } } 

	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredFeatures?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUFeatureName>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredFeatures = newValue?.handler?.toCPointer() } } 

	actual var requiredLimits: WGPURequiredLimits?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.requiredLimits?.toLong()?.takeIf {it != 0L}?.let { WGPURequiredLimits(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.requiredLimits = newValue?.handler?.toCPointer() } } 

	actual val defaultQueue: WGPUQueueDescriptor
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.defaultQueue?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUQueueDescriptor(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.deviceLostCallbackInfo?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUDeviceLostCallbackInfo(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.uncapturedErrorCallbackInfo?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUUncapturedErrorCallbackInfo(it) } ?: error("pointer of WGPUDeviceDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceDescriptor>())
				.let(::WGPUDeviceDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUDeviceDescriptor> {
		return cValue<webgpu.native.WGPUDeviceDescriptor> {
			label.adapt(this@WGPUDeviceDescriptor.label)
			defaultQueue.adapt(this@WGPUDeviceDescriptor.defaultQueue)
			deviceLostCallbackInfo.adapt(this@WGPUDeviceDescriptor.deviceLostCallbackInfo)
			uncapturedErrorCallbackInfo.adapt(this@WGPUDeviceDescriptor.uncapturedErrorCallbackInfo)
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
	requiredFeatureCount = structure.requiredFeatureCount
	requiredFeatures = structure.requiredFeatures?.handler?.toCPointer()
	requiredLimits = structure.requiredLimits?.handler?.toCPointer()
}

actual value class WGPUExtent3D(actual val handler: NativeAddress) {
	actual var width: UInt
		get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.width ?: error("pointer of WGPUExtent3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.width = newValue } } 

	actual var height: UInt
		get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.height ?: error("pointer of WGPUExtent3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.height = newValue } } 

	actual var depthOrArrayLayers: UInt
		get() = handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.depthOrArrayLayers ?: error("pointer of WGPUExtent3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUExtent3D>()?.pointed?.let { it.depthOrArrayLayers = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D {
			return allocator.allocate(sizeOf<webgpu.native.WGPUExtent3D>())
				.let(::WGPUExtent3D)
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

actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual val entryPoint: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUFragmentState is null")

	actual var constantCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.constantCount ?: error("pointer of WGPUFragmentState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.constantCount = newValue } } 

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

	actual var targetCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.targetCount ?: error("pointer of WGPUFragmentState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.targetCount = newValue } } 

	actual var targets: ArrayHolder<WGPUColorTargetState>?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.targets?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUColorTargetState>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.targets = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFragmentState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFragmentState>())
				.let(::WGPUFragmentState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUFragmentState> {
		return cValue<webgpu.native.WGPUFragmentState> {
			entryPoint.adapt(this@WGPUFragmentState.entryPoint)
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
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
	targetCount = structure.targetCount
	targets = structure.targets?.handler?.toCPointer()
}

actual value class WGPUFuture(actual val handler: NativeAddress) {
	actual var id: ULong
		get() = handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.id ?: error("pointer of WGPUFuture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.let { it.id = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFuture>())
				.let(::WGPUFuture)
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

actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	actual val future: WGPUFuture
		get() = handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.future?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUFuture(it) } ?: error("pointer of WGPUFutureWaitInfo is null")

	actual var completed: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.completed?.toBoolean() ?: error("pointer of WGPUFutureWaitInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.let { it.completed = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUFutureWaitInfo>())
				.let(::WGPUFutureWaitInfo)
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

actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	actual val layout: WGPUTextureDataLayout
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.layout?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureDataLayout(it) } ?: error("pointer of WGPUImageCopyBuffer is null")

	actual var buffer: WGPUBuffer?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.let { it.buffer = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyBuffer {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyBuffer>())
				.let(::WGPUImageCopyBuffer)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUImageCopyBuffer> {
		return cValue<webgpu.native.WGPUImageCopyBuffer> {
			layout.adapt(this@WGPUImageCopyBuffer.layout)
			buffer = this@WGPUImageCopyBuffer.buffer?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUImageCopyBuffer.adapt(structure: WGPUImageCopyBuffer) {
	layout.adapt(structure.layout)
	buffer = structure.buffer?.handler?.toCPointer()
}

actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

	actual var mipLevel: UInt
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.mipLevel ?: error("pointer of WGPUImageCopyTexture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.mipLevel = newValue } } 

	actual val origin: WGPUOrigin3D
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.origin?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUOrigin3D(it) } ?: error("pointer of WGPUImageCopyTexture is null")

	actual var aspect: WGPUTextureAspect
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.aspect ?: error("pointer of WGPUImageCopyTexture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.aspect = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUImageCopyTexture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUImageCopyTexture>())
				.let(::WGPUImageCopyTexture)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUImageCopyTexture> {
		return cValue<webgpu.native.WGPUImageCopyTexture> {
			origin.adapt(this@WGPUImageCopyTexture.origin)
			texture = this@WGPUImageCopyTexture.texture?.handler?.toCPointer()
			mipLevel = this@WGPUImageCopyTexture.mipLevel
			aspect = this@WGPUImageCopyTexture.aspect
		}
	}
}

fun webgpu.native.WGPUImageCopyTexture.adapt(structure: WGPUImageCopyTexture) {
	origin.adapt(structure.origin)
	texture = structure.texture?.handler?.toCPointer()
	mipLevel = structure.mipLevel
	aspect = structure.aspect
}

actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	actual val features: WGPUInstanceFeatures
		get() = handler.toCPointer<webgpu.native.WGPUInstanceDescriptor>()?.pointed?.features?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUInstanceFeatures(it) } ?: error("pointer of WGPUInstanceDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceDescriptor>())
				.let(::WGPUInstanceDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUInstanceDescriptor> {
		return cValue<webgpu.native.WGPUInstanceDescriptor> {
			features.adapt(this@WGPUInstanceDescriptor.features)
		}
	}
}

fun webgpu.native.WGPUInstanceDescriptor.adapt(structure: WGPUInstanceDescriptor) {
	features.adapt(structure.features)
}

actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	actual var timedWaitAnyEnable: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyEnable?.toBoolean() ?: error("pointer of WGPUInstanceFeatures is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyEnable = newValue.toUInt() } } 

	actual var timedWaitAnyMaxCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyMaxCount ?: error("pointer of WGPUInstanceFeatures is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyMaxCount = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUInstanceFeatures {
			return allocator.allocate(sizeOf<webgpu.native.WGPUInstanceFeatures>())
				.let(::WGPUInstanceFeatures)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUInstanceFeatures> {
		return cValue<webgpu.native.WGPUInstanceFeatures> {
			timedWaitAnyEnable = this@WGPUInstanceFeatures.timedWaitAnyEnable.toUInt()
			timedWaitAnyMaxCount = this@WGPUInstanceFeatures.timedWaitAnyMaxCount
		}
	}
}

fun webgpu.native.WGPUInstanceFeatures.adapt(structure: WGPUInstanceFeatures) {
	timedWaitAnyEnable = structure.timedWaitAnyEnable.toUInt()
	timedWaitAnyMaxCount = structure.timedWaitAnyMaxCount
}

actual value class WGPULimits(actual val handler: NativeAddress) {
	actual var maxTextureDimension1D: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension1D ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension1D = newValue } } 

	actual var maxTextureDimension2D: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension2D ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension2D = newValue } } 

	actual var maxTextureDimension3D: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureDimension3D ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureDimension3D = newValue } } 

	actual var maxTextureArrayLayers: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxTextureArrayLayers ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxTextureArrayLayers = newValue } } 

	actual var maxBindGroups: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindGroups ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindGroups = newValue } } 

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindGroupsPlusVertexBuffers ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindGroupsPlusVertexBuffers = newValue } } 

	actual var maxBindingsPerBindGroup: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBindingsPerBindGroup ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBindingsPerBindGroup = newValue } } 

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxDynamicUniformBuffersPerPipelineLayout ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxDynamicUniformBuffersPerPipelineLayout = newValue } } 

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxDynamicStorageBuffersPerPipelineLayout ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxDynamicStorageBuffersPerPipelineLayout = newValue } } 

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxSampledTexturesPerShaderStage ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxSampledTexturesPerShaderStage = newValue } } 

	actual var maxSamplersPerShaderStage: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxSamplersPerShaderStage ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxSamplersPerShaderStage = newValue } } 

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageBuffersPerShaderStage ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageBuffersPerShaderStage = newValue } } 

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageTexturesPerShaderStage ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageTexturesPerShaderStage = newValue } } 

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxUniformBuffersPerShaderStage ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxUniformBuffersPerShaderStage = newValue } } 

	actual var maxUniformBufferBindingSize: ULong
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxUniformBufferBindingSize ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxUniformBufferBindingSize = newValue } } 

	actual var maxStorageBufferBindingSize: ULong
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxStorageBufferBindingSize ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxStorageBufferBindingSize = newValue } } 

	actual var minUniformBufferOffsetAlignment: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.minUniformBufferOffsetAlignment ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.minUniformBufferOffsetAlignment = newValue } } 

	actual var minStorageBufferOffsetAlignment: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.minStorageBufferOffsetAlignment ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.minStorageBufferOffsetAlignment = newValue } } 

	actual var maxVertexBuffers: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexBuffers ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexBuffers = newValue } } 

	actual var maxBufferSize: ULong
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxBufferSize ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxBufferSize = newValue } } 

	actual var maxVertexAttributes: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexAttributes ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexAttributes = newValue } } 

	actual var maxVertexBufferArrayStride: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxVertexBufferArrayStride ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxVertexBufferArrayStride = newValue } } 

	actual var maxInterStageShaderVariables: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxInterStageShaderVariables ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxInterStageShaderVariables = newValue } } 

	actual var maxColorAttachments: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxColorAttachments ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxColorAttachments = newValue } } 

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxColorAttachmentBytesPerSample ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxColorAttachmentBytesPerSample = newValue } } 

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupStorageSize ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupStorageSize = newValue } } 

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeInvocationsPerWorkgroup ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeInvocationsPerWorkgroup = newValue } } 

	actual var maxComputeWorkgroupSizeX: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeX ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeX = newValue } } 

	actual var maxComputeWorkgroupSizeY: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeY ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeY = newValue } } 

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupSizeZ ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupSizeZ = newValue } } 

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.maxComputeWorkgroupsPerDimension ?: error("pointer of WGPULimits is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPULimits>()?.pointed?.let { it.maxComputeWorkgroupsPerDimension = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPULimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPULimits>())
				.let(::WGPULimits)
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

actual value class WGPUMultisampleState(actual val handler: NativeAddress) {
	actual var count: UInt
		get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.count ?: error("pointer of WGPUMultisampleState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.count = newValue } } 

	actual var mask: UInt
		get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.mask ?: error("pointer of WGPUMultisampleState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.mask = newValue } } 

	actual var alphaToCoverageEnabled: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.alphaToCoverageEnabled?.toBoolean() ?: error("pointer of WGPUMultisampleState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUMultisampleState>()?.pointed?.let { it.alphaToCoverageEnabled = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUMultisampleState>())
				.let(::WGPUMultisampleState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUMultisampleState> {
		return cValue<webgpu.native.WGPUMultisampleState> {
			count = this@WGPUMultisampleState.count
			mask = this@WGPUMultisampleState.mask
			alphaToCoverageEnabled = this@WGPUMultisampleState.alphaToCoverageEnabled.toUInt()
		}
	}
}

fun webgpu.native.WGPUMultisampleState.adapt(structure: WGPUMultisampleState) {
	count = structure.count
	mask = structure.mask
	alphaToCoverageEnabled = structure.alphaToCoverageEnabled.toUInt()
}

actual value class WGPUOrigin3D(actual val handler: NativeAddress) {
	actual var x: UInt
		get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.x ?: error("pointer of WGPUOrigin3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.x = newValue } } 

	actual var y: UInt
		get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.y ?: error("pointer of WGPUOrigin3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.y = newValue } } 

	actual var z: UInt
		get() = handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.z ?: error("pointer of WGPUOrigin3D is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUOrigin3D>()?.pointed?.let { it.z = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D {
			return allocator.allocate(sizeOf<webgpu.native.WGPUOrigin3D>())
				.let(::WGPUOrigin3D)
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

actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUPipelineLayoutDescriptor is null")

	actual var bindGroupLayoutCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.bindGroupLayoutCount ?: error("pointer of WGPUPipelineLayoutDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.bindGroupLayoutCount = newValue } } 

	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
		get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.bindGroupLayouts?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUBindGroupLayout>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.bindGroupLayouts = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPipelineLayoutDescriptor>())
				.let(::WGPUPipelineLayoutDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUPipelineLayoutDescriptor> {
		return cValue<webgpu.native.WGPUPipelineLayoutDescriptor> {
			label.adapt(this@WGPUPipelineLayoutDescriptor.label)
			bindGroupLayoutCount = this@WGPUPipelineLayoutDescriptor.bindGroupLayoutCount
			bindGroupLayouts = this@WGPUPipelineLayoutDescriptor.bindGroupLayouts?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUPipelineLayoutDescriptor.adapt(structure: WGPUPipelineLayoutDescriptor) {
	label.adapt(structure.label)
	bindGroupLayoutCount = structure.bindGroupLayoutCount
	bindGroupLayouts = structure.bindGroupLayouts?.handler?.toCPointer()
}

actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	actual var topology: WGPUPrimitiveTopology
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.topology ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.topology = newValue } } 

	actual var stripIndexFormat: WGPUIndexFormat
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.stripIndexFormat ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.stripIndexFormat = newValue } } 

	actual var frontFace: WGPUFrontFace
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.frontFace ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.frontFace = newValue } } 

	actual var cullMode: WGPUCullMode
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.cullMode ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.cullMode = newValue } } 

	actual var unclippedDepth: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.unclippedDepth?.toBoolean() ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.unclippedDepth = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPrimitiveState>())
				.let(::WGPUPrimitiveState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUPrimitiveState> {
		return cValue<webgpu.native.WGPUPrimitiveState> {
			topology = this@WGPUPrimitiveState.topology
			stripIndexFormat = this@WGPUPrimitiveState.stripIndexFormat
			frontFace = this@WGPUPrimitiveState.frontFace
			cullMode = this@WGPUPrimitiveState.cullMode
			unclippedDepth = this@WGPUPrimitiveState.unclippedDepth.toUInt()
		}
	}
}

fun webgpu.native.WGPUPrimitiveState.adapt(structure: WGPUPrimitiveState) {
	topology = structure.topology
	stripIndexFormat = structure.stripIndexFormat
	frontFace = structure.frontFace
	cullMode = structure.cullMode
	unclippedDepth = structure.unclippedDepth.toUInt()
}

actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual val entryPoint: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUProgrammableStageDescriptor is null")

	actual var constantCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.constantCount ?: error("pointer of WGPUProgrammableStageDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.constantCount = newValue } } 

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUProgrammableStageDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUProgrammableStageDescriptor>())
				.let(::WGPUProgrammableStageDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUProgrammableStageDescriptor> {
		return cValue<webgpu.native.WGPUProgrammableStageDescriptor> {
			entryPoint.adapt(this@WGPUProgrammableStageDescriptor.entryPoint)
			module = this@WGPUProgrammableStageDescriptor.module?.handler?.toCPointer()
			constantCount = this@WGPUProgrammableStageDescriptor.constantCount
			constants = this@WGPUProgrammableStageDescriptor.constants?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUProgrammableStageDescriptor.adapt(structure: WGPUProgrammableStageDescriptor) {
	entryPoint.adapt(structure.entryPoint)
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
}

actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUQuerySetDescriptor is null")

	actual var type: WGPUQueryType
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.type ?: error("pointer of WGPUQuerySetDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.type = newValue } } 

	actual var count: UInt
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.count ?: error("pointer of WGPUQuerySetDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.count = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQuerySetDescriptor>())
				.let(::WGPUQuerySetDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUQuerySetDescriptor> {
		return cValue<webgpu.native.WGPUQuerySetDescriptor> {
			label.adapt(this@WGPUQuerySetDescriptor.label)
			type = this@WGPUQuerySetDescriptor.type
			count = this@WGPUQuerySetDescriptor.count
		}
	}
}

fun webgpu.native.WGPUQuerySetDescriptor.adapt(structure: WGPUQuerySetDescriptor) {
	label.adapt(structure.label)
	type = structure.type
	count = structure.count
}

actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUQueueDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueDescriptor>())
				.let(::WGPUQueueDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUQueueDescriptor> {
		return cValue<webgpu.native.WGPUQueueDescriptor> {
			label.adapt(this@WGPUQueueDescriptor.label)
		}
	}
}

fun webgpu.native.WGPUQueueDescriptor.adapt(structure: WGPUQueueDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderBundleDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleDescriptor>())
				.let(::WGPURenderBundleDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderBundleDescriptor> {
		return cValue<webgpu.native.WGPURenderBundleDescriptor> {
			label.adapt(this@WGPURenderBundleDescriptor.label)
		}
	}
}

fun webgpu.native.WGPURenderBundleDescriptor.adapt(structure: WGPURenderBundleDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")

	actual var colorFormatCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.colorFormatCount ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.colorFormatCount = newValue } } 

	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.colorFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.colorFormats = newValue?.handler?.toCPointer() } } 

	actual var depthStencilFormat: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.depthStencilFormat ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.depthStencilFormat = newValue } } 

	actual var sampleCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

	actual var depthReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

	actual var stencilReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleEncoderDescriptor>())
				.let(::WGPURenderBundleEncoderDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderBundleEncoderDescriptor> {
		return cValue<webgpu.native.WGPURenderBundleEncoderDescriptor> {
			label.adapt(this@WGPURenderBundleEncoderDescriptor.label)
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
	colorFormatCount = structure.colorFormatCount
	colorFormats = structure.colorFormats?.handler?.toCPointer()
	depthStencilFormat = structure.depthStencilFormat
	sampleCount = structure.sampleCount
	depthReadOnly = structure.depthReadOnly.toUInt()
	stencilReadOnly = structure.stencilReadOnly.toUInt()
}

actual value class WGPURenderPassColorAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.view = newValue?.handler?.toCPointer() } } 

	actual var depthSlice: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.depthSlice ?: error("pointer of WGPURenderPassColorAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.depthSlice = newValue } } 

	actual var resolveTarget: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.resolveTarget?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.resolveTarget = newValue?.handler?.toCPointer() } } 

	actual var loadOp: WGPULoadOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.loadOp ?: error("pointer of WGPURenderPassColorAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.loadOp = newValue } } 

	actual var storeOp: WGPUStoreOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.storeOp ?: error("pointer of WGPURenderPassColorAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.let { it.storeOp = newValue } } 

	actual val clearValue: WGPUColor
		get() = handler.toCPointer<webgpu.native.WGPURenderPassColorAttachment>()?.pointed?.clearValue?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUColor(it) } ?: error("pointer of WGPURenderPassColorAttachment is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassColorAttachment>())
				.let(::WGPURenderPassColorAttachment)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassColorAttachment> {
		return cValue<webgpu.native.WGPURenderPassColorAttachment> {
			clearValue.adapt(this@WGPURenderPassColorAttachment.clearValue)
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
	view = structure.view?.handler?.toCPointer()
	depthSlice = structure.depthSlice
	resolveTarget = structure.resolveTarget?.handler?.toCPointer()
	loadOp = structure.loadOp
	storeOp = structure.storeOp
}

actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.view = newValue?.handler?.toCPointer() } } 

	actual var depthLoadOp: WGPULoadOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthLoadOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthLoadOp = newValue } } 

	actual var depthStoreOp: WGPUStoreOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthStoreOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthStoreOp = newValue } } 

	actual var depthClearValue: Float
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthClearValue = newValue } } 

	actual var depthReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

	actual var stencilLoadOp: WGPULoadOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilLoadOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilLoadOp = newValue } } 

	actual var stencilStoreOp: WGPUStoreOp
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilStoreOp ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilStoreOp = newValue } } 

	actual var stencilClearValue: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilClearValue = newValue } } 

	actual var stencilReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDepthStencilAttachment>())
				.let(::WGPURenderPassDepthStencilAttachment)
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

actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderPassDescriptor is null")

	actual var colorAttachmentCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.colorAttachmentCount ?: error("pointer of WGPURenderPassDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.colorAttachmentCount = newValue } } 

	actual var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.colorAttachments?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPURenderPassColorAttachment>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.colorAttachments = newValue?.handler?.toCPointer() } } 

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.depthStencilAttachment?.toLong()?.takeIf {it != 0L}?.let { WGPURenderPassDepthStencilAttachment(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.depthStencilAttachment = newValue?.handler?.toCPointer() } } 

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.occlusionQuerySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.occlusionQuerySet = newValue?.handler?.toCPointer() } } 

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.timestampWrites?.toLong()?.takeIf {it != 0L}?.let { WGPURenderPassTimestampWrites(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.timestampWrites = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassDescriptor>())
				.let(::WGPURenderPassDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassDescriptor> {
		return cValue<webgpu.native.WGPURenderPassDescriptor> {
			label.adapt(this@WGPURenderPassDescriptor.label)
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
	colorAttachmentCount = structure.colorAttachmentCount
	colorAttachments = structure.colorAttachments?.handler?.toCPointer()
	depthStencilAttachment = structure.depthStencilAttachment?.handler?.toCPointer()
	occlusionQuerySet = structure.occlusionQuerySet?.handler?.toCPointer()
	timestampWrites = structure.timestampWrites?.handler?.toCPointer()
}

actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	actual var maxDrawCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.maxDrawCount ?: error("pointer of WGPURenderPassMaxDrawCount is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.let { it.maxDrawCount = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassMaxDrawCount>())
				.let(::WGPURenderPassMaxDrawCount)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPassMaxDrawCount> {
		return cValue<webgpu.native.WGPURenderPassMaxDrawCount> {
			maxDrawCount = this@WGPURenderPassMaxDrawCount.maxDrawCount
		}
	}
}

fun webgpu.native.WGPURenderPassMaxDrawCount.adapt(structure: WGPURenderPassMaxDrawCount) {
	maxDrawCount = structure.maxDrawCount
}

actual value class WGPURenderPassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.querySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.querySet = newValue?.handler?.toCPointer() } } 

	actual var beginningOfPassWriteIndex: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.beginningOfPassWriteIndex ?: error("pointer of WGPURenderPassTimestampWrites is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.beginningOfPassWriteIndex = newValue } } 

	actual var endOfPassWriteIndex: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.endOfPassWriteIndex ?: error("pointer of WGPURenderPassTimestampWrites is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassTimestampWrites>()?.pointed?.let { it.endOfPassWriteIndex = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPassTimestampWrites {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPassTimestampWrites>())
				.let(::WGPURenderPassTimestampWrites)
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

actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

	actual var layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual val vertex: WGPUVertexState
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.vertex?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUVertexState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

	actual val primitive: WGPUPrimitiveState
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.primitive?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUPrimitiveState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

	actual var depthStencil: WGPUDepthStencilState?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.depthStencil?.toLong()?.takeIf {it != 0L}?.let { WGPUDepthStencilState(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.depthStencil = newValue?.handler?.toCPointer() } } 

	actual val multisample: WGPUMultisampleState
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.multisample?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUMultisampleState(it) } ?: error("pointer of WGPURenderPipelineDescriptor is null")

	actual var fragment: WGPUFragmentState?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.fragment?.toLong()?.takeIf {it != 0L}?.let { WGPUFragmentState(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.fragment = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderPipelineDescriptor>())
				.let(::WGPURenderPipelineDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURenderPipelineDescriptor> {
		return cValue<webgpu.native.WGPURenderPipelineDescriptor> {
			label.adapt(this@WGPURenderPipelineDescriptor.label)
			vertex.adapt(this@WGPURenderPipelineDescriptor.vertex)
			primitive.adapt(this@WGPURenderPipelineDescriptor.primitive)
			multisample.adapt(this@WGPURenderPipelineDescriptor.multisample)
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
	layout = structure.layout?.handler?.toCPointer()
	depthStencil = structure.depthStencil?.handler?.toCPointer()
	fragment = structure.fragment?.handler?.toCPointer()
}

actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	actual var compatibleSurface: WGPUSurface?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.compatibleSurface?.toLong()?.takeIf {it != 0L}?.let { WGPUSurface(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.compatibleSurface = newValue?.handler?.toCPointer() } } 

	actual var powerPreference: WGPUPowerPreference
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.powerPreference ?: error("pointer of WGPURequestAdapterOptions is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.powerPreference = newValue } } 

	actual var backendType: WGPUBackendType
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.backendType ?: error("pointer of WGPURequestAdapterOptions is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.backendType = newValue } } 

	actual var forceFallbackAdapter: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.forceFallbackAdapter?.toBoolean() ?: error("pointer of WGPURequestAdapterOptions is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.forceFallbackAdapter = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterOptions>())
				.let(::WGPURequestAdapterOptions)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequestAdapterOptions> {
		return cValue<webgpu.native.WGPURequestAdapterOptions> {
			compatibleSurface = this@WGPURequestAdapterOptions.compatibleSurface?.handler?.toCPointer()
			powerPreference = this@WGPURequestAdapterOptions.powerPreference
			backendType = this@WGPURequestAdapterOptions.backendType
			forceFallbackAdapter = this@WGPURequestAdapterOptions.forceFallbackAdapter.toUInt()
		}
	}
}

fun webgpu.native.WGPURequestAdapterOptions.adapt(structure: WGPURequestAdapterOptions) {
	compatibleSurface = structure.compatibleSurface?.handler?.toCPointer()
	powerPreference = structure.powerPreference
	backendType = structure.backendType
	forceFallbackAdapter = structure.forceFallbackAdapter.toUInt()
}

actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits
		get() = handler.toCPointer<webgpu.native.WGPURequiredLimits>()?.pointed?.limits?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPULimits(it) } ?: error("pointer of WGPURequiredLimits is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequiredLimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequiredLimits>())
				.let(::WGPURequiredLimits)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPURequiredLimits> {
		return cValue<webgpu.native.WGPURequiredLimits> {
			limits.adapt(this@WGPURequiredLimits.limits)
		}
	}
}

fun webgpu.native.WGPURequiredLimits.adapt(structure: WGPURequiredLimits) {
	limits.adapt(structure.limits)
}

actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUSamplerBindingType
		get() = handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.type ?: error("pointer of WGPUSamplerBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerBindingLayout>()?.pointed?.let { it.type = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerBindingLayout>())
				.let(::WGPUSamplerBindingLayout)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSamplerBindingLayout> {
		return cValue<webgpu.native.WGPUSamplerBindingLayout> {
			type = this@WGPUSamplerBindingLayout.type
		}
	}
}

fun webgpu.native.WGPUSamplerBindingLayout.adapt(structure: WGPUSamplerBindingLayout) {
	type = structure.type
}

actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUSamplerDescriptor is null")

	actual var addressModeU: WGPUAddressMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeU ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeU = newValue } } 

	actual var addressModeV: WGPUAddressMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeV ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeV = newValue } } 

	actual var addressModeW: WGPUAddressMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.addressModeW ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.addressModeW = newValue } } 

	actual var magFilter: WGPUFilterMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.magFilter ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.magFilter = newValue } } 

	actual var minFilter: WGPUFilterMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.minFilter ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.minFilter = newValue } } 

	actual var mipmapFilter: WGPUMipmapFilterMode
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.mipmapFilter ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.mipmapFilter = newValue } } 

	actual var lodMinClamp: Float
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMinClamp ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMinClamp = newValue } } 

	actual var lodMaxClamp: Float
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMaxClamp ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMaxClamp = newValue } } 

	actual var compare: WGPUCompareFunction
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.compare ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.compare = newValue } } 

	actual var maxAnisotropy: UShort
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.maxAnisotropy ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.maxAnisotropy = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSamplerDescriptor>())
				.let(::WGPUSamplerDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSamplerDescriptor> {
		return cValue<webgpu.native.WGPUSamplerDescriptor> {
			label.adapt(this@WGPUSamplerDescriptor.label)
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

actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUShaderModuleDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderModuleDescriptor>())
				.let(::WGPUShaderModuleDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderModuleDescriptor> {
		return cValue<webgpu.native.WGPUShaderModuleDescriptor> {
			label.adapt(this@WGPUShaderModuleDescriptor.label)
		}
	}
}

fun webgpu.native.WGPUShaderModuleDescriptor.adapt(structure: WGPUShaderModuleDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	actual var codeSize: UInt
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.codeSize ?: error("pointer of WGPUShaderSourceSPIRV is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.codeSize = newValue } } 

	actual var code: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.code?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.code = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceSPIRV>())
				.let(::WGPUShaderSourceSPIRV)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderSourceSPIRV> {
		return cValue<webgpu.native.WGPUShaderSourceSPIRV> {
			codeSize = this@WGPUShaderSourceSPIRV.codeSize
			code = this@WGPUShaderSourceSPIRV.code?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUShaderSourceSPIRV.adapt(structure: WGPUShaderSourceSPIRV) {
	codeSize = structure.codeSize
	code = structure.code?.toCPointer()
}

actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual val code: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.code?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUShaderSourceWGSL is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceWGSL>())
				.let(::WGPUShaderSourceWGSL)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUShaderSourceWGSL> {
		return cValue<webgpu.native.WGPUShaderSourceWGSL> {
			code.adapt(this@WGPUShaderSourceWGSL.code)
		}
	}
}

fun webgpu.native.WGPUShaderSourceWGSL.adapt(structure: WGPUShaderSourceWGSL) {
	code.adapt(structure.code)
}

actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	actual var compare: WGPUCompareFunction
		get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.compare ?: error("pointer of WGPUStencilFaceState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.compare = newValue } } 

	actual var failOp: WGPUStencilOperation
		get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.failOp ?: error("pointer of WGPUStencilFaceState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.failOp = newValue } } 

	actual var depthFailOp: WGPUStencilOperation
		get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.depthFailOp ?: error("pointer of WGPUStencilFaceState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.depthFailOp = newValue } } 

	actual var passOp: WGPUStencilOperation
		get() = handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.passOp ?: error("pointer of WGPUStencilFaceState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStencilFaceState>()?.pointed?.let { it.passOp = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStencilFaceState>())
				.let(::WGPUStencilFaceState)
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

actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	actual var access: WGPUStorageTextureAccess
		get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.access ?: error("pointer of WGPUStorageTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.access = newValue } } 

	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.format ?: error("pointer of WGPUStorageTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.format = newValue } } 

	actual var viewDimension: WGPUTextureViewDimension
		get() = handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.viewDimension ?: error("pointer of WGPUStorageTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStorageTextureBindingLayout>()?.pointed?.let { it.viewDimension = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStorageTextureBindingLayout>())
				.let(::WGPUStorageTextureBindingLayout)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUStorageTextureBindingLayout> {
		return cValue<webgpu.native.WGPUStorageTextureBindingLayout> {
			access = this@WGPUStorageTextureBindingLayout.access
			format = this@WGPUStorageTextureBindingLayout.format
			viewDimension = this@WGPUStorageTextureBindingLayout.viewDimension
		}
	}
}

fun webgpu.native.WGPUStorageTextureBindingLayout.adapt(structure: WGPUStorageTextureBindingLayout) {
	access = structure.access
	format = structure.format
	viewDimension = structure.viewDimension
}

actual value class WGPUSupportedFeatures(actual val handler: NativeAddress) {
	actual var featureCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.featureCount ?: error("pointer of WGPUSupportedFeatures is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.let { it.featureCount = newValue } } 

	actual var features: ArrayHolder<WGPUFeatureName>?
		get() = handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.features?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUFeatureName>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSupportedFeatures>()?.pointed?.let { it.features = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedFeatures>())
				.let(::WGPUSupportedFeatures)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSupportedFeatures> {
		return cValue<webgpu.native.WGPUSupportedFeatures> {
			featureCount = this@WGPUSupportedFeatures.featureCount
			features = this@WGPUSupportedFeatures.features?.handler?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSupportedFeatures.adapt(structure: WGPUSupportedFeatures) {
	featureCount = structure.featureCount
	features = structure.features?.handler?.toCPointer()
}

actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits
		get() = handler.toCPointer<webgpu.native.WGPUSupportedLimits>()?.pointed?.limits?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPULimits(it) } ?: error("pointer of WGPUSupportedLimits is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSupportedLimits {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSupportedLimits>())
				.let(::WGPUSupportedLimits)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSupportedLimits> {
		return cValue<webgpu.native.WGPUSupportedLimits> {
			limits.adapt(this@WGPUSupportedLimits.limits)
		}
	}
}

fun webgpu.native.WGPUSupportedLimits.adapt(structure: WGPUSupportedLimits) {
	limits.adapt(structure.limits)
}

actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	actual var usages: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.usages ?: error("pointer of WGPUSurfaceCapabilities is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.usages = newValue } } 

	actual var formatCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.formatCount ?: error("pointer of WGPUSurfaceCapabilities is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.formatCount = newValue } } 

	actual var formats: ArrayHolder<WGPUTextureFormat>?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.formats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.formats = newValue?.handler?.toCPointer() } } 

	actual var presentModeCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.presentModeCount ?: error("pointer of WGPUSurfaceCapabilities is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.presentModeCount = newValue } } 

	actual var presentModes: ArrayHolder<WGPUPresentMode>?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.presentModes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUPresentMode>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.presentModes = newValue?.handler?.toCPointer() } } 

	actual var alphaModeCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.alphaModeCount ?: error("pointer of WGPUSurfaceCapabilities is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.alphaModeCount = newValue } } 

	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.alphaModes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUCompositeAlphaMode>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.alphaModes = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceCapabilities>())
				.let(::WGPUSurfaceCapabilities)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceCapabilities> {
		return cValue<webgpu.native.WGPUSurfaceCapabilities> {
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
	usages = structure.usages
	formatCount = structure.formatCount
	formats = structure.formats?.handler?.toCPointer()
	presentModeCount = structure.presentModeCount
	presentModes = structure.presentModes?.handler?.toCPointer()
	alphaModeCount = structure.alphaModeCount
	alphaModes = structure.alphaModes?.handler?.toCPointer()
}

actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	actual var device: WGPUDevice?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.device?.toLong()?.takeIf {it != 0L}?.let { WGPUDevice(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.device = newValue?.handler?.toCPointer() } } 

	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.format ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.format = newValue } } 

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.usage ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.usage = newValue } } 

	actual var width: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.width ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.width = newValue } } 

	actual var height: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.height ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.height = newValue } } 

	actual var viewFormatCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.viewFormatCount ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.viewFormatCount = newValue } } 

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.viewFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.viewFormats = newValue?.handler?.toCPointer() } } 

	actual var alphaMode: WGPUCompositeAlphaMode
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.alphaMode ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.alphaMode = newValue } } 

	actual var presentMode: WGPUPresentMode
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.presentMode ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.presentMode = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceConfiguration>())
				.let(::WGPUSurfaceConfiguration)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceConfiguration> {
		return cValue<webgpu.native.WGPUSurfaceConfiguration> {
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

actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUSurfaceDescriptor is null")

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceDescriptor>())
				.let(::WGPUSurfaceDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceDescriptor> {
		return cValue<webgpu.native.WGPUSurfaceDescriptor> {
			label.adapt(this@WGPUSurfaceDescriptor.label)
		}
	}
}

fun webgpu.native.WGPUSurfaceDescriptor.adapt(structure: WGPUSurfaceDescriptor) {
	label.adapt(structure.label)
}

actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	actual var window: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.window?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.let { it.window = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>())
				.let(::WGPUSurfaceSourceAndroidNativeWindow)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow> {
			window = this@WGPUSurfaceSourceAndroidNativeWindow.window?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceAndroidNativeWindow.adapt(structure: WGPUSurfaceSourceAndroidNativeWindow) {
	window = structure.window?.toCPointer()
}

actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	actual var layer: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.layer?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.let { it.layer = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceMetalLayer>())
				.let(::WGPUSurfaceSourceMetalLayer)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceMetalLayer> {
		return cValue<webgpu.native.WGPUSurfaceSourceMetalLayer> {
			layer = this@WGPUSurfaceSourceMetalLayer.layer?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceMetalLayer.adapt(structure: WGPUSurfaceSourceMetalLayer) {
	layer = structure.layer?.toCPointer()
}

actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	actual var display: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.display?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.display = newValue?.toCPointer() } } 

	actual var surface: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.surface?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.surface = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWaylandSurface>())
				.let(::WGPUSurfaceSourceWaylandSurface)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceWaylandSurface> {
		return cValue<webgpu.native.WGPUSurfaceSourceWaylandSurface> {
			display = this@WGPUSurfaceSourceWaylandSurface.display?.toCPointer()
			surface = this@WGPUSurfaceSourceWaylandSurface.surface?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceWaylandSurface.adapt(structure: WGPUSurfaceSourceWaylandSurface) {
	display = structure.display?.toCPointer()
	surface = structure.surface?.toCPointer()
}

actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	actual var hinstance: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hinstance?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hinstance = newValue?.toCPointer() } } 

	actual var hwnd: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hwnd?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hwnd = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceWindowsHWND>())
				.let(::WGPUSurfaceSourceWindowsHWND)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceWindowsHWND> {
		return cValue<webgpu.native.WGPUSurfaceSourceWindowsHWND> {
			hinstance = this@WGPUSurfaceSourceWindowsHWND.hinstance?.toCPointer()
			hwnd = this@WGPUSurfaceSourceWindowsHWND.hwnd?.toCPointer()
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceWindowsHWND.adapt(structure: WGPUSurfaceSourceWindowsHWND) {
	hinstance = structure.hinstance?.toCPointer()
	hwnd = structure.hwnd?.toCPointer()
}

actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	actual var connection: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.connection?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.connection = newValue?.toCPointer() } } 

	actual var window: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXCBWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.window = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXCBWindow>())
				.let(::WGPUSurfaceSourceXCBWindow)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceXCBWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceXCBWindow> {
			connection = this@WGPUSurfaceSourceXCBWindow.connection?.toCPointer()
			window = this@WGPUSurfaceSourceXCBWindow.window
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceXCBWindow.adapt(structure: WGPUSurfaceSourceXCBWindow) {
	connection = structure.connection?.toCPointer()
	window = structure.window
}

actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	actual var display: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.display?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.display = newValue?.toCPointer() } } 

	actual var window: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXlibWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.window = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceSourceXlibWindow>())
				.let(::WGPUSurfaceSourceXlibWindow)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUSurfaceSourceXlibWindow> {
		return cValue<webgpu.native.WGPUSurfaceSourceXlibWindow> {
			display = this@WGPUSurfaceSourceXlibWindow.display?.toCPointer()
			window = this@WGPUSurfaceSourceXlibWindow.window
		}
	}
}

fun webgpu.native.WGPUSurfaceSourceXlibWindow.adapt(structure: WGPUSurfaceSourceXlibWindow) {
	display = structure.display?.toCPointer()
	window = structure.window
}

actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.status ?: error("pointer of WGPUSurfaceTexture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.let { it.status = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceTexture>())
				.let(::WGPUSurfaceTexture)
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

actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	actual var sampleType: WGPUTextureSampleType
		get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.sampleType ?: error("pointer of WGPUTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.sampleType = newValue } } 

	actual var viewDimension: WGPUTextureViewDimension
		get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.viewDimension ?: error("pointer of WGPUTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.viewDimension = newValue } } 

	actual var multisampled: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.multisampled?.toBoolean() ?: error("pointer of WGPUTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.multisampled = newValue.toUInt() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureBindingLayout>())
				.let(::WGPUTextureBindingLayout)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureBindingLayout> {
		return cValue<webgpu.native.WGPUTextureBindingLayout> {
			sampleType = this@WGPUTextureBindingLayout.sampleType
			viewDimension = this@WGPUTextureBindingLayout.viewDimension
			multisampled = this@WGPUTextureBindingLayout.multisampled.toUInt()
		}
	}
}

fun webgpu.native.WGPUTextureBindingLayout.adapt(structure: WGPUTextureBindingLayout) {
	sampleType = structure.sampleType
	viewDimension = structure.viewDimension
	multisampled = structure.multisampled.toUInt()
}

actual value class WGPUTextureDataLayout(actual val handler: NativeAddress) {
	actual var offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.offset ?: error("pointer of WGPUTextureDataLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.offset = newValue } } 

	actual var bytesPerRow: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.bytesPerRow ?: error("pointer of WGPUTextureDataLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.bytesPerRow = newValue } } 

	actual var rowsPerImage: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.rowsPerImage ?: error("pointer of WGPUTextureDataLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDataLayout>()?.pointed?.let { it.rowsPerImage = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDataLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDataLayout>())
				.let(::WGPUTextureDataLayout)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureDataLayout> {
		return cValue<webgpu.native.WGPUTextureDataLayout> {
			offset = this@WGPUTextureDataLayout.offset
			bytesPerRow = this@WGPUTextureDataLayout.bytesPerRow
			rowsPerImage = this@WGPUTextureDataLayout.rowsPerImage
		}
	}
}

fun webgpu.native.WGPUTextureDataLayout.adapt(structure: WGPUTextureDataLayout) {
	offset = structure.offset
	bytesPerRow = structure.bytesPerRow
	rowsPerImage = structure.rowsPerImage
}

actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUTextureDescriptor is null")

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.usage = newValue } } 

	actual var dimension: WGPUTextureDimension
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.dimension ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.dimension = newValue } } 

	actual val size: WGPUExtent3D
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.size?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUExtent3D(it) } ?: error("pointer of WGPUTextureDescriptor is null")

	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.format ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.format = newValue } } 

	actual var mipLevelCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.mipLevelCount ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.mipLevelCount = newValue } } 

	actual var sampleCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

	actual var viewFormatCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.viewFormatCount ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.viewFormatCount = newValue } } 

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.viewFormats?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUTextureFormat>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.viewFormats = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureDescriptor>())
				.let(::WGPUTextureDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureDescriptor> {
		return cValue<webgpu.native.WGPUTextureDescriptor> {
			label.adapt(this@WGPUTextureDescriptor.label)
			size.adapt(this@WGPUTextureDescriptor.size)
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
	usage = structure.usage
	dimension = structure.dimension
	format = structure.format
	mipLevelCount = structure.mipLevelCount
	sampleCount = structure.sampleCount
	viewFormatCount = structure.viewFormatCount
	viewFormats = structure.viewFormats?.handler?.toCPointer()
}

actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual val label: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.label?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUTextureViewDescriptor is null")

	actual var format: WGPUTextureFormat
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.format ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.format = newValue } } 

	actual var dimension: WGPUTextureViewDimension
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.dimension ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.dimension = newValue } } 

	actual var baseMipLevel: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.baseMipLevel ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.baseMipLevel = newValue } } 

	actual var mipLevelCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.mipLevelCount ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.mipLevelCount = newValue } } 

	actual var baseArrayLayer: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.baseArrayLayer ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.baseArrayLayer = newValue } } 

	actual var arrayLayerCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.arrayLayerCount ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.arrayLayerCount = newValue } } 

	actual var aspect: WGPUTextureAspect
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.aspect ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.aspect = newValue } } 

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.usage = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUTextureViewDescriptor>())
				.let(::WGPUTextureViewDescriptor)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUTextureViewDescriptor> {
		return cValue<webgpu.native.WGPUTextureViewDescriptor> {
			label.adapt(this@WGPUTextureViewDescriptor.label)
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
	format = structure.format
	dimension = structure.dimension
	baseMipLevel = structure.baseMipLevel
	mipLevelCount = structure.mipLevelCount
	baseArrayLayer = structure.baseArrayLayer
	arrayLayerCount = structure.arrayLayerCount
	aspect = structure.aspect
	usage = structure.usage
}

actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	actual var format: WGPUVertexFormat
		get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.format ?: error("pointer of WGPUVertexAttribute is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.format = newValue } } 

	actual var offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.offset ?: error("pointer of WGPUVertexAttribute is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.offset = newValue } } 

	actual var shaderLocation: UInt
		get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.shaderLocation ?: error("pointer of WGPUVertexAttribute is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.shaderLocation = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexAttribute>())
				.let(::WGPUVertexAttribute)
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

actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	actual var arrayStride: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.arrayStride ?: error("pointer of WGPUVertexBufferLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.arrayStride = newValue } } 

	actual var stepMode: WGPUVertexStepMode
		get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.stepMode ?: error("pointer of WGPUVertexBufferLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.stepMode = newValue } } 

	actual var attributeCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.attributeCount ?: error("pointer of WGPUVertexBufferLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.attributeCount = newValue } } 

	actual var attributes: ArrayHolder<WGPUVertexAttribute>?
		get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.attributes?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUVertexAttribute>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.attributes = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexBufferLayout>())
				.let(::WGPUVertexBufferLayout)
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

actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual val entryPoint: WGPUStringView
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.entryPoint?.rawPtr?.toLong()?.takeIf {it != 0L}?.let { WGPUStringView(it) } ?: error("pointer of WGPUVertexState is null")

	actual var constantCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.constantCount ?: error("pointer of WGPUVertexState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.constantCount = newValue } } 

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.constants?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUConstantEntry>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.constants = newValue?.handler?.toCPointer() } } 

	actual var bufferCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.bufferCount ?: error("pointer of WGPUVertexState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.bufferCount = newValue } } 

	actual var buffers: ArrayHolder<WGPUVertexBufferLayout>?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.buffers?.toLong()?.takeIf {it != 0L}?.let { ArrayHolder<WGPUVertexBufferLayout>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.buffers = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUVertexState {
			return allocator.allocate(sizeOf<webgpu.native.WGPUVertexState>())
				.let(::WGPUVertexState)
		}
	}
	fun toCValue(): CValue<webgpu.native.WGPUVertexState> {
		return cValue<webgpu.native.WGPUVertexState> {
			entryPoint.adapt(this@WGPUVertexState.entryPoint)
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
	module = structure.module?.handler?.toCPointer()
	constantCount = structure.constantCount
	constants = structure.constants?.handler?.toCPointer()
	bufferCount = structure.bufferCount
	buffers = structure.buffers?.handler?.toCPointer()
}

actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.next?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.let { it.next = newValue?.handler?.toCPointer() } } 

	actual var sType: WGPUSType
		get() = handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.sType ?: error("pointer of WGPUChainedStruct is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStruct>()?.pointed?.let { it.sType = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStruct>())
				.let(::WGPUChainedStruct)
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

actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStructOut?
		get() = handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.next?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStructOut(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.let { it.next = newValue?.handler?.toCPointer() } } 

	actual var sType: WGPUSType
		get() = handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.sType ?: error("pointer of WGPUChainedStructOut is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUChainedStructOut>()?.pointed?.let { it.sType = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUChainedStructOut {
			return allocator.allocate(sizeOf<webgpu.native.WGPUChainedStructOut>())
				.let(::WGPUChainedStructOut)
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

actual value class WGPUStringView(actual val handler: NativeAddress) {
	actual var data: CString?
		get() = handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.data?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.let { it.data = newValue?.handler?.toCPointer() } } 

	actual var length: ULong
		get() = handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.length ?: error("pointer of WGPUStringView is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUStringView>()?.pointed?.let { it.length = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
			return allocator.allocate(sizeOf<webgpu.native.WGPUStringView>())
				.let(::WGPUStringView)
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

actual value class WGPUBufferMapCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUBufferMapCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUBufferMapCallbackInfo>())
				.let(::WGPUBufferMapCallbackInfo)
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

actual value class WGPUCompilationInfoCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCompilationInfoCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCompilationInfoCallbackInfo>())
				.let(::WGPUCompilationInfoCallbackInfo)
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

actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCreateComputePipelineAsyncCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>())
				.let(::WGPUCreateComputePipelineAsyncCallbackInfo)
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

actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>())
				.let(::WGPUCreateRenderPipelineAsyncCallbackInfo)
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

actual value class WGPUDeviceLostCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUDeviceLostCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUDeviceLostCallbackInfo>())
				.let(::WGPUDeviceLostCallbackInfo)
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

actual value class WGPUPopErrorScopeCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUPopErrorScopeCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUPopErrorScopeCallbackInfo>())
				.let(::WGPUPopErrorScopeCallbackInfo)
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

actual value class WGPUQueueWorkDoneCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUQueueWorkDoneCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueWorkDoneCallbackInfo>())
				.let(::WGPUQueueWorkDoneCallbackInfo)
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

actual value class WGPURequestAdapterCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPURequestAdapterCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestAdapterCallbackInfo>())
				.let(::WGPURequestAdapterCallbackInfo)
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

actual value class WGPURequestDeviceCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPURequestDeviceCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPURequestDeviceCallbackInfo>())
				.let(::WGPURequestDeviceCallbackInfo)
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

actual value class WGPUUncapturedErrorCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.nextInChain?.toLong()?.takeIf {it != 0L}?.let { WGPUChainedStruct(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.nextInChain = newValue?.handler?.toCPointer() } } 

	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder<WGPUUncapturedErrorCallback>(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.callback = newValue?.handler?.toCPointer() } } 

	actual var userdata1: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userdata1?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userdata1 = newValue?.toCPointer() } } 

	actual var userdata2: NativeAddress?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userdata2?.toLong()?.takeIf {it != 0L}
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userdata2 = newValue?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo {
			return allocator.allocate(sizeOf<webgpu.native.WGPUUncapturedErrorCallbackInfo>())
				.let(::WGPUUncapturedErrorCallbackInfo)
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

