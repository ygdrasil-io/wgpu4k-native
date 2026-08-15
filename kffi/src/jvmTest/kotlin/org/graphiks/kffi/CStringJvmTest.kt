package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CStringJvmTest : FreeSpec({
    "toKString reads from raw address" {
        memoryScope { scope ->
            val string = scope.allocateFrom("kffi-p2")
            string.toKString() shouldBe "kffi-p2"
        }
    }

    "null address yields null string" {
        val string = CString(NativeAddress(0L))
        string.toKString() shouldBe null
    }
})
