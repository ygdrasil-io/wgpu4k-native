package org.graphiks.kffi.engine

import org.graphiks.kffi.NativeAddress
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles

/**
 * Moteur d'upcall JVM : fabrique de trampolines par forme de signature.
 *
 * Symétrique d'UpcallEngine (Android). Le code généré (kextract) fournit la
 * classe + méthode statique du dispatcher et sa signature encodée ; le moteur
 * construit le stub FFM correspondant. Le moteur ne route pas : le routage par
 * token reste dans le dispatcher généré via CallbackRuntime.dispatchSafely, le
 * userdata occupant sa position C réelle (dernier paramètre pour wgpu).
 *
 * Encodage dispatchSig (convention Java : I=int, J=long, F=float, D=double,
 * Z=boolean, V=void) — les pointeurs sont encodés J (carrier long 64-bit,
 * ABI-identique sur les plateformes cibles, comme le moteur C Android
 * utilise jlong). V en retour produit un descripteur ofVoid.
 *
 * La résolution de la méthode de dispatch utilise privateLookupIn : elle
 * accepte les objets trampoline privés générés par kextract, à l'instar de
 * GetStaticMethodID côté Android (kffi_upcall.c) qui ignore les contrôles
 * d'accès Java. Précondition : classpath (modules non nommés, packages
 * ouverts) — si kffi ou le consommateur passe sur le module path (module
 * nommé), l'accès lèvera IllegalAccessException ; à revoir à ce moment.
 *
 * NOTE NATIVE ACCESS : les formes de callbacks que ce moteur ne peut pas
 * exprimer (upcalls struct-by-value, scalaires I8/I16/CHAR16, indirections
 * multiples) sont émises par kextract sur un chemin de secours FFM direct
 * (`KotlinCallbackJvmEmitter.emitFfmTrampoline`) qui appelle
 * `Linker.upcallStub`/`MethodHandles.lookup()` dans le code généré. Ce chemin
 * est de la dette documentée (handover P3) et exige
 * `--enable-native-access=ALL-UNNAMED` au lancement, faute de quoi la JVM
 * émet un warning (et bloquera l'appel dans une future version du JDK).
 */
object JvmUpcallEngine {

    private val linker = Linker.nativeLinker()
    private val arena = Arena.global()

    /**
     * Crée un trampoline appelant la méthode statique [dispatchMethod] de
     * [dispatcherClass] avec la signature [dispatchSig] (ex. "(IIJ)V").
     * Les paramètres du stub suivent l'ordre C : le premier reçoit le premier
     * argument C, le dernier le userdata/token si le callback est routé.
     */
    fun allocateTrampoline(
        dispatcherClass: Class<*>,
        dispatchMethod: String,
        dispatchSig: String,
    ): NativeAddress {
        val (returnType, parameterTypes) = parseSig(dispatchSig)
        val descriptor = if (returnType == null) {
            FunctionDescriptor.ofVoid(*parameterTypes.map { it.layout }.toTypedArray())
        } else {
            FunctionDescriptor.of(returnType.layout, *parameterTypes.map { it.layout }.toTypedArray())
        }
        val methodHandle = MethodHandles.privateLookupIn(dispatcherClass, MethodHandles.lookup())
            .findStatic(dispatcherClass, dispatchMethod, descriptor.toMethodType())
        return NativeAddress(linker.upcallStub(methodHandle, descriptor, arena).address())
    }

    private enum class Carrier(val layout: ValueLayout) {
        I(ValueLayout.JAVA_INT),
        J(ValueLayout.JAVA_LONG),
        F(ValueLayout.JAVA_FLOAT),
        D(ValueLayout.JAVA_DOUBLE),
        Z(ValueLayout.JAVA_BOOLEAN),
    }

    /** "(IIJ)V" -> retour null (void) + paramètres [I, I, J]. */
    private fun parseSig(sig: String): Pair<Carrier?, List<Carrier>> {
        val parameters = sig.substringAfter('(').substringBefore(')')
            .map { Carrier.valueOf(it.toString()) }
        val returnPart = sig.substringAfter(')')
        return (if (returnPart == "V") null else Carrier.valueOf(returnPart)) to parameters
    }
}
