@file:OptIn(ExperimentalForeignApi::class)
@file:Suppress("NOTHING_TO_INLINE", "INLINE_WARNING")

package webgpu

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCPointer

actual inline fun wgpuDeviceGetQueue(handler: WGPUDevice): WGPUQueue? = webgpu.native.wgpuDeviceGetQueue(handler.handler.toCPointer())
    ?.rawValue?.toLong()
    ?.let(::WGPUQueue)