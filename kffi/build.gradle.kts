import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val callbackFixtureSource = layout.projectDirectory.file("src/ffiTest/resources/callback_fixture.c")
val callbackFixtureHeader = layout.projectDirectory.file("src/ffiTest/resources/callback_fixture.h")
val callbackFixtureOutputDirectory = layout.buildDirectory.dir("callback-fixture")
val callbackFixtureHost = providers.gradleProperty("kffi.callbackFixture.hostForTest")
    .orNull
    ?.lowercase()
    ?: when {
        System.getProperty("os.name").contains("mac", ignoreCase = true) -> "macos"
        System.getProperty("os.name").contains("linux", ignoreCase = true) -> "linux"
        System.getProperty("os.name").contains("windows", ignoreCase = true) -> "windows"
        else -> error("Unsupported callback fixture host: ${System.getProperty("os.name")}")
    }
require(callbackFixtureHost in setOf("macos", "linux", "windows")) {
    "Unsupported callback fixture host override: $callbackFixtureHost"
}
val callbackFixtureSharedLibrary = when (callbackFixtureHost) {
    "macos" -> callbackFixtureOutputDirectory.map { it.file("libcallback_fixture.dylib") }
    "linux" -> callbackFixtureOutputDirectory.map { it.file("libcallback_fixture.so") }
    "windows" -> null
    else -> error("Unsupported callback fixture host: $callbackFixtureHost")
}
val callbackFixtureWatchdogProbeSource =
    layout.projectDirectory.file("src/ffiTest/resources/callback_fixture_watchdog_probe.c")
val callbackFixtureWatchdogProbe = when (callbackFixtureHost) {
    "macos", "linux" -> callbackFixtureOutputDirectory.map { it.file("callback_fixture_watchdog_probe") }
    "windows" -> null
    else -> error("Unsupported callback fixture host: $callbackFixtureHost")
}
val compileCallbackFixtureWatchdogProbe = callbackFixtureWatchdogProbe?.let { probe ->
    tasks.register<Exec>("compileCallbackFixtureWatchdogProbe") {
        group = "verification"
        inputs.files(callbackFixtureSource, callbackFixtureHeader, callbackFixtureWatchdogProbeSource)
        outputs.file(probe)
        doFirst { callbackFixtureOutputDirectory.get().asFile.mkdirs() }
        commandLine(
            "cc",
            "-std=c11",
            "-pthread",
            "-DFIXTURE_TEARDOWN_TIMEOUT_MS=100u",
            callbackFixtureSource.asFile.absolutePath,
            callbackFixtureWatchdogProbeSource.asFile.absolutePath,
            "-o",
            probe.get().asFile.absolutePath,
        )
    }
}
val callbackFixtureObject = callbackFixtureOutputDirectory.map { it.file("callback_fixture.o") }
val callbackFixtureArchive = callbackFixtureOutputDirectory.map { it.file("libcallback_fixture.a") }

val compileCallbackFixtureShared = callbackFixtureSharedLibrary?.let { sharedLibrary ->
    tasks.register<Exec>("compileCallbackFixtureShared") {
        group = "verification"
        description = "Compiles the delayed callback C fixture for JVM FFM tests."
        inputs.files(callbackFixtureSource, callbackFixtureHeader)
        outputs.file(sharedLibrary)
        doFirst {
            callbackFixtureOutputDirectory.get().asFile.mkdirs()
        }
        commandLine(
            buildList {
                addAll(listOf("cc", "-std=c11", "-fPIC", "-pthread"))
                add(
                    when (callbackFixtureHost) {
                        "macos" -> "-dynamiclib"
                        "linux" -> "-shared"
                        else -> error("No pthread shared fixture on $callbackFixtureHost")
                    },
                )
                addAll(
                    listOf(
                        callbackFixtureSource.asFile.absolutePath,
                        "-o",
                        sharedLibrary.get().asFile.absolutePath,
                    ),
                )
            },
        )
    }
}

