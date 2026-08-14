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

    @Test
    fun unsignedArrayRoundTripsWorkOnArt() {
        org.graphiks.kffi.memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(64uL)
            val ub = ubyteArrayOf(1u, 2u, 255u)
            buffer.writeUBytes(ub)
            val ubOut = UByteArray(3)
            buffer.readUBytes(ubOut)
            assertEquals(1u, ubOut[0])
            assertEquals(255u, ubOut[2])

            val ui = uintArrayOf(10u, 20u, 30u)
            buffer.writeUInts(ui)
            val uiOut = UIntArray(3)
            buffer.readUInts(uiOut)
            assertEquals(10u, uiOut[0])
            assertEquals(30u, uiOut[2])

            val ul = ulongArrayOf(100uL, 200uL)
            buffer.writeULongs(ul)
            val ulOut = ULongArray(2)
            buffer.readULongs(ulOut)
            assertEquals(100uL, ulOut[0])
            assertEquals(200uL, ulOut[1])
        }
    }
}
