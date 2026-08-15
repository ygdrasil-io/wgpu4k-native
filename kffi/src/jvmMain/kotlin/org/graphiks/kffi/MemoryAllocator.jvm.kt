package org.graphiks.kffi

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class MemoryAllocator : AutoCloseable {

    val arena = Arena.ofConfined()

    actual fun allocate(sizeInByte: Long): NativeAddress =
        arena.allocate(sizeInByte).let { NativeAddress(it.address()) }

    actual override fun close() {
        arena.close()
    }

    actual fun bufferOf(value: Long): MemoryBuffer =
        arena.allocate(ValueLayout.JAVA_LONG)
            .also { it.set(ValueLayout.JAVA_LONG, 0, value) }
            .let { MemoryBuffer(NativeAddress(it.address()), Long.SIZE_BYTES.toULong(), it) }

    actual fun allocateFrom(value: String): CString =
        arena.allocateFrom(value)
            .let { segment ->
                CString(MemoryBuffer(NativeAddress(segment.address()), segment.byteSize().toULong(), segment).handler)
            }

    actual fun bufferOfAddress(value: NativeAddress): MemoryBuffer = bufferOf(value.rawValue)

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        arena.allocate(size.toLong())
            .let { segment -> MemoryBuffer(NativeAddress(segment.address()), size, segment) }

    actual fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer {
        val size = (Long.SIZE_BYTES * value.size).toULong()
        return allocateBuffer(size)
            .also { buffer -> value.forEachIndexed { index, pointer ->
                buffer.writePointer(pointer, (Long.SIZE_BYTES * index).toULong())
            }}
    }
}
