@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Buffer borné sur une adresse brute.
 *
 * Décision I2-(a) : le scope d'arène/session vit ici (pas dans NativeAddress).
 * - Créé via [MemoryAllocator] : porte le segment scopé de l'arène → use-after-close
 *   lève IllegalStateException (garantie JVM préservée).
 * - Créé depuis une adresse brute (MemoryBuffer(addr, size)) : sans scope, accès post-close
 *   non détecté (UB documenté, aligné sur Android/native).
 */
actual class MemoryBuffer actual constructor(
    handler: NativeAddress,
    actual val size: ULong,
) {
    actual val handler: NativeAddress = handler

    /**
     * Segment scopé hérité de l'arène, ou null si créé depuis une adresse brute.
     * var uniquement car un constructeur secondaire ne peut pas initialiser un val
     * laissé non initialisé par le constructeur primaire ; écrit une seule fois,
     * à la construction (effectivement val).
     */
    private var scopedSegment: MemorySegment? = null

    internal constructor(handler: NativeAddress, size: ULong, scopedSegment: MemorySegment) : this(handler, size) {
        this.scopedSegment = scopedSegment
    }

    private fun segment(): MemorySegment =
        scopedSegment ?: handler.toJvmSegment(size.toLong())

    private fun writeArray(destinationOffset: ULong, source: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val sourceOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        write(destinationOffset, sourceOffset, source, bytesToCopy)
    }

    private fun write(destinationOffset: ULong, sourceOffset: ULong, source: MemorySegment, bytesToCopy: ULong) {
        val sourceBytes = source.byteSize().toULong()
        val destinationBytes = size

        require(destinationBytes >= (destinationOffset + bytesToCopy)) { "Out of destination bounds" }
        require(sourceOffset + bytesToCopy <= sourceBytes) { "Out of source bounds" }

        segment().asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(source.asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    private fun readArray(sourceOffset: ULong, destination: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val destinationOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        read(sourceOffset, destinationOffset, destination, bytesToCopy)
    }

    private fun read(sourceOffset: ULong, destinationOffset: ULong, destination: MemorySegment, bytesToCopy: ULong) {
        val destinationBytes = destination.byteSize().toULong()
        val sourceBytes = size

        require(destinationBytes >= (destinationOffset + bytesToCopy)) { "Out of destination bounds" }
        require(sourceOffset + bytesToCopy <= sourceBytes) { "Out of source bounds" }

        destination.asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(segment().asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    actual fun writeByte(value: Byte, offset: ULong) { segment().set(ValueLayout.JAVA_BYTE, offset.toLong(), value) }
    actual fun readByte(offset: ULong): Byte = segment().get(ValueLayout.JAVA_BYTE, offset.toLong())
    actual fun writeUByte(value: UByte, offset: ULong) { segment().set(ValueLayout.JAVA_BYTE, offset.toLong(), value.toByte()) }
    actual fun readUByte(offset: ULong): UByte = segment().get(ValueLayout.JAVA_BYTE, offset.toLong()).toUByte()
    actual fun writeShort(value: Short, offset: ULong) { segment().set(ValueLayout.JAVA_SHORT, offset.toLong(), value) }
    actual fun readShort(offset: ULong): Short = segment().get(ValueLayout.JAVA_SHORT, offset.toLong())
    actual fun writeUShort(value: UShort, offset: ULong) { segment().set(ValueLayout.JAVA_SHORT, offset.toLong(), value.toShort()) }
    actual fun readUShort(offset: ULong): UShort = segment().get(ValueLayout.JAVA_SHORT, offset.toLong()).toUShort()
    actual fun writeInt(value: Int, offset: ULong) { segment().set(ValueLayout.JAVA_INT, offset.toLong(), value) }
    actual fun readInt(offset: ULong): Int = segment().get(ValueLayout.JAVA_INT, offset.toLong())
    actual fun writeUInt(value: UInt, offset: ULong) { segment().set(ValueLayout.JAVA_INT, offset.toLong(), value.toInt()) }
    actual fun readUInt(offset: ULong): UInt = segment().get(ValueLayout.JAVA_INT, offset.toLong()).toUInt()
    actual fun writeLong(value: Long, offset: ULong) { segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value) }
    actual fun readLong(offset: ULong): Long = segment().get(ValueLayout.JAVA_LONG, offset.toLong())
    actual fun writeULong(value: ULong, offset: ULong) { segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value.toLong()) }
    actual fun readULong(offset: ULong): ULong = segment().get(ValueLayout.JAVA_LONG, offset.toLong()).toULong()
    actual fun writeFloat(value: Float, offset: ULong) { segment().set(ValueLayout.JAVA_FLOAT, offset.toLong(), value) }
    actual fun readFloat(offset: ULong): Float = segment().get(ValueLayout.JAVA_FLOAT, offset.toLong())
    actual fun writeDouble(value: Double, offset: ULong) { segment().set(ValueLayout.JAVA_DOUBLE, offset.toLong(), value) }
    actual fun readDouble(offset: ULong): Double = segment().get(ValueLayout.JAVA_DOUBLE, offset.toLong())

    actual fun writePointer(value: NativeAddress, offset: ULong) {
        segment().set(ValueLayout.ADDRESS, offset.toLong(), value.toJvmSegmentOrNull() ?: MemorySegment.NULL)
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        val raw = segment().get(ValueLayout.ADDRESS, offset.toLong())
        return NativeAddress(if (raw == MemorySegment.NULL) 0L else raw.address())
    }

    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    actual fun writeUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    actual fun readUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    actual fun writeShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    actual fun readShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    actual fun writeUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    actual fun readUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    actual fun writeInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    actual fun readInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    actual fun writeUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    actual fun readUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    actual fun writeLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    actual fun readLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    actual fun writeULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    actual fun readULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    actual fun writeFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    actual fun readFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    actual fun writeDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
    actual fun readDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
}
