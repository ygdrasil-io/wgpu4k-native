# kffi — couche FFI multiplateforme de wgpu4k-native

Module Kotlin Multiplatform `org.graphiks:kffi` : couche d'accès bas-niveau à la
mémoire native et aux appels FFI, partagée par les bindings wgpu4k-native.
Le contrat `expect/actual` vit dans `commonMain` (`org.graphiks.kffi`) :
`NativeAddress`, `MemoryBuffer`, `MemoryAllocator` (+ `memoryScope`, `globalMemory`),
`CString`, `Callback`/`CallbackRuntime`.

## Backends

| Backend | Implémentation | Notes |
|---------|----------------|-------|
| **JVM** | Panama FFM (`java.lang.foreign`) | Arènes confinées ; downcalls via `MethodHandle` |
| **Android** | Couche Kotlin maison | Réimplémentation du modèle `MemorySegment`/`ValueLayout` par-dessus JNA |
| **Native** | `kotlinx.cinterop` | iOS, macOS, Linux, Windows, Android native |

Le contrat mémoire ci-dessous est unifié sur les trois backends.

## Modèle mémoire

- `NativeAddress` — adresse native brute (value class sur `Long`). **Non bornée par
  nature** : tout accès typé passe par `MemoryBuffer` (borné) ou par l'option
  `unsafe`.
- `MemoryBuffer` — buffer borné sur une adresse native : `handler` (adresse) +
  `size` (taille en octets). Accès scalaires et tableaux pour toutes les familles
  (Byte/Short/Int/Long/Float/Double, signés et non signés, pointeurs).
- `MemoryAllocator` — arène confinée : allocation (`allocate`, `allocateBuffer`,
  `allocateFrom`, `bufferOf`, `bufferOfAddress`, `bufferOfAddresses`), fermeture
  (`close`), et `memoryScope { }` qui garantit la fermeture de l'arène en fin de bloc.

```kotlin
import org.graphiks.kffi.*

memoryScope { allocator ->
    val buffer = allocator.allocateBuffer(size = 16uL)

    buffer.writeInt(value = 42, offset = 0uL)
    val value = buffer.readInt(offset = 0uL) // 42

    buffer.readLong(offset = 12uL)
    // IndexOutOfBoundsException : offset=12 width=8 size=16
}
```

## Contrat de sécurité mémoire

### Bornes-check

Tout accès typé (scalaire ou tableau) est vérifié : `offset + elementSize ≤ size`.
Hors bornes → `IndexOutOfBoundsException` avec offset/taille dans le message.

### Mode `unsafe` (opt-in)

`unsafe = true` élimine les bornes-check, au choix :

- par allocateur — `MemoryAllocator(unsafe = true)` propage l'option à **tous** les
  buffers créés par cet allocateur ;
- par buffer — `MemoryBuffer(addr, size, unsafe = true)` opt-in local.

Défaut : `false` (bornes-check actifs). En mode `unsafe`, tout accès hors bornes
devient un **comportement indéfini** (UB) : pas d'exception, corruption mémoire
possible. C'est un choix délibéré, réservé aux chemins à chaud.

### Durée de vie

Décision I2-(a) : le scope d'arène/session vit dans le `MemoryBuffer` (pas dans
`NativeAddress`).

- Buffer créé via `MemoryAllocator` (JVM) : porte le segment scopé de l'arène →
  accès après `close()` de l'arène → `IllegalStateException`.
- Buffer créé depuis une adresse brute (`MemoryBuffer(addr, size)`) : sans scope →
  accès post-close **non détecté** (UB documenté, aligné Android/native).
- Mode `unsafe` JVM : la garde de close I2-(a) est **conservée** (vérification
  `isAlive` légère avant l'accès) ; seules les bornes sont sautées.

### Différence native

Sur les backends native, le mode `unsafe` est **figé à la compilation** : la valeur
est une constante build-time (`KFFI_NATIVE_UNSAFE` dans `MemoryBuffer.native.kt`,
actuellement `false`). Les distributions native ne peuvent pas basculer au runtime ;
le flag d'API est accepté pour la compatibilité et sans effet. Basculer à la
compilation : éditer la constante puis recompiler le module.

### Aliasing

Deux buffers sur la même zone mémoire sont vus mutuellement : les écritures de l'un
sont visibles par l'autre. **Pas de verrou** : la synchronisation relève du
consommateur.

## Version

`Kffi.VERSION` (`1.0.0`) — groupe `org.graphiks`. Ce README est le contrat
consommateur du module ; la référence sémantique est le KDoc commun
(`MemoryBuffer.kt`, `MemoryAllocator.kt` dans `commonMain`).
