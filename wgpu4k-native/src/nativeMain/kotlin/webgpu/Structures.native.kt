// This file has been generated DO NOT EDIT !!!
@file:OptIn(ExperimentalForeignApi::class)
package webgpu
    
import ffi.NativeAddress
import ffi.CallbackHolder
import ffi.CString
import ffi.toCString
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKString
import kotlinx.cinterop.toLong

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

	actual var backendType: WGPUBackendType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var adapterType: WGPUAdapterType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var vendorID: UInt
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.vendorID ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.vendorID = newValue } } 

	actual var deviceID: UInt
		get() = handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.deviceID ?: error("pointer of WGPUAdapterInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUAdapterInfo>()?.pointed?.let { it.deviceID = newValue } } 

}

actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var layout: WGPUBindGroupLayout?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUBindGroupLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual var entries: Long
		get() = TODO()
		set(newValue) {  TODO() } 

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

}

actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var entries: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.binding ?: error("pointer of WGPUBindGroupLayoutEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.binding = newValue } } 

	actual var visibility: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.visibility ?: error("pointer of WGPUBindGroupLayoutEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBindGroupLayoutEntry>()?.pointed?.let { it.visibility = newValue } } 

	actual var buffer: WGPUBufferBindingLayout?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var sampler: WGPUSamplerBindingLayout?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var texture: WGPUTextureBindingLayout?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var storageTexture: WGPUStorageTextureBindingLayout?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	actual var operation: WGPUBlendOperation?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var srcFactor: WGPUBlendFactor?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var dstFactor: WGPUBlendFactor?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUBlendState(actual val handler: NativeAddress) {
	actual var color: WGPUBlendComponent?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var alpha: WGPUBlendComponent?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUBufferBindingType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var hasDynamicOffset: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.hasDynamicOffset?.toBoolean() ?: error("pointer of WGPUBufferBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.hasDynamicOffset = newValue.toUInt() } } 

	actual var minBindingSize: ULong
		get() = handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.minBindingSize ?: error("pointer of WGPUBufferBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferBindingLayout>()?.pointed?.let { it.minBindingSize = newValue } } 

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

}

actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var blend: WGPUBlendState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var writeMask: ULong
		get() = handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.writeMask ?: error("pointer of WGPUColorTargetState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUColorTargetState>()?.pointed?.let { it.writeMask = newValue } } 

}

actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandBufferDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCommandEncoderDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	actual var messages: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual var message: CString?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.message?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationMessage>()?.pointed?.let { it.message = newValue?.handler?.toCPointer() } } 

	actual var type: WGPUCompilationMessageType?
		get() = TODO()
		set(newValue) {  TODO() } 

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

}

actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePassDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = TODO()
		set(newValue) {  TODO() } 

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

}

actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUComputePipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual var compute: WGPUProgrammableStageDescriptor?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual var key: CString?
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.key?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.key = newValue?.handler?.toCPointer() } } 

	actual var value: Double
		get() = handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.value ?: error("pointer of WGPUConstantEntry is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUConstantEntry>()?.pointed?.let { it.value = newValue } } 

}

actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthWriteEnabled: WGPUOptionalBool?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthCompare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var stencilFront: WGPUStencilFaceState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var stencilBack: WGPUStencilFaceState?
		get() = TODO()
		set(newValue) {  TODO() } 

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

}

actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var requiredFeatures: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var requiredLimits: WGPURequiredLimits?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var defaultQueue: WGPUQueueDescriptor?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo?
		get() = TODO()
		set(newValue) {  TODO() } 

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

}

actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFragmentState>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

	actual var constants: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var targets: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUFuture(actual val handler: NativeAddress) {
	actual var id: ULong
		get() = handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.id ?: error("pointer of WGPUFuture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFuture>()?.pointed?.let { it.id = newValue } } 

}

actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	actual var future: WGPUFuture?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var completed: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.completed?.toBoolean() ?: error("pointer of WGPUFutureWaitInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUFutureWaitInfo>()?.pointed?.let { it.completed = newValue.toUInt() } } 

}

actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	actual var layout: WGPUTextureDataLayout?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var buffer: WGPUBuffer?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.buffer?.toLong()?.takeIf {it != 0L}?.let { WGPUBuffer(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyBuffer>()?.pointed?.let { it.buffer = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

	actual var mipLevel: UInt
		get() = handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.mipLevel ?: error("pointer of WGPUImageCopyTexture is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUImageCopyTexture>()?.pointed?.let { it.mipLevel = newValue } } 

	actual var origin: WGPUOrigin3D?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var aspect: WGPUTextureAspect?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	actual var features: WGPUInstanceFeatures?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	actual var timedWaitAnyEnable: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyEnable?.toBoolean() ?: error("pointer of WGPUInstanceFeatures is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyEnable = newValue.toUInt() } } 

	actual var timedWaitAnyMaxCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.timedWaitAnyMaxCount ?: error("pointer of WGPUInstanceFeatures is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUInstanceFeatures>()?.pointed?.let { it.timedWaitAnyMaxCount = newValue } } 

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

}

actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPipelineLayoutDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var bindGroupLayouts: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	actual var topology: WGPUPrimitiveTopology?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var stripIndexFormat: WGPUIndexFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var frontFace: WGPUFrontFace?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var cullMode: WGPUCullMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var unclippedDepth: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.unclippedDepth?.toBoolean() ?: error("pointer of WGPUPrimitiveState is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPrimitiveState>()?.pointed?.let { it.unclippedDepth = newValue.toUInt() } } 

}

actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUProgrammableStageDescriptor>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

	actual var constants: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var type: WGPUQueryType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var count: UInt
		get() = handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.count ?: error("pointer of WGPUQuerySetDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQuerySetDescriptor>()?.pointed?.let { it.count = newValue } } 

}

actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var colorFormats: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthStencilFormat: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var sampleCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

	actual var depthReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

	actual var stencilReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderBundleEncoderDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderBundleEncoderDescriptor>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

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

	actual var loadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var storeOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var clearValue: WGPUColor?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.view?.toLong()?.takeIf {it != 0L}?.let { WGPUTextureView(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.view = newValue?.handler?.toCPointer() } } 

	actual var depthLoadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthStoreOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthClearValue: Float
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthClearValue = newValue } } 

	actual var depthReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.depthReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.depthReadOnly = newValue.toUInt() } } 

	actual var stencilLoadOp: WGPULoadOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var stencilStoreOp: WGPUStoreOp?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var stencilClearValue: UInt
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilClearValue ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilClearValue = newValue } } 

	actual var stencilReadOnly: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.stencilReadOnly?.toBoolean() ?: error("pointer of WGPURenderPassDepthStencilAttachment is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDepthStencilAttachment>()?.pointed?.let { it.stencilReadOnly = newValue.toUInt() } } 

}

actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var colorAttachments: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.occlusionQuerySet?.toLong()?.takeIf {it != 0L}?.let { WGPUQuerySet(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassDescriptor>()?.pointed?.let { it.occlusionQuerySet = newValue?.handler?.toCPointer() } } 

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	actual var maxDrawCount: ULong
		get() = handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.maxDrawCount ?: error("pointer of WGPURenderPassMaxDrawCount is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPassMaxDrawCount>()?.pointed?.let { it.maxDrawCount = newValue } } 

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

}

actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var layout: WGPUPipelineLayout?
		get() = handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.layout?.toLong()?.takeIf {it != 0L}?.let { WGPUPipelineLayout(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURenderPipelineDescriptor>()?.pointed?.let { it.layout = newValue?.handler?.toCPointer() } } 

	actual var vertex: WGPUVertexState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var primitive: WGPUPrimitiveState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthStencil: WGPUDepthStencilState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var multisample: WGPUMultisampleState?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var fragment: WGPUFragmentState?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	actual var compatibleSurface: WGPUSurface?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.compatibleSurface?.toLong()?.takeIf {it != 0L}?.let { WGPUSurface(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.compatibleSurface = newValue?.handler?.toCPointer() } } 

	actual var powerPreference: WGPUPowerPreference?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var backendType: WGPUBackendType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var forceFallbackAdapter: Boolean
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.forceFallbackAdapter?.toBoolean() ?: error("pointer of WGPURequestAdapterOptions is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterOptions>()?.pointed?.let { it.forceFallbackAdapter = newValue.toUInt() } } 

}

actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	actual var limits: WGPULimits?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUSamplerBindingType?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var addressModeU: WGPUAddressMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var addressModeV: WGPUAddressMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var addressModeW: WGPUAddressMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var magFilter: WGPUFilterMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var minFilter: WGPUFilterMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var mipmapFilter: WGPUMipmapFilterMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var lodMinClamp: Float
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMinClamp ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMinClamp = newValue } } 

	actual var lodMaxClamp: Float
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.lodMaxClamp ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.lodMaxClamp = newValue } } 

	actual var compare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var maxAnisotropy: UShort
		get() = handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.maxAnisotropy ?: error("pointer of WGPUSamplerDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSamplerDescriptor>()?.pointed?.let { it.maxAnisotropy = newValue } } 

}

actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderModuleDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	actual var codeSize: UInt
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.codeSize ?: error("pointer of WGPUShaderSourceSPIRV is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.codeSize = newValue } } 

	actual var code: UInt
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.code ?: error("pointer of WGPUShaderSourceSPIRV is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceSPIRV>()?.pointed?.let { it.code = newValue } } 

}

actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual var code: CString?
		get() = handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.code?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUShaderSourceWGSL>()?.pointed?.let { it.code = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	actual var compare: WGPUCompareFunction?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var failOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var depthFailOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var passOp: WGPUStencilOperation?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	actual var access: WGPUStorageTextureAccess?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var viewDimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	actual var limits: WGPULimits?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	actual var usages: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.usages ?: error("pointer of WGPUSurfaceCapabilities is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceCapabilities>()?.pointed?.let { it.usages = newValue } } 

	actual var formats: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var presentModes: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var alphaModes: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	actual var device: WGPUDevice?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.device?.toLong()?.takeIf {it != 0L}?.let { WGPUDevice(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.device = newValue?.handler?.toCPointer() } } 

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.usage ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.usage = newValue } } 

	actual var width: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.width ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.width = newValue } } 

	actual var height: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.height ?: error("pointer of WGPUSurfaceConfiguration is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceConfiguration>()?.pointed?.let { it.height = newValue } } 

	actual var viewFormats: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var alphaMode: WGPUCompositeAlphaMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var presentMode: WGPUPresentMode?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

}

actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	actual var window: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceAndroidNativeWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceAndroidNativeWindow>()?.pointed?.let { it.window = newValue } } 

}

actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	actual var layer: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.layer ?: error("pointer of WGPUSurfaceSourceMetalLayer is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceMetalLayer>()?.pointed?.let { it.layer = newValue } } 

}

actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	actual var display: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.display ?: error("pointer of WGPUSurfaceSourceWaylandSurface is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.display = newValue } } 

	actual var surface: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.surface ?: error("pointer of WGPUSurfaceSourceWaylandSurface is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWaylandSurface>()?.pointed?.let { it.surface = newValue } } 

}

actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	actual var hinstance: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hinstance ?: error("pointer of WGPUSurfaceSourceWindowsHWND is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hinstance = newValue } } 

	actual var hwnd: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.hwnd ?: error("pointer of WGPUSurfaceSourceWindowsHWND is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceWindowsHWND>()?.pointed?.let { it.hwnd = newValue } } 

}

actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	actual var connection: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.connection ?: error("pointer of WGPUSurfaceSourceXCBWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.connection = newValue } } 

	actual var window: UInt
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXCBWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXCBWindow>()?.pointed?.let { it.window = newValue } } 

}

actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	actual var display: Unit
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.display ?: error("pointer of WGPUSurfaceSourceXlibWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.display = newValue } } 

	actual var window: ULong
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.window ?: error("pointer of WGPUSurfaceSourceXlibWindow is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceSourceXlibWindow>()?.pointed?.let { it.window = newValue } } 

}

actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.texture?.toLong()?.takeIf {it != 0L}?.let { WGPUTexture(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUSurfaceTexture>()?.pointed?.let { it.texture = newValue?.handler?.toCPointer() } } 

	actual var status: WGPUSurfaceGetCurrentTextureStatus?
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	actual var sampleType: WGPUTextureSampleType?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var viewDimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var multisampled: Boolean
		get() = handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.multisampled?.toBoolean() ?: error("pointer of WGPUTextureBindingLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureBindingLayout>()?.pointed?.let { it.multisampled = newValue.toUInt() } } 

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

}

actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.usage = newValue } } 

	actual var dimension: WGPUTextureDimension?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var size: WGPUExtent3D?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var mipLevelCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.mipLevelCount ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.mipLevelCount = newValue } } 

	actual var sampleCount: UInt
		get() = handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.sampleCount ?: error("pointer of WGPUTextureDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureDescriptor>()?.pointed?.let { it.sampleCount = newValue } } 

	actual var viewFormats: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.label?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.label = newValue?.handler?.toCPointer() } } 

	actual var format: WGPUTextureFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var dimension: WGPUTextureViewDimension?
		get() = TODO()
		set(newValue) {  TODO() } 

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

	actual var aspect: WGPUTextureAspect?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var usage: ULong
		get() = handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.usage ?: error("pointer of WGPUTextureViewDescriptor is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUTextureViewDescriptor>()?.pointed?.let { it.usage = newValue } } 

}

actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	actual var format: WGPUVertexFormat?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var offset: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.offset ?: error("pointer of WGPUVertexAttribute is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.offset = newValue } } 

	actual var shaderLocation: UInt
		get() = handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.shaderLocation ?: error("pointer of WGPUVertexAttribute is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexAttribute>()?.pointed?.let { it.shaderLocation = newValue } } 

}

actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	actual var arrayStride: ULong
		get() = handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.arrayStride ?: error("pointer of WGPUVertexBufferLayout is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexBufferLayout>()?.pointed?.let { it.arrayStride = newValue } } 

	actual var stepMode: WGPUVertexStepMode?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var attributes: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.module?.toLong()?.takeIf {it != 0L}?.let { WGPUShaderModule(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.module = newValue?.handler?.toCPointer() } } 

	actual var entryPoint: CString?
		get() = handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.entryPoint?.toCString()
		set(newValue) { handler.toCPointer<webgpu.native.WGPUVertexState>()?.pointed?.let { it.entryPoint = newValue?.handler?.toCPointer() } } 

	actual var constants: Long
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var buffers: Long
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStructOut?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) {  TODO() } 

}

actual value class WGPUBufferMapCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUBufferMapCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUBufferMapCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUBufferMapCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUCompilationInfoCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUCompilationInfoCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUCompilationInfoCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCompilationInfoCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUCreateComputePipelineAsyncCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUCreateComputePipelineAsyncCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateComputePipelineAsyncCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUCreateRenderPipelineAsyncCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUCreateRenderPipelineAsyncCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUCreateRenderPipelineAsyncCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUDeviceLostCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUDeviceLostCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUDeviceLostCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUDeviceLostCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUPopErrorScopeCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUPopErrorScopeCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUPopErrorScopeCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUPopErrorScopeCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUQueueWorkDoneCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUQueueWorkDoneCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUQueueWorkDoneCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUQueueWorkDoneCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPURequestAdapterCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPURequestAdapterCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPURequestAdapterCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestAdapterCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPURequestDeviceCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPURequestDeviceCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPURequestDeviceCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPURequestDeviceCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

actual value class WGPUUncapturedErrorCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) {  TODO() } 

	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) }
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.callback?.toLong()?.takeIf {it != 0L}?.let { CallbackHolder(it) } } 

	actual var userData1: Long?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userData1 ?: error("pointer of WGPUUncapturedErrorCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userData1 = newValue } } 

	actual var userData2: Long?
		get() = handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.userData2 ?: error("pointer of WGPUUncapturedErrorCallbackInfo is null")
		set(newValue) { handler.toCPointer<webgpu.native.WGPUUncapturedErrorCallbackInfo>()?.pointed?.let { it.userData2 = newValue } } 

}

