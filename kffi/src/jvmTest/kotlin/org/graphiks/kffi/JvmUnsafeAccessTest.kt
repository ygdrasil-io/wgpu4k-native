@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JvmUnsafeAccessTest : FreeSpec({

    "unsafe read/write round-trips through the raw address" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            JvmUnsafeAccess.putLong(buffer.handler.rawValue, 8L, 0xCAFE)
            JvmUnsafeAccess.getLong(buffer.handler.rawValue, 8L) shouldBe 0xCAFE
        }
    }

    "all scalar accessors round-trip through the raw address" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(64u)
            val base = buffer.handler.rawValue
            JvmUnsafeAccess.putByte(base, 0L, 0x7F.toByte())
            JvmUnsafeAccess.getByte(base, 0L) shouldBe 0x7F.toByte()
            JvmUnsafeAccess.putShort(base, 2L, 0x7FFF.toShort())
            JvmUnsafeAccess.getShort(base, 2L) shouldBe 0x7FFF.toShort()
            JvmUnsafeAccess.putInt(base, 4L, 0x7FFFFFFF)
            JvmUnsafeAccess.getInt(base, 4L) shouldBe 0x7FFFFFFF
            JvmUnsafeAccess.putLong(base, 8L, 0x7FFFFFFFFFFFFFFFL)
            JvmUnsafeAccess.getLong(base, 8L) shouldBe 0x7FFFFFFFFFFFFFFFL
            JvmUnsafeAccess.putFloat(base, 16L, 3.14f)
            JvmUnsafeAccess.getFloat(base, 16L) shouldBe 3.14f
            JvmUnsafeAccess.putDouble(base, 24L, 2.71828)
            JvmUnsafeAccess.getDouble(base, 24L) shouldBe 2.71828
        }
    }

    "unsafe access does not bounds-check (by design)" {
        memoryScope { scope ->
            // Buffer nominal de 64 octets sur une allocation réelle de 128 : l'écriture
            // [96,104) dépasse la taille nominale du buffer mais reste dans l'allocation
            // réelle — pas d'exception, pas de corruption de tas.
            val buffer = MemoryBuffer(scope.allocate(128L), 64u)
            JvmUnsafeAccess.putLong(buffer.handler.rawValue + 96L, 8L, 1L)
        }
    }
})
