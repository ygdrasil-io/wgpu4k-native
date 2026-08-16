# kffi P5 — kextract générique + publication + migration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Clôturer le socle kffi : (1) optimiser le mode unsafe JVM (décision P3/P4 : tenter de battre le mode sûr via une garde de close allégée) ; (2) splitter kffi + kffi-benchmark-* vers le repo indépendant `Graphiks-org/kffi` avec publication `org.graphiks:kffi-*` ; (3) basculer wgpu4k-native sur la version publiée ; (4) consumer test externe (mini-projet généré avec kextract, build + run JVM/native).

**Architecture:** Le split est réalisé par extraction de sous-arbre (git subtree/split) de `kffi/`, `kffi-benchmark-spi/`, `kffi-benchmark-jvm/`, `kffi-benchmark-native/`, `kffi-benchmark-android/` vers `Graphiks-org/kffi` (repo PUBLIC existant, branche master, créé en P0 bis). Les build files sont adaptés (pom/url/scm → Graphiks-org, workflow snapshots activé en push sur main). Le repo hôte `wgpu4k-native` bascule sur la version publiée (snapshot) au lieu d'inclure les modules kffi — la CI hôte valide la consommation de l'artifact externe. L'optimisation unsafe JVM précède le split (le code part dans le repo cible).

**Tech Stack:** Kotlin Multiplatform, Gradle (vanniktech maven-publish, dokka), git subtree, GitHub Actions, kextract (submodule), sun.misc.Unsafe (JVM).

---

## Contexte et décisions P5

### État P4 (constaté, branche `feat/kffi-socle-generique`)

- **Infrastructure** : `buildSrc/src/main/kotlin/publish.gradle.kts` (vanniktech maven-publish + dokka) appliqué à `kffi` — mais le pom `url`/`scm` pointent vers `wgpu4k-native` (à corriger pour le split) ; `.github/workflows/kffi-publish-snapshots.yml` existe (déclenchement manuel, commentaire "At the P5 split... switch this back to push on main") ; `.github/workflows/kffi-benchmark-ci.yml` existe.
- **Repo cible** : `Graphiks-org/kffi` (PUBLIC, branche master, template KMP) — vide de code source (README/licence seulement).
- **Fallback C2** : en place (P1-P3) — table bornée générée depuis les headers + refus à la génération pour les formes hors table ; la généricité kextract contre n'importe quel header est déjà démontrée par wgpu.
- **Consommation actuelle** : `settings.gradle.kts` inclut `kffi`, `kffi-benchmark-*`, `kextract` (submodule klang-toolkit), `Kadre` (includeBuild), `demo:*`.
- **Décision unsafe JVM en attente** (annexe P3/P4) : mesuré plus lent que le mode sûr (scalarSafe 1.83 vs scalarUnsafe 2.53 ns) — le coût vient de la garde de close `rawAddress()` (scope isAlive check par accès) + chemin sun.misc.Unsafe. **Décision P5 (utilisateur) : optimiser** la garde pour tenter de battre le mode sûr.
- **Artifacts actuels** : groupe `org.graphiks` (kffi), version `v29.0.0-SNAPSHOT` (via `VERSION` env), publication vérifiée par `verifyPublicationMetadata` (kffi-jvm + wgpu4k-native-jvm).

### Décisions P5 (validées)

| Décision | Choix |
|---|---|
| Migration | **Split effectif avec push** : extraction de sous-arbre + push vers `Graphiks-org/kffi` ; le repo hôte bascule sur la version publiée |
| Mode unsafe JVM | **Optimiser** : garde de close allégée (coût réduit par accès) pour tenter de battre le mode sûr ; re-mesuré en P5 |
| Consumer test | **Mini-projet dans le repo hôte** : `kffi-consumer-test/` (module ou build séparé) qui consomme les artifacts publiés, génère des bindings kextract sur un header simple, build + run JVM/native |

### Critère de sortie P5

