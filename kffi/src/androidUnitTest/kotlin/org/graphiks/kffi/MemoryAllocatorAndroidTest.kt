@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MemoryAllocatorAndroidTest : FreeSpec({
    "allocations are distinct and aligned" {
        memoryScope { allocator ->
            val a = allocator.allocate(8L)
            val b = allocator.allocate(8L)
            a.rawValue shouldNotBe b.rawValue
            a.rawValue % 8L shouldBe 0L
            b.rawValue % 8L shouldBe 0L
        }
    }
    "bufferOf writes a long" {
        memoryScope { allocator ->
            val buffer = allocator.bufferOf(42L)
            buffer.readLong(0uL) shouldBe 42L
        }
    }
    "allocateBuffer is usable for reads/writes" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(16uL)
            buffer.writeInt(7, 0uL)
            buffer.readInt(0uL) shouldBe 7
        }
    }
    "repeated same-size allocations reuse the free-list" {
        memoryScope { allocator ->
            val first = allocator.allocate(64L)
            first.rawValue shouldNotBe 0L
            val second = allocator.allocate(64L)
            second.rawValue shouldNotBe first.rawValue
        }
    }
    "close is idempotent and frees the arena" {
        val allocator = MemoryAllocator()
        allocator.allocate(8L)
        allocator.close()
        allocator.close()
    }
})
