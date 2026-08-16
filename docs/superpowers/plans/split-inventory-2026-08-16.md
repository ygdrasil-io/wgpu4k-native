# kffi P5/M2.2 — Inventaire du split (contenu + dépendances)

> **Date:** 2026-08-16 — **Branche:** `feat/kffi-socle-generique` — **Task:** M2.2 du plan `2026-08-16-kffi-p5-release-and-migration.md`
>
> **But:** déterminer la liste EXACTE des chemins extraits vers `Graphiks-org/kffi`, les dépendances inter-modules et les fichiers racine que le repo cible doit copier/adapter, et prouver la faisabilité d'un build autonome des 5 modules.

---

## 1. Inventaire des chemins

### 1.1 Extraits vers `Graphiks-org/kffi` (5 modules)

| Chemin | Contenu |
|---|---|
| `kffi/` | Module KMP `org.graphiks:kffi` (3 backends JVM/Android/Native), fixtures C `src/ffiTest`, `src/main/cpp` (CMake + libffi glue), `src/nativeInterop`, README — **EXCLURE `benchmarks/results/` du contenu extrait** (chemins absolus host dans les JSON, cf. §2.2) : purger avant push par un commit d'une ligne dans le repo cible (`git rm -r kffi/benchmarks/results`) — les baselines restent versionnées dans l'historique host |
| `kffi-benchmark-spi/` | SPI pur (Model.kt, tests kotest) — **aucune dépendance projet** |
| `kffi-benchmark-jvm/` | Harness JMH (jmh plugin `me.champeau.jmh` 0.7.2, gson hardcodé) + fixture C `src/jmh/resources/bench_fixture.{c,h}` |
| `kffi-benchmark-native/` | Harness Kotlin/Native `NativeHarness.kt` (macosMain) + cinterop `benchFixture.def` |
| `kffi-benchmark-android/` | Harness Android instrumenté `AndroidHarness.kt` + task `stageKffiBenchFixture` |

### 1.2 Restent dans le repo hôte wgpu4k-native

| Chemin | Rôle |
|---|---|
| `wgpu4k-native/` | Bindings wgpu (génération kextract + runtime) |
| `wgpu4k-native-specs/` | Specs |
| `demo/` (common, desktop-and-ios, android, android-native) | Démos |
| `Kadre/` | Submodule + `includeBuild` (`org.graphiks.kadre:kadre` substitution) |
| `kextract/` | Submodule klang-toolkit — dépendance de GÉNÉRATION (host only) |
| `.devcontainer/`, `.github/workflows/{publish,snapshot,test,codeql,static,junie}.yml`, `dependabot.yml` | Infra host (test.yml est mixte, cf. §3.4) |
| `doc/`, `docs/`, README racine | Documentation hôte |

### 1.3 Emplacements cachés vérifiés (racine)

