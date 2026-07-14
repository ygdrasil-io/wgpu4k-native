package io.ygdrasil.wgpu

import ffi.LibraryLoader
import io.ygdrasil.kffi.NativeAddress
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.Window
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment

fun main(args: Array<String>) {
    val verifyCaptureIndex = args.indexOf("--verify-capture")
    if (verifyCaptureIndex >= 0) {
        val path = args.getOrNull(verifyCaptureIndex + 1)
            ?: error("--verify-capture requires a PNG output path")
        captureFrame(path)
        verifyCapture(path)
        return
    }

    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requires a PNG output path")
        captureFrame(path)
        return
    }

    LibraryLoader.load()
    configureLogs()
    EventLoop().runApp(HelloTriangleKadreApp())
}

private class HelloTriangleKadreApp : ApplicationHandler {

    private var window: Window? = null
    private var instance: WGPUInstance? = null
    private var surface: WGPUSurface? = null
    private var adapter: WGPUAdapter? = null
    private var device: WGPUDevice? = null
    private var surfaceFormat: WGPUTextureFormat = WGPUTextureFormat_Undefined
    private var alphaMode: WGPUCompositeAlphaMode = WGPUCompositeAlphaMode_Auto
    private var scene: HelloTriangleScene? = null

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val createdWindow = eventLoop.createWindow(
            WindowAttributes(
                title = "Kadre + WebGPU",
                size = PhysicalSize(width = 640, height = 480),
                visible = true,
                resizable = true,
            )
        )
        window = createdWindow

        val createdInstance = wgpuCreateInstance(null) ?: error("fail to create instance")
        val createdSurface = getSurface(createdInstance, createdWindow.rawWindowHandle)
        val createdAdapter = getAdapter(createdSurface, createdInstance)
        val createdDevice = getDevice(createdAdapter, createdInstance)
        val capabilities = surfaceCapabilities(createdSurface, createdAdapter)
        val format = capabilities.formats.first()
        val selectedAlphaMode = capabilities.alphaModes.firstOrNull { it == WGPUCompositeAlphaMode_Opaque }
            ?: capabilities.alphaModes.first()

        val size = createdWindow.innerSize
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

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        window?.requestRedraw()
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        when (event) {
            is WindowEvent.RedrawRequested -> scene?.render()
            is WindowEvent.Resized -> resize(event.size)
            is WindowEvent.ScaleFactorChanged -> window?.innerSize?.let(::resize)
            is WindowEvent.CloseRequested -> {
                releaseResources()
                eventLoop.exit()
            }
        }
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
        window = null
    }
}

private fun getSurface(instance: WGPUInstance, rawWindowHandle: Any): WGPUSurface =
    when (rawWindowHandle) {
        is RawWindowHandle.AppKit -> {
            val metalLayer = rawWindowHandle.nsLayer.takeIf { it != 0L }
                ?: error("Kadre AppKit did not expose a CAMetalLayer")
            getSurfaceFromMetalLayer(instance, metalLayer.toNativeAddress())
        }

        is RawWindowHandle.Win32 -> getSurfaceFromWindows(
            instance,
            rawWindowHandle.hinstance.toNativeAddress(),
            rawWindowHandle.hwnd.toNativeAddress(),
        )

        is RawWindowHandle.Xlib -> getSurfaceFromX11Window(
            instance,
            rawWindowHandle.display.toNativeAddress(),
            rawWindowHandle.window.toULong(),
        )

        is RawWindowHandle.Wayland -> getSurfaceFromWaylandWindow(
            instance,
            rawWindowHandle.display.toNativeAddress(),
            rawWindowHandle.surface.toNativeAddress(),
        )

        else -> error("Unsupported Kadre window handle for the JVM demo: $rawWindowHandle")
    } ?: error("fail to create surface from Kadre window handle")

private fun Long.toNativeAddress() = NativeAddress(MemorySegment.ofAddress(this))
