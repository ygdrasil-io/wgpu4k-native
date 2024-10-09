package generator.structure

import builder.templateBuilder
import converter.getOffsetSize
import converter.variableType
import domain.CLibraryModel
import domain.toFunctionKotlinType
import java.io.File

fun CLibraryModel.Structure.toAndroidStructure() = templateBuilder {
    val structureName = name
    appendLine("@JvmInline")
    appendBlock("actual value class $structureName(actual override val handler: NativeAddress) : CStructure") {

        appendLine("internal fun toCValue() = ${structureName}ByValue(handler.pointer.toAddress())")

        members.forEach { (name, type, optional) ->

            appendLine("actual ${type.variableType()} $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                CLibraryModel.Void,
                CLibraryModel.Reference.OpaquePointer, -> "get(${name}Layout, ${name}Offset)"
                CLibraryModel.Reference.CString,-> "get(${name}Layout, ${name}Offset).let(::CString)"
                is CLibraryModel.Array, -> "get(${name}Layout, ${name}Offset).let(::ArrayHolder)"
                is CLibraryModel.Reference.Callback -> "get(${name}Layout, ${name}Offset).let(::CallbackHolder)"
                CLibraryModel.Primitive.Float32 -> "getFloat(${name}Layout, ${name}Offset)"
                CLibraryModel.Primitive.Float64 -> "getDouble(${name}Layout, ${name}Offset)"
                CLibraryModel.Primitive.Int64 -> "getLong(${name}Layout, ${name}Offset)"
                CLibraryModel.Primitive.UInt16 -> "getUShort(${name}Layout, ${name}Offset)"
                CLibraryModel.Primitive.UInt64 -> "getULong(${name}Layout, ${name}Offset)"
                CLibraryModel.Primitive.Bool -> "getInt(${name}Layout, ${name}Offset).toBoolean()"
                CLibraryModel.Primitive.Int32 -> "getInt(${name}Layout, ${name}Offset)"
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Primitive.UInt32 -> "getUInt(${name}Layout, ${name}Offset)"
                is CLibraryModel.Reference -> "get(${name}Layout, ${name}Offset).let(::${type.name})"
            }.let { appendLine("\tget() = $it") }

            // Setter
            when (type) {
                CLibraryModel.Void,
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
            }?.let { appendLine("\tset(newValue) = $it") }
            newLine()
        }

        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator): $structureName") {
                appendLine("return allocator.allocate(LAYOUT.byteSize())")
                appendLine("\t.let(::$structureName)")
            }

            // Generate layout
            appendLine("internal val LAYOUT = structLayout(")
            members.forEach { (name, type, _) ->
                when (type) {
                    CLibraryModel.Reference.OpaquePointer,
                    is CLibraryModel.Reference.Pointer,
                    CLibraryModel.Reference.CString,
                    is CLibraryModel.Array,
                    is CLibraryModel.Reference.Callback,
                    CLibraryModel.Void,
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
                }.let { "\t$it.withName(\"${name}\")," }
                    .also(::appendLine)
            }
            appendLine(").withName(\"$structureName\")")

            // Write offset
            var offset: Int? = 0
            var oldName: String? = null
            members.forEachIndexed { index, (name, type, _), ->
                appendLine("val ${name}Offset = " + (offset?.let { "${it}L" } ?: "LAYOUT.withName(\"$oldName\").byteSize()") + (oldName?.let { " + ${oldName}Offset" } ?: ""))
                appendLine("val ${name}Layout = LAYOUT.withName(\"$name\")")
                offset = type.getOffsetSize()
                oldName = name
            }
        }

    }
}

