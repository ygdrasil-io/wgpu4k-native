package org.graphiks.kffi

actual class NativeAddress(val rawValue: Long)

fun NativeAddress?.adapt(): Long = if (this == null) 0 else this.rawValue
