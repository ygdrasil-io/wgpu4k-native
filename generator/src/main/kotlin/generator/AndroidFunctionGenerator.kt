package generator

import androidMainBasePath
import disclamer
import domain.CLibraryModel
import java.io.File

val androidNativeFunctionsMainFile = androidMainBasePath
    .resolve("webgpu")
    .resolve("android")
    .resolve("Functions.kt")


private val header = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    import com.sun.jna.Native
    
    
""".trimIndent()

internal fun File.generateAndroidNativeFunctions(functions: List<CLibraryModel.Function>) {
    writeText(header)

    appendText("object Functions {\n\n")

    appendText("\tinit {\n")
    appendText("\t\tNative.register(Functions::class.java, \"wgpu4k\")\n")
    appendText("\t}\n\n")

    functions.forEach { function ->
        writeFunction(function)
    }

    appendText("}\n")
}

private fun File.writeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toAndroidNativeType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toAndroidNativeType()}" }
        .joinToString(", ")
    appendText("\texternal fun $name($args): $returnType\n")
}

internal fun CLibraryModel.Type.toAndroidNativeType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    is CLibraryModel.Primitive.Int64 -> "Long"
    is CLibraryModel.Primitive.Void -> "Unit"
    CLibraryModel.Primitive.Bool -> "UInt"
    CLibraryModel.Primitive.UInt64 -> "ULong"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "Long"
}