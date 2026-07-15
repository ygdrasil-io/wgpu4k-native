plugins {
    java
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation("io.ygdrasil:wgpu4k-native-jvm:${providers.gradleProperty("wgpu4kVersion").get()}")
}
