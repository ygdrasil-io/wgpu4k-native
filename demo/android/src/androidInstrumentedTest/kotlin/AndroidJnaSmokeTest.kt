package io.ygdrasil.wgpu

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidJnaSmokeTest {
    @Test
    fun loadsVersionAndCreatesInstance() {
        assertNotEquals(0u, wgpuGetVersion())
        val instance = assertNotNull(wgpuCreateInstance(null))
        wgpuInstanceRelease(instance)
    }
}
