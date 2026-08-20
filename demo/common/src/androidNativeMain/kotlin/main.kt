@file:OptIn(
    ExperimentalForeignApi::class,
    ExperimentalNativeApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.memoryScope
import io.ygdrasil.wgpu.HelloTriangleScene
import io.ygdrasil.wgpu.WGPUBackendType_OpenGLES
import io.ygdrasil.wgpu.WGPUBackendType_Vulkan
import io.ygdrasil.wgpu.WGPUInstanceBackend_GL
import io.ygdrasil.wgpu.WGPUInstanceBackend_Vulkan
import io.ygdrasil.wgpu.WGPUInstanceDescriptor
import io.ygdrasil.wgpu.WGPUInstanceExtras
import io.ygdrasil.wgpu.WGPULogCallback
import io.ygdrasil.wgpu.WGPULogLevel
import io.ygdrasil.wgpu.WGPULogLevel_Debug
import io.ygdrasil.wgpu.WGPULogLevel_Error
import io.ygdrasil.wgpu.WGPULogLevel_Info
import io.ygdrasil.wgpu.WGPULogLevel_Trace
import io.ygdrasil.wgpu.WGPULogLevel_Warn
import io.ygdrasil.wgpu.configureSurface
import io.ygdrasil.wgpu.getAdapter
import io.ygdrasil.wgpu.getDevice
import io.ygdrasil.wgpu.getSurfaceAndroidView
import io.ygdrasil.wgpu.selectAndroidBackend
import io.ygdrasil.wgpu.surfaceCapabilities
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuCreateInstance
import io.ygdrasil.wgpu.wgpuInstanceRelease
import io.ygdrasil.wgpu.wgpuSetLogCallback
import io.ygdrasil.wgpu.wgpuSetLogLevel
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import platform.android.ANDROID_LOG_INFO
import platform.android.ANativeActivity
import platform.android.ANativeWindow_getHeight
import platform.android.ANativeWindow_getWidth
import platform.android.__android_log_print
import kotlin.concurrent.atomics.AtomicInt
import kotlin.experimental.ExperimentalNativeApi

private const val LOG_TAG = "NativeActivity"

private fun logInfo(message: String) {
    __android_log_print(ANDROID_LOG_INFO.toInt(), LOG_TAG, message)
}

private fun createAndroidInstance(backends: ULong): io.ygdrasil.wgpu.WGPUInstance = memoryScope { scope ->
    val extras = WGPUInstanceExtras.allocate(scope).apply {
        chain.sType = io.ygdrasil.wgpu.WGPUSType_InstanceExtras
        this.backends = backends
    }
    val descriptor = WGPUInstanceDescriptor.allocate(scope).apply {
        nextInChain = extras.chain
    }
    wgpuCreateInstance(descriptor) ?: error("fail to create instance")
}

private val onNativeWindowCreatedCallback = staticCFunction<CPointer<ANativeActivity>?, COpaquePointer?, Unit> { activity, window ->
    logInfo("onNativeWindowCreated called")

    val window = window ?: error("window is null")
    val windowPtr = window.toNativeAddress()

    /*
     * Android backend selection:
     * Requesting an adapter with a compatible ANativeWindow while Vulkan and
     * GLES are both enabled can claim the BufferQueue before GLES creates its
     * EGL surface. Probe and select the backend without a surface, then bind
     * the window only when the surface is configured.
     *
     * The instance must also be restricted to the selected backend. wgpu's
     * Android GLES backend can leave an EGL connection behind when it is
     * initialized from an all-backends instance after a Vulkan probe.
     */
    val probeInstance = createAndroidInstance(WGPUInstanceBackend_Vulkan)
    val selectedBackend = try {
        selectAndroidBackend(
            probeVulkan = {
                val probeAdapter = getAdapter(
                    surface = null,
                    instance = probeInstance,
                    backendType = WGPUBackendType_Vulkan,
                )
                wgpuAdapterRelease(probeAdapter)
                true
            },
            onFallback = ::logInfo,
        )
    } finally {
        wgpuInstanceRelease(probeInstance)
    }
    val instance = createAndroidInstance(
        if (selectedBackend == WGPUBackendType_Vulkan) {
            WGPUInstanceBackend_Vulkan
        } else {
            WGPUInstanceBackend_GL
        },
    )
    val surface = getSurfaceAndroidView(instance, windowPtr)
    // Do not let adapter selection touch the Android window. On API 29 the GL
    // backend may create a temporary EGL window surface for a compatible-surface
    // hint; configuring the real surface afterwards then fails with
    // EGL_BAD_ALLOC / BufferQueue "already connected".
    val adapter = getAdapter(surface = null, instance = instance, backendType = selectedBackend)
    val device = getDevice(adapter, instance)
    val surfaceCapabilities = surfaceCapabilities(surface, adapter)
    val surfaceFormat = surfaceCapabilities.formats.first()
    val alphaMode = surfaceCapabilities.alphaModes.first()
    val width = ANativeWindow_getWidth(window.reinterpret())
    val height = ANativeWindow_getHeight(window.reinterpret())
    configureSurface(device, width, height, surface, surfaceFormat, alphaMode, listOf(surfaceFormat))
    val scene = HelloTriangleScene(device, surfaceFormat, surface).apply {
        initialize()
    }
    scene.render()
}

@CName("ANativeActivity_onCreate")
fun ANativeActivity_onCreate(
    activity: ANativeActivity?,
    savedState: COpaquePointer?,
    savedStateSize: Int
) {
    println("ANativeActivity_onCreate called")

    configureLogs()

    val activity = activity ?: error("activity is null")
    val callbacks = activity.callbacks ?: error("callbacks is null")

    callbacks.pointed.onNativeWindowCreated = onNativeWindowCreatedCallback.reinterpret()
}

private val logCallbackConfigurationLock = AtomicInt(0)
private var logCallback: CallbackRegistration<WGPULogCallback>? = null

private fun configureLogs(logLevel: WGPULogLevel = WGPULogLevel_Trace) = withLogCallbackConfigurationLock {
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

private fun COpaquePointer.toNativeAddress() = NativeAddress.fromPointer(this)
