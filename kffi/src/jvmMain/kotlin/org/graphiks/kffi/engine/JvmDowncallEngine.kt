package org.graphiks.kffi.engine

import org.graphiks.kffi.C_POINTER
import org.graphiks.kffi.findOrThrow
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
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
    }

    /** MéthodeHandle par (adresse de fonction, forme) ; borné par les adresses résolues. */
    private val handleCache = java.util.concurrent.ConcurrentHashMap<Long, MethodHandle>()

    fun resolveSymbol(name: String): Long = findOrThrow(name)

    private fun segment(address: Long): MemorySegment =
        MemorySegment.ofAddress(address)

    private fun handle(fn: Long, shapeId: Int, descriptor: FunctionDescriptor): MethodHandle {
        require(fn != 0L) { "Cannot downcall through null function address" }
        // (fn shl 8) est sans perte : les adresses de fonction sont alignées page,
        // les 8 bits bas sont toujours à zéro.
        return handleCache.computeIfAbsent((fn shl 8) or shapeId.toLong()) {
            linker.downcallHandle(segment(fn), descriptor)
        }
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

    // --- struct-by-value : registre de layouts (M5.2bis) ---

    data class StructField(val cName: String, val kind: FieldKind, val offsetBytes: Long)

    enum class FieldKind { INT8, UINT8, INT16, UINT16, INT32, UINT32, INT64, UINT64, FLOAT32, FLOAT64, POINTER, STRUCT, PADDING }

    private val structLayouts = java.util.concurrent.ConcurrentHashMap<String, Pair<Long, List<StructField>>>()

    fun registerStructLayout(name: String, sizeBytes: Long, alignmentBytes: Long, fields: List<StructField>) {
        structLayouts[name] = sizeBytes to fields
        structDescriptors.remove(name)
    }

    private val structDescriptors = java.util.concurrent.ConcurrentHashMap<String, MemoryLayout>()

    private fun structLayout(name: String): MemoryLayout =
        structDescriptors.computeIfAbsent(name) { structName ->
            val (_, fields) = structLayouts.getValue(structName)
            val elements = fields.map { field ->
                when (field.kind) {
                    FieldKind.PADDING -> MemoryLayout.paddingLayout(field.offsetBytes)
                    else -> primitiveLayout(field.kind).withName(field.cName)
                }
            }
            MemoryLayout.structLayout(*elements.toTypedArray())
        }

    private fun primitiveLayout(kind: FieldKind): ValueLayout = when (kind) {
        FieldKind.INT8, FieldKind.UINT8 -> ValueLayout.JAVA_BYTE
        FieldKind.INT16, FieldKind.UINT16 -> ValueLayout.JAVA_SHORT
        FieldKind.INT32, FieldKind.UINT32 -> ValueLayout.JAVA_INT
        FieldKind.INT64, FieldKind.UINT64 -> ValueLayout.JAVA_LONG
        FieldKind.FLOAT32 -> ValueLayout.JAVA_FLOAT
        FieldKind.FLOAT64 -> ValueLayout.JAVA_DOUBLE
        FieldKind.POINTER -> ValueLayout.ADDRESS
        FieldKind.STRUCT -> error("nested struct layouts resolve in M5.2bis")
        FieldKind.PADDING -> error("padding handled separately")
    }
}
