package ffi

import java.lang.foreign.ValueLayout

actual class Buffer actual constructor(handler: NativeAddress, actual val size: ULong) {

    actual val handler: NativeAddress = handler.handler.reinterpret(size.toLong()).let(::NativeAddress)

    actual fun readLong(): Long {
        TODO("Not yet implemented")
    }

    actual fun writeLong(value: Long) {
    }

    actual fun readFloat(): Float {
        TODO("Not yet implemented")
    }

    actual fun writeFloat(value: Float) {
    }

    actual fun readInt(): Int = handler.handler.get(ValueLayout.JAVA_INT, 0)

    actual fun writeInt(value: Int) {
    }

    actual fun readPointer(): NativeAddress {
        TODO("Not yet implemented")
    }

    actual fun writePointer(value: NativeAddress) {
    }

}