package io.ygdrasil.wgpu

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import ffi.memoryScope
import webgpu.HelloTriangleScene
import webgpu.WGPUChainedStruct
import webgpu.WGPUInstance
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceAndroidNativeWindow
import webgpu.compatibleFormat
import webgpu.configureSurface
import webgpu.getAdapter
import webgpu.getDevice
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceCreateSurface
import java.lang.foreign.MemorySegment

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {

    var scene: HelloTriangleScene? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        val instance = wgpuCreateInstance(null) ?: error("fail to create instance")
        val surface = getSurface(instance, holder)
        val adapter = getAdapter(surface, instance)
        val device = getDevice(adapter)
        val compatibleFormat = compatibleFormat(surface, adapter)
        configureSurface(device, width, height, surface, compatibleFormat)

        scene = HelloTriangleScene(device, compatibleFormat, surface)
        scene?.initialize()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) { }

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
            chain.sType = 0x00000008u
            window = MemorySegment(nativeWindow, 0)
        }.let { WGPUChainedStruct(it.handler) }
    }

    wgpuInstanceCreateSurface(instance, surfaceDescriptor) ?: error("fail to create surface")
}