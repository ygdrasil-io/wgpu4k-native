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

    "read ShortArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = ShortArray(arraySize) { random.nextInt().toShort() }
            val buffer = scope.allocateBuffer((sourceArray.size * Short.SIZE_BYTES).toULong())
            val destinationArray = ShortArray(sourceArray.size)
            buffer.writeShorts(sourceArray)
            destinationArray shouldNotBe sourceArray

            //When
            buffer.readShorts(destinationArray)

            //Then
            destinationArray shouldBe sourceArray

        }
    }

    "try to write out of bounds ShortArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = ShortArray(arraySize) { random.nextInt().toShort() }
            val buffer = scope.allocateBuffer((sourceArray.size * Short.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeShorts(sourceArray, arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeShorts(sourceArray, bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeShorts(sourceArray, size = arraySize.toULong() + 1u)
            }
        }
    }

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

    "read IntArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = IntArray(arraySize) { random.nextInt() }
            val buffer = scope.allocateBuffer((sourceArray.size * Int.SIZE_BYTES).toULong())
            val destinationArray = IntArray(sourceArray.size)
            buffer.writeInts(sourceArray)
            destinationArray shouldNotBe sourceArray

            //When
            buffer.readInts(destinationArray)

            //Then
            destinationArray shouldBe sourceArray

        }
    }

    "try to write out of bounds IntArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = IntArray(arraySize) { random.nextInt() }
            val buffer = scope.allocateBuffer((sourceArray.size * Int.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray, arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray, bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray, size = arraySize.toULong() + 1u)
            }
        }
    }

    "write LongArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = LongArray(arraySize) { random.nextLong() }
            val buffer = scope.allocateBuffer((sourceArray.size * Long.SIZE_BYTES).toULong())

            // when
            buffer.writeInts(sourceArray.map { it.toInt() }.toIntArray())

            // Then
            sourceArray.forEachIndexed { index, long ->
                buffer.readInt((index * Long.SIZE_BYTES).toULong()).toLong() shouldBe long
            }

        }
    }

    "read LongArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = LongArray(arraySize) { random.nextLong() }
            val buffer = scope.allocateBuffer((sourceArray.size * Long.SIZE_BYTES).toULong())
            val destinationArray = LongArray(sourceArray.size)
            buffer.writeInts(sourceArray.map { it.toInt() }.toIntArray())
            destinationArray shouldNotBe sourceArray

            //When
            buffer.readLongs(destinationArray.copyOf())

            //Then
            destinationArray shouldBe sourceArray

        }
    }

    "try to write out of bounds LongArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = LongArray(arraySize) { random.nextLong() }
            val buffer = scope.allocateBuffer((sourceArray.size * Long.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toInt() }.toIntArray(), arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toInt() }.toIntArray(), bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toInt() }.toIntArray(), size = arraySize.toULong() + 1u)
            }
        }
    }

    "write FloatArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = FloatArray(arraySize) { random.nextFloat() }
            val buffer = scope.allocateBuffer((sourceArray.size * Float.SIZE_BYTES).toULong())

            // when
            buffer.writeInts(sourceArray.map { it.toBits() }.toIntArray())

            // Then
            sourceArray.forEachIndexed { index, float ->
                val readValue = Float.fromBits(buffer.readInt((index * Float.SIZE_BYTES).toULong()))
                readValue shouldBe float
            }

        }
    }

    "read FloatArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = FloatArray(arraySize) { random.nextFloat() }
            val buffer = scope.allocateBuffer((sourceArray.size * Float.SIZE_BYTES).toULong())
            val destinationArray = FloatArray(sourceArray.size)
            buffer.writeInts(sourceArray.map { it.toBits() }.toIntArray())
            destinationArray shouldNotBe sourceArray

            //When
            buffer.readBytes(destinationArray.map { it.toBits().toByte() }.toByteArray())

            //Then
            destinationArray shouldBe sourceArray

        }
    }

    "try to write out of bounds FloatArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = FloatArray(arraySize) { random.nextFloat() }
            val buffer = scope.allocateBuffer((sourceArray.size * Float.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits() }.toIntArray(), arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits() }.toIntArray(), bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits() }.toIntArray(), size = arraySize.toULong() + 1u)
            }
        }
    }

    "write DoubleArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = DoubleArray(arraySize) { random.nextDouble() }
            val buffer = scope.allocateBuffer((sourceArray.size * Double.SIZE_BYTES).toULong())

            // when
            buffer.writeInts(sourceArray.map { it.toBits().toInt() }.toIntArray())

            // Then
            sourceArray.forEachIndexed { index, double ->
                val readValue = Double.fromBits(buffer.readInt((index * Double.SIZE_BYTES).toULong()).toLong())
                readValue shouldBe double
            }

        }
    }

    "read DoubleArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = DoubleArray(arraySize) { random.nextDouble() }
            val buffer = scope.allocateBuffer((sourceArray.size * Double.SIZE_BYTES).toULong())
            val destinationArray = DoubleArray(sourceArray.size)
            buffer.writeInts(sourceArray.map { it.toBits().toInt() }.toIntArray())
            destinationArray shouldNotBe sourceArray

            //When
            buffer.readBytes(destinationArray.map { it.toBits().toByte() }.toByteArray())

            //Then
            destinationArray shouldBe sourceArray

        }
    }

    "try to write out of bounds DoubleArray to MemoryBuffer" {
        memoryScope { scope ->

            // Given
            val sourceArray = DoubleArray(arraySize) { random.nextDouble() }
            val buffer = scope.allocateBuffer((sourceArray.size * Double.SIZE_BYTES).toULong())

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits().toInt() }.toIntArray(), arrayIndex = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits().toInt() }.toIntArray(), bufferOffset = 1u)
            }

            // Should
            shouldThrow<IllegalStateException> {
                // When
                buffer.writeInts(sourceArray.map { it.toBits().toInt() }.toIntArray(), size = arraySize.toULong() + 1u)
            }
        }
    }

})