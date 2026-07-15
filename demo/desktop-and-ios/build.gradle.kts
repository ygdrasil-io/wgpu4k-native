import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import javax.imageio.ImageIO
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

plugins {
    `kotlin-multiplatform`
}

val os = DefaultNativePlatform.getCurrentOperatingSystem()
val nativeTargetName = when {
    project.findProperty("demo.nativeTarget") == "mingwX64" -> "mingwX64"
    os.isLinux && System.getProperty("os.arch") != "aarch64" -> "linuxX64"
    os.isMacOsX && System.getProperty("os.arch") == "aarch64" -> "macosArm64"
    os.isMacOsX -> "macosX64"
    os.isWindows -> "mingwX64"
    else -> null
}

kotlin {

    val xcframeworkName = "WgpuApp"
    val xcf = XCFramework(xcframeworkName)

    if (os.isMacOsX) {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "WgpuApp"
                isStatic = true
                xcf.add(this)
                binaryOption("bundleId", "io.ygdrasil.webgpu.$xcframeworkName")
            }
        }
    }


    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val nativeTarget = when {
        // No toolchain on this architecture
        os.isLinux && isArm64 -> null.also { println("Linux native Arm64 not yet supported") }
        nativeTargetName == "linuxX64" -> linuxX64()
        nativeTargetName == "macosArm64" -> macosArm64()
        nativeTargetName == "macosX64" -> macosX64()
        nativeTargetName == "mingwX64" -> mingwX64()
        else -> null // Not supported
    }

    if (nativeTarget != null) {
        with(nativeTarget) {

            binaries {
                executable {
                    entryPoint = "io.ygdrasil.wgpu.main"
                }
            }
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_24
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {

        commonMain {
            dependencies {
                implementation(project(":demo:common"))
            }
        }

        val desktopMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.ygdrasil.glfw.native)
            }
        }

        macosMain {
            dependsOn(desktopMain)
        }

        linuxMain {
            dependsOn(desktopMain)
        }

        mingwMain {
            dependsOn(desktopMain)
        }

        jvmMain {
            dependencies {
                implementation("org.graphiks.kadre:kadre:1.0.0")
                api(libs.rococoa)
                api(libs.jnaPlatform)
                val lwjglVersion = "3.3.6"
                api("org.lwjgl:lwjgl:$lwjglVersion")
                api("org.lwjgl:lwjgl-glfw:$lwjglVersion")
                listOf(
                    "natives-windows",
                    "natives-macos",
                    "natives-macos-arm64",
                    "natives-linux",
                    "natives-linux-arm64"
                ).forEach { dependencyType ->
                    runtimeOnly("org.lwjgl:lwjgl:$lwjglVersion:$dependencyType")
                    runtimeOnly("org.lwjgl:lwjgl-glfw:$lwjglVersion:$dependencyType")
                }
            }
        }
    }
}

tasks.register<JavaExec>("runJvm") {
    group = "run"
    mainClass = "io.ygdrasil.wgpu.MainKt"
    jvmArgs(
        if (Platform.os == Os.MacOs) {
            listOf(
                "-XstartOnFirstThread",
                "--add-opens=java.base/java.lang=ALL-UNNAMED",
                "--enable-native-access=ALL-UNNAMED"
            )
        } else {
            listOf(
                "--add-opens=java.base/java.lang=ALL-UNNAMED",
                "--enable-native-access=ALL-UNNAMED"
            )
        }
    )
    classpath = sourceSets["jvmMain"].runtimeClasspath
}

tasks.register<JavaExec>("runJvmHeadless") {
    group = "run"
    mainClass = "io.ygdrasil.wgpu.HeadlessMainKt"
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--enable-native-access=ALL-UNNAMED"
    )
    classpath = sourceSets["jvmMain"].runtimeClasspath
}

val callbackStressJavaLauncher = javaToolchains.launcherFor {
    languageVersion = JavaLanguageVersion.of(25)
}

