package ffi

interface Callback

data class CallbackHolder<T: Callback>(val handler: Long)