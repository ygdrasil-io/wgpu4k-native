import com.charleskorn.kaml.Yaml
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
    functionsCommonMainFile.generateCommonFunctions(webgpuModel.functions, webgpuModel.objects)

    structuresCommonMainFile.generateCommonStructures(webgpuModel.structs)
    structuresAndroidMainFile.generateJvmStructures(webgpuModel.structs)
    structuresNativeMainFile.generateNativeStructures(webgpuModel.structs)
}

private fun YamlModel.toCModel(): Pair<YamlModel, CLibraryModel> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }
    return this to CLibraryModel(pointers)
}


