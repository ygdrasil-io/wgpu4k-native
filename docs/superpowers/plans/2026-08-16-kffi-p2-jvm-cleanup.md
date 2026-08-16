# kffi P2 — Nettoyage JVM Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Éliminer les fuites d'implémentation FFM du code généré JVM (`.handler.handler`, `Arena.ofAuto()`, `MemorySegment`/`MethodHandle`/`ValueLayout`) en alignant le backend JVM sur le modèle memory-backed Android : `NativeAddress` = adresse brute inline, `MemoryBuffer` allégé avec scope, moteurs downcall/upcall JVM typés par forme dans kffi, kextract générant contre l'API kffi uniquement.

**Architecture:** Le backend JVM adopte le modèle déjà livré en P1 pour Android : structs memory-backed (`ByReference`/`ByValue` avec `MemoryBuffer`), adresses brutes (`NativeAddress(rawValue: Long)`), moteur d'appel typé par forme (`JvmDowncallEngine.callP2PP(fn, a1, a2)` cachant les `MethodHandle` FFM `invokeExact`), et upcalls via un `JvmUpcallEngine` runtime (les trampolines de signature quittent le code généré). La décision I2-(a) est retenue : le scope d'arène/session vit dans `MemoryBuffer` (pas dans `NativeAddress`), préservant la détection de use-after-close JVM. Les retours de structs par valeur reçoivent un paramètre `allocator: MemoryAllocator` explicite (fin d'`Arena.ofAuto()`), propagé aux 3 backends via la signature commune.

**Tech Stack:** Kotlin Multiplatform, java.lang.foreign (FFM, interne à kffi), kextract (générateur), wgpu4k-native (consommateur), JMH (re-baseline).

---

## Contexte et décisions P2

### État P1 (constaté dans le worktree, branche `feat/kffi-socle-generique`)

- `NativeAddress.jvm.kt` : `class JvmNativeAddress(val handler: MemorySegment)` + `actual typealias NativeAddress = JvmNativeAddress` → fuite FFM à 1368 occurrences de `.handler` dans `wgpu_hJvm.kt` (8885 lignes générées).
- 9 sites `Arena.ofAuto()` dans `wgpu_hJvm.kt` (retours `WGPUFuture` par valeur : `wgpuAdapterRequestDevice`, `wgpuBufferMapAsync`, `wgpuDeviceCreateComputePipelineAsync`, `wgpuDeviceCreateRenderPipelineAsync`, `wgpuDeviceGetLostFuture`, `wgpuDevicePopErrorScope`, `wgpuInstanceRequestAdapter`, `wgpuQueueOnSubmittedWorkDone`, `wgpuShaderModuleGetCompilationInfo`).
- `MemoryBuffer.jvm.kt:10` porte le scope via `handler.handler.reinterpret(size)` → détection de use-after-close aujourd'hui (I2 : option (a) à préserver).
- kextract `KotlinKmpJvmBuilder` émet `MemorySegment`, `MethodHandle`, `VarHandle`, `ValueLayout`, `FunctionDescriptor`, `Linker`, `Arena` directement dans le code généré ; `KotlinCallbackJvmEmitter` émet des trampolines `upcallStub` avec `MethodHandles.lookup()`.
- Backend Android (modèle cible) : structs memory-backed via `MemoryBuffer`, `NativeEngine` typé par forme (`callP2PP(fn: Long, a1: Long, a2: Long)`), adresses brutes, `actual class NativeAddress(val rawValue: Long)`.
- Baseline JVM P0 : `downcall.empty` 27.36 ns, `downcall.add4` 26.54 ns, `downcall.add8` 36.45 ns, `struct_by_value_return` 58.98 ns, `struct_by_value_arg` 36.96 ns, `fmmExact` 25.96 ns, `upcall.fire_one` 120.18 ns.

### Décisions P2 (validées)

| Décision | Choix |
|---|---|
| I2 — durée de vie JVM | **(a)** scope d'arène/session dans `MemoryBuffer` (pas dans `NativeAddress`) ; la JVM garde la détection de use-after-close |
| Retours par valeur | **Paramètre `allocator: MemoryAllocator` ajouté** aux fonctions générées retournant un struct par valeur (breaking change API générée, sites d'appel mis à jour) |
| Moteur downcall JVM | **Table typée par forme** : `JvmDowncallEngine.callXXX(fn: Long, ...)` cachant les MethodHandles `invokeExact` (symétrique `NativeEngine` Android) |
| Upcalls JVM | Trampolines par forme fournis par le runtime kffi (`JvmUpcallEngine`), le code généré ne référence plus `Linker`/`MethodHandles` |

### Critère de sortie P2

1. `rg "handler\.handler|\.handler\b" wgpu4k-native/src/jvmMain` → zéro occurrence FFM ; `rg "Arena\.ofAuto|MemorySegment|MethodHandle|ValueLayout|FunctionDescriptor|Linker" wgpu4k-native/src/jvmMain` → zéro occurrence (hors imports de types kffi).
2. `./gradlew :kffi:jvmTest` → 87+ tests verts (count conservé ou augmenté).
3. kextract goldens JVM : compile + probe verts (`KmpJvmFfmAbiTest` migré → `KmpJvmMemoryBackedAbiTest`).
4. Re-baseline JVM : rapport versionné dans `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` ; Δ downcall documenté (invokeExact conservé, overhead `ofAddress` assumé, seuil d'alerte : `downcall.add4` ≤ 1.5 × 26.54 ns sinon optimisation du moteur).
5. Les 3 backends compilent et les tests wgpu JVM passent avec la nouvelle signature `allocator`.

### Files map

**kffi — jvmMain (runtime réécrit) :**
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/NativeAddress.jvm.kt` — value class `rawValue: Long`
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt` — allégé, scope I2(a)
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt` — allocation + scope
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CString.jvm.kt` — `toKString` via segment à la demande
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/FFI.kt` — `findOrThrow` → `Long` ; moteur downcall
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt` — accès typés internes via segment à la demande
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.jvm.kt` — encode/decode sur `rawValue`
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt` — nouveau (créer)
- `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmUpcallEngine.kt` — nouveau (créer)

**kffi — commonMain :**
- `kffi/src/commonMain/kotlin/org/graphiks/kffi/NativeAddress.kt` — `expect value class NativeAddress(val rawValue: Long)`

**kextract :**
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt` — symboles runtime JVM engine
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt` — réécrit memory-backed
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt` — param `allocator` sur retours par valeur
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt` — via `JvmUpcallEngine`
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt` — actual avec `allocator`
- `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt` — actual avec `allocator`
- tests d'intégration kextract (goldens JVM)

**wgpu4k-native :**
- `wgpu4k-native/src/{common,jvm,android,native}Main/.../wgpu_h*.kt` — régénérés
- tests consumers JVM (`GeneratedCallbackJvmTest`, `CallbackInfoFactoryJvmTest`, `JvmBootstrapProbe`) — `memoryScope` sur les sites `WGPUFuture`

**kffi-benchmark-jvm :** benchmarks adaptés aux nouvelles signatures + re-baseline.

---

## Milestone M1 — NativeAddress JVM inline (long brut)

Le contrat commun passe à une adresse brute. `NativeAddress` n'est plus un `MemorySegment` : toute conversion FFM devient interne à kffi.

### Task M1.1: Contrat commun `expect value class NativeAddress(val rawValue: Long)`

**Files:**
- Modify: `kffi/src/commonMain/kotlin/org/graphiks/kffi/NativeAddress.kt`
- Test: `kffi/src/commonTest/kotlin/org/graphiks/kffi/NativeAddressCommonTest.kt` (créer)

- [ ] **Step 1: Écrire le test commun**

```kotlin
package org.graphiks.kffi

import kotlin.test.Test
import kotlin.test.assertEquals

class NativeAddressCommonTest {

    @Test
    fun `rawValue is preserved`() {
        val address = NativeAddress(0x1234_5678L)
        assertEquals(0x1234_5678L, address.rawValue)
    }

    @Test
    fun `null contract via rawValue 0`() {
        val address = NativeAddress(0L)
        assertEquals(0L, address.rawValue)
    }
}
```

- [ ] **Step 2: Vérifier qu'il compile (échoue au runtime attendu)**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.NativeAddressCommonTest"`
Expected: échec de compilation — `expect class NativeAddress` n'expose pas `rawValue`.

- [ ] **Step 3: Modifier le contrat commun**

`kffi/src/commonMain/kotlin/org/graphiks/kffi/NativeAddress.kt` :

```kotlin
package org.graphiks.kffi

expect value class NativeAddress(val rawValue: Long)
```

- [ ] **Step 4: Adapter les 3 actuals**

`kffi/src/androidMain/kotlin/org/graphiks/kffi/NativeAddress.android.kt` :

```kotlin
package org.graphiks.kffi

actual value class NativeAddress actual constructor(actual val rawValue: Long)

fun NativeAddress?.adapt(): Long = if (this == null) 0 else this.rawValue
```

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/NativeAddress.jvm.kt` :

```kotlin
package org.graphiks.kffi

actual value class NativeAddress actual constructor(actual val rawValue: Long)
```

`kffi/src/nativeMain/kotlin/org/graphiks/kffi/NativeAddress.native.kt` :

```kotlin
@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kffi

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong

actual value class NativeAddress actual constructor(actual val rawValue: Long) {

    val pointer: CPointer<*>?
        get() = rawValue.toCPointer<kotlinx.cinterop.COpaque>()

    fun <T : kotlinx.cinterop.CPointed> reinterpret(): CPointer<T> {
        return requireNotNull(pointer).reinterpret()
    }
}
```

Note : les usages cinterop existants (`Pointer` alias supprimé) sont migrés au fur et à mesure dans les tâches suivantes.

- [ ] **Step 5: Compiler et corriger les usages directs dans kffi**

Run: `./gradlew :kffi:compileKotlinJvm :kffi:compileDebugKotlinAndroid`
Expected: erreurs de compilation sur chaque usage de `NativeAddress(...)`/`.handler` — corrigés un par un dans M1.2–M1.5 (les compilations intermédiaires sont volontairement rouges jusqu'à M1.6).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/commonMain/kotlin/org/graphiks/kffi/NativeAddress.kt \
        kffi/src/commonTest/kotlin/org/graphiks/kffi/NativeAddressCommonTest.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/NativeAddress.jvm.kt \
        kffi/src/androidMain/kotlin/org/graphiks/kffi/NativeAddress.android.kt \
        kffi/src/nativeMain/kotlin/org/graphiks/kffi/NativeAddress.native.kt
git commit -m "refactor(kffi): NativeAddress as inline raw-address value class across backends"
```

### Task M1.2: Helpers de conversion FFM internes (jvmMain)

**Files:**
- Create: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmConversions.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/JvmConversionsTest.kt` (créer)

- [ ] **Step 1: Écrire le test**

```kotlin
package org.graphiks.kffi

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class JvmConversionsTest {

    @Test
    fun `segment round-trips through rawValue`() {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(64u)
            val segment = buffer.toJvmSegment()
            assertEquals(64L, segment.byteSize())
            val again = NativeAddress(segment.address()).let { MemoryBuffer(it, 64u) }
            again.writeLong(0xCAFE, 0u)
            assertEquals(0xCAFE, buffer.readLong(0u))
        }
    }

    @Test
    fun `rawValue 0 maps to null segment`() {
        assertEquals(null, NativeAddress(0L).toJvmSegmentOrNull())
    }
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.JvmConversionsTest"`
Expected: FAIL — `toJvmSegment` n'existe pas.

- [ ] **Step 3: Implémenter les conversions**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmConversions.kt` :

```kotlin
package org.graphiks.kffi

import java.lang.foreign.MemorySegment

/** Segment FFM éphémère (non-scopé) pour une adresse brute ; zéro = segment nul. */
internal fun NativeAddress.toJvmSegmentOrNull(): MemorySegment? =
    if (rawValue == 0L) null else MemorySegment.ofAddress(rawValue)

internal fun NativeAddress.toJvmSegment(): MemorySegment =
    requireNotNull(toJvmSegmentOrNull()) { "Cannot convert null NativeAddress to segment" }

/** Adresse d'un segment FFM ; segment nul → 0. */
internal fun MemorySegment?.toNativeAddress(): NativeAddress =
    NativeAddress(this?.address() ?: 0L)

/** Dérive un segment borné [size] depuis l'adresse brute. */
internal fun NativeAddress.toJvmSegment(size: Long): MemorySegment =
    MemorySegment.ofAddress(rawValue).reinterpret(size)

internal fun MemoryBuffer.toJvmSegment(): MemorySegment = handler.toJvmSegment(size.toLong())
```

> **Note JDK 23+** : `MemorySegment.ofAddress(long)` est le seul ctor public (JDK 22 avait `ofAddress(addr, size, arena)`, supprimé en 23). Les segments retournés sont non-scopés (arena global implicite) ; la borne est posée par `reinterpret(size)` quand nécessaire. Toolchain : Temurin 25 (kffi/build.gradle.kts:277).

- [ ] **Step 4: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.JvmConversionsTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/JvmConversions.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/JvmConversionsTest.kt
git commit -m "feat(kffi): internal FFM<->raw-address conversions for the JVM backend"
```

### Task M1.3: MemoryBuffer JVM allégé avec scope (I2-a)

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferScopeJvmTest.kt` (créer)

- [ ] **Step 1: Écrire le test de scope (détection de use-after-close)**

```kotlin
package org.graphiks.kffi

import kotlin.test.Test
import kotlin.test.assertFailsWith

class MemoryBufferScopeJvmTest {

    @Test
    fun `use after close raises IllegalStateException (I2-a)`() {
        val allocator = MemoryAllocator()
        val buffer = allocator.allocateBuffer(16u)
        allocator.close()
        assertFailsWith<IllegalStateException> {
            buffer.writeLong(1L, 0u)
        }
    }

    @Test
    fun `global memory survives allocator close`() {
        val buffer = globalMemory.allocateBuffer(16u)
        buffer.writeLong(42L, 0u)
        assertEquals(42L, buffer.readLong(0u))
    }

    @Test
    fun `unsafe buffer from raw address has no scope`() {
        val allocator = MemoryAllocator()
        val raw = allocator.allocate(16)
        allocator.close()
        // Adresse brute sans scope : accès permis (UB documenté), pas d'exception.
        val buffer = MemoryBuffer(raw, 16u)
        buffer.writeLong(7L, 0u)
    }
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.MemoryBufferScopeJvmTest"`
Expected: FAIL — la construction actuelle `reinterpret(size)` sur adresse brute échoue ou le scope n'est pas porté.

- [ ] **Step 3: Réécrire MemoryBuffer JVM**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt` :

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Buffer borné sur une adresse brute.
 *
 * Décision I2-(a) : le scope d'arène/session vit ici (pas dans NativeAddress).
 * - Créé via [MemoryAllocator] : porte le segment scopé de l'arène → use-after-close
 *   lève IllegalStateException (garantie JVM préservée).
 * - Créé depuis une adresse brute (MemoryBuffer(addr, size)) : sans scope, accès post-close
 *   non détecté (UB documenté, aligné sur Android/native).
 */
actual class MemoryBuffer actual constructor(
    handler: NativeAddress,
    actual val size: ULong,
) {
    actual val handler: NativeAddress = handler

    /** Segment scopé hérité de l'arène, ou null si créé depuis une adresse brute. */
    private val scopedSegment: MemorySegment? = null

    internal constructor(handler: NativeAddress, size: ULong, scopedSegment: MemorySegment) : this(handler, size) {
        this.scopedSegment = scopedSegment
    }

    private fun segment(): MemorySegment =
        scopedSegment ?: handler.toJvmSegment(size.toLong())

    private fun writeArray(destinationOffset: ULong, source: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val sourceOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        write(destinationOffset, sourceOffset, source, bytesToCopy)
    }

    private fun write(destinationOffset: ULong, sourceOffset: ULong, source: MemorySegment, bytesToCopy: ULong) {
        val sourceBytes = source.byteSize().toULong()
        val destinationBytes = size

        require(destinationBytes >= (destinationOffset + bytesToCopy)) { "Out of destination bounds" }
        require(sourceOffset + bytesToCopy <= sourceBytes) { "Out of source bounds" }

        segment().asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(source.asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    private fun readArray(sourceOffset: ULong, destination: MemorySegment, arrayIndex: ULong, size: ULong, elementSizeBytes: Int) {
        val destinationOffset = elementSizeBytes.toULong() * arrayIndex
        val bytesToCopy = elementSizeBytes.toULong() * size
        read(sourceOffset, destinationOffset, destination, bytesToCopy)
    }

    private fun read(sourceOffset: ULong, destinationOffset: ULong, destination: MemorySegment, bytesToCopy: ULong) {
        val destinationBytes = destination.byteSize().toULong()
        val sourceBytes = size

        require(destinationBytes >= (destinationOffset + bytesToCopy)) { "Out of destination bounds" }
        require(sourceOffset + bytesToCopy <= sourceBytes) { "Out of source bounds" }

        destination.asSlice(destinationOffset.toLong(), bytesToCopy.toLong())
            .copyFrom(segment().asSlice(sourceOffset.toLong(), bytesToCopy.toLong()))
    }

    actual fun writeByte(value: Byte, offset: ULong) { segment().set(ValueLayout.JAVA_BYTE, offset.toLong(), value) }
    actual fun readByte(offset: ULong): Byte = segment().get(ValueLayout.JAVA_BYTE, offset.toLong())
    actual fun writeUByte(value: UByte, offset: ULong) { segment().set(ValueLayout.JAVA_BYTE, offset.toLong(), value.toByte()) }
    actual fun readUByte(offset: ULong): UByte = segment().get(ValueLayout.JAVA_BYTE, offset.toLong()).toUByte()
    actual fun writeShort(value: Short, offset: ULong) { segment().set(ValueLayout.JAVA_SHORT, offset.toLong(), value) }
    actual fun readShort(offset: ULong): Short = segment().get(ValueLayout.JAVA_SHORT, offset.toLong())
    actual fun writeUShort(value: UShort, offset: ULong) { segment().set(ValueLayout.JAVA_SHORT, offset.toLong(), value.toShort()) }
    actual fun readUShort(offset: ULong): UShort = segment().get(ValueLayout.JAVA_SHORT, offset.toLong()).toUShort()
    actual fun writeInt(value: Int, offset: ULong) { segment().set(ValueLayout.JAVA_INT, offset.toLong(), value) }
    actual fun readInt(offset: ULong): Int = segment().get(ValueLayout.JAVA_INT, offset.toLong())
    actual fun writeUInt(value: UInt, offset: ULong) { segment().set(ValueLayout.JAVA_INT, offset.toLong(), value.toInt()) }
    actual fun readUInt(offset: ULong): UInt = segment().get(ValueLayout.JAVA_INT, offset.toLong()).toUInt()
    actual fun writeLong(value: Long, offset: ULong) { segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value) }
    actual fun readLong(offset: ULong): Long = segment().get(ValueLayout.JAVA_LONG, offset.toLong())
    actual fun writeULong(value: ULong, offset: ULong) { segment().set(ValueLayout.JAVA_LONG, offset.toLong(), value.toLong()) }
    actual fun readULong(offset: ULong): ULong = segment().get(ValueLayout.JAVA_LONG, offset.toLong()).toULong()
    actual fun writeFloat(value: Float, offset: ULong) { segment().set(ValueLayout.JAVA_FLOAT, offset.toLong(), value) }
    actual fun readFloat(offset: ULong): Float = segment().get(ValueLayout.JAVA_FLOAT, offset.toLong())
    actual fun writeDouble(value: Double, offset: ULong) { segment().set(ValueLayout.JAVA_DOUBLE, offset.toLong(), value) }
    actual fun readDouble(offset: ULong): Double = segment().get(ValueLayout.JAVA_DOUBLE, offset.toLong())

    actual fun writePointer(value: NativeAddress, offset: ULong) {
        segment().set(ValueLayout.ADDRESS, offset.toLong(), value.toJvmSegmentOrNull() ?: MemorySegment.NULL)
    }

    actual fun readPointer(offset: ULong): NativeAddress {
        val raw = segment().get(ValueLayout.ADDRESS, offset.toLong())
        return NativeAddress(if (raw == MemorySegment.NULL) 0L else raw.address())
    }

    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Byte.SIZE_BYTES)
    actual fun writeUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    actual fun readUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asByteArray()), arrayIndex, size, UByte.SIZE_BYTES)
    actual fun writeShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    actual fun readShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Short.SIZE_BYTES)
    actual fun writeUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    actual fun readUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asShortArray()), arrayIndex, size, UShort.SIZE_BYTES)
    actual fun writeInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    actual fun readInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Int.SIZE_BYTES)
    actual fun writeUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    actual fun readUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asIntArray()), arrayIndex, size, UInt.SIZE_BYTES)
    actual fun writeLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    actual fun readLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Long.SIZE_BYTES)
    actual fun writeULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    actual fun readULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array.asLongArray()), arrayIndex, size, ULong.SIZE_BYTES)
    actual fun writeFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    actual fun readFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Float.SIZE_BYTES)
    actual fun writeDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
    actual fun readDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(bufferOffset, MemorySegment.ofArray(array), arrayIndex, size, Double.SIZE_BYTES)
}
```

- [ ] **Step 4: Adapter MemoryAllocator JVM pour injecter le segment scopé**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt` :

```kotlin
package org.graphiks.kffi

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class MemoryAllocator : AutoCloseable {

    val arena = Arena.ofConfined()

    actual fun allocate(sizeInByte: Long): NativeAddress {
        return arena.allocate(sizeInByte).let { MemorySegment -> NativeAddress(MemorySegment.address()) }
    }

    actual override fun close() {
        arena.close()
    }

    actual fun bufferOf(value: Long): MemoryBuffer =
        arena.allocate(ValueLayout.JAVA_LONG)
            .also { it.set(ValueLayout.JAVA_LONG, 0, value) }
            .let { MemoryBuffer(NativeAddress(it.address()), Long.SIZE_BYTES.toULong(), it) }

    actual fun allocateFrom(value: String): CString =
        arena.allocateFrom(value)
            .let { segment -> CString(MemoryBuffer(NativeAddress(segment.address()), segment.byteSize().toULong(), segment).handler) }

    actual fun bufferOfAddress(value: NativeAddress): MemoryBuffer = bufferOf(value.rawValue)

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        arena.allocate(size.toLong())
            .let { segment -> MemoryBuffer(NativeAddress(segment.address()), size, segment) }

    actual fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer {
        val size = (Long.SIZE_BYTES * value.size).toULong()
        return allocateBuffer(size)
            .also { buffer -> value.forEachIndexed { index, pointer ->
                buffer.writePointer(pointer, (Long.SIZE_BYTES * index).toULong())
            }}
    }
}
```

Note : `MemoryBuffer` expose un constructeur interne `(handler, size, scopedSegment)` ; `allocateBuffer` et `bufferOf*` l'utilisent pour préserver le scope. `ArrayHolder` (commonMain) continue de construire `MemoryBuffer(handler, size)` sans scope — comportement inchangé pour les slices (accès brut documenté).

- [ ] **Step 5: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.MemoryBufferScopeJvmTest" --tests "org.graphiks.kffi.MemoryBufferArrayTest"`
Expected: PASS (scope + arrays).

- [ ] **Step 6: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryBuffer.jvm.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/MemoryAllocator.jvm.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/MemoryBufferScopeJvmTest.kt
git commit -m "refactor(kffi): JVM MemoryBuffer over raw addresses with I2-a scope retention"
```

### Task M1.4: CString et CallbackTokenAddressCodec JVM

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CString.jvm.kt`
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.jvm.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CStringJvmTest.kt` (créer)

- [ ] **Step 1: Écrire le test CString**

```kotlin
package org.graphiks.kffi

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CStringJvmTest {

    @Test
    fun `toKString reads from raw address`() {
        memoryScope { scope ->
            val string = scope.allocateFrom("kffi-p2")
            assertEquals("kffi-p2", string.toKString())
        }
    }

    @Test
    fun `null address yields null string`() {
        val string = CString(NativeAddress(0L))
        assertNull(string.toKString())
    }
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CStringJvmTest"`
Expected: FAIL — compilation (`handler.handler` n'existe plus).

- [ ] **Step 3: Réécrire CString JVM**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/CString.jvm.kt` :

```kotlin
package org.graphiks.kffi

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        val raw = handler.rawValue
        if (raw == 0L) return null
        return handler.toJvmSegment(Long.MAX_VALUE).getString(0)
    }

    actual fun toKString(size: ULong): String? {
        if (handler.rawValue == 0L) return null
        val bytes = ByteArray(size.toInt())
        handler.toJvmSegment(size.toLong()).asByteBuffer().get(bytes)
        return String(bytes)
    }
}
```

- [ ] **Step 4: Réécrire CallbackTokenAddressCodec JVM**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.jvm.kt` :

```kotlin
package org.graphiks.kffi

internal actual object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    actual override val pointerBits: Int = Long.SIZE_BITS
    actual override val maxToken: ULong = Long.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        return NativeAddress(token.toLong())
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val raw = address?.rawValue ?: return null
        if (raw == 0L) return null
        val token = raw.toULong()
        requireValidCallbackToken(token)
        return token
    }
}
```

- [ ] **Step 5: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.CStringJvmTest" --tests "org.graphiks.kffi.CallbackTokenAddressCodecJvmTest"`
Expected: PASS.

- [ ] **Step 6: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/CString.jvm.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.jvm.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/CStringJvmTest.kt
git commit -m "refactor(kffi): CString and callback token codec over raw addresses"
```

### Task M1.5: CStructure et FFI JVM sur adresses brutes

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt`
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/FFI.kt`

- [ ] **Step 1: Réécrire CStructure JVM (accès via segment à la demande)**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt` :

```kotlin
package org.graphiks.kffi

import java.lang.foreign.MemorySegment

private fun Boolean.toInt() = if (this) 1 else 0

interface CStructure {
    val handler: NativeAddress

    private fun segment(): MemorySegment = handler.toJvmSegment(Long.MAX_VALUE)

    fun getAddress(offset: Long): NativeAddress {
        val raw = segment().get(java.lang.foreign.ValueLayout.ADDRESS, offset)
        return NativeAddress(if (raw == MemorySegment.NULL) 0L else raw.address())
    }

    fun getUInt(offset: Long): UInt = segment().get(java.lang.foreign.ValueLayout.JAVA_INT, offset).toUInt()
    fun getInt(offset: Long): Int = segment().get(java.lang.foreign.ValueLayout.JAVA_INT, offset)
    fun getULong(offset: Long): ULong = segment().get(java.lang.foreign.ValueLayout.JAVA_LONG, offset).toULong()
    fun getUShort(offset: Long): UShort = segment().get(java.lang.foreign.ValueLayout.JAVA_SHORT, offset).toUShort()
    fun getShort(offset: Long): Short = segment().get(java.lang.foreign.ValueLayout.JAVA_SHORT, offset)
    fun getFloat(offset: Long): Float = segment().get(java.lang.foreign.ValueLayout.JAVA_FLOAT, offset)
    fun getDouble(offset: Long): Double = segment().get(java.lang.foreign.ValueLayout.JAVA_DOUBLE, offset)

    fun set(offset: Long, address: NativeAddress) {
        segment().set(java.lang.foreign.ValueLayout.ADDRESS, offset, address.toJvmSegmentOrNull() ?: MemorySegment.NULL)
    }

    fun set(offset: Long, value: UInt) = segment().set(java.lang.foreign.ValueLayout.JAVA_INT, offset, value.toInt())
    fun set(offset: Long, value: Int) = segment().set(java.lang.foreign.ValueLayout.JAVA_INT, offset, value)
    fun set(offset: Long, value: Boolean) = segment().set(java.lang.foreign.ValueLayout.JAVA_INT, offset, value.toInt())
    fun set(offset: Long, value: ULong) = segment().set(java.lang.foreign.ValueLayout.JAVA_LONG, offset, value.toLong())
    fun set(offset: Long, value: UShort) = segment().set(java.lang.foreign.ValueLayout.JAVA_SHORT, offset, value.toShort())
    fun set(offset: Long, value: Short) = segment().set(java.lang.foreign.ValueLayout.JAVA_SHORT, offset, value)
    fun set(offset: Long, value: Float) = segment().set(java.lang.foreign.ValueLayout.JAVA_FLOAT, offset, value)
    fun set(offset: Long, value: Double) = segment().set(java.lang.foreign.ValueLayout.JAVA_DOUBLE, offset, value)
}
```

- [ ] **Step 2: Adapter FFI.kt — findOrThrow retourne l'adresse brute**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/FFI.kt` :

```kotlin
package org.graphiks.kffi

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles

val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN
val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE
val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT
val C_INT: ValueLayout = ValueLayout.JAVA_INT
val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG
val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT
val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE
val C_POINTER: ValueLayout = ValueLayout.ADDRESS
val C_LONG: ValueLayout = ValueLayout.JAVA_LONG

private val SYMBOL_LOOKUP by lazy {
    SymbolLookup.loaderLookup()
        .or(Linker.nativeLinker().defaultLookup())
}

/** Résout un symbole et retourne son adresse brute ; lève `UnsatisfiedLinkError` si introuvable. */
fun findOrThrow(symbol: String): Long {
    return SYMBOL_LOOKUP.find(symbol)
        .orElseThrow { UnsatisfiedLinkError("unresolved symbol: $symbol") }
        .address()
}

internal fun upcallHandle(fi: Class<*>?, name: String?, fdesc: FunctionDescriptor): MethodHandle {
    try {
        return MethodHandles.lookup().findVirtual(fi, name, fdesc.toMethodType())
    } catch (ex: ReflectiveOperationException) {
        throw AssertionError(ex)
    }
}
```

- [ ] **Step 3: Vérifier la compilation du module kffi (rouge attendu sur callbacks JVM)**

Run: `./gradlew :kffi:compileKotlinJvm`
Expected: erreurs résiduelles dans `CallbackRuntime` JVM si trampolines FFM — traitées en M4 (JvmUpcallEngine). Les tests encore rouges sont acceptés jusqu'à M4 ; compiler `:kffi:jvmTestClasses` pour le diagnostic.

- [ ] **Step 4: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt \
        kffi/src/jvmMain/kotlin/org/graphiks/kffi/FFI.kt
git commit -m "refactor(kffi): CStructure and symbol resolution over raw addresses"
```

### Task M1.6: Réparer la suite kffi JVM existante (round 1)

**Files:**
- Modify: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackFfiJvmTest.kt`
- Modify: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackRuntimeJvmTest.kt`
- Modify: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackTokenAddressCodecJvmTest.kt`

- [ ] **Step 1: Migrer les usages `JvmNativeAddress(...)` → `NativeAddress(long)`**

Dans `CallbackFfiJvmTest.kt`, remplacer :
```kotlin
JvmNativeAddress(routingUserdata)
```
par :
```kotlin
NativeAddress(requireNotNull(routingUserdata).rawValue)
```
et `registration.callback.handler` / `registration.userdata.handler` par `registration.callback.rawValue` / `registration.userdata?.rawValue ?: 0L`.

Dans `CallbackTokenAddressCodecJvmTest.kt`, `encode(token).handler.address()` devient `encode(token).rawValue`, et :
- supprimer les références à `validatedJvmCallbackPointerBits` (helper supprimé en M1.4, lignes ~45/51) — remplacer par un test direct de `pointerBits == Long.SIZE_BITS` ;
- adapter l'assertion `decode(zéro)` : `decode(NativeAddress(0L))` retourne désormais `null` (0 = sentinelle null, aligné raw-address) au lieu de lever — M1.4 a changé ce comportement délibérément.

- [ ] **Step 2: Remplacer les références aux trampolines FFM par des addresses brutes**

Les `JvmFfiTrampolines.routedStub` (MemorySegment) deviennent `NativeAddress` construits une fois via `Linker.nativeLinker().upcallStub(...).address()`, en attendant M4 (le runtime reste dans le test pour le round 1) :

```kotlin
private object JvmFfiTrampolines {
    private val linker = Linker.nativeLinker()
    val routedStub: NativeAddress = NativeAddress(linker.upcallStub(
        MethodHandles.lookup().findStatic(JvmFfiTrampolines::class.java, "routedInvoke", ROUTED_DESCRIPTOR.toMethodType()),
        ROUTED_DESCRIPTOR,
        Arena.global(),
    ).address())
    // ... même motif pour retiredNoUserdataStub / rearmedNoUserdataStub
}
```

- [ ] **Step 3: Vérifier**

Run: `./gradlew :kffi:jvmTest`
Expected: 87+ tests verts (tous les tests non liés aux callbacks générés).

- [ ] **Step 4: Commit**

```bash
git add kffi/src/jvmTest/kotlin/org/graphiks/kffi/
git commit -m "test(kffi): migrate JVM tests to raw-address NativeAddress"
```

---

## Milestone M2 — Moteur downcall JVM typé par forme

`JvmDowncallEngine` : table de wrappers par forme de signature, symétrique `NativeEngine` Android, cachant les `MethodHandle` FFM `invokeExact`. Les adresses de fonctions et de pointeurs circulent en `Long`.

### Task M2.1: JvmDowncallEngine — formes scalaires/pointeurs

**Files:**
- Create: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/engine/JvmDowncallEngineTest.kt` (créer)

- [ ] **Step 1: Écrire le test avec des fixtures natives réelles**

Les fixtures C existent déjà : `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c` (avec `bench_empty`, `bench_add4`, `bench_add8`, `bench_roundtrip_ptr`, `bench_make_pair`/`bench_pair_sum` pour les structs par valeur) et le header `bench_fixture.h`. Le chargement est géré par `FixtureLoader` (chargé dans les benchmarks JMH) ; pour le test unitaire, charger la fixture via `System.load` sur la lib compilée par Gradle (tâche de compilation C du module `kffi-benchmark-jvm`, vérifier la task exacte par `./gradlew :kffi-benchmark-jvm:tasks | grep -i "fixture\|native"`).

```kotlin
package org.graphiks.kffi.engine

import org.graphiks.kffi.NativeAddress
import kotlin.test.Test
import kotlin.test.assertEquals

class JvmDowncallEngineTest {

    private fun symbol(name: String): Long =
        JvmDowncallEngine.resolveSymbol(name)

    @Test
    fun `empty returns 42`() {
        val result = JvmDowncallEngine.callI0(symbol("bench_empty"))
        assertEquals(42L, result)
    }

    @Test
    fun `add4 returns sum`() {
        val result = JvmDowncallEngine.callI4IIII(symbol("bench_add4"), 1, 2, 3, 4)
        assertEquals(10L, result)
    }

    @Test
    fun `add8 returns sum`() {
        val result = JvmDowncallEngine.callL8LLLLLLLL(symbol("bench_add8"), 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L)
        assertEquals(36L, result)
    }

    @Test
    fun `pointer round-trip via raw address`() {
        memoryScope { scope ->
            val buffer = scope.allocateBuffer(8u)
            buffer.writeLong(0xABCD, 0u)
            val returned = JvmDowncallEngine.callP1P(symbol("bench_roundtrip_ptr"), buffer.handler.rawValue)
            assertEquals(buffer.handler.rawValue, returned)
        }
    }
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.engine.JvmDowncallEngineTest"`
Expected: FAIL — `JvmDowncallEngine` n'existe pas.

- [ ] **Step 3: Implémenter le moteur**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt` :

```kotlin
package org.graphiks.kffi.engine

import org.graphiks.kffi.C_POINTER
import org.graphiks.kffi.findOrThrow
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/**
 * Moteur de downcall JVM typé par forme — symétrique de NativeEngine (Android).
 *
 * Chaque wrapper par forme cache un MethodHandle FFM (invokeExact) et convertit
 * les adresses brutes en segments éphémères à l'appel. Les formes couvertes sont
 * celles réellement référencées par les bindings générés (union des signatures
 * wgpu) — la table grandit par ajout de wrapper, jamais par combinatoire.
 */
object JvmDowncallEngine {

    private val linker = Linker.nativeLinker()

    fun resolveSymbol(name: String): Long = findOrThrow(name)

    private fun segment(address: Long): MemorySegment =
        MemorySegment.ofAddress(address)

    private fun handle(fn: Long, descriptor: FunctionDescriptor): MethodHandle =
        linker.downcallHandle(segment(fn), descriptor)

    // --- void returns ---

    fun callV0(fn: Long) {
        val handle = handle(fn, FunctionDescriptor.ofVoid())
        handle.invokeExact()
    }

    fun callV1P(fn: Long, p1: Long) {
        val handle = handle(fn, FunctionDescriptor.ofVoid(C_POINTER))
        handle.invokeExact(segment(p1))
    }

    fun callV2PP(fn: Long, p1: Long, p2: Long) {
        val handle = handle(fn, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER))
        handle.invokeExact(segment(p1), segment(p2))
    }

    fun callV3PPL(fn: Long, p1: Long, p2: Long, a3: Long) {
        val handle = handle(fn, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3)
    }

    fun callV4PPPP(fn: Long, p1: Long, p2: Long, p3: Long, p4: Long) {
        val handle = handle(fn, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_POINTER))
        handle.invokeExact(segment(p1), segment(p2), segment(p3), segment(p4))
    }

    fun callV5PIIII(fn: Long, p1: Long, a2: Int, a3: Int, a4: Int, a5: Int) {
        val handle = handle(fn, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), a2, a3, a4, a5)
    }

    // --- long returns ---

    fun callI0(fn: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG))
        return handle.invokeExact() as Long
    }

    fun callI1I(fn: Long, a1: Int): Long {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT))
        return handle.invokeExact(a1) as Long
    }

    fun callI1P(fn: Long, a1: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER))
        return handle.invokeExact(segment(a1)) as Long
    }

    fun callI4IIII(fn: Long, a1: Int, a2: Int, a3: Int, a4: Int): Long {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        return handle.invokeExact(a1, a2, a3, a4) as Long
    }

    fun callL8LLLLLLLL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long, a6: Long, a7: Long, a8: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        return handle.invokeExact(a1, a2, a3, a4, a5, a6, a7, a8) as Long
    }

    fun callP1P(fn: Long, a1: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(C_POINTER, C_POINTER))
        return (handle.invokeExact(segment(a1)) as MemorySegment).address()
    }

    fun callP2PP(fn: Long, a1: Long, a2: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(C_POINTER, C_POINTER, C_POINTER))
        return (handle.invokeExact(segment(a1), segment(a2)) as MemorySegment).address()
    }

    fun callP2PI(fn: Long, a1: Long, a2: Int): Long {
        val handle = handle(fn, FunctionDescriptor.of(C_POINTER, C_POINTER, ValueLayout.JAVA_INT))
        return (handle.invokeExact(segment(a1), a2) as MemorySegment).address()
    }

    fun callP3PLL(fn: Long, a1: Long, a2: Long, a3: Long): Long {
        val handle = handle(fn, FunctionDescriptor.of(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        return (handle.invokeExact(segment(a1), a2, a3) as MemorySegment).address()
    }

    fun callF1P(fn: Long, a1: Long): Float {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, C_POINTER))
        return handle.invokeExact(segment(a1)) as Float
    }

    fun callD1P(fn: Long, a1: Long): Double {
        val handle = handle(fn, FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, C_POINTER))
        return handle.invokeExact(segment(a1)) as Double
    }

    // --- struct-by-value : formes construites depuis le registre de layouts (M5.2bis) ---
}

