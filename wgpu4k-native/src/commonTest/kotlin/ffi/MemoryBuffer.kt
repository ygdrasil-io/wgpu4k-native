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
        memoryScope {scope ->

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
        memoryScope {scope ->

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
        memoryScope {scope ->

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

})