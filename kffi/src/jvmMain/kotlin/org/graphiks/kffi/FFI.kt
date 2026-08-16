package org.graphiks.kffi

import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

val C_POINTER: ValueLayout = ValueLayout.ADDRESS

private val SYMBOL_LOOKUP by lazy {
    SymbolLookup.loaderLookup()
        .or(Linker.nativeLinker().defaultLookup())
}

/** Résout un symbole et retourne son adresse brute ; lève `UnsatisfiedLinkError` si introuvable. */
fun findOrThrow(symbol: String): Long {
    return SYMBOL_LOOKUP.find(symbol)
        .orElseThrow { UnsatisfiedLinkError("unresolved symbol: $symbol") }
        .address()
}