Note : le calcul exact des formes nécessaires se fait par analyse des signatures wgpu (M5). M2 implémente la mécanique + les formes de la fixture ; M5 complète la table avec toutes les formes référencées (boucle : compiler wgpu_hJvm généré → erreur "forme manquante" → ajouter le wrapper).

- [ ] **Step 4: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.engine.JvmDowncallEngineTest"`
Expected: PASS (fixtures natives chargées via les resources du module).

- [ ] **Step 5: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/engine/JvmDowncallEngineTest.kt
git commit -m "feat(kffi): JVM downcall engine with typed per-shape wrappers"
```

### Task M2.2: Benchmark bake-off du moteur (smoke perf)

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt`

- [ ] **Step 1: Ajouter l'axe JvmDowncallEngine au bake-off**

Ajouter un état qui résout le symbole une fois puis mesure `JvmDowncallEngine.callI4IIII` vs `fmmExact` existant :

```kotlin
@State(Scope.Thread)
class EngineState {
    lateinit var emptyAddr: Long
    lateinit var add4Addr: Long

    @Setup
    fun setup() {
        emptyAddr = JvmDowncallEngine.resolveSymbol("empty")
        add4Addr = JvmDowncallEngine.resolveSymbol("add4")
    }
}

@Benchmark
fun jvmEngineAdd4(state: EngineState, blackhole: Blackhole): Unit =
    blackhole.consume(JvmDowncallEngine.callI4IIII(state.add4Addr, 1, 2, 3, 4))

@Benchmark
fun jvmEngineEmpty(state: EngineState, blackhole: Blackhole): Unit =
    JvmDowncallEngine.callV0(state.emptyAddr).let(blackhole::consume)
```

