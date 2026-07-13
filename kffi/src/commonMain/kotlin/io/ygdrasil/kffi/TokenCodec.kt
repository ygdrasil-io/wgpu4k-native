package io.ygdrasil.kffi

internal interface TokenCodec {
    val pointerBits: Int
    val maxToken: ULong

    fun encode(token: ULong): NativeAddress
    fun decode(address: NativeAddress?): ULong?
}

internal expect object PlatformTokenCodec : TokenCodec

internal fun requireValidCallbackToken(token: ULong) {
    require(token in 1uL..Long.MAX_VALUE.toULong()) {
        "Callback token must be between 1 and ${Long.MAX_VALUE}"
    }
}
