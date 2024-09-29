import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

val basePath = File(".")
val sourceBasePath = basePath
    .resolve("wgpu4k-native")
    .resolve("src")
val commonMainBasePath = sourceBasePath
    .resolve("commonMain")
    .resolve("kotlin")

fun main() {
    println(File(".").absoluteFile)

    val webgpuModel = basePath.resolve("webgpu-headers")
        .resolve("webgpu.yml")
        .readText()
        .let { Yaml.default.decodeFromString(HeaderModel.serializer(), it) }

    val types = (webgpuModel.functions.flatMap { it.args }.map { it.type } +
            webgpuModel.objects.flatMap { it.methods }.flatMap { it.args }.map { it.type } +
            webgpuModel.structs.flatMap { it.members }.map { it.type } +
            webgpuModel.callbacks.flatMap { it.args }.map { it.type })
        .toSet()

    types.forEach { println(it) }


    //println(typesCommonMainFile.absolutePath)
    webgpuModel.objects.map { it.name.convertToKotlinClassName() }
        .also {
            typesCommonMainFile.generateTypesCommonMain(it)
            //typesAndroidFile.generateTypesAndroidMain(it)
            //typesNativeFile.generateTypesNativeMain(it)
        }
    callbackCommonMainFile.generateCallback(webgpuModel.callbacks)
}



@Serializable
data class HeaderModel(
    val copyright: String,
    val name: String,
    val enum_prefix: String,
    val constants: List<Constant>,
    val typedefs: List<String>,
    val enums: List<Enum>,
    val bitflags: List<Bitflag>,
    val structs: List<Struct>,
    val callbacks: List<Callback>,
    val functions: List<Function>,
    val objects: List<Object>,
) {

    @Serializable
    data class Object(
        val name: String,
        val doc: String,
        val methods: List<Function>,
    )

    @Serializable
    data class Function(
        val name: String,
        val doc: String,
        val args: List<Arg> = listOf(),
        val returns: Return? = null,
        val callback: String? = null,
    ) {
        @Serializable
        data class Return(
            val type: String,
            val doc: String,
            val pointer: String? = null,
        )

        @Serializable
        data class Arg(
            val name: String,
            val doc: String,
            val type: String,
            val pointer: String? = null,
            val optional: Boolean = false,
        )
    }

    @Serializable
    data class Callback(
        val name: String,
        val doc: String,
        val style: String,
        val args: List<Arg>,
    ) {
        @Serializable
        data class Arg(
            val name: String,
            val doc: String,
            val type: String,
            val pointer: String? = null,
        )

    }

    @Serializable
    data class Struct(
        val name: String,
        val doc: String,
        val type: String,
        val members: List<Member>,
        val free_members: Boolean = false,
        val extends: List<String> = listOf()
    ) {
        @Serializable
        data class Member(
            val name: String,
            val doc: String,
            val type: String,
            val optional: Boolean = false,
            val pointer: String? = null
        )
    }

    @Serializable
    data class Bitflag(
        val name: String,
        val doc: String,
        val entries: List<Entry>,
    ) {
        @Serializable
        data class Entry(
            val name: String,
            val doc: String,
            val value_combination: List<String>? = listOf(),
        )
    }

    @Serializable
    data class Constant(
        val name: String,
        val value: String,
        val doc: String
    )

    @Serializable
    data class Enum(
        val name: String,
        val doc: String,
        val entries: List<Entry>,
    ) {
        @Serializable
        data class Entry(
            val name: String,
            val doc: String,
            val value: Int? = null,
        )
    }
}