- [ ] **Step 2: Lancer le smoke (hors rapport officiel)**

Run: `./gradlew :kffi-benchmark-jvm:jmhJar` puis `java -jar kffi-benchmark-jvm/build/libs/kffi-benchmark-jvm-jmh.jar "DowncallEngineBakeoff" -f 1 -wi 3 -i 5`
Expected: valeurs ~25-40 ns/op pour les wrappers moteur ; le rapport officiel est produit en M6.

> **Résultat M2.2 (réalisé)** : sans cache, `jvmEngineAdd4` = 371.7 ns/op (cold path complet par appel) → **cache par (fn, shape) ajouté** (commit ca7b1e7b, clé Long zéro-alloc `(fn shl 8) or shapeId`, adresses page-aligned) → `jvmEngineAdd4` = **35.09 ± 0.40 ns/op**, `jvmEngineEmpty` = 29.05 ± 0.78 ns/op, `fmmExact` = 3.99. Critère P2 (≤ 1.5 × 26.54 ≈ 40 ns) respecté. Le cache est donc **mandatoire** (pas optionnel). Les symboles de la fixture sont `bench_empty`/`bench_add4` (pas `empty`/`add4` du sketch) ; `bench_empty` retourne 42 (pas void) → axe mesuré via `callI0`. Le fix `UpcallBenchmarks.kt` (adaptation API raw-address) était requis pour compiler le module.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt
git commit -m "bench(jvm): JvmDowncallEngine axis in downcall bake-off"
```

---

## Milestone M3 — Retours struct par valeur : paramètre allocator (API commune)

### Task M3.1: kextract — builder commun émet `allocator` sur les retours par valeur

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Test: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpAllocatorSignatureTest.kt` (créer)

