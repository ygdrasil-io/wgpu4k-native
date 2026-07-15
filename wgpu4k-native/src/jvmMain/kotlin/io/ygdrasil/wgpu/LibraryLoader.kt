package io.ygdrasil.wgpu

import io.ygdrasil.kffi.findOrThrow as findKffiSymbol
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.foreign.MemorySegment
import java.nio.file.Files
import java.nio.file.StandardCopyOption

private const val WGPU_NATIVE_VERSION = "v29.0.0.0"

internal class NativeLibraryLoadState(
    private val loadOperation: () -> Unit,
) {
    @Volatile
    var isLoaded: Boolean = false
        private set

    fun load() {
        if (isLoaded) return
        synchronized(this) {
            if (isLoaded) return
            loadOperation()
            isLoaded = true
        }
    }
}

object LibraryLoader {
    private val state = NativeLibraryLoadState(::exportAndLoadLibrary)

    fun load() = state.load()

    internal val isLoaded: Boolean
        get() = state.isLoaded
}

internal fun findWgpuSymbol(symbol: String): MemorySegment {
    LibraryLoader.load()
    return findKffiSymbol(symbol)
}

private fun exportAndLoadLibrary() {
    val libraryPath = findLibraryPath()
    when (WgpuPlatform.os) {
        WgpuOs.Windows -> loadFromLibraryPath(libraryPath, "", "dll")
        WgpuOs.MacOs -> loadFromLibraryPath(libraryPath, "lib", "dylib")
        WgpuOs.Linux -> {
            val libraryFile = Files.createTempFile("wgpu", "lib").toFile()
            println(libraryFile.absolutePath)
            extractResourceToTemp(libraryPath, libraryFile)
            println("will load library at path ${libraryFile.absolutePath}")
            System.load(libraryFile.absolutePath)
        }
    }
}

private fun loadFromLibraryPath(
    libraryPath: String,
    prefix: String,
    extension: String,
) {
    val libraryFiles = System.getProperty("java.library.path")
        .split(File.pathSeparator)
        .map(::File)
        .map { it.resolve("${prefix}WGPU-$WGPU_NATIVE_VERSION.$extension") }
    val libraryFile = libraryFiles.firstOrNull(File::exists) ?: libraryFiles.firstOrNull { path ->
        extractResourceToTemp(libraryPath, path)
    } ?: error("Could not find temporary resource for path: $libraryPath")
    println("will load library at path ${libraryFile.absolutePath}")
    System.loadLibrary(libraryFile.nameWithoutExtension.removePrefix("lib"))
}

private fun findLibraryPath(): String {
    val os = when (WgpuPlatform.os) {
        WgpuOs.Linux -> "linux"
        WgpuOs.Windows -> "win32"
        WgpuOs.MacOs -> "darwin"
    }
    val libraryName = when (WgpuPlatform.os) {
        WgpuOs.Linux -> "libWGPU.so"
        WgpuOs.Windows -> "WGPU.dll"
        WgpuOs.MacOs -> "libWGPU.dylib"
    }
    val architecture = when (WgpuPlatform.architecture) {
        WgpuArchitecture.X86_64 -> "x86-64"
        WgpuArchitecture.AARCH64 -> when (WgpuPlatform.os) {
            WgpuOs.Windows -> error("aarch64 not supported on windows")
            else -> "aarch64"
        }
    }
    return "/$os-$architecture/$libraryName"
}

private fun extractResourceToTemp(fileOnClasspath: String, target: File): Boolean {
    println("will extract library to path ${target.absolutePath}")
    try {
        val resource: InputStream = LibraryLoader::class.java.getResourceAsStream(fileOnClasspath)
            ?: error("Could not find file $fileOnClasspath on the classpath")
        resource.use {
            Files.copy(it, target.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
    } catch (exception: IOException) {
        println("fail to extract to path ${target.absolutePath} with reason ${exception.message}")
        return false
    }
    return true
}

private enum class WgpuOs {
    Linux,
    Windows,
    MacOs,
}

private enum class WgpuArchitecture {
    X86_64,
    AARCH64,
}

private object WgpuPlatform {
    val os: WgpuOs
        get() = System.getProperty("os.name").let { name ->
            when {
                arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } -> WgpuOs.Linux
                arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } -> WgpuOs.MacOs
                name.startsWith("Windows") -> WgpuOs.Windows
                else -> error("Unrecognized or unsupported operating system.")
            }
        }

    val architecture: WgpuArchitecture
        get() = when (val architecture = System.getProperty("os.arch")) {
            "aarch64" -> WgpuArchitecture.AARCH64
            "x86_64", "amd64" -> WgpuArchitecture.X86_64
            else -> error("Unrecognized or unsupported architecture $architecture.")
        }
}
