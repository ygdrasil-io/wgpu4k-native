#include <jni.h>
#include <stdint.h>
#include <dlfcn.h>

typedef uint64_t (*fn_i0)(void);      /* int-like return, 0 args */
typedef void (*fn_void_v0)(void);     /* void return, 0 args */
typedef uint64_t (*fn_i4_i4i4i4i4)(int, int, int, int);

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void)vm; (void)reserved;
    return JNI_VERSION_1_6;
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_resolveSymbol(
    JNIEnv *env, jclass cls, jstring name) {
    (void)cls;
    const char *cname = (*env)->GetStringUTFChars(env, name, NULL);
    if ((*env)->ExceptionCheck(env)) return 0L;
    void *sym = dlsym(RTLD_DEFAULT, cname);
    (*env)->ReleaseStringUTFChars(env, name, cname);
    return (jlong)(uintptr_t)sym;
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV0(JNIEnv *env, jclass cls, jlong fn) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_void_v0)(uintptr_t)fn)();
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI0(JNIEnv *env, jclass cls, jlong fn) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i0)(uintptr_t)fn)();
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI4IIII(JNIEnv *env, jclass cls, jlong fn, jint a, jint b, jint c, jint d) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i4_i4i4i4i4)(uintptr_t)fn)(a, b, c, d);
}
