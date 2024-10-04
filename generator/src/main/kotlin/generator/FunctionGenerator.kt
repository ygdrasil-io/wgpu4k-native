package generator

import commonMainBasePath
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import java.io.File

val functionsCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Functions.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.CString
    
""".trimIndent()

internal fun File.generateCommonFunctions(functions: List<CLibraryModel.Function>) {

    writeText(header)

    functions.forEach { function ->
        writeFunction(function)
    }

}

fun File.writeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}" }
        .joinToString(", ")
    appendText("fun $name($args): $returnType = TODO()\n")
}

