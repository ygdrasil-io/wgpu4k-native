import java.io.File

val typesCommonMainFile = sourceBasePath
    .resolve("commonMain")
    .resolve("kotlin")
    .resolve("webgpu")
    .resolve("Types.kt")

val typesNativeFile = sourceBasePath
    .resolve("nativeMain")
    .resolve("kotlin")
    .resolve("webgpu")
    .resolve("Types.native.kt")

val typesAndroidFile = sourceBasePath
    .resolve("androidMain")
    .resolve("kotlin")
    .resolve("webgpu")
    .resolve("Types.android.kt")

private val header = """
    package webgpu
    
    import ffi.NativeAddress
    import kotlin.jvm.JvmInline
    
""".trimIndent()

private val headerNative = """
    @file:OptIn(ExperimentalForeignApi::class)
    
    package webgpu
    
    import kotlinx.cinterop.ExperimentalForeignApi
        
    
""".trimIndent()

fun File.generateTypesCommonMain(classes: List<String>) {

    writeText(header)
    classes.forEach {
        appendText("@JvmInline\n")
        appendText("value class $it(val handler: NativeAddress)\n")
    }
}
