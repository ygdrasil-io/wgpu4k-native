rootProject.name = "wgpu4k-native-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}

val hostOs = System.getProperty("os.name")

include("wgpu4k-native")
