// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.Callback
import ffi.CString
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress

actual interface WGPUBufferMapCallback : Callback {
	actual fun invoke(status: WGPUMapAsyncStatus, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> = TODO()
	}
}

actual interface WGPUCompilationInfoCallback : Callback {
	actual fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCompilationInfoCallback): CallbackHolder<WGPUCompilationInfoCallback> = TODO()
	}
}

actual interface WGPUCreateComputePipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateComputePipelineAsyncCallback): CallbackHolder<WGPUCreateComputePipelineAsyncCallback> = TODO()
	}
}

actual interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateRenderPipelineAsyncCallback): CallbackHolder<WGPUCreateRenderPipelineAsyncCallback> = TODO()
	}
}

actual interface WGPUDeviceLostCallback : Callback {
	actual fun invoke(device: WGPUDevice?, reason: WGPUDeviceLostReason, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUDeviceLostCallback): CallbackHolder<WGPUDeviceLostCallback> = TODO()
	}
}

actual interface WGPUPopErrorScopeCallback : Callback {
	actual fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUPopErrorScopeCallback): CallbackHolder<WGPUPopErrorScopeCallback> = TODO()
	}
}

actual interface WGPUQueueWorkDoneCallback : Callback {
	actual fun invoke(status: WGPUQueueWorkDoneStatus, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUQueueWorkDoneCallback): CallbackHolder<WGPUQueueWorkDoneCallback> = TODO()
	}
}

actual interface WGPURequestAdapterCallback : Callback {
	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> = TODO()
	}
}

actual interface WGPURequestDeviceCallback : Callback {
	actual fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice?, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestDeviceCallback): CallbackHolder<WGPURequestDeviceCallback> = TODO()
	}
}

actual interface WGPUUncapturedErrorCallback : Callback {
	actual fun invoke(device: WGPUDevice?, type: WGPUErrorType, message: CString?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUUncapturedErrorCallback): CallbackHolder<WGPUUncapturedErrorCallback> = TODO()
	}
}

