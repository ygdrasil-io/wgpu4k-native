package ffi

class MemorySegment(
    val handler: java.lang.foreign.MemorySegment
)

actual typealias NativeAddress = MemorySegment