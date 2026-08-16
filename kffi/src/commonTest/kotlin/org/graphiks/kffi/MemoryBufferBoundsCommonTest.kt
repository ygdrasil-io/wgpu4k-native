package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
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

    "scalar write crossing the end throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.writeLong(1L, 6u) // 6 + 8 > 8
            }
        }
    }

    "array write crossing the end throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.writeInts(IntArray(4), bufferOffset = 12u) // 12 + 16 > 16
            }
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
})
