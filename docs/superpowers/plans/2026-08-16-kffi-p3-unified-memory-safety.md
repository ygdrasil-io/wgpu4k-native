# kffi P3 — Sûreté mémoire unifiée Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Uniformiser la sûreté mémoire des `MemoryBuffer` sur les 3 backends (JVM/Android/native) : bornes-check scalaires + tableaux au même endroit logique, exception homogène `IndexOutOfBoundsException` avec offset/taille, opt-in `unsafe` par allocateur/buffer (I3), contrat commun documenté, re-baseline mesurée.

**Architecture:** Chaque backend garde son mécanisme d'accès mémoire (FFM borné, Unsafe Android, cinterop) mais expose le même contrat : tout accès typé passe par `MemoryBuffer` (borné par défaut), `NativeAddress` nu reste non-borné par nature. Le flag `unsafe` est porté par `MemoryAllocator` (propagé aux buffers créés) ET par le constructeur `MemoryBuffer(handler, size, unsafe)` pour les adresses brutes — actif → bornes-check éliminés, défaut → actifs. JVM : FFM borné par défaut, second chemin `sun.misc.Unsafe` quand unsafe est demandé (doublement assumé, décision validée). Android : le flag retire le `boundsCheck` (Unsafe déjà sous le capot). Native : constante compilée build-time, non basculable au runtime — le contrat documente cette différence.

**Tech Stack:** Kotlin Multiplatform, FFM (JVM), sun.misc.Unsafe (JVM unsafe), cinterop (native), kextract (inchangé en P3), JMH + harness native (re-baseline).

---

## Contexte et décisions P3

### État P2 (constaté, branche `feat/kffi-socle-generique`)

- **JVM** (`MemoryBuffer.jvm.kt`) : bornes-check via FFM natif — le segment est borné (`reinterpret(size)` ou `scopedSegment`), FFM lève `IndexOutOfBoundsException` sur les accès hors bornes. Les `write`/`read` de tableaux font aussi des `require(...) { "Out of destination bounds" }` (IllegalArgumentException). Mécanisme unsafe : inexistant.
- **Android** (`MemoryBuffer.android.kt`) : `boundsCheck(offset, width)` sur chaque scalaire (`require(offset < size && offset + width.toULong() <= size)` → IllegalArgumentException) + `require` sur les tableaux. Accès via `AndroidUnsafe` (sun.misc.Unsafe). Le flag unsafe consiste à sauter `boundsCheck`.
- **Native** (`MemoryBuffer.native.kt`) : **scalaires NON bornés** — `getPointerAtOffset(offset).pointed.value` brut, pas de check ; seuls les tableaux ont `boundCheck` (`require(bufferEnd <= size)` → IllegalArgumentException). C'est le gap principal de sûreté P3.
- **Contrat actuel** : aucune documentation commune des bornes/aliasing/validité après close ; la décision I2-(a) (P2) donne : buffers scopés → `IllegalStateException` post-close, buffers bruts → UB documenté.
- `NativeAddress` : `expect value class NativeAddress(val rawValue: Long)` (inline, P2) — nu, non borné.
- Baseline native P0 : harness `kffi-benchmark-native` (macosArm64/X64) mesure downcall + marshaling ; baseline JVM P2 : `2026-08-16-f6cdb8f0-jvm-baseline.{md,json}`.

### Décisions P3 (validées)

