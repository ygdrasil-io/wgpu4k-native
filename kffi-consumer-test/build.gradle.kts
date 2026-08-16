import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.3.21"
}

kotlin {
    jvm {
        compilerOptions {
            // jvmTarget cohérent avec kffi (JVM_24, voir kffi/build.gradle.kts)
            jvmTarget = JvmTarget.JVM_24
        }
        // DSL KMP/JVM binaries (kotl.in/jvm-binaries-dsl) — remplace le plugin
        // `application`, incompatible avec kotlin.multiplatform depuis Kotlin 2.3.
        binaries {
            executable {
                mainClass.set("consumer.MainKt")
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
                entryPoint = "consumer.main"
            }
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                // Root metadata artifact : la variante plateforme est résolue
                // automatiquement par Gradle (pas d'artifact « kffi-native » agrégé).
                implementation("org.graphiks:kffi:1.0.0-SNAPSHOT")
            }
        }
        // Un seul target natif → pas de `nativeMain` par défaut dans le template
        // de hiérarchie ; on le crée explicitement (commonMain <- nativeMain <- macosArm64Main).
        val nativeMain by creating {
            dependsOn(getByName("commonMain"))
            dependencies {
                implementation("org.graphiks:kffi:1.0.0-SNAPSHOT")
            }
        }
        val macosArm64Main by getting {
            dependsOn(nativeMain)
        }
    }

    // Toolchain >= jvmTarget 24 (kffi requiert JDK 24+ pour java.lang.foreign)
    jvmToolchain(25)
}

tasks.withType<JavaExec>().configureEach {
    // kffi JVM runtime utilise les API restreintes de java.lang.foreign
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
