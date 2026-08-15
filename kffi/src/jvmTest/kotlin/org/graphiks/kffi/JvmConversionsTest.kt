package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JvmConversionsTest : FreeSpec({
    "a MemoryBuffer segment round-trips through its raw address" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(64u)
            val segment = buffer.toJvmSegment()
            segment.byteSize() shouldBe 64L
            val again = NativeAddress(segment.address()).let { MemoryBuffer(it, 64u) }
            again.writeLong(0xCAFE, 0u)
            buffer.readLong(0u) shouldBe 0xCAFE
        }
    }

    "rawValue 0 maps to null segment" {
        NativeAddress(0L).toJvmSegmentOrNull() shouldBe null
    }
})
