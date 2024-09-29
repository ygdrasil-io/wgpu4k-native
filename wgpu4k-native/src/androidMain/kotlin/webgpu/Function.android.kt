@file:Suppress("NOTHING_TO_INLINE", "INLINE_WARNING")

package webgpu

import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual inline  fun wgpuDeviceGetQueue(handler: WGPUDevice): WGPUQueue? = NativeWgpu4k.wgpuDeviceGetQueue(handler.value)
    .takeIf { it != 0L }
    ?.let(::WGPUQueue)
