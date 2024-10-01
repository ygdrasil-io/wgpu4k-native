package generator

import androidMainBasePath
import commonMainBasePath
import convertToKotlinClassName
import disclamer
import domain.CLibraryModel
import domain.YamlModel
import domain.toFunctionKotlinType
import nativeMainBasePath
import java.io.File

val structuresCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Structures.kt")

val structuresAndroidMainFile = androidMainBasePath
    .resolve("webgpu")
    .resolve("Structures.android.kt")

val structuresNativeMainFile = nativeMainBasePath
    .resolve("webgpu")
    .resolve("Structures.native.kt")


private val header = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    
    
""".trimIndent()

internal fun File.generateCommonStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("expect value class ${it.name}(val handler: NativeAddress) {\n")
        appendText("}\n\n")
    }
}

internal fun File.generateNativeStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("actual value class ${it.name}(actual val handler: NativeAddress) {\n")
        it.members.forEach { (name, type) ->
            appendText("\tval $name: ${type.toFunctionKotlinType()}\n\t\tget() = TODO()\n\n")
        }
        appendText("}\n\n")
    }
}

internal fun File.generateJvmStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("@JvmInline\n")
        appendText("actual value class ${it.name}(actual val handler: NativeAddress) {\n")
        appendText("}\n\n")
    }
}