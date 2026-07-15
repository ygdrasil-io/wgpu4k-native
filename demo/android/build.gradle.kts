import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
