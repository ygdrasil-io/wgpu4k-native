package org.graphiks.kffi.engine

object NativeEngine {
    init {
        System.loadLibrary("kffi")
    }

    external fun resolveSymbol(name: String): Long
    external fun callV0(fn: Long)
    external fun callI0(fn: Long): Long
    external fun callI4IIII(fn: Long, a: Int, b: Int, c: Int, d: Int): Long
}
