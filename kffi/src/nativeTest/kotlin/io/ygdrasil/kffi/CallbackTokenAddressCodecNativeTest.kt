@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.kffi

import callbackTokenCodec.kffi_callback_token_decode
import callbackTokenCodec.kffi_callback_token_encode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecNativeTest : FreeSpec({
    "valid callback tokens round-trip through Native pointers" {
        listOf(1uL, 2uL, Int.MAX_VALUE.toULong(), Long.MAX_VALUE.toULong()).forEach { token ->
            PlatformCallbackTokenAddressCodec.decode(PlatformCallbackTokenAddressCodec.encode(token)) shouldBe token
        }
    }

    "the generated C helpers convert a host pointer round-trip" {
        kffi_callback_token_decode(kffi_callback_token_encode(1uL)) shouldBe 1uL
    }

    "a null Native pointer decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }

    "a high-bit Native pointer is not a callback token" {
        val highBitPointer = requireNotNull(
            kffi_callback_token_encode(Long.MAX_VALUE.toULong() + 1uL)
        )

        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.decode(Pointer(highBitPointer))
        }
    }

    "zero is not a callback token" {
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.encode(0uL)
        }
    }

    "tokens above Long MAX_VALUE are rejected" {
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong() + 1uL)
        }
    }

    "the Native callback token ABI is a signed-positive 64-bit address range" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe 64
        PlatformCallbackTokenAddressCodec.maxToken shouldBe Long.MAX_VALUE.toULong()
    }
})
