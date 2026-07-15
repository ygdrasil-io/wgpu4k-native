package io.ygdrasil.kffi

import com.sun.jna.Pointer

actual typealias NativeAddress = Pointer

fun NativeAddress?.adapt(): Long {
    return if (this == null) 0 else this.toAddress()
}
