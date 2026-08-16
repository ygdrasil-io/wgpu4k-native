@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.graphiks.kffi.memoryScope

internal object JvmDowncallFixture {
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

    // M5.3 : formes de l'union des signatures wgpu.

    "callI2PP adds through two pointers" {
        memoryScope { scope ->
            val a = scope.allocateBuffer(8u).apply { writeLong(20, 0u) }
            val b = scope.allocateBuffer(8u).apply { writeLong(22, 0u) }
            JvmDowncallEngine.callI2PP(
                JvmDowncallFixture.symbol("bench_add_indirect"),
                a.handler.rawValue,
                b.handler.rawValue,
            ) shouldBe 42L
        }
    }

    "callI2PI combines pointer value and int arg" {
        memoryScope { scope ->
            val p = scope.allocateBuffer(8u).apply { writeLong(40, 0u) }
            JvmDowncallEngine.callI2PI(
                JvmDowncallFixture.symbol("bench_has_feature"),
                p.handler.rawValue,
                2,
            ) shouldBe 42L
        }
    }

    "callL1P loads a 64-bit value through a pointer" {
        memoryScope { scope ->
            val p = scope.allocateBuffer(8u).apply { writeLong(42, 0u) }
            JvmDowncallEngine.callL1P(
                JvmDowncallFixture.symbol("bench_load_u64"),
                p.handler.rawValue,
            ) shouldBe 42L
        }
    }

    "callI4PLPL mixes pointers and longs" {
        memoryScope { scope ->
            val p1 = scope.allocateBuffer(64u).apply { writeLong(10, 0u); writeLong(20, 8u) }
            val p2 = scope.allocateBuffer(8u).apply { writeLong(30, 0u) }
            JvmDowncallEngine.callI4PLPL(
                JvmDowncallFixture.symbol("bench_read_range"),
                p1.handler.rawValue,
                1L,
                p2.handler.rawValue,
                2L,
            ) shouldBe 52L
        }
    }

    "callV2PI writes an int through a pointer" {
        memoryScope { scope ->
            val p = scope.allocateBuffer(8u)
            JvmDowncallEngine.callV2PI(
                JvmDowncallFixture.symbol("bench_set_flag"),
                p.handler.rawValue,
                42,
            )
            p.readLong(0u) shouldBe 42L
        }
    }

    "callV3PLP passes pointer-long-pointer" {
        memoryScope { scope ->
            val queue = scope.allocateBuffer(8u).apply { writeLong(40, 0u) }
            val commands = scope.allocateBuffer(8u)
            JvmDowncallEngine.callV3PLP(
                JvmDowncallFixture.symbol("bench_execute_bundles"),
                queue.handler.rawValue,
                2L,
                commands.handler.rawValue,
            )
            commands.readLong(0u) shouldBe 42L
        }
    }

    "callV7PFFFFFF passes a pointer and six floats" {
        memoryScope { scope ->
            val encoder = scope.allocateBuffer(8u)
            JvmDowncallEngine.callV7PFFFFFF(
                JvmDowncallFixture.symbol("bench_set_viewport"),
                encoder.handler.rawValue,
                1f, 2f, 3f, 4f, 0.1f, 0.9f,
            )
        }
        memoryScope { scope ->
            val index = scope.allocateBuffer(4u)
            index.writeInt(3, 0u)
            JvmDowncallEngine.callF1P(
                JvmDowncallFixture.symbol("bench_viewport_get"),
                index.handler.rawValue,
            ) shouldBe 4f
        }
    }

    "callV1I and callI0 round-trip a level value" {
        JvmDowncallEngine.callV1I(JvmDowncallFixture.symbol("bench_set_level"), 7)
        JvmDowncallEngine.callI0(JvmDowncallFixture.symbol("bench_get_level")) shouldBe 7L
    }
})
