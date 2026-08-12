package org.graphiks.kffi

import java.nio.charset.StandardCharsets

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        if (handler.rawValue == 0L) return null
        val unsafe = AndroidUnsafe.get()
        val start = handler.rawValue
        var len = 0
        while (unsafe.getByte(start + len) != 0.toByte()) len++
        val bytes = ByteArray(len)
        unsafe.copyMemory(null, start, bytes, 16L, len.toLong())
        return String(bytes, StandardCharsets.UTF_8)
    }

    actual fun toKString(size: ULong): String? {
        if (handler.rawValue == 0L) return null
        val unsafe = AndroidUnsafe.get()
        val bytes = ByteArray(size.toInt())
        unsafe.copyMemory(null, handler.rawValue, bytes, 16L, size.toLong())
        return String(bytes, StandardCharsets.UTF_8)
    }
}
