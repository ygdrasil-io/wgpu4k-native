#include <jni.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>
#include <ffi.h>

#include "kffi_upcall.h"

typedef uint64_t (*fn_i0)(void);      /* int-like return, 0 args */
typedef void (*fn_void_v0)(void);     /* void return, 0 args */
typedef uint64_t (*fn_i4_i4i4i4i4)(int, int, int, int);
typedef void (*fn_void_v2_pp)(void *, void *);
typedef void (*fn_void_v1_i)(int);

/*
 * Typed downcall wrapper table (M5.5). One entry per `call<R><N><ARGS>` form the
 * regenerated wgpu Android bindings reference: R ∈ V/I/L/P/D/F/S/B (V void,
 * I/L/P 64-bit carrier returned as jlong, D double, F float, S short, B byte),
 * args I=int, L=int64_t, P=void*, D=double, F=float, S=short, B=int8_t.
 * Struct-by-value signatures ride callGeneric instead.
 */
typedef float (*fn_f_1_p)(void *);
typedef uint64_t (*fn_i_1_i)(int);
typedef uint64_t (*fn_i_1_p)(void *);
typedef uint64_t (*fn_i_2_pi)(void *, int);
typedef uint64_t (*fn_i_2_pp)(void *, void *);
typedef uint64_t (*fn_i_3_pip)(void *, int, void *);
typedef uint64_t (*fn_i_3_ppp)(void *, void *, void *);
typedef uint64_t (*fn_i_4_plpl)(void *, int64_t, void *, int64_t);
typedef uint64_t (*fn_l_1_p)(void *);
typedef uint64_t (*fn_l_3_plp)(void *, int64_t, void *);
typedef uint64_t (*fn_l_3_ppp)(void *, void *, void *);
typedef void *(*fn_p_1_p)(void *);
typedef void *(*fn_p_2_pi)(void *, int);
typedef void *(*fn_p_2_pp)(void *, void *);
typedef void *(*fn_p_3_pll)(void *, int64_t, int64_t);
typedef void (*fn_v_1_p)(void *);
typedef void (*fn_v_2_pi)(void *, int);
typedef void (*fn_v_3_plp)(void *, int64_t, void *);
typedef void (*fn_v_3_ppi)(void *, void *, int);
typedef void (*fn_v_3_ppl)(void *, void *, int64_t);
typedef void (*fn_v_4_piii)(void *, int, int, int);
typedef void (*fn_v_4_piip)(void *, int, int, void *);
typedef void (*fn_v_4_ppli)(void *, void *, int64_t, int);
typedef void (*fn_v_4_ppll)(void *, void *, int64_t, int64_t);
typedef void (*fn_v_4_pppp)(void *, void *, void *, void *);
typedef void (*fn_v_5_piiii)(void *, int, int, int, int);
typedef void (*fn_v_5_pipll)(void *, int, void *, int64_t, int64_t);
typedef void (*fn_v_5_piplp)(void *, int, void *, int64_t, void *);
typedef void (*fn_v_5_ppill)(void *, void *, int, int64_t, int64_t);
typedef void (*fn_v_5_pplpl)(void *, void *, int64_t, void *, int64_t);
typedef void (*fn_v_6_piiiii)(void *, int, int, int, int, int);
typedef void (*fn_v_6_ppiipl)(void *, void *, int, int, void *, int64_t);
typedef void (*fn_v_6_pplpli)(void *, void *, int64_t, void *, int64_t, int);
typedef void (*fn_v_6_pplpll)(void *, void *, int64_t, void *, int64_t, int64_t);
typedef void (*fn_v_6_ppplpp)(void *, void *, void *, int64_t, void *, void *);
typedef void (*fn_v_7_pffffff)(void *, float, float, float, float, float, float);

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

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV2PP(JNIEnv *env, jclass cls, jlong fn, jlong p1, jlong p2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_void_v2_pp)(uintptr_t)fn)((void *)(uintptr_t)p1, (void *)(uintptr_t)p2);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV1I(JNIEnv *env, jclass cls, jlong fn, jint i) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_void_v1_i)(uintptr_t)fn)(i);
}

