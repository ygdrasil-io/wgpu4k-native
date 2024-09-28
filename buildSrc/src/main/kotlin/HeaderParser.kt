import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File


fun main() {
    println(File(".").absoluteFile)

    val webgpuModel = File(".").resolve("webgpu-headers")
        .resolve("webgpu.yml")
        .readText()
        .let { Yaml.default.decodeFromString(HeaderModel.serializer(), it) }

    println(webgpuModel)
}

@Serializable
data class HeaderModel(
    val copyright: String,
    val name: String,
    val enum_prefix: String,
    val constants: List<Constant>,
    val typedefs: List<String>,
    val enums: List<Enum>,
    val bitflags: List<String>
    val structs: List<String>

) {
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