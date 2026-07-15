package io.ygdrasil.kffi

import com.sun.jna.Native

private const val UNSUPPORTED_CALLBACK_REGISTRATION =
    "Android/JNA callback registration is not supported"

internal actual object PlatformTokenCodec : TokenCodec {
    actual override val pointerBits: Int = Native.POINTER_SIZE * Byte.SIZE_BITS
    actual override val maxToken: ULong = 0uL

    actual override fun encode(token: ULong): NativeAddress {
        throw UnsupportedOperationException(UNSUPPORTED_CALLBACK_REGISTRATION)
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        if (address == null) return null
        throw UnsupportedOperationException(UNSUPPORTED_CALLBACK_REGISTRATION)
    }
}
