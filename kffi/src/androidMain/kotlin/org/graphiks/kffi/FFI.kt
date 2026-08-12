package org.graphiks.kffi

fun NativeAddress?.toAddress(): Long = this?.rawValue ?: 0L
