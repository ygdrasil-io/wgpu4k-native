package generator

import builder.templateBuilder
import commonMainBasePath
import disclamer
import domain.NativeModel
import java.io.File

val enumerationCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Enumerations.kt")

private val header = """
    $disclamer
    package io.ygdrasil.wgpu
    
    
""".trimIndent()

internal fun File.generateCommonEnumerations(enumerations: List<NativeModel.Enumeration>) {

    writeText(header)

    templateBuilder {
        enumerations.forEach { enumeration ->
            val type = if (enumeration.size == 32) "UInt" else "ULong"
            val valueSuffix = if (enumeration.size == 32) "u" else "uL"
            appendLine("typealias ${enumeration.name} = $type")
            enumeration.values.forEach { (name, value) ->
                appendLine("const val ${enumeration.name}_$name : ${enumeration.name} = ${value}$valueSuffix")
            }
            newLine()
        }
    }.let(::appendText)

}