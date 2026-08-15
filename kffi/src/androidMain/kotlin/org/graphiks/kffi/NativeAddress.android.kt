package org.graphiks.kffi

@JvmInline
actual value class NativeAddress actual constructor(actual val rawValue: Long)

fun NativeAddress?.adapt(): Long = if (this == null) 0 else this.rawValue