| Décision | Choix |
|---|---|
| Emplacement du flag `unsafe` (I3) | **Flag sur `MemoryAllocator` (propagé aux buffers créés) + paramètre du constructeur `MemoryBuffer` pour les adresses brutes** — couvre « par allocateur ou par buffer » |
| Exception homogène | **`IndexOutOfBoundsException` partout** avec offset/taille dans le message (JVM FFM lève déjà cette classe ; Android/native migrent de `require`/IllegalArgumentException) |
| Mécanisme unsafe JVM | **Second chemin `sun.misc.Unsafe`** (get/put sur l'adresse brute) quand `unsafe` est demandé ; FFM borné par défaut — doublement assumé |
| Mécanisme unsafe Android | Le flag saute le `boundsCheck` (l'accès passe déjà par Unsafe) |
| Mécanisme unsafe Native | **Constante compilée build-time** — non basculable au runtime ; le contrat documente que les distributions native sont figées à la compilation |

### Critère de sortie P3

1. Les 3 backends lèvent `IndexOutOfBoundsException` avec offset/taille sur tout accès hors bornes (scalaires + tableaux), même scénario de test commun.
2. `MemoryAllocator(unsafe = true)` et `MemoryBuffer(handler, size, unsafe = true)` éliminent les bornes-check (sémantique vérifiée par test : un accès hors bornes ne lève pas).
3. JVM : mode défaut = FFM borné (perf P2 inchangée), mode unsafe = sun.misc.Unsafe (accès plus rapide, mesuré).
4. Native : scalaires bornés (gap comblé), constante build-time documentée.
5. Contrat commun documenté (KDoc MemoryBuffer/MemoryAllocator + README) : bornes-check, aliasing, validité après close (I2-a), accès post-close UB, différence native build-time.
6. Tests communs : mêmes scénarios d'erreur sur les 3 backends (kotest commonTest).
7. Re-baseline : JVM (JMH) + native (harness) — overhead bornes-check mesuré vs P0/P2, rapport versionné.

### Files map

**kffi — commonMain (contrat + tests communs) :**
- `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt` — expect : paramètre `unsafe` sur le constructeur
- `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt` — expect : paramètre `unsafe` sur le constructeur
- `kffi/src/commonTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsCommonTest.kt` — créer (scénarios d'erreur partagés)

**kffi — jvmMain (FFM défaut + chemin Unsafe) :**
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt` — flag unsafe + second chemin Unsafe
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt` — flag unsafe propagé
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmUnsafeAccess.kt` — créer (accès sun.misc.Unsafe)
- `kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt` — créer

**kffi — androidMain (boundsCheck conditionnel) :**
- `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryBuffer.android.kt` — flag unsafe + IndexOutOfBoundsException
- `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryAllocator.android.kt` — flag unsafe propagé

**kffi — nativeMain (gap comblé + constante build-time) :**
- `kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryBuffer.native.kt` — bornes-check scalaires + constante unsafe
- `kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryAllocator.native.kt` — flag unsafe (build-time)
- `kffi/src/nativeTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsNativeTest.kt` — créer (vérifie la constante build-time)

**kffi — benchmarks :**
- `kffi-benchmark-jvm/src/jmh/.../MarshalingBenchmarks.kt` — axe unsafe vs borné
- `kffi-benchmark-native/src/macosMain/.../NativeHarness.kt` — axe marshaling borné vs unsafe (build-time)
- `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` + rapport native (re-baseline)

**Docs :**
- `kffi/README.md` (ou doc équivalente) — section contrat mémoire

---

## Milestone M1 — Contrat commun : flag unsafe + exception homogène

### Task M1.1: Contrat commun — `unsafe` sur MemoryAllocator et MemoryBuffer

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt`
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt`
- Test: `kffi/src/commonTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsCommonTest.kt` (créer)

- [ ] **Step 1: Écrire le test commun de bornes (même scénario sur les 3 backends)**

```kotlin
package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldContain

class MemoryBufferBoundsCommonTest : FreeSpec({

    "scalar read beyond size throws IndexOutOfBoundsException with offset" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }.message shouldContain "8"
        }
    }

    "scalar write crossing the end throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.writeLong(1L, 6u) // 6 + 8 > 8
            }
        }
    }

    "array write crossing the end throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.writeInts(IntArray(4), bufferOffset = 12u) // 12 + 16 > 16
            }
        }
    }

    "pointer read beyond size throws IndexOutOfBoundsException" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readPointer(8u)
            }
        }
    }
})
```

- [ ] **Step 2: Vérifier qu'il échoue (native)**

Run: `./gradlew :kffi:macosArm64Test --tests "org.graphiks.kffi.MemoryBufferBoundsCommonTest"`
Expected: FAIL — les scalaires native ne lèvent rien (accès brut hors bornes, comportement indéterminé).

- [ ] **Step 3: Modifier les contrats communs**

`kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt` :

```kotlin
expect class MemoryBuffer(handler: NativeAddress, size: ULong, unsafe: Boolean = false) {
    val size: ULong
    val handler: NativeAddress
    // ... accesseurs inchangés
}
```

`kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt` :

```kotlin
expect class MemoryAllocator(unsafe: Boolean = false) : AutoCloseable {
    // ... méthodes inchangées
}
```

- [ ] **Step 4: Vérifier la compilation (les actuals sont à adapter — attendu rouge)**

Run: `./gradlew :kffi:compileKotlinJvm :kffi:compileDebugKotlinAndroid :kffi:compileKotlinMacosArm64`
Expected: rouge sur les 3 actuals (constructeurs) — corrigés en M2/M3/M4 ; les tests communs restent le contrat.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt \
        kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt \
        kffi/src/commonTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsCommonTest.kt
git commit -m "feat(kffi): common unsafe flag and homogeneous bounds contract"
```

### Task M1.2: Documenter le contrat commun (KDoc)

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt`
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt`

- [ ] **Step 1: Ajouter les KDoc de contrat**

Sur `MemoryBuffer` :

```kotlin
/**
 * Buffer borné sur une adresse native.
 *
 * ## Contrat mémoire (P3 — unifié sur les 3 backends)
 * - Tout accès typé (scalaire ou tableau) est vérifié : offset + elementSize ≤ size.
 * - Hors bornes → [IndexOutOfBoundsException] avec offset/taille dans le message.
 * - [unsafe] = true élimine les bornes-check (opt-in par allocateur ou par buffer,
 *   I3) : tout accès hors bornes devient UB. Défaut : false (bornes-check actifs).
 * - `NativeAddress` nu n'est PAS borné par nature ; tout accès typé passe par
 *   `MemoryBuffer` (borné) ou l'option unsafe.
 * - Durée de vie (I2-a, P2) : buffer créé via [MemoryAllocator] → use-after-close
 *   lève IllegalStateException (JVM) ; buffer depuis adresse brute → accès post-close
 *   non détecté (UB documenté, aligné Android/native).
 * - Aliasing : deux buffers sur la même zone mémoire sont vus mutuellement ; pas de
 *   verrou.
 */
```

