#include <jni.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>
#include <ffi.h>

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

/*
 * GENERIC downcall path (libffi fallback). Handles signatures outside the
 * typed wrapper table by packing every argument into a uint64 carrier and
 * letting libffi marshal them. The buffer-in/buffer-out contract matches the
 * fixture wrappers above.
 */
JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callGeneric(
    JNIEnv *env, jclass cls, jlong fn, jint argc, jstring typeSpec, jlong argsPtr, jlong outPtr) {
    (void)cls;
    (void)typeSpec; /* typeSpec reserved for M5 kextract emission; per-arg ffi_type selection lands there */
    if (fn == 0 || argsPtr == 0 || outPtr == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null generic-call argument");
        return;
    }
    if (argc < 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: negative generic-call argc");
        return;
    }
    ffi_type **types = calloc((size_t)argc, sizeof(ffi_type *));
    if (types == NULL) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"),
                         "kffi: calloc types failed");
        return;
    }
    ffi_cif cif;
    for (int i = 0; i < argc; i++) {
        types[i] = &ffi_type_uint64; /* generic path: every scalar/pointer arg rides a uint64 carrier */
    }
    ffi_status status = ffi_prep_cif(&cif, FFI_DEFAULT_ABI, (unsigned)argc, &ffi_type_uint64, types);
    if (status != FFI_OK) {
        free(types);
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: ffi_prep_cif failed");
        return;
    }
    /* ffi_call expects avalue to be an array of pointers to each argument's
       value. The caller packs the values contiguously (one uint64 carrier per
       arg), so build the pointer array into that buffer. */
    void **avalue = calloc((size_t)argc, sizeof(void *));
    if (avalue == NULL) {
        free(types);
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"),
                         "kffi: calloc avalue failed");
        return;
    }
    uintptr_t base = (uintptr_t)argsPtr;
    for (int i = 0; i < argc; i++) {
        avalue[i] = (void *)(base + (uintptr_t)i * sizeof(uint64_t));
    }
    ffi_call(&cif, FFI_FN(fn), (void *)(uintptr_t)outPtr, avalue);
    free(avalue);
    free(types);
}
