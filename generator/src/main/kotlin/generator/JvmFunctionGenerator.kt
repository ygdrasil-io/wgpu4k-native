package generator

import disclamer
import domain.NativeModel
import generator.function.toJvmFunctionsInterface
import java.io.File

private val header = """
    $disclamer
    package io.ygdrasil.wgpu
    
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.Linker
    import io.ygdrasil.kffi.findOrThrow
    import io.ygdrasil.kffi.C_POINTER
    import io.ygdrasil.kffi.C_INT
    import io.ygdrasil.kffi.C_LONG
    import io.ygdrasil.kffi.C_FLOAT
    import io.ygdrasil.kffi.NativeAddress
    import java.lang.foreign.FunctionDescriptor
    
    
""".trimIndent()

internal fun File.generateJvmNativeFunctions(functions: List<NativeModel.Function>)
 = resolve("Functions.kt").apply {
    writeText(header)
    functions.toJvmFunctionsInterface()
        .let(::appendText)
}
