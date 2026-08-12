package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecAndroidTest : FreeSpec({
    "round-trips valid tokens" {
        for (token in listOf(1uL, 42uL, Long.MAX_VALUE.toULong())) {
            PlatformCallbackTokenAddressCodec.decode(
                PlatformCallbackTokenAddressCodec.encode(token),
            ) shouldBe token
        }
    }
    "null decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }
    "rejects out-of-range tokens" {
        shouldThrow<IllegalArgumentException> { PlatformCallbackTokenAddressCodec.encode(0uL) }
        shouldThrow<IllegalArgumentException> { PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong() + 1uL) }
    }
    "pointerBits and maxToken match the ABI" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe AndroidUnsafe.get().addressSize() * 8
        PlatformCallbackTokenAddressCodec.maxToken shouldBe
            (if (AndroidUnsafe.get().addressSize() == 8) Long.MAX_VALUE.toULong() else UInt.MAX_VALUE.toULong())
    }
})
