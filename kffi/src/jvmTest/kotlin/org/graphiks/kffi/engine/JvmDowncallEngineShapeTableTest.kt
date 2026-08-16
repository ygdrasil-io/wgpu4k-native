package org.graphiks.kffi.engine

import io.kotest.assertions.fail
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.graphiks.kffi.MemoryAllocator

/**
 * Vérification mécanique (review M5.3) : la signature Java de chaque wrapper du
 * moteur est épinglée contre la chaîne de lettres que kextract déclare et émet
 * (`jvmEngineWrappers` / `jvmEngineStructWrapperShapes` dans KotlinKmpJvmBuilder).
 * Trois tables vivent en parallèle (moteur, garde kextract, stub de test) — un
 * décalage est surtout fail-loud, mais une entrée existante avec les mêmes types
 * Kotlin et une ABI différente compilerait en silence : ce test réduit la surface
 * de dérive silencieuse au niveau signature Java.
 *
 * - Wrappers scalaires : le nom encode la forme (`call` + R + N + lettres,
 *   convention wrapperForm) — le test parse le nom et vérifie arité + classes
 *   porteuses des paramètres et du retour. Un changement de nom ou de signature
 *   hors contrat échoue.
 * - Wrappers struct-by-value : table miroir de `jvmEngineStructWrapperShapes`
 *   (nom émis → lettres, S = struct par valeur en Long à sa position C) — le
 *   test vérifie la signature Java de chaque nom émis, allocator compris.
 * - Invariant « une forme = un nom » : aucune méthode publique `call*` ne doit
 *   être surchargée, et chaque méthode doit être couverte par l'une des deux
 *   mécaniques (pas de wrapper muet, pas d'entrée orpheline).
 *
 * Le niveau ABI profond (FunctionDescriptor / largeurs ValueLayout) est épinglé
 * par les tests runtime de la fixture : un descripteur en désaccord avec la
 * signature Java du wrapper fait échouer invokeExact (WrongMethodTypeException)
 * au premier appel.
 */
class JvmDowncallEngineShapeTableTest : FreeSpec({

    "scalar wrapper names encode their exact Java signature" {
        val byName = callMethodsByKotlinName()
        val scalarNames = byName.keys - STRUCT_SHAPES.keys
        scalarNames.forEach { name ->
            val method = byName.getValue(name).single()
            val returnLetter = name[4]
            val rest = name.drop(5)
            val count = rest.takeWhile { it.isDigit() }.toInt()
            val letters = rest.dropWhile { it.isDigit() }
            letters.length shouldBe count

            // fn + N arguments, carriers par lettre.
            method.parameterCount shouldBe 1 + count
            method.parameterTypes[0] shouldBe java.lang.Long.TYPE
            method.parameterTypes.drop(1).toList() shouldBe letters.map(::carrier)
            method.returnType shouldBe returnCarrier(returnLetter)
        }
    }

    "struct wrapper signatures match the kextract-declared letter strings" {
        val byName = callMethodsByKotlinName()
        STRUCT_SHAPES.forEach { (name, shape) ->
            val method = byName[name]?.single() ?: fail("wrapper struct manquant : $name")
            val (argLetters, returnKind) = shape.split('|').let { it[0] to it[1].single() }
            val params = buildList {
                add(java.lang.Long.TYPE) // fn
                if (returnKind == 'S') add(MemoryAllocator::class.java) // allocator FFM
                argLetters.forEach { letter -> add(carrier(letter)) }
            }
            method.parameterTypes.toList() shouldBe params
            method.returnType shouldBe returnCarrier(returnKind)
        }
    }

    "one wrapper per shape: no overloads, no orphan methods" {
        val byName = callMethodsByKotlinName()
        byName.values.forEach { overloads -> overloads.size shouldBe 1 }
        val allNames = byName.keys
        val scalarNames = allNames - STRUCT_SHAPES.keys
        // Chaque méthode qui n'est pas une entrée struct connue doit respecter la
        // convention wrapperForm (call + R + N + lettres) — un wrapper ajouté hors
        // table (nommé callFoo ou callStructX sans entrée) échoue ici.
        scalarNames.forEach { name ->
            val pattern = Regex("call[VILPDF][0-9]+[PILFDSB]*")
            pattern.matches(name) shouldBe true
        }
    }
})

