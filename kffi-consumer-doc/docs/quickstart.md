# Quickstart — consommer kffi de bout en bout

Ce guide couvre : dépendance Gradle, `memoryScope`, allocation, write/read,
`CString`, durée de vie, option `unsafe`, callback simple, chargement d'une
bibliothèque native. Tous les symboles utilisés correspondent à l'API publique
`org.graphiks.kffi` (voir le [README](../README.md) pour le contrat complet).

## 1. Dépendance

Le groupe est `org.graphiks`. Les snapshots sont sur le dépôt Sonatype, les
releases sur Maven Central :

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

> **Note M2.4** : la coordonnée snapshot finale est `1.0.0-SNAPSHOT` —
> effective après la migration M2.4 (versionnement indépendant du module kffi).
> Avant cette migration, les snapshots publiés sont `v29.0.0-<timestamp>-SNAPSHOT`
> (dépôt Sonatype).

```kotlin
// build.gradle.kts — projet KMP : l'artifact racine résout la variante plateforme
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("org.graphiks:kffi:1.0.0-SNAPSHOT") // release : "1.0.0"
        }
    }
}
```

Projet JVM seul : `implementation("org.graphiks:kffi-jvm:1.0.0-SNAPSHOT")`
(JDK 24+ pour `java.lang.foreign`).
Projet Android : `implementation("org.graphiks:kffi-android:1.0.0-SNAPSHOT")`
(minSdk 28).
Projet natif : l'artifact racine `org.graphiks:kffi` suffit — Gradle résout la
variante `kffi-<cible>` (ex. `kffi-macosarm64`). Il n'existe pas d'artifact
agrégé « kffi-native ».

## 2. `memoryScope`, allocation, write/read

`memoryScope { }` crée une arène confinée et garantit sa fermeture en fin de
bloc (équivalent `use { }`) :

```kotlin
import org.graphiks.kffi.*

memoryScope { allocator ->
    val buffer = allocator.allocateBuffer(size = 16uL)

    buffer.writeInt(value = 42, offset = 0uL)
    check(buffer.readInt(offset = 0uL) == 42)

    buffer.writeLong(value = 0xCAFEL, offset = 8uL)
    check(buffer.readLong(offset = 8uL) == 0xCAFEL)

    // Bornes-check actifs par défaut : tout accès hors `size` lève
    // IndexOutOfBoundsException (offset/largeur/taille dans le message).
    buffer.readLong(offset = 12uL)
    // IndexOutOfBoundsException : MemoryBuffer access out of bounds: offset=12 width=8 size=16
}
// allocator.close() est garanti ici — tout buffer issu de ce scope est invalide.
```

## 3. Tableaux et chaînes

```kotlin
memoryScope { allocator ->
    val buffer = allocator.allocateBuffer(size = 32uL)

    // Tableaux : index de départ dans le tableau + offset dans le buffer
    val out = IntArray(4)
    buffer.writeInts(intArrayOf(1, 2, 3, 4), bufferOffset = 0uL)
    buffer.readInts(out, bufferOffset = 0uL)
    check(out.contentEquals(intArrayOf(1, 2, 3, 4)))

    // Chaînes : allocation C (UTF-8, terminée par \0) depuis le scope
    val cstr = allocator.allocateFrom("bonjour")
    check(cstr.toKString() == "bonjour")
}
```

## 4. Durée de vie

Le scope d'arène vit dans le `MemoryBuffer` (décision I2-a) :

- buffer issu d'un `MemoryAllocator` : accès après `close()` →
  `IllegalStateException` (JVM) ;
- buffer construit depuis une adresse brute : **aucune garde** — l'accès après
  libération de la zone est un **comportement indéfini** (UB documenté, aligné
  sur les trois backends) ;

```kotlin
val allocator = MemoryAllocator()
val scoped = allocator.allocateBuffer(16uL)
allocator.close()
scoped.writeLong(1L, 0uL) // JVM : IllegalStateException : "MemoryBuffer has been closed"

// Buffer depuis une adresse brute : pas de scope, aucune garde — utiliser la
// zone après sa libération est un UB documenté.
val backing = MemoryAllocator()
val raw = MemoryBuffer(backing.allocate(16L), 16uL) // zone vivante tant que backing n'est pas fermé
raw.writeLong(7L, 0uL)

// Arène de durée de vie processus
val forever = globalMemory.allocateBuffer(16uL)
```

Deux buffers sur la même zone se voient mutuellement ; aucune synchronisation
n'est fournie.

Confinement (JVM) : l'arène est confinée au thread de création
(`Arena.ofConfined()`) — accès aux buffers scopés et `close()` depuis un autre
thread lèvent `WrongThreadException` ; `memoryScope` idem. En mode `unsafe`,
l'accès passe par l'adresse brute : aucune vérification de thread, seule la
garde de close s'applique. Les buffers bruts (adresse) ne portent aucun
confinement (garde nulle, UB documenté).

## 5. Option `unsafe`

