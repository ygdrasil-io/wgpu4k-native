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

interface AddressProvider {
    fun getLayout(name: String) : AddressLayout
}

interface CStructure : AddressProvider {
    val handler: NativeAddress

    fun get(name: String, offset: Long)
            = handler.get(getLayout(name), offset)

    fun getUInt(name: String, offset: Long): UInt
            = handler.get(getLayout(name) as ValueLayout.OfInt, offset).toUInt()

    fun getInt(name: String, offset: Long): Int
            = handler.get(getLayout(name) as ValueLayout.OfInt, offset)

    fun getULong(name: String, offset: Long): ULong
            = handler.get(getLayout(name) as ValueLayout.OfLong, offset).toULong()

    fun getUShort(name: String, offset: Long): UShort
            = handler.get(getLayout(name) as ValueLayout.OfShort, offset).toUShort()

    fun getShort(name: String, offset: Long): Short
            = handler.get(getLayout(name) as ValueLayout.OfShort, offset)

    fun getFloat(name: String, offset: Long): Float
            = handler.get(getLayout(name) as ValueLayout.OfFloat, offset)

    fun getDouble(name: String, offset: Long): Double
            = handler.get(getLayout(name) as ValueLayout.OfDouble, offset)

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