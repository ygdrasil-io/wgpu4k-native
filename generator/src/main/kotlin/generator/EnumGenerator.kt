package generator

import commonMainBasePath
import disclamer
import domain.CLibraryModel
import java.io.File

val enumerationCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Enumerations.kt")

private val header = """
    $disclamer
    package webgpu
    
    
""".trimIndent()

internal fun File.generateCommonEnumerations(enumerations: List<CLibraryModel.Enumeration>) {

    writeText(header)

    enumerations.forEach { function ->
        appendText("typealias ${function.name} = UInt\n")
    }

}