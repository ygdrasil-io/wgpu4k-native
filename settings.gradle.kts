rootProject.name = "wgpu4k-native-root"

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
	plugins {
		id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
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
		// Snapshots kffi (org.graphiks:kffi:1.0.0-SNAPSHOT) — publiés par
		// Graphiks-org/kffi (split M4). En local, mavenLocal() prime.
		maven("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

include("wgpu4k-native")
include("wgpu4k-native-specs")
include("demo:common")
include("demo:desktop-and-ios")
include("demo:android")
include("demo:android-native")
include("kextract")
