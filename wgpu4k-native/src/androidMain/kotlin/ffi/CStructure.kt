package ffi

import webgpu.toInt
import java.lang.foreign.AddressLayout
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemorySegment.Companion.NULL
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.Companion.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.Companion.JAVA_FLOAT
import java.lang.foreign.ValueLayout.Companion.JAVA_INT
import java.lang.foreign.ValueLayout.Companion.JAVA_LONG
import java.lang.foreign.ValueLayout.Companion.JAVA_SHORT

interface CStructure {
    val handler: NativeAddress

    fun get(layout: ValueLayout, offset: Long)
            = handler.get(layout as? AddressLayout, offset)

    fun getUInt(offset: Long): UInt
            = handler.get(JAVA_INT, offset).toUInt()

    fun getInt(offset: Long): Int
            = handler.get(JAVA_INT, offset)

    fun getULong(offset: Long): ULong
            = handler.get(JAVA_LONG, offset).toULong()

    fun getUShort(offset: Long): UShort
            = handler.get(JAVA_SHORT, offset).toUShort()

    fun getShort(offset: Long): Short
            = handler.get(JAVA_SHORT, offset)

    fun getFloat(offset: Long): Float
            = handler.get(JAVA_FLOAT, offset)

    fun getDouble(offset: Long): Double
            = handler.get(JAVA_DOUBLE, offset)

    fun set(layout: ValueLayout, offset: Long, address: NativeAddress?)
            = handler.set(layout, offset, address ?: NULL)

    fun set(offset: Long, value: UInt)
            = handler.set(JAVA_INT, offset, value.toInt())

    fun set(offset: Long, value: Int)
            = handler.set(JAVA_INT, offset, value)

    fun set(offset: Long, value: Boolean)
            = handler.set(JAVA_INT, offset, value.toInt())

    fun set(offset: Long, value: ULong)
            = handler.set(JAVA_LONG, offset, value.toLong())

    fun set(offset: Long, value: UShort)
            = handler.set(JAVA_SHORT, offset, value.toShort())

    fun set(offset: Long, value: Short)
            = handler.set(JAVA_SHORT, offset, value)

    fun set(offset: Long, value: Float)
            = handler.set(JAVA_FLOAT, offset, value)

    fun set(offset: Long, value: Double)
            = handler.set(JAVA_DOUBLE, offset, value)
}