// Miroir de jvmEngineStructWrapperShapes (kextract) : nom émis → chaîne
// « argLetters|returnKind ». S = struct par valeur (paramètre Long).
private val STRUCT_SHAPES: Map<String, String> = mapOf(
    "callStructArgBox" to "S|V",
    "callStructArgWGPUStringView" to "PS|V",
    "callStructArgWGPUStringViewRetP" to "S|P",
    "callStructArgWGPUAdapterInfo" to "S|V",
    "callStructArgWGPUSupportedFeatures" to "S|V",
    "callStructArgWGPUSupportedInstanceFeatures" to "S|V",
    "callStructArgWGPUSupportedWGSLLanguageFeatures" to "S|V",
    "callStructArgWGPUSurfaceCapabilities" to "S|V",
    "callStructReturnBox" to "I|S",
    "callStructReturnWGPUFuture" to "P|S",
    "callStructReturnWGPUFutureWGPUQueueWorkDoneCallbackInfo" to "PS|S",
    "callStructReturnWGPUFutureWGPUPopErrorScopeCallbackInfo" to "PS|S",
    "callStructReturnWGPUFutureWGPUCompilationInfoCallbackInfo" to "PS|S",
    "callStructReturnWGPUFutureWGPURequestAdapterCallbackInfo" to "PPS|S",
    "callStructReturnWGPUFutureWGPURequestDeviceCallbackInfo" to "PPS|S",
    "callStructReturnWGPUFutureWGPUCreateRenderPipelineAsyncCallbackInfo" to "PPS|S",
    "callStructReturnWGPUFutureWGPUCreateComputePipelineAsyncCallbackInfo" to "PPS|S",
    "callStructReturnWGPUFutureWGPUBufferMapCallbackInfo" to "PLLLS|S",
)

private fun carrier(letter: Char): Class<*> = when (letter) {
    'P', 'L' -> java.lang.Long.TYPE
    'I' -> java.lang.Integer.TYPE
    'F' -> java.lang.Float.TYPE
    'D' -> java.lang.Double.TYPE
    // S = struct par valeur → structPtr (Long). La table struct n'a aucun
    // short scalaire ; un wrapper scalaire avec un argument I16 étendrait la
    // table et devrait introduire une disambiguation ici.
    'S' -> java.lang.Long.TYPE
    'B' -> java.lang.Byte.TYPE
    else -> error("lettre d'argument inconnue : $letter")
}

private fun returnCarrier(returnKind: Char): Class<*> = when (returnKind) {
    'V' -> java.lang.Void.TYPE
    'F' -> java.lang.Float.TYPE
    'D' -> java.lang.Double.TYPE
    // Convention moteur : les retours I/L/P sont portés en Long (JAVA_LONG),
    // le code généré rétrécit vers Int/Short/… côté émission.
    'P', 'I', 'L' -> java.lang.Long.TYPE
    // Retour struct : NativeAddress, dé-boîté en long au niveau JVM (value class).
    'S' -> java.lang.Long.TYPE
    else -> error("lettre de retour inconnue : $returnKind")
}

/**
 * Indexe les méthodes publiques `call*` par leur NOM KOTLIN : le compilateur
 * suffixe les fonctions dont un paramètre est une value class non dé-boîtable
 * (MemoryAllocator sur les wrappers à retour struct) d'un hash JVM
 * (`callStructReturnBox-eTG-Znw`), que la réflexion brute par nom ne voit pas.
 */
private fun callMethodsByKotlinName(): Map<String, List<java.lang.reflect.Method>> =
    JvmDowncallEngine::class.java.methods
        .filter { it.name.startsWith("call") }
        .groupBy { it.name.substringBefore('-') }
