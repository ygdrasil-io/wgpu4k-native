package generator.function

import builder.Builder
import builder.templateBuilder
import domain.NativeModel
import domain.toFunctionKotlinType

internal fun List<NativeModel.Function>.toNativeFunctionsInterface() = templateBuilder {
    forEach { function -> toNativeFunction(function) }
}

private fun Builder.toNativeFunction(function: NativeModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    val argsCall = function.args
        .map { (name, type) -> type.toNativeArgCall(name) }
        .joinToString(", ")
    appendBlock("actual fun $name($args): $returnType") {
        appendLine("${if (function.returnType is NativeModel.Void) "" else "return "}webgpu.native.$name($argsCall)")

        when (function.returnType) {
            is NativeModel.Reference.Enumeration -> null
            is NativeModel.Reference.OpaquePointer -> "?.let(::NativeAddress)"
            is NativeModel.Reference.StructureField -> ".let(${function.returnType.name}::ByValue)"
            is NativeModel.Reference -> {
                "?.let(::NativeAddress)?.let(::${function.returnType.name})"
            }
            is NativeModel.Primitive.Bool -> ".toBoolean()"
            else -> null
        }?.let { appendLine("\t$it") }

    }
    newLine()
}


private fun NativeModel.Type.toNativeArgCall(name: String) = when(this) {
    is NativeModel.Reference.StructureField -> "$name.toCValue()"
    is NativeModel.Reference.CString -> "$name?.toKString()"
    is NativeModel.Reference.OpaquePointer -> "$name?.pointer"
    is NativeModel.Reference.Enumeration -> name
    is NativeModel.Array,
    is NativeModel.Reference -> "$name?.handler?.reinterpret()"
    else -> name
}