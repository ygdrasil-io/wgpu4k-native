pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        // PRE-SPLIT : consommation locale — les snapshots kffi ne sont pas encore
        // Les snapshots ne sont pas encore sur Sonatype (publication via le workflow
        // du repo cible Graphiks-org/kffi après M4.4). En attendant, ce build résout
        // org.graphiks:kffi depuis mavenLocal() (artifacts publiés depuis le repo cible,
        // voir M4.2 du plan P5).
        // Bascule vers Sonatype à M4.2 (plan M4.2 Step 3) : supprimer mavenLocal() et
        // dé-commenter la ligne snapshots ci-dessous.
        mavenLocal()
        mavenCentral()
        // maven("https://oss.sonatype.org/content/repositories/snapshots/") // snapshots kffi (POST-split)
    }
}
rootProject.name = "kffi-consumer-test"
