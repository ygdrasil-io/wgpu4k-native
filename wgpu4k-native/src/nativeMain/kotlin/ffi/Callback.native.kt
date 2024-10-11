@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.CFunction
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi

actual class CallbackHolder<T : Callback>(
    actual val handler: NativeAddress,
    val actualCallback: COpaquePointer? = null
)