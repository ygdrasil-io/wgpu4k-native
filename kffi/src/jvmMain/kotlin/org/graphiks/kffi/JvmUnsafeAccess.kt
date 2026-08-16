package org.graphiks.kffi

import sun.misc.Unsafe

/** Accès mémoire non vérifiés (mode unsafe, I3) — sun.misc.Unsafe sur l'adresse brute. */
internal object JvmUnsafeAccess {
    private val unsafe: Unsafe by lazy {
        val field = Unsafe::class.java.getDeclaredField("theUnsafe")
        field.isAccessible = true
        field.get(null) as Unsafe
    }

    fun getByte(address: Long, offset: Long): Byte = unsafe.getByte(address + offset)
    fun putByte(address: Long, offset: Long, value: Byte) = unsafe.putByte(address + offset, value)
    fun getShort(address: Long, offset: Long): Short = unsafe.getShort(address + offset)
    fun putShort(address: Long, offset: Long, value: Short) = unsafe.putShort(address + offset, value)
    fun getInt(address: Long, offset: Long): Int = unsafe.getInt(address + offset)
    fun putInt(address: Long, offset: Long, value: Int) = unsafe.putInt(address + offset, value)
    fun getLong(address: Long, offset: Long): Long = unsafe.getLong(address + offset)
    fun putLong(address: Long, offset: Long, value: Long) = unsafe.putLong(address + offset, value)
    fun getFloat(address: Long, offset: Long): Float = unsafe.getFloat(address + offset)
    fun putFloat(address: Long, offset: Long, value: Float) = unsafe.putFloat(address + offset, value)
    fun getDouble(address: Long, offset: Long): Double = unsafe.getDouble(address + offset)
    fun putDouble(address: Long, offset: Long, value: Double) = unsafe.putDouble(address + offset, value)
}
