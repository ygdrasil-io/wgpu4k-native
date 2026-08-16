package org.graphiks.kffi

import java.lang.foreign.MemorySegment

/** Segment FFM éphémère (non-scopé) pour une adresse brute ; zéro = segment nul. */
internal fun NativeAddress.toJvmSegmentOrNull(): MemorySegment? =
    if (rawValue == 0L) null else MemorySegment.ofAddress(rawValue)

/** Adresse d'un segment FFM ; segment nul → 0. */
internal fun MemorySegment?.toNativeAddress(): NativeAddress =
    NativeAddress(this?.address() ?: 0L)

/** Lit/écrit le scope : dérive un segment borné [size] depuis l'adresse brute. */
internal fun NativeAddress.toJvmSegment(size: Long): MemorySegment {
    require(rawValue != 0L) { "Cannot derive segment from null address" }
    return MemorySegment.ofAddress(rawValue).reinterpret(size)
}
