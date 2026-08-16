package org.graphiks.kffi.engine

import org.graphiks.kffi.C_POINTER
import org.graphiks.kffi.MemoryAllocator
import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.findOrThrow
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/**
 * Moteur de downcall JVM typé par forme — symétrique de NativeEngine (Android).
 *
 * Chaque wrapper consulte un cache par (forme, adresse de fonction) : le
 * MethodHandle FFM (invokeExact) est construit une seule fois par couple
 * adresse × forme, l'adresse cible étant liée à la construction. La clé est
 * encodée sur un Long ((fn shl 8) or shapeId) — zéro allocation sur le chemin
 * chaud — et le cache est borné en pratique par le nombre d'adresses exportées
 * distinctes résolues par les bindings. Les formes couvertes sont
 * celles réellement référencées par les bindings générés (union des signatures
 * wgpu) — la table grandit par ajout de wrapper, jamais par combinatoire.
 *
 * Les signatures portant des structs par valeur (arg ou retour) sont couvertes
 * par des wrappers construits depuis le registre de layouts (M5.2bis) : le code
 * généré enregistre des métadonnées (taille/alignement/champs) via
 * [registerStructLayout], et le moteur construit les GroupLayout FFM en interne.
 */
object JvmDowncallEngine {

    private val linker = Linker.nativeLinker()

    /** shapeId stables par wrapper — encodés dans la clé de [handleCache] (bas 8 bits). */
    private object ShapeId {
        const val V0 = 1
        const val V1P = 2
        const val V2PP = 3
        const val V3PPL = 4
        const val V4PPPP = 5
        const val V5PIIII = 6
        const val I0 = 7
        const val I1I = 8
        const val I1P = 9
        const val I4IIII = 10
        const val L8LLLLLLLL = 11
        const val P1P = 12
        const val P2PP = 13
        const val P2PI = 14
        const val P3PLL = 15
        const val F1P = 16
        const val D1P = 17

        /** Formes struct-by-value (M5.2bis) : shapeId dédiés par struct × forme —
         *  le layout du struct fait partie de la forme, la clé de cache reste
         *  unique par (adresse, forme). */
        const val S_ARG_BOX = 18
        const val S_RET_BOX = 19

        // --- Union des signatures wgpu (M5.3) : scalaires ---
        const val I2PP = 20
        const val I2PI = 21
        const val L1P = 22
        const val I4PLPL = 23
        const val V2PI = 24
        const val V3PLP = 25
        const val V5PPLPL = 26
        const val V6PPPLPP = 27
        const val V6PIIIII = 28
        const val V5PIPLP = 29
        const val V5PPILL = 30
        const val V5PIPLL = 31
        const val I3PPP = 32
        const val L3PPP = 33
        const val L3PLP = 34
        const val I3PIP = 35
        const val V1I = 36
        const val V4PIIP = 37
        const val V4PPLI = 38
        const val V6PPLPLI = 39
        const val V3PPI = 40
        const val V4PPLL = 41
        const val V6PPLPLL = 42
        const val V6PPIIPL = 43
        const val V4PIII = 44
        const val V7PFFFFFF = 45

        // --- Union des signatures wgpu (M5.3) : structs par valeur ---
        const val S_ARG_ADAPTER_INFO = 46
        const val S_ARG_SUPPORTED_FEATURES = 47
        const val S_ARG_SUPPORTED_INSTANCE_FEATURES = 48
        const val S_ARG_SUPPORTED_WGSL_LANGUAGE_FEATURES = 49
        const val S_ARG_SURFACE_CAPABILITIES = 50
        const val S_ARG_STRINGVIEW_P = 51
        const val S_ARG_STRINGVIEW_RET_P = 52
        const val S_RET_FUTURE_P = 53
        const val S_RET_FUTURE_P_QUEUE_WORK_DONE = 54
        const val S_RET_FUTURE_P_POP_ERROR_SCOPE = 55
        const val S_RET_FUTURE_P_COMPILATION_INFO = 56
        const val S_RET_FUTURE_PP_REQUEST_ADAPTER = 57
        const val S_RET_FUTURE_PP_REQUEST_DEVICE = 58
        const val S_RET_FUTURE_PP_CREATE_RENDER_PIPELINE_ASYNC = 59
        const val S_RET_FUTURE_PP_CREATE_COMPUTE_PIPELINE_ASYNC = 60
        const val S_RET_FUTURE_PLLL_BUFFER_MAP = 61
    }

