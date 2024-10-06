package ffi

import java.lang.foreign.Arena
import java.lang.foreign.SegmentAllocator

actual class MemoryAllocator : AutoCloseable {

    private val arena = Arena.ofConfined()
    val allocator: SegmentAllocator
        get() = arena

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return allocator.allocate(sizeInByte)
            .let(::NativeAddress)
    }

    actual override fun close() {
        arena.close()
    }
}