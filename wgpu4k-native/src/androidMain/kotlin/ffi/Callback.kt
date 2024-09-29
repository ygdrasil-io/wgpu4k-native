package ffi

interface WGPURequestDevice {
    fun invoke(status: UInt, device: Long, message: Long, userData1: Long/*, userData2: Long*/)
}

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