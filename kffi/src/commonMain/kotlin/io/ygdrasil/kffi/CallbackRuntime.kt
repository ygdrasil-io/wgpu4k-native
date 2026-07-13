@file:OptIn(
    CallbackRuntimeApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.kffi

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.AtomicReference

internal enum class DeliveryState {
    PREPARED,
    ACTIVE,
    CLAIMED,
    CLOSED,
    ABORTED,
}

internal class DeliveryStateMachine(
    private val policy: CallbackPolicy,
    initialState: DeliveryState = DeliveryState.ACTIVE,
    private val stateRef: AtomicReference<DeliveryState> = AtomicReference(initialState),
    private val inFlightRef: AtomicInt = AtomicInt(0),
) {
    val state: DeliveryState
        get() = stateRef.load()

    val inFlight: Int
        get() = inFlightRef.load()

    val isClosed: Boolean
        get() = state !in setOf(DeliveryState.PREPARED, DeliveryState.ACTIVE)

    fun activate(): Boolean = stateRef.compareAndSet(DeliveryState.PREPARED, DeliveryState.ACTIVE)

    fun abort(): Boolean = stateRef.compareAndSet(DeliveryState.PREPARED, DeliveryState.ABORTED)

    fun tryEnter(): Boolean = when (policy) {
        CallbackPolicy.ONCE -> stateRef.compareAndSet(DeliveryState.ACTIVE, DeliveryState.CLAIMED)
        CallbackPolicy.REPEATING -> tryEnterRepeating()
    }

    private fun tryEnterRepeating(): Boolean {
        if (stateRef.load() != DeliveryState.ACTIVE) return false
        inFlightRef.fetchAndAdd(1)
        if (stateRef.load() == DeliveryState.ACTIVE) return true
        inFlightRef.fetchAndAdd(-1)
        return false
    }

    fun leave() {
        check(policy == CallbackPolicy.REPEATING) { "Only REPEATING callbacks have in-flight deliveries" }
        check(inFlightRef.fetchAndAdd(-1) > 0) { "Callback delivery left without entering" }
    }

    fun close(): Boolean = stateRef.compareAndSet(DeliveryState.ACTIVE, DeliveryState.CLOSED)
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
    val state = AtomicReference(initialState)
    val inFlight = AtomicInt(0)
    val lifecycle = DeliveryStateMachine(policy, initialState, state, inFlight)
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
        if (entry.policy == CallbackPolicy.REPEATING) entry.lifecycle.leave()
    }
}

/** Registration and dispatch primitives reserved for generated callback bindings. */
@CallbackRuntimeApi
object CallbackRuntime {
    private const val BUCKET_COUNT = 64

    private val lastAllocatedToken = AtomicLong(0L)
    private val buckets = List(BUCKET_COUNT) {
        AtomicReference<Map<ULong, RegistryEntry<*>>>(emptyMap())
    }
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
            userdata = token?.let(PlatformTokenCodec::encode),
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
            userdata = token?.let(PlatformTokenCodec::encode),
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
        buckets.sumOf { it.load().size } + activeNoUserdataRegistrations.load()

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
        val bucket = bucket(token)
        while (true) {
            val current = bucket.load()
            check(token !in current) { "Callback token was unexpectedly reused" }
            if (bucket.compareAndSet(current, current + (token to entry))) return
        }
    }

    private fun unpublish(entry: RegistryEntry<*>) {
        val token = entry.token
        if (token != null) {
            removeToken(token, entry)
        } else {
            retireNoUserdata(entry)
        }
    }

    private fun removeToken(token: ULong, entry: RegistryEntry<*>): Boolean {
        val bucket = bucket(token)
        while (true) {
            val current = bucket.load()
            if (current[token] !== entry) return false
            if (bucket.compareAndSet(current, current - token)) return true
        }
    }

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

        val token = requireNotNull(PlatformTokenCodec.decode(userdata)) {
            "Callback type '${type.canonicalId}' requires routing userdata"
        }
        val entry = bucket(token).load()[token] ?: return null
        require(entry.type === type) {
            "Callback token $token belongs to '${entry.type.canonicalId}', not '${type.canonicalId}'"
        }
        return entry as RegistryEntry<C>
    }

    private fun bucket(token: ULong): AtomicReference<Map<ULong, RegistryEntry<*>>> =
        buckets[(token % BUCKET_COUNT.toULong()).toInt()]

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
