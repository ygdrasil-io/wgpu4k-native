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
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout.structLayout

@JvmInline
actual value class WGPUAdapterInfo(actual val handler: NativeAddress) {
	actual var vendor: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var architecture: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var device: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var description: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var backendType: WGPUBackendType
		get() = TODO()
		set(newValue) = TODO()

	actual var adapterType: WGPUAdapterType
		get() = TODO()
		set(newValue) = TODO()

	actual var vendorID: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var deviceID: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("vendor"),
			C_POINTER.withName("architecture"),
			C_POINTER.withName("device"),
			C_POINTER.withName("description"),
			C_INT.withName("backendType"),
			C_INT.withName("adapterType"),
			C_INT.withName("vendorID"),
			C_INT.withName("deviceID")
		).withName("WGPUAdapterInfo")
	}
}

@JvmInline
actual value class WGPUBindGroupDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUBindGroupLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var entries: ArrayHolder<WGPUBindGroupEntry>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupDescriptor")
	}
}

@JvmInline
actual value class WGPUBindGroupEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var size: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var sampler: WGPUSampler?
		get() = TODO()
		set(newValue) = TODO()

	actual var textureView: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("binding"),
			C_POINTER.withName("buffer"),
			C_LONG.withName("offset"),
			C_LONG.withName("size"),
			C_POINTER.withName("sampler"),
			C_POINTER.withName("textureView")
		).withName("WGPUBindGroupEntry")
	}
}

@JvmInline
actual value class WGPUBindGroupLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var entries: ArrayHolder<WGPUBindGroupLayoutEntry>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("entryCount"),
			C_POINTER.withName("entries")
		).withName("WGPUBindGroupLayoutDescriptor")
	}
}

@JvmInline
actual value class WGPUBindGroupLayoutEntry(actual val handler: NativeAddress) {
	actual var binding: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var visibility: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual val buffer: WGPUBufferBindingLayout
		get() = get("buffer", 0L).let(::WGPUBufferBindingLayout)

	actual val sampler: WGPUSamplerBindingLayout
		get() = get("sampler", 0L).let(::WGPUSamplerBindingLayout)

	actual val texture: WGPUTextureBindingLayout
		get() = get("texture", 0L).let(::WGPUTextureBindingLayout)

	actual val storageTexture: WGPUStorageTextureBindingLayout
		get() = get("storageTexture", 0L).let(::WGPUStorageTextureBindingLayout)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("binding"),
			C_LONG.withName("visibility"),
			WGPUBufferBindingLayout.`$LAYOUT`.withName("buffer"),
			WGPUSamplerBindingLayout.`$LAYOUT`.withName("sampler"),
			WGPUTextureBindingLayout.`$LAYOUT`.withName("texture"),
			WGPUStorageTextureBindingLayout.`$LAYOUT`.withName("storageTexture")
		).withName("WGPUBindGroupLayoutEntry")
	}
}

@JvmInline
actual value class WGPUBlendComponent(actual val handler: NativeAddress) {
	actual var operation: WGPUBlendOperation
		get() = TODO()
		set(newValue) = TODO()

	actual var srcFactor: WGPUBlendFactor
		get() = TODO()
		set(newValue) = TODO()

	actual var dstFactor: WGPUBlendFactor
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("operation"),
			C_INT.withName("srcFactor"),
			C_INT.withName("dstFactor")
		).withName("WGPUBlendComponent")
	}
}

@JvmInline
actual value class WGPUBlendState(actual val handler: NativeAddress) {
	actual val color: WGPUBlendComponent
		get() = get("color", 0L).let(::WGPUBlendComponent)

	actual val alpha: WGPUBlendComponent
		get() = get("alpha", 0L).let(::WGPUBlendComponent)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPUBlendComponent.`$LAYOUT`.withName("color"),
			WGPUBlendComponent.`$LAYOUT`.withName("alpha")
		).withName("WGPUBlendState")
	}
}

