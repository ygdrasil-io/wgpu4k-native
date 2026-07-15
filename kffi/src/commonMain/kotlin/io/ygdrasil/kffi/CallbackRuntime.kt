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
    CLAIMING,
    CLAIMED,
    CLOSED,
    ABORTED,
}

private class DeliverySnapshot(
    val state: DeliveryState,
    val inFlight: Int,
)

internal class DeliveryStateMachine(
    private val policy: CallbackPolicy,
    initialState: DeliveryState = DeliveryState.ACTIVE,
    private val beforeTryEnterCompareAndSet: (() -> Unit)? = null,
) {
    private val snapshotRef = AtomicReference(DeliverySnapshot(initialState, 0))

    val state: DeliveryState
        get() = snapshotRef.load().state

    val inFlight: Int
        get() = snapshotRef.load().inFlight

    val isClosed: Boolean
        get() = snapshotRef.load().state !in setOf(DeliveryState.PREPARED, DeliveryState.ACTIVE)

    val isQuiescent: Boolean
        get() = snapshotRef.load().let { current ->
            current.state !in setOf(DeliveryState.PREPARED, DeliveryState.ACTIVE) &&
                current.inFlight == 0
        }

    fun activate(): Boolean = transitionState(DeliveryState.PREPARED, DeliveryState.ACTIVE)

    fun abort(): Boolean = transitionState(DeliveryState.PREPARED, DeliveryState.ABORTED)

    fun tryEnter(): Boolean = when (policy) {
        CallbackPolicy.ONCE -> tryEnterOnce()
        CallbackPolicy.REPEATING -> tryEnterRepeating()
    }

    private fun tryEnterOnce(): Boolean {
        val current = snapshotRef.load()
        if (current.state != DeliveryState.ACTIVE) return false
        beforeTryEnterCompareAndSet?.invoke()
        val claiming = DeliverySnapshot(DeliveryState.CLAIMING, 1)
        if (!snapshotRef.compareAndSet(current, claiming)) return false
        check(
            snapshotRef.compareAndSet(
                claiming,
                DeliverySnapshot(DeliveryState.CLAIMED, claiming.inFlight),
            ),
        ) { "ONCE callback claim was unexpectedly modified" }
        return true
    }

    private fun tryEnterRepeating(): Boolean {
        while (true) {
            val current = snapshotRef.load()
            if (current.state != DeliveryState.ACTIVE) return false
            beforeTryEnterCompareAndSet?.invoke()
            if (
                snapshotRef.compareAndSet(
                    current,
                    DeliverySnapshot(DeliveryState.ACTIVE, current.inFlight + 1),
                )
            ) {
                return true
            }
        }
    }

    fun leave() {
        while (true) {
            val current = snapshotRef.load()
            check(current.inFlight > 0) { "Callback delivery left without entering" }
            if (
                snapshotRef.compareAndSet(
                    current,
                    DeliverySnapshot(current.state, current.inFlight - 1),
                )
            ) {
                return
            }
        }
    }

    fun close(): Boolean = transitionState(DeliveryState.ACTIVE, DeliveryState.CLOSED)

    private fun transitionState(expected: DeliveryState, updated: DeliveryState): Boolean {
        while (true) {
            val current = snapshotRef.load()
            if (current.state != expected) return false
            if (
                snapshotRef.compareAndSet(
                    current,
                    DeliverySnapshot(updated, current.inFlight),
                )
            ) {
                return true
            }
        }
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

        val token = requireNotNull(PlatformCallbackTokenAddressCodec.decode(userdata)) {
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
