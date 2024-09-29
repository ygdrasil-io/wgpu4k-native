package ffi

import webgpu.WGPURequestDevice

object Callback {

    init {
        System.loadLibrary("wgpu4k")
    }

    fun registerRequestDevice(callback: WGPURequestDevice) : NativeAddress {
        val methodName = callback::class.java.declaredMethods.first { it.name.contains("invoke") }.name
        return registerRequestDevice(callback, methodName)
    }

    private external fun registerRequestDevice(callback: WGPURequestDevice, methodName: String) : NativeAddress
    external fun free(callbackAddress: NativeAddress) : NativeAddress
}