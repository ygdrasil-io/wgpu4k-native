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
    
    
""".trimIndent()

private val headerNative = """
    @file:OptIn(ExperimentalForeignApi::class)
    
    package webgpu
    
    import kotlinx.cinterop.ExperimentalForeignApi
        
    
""".trimIndent()

fun File.generateTypesCommonMain(classes: List<String>) {

    writeText(header)
    classes.forEach {
        appendText("expect class $it\n")
    }
}

fun File.generateTypesNativeMain(classes: List<String>) {

    writeText(headerNative)
    classes.forEach {
        appendText("actual class $it(val value: webgpu.native.$it)\n")
    }
}

fun File.generateTypesAndroidMain(classes: List<String>) {

    writeText(header)
    classes.forEach {
        appendText("actual class $it(val value: Long)\n")
    }
}