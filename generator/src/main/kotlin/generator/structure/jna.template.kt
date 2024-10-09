package generator.structure

import builder.templateBuilder
import converter.toPrimitiveDefaultValue
import converter.toPrimitiveKotlinType
import converter.variableType
import domain.CLibraryModel


fun CLibraryModel.Structure.toJnaStructure() = templateBuilder {
    val structureName = name
    appendBlock("internal class ${name}ByValue(handler: Long) : com.sun.jna.Structure(com.sun.jna.Pointer(handler)), com.sun.jna.Structure.ByValue") {
        members.forEach { (name, type, _) ->
            appendLine("@JvmField ${type.variableType()} $name: ${type.toJnaType()}${type.toOptionalModifier()} = ${type.toDefaultValue(structureName)}")

        }

        appendLine("override fun getFieldOrder() = listOf(${members.map { (name, _, _) -> "\"$name\"" }.joinToString(", ")})")
    }
}

private fun CLibraryModel.Type.toDefaultValue(structureName: String): String = when (this) {
    is CLibraryModel.Primitive.Bool -> "0"
    is CLibraryModel.Primitive.UInt64 -> "0L"
    is CLibraryModel.Primitive.UInt32 -> "0"
    is CLibraryModel.Primitive.UInt16 -> "0"
    is CLibraryModel.Primitive -> toPrimitiveDefaultValue()
    is CLibraryModel.Reference.StructureField -> "${name}ByValue($structureName(handler.let(::Pointer).let(::NativeAddress)).handler.pointer.toAddress())"
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
    is CLibraryModel.Reference.StructureField -> "${name}ByValue"
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "com.sun.jna.Pointer"
}
