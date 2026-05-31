package generator

import disclamer
import domain.NativeModel
import generator.function.toAndroidFunctions
import generator.function.toJnaFunctionsInterface
import java.io.File

private val jnaHeader = """
    $disclamer
    package io.ygdrasil.wgpu.android

    
    
""".trimIndent()


private val header = """
    $disclamer
    package io.ygdrasil.wgpu
    
    import io.ygdrasil.kffi.CString
    import io.ygdrasil.kffi.NativeAddress
    import io.ygdrasil.kffi.CallbackHolder
    import io.ygdrasil.kffi.ArrayHolder
    import io.ygdrasil.kffi.adapt
    
    
    
""".trimIndent()

internal fun File.generateAndroidNativeFunctions(functions: List<NativeModel.Function>) =
    resolve("Functions.kt").apply {
        writeText(jnaHeader)

        functions.toJnaFunctionsInterface()
            .let(::appendText)

    }


internal fun File.generateAndroidFunctions(functions: List<NativeModel.Function>) = resolve("Functions.android.kt").apply {
        writeText(header)

        functions.toAndroidFunctions()
            .let(::appendText)

    }