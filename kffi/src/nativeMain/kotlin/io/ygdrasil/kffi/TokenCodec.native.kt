@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.kffi

import callbackTokenCodec.kffi_callback_token_decode
import callbackTokenCodec.kffi_callback_token_encode
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.sizeOf

internal actual object PlatformTokenCodec : TokenCodec {
    init {
        require(sizeOf<COpaquePointerVar>() * Byte.SIZE_BITS == 64L) {
            "Callback tokens require a 64-bit pointer ABI"
        }
    }

    actual override val pointerBits: Int = 64
    actual override val maxToken: ULong = Long.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        val pointer = requireNotNull(kffi_callback_token_encode(token)) {
            "A valid callback token must not encode to null"
        }
        return Pointer(pointer)
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val token = address?.let { kffi_callback_token_decode(it.pointer) } ?: return null
        requireValidCallbackToken(token)
        return token
    }
}