val compileCallbackFixtureObject = if (callbackFixtureHost == "macos") {
    tasks.register<Exec>("compileCallbackFixtureObject") {
        group = "verification"
        description = "Compiles the delayed callback C fixture object for macOS Native tests."
        inputs.files(callbackFixtureSource, callbackFixtureHeader)
        outputs.file(callbackFixtureObject)
        doFirst {
            callbackFixtureOutputDirectory.get().asFile.mkdirs()
        }
        commandLine(
            "cc",
            "-std=c11",
            "-fPIC",
            "-pthread",
            "-arch",
            "arm64",
            "-c",
            callbackFixtureSource.asFile.absolutePath,
            "-o",
            callbackFixtureObject.get().asFile.absolutePath,
        )
    }
} else {
    null
}

val archiveCallbackFixture = compileCallbackFixtureObject?.let { compileObject ->
    tasks.register<Exec>("archiveCallbackFixture") {
        group = "verification"
        description = "Archives the delayed callback C fixture for macosArm64Test cinterop."
        dependsOn(compileObject)
        inputs.file(callbackFixtureObject)
        outputs.file(callbackFixtureArchive)
        doFirst {
            callbackFixtureOutputDirectory.get().asFile.mkdirs()
        }
        commandLine(
            "ar",
            "rcs",
            callbackFixtureArchive.get().asFile.absolutePath,
            callbackFixtureObject.get().asFile.absolutePath,
        )
    }
}

plugins {
    `kotlin-multiplatform`
    publish
    com.android.library
    alias(libs.plugins.kotest)
    alias(libs.plugins.ksp)
}

kotlin {

    val macosArm64Target = macosArm64()

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64Target,
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

        publishLibraryVariants("release", "debug")
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_24
        }
    }

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("callbackTokenCodec") {
                defFile(project.file("src/nativeInterop/cinterop/callbackTokenCodec.def"))
                includeDirs(project.file("src/nativeInterop/cinterop"))
            }
        }
    }

    if (callbackFixtureHost == "macos") {
        val callbackFixtureInterop = macosArm64Target.compilations.getByName("test").cinterops.create(
            "callbackFixture",
        ) {
            defFile(project.file("src/nativeInterop/cinterop/callbackFixture.def"))
            includeDirs(project.file("src/ffiTest/resources"))
            extraOpts("-libraryPath", callbackFixtureOutputDirectory.get().asFile.absolutePath)
        }
        tasks.named(callbackFixtureInterop.interopProcessingTaskName) {
            dependsOn(requireNotNull(archiveCallbackFixture))
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

tasks.withType<Test>().configureEach {
    when (name) {
        "jvmTest" -> useJUnitPlatform()
        "testDebugUnitTest" -> {
            useJUnitPlatform()
            filter {
                excludeTestsMatching("io.ygdrasil.kffi.MemoryBufferArrayTest")
            }
        }
    }
}

tasks.named<Test>("jvmTest") {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    when (callbackFixtureHost) {
        "macos", "linux" -> {
            val sharedLibrary = requireNotNull(callbackFixtureSharedLibrary)
            dependsOn(requireNotNull(compileCallbackFixtureShared))
            val watchdogProbe = requireNotNull(callbackFixtureWatchdogProbe)
            dependsOn(requireNotNull(compileCallbackFixtureWatchdogProbe))
            inputs.file(sharedLibrary)
            inputs.file(watchdogProbe)
            doFirst {
                systemProperty(
                    "kffi.callback.fixture.library",
                    sharedLibrary.get().asFile.absolutePath,
                )
                systemProperty(
                    "kffi.callback.fixture.watchdog.probe",
                    watchdogProbe.get().asFile.absolutePath,
                )
            }
        }

        "windows" -> filter {
            // callback_fixture.c requires pthreads; keep every other JVM test in Windows CI.
            excludeTestsMatching("io.ygdrasil.kffi.CallbackFfiJvmTest")
            excludeTestsMatching("io.ygdrasil.kffi.CallbackFixtureWatchdogJvmTest")
        }
    }
}

archiveCallbackFixture?.let { archiveFixture ->
    tasks.named("macosArm64Test") {
        dependsOn(archiveFixture)
        inputs.file(callbackFixtureArchive)
    }
}
