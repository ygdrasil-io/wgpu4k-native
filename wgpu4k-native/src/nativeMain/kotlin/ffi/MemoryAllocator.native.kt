@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc

actual class MemoryAllocator : AutoCloseable {

    val allocator = Arena()

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return allocator.alloc(sizeInByte, 0).rawPtr.toLong()
    }

    actual override fun close() {
        allocator.clear()
    }
}