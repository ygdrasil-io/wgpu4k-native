@file:OptIn(ExperimentalForeignApi::class)

import ffi.memoryScope
import glfw.GLFW_CLIENT_API
import glfw.GLFW_FALSE
import glfw.GLFW_NO_API
import glfw.GLFW_RESIZABLE
import glfw.GLFW_VISIBLE
import glfw.glfwCreateWindow
import glfw.glfwDestroyWindow
import glfw.glfwInit
import glfw.glfwPollEvents
import glfw.glfwShowWindow
import glfw.glfwWindowHint
import glfw.glfwWindowShouldClose
import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPURequestAdapterCallbackInfo
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceRequestAdapter


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

    //val glfwContext = glfwContextRenderer()
    val instance = wgpuCreateInstance(null) ?: error("fail to create instance")

    val adapter = memoryScope { scope ->
        val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(scope)

        wgpuInstanceRequestAdapter(instance, null, callbackInfo)
    }



    glfwShowWindow(windowHandler)

    while (glfwWindowShouldClose(windowHandler) != 1) {
        glfwPollEvents()
    }

    glfwDestroyWindow(windowHandler)
}

