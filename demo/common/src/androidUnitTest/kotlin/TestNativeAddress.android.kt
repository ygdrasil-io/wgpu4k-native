package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import org.graphiks.kffi.NativeAddress

actual fun testNativeAddress(): NativeAddress = Pointer(1L)
