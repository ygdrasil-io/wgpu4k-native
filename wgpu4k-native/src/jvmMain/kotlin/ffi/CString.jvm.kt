package ffi

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        return handler.handler.getString(0)
    }

    actual fun toKString(size: ULong): String? {
        return ByteArray(size.toInt())
            .also { byte ->
                handler.handler.reinterpret(size.toLong())
                    .asByteBuffer()
                    .get(byte)
            }.let { String(it) }
    }
}