import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val benchFixtureSource = layout.projectDirectory.file("src/jmh/resources/bench_fixture.c")
val benchFixtureHeader = layout.projectDirectory.file("src/jmh/resources/bench_fixture.h")
val benchFixtureOutputDirectory = layout.buildDirectory.dir("bench-fixture")
val benchFixtureHost = providers.gradleProperty("kffi.benchFixture.hostForTest")
    .orNull
    ?.lowercase()
    ?: when {
        System.getProperty("os.name").contains("mac", ignoreCase = true) -> "macos"
        System.getProperty("os.name").contains("linux", ignoreCase = true) -> "linux"
        System.getProperty("os.name").contains("windows", ignoreCase = true) -> "windows"
        else -> error("Unsupported bench fixture host: ${System.getProperty("os.name")}")
    }
require(benchFixtureHost in setOf("macos", "linux", "windows")) {
    "Unsupported bench fixture host override: $benchFixtureHost"
}
val benchFixtureSharedLibrary = when (benchFixtureHost) {
    "macos" -> benchFixtureOutputDirectory.map { it.file("libbench_fixture.dylib") }
    "linux" -> benchFixtureOutputDirectory.map { it.file("libbench_fixture.so") }
    "windows" -> null
    else -> error("Unsupported bench fixture host: $benchFixtureHost")
}

val compileBenchFixtureShared = benchFixtureSharedLibrary?.let { sharedLibrary ->
    tasks.register<Exec>("compileBenchFixtureShared") {
        group = "verification"
        description = "Compiles the dedicated kffi benchmark C fixture for JVM JMH tests."
        inputs.files(benchFixtureSource, benchFixtureHeader)
        outputs.file(sharedLibrary)
        doFirst { benchFixtureOutputDirectory.get().asFile.mkdirs() }
        commandLine(
            buildList {
                addAll(listOf("cc", "-std=c11", "-fPIC", "-pthread", "-O2"))
                add(
                    when (benchFixtureHost) {
                        "macos" -> "-dynamiclib"
                        "linux" -> "-shared"
                        else -> error("No shared bench fixture on $benchFixtureHost")
                    },
                )
                addAll(
                    listOf(
                        benchFixtureSource.asFile.absolutePath,
                        "-o",
                        sharedLibrary.get().asFile.absolutePath,
                    ),
                )
            },
        )
    }
}

plugins {
    kotlin("jvm")
    id("me.champeau.jmh") version "0.7.2"
}

group = "org.graphiks"

kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_24)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(24)
}

dependencies {
    jmh(project(":kffi"))
    jmh(project(":kffi-benchmark-spi"))
    jmh("org.jetbrains.kotlin:kotlin-stdlib")
    jmh("com.google.code.gson:gson:2.13.1")
}

jmh {
    warmupIterations.set(3)
    iterations.set(5)
    warmup.set("1s")
    timeOnIteration.set("1s")
    fork.set(2)
    resultFormat.set("JSON")
}

tasks.named<me.champeau.jmh.JMHTask>("jmh") {
    if (benchFixtureHost != "windows") {
        val sharedLibrary = requireNotNull(benchFixtureSharedLibrary)
        dependsOn(requireNotNull(compileBenchFixtureShared))
        inputs.file(sharedLibrary)
        jvmArgsAppend.add(
            sharedLibrary.map { "-Dkffi.bench.fixture.library=${it.asFile.absolutePath}" },
        )
    }
}