tasks.register("verifyJvmCallbackStress") {
    group = "verification"
    description = "Runs the bounded JVM WebGPU callback stress scenario."
    dependsOn(":wgpu4k-native:fetch-native-dependencies", "jvmMainClasses")
    doLast {
        runBoundedProcess(
            label = "JVM callback stress",
            commandLine = listOf(
                callbackStressJavaLauncher.get().executablePath.asFile.absolutePath,
                "--add-opens=java.base/java.lang=ALL-UNNAMED",
                "--enable-native-access=ALL-UNNAMED",
                "-cp",
                sourceSets["jvmMain"].runtimeClasspath.asPath,
                "io.ygdrasil.wgpu.HeadlessMainKt",
                "--callback-stress",
            ),
            workingDirectory = projectDir,
            outputLogger = logger,
        )
    }
}

tasks.register("verifyJvmCapture") {
    group = "verification"
    description = "Runs the bounded JVM WebGPU capture verification."
    dependsOn(":wgpu4k-native:fetch-native-dependencies", "jvmMainClasses")
    val outputFile = layout.buildDirectory.file("capture/triangle.png")
    outputs.file(outputFile)
    outputs.upToDateWhen { false }
    doLast {
        val file = outputFile.get().asFile
        file.parentFile.mkdirs()
        if (file.exists()) check(file.delete()) { "Unable to remove stale JVM capture: ${file.absolutePath}" }
        runBoundedProcess(
            label = "JVM capture",
            commandLine = listOf(
                callbackStressJavaLauncher.get().executablePath.asFile.absolutePath,
                "--add-opens=java.base/java.lang=ALL-UNNAMED",
                "--enable-native-access=ALL-UNNAMED",
                "-cp",
                sourceSets["jvmMain"].runtimeClasspath.asPath,
                "io.ygdrasil.wgpu.MainKt",
                "--verify-capture",
                file.absolutePath,
            ),
            workingDirectory = projectDir,
            outputLogger = logger,
        )
    }
}

tasks.register("verifyJvmHeadlessRender") {
    group = "verification"
    dependsOn("runJvmHeadless")
    val outputFile = layout.buildDirectory.file("headless/triangle.png")
    inputs.file(outputFile)
    doLast {
        val file = outputFile.get().asFile
        check(file.isFile) { "Missing JVM headless render output: ${file.absolutePath}" }
        val image = ImageIO.read(file) ?: error("Unable to read JVM headless render output: ${file.absolutePath}")
        verifyHeadlessPixels(
            width = image.width,
            height = image.height,
            pixels = IntArray(image.width * image.height) { index ->
                image.getRGB(index % image.width, index / image.width)
            },
            source = file.absolutePath
        )
    }
}

