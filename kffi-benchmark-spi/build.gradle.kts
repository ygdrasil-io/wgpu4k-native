plugins {
    `kotlin-multiplatform`
    publish
    com.android.library
}

group = "org.graphiks"

kotlin {
    jvm()
    androidTarget {
        android {
            namespace = "org.graphiks.kffi.benchmark"
            compileSdk = 36
        }
    }
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    macosX64()
    linuxX64()
    androidNativeArm64()
    androidNativeX64()

    sourceSets {
        commonMain.dependencies {
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
