@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferAndroidTest : FreeSpec({
    "scalar read/write round-trips" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(32uL)
            buffer.writeByte(1, 0uL)
            buffer.readByte(0uL) shouldBe 1
            buffer.writeInt(0x01020304, 4uL)
            buffer.readInt(4uL) shouldBe 0x01020304
            buffer.writeLong(0x0102030405060708L, 8uL)
            buffer.readLong(8uL) shouldBe 0x0102030405060708L
            buffer.writeDouble(3.5, 16uL)
            buffer.readDouble(16uL) shouldBe 3.5
            buffer.writeFloat(1.5f, 24uL)
            buffer.readFloat(24uL) shouldBe 1.5f
        }
    }
    "scalar write beyond bounds throws" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(8uL)
            shouldThrow<IllegalArgumentException> { buffer.writeInt(0, 6uL) }
            shouldThrow<IllegalArgumentException> { buffer.readLong(4uL) }
        }
    }
    "bulk write/read with bad offsets throws IllegalArgumentException" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(16uL)
            shouldThrow<IllegalArgumentException> { buffer.writeInts(IntArray(8), bufferOffset = 8uL) }
            shouldThrow<IllegalArgumentException> { buffer.writeInts(IntArray(8), arrayIndex = 4u) }
        }
    }
})
