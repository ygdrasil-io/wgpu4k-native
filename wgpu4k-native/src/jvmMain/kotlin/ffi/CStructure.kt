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
}