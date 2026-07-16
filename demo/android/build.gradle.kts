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
                applicationId = "io.ygdrasil.wgpu.app"

                minSdk = 28
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                ndk {
                    abiFilters += setOf("arm64-v8a", "x86_64")
                }
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
                jniLibs {
                    useLegacyPackaging = true
                }
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    excludes += "META-INF/INDEX.LIST"
                    excludes += "**/**.sha1"
                }

            }
            namespace = "io.ygdrasil.wgpu.demo.android"
        }

    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.native.helper)
            implementation(project(":demo:common"))
            implementation(libs.activity.compose)
            implementation("org.graphiks.kadre:kadre:1.0.0")
        }

        androidUnitTest.dependencies {
            implementation(kotlin("test"))
        }

        androidInstrumentedTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.androidx.test.ext.junit)
            implementation(libs.androidx.test.runner)
        }
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.register("verifyAndroidJvmApk") {
    dependsOn("assembleDebug")
    doLast {
        val apk = layout.buildDirectory.file("outputs/apk/debug/android-debug.apk").get().asFile
        require(apk.isFile) { "Missing APK: $apk" }
        ZipFile(apk).use { zip ->
            val actualAbis = zip.entries().asSequence()
                .map { it.name }
                .filter { it.startsWith("lib/") && it.endsWith(".so") }
                .map { it.substringAfter("lib/").substringBefore('/') }
                .toSet()
            require(actualAbis == setOf("arm64-v8a", "x86_64")) {
                "Unexpected Android/JVM APK ABIs: $actualAbis"
            }
        }
    }
}
