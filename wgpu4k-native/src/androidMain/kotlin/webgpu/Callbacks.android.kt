// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.Callback
import ffi.CString
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress

actual interface WGPUBufferMapCallback : Callback {
	actual fun invoke(status: WGPUMapAsyncStatus, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUCompilationInfoCallback : Callback {
	actual fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, compilationInfo: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCompilationInfoCallback): CallbackHolder<WGPUCompilationInfoCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, compilationInfo: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), compilationInfo.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUCompilationInfo(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUCreateComputePipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, pipeline: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateComputePipelineAsyncCallback): CallbackHolder<WGPUCreateComputePipelineAsyncCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, pipeline: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), pipeline.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUComputePipeline(it) }, message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, pipeline: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateRenderPipelineAsyncCallback): CallbackHolder<WGPUCreateRenderPipelineAsyncCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, pipeline: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), pipeline.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPURenderPipeline(it) }, message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUDeviceLostCallback : Callback {
	actual fun invoke(device: WGPUDevice?, reason: WGPUDeviceLostReason, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(device: com.sun.jna.Pointer, reason: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUDeviceLostCallback): CallbackHolder<WGPUDeviceLostCallback> {
			val callbackFunction = object : Function {
				override fun apply(device: com.sun.jna.Pointer, reason: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(device.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUDevice(it) }, reason.toUInt(), message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUPopErrorScopeCallback : Callback {
	actual fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, type: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUPopErrorScopeCallback): CallbackHolder<WGPUPopErrorScopeCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, type: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), type.toUInt(), message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUQueueWorkDoneCallback : Callback {
	actual fun invoke(status: WGPUQueueWorkDoneStatus, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUQueueWorkDoneCallback): CallbackHolder<WGPUQueueWorkDoneCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPURequestAdapterCallback : Callback {
	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, adapter: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, adapter: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), adapter.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUAdapter(it) }, message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPURequestDeviceCallback : Callback {
	actual fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(status: Int, device: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestDeviceCallback): CallbackHolder<WGPURequestDeviceCallback> {
			val callbackFunction = object : Function {
				override fun apply(status: Int, device: com.sun.jna.Pointer, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(status.toUInt(), device.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUDevice(it) }, message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

actual interface WGPUUncapturedErrorCallback : Callback {
	actual fun invoke(device: WGPUDevice?, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	interface Function : com.sun.jna.Callback {
		fun apply(device: com.sun.jna.Pointer, type: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUUncapturedErrorCallback): CallbackHolder<WGPUUncapturedErrorCallback> {
			val callbackFunction = object : Function {
				override fun apply(device: com.sun.jna.Pointer, type: Int, message: com.sun.jna.Pointer, userdata1: com.sun.jna.Pointer, userdata2: com.sun.jna.Pointer) {
					callback.invoke(device.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUDevice(it) }, type.toUInt(), message.takeIf { it != com.sun.jna.Pointer.NULL }?.let { WGPUStringView(it) }, userdata1, userdata2)
				}
			}
			return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)
		}
	}
}

