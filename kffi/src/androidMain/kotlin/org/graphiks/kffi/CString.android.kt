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
        for (i in 0 until len) bytes[i] = unsafe.getByte(start + i)
        return String(bytes, StandardCharsets.UTF_8)
    }

    actual fun toKString(size: ULong): String? {
        if (handler.rawValue == 0L) return null
        val unsafe = AndroidUnsafe.get()
        val bytes = ByteArray(size.toInt())
        for (i in bytes.indices) bytes[i] = unsafe.getByte(handler.rawValue + i)
        return String(bytes, StandardCharsets.UTF_8)
    }
}
