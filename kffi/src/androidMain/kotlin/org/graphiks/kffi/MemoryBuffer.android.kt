@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

actual class MemoryBuffer actual constructor(
    actual val handler: NativeAddress,
    actual val size: ULong,
) {
    private val unsafe = AndroidUnsafe.get()
    private val base = handler.rawValue

    private fun boundsCheck(offset: ULong, width: Long) {
        require(offset.toLong() + width <= size.toLong()) {
            "Out of destination bounds: offset=$offset width=$width size=$size"
        }
    }

    actual fun writeByte(value: Byte, offset: ULong) { boundsCheck(offset, 1); unsafe.putByte(base + offset.toLong(), value) }
    actual fun readByte(offset: ULong): Byte { boundsCheck(offset, 1); return unsafe.getByte(base + offset.toLong()) }
    actual fun writeUByte(value: UByte, offset: ULong) { writeByte(value.toByte(), offset) }
    actual fun readUByte(offset: ULong): UByte = readByte(offset).toUByte()
    actual fun writeShort(value: Short, offset: ULong) { boundsCheck(offset, 2); unsafe.putShort(base + offset.toLong(), value) }
    actual fun readShort(offset: ULong): Short { boundsCheck(offset, 2); return unsafe.getShort(base + offset.toLong()) }
    actual fun writeUShort(value: UShort, offset: ULong) { writeShort(value.toShort(), offset) }
    actual fun readUShort(offset: ULong): UShort = readShort(offset).toUShort()
    actual fun writeInt(value: Int, offset: ULong) { boundsCheck(offset, 4); unsafe.putInt(base + offset.toLong(), value) }
    actual fun readInt(offset: ULong): Int { boundsCheck(offset, 4); return unsafe.getInt(base + offset.toLong()) }
    actual fun writeUInt(value: UInt, offset: ULong) { writeInt(value.toInt(), offset) }
    actual fun readUInt(offset: ULong): UInt = readInt(offset).toUInt()
    actual fun writeLong(value: Long, offset: ULong) { boundsCheck(offset, 8); unsafe.putLong(base + offset.toLong(), value) }
    actual fun readLong(offset: ULong): Long { boundsCheck(offset, 8); return unsafe.getLong(base + offset.toLong()) }
    actual fun writeULong(value: ULong, offset: ULong) { writeLong(value.toLong(), offset) }
    actual fun readULong(offset: ULong): ULong = readLong(offset).toULong()
    actual fun writeFloat(value: Float, offset: ULong) { boundsCheck(offset, 4); unsafe.putFloat(base + offset.toLong(), value) }
    actual fun readFloat(offset: ULong): Float { boundsCheck(offset, 4); return unsafe.getFloat(base + offset.toLong()) }
    actual fun writeDouble(value: Double, offset: ULong) { boundsCheck(offset, 8); unsafe.putDouble(base + offset.toLong(), value) }
    actual fun readDouble(offset: ULong): Double { boundsCheck(offset, 8); return unsafe.getDouble(base + offset.toLong()) }
    actual fun writePointer(value: NativeAddress, offset: ULong) {
        boundsCheck(offset, 8)
        if (unsafe.addressSize() == 8) unsafe.putLong(base + offset.toLong(), value.rawValue)
        else unsafe.putInt(base + offset.toLong(), value.rawValue.toInt())
    }
    actual fun readPointer(offset: ULong): NativeAddress {
        boundsCheck(offset, 8)
        val raw = if (unsafe.addressSize() == 8) unsafe.getLong(base + offset.toLong())
            else unsafe.getInt(base + offset.toLong()).toLong()
        return NativeAddress(raw)
    }

    private fun writeArray(
        arrayBytes: Int, elementSize: Int, array: Any,
        arrayIndex: ULong, bufferOffset: ULong, size: ULong,
    ) {
        val bytes = size.toLong() * elementSize
        val dest = bufferOffset.toLong()
        val src = arrayIndex.toLong() * elementSize
        require(dest + bytes <= this.size.toLong()) { "Out of destination bounds" }
        require(src + bytes <= arrayBytes.toLong()) { "Out of source bounds" }
        unsafe.copyMemory(array, 16L + src, null, base + dest, bytes)
    }

    private fun readArray(
        arrayBytes: Int, elementSize: Int, array: Any,
        arrayIndex: ULong, bufferOffset: ULong, size: ULong,
    ) {
        val bytes = size.toLong() * elementSize
        val src = bufferOffset.toLong()
        val dest = arrayIndex.toLong() * elementSize
        require(src + bytes <= this.size.toLong()) { "Out of source bounds" }
        require(dest + bytes <= arrayBytes.toLong()) { "Out of destination bounds" }
        unsafe.copyMemory(null, base + src, array, 16L + dest, bytes)
    }

    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun writeUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun readUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun writeShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun readShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun writeUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun readUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun writeInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun writeULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun writeFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
}
