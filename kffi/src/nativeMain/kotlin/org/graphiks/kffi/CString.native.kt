@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kffi

import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.toKString

actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? = handler.reinterpret<ByteVarOf<Byte>>().toKString()

    actual fun toKString(size: ULong): String? {
        return handler.reinterpret<ByteVarOf<Byte>>()
                .readBytes(size.toInt())
                .toKString()
    }
}

fun CPointer<ByteVarOf<Byte>>.toCString() = CString(NativeAddress.fromPointer(this))
