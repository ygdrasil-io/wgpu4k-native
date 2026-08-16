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
 *
 * Mode [unsafe] (I3) : élimine les bornes-check (tout accès hors bornes devient UB) ;
 * l'accès passe par sun.misc.Unsafe sur l'adresse brute. Décision M1.1 : la garde
 * de close I2-(a) est conservée en unsafe via le flag `closed` porté par l'allocateur
 * (1 load volatil au lieu de scope().isAlive, 2 appels FFM) ; seules les bornes sont sautées.
 */
actual class MemoryBuffer actual constructor(
    handler: NativeAddress,
    actual val size: ULong,
    unsafe: Boolean,
) {
    actual val handler: NativeAddress = handler

    private val unsafe: Boolean = unsafe

    /**
     * Segment scopé hérité de l'arène, ou null si créé depuis une adresse brute.
     * var uniquement car un constructeur secondaire ne peut pas initialiser un val
     * laissé non initialisé par le constructeur primaire ; écrit une seule fois,
     * à la construction (effectivement val).
     */
    private var scopedSegment: MemorySegment? = null

    /** Flag de fermeture porté par l'allocateur (1 load volatil vs scope().isAlive). */
    private var allocatorClosed: java.util.concurrent.atomic.AtomicBoolean? = null

    internal constructor(
        handler: NativeAddress,
        size: ULong,
        scopedSegment: MemorySegment,
        unsafe: Boolean,
        allocatorClosed: java.util.concurrent.atomic.AtomicBoolean,
    ) : this(handler, size, unsafe) {
        this.scopedSegment = scopedSegment
        this.allocatorClosed = allocatorClosed
    }

    /** Segment dérivé de l'adresse brute pour les buffers non scopés (créé une seule fois). */
    private val fallbackSegment: MemorySegment by lazy { handler.toJvmSegment(size.toLong()) }

    private fun segment(): MemorySegment =
        scopedSegment ?: fallbackSegment

    /** Vérifie la vie du scope (I2-a) même en mode unsafe, puis retourne l'adresse brute. */
    private fun rawAddress(): Long {
        allocatorClosed?.let { closed ->
            if (closed.get()) throw IllegalStateException("MemoryBuffer has been closed")
            return handler.rawValue
        }
        val scope = scopedSegment?.scope()
        if (scope != null && !scope.isAlive) {
            throw IllegalStateException("MemoryBuffer has been closed")
        }
        return handler.rawValue
    }

    private fun boundsCheck(offset: ULong, width: Long) {
        if (unsafe) return
        if (offset >= size || offset + width.toULong() > size) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer access out of bounds: offset=$offset width=$width size=$size",
            )
        }
    }

    // ------------------------------------------------------------------
    // Accesseurs scalaires — deux chemins (sûr = FFM, unsafe = Unsafe)
    // ------------------------------------------------------------------

    actual fun writeByte(value: Byte, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putByte(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 1L); segment().set(ValueLayout.JAVA_BYTE, offset.toLong(), value) }
    }

    actual fun readByte(offset: ULong): Byte {
        if (unsafe) return JvmUnsafeAccess.getByte(rawAddress(), offset.toLong())
        boundsCheck(offset, 1L); return segment().get(ValueLayout.JAVA_BYTE, offset.toLong())
    }

    actual fun writeUByte(value: UByte, offset: ULong) { writeByte(value.toByte(), offset) }
    actual fun readUByte(offset: ULong): UByte = readByte(offset).toUByte()

    actual fun writeShort(value: Short, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putShort(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 2L); segment().set(ValueLayout.JAVA_SHORT, offset.toLong(), value) }
    }

    actual fun readShort(offset: ULong): Short {
        if (unsafe) return JvmUnsafeAccess.getShort(rawAddress(), offset.toLong())
        boundsCheck(offset, 2L); return segment().get(ValueLayout.JAVA_SHORT, offset.toLong())
    }

    actual fun writeUShort(value: UShort, offset: ULong) { writeShort(value.toShort(), offset) }
    actual fun readUShort(offset: ULong): UShort = readShort(offset).toUShort()

    actual fun writeInt(value: Int, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putInt(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 4L); segment().set(ValueLayout.JAVA_INT, offset.toLong(), value) }
    }

    actual fun readInt(offset: ULong): Int {
        if (unsafe) return JvmUnsafeAccess.getInt(rawAddress(), offset.toLong())
        boundsCheck(offset, 4L); return segment().get(ValueLayout.JAVA_INT, offset.toLong())
    }

    actual fun writeUInt(value: UInt, offset: ULong) { writeInt(value.toInt(), offset) }
    actual fun readUInt(offset: ULong): UInt = readInt(offset).toUInt()

    actual fun writeLong(value: Long, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putLong(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 8L); segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value) }
    }

    actual fun readLong(offset: ULong): Long {
        if (unsafe) return JvmUnsafeAccess.getLong(rawAddress(), offset.toLong())
        boundsCheck(offset, 8L); return segment().get(ValueLayout.JAVA_LONG, offset.toLong())
    }

    actual fun writeULong(value: ULong, offset: ULong) { writeLong(value.toLong(), offset) }
    actual fun readULong(offset: ULong): ULong = readLong(offset).toULong()

    actual fun writeFloat(value: Float, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putFloat(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 4L); segment().set(ValueLayout.JAVA_FLOAT, offset.toLong(), value) }
    }

    actual fun readFloat(offset: ULong): Float {
        if (unsafe) return JvmUnsafeAccess.getFloat(rawAddress(), offset.toLong())
        boundsCheck(offset, 4L); return segment().get(ValueLayout.JAVA_FLOAT, offset.toLong())
    }

    actual fun writeDouble(value: Double, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putDouble(rawAddress(), offset.toLong(), value)
        else { boundsCheck(offset, 8L); segment().set(ValueLayout.JAVA_DOUBLE, offset.toLong(), value) }
    }

    actual fun readDouble(offset: ULong): Double {
        if (unsafe) return JvmUnsafeAccess.getDouble(rawAddress(), offset.toLong())
        boundsCheck(offset, 8L); return segment().get(ValueLayout.JAVA_DOUBLE, offset.toLong())
    }

    actual fun writePointer(value: NativeAddress, offset: ULong) {
        if (unsafe) JvmUnsafeAccess.putLong(rawAddress(), offset.toLong(), value.rawValue)
        else {
            boundsCheck(offset, 8L)
            segment().set(ValueLayout.ADDRESS, offset.toLong(), value.toJvmSegmentOrNull() ?: MemorySegment.NULL)
        }
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        if (unsafe) return NativeAddress(JvmUnsafeAccess.getLong(rawAddress(), offset.toLong()))
        boundsCheck(offset, 8L)
        val raw = segment().get(ValueLayout.ADDRESS, offset.toLong())
        return NativeAddress(if (raw == MemorySegment.NULL) 0L else raw.address())
    }

    // ------------------------------------------------------------------
    // Accesseurs de tableaux — deux chemins (sûr = FFM copyFrom,
    // unsafe = boucle d'éléments via JvmUnsafeAccess, pattern Android)
    // ------------------------------------------------------------------

    private fun writeArray(
        destinationOffset: ULong,
        source: MemorySegment,
        sourceArray: Any,
        arrayIndex: ULong,
        size: ULong,
        elementSizeBytes: Int,
    ) {
        val sourceOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        if (unsafe) {
            unsafeCopyToNative(destinationOffset, sourceArray, sourceOffset, size.toInt(), elementSizeBytes)
        } else {
            write(destinationOffset, sourceOffset, source, bytesToCopy)
        }
    }

    private fun write(destinationOffset: ULong, sourceOffset: ULong, source: MemorySegment, bytesToCopy: ULong) {
        val sourceBytes = source.byteSize().toULong()
        val destinationBytes = size

        if (destinationOffset + bytesToCopy > destinationBytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array write out of bounds: bufferOffset=$destinationOffset bytes=$bytesToCopy size=$destinationBytes",
            )
        }
        if (sourceOffset + bytesToCopy > sourceBytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array write out of bounds: arrayIndex=$sourceOffset bytes=$bytesToCopy arrayBytes=$sourceBytes",
            )
        }

        segment().asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(source.asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    private fun readArray(
        sourceOffset: ULong,
        destination: MemorySegment,
        destinationArray: Any,
        arrayIndex: ULong,
        size: ULong,
        elementSizeBytes: Int,
    ) {
        val destinationOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        if (unsafe) {
            unsafeCopyFromNative(sourceOffset, destinationArray, destinationOffset, size.toInt(), elementSizeBytes)
        } else {
            read(sourceOffset, destinationOffset, destination, bytesToCopy)
        }
    }

    private fun read(sourceOffset: ULong, destinationOffset: ULong, destination: MemorySegment, bytesToCopy: ULong) {
        val destinationBytes = destination.byteSize().toULong()
        val sourceBytes = size

        if (destinationOffset + bytesToCopy > destinationBytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array read out of bounds: arrayIndex=$destinationOffset bytes=$bytesToCopy arrayBytes=$destinationBytes",
            )
        }
        if (sourceOffset + bytesToCopy > sourceBytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array read out of bounds: bufferOffset=$sourceOffset bytes=$bytesToCopy size=$sourceBytes",
            )
        }

        destination.asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(segment().asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    /** Copie élément par élément (tableau hôte → natif) via Unsafe — aucun bornes-check. */
    private fun unsafeCopyToNative(destinationOffset: ULong, array: Any, arrayOffset: ULong, count: Int, elementSize: Int) {
        var arrayPos = JvmUnsafeAccess.arrayBaseOffset(array.javaClass).toLong() + arrayOffset.toLong()
        var nativePos = rawAddress() + destinationOffset.toLong()
        repeat(count) {
            when (elementSize) {
                1 -> JvmUnsafeAccess.putByte(nativePos, 0, JvmUnsafeAccess.getByte(array, arrayPos))
                2 -> JvmUnsafeAccess.putShort(nativePos, 0, JvmUnsafeAccess.getShort(array, arrayPos))
                4 -> JvmUnsafeAccess.putInt(nativePos, 0, JvmUnsafeAccess.getInt(array, arrayPos))
                8 -> JvmUnsafeAccess.putLong(nativePos, 0, JvmUnsafeAccess.getLong(array, arrayPos))
                else -> error("Unsupported array element size: $elementSize")
            }
            arrayPos += elementSize
            nativePos += elementSize
        }
    }

    /** Copie élément par élément (natif → tableau hôte) via Unsafe — aucun bornes-check. */
    private fun unsafeCopyFromNative(sourceOffset: ULong, array: Any, arrayOffset: ULong, count: Int, elementSize: Int) {
        var arrayPos = JvmUnsafeAccess.arrayBaseOffset(array.javaClass).toLong() + arrayOffset.toLong()
        var nativePos = rawAddress() + sourceOffset.toLong()
        repeat(count) {
            when (elementSize) {
                1 -> JvmUnsafeAccess.putByte(array, arrayPos, JvmUnsafeAccess.getByte(nativePos, 0))
                2 -> JvmUnsafeAccess.putShort(array, arrayPos, JvmUnsafeAccess.getShort(nativePos, 0))
                4 -> JvmUnsafeAccess.putInt(array, arrayPos, JvmUnsafeAccess.getInt(nativePos, 0))
                8 -> JvmUnsafeAccess.putLong(array, arrayPos, JvmUnsafeAccess.getLong(nativePos, 0))
                else -> error("Unsupported array element size: $elementSize")
            }
            arrayPos += elementSize
            nativePos += elementSize
        }
    }

    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Byte.SIZE_BYTES)
    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Byte.SIZE_BYTES)
    actual fun writeUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val bytes = array.asByteArray()
        writeArray(bufferOffset, MemorySegment.ofArray(bytes), bytes, arrayIndex, size, UByte.SIZE_BYTES)
    }
    actual fun readUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val bytes = array.asByteArray()
        readArray(bufferOffset, MemorySegment.ofArray(bytes), bytes, arrayIndex, size, UByte.SIZE_BYTES)
    }
    actual fun writeShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Short.SIZE_BYTES)
    actual fun readShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Short.SIZE_BYTES)
    actual fun writeUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val shorts = array.asShortArray()
        writeArray(bufferOffset, MemorySegment.ofArray(shorts), shorts, arrayIndex, size, UShort.SIZE_BYTES)
    }
    actual fun readUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val shorts = array.asShortArray()
        readArray(bufferOffset, MemorySegment.ofArray(shorts), shorts, arrayIndex, size, UShort.SIZE_BYTES)
    }
    actual fun writeInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Int.SIZE_BYTES)
    actual fun readInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Int.SIZE_BYTES)
    actual fun writeUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val ints = array.asIntArray()
        writeArray(bufferOffset, MemorySegment.ofArray(ints), ints, arrayIndex, size, UInt.SIZE_BYTES)
    }
    actual fun readUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val ints = array.asIntArray()
        readArray(bufferOffset, MemorySegment.ofArray(ints), ints, arrayIndex, size, UInt.SIZE_BYTES)
    }
    actual fun writeLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Long.SIZE_BYTES)
    actual fun readLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Long.SIZE_BYTES)
    actual fun writeULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val longs = array.asLongArray()
        writeArray(bufferOffset, MemorySegment.ofArray(longs), longs, arrayIndex, size, ULong.SIZE_BYTES)
    }
    actual fun readULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) {
        val longs = array.asLongArray()
        readArray(bufferOffset, MemorySegment.ofArray(longs), longs, arrayIndex, size, ULong.SIZE_BYTES)
    }
    actual fun writeFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Float.SIZE_BYTES)
    actual fun readFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Float.SIZE_BYTES)
    actual fun writeDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Double.SIZE_BYTES)
    actual fun readDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), array, arrayIndex, size, Double.SIZE_BYTES)
}
