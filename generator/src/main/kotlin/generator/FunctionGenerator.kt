package generator

import androidMainBasePath
import commonMainBasePath
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import jvmMainBasePath
import nativeMainBasePath
import java.io.File

val functionsCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Functions.kt")

val functionsNativeMainFile = nativeMainBasePath
    .resolve("webgpu")
    .resolve("Functions.native.kt")

val functionsJvmMainFile = jvmMainBasePath
    .resolve("webgpu")
    .resolve("Functions.jvm.kt")

val functionsAndroidMainFile = androidMainBasePath
    .resolve("webgpu")
    .resolve("Functions.android.kt")



private val header = """
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.ArrayHolder
    import ffi.adapt
    
    
    
""".trimIndent()

private val nativeHeader = """
    @file:OptIn(ExperimentalForeignApi::class)
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.ArrayHolder
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.toCPointer
    
    
    
""".trimIndent()

internal fun File.generateCommonFunctions(functions: List<CLibraryModel.Function>) {

    writeText(header)

    functions.forEach { function ->
        writeFunction(function)
    }

}

private fun File.writeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    appendText("expect fun $name($args): $returnType\n")
}

private fun CLibraryModel.Type.optionalReturnType(): String = when (this) {
    CLibraryModel.Primitive.Void,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}

private fun CLibraryModel.Type.optional(): String = when (this) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}


internal fun File.generateNativeFunctions(functions: List<CLibraryModel.Function>) {

    writeText(nativeHeader)

    functions.forEach { function ->
        writeNativeFunction(function)
    }

}

private fun File.writeNativeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    val argsCall = function.args
        .map { (name, type) -> type.toNativeArgCall(name) }
        .joinToString(", ")
    appendText("actual fun $name($args): $returnType\n")
    appendText("\t = webgpu.native.$name($argsCall)\n")

    when (function.returnType) {
        is CLibraryModel.Reference.Enumeration -> null
        is CLibraryModel.Reference -> {
            "?.rawValue?.toLong()?.let(::${function.returnType.name})"
        }
        is CLibraryModel.Primitive.Bool -> ".toBoolean()\n"
        else -> null
    }?.let { appendText("\t\t$it\n") }

    appendText("\n")
}

internal fun File.generateJvmFunctions(functions: List<CLibraryModel.Function>) {

    writeText(header)

    functions.forEach { function ->
        writeJvmFunction(function)
    }

}

private fun File.writeJvmFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    val argsCall = function.args
        .map { (name, type) -> type.toJvmArgCall(name) }
        .joinToString(", ")
    appendText("actual fun $name($args): $returnType\n")
    appendText("\t = Functions.$name($argsCall)\n")

    when (function.returnType) {
        is CLibraryModel.Reference.Enumeration -> null
        is CLibraryModel.Reference -> {
            "?.let(::NativeAddress)?.let(::${function.returnType.name})"
        }
        is CLibraryModel.Primitive.Bool -> ".toBoolean()\n"
        else -> null
    }?.let { appendText("\t\t$it\n") }

    appendText("\n")
}

private fun CLibraryModel.Type.toJvmArgCall(name: String) = when(this) {
    is CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Enumeration -> name
    is CLibraryModel.Array,
    is CLibraryModel.Reference -> "$name?.handler.adapt()"
    else -> name
}

private fun CLibraryModel.Type.toNativeArgCall(name: String) = when(this) {
    is CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Enumeration -> name
    is CLibraryModel.Array,
    is CLibraryModel.Reference -> "$name?.handler?.toCPointer()"
    else -> name
}