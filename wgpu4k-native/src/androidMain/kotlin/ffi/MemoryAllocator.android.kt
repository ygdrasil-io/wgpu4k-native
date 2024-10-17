package ffi

import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout

actual class MemoryAllocator : AutoCloseable {

    private val arena = JnaArena()
    private val callbacks = mutableListOf<com.sun.jna.Callback>()
    private val references = mutableListOf<Any>()

    val allocator: SegmentAllocator = SegmentAllocator(arena)

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return allocator.allocate(sizeInByte)
    }

    actual override fun close() {
        arena.close()
        callbacks.clear()
        references.clear()
    }

    actual fun bufferOf(value: Long): Buffer = allocator.allocate(ValueLayout.JAVA_LONG)
        .also { it.set(ValueLayout.JAVA_LONG, 0, value) }
        .let { Buffer(it.pointer, it.size.toULong()) }


    actual fun allocateFrom(value: String): CString = allocator
        .allocateFrom(value)
        .let(::CString)

    actual fun bufferOfAddress(value: NativeAddress): Buffer = bufferOf(value.toAddress())

    fun registerCallback(function: com.sun.jna.Callback) {
        callbacks.add(function)
    }

    fun register(it: Any) {
        references.add(it)
    }
}