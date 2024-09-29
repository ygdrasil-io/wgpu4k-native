@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package ffi

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import platform.android.JNIEnvVar
import platform.android.jlong
import platform.android.jobject
import platform.android.jstring
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_ffi_Callback_registerRequestDevice")
fun registerRequestDevice(env: CPointer<JNIEnvVar>, thiz: jobject, callback: jobject, methodName: jstring) : Long {
    val methodName = env.getString(methodName)
    val callbackInfo = CallbackInfo(
        env.newGlobalRefOrThrow(callback).reinterpret(),
        methodName,
        env
    )

    val handleRequestDevice = staticCFunction { status: UInt, device: Long, message: Long, userdata1: Long ->
        val (callback, methodName, env, _) = findCallbackClassFromPointer(userdata1)

        val callbackClass = env.getObjectClassOrThrow(callback)
        val method = env.getMethodIdOrThrow(callbackClass,methodName, "(IJJJ)V")

        memScoped {
            env.callVoidMethod(callback, method, status.jvalue(this), device.jvalue(this), message.jvalue(this), userdata1.jvalue(this))
        }

        env.deleteGlobalRef(callback)
    }
    callbackInfo.callbackPointer = handleRequestDevice

    handleRequestDeviceCallbacks.add(callbackInfo)

    return handleRequestDevice.rawValue.toLong()
}



@CName("Java_ffi_Callback_free")
fun freeCallback(env: CPointer<JNIEnvVar>, thiz: jobject, callback: jlong) {
    deleteCallback(callback)
}


