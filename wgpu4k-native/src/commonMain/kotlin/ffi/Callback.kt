package ffi

private val callbackMap = mutableMapOf<NativeAddress, Callback>()

internal fun registerCallback(address: NativeAddress, callback: Callback) {
    callbackMap.set(address, callback)
}

internal inline fun <reified R : Callback> findCallback(address: NativeAddress): R? {
    return callbackMap.get(address)?.let { it as? R }
}

interface Callback

expect class CallbackHolder<T: Callback> {
    val handler: NativeAddress
}