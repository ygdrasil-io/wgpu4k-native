@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import glfw.glfwGetWin32Window
import kotlinx.cinterop.*
import platform.windows.GetModuleHandle
import ffi.NativeAddress

actual fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface {
    val hwnd = glfwGetWin32Window(window)  ?: error("fail to get hwnd")
    val hinstance: COpaquePointer = GetModuleHandle?.invoke(null)
        ?.let { interpretCPointer<COpaque>(it.rawValue) }
        ?.reinterpret() ?: error("fail to get hinstance")
    return getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")
}

fun getSurfaceFromWindows(instance: WGPUInstance, hinstance: COpaquePointer, hwnd: COpaquePointer) = memoryScope {
    scope ->

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceWindowsHWND.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceWindowsHWND
            this.hwnd = hwnd
            this.hinstance = hinstance
        }.handler
    }

    return wgpuInstanceCreateSurface(instance, surfaceDescriptor)
}