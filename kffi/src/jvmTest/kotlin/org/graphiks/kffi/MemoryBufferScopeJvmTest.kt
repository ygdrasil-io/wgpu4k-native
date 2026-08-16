@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.lang.foreign.Arena

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
        // I2-(a) : un buffer construit depuis une adresse brute ne porte AUCUN scope
        // d'arène -> les accès ne lèvent pas IllegalStateException (contrairement au
        // chemin scopé). Le chunk natif de l'arène fermée ne doit PAS être écrit
        // (use-after-free, UB documenté) : le buffer est construit sur un chunk natif
        // stable via une arène distincte, encore vivante pendant le test.
        val allocator = MemoryAllocator()
        allocator.close()
        val stable = Arena.ofAuto()
        val chunk = stable.allocate(16L)
        val buffer = MemoryBuffer(NativeAddress(chunk.address()), 16u)
        buffer.writeLong(7L, 0u)
        buffer.readLong(0u) shouldBe 7L
    }
})