- [ ] **Step 1: Écrire le test d'intégration (golden de signature)**

```kotlin
package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpAllocatorSignatureTest : FreeSpec({

    fun generate(header: String, sourceSet: String): String {
        val input = Files.createTempFile("kextract-alloc", ".h")
        val output = Files.createTempDirectory("kextract-alloc-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(targetPackage = "sample.bindings", outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.SUCCESS
            val root = output.resolve(sourceSet)
            Files.walk(root).use { paths ->
                paths.filter { it.fileName.toString().endsWith(".kt") }
                    .map { it.toFile().readText() }
                    .toList()
                    .joinToString("\n")
            }
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    val header = """
        typedef struct { int a; } Box;
        typedef struct { int a; int b; } Box2;
        Box makeBox(void);
        void consumeBox(Box b);
        Box2 makeBox2(int x);
    """.trimIndent()

    "common expect carries allocator on struct-by-value returns only" {
        val source = generate(header, "commonMain")
        source shouldContain "expect fun makeBox(allocator: MemoryAllocator): Box"
        source shouldContain "expect fun makeBox2(allocator: MemoryAllocator, x: Int): Box2"
        source shouldNotContain "allocator" // consumeBox n'en a pas
    }

    "android actual uses allocator for out buffer" {
        val source = generate(header, "androidMain")
        source shouldContain "actual fun makeBox(allocator: MemoryAllocator): Box"
        source shouldNotContain "MemoryAllocator().allocateBuffer" // plus d'allocation interne
    }
})
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpAllocatorSignatureTest"`
Expected: FAIL — `makeBox` n'a pas de paramètre `allocator`.

