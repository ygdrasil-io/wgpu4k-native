package ffi

import java.lang.foreign.MemorySegment

actual typealias NativeAddress = MemorySegment

internal fun NativeAddress?.adapt(): Long {
    return if (this == null) 0 else this.pointer.toAddress()
}