Sur `MemoryAllocator` :

```kotlin
/**
 * Allocateur d'arène confinée.
 * [unsafe] = true propage l'option unsafe à tous les buffers créés par cet
 * allocateur (bornes-check éliminés, I3). Défaut : false.
 * NOTE native : la valeur unsafe est figée à la compilation (constante build-time,
 * voir MemoryBuffer.native.kt) — les distributions native ne peuvent pas basculer
 * au runtime ; le flag est accepté pour la compatibilité d'API et sans effet.
 */
```

- [ ] **Step 2: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryBuffer.kt \
        kffi/src/commonMain/kotlin/org/graphiks/kffi/MemoryAllocator.kt
git commit -m "docs(kffi): common memory contract (bounds, aliasing, lifetime, unsafe)"
```

---

## Milestone M2 — Native : bornes-check scalaires + constante build-time

### Task M2.1: Bornes-check scalaires native

**Files:**
- Modify: `kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryBuffer.native.kt`

- [ ] **Step 1: Réécrire les accesseurs scalaires avec boundsCheck**

Pattern pour chaque paire (les 10 types scalaires + pointeur) :

```kotlin
actual fun writeByte(value: Byte, offset: ULong) {
    boundsCheck(offset, 1L)
    getPointerAtOffset<ByteVar>(offset).pointed.value = value
}

actual fun readByte(offset: ULong): Byte {
    boundsCheck(offset, 1L)
    return getPointerAtOffset<ByteVar>(offset).pointed.value
}

actual fun writeLong(value: Long, offset: ULong) {
    boundsCheck(offset, 8L)
    getPointerAtOffset<LongVar>(offset).pointed.value = value
}

actual fun readLong(offset: ULong): Long {
    boundsCheck(offset, 8L)
    return getPointerAtOffset<LongVar>(offset).pointed.value
}
// ... Short/UShort (2), Int/UInt (4), Float (4), Double (8), ULong (8), UByte (1)
```

Pointer (adresse 8 octets) :

```kotlin
actual fun writePointer(value: NativeAddress, offset: ULong) {
    boundsCheck(offset, 8L)
    getPointerAtOffset<LongVar>(offset).pointed.value = value.rawValue
}

actual fun readPointer(offset: ULong): NativeAddress {
    boundsCheck(offset, 8L)
    return getPointerAtOffset<LongVar>(offset).pointed.value.toCPointer<COpaque>()
        ?.let(NativeAddress::fromPointer)
        ?: error("fail to read pointer at offset $offset")
}
```

- [ ] **Step 2: Ajouter le helper `boundsCheck` (exception homogène)**

```kotlin
private fun boundsCheck(offset: ULong, width: Long) {
    if (unsafe) return
    if (offset >= size || offset + width.toULong() > size) {
        throw IndexOutOfBoundsException(
            "MemoryBuffer access out of bounds: offset=$offset width=$width size=$size",
        )
    }
}
```

- [ ] **Step 3: Mettre à jour le constructeur avec le flag unsafe (constante build-time)**

```kotlin
actual class MemoryBuffer actual constructor(
    actual val handler: NativeAddress,
    actual val size: ULong,
    unsafe: Boolean,
) {
    /**
     * Constante compilée : les distributions native sont figées à la compilation
     * (I3, P3) — la valeur du flag runtime est ignorée au profit de cette constante.
     * Basculer à la compilation : définir KFFI_NATIVE_UNSAFE=true dans la tâche
     * cinterop/native du module (voir build.gradle.kts).
     */
    private val unsafe: Boolean = KFFI_NATIVE_UNSAFE

    // ... reste inchangé
}
```

avec, en haut du fichier :

```kotlin
private const val KFFI_NATIVE_UNSAFE: Boolean = false
```

- [ ] **Step 4: Adapter le constructeur MemoryAllocator.native**

`kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryAllocator.native.kt` :

```kotlin
actual class MemoryAllocator actual constructor(unsafe: Boolean) : AutoCloseable {
    // flag ignoré au runtime (constante compilée) — documenté dans le KDoc commun
    // ... reste inchangé
}
```

- [ ] **Step 5: Vérifier**

Run: `./gradlew :kffi:macosArm64Test --tests "org.graphiks.kffi.MemoryBufferBoundsCommonTest"`
Expected: PASS (les 4 scénarios lèvent IndexOutOfBoundsException).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryBuffer.native.kt \
        kffi/src/nativeMain/kotlin/org/graphiks/kffi/MemoryAllocator.native.kt
git commit -m "feat(kffi): native scalar bounds checks with build-time unsafe constant"
```

