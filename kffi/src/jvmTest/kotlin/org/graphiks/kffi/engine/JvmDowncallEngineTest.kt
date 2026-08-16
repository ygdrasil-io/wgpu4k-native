@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.graphiks.kffi.memoryScope

private object JvmDowncallFixture {
    val libraryPath: String =
        requireNotNull(System.getProperty("kffi.downcall.fixture.library")) {
            "kffi.downcall.fixture.library must point at the compiled downcall fixture"
        }

    fun symbol(name: String): Long {
        // System.load is idempotent for an already-loaded path; loaderLookup()
        // (used by JvmDowncallEngine.resolveSymbol) only sees System.load'ed libraries.
        System.load(libraryPath)
        return JvmDowncallEngine.resolveSymbol(name)
    }
}

class JvmDowncallEngineTest : FreeSpec({

    "empty returns 42" {
        val result = JvmDowncallEngine.callI0(JvmDowncallFixture.symbol("bench_empty"))
        result shouldBe 42L
    }

    "add4 returns sum" {
        val result = JvmDowncallEngine.callI4IIII(JvmDowncallFixture.symbol("bench_add4"), 1, 2, 3, 4)
        result shouldBe 10L
    }

    "add8 returns sum" {
        val result = JvmDowncallEngine.callL8LLLLLLLL(
            JvmDowncallFixture.symbol("bench_add8"),
            1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L,
        )
        result shouldBe 36L
    }

    "pointer round-trip via raw address" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            buffer.writeLong(0xABCD, 0u)
            val returned = JvmDowncallEngine.callP1P(
                JvmDowncallFixture.symbol("bench_roundtrip_ptr"),
                buffer.handler.rawValue,
            )
            returned shouldBe buffer.handler.rawValue
        }
    }
})
