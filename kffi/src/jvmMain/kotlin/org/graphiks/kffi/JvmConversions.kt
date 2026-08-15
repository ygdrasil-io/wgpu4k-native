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
internal fun NativeAddress.toJvmSegment(size: Long): MemorySegment =
    MemorySegment.ofAddress(rawValue).reinterpret(size)

internal fun MemoryBuffer.toJvmSegment(): MemorySegment = handler.toJvmSegment(size.toLong())
