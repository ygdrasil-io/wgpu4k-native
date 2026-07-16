package io.ygdrasil.kffi

import com.sun.jna.Native
import com.sun.jna.Pointer

internal actual object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    init {
        require(Native.POINTER_SIZE == Long.SIZE_BYTES) {
            "KFFI callback tokens require a 64-bit Android/JNA pointer ABI, " +
                "found ${Native.POINTER_SIZE * Byte.SIZE_BITS} bits"
        }
    }

    actual override val pointerBits: Int = Native.POINTER_SIZE * Byte.SIZE_BITS
    actual override val maxToken: ULong = Long.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        return Pointer(token.toLong())
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        if (address == null) return null
        val token = Pointer.nativeValue(address).toULong()
        requireValidCallbackToken(token)
        return token
    }
}
