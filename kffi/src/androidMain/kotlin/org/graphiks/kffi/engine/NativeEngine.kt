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
    external fun callF1P(fn: Long, a1: Long): Float
    external fun callI1I(fn: Long, a1: Int): Long
    external fun callI1P(fn: Long, a1: Long): Long
    external fun callI2PI(fn: Long, a1: Long, a2: Int): Long
    external fun callI2PP(fn: Long, a1: Long, a2: Long): Long
    external fun callI3PIP(fn: Long, a1: Long, a2: Int, a3: Long): Long
    external fun callI3PPP(fn: Long, a1: Long, a2: Long, a3: Long): Long
    external fun callI4PLPL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long): Long
    external fun callL1P(fn: Long, a1: Long): Long
    external fun callL3PLP(fn: Long, a1: Long, a2: Long, a3: Long): Long
    external fun callL3PPP(fn: Long, a1: Long, a2: Long, a3: Long): Long
    external fun callP1P(fn: Long, a1: Long): Long
    external fun callP2PI(fn: Long, a1: Long, a2: Int): Long
    external fun callP2PP(fn: Long, a1: Long, a2: Long): Long
    external fun callP3PLL(fn: Long, a1: Long, a2: Long, a3: Long): Long
    external fun callV1P(fn: Long, a1: Long)
    external fun callV2PI(fn: Long, a1: Long, a2: Int)
    external fun callV3PLP(fn: Long, a1: Long, a2: Long, a3: Long)
    external fun callV3PPI(fn: Long, a1: Long, a2: Long, a3: Int)
    external fun callV3PPL(fn: Long, a1: Long, a2: Long, a3: Long)
    external fun callV4PIII(fn: Long, a1: Long, a2: Int, a3: Int, a4: Int)
    external fun callV4PIIP(fn: Long, a1: Long, a2: Int, a3: Int, a4: Long)
    external fun callV4PPLI(fn: Long, a1: Long, a2: Long, a3: Long, a4: Int)
    external fun callV4PPLL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long)
    external fun callV4PPPP(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long)
    external fun callV5PIIII(fn: Long, a1: Long, a2: Int, a3: Int, a4: Int, a5: Int)
    external fun callV5PIPLL(fn: Long, a1: Long, a2: Int, a3: Long, a4: Long, a5: Long)
    external fun callV5PIPLP(fn: Long, a1: Long, a2: Int, a3: Long, a4: Long, a5: Long)
    external fun callV5PPILL(fn: Long, a1: Long, a2: Long, a3: Int, a4: Long, a5: Long)
    external fun callV5PPLPL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long)
    external fun callV6PIIIII(fn: Long, a1: Long, a2: Int, a3: Int, a4: Int, a5: Int, a6: Int)
    external fun callV6PPIIPL(fn: Long, a1: Long, a2: Long, a3: Int, a4: Int, a5: Long, a6: Long)
    external fun callV6PPLPLI(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long, a6: Int)
    external fun callV6PPLPLL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long, a6: Long)
    external fun callV6PPPLPP(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long, a6: Long)
    external fun callV7PFFFFFF(fn: Long, a1: Long, a2: Float, a3: Float, a4: Float, a5: Float, a6: Float, a7: Float)
    external fun directBufferAddress(buffer: ByteBuffer): Long
    external fun callStructArgL(fn: Long, structSize: Int, structPtr: Long, arg2: Long): Long
    external fun callStructReturn(fn: Long, a: Long, b: Long, structSize: Int, outPtr: Long)
    external fun callGeneric(fn: Long, argc: Int, typeSpec: String, argsPtr: Long, outPtr: Long)
}
