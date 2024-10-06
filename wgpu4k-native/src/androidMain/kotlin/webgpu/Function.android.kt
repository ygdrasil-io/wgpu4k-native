@file:Suppress("NOTHING_TO_INLINE", "INLINE_WARNING")

package webgpu

import com.sun.jna.Pointer
import ffi.NativeAddress
import ffi.toAddress
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual inline  fun wgpuDeviceGetQueue2(handler: WGPUDevice): WGPUQueue? = NativeWgpu4k.wgpuDeviceGetQueue(handler.handler.pointer.toAddress())
    .takeIf { it != 0L }
    ?.let { NativeAddress(Pointer(it), Long.SIZE_BYTES.toLong()) }
    ?.let(::WGPUQueue)
