@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import java.nio.charset.StandardCharsets

internal class AndroidArena : AutoCloseable {
    private data class Block(val base: Long, val capacity: Long) {
        var offset: Long = 0
    }

    private val blocks = mutableListOf<Block>()
    private var current: Block? = null
    private val freeBySize = HashMap<Long, MutableList<Long>>()
    private val unsafe = AndroidUnsafe.get()
    private val defaultBlock = 1L shl 16

    fun allocate(size: Long): Long {
        val aligned = alignUp(size, 8L)
        val freed = freeBySize[aligned]?.takeIf { it.isNotEmpty() }?.removeLast()
        if (freed != null) return freed
        var block = current
        if (block == null || block.offset + aligned > block.capacity) {
            val capacity = maxOf(defaultBlock, alignUp(aligned, defaultBlock))
            val base = unsafe.allocateMemory(capacity)
            unsafe.setMemory(base, capacity, 0)
            block = Block(base, capacity)
            blocks.add(block)
            current = block
        }
        val addr = block.base + block.offset
        block.offset += aligned
        return addr
    }

    fun free(addr: Long, size: Long) {
        freeBySize.getOrPut(alignUp(size, 8L)) { mutableListOf() }.add(addr)
    }

    override fun close() {
        blocks.forEach { unsafe.freeMemory(it.base) }
        blocks.clear()
        current = null
        freeBySize.clear()
    }

    private fun alignUp(value: Long, alignment: Long): Long =
        (value + alignment - 1) and -alignment
}

actual class MemoryAllocator : AutoCloseable {
    private val arena = AndroidArena()

    actual fun allocate(sizeInByte: Long): NativeAddress = NativeAddress(arena.allocate(sizeInByte))

    actual override fun close() { arena.close() }

    actual fun bufferOf(value: Long): MemoryBuffer {
        val addr = arena.allocate(Long.SIZE_BYTES.toLong())
        AndroidUnsafe.get().putLong(addr, value)
        return MemoryBuffer(NativeAddress(addr), Long.SIZE_BYTES.toULong())
    }

    actual fun allocateFrom(value: String): CString {
        val bytes = value.toByteArray(StandardCharsets.UTF_8)
        val addr = arena.allocate(bytes.size + 1L)
        val unsafe = AndroidUnsafe.get()
        for (i in bytes.indices) unsafe.putByte(addr + i, bytes[i])
        unsafe.putByte(addr + bytes.size, 0)
        return CString(NativeAddress(addr))
    }

    actual fun bufferOfAddress(value: NativeAddress): MemoryBuffer = bufferOf(value.rawValue)

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        MemoryBuffer(NativeAddress(arena.allocate(size.toLong())), size)

    actual fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer {
        val buffer = allocateBuffer((value.size * 8).toULong())
        value.forEachIndexed { index, address ->
            buffer.writePointer(address, (index * 8).toULong())
        }
        return buffer
    }
}