| Fichier | Destin | Détail |
|---|---|---|
| `settings.gradle.kts` | **Adapter** | `rootProject.name = "wgpu4k-native-root"` + `includeBuild("Kadre")` + includes host (`wgpu4k-native`, `-specs`, `demo:*`, `kextract`) — le repo cible n'inclut QUE les 5 modules kffi (cf. §5) |
| `build.gradle.kts` (racine) | **Adapter** | `allprojects` force `group = "io.ygdrasil"` / version `VERSION env ?: v29.0.0-SNAPSHOT` ; `verifyPublicationMetadata` dépend de `:wgpu4k-native:publish…` ; `verifyPublishedConsumer` (GradleBuild) utilise `gradle/publication-consumer` + `wgpu4kVersion` — tout ceci est host-only |
| `gradle.properties` | **Copier (nettoyé)** | requis : `kotlin.mpp.enableCInteropCommonization`, `kotlin.native.ignoreDisabledTargets`, android.* ; inutile : `wgpu.base.url` (usage wgpu4k-native only) |
| `gradle/` | **Copie partielle** | `wrapper/` + `libs.versions.toml` → copier. `publication-consumer/` + `jvm-bootstrap-consumer/` → hôte (consumers wgpu, cf. §3.3) |
| `gradlew`, `gradlew.bat` | **Copier tel quel** | Gradle 9.5.0 |
| `buildSrc/` | **Copier (réutilisable)** | cf. §3.2 |
| `.github/workflows/kffi-publish-snapshots.yml` | **Copier (adapter M4.2)** | déjà switché `push: [main]` (M2.1) ; **adapter la ligne `export VERSION=…` → `export KFFI_VERSION=…`** (le root cible lit `kffi.version`/`KFFI_VERSION`, pas `VERSION` — aligné avec M2.4, cf. §3) ; retirer `submodules: recursive` (pas de submodule cible) |
| `.github/workflows/kffi-benchmark-ci.yml` | **Copier tel quel** | 3 jobs : `:kffi-benchmark-jvm:jmh`, `:kffi-benchmark-native:compileKotlinMacosArm64`, `:kffi-benchmark-android:assembleDebugAndroidTest` ; **garder `submodules: recursive`** (inoffensif sans submodule, cohérent avec le host) |
| `.gitmodules` | **NE PAS copier** | kextract + Kadre restent au host ; le repo cible n'a aucun submodule |
| `.gitignore` | **Copier** | couvre `**/build/`, `.gradle`, `.cxx/`, `*.log`, `local.properties`… |
| `LICENSE` | **Copier** | MIT, Copyright (c) 2024 wgpu4k (cohérent avec le pom MIT du plugin publish) |
| `hs_err_pid*.log`, `replay_pid*.log` | **Ignorer** | junk `hs_err_pid*.log`/`replay_pid*.log` gitignorés (`.gitignore` : `*.log`, non trackés) — ne partent pas |

---

## 2. Dépendances par module

### 2.1 Dépendances internes (entre modules kffi)

| Module | Dépendances projet | Notes |
|---|---|---|
| `kffi` | — | aucun `project(...)` |
| `kffi-benchmark-spi` | — | SPI pur (aucun `project(...)`) |
| `kffi-benchmark-jvm` | `project(":kffi")`, `project(":kffi-benchmark-spi")` (config `jmh`) | + `gson:2.13.1` et `kotlin-stdlib` hardcodés |
| `kffi-benchmark-native` | `project(":kffi")`, `project(":kffi-benchmark-spi")` (macosMain) | **+ dépendance FILESYSTEM :** `benchFixtureHeaderDir = layout.projectDirectory.dir("../kffi-benchmark-jvm/src/jmh/resources")` et `benchFixtureSource = ../kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c` (lignes 9-10) |
| `kffi-benchmark-android` | `project(":kffi")`, `project(":kffi-benchmark-spi")` (androidMain) | **+ dépendance de SORTIE de build :** `stageKffiBenchFixture` lit `fileTree(project(":kffi").layout.buildDirectory.dir("intermediates/cmake/debug/obj")) { include("**/libkffi_bench_fixture.so") }` (lignes 67-74) |

### 2.2 Dépendances externes (kextract, wgpu4k-native, libs)

- **kextract — AUCUNE dépendance build/runtime dans les 5 modules.** `rg "kextract" kffi*` ne trouve que des **commentaires** dans le code (`kffi_upcall.c`, `kffi_engine.c`, `JvmDowncallEngine.kt`, `JvmUpcallEngine.kt`, `JvmDowncallEngineShapeTableTest.kt`, `TestUpcallDispatchers.kt`). **La génération vit dans le host :** `wgpu4k-native/build.gradle.kts:311-531` (task `generateBindingsFromHeader`, `dependsOn(":kextract:createKextractImage")`, `kextractDistribution`…). kffi est le **runtime** des bindings générés, jamais le générateur → kextract reste au host, le repo cible n'en a pas besoin.
- **wgpu4k-native — AUCUNE référence** (build ni runtime) dans les 5 modules. Les occurrences `wgpu4k` se limitent au README kffi (contexte parent) et aux chemins absolus machine dans `kffi/benchmarks/results/*.json` (artefacts de mesure, sans impact build) → **justifie l'exclusion de `benchmarks/results/` du split** (cf. §1.1).
- **Kadre — AUCUNE référence** dans kffi\* (l'`includeBuild` n'est consommé que par le host).
- **Libs tierces (repo cible, via `gradle/libs.versions.toml`) :** kotlin 2.3.21, agp 9.0.0, kotest 6.1.11, ksp, androidx-test, jna 5.18.1 (backend Android), dokka 2.2.0, vanniktech 0.36.0, gson (buildSrc). **Hors catalog :** `me.champeau.jmh` 0.7.2 et `gson:2.13.1` hardcodés dans `kffi-benchmark-jvm/build.gradle.kts` — fonctionnent tels quels dans le repo cible.
- **Toolchain externe :** `cc`/`ar` (fixtures C), JDK 25 toolchain (`java { toolchain { 25 } }`), SDK Android + NDK 30 (`ndkVersion = "30.0.15729638"`, compileSdk 36) — requis par le build kffi, identique host/cible.

