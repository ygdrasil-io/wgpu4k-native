import com.charleskorn.kaml.Yaml
import domain.CLibraryModel
import domain.YamlModel
import domain.toCType
import generator.callbackCommonMainFile
import generator.enumerationCommonMainFile
import generator.functionsCommonMainFile
import generator.generateCallback
import generator.generateCommonEnumerations
import generator.generateCommonFunctions
import generator.generateCommonStructures
import generator.generateJvmStructures
import generator.generateNativeStructures
import generator.generateTypesCommonMain
import generator.structuresAndroidMainFile
import generator.structuresCommonMainFile
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
val nativeMainBasePath = sourceBasePath
    .resolve("nativeMain")
    .resolve("kotlin")

/*
typedef struct WGPUChainedStruct {
    struct WGPUChainedStruct const * next;
    WGPUSType sType;
} WGPUChainedStruct WGPU_STRUCTURE_ATTRIBUTE;

typedef struct WGPUChainedStructOut {
    struct WGPUChainedStructOut * next;
    WGPUSType sType;
} WGPUChainedStructOut WGPU_STRUCTURE_ATTRIBUTE;
 */

fun main() {
    println(File(".").absoluteFile)

    val (webgpuModel, webgpuCModel) = basePath.resolve("webgpu-headers")
        .resolve("webgpu.yml")
        .readText()
        .let { Yaml.default.decodeFromString(YamlModel.serializer(), it) }
        .toCModel()

    val types = (webgpuModel.functions.flatMap { it.args }.map { it.type } +
            webgpuModel.objects.flatMap { it.methods }.flatMap { it.args }.map { it.type } +
            webgpuModel.structs.flatMap { it.members }.map { it.type } +
            webgpuModel.callbacks.flatMap { it.args }.map { it.type })
        .toSet()

    //types.forEach { println(it) }
    webgpuModel.structs.map { it.type }.toSet().forEach { println(it) }


    typesCommonMainFile.generateTypesCommonMain(webgpuCModel.pointers)

    callbackCommonMainFile.generateCallback(webgpuModel.callbacks)

    functionsCommonMainFile.generateCommonFunctions(webgpuCModel.functions)

    structuresCommonMainFile.generateCommonStructures(webgpuModel.structs)
    structuresAndroidMainFile.generateJvmStructures(webgpuModel.structs)
    structuresNativeMainFile.generateNativeStructures(webgpuModel.structs)

    enumerationCommonMainFile.generateCommonEnumerations(webgpuCModel.enumerations)
}

private fun YamlModel.toCModel(): Pair<YamlModel, CLibraryModel> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }

    val functions = functions.map {
        CLibraryModel.Function(
            it.name.convertToKotlinFunctionName(),
            it.returns.let { it?.type }.toCType(),
            it.args.map { it.name to it.type.toCType() }
        )
    } + objects.flatMap { reference ->
        reference.methods.map {
            val name = "${reference.name}_${it.name}".convertToKotlinFunctionName()
            val args = listOf(YamlModel.Function.Arg("handler", "", "object.${reference.name}")) + it.args
            CLibraryModel.Function(
                name,
                it.returns.let { it?.type }.toCType(),
                args.map { it.name to it.type.toCType() }
            )
        }
    }

    val enumerations = enums.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName())}

    return this to CLibraryModel(pointers, functions, enumerations)
}