@JvmInline
actual value class WGPUBufferBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUBufferBindingType
		get() = TODO()
		set(newValue) = TODO()

	actual var hasDynamicOffset: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var minBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("type"),
			C_INT.withName("hasDynamicOffset"),
			C_LONG.withName("minBindingSize")
		).withName("WGPUBufferBindingLayout")
	}
}

@JvmInline
actual value class WGPUBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var size: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var mappedAtCreation: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("usage"),
			C_LONG.withName("size"),
			C_INT.withName("mappedAtCreation")
		).withName("WGPUBufferDescriptor")
	}
}

@JvmInline
actual value class WGPUColor(actual val handler: NativeAddress) {
	actual var r: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var g: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var b: Double
		get() = TODO()
		set(newValue) = TODO()

	actual var a: Double
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_DOUBLE.withName("r"),
			C_DOUBLE.withName("g"),
			C_DOUBLE.withName("b"),
			C_DOUBLE.withName("a")
		).withName("WGPUColor")
	}
}

@JvmInline
actual value class WGPUColorTargetState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var blend: WGPUBlendState?
		get() = TODO()
		set(newValue) = TODO()

	actual var writeMask: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("format"),
			C_POINTER.withName("blend"),
			C_LONG.withName("writeMask")
		).withName("WGPUColorTargetState")
	}
}

@JvmInline
actual value class WGPUCommandBufferDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUCommandBufferDescriptor")
	}
}

@JvmInline
actual value class WGPUCommandEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUCommandEncoderDescriptor")
	}
}

@JvmInline
actual value class WGPUCompilationInfo(actual val handler: NativeAddress) {
	actual var messageCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var messages: ArrayHolder<WGPUCompilationMessage>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("messageCount"),
			C_POINTER.withName("messages")
		).withName("WGPUCompilationInfo")
	}
}

@JvmInline
actual value class WGPUCompilationMessage(actual val handler: NativeAddress) {
	actual var message: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var type: WGPUCompilationMessageType
		get() = TODO()
		set(newValue) = TODO()

	actual var lineNum: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var linePos: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var length: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16LinePos: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16Offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var utf16Length: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPUComputePassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var timestampWrites: WGPUComputePassTimestampWrites?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("timestampWrites")
		).withName("WGPUComputePassDescriptor")
	}
}

@JvmInline
actual value class WGPUComputePassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var beginningOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var endOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPUComputePassTimestampWrites")
	}
}

@JvmInline
actual value class WGPUComputePipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual val compute: WGPUProgrammableStageDescriptor
		get() = get("compute", 0L).let(::WGPUProgrammableStageDescriptor)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			WGPUProgrammableStageDescriptor.`$LAYOUT`.withName("compute")
		).withName("WGPUComputePipelineDescriptor")
	}
}

@JvmInline
actual value class WGPUConstantEntry(actual val handler: NativeAddress) {
	actual var key: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var value: Double
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("key"),
			C_DOUBLE.withName("value")
		).withName("WGPUConstantEntry")
	}
}

@JvmInline
actual value class WGPUDepthStencilState(actual val handler: NativeAddress) {
	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var depthWriteEnabled: WGPUOptionalBool
		get() = TODO()
		set(newValue) = TODO()

	actual var depthCompare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = TODO()

	actual val stencilFront: WGPUStencilFaceState
		get() = get("stencilFront", 0L).let(::WGPUStencilFaceState)

	actual val stencilBack: WGPUStencilFaceState
		get() = get("stencilBack", 0L).let(::WGPUStencilFaceState)

	actual var stencilReadMask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilWriteMask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBias: Int
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBiasSlopeScale: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var depthBiasClamp: Float
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("format"),
			C_INT.withName("depthWriteEnabled"),
			C_INT.withName("depthCompare"),
			WGPUStencilFaceState.`$LAYOUT`.withName("stencilFront"),
			WGPUStencilFaceState.`$LAYOUT`.withName("stencilBack"),
			C_INT.withName("stencilReadMask"),
			C_INT.withName("stencilWriteMask"),
			C_INT.withName("depthBias"),
			C_FLOAT.withName("depthBiasSlopeScale"),
			C_FLOAT.withName("depthBiasClamp")
		).withName("WGPUDepthStencilState")
	}
}

