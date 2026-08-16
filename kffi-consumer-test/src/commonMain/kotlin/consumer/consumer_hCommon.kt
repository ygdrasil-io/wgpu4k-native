package consumer

import org.graphiks.kffi.NativeAddress
import org.graphiks.kffi.Callback
import org.graphiks.kffi.CallbackExceptionHandler
import org.graphiks.kffi.CallbackPolicy
import org.graphiks.kffi.CallbackRegistration
import org.graphiks.kffi.CallbackRuntimeApi
import org.graphiks.kffi.CallbackType
import org.graphiks.kffi.PreparedCallbackRegistration
import org.graphiks.kffi.UnsafeCallbackRearmApi
import org.graphiks.kffi.CString
import org.graphiks.kffi.ArrayHolder
import org.graphiks.kffi.MemoryAllocator
import kotlin.OptIn

expect fun consumer_negate(x: Int): Int

expect fun consumer_strlen(s: NativeAddress?): Int

expect fun consumer_set_string(s: NativeAddress?): Unit

expect fun consumer_get_ping(out: NativeAddress?): Unit

