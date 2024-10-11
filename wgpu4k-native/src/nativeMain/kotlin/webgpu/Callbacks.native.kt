@file:OptIn(ExperimentalForeignApi::class)

package webgpu

import ffi.CString
import ffi.Callback
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.findCallback
import ffi.registerCallback
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.value

actual interface WGPUBufferMapCallback : Callback {
    actual fun invoke(status: WGPUMapAsyncStatus, message: CString, userdata1: NativeAddress, userdata2: NativeAddress)

    actual companion object {

        actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> {
            val actualCallback =
                staticCFunction { arg1: UInt, arg2: COpaquePointer?, arg3: COpaquePointer?, arg4: CPointer<LongVar>? ->
                    val callback = findCallback<WGPUBufferMapCallback>(arg4?.pointed?.value ?: error("Missing callback address on last argument"))
                        ?: error("Callback not found with address ${arg4?.pointed?.value} and type WGPUBufferMapCallback")
                    callback.invoke(arg1, CString(arg2!!.rawValue.toLong()), arg3!!.rawValue.toLong(), arg4!!.rawValue.toLong())
                }
            registerCallback(actualCallback.rawValue.toLong(), callback)
            return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
        }
    }
}

