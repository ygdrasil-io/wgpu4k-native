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

    "unsafe access does not bounds-check (by design)" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            // Adresse au-delà du buffer : pas d'exception (UB assumé dans ce mode)
            JvmUnsafeAccess.putLong(buffer.handler.rawValue + 16L, 8L, 1L)
        }
    }
})
