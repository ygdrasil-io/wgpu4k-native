package generator.function

import builder.templateBuilder
import domain.CLibraryModel
import domain.toFunctionKotlinType


fun List<CLibraryModel.Function>.toAndroidFunctions() = templateBuilder {
    forEach { function ->
        val name = function.name
        val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
        val args = function.args
            .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
            .joinToString(", ")
        val argsCall = function.args
            .map { (name, type) -> type.toJvmArgCall(name) }
            .joinToString(", ")
        appendLine("actual fun $name($args): $returnType")
        appendLine("\t = webgpu.android.Functions.$name($argsCall)")

        when (function.returnType) {
            is CLibraryModel.Reference.Enumeration -> null
            is CLibraryModel.Reference.OpaquePointer -> "?.let(::NativeAddress)"
            is CLibraryModel.Reference -> {
                "?.let(::NativeAddress)?.let(::${function.returnType.name})"
            }
            is CLibraryModel.Primitive.Bool -> ".toBoolean()"
            else -> null
        }?.let { appendLine("\t$it") }

        newLine()
    }
}


private fun CLibraryModel.Type.toJvmArgCall(name: String) = when(this) {
    is CLibraryModel.Reference.StructureField -> "${name}.toCValue()"
    is CLibraryModel.Reference.OpaquePointer -> "$name.adapt()"
    is CLibraryModel.Reference.Enumeration -> name
    is CLibraryModel.Array,
    is CLibraryModel.Reference -> "$name?.handler.adapt()"
    else -> name
}

private fun CLibraryModel.Type.optionalReturnType(): String = when (this) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}

private fun CLibraryModel.Type.optional(): String = when (this) {
    CLibraryModel.Void,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}