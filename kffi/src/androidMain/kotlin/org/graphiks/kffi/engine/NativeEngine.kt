package org.graphiks.kffi.engine

import java.nio.ByteBuffer

object NativeEngine {
    init {
        System.loadLibrary("kffi")
    }

    external fun loadNativeLibrary(path: String): Long
    external fun resolveSymbolIn(handle: Long, name: String): Long
    external fun resolveSymbol(name: String): Long
    external fun callV0(fn: Long)
    external fun callI0(fn: Long): Long
    external fun callI4IIII(fn: Long, a: Int, b: Int, c: Int, d: Int): Long
    external fun callV2PP(fn: Long, p1: Long, p2: Long)
    external fun callV1I(fn: Long, i: Int)
    external fun directBufferAddress(buffer: ByteBuffer): Long
    external fun callStructArgL(fn: Long, structSize: Int, structPtr: Long, arg2: Long): Long
    external fun callStructReturn(fn: Long, a: Long, b: Long, structSize: Int, outPtr: Long)
    external fun callGeneric(fn: Long, argc: Int, typeSpec: String, argsPtr: Long, outPtr: Long)
}
