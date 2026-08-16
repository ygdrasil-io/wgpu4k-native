// NOTE (mainteneur) : fichier généré par `kextract --multiplatform` — le consumer
// ne déclare AUCUN target android, ce fichier n'est donc jamais compilé ; il est
// conservé pour la parité avec la sortie du générateur (regénération idempotente).
package consumer

import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.engine.NativeEngine
import org.graphiks.kffi.engine.UpcallEngine
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
import org.graphiks.kffi.toAddress
import kotlin.OptIn
import kotlin.UnsupportedOperationException
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

private val consumer_negate_ADDR: Long by lazy { NativeEngine.resolveSymbol("consumer_negate") }
actual fun consumer_negate(x: Int): Int {
    return NativeEngine.callI1I(consumer_negate_ADDR, x).toInt()
}

private val consumer_strlen_ADDR: Long by lazy { NativeEngine.resolveSymbol("consumer_strlen") }
actual fun consumer_strlen(s: NativeAddress?): Int {
    return NativeEngine.callI1P(consumer_strlen_ADDR, s.toAddress()).toInt()
}

private val consumer_set_string_ADDR: Long by lazy { NativeEngine.resolveSymbol("consumer_set_string") }
actual fun consumer_set_string(s: NativeAddress?): Unit {
    NativeEngine.callV1P(consumer_set_string_ADDR, s.toAddress())
    return
}

private val consumer_get_ping_ADDR: Long by lazy { NativeEngine.resolveSymbol("consumer_get_ping") }
actual fun consumer_get_ping(out: NativeAddress?): Unit {
    NativeEngine.callV1P(consumer_get_ping_ADDR, out.toAddress())
    return
}

