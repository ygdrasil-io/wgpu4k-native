@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.value

actual class MemoryBuffer actual constructor(actual val handler: NativeAddress, actual val size: ULong) {
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

    actual fun readInt(): Int {
        return handler.reinterpret<IntVar>().pointed.value
    }

    actual fun writeInt(value: Int) {
    }

    actual fun readPointer(): NativeAddress {
        TODO("Not yet implemented")
    }

    actual fun writePointer(value: NativeAddress) {
    }

}