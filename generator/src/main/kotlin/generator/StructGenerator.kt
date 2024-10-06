package generator

import androidMainBasePath
import commonMainBasePath
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import jvmMainBasePath
import nativeMainBasePath
import java.io.File

val structuresCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Structures.kt")

val structuresAndroidMainFile = androidMainBasePath
    .resolve("webgpu")
    .resolve("Structures.android.kt")

val structuresJvmMainFile = jvmMainBasePath
    .resolve("webgpu")
    .resolve("Structures.jvm.kt")

val structuresNativeMainFile = nativeMainBasePath
    .resolve("webgpu")
    .resolve("Structures.native.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.ArrayHolder
    
    
""".trimIndent()

private val headerJvm = """
    $disclamer
    package webgpu
    
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
    import java.lang.foreign.AddressLayout
    import java.lang.foreign.MemoryLayout.structLayout
    
    
""".trimIndent()

private val headerAndroid = """
    $disclamer
    package webgpu
    
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
    import java.lang.foreign.AddressLayout
    import java.lang.foreign.MemoryLayout.Companion.structLayout
    
    
""".trimIndent()

private val headerNative = """
    $disclamer
    @file:OptIn(ExperimentalForeignApi::class)
    package webgpu
        
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.toCString
    import ffi.ArrayHolder
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.pointed
    import kotlinx.cinterop.toCPointer
    import kotlinx.cinterop.toKString
    import kotlinx.cinterop.toLong
    
    
""".trimIndent()

internal fun File.generateCommonStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("expect value class ${it.name}(val handler: NativeAddress) {\n")
        it.members.forEach { (name, type, optional) ->
            val variableType = type.generateVariableType()
            appendText("\t$variableType $name: ${type.toFunctionKotlinType()}$optional\n")
        }
        appendText("}\n\n")
    }
}

internal fun File.generateNativeStructures(structures: List<CLibraryModel.Structure>) {
    writeText(headerNative)
    structures.forEach {
        val structureName = it.name
        appendText("actual value class $structureName(actual val handler: NativeAddress) {\n")
        it.members.forEach { (name, type, optional) ->
            val variableType = type.generateVariableType()
            val nativeAccessor = "handler.toCPointer<webgpu.native.$structureName>()?.pointed"
            appendText("\tactual $variableType $name: ${type.toFunctionKotlinType()}$optional\n")
            // Getter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}"
                is CLibraryModel.Reference.Enumeration
                    -> "$nativeAccessor?.${name} ?: error(\"pointer of $structureName is null\")"
                is CLibraryModel.Primitive.Bool
                    -> "$nativeAccessor?.${name}?.toBoolean() ?: error(\"pointer of $structureName is null\")"
                is CLibraryModel.Primitive
                    -> "$nativeAccessor?.${name} ?: error(\"pointer of $structureName is null\")"
                is CLibraryModel.Reference.CString
                    -> "$nativeAccessor?.${name}?.toCString()"
                is CLibraryModel.Reference.Pointer
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"
                is CLibraryModel.Reference.StructureField
                    -> "$nativeAccessor?.${name}?.rawPtr?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) } ?: error(\"pointer of $structureName is null\")"
                is CLibraryModel.Reference.Structure
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"
                is CLibraryModel.Reference.Callback
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { CallbackHolder<${type.name}>(it) }"

                is CLibraryModel.Array
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ArrayHolder<${type.subType.toFunctionKotlinType()}>(it) }"
            }.let { appendText("\t\tget() = $it\n") }
            // Setter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.toCPointer() }"
                is CLibraryModel.Reference.Enumeration
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue }"
                is CLibraryModel.Reference.CString
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Primitive.Bool
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue.toUInt() }"
                is CLibraryModel.Primitive
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue }"
                is CLibraryModel.Reference.Pointer
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Reference.Structure
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Reference.Callback
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Array
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Reference.StructureField -> null
            }?.let { appendText("\t\tset(newValue) { $it } \n\n") } ?: appendText("\n")
        }
        appendText("}\n\n")
    }
}

internal fun File.generateJvmStructures(structures: List<CLibraryModel.Structure>) {
    val isAndroid = absolutePath.contains("android")
    if (isAndroid) writeText(headerAndroid) else writeText(headerJvm)

    structures.forEach {
        val structureName = it.name
        appendText("@JvmInline\n")
        appendText("actual value class $structureName(actual override val handler: NativeAddress) : CStructure {\n")

        it.members.forEach { (name, type, optional) ->
            val variableType = type.generateVariableType()
            appendText("\tactual $variableType $name: ${type.toFunctionKotlinType()}$optional\n")
            // Getter
            when (type) {
                is CLibraryModel.Array,
                CLibraryModel.Primitive.Void,
                CLibraryModel.Reference.CString,
                is CLibraryModel.Reference.Callback,
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Reference.OpaquePointer,
                is CLibraryModel.Reference.Pointer,
                is CLibraryModel.Reference.Structure, -> "TODO()"

                CLibraryModel.Primitive.Float32 -> "getFloat(\"$name\", ${name}Offset)"
                CLibraryModel.Primitive.Float64 -> "getDouble(\"$name\", ${name}Offset)"
                CLibraryModel.Primitive.Int64 -> "getLong(\"$name\", ${name}Offset)"
                CLibraryModel.Primitive.UInt16 -> "getUShort(\"$name\", ${name}Offset)"
                CLibraryModel.Primitive.UInt64 -> "getULong(\"$name\", ${name}Offset)"

                CLibraryModel.Primitive.Bool -> "getInt(\"$name\", ${name}Offset).toBoolean()"
                CLibraryModel.Primitive.Int32 -> "getInt(\"$name\", ${name}Offset)"
                CLibraryModel.Primitive.UInt32 -> "getUInt(\"$name\", ${name}Offset)"

                is CLibraryModel.Reference.StructureField -> "get(\"$name\", ${name}Offset).let(::${type.name})"
            }.let { appendText("\t\tget() = $it\n") }

            // Setter
            when (type) {
                CLibraryModel.Primitive.Void,
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Reference.OpaquePointer,
                CLibraryModel.Primitive.Float32,
                CLibraryModel.Primitive.Float64,
                CLibraryModel.Primitive.Int64,
                CLibraryModel.Primitive.UInt16,
                CLibraryModel.Primitive.UInt64,
                CLibraryModel.Primitive.Bool,
                CLibraryModel.Primitive.Int32,
                CLibraryModel.Primitive.UInt32 -> "set(${name}Layout, ${name}Offset, newValue)"

                is CLibraryModel.Reference.Callback,
                CLibraryModel.Reference.CString,
                is CLibraryModel.Reference.Structure,
                is CLibraryModel.Array,
                is CLibraryModel.Reference.Pointer -> "set(${name}Layout, ${name}Offset, newValue?.handler)"

                is CLibraryModel.Reference.StructureField -> null
            }?.let { appendText("\t\tset(newValue) = $it\n\n") } ?: appendText("\n")
        }

        appendHelperFunctions(structureName)

        // Generate layout
        appendText("\tcompanion object {\n")
        appendText("\t\tinternal val LAYOUT = structLayout(\n")
        it.members.map { (name, type, _) ->
            when (type) {
                CLibraryModel.Reference.OpaquePointer,
                is CLibraryModel.Reference.Pointer,
                CLibraryModel.Reference.CString,
                is CLibraryModel.Array,
                is CLibraryModel.Reference.Callback,
                CLibraryModel.Primitive.Void,
                is CLibraryModel.Reference.Structure -> "C_POINTER"

                CLibraryModel.Primitive.UInt16 -> "C_SHORT"

                CLibraryModel.Primitive.Bool,
                CLibraryModel.Primitive.UInt32,
                CLibraryModel.Primitive.Int32 -> "C_INT"

                CLibraryModel.Primitive.UInt64,
                CLibraryModel.Primitive.Int64 -> "C_LONG"

                CLibraryModel.Primitive.Float32 -> "C_FLOAT"
                CLibraryModel.Primitive.Float64 -> "C_DOUBLE"

                is CLibraryModel.Reference.Enumeration -> "C_INT"
                is CLibraryModel.Reference.StructureField -> "${type.name}.LAYOUT"
            }.let { "\t\t\t$it.withName(\"${name}\")" }
        }.joinToString(",\n")
            .let { "$it\n" }
            .let(::appendText)
        appendText("\t\t).withName(\"$structureName\")\n\n")

        // Write offset
        var offset: Int? = 0
        var oldName: String? = null
        it.members.mapIndexed { index, (name, type, _), ->
            "val ${name}Offset = " + (offset?.let { "${it}L" } ?: "LAYOUT.withName(\"$oldName\").byteSize()") +
                    (oldName?.let { " + ${oldName}Offset" } ?: "") + "\n" +
                    "\t\tval ${name}Layout = LAYOUT.withName(\"$name\")\n"
                .also { offset = type.getOffsetSize() }
                .also { oldName = name }
        }.map { "\t\t$it\n" }
            .joinToString("")
            .let(::appendText)

        appendText("\t}\n")
        /*
        	fun allocate(allocator: SegmentAllocator): MemorySegment {
		return allocator.allocate(`$LAYOUT`)
	}
         */

        appendText("}\n\n")
    }
}

fun File.appendHelperFunctions(structureName: String) {
    appendText("\toverride fun getLayout(name: String)\n")
    appendText("\t\t= $structureName.LAYOUT.withName(name) as AddressLayout\n")
    appendText("\n")
}

private fun CLibraryModel.Type.getOffsetSize() = when(this) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Array,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Primitive.Void,
    is CLibraryModel.Reference.Structure -> Long.SIZE_BYTES

    CLibraryModel.Primitive.UInt16 -> Short.SIZE_BYTES

    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32,
    CLibraryModel.Primitive.Int32 -> Int.SIZE_BYTES

    CLibraryModel.Primitive.UInt64,
    CLibraryModel.Primitive.Int64 -> Long.SIZE_BYTES

    CLibraryModel.Primitive.Float32 -> Float.SIZE_BYTES
    CLibraryModel.Primitive.Float64 -> Double.SIZE_BYTES

    is CLibraryModel.Reference.Enumeration -> Int.SIZE_BYTES
    is CLibraryModel.Reference.StructureField -> null
}

private fun CLibraryModel.Type.generateVariableType(): String = when(this) {
    is CLibraryModel.Reference.StructureField -> "val"
    else -> "var"
}