1. **Split** : `Graphiks-org/kffi` contient `kffi` + `kffi-benchmark-*` extraits avec histoire propre ; build indépendant du repo cible vert (compileKotlinJvm/Android/native + tests).
2. **Publication** : `org.graphiks:kffi-*` publiés (snapshot) sur Maven Central depuis le repo cible ; pom url/scm corrects ; workflow snapshots déclenché par push sur main.
3. **Bascule** : `wgpu4k-native` consomme les artifacts publiés (plus de `include("kffi")`) ; CI hôte verte (tests wgpu avec kffi externe) ; `verifyPublicationMetadata` adapté.
4. **Consumer test** : `kffi-consumer-test/` — build + run JVM + native sur des bindings générés par kextract contre un header simple ; vérifie l'API publique (MemoryAllocator, memoryScope, NativeAddress, callbacks, option unsafe).
5. **unsafe JVM optimisé** : `scalarUnsafe` ≤ `scalarSafe` mesuré (ou régression documentée) ; re-baseline partielle upcall/scalar.
6. Toutes les suites restent vertes dans le repo hôte avant le split et dans le repo cible après.

### Files map

**Repo hôte (avant split) :**
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt` — garde de close allégée (M1)
- `kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt` — test perf/contrat (M1)
- `buildSrc/src/main/kotlin/publish.gradle.kts` — pom url/scm → Graphiks-org/kffi (M2)
- `.github/workflows/kffi-publish-snapshots.yml` — trigger push main (M2)
- `settings.gradle.kts` — retrait des modules kffi après split, bascule consommation (M4)
- `wgpu4k-native/build.gradle.kts` — dépendance version publiée (M4)
- `kffi-consumer-test/` — nouveau mini-projet (M3)

**Repo cible (Graphiks-org/kffi, via split) :**
- `kffi/`, `kffi-benchmark-spi/`, `kffi-benchmark-jvm/`, `kffi-benchmark-native/`, `kffi-benchmark-android/` — sous-arbre extrait
- `settings.gradle.kts` — rootProject adapté (kffi en module racine)
- `.github/workflows/` — CI + snapshots du repo cible

---

## Milestone M1 — Optimisation unsafe JVM

### Task M1.1: Garde de close allégée

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt`

- [ ] **Step 1: Comprendre le coût actuel**

`rawAddress()` (MemoryBuffer.jvm.kt) fait par accès unsafe : `scopedSegment?.scope()` (null-check + appel FFM) → `scope.isAlive` (appel natif/FFM) → retour rawValue. Le benchmark P4 montre scalarUnsafe 2.53 ns vs scalarSafe 1.83 ns — la garde + le chemin Unsafe coûtent plus que le check FFM JIT-éliminé.

- [ ] **Step 2: Écrire le test de contrat (garde toujours active, coût réduit)**

Adapter/étendre `MemoryBufferUnsafeJvmTest` — le contrat I2-a doit rester : use-after-close lève IllegalStateException même en unsafe. Le test existant le vérifie déjà ; ne pas le changer. Ajouter éventuellement un commentaire perf.

- [ ] **Step 3: Optimiser la garde**

