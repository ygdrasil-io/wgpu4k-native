package generator

import disclamer
import domain.CLibraryModel
import jvmMainBasePath
import java.io.File

val jvmNativeFunctionsMainFile = jvmMainBasePath
    .resolve("webgpu")
    .resolve("jvm")
    .resolve("Functions.kt")


private val header = """
    $disclamer
    package webgpu
    
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.Linker
    import ffi.findOrThrow
    import ffi.C_POINTER
    import ffi.C_INT
    import ffi.C_LONG
    import ffi.C_FLOAT
    import java.lang.foreign.FunctionDescriptor
    
    
""".trimIndent()

internal fun File.generateJvmNativeFunctions(functions: List<CLibraryModel.Function>) {
    writeText(header)

    appendText("object Functions {\n\n")

    functions.forEach { function ->
        writeFunction(function)
    }

    appendText("}\n")
}

private fun File.writeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toJvmNativeType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toJvmNativeType()}" }
        .joinToString(", ")
    appendText("\tfun $name($args): $returnType {\n")

    val handlerCallArgs = function.args
        .map { (name, _) -> name }
        .joinToString(", ")
    appendText("\t\treturn ${name}Handler.invokeExact($handlerCallArgs) as ${function.returnType.toJvmNativeType()}\n")
    appendText("\t}\n")


    appendText("\tprivate val ${name}HandlerDescription = ${function.generateDescriptor()}\n")
    appendText("\tprivate val ${name}HandlerAddress = findOrThrow(\"$name\")\n")
    appendText("\tprivate val ${name}Handler = Linker.nativeLinker().downcallHandle(${name}HandlerAddress, ${name}HandlerDescription)\n\n")
}

private fun CLibraryModel.Function.generateDescriptor(): String {
    return when (returnType) {
        is CLibraryModel.Primitive.Void -> "FunctionDescriptor.ofVoid("
        else -> "FunctionDescriptor.of(\n\t\t\t${returnType.toJvmDescriptorType()},"
    }.let { "$it\n" } + (args.map { (_, type) -> "\t\t\t${type.toJvmDescriptorType()}" }
    .joinToString(",\n", postfix = "\n\t\t)"))
}

internal fun CLibraryModel.Type.toJvmDescriptorType(): String = when (this) {
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32,
    is CLibraryModel.Primitive.Int32 -> "C_INT"
    is CLibraryModel.Primitive.Int64,
    CLibraryModel.Primitive.UInt64 -> "C_LONG"
    CLibraryModel.Primitive.Float64 -> "C_DOUBLE"
    CLibraryModel.Primitive.Float32 -> "C_FLOAT"
    CLibraryModel.Primitive.UInt16 -> "C_SHORT"
    is CLibraryModel.Primitive.Void,
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "C_POINTER"
}

internal fun CLibraryModel.Type.toJvmNativeType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    is CLibraryModel.Primitive.Int64 -> "Long"
    is CLibraryModel.Primitive.Void -> "Unit"
    CLibraryModel.Primitive.Bool -> "Boolean"
    CLibraryModel.Primitive.UInt64 -> "ULong"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "MemorySegment"
}