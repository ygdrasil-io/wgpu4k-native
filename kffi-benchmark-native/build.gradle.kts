plugins {
    `kotlin-multiplatform`
}

group = "org.graphiks"

val isArm64Host = System.getProperty("os.arch") == "aarch64" || System.getProperty("os.arch") == "arm64"
val macosArch = if (isArm64Host) "arm64" else "x86_64"
val macosTargetName = if (isArm64Host) "macosArm64" else "macosX64"
val macosLinkTaskName = if (isArm64Host) "linkBenchmarkDebugExecutableMacosArm64" else "linkBenchmarkDebugExecutableMacosX64"

val benchFixtureHeaderDir = layout.projectDirectory.dir("../kffi-benchmark-jvm/src/jmh/resources")
val benchFixtureSource = layout.projectDirectory.file("../kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c")
val benchFixtureOutputDirectory = layout.buildDirectory.dir("bench-fixture")
val benchFixtureObject = benchFixtureOutputDirectory.map { it.file("bench_fixture.o") }
val benchFixtureArchive = benchFixtureOutputDirectory.map { it.file("libbench_fixture.a") }

val compileBenchFixtureObject = tasks.register<Exec>("compileBenchFixtureObject") {
    group = "verification"
    description = "Compiles the bench fixture for the native harness ($macosArch)."
    inputs.file(benchFixtureSource)
    outputs.file(benchFixtureObject)
    doFirst { benchFixtureOutputDirectory.get().asFile.mkdirs() }
    commandLine(
        "cc", "-std=c11", "-fPIC", "-pthread", "-O2", "-arch", macosArch, "-c",
        benchFixtureSource.asFile.absolutePath,
        "-o", benchFixtureObject.get().asFile.absolutePath,
    )
}

val archiveBenchFixture = tasks.register<Exec>("archiveBenchFixture") {
    group = "verification"
    description = "Archives the bench fixture object for the native harness cinterop."
    dependsOn(compileBenchFixtureObject)
    inputs.file(benchFixtureObject)
    outputs.file(benchFixtureArchive)
    doFirst { benchFixtureOutputDirectory.get().asFile.mkdirs() }
    commandLine(
        "ar", "rcs",
        benchFixtureArchive.get().asFile.absolutePath,
        benchFixtureObject.get().asFile.absolutePath,
    )
}

kotlin {
    val macosTarget = when (macosTargetName) {
        "macosArm64" -> macosArm64()
        else -> macosX64()
    }
    applyDefaultHierarchyTemplate()

    sourceSets {
        val macosMain by getting {
            dependencies {
                implementation(project(":kffi"))
                implementation(project(":kffi-benchmark-spi"))
            }
        }
    }

    val benchInterop = macosTarget.compilations.getByName("main").cinterops.create("benchFixture") {
        defFile(project.file("src/nativeInterop/cinterop/benchFixture.def"))
        includeDirs(benchFixtureHeaderDir.asFile.absolutePath)
        extraOpts("-libraryPath", benchFixtureOutputDirectory.get().asFile.absolutePath)
    }
    tasks.named(benchInterop.interopProcessingTaskName) {
        dependsOn(archiveBenchFixture)
    }

    macosTarget.binaries {
        executable("benchmark") {
            entryPoint = "org.graphiks.kffi.benchmark.native.main"
        }
    }

    val runBenchmarkNative = tasks.register<Exec>("runBenchmarkNative") {
        group = "verification"
        description = "Runs the Kotlin/Native benchmark harness and prints the markdown report."
        dependsOn(macosLinkTaskName)
        val binary = macosTarget.binaries.getExecutable("benchmark", "DEBUG")
        doFirst {
            commandLine(binary.outputFile.absolutePath)
        }
    }
}
