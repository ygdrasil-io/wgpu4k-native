package generator

import commonMainBasePath
import convertToKotlinClassName
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import toFunctionKotlinType
import java.io.File

val callbackCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Callbacks.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.Callback
    import ffi.CString
    
    
""".trimIndent()

internal fun File.generateCallback(callbacks: List<CLibraryModel.Callback>) {

    writeText(header)
    callbacks.forEach {
        val name = it.name
        val args = it.members
            .map { (name, type) -> "$name: ${type.toFunctionKotlinType()}" }
            .joinToString(", ")
        appendText("interface ${name} : Callback {\n")
        appendText("\tfun invoke($args)\n")
        appendText("}\n\n")

    }
}