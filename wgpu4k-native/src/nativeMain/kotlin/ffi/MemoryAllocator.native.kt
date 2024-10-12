@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.value

actual class MemoryAllocator : AutoCloseable {

    val allocator = Arena()

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return allocator.alloc(sizeInByte, 0).rawPtr.toLong()
    }

    actual override fun close() {
        allocator.clear()
    }

    actual fun bufferOf(value: Long): Buffer = allocator.alloc<LongVar>().also {
        it.value = value
    }.rawPtr.toLong().let { Buffer(it, Long.SIZE_BYTES.toULong()) }

    actual fun bufferOfAddress(value: NativeAddress): Buffer = bufferOf(value)

    actual fun allocateFrom(value: String): CString {
        return value.cstr.getPointer(allocator)
            .let { CString(it.rawValue.toLong()) }
    }
}