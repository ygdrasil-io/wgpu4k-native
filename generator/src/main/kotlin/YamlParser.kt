import com.charleskorn.kaml.Yaml
import converter.toCModel
import domain.YamlModel
import generator.enumerationCommonMainFile
import generator.functionsCommonMainFile
import generator.functionsJvmMainFile
import generator.functionsNativeMainFile
import generator.generateAndroidCallback
import generator.generateAndroidFunctions
import generator.generateAndroidNativeFunctions
import generator.generateAndroidStructures
import generator.generateCommonCallback
import generator.generateCommonEnumerations
import generator.generateCommonFunctions
import generator.generateCommonStructures
import generator.generateJvmCallback
import generator.generateJvmFunctions
import generator.generateJvmNativeFunctions
import generator.generateJvmStructures
import generator.generateNativeCallback
import generator.generateNativeFunctions
import generator.generateNativeStructures
import generator.generateTypesCommonMain
import generator.jvmNativeFunctionsMainFile
import generator.structuresCommonMainFile
import generator.structuresJvmMainFile
import generator.structuresNativeMainFile
import generator.typesCommonMainFile
import java.io.File

val basePath = File(".")
val sourceBasePath = basePath
    .resolve("wgpu4k-native")
    .resolve("src")
val commonMainBasePath = sourceBasePath
    .resolve("commonMain")
    .resolve("kotlin")
val androidMainBasePath = sourceBasePath
    .resolve("androidMain")
    .resolve("kotlin")
val jvmMainBasePath = sourceBasePath
    .resolve("jvmMain")
    .resolve("kotlin")
val nativeMainBasePath = sourceBasePath
    .resolve("nativeMain")
    .resolve("kotlin")

fun main() {
    println(File(".").absoluteFile)

    val webgpuCModel = basePath.resolve("webgpu-headers")
        .resolve("webgpu.yml")
        .readText()
        .let { Yaml.default.decodeFromString(YamlModel.serializer(), it) }
        .toCModel()


    typesCommonMainFile.generateTypesCommonMain(webgpuCModel.pointers)


    commonMainBasePath.apply {
        generateCommonCallback(webgpuCModel.callbacks)
    }

    jvmMainBasePath.apply {
        generateJvmCallback(webgpuCModel.callbacks)
    }

    nativeMainBasePath.apply {
        generateNativeCallback(webgpuCModel.callbacks)
    }

    jvmNativeFunctionsMainFile.generateJvmNativeFunctions(webgpuCModel.functions)

    functionsCommonMainFile.generateCommonFunctions(webgpuCModel.functions)
    functionsNativeMainFile.generateNativeFunctions(webgpuCModel.functions)
    functionsJvmMainFile.generateJvmFunctions(webgpuCModel.functions)

    structuresCommonMainFile.generateCommonStructures(webgpuCModel.structures)



    structuresJvmMainFile.generateJvmStructures(webgpuCModel.structures)
    structuresNativeMainFile.generateNativeStructures(webgpuCModel.structures)

    enumerationCommonMainFile.generateCommonEnumerations(webgpuCModel.enumerations)

    androidMainBasePath.apply {
        generateAndroidCallback(webgpuCModel.callbacks)
        generateAndroidStructures(webgpuCModel.structures)
        generateAndroidNativeFunctions(webgpuCModel.functions)
        generateAndroidFunctions(webgpuCModel.functions)
    }
}





