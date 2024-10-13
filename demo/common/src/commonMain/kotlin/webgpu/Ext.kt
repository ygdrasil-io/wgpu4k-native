package webgpu

import ffi.Buffer
import ffi.NativeAddress
import ffi.memoryScope

fun compatibleFormat(surface: WGPUSurface, adapter: WGPUAdapter): UInt = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)
    return surfaceCapabilities.formats?.handler?.let { Buffer(it, 0u) }?.readInt()?.toUInt()
        ?: error("no compatible format")
}

fun configureSurface(device: WGPUDevice, width: Int, height: Int, surface: WGPUSurface, renderingContextFormat: UInt) {
    memoryScope { scope ->
        val configuration = WGPUSurfaceConfiguration.allocate(scope).apply {
            this.device = device
            format = renderingContextFormat
            usage = 16u
            this.width = width.toUInt()
            this.height = height.toUInt()
        }
        wgpuSurfaceConfigure(surface, configuration)
    }
}

fun getDevice(adapter: WGPUAdapter): WGPUDevice = memoryScope { scope ->
    var fetchedDevice: WGPUDevice? = null

    val callback = WGPURequestDeviceCallback.allocate(scope, object : WGPURequestDeviceCallback {
        override fun invoke(
            status: WGPURequestDeviceStatus,
            device: WGPUDevice?,
            message: WGPUStringView?,
            userdata1: NativeAddress,
            userdata2: NativeAddress
        ) {
            if (status != 1u && device == null) error("fail to get device")
            fetchedDevice = device
        }

    })

    val callbackInfo = WGPURequestDeviceCallbackInfo.allocate(scope).apply {
        this.callback = callback
        this.userdata2 = scope.bufferOfAddress(callback.handler).handler
    }

    wgpuAdapterRequestDevice(adapter, null, callbackInfo)

    fetchedDevice ?: error("fail to get device")
}

fun getAdapter(surface: WGPUSurface, instance: WGPUInstance) = memoryScope { scope ->
    val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(scope)
    val options = WGPURequestAdapterOptions.allocate(scope).apply {
        compatibleSurface = surface
    }

    var fetchedAdapter: WGPUAdapter? = null

    val callback = WGPURequestAdapterCallback.allocate(scope, object : WGPURequestAdapterCallback {
        override fun invoke(
            status: WGPURequestAdapterStatus,
            adapter: WGPUAdapter?,
            message: WGPUStringView?,
            userdata1: NativeAddress,
            userdata2: NativeAddress
        ) {
            if (status != 1u && adapter == null) error("fail to get adapter")
            fetchedAdapter = adapter
        }

    })

    callbackInfo.callback = callback
    callbackInfo.userdata2 = scope.bufferOfAddress(callback.handler).handler

    wgpuInstanceRequestAdapter(instance, options, callbackInfo)

    fetchedAdapter ?: error("fail to get adapter")
}