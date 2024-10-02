package generator

import androidMainBasePath
import commonMainBasePath
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import nativeMainBasePath
import java.io.File

val structuresCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Structures.kt")

val structuresAndroidMainFile = androidMainBasePath
    .resolve("webgpu")
    .resolve("Structures.android.kt")

val structuresNativeMainFile = nativeMainBasePath
    .resolve("webgpu")
    .resolve("Structures.native.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    import ffi.CallbackHolder
    
    
""".trimIndent()

private val headerNative = """
    $disclamer
    @file:OptIn(ExperimentalForeignApi::class)
    package webgpu
        
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.pointed
    import kotlinx.cinterop.toCPointer
    import kotlinx.cinterop.toKString
    import kotlinx.cinterop.toLong
    
    
""".trimIndent()

internal fun File.generateCommonStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("expect value class ${it.name}(val handler: NativeAddress) {\n")
        it.members.forEach { (name, type, optional) ->
            appendText("\tvar $name: ${type.toFunctionKotlinType()}$optional\n")
        }
        appendText("}\n\n")
    }
}

internal fun File.generateNativeStructures(structures: List<CLibraryModel.Structure>) {
    writeText(headerNative)
    structures.forEach {
        val structureName = it.name
        appendText("actual value class $structureName(actual val handler: NativeAddress) {\n")
        it.members.forEach { (name, type, optional) ->
            val nativeAccessor = "handler.toCPointer<webgpu.native.$structureName>()?.pointed"
            appendText("\tactual var $name: ${type.toFunctionKotlinType()}$optional\n")
            when (type) {
                is CLibraryModel.Reference.CString
                    -> "$nativeAccessor?.${name}?.toKString()"
                is CLibraryModel.Reference.Pointer
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"
                is CLibraryModel.Reference.Callback
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { CallbackHolder(it) }"
                else -> "TODO()"
            }.let { appendText("\t\tget() = $it\n") }
            when (type) {
                is CLibraryModel.Reference.Pointer
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"
                is CLibraryModel.Reference.Callback
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { CallbackHolder(it) }"
                else -> " TODO()"
            }.let { appendText("\t\tset(newValue) { $it } \n\n") }
        }
        appendText("}\n\n")
    }
}

internal fun File.generateJvmStructures(structures: List<CLibraryModel.Structure>) {
    writeText(header)
    structures.forEach {
        appendText("@JvmInline\n")
        appendText("actual value class ${it.name}(actual val handler: NativeAddress) {\n")
        it.members.forEach { (name, type, optional) ->
            appendText("\tactual var $name: ${type.toFunctionKotlinType()}$optional\n")
            when (type) {
                /*is CLibraryModel.Reference.Pointer
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"
                is CLibraryModel.Reference.Callback
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { CallbackHolder(it) }"*/
                else -> "TODO()"
            }.let { appendText("\t\tget() = $it\n") }
            appendText("\t\tset(newValue) = TODO()\n\n")
        }
        appendText("}\n\n")
    }
}