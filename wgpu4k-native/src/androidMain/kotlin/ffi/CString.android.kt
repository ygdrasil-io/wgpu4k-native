package ffi

import com.sun.jna.Pointer

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? = handler.pointer.getString(0)
}