#ifndef KFFI_UPCALL_H
#define KFFI_UPCALL_H

#include <jni.h>

/*
 * kffi Android upcall engine: libffi closures that let a C library invoke a
 * Kotlin static dispatcher from an arbitrary thread. See kffi_upcall.c.
 */

/* Called once from JNI_OnLoad. */
void kffi_upcall_init(JavaVM *vm);

#endif /* KFFI_UPCALL_H */
