package webgpu

import ffi.CString
import ffi.Callback
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress

actual interface WGPUBufferMapCallback : Callback {
    actual fun invoke(status: WGPUMapAsyncStatus, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)

    actual companion object {
        actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> = TODO()
    }
}