- [ ] **Step 3: Implémenter dans le builder commun**

Dans `KotlinKmpCommonBuilder.kt`, repérer l'émission des `expect fun` et ajouter un préfixe `allocator: MemoryAllocator, ` aux fonctions dont le type de retour est un struct par valeur. La détection doit être partagée : créer une fonction utilitaire dans `KmpTypeMapper` :

```kotlin
// KmpTypeMapper.kt — nouveau helper
fun returnsStructByValue(type: Type): Boolean = when {
    type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> false
    type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> returnsStructByValue(type.type())
    type is Type.Declared && isStructOrUnion(type) -> !isOpaqueHandle(type)
    else -> false
}
```

Dans l'émission des paramètres communs, préfixer :

```kotlin
val allocatorPrefix = if (typeMapper.returnsStructByValue(function.type().returnType())) {
    "allocator: $memoryAllocator, "
} else ""
builder.appendLine("expect fun ${name}(...): ...")
```

(Adapter au code réel du builder commun — l'émission des expect vit dans `KotlinKmpCommonBuilder`/`KotlinGenerator`.)

- [ ] **Step 4: Propager aux actuals Android**

`KotlinKmpAndroidBuilder.kt` : même préfixe sur l'`actual fun`, et remplacer l'allocation interne du out buffer par l'allocator de l'appelant dans `emitGenericDowncall` :

```kotlin
// avant : val out = $memoryAllocator().allocateBuffer(${outSize}uL)
// après : val out = allocator.allocateBuffer(${outSize}uL)
```
et le pack des arguments struct par valeur reste `allocateBuffer` interne (argument) — OK.

- [ ] **Step 5: Propager aux actuals JVM et Native**

`KotlinKmpJvmBuilder.kt` et `KotlinKmpNativeBuilder.kt` : préfixe `allocator` sur les mêmes fonctions (les actuals JVM/native seront réécrits en M5 ; ici on ajuste la signature pour compiler contre le common).

- [ ] **Step 6: Vérifier**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpAllocatorSignatureTest"`
Expected: PASS.

- [ ] **Step 7: Commit**

```bash
git add kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/ \
        kextract/src/main/kotlin/org/graphiks/kextract/kotlin/utils/TypeMapper.kt \
        kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpAllocatorSignatureTest.kt
git commit -m "feat(kextract): allocator parameter on struct-by-value return bindings (all source sets)"
```

### Task M3.2: Mettre à jour les callers wgpu existants (sites WGPUFuture)

**Files:**
- Modify: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/GeneratedCallbackJvmTest.kt`
- Modify: `wgpu4k-native/src/jvmTest/kotlin/io/ygdrasil/wgpu/CallbackInfoFactoryJvmTest.kt`
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt`

- [ ] **Step 1: Adapter les appels des 9 fonctions WGPUFuture**

Pattern pour chaque site (ex. `wgpuAdapterRequestDevice`) :

```kotlin
// avant :
wgpuAdapterRequestDevice(adapter, descriptor, callbackInfo)
// après :
memoryScope { scope ->
    wgpuAdapterRequestDevice(scope, adapter, descriptor, callbackInfo)
}
```

Vérifier par `rg -n "wgpuAdapterRequestDevice|wgpuBufferMapAsync|wgpuDeviceCreateComputePipelineAsync|wgpuDeviceCreateRenderPipelineAsync|wgpuDeviceGetLostFuture|wgpuDevicePopErrorScope|wgpuInstanceRequestAdapter|wgpuQueueOnSubmittedWorkDone|wgpuShaderModuleGetCompilationInfo" wgpu4k-native/src --type kotlin` que tous les sites sont couverts.

- [ ] **Step 2: Vérifier**

Run: `./gradlew :wgpu4k-native:jvmTest` (les tests appelant ces fonctions compilent avec le nouveau paramètre).
Expected: PASS ou rouge attendu uniquement sur les sites encore générés en ancien format (régénérés en M5).

- [ ] **Step 3: Commit**

```bash
git add wgpu4k-native/src/jvmTest wgpu4k-native/src/androidUnitTest wgpu4k-native/src/nativeTest kffi-benchmark-jvm/src/jmh
git commit -m "refactor(wgpu): pass explicit allocator at WGPUFuture-returning call sites"
```

---

## Milestone M4 — Upcalls JVM : JvmUpcallEngine (trampolines hors du code généré)

Les callbacks JVM générés n'utilisent plus `Linker.upcallStub`/`MethodHandles` : le runtime kffi fournit des trampolines par forme de signature, et le code généré ne référence que `JvmUpcallEngine`.

### Task M4.1: JvmUpcallEngine — trampolines par forme

**Files:**
- Create: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmUpcallEngine.kt`
- Test: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/engine/JvmUpcallEngineTest.kt` (créer)

- [ ] **Step 1: Écrire le test**

```kotlin
package org.graphiks.kffi.engine

import org.graphiks.kffi.NativeAddress
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JvmUpcallEngineTest {

    @Test
    fun `V2PP trampoline routes invocation to handler`() {
        var calls = 0
        val stub = JvmUpcallEngine.trampolineV2PP { a1, a2 ->
            calls += 1
            assertEquals(0x1111L, a1)
            assertEquals(0x2222L, a2)
        }
        assertTrue(stub.rawValue > 0L)
        // Déclenchement : le moteur de test appelle le stub via downcallHandle FFM.
        // En pratique, le natif (fixture C) invoque le trampoline.
        invokeV2PP(stub.rawValue, 0x1111L, 0x2222L)
        assertEquals(1, calls)
    }
}

private fun invokeV2PP(stub: Long, a1: Long, a2: Long) {
    val linker = Linker.nativeLinker()
    val handle = linker.downcallHandle(
        MemorySegment.ofAddress(stub),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )
    handle.invokeExact(MemorySegment.ofAddress(a1), MemorySegment.ofAddress(a2))
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.engine.JvmUpcallEngineTest"`
Expected: FAIL — `JvmUpcallEngine` n'existe pas.

- [ ] **Step 3: Implémenter le moteur**

`kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmUpcallEngine.kt` :

```kotlin
package org.graphiks.kffi.engine

import org.graphiks.kffi.NativeAddress
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles

/**
 * Moteur d'upcall JVM : trampolines par forme de signature.
 *
 * Chaque forme expose une cible statique (`invoke`) appelée par le natif ; le
 * routage par token reste la responsabilité de CallbackRuntime (userdata =
 * token encodé). Le code généré par kextract ne référence que ces méthodes —
 * jamais Linker/MethodHandles/FunctionDescriptor.
 */
object JvmUpcallEngine {

    private val linker = Linker.nativeLinker()
    private val arena = Arena.global()

    // --- forme V2PP : void (ptr, ptr) ---

    @JvmStatic
    fun trampolineV2PP(handler: (Long, Long) -> Unit): NativeAddress {
        val stub = linker.upcallStub(
            MethodHandles.lookup().findStatic(
                JvmUpcallEngine::class.java,
                "invokeV2PP",
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS).toMethodType(),
            ),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        return NativeAddress(stub.address())
    }

    @JvmStatic
    @Suppress("UNUSED_PARAMETER")
    private fun invokeV2PP(userdata: MemorySegment, a1: MemorySegment, a2: MemorySegment) {
        // Le userdata porte le token ; CallbackRuntime.dispatchSafely route.
        // Le corps réel est fourni par les bindings générés via un callback enregistré.
    }
}
```

Note d'implémentation : le couplage exact trampoline → callback Kotlin passe par `CallbackRuntime` (token dans le userdata). Le `handler` en paramètre ci-dessus est la forme API pour les tests ; la variante générée (M5) enregistre un `CallbackRegistration` via `CallbackRuntime.register(type, trampoline, policy, onError, callback)` — le stub reçoit `(userdata, args...)` et le moteur route par token. Pour éviter un double dispatch, la forme produite par kextract embarque le token dans le premier argument (userdata = token → dispatch direct, comme Android).

- [ ] **Step 4: Vérifier**

Run: `./gradlew :kffi:jvmTest --tests "org.graphiks.kffi.engine.JvmUpcallEngineTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmUpcallEngine.kt \
        kffi/src/jvmTest/kotlin/org/graphiks/kffi/engine/JvmUpcallEngineTest.kt
git commit -m "feat(kffi): JVM upcall engine with per-shape trampolines"
```

### Task M4.2: kextract — KotlinCallbackJvmEmitter contre JvmUpcallEngine

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt` (symboles runtime JVM engine)

- [ ] **Step 1: Ajouter les symboles runtime**

Dans `KotlinKmpNamePlan.kt`, enum `KotlinKmpRuntimeSymbol` :

```kotlin
JVM_DOWNCALL_ENGINE("org.graphiks.kffi.engine.JvmDowncallEngine", jvm()),
JVM_UPCALL_ENGINE("org.graphiks.kffi.engine.JvmUpcallEngine", jvm()),
```

- [ ] **Step 2: Réécrire l'émission des trampolines**

Dans `KotlinCallbackJvmEmitter.emitTrampoline`, remplacer le bloc `Linker.upcallStub(...)` par un appel au moteur. La forme est dérivée des paramètres bruts du callback :

```kotlin
private fun trampolineForm(rawParameters: List<KotlinCallbackParameter>): String {
    // V + lettres par paramètre (P = adresse, I = int, L = long, ...)
    val letters = rawParameters.joinToString("") { param ->
        when (param.cAbiType) {
            is KotlinKmpCAbiType.Address -> "P"
            is KotlinKmpCAbiType.Scalar -> when (param.cAbiType.kind) {
                KotlinKmpCAbiType.Scalar.Kind.I32, KotlinKmpCAbiType.Scalar.Kind.BOOL -> "I"
                KotlinKmpCAbiType.Scalar.Kind.I64 -> "L"
                KotlinKmpCAbiType.Scalar.Kind.F32 -> "F"
                KotlinKmpCAbiType.Scalar.Kind.F64 -> "D"
                else -> error("Unsupported upcall carrier ${param.cAbiType.kind}")
            }
            is KotlinKmpCAbiType.StructValue -> error("Struct-by-value upcall arguments are not supported")
        }
    }
    return "V$letters"
}
```

L'objet trampoline généré devient :

```kotlin
builder.appendLine("private object ${callback.trampolineName} {")
builder.indent()
builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
builder.indent()
builder.appendLine("${namePlan.runtime(JVM_UPCALL_ENGINE)}.trampoline$form { args ->")
builder.indent()
// ... dispatchSafely avec token extrait du userdata (1er argument)
builder.unindent()
builder.appendLine("}")
builder.unindent()
builder.appendLine("}")
builder.unindent()
builder.appendLine("}")
```

Le dispatch reprend la structure actuelle (`dispatchSafely(type, userdata = ...) { callback -> callback.invoke(...) }`) ; le premier paramètre brut (routing userdata) est consommé par le routage, pas passé au callback Kotlin.

- [ ] **Step 3: Vérifier la régénération sur la fixture callback**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest"`
Expected: PASS (goldens mis à jour).

- [ ] **Step 4: Commit**

```bash
git add kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt \
        kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt
git commit -m "refactor(kextract): JVM callback trampolines via JvmUpcallEngine"
```

### Task M4.3: Adapter le runtime callbacks JVM aux trampolines (suppression FFM du test)

**Files:**
- Modify: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackFfiJvmTest.kt`
- Modify: `kffi/src/jvmTest/kotlin/org/graphiks/kffi/CallbackRuntimeJvmTest.kt`

- [ ] **Step 1: Remplacer les stubs FFM des tests par des trampolines JvmUpcallEngine**

`JvmFfiTrampolines.routedStub` etc. deviennent :

```kotlin
private object JvmFfiTrampolines {
    val routedStub: NativeAddress = JvmUpcallEngine.trampolineV2PP { _, _ -> }
    // ...
}
```

- [ ] **Step 2: Vérifier**

Run: `./gradlew :kffi:jvmTest`
Expected: 87+ tests verts (les callbacks JVM complets passent via le moteur).

- [ ] **Step 3: Commit**

```bash
git add kffi/src/jvmTest/kotlin/org/graphiks/kffi/
git commit -m "test(kffi): JVM callback tests through JvmUpcallEngine trampolines"
```

---

## Milestone M5 — kextract : builder JVM memory-backed + régénération wgpu

Le `KotlinKmpJvmBuilder` est réécrit sur le modèle Android : structs `ByReference`/`ByValue` memory-backed via `MemoryBuffer`, downcalls via `JvmDowncallEngine`, aucune référence FFM dans le code généré.

### Task M5.1: Réécrire l'émission des structs (memory-backed)

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Test: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmMemoryBackedAbiTest.kt` (créer, migré depuis KmpJvmFfmAbiTest)

- [ ] **Step 1: Migrer le test FFM vers memory-backed**

Créer `KmpJvmMemoryBackedAbiTest.kt` en reprenant les scénarios de `KmpJvmFfmAbiTest` (union layout, tail padding, WGPUNativeDisplayHandle) mais en vérifiant le nouveau shape :

```kotlin
"generated JVM struct is memory-backed (no java.lang.foreign)" {
    val source = generateJvm(header)
    source shouldContain "class ByReference(val handle: NativeAddress = NativeAddress(0L)) : Box"
    source shouldContain "private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, ${size}uL) }"
    source shouldNotContain "java.lang.foreign"
    source shouldNotContain "VarHandle"
    source shouldNotContain "MethodHandle"
}
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpJvmMemoryBackedAbiTest"`
Expected: FAIL — le généré actuel contient `java.lang.foreign`.

- [ ] **Step 3: Réécrire l'émission des structs dans KotlinKmpJvmBuilder**

Aligner `visitScoped` (branch STRUCT/UNION) sur `KotlinKmpAndroidBuilder.emitMemoryRecordImpl` :

- Interface : `actual interface $structName { actual var $field: $type; actual val handler: NativeAddress }` (plus de `: CStructure`).
- Companion : `invoke(address)`, `allocate(allocator)`, `allocateArray(allocator, size, provider)` — formes Android (offsets du `KotlinJvmRecordLayout` → taille en octets).
- `ByReference`/`ByValue` : classes avec `private val buffer: MemoryBuffer by lazy { MemoryBuffer(handle, ${size}uL) }` et accesseurs `buffer.readX(offset)` / `buffer.writeX(value, offset)` par champ (réutiliser les helpers `memoryPrimitives`/`enumMemoryPrimitives` du builder Android — les extraire dans un fichier partagé `MemoryFieldAccessors.kt` si besoin).
- Champs struct imbriqués : getter = `$type.ByValue(NativeAddress(handle.rawValue + offset))`, setter = copie de bytes via `ByteArray` (pattern Android).
- `WGPUNativeDisplayHandle` : émettre selon le pattern Android `emitNativeDisplayHandleImpl`.
- `actual interface $structName : $cStructure` → supprimer `CStructure` de l'API générée JVM (le commonMain ne la référence pas).

- [ ] **Step 4: Vérifier**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpJvmMemoryBackedAbiTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt \
        kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmMemoryBackedAbiTest.kt
git commit -m "refactor(kextract): JVM structs memory-backed via MemoryBuffer"
```

### Task M5.2bis: Registre de layouts struct + wrappers struct-by-value du moteur

Le layout FFM des structs par valeur (arg/return) est construit **dans le moteur** depuis des métadonnées enregistrées par le code généré — jamais de `MemoryLayout` dans le code généré. Le squelette `registerStructLayout`/`structLayout` est déjà posé en M2.1 ; cette tâche le complète (alignement, champs imbriqués, padding exact) et ajoute les wrappers struct-by-value.

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Test: `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmStructByValueTest.kt` (créer)

- [ ] **Step 1: Écrire le test (golden compile+probe)**

```kotlin
package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.graphiks.kextract.pipeline.KextractTool
import org.graphiks.kextract.pipeline.Logger
import org.graphiks.kextract.pipeline.Options
import java.nio.file.Files

class KmpJvmStructByValueTest : FreeSpec({

    fun generateJvm(header: String): String {
        val input = Files.createTempFile("kextract-jvm-struct", ".h")
        val output = Files.createTempDirectory("kextract-jvm-struct-out")
        return try {
            input.toFile().writeText(header)
            KextractTool(Logger.DEFAULT).runGeneration(
                listOf(input.toString()),
                Options(targetPackage = "sample.bindings", outputDir = output.toString(), multiplatform = true),
            ) shouldBe KextractTool.SUCCESS
            Files.walk(output.resolve("jvmMain")).use { paths ->
                paths.filter { it.fileName.toString().endsWith(".kt") }
                    .map { it.toFile().readText() }
                    .toList()
                    .joinToString("\n")
            }
        } finally {
            input.toFile().delete()
            output.toFile().deleteRecursively()
        }
    }

    "struct-by-value arg and return register layouts and call engine wrappers" {
        val source = generateJvm(
            """
            typedef struct { int a; int b; } Box;
            Box makeBox(int x);
            void consumeBox(Box b);
            """.trimIndent(),
        )
        source shouldContain "JvmDowncallEngine.registerStructLayout"
        source shouldContain "JvmDowncallEngine.callStructReturnBox"
        source shouldContain "JvmDowncallEngine.callStructArgBox"
        source shouldNotContain "MemoryLayout"
        source shouldNotContain "FunctionDescriptor"
    }
})
```

- [ ] **Step 2: Vérifier qu'il échoue**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpJvmStructByValueTest"`
Expected: FAIL — les wrappers n'existent pas.

- [ ] **Step 3: Compléter le registre dans JvmDowncallEngine**

Le squelette `StructField`/`FieldKind`/`structLayouts` est déjà dans le plan M2.1. Compléter `structLayout` pour l'alignement réel, le padding, et les champs struct imbriqués :

```kotlin
// JvmDowncallEngine.kt — complément M5.2bis
private val structAlignments = java.util.concurrent.ConcurrentHashMap<String, Long>()

fun registerStructLayout(name: String, sizeBytes: Long, alignmentBytes: Long, fields: List<StructField>) {
    structLayouts[name] = sizeBytes to fields
    structAlignments[name] = alignmentBytes
    structDescriptors.remove(name)
}

internal fun structLayout(name: String): MemoryLayout {
    return structDescriptors.computeIfAbsent(name) { structName ->
        val (size, fields) = structLayouts.getValue(structName)
        val elements = fields.map { field ->
            when (field.kind) {
                FieldKind.PADDING -> java.lang.foreign.MemoryLayout.paddingLayout(field.offsetBytes)
                FieldKind.STRUCT -> structLayout(resolveStructName(field.cName))
                else -> primitiveLayout(field.kind).withName(field.cName)
            }
        }
        java.lang.foreign.MemoryLayout.structLayout(*elements.toTypedArray())
            .withByteAlignment(structAlignments.getValue(structName))
    }
}
```

> **Notes de review M2.1** : (1) `structLayout` retourne `MemoryLayout` (GroupLayout), pas `ValueLayout` — le squelette M2.1 a été corrigé en conséquence ; visibilité `internal` requise pour les wrappers struct-by-value. (2) Les métadonnées `StructField` portent un `offsetBytes` qui sert à dériver le padding — kextract émet un champ `PADDING` explicite par écart > 0 (comme `structLayoutElements` avec `paddingLayout`), et `paddingLayout(offsetBytes)` du squelette est à interpréter comme la TAILLE du padding (corriger la sémantique dans l'implémentation M5.2bis : émettre `paddingLayout(gap)` avec gap = écart entre champs). (3) La table des formes complètes (callStructArg<Name>/callStructReturn<Name> par struct, y compris `reinterpret(layout.byteSize())` sur le segment d'argument) est ajoutée ici.

- [ ] **Step 4: Ajouter les wrappers struct-by-value**

```kotlin
// JvmDowncallEngine.kt — wrappers générés par forme par struct

fun callStructArgBox(fn: Long, structPtr: Long, a2: Long): Long {
    val layout = structLayout("Box")
    val handle = handle(fn, FunctionDescriptor.of(C_POINTER, layout, C_POINTER))
    val structSegment = segment(structPtr).reinterpret(layout.byteSize())
    return (handle.invokeExact(structSegment, segment(a2)) as MemorySegment).address()
}

fun callStructReturnBox(fn: Long, allocator: MemoryAllocator, a1: Long): NativeAddress {
    val layout = structLayout("Box")
    val handle = handle(fn, FunctionDescriptor.of(layout, C_POINTER))
    val segmentAllocator = allocator.arena
    val result = handle.invokeExact(segmentAllocator, segment(a1)) as MemorySegment
    return NativeAddress(result.address())
}
```

Note : `handle.invokeExact` avec un retour struct exige que le premier argument du MethodHandle soit un `SegmentAllocator` (convention FFM) ; `MemoryAllocator.arena` est un `Arena` qui implémente `SegmentAllocator`. Les wrappers par struct sont nommés `callStructArg<Name>`/`callStructReturn<Name>` — kextract émet le nom depuis le type de retour/d'argument du struct.

- [ ] **Step 5: kextract émet l'enregistrement des layouts**

Dans `KotlinKmpJvmBuilder` (branch STRUCT/UNION), émettre à la place de `emitGroupLayout`/`Companion.layout` :

```kotlin
builder.appendLine("init {")
builder.indent()
builder.appendLine("$jvmDowncallEngine.registerStructLayout(")
builder.indent()
builder.appendLine("\"$structName\",")
builder.appendLine("${layout.sizeBytes}L, ${layout.alignmentBytes}L,")
builder.appendLine("listOf(")
builder.indent()
// un StructField par champ : (cName, kind, offsetBytes)
layout.members.forEach { member ->
    val kind = when (member.layoutExpression) {
        else -> "JvmDowncallEngine.FieldKind.${fieldKindOf(member)}"
    }
    builder.appendLine("JvmDowncallEngine.StructField(\"${member.cName}\", $kind, ${member.offsetBytes}L),")
}
builder.unindent()
builder.appendLine(")")
builder.unindent()
builder.appendLine(")")
builder.unindent()
builder.appendLine("}")
```

où `fieldKindOf(member)` mappe le type du champ vers `FieldKind` (INT32 pour `int`, POINTER pour pointeurs, STRUCT pour champs struct imbriqués, PADDING pour le padding explicite). Le padding entre champs est dérivé des offsets (`offsetBytes` cumulés) — émettre un `StructField("__pad", PADDING, gap)` pour chaque écart > 0, comme `structLayoutElements` le fait avec `paddingLayout`.

- [ ] **Step 6: Vérifier**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.KmpJvmStructByValueTest"`
Expected: PASS.

- [ ] **Step 7: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt \
        kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt \
        kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpJvmStructByValueTest.kt
git commit -m "feat(kextract): JVM struct-by-value via engine layout registry"
```

### Task M5.2: Réécrire l'émission des fonctions (downcalls JvmDowncallEngine)

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`

- [ ] **Step 1: Émettre ADDR en Long + appels moteur**

Remplacer le bloc DESC/ADDR/HANDLE/invokeExact par :

```kotlin
private val ${name}_ADDR: Long by lazy { ${bootstrapOrFind}.resolve("$cName") }
actual fun $name(${params}) : $returnType {
    return ${engineCall}
}
```

où `engineCall` est émis selon les mêmes règles que `KotlinKmpAndroidBuilder.emitEngineDowncall` :
- `wrapperForm(type)` : `call<Ret><N><Args>` avec lettres I/P/L/F/D/S/B ;
- formes struct par valeur (arg ou return) : wrapper dédié construit depuis le registre de layouts (Task M5.2bis) — jamais `MemorySegment` dans le généré.
- `toEngineArgument` : `name?.handler?.rawValue ?: 0L` / `name.rawValue` / conversions enum (copier les helpers du builder Android).

Résolution du symbole : `KextractNativeBootstrap.resolve` retourne désormais `Long` (retour de `findOrThrow`), le `nativeBootstrapName.resolve` du bootstrap JVM est ajusté en conséquence (`KotlinJvmNativeBootstrapEmitter` — retour `Long`).

- [ ] **Step 2: Supprimer les symboles FFM du plan de noms JVM**

Dans `KotlinKmpNamePlan.kt`, marquer `ARENA`, `FUNCTION_DESCRIPTOR`, `GROUP_LAYOUT`, `LINKER`, `MEMORY_LAYOUT`, `MEMORY_SEGMENT`, `SEGMENT_ALLOCATOR`, `VALUE_LAYOUT`, `METHOD_HANDLE`, `METHOD_HANDLES`, `VAR_HANDLE`, `GROUP_ELEMENT` comme JVM-only retirés — les supprimer de l'énum si plus aucun builder ne les référence, sinon les laisser pour compat le temps de la transition, puis supprimer à la fin de M5.

- [ ] **Step 3: Vérifier les goldens**

Run: `./gradlew :kextract:test --tests "org.graphiks.kextract.integration.*Jvm*" --tests "org.graphiks.kextract.integration.KmpJvmPackedLayoutTest"`
Expected: PASS.

- [ ] **Step 4: Commit**

```bash
git add kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt \
        kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt
git commit -m "refactor(kextract): JVM downcalls via JvmDowncallEngine (no FFM in generated code)"
```

### Task M5.3: Compléter la table des formes du moteur (union des signatures wgpu)

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt`

- [ ] **Step 1: Extraire l'union des formes wgpu**

Run:
```bash
rg -o "invokeExact\([^)]*\)" wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt | sort | uniq -c | sort -rn
```
(expected: ~40 formes scalaires + struct par valeur). Mapper chaque forme à un wrapper manquant.

- [ ] **Step 2: Boucle : régénérer → compiler → ajouter la forme manquante**

1. Régénérer : `./gradlew :wgpu4k-native:generateBindingsFromHeader` (ou la task documentée en `wgpu4k-native/build.gradle.kts:361`).
2. Compiler : `./gradlew :wgpu4k-native:compileKotlinJvm`.
3. Chaque erreur "unresolved reference callX..." → ajouter le wrapper dans `JvmDowncallEngine` (même pattern que M2.1).
4. Répéter jusqu'à zéro erreur.

- [ ] **Step 3: Vérifier la non-régression perf (smoke)**

Run: le smoke du bake-off de M2.2 (forme représentative `callI4IIII`).
Expected: ≤ 1.5 × baseline P0.

- [ ] **Step 4: Commit**

```bash
git add kffi/src/jvmMain/kotlin/org/graphiks/kffi/engine/JvmDowncallEngine.kt
git commit -m "feat(kffi): complete JvmDowncallEngine shape table from wgpu signatures"
```

### Task M5.4: Régénérer wgpu et vérifier l'absence de fuites FFM

**Files:**
- Modify: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt` (régénéré)
- Modify: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt` (régénéré)
- Modify: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt` (régénéré)
- Modify: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt` (régénéré)

- [ ] **Step 1: Régénérer les bindings**

Run: `./gradlew :wgpu4k-native:generateBindingsFromHeader`
Expected: les 4 fichiers `wgpu_h*.kt` régénérés.

- [ ] **Step 2: Vérifier le critère zéro-fuite**

Run:
```bash
rg -n "Arena\.ofAuto|MemorySegment|MethodHandle|ValueLayout|FunctionDescriptor|java\.lang\.foreign|\.handler\.handler|VarHandle" wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt
```
Expected: aucune occurrence.

- [ ] **Step 3: Compiler les 3 backends**

Run: `./gradlew :wgpu4k-native:compileKotlinJvm :wgpu4k-native:compileDebugKotlinAndroid :wgpu4k-native:compileKotlinNative` (adapté aux targets du module)
Expected: compilent. Les erreurs natives résiduelles éventuelles sont dues aux signatures `allocator` (traitées en M3) — vérifier l'alignement des actuals.

- [ ] **Step 4: Lancer les tests wgpu JVM**

Run: `./gradlew :wgpu4k-native:jvmTest`
Expected: PASS (87+ tests de la suite kffi + tests wgpu).

- [ ] **Step 5: Commit**

```bash
git add wgpu4k-native/src
git commit -m "chore(wgpu): regenerate bindings against kffi P2 API (raw addresses, allocator, JvmEngine)"
```

### Task M5.5: Supprimer le code mort FFM résiduel (kffi jvmMain)

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt`
- Modify: `kffi/src/jvmMain/kotlin/org/graphiks/kffi/FFI.kt`

- [ ] **Step 1: Auditer les usages restants**

Run:
```bash
rg -n "java\.lang\.foreign|MethodHandle|Linker" kffi/src/jvmMain --type kotlin
```
Expected: les seuls fichiers légitimes sont `JvmConversions.kt`, `JvmDowncallEngine.kt`, `JvmUpcallEngine.kt`, `MemoryBuffer.jvm.kt`, `MemoryAllocator.jvm.kt`, `CString.jvm.kt`, `FFI.kt`.

- [ ] **Step 2: Supprimer `CStructure` JVM si plus référencé**

Si le généré n'implémente plus `CStructure` et qu'aucun test ne l'utilise : supprimer `kffi/src/jvmMain/kotlin/org/graphiks/kffi/CStructure.kt` et le symbole `C_STRUCTURE` du plan de noms kextract.

- [ ] **Step 3: Vérifier**

Run: `./gradlew :kffi:jvmTest :wgpu4k-native:jvmTest`
Expected: verts.

- [ ] **Step 4: Commit**

```bash
git add kffi/src kextract/src
git commit -m "chore(kffi): drop residual FFM exposure from JVM public surface"
```

---

## Milestone M6 — Re-baseline JVM + vérification finale

### Task M6.1: Adapter les benchmarks JVM aux nouvelles signatures

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt`
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt`
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/ArenaBenchmarks.kt`
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt`

- [ ] **Step 1: Migrer les fixtures struct par valeur vers `allocator`**

Les scénarios `struct_by_value_return`/`struct_by_value_arg` passent l'allocator :

```kotlin
@Benchmark
fun structByValueReturn(state: FixtureState, blackhole: Blackhole) {
    memoryScope { scope ->
        blackhole.consume(fixture.structByValueReturn(scope))
    }
}
```

- [ ] **Step 2: Vérifier la compilation des benchmarks**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh
git commit -m "refactor(bench): JVM benchmarks on P2 signatures"
```

### Task M6.2: Produire le rapport de baseline versionné

**Files:**
- Create: `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.md` (généré)
- Create: `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.json` (généré)

- [ ] **Step 1: Lancer la suite complète JMH**

Run: `./gradlew :kffi-benchmark-jvm:jmhJar` puis `java -jar kffi-benchmark-jvm/build/libs/kffi-benchmark-jvm-jmh.jar` (mêmes paramètres que P0 : average time, warmup + itérations configurées).
Expected: rapport JMH complet.

- [ ] **Step 2: Générer le markdown et le json versionné**

Utiliser `JmhJsonToMarkdown` existant ; nommer avec date + sha :
```bash
SHA=$(git rev-parse --short HEAD)
cp build/reports/jmh/result.json kffi/benchmarks/results/$(date +%Y-%m-%d)-$SHA-jvm-baseline.json
# générer le md via la task existante
```

- [ ] **Step 3: Analyser les Δ vs baseline P0**

Comparer avec `2026-08-12-012b50e9-jvm-baseline.md` :
- downcall.empty/add4/add8 : Δ attendu ≤ +50% (overhead `ofAddress` éphémère) ;
- struct_by_value_* : attendu neutre ou meilleur (fin d'`Arena.ofAuto()`, arène de l'appelant) ;
- upcall.* : attendu neutre (même mécanisme, trampolines par forme) ;
- marshaling.* : attendu meilleur ou égal (moins d'allocations de segments dupliqués).

Si `downcall.add4` > 1.5 × 26.54 ns : ouvrir une optimisation (cache de segments par forme avec adresses réutilisées, ou `MethodHandles.filterArguments` pour convertir long→segment hors hot path) — à documenter dans le rapport.

- [ ] **Step 4: Commit**

```bash
git add kffi/benchmarks/results
git commit -m "bench(jvm): P2 re-baseline report"
```

### Task M6.3: Vérification finale + état de branche

- [ ] **Step 1: Confirmer tous les invariants P0/P1**

Run: `./gradlew :kffi:jvmTest` → 87+ tests, 0 failures. `:kffi-benchmark-native:runBenchmarkNative` produit son rapport. Tests Android instrumentés non régressés (`:kffi:connectedDebugAndroidTest` si device disponible).

- [ ] **Step 2: Confirmer les critères P2**

- `rg "Arena\.ofAuto|MemorySegment|MethodHandle|ValueLayout|FunctionDescriptor|java\.lang\.foreign|\.handler\.handler|VarHandle" wgpu4k-native/src/jvmMain` → zéro occurrence.
- `rg "com\.sun\.jna" kffi wgpu4k-native demo` → zéro (Kadre exclu) — inchangé.
- Rapport `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` présent.

- [ ] **Step 3: Report**

Résumer : milestones M1-M6, Δ perf JVM vs P0, dette restante (formes moteur hors table → chemin générique si le fallback existe, upcalls struct-by-value non supportés, scope des buffers bruts documenté), handover P3 (sûreté mémoire unifiée).

---

## Out of scope (follow-up)

- **P3** — sûreté mémoire unifiée : bornes-check uniformes sur les 3 backends, opt-in `unsafe` par allocateur (I3), re-baseline native.
- **P4** — optimisation callback runtime (table d'index par token, zéro-allocation par dispatch).
- **P5** — kextract générique, release `org.graphiks:kffi-*`, migration finale vers `Graphiks-org/kffi`.
- Fallback JVM hors table (signatures exotiques non couvertes par `JvmDowncallEngine`) : documenter le refus à la génération (politique C2) — si nécessaire en P3.
