/*
 * kffi_upcall.c — Android upcall engine over libffi closures.
 *
 * A C library that wants to call back into Kotlin receives a *closure
 * trampoline* (allocated with ffi_closure_alloc + ffi_prep_closure_loc) as
 * its callback function pointer. Each closure captures an upcall_slot that
 * holds a global ref to a Kotlin dispatcher class and the static dispatch
 * method; the C library passes the encoded CallbackRuntime token through its
 * routing_userdata argument, and the closure forwards it to the dispatcher,
 * which routes through CallbackRuntime.dispatchSafely. This is the same
 * mechanism JNA implements in CallbackReference.
 *
 * P1 scope: a fixed callback CIF of (uint32_t value, void *routing_userdata)
 * -> void, i.e. the bench fixture's routed callback shape. The generic
 * per-typedef CIF selection lands with the M5 kextract generator.
 */

#include <jni.h>
#include <stdint.h>
#include <stdlib.h>
#include <ffi.h>

#include "kffi_upcall.h"

#define KFFI_UPCALL_SLOTS 256

typedef struct {
    int in_use;
    void *closure;          /* writable ffi_closure allocation */
    void *fnptr;            /* executable trampoline address returned to Kotlin */
    ffi_cif cif;
    ffi_type *arg_types[2]; /* persistent: ffi_prep_cif borrows this array */
    jclass cls;             /* global ref to the Kotlin dispatcher class */
    jmethodID method;       /* static dispatch method */
} upcall_slot;

static upcall_slot g_slots[KFFI_UPCALL_SLOTS];
static JavaVM *g_vm;

void kffi_upcall_init(JavaVM *vm) {
    g_vm = vm;
}

static JNIEnv *acquire_env(int *attached) {
    JNIEnv *env = NULL;
    *attached = 0;
    if (g_vm == NULL) return NULL;
    if ((*g_vm)->GetEnv(g_vm, (void **)&env, JNI_VERSION_1_6) != JNI_OK) {
        if ((*g_vm)->AttachCurrentThread(g_vm, &env, NULL) != JNI_OK) return NULL;
        *attached = 1;
    }
    return env;
}

static void release_env(int attached) {
    if (attached) (*g_vm)->DetachCurrentThread(g_vm);
}

/*
 * Generic closure entry. user_data is the upcall_slot pointer.
 *
 * P1 CIF: (uint32_t value, void *routing_userdata) -> void. The routing
 * userdata carries the encoded CallbackRuntime token; it is marshalled
 * verbatim into the Kotlin static method `dispatch(token: Long, value: Int)`
 * i.e. JNI (JNIEnv*, jclass, jlong, jint).
 */
static void kffi_upcall_closure(ffi_cif *cif, void *resp, void **args, void *user_data) {
    (void)cif;
    (void)resp;
    upcall_slot *slot = (upcall_slot *)user_data;
    if (slot == NULL || !slot->in_use) return;
    int attached = 0;
    JNIEnv *env = acquire_env(&attached);
    if (env == NULL) return;
    uint32_t value = *(uint32_t *)args[0];
    uintptr_t token = (uintptr_t)*(void **)args[1];
    (*env)->CallStaticVoidMethod(env, slot->cls, slot->method, (jlong)token, (jint)value);
    if ((*env)->ExceptionCheck(env)) {
        /* P1: surface to logcat. M5.4 will route this to CallbackRuntime
           reporting via the Kotlin dispatcher's onError handler. */
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
    }
    release_env(attached);
}

/* Allocate a closure trampoline for the (uint32_t, void*) callback shape.
   Returns the executable trampoline address (jlong), or 0L on failure with a
   Java exception pending. dispatcherClass must be the Class object loaded by
   the caller's classloader (Kotlin hands over UpcallDispatcher::class.java),
   which sidesteps the split-APK FindClass problem for instrumented tests. */
JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_UpcallEngine_allocateTrampoline(
    JNIEnv *env, jclass cls, jclass dispatcherClass, jstring dispatchMethod, jstring dispatchSig) {
    (void)cls;
    const char *mname = (*env)->GetStringUTFChars(env, dispatchMethod, NULL);
    if (mname == NULL) return 0L;
    const char *msig = (*env)->GetStringUTFChars(env, dispatchSig, NULL);
    if (msig == NULL) {
        (*env)->ReleaseStringUTFChars(env, dispatchMethod, mname);
        return 0L;
    }

    upcall_slot *slot = NULL;
    for (int i = 0; i < KFFI_UPCALL_SLOTS; i++) {
        if (!g_slots[i].in_use) {
            slot = &g_slots[i];
            slot->in_use = 1;
            slot->closure = NULL;
            slot->fnptr = NULL;
            slot->cls = NULL;
            slot->method = NULL;
            break;
        }
    }
    if (slot == NULL) {
        (*env)->ReleaseStringUTFChars(env, dispatchMethod, mname);
        (*env)->ReleaseStringUTFChars(env, dispatchSig, msig);
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalStateException"),
                         "kffi: upcall slot exhausted");
        return 0L;
    }

    jlong result = 0L;
    void *fnptr = NULL;

    jclass global = (jclass)(*env)->NewGlobalRef(env, dispatcherClass);
    if (global == NULL) goto fail_slot; /* OOM pending */
    slot->cls = global;

    slot->method = (*env)->GetStaticMethodID(env, dispatcherClass, mname, msig);
    if (slot->method == NULL) {
        (*env)->ExceptionClear(env);
        goto fail_slot;
    }

    ffi_type *args[2] = { &ffi_type_uint32, &ffi_type_pointer };
    /* ffi_prep_cif borrows the arg_types array (does not copy it), so it must
       outlive this function: stash it in the slot. */
    slot->arg_types[0] = args[0];
    slot->arg_types[1] = args[1];
    if (ffi_prep_cif(&slot->cif, FFI_DEFAULT_ABI, 2, &ffi_type_void, slot->arg_types) != FFI_OK) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: ffi_prep_cif failed for upcall closure");
        goto fail_slot;
    }

    slot->closure = ffi_closure_alloc(sizeof(ffi_closure), &fnptr);
    if (slot->closure == NULL) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"),
                         "kffi: ffi_closure_alloc failed");
        goto fail_slot;
    }
    slot->fnptr = fnptr;

    if (ffi_prep_closure_loc(slot->closure, &slot->cif, kffi_upcall_closure, slot, fnptr) != FFI_OK) {
        ffi_closure_free(slot->closure);
        slot->closure = NULL;
        slot->fnptr = NULL;
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalArgumentException"),
                         "kffi: ffi_prep_closure_loc failed");
        goto fail_slot;
    }

    result = (jlong)(uintptr_t)fnptr;
    (*env)->ReleaseStringUTFChars(env, dispatchMethod, mname);
    (*env)->ReleaseStringUTFChars(env, dispatchSig, msig);
    return result;

fail_slot:
    if (slot->cls != NULL) {
        (*env)->DeleteGlobalRef(env, slot->cls);
        slot->cls = NULL;
    }
    slot->in_use = 0;
    (*env)->ReleaseStringUTFChars(env, dispatchMethod, mname);
    (*env)->ReleaseStringUTFChars(env, dispatchSig, msig);
    return result;
}

/* Release the closure identified by the executable trampoline address that
   Kotlin holds. ffi_closure_free takes the writable allocation; both the data
   and code mappings are unmapped together. */
JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_UpcallEngine_freeTrampoline(
    JNIEnv *env, jclass cls, jlong address) {
    (void)cls;
    if (address == 0L) return;
    for (int i = 0; i < KFFI_UPCALL_SLOTS; i++) {
        if (g_slots[i].in_use && g_slots[i].fnptr != NULL &&
            (uintptr_t)address == (uintptr_t)g_slots[i].fnptr) {
            ffi_closure_free(g_slots[i].closure);
            if (g_slots[i].cls != NULL) (*env)->DeleteGlobalRef(env, g_slots[i].cls);
            g_slots[i].closure = NULL;
            g_slots[i].fnptr = NULL;
            g_slots[i].cls = NULL;
            g_slots[i].method = NULL;
            g_slots[i].in_use = 0;
            return;
        }
    }
}
