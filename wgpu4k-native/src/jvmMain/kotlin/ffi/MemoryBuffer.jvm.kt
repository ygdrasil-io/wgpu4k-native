@file:OptIn(ExperimentalUnsignedTypes::class)

package ffi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class MemoryBuffer actual constructor(handler: NativeAddress, actual val size: ULong) {

    actual val handler: NativeAddress = handler.handler.reinterpret(size.toLong()).let(::NativeAddress)
    val memorySegment: MemorySegment = handler.handler



    private fun writeArray(destinationOffset: ULong, source: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val sourceOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size

        write(destinationOffset, sourceOffset, source, bytesToCopy)
    }

    private fun write(
        destinationOffset: ULong,
        sourceOffset: ULong,
        source: MemorySegment,
        bytesToCopy: ULong,
    ) {
        val sourceBytes = source.byteSize().toULong()
        val destinationBytes = memorySegment.byteSize().toULong()

        if (destinationBytes < (destinationOffset + bytesToCopy)) error("Out of destination bounds")
        if (sourceOffset + bytesToCopy > sourceBytes) error("Out of source bounds")

        memorySegment.asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(source.asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }


    private fun readArray(sourceOffset: ULong, destination: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val destinationOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size

        read(sourceOffset, destinationOffset, destination, bytesToCopy)
    }

    private fun read(sourceOffset: ULong, destinationOffset: ULong, destination: MemorySegment, bytesToCopy: ULong) {
        val destinationBytes = destination.byteSize().toULong()
        val sourceBytes = memorySegment.byteSize().toULong()

        if (destinationBytes < (destinationOffset + bytesToCopy)) error("Out of destination bounds")
        if (sourceOffset + bytesToCopy > sourceBytes) error("Out of source bounds")

        destination.asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(memorySegment.asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    actual fun writeByte(value: Byte, offset: ULong) {
        memorySegment.set(ValueLayout.JAVA_BYTE, offset.toLong(), value)
    }

    actual fun readByte(offset: ULong): Byte {
        return memorySegment.get(ValueLayout.JAVA_BYTE, offset.toLong())
    }

    actual fun writeUByte(value: UByte, offset: ULong) {
        memorySegment.set(ValueLayout.JAVA_BYTE, offset.toLong(), value.toByte())
    }

    actual fun readUByte(offset: ULong): UByte {
        return memorySegment.get(ValueLayout.JAVA_BYTE, offset.toLong()).toUByte()
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

    actual fun writeString(value: String, offset: ULong) {
    }

    actual fun readString(offset: ULong): String {
        TODO("Not yet implemented")
    }

    actual fun writeBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    }

    actual fun readBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    }

    actual fun writeUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    }

    actual fun readUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    }

    actual fun writeShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    }

    actual fun readShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    }

    actual fun writeUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    }

    actual fun readUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    }

    actual fun writeChars(
        array: CharArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Char.SIZE_BYTES)
    }

    actual fun readChars(
        array: CharArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Char.SIZE_BYTES)
    }

    actual fun writeInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    }

    actual fun readInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    }

    actual fun writeUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    }

    actual fun readUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    }

    actual fun writeLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    }

    actual fun readLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    }

    actual fun writeULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    }

    actual fun readULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    }

    actual fun writeFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    }

    actual fun readFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    }

    actual fun writeDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
    }

    actual fun readDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
    }


}