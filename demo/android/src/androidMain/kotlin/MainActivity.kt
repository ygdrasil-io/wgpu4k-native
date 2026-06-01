package io.ygdrasil.wgpu

import android.view.Surface
import android.os.Bundle
import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.nativeHelper.Helper
import io.ygdrasil.kffi.memoryScope
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

        val createdInstance = wgpuCreateInstance(null) ?: error("fail to create instance")
        val createdSurface = getSurface(createdInstance, currentWindow.rawWindowHandle)
        val createdAdapter = getAdapter(createdSurface, createdInstance)
        val createdDevice = getDevice(createdAdapter)
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
        scene = null
        device?.let(::wgpuDeviceRelease)
        adapter?.let(::wgpuAdapterRelease)
        surface?.let(::wgpuSurfaceRelease)
        instance?.let(::wgpuInstanceRelease)
        device = null
        adapter = null
        surface = null
        instance = null
    }
}

private fun getSurface(instance: WGPUInstance, rawWindowHandle: Any): WGPUSurface = memoryScope {
    val androidHandle = rawWindowHandle as? RawWindowHandle.Android
        ?: error("Unsupported Kadre window handle for the Android demo: $rawWindowHandle")
    val androidSurface = androidHandle.surface as? Surface
        ?: error("Kadre Android window handle did not expose android.view.Surface")
    val nativeWindow = Helper.nativeWindowFromSurface(androidSurface)
    getSurfaceAndroidView(instance, NativeAddress(nativeWindow))
}
