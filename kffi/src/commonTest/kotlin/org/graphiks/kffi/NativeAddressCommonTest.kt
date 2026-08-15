package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class NativeAddressCommonTest : FreeSpec({

    "rawValue is preserved" {
        val address = NativeAddress(0x1234_5678L)
        address.rawValue shouldBe 0x1234_5678L
    }

    "null contract via rawValue 0" {
        val address = NativeAddress(0L)
        address.rawValue shouldBe 0L
    }
})
