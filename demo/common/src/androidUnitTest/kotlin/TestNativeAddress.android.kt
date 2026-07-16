package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.kffi.NativeAddress

actual fun testNativeAddress(): NativeAddress = Pointer(1L)
