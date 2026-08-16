pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        // PRE-SPLIT : consommation locale — les snapshots kffi ne sont pas encore
        // sur Sonatype avant M4.2. Publier d'abord : `./gradlew :kffi:publishAllPublicationsToMavenLocal`
        // (depuis le repo hôte), puis ce build résout org.graphiks:kffi depuis mavenLocal().
        // Bascule vers Sonatype à M4.2 (plan M4.2 Step 3) : supprimer mavenLocal() et
        // dé-commenter la ligne snapshots ci-dessous.
        mavenLocal()
        mavenCentral()
        // maven("https://oss.sonatype.org/content/repositories/snapshots/") // snapshots kffi (POST-split)
    }
}
rootProject.name = "kffi-consumer-test"
