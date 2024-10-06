@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi

actual class MemoryAllocator : AutoCloseable {

    val allocator = Arena()

    actual override fun close() {
        allocator.clear()
    }
}