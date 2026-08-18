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
        // Snapshots kffi (org.graphiks:kffi-jvm:1.0.0-SNAPSHOT) — publiés par
        // Graphiks-org/kffi via Central Portal.
        maven {
            name = "CentralPortalSnapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            content {
                includeGroup("org.graphiks")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "wgpu4k-jvm-bootstrap-consumer"
