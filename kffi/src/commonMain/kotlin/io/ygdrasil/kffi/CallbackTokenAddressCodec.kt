package io.ygdrasil.kffi

internal interface CallbackTokenAddressCodec {
    val pointerBits: Int
    val maxToken: ULong

    fun encode(token: ULong): NativeAddress
    fun decode(address: NativeAddress?): ULong?
}

internal expect object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    override val pointerBits: Int
    override val maxToken: ULong
    override fun encode(token: ULong): NativeAddress
    override fun decode(address: NativeAddress?): ULong?
}

internal fun requireValidCallbackToken(token: ULong) {
    require(token in 1uL..Long.MAX_VALUE.toULong()) {
        "Callback token must be between 1 and ${Long.MAX_VALUE}"
    }
}
