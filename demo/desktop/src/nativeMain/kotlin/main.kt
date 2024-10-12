@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import ffi.NativeAddress
import ffi.memoryScope
import glfw.GLFW_CLIENT_API
import glfw.GLFW_FALSE
import glfw.GLFW_NO_API
import glfw.GLFW_RESIZABLE
import glfw.GLFW_VISIBLE
import glfw.glfwCreateWindow
import glfw.glfwDestroyWindow
import glfw.glfwGetCocoaWindow
import glfw.glfwInit
import glfw.glfwPollEvents
import glfw.glfwShowWindow
import glfw.glfwWindowHint
import glfw.glfwWindowShouldClose
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.interpretObjCPointer
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.reinterpret
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer
import webgpu.WGPUAdapter
import webgpu.WGPUChainedStruct
import webgpu.WGPUDevice
import webgpu.WGPUDeviceDescriptor
import webgpu.WGPUInstance
import webgpu.WGPURequestAdapterCallback
import webgpu.WGPURequestAdapterCallbackInfo
import webgpu.WGPURequestAdapterOptions
import webgpu.WGPURequestAdapterStatus
import webgpu.WGPURequestDeviceCallback
import webgpu.WGPURequestDeviceCallbackInfo
import webgpu.WGPURequestDeviceStatus
import webgpu.WGPUStringView
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceConfiguration
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceMetalLayer
import webgpu.wgpuAdapterRequestDevice
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceCreateSurface
import webgpu.wgpuInstanceRequestAdapter
import webgpu.wgpuSurfaceConfigure


fun main() {
    val width = 640
    val height = 480
    val title = "GLFW+WebGPU"

    glfwInit()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
    // Disable context creation, WGPU will manage that
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val windowHandler = glfwCreateWindow(width, height, title, null, null)
        ?: error("fail to create windows")

    val instance = wgpuCreateInstance(null) ?: error("fail to create instance")
    val surface = getSurface(instance, windowHandler)
    val adapter = getAdapter(surface, instance)
    val device = getDevice(adapter)
    configureSurface(device, width, height, surface)

    val scene = HelloTriangleScene(device, 23u, surface)
    scene.initialize()

    glfwShowWindow(windowHandler)

    while (glfwWindowShouldClose(windowHandler) != 1) {
        scene.render()
        glfwPollEvents()
    }

    glfwDestroyWindow(windowHandler)
}

private fun configureSurface(device: WGPUDevice, width: Int, height: Int, surface: WGPUSurface) {
    memoryScope { scope ->
        val configuration = WGPUSurfaceConfiguration.allocate(scope).apply {
            this.device = device
            format = 23u
            usage = 16u
            this.width = width.toUInt()
            this.height = height.toUInt()
        }
        wgpuSurfaceConfigure(surface, configuration)
    }
}

private fun getDevice(adapter: WGPUAdapter): WGPUDevice = memoryScope { scope ->
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
        this.userdata2 = scope.bufferOf(callback.handler).handler
    }

    wgpuAdapterRequestDevice(adapter, null, callbackInfo)

    fetchedDevice ?: error("fail to get device")
}

private fun getAdapter(surface: WGPUSurface, instance: WGPUInstance) = memoryScope { scope ->
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
        callbackInfo.userdata2 = scope.bufferOf(callback.handler).handler

        wgpuInstanceRequestAdapter(instance, options, callbackInfo)

        fetchedAdapter ?: error("fail to get adapter")
    }


private fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface {
    val nsWindow = interpretObjCPointer<NSWindow>(glfwGetCocoaWindow(window)!!.rawValue)
    nsWindow.contentView()?.setWantsLayer(true)
    val layer = CAMetalLayer.layer()
    nsWindow.contentView()?.setLayer(layer)
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
    return getSurfaceFromMetalLayer(instance, layerPointer) ?: error("fail to get surface on MacOs")
}

private fun getSurfaceFromMetalLayer(instance: WGPUInstance, metalLayer: COpaquePointer): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceMetalLayer.allocate(scope).apply {
            chain.sType = 0x00000004u
            layer = metalLayer.rawValue.toLong()
        }.let { WGPUChainedStruct(it.handler) }
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}