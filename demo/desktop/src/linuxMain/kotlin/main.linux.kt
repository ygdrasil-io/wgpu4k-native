@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import ffi.memoryScope
import ffi.NativeAddress
import glfw.glfwGetX11Display
import glfw.glfwGetX11Window
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import webgpu.WGPUInstance
import webgpu.WGPUSType_SurfaceSourceXlibWindow
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceXlibWindow
import webgpu.wgpuInstanceCreateSurface

actual fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface {

    val display = glfwGetX11Display(window) ?: error("fail to get X11 display")
    val x11_window = glfwGetX11Window(window).takeIf { it != 0uL } ?: error("fail to get X11 window")

    return getSurfaceFromX11Window(instance, display.reinterpret(), x11_window) ?: error("fail to get surface on MacOs")
}

fun getSurfaceFromX11Window(instance: WGPUInstance, display: COpaquePointer, window: ULong): WGPUSurface? = memoryScope { scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceXlibWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceXlibWindow
            this.display = display.let(::NativeAddress)
            this.window = window
        }.handler
    }

    wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}