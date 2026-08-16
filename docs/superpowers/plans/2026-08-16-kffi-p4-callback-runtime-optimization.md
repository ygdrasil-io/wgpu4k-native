# kffi P4 — Optimisation callback runtime Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Réduire le coût par dispatch des upcalls kffi (baseline P3 : `upcall.fire_one` 76.84 ns, `upcall.fire_1000` 72.7 µs) en éliminant les allocations par dispatch (DeliverySnapshot par tentative CAS, AcquiredDelivery par dispatch) et en remplaçant les buckets `AtomicReference<Map>` par une table d'index token→entrée (accès tableau, doublement + epoch), sans casser la sémantique fine (quiescence, ONCE/REPEATING, routage par token, no-userdata).

**Architecture:** Le `DeliveryStateMachine` passe d'un `AtomicReference<DeliverySnapshot>` (objet alloué par transition) à un **état packé** `AtomicLong` (state + inFlight dans un seul long, transitions via CAS sans allocation). Les 64 buckets `AtomicReference<Map<ULong, RegistryEntry>>` (immutable map copiée à chaque publication/retrait) deviennent une **table d'index dynamique** : token = compteur monotone → slot tableau, doublement + `AtomicReferenceArray`/epoch pour la croissance. `AcquiredDelivery` (allocation par dispatch) devient un flyweight ou est inliné dans le flux `dispatchSafely`. Le hook de test `beforeTryEnterCompareAndSet` est conservé (injection test uniquement). Garde-fou : `CallbackStateMachineTest` + `CallbackApiTest` doivent passer intacts après chaque milestone.

**Tech Stack:** Kotlin Multiplatform (commonMain), `kotlin.concurrent.atomics` (AtomicLong/AtomicReference existants), JMH (re-baseline upcall).

---

## Contexte et décisions P4

### État P3 (constaté, branche `feat/kffi-socle-generique`)

- `CallbackRuntime.kt` (500 lignes, commonMain) :
  - `DeliveryStateMachine` : `snapshotRef = AtomicReference(DeliverySnapshot(state, inFlight))` — **chaque transition alloue un `DeliverySnapshot`** : `tryEnterOnce` en alloue 2 (claiming + claimed), `tryEnterRepeating` 1, `leave` 1, `transitionState` 1, `close`/`activate`/`abort` 1 chacun. Hot path = 1-2 allocations par dispatch.
  - `AcquiredDelivery(entry)` : **1 allocation par dispatch** dans `acquire()`.
  - `buckets = List(64) { AtomicReference<Map<ULong, RegistryEntry>>(emptyMap()) }` : `insertToken` fait `current + (token to entry)` (copie entière de la map du bucket), `removeToken` fait `current - token` (copie). Publication/retrait = O(bucket size) allocation. `route()` lit `bucket(token).load()[token]` (2 accès atomic).
  - `NoUserdataSlotStateMachine` : `AtomicReference<NoUserdataSnapshot>` — 1 allocation par activation/retrait (hors hot path dispatch).
  - `activeRegistrationCountForTest()` somme les tailles des 64 maps.
- Baseline upcall P3 (`2026-08-16-3ca7130e-jvm-baseline.md`) : `upcall.fire_one` **76.84 ns**, `upcall.fire_one_no_routing` **76.70 ns**, `upcall.fire_1000` **72719 ns** (~72.7 ns/call), `upcall.fire_1000_no_routing` **71084 ns**.
- Tests de garde-fou : `CallbackStateMachineTest` (commonTest) couvre la machine d'états (quiescence, ONCE/REPEATING, inFlight) ; `CallbackApiTest` (commonTest) couvre l'API de registre ; `CallbackRuntimeJvmTest` (jvmTest) utilise `beforeTryEnterCompareAndSet` (injection test, ligne ~168) ; `CallbackFfiJvmTest` (jvmTest) fait le tour FFI complet (13 tests) ; `CallbackRuntimeNativeTest` + `CallbackTokenAddressCodecNativeTest` (nativeTest).

### Décisions P4 (design doc §P4, lignes 163-173)

