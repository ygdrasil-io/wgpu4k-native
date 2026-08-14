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
// The kffi module ships the bench fixture only in its own androidTest APK (testOnly, excluded
// from the AAR). Stage just libkffi_bench_fixture.so into this module's androidTest jniLibs so
// the harness can dlopen it by path from nativeLibraryDir.
abstract class StageKffiBenchFixture : DefaultTask() {
    @get:InputFiles
    abstract val fixtureSources: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun stage() {
        val out = outputDir.get().asFile
        out.deleteRecursively()
        out.mkdirs()
        fixtureSources.files.forEach { src ->
            val abi = src.parentFile.name
            val dest = File(out, "$abi/${src.name}")
            dest.parentFile.mkdirs()
            src.copyTo(dest, overwrite = true)
        }
    }
}

val stageKffiBenchFixture = tasks.register<StageKffiBenchFixture>("stageKffiBenchFixture") {
    fixtureSources.from(
        fileTree(project(":kffi").layout.buildDirectory.dir("intermediates/cmake/debug/obj")) {
            include("**/libkffi_bench_fixture.so")
        },
    )
    outputDir.set(layout.buildDirectory.dir("kffi-fixture-androidTest"))
}

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
        jniLibs {
            // Extract .so to nativeLibraryDir so the test can dlopen the fixture by path.
            useLegacyPackaging = true
        }
    }
    sourceSets {
        getByName("androidTest") {
            jniLibs.srcDirs(files(layout.buildDirectory.dir("kffi-fixture-androidTest")).builtBy(stageKffiBenchFixture))
        }
    }
}
