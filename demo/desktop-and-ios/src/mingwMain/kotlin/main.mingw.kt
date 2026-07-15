@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetWin32Window
import kotlinx.cinterop.*
import platform.windows.GetModuleHandle
import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.wgpu.WGPUInstance
import io.ygdrasil.wgpu.WGPUSurface

actual fun getSurface(instance: WGPUInstance, window: CPointer<GLFWwindow>): WGPUSurface {
    val hwnd = glfwGetWin32Window(window)  ?: error("fail to get hwnd")
    val hinstance: COpaquePointer = GetModuleHandle?.invoke(null)
        ?.let { interpretCPointer<COpaque>(it.rawValue) }
        ?.reinterpret() ?: error("fail to get hinstance")
    return getSurfaceFromWindows(instance, hinstance.let(::NativeAddress), hwnd.let(::NativeAddress))
        ?: error("fail to get surface on Windows")
}
