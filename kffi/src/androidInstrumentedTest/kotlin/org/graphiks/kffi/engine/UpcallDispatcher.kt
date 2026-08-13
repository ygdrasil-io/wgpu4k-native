package org.graphiks.kffi.engine

import org.graphiks.kffi.Callback
import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.CallbackRuntime
import org.graphiks.kffi.CallbackRuntimeApi
import org.graphiks.kffi.CallbackType
import org.graphiks.kffi.NativeAddress

@OptIn(CallbackRuntimeApi::class)
object UpcallDispatcher {

    fun interface BenchCallback : Callback {
        fun invoke(value: UInt)
    }

    private val type: CallbackType<BenchCallback> = CallbackType(
        canonicalId = "bench:UpcallDispatcher",
        hasRoutingUserdata = true,
    )

    @Volatile
    var lastValue: Int = -1

    fun register(): CallbackRegistration<BenchCallback> =
        CallbackRuntime.register(
            type = type,
            trampoline = NativeAddress(0L), // unused for routing; token routing is via userdata
            policy = CallbackPolicy.REPEATING,
            callback = BenchCallback { lastValue = it.toInt() },
        )

    @JvmStatic
    fun dispatch(token: Long, value: Int) {
        CallbackRuntime.dispatchSafely(type, NativeAddress(token)) { cb ->
            cb.invoke(value.toUInt())
        }
    }
}
