@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferUnsafeJvmTest : FreeSpec({

    "unsafe allocator removes bounds checks" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        // Hors bornes : pas d'exception en mode unsafe (UB assumé)
        buffer.writeLong(1L, 64u)
        allocator.close()
    }

    "unsafe buffer from raw address has no bounds checks" {
        memoryScope { scope ->
            val backing = scope.allocateBuffer(64u)
            val unsafeBuffer = MemoryBuffer(backing.handler, 8u, unsafe = true)
            // L'accès déborde du buffer unsafe mais reste dans le backing — pas d'exception
            unsafeBuffer.writeLong(7L, 8u)
        }
    }

    "default (safe) buffer still bounds-checks" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }
        }
    }

    "scoped buffer retains use-after-close detection even in unsafe mode" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        allocator.close()
        shouldThrow<IllegalStateException> {
            buffer.writeLong(1L, 0u)
        }
    }

    "unsafe array path round-trips and skips array bounds checks" {
        val allocator = MemoryAllocator(unsafe = true)
        try {
            val buffer = allocator.allocateBuffer(16u)
            // 3 longs = 24 octets > 16 : pas de borne-check en mode unsafe (UB assumé)
            buffer.writeLongs(longArrayOf(1L, 2L, 3L))
            buffer.readLong(0u) shouldBe 1L
            buffer.readLong(8u) shouldBe 2L
            buffer.readLong(16u) shouldBe 3L
        } finally {
            allocator.close()
        }
    }
})
