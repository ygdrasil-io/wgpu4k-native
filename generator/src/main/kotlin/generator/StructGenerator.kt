package generator

import converter.variableType
import disclamer
import domain.NativeModel
import domain.toFunctionKotlinType
import generator.structure.toJvmStructure
import generator.structure.toNativeStructure
import java.io.File

private val header = """
    $disclamer
    package io.ygdrasil.wgpu
    
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.ArrayHolder
    import ffi.MemoryAllocator
    
    
""".trimIndent()

private val headerJvm = """
    $disclamer
    package io.ygdrasil.wgpu
    
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.ArrayHolder
    import ffi.C_LONG
    import ffi.C_POINTER
    import ffi.C_SHORT
    import ffi.C_INT
    import ffi.C_FLOAT
    import ffi.C_DOUBLE
    import ffi.CStructure
    import ffi.MemoryAllocator
    import java.lang.foreign.AddressLayout
    import java.lang.foreign.MemoryLayout
    import java.lang.foreign.GroupLayout
    import java.lang.foreign.MemoryLayout.PathElement.groupElement
    import java.lang.foreign.MemoryLayout.structLayout
    
    
""".trimIndent()

private val headerNative = """
    $disclamer
    @file:OptIn(ExperimentalForeignApi::class)
    package io.ygdrasil.wgpu
        
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.toCString
    import ffi.ArrayHolder
    import ffi.MemoryAllocator
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.pointed
    import kotlinx.cinterop.useContents
    import kotlinx.cinterop.toCPointer
    import kotlinx.cinterop.toKString
    import kotlinx.cinterop.toLong
    import kotlinx.cinterop.sizeOf
    import kotlinx.cinterop.CValue
    import kotlinx.cinterop.cValue
    
    
""".trimIndent()

internal fun File.generateCommonStructures(structures: List<NativeModel.Structure>)
    = resolve("Structures.kt").apply {
    writeText(header)
    structures.forEach {
        val structureName = it.name
        appendText("expect interface $structureName {\n")
        it.members.forEach { (name, type, optional) ->
            val variableType = type.variableType()
            appendText("\t$variableType $name: ${type.toFunctionKotlinType()}$optional\n")
        }

        appendText("\tval handler: NativeAddress\n")

        appendText("\tcompanion object {\n")
        appendText("\t\toperator fun invoke(address: NativeAddress): $structureName\n")
        appendText("\t\tfun allocate(allocator: MemoryAllocator): $structureName\n")
        appendText("\t\tfun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, $structureName) -> Unit): ArrayHolder<$structureName>\n")
        appendText("\t}\n")
        appendText("}\n\n")
    }
}

internal fun File.generateNativeStructures(structures: List<NativeModel.Structure>)
 = resolve("Structures.native.kt").apply {
    writeText(headerNative)
    structures.map { it.toNativeStructure() }
        .forEach { appendText(it) }
}

internal fun File.generateJvmStructures(structures: List<NativeModel.Structure>)
= resolve("Structures.jvm.kt").apply {
    writeText(headerJvm)
    structures.map { it.toJvmStructure() }
        .forEach { appendText(it) }
}


