@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
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
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.HelloTriangleScene
import webgpu.WGPUInstance
import webgpu.WGPUSurface
import webgpu.compatibleAlphaMode
import webgpu.compatibleFormat
import webgpu.configureLogs
import webgpu.configureSurface
import webgpu.getAdapter
import webgpu.getDevice
import webgpu.wgpuCreateInstance

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

expect fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface
