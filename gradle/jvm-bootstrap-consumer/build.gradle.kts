plugins {
    kotlin("jvm") version "2.3.21"
    application
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation("io.ygdrasil:wgpu4k-native-jvm:v29.0.0-SNAPSHOT")
}

application {
    mainClass = "MainKt"
}

tasks.named<JavaExec>("run") {
    val nativeLibraryDirectory = layout.buildDirectory.dir("native-library")
    doFirst {
        nativeLibraryDirectory.get().asFile.mkdirs()
    }
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    jvmArgs("-Djava.library.path=${nativeLibraryDirectory.get().asFile.absolutePath}")
}
