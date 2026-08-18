import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess

plugins {
    kotlin("multiplatform") version "2.4.10"
}

kotlin {
    jvm {
        compilerOptions {
            // jvmTarget cohérent avec kffi (JVM_24, voir kffi/build.gradle.kts)
            jvmTarget = JvmTarget.JVM_24
        }
        // DSL KMP/JVM binaries (kotl.in/jvm-binaries-dsl) — remplace le plugin
        // `application`, incompatible avec le plugin Kotlin Multiplatform moderne.
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
        val main by compilations.getting {
            cinterops.create("webgpu") {
                // Paquet cinterop imposé par kextract (classificateur « webgpu.native.* »
                // codé en dur dans les bindings native générés — voir webgpu.def).
                packageName = "webgpu.native"
                header(project.file("headers/consumer.h"))
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

    // Toolchain 25 (>= jvmTarget 24 requis par kffi pour java.lang.foreign) :
    // on compile en JVM_24 avec un toolchain plus récent (contrainte FFM restreinte).
    jvmToolchain(25)
}

tasks.withType<JavaExec>().configureEach {
    // kffi JVM runtime utilise les API restreintes de java.lang.foreign
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

// ── Native consumer library ───────────────────────────────────────────────────
// Compile headers/consumer.h (voir native/consumer.c) :
//   - libconsumer.dylib  → chargée au run JVM (System.loadLibrary, java.library.path)
//   - libconsumer.a      → liée statiquement au kexe natif (webgpu.def)
// Le .c est committé ; les artefacts compilés ne le sont PAS (build/).
val consumerNativeLibsDir = layout.buildDirectory.dir("native-libs")
val consumerObjectFile = consumerNativeLibsDir.map { it.file("consumer.o").asFile.absolutePath }
val consumerStaticLib = consumerNativeLibsDir.map { it.file("libconsumer.a").asFile.absolutePath }
val consumerSharedLib = consumerNativeLibsDir.map { it.file("libconsumer.dylib").asFile.absolutePath }

tasks.register("prepareConsumerLibsDir") {
    doFirst {
        mkdir(consumerNativeLibsDir.get().asFile)
    }
}

tasks.register<Exec>("compileConsumerObject") {
    dependsOn("prepareConsumerLibsDir")
    inputs.file("native/consumer.c")
    inputs.file("headers/consumer.h")
    outputs.file(consumerObjectFile)
    commandLine(
        "cc", "-c", "-fPIC", "native/consumer.c", "-o", consumerObjectFile.get(),
        "-I", "headers",
    )
}

tasks.register<Exec>("archiveConsumerStaticLib") {
    dependsOn("compileConsumerObject")
    inputs.file(consumerObjectFile)
    outputs.file(consumerStaticLib)
    // Résolution de `ar` via le toolchain Xcode — REQUISE sur macOS : l'ar du
    // PATH peut être GNU binutils (homebrew), dont les archives sont rejetées
    // par ld (Apple). Pas de fallback : ce build cible jvm + macosArm64.
    val appleAr = providers.exec { commandLine("xcrun", "--find", "ar") }
        .standardOutput.asText.map { it.trim() }
    executable(appleAr.get())
    args("rcs", consumerStaticLib.get(), consumerObjectFile.get())
}

tasks.register<Exec>("linkConsumerSharedLib") {
    dependsOn("compileConsumerObject")
    inputs.file(consumerObjectFile)
    outputs.file(consumerSharedLib)
    commandLine("cc", "-shared", "-fPIC", "-o", consumerSharedLib.get(), consumerObjectFile.get())
}

val compileConsumerLib = tasks.register("compileConsumerLib") {
    group = "build"
    description = "Compiles headers/consumer.h (native/consumer.c) into libconsumer.a + .dylib"
    dependsOn("archiveConsumerStaticLib", "linkConsumerSharedLib")
}

// cinterop + link natif ont besoin du .a ; le run JVM a besoin du .dylib
// (System.loadLibrary("consumer") → java.library.path).
tasks.withType<CInteropProcess>().configureEach {
    dependsOn(compileConsumerLib)
    // Re-invalide le cinterop quand la lib statique change (sinon l'archive
    // « included » copiée dans le klib reste périmée).
    inputs.file(consumerNativeLibsDir.map { it.file("libconsumer.a") })
}
tasks.named("linkDebugExecutableMacosArm64") {
    dependsOn(compileConsumerLib)
}
tasks.named("runDebugExecutableMacosArm64") {
    dependsOn(compileConsumerLib)
}
tasks.named<JavaExec>("runJvm") {
    dependsOn(compileConsumerLib)
    // Résolu par System.loadLibrary("consumer") dans les bindings kextract
    // (KextractNativeBootstrap.load) — doit être posé au démarrage de la JVM.
    systemProperty("java.library.path", consumerNativeLibsDir.get().asFile.absolutePath)
}
