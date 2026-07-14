package io.ygdrasil.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class TokenCodecJvmTest : FreeSpec({
    "valid callback tokens round-trip through JVM FFM addresses" {
        listOf(1uL, 2uL, Int.MAX_VALUE.toULong(), Long.MAX_VALUE.toULong()).forEach { token ->
            PlatformTokenCodec.decode(PlatformTokenCodec.encode(token)) shouldBe token
        }
    }

    "a null JVM FFM address decodes to null" {
        PlatformTokenCodec.decode(null) shouldBe null
    }

    "a non-null zero JVM FFM address is not a callback token" {
        shouldThrow<IllegalArgumentException> {
            PlatformTokenCodec.decode(JvmNativeAddress(MemorySegment.ofAddress(0)))
        }
    }

    "a high-bit JVM FFM address is not a callback token" {
        shouldThrow<IllegalArgumentException> {
            PlatformTokenCodec.decode(JvmNativeAddress(MemorySegment.ofAddress(Long.MIN_VALUE)))
        }
    }

    "zero is not a callback token" {
        shouldThrow<IllegalArgumentException> {
            PlatformTokenCodec.encode(0uL)
        }
    }

    "tokens above Long MAX_VALUE are rejected" {
        shouldThrow<IllegalArgumentException> {
            PlatformTokenCodec.encode(Long.MAX_VALUE.toULong() + 1uL)
        }
    }

    "the JVM token codec derives pointer width from the FFM address layout" {
        validatedJvmCallbackPointerBits(ValueLayout.ADDRESS.byteSize()) shouldBe 64
        PlatformTokenCodec.pointerBits shouldBe 64
    }

    "non-64-bit JVM FFM address layouts are rejected" {
        val failure = shouldThrow<IllegalArgumentException> {
            validatedJvmCallbackPointerBits(4L)
        }
        failure.message shouldBe
            "KFFI callback tokens require an 8-byte JVM FFM address layout, found 4 bytes"
    }

    "the JVM callback token ABI is a signed-positive 64-bit address range" {
        PlatformTokenCodec.pointerBits shouldBe 64
        PlatformTokenCodec.maxToken shouldBe Long.MAX_VALUE.toULong()
    }
})