### 2.3 buildSrc / convention plugins

- **`buildSrc` est réutilisable TEL QUEL** (indépendant des includes du settings racine) :
  - `buildSrc/settings.gradle.kts` → version catalog `libs` via `from(files("../gradle/libs.versions.toml"))` — **contrainte de layout :** le repo cible doit garder `buildSrc/` à la racine et `gradle/libs.versions.toml` à la racine.
  - `buildSrc/build.gradle.kts` → deps : plugin download (de.undercouch), bundle dokka, plugin AGP, vanniktech maven-publish, gson, plugin kotlin-multiplatform.
  - `publish.gradle.kts` (précompilé `publish`) → **déjà adapté kffi par M2.1** (branch `isKffiProject`, url/scm `Graphiks-org/kffi`, description kffi). ⚠️ Référence `rootProject.tasks.named("cleanPublicationVerificationRepository")` (ligne 81) → **le root `build.gradle.kts` du repo cible DOIT définir ce task** (vérifié au test §5). Restes wgpu inoffensifs : property `wgpu4k.jvmVerificationPublication` ; follow-up F4 : `inceptionYear 2024`.
  - `Platform.kt` (générique) ; `DSL.kt` + `FileDownloading.kt` (utilisés uniquement par `wgpu4k-native/build.gradle.kts:125,300` via `configureDownloadTasks`/`Platform`) → **dead code dans le repo cible**, compilent sans erreur mais peuvent être supprimés au nettoyage M4.
- **Plugins appliqués par les 5 modules :** `kotlin-multiplatform`, `publish` (buildSrc), `com.android.library`, `io.kotest`, `com.google.devtools.ksp` (kffi), `me.champeau.jmh` (jvm), `org.jetbrains.kotlin.jvm` (jvm).

---

## 3. Fichiers racine : copier vs adapter

