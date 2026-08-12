package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AndroidUnsafeTest : FreeSpec({
    "raw memory round-trips through Unsafe" {
        val addr = AndroidUnsafe.get().allocateMemory(32)
        try {
            AndroidUnsafe.get().putInt(addr, 0x11223344)
            AndroidUnsafe.get().getInt(addr) shouldBe 0x11223344
            AndroidUnsafe.get().putLong(addr, 0x0102030405060708L)
            AndroidUnsafe.get().getLong(addr) shouldBe 0x0102030405060708L
        } finally {
            AndroidUnsafe.get().freeMemory(addr)
        }
    }
    "pointer size reflects the running ABI" {
        AndroidUnsafe.get().addressSize() shouldBe 8
    }
})
