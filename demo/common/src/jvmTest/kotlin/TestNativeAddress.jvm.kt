package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import java.lang.foreign.MemorySegment

actual fun testNativeAddress(): NativeAddress = NativeAddress(MemorySegment.ofAddress(1L))
