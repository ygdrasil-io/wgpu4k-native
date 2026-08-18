
import com.google.gson.JsonParser
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

allprojects {

	repositories {
		mavenLocal()
		google()
		mavenCentral()
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "v29.0.0-SNAPSHOT"
}

val publicationVerificationRepository = layout.buildDirectory
	.dir("publication-verification/repository")

// kffi est consommé comme une dépendance publiée par Graphiks-org/kffi,
// indépendamment de la version du repo hôte.
val kffiPublishedVersion = "1.0.0-SNAPSHOT"
val mavenLocalRepositoryDirectory = providers.gradleProperty("maven.repo.local")
	.map(::File)
	.getOrElse(File(System.getProperty("user.home"), ".m2/repository"))

val cleanPublicationVerificationRepository by tasks.registering(Delete::class) {
	delete(publicationVerificationRepository)
}

// Les artifacts org.graphiks:kffi* déjà publiés localement sont copiés dans le
// repository de vérification pour valider les métadonnées du module hôte.
val stageKffiPublicationsFromMavenLocal by tasks.registering(Copy::class) {
	group = "verification"
	description = "Stages the published org.graphiks:kffi artifacts from mavenLocal into the verification repository"
	from(mavenLocalRepositoryDirectory.resolve("org/graphiks"))
	into(publicationVerificationRepository.get().asFile.resolve("org/graphiks"))
	include("kffi*/$kffiPublishedVersion/**")
	dependsOn(cleanPublicationVerificationRepository)
}

val verifyPublicationMetadata by tasks.registering {
	group = "verification"
	dependsOn(
		stageKffiPublicationsFromMavenLocal,
		":wgpu4k-native:publishAllPublicationsToPublicationVerificationRepository",
	)
	doLast {
		val repository = publicationVerificationRepository.get().asFile
		val kffiGroup = "org.graphiks"
		val wgpuGroup = "io.ygdrasil"
		val kffiCoordinatePath = kffiGroup.replace('.', '/')
		val wgpuCoordinatePath = wgpuGroup.replace('.', '/')
		val publishedVersion = project.version.toString()
		fun uniquePublishedFile(coordinatePath: String, artifact: String, extension: String, version: String): File {
			val versionDirectory = repository.resolve("$coordinatePath/$artifact/$version")
			val candidates = versionDirectory.listFiles { file ->
				file.isFile &&
					file.name.startsWith("$artifact-") &&
					file.name.endsWith(".$extension")
			}?.toList().orEmpty()
			require(candidates.size == 1) {
				"Expected exactly one $artifact .$extension file in $versionDirectory, " +
					"but found ${candidates.map { it.name }.sorted()}"
			}
			return candidates.single()
		}

		uniquePublishedFile(kffiCoordinatePath, "kffi-jvm", "module", kffiPublishedVersion)
		val metadataFile = uniquePublishedFile(wgpuCoordinatePath, "wgpu4k-native-jvm", "module", publishedVersion)
		val root = JsonParser.parseString(metadataFile.readText()).asJsonObject
		val dependencies = root.getAsJsonArray("variants")
			.flatMap { variant ->
				variant.asJsonObject.getAsJsonArray("dependencies")?.map { it.asJsonObject }.orEmpty()
			}
		val kffiDependencies = dependencies.filter { candidate ->
			candidate["group"].asString == kffiGroup &&
				candidate["module"].asString == "kffi"
		}
		require(kffiDependencies.isNotEmpty()) {
			"Expected wgpu4k-native-jvm metadata to depend on $kffiGroup:kffi"
		}
		val publishedDependencyVersions = kffiDependencies.map { dependency ->
			dependency.getAsJsonObject("version").let { version ->
				version.get("requires")?.asString ?: version.get("strictly")?.asString
			}
		}
		require(publishedDependencyVersions.all { it == kffiPublishedVersion }) {
			"Expected every wgpu4k-native-jvm metadata edge to $kffiGroup:kffi " +
				"to use $kffiPublishedVersion, but found $publishedDependencyVersions"
		}

		val pomFile = uniquePublishedFile(wgpuCoordinatePath, "wgpu4k-native-jvm", "pom", publishedVersion)
		val pom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pomFile)
		val pomDependencies = pom.getElementsByTagName("dependency")
		val kffiJvmDependencies = (0 until pomDependencies.length)
			.map { pomDependencies.item(it) as Element }
			.filter { dependency ->
				dependency.getElementsByTagName("groupId").item(0).textContent == kffiGroup &&
					dependency.getElementsByTagName("artifactId").item(0).textContent == "kffi-jvm"
			}
		require(kffiJvmDependencies.size == 1) {
			"Expected exactly one $kffiGroup:kffi-jvm dependency in $pomFile, " +
				"but found ${kffiJvmDependencies.size}"
		}
		val pomDependencyVersion = kffiJvmDependencies.single()
			.getElementsByTagName("version")
			.item(0)
			.textContent
		require(pomDependencyVersion == kffiPublishedVersion) {
			"Expected wgpu4k-native-jvm POM to depend on kffi-jvm $kffiPublishedVersion, " +
				"but found $pomDependencyVersion"
		}
	}
}

tasks.register<GradleBuild>("verifyPublishedConsumer") {
	group = "verification"
	description = "Publishes wgpu4k-native locally, then compiles an isolated consumer."
	dependsOn(verifyPublicationMetadata)
	dir = file("gradle/publication-consumer")
	tasks = listOf("clean", "compileJava")
	startParameter.projectProperties = mapOf(
		"verificationRepository" to publicationVerificationRepository.get().asFile.toURI().toString(),
		"wgpu4kVersion" to project.version.toString(),
	)
}
