// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.Callback
import ffi.CString

interface WGPUBufferMapCallbackInfo : Callback {
	fun invoke(status: WGPUMapAsyncStatus, message: CString, userData1: Long, userData2: Long)
}

interface WGPUCompilationInfoCallbackInfo : Callback {
	fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo, userData1: Long, userData2: Long)
}

interface WGPUCreateComputePipelineAsyncCallbackInfo : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline, message: CString, userData1: Long, userData2: Long)
}

interface WGPUCreateRenderPipelineAsyncCallbackInfo : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline, message: CString, userData1: Long, userData2: Long)
}

interface WGPUDeviceLostCallbackInfo : Callback {
	fun invoke(device: WGPUDevice, reason: WGPUDeviceLostReason, message: CString, userData1: Long, userData2: Long)
}

interface WGPUPopErrorScopeCallbackInfo : Callback {
	fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: CString, userData1: Long, userData2: Long)
}

interface WGPUQueueWorkDoneCallbackInfo : Callback {
	fun invoke(status: WGPUQueueWorkDoneStatus, userData1: Long, userData2: Long)
}

interface WGPURequestAdapterCallbackInfo : Callback {
	fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter, message: CString, userData1: Long, userData2: Long)
}

interface WGPURequestDeviceCallbackInfo : Callback {
	fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice, message: CString, userData1: Long, userData2: Long)
}

interface WGPUUncapturedErrorCallbackInfo : Callback {
	fun invoke(device: WGPUDevice, type: WGPUErrorType, message: CString, userData1: Long, userData2: Long)
}