@JvmInline
actual value class WGPUDeviceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var requiredFeatureCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var requiredFeatures: ArrayHolder<WGPUFeatureName>?
		get() = TODO()
		set(newValue) = TODO()

	actual var requiredLimits: WGPURequiredLimits?
		get() = TODO()
		set(newValue) = TODO()

	actual val defaultQueue: WGPUQueueDescriptor
		get() = get("defaultQueue", 0L).let(::WGPUQueueDescriptor)

	actual val deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
		get() = get("deviceLostCallbackInfo", 0L).let(::WGPUDeviceLostCallbackInfo)

	actual val uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
		get() = get("uncapturedErrorCallbackInfo", 0L).let(::WGPUUncapturedErrorCallbackInfo)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("requiredFeatureCount"),
			C_POINTER.withName("requiredFeatures"),
			C_POINTER.withName("requiredLimits"),
			WGPUQueueDescriptor.`$LAYOUT`.withName("defaultQueue"),
			WGPUDeviceLostCallbackInfo.`$LAYOUT`.withName("deviceLostCallbackInfo"),
			WGPUUncapturedErrorCallbackInfo.`$LAYOUT`.withName("uncapturedErrorCallbackInfo")
		).withName("WGPUDeviceDescriptor")
	}
}

@JvmInline
actual value class WGPUExtent3D(actual val handler: NativeAddress) {
	actual var width: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var height: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthOrArrayLayers: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("width"),
			C_INT.withName("height"),
			C_INT.withName("depthOrArrayLayers")
		).withName("WGPUExtent3D")
	}
}

@JvmInline
actual value class WGPUFragmentState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var constantCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = TODO()

	actual var targetCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var targets: ArrayHolder<WGPUColorTargetState>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("targetCount"),
			C_POINTER.withName("targets")
		).withName("WGPUFragmentState")
	}
}

@JvmInline
actual value class WGPUFuture(actual val handler: NativeAddress) {
	actual var id: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("id")
		).withName("WGPUFuture")
	}
}

@JvmInline
actual value class WGPUFutureWaitInfo(actual val handler: NativeAddress) {
	actual val future: WGPUFuture
		get() = get("future", 0L).let(::WGPUFuture)

	actual var completed: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPUFuture.`$LAYOUT`.withName("future"),
			C_INT.withName("completed")
		).withName("WGPUFutureWaitInfo")
	}
}

@JvmInline
actual value class WGPUImageCopyBuffer(actual val handler: NativeAddress) {
	actual val layout: WGPUTextureDataLayout
		get() = get("layout", 0L).let(::WGPUTextureDataLayout)

	actual var buffer: WGPUBuffer?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPUTextureDataLayout.`$LAYOUT`.withName("layout"),
			C_POINTER.withName("buffer")
		).withName("WGPUImageCopyBuffer")
	}
}

@JvmInline
actual value class WGPUImageCopyTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevel: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual val origin: WGPUOrigin3D
		get() = get("origin", 0L).let(::WGPUOrigin3D)

	actual var aspect: WGPUTextureAspect
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("mipLevel"),
			WGPUOrigin3D.`$LAYOUT`.withName("origin"),
			C_INT.withName("aspect")
		).withName("WGPUImageCopyTexture")
	}
}

@JvmInline
actual value class WGPUInstanceDescriptor(actual val handler: NativeAddress) {
	actual val features: WGPUInstanceFeatures
		get() = get("features", 0L).let(::WGPUInstanceFeatures)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPUInstanceFeatures.`$LAYOUT`.withName("features")
		).withName("WGPUInstanceDescriptor")
	}
}

@JvmInline
actual value class WGPUInstanceFeatures(actual val handler: NativeAddress) {
	actual var timedWaitAnyEnable: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var timedWaitAnyMaxCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("timedWaitAnyEnable"),
			C_LONG.withName("timedWaitAnyMaxCount")
		).withName("WGPUInstanceFeatures")
	}
}

