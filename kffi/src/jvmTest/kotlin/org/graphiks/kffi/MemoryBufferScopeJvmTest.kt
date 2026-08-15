@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferScopeJvmTest : FreeSpec({

    "use after close raises IllegalStateException (I2-a)" {
        val allocator = MemoryAllocator()
        val buffer = allocator.allocateBuffer(16u)
        allocator.close()
        shouldThrow<IllegalStateException> {
            buffer.writeLong(1L, 0u)
        }
    }

    "global memory survives allocator close" {
        val buffer = globalMemory.allocateBuffer(16u)
        buffer.writeLong(42L, 0u)
        buffer.readLong(0u) shouldBe 42L
    }

    "unsafe buffer from raw address has no scope" {
        val allocator = MemoryAllocator()
        val raw = allocator.allocate(16)
        allocator.close()
        // Adresse brute sans scope : accès permis (UB documenté), pas d'exception.
        val buffer = MemoryBuffer(raw, 16u)
        buffer.writeLong(7L, 0u)
    }
})