| Décision | Choix |
|---|---|
| Structure des buckets | **Table d'index** : token = compteur monotone → slot tableau ; doublement + `AtomicReferenceArray` (ou growable) ; remplace l'immutable map copiée à chaque publication |
| État de la machine | **Packé** : `state + inFlight` dans un seul `AtomicLong` — transitions CAS sans allocation (supprime les DeliverySnapshot) |
| AcquiredDelivery | **Flyweight/inline** : plus d'allocation par dispatch (le `entry` est déjà récupéré ; `complete()` appelle `entry.lifecycle.leave()` directement) |
| Garde-fou | `CallbackStateMachineTest` + `CallbackApiTest` + suites JVM/native existantes **intacts** après chaque milestone |
| Hook de test | `beforeTryEnterCompareAndSet` conservé (réservé aux tests, pas une friction de production) |

### Critère de sortie P4

1. **Zéro allocation par dispatch** dans le hot path `dispatchSafely` (vérifié par inspection + test d'allocation si faisable : allocator compteur dans un test instrumenté, ou inspection du bytecode).
2. **Sémantique intacte** : `CallbackStateMachineTest` + `CallbackApiTest` (commonTest) + `CallbackRuntimeJvmTest` (dont le hook `beforeTryEnterCompareAndSet`) + `CallbackFfiJvmTest` (13 tests FFI) + `CallbackRuntimeNativeTest` + `CallbackTokenAddressCodecNativeTest` — tous verts sur les 3 backends.
3. **Publication/retrait non-copiant** : `insertToken`/`removeToken` en O(1) amorti sans copie de map (table d'index).
4. **Re-baseline upcall** : rapport versionné `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` avec Δ vs P3 (fire_one, fire_1000) — objectif : fire_one significativement sous 76.84 ns, fire_1000/call sous 72.7 ns, sans régression des autres axes.
5. Toutes les suites restent vertes (jvmTest, testDebugUnitTest, macosArm64Test).

### Files map

**kffi — commonMain :**
- `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt` — état packé, table d'index, flyweight dispatch
- (nouveau fichier si utile) `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRegistry.kt` — la table d'index extraite (ou dans le même fichier si cohérent)

**kffi — tests :**
- `kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackStateMachineTest.kt` — intact (garde-fou), éventuellement étendu avec un test d'état packé
- `kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackApiTest.kt` — intact (garde-fou)
- `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackRuntimeJvmTest.kt` — intact (hook)
- `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackFfiJvmTest.kt` — intact (13 tests FFI)

**kffi — benchmarks :**
- `kffi-benchmark-jvm/src/jmh/.../UpcallBenchmarks.kt` — inchangé (les axes existent)
- `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` — rapport P4

---

## Milestone M1 — État packé (DeliveryStateMachine sans allocation)

### Task M1.1: État packé — `AtomicLong` state+inFlight

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt`
- Test: `kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackStateMachineTest.kt` (garde-fou — doit rester vert, éventuellement ajouter un cas packé)

- [ ] **Step 1: Comprendre l'encodage actuel**

`DeliveryState` (enum) : PREPARED, ACTIVE, CLAIMING, CLAIMED, CLOSED, ABORTED — 6 valeurs. `inFlight` : Int borné par la profondeur de récursion des callbacks (petit). Encodage packé : `state` sur 4 bits (16 valeurs possibles), `inFlight` sur 28 bits restants (ou état haut/bas selon la convention).

```kotlin
// Encodage : bits 0-3 = state, bits 4-31 = inFlight
private const val STATE_BITS = 4
private const val IN_FLIGHT_SHIFT = STATE_BITS
private const val STATE_MASK = (1 shl STATE_BITS) - 1
```

- [ ] **Step 2: Vérifier que les tests de garde-fou passent avant (baseline)**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CallbackStateMachineTest" --tests "org.graphiks.kffi.CallbackApiTest"`
Expected: verts (baseline avant modification).

- [ ] **Step 3: Réécrire DeliveryStateMachine avec l'état packé**

```kotlin
internal class DeliveryStateMachine(
    private val policy: CallbackPolicy,
    initialState: DeliveryState = DeliveryState.ACTIVE,
    private val beforeTryEnterCompareAndSet: (() -> Unit)? = null,
) {
    private val packed = AtomicLong(pack(initialState, 0))

    val state: DeliveryState
        get() = unpackState(packed.load())

    val inFlight: Int
        get() = unpackInFlight(packed.load())

    val isClosed: Boolean
        get() = unpackState(packed.load()) !in setOf(DeliveryState.PREPARED, DeliveryState.ACTIVE)

    val isQuiescent: Boolean
        get() = packed.load().let { raw ->
            val s = unpackState(raw)
            s !in setOf(DeliveryState.PREPARED, DeliveryState.ACTIVE) && unpackInFlight(raw) == 0
        }

    fun activate(): Boolean = transitionState(DeliveryState.PREPARED, DeliveryState.ACTIVE)
    fun abort(): Boolean = transitionState(DeliveryState.PREPARED, DeliveryState.ABORTED)
    fun close(): Boolean = transitionState(DeliveryState.ACTIVE, DeliveryState.CLOSED)

    fun tryEnter(): Boolean = when (policy) {
        CallbackPolicy.ONCE -> tryEnterOnce()
        CallbackPolicy.REPEATING -> tryEnterRepeating()
    }

    private fun tryEnterOnce(): Boolean {
        val current = packed.load()
        if (unpackState(current) != DeliveryState.ACTIVE) return false
        beforeTryEnterCompareAndSet?.invoke()
        val claiming = pack(DeliveryState.CLAIMING, 1)
        if (!packed.compareAndSet(current, claiming)) return false
        check(
            packed.compareAndSet(
                claiming,
                pack(DeliveryState.CLAIMED, 1),
            ),
        ) { "ONCE callback claim was unexpectedly modified" }
        return true
    }

    private fun tryEnterRepeating(): Boolean {
        while (true) {
            val current = packed.load()
            if (unpackState(current) != DeliveryState.ACTIVE) return false
            beforeTryEnterCompareAndSet?.invoke()
            if (
                packed.compareAndSet(
                    current,
                    pack(DeliveryState.ACTIVE, unpackInFlight(current) + 1),
                )
            ) {
                return true
            }
        }
    }

    fun leave() {
        while (true) {
            val current = packed.load()
            check(unpackInFlight(current) > 0) { "Callback delivery left without entering" }
            if (
                packed.compareAndSet(
                    current,
                    pack(unpackState(current), unpackInFlight(current) - 1),
                )
            ) {
                return
            }
        }
    }

    private fun transitionState(expected: DeliveryState, updated: DeliveryState): Boolean {
        while (true) {
            val current = packed.load()
            if (unpackState(current) != expected) return false
            if (
                packed.compareAndSet(
                    current,
                    pack(updated, unpackInFlight(current)),
                )
            ) {
                return true
            }
        }
    }

    private companion object {
        private const val STATE_BITS = 4
        private const val IN_FLIGHT_SHIFT = STATE_BITS
        private const val STATE_MASK = (1L shl STATE_BITS) - 1

        private fun pack(state: DeliveryState, inFlight: Int): Long =
            (state.ordinal.toLong() and STATE_MASK) or (inFlight.toLong() shl IN_FLIGHT_SHIFT)

        private fun unpackState(packed: Long): DeliveryState =
            DeliveryState.entries[(packed and STATE_MASK).toInt()]

        private fun unpackInFlight(packed: Long): Int =
            (packed shr IN_FLIGHT_SHIFT).toInt()
    }
}
```

Supprimer `DeliverySnapshot` (plus d'objet alloué) et le `NoUserdataSnapshot` reste inchangé (hors hot path dispatch, alloué à l'activation/retrait seulement — acceptable).

- [ ] **Step 4: Vérifier les tests de garde-fou**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CallbackStateMachineTest" --tests "org.graphiks.kffi.CallbackApiTest" --tests "org.graphiks.kffi.CallbackRuntimeJvmTest" --tests "org.graphiks.kffi.CallbackFfiJvmTest"`
Expected: tous verts (la sémantique doit être IDENTIQUE — le hook beforeTryEnterCompareAndSet doit toujours fonctionner).

- [ ] **Step 5: Vérifier les 3 backends**

Run: `./gradlew :kffi:jvmTest :kffi:testDebugUnitTest :kffi:macosArm64Test`
Expected: verts (counts : jvm ~137, android ~49, native ~81).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt
git commit -m "perf(kffi): pack delivery state into a single AtomicLong (no per-transition allocation)"
```

### Task M1.2: Test de non-allocation (optionnel — inspection + cas packé)

**Files:**
- Modify: `kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackStateMachineTest.kt` (ajouter un cas packé si pertinent)

- [ ] **Step 1: Vérifier par inspection qu'aucune allocation n'a lieu dans tryEnter/leave**

La machine packée ne contient plus `DeliverySnapshot` — les seules allocations restantes dans le hot path dispatch sont : `AcquiredDelivery` (M2), et rien d'autre dans tryEnter/leave/transitionState (CAS sur long primitif, pas de boxing en KMP common).

- [ ] **Step 2: (Optionnel) Test de comportement packé**

Si le fichier test a une structure qui s'y prête, ajouter un cas vérifiant l'encodage (state + inFlight simultanés) — par exemple après `tryEnter` REPEATING 3 fois, `inFlight == 3` et `state == ACTIVE` ; après `leave`, `inFlight == 2`. Vérifier que les tests existants couvrent déjà ces valeurs (si oui, ne pas dupliquer).

- [ ] **Step 3: Commit (si des tests ont été ajoutés)**

```bash
git add kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackStateMachineTest.kt
git commit -m "test(kffi): pin packed delivery state semantics"
```

---

## Milestone M2 — Table d'index token→entrée (fini les maps copiées)

### Task M2.1: Table d'index dynamique

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt`
- Test: `kffi/src/commonTest/kotlin/org/graphiks/kffi/CallbackApiTest.kt` (garde-fou — doit rester vert)

- [ ] **Step 1: Comprendre le contrat actuel des buckets**

- `insertToken(token, entry)` : `bucket(token).compareAndSet(current, current + (token to entry))` — copie la map.
- `removeToken(token, entry)` : `current - token` — copie la map.
- `route()` : `bucket(token).load()[token]` — 2 lectures atomic.
- `activeRegistrationCountForTest()` : somme des tailles des maps.
- Contrainte : token = compteur monotone (allocateToken) — jamais réutilisé (check "Callback token was unexpectedly reused").

- [ ] **Step 2: Vérifier que la garde-fou passe avant**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CallbackApiTest"`
Expected: vert (baseline).

- [ ] **Step 3: Implémenter la table d'index**

```kotlin
/**
 * Table d'index token → RegistryEntry. Le token est un compteur monotone
 * (jamais réutilisé) : l'index de slot est token - 1. Croissance par
 * doublement ; les slots libres après close restent null (le token n'est
 * jamais réutilisé, un slot null signifie "plus actif").
 *
 * Concurrence : un slot est écrit UNE fois (publish) et relu en CAS-free
 * (volatile) — l'AtomicArray garantit la visibilité. Le retrait
 * passe le slot à null (pas de copie de map).
 */
private class TokenIndexTable {
    private val INITIAL_CAPACITY = 64

    @Volatile
    private var slots: AtomicArray<RegistryEntry<*>?> = AtomicArray(INITIAL_CAPACITY, null)

    private val size = AtomicLong(0L)

    fun insert(token: ULong, entry: RegistryEntry<*>): Boolean {
        val index = tokenIndex(token)
        ensureCapacity(index + 1)
        if (!slots.compareAndSetAt(index, null, entry)) return false
        size.fetchAndAdd(1)
        return true
    }

    fun remove(token: ULong, entry: RegistryEntry<*>): Boolean {
        val index = tokenIndex(token)
        if (index >= slots.size) return false
        if (!slots.compareAndSetAt(index, entry, null)) return false
        size.fetchAndAdd(-1)
        return true
    }

    operator fun get(token: ULong): RegistryEntry<*>? {
        val index = tokenIndex(token)
        if (index >= slots.size) return null
        return slots.loadAt(index)
    }

    val size: Long
        get() = size.load()

    private fun tokenIndex(token: ULong): Int = (token - 1uL).toInt()  // token ≥ 1 (monotone)

    private fun ensureCapacity(required: Int) {
        if (required <= slots.size) return
        synchronized(this) {
            if (required <= slots.size) return
            var newCapacity = slots.size
            while (newCapacity < required) newCapacity *= 2
            val grown = AtomicArray<RegistryEntry<*>?>(newCapacity, null)
            for (i in 0 until slots.size) grown[i] = slots[i]
            slots = grown
        }
    }
}
```

Remarques importantes :
- **API KMP 2.3.20 (vérifiée dans kotlin-stdlib-common sources)** : la classe est `AtomicArray<T>` (pas `AtomicReferenceArray`) avec `loadAt(index)`, `storeAt(index, value)`, `compareAndSetAt(index, expected, new)`, `exchangeAt(index, new)`, propriété `size` — l'API est annotée `@ExperimentalAtomicApi` (le module l'active déjà via `@OptIn(ExperimentalAtomicApi::class)` sur CallbackRuntime.kt:3).
- Le token est ≥ 1 (allocateToken pré-incrémente) → index = token - 1, 0-based.
- `ensureCapacity` sous `synchronized` (croissance rare) ; les lectures/écritures de slots sont CAS/volatile-safe via AtomicArray.
- Attention aux tests qui créent beaucoup de registrations (CallbackFfiJvmTest fait 1000 registrations) — la table doit croître jusqu'à ~1000+ slots sans problème.
- En cas d'API indisponible sur une cible (vérifier à la compilation), repli : `Array<RegistryEntry<*>?>` + `@Volatile` + `synchronized` sur la croissance et CAS manuel — mais `AtomicArray` est la voie propre, à privilégier.

- [ ] **Step 4: Remplacer les buckets dans CallbackRuntime**

```kotlin
object CallbackRuntime {
    private val lastAllocatedToken = AtomicLong(0L)
    private val tokenIndexTable = TokenIndexTable()
    private val activeNoUserdataRegistrations = AtomicInt(0)
    // ... BUCKET_COUNT et buckets supprimés

    // insertToken → tokenIndexTable.insert(token, entry) ; si false → check reuse
    // removeToken → tokenIndexTable.remove(token, entry)
    // route() → tokenIndexTable[token]
    // activeRegistrationCountForTest() → tokenIndexTable.size + activeNoUserdataRegistrations
    // bucket(token) → supprimé
}
```

- [ ] **Step 5: Vérifier les tests de garde-fou + FFI + 3 backends**

Run: `./gradlew :kffi:jvmTest :kffi:testDebugUnitTest :kffi:macosArm64Test`
Expected: verts (y compris CallbackFfiJvmTest avec ses 1000 registrations et le shuffle).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt
git commit -m "perf(kffi): token index table replaces per-bucket immutable maps"
```

### Task M2.2: Test de la table (croissance + retrait)

**Files:**
- Test: `kffi/src/commonTest/kotlin/org/graphiks/kffi/TokenIndexTableTest.kt` (créer, si la classe est internal et accessible depuis commonTest — sinon tester via l'API CallbackRuntime)

- [ ] **Step 1: Écrire le test de la table (via l'API publique si la classe est internal dans le même module)**

Via l'API : `CallbackApiTest` couvre déjà register/close/route. Ajouter un test dédié de croissance si accessible :

```kotlin
class TokenIndexTableTest : FreeSpec({
    "table grows beyond initial capacity" {
        // via CallbackRuntime : enregistrer 200 callbacks routés, tous routables,
        // fermer la moitié, vérifier count
    }
})
```

Si `TokenIndexTable` n'est pas accessible depuis commonTest (internal oui, commonTest est le même module — accessible), tester directement la classe.

- [ ] **Step 2: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.TokenIndexTableTest" --tests "org.graphiks.kffi.CallbackApiTest"`
Expected: verts.

- [ ] **Step 3: Commit**

```bash
git add kffi/src/commonTest/kotlin/org/graphiks/kffi/TokenIndexTableTest.kt
git commit -m "test(kffi): token index table growth and removal"
```

---

## Milestone M3 — Dispatch zéro-allocation

### Task M3.1: Flyweight dispatch — plus d'AcquiredDelivery alloué

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt`
- Test: garde-fous existants (CallbackFfiJvmTest 13 tests, CallbackApiTest)

- [ ] **Step 1: Comprendre le flux actuel**

`dispatchSafely` → `acquire(type, userdata)` → `AcquiredDelivery(entry)` (allocation) → `invoke` → `delivery.complete()` → `entry.lifecycle.leave()`. L'objet `AcquiredDelivery` ne porte QUE l'entry.

- [ ] **Step 2: Remplacer par un flux inline**

```kotlin
fun <C : Callback> dispatchSafely(
    type: CallbackType<C>,
    userdata: NativeAddress?,
    invoke: (C) -> Unit,
) {
    val entry = route(type, userdata) ?: return
    var entered = false
    try {
        if (!entry.lifecycle.tryEnter()) return
        entered = true
        if (entry.policy == CallbackPolicy.ONCE) unpublish(entry)
        try {
            invoke(entry.callback)
        } catch (failure: Throwable) {
            reportDeliveryFailure(entry.onError, failure)
        } finally {
            try {
                entry.lifecycle.leave()
            } catch (failure: Throwable) {
                reportDeliveryFailure(entry.onError, failure)
            }
        }
    } catch (failure: Throwable) {
        reportUnroutedFailure(failure)
    }
}
```

Remarques :
- `AcquiredDelivery` est supprimé (plus d'allocation).
- La sémantique d'exception doit être IDENTIQUE : `dispatchSafely` attrape les Throwable du routage ET du dispatch dans `reportUnroutedFailure` ; les exceptions du callback et du leave vont à `reportDeliveryFailure(onError)`. Vérifier que le `entered` n'est pas nécessaire (le `finally` leave ne doit tourner que si tryEnter a réussi — structure : tryEnter avant le try interne, comme le code actuel via delivery).
- ATTENTION : dans le code actuel, si `tryEnter` échoue → `return` SANS leave ; si le callback lève → reportDeliveryFailure + finally leave. La structure proposée préserve ça (leave dans le finally interne).
- Le cas ONCE : unpublish APRÈS tryEnter réussi, avant invoke — identique à l'actuel.

- [ ] **Step 3: Vérifier les garde-fous + FFI complet**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CallbackFfiJvmTest" --tests "org.graphiks.kffi.CallbackApiTest" --tests "org.graphiks.kffi.CallbackStateMachineTest"`
Expected: verts (13 tests FFI incluant le shuffle 1000, le watchdog, les 3 trampolines).

- [ ] **Step 4: Vérifier les 3 backends**

Run: `./gradlew :kffi:jvmTest :kffi:testDebugUnitTest :kffi:macosArm64Test`
Expected: verts.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt
git commit -m "perf(kffi): allocation-free dispatchSafely (inline lifecycle, no AcquiredDelivery)"
```

### Task M3.2: Audit final d'allocation du hot path

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt` (si un point d'allocation résiduel est trouvé)

- [ ] **Step 1: Auditer le hot path dispatch**

Chemin : `dispatchSafely` → `route` → `tryEnter` → `invoke` → `leave`.
Allocations restantes attendues : AUCUNE (pas de DeliverySnapshot, pas d'AcquiredDelivery, pas de boxing, pas de map).
Vérifier par inspection : `rg "DeliverySnapshot|AcquiredDelivery|\.toMap\(|emptyMap\(" kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt` → zéro.

- [ ] **Step 2: Vérifier les allocations restantes hors hot path (acceptables)**

`NoUserdataSnapshot` (activation/retrait, hors dispatch), `RegistryEntry`/`RuntimeCallbackRegistration` (register, hors dispatch), `PreparedCallbackRegistration` (prepare, hors dispatch). Ces allocations restent — elles sont hors du chemin par dispatch.

- [ ] **Step 3: Commit (si changement)**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt
git commit -m "chore(kffi): audit dispatch hot path allocation-free"
```

---

## Milestone M4 — Re-baseline upcall

### Task M4.1: Mesure avant/après (smoke) et rapport versionné

**Files:**
- Create: `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` (généré)
- (éventuellement) Modify: `kffi-benchmark-jvm/src/jmh/.../UpcallBenchmarks.kt` (si un axe d'allocation est ajouté)

- [ ] **Step 1: Vérifier la compilation des benchmarks**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: PASS (le runtime commun a changé — les benchmarks upcall utilisent CallbackRuntime).

- [ ] **Step 2: Lancer la suite JMH complète (config P2/P3)**

Run: `./gradlew :kffi-benchmark-jvm:jmhJar` puis `java -jar ...` (avgt, wi=3, i=5, 1s, fork=2 — même config que P2/P3)
Expected: rapport complet.

- [ ] **Step 3: Analyser les Δ vs baseline P3 (`2026-08-16-3ca7130e-jvm-baseline.md`)**

Focus upcall :
- `upcall.fire_one` (76.84 ns P3) : attendu significativement plus bas (zéro-allocation + CAS long vs CAS objet).
- `upcall.fire_one_no_routing` (76.70 ns) : attendu plus bas aussi (le no-userdata path n'avait pas la map mais avait quand même les snapshots).
- `upcall.fire_1000` (72719 ns → ~72.7 ns/call) : attendu plus bas par call.
- Autres axes (downcall, marshaling, struct, arena) : attendus stables (inchangés par P4) — vérifier ±8%.

- [ ] **Step 4: Générer les rapports versionnés + analyser**

Suivre la convention P2/P3 : json depuis build/reports/jmh/result.json + md avec table des scénarios, Δ vs P3, verdicts, notes de comparabilité. Ajouter une note sur l'objectif zéro-allocation (l'inspection du bytecode ou un test instrumenté d'allocation si faisable — sinon l'attribution par la mesure seule).

- [ ] **Step 5: Commit**

```bash
git add kffi/benchmarks/results
git commit -m "bench(kffi): P4 re-baseline report (allocation-free dispatch)"
```

### Task M4.2: Vérification finale + état de branche

- [ ] **Step 1: Matrice complète**

Run: `./gradlew :kffi:jvmTest :kffi:testDebugUnitTest :kffi:macosArm64Test :kffi-benchmark-jvm:compileJmhKotlin :kffi-benchmark-native:compileKotlinMacosArm64`
Expected: tous verts.

- [ ] **Step 2: Critères P4**

- `rg "DeliverySnapshot|AcquiredDelivery|emptyMap\(" kffi/src/commonMain/kotlin/org/graphiks/kffi/CallbackRuntime.kt` → zéro.
- `CallbackStateMachineTest` + `CallbackApiTest` + `CallbackFfiJvmTest` (13) + `CallbackRuntimeJvmTest` (hook) + `CallbackRuntimeNativeTest` + `CallbackTokenAddressCodecNativeTest` — tous verts sur les 3 backends.
- Rapport `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` présent avec Δ upcall vs P3.

- [ ] **Step 3: Report**

Résumer : milestones M1-M4, Δ perf upcall vs P3, allocations éliminées, sémantique intacte (garde-fous), handover P5 (kextract générique, release, migration finale) + décisions P3/P4 en attente (valeur du mode unsafe JVM — annexe P3).

---

## Out of scope (follow-up)

- **P5** — kextract générique, release `org.graphiks:kffi-*`, migration finale vers `Graphiks-org/kffi`.
- Décision annexe P3/P4 : proposition de valeur du mode `unsafe` JVM (parité scalaire mesurée) — à trancher en P5 avec la re-baseline native.
- `NoUserdataSlotStateMachine` : snapshot object toujours alloué à l'activation/retrait — hors hot path dispatch, non optimisé en P4.
- Optimisations `Unsafe.copyMemory` bulk (annexe P3) : dépend de la décision unsafe-JVM.