### Task M2.2: Test native de la constante build-time

**Files:**
- Test: `kffi/src/nativeTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsNativeTest.kt` (créer)

- [ ] **Step 1: Écrire le test**

```kotlin
package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class MemoryBufferBoundsNativeTest : FreeSpec({

    "unsafe flag is a compile-time constant on native (build-time, I3)" {
        // La distribution native est figée : le flag runtime est ignoré.
        // Ce test documente le comportement de la distribution par défaut (bornée).
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }
        }
    }

    "allocator unsafe flag is accepted for API compatibility (no runtime effect)" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        shouldThrow<IndexOutOfBoundsException> {
            buffer.readLong(8u)
        }
        allocator.close()
    }
})
```

- [ ] **Step 2: Vérifier**

Run: `./gradlew :kffi:macosArm64Test --tests "org.graphiks.kffi.MemoryBufferBoundsNativeTest"`
Expected: PASS (2 tests).

- [ ] **Step 3: Commit**

```bash
git add kffi/src/nativeTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsNativeTest.kt
git commit -m "test(kffi): native build-time unsafe constant contract"
```

---

## Milestone M3 — Android : exception homogène + flag unsafe

### Task M3.1: IndexOutOfBoundsException + flag unsafe sur Android

**Files:**
- Modify: `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryBuffer.android.kt`
- Modify: `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryAllocator.android.kt`

- [ ] **Step 1: Remplacer `require` par `IndexOutOfBoundsException` dans boundsCheck**

```kotlin
private fun boundsCheck(offset: ULong, width: Long) {
    if (unsafe) return
    if (offset >= size || offset + width.toULong() > size) {
        throw IndexOutOfBoundsException(
            "MemoryBuffer access out of bounds: offset=$offset width=$width size=$size",
        )
    }
}
```

> **Notes de review M1.1/M2.1/M3.1 (résolues/ajustées)** : `MemoryBufferArrayTest` (commonTest) avait 30 assertions `shouldThrow<IllegalArgumentException>` sur les bornes de tableaux — **déjà migrées vers `IndexOutOfBoundsException` en M2.1** (commit 1ae5df3c, déclenchée par la migration native des tableaux). **Découverte M2.1** : le chemin **JVM array** (`writeArray`/`readArray` → `write()`/`read()` dans `MemoryBuffer.jvm.kt`) utilise aussi `require` → IllegalArgumentException ; seule la voie scalaire JVM hérite de l'IndexOutOfBoundsException FFM. **La migration JVM array-require est déplacée en M4.2** (le double chemin unsafe JVM y est implémenté de toute façon — plus cohérent que M3.1, qui est android-only). **M3.1 (résolu)** : test Android du flag `unsafe` ajouté (`MemoryBufferUnsafeAndroidTest`, hors-bornes ne lève pas).

- [ ] **Step 2: Remplacer les `require(...) { "Out of ... bounds" }` des tableaux**

Les 4 sites (writeArray, readArray, write, read) passent de `require` à `IndexOutOfBoundsException` explicite, même message avec offset/taille :

```kotlin
private fun writeArray(...) {
    // ...
    val bytes = size * elementSize.toULong()
    if (unsafe) {
        // pas de vérification — copie directe
    } else {
        if (bytes > this.size || bufferOffset > this.size - bytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array write out of bounds: bufferOffset=$bufferOffset bytes=$bytes size=$this.size",
            )
        }
        if (bytes > arrayBytes.toULong() || arrayIndex * elementSize.toULong() > arrayBytes.toULong() - bytes) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer array write out of bounds: arrayIndex=$arrayIndex bytes=$bytes arrayBytes=$arrayBytes",
            )
        }
    }
    // ... copie via Unsafe inchangée
}
```

- [ ] **Step 3: Mettre à jour le constructeur**

```kotlin
actual class MemoryBuffer actual constructor(
    actual val handler: NativeAddress,
    actual val size: ULong,
    unsafe: Boolean,
) {
    private val unsafe: Boolean = unsafe
    // ... reste inchangé (base, boundsCheck, accesseurs)
}
```

- [ ] **Step 4: Adapter MemoryAllocator.android**

```kotlin
actual class MemoryAllocator actual constructor(unsafe: Boolean) : AutoCloseable {
    private val unsafe: Boolean = unsafe

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        // ... MemoryBuffer(..., unsafe)
}
```

Vérifier que toutes les méthodes créant des MemoryBuffer (allocateBuffer, bufferOf, allocateFrom, bufferOfAddresses) propagent le flag.

- [ ] **Step 5: Vérifier**

