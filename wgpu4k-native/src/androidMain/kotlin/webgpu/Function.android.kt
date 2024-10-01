@file:Suppress("NOTHING_TO_INLINE", "INLINE_WARNING")

package webgpu

import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual inline  fun wgpuDeviceGetQueue2(handler: WGPUDevice): WGPUQueue? = NativeWgpu4k.wgpuDeviceGetQueue(handler.handler)
    .takeIf { it != 0L }
    ?.let(::WGPUQueue)
