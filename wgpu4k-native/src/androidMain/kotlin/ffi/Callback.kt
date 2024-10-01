package ffi

import webgpu.WGPURequestDeviceCallback

object Callback {

    init {
        System.loadLibrary("wgpu4k")
    }

    fun registerRequestDevice(callback: WGPURequestDeviceCallback) : NativeAddress {
        val methodName = callback::class.java.declaredMethods.first { it.name.contains("invoke") }.name
        return registerRequestDevice(callback, methodName)
    }

    private external fun registerRequestDevice(callback: WGPURequestDeviceCallback, methodName: String) : NativeAddress
    external fun free(callbackAddress: NativeAddress) : NativeAddress
}