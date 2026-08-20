package io.ygdrasil.wgpu

import android.view.Surface
import android.os.Bundle
import org.graphiks.kffi.NativeAddress
import io.ygdrasil.nativeHelper.Helper
import org.graphiks.kffi.memoryScope
import io.ygdrasil.wgpu.WGPUBackendType_OpenGLES
import io.ygdrasil.wgpu.WGPUBackendType_Vulkan
import io.ygdrasil.wgpu.WGPUInstanceBackend_GL
import io.ygdrasil.wgpu.WGPUInstanceBackend_Vulkan
import io.ygdrasil.wgpu.WGPUInstanceDescriptor
import io.ygdrasil.wgpu.WGPUInstanceExtras
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.android.KadreActivity
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent

class MainActivity : KadreActivity() {

    override fun createHandler(): ApplicationHandler = HelloTriangleAndroidKadreApp()

    override fun onCreate(savedInstanceState: Bundle?) {
        configureLogs()
        super.onCreate(savedInstanceState)
    }
}

private class HelloTriangleAndroidKadreApp : ApplicationHandler {

    private var window: Window? = null
    private var instance: WGPUInstance? = null
    private var surface: WGPUSurface? = null
    private var adapter: WGPUAdapter? = null
    private var device: WGPUDevice? = null
    private var surfaceFormat: WGPUTextureFormat = WGPUTextureFormat_Undefined
    private var alphaMode: WGPUCompositeAlphaMode = WGPUCompositeAlphaMode_Auto
    private var scene: HelloTriangleScene? = null

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        window = eventLoop.createWindow(
            WindowAttributes(
                title = "Kadre + WebGPU",
                size = PhysicalSize(width = 640, height = 480),
                visible = true,
                resizable = false,
            )
        )
    }

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        window?.requestRedraw()
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WindowEvent.Resized -> {
                val currentWindow = window ?: return
                ensureScene(currentWindow, event.size)
                resize(event.size)
            }

            is WindowEvent.RedrawRequested -> {
                val currentWindow = window ?: return
                ensureScene(currentWindow, currentWindow.innerSize)
                scene?.render()
            }
        }
    }

    override fun destroySurfaces(eventLoop: ActiveEventLoop) {
        releaseResources()
        window = null
    }

    private fun ensureScene(currentWindow: Window, size: PhysicalSize<Int>) {
        if (scene != null || size.width <= 0 || size.height <= 0) return

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
                onFallback = { println("[Android] $it") },
            )
        } finally {
            wgpuInstanceRelease(probeInstance)
        }
        val createdInstance = createAndroidInstance(
            if (selectedBackend == WGPUBackendType_Vulkan) {
                WGPUInstanceBackend_Vulkan
            } else {
                WGPUInstanceBackend_GL
            },
        )
        val createdSurface = getSurface(createdInstance, currentWindow.rawWindowHandle)
        // Keep adapter selection independent from the Android window. On API 29
        // a GL compatible-surface probe can leave the BufferQueue connected and
        // make the later surface configuration fail with EGL_BAD_ALLOC.
        val createdAdapter = getAdapter(
            surface = null,
            instance = createdInstance,
            backendType = selectedBackend,
        )
        val createdDevice = getDevice(createdAdapter, createdInstance)
        val capabilities = surfaceCapabilities(createdSurface, createdAdapter)
        val format = capabilities.formats.first()
        val selectedAlphaMode = capabilities.alphaModes.firstOrNull { it == WGPUCompositeAlphaMode_Opaque }
            ?: capabilities.alphaModes.first()

        configureSurface(
            createdDevice,
            size.width,
            size.height,
            createdSurface,
            format,
            selectedAlphaMode,
            listOf(format),
        )

        instance = createdInstance
        surface = createdSurface
        adapter = createdAdapter
        device = createdDevice
        surfaceFormat = format
        alphaMode = selectedAlphaMode
        scene = HelloTriangleScene(createdDevice, format, createdSurface).also { it.initialize() }
    }

    private fun resize(size: PhysicalSize<Int>) {
        val currentDevice = device ?: return
        val currentSurface = surface ?: return
        if (size.width <= 0 || size.height <= 0) return
        configureSurface(
            currentDevice,
            size.width,
            size.height,
            currentSurface,
            surfaceFormat,
            alphaMode,
            listOf(surfaceFormat),
        )
    }

    private fun releaseResources() {
        releaseAndroidResources(
            scene = scene,
            device = device,
            adapter = adapter,
            surface = surface,
            instance = instance,
        )
        scene = null
        device = null
        adapter = null
        surface = null
        instance = null
    }
}

internal fun releaseAndroidResources(
    scene: HelloTriangleScene?,
    device: WGPUDevice?,
    adapter: WGPUAdapter?,
    surface: WGPUSurface?,
    instance: WGPUInstance?,
    releaseScene: (HelloTriangleScene?) -> Unit = { it?.close() },
    releaseDevice: (WGPUDevice) -> Unit = ::wgpuDeviceRelease,
    releaseAdapter: (WGPUAdapter) -> Unit = ::wgpuAdapterRelease,
    releaseSurface: (WGPUSurface) -> Unit = ::wgpuSurfaceRelease,
    releaseInstance: (WGPUInstance) -> Unit = ::wgpuInstanceRelease,
) {
    releaseScene(scene)
    device?.let(releaseDevice)
    adapter?.let(releaseAdapter)
    surface?.let(releaseSurface)
    instance?.let(releaseInstance)
}

private fun getSurface(instance: WGPUInstance, rawWindowHandle: Any): WGPUSurface = memoryScope {
    val androidHandle = rawWindowHandle as? RawWindowHandle.Android
        ?: error("Unsupported Kadre window handle for the Android demo: $rawWindowHandle")
    val androidSurface = androidHandle.surface as? Surface
        ?: error("Kadre Android window handle did not expose android.view.Surface")
    val nativeWindow = Helper.nativeWindowFromSurface(androidSurface)
    getSurfaceAndroidView(instance, NativeAddress(nativeWindow))
}

private fun createAndroidInstance(backends: ULong): WGPUInstance = memoryScope { scope ->
    val extras = WGPUInstanceExtras.allocate(scope).apply {
        chain.sType = WGPUSType_InstanceExtras
        this.backends = backends
    }
    val descriptor = WGPUInstanceDescriptor.allocate(scope).apply {
        nextInChain = extras.chain
    }
    wgpuCreateInstance(descriptor) ?: error("fail to create instance")
}
