import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-multiplatform`
    com.android.library
    alias(libs.plugins.kotest)
}

group = "org.graphiks"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        android {
            namespace = "org.graphiks.kffi.benchmark"
            compileSdk = 36
            defaultConfig {
                minSdk = 28
            }
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":kffi"))
                implementation(project(":kffi-benchmark-spi"))
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:${libs.versions.kotest.get()}")
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.runner)
            }
        }
    }
}

// JUnit5/kotest jars ship duplicate META-INF LICENSE/README files; exclude them from merge.
android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
            excludes += "/META-INF/versions/9/previous-compilation-data.bin"
        }
    }
}
