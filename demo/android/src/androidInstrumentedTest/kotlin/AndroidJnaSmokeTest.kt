package io.ygdrasil.wgpu

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sun.jna.Pointer
import io.ygdrasil.kffi.memoryScope
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidJnaSmokeTest {
    @Test
    fun writesCompleteNativeWindowSourceBeforeChaining() = memoryScope { scope ->
        val expectedWindow = Pointer(0x1234L)
        val source = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
            chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
            window = expectedWindow
        }
        source.handler

        val descriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = source.chain
        }
        val rawDescriptor = io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByReference(descriptor.handler)
            .apply { read() }
        val rawSource = io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByReference(
            rawDescriptor.nextInChain
        ).apply { read() }

        assertEquals(WGPUSType_SurfaceSourceAndroidNativeWindow.toInt(), rawSource.chain.sType)
        assertEquals(Pointer.nativeValue(expectedWindow), Pointer.nativeValue(rawSource.window))
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
