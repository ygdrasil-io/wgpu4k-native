import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlList
import com.charleskorn.kaml.YamlNode
import com.charleskorn.kaml.YamlNull
import com.charleskorn.kaml.yamlMap
import com.charleskorn.kaml.yamlScalar
import converter.toCModel
import domain.YamlModel
import generator.enumerationCommonMainFile
import generator.functionsCommonMainFile
import generator.functionsJvmMainFile
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

    val webgpuCModel = loadWebGPUYaml()
        .merge(loadExtraYaml())
        .toCModel()

    typesCommonMainFile.generateTypesCommonMain(webgpuCModel.pointers)


    commonMainBasePath.apply {
        generateCommonCallback(webgpuCModel.callbacks)
    }

    jvmMainBasePath.apply {
        generateJvmCallback(webgpuCModel.callbacks)
    }

    nativeMainBasePath.apply {
        generateNativeFunctions(webgpuCModel.functions)
        generateNativeCallback(webgpuCModel.callbacks)
    }

    jvmNativeFunctionsMainFile.generateJvmNativeFunctions(webgpuCModel.functions)

    functionsCommonMainFile.generateCommonFunctions(webgpuCModel.functions)
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

fun loadExtraYaml() = basePath.resolve("wgpu4k-native-specs")
    .resolve("src")
    .resolve("jvmMain")
    .resolve("resources")
    .resolve("extra.yml")
    .readText()
    .let { text -> parser.decodeFromString(YamlModel.serializer(), text) }

fun loadWebGPUYaml() = basePath.resolve("wgpu4k-native-specs")
    .resolve("src")
    .resolve("jvmMain")
    .resolve("resources")
    .resolve("webgpu.yml")
    .readText()
    .let { text -> parser.decodeFromString(YamlModel.serializer(), text)}

val parser = Yaml(
    configuration = Yaml.default.configuration.copy(strictMode = false)
)

