package org.graphiks.kffi

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoryMarshalingDeviceTest {

    @Test
    fun bulkArrayRoundTripWorksOnArt() {
        org.graphiks.kffi.memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(64uL)
            val source = IntArray(8) { it * 3 }
            buffer.writeInts(source)
            val out = IntArray(8)
            buffer.readInts(out)
            assertEquals(0, out[0])
            assertEquals(21, out[7])
        }
    }

    @Test
    fun cStringRoundTripWorksOnArt() {
        org.graphiks.kffi.memoryScope { allocator ->
            val cstr = allocator.allocateFrom("héllo device")
            assertEquals("héllo device", cstr.toKString())
        }
    }
}
