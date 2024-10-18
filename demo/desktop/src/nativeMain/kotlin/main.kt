@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import ffi.MemoryAllocator
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
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.interpretObjCPointer
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.reinterpret
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer
import webgpu.HelloTriangleScene
import webgpu.WGPUChainedStruct
import webgpu.WGPUInstance
import webgpu.WGPULogCallback
import webgpu.WGPULogLevel
import webgpu.WGPUStringView
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceMetalLayer
import webgpu.compatibleAlphaMode
import webgpu.compatibleFormat
import webgpu.configureLogs
import webgpu.configureSurface
import webgpu.getAdapter
import webgpu.getDevice
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceCreateSurface
import webgpu.wgpuSetLogCallback
import webgpu.wgpuSetLogLevel

fun main() {
    val width = 640
    val height = 480
    val title = "GLFW+WebGPU"

    configureLogs()

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
    val compatibleFormat = compatibleFormat(surface, adapter)
    val alphaMode = compatibleAlphaMode(surface, adapter)
    configureSurface(device, width, height, surface, compatibleFormat, alphaMode)

    val scene = HelloTriangleScene(device, compatibleFormat, surface)
    scene.initialize()

    glfwShowWindow(windowHandler)

    while (glfwWindowShouldClose(windowHandler) != 1) {
        scene.render()
        glfwPollEvents()
    }

    glfwDestroyWindow(windowHandler)
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
        }.handler
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}