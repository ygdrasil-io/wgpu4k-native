// This file has been generated DO NOT EDIT !!!
package webgpu

interface WGPUBufferMapCallbackInfo {
	fun invoke(status: WGPUMapAsyncStatus, message: String, userData1: Long, userData2: Long)
}

interface WGPUCompilationInfoCallbackInfo {
	fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo, userData1: Long, userData2: Long)
}

interface WGPUCreateComputePipelineAsyncCallbackInfo {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline, message: String, userData1: Long, userData2: Long)
}

interface WGPUCreateRenderPipelineAsyncCallbackInfo {
	fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline, message: String, userData1: Long, userData2: Long)
}

interface WGPUDeviceLostCallbackInfo {
	fun invoke(device: WGPUDevice, reason: WGPUDeviceLostReason, message: String, userData1: Long, userData2: Long)
}

interface WGPUPopErrorScopeCallbackInfo {
	fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: String, userData1: Long, userData2: Long)
}

interface WGPUQueueWorkDoneCallbackInfo {
	fun invoke(status: WGPUQueueWorkDoneStatus, userData1: Long, userData2: Long)
}

interface WGPURequestAdapterCallbackInfo {
	fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter, message: String, userData1: Long, userData2: Long)
}

interface WGPURequestDeviceCallbackInfo {
	fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice, message: String, userData1: Long, userData2: Long)
}

interface WGPUUncapturedErrorCallbackInfo {
	fun invoke(device: WGPUDevice, type: WGPUErrorType, message: String, userData1: Long, userData2: Long)
}

