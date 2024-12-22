import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.nio.file.Files

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
    id("com.android.library")
    alias(libs.plugins.kotest)
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
val jvmLibResourcesDirectory = project.file("build").resolve("generated").resolve("resources")

kotlin {


    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64()
    )

    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }

        publishLibraryVariants("release", "debug")
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }
    }

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("webgpu") {
                packageName = "webgpu.native"
                header(buildNativeResourcesDirectory.resolve("wgpu.h"))
            }
        }
    }

    compilerOptions {
        //allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        androidMain {
            dependencies {
                val jna = libs.jna.get()
                api("${jna.module.group}:${jna.module.name}:${jna.versionConstraint}:@aar")
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kotest)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvmMain {
            sourceSets {
                resources.srcDirs(jvmLibResourcesDirectory.absolutePath)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation(libs.kotlin.reflect)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
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

    /*testOptions {
        unitTests.all {
            it.useJUnitPlatform()

        }
    }*/


    buildTypes {
        getByName("release") {

            isMinifyEnabled = true
            isJniDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isJniDebuggable = true
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

fun jniBasePath() = buildNativeResourcesDirectory.resolve("libsDebug")

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

