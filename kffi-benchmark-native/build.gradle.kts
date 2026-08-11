plugins {
    `kotlin-multiplatform`
}

group = "org.graphiks"

val isArm64Host = System.getProperty("os.arch") == "aarch64" || System.getProperty("os.arch") == "arm64"

val benchFixtureHeaderDir = layout.projectDirectory.dir("../kffi-benchmark-jvm/src/jmh/resources")
val benchFixtureSource = layout.projectDirectory.file("../kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c")
val benchFixtureOutputDirectory = layout.buildDirectory.dir("bench-fixture")

val benchFixtureArm64Directory = benchFixtureOutputDirectory.map { it.dir("arm64") }
val benchFixtureArm64Object = benchFixtureArm64Directory.map { it.file("bench_fixture.o") }
val benchFixtureArm64Archive = benchFixtureArm64Directory.map { it.file("libbench_fixture.a") }

val benchFixtureX64Directory = benchFixtureOutputDirectory.map { it.dir("x64") }
val benchFixtureX64Object = benchFixtureX64Directory.map { it.file("bench_fixture.o") }
val benchFixtureX64Archive = benchFixtureX64Directory.map { it.file("libbench_fixture.a") }

val compileBenchFixtureObjectArm64 = tasks.register<Exec>("compileBenchFixtureObjectArm64") {
    group = "verification"
    description = "Compiles the bench fixture for the macOS arm64 native harness."
    inputs.file(benchFixtureSource)
    outputs.file(benchFixtureArm64Object)
    doFirst { benchFixtureArm64Directory.get().asFile.mkdirs() }
    commandLine(
        "cc", "-std=c11", "-fPIC", "-pthread", "-O2", "-arch", "arm64", "-c",
        benchFixtureSource.asFile.absolutePath,
        "-o", benchFixtureArm64Object.get().asFile.absolutePath,
    )
}

val archiveBenchFixtureArm64 = tasks.register<Exec>("archiveBenchFixtureArm64") {
    group = "verification"
    description = "Archives the bench fixture for the macOS arm64 native harness cinterop."
    dependsOn(compileBenchFixtureObjectArm64)
    inputs.file(benchFixtureArm64Object)
    outputs.file(benchFixtureArm64Archive)
    doFirst { benchFixtureArm64Directory.get().asFile.mkdirs() }
    commandLine(
        "ar", "rcs",
        benchFixtureArm64Archive.get().asFile.absolutePath,
        benchFixtureArm64Object.get().asFile.absolutePath,
    )
}

val compileBenchFixtureObjectX64 = tasks.register<Exec>("compileBenchFixtureObjectX64") {
    group = "verification"
    description = "Compiles the bench fixture for the macOS x64 native harness."
    inputs.file(benchFixtureSource)
    outputs.file(benchFixtureX64Object)
    doFirst { benchFixtureX64Directory.get().asFile.mkdirs() }
    commandLine(
        "cc", "-std=c11", "-fPIC", "-pthread", "-O2", "-arch", "x86_64", "-c",
        benchFixtureSource.asFile.absolutePath,
        "-o", benchFixtureX64Object.get().asFile.absolutePath,
    )
}

val archiveBenchFixtureX64 = tasks.register<Exec>("archiveBenchFixtureX64") {
    group = "verification"
    description = "Archives the bench fixture for the macOS x64 native harness cinterop."
    dependsOn(compileBenchFixtureObjectX64)
    inputs.file(benchFixtureX64Object)
    outputs.file(benchFixtureX64Archive)
    doFirst { benchFixtureX64Directory.get().asFile.mkdirs() }
    commandLine(
        "ar", "rcs",
        benchFixtureX64Archive.get().asFile.absolutePath,
        benchFixtureX64Object.get().asFile.absolutePath,
    )
}

kotlin {
    val macosArm64Target = macosArm64()
    val macosX64Target = macosX64()
    applyDefaultHierarchyTemplate()

    sourceSets {
        val macosMain by getting {
            dependencies {
                implementation(project(":kffi"))
                implementation(project(":kffi-benchmark-spi"))
            }
        }
    }

    val benchInteropArm64 = macosArm64Target.compilations.getByName("main").cinterops.create("benchFixture") {
        defFile(project.file("src/nativeInterop/cinterop/benchFixture.def"))
        includeDirs(benchFixtureHeaderDir.asFile.absolutePath)
        extraOpts("-libraryPath", benchFixtureArm64Directory.get().asFile.absolutePath)
    }
    tasks.named(benchInteropArm64.interopProcessingTaskName) {
        dependsOn(archiveBenchFixtureArm64)
    }

    val benchInteropX64 = macosX64Target.compilations.getByName("main").cinterops.create("benchFixture") {
        defFile(project.file("src/nativeInterop/cinterop/benchFixture.def"))
        includeDirs(benchFixtureHeaderDir.asFile.absolutePath)
        extraOpts("-libraryPath", benchFixtureX64Directory.get().asFile.absolutePath)
    }
    tasks.named(benchInteropX64.interopProcessingTaskName) {
        dependsOn(archiveBenchFixtureX64)
    }

    macosArm64Target.binaries {
        executable("benchmark") {
            entryPoint = "org.graphiks.kffi.benchmark.native.main"
        }
    }

    macosX64Target.binaries {
        executable("benchmark") {
            entryPoint = "org.graphiks.kffi.benchmark.native.main"
        }
    }

    val runBenchmarkNative = tasks.register<Exec>("runBenchmarkNative") {
        group = "verification"
        description = "Runs the Kotlin/Native benchmark harness and prints the markdown report."
        dependsOn(if (isArm64Host) "linkBenchmarkDebugExecutableMacosArm64" else "linkBenchmarkDebugExecutableMacosX64")
        val target = if (isArm64Host) macosArm64Target else macosX64Target
        val binary = target.binaries.getExecutable("benchmark", "DEBUG")
        doFirst {
            commandLine(binary.outputFile.absolutePath)
        }
    }
}
