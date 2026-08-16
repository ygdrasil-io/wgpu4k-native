package org.graphiks.kffi

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

val globalMemory = MemoryAllocator()

/**
 * Allocateur d'arène confinée.
 * [unsafe] = true propage l'option unsafe à tous les buffers créés par cet
 * allocateur (bornes-check éliminés, I3). Défaut : false.
 * NOTE native : la valeur unsafe est figée à la compilation (constante build-time,
 * voir MemoryBuffer.native.kt) — les distributions native ne peuvent pas basculer
 * au runtime ; le flag est accepté pour la compatibilité d'API et sans effet.
 */
expect class MemoryAllocator(unsafe: Boolean = false) : AutoCloseable {

    fun allocate(sizeInByte: Long): NativeAddress

    override fun close()

    fun bufferOf(value: Long): MemoryBuffer

    fun allocateFrom(value: String): CString
    fun bufferOfAddress(value: NativeAddress): MemoryBuffer
    fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer
    fun allocateBuffer(size: ULong): MemoryBuffer
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
