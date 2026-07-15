package io.ygdrasil.kffi

class JvmNativeAddress(
    val handler: java.lang.foreign.MemorySegment
)

actual typealias NativeAddress = JvmNativeAddress

fun NativeAddress?.adapt(): java.lang.foreign.MemorySegment? {
    return this?.handler
}
