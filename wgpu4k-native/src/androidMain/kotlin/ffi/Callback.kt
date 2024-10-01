package ffi

import webgpu.WGPURequestDeviceCallbackInfo

object Callback {

    init {
        System.loadLibrary("wgpu4k")
    }

    fun registerRequestDevice(callback: WGPURequestDeviceCallbackInfo) : NativeAddress {
        val methodName = callback::class.java.declaredMethods.first { it.name.contains("invoke") }.name
        return registerRequestDevice(callback, methodName)
    }

    private external fun registerRequestDevice(callback: WGPURequestDeviceCallbackInfo, methodName: String) : NativeAddress
    external fun free(callbackAddress: NativeAddress) : NativeAddress
}