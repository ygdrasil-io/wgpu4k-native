package org.graphiks.kffi.benchmark.jvm

import java.lang.foreign.AddressLayout
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

object FixtureLoader {
    val libraryPath: String =
        System.getProperty("kffi.bench.fixture.library")
            ?: error("System property kffi.bench.fixture.library must point to the compiled bench fixture")

    val lookup: SymbolLookup by lazy {
        System.load(libraryPath)
        SymbolLookup.loaderLookup()
            .or(Linker.nativeLinker().defaultLookup())
    }

    fun findOrThrow(symbol: String): MemorySegment =
        lookup.find(symbol).orElseThrow { UnsatisfiedLinkError("unresolved symbol: $symbol") }

    val ADDRESS: AddressLayout = ValueLayout.ADDRESS
}
