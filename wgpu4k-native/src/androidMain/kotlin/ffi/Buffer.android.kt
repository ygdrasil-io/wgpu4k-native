package ffi

actual class MemoryBuffer actual constructor(actual val handler: NativeAddress, actual val size: ULong) {

    actual fun writeByte(value: Byte, offset: ULong) {
        handler.setByte(offset.toLong(), value)
    }

    actual fun readByte(offset: ULong): Byte {
        return handler.getByte(offset.toLong())
    }

    actual fun writeUByte(value: UByte, offset: ULong) {
    }

    actual fun readUByte(offset: ULong): UByte {
        TODO("Not yet implemented")
    }

    actual fun writeChar(value: Char, offset: ULong) {
    }

    actual fun readChar(offset: ULong): Char {
        TODO("Not yet implemented")
    }

    actual fun writeShort(value: Short, offset: ULong) {
    }

    actual fun readShort(offset: ULong): Short {
        TODO("Not yet implemented")
    }

    actual fun writeUShort(value: UShort, offset: ULong) {
    }

    actual fun readUShort(offset: ULong): UShort {
        TODO("Not yet implemented")
    }

    actual fun writeInt(value: Int, offset: ULong) {
    }

    actual fun readInt(offset: ULong): Int {
        TODO("Not yet implemented")
    }

    actual fun writeUInt(value: UInt, offset: ULong) {
    }

    actual fun readUInt(offset: ULong): UInt {
        TODO("Not yet implemented")
    }

    actual fun writeLong(value: Long, offset: ULong) {
    }

    actual fun readLong(offset: ULong): Long {
        TODO("Not yet implemented")
    }

    actual fun writeULong(value: ULong, offset: ULong) {
    }

    actual fun readULong(offset: ULong): ULong {
        TODO("Not yet implemented")
    }

    actual fun writeFloat(value: Float, offset: ULong) {
    }

    actual fun readFloat(offset: ULong): Float {
        TODO("Not yet implemented")
    }

    actual fun writeDouble(value: Double, offset: ULong) {
    }

    actual fun readDouble(offset: ULong): Double {
        TODO("Not yet implemented")
    }

    actual fun writePointer(value: NativeAddress, offset: ULong) {
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        TODO("Not yet implemented")
    }

    actual fun writeBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
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

    actual fun writeChars(
        array: CharArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
    }

    actual fun readChars(
        array: CharArray,
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