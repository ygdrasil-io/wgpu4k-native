import publish.centralPortalPublish
import publish.PublishingType
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository

plugins {
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
}

val libraryDescription = "wgpu4k kotlin native binding."

val signingKey = System.getenv("JRELEASER_GPG_SECRET_KEY")
val signingPassword = System.getenv("JRELEASER_GPG_PASSPHRASE")
val jvmVerificationPublication = providers.gradleProperty("wgpu4k.jvmVerificationPublication")
    .map(String::toBoolean)
    .orElse(false)

if (!isSnapshot()) {
    signing {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}

project.centralPortalPublish {
    username = System.getenv("JRELEASER_MAVENCENTRAL_USERNAME")
    password = System.getenv("JRELEASER_MAVENCENTRAL_PASSWORD")
    publishingType = PublishingType.USER_MANAGED
    url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
}

val dokkaHtml = tasks.named("dokkaGeneratePublicationHtml")

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn(dokkaHtml)
    from(dokkaHtml.map { it.outputs.files })
}

publishing {
    publications {
        withType<MavenPublication> {
            if (!jvmVerificationPublication.get()) {
                artifact(javadocJar)
            }
            pom {
                name.set(project.name)
                description.set(libraryDescription)
                url.set("https://github.com/wgpu4k/wgpu4k-native")
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
                    connection.set("scm:git:https://github.com/wgpu4k/wgpu4k-native.git")
                    developerConnection.set("scm:git:https://github.com/wgpu4k/wgpu4k-native.git")
                    url.set("https://github.com/wgpu4k/wgpu4k-native")
                }
            }
        }
    }


    repositories {
        maven {
            if (isSnapshot()) {
                logger.info("publishing is configure as snapshot")
                name = "GitLab"
                url = uri("https://gitlab.com/api/v4/projects/25805863/packages/maven")
                credentials(HttpHeaderCredentials::class) {
                    name = "Authorization"
                    value = "Bearer ${System.getenv("GITLAB_TOKEN")}"
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            } else {
                name = "Local"
                logger.info("publishing is configure as release")
                url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
                logger.info("publishing path is ${url.path}")
            }
        }
    }
}

val publicationVerificationRepository = publishing.repositories.maven {
    name = "PublicationVerification"
    url = rootProject.layout.buildDirectory
        .dir("publication-verification/repository")
        .get()
        .asFile
        .toURI()
}

tasks.withType<PublishToMavenRepository>().configureEach {
    if (repository == publicationVerificationRepository) {
        dependsOn(rootProject.tasks.named("cleanPublicationVerificationRepository"))
    }
}

if (!isSnapshot()) {
    val signingTasks = tasks.withType<Sign>()
    tasks.withType<AbstractPublishToMaven>().configureEach {
        dependsOn(signingTasks)
    }
}
