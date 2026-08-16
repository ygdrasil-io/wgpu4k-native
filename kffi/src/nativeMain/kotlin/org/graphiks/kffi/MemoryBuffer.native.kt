@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kffi

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.ShortVar
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.ULongVar
import kotlinx.cinterop.UShortVar
import kotlinx.cinterop.get
import kotlinx.cinterop.pointed
import kotlinx.cinterop.set
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.value

/**
 * Constante compilée : les distributions native sont figées à la compilation
 * (I3, P3) — la valeur du flag runtime est ignorée au profit de cette constante.
 * Basculer à la compilation : éditer la constante `KFFI_NATIVE_UNSAFE` en tête
 * de ce fichier puis recompiler le module.
 */
private const val KFFI_NATIVE_UNSAFE: Boolean = false

actual class MemoryBuffer actual constructor(
    actual val handler: NativeAddress,
    actual val size: ULong,
    unsafe: Boolean,
) {
    // Le flag runtime est ignoré au profit de la constante build-time KFFI_NATIVE_UNSAFE :
    // les distributions native sont figées à la compilation (divergence documentée, I3/P3).
    private val unsafe: Boolean = KFFI_NATIVE_UNSAFE

    private fun <T : CPointed> getPointerAtOffset(offset: ULong): CPointer<T> {
        return (handler.rawValue + offset.toLong()).toCPointer()
            ?: error("fail to get pointer at offset $offset")
    }

    private fun boundsCheck(offset: ULong, width: Long) {
        if (unsafe) return
        if (offset >= size || offset + width.toULong() > size) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer access out of bounds: offset=$offset width=$width size=$size",
            )
        }
    }

    actual fun writeByte(value: Byte, offset: ULong) {
        boundsCheck(offset, 1L)
        getPointerAtOffset<ByteVar>(offset).pointed.value = value
    }

    actual fun readByte(offset: ULong): Byte {
        boundsCheck(offset, 1L)
        return getPointerAtOffset<ByteVar>(offset).pointed.value
    }

    actual fun writeUByte(value: UByte, offset: ULong) {
        boundsCheck(offset, 1L)
        getPointerAtOffset<UByteVar>(offset).pointed.value = value
    }

    actual fun readUByte(offset: ULong): UByte {
        boundsCheck(offset, 1L)
        return getPointerAtOffset<UByteVar>(offset).pointed.value
    }

    actual fun writeShort(value: Short, offset: ULong) {
        boundsCheck(offset, 2L)
        getPointerAtOffset<ShortVar>(offset).pointed.value = value
    }

    actual fun readShort(offset: ULong): Short {
        boundsCheck(offset, 2L)
        return getPointerAtOffset<ShortVar>(offset).pointed.value
    }

    actual fun writeUShort(value: UShort, offset: ULong) {
        boundsCheck(offset, 2L)
        getPointerAtOffset<UShortVar>(offset).pointed.value = value
    }

    actual fun readUShort(offset: ULong): UShort {
        boundsCheck(offset, 2L)
        return getPointerAtOffset<UShortVar>(offset).pointed.value
    }

    actual fun writeInt(value: Int, offset: ULong) {
        boundsCheck(offset, 4L)
        getPointerAtOffset<IntVar>(offset).pointed.value = value
    }

    actual fun readInt(offset: ULong): Int {
        boundsCheck(offset, 4L)
        return getPointerAtOffset<IntVar>(offset).pointed.value
    }

    actual fun writeUInt(value: UInt, offset: ULong) {
        boundsCheck(offset, 4L)
        getPointerAtOffset<UIntVar>(offset).pointed.value = value
    }

    actual fun readUInt(offset: ULong): UInt {
        boundsCheck(offset, 4L)
        return getPointerAtOffset<UIntVar>(offset).pointed.value
    }

    actual fun writeLong(value: Long, offset: ULong) {
        boundsCheck(offset, 8L)
        getPointerAtOffset<LongVar>(offset).pointed.value = value
    }

    actual fun readLong(offset: ULong): Long {
        boundsCheck(offset, 8L)
        return getPointerAtOffset<LongVar>(offset).pointed.value
    }

    actual fun writeULong(value: ULong, offset: ULong) {
        boundsCheck(offset, 8L)
        getPointerAtOffset<ULongVar>(offset).pointed.value = value
    }

    actual fun readULong(offset: ULong): ULong {
        boundsCheck(offset, 8L)
        return getPointerAtOffset<ULongVar>(offset).pointed.value
    }

    actual fun writeFloat(value: Float, offset: ULong) {
        boundsCheck(offset, 4L)
        getPointerAtOffset<FloatVar>(offset).pointed.value = value
    }

    actual fun readFloat(offset: ULong): Float {
        boundsCheck(offset, 4L)
        return getPointerAtOffset<FloatVar>(offset).pointed.value
    }

    actual fun writeDouble(value: Double, offset: ULong) {
        boundsCheck(offset, 8L)
        getPointerAtOffset<DoubleVar>(offset).pointed.value = value
    }

    actual fun readDouble(offset: ULong): Double {
        boundsCheck(offset, 8L)
        return getPointerAtOffset<DoubleVar>(offset).pointed.value
    }

    actual fun writePointer(value: NativeAddress, offset: ULong) {
        boundsCheck(offset, 8L)
        getPointerAtOffset<LongVar>(offset).pointed.value = value.rawValue
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        boundsCheck(offset, 8L)
        return getPointerAtOffset<LongVar>(offset).pointed.value.toCPointer<COpaque>()
            ?.let(NativeAddress::fromPointer)
            ?: error("fail to read pointer at offset $offset")
    }

    actual fun writeBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ByteVar>())

        val buffer = getPointerAtOffset<ByteVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readBytes(
        array: ByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ByteVar>())

        val buffer = getPointerAtOffset<ByteVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UByteVar>())

        val buffer = getPointerAtOffset<UByteVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readUBytes(
        array: UByteArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UByteVar>())

        val buffer = getPointerAtOffset<UByteVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ShortVar>())

        val buffer = getPointerAtOffset<ShortVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readShorts(
        array: ShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ShortVar>())

        val buffer = getPointerAtOffset<ShortVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UShortVar>())

        val buffer = getPointerAtOffset<UShortVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readUShorts(
        array: UShortArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UShortVar>())

        val buffer = getPointerAtOffset<UShortVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }


    actual fun writeInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<IntVar>())

        val buffer = getPointerAtOffset<IntVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readInts(
        array: IntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<IntVar>())

        val buffer = getPointerAtOffset<IntVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UIntVar>())

        val buffer = getPointerAtOffset<UIntVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readUInts(
        array: UIntArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<UIntVar>())

        val buffer = getPointerAtOffset<UIntVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<LongVar>())

        val buffer = getPointerAtOffset<LongVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readLongs(
        array: LongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<LongVar>())

        val buffer = getPointerAtOffset<LongVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ULongVar>())

        val buffer = getPointerAtOffset<ULongVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readULongs(
        array: ULongArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<ULongVar>())

        val buffer = getPointerAtOffset<ULongVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<FloatVar>())

        val buffer = getPointerAtOffset<FloatVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readFloats(
        array: FloatArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<FloatVar>())

        val buffer = getPointerAtOffset<FloatVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    actual fun writeDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<DoubleVar>())

        val buffer = getPointerAtOffset<DoubleVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            buffer[index] = array[index + arrayIndex.toInt()]
        }
    }

    actual fun readDoubles(
        array: DoubleArray,
        arrayIndex: ULong,
        bufferOffset: ULong,
        size: ULong
    ) {
        boundsCheck(bufferOffset, size, arrayIndex, array.size, sizeOf<DoubleVar>())

        val buffer = getPointerAtOffset<DoubleVar>(bufferOffset)
        (0 until size.toInt()).forEach { index ->
            array[index + arrayIndex.toInt()] = buffer[index]
        }
    }

    private fun boundsCheck(
        bufferOffset: ULong,
        size: ULong,
        arrayIndex: ULong,
        arraySize: Int,
        elementSizeInByte: Long
    ) {
        if (unsafe) return
        val bufferEnd = bufferOffset + size * elementSizeInByte.toULong()
        if (bufferEnd > this.size) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array access out of bounds: bufferOffset=$bufferOffset bytes=${size * elementSizeInByte.toULong()} size=${this.size}",
            )
        }
        val arrayEnd = arrayIndex + size
        if (arrayEnd > arraySize.toULong()) {
            throw IndexOutOfBoundsException(
                "Array overflow: trying to access $arrayEnd but array size is $arraySize",
            )
        }
    }
}