@JvmInline
actual value class WGPULimits(actual val handler: NativeAddress) {
	actual var maxTextureDimension1D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureDimension2D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureDimension3D: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxTextureArrayLayers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindGroups: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindGroupsPlusVertexBuffers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBindingsPerBindGroup: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxSampledTexturesPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxSamplersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageBuffersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageTexturesPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxUniformBuffersPerShaderStage: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxUniformBufferBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var maxStorageBufferBindingSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var minUniformBufferOffsetAlignment: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var minStorageBufferOffsetAlignment: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexBuffers: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxBufferSize: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexAttributes: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxVertexBufferArrayStride: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxInterStageShaderVariables: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxColorAttachments: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxColorAttachmentBytesPerSample: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupStorageSize: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeInvocationsPerWorkgroup: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeX: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeY: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupSizeZ: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var maxComputeWorkgroupsPerDimension: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPUMultisampleState(actual val handler: NativeAddress) {
	actual var count: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var mask: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaToCoverageEnabled: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("count"),
			C_INT.withName("mask"),
			C_INT.withName("alphaToCoverageEnabled")
		).withName("WGPUMultisampleState")
	}
}

@JvmInline
actual value class WGPUOrigin3D(actual val handler: NativeAddress) {
	actual var x: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var y: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var z: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("x"),
			C_INT.withName("y"),
			C_INT.withName("z")
		).withName("WGPUOrigin3D")
	}
}

@JvmInline
actual value class WGPUPipelineLayoutDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var bindGroupLayoutCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var bindGroupLayouts: ArrayHolder<WGPUBindGroupLayout>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("bindGroupLayoutCount"),
			C_POINTER.withName("bindGroupLayouts")
		).withName("WGPUPipelineLayoutDescriptor")
	}
}

@JvmInline
actual value class WGPUPrimitiveState(actual val handler: NativeAddress) {
	actual var topology: WGPUPrimitiveTopology
		get() = TODO()
		set(newValue) = TODO()

	actual var stripIndexFormat: WGPUIndexFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var frontFace: WGPUFrontFace
		get() = TODO()
		set(newValue) = TODO()

	actual var cullMode: WGPUCullMode
		get() = TODO()
		set(newValue) = TODO()

	actual var unclippedDepth: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("topology"),
			C_INT.withName("stripIndexFormat"),
			C_INT.withName("frontFace"),
			C_INT.withName("cullMode"),
			C_INT.withName("unclippedDepth")
		).withName("WGPUPrimitiveState")
	}
}

@JvmInline
actual value class WGPUProgrammableStageDescriptor(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var constantCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants")
		).withName("WGPUProgrammableStageDescriptor")
	}
}

@JvmInline
actual value class WGPUQuerySetDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var type: WGPUQueryType
		get() = TODO()
		set(newValue) = TODO()

	actual var count: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_INT.withName("type"),
			C_INT.withName("count")
		).withName("WGPUQuerySetDescriptor")
	}
}

@JvmInline
actual value class WGPUQueueDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUQueueDescriptor")
	}
}

@JvmInline
actual value class WGPURenderBundleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPURenderBundleDescriptor")
	}
}

@JvmInline
actual value class WGPURenderBundleEncoderDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var colorFormatCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var colorFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStencilFormat: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var sampleCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var depthReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("colorFormatCount"),
			C_POINTER.withName("colorFormats"),
			C_INT.withName("depthStencilFormat"),
			C_INT.withName("sampleCount"),
			C_INT.withName("depthReadOnly"),
			C_INT.withName("stencilReadOnly")
		).withName("WGPURenderBundleEncoderDescriptor")
	}
}

@JvmInline
actual value class WGPURenderPassColorAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthSlice: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var resolveTarget: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var loadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = TODO()

	actual var storeOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = TODO()

	actual val clearValue: WGPUColor
		get() = get("clearValue", 0L).let(::WGPUColor)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("view"),
			C_INT.withName("depthSlice"),
			C_POINTER.withName("resolveTarget"),
			C_INT.withName("loadOp"),
			C_INT.withName("storeOp"),
			WGPUColor.`$LAYOUT`.withName("clearValue")
		).withName("WGPURenderPassColorAttachment")
	}
}