| Fichier | Action | Détail |
|---|---|---|
| `gradlew`, `gradlew.bat`, `gradle/wrapper/*` | **Copier tel quel** | Gradle 9.5.0 |
| `gradle/libs.versions.toml` | **Copier tel quel** | rien de wgpu dans les libs/plugins utilisés par kffi (les entrées demo/glfw/rococoa sont inutilisées par les 5 modules mais inoffensives — nettoyage optionnel) |
| `gradle.properties` | **Copier, retirer `wgpu.base.url`** | le reste est requis (CInteropCommonization, ignoreDisabledTargets, android.*, jvmargs) |
| `.gitignore` | **Copier tel quel** | |
| `LICENSE` | **Copier tel quel** | MIT |
| `buildSrc/` | **Copier tel quel** | cf. §2.3 (nettoyage optionnel de DSL.kt/FileDownloading.kt) |
| `settings.gradle.kts` | **Adapter** | `rootProject.name = "kffi"` ; supprimer `includeBuild("Kadre")` et les 7 includes host (`wgpu4k-native`, `wgpu4k-native-specs`, `demo:common`, `demo:desktop-and-ios`, `demo:android`, `demo:android-native`, `kextract`) ; garder pluginManagement (foojay 0.8.0) + repos google/mavenCentral ; n'inclure que les 5 modules |
| `build.gradle.kts` (racine) | **Adapter** | garder `allprojects { repositories }` + le task `cleanPublicationVerificationRepository` (requis par le plugin publish) ; remplacer group `io.ygdrasil` → `org.graphiks` et version → kffi propre (`1.0.0-SNAPSHOT`, M2.4) ; **supprimer** la partie wgpu de `verifyPublicationMetadata` (`:wgpu4k-native:publish…`) et `verifyPublishedConsumer`/`gradle/publication-consumer` (ou les réécrire en consumers kffi) |
| `gradle/publication-consumer`, `gradle/jvm-bootstrap-consumer` | **Ne pas copier** (hôte) | consumers de vérification wgpu (`wgpu4kVersion`, `io.ygdrasil`) ; le repo cible peut en avoir un équivalent kffi (à décider en M4) |
| `.github/workflows/kffi-publish-snapshots.yml` | **Copier (adapter M4.2)** | push main ; **ADAPTER la ligne `export VERSION="$(date +%Y%m%d%H%M%S)-SNAPSHOT"` → `export KFFI_VERSION=…`** : `VERSION` n'est lue que par le root build host (`System.getenv("VERSION")`) ; le root cible utilise le mécanisme M2.4 (`kffi.version` gradle property / `KFFI_VERSION` env) ; retirer `submodules: recursive` (pas de submodule cible). ⚠️ **F1** : ce workflow publie `:kffi` + `:kffi-benchmark-spi` — spi est l'un des modules F1 (`io.ygdrasil`), donc **sans le fix F1, le premier push du repo cible mis-publie `io.ygdrasil:kffi-benchmark-spi` sur Central** — F1 doit être corrigé avant la première publication cible (P5 annexe) |
| `.github/workflows/kffi-benchmark-ci.yml` | **Copier tel quel** | garder `submodules: recursive` (inoffensif sans submodule, cohérent avec le host) |
| `.github/workflows/test.yml` | **Extraire** | jobs kffi (lignes ~88, 133-169 : `:kffi:jvmTest`, compiles iOS/macOS/Linux/Mingw, `macosArm64Test`/`linuxX64Test`/`mingwX64Test`) mélangés aux jobs wgpu — le repo cible a besoin d'un workflow `kffi-test.yml` dédié (les lignes kffi de test.yml sont réutilisables telles quelles, en retirant les parties wgpu) |
| `.gitmodules` | **Ne pas copier** | |
| `hs_err_pid*.log`, `replay_pid*.log`, `build/`, `kffi/build/`… | **Ignorer** | non trackés (`.gitignore`) |

---

## 4. Résultat du test de faisabilité (Step 4)

### 4.1 Méthode

Copie isolée (rsync, sans `build/`, `.gradle/`, `hs_err*`, `replay_pid*`, `benchmarks/results/` — exclusion alignée avec §1.1) des 5 modules + `buildSrc/` + `gradle/` + `gradlew` + `gradle.properties` dans
`/var/folders/81/9k3fbzrd42b_r_vm8fkfy16w0000gn/T/opencode/kffi-split-test`, avec :