/*
 * Typed downcall wrappers emitted by the wgpu Android bindings (M5.5).
 * Each follows the null-fn guard + sizeof-aware cast pattern above.
 */
JNIEXPORT jfloat JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callF1P(JNIEnv *env, jclass cls, jlong fn, jlong a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0.0f;
    }
    return (jfloat)((fn_f_1_p)(uintptr_t)fn)((void *)(uintptr_t)a1);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI1I(JNIEnv *env, jclass cls, jlong fn, jint a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_1_i)(uintptr_t)fn)(a1);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI1P(JNIEnv *env, jclass cls, jlong fn, jlong a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_1_p)(uintptr_t)fn)((void *)(uintptr_t)a1);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI2PI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_2_pi)(uintptr_t)fn)((void *)(uintptr_t)a1, a2);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI2PP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_2_pp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI3PIP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_3_pip)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, (void *)(uintptr_t)a3);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI3PPP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_3_ppp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (void *)(uintptr_t)a3);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI4PLPL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_i_4_plpl)(uintptr_t)fn)((void *)(uintptr_t)a1, (int64_t)a2, (void *)(uintptr_t)a3, (int64_t)a4);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callL1P(JNIEnv *env, jclass cls, jlong fn, jlong a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_l_1_p)(uintptr_t)fn)((void *)(uintptr_t)a1);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callL3PLP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_l_3_plp)(uintptr_t)fn)((void *)(uintptr_t)a1, (int64_t)a2, (void *)(uintptr_t)a3);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callL3PPP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)((fn_l_3_ppp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (void *)(uintptr_t)a3);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callP1P(JNIEnv *env, jclass cls, jlong fn, jlong a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)(uintptr_t)((fn_p_1_p)(uintptr_t)fn)((void *)(uintptr_t)a1);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callP2PI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)(uintptr_t)((fn_p_2_pi)(uintptr_t)fn)((void *)(uintptr_t)a1, a2);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callP2PP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)(uintptr_t)((fn_p_2_pp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callP3PLL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return 0L;
    }
    return (jlong)(uintptr_t)((fn_p_3_pll)(uintptr_t)fn)((void *)(uintptr_t)a1, (int64_t)a2, (int64_t)a3);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV1P(JNIEnv *env, jclass cls, jlong fn, jlong a1) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_1_p)(uintptr_t)fn)((void *)(uintptr_t)a1);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV2PI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_2_pi)(uintptr_t)fn)((void *)(uintptr_t)a1, a2);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV3PLP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_3_plp)(uintptr_t)fn)((void *)(uintptr_t)a1, (int64_t)a2, (void *)(uintptr_t)a3);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV3PPI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jint a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_3_ppi)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, a3);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV3PPL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_3_ppl)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV4PIII(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jint a3, jint a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_4_piii)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, a3, a4);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV4PIIP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jint a3, jlong a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_4_piip)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, a3, (void *)(uintptr_t)a4);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV4PPLI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jint a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_4_ppli)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3, a4);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV4PPLL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_4_ppll)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3, (int64_t)a4);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV4PPPP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_4_pppp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (void *)(uintptr_t)a3, (void *)(uintptr_t)a4);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV5PIIII(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jint a3, jint a4, jint a5) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_5_piiii)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, a3, a4, a5);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV5PIPLL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jlong a3, jlong a4, jlong a5) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_5_pipll)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, (void *)(uintptr_t)a3, (int64_t)a4, (int64_t)a5);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV5PIPLP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jlong a3, jlong a4, jlong a5) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_5_piplp)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, (void *)(uintptr_t)a3, (int64_t)a4, (void *)(uintptr_t)a5);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV5PPILL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jint a3, jlong a4, jlong a5) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_5_ppill)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, a3, (int64_t)a4, (int64_t)a5);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV5PPLPL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4, jlong a5) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_5_pplpl)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3, (void *)(uintptr_t)a4, (int64_t)a5);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV6PIIIII(JNIEnv *env, jclass cls, jlong fn, jlong a1, jint a2, jint a3, jint a4, jint a5, jint a6) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_6_piiiii)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, a3, a4, a5, a6);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV6PPIIPL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jint a3, jint a4, jlong a5, jlong a6) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_6_ppiipl)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, a3, a4, (void *)(uintptr_t)a5, (int64_t)a6);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV6PPLPLI(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4, jlong a5, jint a6) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_6_pplpli)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3, (void *)(uintptr_t)a4, (int64_t)a5, a6);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV6PPLPLL(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4, jlong a5, jlong a6) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_6_pplpll)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (int64_t)a3, (void *)(uintptr_t)a4, (int64_t)a5, (int64_t)a6);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV6PPPLPP(JNIEnv *env, jclass cls, jlong fn, jlong a1, jlong a2, jlong a3, jlong a4, jlong a5, jlong a6) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_6_ppplpp)(uintptr_t)fn)((void *)(uintptr_t)a1, (void *)(uintptr_t)a2, (void *)(uintptr_t)a3, (int64_t)a4, (void *)(uintptr_t)a5, (void *)(uintptr_t)a6);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV7PFFFFFF(JNIEnv *env, jclass cls, jlong fn, jlong a1, jfloat a2, jfloat a3, jfloat a4, jfloat a5, jfloat a6, jfloat a7) {
    (void)cls;
    if (fn == 0) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"),
                         "kffi: null native function pointer");
        return;
    }
    ((fn_v_7_pffffff)(uintptr_t)fn)((void *)(uintptr_t)a1, a2, a3, a4, a5, a6, a7);
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
    (void)typeSpec; /* typeSpec is currently unused by the generic reader (all args ride 8-byte carriers); struct-by-value args packed by kextract at natural alignment are truncated to 8 bytes at runtime (43 of 44 generic sites). TODO(M6/P2): implement per-arg ffi_type selection to honor typeSpec exactly. */
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

