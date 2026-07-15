package io.ygdrasil.kffi

import com.sun.jna.Pointer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecAndroidTest : FreeSpec({
    "Android JNA callback tokens round-trip through pointers" {
        listOf(1uL, Long.MAX_VALUE.toULong()).forEach { token ->
            PlatformCallbackTokenAddressCodec.decode(
                PlatformCallbackTokenAddressCodec.encode(token),
            ) shouldBe token
        }
    }

    "a null Android JNA pointer decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }

    "Android JNA callback tokens reject values outside the supported range" {
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.encode(0uL)
        }
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong() + 1uL)
        }
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.decode(Pointer(Long.MIN_VALUE))
        }
    }

    "Android JNA callback tokens use a 64-bit positive pointer range" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe Long.SIZE_BITS
        PlatformCallbackTokenAddressCodec.maxToken shouldBe Long.MAX_VALUE.toULong()
    }
})