    /** MéthodeHandle par (adresse de fonction, forme, version de layout). */
    private val handleCache =
        java.util.concurrent.ConcurrentHashMap<Long, java.util.concurrent.ConcurrentHashMap<Int, MethodHandle>>()

    fun resolveSymbol(name: String): Long = findOrThrow(name)

    private fun segment(address: Long): MemorySegment =
        MemorySegment.ofAddress(address)

    /**
     * Cache à deux niveaux : clé externe = adresse de fonction (Long exact,
     * aucune collision possible entre deux adresses — contrairement à un encodage
     * par décalage, qui replie les bits 48+ des adresses canoniques dans les bits
     * bas de la clé), clé interne = `(layoutVersion shl 8) or shapeId`. La version
     * du layout fait partie de la clé interne : une re-registration
     * (registerStructLayout) construit un nouveau MethodHandle au lieu de réutiliser
     * celui du descripteur précédent (corruption ABI silencieuse sinon). La table
     * interne est minuscule (quelques formes par adresse de fonction).
     */
    private fun handle(fn: Long, shapeId: Int, descriptor: FunctionDescriptor, layoutVersion: Int = 0): MethodHandle {
        require(fn != 0L) { "Cannot downcall through null function address" }
        val key = (layoutVersion shl 8) or shapeId
        return handleCache
            .computeIfAbsent(fn) { java.util.concurrent.ConcurrentHashMap() }
            .computeIfAbsent(key) { linker.downcallHandle(segment(fn), descriptor) }
    }

    // --- void returns ---

    fun callV0(fn: Long) {
        val handle = handle(fn, ShapeId.V0, FunctionDescriptor.ofVoid())
        handle.invokeExact()
    }

    fun callV1P(fn: Long, p1: Long) {
        val handle = handle(fn, ShapeId.V1P, FunctionDescriptor.ofVoid(C_POINTER))
        handle.invokeExact(segment(p1))
    }