Opt-in **par allocateur** (propage à tous les buffers de l'arène) ou **par
buffer** :

```kotlin
// Par allocateur : tous les buffers créés sont unsafe
val hotAllocator = MemoryAllocator(unsafe = true)
val hot = hotAllocator.allocateBuffer(8uL)
hot.writeLong(1L, 64uL) // hors bornes : PAS d'exception — UB (zone réelle plus grande, ici)

// Par buffer : opt-in local (allocation réelle de 128 octets, taille nominale 64)
memoryScope { allocator ->
    val localUnsafe = MemoryBuffer(allocator.allocate(128L), 64uL, unsafe = true)
    localUnsafe.writeLong(1L, 96uL) // hors taille nominale : PAS d'exception — UB
}

hotAllocator.close()
hot.writeLong(2L, 0uL) // JVM : IllegalStateException — la garde de durée de vie est conservée en unsafe
```

Politique unsafe (P2) : le mode unsafe saute **uniquement** les bornes-check.
La discipline de durée de vie reste la même que pour les buffers sûrs —
utiliser un buffer après fermeture de l'arène reste interdit (use-after-free).

**Différence native** : sur les backends natifs, le flag runtime est ignoré —
la valeur est figée à la compilation (`KFFI_NATIVE_UNSAFE` dans
`MemoryBuffer.native.kt`, `false` par défaut). Basculer : éditer la constante
puis recompiler le module.

## 6. Callback simple (upcall natif → Kotlin)

Le code généré par kextract produit les descripteurs, trampolines et
dispatchers ; voici le même mécanisme à la main. Un callback est d'abord un
`Callback` (interface marqueur) :

```kotlin
import org.graphiks.kffi.engine.JvmUpcallEngine

@OptIn(CallbackRuntimeApi::class)
private fun interface StatusCallback : Callback {
    fun onStatus(value: Int)
}
```

Le trampoline est un stub natif qui appelle un dispatcher statique ; le
dispatcher route vers la lambda enregistrée via `CallbackRuntime.dispatchSafely`
(le userdata = token de routage, dernier paramètre C) :

```kotlin
@OptIn(CallbackRuntimeApi::class)
private object StatusTrampolines {
    val type = CallbackType<StatusCallback>("quickstart-status", hasRoutingUserdata = true)

    val stub: NativeAddress by lazy {
        JvmUpcallEngine.allocateTrampoline(
            dispatcherClass = StatusTrampolines::class.java,
            dispatchMethod = "dispatch",
            dispatchSig = "(IJ)V", // (int value, long routingUserdata) → void
        )
    }

    @JvmStatic
    fun dispatch(value: Int, userdata: Long) {
        CallbackRuntime.dispatchSafely(
            type,
            userdata.takeIf { it != 0L }?.let(::NativeAddress),
        ) { it.onStatus(value) }
    }
}
```

Enregistrement : la registration expose l'adresse du trampoline
(`callback`) et le token de routage (`userdata`) à passer à la lib native :

```kotlin
@OptIn(CallbackRuntimeApi::class)
fun installStatusCallback() {
    val registration = CallbackRuntime.register(
        type = StatusTrampolines.type,
        trampoline = StatusTrampolines.stub,
        policy = CallbackPolicy.REPEATING,
        onError = CallbackExceptionHandler { error -> println("callback failed: $error") },
        callback = StatusCallback { value -> println("native nous a appelés avec $value") },
    )

    // La lib native reçoit l'adresse du trampoline + le userdata (dernier argument)
    // nativeCallExpectsCallback(registration.callback, registration.userdata)

    // Fermeture : plus aucune livraison ; isQuiescent devient vrai quand les
    // appels natifs en vol sont revenus.
    registration.close()
    check(registration.isClosed)
}
```

Points de contrat :

- `close()` retire le slot (token jamais réutilisé) ; `isQuiescent` ne devient
  vrai qu'une fois les livraisons en vol revenues.
- `ONCE` : dé-publié après la première livraison ; `REPEATING` : jusqu'au
  `close()`.
- Aucune exception ne traverse la frontière native — les échecs sont routés
  vers `onError` (ou le canal de secours).
- JVM : les stubs sont alloués dans une arène globale (durée de vie
  processus). Android : `UpcallEngine.allocateTrampoline` / `freeTrampoline`
  (gestion JNI explicite, généré par kextract).

## 7. Charger une bibliothèque native

**JVM** — charger la lib dans le processus, puis résoudre les symboles :

```kotlin
import org.graphiks.kffi.findOrThrow

System.loadLibrary("monlib") // doit être sur java.library.path / classpath

val fn = findOrThrow("mon_symbole") // UnsatisfiedLinkError si introuvable
```

Lancer la JVM avec `--enable-native-access=ALL-UNNAMED` : le runtime JVM kffi
utilise lui-même les API restreintes de `java.lang.foreign`, et les bindings
kextract peuvent émettre des upcalls sur le chemin FFM direct. Sans le flag, la
JVM émet un warning (appel bloqué dans une future version du JDK).

**Android** — le moteur `libkffi.so` est chargé automatiquement ; la lib
consommée est chargée par `dlopen` :

```kotlin
import org.graphiks.kffi.engine.NativeEngine

val handle = NativeEngine.loadNativeLibrary(pathToLib) // dlopen RTLD_NOW|RTLD_GLOBAL
val fn = NativeEngine.resolveSymbolIn(handle, "mon_symbole")
```

**Native** — lien à la compilation via cinterop (`.def`) ; pas de chargement
dynamique.

## 8. Générer des bindings

kffi est runtime-only : générez vos bindings avec
[kextract](https://github.com/klang-toolkit/kextract) (générateur Kotlin,
cible `org.graphiks.kffi`), puis liez-les à kffi comme décrit ci-dessus. Les
bindings générés utilisent les mêmes primitives que ce guide (`MemoryBuffer`,
`MemoryAllocator`, `CallbackRuntime`, moteurs).
