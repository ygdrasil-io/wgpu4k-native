package org.graphiks.kffi

internal actual object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    actual override val pointerBits: Int = Long.SIZE_BITS
    actual override val maxToken: ULong = Long.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        return NativeAddress(token.toLong())
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val raw = address?.rawValue ?: return null
        if (raw == 0L) return null
        val token = raw.toULong()
        requireValidCallbackToken(token)
        return token
    }
}
