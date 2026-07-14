#ifndef KFFI_CALLBACK_FIXTURE_H
#define KFFI_CALLBACK_FIXTURE_H

#include <stdint.h>

_Static_assert(sizeof(void *) == sizeof(uintptr_t), "unsupported callback token ABI");

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*fixture_callback)(
    uint32_t value,
    void *application_userdata,
    void *routing_userdata
);
typedef void (*fixture_no_userdata_callback)(uint32_t value);

void fixture_store(
    fixture_callback callback,
    void *application_userdata,
    void *routing_userdata
);
void fixture_fire_now(uint32_t value);
void fixture_fire_twice(uint32_t value);
void fixture_fire_after_ms(uint32_t value, uint32_t delay_ms);
void fixture_store_many(uint32_t index, fixture_callback callback, void *routing_userdata);
void fixture_fire_many_shuffled(uint32_t count);
void fixture_store_no_userdata(fixture_no_userdata_callback callback);
void fixture_fire_no_userdata_after_ms(uint32_t value, uint32_t delay_ms);
void fixture_unregister_and_join(void);
uintptr_t fixture_roundtrip_userdata(void *userdata);
uint32_t fixture_active_native_slots(void);

#ifdef __cplusplus
}
#endif

#endif
