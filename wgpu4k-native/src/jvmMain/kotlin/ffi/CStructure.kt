package ffi

import webgpu.toInt
import java.lang.foreign.AddressLayout
import java.lang.foreign.StructLayout
import java.lang.foreign.ValueLayout

interface AddressProvider {
    fun getLayout(name: String) : AddressLayout
}

interface CStructure : AddressProvider {
    val handler: NativeAddress

    fun get(layout: StructLayout, offset: Long)
            = handler.handler.get(layout as AddressLayout, offset).let(::NativeAddress)

    fun getUInt(layout: StructLayout, offset: Long): UInt
            = handler.handler.get(layout as ValueLayout.OfInt, offset).toUInt()

    fun getInt(layout: StructLayout, offset: Long): Int
            = handler.handler.get(layout as ValueLayout.OfInt, offset)

    fun getULong(layout: StructLayout, offset: Long): ULong
            = handler.handler.get(layout as ValueLayout.OfLong, offset).toULong()

    fun getUShort(layout: StructLayout, offset: Long): UShort
            = handler.handler.get(layout as ValueLayout.OfShort, offset).toUShort()

    fun getShort(layout: StructLayout, offset: Long): Short
            = handler.handler.get(layout as ValueLayout.OfShort, offset)

    fun getFloat(layout: StructLayout, offset: Long): Float
            = handler.handler.get(layout as ValueLayout.OfFloat, offset)

    fun getDouble(layout: StructLayout, offset: Long): Double
            = handler.handler.get(layout as ValueLayout.OfDouble, offset)

    fun set(layout: StructLayout, offset: Long, address: NativeAddress?)
            = handler.handler.set(layout as AddressLayout, offset, address?.handler)

    fun set(layout: StructLayout, offset: Long, value: UInt)
            = handler.handler.set(layout as ValueLayout.OfInt, offset, value.toInt())

    fun set(layout: StructLayout, offset: Long, value: Int)
            = handler.handler.set(layout as ValueLayout.OfInt, offset, value)

    fun set(layout: StructLayout, offset: Long, value: Boolean)
            = handler.handler.set(layout as ValueLayout.OfInt, offset, value.toInt())

    fun set(layout: StructLayout, offset: Long, value: ULong)
            = handler.handler.set(layout as ValueLayout.OfLong, offset, value.toLong())

    fun set(layout: StructLayout, offset: Long, value: UShort)
            = handler.handler.set(layout as ValueLayout.OfShort, offset, value.toShort())

    fun set(layout: StructLayout, offset: Long, value: Short)
            = handler.handler.set(layout as ValueLayout.OfShort, offset, value)

    fun set(layout: StructLayout, offset: Long, value: Float)
            = handler.handler.set(layout as ValueLayout.OfFloat, offset, value)

    fun set(layout: StructLayout, offset: Long, value: Double)
            = handler.handler.set(layout as ValueLayout.OfDouble, offset, value)
}