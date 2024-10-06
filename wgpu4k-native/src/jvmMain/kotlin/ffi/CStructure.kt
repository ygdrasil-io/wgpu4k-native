package ffi

import java.lang.foreign.AddressLayout
import java.lang.foreign.ValueLayout

interface AddressProvider {
    fun getLayout(name: String) : AddressLayout
}

interface CStructure : AddressProvider {
    val handler: NativeAddress

    fun get(name: String, offset: Long)
            = handler.handler.get(getLayout(name), offset).let(::NativeAddress)

    fun getUInt(name: String, offset: Long): UInt
            = handler.handler.get(getLayout(name) as ValueLayout.OfInt, offset).toUInt()

    fun getInt(name: String, offset: Long): Int
            = handler.handler.get(getLayout(name) as ValueLayout.OfInt, offset)

    fun getULong(name: String, offset: Long): ULong
            = handler.handler.get(getLayout(name) as ValueLayout.OfLong, offset).toULong()

    fun getUShort(name: String, offset: Long): UShort
            = handler.handler.get(getLayout(name) as ValueLayout.OfShort, offset).toUShort()

    fun getShort(name: String, offset: Long): Short
            = handler.handler.get(getLayout(name) as ValueLayout.OfShort, offset)

    fun getFloat(name: String, offset: Long): Float
            = handler.handler.get(getLayout(name) as ValueLayout.OfFloat, offset)

    fun getDouble(name: String, offset: Long): Double
            = handler.handler.get(getLayout(name) as ValueLayout.OfDouble, offset)
}