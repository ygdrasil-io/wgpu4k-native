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
    "bulk ints round-trip through the array helpers" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(64uL)
            val source = IntArray(8) { it * 3 }
            buffer.writeInts(source, arrayIndex = 1u, bufferOffset = 8uL, size = 4u)
            val out = IntArray(8)
            buffer.readInts(out, arrayIndex = 2u, bufferOffset = 8uL, size = 4u)
            out[2] shouldBe 3
            out[3] shouldBe 6
            out[4] shouldBe 9
            out[5] shouldBe 12
        }
    }
    "huge offsets cannot bypass the bounds check" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(8uL)
            shouldThrow<IllegalArgumentException> { buffer.readLong(ULong.MAX_VALUE) }
            shouldThrow<IllegalArgumentException> { buffer.writeInt(1, ULong.MAX_VALUE) }
        }
    }
    "huge bufferOffset cannot bypass the array bounds check" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(16uL)
            shouldThrow<IllegalArgumentException> {
                buffer.writeInts(IntArray(4), bufferOffset = ULong.MAX_VALUE)
            }
            shouldThrow<IllegalArgumentException> {
                buffer.readInts(IntArray(4), arrayIndex = ULong.MAX_VALUE)
            }
        }
    }
})
