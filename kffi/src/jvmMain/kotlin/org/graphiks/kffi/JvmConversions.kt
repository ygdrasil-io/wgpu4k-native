package org.graphiks.kffi

import java.lang.foreign.MemorySegment

/** Segment FFM éphémère (non-scopé) pour une adresse brute ; zéro = segment nul. */
internal fun NativeAddress.toJvmSegmentOrNull(): MemorySegment? =
    if (rawValue == 0L) null else MemorySegment.ofAddress(rawValue)

internal fun NativeAddress.toJvmSegment(): MemorySegment =
    requireNotNull(toJvmSegmentOrNull()) { "Cannot convert null NativeAddress to segment" }

/** Adresse d'un segment FFM ; segment nul → 0. */
internal fun MemorySegment?.toNativeAddress(): NativeAddress =
    NativeAddress(this?.address() ?: 0L)

/** Lit/écrit le scope : dérive un segment borné [size] depuis l'adresse brute. */
internal fun NativeAddress.toJvmSegment(size: Long): MemorySegment {
    require(rawValue != 0L) { "Cannot derive segment from null address" }
    return MemorySegment.ofAddress(rawValue).reinterpret(size)
}

/**
 * Segment FFM pour ce buffer, dérivé de l'adresse brute.
 * NOTE : scope global — ne porte PAS le scope d'arène (I2-a) ; à réserver
 * aux buffers bruts/non scopés (le chemin scopé arrive en M1.3).
 */
internal fun MemoryBuffer.toJvmSegment(): MemorySegment = handler.toJvmSegment(size.toLong())
