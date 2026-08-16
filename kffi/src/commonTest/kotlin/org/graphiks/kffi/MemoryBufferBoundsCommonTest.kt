package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class MemoryBufferBoundsCommonTest : FreeSpec({

    "scalar read beyond size throws IndexOutOfBoundsException with offset" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }.message shouldContain "8"
        }
    }

    "every scalar family read throws with offset AND size in message" {
        memoryScope { scope ->
            // Valeurs distinctes par largeur : offset < size, les deux doivent apparaître
            val buffer = scope.allocateBuffer(16u)
            // 1-octet : offset == size == 16 (dans le message : offset=16 et size=16)
            shouldThrow<IndexOutOfBoundsException> { buffer.readByte(16u) }.message shouldContain "16"
            shouldThrow<IndexOutOfBoundsException> { buffer.readUByte(16u) }.message shouldContain "16"
            // 2-octets : offset 15 + 2 > 16
            shouldThrow<IndexOutOfBoundsException> { buffer.readShort(15u) }
                .message.shouldContain("15").shouldContain("16")
            shouldThrow<IndexOutOfBoundsException> { buffer.readUShort(15u) }
                .message.shouldContain("15").shouldContain("16")
            // 4-octets : offset 13 + 4 > 16
            shouldThrow<IndexOutOfBoundsException> { buffer.readInt(13u) }
                .message.shouldContain("13").shouldContain("16")
            shouldThrow<IndexOutOfBoundsException> { buffer.readUInt(13u) }
                .message.shouldContain("13").shouldContain("16")
            shouldThrow<IndexOutOfBoundsException> { buffer.readFloat(13u) }
                .message.shouldContain("13").shouldContain("16")
            // 8-octets : offset 10 + 8 > 16
            shouldThrow<IndexOutOfBoundsException> { buffer.readLong(10u) }
                .message.shouldContain("10").shouldContain("16")
            shouldThrow<IndexOutOfBoundsException> { buffer.readULong(10u) }
                .message.shouldContain("10").shouldContain("16")
            shouldThrow<IndexOutOfBoundsException> { buffer.readDouble(10u) }
                .message.shouldContain("10").shouldContain("16")
            // Pointeur : offset == size == 16
            shouldThrow<IndexOutOfBoundsException> { buffer.readPointer(16u) }.message shouldContain "16"
        }
    }

    "write crossing end throws for each family" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> { buffer.writeByte(1, 8u) } // 8 + 1 > 8
            shouldThrow<IndexOutOfBoundsException> { buffer.writeUByte(1u, 8u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeShort(1, 7u) } // 7 + 2 > 8
            shouldThrow<IndexOutOfBoundsException> { buffer.writeUShort(1u, 7u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeInt(1, 6u) } // 6 + 4 > 8
            shouldThrow<IndexOutOfBoundsException> { buffer.writeUInt(1u, 6u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeFloat(1.0f, 6u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeLong(1L, 1u) } // 1 + 8 > 8
            shouldThrow<IndexOutOfBoundsException> { buffer.writeULong(1uL, 1u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writeDouble(1.0, 1u) }
            shouldThrow<IndexOutOfBoundsException> { buffer.writePointer(NativeAddress(0xCAFEL), 1u) }
        }
    }

    "array write crossing the end throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.writeInts(IntArray(4), bufferOffset = 12u) // 12 + 16 > 16
            }.message.shouldContain("12").shouldContain("16")
        }
    }

    "array read crossing the end throws (read path, not just write)" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readInts(IntArray(4), bufferOffset = 12u) // 12 + 16 > 16
            }.message.shouldContain("12").shouldContain("16")
            // bytes (8) et size (16) distincts : les trois valeurs sont forcées
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readBytes(ByteArray(8), bufferOffset = 12u) // 12 + 8 > 16
            }.message.shouldContain("12").shouldContain("8").shouldContain("16")
        }
    }

    "pointer read beyond size throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readPointer(8u)
            }
        }
    }

    "boundary access at exactly size is allowed (inclusive upper bound)" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            buffer.writeLong(0xCAFE, 0u) // offset 0 + 8 = 8 ≤ 8 : autorisé
            buffer.readLong(0u) shouldBe 0xCAFE
            buffer.writeInt(0xBEEF, 4u) // offset 4 + 4 = 8 ≤ 8 : autorisé (borne non nulle)
            buffer.readInt(4u) shouldBe 0xBEEF
        }
    }
})
