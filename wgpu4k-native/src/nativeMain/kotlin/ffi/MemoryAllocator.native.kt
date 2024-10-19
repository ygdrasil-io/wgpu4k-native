@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.Arena
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.NativePointed
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.value

actual class MemoryAllocator : AutoCloseable {

    val allocator = Arena()

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return allocator.alloc(sizeInByte, 0)
            .toOpaqueNativeAddress()
    }

    actual override fun close() {
        allocator.clear()
    }

    actual fun bufferOf(value: Long): Buffer = allocator.alloc<LongVar>().also {
        it.value = value
    }.toOpaqueNativeAddress()
        .let { Buffer(it, Long.SIZE_BYTES.toULong()) }

    actual fun bufferOfAddress(value: NativeAddress): Buffer = bufferOf(value.rawValue)

    actual fun allocateFrom(value: String): CString {
        return value.cstr.getPointer(allocator)
            .reinterpret<COpaque>()
            .let(::NativeAddress)
            .let { CString(it) }
    }
}

fun NativePointed.toOpaqueNativeAddress() = rawPtr
    .toLong()
    .let(::NativeAddress)