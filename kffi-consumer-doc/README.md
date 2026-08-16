# kffi — couche FFI multiplateforme (JVM / Android / Native)

[![Static Badge](https://img.shields.io/badge/Licence-MIT-blue?style=plastic)](https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FMIT_License)

`kffi` est une couche d'accès bas-niveau à la mémoire native et aux appels FFI
(foreign function interface), écrite en Kotlin Multiplatform. Elle est partagée
par les bindings [wgpu4k-native](https://github.com/wgpu4k/wgpu4k-native) et
utilisable par n'importe quel projet consommant des bibliothèques C.

C'est un module **runtime uniquement** : la génération des bindings est assurée
par un outil séparé, [kextract](https://github.com/klang-toolkit/kextract) (voir
[Génération de bindings](#génération-de-bindings)).

Le contrat `expect/actual` vit dans `commonMain` (`org.graphiks.kffi`) :
`NativeAddress`, `MemoryBuffer`, `MemoryAllocator` (+ `memoryScope`,
`globalMemory`), `CString`, `Callback`/`CallbackRuntime`. Le contrat de sécurité
mémoire est **unifié sur les trois backends** (spec P3).

- [Démarrage rapide](docs/quickstart.md)
- [Contrat de sécurité mémoire](#contrat-de-sécurité-mémoire)
- [Option `unsafe`](#option-unsafe)
- [Callbacks](#callbacks)
- [Versionnement](#versionnement)

## Backends

| Backend | Implémentation | Notes |
|---------|----------------|-------|
| **JVM** | Panama FFM (`java.lang.foreign`) | Arènes confinées ; downcalls via `MethodHandle` ; `jvmTarget` 24 |
| **Android** | Moteur Kotlin + JNI (`NativeEngine`/`UpcallEngine`) | `.so` embarqué dans l'AAR ; chemin de secours libffi |
| **Native** | `kotlinx.cinterop` | iOS, macOS, Linux, Windows (MinGW), Android Native |

## Consommer kffi

### Dépôts

Les versions **release** sont publiées sur Maven Central. Les **snapshots**
(publiés à chaque push sur `main`) sont sur le dépôt snapshots Sonatype :

```kotlin
// settings.gradle.kts — resolution repos
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

### Artifacts

Le groupe est `org.graphiks`. Les publications suivent le nommage Kotlin
Multiplatform standard :

| Contexte | Artifact | Notes |
|----------|----------|-------|
| Consommateur KMP | `org.graphiks:kffi` | Artifact racine (métadonnées) : Gradle résout automatiquement la variante plateforme |
| Projet JVM seul | `org.graphiks:kffi-jvm` | Exige un JDK avec `java.lang.foreign` (24+) |
| Projet Android | `org.graphiks:kffi-android` | AAR (variantes `release`/`debug`), minSdk 28 |
| Projet natif | `org.graphiks:kffi-<cible>` | Un artifact **par cible native** (voir la liste ci-dessous) |

Il n'existe **pas** d'artifact agrégé « kffi-native » : chaque cible native est
publiée séparément. Pour un consommateur KMP, déclarer simplement l'artifact
racine `org.graphiks:kffi` — Gradle choisit la variante correspondant à la cible
compilée.

Targets natives publiées : `kffi-iosx64`, `kffi-iosarm64`,
`kffi-iossimulatorarm64`, `kffi-macosx64`, `kffi-macosarm64`, `kffi-linuxx64`,
`kffi-linuxarm64`, `kffi-mingwx64`, `kffi-androidnativearm64`,
`kffi-androidnativex64`.

### Déclaration de dépendance

> **Note M2.4** : la coordonnée snapshot finale est `1.0.0-SNAPSHOT` — effective
> après la migration M2.4 (versionnement indépendant du module kffi). Avant
> cette migration, le module hérite de la version du dépôt hôte et les
> snapshots publiés sont `v29.0.0-<timestamp>-SNAPSHOT` (dépôt Sonatype).

```kotlin
// build.gradle.kts — projet KMP
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("org.graphiks:kffi:1.0.0-SNAPSHOT") // release : "1.0.0"
        }
    }
}
```

```kotlin
// build.gradle.kts — projet JVM seul
dependencies {
    implementation("org.graphiks:kffi-jvm:1.0.0-SNAPSHOT")
}
```

```kotlin
// build.gradle.kts — projet Android
dependencies {
    implementation("org.graphiks:kffi-android:1.0.0-SNAPSHOT")
}
```

Voir [docs/quickstart.md](docs/quickstart.md) pour un exemple complet de bout en
bout.

## Génération de bindings

kffi est **runtime-only** : il fournit le moteur d'exécution (mémoire,
downcall, upcall) mais pas de générateur. Les bindings Kotlin contre vos
headers C/ObjC sont générés par
**[kextract](https://github.com/klang-toolkit/kextract)** (générateur Kotlin
utilisant libclang), qui cible l'API publique `org.graphiks.kffi` :

- structs mémoire-backed (`MemoryBuffer`) et accès scalaires/tableaux ;
- wrappers de downcall par fonction (résolution de symbole au chargement) ;
- déclarations de callbacks (`CallbackType` canoniques) et trampolines
  d'upcall par forme de signature.

Le workflow de consommation : générer les bindings avec kextract → lier les
bindings à kffi → charger la bibliothèque native au démarrage (voir
ci-dessous).

## Charger une bibliothèque native

### JVM

1. Charger la bibliothèque dans le processus : `System.loadLibrary("monlib")`
   (ou la placer sur `java.library.path`) — les symboles résolus par
   `SymbolLookup.loaderLookup()` n'existent que pour les libs chargées par le
   classloader.
2. Résoudre les symboles à l'adresse brute :

```kotlin
import org.graphiks.kffi.findOrThrow

val symbol: Long = findOrThrow("mon_symbole") // UnsatisfiedLinkError si absent
```

Le runtime JVM kffi utilise lui-même les API restreintes de
`java.lang.foreign` (Linker, MethodHandles) : lancez la JVM avec
`--enable-native-access=ALL-UNNAMED`. Sans ce flag, la JVM émet un warning
(et bloquera l'appel dans une future version du JDK). C'est aussi requis pour
les upcalls émis par kextract sur le chemin de secours FFM direct.

### Android

L'AAR embarque le moteur `libkffi.so` (JNI) : il est chargé automatiquement
(`System.loadLibrary("kffi")` à l'initialisation de `NativeEngine`). La
bibliothèque consommée est chargée dynamiquement :

```kotlin
import org.graphiks.kffi.engine.NativeEngine

val handle = NativeEngine.loadNativeLibrary("/data/app/.../libmonlib.so") // dlopen
val symbol = NativeEngine.resolveSymbolIn(handle, "mon_symbole")
```

L'AAR embarque les règles R8 consommateur (appliquées au minification du
consommateur) et les ABIs `arm64-v8a`, `x86_64`, `armeabi-v7a`.

### Native

Le lien se fait à la compilation via cinterop (fichiers `.def`) : les bindings
générés par kextract et le module kffi sont liés à la bibliothèque native au
link final. Aucun `dlopen` manuel.

## Modèle mémoire

- `NativeAddress` — adresse native brute (value class sur `Long`). **Non
  bornée par nature** : tout accès typé passe par `MemoryBuffer` (borné) ou par
  l'option `unsafe`.
- `MemoryBuffer` — buffer borné sur une adresse native : `handler` (adresse) +
  `size` (taille en octets). Accès scalaires et tableaux pour toutes les
  familles (Byte/Short/Int/Long/Float/Double, signés et non signés, pointeurs).
- `CString` — chaîne C (UTF-8, terminée par `\0`) : allocation via
  `MemoryAllocator.allocateFrom`, lecture via `toKString()`.
- `MemoryAllocator` — arène confinée : allocation (`allocate`,
  `allocateBuffer`, `allocateFrom`, `bufferOf`, `bufferOfAddress`,
  `bufferOfAddresses`), fermeture (`close`), et `memoryScope { }` qui garantit
  la fermeture de l'arène en fin de bloc. `globalMemory` est une arène de
  durée de vie processus.

```kotlin
import org.graphiks.kffi.*

memoryScope { allocator ->
    val buffer = allocator.allocateBuffer(size = 16uL)

    buffer.writeInt(value = 42, offset = 0uL)
    val value = buffer.readInt(offset = 0uL) // 42

    buffer.readLong(offset = 12uL)
    // IndexOutOfBoundsException : MemoryBuffer access out of bounds: offset=12 width=8 size=16
}
```

## Contrat de sécurité mémoire

### Bornes-check

Tout accès typé (scalaire ou tableau) est vérifié : `offset + elementSize ≤ size`.
Hors bornes → `IndexOutOfBoundsException` avec offset/taille dans le message.
Défaut : vérifications actives.

### Durée de vie

Décision I2-(a) : le scope d'arène/session vit dans le `MemoryBuffer` (pas dans
`NativeAddress`).

- Buffer créé via `MemoryAllocator` (JVM) : porte le segment scopé de l'arène →
  accès après `close()` de l'arène → `IllegalStateException`.
- Buffer créé depuis une adresse brute (`MemoryBuffer(addr, size)`) : sans
  scope → accès post-close **non détecté** (UB documenté, aligné
  Android/native).
- Mode `unsafe` JVM : la garde de close est **conservée** (vérification légère
  avant l'accès) ; seules les bornes sont sautées. Fermer l'arène pendant
  qu'un buffer unsafe est encore utilisé reste un **use-after-free** : la garde
  détecte la fermeture, mais un buffer brut n'a aucune garde.

### Confinement

JVM : l'arène est `Arena.ofConfined()` — les buffers scopés et `close()` sont
confinés au thread de création (accès depuis un autre thread →
`WrongThreadException`). `memoryScope` est soumis à la même règle. En mode
`unsafe`, l'accès passe par l'adresse brute (`sun.misc.Unsafe`) : aucune
vérification de thread, seule la garde de close s'applique. Un buffer construit
depuis une adresse brute ne porte **aucun** confinement (garde nulle, UB
documenté) — c'est le chemin à privilégier pour un partage inter-threads
assumé, à vos risques.

### Aliasing

Deux buffers sur la même zone mémoire sont vus mutuellement : les écritures de
l'un sont visibles par l'autre. **Pas de verrou** : la synchronisation relève
du consommateur. Le partage inter-threads est en outre limité par le
[confinement](#confinement) des arènes JVM.

## Option `unsafe`

`unsafe = true` élimine les bornes-check, au choix :

- par allocateur — `MemoryAllocator(unsafe = true)` propage l'option à **tous**
  les buffers créés par cet allocateur ;
- par buffer — `MemoryBuffer(addr, size, unsafe = true)` opt-in local.

Défaut : `false` (bornes-check actifs). En mode `unsafe`, tout accès hors
bornes devient un **comportement indéfini** (UB) : pas d'exception, corruption
mémoire possible. C'est un choix délibéré, réservé aux chemins à chaud.

Politique de durée de vie (P2) : le mode unsafe **ne dispense pas** de la
discipline de durée de vie. Les buffers unsafe suivent la même politique que
les buffers sûrs (scope porté par le buffer, UB documenté pour les buffers
bruts) — uniquement les bornes sont sautées.

### Différence native

Sur les backends native, le mode `unsafe` est **figé à la compilation** : la
valeur est une constante build-time (`KFFI_NATIVE_UNSAFE` dans
`MemoryBuffer.native.kt`, actuellement `false`). Les distributions native ne
peuvent pas basculer au runtime ; le flag d'API est accepté pour la
compatibilité et sans effet. Basculer à la compilation : éditer la constante
puis recompiler le module.

## Callbacks

Les callbacks (upcalls, de la native vers Kotlin) sont gérés par
`CallbackRuntime` et les moteurs d'upcall par backend (`JvmUpcallEngine`,
`UpcallEngine` Android). Le code généré par kextract produit les descripteurs
(`CallbackType`), les trampolines et les dispatchers ; le consommateur
enregistre une lambda Kotlin par `CallbackRuntime.register`.

Cycle de vie :

- `CallbackRegistration.close()` — ferme la registration : plus aucune
  livraison. `isClosed` devient vrai immédiatement ; `isQuiescent` ne devient
  vrai qu'une fois **toutes les livraisons natives en vol revenues**
  (comptage d'`inFlight`).
- `CallbackPolicy.ONCE` — le callback n'est livré qu'une fois : le slot est
  dé-publié après la première livraison (claim). `REPEATING` — livré tant que
  la registration n'est pas fermée.
- `CallbackRuntime.prepare` / `activateForNativeCall` — pattern transactionnel
  pour les appels natifs ponctuels générés (préparation, activation à l'appel,
  close en cas d'échec).
- `CallbackRuntime.rearmAfterNativeQuiescence` — réarmement d'un slot sans
  userdata (`@UnsafeCallbackRearmApi`) : le consommateur doit avoir établi la
  quiescence native avant de réarmer.
- Barrière d'exception : aucune exception ne traverse la frontière native —
  `dispatchSafely` route les échecs vers le `CallbackExceptionHandler` de la
  registration (`onError`), ou vers le canal de secours si le routage échoue.

Durée de vie des trampolines : JVM — stubs alloués dans une arène globale
(durée de vie processus) ; Android — trampolines JNI avec gestion explicite
(`allocateTrampoline` / `freeTrampoline`). Dans les deux cas, la registration
(`CallbackRegistration`) contrôle le routage : la fermer retire le slot de la
table de tokens (token jamais réutilisé).

## Versionnement

Semver strict. `1.0.0` est le **premier contrat stable** : le redessin
P1-P4 (moteurs downcall/upcall, politique de durée de vie I2-a, option unsafe
I3, optimisation M1) est le dernier avant 1.0. Les API publiques sont
stabilisées à partir de cette version ; toute rupture passe par un bump
majeur.

- Les **releases** sont versionnées `x.y.z` (actuellement en préparation de
  `1.0.0`).
- Les **snapshots** sont publiés à chaque push sur `main`, en `-SNAPSHOT`
  (ex. `1.0.0-SNAPSHOT` — coordonnée finale après la migration M2.4 ; avant,
  snapshots publiés `v29.0.0-<timestamp>-SNAPSHOT`).
- La version courante du runtime est exposée : `Kffi.VERSION` (`1.0.0` — à
  partir de la version module M2.4).

## Licence

MIT — voir [LICENSE](LICENSE).
