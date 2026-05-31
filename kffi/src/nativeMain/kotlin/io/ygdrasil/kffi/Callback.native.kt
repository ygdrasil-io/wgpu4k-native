@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.kffi

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi

actual class CallbackHolder<T : Callback>(
    actual val handler: NativeAddress,
    val actualCallback: COpaquePointer? = null
)

@PublishedApi
internal val callbackMap = mutableMapOf<CPointer<*>, Callback>()

fun registerCallback(address: CPointer<*>, callback: Callback) {
    callbackMap.set(address, callback)
}

inline fun <reified R : Callback> findCallback(address: CPointer<*>): R? {
    return callbackMap.get(address)?.let { it as? R }
}

fun removeCallback(address: CPointer<*>) {
    callbackMap.remove(address)
}
