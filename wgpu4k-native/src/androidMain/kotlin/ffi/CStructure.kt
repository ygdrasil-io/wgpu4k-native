package ffi

import webgpu.toInt
import java.lang.foreign.AddressLayout
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemorySegment.Companion.NULL
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.Companion.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.Companion.JAVA_FLOAT
import java.lang.foreign.ValueLayout.Companion.JAVA_INT
import java.lang.foreign.ValueLayout.Companion.JAVA_LONG
import java.lang.foreign.ValueLayout.Companion.JAVA_SHORT

interface CStructure {
    val handler: NativeAddress


}