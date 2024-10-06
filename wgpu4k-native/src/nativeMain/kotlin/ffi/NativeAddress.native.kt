@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCPointer

actual typealias NativeAddress = Long

inline fun <reified T : CPointed>NativeAddress.adapt(): CPointer<T>? {
    return toCPointer()
}
