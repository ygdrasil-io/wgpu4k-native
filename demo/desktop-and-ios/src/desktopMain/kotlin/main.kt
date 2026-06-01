@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

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
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fwrite
import platform.posix.mkdir

fun main(args: Array<String>) {
    if ("--headless" in args) {
        configureLogs(WGPULogLevel_Warn)
        val image = renderHeadlessTriangle()
        val outputPath = "build/headless/triangle.ppm"
        mkdir("build", 511.convert())
        mkdir("build/headless", 511.convert())
        writePpm(image, outputPath)
        println("Wrote $outputPath")
        return
    }

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
    val surfaceCapabilities = surfaceCapabilities(surface, adapter)
    configureSurface(device, width, height, surface, surfaceCapabilities.formats.first(), surfaceCapabilities.alphaModes.first(), listOf(surfaceCapabilities.formats.first()))

    val scene = HelloTriangleScene(device, surfaceCapabilities.formats.first(), surface)
    scene.initialize()

    glfwShowWindow(windowHandler)

    while (glfwWindowShouldClose(windowHandler) != 1) {
        scene.render()
        glfwPollEvents()
    }

    glfwDestroyWindow(windowHandler)
}

expect fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface

private fun writePpm(image: HeadlessTriangleImage, path: String) {
    val file = fopen(path, "wb") ?: error("fail to open $path")
    try {
        val header = "P6\n${image.width} ${image.height}\n255\n".encodeToByteArray()
        header.usePinned {
            fwrite(it.addressOf(0), 1.convert(), header.size.convert(), file)
        }

        val rgb = ByteArray(image.width * image.height * 3)
        var destination = 0
        for (source in image.rgba.indices step 4) {
            rgb[destination++] = image.rgba[source]
            rgb[destination++] = image.rgba[source + 1]
            rgb[destination++] = image.rgba[source + 2]
        }
        rgb.usePinned {
            fwrite(it.addressOf(0), 1.convert(), rgb.size.convert(), file)
        }
    } finally {
        fclose(file)
    }
}
