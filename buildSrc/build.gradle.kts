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
	implementation(libs.bundles.dokka)
	implementation(libs.jreleaser.plugin)
	implementation(libs.android.library)

	implementation(libs.gson)
	implementation(libs.zip4j)
	implementation(libs.okhttp)
	implementation(libs.commons.io)

}
