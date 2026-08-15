@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kffi

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong

actual value class NativeAddress actual constructor(actual val rawValue: Long) {

    companion object {
        fun fromPointer(pointer: CPointer<*>): NativeAddress = NativeAddress(pointer.toLong())

        fun fromPointer(pointer: CPointer<*>?): NativeAddress =
            NativeAddress(requireNotNull(pointer) { "Invalid pointer" }.toLong())
    }

    val pointer: CPointer<*>?
        get() = rawValue.toCPointer<kotlinx.cinterop.COpaque>()

    fun <T : kotlinx.cinterop.CPointed> reinterpret(): CPointer<T> {
        return requireNotNull(pointer).reinterpret()
    }
}
