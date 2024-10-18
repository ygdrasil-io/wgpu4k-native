package generator.function

import builder.Builder
import builder.templateBuilder
import domain.CLibraryModel

internal fun List<CLibraryModel.Function>.toJvmFunctionsInterface() = templateBuilder {
    appendBlock("object Functions") {
        forEach { function -> toJvmFunction(function) }
    }
}


private fun Builder.toJvmFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toKotlinNativeType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toKotlinNativeType()}" }
        .joinToString(", ")
    appendBlock("fun $name($args): $returnType") {
        val handlerCallArgs = function.args
            .map { (name, type) -> "$name${type.toJavaExtraConverter()}" }
            .joinToString(", ")
        appendLine("return (${name}Handler.invokeExact($handlerCallArgs) as ${function.returnType.toJvmNativeType()})${function.returnType.toKotlinExtraConverter()}")
    }


    appendLine("private val ${name}HandlerDescription = ${function.generateDescriptor()}")
    appendLine("private val ${name}HandlerAddress = findOrThrow(\"$name\")")
    appendLine("private val ${name}Handler = Linker.nativeLinker().downcallHandle(${name}HandlerAddress, ${name}HandlerDescription)")
    newLine()
}

private fun CLibraryModel.Type.toKotlinExtraConverter(): String = when (this) {
    CLibraryModel.Primitive.UInt16 -> ".toUShort()"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32 -> ".toUInt()"
    CLibraryModel.Primitive.UInt64 -> ".toULong()"
    else -> ""
}
private fun CLibraryModel.Type.toJavaExtraConverter(): String = when (this) {
    CLibraryModel.Primitive.UInt16 -> ".toShort()"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32 -> ".toInt()"
    CLibraryModel.Primitive.UInt64 -> ".toLong()"
    else -> ""
}

private fun CLibraryModel.Function.generateDescriptor(): String {
    return when (returnType) {
        is CLibraryModel.Void -> "FunctionDescriptor.ofVoid("
        else -> "FunctionDescriptor.of(\n\t\t\t${returnType.toJvmDescriptorType()},"
    }.let { "$it\n" } + (args.map { (_, type) -> "\t\t\t${type.toJvmDescriptorType()}" }
        .joinToString(",\n", postfix = "\n\t\t)"))
}

internal fun CLibraryModel.Type.toJvmDescriptorType(): String = when (this) {
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32,
    is CLibraryModel.Reference.Enumeration,
    is CLibraryModel.Primitive.Int32 -> "C_INT"
    is CLibraryModel.Primitive.Int64,
    CLibraryModel.Primitive.UInt64 -> "C_LONG"
    CLibraryModel.Primitive.Float64 -> "C_DOUBLE"
    CLibraryModel.Primitive.Float32 -> "C_FLOAT"
    CLibraryModel.Primitive.UInt16 -> "C_SHORT"
    is CLibraryModel.Void,
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "C_POINTER"
}

internal fun CLibraryModel.Type.toJvmNativeType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    CLibraryModel.Primitive.UInt64,
    is CLibraryModel.Primitive.Int64 -> "Long"
    is CLibraryModel.Void -> "Unit"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32 -> "Int"
    CLibraryModel.Primitive.UInt16 -> "Short"
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "java.lang.foreign.MemorySegment"
}

internal fun CLibraryModel.Type.toKotlinNativeType(): String = when (this) {
    CLibraryModel.Primitive.UInt64 -> "ULong"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
    else -> toJvmNativeType()
}