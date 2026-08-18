pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            name = "CentralPortalSnapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            content {
                includeGroup("org.graphiks")
            }
        }
    }
}
rootProject.name = "kffi-consumer-test"
