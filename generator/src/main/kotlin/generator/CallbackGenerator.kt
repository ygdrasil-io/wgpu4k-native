package generator

import commonMainBasePath
import convertToKotlinClassName
import disclamer
import domain.YamlModel
import toFunctionKotlinType
import java.io.File

val callbackCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Callbacks.kt")

private val header = """
    $disclamer
    package webgpu
    
    
""".trimIndent()

internal fun File.generateCallback(callbacks: List<YamlModel.Callback>) {

    writeText(header)
    callbacks.forEach {
        val name = it.name.convertToKotlinClassName()
        val args = it.args
            .map { "${it.name}: ${it.type.toFunctionKotlinType()}" }
            .plus("userData1: Long")
            // TODO uncomment when upgrading version
            //.plus("userData2: Long")
            .joinToString(", ")
        appendText("interface ${name}Callback {\n")
        appendText("\tfun invoke($args)\n")
        appendText("}\n\n")

    }
}