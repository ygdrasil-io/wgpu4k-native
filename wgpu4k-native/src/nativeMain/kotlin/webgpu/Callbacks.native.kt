// This file has been generated DO NOT EDIT !!!
@file:OptIn(ExperimentalForeignApi::class)

package webgpu

import ffi.CString
import ffi.Callback
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.findCallback
import ffi.registerCallback
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.value

actual interface WGPUBufferMapCallback : Callback {
	actual fun invoke(status: WGPUMapAsyncStatus, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> {
			val actualCallback = staticCFunction { status: UInt, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUBufferMapCallback>(address)
					?: error("Callback not found with address $address and type WGPUBufferMapCallback")
				callback.invoke(status, message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUCompilationInfoCallback : Callback {
	actual fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCompilationInfoCallback): CallbackHolder<WGPUCompilationInfoCallback> {
			val actualCallback = staticCFunction { status: UInt, compilationInfo: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUCompilationInfoCallback>(address)
					?: error("Callback not found with address $address and type WGPUCompilationInfoCallback")
				callback.invoke(status, compilationInfo?.rawValue?.toLong()?.let(::WGPUCompilationInfo), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUCreateComputePipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateComputePipelineAsyncCallback): CallbackHolder<WGPUCreateComputePipelineAsyncCallback> {
			val actualCallback = staticCFunction { status: UInt, pipeline: COpaquePointer?, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUCreateComputePipelineAsyncCallback>(address)
					?: error("Callback not found with address $address and type WGPUCreateComputePipelineAsyncCallback")
				callback.invoke(status, pipeline?.rawValue?.toLong()?.let(::WGPUComputePipeline), message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateRenderPipelineAsyncCallback): CallbackHolder<WGPUCreateRenderPipelineAsyncCallback> {
			val actualCallback = staticCFunction { status: UInt, pipeline: COpaquePointer?, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUCreateRenderPipelineAsyncCallback>(address)
					?: error("Callback not found with address $address and type WGPUCreateRenderPipelineAsyncCallback")
				callback.invoke(status, pipeline?.rawValue?.toLong()?.let(::WGPURenderPipeline), message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUDeviceLostCallback : Callback {
	actual fun invoke(device: WGPUDevice?, reason: WGPUDeviceLostReason, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUDeviceLostCallback): CallbackHolder<WGPUDeviceLostCallback> {
			val actualCallback = staticCFunction { device: COpaquePointer?, reason: UInt, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUDeviceLostCallback>(address)
					?: error("Callback not found with address $address and type WGPUDeviceLostCallback")
				callback.invoke(device?.rawValue?.toLong()?.let(::WGPUDevice), reason, message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUPopErrorScopeCallback : Callback {
	actual fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUPopErrorScopeCallback): CallbackHolder<WGPUPopErrorScopeCallback> {
			val actualCallback = staticCFunction { status: UInt, type: UInt, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUPopErrorScopeCallback>(address)
					?: error("Callback not found with address $address and type WGPUPopErrorScopeCallback")
				callback.invoke(status, type, message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUQueueWorkDoneCallback : Callback {
	actual fun invoke(status: WGPUQueueWorkDoneStatus, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUQueueWorkDoneCallback): CallbackHolder<WGPUQueueWorkDoneCallback> {
			val actualCallback = staticCFunction { status: UInt, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUQueueWorkDoneCallback>(address)
					?: error("Callback not found with address $address and type WGPUQueueWorkDoneCallback")
				callback.invoke(status, userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPURequestAdapterCallback : Callback {
	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> {
			val actualCallback = staticCFunction { status: UInt, adapter: COpaquePointer?, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPURequestAdapterCallback>(address)
					?: error("Callback not found with address $address and type WGPURequestAdapterCallback")
				callback.invoke(status, adapter?.rawValue?.toLong()?.let(::WGPUAdapter), message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPURequestDeviceCallback : Callback {
	actual fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestDeviceCallback): CallbackHolder<WGPURequestDeviceCallback> {
			val actualCallback = staticCFunction { status: UInt, device: COpaquePointer?, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPURequestDeviceCallback>(address)
					?: error("Callback not found with address $address and type WGPURequestDeviceCallback")
				callback.invoke(status, device?.rawValue?.toLong()?.let(::WGPUDevice), message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

actual interface WGPUUncapturedErrorCallback : Callback {
	actual fun invoke(device: WGPUDevice?, type: WGPUErrorType, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUUncapturedErrorCallback): CallbackHolder<WGPUUncapturedErrorCallback> {
			val actualCallback = staticCFunction { device: COpaquePointer?, type: UInt, message: COpaquePointer?, userdata1: COpaquePointer?, userdata2: COpaquePointer? ->
				val address = userdata2?.reinterpret<LongVar>()?.pointed?.value ?: error("Missing callback address on last argument")
				val callback = findCallback<WGPUUncapturedErrorCallback>(address)
					?: error("Callback not found with address $address and type WGPUUncapturedErrorCallback")
				callback.invoke(device?.rawValue?.toLong()?.let(::WGPUDevice), type, message?.rawValue?.toLong()?.let(::CString), userdata1?.rawValue?.toLong() ?: 0L, userdata2?.rawValue?.toLong() ?: 0L)
			}
			registerCallback(actualCallback.rawValue.toLong(), callback)
			return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
		}
	}
}

