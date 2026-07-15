package io.ygdrasil.kffi

import com.sun.jna.Pointer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecAndroidTest : FreeSpec({
    "Android JNA callback token registration is explicitly unsupported" {
        val failure = shouldThrow<UnsupportedOperationException> {
            PlatformCallbackTokenAddressCodec.encode(1uL)
        }

        failure.message shouldBe "Android/JNA callback registration is not supported"
    }

    "a null Android JNA pointer decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }

    "a non-null Android JNA pointer cannot be decoded as a callback token" {
        val failure = shouldThrow<UnsupportedOperationException> {
            PlatformCallbackTokenAddressCodec.decode(Pointer(1L))
        }

        failure.message shouldBe "Android/JNA callback registration is not supported"
    }
})
