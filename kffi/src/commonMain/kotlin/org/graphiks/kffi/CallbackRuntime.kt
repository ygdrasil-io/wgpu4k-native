@file:OptIn(
    CallbackRuntimeApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package org.graphiks.kffi

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.AtomicReference
import kotlin.concurrent.atomics.atomicArrayOfNulls

internal enum class DeliveryState {
    PREPARED,
    ACTIVE,
    CLAIMING,
    CLAIMED,
    CLOSED,
    ABORTED,
}

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
        get() {
            val s = unpackState(packed.load())
            return s != DeliveryState.PREPARED && s != DeliveryState.ACTIVE
        }

    val isQuiescent: Boolean
        get() {
            val raw = packed.load()
            val s = unpackState(raw)
            return s != DeliveryState.PREPARED && s != DeliveryState.ACTIVE &&
                unpackInFlight(raw) == 0
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
            val currentInFlight = unpackInFlight(current)
            check(currentInFlight > 0) { "Callback delivery left without entering" }
            if (
                packed.compareAndSet(
                    current,
                    pack(unpackState(current), currentInFlight - 1),
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

    // bits 0-3 = state, bits 4+ = inFlight
    private companion object {
        private const val STATE_BITS = 4
        private const val IN_FLIGHT_SHIFT = STATE_BITS
        private const val STATE_MASK = (1L shl STATE_BITS) - 1

        init {
            require(DeliveryState.entries.size <= (1 shl STATE_BITS)) {
                "DeliveryState has ${DeliveryState.entries.size} values; " +
                    "packed encoding supports ${1 shl STATE_BITS}"
            }
        }

        private fun pack(state: DeliveryState, inFlight: Int): Long =
            (state.ordinal.toLong() and STATE_MASK) or (inFlight.toLong() shl IN_FLIGHT_SHIFT)

        private fun unpackState(packed: Long): DeliveryState =
            DeliveryState.entries[(packed and STATE_MASK).toInt()]

        private fun unpackInFlight(packed: Long): Int =
            (packed shr IN_FLIGHT_SHIFT).toInt()
    }
}

internal enum class NoUserdataPhase {
    UNUSED,
    ACTIVE,
    RETIRED,
}

private class NoUserdataSnapshot<T : Any>(
    val phase: NoUserdataPhase,
    val value: T?,
)

internal class NoUserdataSlotStateMachine<T : Any> {
    private val snapshot = AtomicReference(
        NoUserdataSnapshot<T>(NoUserdataPhase.UNUSED, null),
    )

    val phase: NoUserdataPhase
        get() = snapshot.load().phase

    val activeValue: T?
        get() = snapshot.load().takeIf { it.phase == NoUserdataPhase.ACTIVE }?.value

    fun activate(value: T): Boolean = transitionToActive(NoUserdataPhase.UNUSED, value)

    fun rearm(value: T): Boolean = transitionToActive(NoUserdataPhase.RETIRED, value)

    private fun transitionToActive(expectedPhase: NoUserdataPhase, value: T): Boolean {
        val current = snapshot.load()
        if (current.phase != expectedPhase) return false
        return snapshot.compareAndSet(
            current,
            NoUserdataSnapshot(NoUserdataPhase.ACTIVE, value),
        )
    }

    fun retire(value: T): Boolean {
        val current = snapshot.load()
        if (current.phase != NoUserdataPhase.ACTIVE || current.value !== value) return false
        return snapshot.compareAndSet(
            current,
            NoUserdataSnapshot(NoUserdataPhase.RETIRED, null),
        )
    }
}

/** Canonical generated descriptor for one callback typedef. */
@CallbackRuntimeApi
class CallbackType<C : Callback>(
    val canonicalId: String,
    val hasRoutingUserdata: Boolean,
) {
    init {
        require(canonicalId.isNotBlank()) { "Callback canonical ID must not be blank" }
    }

    internal val noUserdataSlot = NoUserdataSlotStateMachine<RegistryEntry<C>>()
}

internal class RegistryEntry<C : Callback>(
    val type: CallbackType<C>,
    val callback: C,
    val policy: CallbackPolicy,
    val onError: CallbackExceptionHandler,
    val token: ULong?,
    initialState: DeliveryState,
) {
    val lifecycle = DeliveryStateMachine(policy, initialState)
}

private class RuntimeCallbackRegistration<C : Callback>(
    override val callback: NativeAddress,
    override val userdata: NativeAddress?,
    internal val entry: RegistryEntry<C>,
) : CallbackRegistration<C> {
    override val policy: CallbackPolicy
        get() = entry.policy

    override val isClosed: Boolean
        get() = entry.lifecycle.isClosed

    override val isQuiescent: Boolean
        get() = entry.lifecycle.isQuiescent

    override fun close() {
        CallbackRuntime.close(entry)
    }
}

/** A generated binding's non-published registration for a transactional native call. */
@CallbackRuntimeApi
class PreparedCallbackRegistration<C : Callback> internal constructor(
    val callback: NativeAddress,
    val userdata: NativeAddress?,
    internal val entry: RegistryEntry<C>,
) : AutoCloseable {
    val policy: CallbackPolicy
        get() = entry.policy

    val isAborted: Boolean
        get() = entry.lifecycle.state == DeliveryState.ABORTED

    override fun close() {
        entry.lifecycle.abort()
    }
}

/**
 * Table d'index token → RegistryEntry. Le token est un compteur monotone
 * (jamais réutilisé) : l'index de slot est token - 1. Croissance par
 * doublement ; les slots libres après close restent null (le token n'est
 * jamais réutilisé, un slot null signifie "plus actif").
 *
 * Concurrence : un slot est écrit UNE fois (publish) et relu en CAS-free
 * (volatile) — l'AtomicArray garantit la visibilité. Le retrait passe le
 * slot à null (pas de copie de map). Le grow est lock-free : la génération
 * courante est publiée par CAS (AtomicReference) ; toute opération de slot
 * relit la référence après son CAS et rejoue sur la génération courante si
 * un grow a remplacé la sienne, reconnaissant par identité une entrée déjà
 * copiée par le grow.
 *
 * Invariant de compteur : count == nombre d'entrées visibles dans la
 * génération courante (slots non null). L'API du runtime publie une entrée
 * au plus une fois et la retire au plus une fois (lifecycle) ; chaque
 * insert qui publie incrémente une seule fois, chaque remove qui retire de
 * la table décrémente une seule fois — les rejouages ci-dessus sont
 * exactement ce qui maintient cet équilibre lors d'un grow concurrent.
 *
 * Terminaison : chaque itération de rejeu consomme une nouvelle génération
 * (publiée par un grow) ; la capacité double à chaque grow et plafonne à
 * Int.MAX_VALUE, donc le nombre total de générations est borné par
 * O(log(MAX_INDEXABLE_TOKEN)) et toute boucle termine.
 *
 * Bornes de token : l'index (token - 1) doit tenir dans un Int positif —
 * plafond MAX_INDEXABLE_TOKEN = 2³¹−1, en dessous de la plage validée par
 * le codec (1..Long.MAX_VALUE, requireValidCallbackToken). Un token hors
 * plafond est rejeté par require avec un message dédié (jamais confondu
 * avec une réutilisation) dans insert/remove, et lu comme "inconnu"
 * (null) dans get.
 */
private class TokenIndexTable {
    private val INITIAL_CAPACITY = 64

    // token = index + 1 ; l'index doit tenir dans un Int positif.
    private val MAX_INDEXABLE_TOKEN = Int.MAX_VALUE.toULong()

    private val slots = AtomicReference(atomicArrayOfNulls<RegistryEntry<*>>(INITIAL_CAPACITY))

    private val count = AtomicLong(0L)

    fun insert(token: ULong, entry: RegistryEntry<*>): Boolean {
        val index = tokenIndex(token)
        while (true) {
            ensureCapacity(index + 1)
            val current = slots.load()
            val published = current.compareAndSetAt(index, null, entry)
            val latest = slots.load()
            if (latest === current) {
                // Génération stable pendant le CAS : le résultat est définitif.
                if (published) break
                return false
            }
            // Un grow a publié une génération plus récente pendant le CAS.
            // Si notre entrée y a été copiée (CAS réussi avant la copie),
            // elle est déjà publiée : on compte et on termine.
            if (published && latest.loadAt(index) === entry) break
            // Sinon, rejouer sur la génération courante.
        }
        count.fetchAndAdd(1)
        return true
    }

    fun remove(token: ULong, entry: RegistryEntry<*>): Boolean {
        val index = tokenIndex(token)
        var removedFromTable = false
        while (true) {
            val current = slots.load()
            if (index >= current.size) {
                // Atteint uniquement à la première itération (token jamais
                // inséré, aucune génération ne le couvre) : removedFromTable y
                // est nécessairement faux, car un CAS réussi a requis
                // size > index et les générations ne rétrécissent jamais.
                return removedFromTable
            }
            val nulled = current.compareAndSetAt(index, entry, null)
            val latest = slots.load()
            if (latest === current) {
                if (nulled) {
                    count.fetchAndAdd(-1)
                    return true
                }
                // Le slot de la génération courante ne contient pas cette
                // entrée (déjà retirée, ou entrée étrangère).
                if (removedFromTable) count.fetchAndAdd(-1)
                return removedFromTable
            }
            if (nulled) removedFromTable = true
            // Null-out visé une ancienne génération : la génération courante
            // peut encore contenir l'entrée (copiée avant le null-out) — on
            // rejoue pour l'y retirer aussi.
            if (latest.loadAt(index) !== entry) {
                // La génération courante ne contient plus l'entrée : le retrait
                // est effectif (copiée déjà null, ou nullée ci-dessus).
                if (removedFromTable) count.fetchAndAdd(-1)
                return removedFromTable
            }
            // L'entrée est encore présente dans la génération courante :
            // retenter le CAS.
        }
    }

    operator fun get(token: ULong): RegistryEntry<*>? {
        // route() reste null-safe pour tout token (décodage non validé d'un
        // pointeur étranger) : hors plafond ⇒ inconnu, comme l'ancienne map.
        if (token < 1uL || token > MAX_INDEXABLE_TOKEN) return null
        val index = (token - 1uL).toInt()
        val current = slots.load()
        if (index >= current.size) return null
        return current.loadAt(index)
    }

    val size: Long
        get() = count.load()

    private fun tokenIndex(token: ULong): Int {
        require(token in 1uL..MAX_INDEXABLE_TOKEN) {
            "Callback token $token is outside the token index table range (1..$MAX_INDEXABLE_TOKEN)"
        }
        return (token - 1uL).toInt() // token ≥ 1 (monotone)
    }

    private fun ensureCapacity(required: Int) {
        while (true) {
            val current = slots.load()
            if (required <= current.size) return
            var newCapacity = current.size
            while (newCapacity < required) {
                newCapacity = if (newCapacity > Int.MAX_VALUE / 2) Int.MAX_VALUE else newCapacity * 2
            }
            val grown = atomicArrayOfNulls<RegistryEntry<*>>(newCapacity)
            for (i in 0 until current.size) grown.storeAt(i, current.loadAt(i))
            if (slots.compareAndSet(current, grown)) return
        }
    }
}

/** Registration and dispatch primitives reserved for generated callback bindings. */
@CallbackRuntimeApi
object CallbackRuntime {
    private val lastAllocatedToken = AtomicLong(0L)
    private val tokenIndexTable = TokenIndexTable()
    private val activeNoUserdataRegistrations = AtomicInt(0)

    fun <C : Callback> register(
        type: CallbackType<C>,
        trampoline: NativeAddress,
        policy: CallbackPolicy,
        onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
        callback: C,
    ): CallbackRegistration<C> {
        val token = if (type.hasRoutingUserdata) allocateToken() else null
        val entry = RegistryEntry(
            type = type,
            callback = callback,
            policy = policy,
            onError = onError,
            token = token,
            initialState = DeliveryState.ACTIVE,
        )
        val registration = RuntimeCallbackRegistration(
            callback = trampoline,
            userdata = token?.let(PlatformCallbackTokenAddressCodec::encode),
            entry = entry,
        )
        publish(entry, allowNoUserdataRearm = false)
        return registration
    }

    fun <C : Callback> prepare(
        type: CallbackType<C>,
        trampoline: NativeAddress,
        policy: CallbackPolicy,
        onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
        callback: C,
    ): PreparedCallbackRegistration<C> {
        val token = if (type.hasRoutingUserdata) allocateToken() else null
        return PreparedCallbackRegistration(
            callback = trampoline,
            userdata = token?.let(PlatformCallbackTokenAddressCodec::encode),
            entry = RegistryEntry(
                type = type,
                callback = callback,
                policy = policy,
                onError = onError,
                token = token,
                initialState = DeliveryState.PREPARED,
            ),
        )
    }

    fun <C : Callback> activateForNativeCall(
        prepared: PreparedCallbackRegistration<C>,
        downcall: (CallbackRegistration<C>) -> Unit,
    ): CallbackRegistration<C> {
        val entry = prepared.entry
        check(entry.lifecycle.activate()) { "Prepared callback registration is no longer available" }
        val registration = RuntimeCallbackRegistration(
            callback = prepared.callback,
            userdata = prepared.userdata,
            entry = entry,
        )
        try {
            publish(entry, allowNoUserdataRearm = false)
            downcall(registration)
            return registration
        } catch (failure: Throwable) {
            registration.close()
            throw failure
        }
    }

    /**
     * Re-arms a retired callback slot after the caller has established native quiescence.
     * This operation neither performs nor verifies native quiescence.
     */
    @UnsafeCallbackRearmApi
    fun <C : Callback> rearmAfterNativeQuiescence(
        type: CallbackType<C>,
        trampoline: NativeAddress,
        policy: CallbackPolicy,
        onError: CallbackExceptionHandler = CallbackExceptionHandler.Default,
        callback: C,
    ): CallbackRegistration<C> {
        check(!type.hasRoutingUserdata) {
            "Only callback types without routing userdata require unsafe re-arming"
        }
        val entry = RegistryEntry(
            type = type,
            callback = callback,
            policy = policy,
            onError = onError,
            token = null,
            initialState = DeliveryState.ACTIVE,
        )
        val registration = RuntimeCallbackRegistration(
            callback = trampoline,
            userdata = null,
            entry = entry,
        )
        publish(entry, allowNoUserdataRearm = true)
        return registration
    }

    /**
     * Dispatch sécurisé d'un upcall natif : point d'entrée du moteur
     * d'upcall. Aucun Throwable ne s'échappe vers la native — tout est
     * contenu et routé vers l'un des deux canaux de signalement :
     *
     * - échecs de routage et d'entrée (route, tryEnter, unpublish ONCE)
     *   → reportUnroutedFailure ;
     * - échecs du callback et du leave → reportDeliveryFailure(onError).
     *
     * Le leave est gardé par le try/finally interne : il tourne au plus
     * une fois, uniquement après un tryEnter réussi, et son propre échec
     * est contenu (jamais propagé, donc jamais masquant) — signalé via
     * onError. Un token inconnu ou un tryEnter échoué est un no-op
     * silencieux : return sans effet de bord (ni leave, ni signalement).
     */
    fun <C : Callback> dispatchSafely(
        type: CallbackType<C>,
        userdata: NativeAddress?,
        invoke: (C) -> Unit,
    ) {
        try {
            val entry = route(type, userdata) ?: return
            if (!entry.lifecycle.tryEnter()) return
            // ONCE : le retrait ne vient qu'après un claim réussi — un racer
            // perdant ne dé-publie jamais. Si unpublish lance, l'entrée reste
            // en CLAIMED sans leave (cas préexistant, comportement inchangé).
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

    fun reportUnroutedFailure(error: Throwable) {
        reportUnhandledCallbackException(error)
    }

    internal fun activeRegistrationCountForTest(): Int =
        tokenIndexTable.size.toInt() + activeNoUserdataRegistrations.load()

    internal fun <C : Callback> close(entry: RegistryEntry<C>) {
        if (!entry.lifecycle.close()) return
        unpublish(entry)
    }

    private fun allocateToken(): ULong {
        while (true) {
            val previous = lastAllocatedToken.load()
            if (previous == Long.MAX_VALUE) {
                throw IllegalStateException("Callback token space exhausted")
            }
            val next = previous + 1L
            if (lastAllocatedToken.compareAndSet(previous, next)) return next.toULong()
        }
    }

    private fun <C : Callback> publish(
        entry: RegistryEntry<C>,
        allowNoUserdataRearm: Boolean,
    ) {
        val token = entry.token
        if (token != null) {
            insertToken(token, entry)
            return
        }

        val published = if (allowNoUserdataRearm) {
            entry.type.noUserdataSlot.rearm(entry)
        } else {
            entry.type.noUserdataSlot.activate(entry)
        }
        if (!published) {
            entry.lifecycle.close()
            val phase = entry.type.noUserdataSlot.phase
            throw IllegalStateException(
                "Callback type '${entry.type.canonicalId}' cannot be registered from no-userdata state $phase",
            )
        }
        activeNoUserdataRegistrations.fetchAndAdd(1)
    }

    private fun insertToken(token: ULong, entry: RegistryEntry<*>) {
        check(tokenIndexTable.insert(token, entry)) { "Callback token was unexpectedly reused" }
    }

    private fun unpublish(entry: RegistryEntry<*>) {
        val token = entry.token
        if (token != null) {
            removeToken(token, entry)
        } else {
            retireNoUserdata(entry)
        }
    }

    private fun removeToken(token: ULong, entry: RegistryEntry<*>): Boolean =
        tokenIndexTable.remove(token, entry)

    @Suppress("UNCHECKED_CAST")
    private fun retireNoUserdata(entry: RegistryEntry<*>): Boolean {
        val typedEntry = entry as RegistryEntry<Callback>
        val retired = typedEntry.type.noUserdataSlot.retire(typedEntry)
        if (retired) activeNoUserdataRegistrations.fetchAndAdd(-1)
        return retired
    }

    @Suppress("UNCHECKED_CAST")
    private fun <C : Callback> route(
        type: CallbackType<C>,
        userdata: NativeAddress?,
    ): RegistryEntry<C>? {
        if (!type.hasRoutingUserdata) {
            require(userdata == null) {
                "Callback type '${type.canonicalId}' does not accept routing userdata"
            }
            return type.noUserdataSlot.activeValue
        }

        val token = requireNotNull(PlatformCallbackTokenAddressCodec.decode(userdata)) {
            "Callback type '${type.canonicalId}' requires routing userdata"
        }
        val entry = tokenIndexTable[token] ?: return null
        require(entry.type === type) {
            "Callback token $token belongs to '${entry.type.canonicalId}', not '${type.canonicalId}'"
        }
        return entry as RegistryEntry<C>
    }

    private fun reportDeliveryFailure(
        handler: CallbackExceptionHandler,
        callbackFailure: Throwable,
    ) {
        try {
            handler.onException(callbackFailure)
        } catch (handlerFailure: Throwable) {
            reportUnroutedFailure(
                CallbackExceptionHandlerFailure(callbackFailure, handlerFailure),
            )
        }
    }
}