    fun callV2PP(fn: Long, p1: Long, p2: Long) {
        val handle = handle(fn, ShapeId.V2PP, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER))
        handle.invokeExact(segment(p1), segment(p2))
    }

    fun callV3PPL(fn: Long, p1: Long, p2: Long, a3: Long) {
        val handle = handle(fn, ShapeId.V3PPL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3)
    }

    fun callV4PPPP(fn: Long, p1: Long, p2: Long, p3: Long, p4: Long) {
        val handle = handle(fn, ShapeId.V4PPPP, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_POINTER))
        handle.invokeExact(segment(p1), segment(p2), segment(p3), segment(p4))
    }

    fun callV5PIIII(fn: Long, p1: Long, a2: Int, a3: Int, a4: Int, a5: Int) {
        val handle = handle(fn, ShapeId.V5PIIII, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), a2, a3, a4, a5)
    }

    // --- long returns ---

    fun callI0(fn: Long): Long {
        val handle = handle(fn, ShapeId.I0, FunctionDescriptor.of(ValueLayout.JAVA_LONG))
        return handle.invokeExact() as Long
    }

    fun callI1I(fn: Long, a1: Int): Long {
        val handle = handle(fn, ShapeId.I1I, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT))
        return handle.invokeExact(a1) as Long
    }

    fun callI1P(fn: Long, a1: Long): Long {
        val handle = handle(fn, ShapeId.I1P, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER))
        return handle.invokeExact(segment(a1)) as Long
    }

    fun callI4IIII(fn: Long, a1: Int, a2: Int, a3: Int, a4: Int): Long {
        val handle = handle(fn, ShapeId.I4IIII, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        return handle.invokeExact(a1, a2, a3, a4) as Long
    }

    fun callL8LLLLLLLL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long, a5: Long, a6: Long, a7: Long, a8: Long): Long {
        val handle = handle(fn, ShapeId.L8LLLLLLLL, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        return handle.invokeExact(a1, a2, a3, a4, a5, a6, a7, a8) as Long
    }

    fun callP1P(fn: Long, a1: Long): Long {
        val handle = handle(fn, ShapeId.P1P, FunctionDescriptor.of(C_POINTER, C_POINTER))
        return (handle.invokeExact(segment(a1)) as MemorySegment).address()
    }

    fun callP2PP(fn: Long, a1: Long, a2: Long): Long {
        val handle = handle(fn, ShapeId.P2PP, FunctionDescriptor.of(C_POINTER, C_POINTER, C_POINTER))
        return (handle.invokeExact(segment(a1), segment(a2)) as MemorySegment).address()
    }

    fun callP2PI(fn: Long, a1: Long, a2: Int): Long {
        val handle = handle(fn, ShapeId.P2PI, FunctionDescriptor.of(C_POINTER, C_POINTER, ValueLayout.JAVA_INT))
        return (handle.invokeExact(segment(a1), a2) as MemorySegment).address()
    }

    fun callP3PLL(fn: Long, a1: Long, a2: Long, a3: Long): Long {
        val handle = handle(fn, ShapeId.P3PLL, FunctionDescriptor.of(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        return (handle.invokeExact(segment(a1), a2, a3) as MemorySegment).address()
    }

    fun callF1P(fn: Long, a1: Long): Float {
        val handle = handle(fn, ShapeId.F1P, FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, C_POINTER))
        return handle.invokeExact(segment(a1)) as Float
    }

    fun callD1P(fn: Long, a1: Long): Double {
        val handle = handle(fn, ShapeId.D1P, FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, C_POINTER))
        return handle.invokeExact(segment(a1)) as Double
    }

    // --- Union des signatures wgpu (M5.3) : scalaires ---
    // Les formes manquantes du bake-off M2.2 sont ajoutées une à une jusqu'à
    // couvrir l'union des signatures du moteur wgpu : chaque wrapper suit le
    // pattern M2.1 (typed, cache (adresse, forme), garde null).

    fun callI2PP(fn: Long, a1: Long, a2: Long): Long {
        val handle = handle(fn, ShapeId.I2PP, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, C_POINTER))
        return handle.invokeExact(segment(a1), segment(a2)) as Long
    }

    fun callI2PI(fn: Long, a1: Long, a2: Int): Long {
        val handle = handle(fn, ShapeId.I2PI, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_INT))
        return handle.invokeExact(segment(a1), a2) as Long
    }

    fun callL1P(fn: Long, a1: Long): Long {
        val handle = handle(fn, ShapeId.L1P, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER))
        return handle.invokeExact(segment(a1)) as Long
    }

    fun callI4PLPL(fn: Long, a1: Long, a2: Long, a3: Long, a4: Long): Long {
        val handle = handle(fn, ShapeId.I4PLPL, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG))
        return handle.invokeExact(segment(a1), a2, segment(a3), a4) as Long
    }

    fun callV2PI(fn: Long, p1: Long, a2: Int) {
        val handle = handle(fn, ShapeId.V2PI, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), a2)
    }

    fun callV3PLP(fn: Long, p1: Long, a2: Long, p3: Long) {
        val handle = handle(fn, ShapeId.V3PLP, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_LONG, C_POINTER))
        handle.invokeExact(segment(p1), a2, segment(p3))
    }

    fun callV5PPLPL(fn: Long, p1: Long, p2: Long, a3: Long, p4: Long, a5: Long) {
        val handle = handle(fn, ShapeId.V5PPLPL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3, segment(p4), a5)
    }

    fun callV6PPPLPP(fn: Long, p1: Long, p2: Long, p3: Long, a4: Long, p5: Long, p6: Long) {
        val handle = handle(fn, ShapeId.V6PPPLPP, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER, C_POINTER))
        handle.invokeExact(segment(p1), segment(p2), segment(p3), a4, segment(p5), segment(p6))
    }

    fun callV6PIIIII(fn: Long, p1: Long, a2: Int, a3: Int, a4: Int, a5: Int, a6: Int) {
        val handle = handle(fn, ShapeId.V6PIIIII, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), a2, a3, a4, a5, a6)
    }

    fun callV5PIPLP(fn: Long, p1: Long, a2: Int, p3: Long, a4: Long, p5: Long) {
        val handle = handle(fn, ShapeId.V5PIPLP, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER))
        handle.invokeExact(segment(p1), a2, segment(p3), a4, segment(p5))
    }

    fun callV5PPILL(fn: Long, p1: Long, p2: Long, a3: Int, a4: Long, a5: Long) {
        val handle = handle(fn, ShapeId.V5PPILL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3, a4, a5)
    }

    fun callV5PIPLL(fn: Long, p1: Long, a2: Int, p3: Long, a4: Long, a5: Long) {
        val handle = handle(fn, ShapeId.V5PIPLL, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), a2, segment(p3), a4, a5)
    }

    fun callI3PPP(fn: Long, a1: Long, a2: Long, a3: Long): Long {
        val handle = handle(fn, ShapeId.I3PPP, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, C_POINTER, C_POINTER))
        return handle.invokeExact(segment(a1), segment(a2), segment(a3)) as Long
    }

    fun callL3PPP(fn: Long, a1: Long, a2: Long, a3: Long): Long {
        val handle = handle(fn, ShapeId.L3PPP, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, C_POINTER, C_POINTER))
        return handle.invokeExact(segment(a1), segment(a2), segment(a3)) as Long
    }

    fun callL3PLP(fn: Long, a1: Long, a2: Long, a3: Long): Long {
        val handle = handle(fn, ShapeId.L3PLP, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER))
        return handle.invokeExact(segment(a1), a2, segment(a3)) as Long
    }

    fun callI3PIP(fn: Long, a1: Long, a2: Int, a3: Long): Long {
        val handle = handle(fn, ShapeId.I3PIP, FunctionDescriptor.of(ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_INT, C_POINTER))
        return handle.invokeExact(segment(a1), a2, segment(a3)) as Long
    }

    fun callV1I(fn: Long, a1: Int) {
        val handle = handle(fn, ShapeId.V1I, FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT))
        handle.invokeExact(a1)
    }

    fun callV4PIIP(fn: Long, p1: Long, a2: Int, a3: Int, p4: Long) {
        val handle = handle(fn, ShapeId.V4PIIP, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, C_POINTER))
        handle.invokeExact(segment(p1), a2, a3, segment(p4))
    }

    fun callV4PPLI(fn: Long, p1: Long, p2: Long, a3: Long, a4: Int) {
        val handle = handle(fn, ShapeId.V4PPLI, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), segment(p2), a3, a4)
    }

    fun callV6PPLPLI(fn: Long, p1: Long, p2: Long, a3: Long, p4: Long, a5: Long, a6: Int) {
        val handle = handle(fn, ShapeId.V6PPLPLI, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), segment(p2), a3, segment(p4), a5, a6)
    }

    fun callV3PPI(fn: Long, p1: Long, p2: Long, a3: Int) {
        val handle = handle(fn, ShapeId.V3PPI, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), segment(p2), a3)
    }

    fun callV4PPLL(fn: Long, p1: Long, p2: Long, a3: Long, a4: Long) {
        val handle = handle(fn, ShapeId.V4PPLL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3, a4)
    }

    fun callV6PPLPLL(fn: Long, p1: Long, p2: Long, a3: Long, p4: Long, a5: Long, a6: Long) {
        val handle = handle(fn, ShapeId.V6PPLPLL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_LONG, C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3, segment(p4), a5, a6)
    }

    fun callV6PPIIPL(fn: Long, p1: Long, p2: Long, a3: Int, a4: Int, p5: Long, a6: Long) {
        val handle = handle(fn, ShapeId.V6PPIIPL, FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, C_POINTER, ValueLayout.JAVA_LONG))
        handle.invokeExact(segment(p1), segment(p2), a3, a4, segment(p5), a6)
    }

    fun callV4PIII(fn: Long, p1: Long, a2: Int, a3: Int, a4: Int) {
        val handle = handle(fn, ShapeId.V4PIII, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT))
        handle.invokeExact(segment(p1), a2, a3, a4)
    }

    fun callV7PFFFFFF(fn: Long, p1: Long, a2: Float, a3: Float, a4: Float, a5: Float, a6: Float, a7: Float) {
        val handle = handle(fn, ShapeId.V7PFFFFFF, FunctionDescriptor.ofVoid(C_POINTER, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT))
        handle.invokeExact(segment(p1), a2, a3, a4, a5, a6, a7)
    }

    // --- struct-by-value : registre de layouts (M5.2bis) ---

    data class StructField(val cName: String, val kind: FieldKind, val offsetBytes: Long)

    enum class FieldKind { INT8, UINT8, INT16, UINT16, INT32, UINT32, INT64, UINT64, FLOAT32, FLOAT64, POINTER, STRUCT, PADDING }

    private val structLayouts = java.util.concurrent.ConcurrentHashMap<String, Pair<Long, List<StructField>>>()

    private val structAlignments = java.util.concurrent.ConcurrentHashMap<String, Long>()

    /**
     * Version par struct, incrémentée à chaque [registerStructLayout] : elle fait
     * partie de la clé de [handleCache] (bits 8-15) pour que les wrappers
     * struct-by-value reconstruisent le MethodHandle quand le descripteur change.
     */
    private val layoutVersions = java.util.concurrent.ConcurrentHashMap<String, Int>()

    /**
     * Enregistre les métadonnées de layout d'un struct par valeur, émises par le
     * code généré (kextract) au chargement du fichier de bindings. Les champs
     * [StructField] portent le type ([FieldKind]) et, pour PADDING, la TAILLE du
     * padding (gap entre champs consécutifs, y compris le padding final) ; pour les
     * autres champs, [StructField.offsetBytes] est informatif — le GroupLayout FFM
     * est reconstruit depuis les éléments et leurs tailles.
     */
    fun registerStructLayout(name: String, sizeBytes: Long, alignmentBytes: Long, fields: List<StructField>) {
        structLayouts[name] = sizeBytes to fields
        structAlignments[name] = alignmentBytes
        layoutVersions[name] = (layoutVersions[name] ?: 0) + 1
        structDescriptors.remove(name)
    }

    private fun layoutVersion(name: String): Int = layoutVersions[name] ?: 0

    private val structDescriptors = java.util.concurrent.ConcurrentHashMap<String, MemoryLayout>()

    /**
     * Layout FFM d'un struct enregistré, construit depuis les métadonnées
     * [registerStructLayout] (taille, alignement, champs, padding explicite).
     *
     * API publique : utilisée par le code généré (kextract) — y compris les
     * trampolines de callbacks FFM de secours — pour construire les descripteurs
     * de fonctions portant des structs par valeur.
     *
     * Contrat :
     * - [name] doit avoir été enregistré via [registerStructLayout] avant le
     *   premier appel (le code généré s'en charge à l'initialisation du fichier,
     *   avant tout downcall/upcall) ;
     * - nom inconnu → `NoSuchElementException` ;
     * - métadonnées incohérentes avec la taille calculée → `IllegalStateException`
     *   (garde `check`) ;
     * - le layout est mis en cache et reconstruit à chaque ré-enregistrement
     *   (version bump) — les descripteurs dérivés d'un layout ancien sont
     *   invalidés avec lui.
     */
    fun structLayout(name: String): MemoryLayout =
        structDescriptors.computeIfAbsent(name) { structName ->
            val (size, fields) = structLayouts.getValue(structName)
            val elements = fields.map { field ->
                when (field.kind) {
                    // L'offsetBytes d'un champ PADDING porte la TAILLE du padding :
                    // FFM place chaque élément séquentiellement, sans padding implicite,
                    // et withByteAlignment n'arrondit pas la taille — les gaps explicites
                    // (y compris final) sont donc obligatoires pour reproduire l'offset
                    // et la taille Clang.
                    FieldKind.PADDING -> MemoryLayout.paddingLayout(field.offsetBytes)
                    // Le cName d'un champ STRUCT porte le nom enregistré du type imbriqué.
                    FieldKind.STRUCT -> structLayout(field.cName).withName(field.cName)
                    else -> primitiveLayout(field.kind).withName(field.cName)
                }
            }
            val layout = MemoryLayout.structLayout(*elements.toTypedArray())
                .withByteAlignment(structAlignments.getValue(structName))
            check(layout.byteSize() == size) {
                "Registered size for $structName ($size bytes) disagrees with the layout built " +
                    "from its fields (${layout.byteSize()} bytes)"
            }
            layout
        }

    private fun primitiveLayout(kind: FieldKind): ValueLayout = when (kind) {
        FieldKind.INT8, FieldKind.UINT8 -> ValueLayout.JAVA_BYTE
        FieldKind.INT16, FieldKind.UINT16 -> ValueLayout.JAVA_SHORT
        FieldKind.INT32, FieldKind.UINT32 -> ValueLayout.JAVA_INT
        FieldKind.INT64, FieldKind.UINT64 -> ValueLayout.JAVA_LONG
        FieldKind.FLOAT32 -> ValueLayout.JAVA_FLOAT
        FieldKind.FLOAT64 -> ValueLayout.JAVA_DOUBLE
        FieldKind.POINTER -> ValueLayout.ADDRESS
        FieldKind.STRUCT -> error("nested struct layouts resolve through structLayout")
        FieldKind.PADDING -> error("padding handled separately")
    }

    // --- wrappers struct-by-value par struct (M5.2bis) ---
    //
    // Formes dédiées par struct : le layout est résolu depuis le registre à chaque
    // appel (cached dans structDescriptors), le MethodHandle par (adresse, forme)
    // reste dans handleCache. Le segment d'argument est borné à la taille du layout
    // (reinterpret) ; le retour struct exige un SegmentAllocator en premier
    // argument du MethodHandle (convention FFM) — l'arène du MemoryAllocator
    // appelant, qui porte le scope de la structure retournée.

    fun callStructArgBox(fn: Long, structPtr: Long) {
        val layout = structLayout("Box")
        val handle = handle(fn, ShapeId.S_ARG_BOX, FunctionDescriptor.ofVoid(layout), layoutVersion("Box"))
        handle.invokeExact(segment(structPtr).reinterpret(layout.byteSize()))
    }

    fun callStructReturnBox(fn: Long, allocator: MemoryAllocator, a1: Int): NativeAddress {
        val layout = structLayout("Box")
        val handle = handle(fn, ShapeId.S_RET_BOX, FunctionDescriptor.of(layout, ValueLayout.JAVA_INT), layoutVersion("Box"))
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(segmentAllocator, a1) as MemorySegment
        return NativeAddress(result.address())
    }

    // --- Union des signatures wgpu (M5.3) : structs par valeur ---
    //
    // Formes M5.2bis étendues : les signatures wgpu portent des structs par valeur
    // avec des arguments scalaires AUTOUR du struct (StringView en argument derrière
    // un pointeur, structs callbackInfo en argument d'une fonction à retour WGPUFuture,
    // etc.). Chaque wrapper est dédié à une (forme, paire de structs) — le layout de
    // chaque struct est résolu depuis le registre, le descripteur est construit une
    // fois par (adresse, forme, version de layout) dans handleCache. Le segment
    // d'argument struct est borné à la taille de son layout ; le retour struct exige
    // le SegmentAllocator de l'allocateur appelant (convention FFM).

    /**
     * MethodHandle d'un wrapper struct-par-valeur en argument : [scalarArgLayouts]
     * sont les layouts des arguments scalaires/pointeurs qui PRÉCÈDENT le struct
     * dans l'ordre C (le struct est toujours le dernier argument — vérifié par la
     * garde kextract à la génération). [returnLayout] est null pour les retours
     * Unit, sinon le layout du retour (ex. C_POINTER pour wgpuGetProcAddress) —
     * une forme par nom de wrapper, jamais d'overload.
     */
    private fun structArgHandle(
        fn: Long,
        shapeId: Int,
        structName: String,
        scalarArgLayouts: List<ValueLayout> = emptyList(),
        returnLayout: MemoryLayout? = null,
    ): MethodHandle {
        val layout = structLayout(structName)
        val descriptor = if (returnLayout == null) {
            FunctionDescriptor.ofVoid(*(scalarArgLayouts + layout).toTypedArray())
        } else {
            FunctionDescriptor.of(returnLayout, *(scalarArgLayouts + layout).toTypedArray())
        }
        return handle(fn, shapeId, descriptor, layoutVersion(structName))
    }

    /**
     * MethodHandle d'un wrapper struct-par-valeur en retour, avec zéro ou un struct
     * par valeur en argument ([argStructNames], dernier argument). La version de
     * layout de la clé de cache combine les versions de TOUS les structs du
     * descripteur (retour + arguments) : une re-registration de l'un d'eux
     * reconstruit le MethodHandle au lieu de réutiliser le descripteur périmé.
     */
    private fun structReturnHandle(
        fn: Long,
        shapeId: Int,
        returnName: String,
        argStructNames: List<String> = emptyList(),
        scalarArgLayouts: List<ValueLayout> = emptyList(),
    ): MethodHandle {
        val argLayouts = argStructNames.map(::structLayout)
        val descriptor = FunctionDescriptor.of(
            structLayout(returnName),
            *(scalarArgLayouts + argLayouts).toTypedArray(),
        )
        val version = (listOf(returnName) + argStructNames).fold(0) { acc, name ->
            acc * 31 + layoutVersion(name)
        }
        return handle(fn, shapeId, descriptor, version)
    }

    private fun structSegment(structPtr: Long, structName: String): MemorySegment {
        val layout = structLayout(structName)
        return segment(structPtr).reinterpret(layout.byteSize())
    }

    // --- struct en argument, retour Unit (freeMembers et consorts) ---

    fun callStructArgWGPUAdapterInfo(fn: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_ADAPTER_INFO, "WGPUAdapterInfo")
        handle.invokeExact(structSegment(structPtr, "WGPUAdapterInfo"))
    }

    fun callStructArgWGPUSupportedFeatures(fn: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_SUPPORTED_FEATURES, "WGPUSupportedFeatures")
        handle.invokeExact(structSegment(structPtr, "WGPUSupportedFeatures"))
    }

    fun callStructArgWGPUSupportedInstanceFeatures(fn: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_SUPPORTED_INSTANCE_FEATURES, "WGPUSupportedInstanceFeatures")
        handle.invokeExact(structSegment(structPtr, "WGPUSupportedInstanceFeatures"))
    }

    fun callStructArgWGPUSupportedWGSLLanguageFeatures(fn: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_SUPPORTED_WGSL_LANGUAGE_FEATURES, "WGPUSupportedWGSLLanguageFeatures")
        handle.invokeExact(structSegment(structPtr, "WGPUSupportedWGSLLanguageFeatures"))
    }

    fun callStructArgWGPUSurfaceCapabilities(fn: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_SURFACE_CAPABILITIES, "WGPUSurfaceCapabilities")
        handle.invokeExact(structSegment(structPtr, "WGPUSurfaceCapabilities"))
    }

    // --- WGPUStringView en argument (SetLabel / PushDebugGroup / InsertDebugMarker) ---

    fun callStructArgWGPUStringView(fn: Long, p1: Long, structPtr: Long) {
        val handle = structArgHandle(fn, ShapeId.S_ARG_STRINGVIEW_P, "WGPUStringView", listOf(C_POINTER))
        handle.invokeExact(segment(p1), structSegment(structPtr, "WGPUStringView"))
    }

    // --- WGPUStringView en argument, retour pointeur (wgpuGetProcAddress) ---
    // Forme distincte de callStructArgWGPUStringView (retour Unit) : nom dédié,
    // pas d'overload — une forme = un nom de wrapper.

    fun callStructArgWGPUStringViewRetP(fn: Long, structPtr: Long): Long {
        val handle = structArgHandle(
            fn,
            ShapeId.S_ARG_STRINGVIEW_RET_P,
            "WGPUStringView",
            returnLayout = C_POINTER,
        )
        return (handle.invokeExact(structSegment(structPtr, "WGPUStringView")) as MemorySegment).address()
    }

    // --- WGPUFuture en retour ---

    fun callStructReturnWGPUFuture(fn: Long, allocator: MemoryAllocator, p1: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_P,
            returnName = "WGPUFuture",
            scalarArgLayouts = listOf(C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(segmentAllocator, segment(p1)) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUQueueWorkDoneCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_P_QUEUE_WORK_DONE,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUQueueWorkDoneCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            structSegment(structPtr, "WGPUQueueWorkDoneCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUPopErrorScopeCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_P_POP_ERROR_SCOPE,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUPopErrorScopeCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            structSegment(structPtr, "WGPUPopErrorScopeCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUCompilationInfoCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_P_COMPILATION_INFO,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUCompilationInfoCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            structSegment(structPtr, "WGPUCompilationInfoCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPURequestAdapterCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, p2: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_PP_REQUEST_ADAPTER,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPURequestAdapterCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER, C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            segment(p2),
            structSegment(structPtr, "WGPURequestAdapterCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPURequestDeviceCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, p2: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_PP_REQUEST_DEVICE,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPURequestDeviceCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER, C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            segment(p2),
            structSegment(structPtr, "WGPURequestDeviceCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUCreateRenderPipelineAsyncCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, p2: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_PP_CREATE_RENDER_PIPELINE_ASYNC,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUCreateRenderPipelineAsyncCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER, C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            segment(p2),
            structSegment(structPtr, "WGPUCreateRenderPipelineAsyncCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUCreateComputePipelineAsyncCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, p2: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_PP_CREATE_COMPUTE_PIPELINE_ASYNC,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUCreateComputePipelineAsyncCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER, C_POINTER),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            segment(p2),
            structSegment(structPtr, "WGPUCreateComputePipelineAsyncCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }

    fun callStructReturnWGPUFutureWGPUBufferMapCallbackInfo(fn: Long, allocator: MemoryAllocator, p1: Long, a2: Long, a3: Long, a4: Long, structPtr: Long): NativeAddress {
        val handle = structReturnHandle(
            fn,
            ShapeId.S_RET_FUTURE_PLLL_BUFFER_MAP,
            returnName = "WGPUFuture",
            argStructNames = listOf("WGPUBufferMapCallbackInfo"),
            scalarArgLayouts = listOf(C_POINTER, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG),
        )
        val segmentAllocator: SegmentAllocator = allocator.arena
        val result = handle.invokeExact(
            segmentAllocator,
            segment(p1),
            a2,
            a3,
            a4,
            structSegment(structPtr, "WGPUBufferMapCallbackInfo"),
        ) as MemorySegment
        return NativeAddress(result.address())
    }
}
