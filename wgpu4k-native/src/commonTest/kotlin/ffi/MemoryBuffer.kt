package ffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.random.Random

class MemoryBufferArrayTest : FreeSpec({

    val random = Random.Default
    // Array will be on range [100, 300]
    val arraySize = random.nextInt(200) + 100

    "write ByteArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceBytes = random.nextBytes(arraySize)
            val buffer = scope.allocateBuffer((sourceBytes.size * Byte.SIZE_BYTES).toULong())

            // when
            buffer.writeBytes(sourceBytes)

            // Then
            sourceBytes.forEachIndexed { index, byte ->
                buffer.readByte((index * Byte.SIZE_BYTES).toULong()) shouldBe byte
            }

        }
    }

    "read ByteArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceBytes = random.nextBytes(arraySize)
            val buffer = scope.allocateBuffer((sourceBytes.size * Byte.SIZE_BYTES).toULong())
            val destinationBytes = ByteArray(sourceBytes.size)
            buffer.writeBytes(sourceBytes)
            destinationBytes shouldNotBe sourceBytes

            //When
            buffer.readBytes(destinationBytes)

            //Then
            destinationBytes shouldBe sourceBytes

        }
    }

    "try to write out of bounds ByteArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceBytes = random.nextBytes(arraySize)
            val buffer = scope.allocateBuffer((sourceBytes.size * Byte.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeBytes(sourceBytes, arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeBytes(sourceBytes, bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeBytes(sourceBytes, size = arraySize.toULong() + 1u)
            }
        }
    }

    "write ShortArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = ShortArray(arraySize) { random.nextInt().toShort() }
            val buffer = scope.allocateBuffer((sourceArray.size * Short.SIZE_BYTES).toULong())

            // when
            buffer.writeShorts(sourceArray)

            // Then
            sourceArray.forEachIndexed { index, short ->
                buffer.readShort((index * Short.SIZE_BYTES).toULong()) shouldBe short
            }

        }
    }

    "read ShortArray to MemoryBuffer" { TODO() }

    "try to write out of bounds ShortArray to MemoryBuffer" { TODO() }

    "write IntArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = IntArray(arraySize) { random.nextInt() }
            val buffer = scope.allocateBuffer((sourceArray.size * Int.SIZE_BYTES).toULong())

            // when
            buffer.writeInts(sourceArray)

            // Then
            sourceArray.forEachIndexed { index, int ->
               buffer.readInt((index * Int.SIZE_BYTES).toULong()) shouldBe int
            }

        }
    }

    "read IntArray to MemoryBuffer" { TODO() }

    "try to write out of bounds IntArray to MemoryBuffer" { TODO() }

    "write LongArray to MemoryBuffer" { TODO() }
    
    "read LongArray to MemoryBuffer" { TODO() }

    "try to write out of bounds LongArray to MemoryBuffer" { TODO() }

    "write FloatArray to MemoryBuffer" { TODO() }

    "read FloatArray to MemoryBuffer" { TODO() }

    "try to write out of bounds FloatArray to MemoryBuffer" { TODO() }

    "write DoubleArray to MemoryBuffer" { TODO() }

    "read DoubleArray to MemoryBuffer" { TODO() }

    "try to write out of bounds DoubleArray to MemoryBuffer" { TODO() }
    
    
})