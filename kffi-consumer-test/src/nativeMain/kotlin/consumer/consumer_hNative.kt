@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package consumer

import org.graphiks.kffi.NativeAddress
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
import org.graphiks.kffi.toCString
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CValue
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.ShortVar
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.ULongVar
import kotlinx.cinterop.UShortVar
import kotlinx.cinterop.cValue
import kotlinx.cinterop.get
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.useContents
import kotlin.OptIn

actual fun consumer_negate(x: Int): Int {
    return webgpu.native.consumer_negate(x)
}

actual fun consumer_strlen(s: NativeAddress?): Int {
    return webgpu.native.consumer_strlen(s?.pointer?.takeIf { s.rawValue != 0L }?.reinterpret<UByteVar>())
}

actual fun consumer_set_string(s: NativeAddress?): Unit {
    webgpu.native.consumer_set_string(s?.pointer?.takeIf { s.rawValue != 0L }?.reinterpret<UByteVar>())
    return
}

actual fun consumer_get_ping(out: NativeAddress?): Unit {
    webgpu.native.consumer_get_ping(out?.pointer?.takeIf { out.rawValue != 0L }?.reinterpret<LongVar>())
    return
}

