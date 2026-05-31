package generator

import disclamer
import domain.NativeModel
import generator.structure.toAndroidStructure
import generator.structure.toJnaStructure
import java.io.File

private val headerAndroidJna = """
    $disclamer
    package io.ygdrasil.wgpu.android
    
    import io.ygdrasil.kffi.NativeAddress

    
""".trimIndent()

private val headerAndroid = """
    $disclamer
    package io.ygdrasil.wgpu
    
    import io.ygdrasil.kffi.NativeAddress
    import io.ygdrasil.kffi.CallbackHolder
    import io.ygdrasil.kffi.CString
    import io.ygdrasil.kffi.ArrayHolder
    import io.ygdrasil.kffi.C_LONG
    import io.ygdrasil.kffi.C_POINTER
    import io.ygdrasil.kffi.C_SHORT
    import io.ygdrasil.kffi.C_INT
    import io.ygdrasil.kffi.C_FLOAT
    import io.ygdrasil.kffi.C_DOUBLE
    import io.ygdrasil.kffi.CStructure
    import io.ygdrasil.kffi.MemoryAllocator
    import io.ygdrasil.kffi.toAddress
    import java.lang.foreign.AddressLayout
    
    
""".trimIndent()

fun File.generateAndroidStructures(structures: List<NativeModel.Structure>) = this.apply {
    resolve("Structures.android.kt").apply {
        writeText(headerAndroid)
        structures.map(NativeModel.Structure::toAndroidStructure)
            .forEach(::appendText)
    }
    resolve("Structures.kt").apply {
        writeText(headerAndroidJna)
        structures.map(NativeModel.Structure::toJnaStructure)
            .forEach(::appendText)
    }
}



