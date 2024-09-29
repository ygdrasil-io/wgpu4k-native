import java.io.File

val typesCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Types.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    import kotlin.jvm.JvmInline
    
""".trimIndent()

fun File.generateTypesCommonMain(classes: List<String>) {
    writeText(header)
    classes.forEach {
        appendText("@JvmInline\n")
        appendText("value class $it(val handler: NativeAddress)\n")
    }
}
