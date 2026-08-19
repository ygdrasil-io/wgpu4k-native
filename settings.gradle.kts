rootProject.name = "wgpu4k-native-root"

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
	plugins {
		id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
	}
}

includeBuild("Kadre") {
	name = "kadre-build"
	dependencySubstitution {
		substitute(module("org.graphiks.kadre:kadre")).using(project(":kadre"))
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
	repositories {
		mavenLocal {
			// Published kffi snapshots must not be shadowed by an older local install.
			// Keep other local org.graphiks.* projects, such as Kadre, available.
			content {
				excludeGroup("org.graphiks")
			}
		}
		google()
		mavenCentral()
		// Snapshots kffi (org.graphiks:kffi:1.0.0-SNAPSHOT) — publiés par
		// Graphiks-org/kffi via Central Portal.
		maven {
			name = "CentralPortalSnapshots"
			url = uri("https://central.sonatype.com/repository/maven-snapshots/")
			content {
				includeGroup("org.graphiks")
			}
		}
	}
}

include("wgpu4k-native")
include("wgpu4k-native-specs")
include("demo:common")
include("demo:desktop-and-ios")
include("demo:android")
include("demo:android-native")
include("kextract")
