package generator.structure

import builder.templateBuilder
import converter.toPrimitiveDefaultValue
import converter.toPrimitiveKotlinType
import converter.variableType
import domain.CLibraryModel


fun CLibraryModel.Structure.toJnaStructure() = templateBuilder {
    appendBlock("internal sealed class ${name}(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer)") {
        members.forEach { (name, type, _) ->
            appendLine("@JvmField ${type.variableType()} $name: ${type.toJnaType()}${type.toOptionalModifier()} = ${type.toDefaultValue()}")

        }

        appendLine("override fun getFieldOrder() = listOf(${members.map { (name, _, _) -> "\"$name\"" }.joinToString(", ")})")
        newLine()
        appendLine("internal class ByReference(pointer: com.sun.jna.Pointer? = null) : ${name}(pointer), com.sun.jna.Structure.ByReference")
        appendLine("internal class ByValue(pointer: com.sun.jna.Pointer? = null) : ${name}(pointer), com.sun.jna.Structure.ByValue")
    }
}

private fun CLibraryModel.Type.toDefaultValue(): String = when (this) {
    is CLibraryModel.Primitive.Bool -> "0"
    is CLibraryModel.Primitive.UInt64 -> "0L"
    is CLibraryModel.Primitive.UInt32 -> "0"
    is CLibraryModel.Primitive.UInt16 -> "0"
    is CLibraryModel.Primitive -> toPrimitiveDefaultValue()
    is CLibraryModel.Reference.StructureField -> "${name}.ByValue()"
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "null"
}

private fun CLibraryModel.Type.toOptionalModifier(): String  = when (this) {
    is CLibraryModel.Primitive -> ""
    is CLibraryModel.Reference.StructureField -> ""
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "?"
}

private fun CLibraryModel.Type.toJnaType(): String  = when (this) {
    is CLibraryModel.Primitive.Bool -> "Int"
    is CLibraryModel.Primitive.UInt64 -> "Long"
    is CLibraryModel.Primitive.UInt32 -> "Int"
    is CLibraryModel.Primitive.UInt16 -> "Short"
    is CLibraryModel.Primitive -> toPrimitiveKotlinType()
    is CLibraryModel.Reference.Structure -> "${name}.ByReference"
    is CLibraryModel.Reference.StructureField -> "${name}.ByValue"
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "com.sun.jna.Pointer"
}
