import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
    id("com.android.library")
    alias(libs.plugins.kotest)
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
val buildNativeResourcesDirectory2 = project.file("..").resolve("headers")

print(buildNativeResourcesDirectory2.resolve("wgpu.h").absolutePath)

kotlin {

    val androidNativeTargets = listOf(
        androidNativeArm64(),
        androidNativeX64()
    )

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64()
    ) + androidNativeTargets

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }

        publishLibraryVariants("release", "debug")
    }

    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }
    }

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("webgpu") {
                packageName = "webgpu.native"
                header(buildNativeResourcesDirectory2.resolve("wgpu.h"))
            }
        }
    }

    androidNativeTargets.forEach { target ->
        target.binaries {
            sharedLib {
                baseName = "wgpu4k"
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        androidMain {
            dependencies {
                val jna = libs.jna.get()
                implementation("${jna.module.group}:${jna.module.name}:${jna.versionConstraint}:@aar")
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation(libs.kotlin.reflect)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

android {
    namespace = "io.ygdrasil.nativeWgpu4k"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_22
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs(jniBasePath().absolutePath)
        }
    }

}

configureDownloadTasks {
    baseUrl = "${project.properties["wgpu.base.url"]}${libs.versions.wgpu.get()}/"

    /*** Macos ***/
    download("wgpu-macos-aarch64-release.zip") {
        //extract("webgpu.h", buildNativeResourcesDirectory.resolve("webgpu.h"))
        //extract("wgpu.h", buildNativeResourcesDirectory.resolve("wgpu.h"))
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.a"))
    }
    download("wgpu-macos-x86_64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-x64").resolve("libWGPU.a"))
    }

    /*** Windows ***/
    download("wgpu-windows-x86_64-gnu-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("windows-x64").resolve("wgpu.a"))
    }

    /*** Linux ***/
    download("wgpu-linux-x86_64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-x64").resolve("libWGPU.a"))
    }
    download("wgpu-linux-aarch64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.a"))
    }

    /*** Android ***/
    download("wgpu-android-x86_64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("android-x64").resolve("libWGPU.a"))
    }
    download("wgpu-android-aarch64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("android-aarch64").resolve("libWGPU.a"))
    }

    /*** iOS ***/
    download("wgpu-ios-x86_64-simulator-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-simulator-x64").resolve("libWGPU.a"))
    }
    download("wgpu-ios-aarch64-simulator-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-simulator-aarch64").resolve("libWGPU.a"))
    }
    download("wgpu-ios-aarch64-release.zip") {
        extract("lib/libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-aarch64").resolve("libWGPU.a"))
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

tasks.create("copyJniLibraries") {
    dependsOn("androidNativeArm64Binaries")
    dependsOn("androidNativeX64Binaries")
    doFirst {
        copyJniLibraries()
    }
}

tasks.findByName("assemble")?.dependsOn("copyJniLibraries")

fun copyJniLibraries() {
    val libraryFullName = "libwgpu4k.so"
    filesToCopy().forEach { (source, target) ->
        target.mkdirs()
        target.resolve(libraryFullName)
            .also { fileTarget ->
                source.resolve(libraryFullName).copyTo(fileTarget, overwrite = true)
            }
    }
}

fun nativeBasePath() = project.projectDir.resolve("build").resolve("bin")
fun jniBasePath() = nativeBasePath().resolve("libs")

fun filesToCopy() = listOf(
    nativeBasePath().resolve("androidNativeArm64").resolve("releaseShared")
            to jniBasePath().resolve("arm64-v8a"),
    nativeBasePath().resolve("androidNativeX64").resolve("releaseShared")
            to jniBasePath().resolve("x86_64")
)

if (Platform.os == Os.MacOs) {
    tasks.findByName("linkDebugTestMingwX64")?.apply { enabled = false }
    tasks.findByName("mingwX64Test")?.apply { enabled = false }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        isFailOnNoMatchingTests = false
    }
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

