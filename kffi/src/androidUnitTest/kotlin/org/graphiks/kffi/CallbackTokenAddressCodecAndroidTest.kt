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
    "32-bit pointer ABI caps tokens at UInt.MAX and rejects above" {
        // The 32-bit (armeabi-v7a) branch of the codec cannot execute on a
        // 64-bit test host; it is verified by code review of the
        // (pointerBytes == 4) path plus CI on a 32-bit device. This test pins
        // the cap FORMULA (M6.2) so the host test would fail if the formula
        // ever drifted from the ABI width.
        val pointerBytes = AndroidUnsafe.get().addressSize()
        val expected = if (pointerBytes == 8) Long.MAX_VALUE.toULong() else UInt.MAX_VALUE.toULong()
        PlatformCallbackTokenAddressCodec.maxToken shouldBe expected
        if (pointerBytes == 4) {
            shouldThrow<IllegalArgumentException> {
                PlatformCallbackTokenAddressCodec.encode(UInt.MAX_VALUE.toULong() + 1uL)
            }
        }
    }
})