@JvmInline
actual value class WGPURenderPassDepthStencilAttachment(actual val handler: NativeAddress) {
	actual var view: WGPUTextureView?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthLoadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStoreOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = TODO()

	actual var depthClearValue: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var depthReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilLoadOp: WGPULoadOp
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilStoreOp: WGPUStoreOp
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilClearValue: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var stencilReadOnly: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPURenderPassDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var colorAttachmentCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var colorAttachments: ArrayHolder<WGPURenderPassColorAttachment>?
		get() = TODO()
		set(newValue) = TODO()

	actual var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
		get() = TODO()
		set(newValue) = TODO()

	actual var occlusionQuerySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var timestampWrites: WGPURenderPassTimestampWrites?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("colorAttachmentCount"),
			C_POINTER.withName("colorAttachments"),
			C_POINTER.withName("depthStencilAttachment"),
			C_POINTER.withName("occlusionQuerySet"),
			C_POINTER.withName("timestampWrites")
		).withName("WGPURenderPassDescriptor")
	}
}

@JvmInline
actual value class WGPURenderPassMaxDrawCount(actual val handler: NativeAddress) {
	actual var maxDrawCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("maxDrawCount")
		).withName("WGPURenderPassMaxDrawCount")
	}
}

@JvmInline
actual value class WGPURenderPassTimestampWrites(actual val handler: NativeAddress) {
	actual var querySet: WGPUQuerySet?
		get() = TODO()
		set(newValue) = TODO()

	actual var beginningOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var endOfPassWriteIndex: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("querySet"),
			C_INT.withName("beginningOfPassWriteIndex"),
			C_INT.withName("endOfPassWriteIndex")
		).withName("WGPURenderPassTimestampWrites")
	}
}

@JvmInline
actual value class WGPURenderPipelineDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var layout: WGPUPipelineLayout?
		get() = TODO()
		set(newValue) = TODO()

	actual val vertex: WGPUVertexState
		get() = get("vertex", 0L).let(::WGPUVertexState)

	actual val primitive: WGPUPrimitiveState
		get() = get("primitive", 0L).let(::WGPUPrimitiveState)

	actual var depthStencil: WGPUDepthStencilState?
		get() = TODO()
		set(newValue) = TODO()

	actual val multisample: WGPUMultisampleState
		get() = get("multisample", 0L).let(::WGPUMultisampleState)

	actual var fragment: WGPUFragmentState?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_POINTER.withName("layout"),
			WGPUVertexState.`$LAYOUT`.withName("vertex"),
			WGPUPrimitiveState.`$LAYOUT`.withName("primitive"),
			C_POINTER.withName("depthStencil"),
			WGPUMultisampleState.`$LAYOUT`.withName("multisample"),
			C_POINTER.withName("fragment")
		).withName("WGPURenderPipelineDescriptor")
	}
}

@JvmInline
actual value class WGPURequestAdapterOptions(actual val handler: NativeAddress) {
	actual var compatibleSurface: WGPUSurface?
		get() = TODO()
		set(newValue) = TODO()

	actual var powerPreference: WGPUPowerPreference
		get() = TODO()
		set(newValue) = TODO()

	actual var backendType: WGPUBackendType
		get() = TODO()
		set(newValue) = TODO()

	actual var forceFallbackAdapter: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("compatibleSurface"),
			C_INT.withName("powerPreference"),
			C_INT.withName("backendType"),
			C_INT.withName("forceFallbackAdapter")
		).withName("WGPURequestAdapterOptions")
	}
}

@JvmInline
actual value class WGPURequiredLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits
		get() = get("limits", 0L).let(::WGPULimits)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPULimits.`$LAYOUT`.withName("limits")
		).withName("WGPURequiredLimits")
	}
}

