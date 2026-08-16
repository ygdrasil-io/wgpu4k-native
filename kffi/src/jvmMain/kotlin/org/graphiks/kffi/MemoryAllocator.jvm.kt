package org.graphiks.kffi

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class MemoryAllocator actual constructor(unsafe: Boolean) : AutoCloseable {

    private val unsafe: Boolean = unsafe

    val arena = Arena.ofConfined()

    /** Drapeau de fermeture : 1 load volatil par accès unsafe au lieu de scope().isAlive (2 appels FFM). */
    private val closed = java.util.concurrent.atomic.AtomicBoolean(false)

    actual fun allocate(sizeInByte: Long): NativeAddress =
        arena.allocate(sizeInByte).let { NativeAddress(it.address()) }

    actual override fun close() {
        closed.set(true)
        arena.close()
    }

    actual fun bufferOf(value: Long): MemoryBuffer =
        arena.allocate(ValueLayout.JAVA_LONG)
            .also { it.set(ValueLayout.JAVA_LONG, 0, value) }
            .let { MemoryBuffer(NativeAddress(it.address()), Long.SIZE_BYTES.toULong(), it, unsafe, closed) }

    actual fun allocateFrom(value: String): CString =
        arena.allocateFrom(value)
            .let { segment ->
                CString(MemoryBuffer(NativeAddress(segment.address()), segment.byteSize().toULong(), segment, unsafe, closed).handler)
            }

    actual fun bufferOfAddress(value: NativeAddress): MemoryBuffer = bufferOf(value.rawValue)

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        arena.allocate(size.toLong())
            .let { segment -> MemoryBuffer(NativeAddress(segment.address()), size, segment, unsafe, closed) }

    actual fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer {
        val size = (Long.SIZE_BYTES * value.size).toULong()
        return allocateBuffer(size)
            .also { buffer -> value.forEachIndexed { index, pointer ->
                buffer.writePointer(pointer, (Long.SIZE_BYTES * index).toULong())
            }}
    }
}
