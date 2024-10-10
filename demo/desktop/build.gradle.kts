plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}


kotlin {

    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val nativeTarget = when {
        // No toolchain on this architecture
        hostOs == "Linux" && isArm64 -> null.also { println("Linux native Arm64 not yet supported") }
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        else -> null // Not supported
    }

    if (nativeTarget != null) {
        with(nativeTarget) {

            binaries {
                executable {
                    entryPoint = "main"
                }
            }
        }
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.demo.common)
            }
        }

        nativeMain {
            dependencies {
                implementation("io.ygdrasil:glfw-native:0.0.1")
            }
        }


    }

}