- `settings.gradle.kts` minimal : `rootProject.name = "kffi"`, pluginManagement (foojay 0.8.0), repos google+mavenCentral, **uniquement les 5 includes** ;
- `build.gradle.kts` adapté : `allprojects` group `org.graphiks` version `1.0.0-SNAPSHOT` + `cleanPublicationVerificationRepository` + `verifyPublicationMetadata` limité à `:kffi`/`:kffi-benchmark-spi`/`:kffi-benchmark-jvm` (preuve que le task racine requis par le plugin `publish` compile et s'évalue).

Aucun fichier du worktree n'a été modifié (`git status` propre après test).

### 4.2 Résultats

| Commande | Résultat |
|---|---|
| `./gradlew :kffi:jvmTest` | ✅ **BUILD SUCCESSFUL** (6 s ; fixtures C `cc`, kotest verts) |
| `./gradlew :kffi-benchmark-spi:jvmTest :kffi-benchmark-jvm:compileKotlin :kffi-benchmark-native:compileKotlinMacosArm64 :kffi-benchmark-android:assembleDebugAndroidTest` | ✅ **BUILD SUCCESSFUL** (19 s, 115 tasks) — couvre : SPI + kotest ; JMH module (compile) ; cinterop `benchFixture` (cc/ar arm64) du module native **lecture du fixture dans kffi-benchmark-jvm** ; CMake Android de `:kffi` (`libkffi_bench_fixture.so`) + **staging dans l'APK androidTest** de kffi-benchmark-android |

### 4.3 Conclusions

1. **Le build des 5 modules est autonome** sans kextract, wgpu4k-native, Kadre, demo : configuration, compilation, tests JVM, cinterop native et assemble Android passent dans le repo simulé.
2. **Les dépendances croisées non-Gradle fonctionnent en isolation** : (a) `kffi-benchmark-native` → fixture de `kffi-benchmark-jvm` (chemin relatif `../kffi-benchmark-jvm/…`, préservé car les deux partent) ; (b) `kffi-benchmark-android` → sortie CMake de `:kffi` (project dep, préservé).
3. **buildSrc compile avec seulement les modules kffi** (version catalog + plugin publish + dokka), moyennant la présence du task racine `cleanPublicationVerificationRepository`.
4. Avertissements non bloquants, identiques au host : dépréciation AGP 9 `kotlin-multiplatform` + `com.android.library` (migration `com.android.kotlin.multiplatform.library` à suivre côté host) ; `sun.misc.Unsafe` terminally deprecated (JVM 25, attendu).
5. Conditions d'environnement identiques host/cible : JDK 25 toolchain, `cc`/`ar`, Android SDK + NDK 30, `ANDROID_HOME`.

---

## 5. Surprises / découvertes

1. **kextract n'est référencé nulle part dans les 5 modules au build** — uniquement dans des commentaires source. La génération (`generateBindingsFromHeader` dans `wgpu4k-native/build.gradle.kts:311-531`) reste intégralement au host. Le repo cible est **runtime-only** : c'est exactement ce que le split doit produire.
2. **`kffi-benchmark-native` lit le fixture C de `kffi-benchmark-jvm` par chemin de fichier** (`../kffi-benchmark-jvm/src/jmh/resources/bench_fixture.{c,h}`) — dépendance invisible aux yeux de Gradle. Les deux modules partent ensemble, le chemin relatif est préservé ; à ne PAS éclater en repos séparés.
3. **`kffi-benchmark-android` consomme la sortie CMake de `:kffi`** (`stageKffiBenchFixture`) — le module android du repo cible dépend du build natif Android du module kffi. Vérifié fonctionnel en isolation (§4).
4. **Le plugin `publish` (buildSrc) exige un task du root** (`cleanPublicationVerificationRepository`) — le root `build.gradle.kts` du repo cible doit le conserver, sinon échec de configuration (le host l'oublierait facilement car il y est déjà).
5. **Follow-up F1 actif pour le split** : les 4 modules benchmark publient encore sous `io.ygdrasil` (groupId vanniktech figé à l'application du plugin ; l'override `group` est no-op) — à corriger avant la publication depuis le repo cible (tracké P5 annexe).
6. **Version :** le root host force `io.ygdrasil`/`v29.0.0-SNAPSHOT` globalement ; le repo cible doit porter sa propre version kffi (`1.0.0-SNAPSHOT`, M2.4) — le root adapté doit retirer le bloc `allprojects { group/version }` host ou le remplacer (fait dans le test §4).
7. **`test.yml` hôte est mixte** (kffi + wgpu + demo + kextract) — le repo cible a besoin d'un workflow test dédié extrait des jobs kffi (lignes ~75-169).
8. **Junk local :** junk `hs_err_pid*.log`/`replay_pid*.log` gitignorés (`.gitignore` : `*.log`, non trackés) — ne partent pas dans le split, à ignorer.
9. `mavenLocal()` dans `allprojects.repositories` (host) : conservé dans le root adapté — inoffensif mais optionnel dans le repo cible.

---

## 6. Conclusion M2.2

L'inventaire confirme le découpage du plan P5 : les 5 modules `kffi*` sont **auto-suffisants** (aucune dépendance build/runtime sur wgpu4k-native, kextract, Kadre ou demo), et le **test de faisabilité est vert** pour `:kffi:jvmTest`, SPI, JMH, native (cinterop + harness) et android (assemble + staging). Le repo cible doit copier la machinerie racine (wrapper, version catalog, gradle.properties, .gitignore, LICENSE, buildSrc) et **adapter** `settings.gradle.kts`, le root `build.gradle.kts` (task `cleanPublicationVerificationRepository` obligatoire, vérification wgpu retirée) et les workflows (2 copiés — `kffi-publish-snapshots` à adapter : `KFFI_VERSION` + F1 avant première publication — 1 extrait de test.yml) ; `kffi/benchmarks/results/` est **exclu** du contenu extrait. Le split M4 peut s'exécuter sur cette base.
