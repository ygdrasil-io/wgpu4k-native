import java.io.File

val functionsCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Functions.kt")

private val header = """
    $disclamer
    package webgpu
    
    
""".trimIndent()

internal fun File.generateCommonFunctions(functions: List<HeaderModel.Function>, objects: List<HeaderModel.Object>) {

    writeText(header)
    (functions.map { it.name to it } +
            objects.flatMap { ref -> ref.methods.map { "${ref.name}_${it.name}" to it } })
        .map { (name, function) -> name.convertToKotlinFunctionName() to function }
        .forEach { (name, function) ->
        val returnType = function.returns?.type?.toKotlinType() ?: "Unit"
        val args = function.args
            .map { "${it.name}: ${it.type.toKotlinType()}" }
            .joinToString(", ")
        appendText("fun $name($args): $returnType = TODO()\n")
    }
}