@JvmInline
actual value class WGPUSamplerBindingLayout(actual val handler: NativeAddress) {
	actual var type: WGPUSamplerBindingType
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("type")
		).withName("WGPUSamplerBindingLayout")
	}
}

@JvmInline
actual value class WGPUSamplerDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeU: WGPUAddressMode
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeV: WGPUAddressMode
		get() = TODO()
		set(newValue) = TODO()

	actual var addressModeW: WGPUAddressMode
		get() = TODO()
		set(newValue) = TODO()

	actual var magFilter: WGPUFilterMode
		get() = TODO()
		set(newValue) = TODO()

	actual var minFilter: WGPUFilterMode
		get() = TODO()
		set(newValue) = TODO()

	actual var mipmapFilter: WGPUMipmapFilterMode
		get() = TODO()
		set(newValue) = TODO()

	actual var lodMinClamp: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var lodMaxClamp: Float
		get() = TODO()
		set(newValue) = TODO()

	actual var compare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = TODO()

	actual var maxAnisotropy: UShort
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPUShaderModuleDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUShaderModuleDescriptor")
	}
}

@JvmInline
actual value class WGPUShaderSourceSPIRV(actual val handler: NativeAddress) {
	actual var codeSize: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var code: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("codeSize"),
			C_POINTER.withName("code")
		).withName("WGPUShaderSourceSPIRV")
	}
}

@JvmInline
actual value class WGPUShaderSourceWGSL(actual val handler: NativeAddress) {
	actual var code: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("code")
		).withName("WGPUShaderSourceWGSL")
	}
}

@JvmInline
actual value class WGPUStencilFaceState(actual val handler: NativeAddress) {
	actual var compare: WGPUCompareFunction
		get() = TODO()
		set(newValue) = TODO()

	actual var failOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = TODO()

	actual var depthFailOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = TODO()

	actual var passOp: WGPUStencilOperation
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("compare"),
			C_INT.withName("failOp"),
			C_INT.withName("depthFailOp"),
			C_INT.withName("passOp")
		).withName("WGPUStencilFaceState")
	}
}

@JvmInline
actual value class WGPUStorageTextureBindingLayout(actual val handler: NativeAddress) {
	actual var access: WGPUStorageTextureAccess
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var viewDimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("access"),
			C_INT.withName("format"),
			C_INT.withName("viewDimension")
		).withName("WGPUStorageTextureBindingLayout")
	}
}

@JvmInline
actual value class WGPUSupportedLimits(actual val handler: NativeAddress) {
	actual val limits: WGPULimits
		get() = get("limits", 0L).let(::WGPULimits)

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			WGPULimits.`$LAYOUT`.withName("limits")
		).withName("WGPUSupportedLimits")
	}
}

@JvmInline
actual value class WGPUSurfaceCapabilities(actual val handler: NativeAddress) {
	actual var usages: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var formatCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var formats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = TODO()

	actual var presentModeCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var presentModes: ArrayHolder<WGPUPresentMode>?
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaModeCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaModes: ArrayHolder<WGPUCompositeAlphaMode>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("usages"),
			C_LONG.withName("formatCount"),
			C_POINTER.withName("formats"),
			C_LONG.withName("presentModeCount"),
			C_POINTER.withName("presentModes"),
			C_LONG.withName("alphaModeCount"),
			C_POINTER.withName("alphaModes")
		).withName("WGPUSurfaceCapabilities")
	}
}

@JvmInline
actual value class WGPUSurfaceConfiguration(actual val handler: NativeAddress) {
	actual var device: WGPUDevice?
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var width: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var height: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormatCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = TODO()

	actual var alphaMode: WGPUCompositeAlphaMode
		get() = TODO()
		set(newValue) = TODO()

	actual var presentMode: WGPUPresentMode
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPUSurfaceDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label")
		).withName("WGPUSurfaceDescriptor")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceAndroidNativeWindow(actual val handler: NativeAddress) {
	actual var window: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("window")
		).withName("WGPUSurfaceSourceAndroidNativeWindow")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceMetalLayer(actual val handler: NativeAddress) {
	actual var layer: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("layer")
		).withName("WGPUSurfaceSourceMetalLayer")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceWaylandSurface(actual val handler: NativeAddress) {
	actual var display: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var surface: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("display"),
			C_POINTER.withName("surface")
		).withName("WGPUSurfaceSourceWaylandSurface")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceWindowsHWND(actual val handler: NativeAddress) {
	actual var hinstance: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var hwnd: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("hinstance"),
			C_POINTER.withName("hwnd")
		).withName("WGPUSurfaceSourceWindowsHWND")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceXCBWindow(actual val handler: NativeAddress) {
	actual var connection: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var window: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("connection"),
			C_INT.withName("window")
		).withName("WGPUSurfaceSourceXCBWindow")
	}
}

