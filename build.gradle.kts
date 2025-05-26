
allprojects {

	repositories {
		mavenLocal()
		google()
		mavenCentral()
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "v25.0.1-SNAPSHOT"
}


