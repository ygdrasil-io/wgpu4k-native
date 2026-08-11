# kffi — Socle générique et performant, déporté

Date : 2026-08-11
Statut : Design validé, corrigé suite review indépendante
Cible : déporter kffi hors de wgpu4k-native vers `https://github.com/Graphiks-org/kffi`, namespace `org.graphiks.kffi`.

> **Révision (2026-08-11, review indépendante)** : deux hypothèses de faisabilité du moteur
> de downcall Android étaient fausses contre les bindings wgpu réels (structs par valeur) et la
> généricité P5 n'avait pas de chemin de repli. Corrections intégrées : C1 (wrappers par struct),
> C2 (politique de fallback), I1 (upcall Android en milestone dédié), I2 (durée de vie JVM
> explicite), I3 (opt-in par allocateur), I4 (comparaisons intra-backend), I5 (repo/CI tôt),
> I6 (ABI 32-bit + packaging). Voir sections concernées.

## Contexte

kffi est la couche FFI multiplateforme de wgpu4k-native. Objectif : en faire une lib indépendante, générique (utilisable par d'autres librairies via kextract) et performante.

### État actuel (points clés)

- **Contrat `expect/actual`** dans `commonMain` : `NativeAddress`, `MemoryBuffer`, `MemoryAllocator` (+ `memoryScope`), `CString`, `Callback`/`CallbackRuntime`.
- **Backend JVM** : java.lang.foreign (FFM), downcalls via `MethodHandle.invokeExact`, arènes `Arena.ofConfined()`.
- **Backend Android** : re-implémentation du package `java/lang/foreign/` (MemorySegment, ValueLayout, GroupLayout, SegmentAllocator, NativeString — 317 lignes) par-dessus JNA (`JnaArena` = liste de `Memory`).
- **Backend Native** : kotlinx.cinterop.
- **Callback runtime** : machine à états (`DeliveryStateMachine`), routage par token encodé dans le `userdata`, buckets `AtomicReference<Map<ULong, RegistryEntry<*>>>`, `NoUserdataSlotStateMachine`.
- **Code généré** (`wgpu_hCommon.kt`, `wgpu_hJvm.kt`) : fuite d'implémentation (`descriptor?.handler?.handler ?: MemorySegment.NULL`, `Arena.ofAuto()` pour les valeurs retournées). **Structs par valeur omniprésents** : `WGPUStringView.layout` passé par valeur à ~29 sites JVM, `WGPUFuture` retourné par valeur à 9 sites, `Structure.ByValue` massif côté Android (`wgpu_hAndroid.kt`, 569KB).
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
| P0 | Benchmark harness (4 axes, scénarios communs) + **repo Graphiks-org/kffi + CI + publication de snapshots** | Baseline chiffrée JVM/Android/native, CI/publishing opérationnels |
| P1 | Migration namespace `org.graphiks.kffi` + réécriture backend Android (couche Kotlin + moteur downcall maison + **upcalls en milestone dédié**) | Δ perf Android (downcall + marshaling), upcalls fonctionnels |
| P2 | Nettoyage JVM : suppression fuites `.handler.handler`, `NativeAddress` inline, fin d'`Arena.ofAuto()` | Δ downcall JVM, code généré propre |
| P3 | Sûreté mémoire unifiée : bornes-check uniforme, opt-in `unsafe` par allocateur, **décision explicite durée de vie** | Overhead bornes-check mesuré, invariants documentés, **re-baseline** |
| P4 | Optimisation callback runtime guidée par la baseline | Δ throughput upcall |
| P5 | kextract générique + artifacts `org.graphiks:kffi-*` publiés + migration finale vers Graphiks-org/kffi | Artifact indépendant, consumer test |

## Détail par phase

### P0 — Benchmark harness

- **4 axes** :
  1. Downcall — fonction native « vide » (`return 42`), variantes 1/4/8 arguments, **et variantes avec struct par valeur en argument / en retour**.
  2. Upcall — déclenchement callback depuis le natif, variantes avec et sans token de routage.
  3. Marshaling — write/read scalaires + tableaux (16 / 1024 éléments), comparé au coût d'un `ByteArray` pur.
  4. Arène — N allocations + close dans `memoryScope`, vs `globalMemory`.
- **Infrastructure** : JMH sur JVM (average time, warmup + itérations + blackhole configurés), micro-harness maison sur Android (device), harness `nativeTest` avec `kotlin.time`.
- **Fixtures** : nouvelles fixtures C dédiées (fonction vide, N-args, returns variés, **struct par valeur arg/return**), indépendantes de wgpu.
- **Scénarios communs** définis dans `kffi-benchmark-spi`, déclinés par backend.
- **Règle de comparaison** : les Δ ne sont signifiants qu'**intra-backend** (JMH forké/warmé sur JVM vs device soumis au throttling vs AOT native ne sont pas commensurables). Le rapport de sortie sépare les backends et interdit les comparaisons croisées en valeur absolue.
- **Format de sortie** : tableau ns/op par axe × backend × variante, versionné dans `kffi/benchmarks/results/<date>-<commit>.md`.
- **Bake-off moteur de downcall** (Android) : wrap-once typé vs JNI pur vs libffi direct, mesuré sur device, **incluant des cas struct par valeur**.

### P0 bis — Repo cible, CI et publication (avancés dès P0)

Créer `Graphiks-org/kffi` **tôt** (privé si besoin) plutôt qu'en fin de chantier :
- Stand-up de CI (build KMP, tests JVM/Android/native) et du pipeline de publication de **snapshots** Maven sur ce repo.
- wgpu4k-native consomme les snapshots publiés ; le développement se fait par splits de sous-arbre par phase.
- Réduit le risque de la migration finale (histoire git propre via `git filter-repo`/subtree, cadence de release découplée).
- La « migration finale » de P5 reste le moment où wgpu4k-native bascule sur la **version release** ; la décision utilisateur « migrer à la fin » est conservée, mais l'infrastructure est en place dès P0.

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
- Petit `.so` kffi (NDK, ~par ABI) exposant une **table de wrappers JNI typés**, un par forme de signature :
  - wrappers scalaires/pointeurs : `callV0`, `callV1I(fn,int)`, `callI0(fn)`, `callI2II(fn,int,int)`, `callP1P(fn,ptr)`, `callL4IIPP(fn,int,int,ptr,ptr)`, … ;
  - **wrappers par struct par valeur** (arg et return) : générés par kextract depuis les définitions de structs des headers. Le wrapper assemble les champs dans les registres/stack ABI natifs et capture les retours par valeur — nécessite un **classifieur d'arguments** (alignement, padding, règles HFA sur AArch64). C'est la correction C1 : les structs par valeur sont omniprésents dans wgpu (`WGPUStringView` par valeur à ~29 sites JVM, `WGPUFuture` retourné par valeur à 9 sites, `Structure.ByValue` côté Android).
- La table est **générée à partir de l'union des formes réellement référencées par les headers cibles** (≈40 formes scalaires pour les 227 downcalls wgpu) — pas une table combinatoire complète (~2,9M wrappers, infaisable).
- **Politique de fallback (C2)** : tout ce qui sort de la table (varargs, `long long`, arité > 8, structs exotiques) bascule sur un **chemin générique de secours** par appel (libffi) — explicite et documenté, refusé à la génération kextract si non supporté. Sans cela, la table bornée et la généricité P5 sont en tension.
- kextract génère par fonction : lookup du symbole (une fois au chargement) + wrapper Kotlin appelant le bon wrapper JNI avec l'adresse de fonction en premier argument.
- Coût par downcall dans la table : **une transition JNI typée**, sans buffer d'arguments ni libffi. Les cas fallback paient libffi.
- Chargement via `dlopen` maison.
- Compilation NDK pour `arm64-v8a` / `x86_64` / `armeabi-v7a` ; Android 16KB pages géré par le NDK moderne.
- **ABI 32-bit (I6)** : les types JNI des wrappers sont **paramétrés par ABI** — sur armeabi-v7a, `C long`/`size_t` font 4 octets (jint) et non 8 (jlong). Le `C_LONG = JAVA_LONG` actuel du shim est un bug latent 32-bit à corriger.

**Upcalls Android (I1) — milestone dédié dans P1** :
Les upcalls sont un sous-système complet (l'équivalent des closures libffi embarquées par JNA), pas une phrase :
- **Acquisition de `JNIEnv`** : `GetEnv` sur le thread appelant, sinon `AttachCurrentThread` + `DetachCurrentThread` — les threads callback de wgpu sont arbitraires et cross-thread (démontré par le watchdog `CallbackFixtureWatchdogJvmTest`).
- **Refs globales** : tenure d'un ref global sur la méthode trampoline, cycle de vie géré (free-list de trampolines, politique de fuite remplaçant ce que gérait `CallbackReference` de JNA).
- **Marshaling du retour** : les callbacks renvoient void aujourd'hui, mais les callbacks génériques auront des retours — à concevoir.
- **Marshaling des exceptions** Kotlin → côté natif.
- Branchés sur `CallbackRuntime` existant (routage par token déjà en place).

**Packaging (I6)** : intégration NDK dans le pipeline Gradle (task dédiée), layout jniLibs dans l'AAR, `abiFilters`, `JNI_OnLoad`/`RegisterNatives`, stripping, règles R8 après retrait de JNA, et compatibilité avec les cibles `androidNativeArm64/X64` existantes (`kffi/build.gradle.kts:149`). **Matrice de test P1 : un device/émulateur armeabi-v7a requis.**

### P2 — Nettoyage JVM

- **Suppression des fuites `.handler.handler`** dans le code généré : kextract génère contre l'API kffi uniquement.
- **`NativeAddress` inline JVM** : `@JvmInline value class` enveloppant un `long` (adresse brute) au lieu d'un `MemorySegment` ; conversions FFM (`MemorySegment.ofAddress`) internes à kffi.
- **`MemoryBuffer` JVM allégé** : création de segment via `ofAddress` à la demande, bornée à la taille.
- **Fin d'`Arena.ofAuto()`** pour les valeurs retournées : arène explicite fournie par l'appelant via `memoryScope`.
- **Décision explicite durée de vie (I2)** : aujourd'hui le JVM `MemoryBuffer` porte le scope de l'arène via `reinterpret(size)` (`MemoryBuffer.jvm.kt:10`) → use-after-close lève `IllegalStateException`. En passant `NativeAddress` à un `long` brut, ce scope disparaît. Deux options à trancher en P2 :
  - (a) conserver un **handle d'arène/session dans `MemoryBuffer`** (pas dans `NativeAddress`) pour que la détection de close survive — la JVM garde sa garantie actuelle ;
  - (b) déclarer explicitement que l'accès post-close est **UB sur tous les backends** et abandonner la prétention de parité.
  Défaut recommandé : (a), sauf si la baseline montre que le handle coûte sur le hot path.
- **Critère** : downcalls JVM ≥ niveau P0 (ne pas dégrader `invokeExact`), marshaling amélioré (moins d'allocations), code généré sans `MemorySegment`/`ValueLayout`/`MethodHandle`.

### P3 — Sûreté mémoire unifiée

- **Bornes-check uniforme** sur `MemoryBuffer` (scalaires + tableaux) : `offset + elementSize ≤ size`, même endroit logique sur les 3 backends. Aujourd'hui les scalaires native ne sont **pas bornés** (`MemoryBuffer.native.kt` fait du `pointed.value` brut ; seuls les tableaux ont un `boundCheck`) — la baseline P0 native doit être re-mesurée après P3.
- **Exception homogène** : `IndexOutOfBoundsException` avec offset/taille.
- **Opt-in `unsafe` par allocateur (I3)** : plutôt qu'un flag global `-Dkffi.unsafe` (un consommateur l'activant désactiverait la sûreté pour tout le process), l'option est **par allocateur ou par buffer** — aligné sur le modèle per-segment de FFM. Sémantique : actif → bornes-check éliminés ; défaut → actifs.
- **Mécanisme par backend (I3)** :
  - JVM : désactiver le bornes-check FFM implique un **second chemin d'accès via `sun.misc.Unsafe`** — le spec doit choisir explicitement (FFM borné par défaut, Unsafe seulement quand `unsafe` est demandé). C'est un doublement d'implémentation à assumer.
  - Android : même logique dans la couche Kotlin maison.
  - Native : aujourd'hui un build-time ; la « constante compilée » ne peut pas se basculer au runtime — le contrat documente cette différence (distributions native figées à la compilation).
- **`NativeAddress` nu** reste non-borné par nature ; tout accès typé passe par `MemoryBuffer` (borné) ou l'option `unsafe`.
- **Contrat commun documenté** : bornes-check, aliasing, **validité après close (selon décision P2/I2)**, et le fait que l'accès post-close est UB quand la décision (b) est retenue.
- **Validation** : overhead bornes-check mesuré (P0 vs P3, puis **re-baseline** puisque le défaut P3 change le coût native) ; tests communs exercent les mêmes scénarios/erreurs sur les 3 backends.

### P4 — Optimisation callback runtime (guidée par la baseline)

Points de friction identifiés (à confirmer par la mesure) :
- Buckets `AtomicReference<Map<...>>` : immutable map copiée à chaque publication.
- Allocations par dispatch : `DeliverySnapshot` alloué **à chaque tentative CAS** (`CallbackRuntime.kt:61-68,80-82,88-101`) + `AcquiredDelivery` par dispatch (:227).
- Le hook `beforeTryEnterCompareAndSet` est un **point d'injection réservé aux tests** (utilisé uniquement dans `CallbackRuntimeJvmTest:168`) — pas une friction de production ; l'optimisation réelle porte sur les allocations et la structure de données.

Axes :
- Remplacement des buckets par une table d'index (token = compteur monotone → accès tableau, doublement + epoch plutôt qu'immutable map).
- Zéro-allocation par dispatch : `AcquiredDelivery` flyweight ou inline ; état packé (state + inFlight dans un seul `AtomicLong`) — supprime les `DeliverySnapshot` alloués par tentative CAS.
- **Garde-fou** : sémantique fine (quiescence, ONCE/REPEATING, token routing, no-userdata) couverte par `CallbackStateMachineTest` / `CallbackApiTest` — doivent passer intacts.

### P5 — kextract générique + publication + migration

- **kextract générique** : produire des bindings contre n'importe quel header C en se branchant sur l'API kffi. La généricité repose sur la **politique de fallback (C2)** définie en P1 : table générée depuis les headers cibles + chemin libffi de secours pour tout ce qui en sort, sinon refus à la génération.
- **Artifacts** `org.graphiks:kffi-*` publiés sur Maven Central.
- **Contrat de compatibilité** : API versionnée (semver) ; le redessin P1-P4 est le dernier avant 1.0.
- **Migration** vers `Graphiks-org/kffi` en fin de phase (l'infrastructure repo/CI/publishing est déjà en place depuis P0 bis) ; le repo hôte bascule sur la version **release** publiée.
- **Documentation consommateur** : README + quickstart (génération de bindings, chargement de lib native, `memoryScope`, callbacks, option `unsafe` **avec la politique de durée de vie de P2 et le cycle de vie des trampolines de callbacks de P1**, pas juste une ligne).
- **Consumer test** : mini-projet externe généré avec kextract, build + run sur JVM/Android/native.

## Risques

- **Shim `java/lang/foreign` Android** (317 lignes de re-implémentation FFM) — éliminé en P1.
- **Structs par valeur** : sans la famille de wrappers par struct (C1), le moteur Android ne peut pas binder wgpu lui-même — corrigé dans le design P1.
- **Tension table bornée vs généricité** : sans politique de fallback explicite (C2), P5 promet plus que P1 ne livre — corrigé dans le design P1.
- **Upcalls Android** : sous-système complet (threads, refs globales, retours) — traité en milestone dédié (I1).
- **Sûreté hétérogène entre backends** — corrigé en P3 ; mécanismes `unsafe` différents par backend (I3) documentés.
- **Durée de vie JVM** : risque de régression silencieuse en P2 (scope perdu) — décision explicite (a)/(b) requise (I2).
- **ABI interne de jnidispatch** — contourné : le moteur de downcall maison n'en dépend plus.
- **NDK dans le pipeline Gradle** (P1) : nouveau coût de build, à intégrer proprement (packaging I6).
- **ABI 32-bit** (armeabi-v7a) : `long` 4 octets, bug latent `C_LONG` existant — wrappers paramétrés par ABI (I6).
- **Régression du callback runtime** (P4) : protégé par les tests d'état existants.
- **Comparaisons cross-backend** : les benchmarks ne sont comparables qu'intra-backend (I4) — règle appliquée dès P0.