Run: `./gradlew :kffi:testDebugUnitTest --tests "org.graphiks.kffi.MemoryBufferBoundsCommonTest"`
Expected: PASS (le test commun s'exécute aussi sur Android unit test).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryBuffer.android.kt \
        kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryAllocator.android.kt
git commit -m "feat(kffi): android homogeneous bounds exceptions and unsafe opt-in"
```

---

## Milestone M4 — JVM : FFM borné par défaut + chemin sun.misc.Unsafe

### Task M4.1: JvmUnsafeAccess — accès sun.misc.Unsafe

**Files:**
- Create: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmUnsafeAccess.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/JvmUnsafeAccessTest.kt` (créer)

- [ ] **Step 1: Écrire le test**

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JvmUnsafeAccessTest : FreeSpec({

    "unsafe read/write round-trips through the raw address" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(16u)
            JvmUnsafeAccess.putLong(buffer.handler.rawValue, 8L, 0xCAFE)
            JvmUnsafeAccess.getLong(buffer.handler.rawValue, 8L) shouldBe 0xCAFE
        }
    }

    "unsafe access does not bounds-check (by design)" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            // Adresse au-delà du buffer : pas d'exception (UB assumé dans ce mode)
            JvmUnsafeAccess.putLong(buffer.handler.rawValue + 16L, 8L, 1L)
        }
    }
})
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.JvmUnsafeAccessTest"`
Expected: FAIL — `JvmUnsafeAccess` n'existe pas.

- [ ] **Step 3: Implémenter**

```kotlin
package org.graphiks.kffi

import sun.misc.Unsafe

/** Accès mémoire non vérifiés (mode unsafe, I3) — sun.misc.Unsafe sur l'adresse brute. */
internal object JvmUnsafeAccess {
    private val unsafe: Unsafe by lazy {
        val field = Unsafe::class.java.getDeclaredField("theUnsafe")
        field.isAccessible = true
        field.get(null) as Unsafe
    }

    fun getByte(address: Long, offset: Long): Byte = unsafe.getByte(address + offset)
    fun putByte(address: Long, offset: Long, value: Byte) = unsafe.putByte(address + offset, value)
    fun getShort(address: Long, offset: Long): Short = unsafe.getShort(address + offset)
    fun putShort(address: Long, offset: Long, value: Short) = unsafe.putShort(address + offset, value)
    fun getInt(address: Long, offset: Long): Int = unsafe.getInt(address + offset)
    fun putInt(address: Long, offset: Long, value: Int) = unsafe.putInt(address + offset, value)
    fun getLong(address: Long, offset: Long): Long = unsafe.getLong(address + offset)
    fun putLong(address: Long, offset: Long, value: Long) = unsafe.putLong(address + offset, value)
    fun getFloat(address: Long, offset: Long): Float = unsafe.getFloat(address + offset)
    fun putFloat(address: Long, offset: Long, value: Float) = unsafe.putFloat(address + offset, value)
    fun getDouble(address: Long, offset: Long): Double = unsafe.getDouble(address + offset)
    fun putDouble(address: Long, offset: Long, value: Double) = unsafe.putDouble(address + offset, value)
}
```

Note : le module JVM doit permettre l'accès à `sun.misc.Unsafe` — vérifier les `--add-opens`/compile options dans `kffi/build.gradle.kts` (le module a déjà `--enable-native-access` pour FFM ; Unsafe nécessite l'accès au module jdk.unsupported qui est ouvert par défaut pour les modules non nommés — vérifier la config Gradle, ajouter `--add-opens` si besoin).

- [ ] **Step 4: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.JvmUnsafeAccessTest"`
Expected: PASS (les 2 tests, le 2e ne lève pas).

- [ ] **Step 5: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmUnsafeAccess.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/JvmUnsafeAccessTest.kt
git commit -m "feat(kffi): JVM raw unsafe access via sun.misc.Unsafe"
```

### Task M4.2: MemoryBuffer JVM — flag unsafe avec double chemin

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt`
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt` (créer)

- [ ] **Step 1: Écrire le test du flag unsafe JVM**

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferUnsafeJvmTest : FreeSpec({

    "unsafe allocator removes bounds checks" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        // Hors bornes : pas d'exception en mode unsafe (UB assumé)
        buffer.writeLong(1L, 64u)
        allocator.close()
    }

    "unsafe buffer from raw address has no bounds checks" {
        memoryScope { scope ->
            val backing = scope.allocateBuffer(64u)
            val unsafeBuffer = MemoryBuffer(backing.handler, 8u, unsafe = true)
            // L'accès déborde du buffer unsafe mais reste dans le backing — pas d'exception
            unsafeBuffer.writeLong(7L, 8u)
        }
    }

    "default (safe) buffer still bounds-checks" {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            shouldThrow<IndexOutOfBoundsException> {
                buffer.readLong(8u)
            }
        }
    }

    "scoped buffer retains use-after-close detection even in unsafe mode" {
        val allocator = MemoryAllocator(unsafe = true)
        val buffer = allocator.allocateBuffer(8u)
        allocator.close()
        shouldThrow<IllegalStateException> {
            buffer.writeLong(1L, 0u)
        }
    }
})
```

