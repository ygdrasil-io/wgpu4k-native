import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.zip.ZipFile

plugins {
    `kotlin-multiplatform`
    id(libs.plugins.android.application.get().pluginId)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }

        android {
            compileSdk = 36

            defaultConfig {
                applicationId = "io.ygdrasil.wgpu.app.native"

                minSdk = 28
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    isDebuggable = true
                }
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                    isDebuggable = true
                    isMinifyEnabled = false
                }
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    excludes += "META-INF/INDEX.LIST"
                    excludes += "**/**.sha1"
                }

            }
            namespace = "io.ygdrasil.wgpu"

            sourceSets {
                getByName("debug") {
                    jniLibs.srcDirs(file("../common/build/androidJniLibs/debug"))
                }
                getByName("release") {
                    jniLibs.srcDirs(file("../common/build/androidJniLibs/release"))
                }
            }
        }

    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.matching { it.name == "mergeDebugJniLibFolders" }.configureEach {
    dependsOn(
        ":demo:common:linkDebugSharedAndroidNativeArm64",
        ":demo:common:linkDebugSharedAndroidNativeX64",
    )
}

tasks.matching { it.name == "mergeReleaseJniLibFolders" }.configureEach {
    dependsOn(
        ":demo:common:linkReleaseSharedAndroidNativeArm64",
        ":demo:common:linkReleaseSharedAndroidNativeX64",
    )
}

fun verifyNativeLibraries(apk: File) {
    require(apk.isFile) { "Missing APK: $apk" }
    ZipFile(apk).use { zip ->
        listOf(
            "lib/arm64-v8a/libwgpu4k_demo.so",
            "lib/x86_64/libwgpu4k_demo.so",
        ).forEach { entry ->
            require(zip.getEntry(entry) != null) { "$apk is missing $entry" }
        }
    }
}

tasks.register("verifyAndroidNativeDebugApk") {
    dependsOn("assembleDebug")
    doLast {
        verifyNativeLibraries(
            layout.buildDirectory.file("outputs/apk/debug/android-native-debug.apk").get().asFile,
        )
    }
}

tasks.register("verifyAndroidNativeReleaseApk") {
    dependsOn("assembleRelease")
    doLast {
        verifyNativeLibraries(
            layout.buildDirectory.file("outputs/apk/release/android-native-release-unsigned.apk").get().asFile,
        )
    }
}

tasks.register("verifyAndroidNativeApks") {
    dependsOn("verifyAndroidNativeDebugApk", "verifyAndroidNativeReleaseApk")
}
