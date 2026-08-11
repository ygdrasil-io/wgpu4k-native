rootProject.name = "wgpu4k-native-root"

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}

includeBuild("Kadre") {
	name = "kadre-build"
	dependencySubstitution {
		substitute(module("org.graphiks.kadre:kadre")).using(project(":kadre"))
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}

include("wgpu4k-native")
include("wgpu4k-native-specs")
include("kffi")
include("kffi-benchmark-spi")
include("kffi-benchmark-jvm")
include("kffi-benchmark-native")
include("kffi-benchmark-android")
include("demo:common")
include("demo:desktop-and-ios")
include("demo:android")
include("demo:android-native")
include("kextract")