Note sur le 4e test : en mode unsafe JVM, l'accès passe par Unsafe sur l'adresse brute — le scopedSegment n'est plus utilisé. La détection post-close ne peut PAS survivre si on court-circuite le segment. DÉCISION à trancher en implémentation : (a) le mode unsafe JVM perd la détection post-close (l'adresse brute est utilisée directement) — documenté ; (b) le mode unsafe garde une vérification de close légère (isAlive sur le segment) mais saute les bornes. Le design I2-a vise la détection de close ; le mode unsafe vise la perf. RECOMMANDATION : (b) — un check `scopedSegment?.scope()?.isAlive == false → IllegalStateException` avant l'accès Unsafe, coût négligeable, préserve la garantie I2-a même en unsafe. Adapter le test au comportement retenu.

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.MemoryBufferUnsafeJvmTest"`
Expected: FAIL — le constructeur n'accepte pas `unsafe`.

- [ ] **Step 3: Modifier MemoryBuffer JVM**

```kotlin
actual class MemoryBuffer actual constructor(
    handler: NativeAddress,
    actual val size: ULong,
    unsafe: Boolean,
) {
    actual val handler: NativeAddress = handler

    private val unsafe: Boolean = unsafe
    private var scopedSegment: MemorySegment? = null

    internal constructor(handler: NativeAddress, size: ULong, scopedSegment: MemorySegment) :
        this(handler, size, false) {
        this.scopedSegment = scopedSegment
    }

    private val fallbackSegment: MemorySegment by lazy { handler.toJvmSegment(size.toLong()) }

    private fun segment(): MemorySegment = scopedSegment ?: fallbackSegment

    /** Vérifie la vie du scope (I2-a) même en mode unsafe, puis retourne l'adresse brute. */
    private fun rawAddress(): Long {
        val scope = scopedSegment?.scope()
        if (scope != null && !scope.isAlive) {
            throw IllegalStateException("MemoryBuffer has been closed")
        }
        return handler.rawValue
    }

    private fun boundsCheck(offset: ULong, width: Long) {
        if (unsafe) return
        if (offset >= size || offset + width.toULong() > size) {
            throw IndexOutOfBoundsException(
                "MemoryBuffer access out of bounds: offset=$offset width=$width size=$size",
            )
        }
    }

    // Accesseurs scalaires — deux chemins :
    actual fun writeLong(value: Long, offset: ULong) {
        if (unsafe) {
            JvmUnsafeAccess.putLong(rawAddress(), offset.toLong(), value)
        } else {
            boundsCheck(offset, 8L)
            segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value)
        }
    }
    // ... même pattern pour les 10 scalaires + pointeur
}
```

Les accesseurs de tableaux : en mode unsafe, utiliser `JvmUnsafeAccess` en boucle (ou `copyMemory`) — pattern similaire à Android ; en mode sûr, chemin FFM existant.

- [ ] **Step 4: Adapter MemoryAllocator JVM**

Le constructeur interne actuel `internal constructor(handler, size, scopedSegment)` doit fusionner avec le nouveau paramètre `unsafe` : le remplacer par `internal constructor(handler, size, scopedSegment, unsafe)` et faire propager le flag par MemoryAllocator :

```kotlin
actual class MemoryAllocator actual constructor(unsafe: Boolean) : AutoCloseable {
    private val unsafe: Boolean = unsafe
    val arena = Arena.ofConfined()

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        arena.allocate(size.toLong())
            .let { segment -> MemoryBuffer(NativeAddress(segment.address()), size, segment, unsafe) }
    // ... bufferOf/allocateFrom/bufferOfAddresses propagent aussi `unsafe`
}
```

Tous les appelants internes du constructeur (JvmConversions, CString, tests) qui utilisent la forme à 3 arguments doivent migrer vers la forme à 4 arguments (ou une factory) — faire un audit `rg "MemoryBuffer\\(" kffi/src/jvmMain kffi/src/jvmTest` et adapter sans casser les consumers.

- [ ] **Step 5: Vérifier**

Run: `./gradlew :kffi:jvmTest`
Expected: 124+ tests verts (incl. le nouveau MemoryBufferUnsafeJvmTest).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferUnsafeJvmTest.kt
git commit -m "feat(kffi): JVM MemoryBuffer unsafe mode via sun.misc.Unsafe with I2-a close guard"
```

---

## Milestone M5 — Tests communs d'erreurs + documentation consommateur

### Task M5.1: Étendre les tests communs d'erreurs

**Files:**
- Modify: `kffi/src/commonTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsCommonTest.kt`

- [ ] **Step 1: Ajouter les scénarios manquants (chaque type scalaire + tableau + pointeur)**

Couvrir : chaque type (Byte, UByte, Short, UShort, Int, UInt, Long, ULong, Float, Double, pointer) en read ET write hors bornes, avec vérification du message (contient offset ET taille — utiliser des valeurs DISTINCTES pour que les deux soient forcés, ex. buffer 16u + readLong(8u) → message doit contenir "8" et "16"). Tableaux : chaque type d'array. Au minimum 2-3 représentants par famille (un scalaire 1-octet, un 4-octets, un 8-octets, le pointeur, un array) — le test commun doit rester lisible.

```kotlin
"every scalar family throws with offset AND size in message" {
    memoryScope { scope ->
        // Valeurs distinctes : offset 8, size 16 — les deux doivent apparaître
        val buffer = scope.allocateBuffer(16u)
        shouldThrow<IndexOutOfBoundsException> { buffer.readByte(16u) }.message.shouldContainAll("16", "16")
        shouldThrow<IndexOutOfBoundsException> { buffer.readInt(14u) }.message shouldContain "14"
        shouldThrow<IndexOutOfBoundsException> { buffer.readLong(10u) }.message.shouldContainAll("10", "16")
        shouldThrow<IndexOutOfBoundsException> { buffer.readDouble(10u) }.message.shouldContainAll("10", "16")
        shouldThrow<IndexOutOfBoundsException> { buffer.readPointer(16u) }.message shouldContain "16"
    }
}

