// This file has been generated DO NOT EDIT !!!
package webgpu

interface WGPUBufferMap {
	fun invoke(status: UInt, message: Long, userData1: Long)
}

interface WGPUCompilationInfo {
	fun invoke(status: UInt, compilation_info: Long, userData1: Long)
}

interface WGPUCreateComputePipelineAsync {
	fun invoke(status: UInt, pipeline: Long, message: Long, userData1: Long)
}

interface WGPUCreateRenderPipelineAsync {
	fun invoke(status: UInt, pipeline: Long, message: Long, userData1: Long)
}

interface WGPUDeviceLost {
	fun invoke(device: Long, reason: UInt, message: Long, userData1: Long)
}

interface WGPUPopErrorScope {
	fun invoke(status: UInt, type: UInt, message: Long, userData1: Long)
}

interface WGPUQueueWorkDone {
	fun invoke(status: UInt, userData1: Long)
}

interface WGPURequestAdapter {
	fun invoke(status: UInt, adapter: Long, message: Long, userData1: Long)
}

interface WGPURequestDevice {
	fun invoke(status: UInt, device: Long, message: Long, userData1: Long)
}

interface WGPUUncapturedError {
	fun invoke(device: Long, type: UInt, message: Long, userData1: Long)
}

