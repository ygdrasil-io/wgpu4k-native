package ffi

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

expect class MemoryAllocator() : AutoCloseable {

    fun allocate(sizeInByte: Long): NativeAddress

    override fun close()
}

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public inline fun <R> memoryScope(block: (allocator: MemoryAllocator) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    val memoryAllocator = MemoryAllocator()
    try {
        return block(memoryAllocator)
    } finally {
        memoryAllocator.close()
    }
}