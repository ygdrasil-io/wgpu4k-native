package io.ygdrasil.kffi

import kotlin.jvm.JvmInline

@JvmInline
value class ArrayHolder<T>(val handler: NativeAddress) {
    fun toBuffer(size: ULong): MemoryBuffer {
        return MemoryBuffer(handler, size)
    }
}
