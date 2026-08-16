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

val downcallFixtureSource = layout.projectDirectory.file("src/ffiTest/resources/downcall_fixture.c")
val downcallFixtureHeader = layout.projectDirectory.file("src/ffiTest/resources/downcall_fixture.h")
val downcallFixtureOutputDirectory = layout.buildDirectory.dir("downcall-fixture")
val downcallFixtureHost = providers.gradleProperty("kffi.downcallFixture.hostForTest")
    .orNull
    ?.lowercase()
    ?: when {
        System.getProperty("os.name").contains("mac", ignoreCase = true) -> "macos"
        System.getProperty("os.name").contains("linux", ignoreCase = true) -> "linux"
        System.getProperty("os.name").contains("windows", ignoreCase = true) -> "windows"
        else -> error("Unsupported downcall fixture host: ${System.getProperty("os.name")}")
    }
require(downcallFixtureHost in setOf("macos", "linux", "windows")) {
    "Unsupported downcall fixture host override: $downcallFixtureHost"
}
val downcallFixtureSharedLibrary = when (downcallFixtureHost) {
    "macos" -> downcallFixtureOutputDirectory.map { it.file("libdowncall_fixture.dylib") }
    "linux" -> downcallFixtureOutputDirectory.map { it.file("libdowncall_fixture.so") }
    "windows" -> null
    else -> error("Unsupported downcall fixture host: $downcallFixtureHost")
}
val compileDowncallFixtureShared = downcallFixtureSharedLibrary?.let { sharedLibrary ->
    tasks.register<Exec>("compileDowncallFixtureShared") {
        group = "verification"
        description = "Compiles the scalar/pointer downcall C fixture for JVM FFM engine tests."
        inputs.files(downcallFixtureSource, downcallFixtureHeader)
        outputs.file(sharedLibrary)
        doFirst {
            downcallFixtureOutputDirectory.get().asFile.mkdirs()
        }
        commandLine(
            buildList {
                addAll(listOf("cc", "-std=c11", "-fPIC"))
                add(
                    when (downcallFixtureHost) {
                        "macos" -> "-dynamiclib"
                        "linux" -> "-shared"
                        else -> error("No shared downcall fixture on $downcallFixtureHost")
                    },
                )
                addAll(
                    listOf(
                        downcallFixtureSource.asFile.absolutePath,
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

android {
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
    defaultConfig {
        externalNativeBuild {
            cmake {
                cFlags += listOf("-std=c11")
            }
        }
        ndk {
            // M6.1: ship exactly the 3 supported ABIs; x86 was building too.
            abiFilters += setOf("arm64-v8a", "x86_64", "armeabi-v7a")
        }
        // M6.1: R8 runs on the consumer, so these rules ship in the AAR and are
        // applied when the consuming app minifies.
        consumerProguardFiles("src/main/resources/consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        jniLibs {
            // Ship the bench fixture only in the androidTest APK; never in the AAR.
            testOnly += setOf("libkffi_bench_fixture.so", "**/libkffi_bench_fixture.so")
            // Extract .so to nativeLibraryDir so the test can dlopen the fixture by path.
            useLegacyPackaging = true
        }
    }
}

group = "org.graphiks"

val kffiVersion = providers.gradleProperty("kffi.version")
    .orElse(System.getenv("KFFI_VERSION") ?: "1.0.0-SNAPSHOT")
version = kffiVersion

afterEvaluate {
	publishing {
		publications.withType<MavenPublication>().all {
			groupId = "org.graphiks"
			version = kffiVersion.get()
		}
	}
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
            namespace = "org.graphiks.kffi"
            compileSdk = 36
            ndkVersion = "30.0.15729638"

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

        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.runner)
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
                excludeTestsMatching("org.graphiks.kffi.MemoryBufferArrayTest")
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
            val downcallSharedLibrary = requireNotNull(downcallFixtureSharedLibrary)
            dependsOn(requireNotNull(compileDowncallFixtureShared))
            inputs.file(downcallSharedLibrary)
            doFirst {
                systemProperty(
                    "kffi.callback.fixture.library",
                    sharedLibrary.get().asFile.absolutePath,
                )
                systemProperty(
                    "kffi.callback.fixture.watchdog.probe",
                    watchdogProbe.get().asFile.absolutePath,
                )
                systemProperty(
                    "kffi.downcall.fixture.library",
                    downcallSharedLibrary.get().asFile.absolutePath,
                )
            }
        }

        "windows" -> filter {
            // callback_fixture.c requires pthreads; keep every other JVM test in Windows CI.
            excludeTestsMatching("org.graphiks.kffi.CallbackFfiJvmTest")
            excludeTestsMatching("org.graphiks.kffi.CallbackFixtureWatchdogJvmTest")
            excludeTestsMatching("org.graphiks.kffi.engine.JvmDowncallEngineTest")
        }
    }
}

archiveCallbackFixture?.let { archiveFixture ->
    tasks.named("macosArm64Test") {
        dependsOn(archiveFixture)
        inputs.file(callbackFixtureArchive)
    }
}
