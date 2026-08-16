package org.graphiks.kffi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

private fun Boolean.toInt() = if (this) 1 else 0

interface CStructure {
    val handler: NativeAddress

    private fun segment(): MemorySegment = handler.toJvmSegment(Long.MAX_VALUE)

    fun getAddress(offset: Long): NativeAddress {
        val raw = segment().get(ValueLayout.ADDRESS, offset)
        return NativeAddress(if (raw == MemorySegment.NULL) 0L else raw.address())
    }

    fun getUInt(offset: Long): UInt = segment().get(ValueLayout.JAVA_INT, offset).toUInt()
    fun getInt(offset: Long): Int = segment().get(ValueLayout.JAVA_INT, offset)
    fun getULong(offset: Long): ULong = segment().get(ValueLayout.JAVA_LONG, offset).toULong()
    fun getUShort(offset: Long): UShort = segment().get(ValueLayout.JAVA_SHORT, offset).toUShort()
    fun getShort(offset: Long): Short = segment().get(ValueLayout.JAVA_SHORT, offset)
    fun getFloat(offset: Long): Float = segment().get(ValueLayout.JAVA_FLOAT, offset)
    fun getDouble(offset: Long): Double = segment().get(ValueLayout.JAVA_DOUBLE, offset)

    fun set(offset: Long, address: NativeAddress) {
        segment().set(ValueLayout.ADDRESS, offset, address.toJvmSegmentOrNull() ?: MemorySegment.NULL)
    }

    fun set(offset: Long, value: UInt) = segment().set(ValueLayout.JAVA_INT, offset, value.toInt())
    fun set(offset: Long, value: Int) = segment().set(ValueLayout.JAVA_INT, offset, value)
    fun set(offset: Long, value: Boolean) = segment().set(ValueLayout.JAVA_INT, offset, value.toInt())
    fun set(offset: Long, value: ULong) = segment().set(ValueLayout.JAVA_LONG, offset, value.toLong())
    fun set(offset: Long, value: UShort) = segment().set(ValueLayout.JAVA_SHORT, offset, value.toShort())
    fun set(offset: Long, value: Short) = segment().set(ValueLayout.JAVA_SHORT, offset, value)
    fun set(offset: Long, value: Float) = segment().set(ValueLayout.JAVA_FLOAT, offset, value)
    fun set(offset: Long, value: Double) = segment().set(ValueLayout.JAVA_DOUBLE, offset, value)
}
