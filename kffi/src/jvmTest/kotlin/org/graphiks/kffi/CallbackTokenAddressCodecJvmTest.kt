package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecJvmTest : FreeSpec({
    "valid callback tokens round-trip through raw addresses" {
        listOf(1uL, 2uL, Int.MAX_VALUE.toULong(), Long.MAX_VALUE.toULong()).forEach { token ->
            PlatformCallbackTokenAddressCodec.decode(PlatformCallbackTokenAddressCodec.encode(token)) shouldBe token
        }
    }

    "a null raw address decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }

    "a zero raw address decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(NativeAddress(0L)) shouldBe null
    }

    "a high-bit raw address is not a callback token" {
        shouldThrow<IllegalArgumentException> {
            PlatformCallbackTokenAddressCodec.decode(NativeAddress(Long.MIN_VALUE))
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

    "the JVM token codec pointer width is the raw-address width" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe Long.SIZE_BITS
    }

    "the JVM callback token ABI is a signed-positive 64-bit address range" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe 64
        PlatformCallbackTokenAddressCodec.maxToken shouldBe Long.MAX_VALUE.toULong()
    }
})
