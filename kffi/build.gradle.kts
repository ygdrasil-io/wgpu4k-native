import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-multiplatform`
    com.android.library
    alias(libs.plugins.kotest)
    alias(libs.plugins.ksp)
}

kotlin {

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64(),
        androidNativeArm64(),
        androidNativeX64(),
    )

    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }

        android {
            namespace = "io.ygdrasil.kffi"
            compileSdk = 36

            defaultConfig {
                minSdk = 28
            }
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_24
        }
    }

    compilerOptions {
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

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
