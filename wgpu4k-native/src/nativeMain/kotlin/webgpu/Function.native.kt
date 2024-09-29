@file:OptIn(ExperimentalForeignApi::class)
@file:Suppress("NOTHING_TO_INLINE", "INLINE_WARNING")

package webgpu

import kotlinx.cinterop.ExperimentalForeignApi

actual inline fun wgpuDeviceGetQueue(handler: WGPUDevice): WGPUQueue? = webgpu.native.wgpuDeviceGetQueue(handler.value)?.let(::WGPUQueue)