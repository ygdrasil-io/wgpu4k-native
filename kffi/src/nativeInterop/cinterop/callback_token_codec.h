#include <stdint.h>

_Static_assert(sizeof(void *) == sizeof(uintptr_t), "unsupported callback token ABI");
_Static_assert(sizeof(uintptr_t) == 8, "callback tokens require a 64-bit uintptr_t");

static inline void *kffi_callback_token_encode(uintptr_t token) {
    return (void *)token;
}

static inline uintptr_t kffi_callback_token_decode(void *userdata) {
    return (uintptr_t)userdata;
}
