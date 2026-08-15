@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kffi

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong

actual value class NativeAddress actual constructor(actual val rawValue: Long) {

    val pointer: CPointer<*>?
        get() = rawValue.toCPointer<kotlinx.cinterop.COpaque>()

    fun <T : kotlinx.cinterop.CPointed> reinterpret(): CPointer<T> {
        return requireNotNull(pointer).reinterpret()
    }
}