Options (choisir la moins invasive qui préserve la sémantique) :
- (a) **Garder une référence au scopedSegment et vérifier via `scopedSegment.scope()` mais avec un cache** : le scope ne change jamais pour un buffer donné — la vérification `isAlive` peut être amortie ? NON : la fermeture est asynchrone, la vérification doit être à jour. 
- (b) **`MemorySegment.isAccessibleBy(thread)` ou une lecture dummy** : tester la vivacité par un accès FFM minimal au lieu de `scope().isAlive` (2 appels) — par ex. un `get` d'un octet sur le segment scopé qui lève IllegalStateException si fermé, mais ça coûte un check FFM complet.
- (c) **Remplacer la garde `scope().isAlive` par un drapeau local au buffer** : le `MemoryAllocator` (qui possède l'arène) met un AtomicBoolean `closed` au `close()`, le buffer le lit (1 load volatil) au lieu de `scope().isAlive` (appel FFM). L'allocateur et le buffer sont dans le même module — l'allocateur peut propager un handle de fermeture au buffer. C'est LA voie propre : 1 load volatil vs 2 appels FFM.
- (d) Vérifier si `MemorySegment.scope()` est déjà bon marché sur la JVM cible (JDK 25) — peut-être que le coût vient d'ailleurs (chemin Unsafe lui-même).

RECOMMANDATION : (c) — l'allocateur possède l'arène et peut propager un `closed` flag au buffer (AtomicBoolean ou un simple `AtomicReference<Boolean>`), le buffer le lit par accès unsafe au lieu de `scope().isAlive`. Mesurer avant/après via le benchmark marshaling (M1.2).

- [ ] **Step 4: Vérifier les tests**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.MemoryBufferUnsafeJvmTest"`
Expected: verts (le contrat I2-a : use-after-close lève toujours IllegalStateException en unsafe).

- [ ] **Step 5: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt
git commit -m "perf(kffi): lightweight unsafe-mode close guard via allocator-owned closed flag"
```

### Task M1.2: Re-mesure unsafe vs sûr

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt` (axes existants scalarSafe/scalarUnsafe)

- [x] **Step 1: Lancer le smoke des axes scalarSafe/scalarUnsafe**

Run: `./gradlew :kffi-benchmark-jvm:jmhJar` puis `java -jar ... "MarshalingBenchmarks" -f 1 -wi 3 -i 5`
Expected: comparer scalarSafe vs scalarUnsafe — objectif : unsafe ≤ safe (ou écart < 0.3 ns).

Résultat M1.2 (post-M1.1, même config que P3 : avgt, wi=3, i=5, 1s, fork=2, serial) :

| axe | P3 (avant M1.1) | M1.2 (après) | Δ |
|---|---|---|---|
| scalarSafe | 1,83 ± 0,01 ns | 1,842 ± 0,010 ns | ~0 (bruit) |
| scalarUnsafe | 2,53 ns | 2,136 ± 0,071 ns | **-0,39 ns (-15%)** |
| écart | 0,70 ns (27,6%) | **0,294 ns (~16%)** | -0,41 ns |

Smoke (f1, wi3, i5) : scalarSafe 1,984 ± 0,027 vs scalarUnsafe 2,198 ± 0,008 — écart 0,214 ns, même direction. Intervalles de confiance disjoints (1,842±0,010 vs 2,136±0,071) — l'écart résiduel est réel.

- [x] **Step 2: Analyser**

**Verdict : unsafe > safe encore, mais objectif d'écart atteint.** L'optimisation M1.1 est **partiellement validée** :
- Le gap a fondu de 0,70 ns → 0,29 ns (sous le seuil 0,3 ns de l'objectif) ; le chemin unsafe gagne -0,39 ns (-15%), l'écart relatif passe de 27,6% à ~16%.
- scalarSafe est inchangé (1,83 → 1,84 ns) — confirme la conclusion d'escape-analysis P4 : le check de la voie sûre est JIT-éliminé, le plancher ~1,8 ns est le write+read FFM nu.
- **Chemin restant documenté** : le résidu ~0,29 ns est le coût du chemin `sun.misc.Unsafe` lui-même (le JVM check de l'appel Unsafe), pas la garde de close (désormais 1 load volatil). La conclusion P3/P4 tient : **le mode unsafe JVM est une surface API, pas une optimisation** — l'objectif P5 (réduire l'écart au minimum) est atteint à ~0,29 ns près du plancher.
- Machine flakiness P3/P4 respectée : run serial (-t 1), même config, smoke f1 puis confirmation fork=2.

- [x] **Step 3: Commit (si changements de benchmark)**

Aucun changement de benchmark nécessaire — les axes existaient déjà et n'ont pas eu besoin d'adaptation. Résultat documenté dans ce plan (commit docs).

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt
git commit -m "bench(jvm): re-measure unsafe mode after lightweight close guard"
```

---

## Milestone M2 — Préparation du split

### Task M2.1: Adapter les build files pour le repo cible

**Files:**
- Modify: `buildSrc/src/main/kotlin/publish.gradle.kts`
- Modify: `.github/workflows/kffi-publish-snapshots.yml`

- [ ] **Step 1: Corriger le pom pour Graphiks-org/kffi**

Dans `buildSrc/src/main/kotlin/publish.gradle.kts`, remplacer :
- `url.set("https://github.com/wgpu4k/wgpu4k-native")` → `https://github.com/Graphiks-org/kffi`
- `scm.connection/developerConnection/url` → `scm:git:https://github.com/Graphiks-org/kffi.git` etc.
- `libraryDescription` → "kffi: multiplatform FFI binding foundation" (description générique, plus wgpu-specific)

- [ ] **Step 2: Activer le trigger snapshots sur push main**

`.github/workflows/kffi-publish-snapshots.yml` : remplacer le bloc `workflow_dispatch` par :

```yaml
on:
  push:
    branches: [main]
```

(le commentaire "Manual trigger only while staged" est supprimé — c'est le switch prévu pour le split).

- [ ] **Step 3: Vérifier**

Run: `./gradlew :kffi:publishAllPublicationsToPublicationVerificationRepository` (le repo de vérification local)
Expected: PASS — le pom généré pointe vers Graphiks-org/kffi.

- [ ] **Step 4: Commit**

```bash
git add buildSrc/src/main/kotlin/publish.gradle.kts .github/workflows/kffi-publish-snapshots.yml
git commit -m "chore(kffi): target Graphiks-org/kffi in publish metadata and snapshot trigger"
```

### Task M2.2: Inventaire du split (contenu + dépendances)

**Files:**
- (analyse seulement — pas de changement)

- [ ] **Step 1: Lister le contenu à splitter**

Les dossiers : `kffi/`, `kffi-benchmark-spi/`, `kffi-benchmark-jvm/`, `kffi-benchmark-native/`, `kffi-benchmark-android/`.
À EXCLURE du split (restent dans wgpu4k-native) : `wgpu4k-native/`, `wgpu4k-native-specs/`, `demo/`, `Kadre/`, `kextract/` (submodule — dépendance build, pas du code kffi).

- [ ] **Step 2: Lister les dépendances inter-modules**

`rg "kffi" settings.gradle.kts` + les `project(":kffi...")` dans les build files des modules à splitter. Déterminer :
- kffi-benchmark-spi : dépend de kffi ? (probablement interface SPI pure)
- kffi-benchmark-jvm/native/android : dépendent de kffi + kffi-benchmark-spi
- Les dépendances externes (kotlinx, kotest, jmh, libs.versions.toml) : le repo cible a besoin de son propre `gradle/libs.versions.toml` + `buildSrc`

- [ ] **Step 3: Vérifier que les modules se compilent indépendamment**

Construire un arbre de travail temporaire avec les 5 modules extraits + un settings.gradle.kts minimal, compiler. Ce sera la base du split M4.

- [ ] **Step 4: Commit (si des corrections d'isolation sont nécessaires)**

```bash
git add .
git commit -m "chore(kffi): isolate split modules from host build"
```

### Task M2.3: Documentation consommateur du repo cible (README + quickstart)

**Files:**
- Create: `kffi-consumer-doc/` (brouillon) ou directement dans le repo cible `README.md` + `docs/quickstart.md` (préparé ici, copié au split M4)

- [ ] **Step 1: Rédiger le README du repo cible**

Le README de `Graphiks-org/kffi` doit couvrir (spec P5 : "Documentation consommateur : README + quickstart") :
- Qu'est-ce que kffi (couche FFI multiplateforme, 3 backends, contrat memory-safety P3)
- Comment consommer : dépendance `org.graphiks:kffi-jvm` / `kffi-android` / `kffi-native` (artifacts)
- Génération de bindings : pointer vers kextract (klang-toolkit/kextract)
- Chargement de lib native (bootstrap JVM, dlopen Android)
- `memoryScope` + durée de vie (I2-a : scope dans MemoryBuffer, UB post-close documenté)
- Callbacks : cycle de vie des trampolines (P1/P4)
- Option `unsafe` : politique de durée de vie P2, opt-in par allocateur/buffer (P3), différence native build-time
- Versionnement : semver, 1.0 = premier contrat stable (le redessin P1-P4 est le dernier avant 1.0)

- [ ] **Step 2: Rédiger le quickstart**

`docs/quickstart.md` : un exemple complet de bout en bout — dépendance Gradle, memoryScope, allocation, write/read, callback simple, option unsafe. (S'appuyer sur le consumer test M3 comme source de vérité du code.)

- [ ] **Step 3: Vérifier la cohérence**

Le code du quickstart doit compiler — utiliser les mêmes extraits que le consumer test (M3) pour éviter la dérive doc/code.

- [ ] **Step 4: Commit**

```bash
git add kffi-consumer-doc
git commit -m "docs(kffi): consumer README and quickstart draft (semver contract, lifetime, unsafe)"
```

### Task M2.4: Contrat de compatibilité semver

**Files:**
- Modify: `kffi/build.gradle.kts` (version propre au module kffi)

- [ ] **Step 1: Versions indépendantes pour les artifacts kffi**

Le repo hôte utilise `v29.0.0-SNAPSHOT` global (via `VERSION` env). Pour le repo cible, le module kffi doit avoir sa PROPRE version indépendante (découplée de wgpu4k-native) :

```kotlin
// kffi/build.gradle.kts — version propre
val kffiVersion = providers.gradleProperty("kffi.version")
    .orElse(System.getenv("KFFI_VERSION"))
    .orElse("1.0.0-SNAPSHOT")
version = kffiVersion
```

Le contract semver : `1.0.0` = premier contrat stable (le redessin P1-P4 est terminé). Les snapshots restent `1.0.0-SNAPSHOT` jusqu'à la release.

- [ ] **Step 2: Vérifier la publication avec la version kffi**

Run: `./gradlew :kffi:publishAllPublicationsToPublicationVerificationRepository -Pkffi.version=1.0.0-SNAPSHOT`
Expected: PASS — l'artifact est `org.graphiks:kffi-jvm:1.0.0-SNAPSHOT`.

- [ ] **Step 3: Commit**

```bash
git add kffi/build.gradle.kts
git commit -m "chore(kffi): independent semver versioning (1.0.0-SNAPSHOT) for kffi artifacts"
```

---

## Milestone M3 — Consumer test (dans le repo hôte)

### Task M3.1: Mini-projet `kffi-consumer-test/`

**Files:**
- Create: `kffi-consumer-test/` (nouveau build indépendant dans le repo hôte, PAS dans le split)
- Create: `kffi-consumer-test/settings.gradle.kts`
- Create: `kffi-consumer-test/build.gradle.kts`
- Create: `kffi-consumer-test/src/jvmMain/kotlin/.../Main.kt`
- Create: `kffi-consumer-test/src/nativeMain/kotlin/.../Main.kt`

- [ ] **Step 1: Créer le build indépendant**

Le consumer test est un mini-projet KMP INDÉPENDANT (son propre settings.gradle.kts, pas un module du build racine) qui consomme les artifacts publiés `org.graphiks:kffi-*` via Maven Central snapshot. Il est placé dans `kffi-consumer-test/` du repo hôte pour le versioning, mais avec son propre `gradlew`/`settings.gradle.kts` pour démontrer la consommation externe.

`kffi-consumer-test/settings.gradle.kts` :

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/") // snapshots kffi
    }
}
rootProject.name = "kffi-consumer-test"
```

- [ ] **Step 2: Écrire le code consommateur**

Un header C simple est généré par kextract (réutiliser une fixture existante ou un header minimal) et les bindings sont générés dans le consumer test. Le Main démontre l'API publique :

```kotlin
// jvmMain
package consumer

import org.graphiks.kffi.*

fun main() {
    memoryScope { scope ->
        val buffer = scope.allocateBuffer(16u)
        buffer.writeLong(0xCAFE, 0u)
        check(buffer.readLong(0u) == 0xCAFEuL)
    }
    // unsafe opt-in
    val unsafeAllocator = MemoryAllocator(unsafe = true)
    val unsafeBuffer = unsafeAllocator.allocateBuffer(8u)
    unsafeBuffer.writeLong(1L, 64u) // hors bornes : pas d'exception
    unsafeAllocator.close()
    println("kffi consumer test OK")
}
```

Le build référence `org.graphiks:kffi-jvm` (JVM) et `org.graphiks:kffi-native` (native). La génération kextract des bindings : soit pré-générée et committée, soit une tâche de génération dans le build (dépendance sur l'outil kextract — voir la task M3.2).

- [ ] **Step 3: Vérifier le build JVM + run**

Run: `cd kffi-consumer-test && ./gradlew :compileKotlinJvm` puis run le Main
Expected: "kffi consumer test OK".

- [ ] **Step 4: Vérifier le build native + run**

Run: `./gradlew :compileKotlinMacosArm64` + run
Expected: OK.

- [ ] **Step 5: Commit**

```bash
git add kffi-consumer-test
git commit -m "test(consumer): external kffi consumer project (kextract bindings, JVM+native)"
```

### Task M3.2: Génération kextract dans le consumer test

**Files:**
- Modify: `kffi-consumer-test/build.gradle.kts`
- Create: `kffi-consumer-test/src/commonMain/kotlin/consumer/Bindings.kt` (généré ou référencé)

- [ ] **Step 1: Choisir la méthode de génération**

Option A — pré-généré : un header C simple (ex. `kffi-consumer-test/src/nativeInterop/cinterop/consumer.h` avec 2-3 fonctions) est transformé par kextract une fois, les bindings générés sont committés dans `src/commonMain`. Simple, démontre les bindings.
Option B — tâche Gradle : le build appelle kextract à la compilation. Plus représentatif du workflow réel mais dépend de l'outil.

RECOMMANDATION : A (pré-généré, committé) pour la robustesse du test — le but est de vérifier que l'API kffi PUBLIÉE compile et tourne, pas de tester kextract lui-même (déjà couvert par les suites kextract).

- [ ] **Step 2: Générer les bindings**

Créer le header, lancer kextract (via la distribution du repo hôte ou le sous-module), committer les bindings générés dans le consumer test.

- [ ] **Step 3: Étendre le Main pour appeler les bindings générés**

Appeler les fonctions du header (ex. `bench_empty()` retourne 42, un callback simple) via l'API kffi.

- [ ] **Step 4: Vérifier JVM + native**

Run: `./gradlew :runJvm` (ou équivalent) + `:runDebugExecutableMacosArm64`
Expected: OK avec les bindings appelés.

- [ ] **Step 5: Commit**

```bash
git add kffi-consumer-test
git commit -m "test(consumer): kextract-generated bindings against published kffi API"
```

---

## Milestone M4 — Split et migration

### Task M4.1: Extraire le sous-arbre kffi

**Files:**
- (script de migration — la tâche est exécutée dans un worktree temporaire)

- [ ] **Step 1: Préparer l'extraction**

Créer un script `tools/split-kffi.sh` (committé dans le repo hôte pour reproductibilité) qui :
1. Crée une branche temporaire `kffi-split` depuis HEAD
2. Utilise `git subtree split` (ou `git filter-repo --path`) sur les 5 modules (`kffi`, `kffi-benchmark-spi`, `kffi-benchmark-jvm`, `kffi-benchmark-native`, `kffi-benchmark-android`)
3. Construit l'arbre du repo cible : les 5 modules + `settings.gradle.kts` minimal + `gradle/libs.versions.toml` + `buildSrc` (adaptés du repo hôte)
4. Clone `Graphiks-org/kffi` dans un répertoire temporaire, y applique l'arbre extrait

- [ ] **Step 2: Adapter le settings.gradle.kts du repo cible**

Le repo cible a besoin de son propre settings : `rootProject.name = "kffi"`, `include("kffi")`, `include("kffi-benchmark-spi")`, etc. — les modules sont référencés par chemin local (pas par coordinate). Le `kffi` du repo cible est le module racine.

- [ ] **Step 3: Vérifier le build du repo cible**

Run (dans le clone temporaire) : `./gradlew :kffi:compileKotlinJvm :kffi:compileDebugKotlinAndroid :kffi:compileKotlinMacosArm64` + `:kffi:jvmTest` + `:kffi-benchmark-spi:compileKotlinJvm`
Expected: verts — le repo cible est autonome.

- [ ] **Step 4: Commit (le script + la préparation, pas le push)**

```bash
git add tools/split-kffi.sh
git commit -m "chore(kffi): split script for Graphiks-org/kffi migration"
```

### Task M4.2: Publier depuis le repo cible

**Files:**
- (repo cible — workflow snapshots)

- [ ] **Step 1: Vérifier la publication locale**

Run (clone temporaire du repo cible) : `./gradlew :kffi:publishAllPublicationsToMavenCentral -Pversion=<date>-SNAPSHOT` (avec les credentials si disponibles — sinon la vérification locale `publishAllPublicationsToPublicationVerificationRepository`)
Expected: publication vérifiée.

- [ ] **Step 2: (Si credentials disponibles) Publier les snapshots**

Suivre le workflow `kffi-publish-snapshots.yml` (désormais déclenché par push sur main du repo cible) : push de la branche main du repo cible → GitHub Actions publie les snapshots.

- [ ] **Step 3: Vérifier la résolution**

Run (consumer test) : `./gradlew :dependencies --configuration jvmRuntimeClasspath` avec la version snapshot
Expected: `org.graphiks:kffi-jvm:<snapshot>` résolu depuis Maven Central snapshots.

### Task M4.3: Bascule du repo hôte sur la version publiée

**Files:**
- Modify: `settings.gradle.kts` (retrait des modules kffi)
- Modify: `wgpu4k-native/build.gradle.kts` (dépendance version publiée)
- Modify: `kffi-benchmark-*` (si retirés du repo hôte — ils partent avec le split)
- (adaptation des autres build files)

- [ ] **Step 1: Retirer les modules kffi du repo hôte**

`settings.gradle.kts` : retirer `include("kffi")`, `include("kffi-benchmark-spi")`, `include("kffi-benchmark-jvm")`, `include("kffi-benchmark-native")`, `include("kffi-benchmark-android")`. Les dossiers correspondants sont retirés (ils vivent désormais dans le repo cible).

- [ ] **Step 2: Ajouter la dépendance version publiée**

`wgpu4k-native/build.gradle.kts` : ajouter `implementation("org.graphiks:kffi-jvm:<version>")` (JVM) et les autres variants nécessaires (android, native). La version : le snapshot publié en M4.2.

- [ ] **Step 3: Vérifier la compilation hôte**

Run: `./gradlew :wgpu4k-native:compileKotlinJvm :wgpu4k-native:compileDebugKotlinAndroid :wgpu4k-native:compileKotlinMacosArm64`
Expected: verts — wgpu consomme kffi externe.

- [ ] **Step 4: Vérifier les tests hôtes**

Run: `./gradlew :wgpu4k-native:jvmTest`
Expected: verts (les tests wgpu avec kffi externe).

- [ ] **Step 5: Commit**

```bash
git add settings.gradle.kts wgpu4k-native/build.gradle.kts
git commit -m "chore(wgpu): consume published org.graphiks:kffi artifacts"
```

### Task M4.4: Push final vers Graphiks-org/kffi

**Files:**
- (push — exécuté par l'utilisateur ou avec confirmation explicite)

- [ ] **Step 1: Push du repo cible**

Run (clone temporaire) : `git push origin master`
Expected: le repo Graphiks-org/kffi contient le code kffi extrait.

- [ ] **Step 2: Vérifier la CI du repo cible**

Suivre les runs GitHub Actions du repo cible : CI verte (build + tests), snapshots publiés si credentials configurées.

- [ ] **Step 3: Vérifier le consumer test contre les artifacts publiés**

Run (consumer test, avec la version publiée) : `./gradlew :compileKotlinJvm` + run
Expected: OK — la boucle est bouclée : kffi publié → consommé → vérifié.

- [ ] **Step 4: Commit final dans le repo hôte (mise à jour de la doc)**

Mettre à jour le README racine : section kffi → pointe vers Graphiks-org/kffi, version consommée, quickstart.

```bash
git add README.md
git commit -m "docs(wgpu): point kffi usage to published Graphiks-org/kffi artifacts"
```

---

## Out of scope (follow-up)

- **P6+ éventuel** : optimisations restantes du callback runtime (stress test concurrent), re-baseline native complète (harness unsafe build-time), migration complète de Kadre.
- Le submodule kextract reste dans le repo hôte (dépendance de génération) — sa migration vers un repo dédié est traitée séparément (klang-toolkit/kextract, déjà sa propre organisation).
- La décision unsafe JVM : si l'optimisation M1 ne parvient pas à battre le mode sûr, la conclusion P3/P4 reste (surface API, pas optimisation) — documentée, pas bloquante.

## Annexe — Follow-ups trackés (reviews M2.1)

- **F1 (Important, AVANT M4.2)** : les 4 modules benchmark (`kffi-benchmark-spi`, `-jvm`, `-native`, `-android`) publient sous `io.ygdrasil` au lieu de `org.graphiks` — `group = "org.graphiks"` est no-op pour les coordonnées vanniktech (groupId figé à l'application du plugin, avant l'override de groupe). Confirmé empiriquement : pom généré `io.ygdrasil`. Le trigger push-main activé par M2.1 rend ce bug actif (mis-publication automatique à chaque push main). Fix : miroir du `afterEvaluate { groupId = "org.graphiks" }` de kffi/build.gradle.kts:221-227 dans les 4 modules benchmark. `verifyPublicationMetadata` ne couvre que `:kffi` et `:wgpu4k-native` — zéro couverture benchmark : étendre la vérification.
- **F2 (M4.4)** : mismatch de branche par défaut — le repo cible `Graphiks-org/kffi` a `master`, le workflow snapshots déclenche sur `main`. Décision : **renommer la branche par défaut du repo cible en `main`** (Settings → Branches → rename, cohérent avec `kffi-benchmark-ci.yml` et le workflow push-main) au moment du push M4.4.
- **F3 (préexistant, non bloquant)** : `verifyPublicationMetadata` échoue sans `-x :wgpu4k-native:generateBindingsFromHeader` — Gradle 9.5 strict-validation : `sourcesJar`-family consomme la sortie de `generateBindingsFromHeader` sans `dependsOn` déclaré. Fix à ajouter dans `wgpu4k-native/build.gradle.kts` (follow-up CI, pas dans le scope P5).
- **F4 (mineur)** : `inceptionYear.set("2024")` faux pour kffi (créé 2026) — conditionner ou retirer.
- **F5 (mineur, optionnel)** : pas de `paths:` filter sur le workflow snapshots — les push docs déclenchent des publications inutiles (snapshots timestampés, inoffensifs).
- **F6 (RÉSOLU — b780dc9c)** : `verifyPublicationMetadata` comparait l'edge wgpu→kffi et le lookup kffi à la version racine (v29.0.0-SNAPSHOT) — cassé par M2.4. Fix : lookup/edges kffi contre `project(":kffi").version`. Passe désormais modulo F3 (`-x :wgpu4k-native:generateBindingsFromHeader`).
- **F7 (tracké, M4.2)** : `kffi-publish-snapshots.yml` exporte `VERSION` (mécanisme host) qui n'atteint plus kffi — les snapshots publiés seraient tous `1.0.0-SNAPSHOT` non timestampés. Passer à `KFFI_VERSION=$(date...)-SNAPSHOT` dans le workflow au moment de la publication cible.
- **F8 (tracké, M4.2)** : le consumer test n'est pas câblé dans la CI hôte — `test.yml` vérifie l'ancien `gradle/jvm-bootstrap-consumer`. Ajouter un job CI qui publie vers mavenLocal (ou un repo local) et exécute `kffi-consumer-test` (JVM + native) en garde de régression de l'API publiée.
- **F9 (tracké, upstream kextract)** : gaps découverts par le consumer test M3.2 — (a) `JvmDowncallEngine` n'expose pas `callI2II` (2 int params) : présent au spec design (socle-generique-design.md:118) mais jamais implémenté — 2 fonctions scalaires int sont courantes en C ; (b) `long` rejeté par kextract (LP64/LLP64, KotlinKmpCAbiType.kt:180-188) — pas de shape `callL1L` non plus ; (c) `const char*` cassé de bout en bout : cinterop Kotlin/Native mappe toujours en `String?` (noStringConversion sans effet sur les params) → les bindings native générés ne compilent pas. Workarounds consumer documentés dans consumer.h. À remonter en issues klang-toolkit/kextract.

---

## Annexe — Résultats M1 (unsafe JVM optimisé)

- **M1.1** (a21bc505) : garde de close allégée — flag `AtomicBoolean` porté par l'allocateur (1 load volatil) au lieu de `scope().isAlive` (2 appels FFM) par accès unsafe. Contrat I2-a intact (test 4 non modifié, vert).
- **M1.2** (mesuré, config P3 avgt wi3/i5/1s/fork2) : scalarSafe 1.84 ns (inchangé — le check FFM est JIT-éliminé, plancher ~1.8 ns), scalarUnsafe **2.136 ns vs 2.53 ns P3 (−0.39 ns, −15%)**. Écart sûr/unsafe : 0.70 → **0.29 ns (~16%)** — objectif « écart < 0.3 ns » atteint, CI disjoints.
- **Conclusion** : l'optimisation est partiellement validée (gap réduit de moitié), mais le mode unsafe JVM reste plus lent que le mode sûr — le résidu ~0.29 ns est le coût du chemin `sun.misc.Unsafe` lui-même. **La décision P3/P4 tient : le mode unsafe JVM est une surface API de parité, pas une optimisation** — l'écart minimal est documenté comme le coût assumé de la surface.
