package io.ygdrasil.kffi

interface Callback

expect class CallbackHolder<T: Callback> {
    val handler: NativeAddress
}
