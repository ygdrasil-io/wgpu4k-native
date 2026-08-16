package consumer

import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.engine.JvmDowncallEngine
import org.graphiks.kffi.engine.JvmUpcallEngine
import org.graphiks.kffi.CallbackExceptionHandler
import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.CallbackRuntime
import org.graphiks.kffi.CallbackRuntimeApi
import org.graphiks.kffi.PreparedCallbackRegistration
import org.graphiks.kffi.UnsafeCallbackRearmApi
import org.graphiks.kffi.CString
import org.graphiks.kffi.ArrayHolder
import org.graphiks.kffi.MemoryAllocator
import org.graphiks.kffi.MemoryBuffer
import org.graphiks.kffi.findOrThrow
import kotlin.OptIn
import kotlin.Suppress
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

private object KextractNativeBootstrap {
    @kotlin.jvm.Volatile private var loaded: kotlin.Boolean = false

    fun resolve(name: kotlin.String): kotlin.Long {
        load()
        return findOrThrow(name)
    }

    private fun load() {
        if (loaded) return
        kotlin.synchronized(this) {
            if (loaded) return
            java.lang.System.loadLibrary("consumer")
            loaded = true
        }
    }
}

private val consumer_negate_ADDR: Long by lazy { KextractNativeBootstrap.resolve("consumer_negate") }
actual fun consumer_negate(x: Int): Int {
    return JvmDowncallEngine.callI1I(consumer_negate_ADDR, x).toInt()
}

private val consumer_strlen_ADDR: Long by lazy { KextractNativeBootstrap.resolve("consumer_strlen") }
actual fun consumer_strlen(s: NativeAddress?): Int {
    return JvmDowncallEngine.callI1P(consumer_strlen_ADDR, s?.rawValue ?: 0L).toInt()
}

private val consumer_set_string_ADDR: Long by lazy { KextractNativeBootstrap.resolve("consumer_set_string") }
actual fun consumer_set_string(s: NativeAddress?): Unit {
    JvmDowncallEngine.callV1P(consumer_set_string_ADDR, s?.rawValue ?: 0L)
    return
}

private val consumer_get_ping_ADDR: Long by lazy { KextractNativeBootstrap.resolve("consumer_get_ping") }
actual fun consumer_get_ping(out: NativeAddress?): Unit {
    JvmDowncallEngine.callV1P(consumer_get_ping_ADDR, out?.rawValue ?: 0L)
    return
}
