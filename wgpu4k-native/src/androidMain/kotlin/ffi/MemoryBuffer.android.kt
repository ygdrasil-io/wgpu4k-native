@file:OptIn(ExperimentalUnsignedTypes::class)

package ffi

actual class MemoryBuffer actual constructor(actual val handler: NativeAddress, actual val size: ULong) {

    actual fun writeByte(value: Byte, offset: ULong) {
        handler.setByte(offset.toLong(), value)
    }

    actual fun readByte(offset: ULong): Byte {
        return handler.getByte(offset.toLong())
    }

    actual fun writeUByte(value: UByte, offset: ULong) {
        handler.setByte(offset.toLong(), value.toByte())
    }

    actual fun readUByte(offset: ULong): UByte {
        return handler.getByte(offset.toLong()).toUByte()
    }

    actual fun writeShort(value: Short, offset: ULong) {
        handler.setShort(offset.toLong(), value)
    }

    actual fun readShort(offset: ULong): Short {
        return handler.getShort(offset.toLong())
    }

    actual fun writeUShort(value: UShort, offset: ULong) {
        handler.setShort(offset.toLong(), value.toShort())
    }

    actual fun readUShort(offset: ULong): UShort {
        return handler.getShort(offset.toLong()).toUShort()
    }

    actual fun writeInt(value: Int, offset: ULong) {
        handler.setInt(offset.toLong(), value)
    }

    actual fun readInt(offset: ULong): Int {
        return handler.getInt(offset.toLong())
    }

    actual fun writeUInt(value: UInt, offset: ULong) {
        handler.setInt(offset.toLong(), value.toInt())
    }

    actual fun readUInt(offset: ULong): UInt {
        return handler.getInt(offset.toLong()).toUInt()
    }

    actual fun writeLong(value: Long, offset: ULong) {
        handler.setLong(offset.toLong(), value)
    }

    actual fun readLong(offset: ULong): Long {
        return handler.getLong(offset.toLong())
    }

    actual fun writeULong(value: ULong, offset: ULong) {
        handler.setLong(offset.toLong(), value.toLong())
    }

    actual fun readULong(offset: ULong): ULong {
        return handler.getLong(offset.toLong()).toULong()
    }

    actual fun writeFloat(value: Float, offset: ULong) {
        handler.setFloat(offset.toLong(), value)
    }

    actual fun readFloat(offset: ULong): Float {
        return handler.getFloat(offset.toLong())
    }

    actual fun writeDouble(value: Double, offset: ULong) {
        handler.setDouble(offset.toLong(), value)
    }

    actual fun readDouble(offset: ULong): Double {
        return handler.getDouble(offset.toLong())
    }

    actual fun writePointer(value: NativeAddress, offset: ULong) {
        handler.setPointer(offset.toLong(), value)
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        return handler.getPointer(offset.toLong())
    }

    actual fun writeBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        array.forEachIndexed { index, byte ->
            handler.setByte(index + bufferOffset.toLong(), byte)
        }
    }

    actual fun readBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        array.forEachIndexed { index, byte ->
            array[index] = handler.getByte(index + bufferOffset.toLong())
        }
    }

    actual fun writeUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun writeDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }


}