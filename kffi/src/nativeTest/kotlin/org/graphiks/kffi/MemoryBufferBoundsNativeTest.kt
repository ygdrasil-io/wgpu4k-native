package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class MemoryBufferBoundsNativeTest : FreeSpec({

    "unsafe flag is a compile-time constant on native (build-time, I3)" {
        // La distribution native est figée : le flag runtime est ignoré.
        // Ce test documente le comportement de la distribution par défaut (bornée).
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }
        }
    }

    "allocator unsafe flag is accepted for API compatibility (no runtime effect)" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        shouldThrow<IndexOutOfBoundsException> {
            buffer.readLong(8u)
        }
        allocator.close()
    }
})
