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

actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	actual var vendor: CString?
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendor?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.vendor = newValue?.handler?.toCPointer() } } 

	actual var architecture: CString?
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.architecture?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.architecture = newValue?.handler?.toCPointer() } } 

	actual var device: CString?
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.device?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.device = newValue?.handler?.toCPointer() } } 

	actual var description: CString?
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.description?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.description = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandBufferDescriptor>())
				.let(::WGPUCommandBufferDescriptor)
		}
	}
}

actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUCommandEncoderDescriptor>())
				.let(::WGPUCommandEncoderDescriptor)
		}
	}
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
}

actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual var message: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.message?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.message = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.timestampWrites?.toLong()?.takeIf {it != 0L}?.let { WGPUComputePassTimestampWrites(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.timestampWrites = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUComputePassDescriptor>())
				.let(::WGPUComputePassDescriptor)
		}
	}
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
}

actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual var key: CString?
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.key?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.key = newValue?.handler?.toCPointer() } } 

	actual var value: Double
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.value ?: error("pointer of WGPUConstantEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.value = newValue } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
			return allocator.allocate(sizeOf<webgpu.native.WGPUConstantEntry>())
				.let(::WGPUConstantEntry)
		}
	}
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
}

actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUQueueDescriptor>())
				.let(::WGPUQueueDescriptor)
		}
	}
}

actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPURenderBundleDescriptor>())
				.let(::WGPURenderBundleDescriptor)
		}
	}
}

actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderModuleDescriptor>())
				.let(::WGPUShaderModuleDescriptor)
		}
	}
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
}

actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual var code: CString?
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.code?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.let { it.code = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
			return allocator.allocate(sizeOf<webgpu.native.WGPUShaderSourceWGSL>())
				.let(::WGPUShaderSourceWGSL)
		}
	}
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
}

actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual companion object {
		actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
			return allocator.allocate(sizeOf<webgpu.native.WGPUSurfaceDescriptor>())
				.let(::WGPUSurfaceDescriptor)
		}
	}
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
}

actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

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
}

actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

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
}

