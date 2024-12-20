package io.ygdrasil.wgpu

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.sun.jna.Pointer
import ffi.memoryScope
import webgpu.HelloTriangleScene
import webgpu.WGPUBackendType_OpenGLES
import webgpu.WGPUBackendType_Vulkan
import webgpu.WGPUInstance
import webgpu.WGPUSType_SurfaceSourceAndroidNativeWindow
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceAndroidNativeWindow
import webgpu.compatibleAlphaMode
import webgpu.compatibleFormat
import webgpu.configureLogs
import webgpu.configureSurface
import webgpu.getAdapter
import webgpu.getDevice
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceCreateSurface
import webgpu.surfaceCapabilities

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {

    var scene: HelloTriangleScene? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        holder.addCallback(this)
        configureLogs()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(surfaceHolder: SurfaceHolder): Unit = memoryScope { scope ->
        val instance = wgpuCreateInstance(null) ?: error("fail to create instance")
        val surface = getSurface(instance, holder)
        val adapter = getAdapter(surface, instance, WGPUBackendType_Vulkan)
        val device = getDevice(adapter)
        val surfaceCapabilities = surfaceCapabilities(surface, adapter)
        configureSurface(device, width, height, surface, surfaceCapabilities.formats.first(), surfaceCapabilities.alphaModes.first(), listOf(surfaceCapabilities.formats.first()))
        scene = HelloTriangleScene(device, surfaceCapabilities.formats.first(), surface).apply {
            initialize()
        }

        setWillNotDraw(false)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        scene?.render()
        invalidate()
    }

    override fun surfaceRedrawNeeded(holder: SurfaceHolder) {}

}

fun getSurface(
    instance: WGPUInstance,
    surfaceHolder: SurfaceHolder
): WGPUSurface = memoryScope { scope ->
    val nativeWindow = io.ygdrasil.nativeHelper.Helper.nativeWindowFromSurface(surfaceHolder.surface)

    val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
        nextInChain = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
            window = Pointer(nativeWindow)
        }.handler
    }

    wgpuInstanceCreateSurface(instance, surfaceDescriptor) ?: error("fail to create surface")
}