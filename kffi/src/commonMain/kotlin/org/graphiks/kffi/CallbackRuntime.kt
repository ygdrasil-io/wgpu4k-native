@file:OptIn(
    CallbackRuntimeApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package org.graphiks.kffi

import kotlin.concurrent.atomics.AtomicArray
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

private class AcquiredDelivery<C : Callback>(
    val entry: RegistryEntry<C>,
) {
    fun complete() {
        entry.lifecycle.leave()
    }
}

/**
 * Table d'index token → RegistryEntry. Le token est un compteur monotone
 * (jamais réutilisé) : l'index de slot est token - 1. Croissance par
 * doublement ; les slots libres après close restent null (le token n'est
 * jamais réutilisé, un slot null signifie "plus actif").
 *
 * Concurrence : un slot est écrit UNE fois (publish) et relu en CAS-free
 * (volatile) — l'AtomicArray garantit la visibilité. Le retrait
 * passe le slot à null (pas de copie de map). Le grow est lock-free :
 * le tableau courant est publié par CAS (AtomicReference) et les opérations
 * de slot relisent la référence après leur CAS pour détecter un grow
 * concurrent (un CAS ayant visé un ancien tableau est rejoué sur le
 * tableau courant ; une entrée copiée par le grow est reconnue par
 * identité et comptée une seule fois).
 */
private class TokenIndexTable {
    private val INITIAL_CAPACITY = 64

    // token = index + 1 ; l'index doit tenir dans un Int positif, sinon
    // route() doit renvoyer null comme pour un token inconnu (pas d'index
    // négatif ni de troncature vers un mauvais slot).
    private val MAX_TOKEN = Int.MAX_VALUE.toULong()

    private val slots = AtomicReference(atomicArrayOfNulls<RegistryEntry<*>>(INITIAL_CAPACITY))

    private val count = AtomicLong(0L)

    fun insert(token: ULong, entry: RegistryEntry<*>): Boolean {
        if (token < 1uL || token > MAX_TOKEN) return false
        val index = tokenIndex(token)
        while (true) {
            ensureCapacity(index + 1)
            val current = slots.load()
            if (current.compareAndSetAt(index, null, entry)) {
                if (slots.load() === current) break
                // CAS réussi sur un ancien tableau remplacé par un grow : on
                // rejoue sur le tableau courant ci-dessous.
            } else if (slots.load() === current) {
                // Slot occupé sur le tableau courant : réutilisation de token.
                return false
            }
            // CAS échoué ou visé un ancien tableau. Si le tableau courant
            // contient déjà notre entrée (copiée par le grow), elle est
            // publiée : on compte et on termine.
            if (slots.load().loadAt(index) === entry) break
        }
        count.fetchAndAdd(1)
        return true
    }

    fun remove(token: ULong, entry: RegistryEntry<*>): Boolean {
        if (token < 1uL || token > MAX_TOKEN) return false
        val index = tokenIndex(token)
        var removedFromTable = false
        while (true) {
            val current = slots.load()
            if (index >= current.size) {
                if (removedFromTable) count.fetchAndAdd(-1)
                return removedFromTable
            }
            if (current.compareAndSetAt(index, entry, null)) {
                if (slots.load() === current) {
                    count.fetchAndAdd(-1)
                    return true
                }
                removedFromTable = true
                // Null-out sur un ancien tableau : le tableau courant peut
                // encore contenir l'entrée (copiée avant le null-out).
            } else if (slots.load() === current) {
                if (removedFromTable) count.fetchAndAdd(-1)
                return removedFromTable
            }
            // CAS échoué ou visé un ancien tableau : si le tableau courant ne
            // contient plus l'entrée, le retrait est effectif (le slot y a été
            // copié déjà null, ou nullé ci-dessus).
            if (slots.load().loadAt(index) !== entry) {
                if (removedFromTable) count.fetchAndAdd(-1)
                return removedFromTable
            }
        }
    }

    operator fun get(token: ULong): RegistryEntry<*>? {
        if (token < 1uL || token > MAX_TOKEN) return null
        val index = tokenIndex(token)
        val current = slots.load()
        if (index >= current.size) return null
        return current.loadAt(index)
    }

    val size: Long
        get() = count.load()

    private fun tokenIndex(token: ULong): Int = (token - 1uL).toInt() // token ≥ 1 (monotone)

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

    fun <C : Callback> dispatchSafely(
        type: CallbackType<C>,
        userdata: NativeAddress?,
        invoke: (C) -> Unit,
    ) {
        var delivery: AcquiredDelivery<C>? = null
        try {
            delivery = acquire(type, userdata) ?: return
            try {
                invoke(delivery.entry.callback)
            } catch (failure: Throwable) {
                reportDeliveryFailure(delivery.entry.onError, failure)
            } finally {
                try {
                    delivery.complete()
                } catch (failure: Throwable) {
                    reportDeliveryFailure(delivery.entry.onError, failure)
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

    private fun <C : Callback> acquire(
        type: CallbackType<C>,
        userdata: NativeAddress?,
    ): AcquiredDelivery<C>? {
        val entry = route(type, userdata) ?: return null
        if (!entry.lifecycle.tryEnter()) return null
        if (entry.policy == CallbackPolicy.ONCE) unpublish(entry)
        return AcquiredDelivery(entry)
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
