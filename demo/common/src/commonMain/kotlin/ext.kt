@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.ArrayHolder
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.memoryScope
import kotlin.concurrent.atomics.AtomicInt

private val logCallbackConfigurationLock = AtomicInt(0)
private var logCallback: CallbackRegistration<WGPULogCallback>? = null

fun configureLogs(logLevel: WGPULogLevel = WGPULogLevel_Trace) = withLogCallbackConfigurationLock {
    val previous = logCallback
    previous?.close()
    wgpuSetLogLevel(logLevel)
    val replacement = wgpuSetLogCallback(policy = CallbackPolicy.REPEATING) { level, message ->
        val kMessage = message.data?.toKString(message.length)
        when (level) {
            WGPULogLevel_Error -> println("ERROR : $kMessage}")
            WGPULogLevel_Warn -> println("WARN : $kMessage")
            WGPULogLevel_Info -> println("INFO : $kMessage")
            WGPULogLevel_Debug -> println("DEBUG : $kMessage")
            WGPULogLevel_Trace -> println("TRACE : $kMessage")
        }
    }
    logCallback = replacement
}

private fun <T> withLogCallbackConfigurationLock(block: () -> T): T {
    while (!logCallbackConfigurationLock.compareAndSet(0, 1)) {
        // Logger reconfiguration is rare and must remain multiplatform without a scheduler dependency.
    }
    try {
        return block()
    } finally {
        logCallbackConfigurationLock.store(0)
    }
}


fun configureSurface(
    device: WGPUDevice,
    width: Int,
    height: Int,
    surface: WGPUSurface,
    renderingContextFormat: UInt,
    alphaMode: UInt,
    viewFormats: List<WGPUTextureFormat> = listOf()
) {
    memoryScope { scope ->
        val configuration = WGPUSurfaceConfiguration.allocate(scope).also {
            it.device = device
            it.format = renderingContextFormat
            it.usage = WGPUTextureUsage_RenderAttachment
            it.width = width.toUInt()
            it.height = height.toUInt()
            it.alphaMode = alphaMode
            if (viewFormats.isNotEmpty()) {
                it.viewFormatCount = viewFormats.size.toULong()
                it.viewFormats = scope.allocateBuffer(viewFormats.size.toULong() * UInt.SIZE_BYTES.toULong())
                    .also { buffer -> buffer.writeUInts(viewFormats.toUIntArray())}
                    .handler
            }
        }
        wgpuSurfaceConfigure(surface, configuration)
    }
}

fun getDevice(adapter: WGPUAdapter, instance: WGPUInstance): WGPUDevice {
    val state = CallbackRequestState<WGPURequestDeviceStatus, WGPUDevice>(::wgpuDeviceRelease)
    val registration = WGPURequestDeviceCallback.register(CallbackPolicy.ONCE) { status, device, message, _ ->
        state.publishCopied(status, device) {
            message.data?.toKString(message.length)
        }
    }
    var futureId = 0uL
    val snapshot = awaitCallbackRequestResult(
        state = state,
        phase = "request-device",
        await = {
            futureId = memoryScope { scope ->
                val info = WGPURequestDeviceCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_WaitAnyOnly,
                    registration,
                )
                wgpuAdapterRequestDevice(adapter, null, info).id
            }
            awaitCallbackFuture(
                futureId = futureId,
                phase = "request-device",
                zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
                isComplete = { state.isComplete },
                isQuiescent = { registration.isQuiescent },
                waitOnce = { waitAnyOnce(instance, it) },
            )
        },
        close = registration::close,
        isClosed = { registration.isClosed },
        isQuiescent = { registration.isQuiescent },
        pump = { pumpRequestCleanup(instance, futureId, "request-device") },
    )
    return resolveDeviceRequestResult(
        snapshot.status,
        state.takeHandle(),
        snapshot.message,
        ::wgpuDeviceRelease,
    )
}