"write crossing end throws for each family" {
    memoryScope { scope ->
        val buffer = scope.allocateBuffer(8u)
        shouldThrow<IndexOutOfBoundsException> { buffer.writeInt(1, 6u) }
        shouldThrow<IndexOutOfBoundsException> { buffer.writeShort(1, 7u) }
        shouldThrow<IndexOutOfBoundsException> { buffer.writeByte(1, 8u) }
        shouldThrow<IndexOutOfBoundsException> { buffer.writeLong(1L, 1u) }
    }
}

"array read crossing the end throws (read path, not just write)" {
    memoryScope { scope ->
        val buffer = scope.allocateBuffer(16u)
        shouldThrow<IndexOutOfBoundsException> {
            buffer.readInts(IntArray(4), bufferOffset = 12u)
        }
    }
}

"boundary access at exactly size is allowed (inclusive upper bound)" {
    memoryScope { scope ->
        val buffer = scope.allocateBuffer(8u)
        buffer.writeLong(0xCAFE, 0u) // offset 0 + 8 = 8 ≤ 8 : autorisé
        buffer.readLong(0u) shouldBe 0xCAFE
    }
}
```

Note : `shouldContainAll` est un matcher kotest (`io.kotest.matchers.string.shouldContainAll`) — vérifier la disponibilité dans le module ; sinon deux `shouldContain` successifs. Le cas frontière positif (`offset + width == size`) protège contre un off-by-one dans l'implémentation native (M2.1).

- [ ] **Step 2: Vérifier sur les 3 backends**

Run: `./gradlew :kffi:jvmTest :kffi:testDebugUnitTest :kffi:macosArm64Test --tests "org.graphiks.kffi.MemoryBufferBoundsCommonTest"`
Expected: PASS partout.

- [ ] **Step 3: Commit**

```bash
git add kffi/src/commonTest/kotlin/org/graphiks/kffi/MemoryBufferBoundsCommonTest.kt
git commit -m "test(kffi): extend common bounds scenarios across all scalar families"
```

### Task M5.2: Documentation consommateur (README)

**Files:**
- Modify: `kffi/README.md` (ou la doc racine existante — vérifier où vit la doc kffi)

- [ ] **Step 1: Ajouter la section contrat mémoire**

Sections à couvrir (au minimum) :
- Bornes-check : tout accès typé vérifié, `IndexOutOfBoundsException` avec offset/taille.
- `unsafe` opt-in : par allocateur (`MemoryAllocator(unsafe = true)`) ou par buffer (`MemoryBuffer(addr, size, unsafe = true)`) ; sémantique ; UB hors bornes.
- Durée de vie : I2-a (scope dans MemoryBuffer) ; post-close : IllegalStateException (JVM, mode sûr) ; buffers bruts : UB documenté ; mode unsafe JVM : garde de close légère.
- Différence native : constante compilée build-time, distributions figées.
- Aliasing : pas de verrou, vues mutuelles.

- [ ] **Step 2: Vérifier (lien + cohérence avec le KDoc commun)**

- [ ] **Step 3: Commit**

```bash
git add kffi/README.md
git commit -m "docs(kffi): consumer memory-safety contract (bounds, unsafe opt-in, lifetime)"
```

---

## Milestone M6 — Re-baseline : overhead bornes-check mesuré

### Task M6.1: Axe unsafe vs borné dans les benchmarks JVM

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt`

- [ ] **Step 1: Ajouter les axes borné vs unsafe**

```kotlin
@State(Scope.Thread)
class MarshalingState {
    lateinit var safeBuffer: MemoryBuffer
    lateinit var unsafeBuffer: MemoryBuffer

    @Setup
    fun setup() {
        val safeAllocator = MemoryAllocator()
        safeBuffer = safeAllocator.allocateBuffer(4096u)
        val unsafeAllocator = MemoryAllocator(unsafe = true)
        unsafeBuffer = unsafeAllocator.allocateBuffer(4096u)
    }
}

@Benchmark
fun scalarSafe(state: MarshalingState, bh: Blackhole): Unit =
    state.safeBuffer.writeLong(1L, 0u).let { bh.consume(state.safeBuffer.readLong(0u)) }

@Benchmark
fun scalarUnsafe(state: MarshalingState, bh: Blackhole): Unit =
    state.unsafeBuffer.writeLong(1L, 0u).let { bh.consume(state.unsafeBuffer.readLong(0u)) }
```

