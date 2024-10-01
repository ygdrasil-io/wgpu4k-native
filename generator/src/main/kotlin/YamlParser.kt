import com.charleskorn.kaml.Yaml
import converter.toCModel
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


    typesCommonMainFile.generateTypesCommonMain(webgpuCModel.pointers)

    callbackCommonMainFile.generateCallback(webgpuModel.callbacks)

    functionsCommonMainFile.generateCommonFunctions(webgpuCModel.functions)

    structuresCommonMainFile.generateCommonStructures(webgpuCModel.structures)
    structuresAndroidMainFile.generateJvmStructures(webgpuCModel.structures)
    structuresNativeMainFile.generateNativeStructures(webgpuCModel.structures)

    enumerationCommonMainFile.generateCommonEnumerations(webgpuCModel.enumerations)
}





