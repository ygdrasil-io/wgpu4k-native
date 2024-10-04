package ffi

import kotlin.jvm.JvmInline

interface Callback

@JvmInline
value  class CallbackHolder<T: Callback>(val handler: Long)