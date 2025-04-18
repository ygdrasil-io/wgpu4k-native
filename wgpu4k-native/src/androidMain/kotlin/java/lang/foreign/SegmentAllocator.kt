package java.lang.foreign

import com.sun.jna.Pointer
import ffi.JnaArena
import ffi.NativeAddress

/**
 * @suppress
 */
class SegmentAllocator(internal val arena: JnaArena) {
    fun allocate(layout: ValueLayout): MemorySegment = MemorySegment(arena.allocate(layout.size), layout.size)
        .also { it.fillWithZero() }

    fun allocate(size: Long): NativeAddress = MemorySegment(arena.allocate(size), size)
        .also { it.fillWithZero() }
        .pointer

    fun allocate(layout: ValueLayout, size: Long): MemorySegment =
        MemorySegment(arena.allocate(layout.size * size), layout.size * size)

    fun allocateFrom(layout: ValueLayout.OfByte, values: ByteArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(layout: ValueLayout.OfShort, values: ShortArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(layout: ValueLayout.OfInt, values: IntArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(layout: ValueLayout.OfLong, values: LongArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(layout: ValueLayout.OfFloat, values: FloatArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(layout: ValueLayout.OfDouble, values: DoubleArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(label: String): Pointer {
        return arena.allocateFrom(label)
    }
}