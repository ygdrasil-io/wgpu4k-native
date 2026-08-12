#include <jni.h>
#include <stdint.h>
#include <string.h>
#include <dlfcn.h>

typedef uint64_t (*fn_i0)(void);      /* int-like return, 0 args */
typedef void (*fn_void_v0)(void);     /* void return, 0 args */
typedef uint64_t (*fn_i4_i4i4i4i4)(int, int, int, int);

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void)vm; (void)reserved;
    return JNI_VERSION_1_6;
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_loadNativeLibrary(
    JNIEnv *env, jclass cls, jstring path) {
    (void)cls;
    const char *cpath = (*env)->GetStringUTFChars(env, path, NULL);
    if ((*env)->ExceptionCheck(env)) return 0L;
    void *handle = dlopen(cpath, RTLD_NOW | RTLD_GLOBAL);
    if (handle == NULL) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         dlerror());
        (*env)->ReleaseStringUTFChars(env, path, cpath);
        return 0L;
    }
    (*env)->ReleaseStringUTFChars(env, path, cpath);
    return (jlong)(uintptr_t)handle;
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_resolveSymbolIn(
    JNIEnv *env, jclass cls, jlong handle, jstring name) {
    (void)cls;
    const char *cname = (*env)->GetStringUTFChars(env, name, NULL);
    if ((*env)->ExceptionCheck(env)) return 0L;
    void *sym = dlsym((void *)(uintptr_t)handle, cname);
    (*env)->ReleaseStringUTFChars(env, name, cname);
    return (jlong)(uintptr_t)sym;
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

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_directBufferAddress(
    JNIEnv *env, jclass cls, jobject buffer) {
    (void)cls;
    if (buffer == NULL) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: null buffer");
        return 0L;
    }
    jlong addr = (jlong)(uintptr_t)(*env)->GetDirectBufferAddress(env, buffer);
    if ((*env)->ExceptionCheck(env)) return 0L;
    if (addr == 0L) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: buffer is not a direct byte buffer");
        return 0L;
    }
    return addr;
}

/*
 * FIXTURE-SPECIFIC struct-by-value wrappers (P1 only).
 * These are hand-written for the bench fixture's `bench_pair`. The
 * buffer-in/buffer-out contract (structPtr/structSize in, outPtr out) is
 * exactly what M5's kextract generator replicates per-struct from header
 * layouts; these bodies will be REPLACED by generated wrappers.
 */
typedef struct bench_pair { uint64_t a; uint64_t b; } bench_pair;
typedef uint64_t (*fn_struct_pair_sum)(bench_pair);

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callStructArgL(
    JNIEnv *env, jclass cls, jlong fn, jint structSize, jlong structPtr, jlong arg2) {
    (void)arg2; (void)cls;
    if (fn == 0 || structPtr == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer or struct pointer");
        return 0L;
    }
    if ((size_t)structSize != sizeof(bench_pair)) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: struct size mismatch for bench_pair wrapper");
        return 0L;
    }
    bench_pair local;
    memcpy(&local, (void *)(uintptr_t)structPtr, (size_t)structSize);
    return (jlong)((fn_struct_pair_sum)(uintptr_t)fn)(local);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callStructReturn(
    JNIEnv *env, jclass cls, jlong fn, jlong a, jlong b, jint structSize, jlong outPtr) {
    (void)cls;
    if (fn == 0 || outPtr == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer or out pointer");
        return;
    }
    if ((size_t)structSize != sizeof(bench_pair)) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: struct size mismatch for bench_pair wrapper");
        return;
    }
    bench_pair r = ((bench_pair (*)(uint64_t, uint64_t))(uintptr_t)fn)((uint64_t)a, (uint64_t)b);
    memcpy((void *)(uintptr_t)outPtr, &r, (size_t)structSize);
}
