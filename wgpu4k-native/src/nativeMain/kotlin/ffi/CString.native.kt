@file:OptIn(ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKString

actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? = handler.toCPointer<ByteVarOf<Byte>>()?.toKString()
}

fun CPointer<ByteVarOf<Byte>>.toCString() = CString(rawValue.toLong())