/*
 * JNI registration (M6.1). All NativeEngine and UpcallEngine externals are
 * bound explicitly via RegisterNatives from JNI_OnLoad (spec I6) instead of
 * relying on Java_<class>_<name> symbol-name matching. The JNIEXPORT
 * naming-convention functions above are kept unchanged: RegisterNatives
 * references their addresses directly, so the JNIEXPORT symbols remain the
 * single source of truth for the engine surface. Signatures below mirror the
 * Kotlin externals in NativeEngine.kt / UpcallEngine.kt exactly.
 *
 * TODO(P2): add an androidUnitTest guarding the RegisterNatives table against
 * NativeEngine.kt drift (a wrong entry fails loudly at load; a missing entry
 * silently falls back to Java_... symbol lookup).
 */
static JNINativeMethod kffi_native_engine_methods[] = {
    { "loadNativeLibrary", "(Ljava/lang/String;)J",
      (void *)Java_org_graphiks_kffi_engine_NativeEngine_loadNativeLibrary },
    { "resolveSymbolIn", "(JLjava/lang/String;)J",
      (void *)Java_org_graphiks_kffi_engine_NativeEngine_resolveSymbolIn },
    { "resolveSymbol", "(Ljava/lang/String;)J",
      (void *)Java_org_graphiks_kffi_engine_NativeEngine_resolveSymbol },
    { "callV0", "(J)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV0 },
    { "callI0", "(J)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI0 },
    { "callI4IIII", "(JIIII)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI4IIII },
    { "callV2PP", "(JJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV2PP },
    { "callV1I", "(JI)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV1I },
    { "callF1P", "(JJ)F", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callF1P },
    { "callI1I", "(JI)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI1I },
    { "callI1P", "(JJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI1P },
    { "callI2PI", "(JJI)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI2PI },
    { "callI2PP", "(JJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI2PP },
    { "callI3PIP", "(JJIJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI3PIP },
    { "callI3PPP", "(JJJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI3PPP },
    { "callI4PLPL", "(JJJJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callI4PLPL },
    { "callL1P", "(JJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callL1P },
    { "callL3PLP", "(JJJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callL3PLP },
    { "callL3PPP", "(JJJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callL3PPP },
    { "callP1P", "(JJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callP1P },
    { "callP2PI", "(JJI)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callP2PI },
    { "callP2PP", "(JJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callP2PP },
    { "callP3PLL", "(JJJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callP3PLL },
    { "callV1P", "(JJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV1P },
    { "callV2PI", "(JJI)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV2PI },
    { "callV3PLP", "(JJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV3PLP },
    { "callV3PPI", "(JJJI)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV3PPI },
    { "callV3PPL", "(JJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV3PPL },
    { "callV4PIII", "(JJIII)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV4PIII },
    { "callV4PIIP", "(JJIIJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV4PIIP },
    { "callV4PPLI", "(JJJJI)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV4PPLI },
    { "callV4PPLL", "(JJJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV4PPLL },
    { "callV4PPPP", "(JJJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV4PPPP },
    { "callV5PIIII", "(JJIIII)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV5PIIII },
    { "callV5PIPLL", "(JJIJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV5PIPLL },
    { "callV5PIPLP", "(JJIJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV5PIPLP },
    { "callV5PPILL", "(JJJIJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV5PPILL },
    { "callV5PPLPL", "(JJJJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV5PPLPL },
    { "callV6PIIIII", "(JJIIIII)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV6PIIIII },
    { "callV6PPIIPL", "(JJJIIJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV6PPIIPL },
    { "callV6PPLPLI", "(JJJJJJI)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV6PPLPLI },
    { "callV6PPLPLL", "(JJJJJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV6PPLPLL },
    { "callV6PPPLPP", "(JJJJJJJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV6PPPLPP },
    { "callV7PFFFFFF", "(JJFFFFFF)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callV7PFFFFFF },
    { "directBufferAddress", "(Ljava/nio/ByteBuffer;)J",
      (void *)Java_org_graphiks_kffi_engine_NativeEngine_directBufferAddress },
    { "callStructArgL", "(JIJJ)J", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callStructArgL },
    { "callStructReturn", "(JJJIJ)V", (void *)Java_org_graphiks_kffi_engine_NativeEngine_callStructReturn },
    { "callGeneric", "(JILjava/lang/String;JJ)V",
      (void *)Java_org_graphiks_kffi_engine_NativeEngine_callGeneric },
};

static JNINativeMethod kffi_upcall_engine_methods[] = {
    { "allocateTrampoline", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)J",
      (void *)Java_org_graphiks_kffi_engine_UpcallEngine_allocateTrampoline },
    { "freeTrampoline", "(J)V", (void *)Java_org_graphiks_kffi_engine_UpcallEngine_freeTrampoline },
};

static jint kffi_register_natives(JNIEnv *env, const char *class_name,
                                  const JNINativeMethod *methods, jint method_count) {
    jclass cls = (*env)->FindClass(env, class_name);
    if (cls == NULL) {
        (*env)->ExceptionClear(env);
        char msg[256];
        (void)snprintf(msg, sizeof(msg),
                       "kffi: JNI_OnLoad: engine class not found: %s", class_name);
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"), msg);
        return JNI_ERR;
    }
    if ((*env)->RegisterNatives(env, cls, methods, method_count) != JNI_OK) {
        (*env)->ExceptionClear(env);
        char msg[256];
        (void)snprintf(msg, sizeof(msg),
                       "kffi: JNI_OnLoad: RegisterNatives failed for %s (%d methods)",
                       class_name, (int)method_count);
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsatisfiedLinkError"), msg);
        return JNI_ERR;
    }
    return JNI_OK;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void)reserved;
    JNIEnv *env = NULL;
    if ((*vm)->GetEnv(vm, (void **)&env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    if (kffi_register_natives(env, "org/graphiks/kffi/engine/NativeEngine",
                              kffi_native_engine_methods,
                              (jint)(sizeof(kffi_native_engine_methods) /
                                     sizeof(kffi_native_engine_methods[0]))) != JNI_OK) {
        return JNI_ERR;
    }
    if (kffi_register_natives(env, "org/graphiks/kffi/engine/UpcallEngine",
                              kffi_upcall_engine_methods,
                              (jint)(sizeof(kffi_upcall_engine_methods) /
                                     sizeof(kffi_upcall_engine_methods[0]))) != JNI_OK) {
        return JNI_ERR;
    }
    kffi_upcall_init(vm);
    return JNI_VERSION_1_6;
}