@JvmInline
actual value class WGPUSurfaceSourceXlibWindow(actual val handler: NativeAddress) {
	actual var display: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var window: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("display"),
			C_LONG.withName("window")
		).withName("WGPUSurfaceSourceXlibWindow")
	}
}

@JvmInline
actual value class WGPUSurfaceTexture(actual val handler: NativeAddress) {
	actual var texture: WGPUTexture?
		get() = TODO()
		set(newValue) = TODO()

	actual var status: WGPUSurfaceGetCurrentTextureStatus
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("texture"),
			C_INT.withName("status")
		).withName("WGPUSurfaceTexture")
	}
}

@JvmInline
actual value class WGPUTextureBindingLayout(actual val handler: NativeAddress) {
	actual var sampleType: WGPUTextureSampleType
		get() = TODO()
		set(newValue) = TODO()

	actual var viewDimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = TODO()

	actual var multisampled: Boolean
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("sampleType"),
			C_INT.withName("viewDimension"),
			C_INT.withName("multisampled")
		).withName("WGPUTextureBindingLayout")
	}
}

@JvmInline
actual value class WGPUTextureDataLayout(actual val handler: NativeAddress) {
	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var bytesPerRow: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var rowsPerImage: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("offset"),
			C_INT.withName("bytesPerRow"),
			C_INT.withName("rowsPerImage")
		).withName("WGPUTextureDataLayout")
	}
}

@JvmInline
actual value class WGPUTextureDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var dimension: WGPUTextureDimension
		get() = TODO()
		set(newValue) = TODO()

	actual val size: WGPUExtent3D
		get() = get("size", 0L).let(::WGPUExtent3D)

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevelCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var sampleCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormatCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var viewFormats: ArrayHolder<WGPUTextureFormat>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("label"),
			C_LONG.withName("usage"),
			C_INT.withName("dimension"),
			WGPUExtent3D.`$LAYOUT`.withName("size"),
			C_INT.withName("format"),
			C_INT.withName("mipLevelCount"),
			C_INT.withName("sampleCount"),
			C_LONG.withName("viewFormatCount"),
			C_POINTER.withName("viewFormats")
		).withName("WGPUTextureDescriptor")
	}
}

@JvmInline
actual value class WGPUTextureViewDescriptor(actual val handler: NativeAddress) {
	actual var label: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var format: WGPUTextureFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var dimension: WGPUTextureViewDimension
		get() = TODO()
		set(newValue) = TODO()

	actual var baseMipLevel: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var mipLevelCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var baseArrayLayer: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var arrayLayerCount: UInt
		get() = TODO()
		set(newValue) = TODO()

	actual var aspect: WGPUTextureAspect
		get() = TODO()
		set(newValue) = TODO()

	actual var usage: ULong
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
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
	}
}

@JvmInline
actual value class WGPUVertexAttribute(actual val handler: NativeAddress) {
	actual var format: WGPUVertexFormat
		get() = TODO()
		set(newValue) = TODO()

	actual var offset: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var shaderLocation: UInt
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_INT.withName("format"),
			C_LONG.withName("offset"),
			C_INT.withName("shaderLocation")
		).withName("WGPUVertexAttribute")
	}
}

