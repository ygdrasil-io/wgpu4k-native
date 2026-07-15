package io.ygdrasil.kffi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

internal fun validatedJvmCallbackPointerBits(addressByteSize: Long): Int {
    require(addressByteSize == Long.SIZE_BYTES.toLong()) {
        "KFFI callback tokens require an 8-byte JVM FFM address layout, " +
            "found $addressByteSize bytes"
    }
    return Long.SIZE_BITS
}

internal actual object PlatformTokenCodec : TokenCodec {
    actual override val pointerBits: Int =
        validatedJvmCallbackPointerBits(ValueLayout.ADDRESS.byteSize())
    actual override val maxToken: ULong = Long.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        return JvmNativeAddress(MemorySegment.ofAddress(token.toLong()))
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val token = address?.handler?.address()?.toULong() ?: return null
        requireValidCallbackToken(token)
        return token
    }
}
