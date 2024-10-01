// This file has been generated DO NOT EDIT !!!
package webgpu

interface WGPUBufferMapCallback {
	fun invoke(status: UInt, message: Long, userData1: Long)
}

interface WGPUCompilationInfoCallback {
	fun invoke(status: UInt, compilation_info: Long, userData1: Long)
}

interface WGPUCreateComputePipelineAsyncCallback {
	fun invoke(status: UInt, pipeline: Long, message: Long, userData1: Long)
}

interface WGPUCreateRenderPipelineAsyncCallback {
	fun invoke(status: UInt, pipeline: Long, message: Long, userData1: Long)
}

interface WGPUDeviceLostCallback {
	fun invoke(device: Long, reason: UInt, message: Long, userData1: Long)
}

interface WGPUPopErrorScopeCallback {
	fun invoke(status: UInt, type: UInt, message: Long, userData1: Long)
}

interface WGPUQueueWorkDoneCallback {
	fun invoke(status: UInt, userData1: Long)
}

interface WGPURequestAdapterCallback {
	fun invoke(status: UInt, adapter: Long, message: Long, userData1: Long)
}

interface WGPURequestDeviceCallback {
	fun invoke(status: UInt, device: Long, message: Long, userData1: Long)
}

interface WGPUUncapturedErrorCallback {
	fun invoke(device: Long, type: UInt, message: Long, userData1: Long)
}

