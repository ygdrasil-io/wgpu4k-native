package org.graphiks.kffi

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        val raw = handler.rawValue
        if (raw == 0L) return null
        return handler.toJvmSegment(Long.MAX_VALUE).getString(0)
    }

    actual fun toKString(size: ULong): String? {
        if (handler.rawValue == 0L) return null
        val bytes = ByteArray(size.toInt())
        handler.toJvmSegment(size.toLong()).asByteBuffer().get(bytes)
        return String(bytes)
    }
}
