package webgpu

fun UInt.toBoolean() = this > 0u
fun Boolean.toUInt() = if (this) 1u else 0u