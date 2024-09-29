@file:OptIn(ExperimentalForeignApi::class)

package webgpu

import kotlinx.cinterop.ExperimentalForeignApi

actual fun wgpuDeviceGetQueue(handler: WGPUDevice): WGPUQueue? = webgpu.native.wgpuDeviceGetQueue(handler.value)?.let(::WGPUQueue)