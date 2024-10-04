// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.Callback
import ffi.CString

interface WGPUBufferMapCallback : Callback {
	fun invoke(status: WGPUMapAsyncStatus, message: CString, userData1: Long, userData2: Long)
}

interface WGPUCompilationInfoCallback : Callback {
	fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo, userData1: Long, userData2: Long)
}

interface WGPUCreateComputePipelineAsyncCallback : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline, message: CString, userData1: Long, userData2: Long)
}

interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline, message: CString, userData1: Long, userData2: Long)
}

interface WGPUDeviceLostCallback : Callback {
	fun invoke(device: WGPUDevice, reason: WGPUDeviceLostReason, message: CString, userData1: Long, userData2: Long)
}

interface WGPUPopErrorScopeCallback : Callback {
	fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: CString, userData1: Long, userData2: Long)
}

interface WGPUQueueWorkDoneCallback : Callback {
	fun invoke(status: WGPUQueueWorkDoneStatus, userData1: Long, userData2: Long)
}

interface WGPURequestAdapterCallback : Callback {
	fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter, message: CString, userData1: Long, userData2: Long)
}

interface WGPURequestDeviceCallback : Callback {
	fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice, message: CString, userData1: Long, userData2: Long)
}

interface WGPUUncapturedErrorCallback : Callback {
	fun invoke(device: WGPUDevice, type: WGPUErrorType, message: CString, userData1: Long, userData2: Long)
}

