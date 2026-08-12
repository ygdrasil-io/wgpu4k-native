package org.graphiks.kffi

internal actual object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    private val pointerBytes: Int = AndroidUnsafe.get().addressSize()

    actual override val pointerBits: Int = pointerBytes * 8
    actual override val maxToken: ULong =
        if (pointerBytes == 8) Long.MAX_VALUE.toULong() else UInt.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        require(token <= maxToken) {
            "Callback token $token exceeds the ${pointerBits}-bit pointer ABI"
        }
        return NativeAddress(token.toLong())
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val token = address?.rawValue?.toULong() ?: return null
        requireValidCallbackToken(token)
        return token
    }
}
