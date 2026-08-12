package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CStringAndroidTest : FreeSpec({
    "allocateFrom produces a NUL-terminated UTF-8 string" {
        memoryScope { allocator ->
            val cstr = allocator.allocateFrom("héllo")
            cstr.toKString() shouldBe "héllo"
        }
    }
    "toKString(size) reads a fixed number of bytes" {
        memoryScope { allocator ->
            val cstr = allocator.allocateFrom("abcd")
            cstr.toKString(4uL) shouldBe "abcd"
        }
    }
})
