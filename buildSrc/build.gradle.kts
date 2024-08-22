plugins {
	`kotlin-dsl`
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
}

dependencies {
	implementation(libs.download)
	implementation(libs.kotlin.multiplatform)
	implementation(libs.jreleaser.plugin)
}