if (nativeTargetName != null) {
    val capitalizedNativeTargetName = nativeTargetName.replaceFirstChar { it.uppercaseChar() }
    tasks.register<Exec>("runNativeHeadless") {
        group = "run"
        dependsOn("linkDebugExecutable$capitalizedNativeTargetName")
        workingDir = projectDir
        val executableName = if (nativeTargetName == "mingwX64") "desktop-and-ios.exe" else "desktop-and-ios.kexe"
        commandLine(
            layout.buildDirectory.file("bin/$nativeTargetName/debugExecutable/$executableName").get().asFile.absolutePath,
            "--headless"
        )
    }

    tasks.register("verifyNativeHeadlessRender") {
        group = "verification"
        dependsOn("runNativeHeadless")
        val outputFile = layout.buildDirectory.file("headless/triangle.ppm")
        inputs.file(outputFile)
        doLast {
            val file = outputFile.get().asFile
            check(file.isFile) { "Missing Native headless render output: ${file.absolutePath}" }
            verifyPpm(file)
        }
    }

    tasks.register("verifyNativeCallbackStress") {
        group = "verification"
        description = "Runs the bounded host Kotlin/Native WebGPU callback stress scenario."
        dependsOn(":wgpu4k-native:fetch-native-dependencies", "linkDebugExecutable$capitalizedNativeTargetName")
        doLast {
            val executableName = if (nativeTargetName == "mingwX64") "desktop-and-ios.exe" else "desktop-and-ios.kexe"
            runBoundedProcess(
                label = "Native callback stress ($nativeTargetName)",
                commandLine = listOf(
                    layout.buildDirectory
                        .file("bin/$nativeTargetName/debugExecutable/$executableName")
                        .get()
                        .asFile
                        .absolutePath,
                    "--callback-stress",
                ),
                workingDirectory = projectDir,
                outputLogger = logger,
            )
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

fun verifyPpm(file: File) {
    val bytes = file.readBytes()
    var cursor = 0

    fun nextToken(): String {
        while (cursor < bytes.size && bytes[cursor].toInt().toChar().isWhitespace()) cursor++
        val start = cursor
        while (cursor < bytes.size && !bytes[cursor].toInt().toChar().isWhitespace()) cursor++
        return bytes.decodeToString(start, cursor)
    }

    check(nextToken() == "P6") { "Unexpected PPM magic in ${file.absolutePath}" }
    val width = nextToken().toInt()
    val height = nextToken().toInt()
    check(nextToken().toInt() == 255) { "Unexpected PPM max value in ${file.absolutePath}" }
    while (cursor < bytes.size && bytes[cursor].toInt().toChar().isWhitespace()) cursor++

    val expectedRgbBytes = width * height * 3
    check(bytes.size - cursor == expectedRgbBytes) {
        "Unexpected PPM pixel data size in ${file.absolutePath}: ${bytes.size - cursor}, expected $expectedRgbBytes"
    }

    val pixels = IntArray(width * height) { index ->
        val offset = cursor + index * 3
        val r = bytes[offset].toInt() and 0xFF
        val g = bytes[offset + 1].toInt() and 0xFF
        val b = bytes[offset + 2].toInt() and 0xFF
        (0xFF shl 24) or (r shl 16) or (g shl 8) or b
    }
    verifyHeadlessPixels(width, height, pixels, file.absolutePath)
}

fun verifyHeadlessPixels(width: Int, height: Int, pixels: IntArray, source: String) {
    check(width == 64 && height == 64) { "Unexpected headless image size for $source: ${width}x$height" }
    check(pixels.size == width * height) { "Unexpected pixel count for $source: ${pixels.size}" }

    var redPixels = 0
    var greenPixels = 0
    for (pixel in pixels) {
        val alpha = pixel ushr 24
        val red = (pixel ushr 16) and 0xFF
        val green = (pixel ushr 8) and 0xFF
        val blue = pixel and 0xFF
        if (alpha > 240 && red > 180 && green < 100 && blue < 100) redPixels++
        if (alpha > 240 && red < 100 && green > 180 && blue < 100) greenPixels++
    }

    check(redPixels > 64) { "Headless image has too few red triangle pixels in $source: $redPixels" }
    check(greenPixels > 64) { "Headless image has too few green background pixels in $source: $greenPixels" }
}

fun runBoundedProcess(
    label: String,
    commandLine: List<String>,
    workingDirectory: File,
    outputLogger: org.gradle.api.logging.Logger,
) {
    val process = ProcessBuilder(commandLine)
        .directory(workingDirectory)
        .start()
    val stdout = ByteArrayOutputStream()
    val stderr = ByteArrayOutputStream()
    val stdoutReader = thread(name = "$label stdout", isDaemon = true) {
        process.inputStream.use { it.copyTo(stdout) }
    }
    val stderrReader = thread(name = "$label stderr", isDaemon = true) {
        process.errorStream.use { it.copyTo(stderr) }
    }

    fun publishOutput() {
        stdoutReader.join()
        stderrReader.join()
        stdout.toString(Charsets.UTF_8.name()).trimEnd().takeIf(String::isNotEmpty)?.let(outputLogger::lifecycle)
        stderr.toString(Charsets.UTF_8.name()).trimEnd().takeIf(String::isNotEmpty)?.let(outputLogger::error)
    }

    val finished = try {
        process.waitFor(2, TimeUnit.MINUTES)
    } catch (failure: InterruptedException) {
        process.destroyForcibly()
        process.waitFor()
        publishOutput()
        Thread.currentThread().interrupt()
        throw GradleException("$label interrupted", failure)
    }

    if (!finished) {
        process.destroy()
        if (!process.waitFor(5, TimeUnit.SECONDS)) {
            process.destroyForcibly()
            process.waitFor()
        }
        publishOutput()
        throw GradleException("$label timed out after 2 minutes; stdout/stderr above are preserved")
    }
    publishOutput()
    check(process.exitValue() == 0) {
        "$label failed with exit code ${process.exitValue()}; stdout/stderr above are preserved"
    }
}
