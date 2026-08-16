package org.graphiks.kffi

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles

val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN
val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE
val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT
val C_INT: ValueLayout = ValueLayout.JAVA_INT
val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG
val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT
val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE
val C_POINTER: ValueLayout = ValueLayout.ADDRESS
val C_LONG: ValueLayout = ValueLayout.JAVA_LONG

private val SYMBOL_LOOKUP by lazy {
    SymbolLookup.loaderLookup()
        .or(Linker.nativeLinker().defaultLookup())
}

/** Résout un symbole et retourne son adresse brute (0 si introuvable). */
fun findOrThrow(symbol: String): Long {
    return SYMBOL_LOOKUP.find(symbol)
        .orElseThrow { UnsatisfiedLinkError("unresolved symbol: $symbol") }
        .address()
}

fun upcallHandle(fi: Class<*>?, name: String?, fdesc: FunctionDescriptor): MethodHandle {
    try {
        return MethodHandles.lookup().findVirtual(fi, name, fdesc.toMethodType())
    } catch (ex: ReflectiveOperationException) {
        throw AssertionError(ex)
    }
}
