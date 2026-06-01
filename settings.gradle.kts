rootProject.name = "wgpu4k-native-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}

includeBuild("Kadre") {
	name = "kadre-build"
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
include("demo:common")
include("demo:desktop-and-ios")
include("demo:android")
include("demo:android-native")
include("kextract")
