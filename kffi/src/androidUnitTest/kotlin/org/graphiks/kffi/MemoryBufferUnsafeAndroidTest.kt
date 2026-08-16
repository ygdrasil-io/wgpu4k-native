@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferUnsafeAndroidTest : FreeSpec({
    "unsafe allocator: out-of-bounds scalar write does not throw" {
        val allocator = MemoryAllocator(unsafe = true)
        try {
            val buffer = allocator.allocateBuffer(8uL)
            buffer.writeLong(42L, 8uL)
            buffer.readLong(8uL) shouldBe 42L
        } finally {
            allocator.close()
        }
    }

    "unsafe direct constructor: out-of-bounds access does not throw" {
        val allocator = MemoryAllocator()
        try {
            val address = allocator.allocate(8)
            val buffer = MemoryBuffer(address, 8uL, unsafe = true)
            buffer.writeInt(7, 6uL)
            buffer.readInt(6uL) shouldBe 7
        } finally {
            allocator.close()
        }
    }

    "safe buffer still throws IndexOutOfBoundsException (contrast)" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8uL)
            shouldThrow<IndexOutOfBoundsException> { buffer.writeLong(42L, 8uL) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeInt(7, 6uL) }
        }
    }
})
