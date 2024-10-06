package ffi

import webgpu.toInt
import java.lang.foreign.AddressLayout
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemorySegment.Companion.NULL
import java.lang.foreign.ValueLayout.Companion.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.Companion.JAVA_FLOAT
import java.lang.foreign.ValueLayout.Companion.JAVA_INT
import java.lang.foreign.ValueLayout.Companion.JAVA_LONG
import java.lang.foreign.ValueLayout.Companion.JAVA_SHORT

interface CStructure {
    val handler: NativeAddress

    fun get(layout: GroupLayout, offset: Long)
            = handler.get(layout as AddressLayout, offset)

    fun getUInt(layout: GroupLayout, offset: Long): UInt
            = handler.get(JAVA_INT, offset).toUInt()

    fun getInt(layout: GroupLayout, offset: Long): Int
            = handler.get(JAVA_INT, offset)

    fun getULong(layout: GroupLayout, offset: Long): ULong
            = handler.get(JAVA_LONG, offset).toULong()

    fun getUShort(layout: GroupLayout, offset: Long): UShort
            = handler.get(JAVA_SHORT, offset).toUShort()

    fun getShort(layout: GroupLayout, offset: Long): Short
            = handler.get(JAVA_SHORT, offset)

    fun getFloat(layout: GroupLayout, offset: Long): Float
            = handler.get(JAVA_FLOAT, offset)

    fun getDouble(layout: GroupLayout, offset: Long): Double
            = handler.get(JAVA_DOUBLE, offset)

    fun set(layout: GroupLayout, offset: Long, address: NativeAddress?)
            = handler.set(layout as AddressLayout, offset, address ?: NULL)

    fun set(layout: GroupLayout, offset: Long, value: UInt)
            = handler.set(JAVA_INT, offset, value.toInt())

    fun set(layout: GroupLayout, offset: Long, value: Int)
            = handler.set(JAVA_INT, offset, value)

    fun set(layout: GroupLayout, offset: Long, value: Boolean)
            = handler.set(JAVA_INT, offset, value.toInt())

    fun set(layout: GroupLayout, offset: Long, value: ULong)
            = handler.set(JAVA_LONG, offset, value.toLong())

    fun set(layout: GroupLayout, offset: Long, value: UShort)
            = handler.set(JAVA_SHORT, offset, value.toShort())

    fun set(layout: GroupLayout, offset: Long, value: Short)
            = handler.set(JAVA_SHORT, offset, value)

    fun set(layout: GroupLayout, offset: Long, value: Float)
            = handler.set(JAVA_FLOAT, offset, value)

    fun set(layout: GroupLayout, offset: Long, value: Double)
            = handler.set(JAVA_DOUBLE, offset, value)
}