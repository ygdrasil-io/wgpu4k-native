package ffi

import java.lang.foreign.SegmentAllocator

actual class MemoryAllocator : AutoCloseable {

    private val arena = JnaArena()

    val allocator: SegmentAllocator = SegmentAllocator(arena)

    actual override fun close() {
        arena.close()
    }
}