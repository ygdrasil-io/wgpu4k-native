// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.Callback
import ffi.CString
import ffi.NativeAddress

interface WGPUBufferMapCallback : Callback {
	fun invoke(status: WGPUMapAsyncStatus, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUCompilationInfoCallback : Callback {
	fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUCreateComputePipelineAsyncCallback : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUDeviceLostCallback : Callback {
	fun invoke(device: WGPUDevice, reason: WGPUDeviceLostReason, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUPopErrorScopeCallback : Callback {
	fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUQueueWorkDoneCallback : Callback {
	fun invoke(status: WGPUQueueWorkDoneStatus, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPURequestAdapterCallback : Callback {
	fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPURequestDeviceCallback : Callback {
	fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

interface WGPUUncapturedErrorCallback : Callback {
	fun invoke(device: WGPUDevice, type: WGPUErrorType, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)
}

