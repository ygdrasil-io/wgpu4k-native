package ffi

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        TODO("Not yet implemented")
    }
}