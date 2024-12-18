package webgpu

import ffi.MemoryBuffer
import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.memoryScope

val allocator = MemoryAllocator()

fun configureLogs() {
    val callback = WGPULogCallback.allocate(allocator, object : WGPULogCallback {
        override fun invoke(level: WGPULogLevel, message: WGPUStringView?, userdata: NativeAddress?) {
            println("${level} : ${message?.data?.toKString(message.length)}")
        }

    })
    wgpuSetLogLevel(WGPULogLevel_Trace)
    wgpuSetLogCallback(callback, allocator.bufferOfAddress(callback.handler).handler)
}

fun compatibleFormat(surface: WGPUSurface, adapter: WGPUAdapter): UInt = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)
    if (surfaceCapabilities.formatCount == 0uL) error("no surface format")
    println("surface format count: ${surfaceCapabilities.formatCount}")
    return surfaceCapabilities.formats?.handler
        ?.let { MemoryBuffer(it, Int.SIZE_BYTES.toULong() * surfaceCapabilities.formatCount) }
        ?.readInt()?.toUInt()
        ?: error("no compatible format")
}

fun compatibleAlphaMode(surface: WGPUSurface, adapter: WGPUAdapter): UInt = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)
    if (surfaceCapabilities.alphaModeCount == 0uL) error("no surface alpha mode")
    return surfaceCapabilities.alphaModes?.handler?.let { MemoryBuffer(it, Int.SIZE_BYTES.toULong() * surfaceCapabilities.formatCount) }?.readInt()?.toUInt()
        ?: error("no compatible alpha mode")
}

fun configureSurface(
    device: WGPUDevice,
    width: Int,
    height: Int,
    surface: WGPUSurface,
    renderingContextFormat: UInt,
    alphaMode: UInt
) {
    memoryScope { scope ->
        val configuration = WGPUSurfaceConfiguration.allocate(scope).apply {
            this.device = device
            format = renderingContextFormat
            usage = WGPUTextureUsage_RenderAttachment
            this.width = width.toUInt()
            this.height = height.toUInt()
            this.alphaMode = alphaMode
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
            userdata1: NativeAddress?,
            userdata2: NativeAddress?
        ) {
            if (status != WGPURequestDeviceStatus_Success && device == null) error("fail to get device")
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

fun getAdapter(surface: WGPUSurface, instance: WGPUInstance, backendType: UInt = WGPUBackendType_Undefined) = memoryScope { scope ->
    val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(scope)
    val options = WGPURequestAdapterOptions.allocate(scope).apply {
        compatibleSurface = surface
        this.backendType = backendType
    }

    var fetchedAdapter: WGPUAdapter? = null

    val callback = WGPURequestAdapterCallback.allocate(scope, object : WGPURequestAdapterCallback {
        override fun invoke(
            status: WGPURequestAdapterStatus,
            adapter: WGPUAdapter?,
            message: WGPUStringView?,
            userdata1: NativeAddress?,
            userdata2: NativeAddress?
        ) {
            if (status != WGPURequestAdapterStatus_Success || adapter == null) error("fail to get adapter")
            fetchedAdapter = adapter
        }

    })

    callbackInfo.callback = callback
    callbackInfo.userdata2 = scope.bufferOfAddress(callback.handler).handler

    wgpuInstanceRequestAdapter(instance, options, callbackInfo)

    fetchedAdapter ?: error("fail to get adapter")
}