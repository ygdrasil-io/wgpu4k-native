package io.ygdrasil.wgpu

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit

class JvmBootstrapIsolatedTest : FreeSpec({
    "first generated downcall loads wgpu-native in a fresh JVM" {
        runBootstrapProbe("downcall").shouldSucceed()
    }

    "registration-based log callback loads wgpu-native before explicit bootstrap" {
        runBootstrapProbe("callback").shouldSucceed()
    }

    "concurrent generated first accesses load wgpu-native once" {
        val result = runBootstrapProbe("concurrent")
        result.shouldSucceed()
        result.output.lineSequence().count {
            it.startsWith("will load library at path ")
        } shouldBe 1
    }
})

private data class ProbeResult(
    val completed: Boolean,
    val exitCode: Int,
    val output: String,
)

private fun runBootstrapProbe(mode: String): ProbeResult {
    val javaExecutable = Path.of(
        System.getProperty("java.home"),
        "bin",
        if (System.getProperty("os.name").startsWith("Windows")) "java.exe" else "java",
    )
    val libraryDirectory = Files.createTempDirectory("wgpu4k-bootstrap-")
    return try {
        val process = ProcessBuilder(
            javaExecutable.toString(),
            "--enable-native-access=ALL-UNNAMED",
            "-Djava.library.path=$libraryDirectory",
            "-cp",
            System.getProperty("java.class.path"),
            JvmBootstrapProbe::class.java.name,
            mode,
        ).redirectErrorStream(true).start()
        val completed = process.waitFor(30, TimeUnit.SECONDS)
        if (!completed) process.destroyForcibly().waitFor()
        ProbeResult(
            completed = completed,
            exitCode = if (completed) process.exitValue() else -1,
            output = process.inputStream.bufferedReader().readText(),
        )
    } finally {
        libraryDirectory.toFile().deleteRecursively()
    }
}

private fun ProbeResult.shouldSucceed() {
    withClue(output) {
        completed shouldBe true
        exitCode shouldBe 0
    }
}
