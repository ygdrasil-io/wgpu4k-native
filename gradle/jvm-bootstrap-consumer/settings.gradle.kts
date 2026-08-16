pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal {
            metadataSources {
                mavenPom()
                artifact()
                ignoreGradleMetadataRedirection()
            }
        }
        // Snapshots kffi (org.graphiks:kffi-jvm:1.0.0-SNAPSHOT) — publiés par Graphiks-org/kffi.
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        mavenCentral()
    }
}

rootProject.name = "wgpu4k-jvm-bootstrap-consumer"
