package io.ygdrasil.wgpu

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.memoryScope
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidSmokeTest {
    @Test
    fun writesCompleteNativeWindowSourceBeforeChaining() = memoryScope { scope ->
        val expectedWindow = NativeAddress(0x1234L)
        val source = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
            window = expectedWindow
        }
        source.handler

        val descriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = source.chain
        }
        val rawSource = WGPUSurfaceSourceAndroidNativeWindow(descriptor.nextInChain!!.handler)

        assertEquals(WGPUSType_SurfaceSourceAndroidNativeWindow, rawSource.chain.sType)
        assertEquals(expectedWindow.rawValue, rawSource.window?.rawValue)
    }

    @Test
    fun registersLogsLoadsVersionAndCreatesInstance() {
        configureLogs(WGPULogLevel_Error)
        assertNotEquals(0u, wgpuGetVersion())
        val instance = assertNotNull(wgpuCreateInstance(null))
        wgpuInstanceRelease(instance)
    }

    @Test
    fun launchesRenderingActivity() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val intent = Intent(instrumentation.targetContext, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val activity = instrumentation.startActivitySync(intent)
        instrumentation.waitForIdleSync()

        assertFalse(activity.isFinishing)
        instrumentation.runOnMainSync(activity::finish)
    }
}