internal fun <D : Any> resolveDeviceRequestResult(
    status: WGPURequestDeviceStatus?,
    device: D?,
    message: String?,
    release: (D) -> Unit,
): D {
    if (status != WGPURequestDeviceStatus_Success) {
        device?.let(release)
        error("fail to get device with status $status${callbackMessageSuffix(message)}")
    }
    return device ?: error("fail to get device: success status returned no device")
}

private fun callbackMessageSuffix(message: String?): String =
    message?.takeIf { it.isNotEmpty() }?.let { ": $it" }.orEmpty()

internal fun <A : Any> resolveAdapterRequestResult(
    status: WGPURequestAdapterStatus?,
    adapter: A?,
    message: String?,
    release: (A) -> Unit,
): A {
    if (status != WGPURequestAdapterStatus_Success) {
        adapter?.let(release)
        error("fail to get adapter with status $status${callbackMessageSuffix(message)}")
    }
    return adapter ?: error("fail to get adapter: success status returned no adapter")
}

fun getAdapter(
    surface: WGPUSurface?,
    instance: WGPUInstance,
    backendType: UInt = WGPUBackendType_Undefined,
): WGPUAdapter {
    val state = CallbackRequestState<WGPURequestAdapterStatus, WGPUAdapter>(::wgpuAdapterRelease)
    val registration = WGPURequestAdapterCallback.register(CallbackPolicy.ONCE) { status, adapter, message, _ ->
        state.publishCopied(status, adapter) {
            message.data?.toKString(message.length)
        }
    }
    var futureId = 0uL
    val snapshot = awaitCallbackRequestResult(
        state = state,
        phase = "request-adapter",
        await = {
            futureId = memoryScope { scope ->
                val options = WGPURequestAdapterOptions.allocate(scope).apply {
                    if (surface != null) compatibleSurface = surface
                    this.backendType = backendType
                }
                val info = WGPURequestAdapterCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_WaitAnyOnly,
                    registration,
                )
                wgpuInstanceRequestAdapter(instance, options, info).id
            }
            awaitCallbackFuture(
                futureId = futureId,
                phase = "request-adapter",
                zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
                isComplete = { state.isComplete },
                isQuiescent = { registration.isQuiescent },
                waitOnce = { waitAnyOnce(instance, it) },
            )
        },
        close = registration::close,
        isClosed = { registration.isClosed },
        isQuiescent = { registration.isQuiescent },
        pump = { pumpRequestCleanup(instance, futureId, "request-adapter") },
    )
    return resolveAdapterRequestResult(
        snapshot.status,
        state.takeHandle(),
        snapshot.message,
        ::wgpuAdapterRelease,
    )
}

private fun pumpRequestCleanup(instance: WGPUInstance, futureId: ULong, phase: String) {
    if (futureId == 0uL) return
    val status = waitAnyOnce(instance, futureId)
    check(status == WGPUWaitStatus_Success || status == WGPUWaitStatus_TimedOut) {
        "$phase-close waitAny status=$status future-id=$futureId"
    }
}

fun getSurfaceFromMetalLayer(instance: WGPUInstance, metalLayer: NativeAddress): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceMetalLayer.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceMetalLayer
            layer = metalLayer
        }.chain
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}

fun getSurfaceAndroidView(
    instance: WGPUInstance,
    surfaceHolder: NativeAddress
): WGPUSurface = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
            window = surfaceHolder
        }.chain
    }

    wgpuInstanceCreateSurface(instance, surfaceDescriptor) ?: error("fail to create surface")
}

fun getSurfaceFromX11Window(instance: WGPUInstance, display: NativeAddress, window: ULong): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceXlibWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceXlibWindow
            this.display = display
            this.window = window
        }.chain
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}

fun getSurfaceFromWindows(instance: WGPUInstance, hinstance: NativeAddress, hwnd: NativeAddress): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceWindowsHWND.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceWindowsHWND
            this.hwnd = hwnd
            this.hinstance = hinstance
        }.chain
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}

fun getSurfaceFromWaylandWindow(instance: WGPUInstance, display: NativeAddress, surface: NativeAddress): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceWaylandSurface.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceWaylandSurface
            this.display = display
            this.surface = surface
        }.chain
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}
