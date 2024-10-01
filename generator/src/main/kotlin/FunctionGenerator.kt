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

    functions.forEach { function ->
        writeFunction(function, function.name, function.args)
    }

    objects.forEach { ref ->
        appendText("// methods of ${ref.name.convertToKotlinClassName() }\n")
        ref.methods.forEach { function ->
            writeFunction(
                function,
                "${ref.name}_${function.name}",
                listOf(HeaderModel.Function.Arg("handler", "", "object.${ref.name}")) + function.args
            )
        }
    }
}

fun File.writeFunction(function: HeaderModel.Function, name: String, args: List<HeaderModel.Function.Arg>) {
    val name = name.convertToKotlinFunctionName()
    val returnType = function.returns?.type?.toKotlinType() ?: "Unit"
    val args = args
        .map { "${it.name}: ${it.type.toKotlinType()}" }
        .joinToString(", ")
    appendText("fun $name($args): $returnType = TODO()\n")
}
