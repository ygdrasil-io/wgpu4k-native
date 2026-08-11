# kffi — Socle générique et performant, déporté

Date : 2026-08-11
Statut : Design validé
Cible : déporter kffi hors de wgpu4k-native vers `https://github.com/Graphiks-org/kffi`, namespace `org.graphiks.kffi`.

## Contexte

kffi est la couche FFI multiplateforme de wgpu4k-native. Objectif : en faire une lib indépendante, générique (utilisable par d'autres librairies via kextract) et performante.

### État actuel (points clés)

- **Contrat `expect/actual`** dans `commonMain` : `NativeAddress`, `MemoryBuffer`, `MemoryAllocator` (+ `memoryScope`), `CString`, `Callback`/`CallbackRuntime`.
- **Backend JVM** : java.lang.foreign (FFM), downcalls via `MethodHandle.invokeExact`, arènes `Arena.ofConfined()`.
- **Backend Android** : re-implémentation du package `java/lang/foreign/` (MemorySegment, ValueLayout, GroupLayout, SegmentAllocator, NativeString) par-dessus JNA (`JnaArena` = liste de `Memory`). ~750 lignes de shim.
- **Backend Native** : kotlinx.cinterop.
- **Callback runtime** : machine à états (`DeliveryStateMachine`), routage par token encodé dans le `userdata`, buckets `AtomicReference<Map<ULong, RegistryEntry<*>>>`, `NoUserdataSlotStateMachine`.
- **Code généré** (`wgpu_hCommon.kt`, `wgpu_hJvm.kt`) : fuite d'implémentation (`descriptor?.handler?.handler ?: MemorySegment.NULL`, `Arena.ofAuto()` pour les valeurs retournées).
- **kextract** : générateur actuellement câblé à wgpu4k-native (génération à partir des headers C depuis `309c8f3c`).

## Décisions validées

1. **Critère de performance** : tout à la fois (latence downcalls, throughput upcalls/callbacks, coût de marshaling, allocation d'arènes).
2. **Plateformes** : JVM (desktop/server), Android, Kotlin/Native.
3. **Dépendances Android** : abandon de la couche Java de JNA (Pointer/Memory/Function/Callback) ; le moteur d'appel natif est réécrit.
4. **API publique** : redessinable (pas un contrat figé).
5. **Démarche** : benchmark-first.
6. **Ambition** : vision complète en phases.
7. **Fixtures** : créer des fixtures C dédiées au benchmark.
8. **Harness** : un par backend, scénarios communs partagés via un SPI.
9. **Backend Android** : couche Kotlin maison (value classes inline, adresses brutes, arène maison) + réécriture du moteur de downcall.
10. **Namespace cible** : `org.graphiks.kffi` ; groupe Maven `org.graphiks`.
11. **Migration** : développement dans wgpu4k-native, migration vers `Graphiks-org/kffi` **en fin** de chantier (P5).

## Architecture cible

```
kffi/                          ← module déporté, namespace org.graphiks.kffi
├── commonMain                 ← API publique : NativeAddress, MemoryBuffer,
│                                 MemoryAllocator, CString, Callback, CallbackRuntime,
│                                 memoryScope (contrat expect/actual minimal)
├── jvmMain                    ← backend FFM (java.lang.foreign) : downcalls, arènes, upcalls
├── androidMain                ← backend maison : couche Kotlin + moteur de downcall natif
├── nativeMain                 ← backend kotlinx.cinterop
├── benchmark-*                ← harness de benchmark par backend
└── kffi-benchmark-spi         ← interfaces de mesure partagées
```

Artifacts publiés (Maven Central, groupe `org.graphiks`) :

```
org.graphiks:kffi-common
org.graphiks:kffi-jvm
org.graphiks:kffi-android
org.graphiks:kffi-native
org.graphiks:kffi-benchmark-spi
```

## Phasage

| Phase | Contenu | Sortie mesurable |
|---|---|---|
| P0 | Benchmark harness (4 axes, scénarios communs) | Baseline chiffrée JVM/Android/native |
| P1 | Migration namespace `org.graphiks.kffi` + réécriture backend Android (couche Kotlin + moteur downcall maison) | Δ perf Android (downcall + marshaling) |
| P2 | Nettoyage JVM : suppression fuites `.handler.handler`, `NativeAddress` inline, fin d'`Arena.ofAuto()` | Δ downcall JVM, code généré propre |
| P3 | Sûreté mémoire unifiée : bornes-check uniforme, flag `-Dkffi.unsafe` | Overhead bornes-check mesuré, invariants documentés |
| P4 | Optimisation callback runtime guidée par la baseline | Δ throughput upcall |
| P5 | kextract générique + artifacts `org.graphiks:kffi-*` + publication + migration vers Graphiks-org/kffi | Artifact indépendant, consumer test |

## Détail par phase

### P0 — Benchmark harness

- **4 axes** :
  1. Downcall — fonction native « vide » (`return 42`), variantes 1/4/8 arguments.
  2. Upcall — déclenchement callback depuis le natif, variantes avec et sans token de routage.
  3. Marshaling — write/read scalaires + tableaux (16 / 1024 éléments), comparé au coût d'un `ByteArray` pur.
  4. Arène — N allocations + close dans `memoryScope`, vs `globalMemory`.
- **Infrastructure** : JMH sur JVM (average time), micro-harness maison sur Android (device), harness `nativeTest` avec `kotlin.time`.
- **Fixtures** : nouvelles fixtures C dédiées (fonction vide, N-args, returns variés), indépendantes de wgpu.
- **Scénarios communs** définis dans `kffi-benchmark-spi`, déclinés par backend.
- **Format de sortie** : tableau ns/op par axe × backend × variante, versionné dans `kffi/benchmarks/results/<date>-<commit>.md`.
- **Bake-off moteur de downcall** (Android) : wrap-once typé vs JNI pur vs libffi direct, mesuré sur device.

### P1 — Migration namespace + backend Android

**Migration namespace** (en une passe, en début de phase) :
- `io.ygdrasil.kffi` → `org.graphiks.kffi` dans tout le repo hôte + kextract.
- kextract doit cibler le nouveau namespace dès P2 (éviter double régénération).

**Backend Android — couche Kotlin maison** :
- `NativeAddress.android.kt` : `@JvmInline value class` enveloppant un `long` brut (zéro allocation).
- `MemoryAllocator.android.kt` : arène confinée (bump alloc + free-list par taille), libération en bloc au `close()` — remplace `JnaArena` et `Memory(size)`.
- `MemoryBuffer.android.kt` : write/read sur le bloc via primitives natives, bornes-check comme la JVM (prépare P3).
- `CString.android.kt` : allocation + copie UTF-8 dans l'arène.
- **Suppression** du package shim `androidMain/kotlin/java/lang/foreign/` et de tout `com.sun.jna.*`.

**Moteur de downcall maison** :
- Petit `.so` kffi (NDK, ~par ABI) exposant une **table bornée de wrappers JNI typés**, un par forme de signature :
  `callV0`, `callV1I(fn,int)`, `callI0(fn)`, `callI2II(fn,int,int)`, `callP1P(fn,ptr)`, `callL4IIPP(fn,int,int,ptr,ptr)`, … — couvre l'espace de signatures réel de wgpu (scalaires + pointeurs, arité ≤ 8).
- kextract génère par fonction : lookup du symbole (une fois au chargement) + wrapper Kotlin appelant le bon wrapper JNI avec l'adresse de fonction en premier argument.
- Coût par downcall : **une transition JNI typée**, sans buffer d'arguments ni libffi.
- Le `.so` contient aussi : trampolines de callbacks (closures, routage vers Kotlin via JNI global ref, branchés sur `CallbackRuntime` existant) et primitives d'allocation/copie mémoire.
- Chargement via `dlopen` maison.
- Compilation NDK pour `arm64-v8a` / `x86_64` / `armeabi-v7a` ; Android 16KB pages géré par le NDK moderne.

### P2 — Nettoyage JVM

- **Suppression des fuites `.handler.handler`** dans le code généré : kextract génère contre l'API kffi uniquement.
- **`NativeAddress` inline JVM** : `@JvmInline value class` enveloppant un `long` (adresse brute) au lieu d'un `MemorySegment` ; conversions FFM (`MemorySegment.ofAddress`) internes à kffi.
- **`MemoryBuffer` JVM allégé** : création de segment via `ofAddress` à la demande, bornée à la taille.
- **Fin d'`Arena.ofAuto()`** pour les valeurs retournées : arène explicite fournie par l'appelant via `memoryScope`.
- **Critère** : downcalls JVM ≥ niveau P0 (ne pas dégrader `invokeExact`), marshaling amélioré (moins d'allocations), code généré sans `MemorySegment`/`ValueLayout`/`MethodHandle`.

### P3 — Sûreté mémoire unifiée

- **Bornes-check uniforme** sur `MemoryBuffer` (scalaires + tableaux) : `offset + elementSize ≤ size`, même endroit logique sur les 3 backends.
- **Exception homogène** : `IndexOutOfBoundsException` avec offset/taille.
- **Flag `-Dkffi.unsafe`** (JVM/Android) / constante compilée (native) : actif → bornes-check éliminés ; défaut → actifs. Par process, documenté.
- **`NativeAddress` nu** reste non-borné par nature ; tout accès typé passe par `MemoryBuffer` (borné) ou le flag `unsafe`.
- **Contrat commun documenté** : bornes-check, aliasing, validité après close.
- **Validation** : overhead bornes-check mesuré (P0 vs P3) ; tests communs exercent les mêmes scénarios/erreurs sur les 3 backends.

### P4 — Optimisation callback runtime (guidée par la baseline)

Points de friction identifiés (à confirmer par la mesure) :
- Buckets `AtomicReference<Map<...>>` : immutable map copiée à chaque publication.
- `tryEnterRepeating` : hook `beforeTryEnterCompareAndSet` invoqué dans la boucle CAS.
- Allocations par dispatch : `AcquiredDelivery` + `DeliverySnapshot`.

Axes :
- Remplacement des buckets par une table d'index (token = compteur monotone → accès tableau, doublement + epoch plutôt qu'immutable map).
- Zéro-allocation par dispatch : `AcquiredDelivery` flyweight ou inline ; état packé (state + inFlight dans un seul `Long`).
- Sortie du hook de la boucle CAS (invocation unique avant la boucle quand équivalent).
- **Garde-fou** : sémantique fine (quiescence, ONCE/REPEATING, token routing, no-userdata) couverte par `CallbackStateMachineTest` / `CallbackApiTest` — doivent passer intacts.

### P5 — kextract générique + publication + migration

- **kextract générique** : produire des bindings contre n'importe quel header C en se branchant sur l'API kffi.
- **Artifacts** `org.graphiks:kffi-*` publiés sur Maven Central.
- **Contrat de compatibilité** : API versionnée (semver) ; le redessin P1-P4 est le dernier avant 1.0.
- **Migration** vers `Graphiks-org/kffi` en fin de phase ; le repo hôte consomme l'artefact publié.
- **Documentation consommateur** : README + quickstart (génération de bindings, chargement de lib native, `memoryScope`, callbacks, flag `unsafe`).
- **Consumer test** : mini-projet externe généré avec kextract, build + run sur JVM/Android/native.

## Risques

- **Shim `java/lang/foreign` Android** (passif lourd) — éliminé en P1.
- **Sûreté hétérogène entre backends** — corrigé en P3.
- **ABI interne de jnidispatch** — contourné : le moteur de downcall maison n'en dépend plus.
- **NDK dans le pipeline Gradle** (P1) : nouveau coût de build, à intégrer proprement.
- **Régression du callback runtime** (P4) : protégé par les tests d'état existants.
