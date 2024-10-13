package io.ygdrasil.wgpu

import HelloTriangleScene
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import webgpu.wgpuCreateInstance

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {

    val scene: HelloTriangleScene? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        NativeWgpu4k.wgpuCreateInstance(0L)
        val instance = wgpuCreateInstance(null) ?: error("fail to create instance")

        println("instance: $instance")

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