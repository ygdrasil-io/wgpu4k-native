import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

val libraryDescription = "wgpu4k kotlin native binding."
val projectHomepage = "https://github.com/wgpu4k/wgpu4k-native"
val jvmVerificationPublication = providers.gradleProperty("wgpu4k.jvmVerificationPublication")
    .map(String::toBoolean)
    .orElse(false)
val dokkaHtml = tasks.named("dokkaGeneratePublicationHtml")

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    configure(
        KotlinMultiplatform(
            javadocJar = if (jvmVerificationPublication.get()) {
                JavadocJar.Empty()
            } else {
                JavadocJar.Dokka(dokkaHtml)
            },
            sourcesJar = SourcesJar.Sources(),
            androidVariantsToPublish = listOf("debug", "release"),
        ),
    )

    coordinates(project.group.toString(), project.name, project.version.toString())

    pom {
        name.set(project.name)
        description.set(libraryDescription)
        url.set(projectHomepage)
        inceptionYear.set("2024")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/license/MIT")
            }
        }
        developers {
            developer {
                id.set("amommers")
                name.set("Alexandre Mommers")
            }
        }
        scm {
            connection.set("scm:git:${projectHomepage}.git")
            developerConnection.set("scm:git:${projectHomepage}.git")
            url.set(projectHomepage)
        }
    }
}