@JvmInline
actual value class WGPUVertexBufferLayout(actual val handler: NativeAddress) {
	actual var arrayStride: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var stepMode: WGPUVertexStepMode
		get() = TODO()
		set(newValue) = TODO()

	actual var attributeCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var attributes: ArrayHolder<WGPUVertexAttribute>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_LONG.withName("arrayStride"),
			C_INT.withName("stepMode"),
			C_LONG.withName("attributeCount"),
			C_POINTER.withName("attributes")
		).withName("WGPUVertexBufferLayout")
	}
}

@JvmInline
actual value class WGPUVertexState(actual val handler: NativeAddress) {
	actual var module: WGPUShaderModule?
		get() = TODO()
		set(newValue) = TODO()

	actual var entryPoint: CString?
		get() = TODO()
		set(newValue) = TODO()

	actual var constantCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var constants: ArrayHolder<WGPUConstantEntry>?
		get() = TODO()
		set(newValue) = TODO()

	actual var bufferCount: ULong
		get() = TODO()
		set(newValue) = TODO()

	actual var buffers: ArrayHolder<WGPUVertexBufferLayout>?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("module"),
			C_POINTER.withName("entryPoint"),
			C_LONG.withName("constantCount"),
			C_POINTER.withName("constants"),
			C_LONG.withName("bufferCount"),
			C_POINTER.withName("buffers")
		).withName("WGPUVertexState")
	}
}

@JvmInline
actual value class WGPUChainedStruct(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStruct")
	}
}

@JvmInline
actual value class WGPUChainedStructOut(actual val handler: NativeAddress) {
	actual var next: WGPUChainedStructOut?
		get() = TODO()
		set(newValue) = TODO()

	actual var sType: WGPUSType
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("next"),
			C_INT.withName("sType")
		).withName("WGPUChainedStructOut")
	}
}

@JvmInline
actual value class WGPUBufferMapCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUBufferMapCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUBufferMapCallbackInfo")
	}
}

@JvmInline
actual value class WGPUCompilationInfoCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUCompilationInfoCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCompilationInfoCallbackInfo")
	}
}

@JvmInline
actual value class WGPUCreateComputePipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUCreateComputePipelineAsyncCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateComputePipelineAsyncCallbackInfo")
	}
}

@JvmInline
actual value class WGPUCreateRenderPipelineAsyncCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUCreateRenderPipelineAsyncCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUCreateRenderPipelineAsyncCallbackInfo")
	}
}

@JvmInline
actual value class WGPUDeviceLostCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUDeviceLostCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUDeviceLostCallbackInfo")
	}
}

@JvmInline
actual value class WGPUPopErrorScopeCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUPopErrorScopeCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUPopErrorScopeCallbackInfo")
	}
}

@JvmInline
actual value class WGPUQueueWorkDoneCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUQueueWorkDoneCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUQueueWorkDoneCallbackInfo")
	}
}

@JvmInline
actual value class WGPURequestAdapterCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPURequestAdapterCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPURequestAdapterCallbackInfo")
	}
}

@JvmInline
actual value class WGPURequestDeviceCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPURequestDeviceCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPURequestDeviceCallbackInfo")
	}
}

@JvmInline
actual value class WGPUUncapturedErrorCallbackInfo(actual val handler: NativeAddress) {
	actual var nextInChain: WGPUChainedStruct?
		get() = TODO()
		set(newValue) = TODO()

	actual var callback: CallbackHolder<WGPUUncapturedErrorCallback>?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata1: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	actual var userdata2: NativeAddress?
		get() = TODO()
		set(newValue) = TODO()

	private fun getLayout(name: String)
		= WGPUStorageTextureBindingLayout.`$LAYOUT`.withName(name) as AddressLayout

	private fun get(name: String, offset: Long)
		= handler.handler.get(getLayout(name), offset).let(::NativeAddress)

	companion object {
		internal val `$LAYOUT` = structLayout(
			C_POINTER.withName("nextInChain"),
			C_POINTER.withName("callback"),
			C_POINTER.withName("userdata1"),
			C_POINTER.withName("userdata2")
		).withName("WGPUUncapturedErrorCallbackInfo")
	}
}