- [ ] **Step 2: Vérifier la compilation**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt
git commit -m "bench(jvm): safe-vs-unsafe marshaling axes"
```

### Task M6.2: Axe marshaling unsafe dans le harness native (build-time)

**Files:**
- Modify: `kffi-benchmark-native/src/macosMain/kotlin/org/graphiks/kffi/benchmark/native/NativeHarness.kt`

- [ ] **Step 1: Ajouter l'axe marshaling unsafe**

Le harness native compile avec la constante KFFI_NATIVE_UNSAFE à false par défaut — l'axe unsafe n'est mesurable qu'avec une variante build-time. Pour cette tâche : documenter dans le harness que l'overhead bornes-check est l'écart entre `measureMarshaling` (borné) et une variante unsafe (à compiler avec la constante à true) ; ajouter un commentaire + la structure pour la variante.

```kotlin
// NOTE P3 : l'overhead des bornes-check native est mesuré par l'écart entre ce
// scénario (borné, KFFI_NATIVE_UNSAFE=false) et la variante unsafe (compiler le
// module avec la constante à true). Le rapport P3 documente les deux mesures.
```

- [ ] **Step 2: Vérifier la compilation native**

Run: `./gradlew :kffi-benchmark-native:compileKotlinMacosArm64`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-native/src/macosMain/kotlin/org/graphiks/kffi/benchmark/native/NativeHarness.kt
git commit -m "bench(native): document bounds-check overhead measurement"
```

### Task M6.3: Produire les rapports de re-baseline

**Files:**
- Create: `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` (généré, JVM)
- Create: `kffi/benchmarks/results/<date>-<sha>-native-baseline.{md,json}` (généré, native)

- [ ] **Step 1: Lancer la suite JMH JVM complète**

Run: `./gradlew :kffi-benchmark-jvm:jmhJar` puis `java -jar ...` (config P0/P2 : avgt, wi=3, i=5, 1s, fork=2)
Expected: rapport complet avec les axes safe/unsafe.

- [ ] **Step 2: Lancer le harness native**

Run: `./gradlew :kffi-benchmark-native:runBenchmarkNative` (ou la task équivalente — vérifier le nom)
Expected: rapport markdown native.

- [ ] **Step 3: Analyser les Δ**

Comparer avec la baseline P2 (`2026-08-16-f6cdb8f0-jvm-baseline.md`) et le harness native P0 :
- overhead bornes-check JVM (scalarSafe vs scalarUnsafe) : attendu ~10-40% (le check FFM est déjà intégré ; l'écart vient du coût de `boundsCheck` Kotlin + segment) ;
- overhead bornes-check native (borné vs variante unsafe) : attendu significatif sur les scalaires (le gap P3 ajoute le check) ;
- les scénarios existants (downcall, upcall, struct, arena) : attendus stables vs P2 (le mode sûr JVM = FFM borné inchangé).

- [ ] **Step 4: Commit**

```bash
git add kffi/benchmarks/results
git commit -m "bench(kffi): P3 re-baseline reports (bounds-check overhead, JVM + native)"
```

---

## Out of scope (follow-up)

- **P4** — optimisation callback runtime (table d'index par token, zéro-allocation dispatch).
- **P5** — kextract générique, release `org.graphiks:kffi-*`, migration finale.
- Décision I2-(b) (UB post-close partout) : rejetée en P2 — le mode unsafe JVM garde la garde de close légère (recommandation M4.2).
- Basculer la constante native à la compilation : doc dans le KDoc + cette annexe (pas de tâche CI multi-variantes en P3).

---

## Annexe — Notes P4 (optimisations identifiées en M4.2, hors scope P3)

- **Chemin unsafe JVM array** : remplacer la boucle élément-par-élément par un `Unsafe.copyMemory` bulk (pas de contrainte ART côté JVM) — hot path du mode unsafe.
- **Hoisting du `when (elementSize)`** hors du `repeat(count)` (JVM unsafe array loops) — pattern Android déjà hoisté.
- **Centraliser la branche dual-path** des 20 accesseurs scalaires JVM (helper `access(offset, width, safeBlock, unsafeBlock)`) — réduit la surface de drift.
- **Cas Float/Double des tableaux unsafe** : ajouter un round-trip de test dédié (le code est correct, la garantie est non testée).
- Migrer `sun.misc.Unsafe` (dépréciation terminale JDK 23+) vers `jdk.internal.misc.Unsafe` (`--add-opens java.base/jdk.internal.misc`) ou FFM raw segments quand nécessaire.
