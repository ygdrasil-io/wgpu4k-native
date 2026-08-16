package org.graphiks.kffi.engine

import org.graphiks.kffi.NativeAddress
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.util.concurrent.ConcurrentHashMap

/**
 * Moteur d'upcall JVM : trampolines par forme de signature.
 *
 * Chaque forme expose un trampoline qui enregistre un handler sous un token
 * userdata et retourne un stub natif ; le natif appelle le stub avec le token
 * en premier argument (mirroir de l'emetteur kextract, dont les descripteurs
 * placent le userdata en tete des parametres bruts), et le moteur route vers
 * le handler enregistre. Le code genere par kextract ne reference que ces
 * methodes — jamais Linker/MethodHandles/FunctionDescriptor.
 */
object JvmUpcallEngine {

    private val linker = Linker.nativeLinker()
    private val arena = Arena.global()

    // --- forme V2PP : void (userdata, ptr, ptr) ---

    private val v2PPDescriptor = FunctionDescriptor.ofVoid(
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
    )
    private val v2PPTarget = MethodHandles.lookup().findStatic(
        JvmUpcallEngine::class.java,
        "invokeV2PP",
        v2PPDescriptor.toMethodType(),
    )
    private val v2PPHandlers = ConcurrentHashMap<Long, (Long, Long) -> Unit>()

    fun trampolineV2PP(userdata: Long, handler: (Long, Long) -> Unit): NativeAddress {
        v2PPHandlers[userdata] = handler
        val stub = linker.upcallStub(v2PPTarget, v2PPDescriptor, arena)
        return NativeAddress(stub.address())
    }

    @JvmStatic
    private fun invokeV2PP(userdata: MemorySegment, a1: MemorySegment, a2: MemorySegment) {
        v2PPHandlers[userdata.address()]?.invoke(a1.address(), a2.address())
    }
}
