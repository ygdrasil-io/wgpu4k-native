package io.ygdrasil.kffi

import java.lang.foreign.MemorySegment

internal actual object PlatformTokenCodec : TokenCodec {
    override val pointerBits: Int = 64
    override val maxToken: ULong = Long.MAX_VALUE.toULong()

    override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        return JvmNativeAddress(MemorySegment.ofAddress(token.toLong()))
    }

    override fun decode(address: NativeAddress?): ULong? {
        val token = address?.handler?.address()?.toULong() ?: return null
        requireValidCallbackToken(token)
        return token
    }
}
