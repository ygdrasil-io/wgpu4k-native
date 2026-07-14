#define _POSIX_C_SOURCE 200809L

#include "callback_fixture.h"

#include <errno.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define FIXTURE_MAX_MANY 1000u
#define FIXTURE_MAX_WORKERS 1000u

typedef struct {
    fixture_callback callback;
    void *routing_userdata;
} fixture_many_slot;

typedef enum {
    FIXTURE_WORKER_ROUTED,
    FIXTURE_WORKER_NO_USERDATA,
} fixture_worker_kind;

typedef struct {
    fixture_worker_kind kind;
    uint32_t value;
    uint32_t delay_ms;
} fixture_worker_args;

typedef struct {
    fixture_callback callback;
    void *application_userdata;
    void *routing_userdata;
    uint32_t value;
} fixture_call;

static pthread_mutex_t fixture_mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t fixture_condition = PTHREAD_COND_INITIALIZER;

static fixture_callback stored_callback;
static void *stored_application_userdata;
static void *stored_routing_userdata;
static fixture_no_userdata_callback stored_no_userdata_callback;
static fixture_many_slot stored_many[FIXTURE_MAX_MANY];

static pthread_t workers[FIXTURE_MAX_WORKERS];
static uint32_t worker_count;
static uint32_t in_flight_calls;
static bool unregistering;

static void fixture_sleep_ms(uint32_t delay_ms) {
    struct timespec remaining = {
        .tv_sec = (time_t)(delay_ms / 1000u),
        .tv_nsec = (long)(delay_ms % 1000u) * 1000000L,
    };
    while (nanosleep(&remaining, &remaining) == -1 && errno == EINTR) {
    }
}

static bool fixture_copy_routed_call(uint32_t value, bool worker, fixture_call *call) {
    bool available = false;
    pthread_mutex_lock(&fixture_mutex);
    if ((worker || !unregistering) && stored_callback != NULL) {
        call->callback = stored_callback;
        call->application_userdata = stored_application_userdata;
        call->routing_userdata = stored_routing_userdata;
        call->value = value;
        in_flight_calls += 1u;
        available = true;
    }
    pthread_mutex_unlock(&fixture_mutex);
    return available;
}

static fixture_no_userdata_callback fixture_copy_no_userdata_callback(bool worker) {
    fixture_no_userdata_callback callback = NULL;
    pthread_mutex_lock(&fixture_mutex);
    if (worker || !unregistering) {
        callback = stored_no_userdata_callback;
        if (callback != NULL) {
            in_flight_calls += 1u;
        }
    }
    pthread_mutex_unlock(&fixture_mutex);
    return callback;
}

static void fixture_complete_call(void) {
    pthread_mutex_lock(&fixture_mutex);
    if (in_flight_calls > 0u) {
        in_flight_calls -= 1u;
    }
    pthread_cond_broadcast(&fixture_condition);
    pthread_mutex_unlock(&fixture_mutex);
}

static void fixture_invoke_routed(uint32_t value, bool worker) {
    fixture_call call;
    if (!fixture_copy_routed_call(value, worker, &call)) {
        return;
    }
    call.callback(
        call.value,
        call.application_userdata,
        call.routing_userdata
    );
    fixture_complete_call();
}

static void fixture_invoke_no_userdata(uint32_t value, bool worker) {
    fixture_no_userdata_callback callback = fixture_copy_no_userdata_callback(worker);
    if (callback == NULL) {
        return;
    }
    callback(value);
    fixture_complete_call();
}

static void *fixture_worker_main(void *opaque_args) {
    fixture_worker_args args = *(fixture_worker_args *)opaque_args;
    free(opaque_args);
    fixture_sleep_ms(args.delay_ms);
    if (args.kind == FIXTURE_WORKER_ROUTED) {
        fixture_invoke_routed(args.value, true);
    } else {
        fixture_invoke_no_userdata(args.value, true);
    }
    return NULL;
}

static void fixture_spawn_worker(
    fixture_worker_kind kind,
    uint32_t value,
    uint32_t delay_ms
) {
    fixture_worker_args *args = malloc(sizeof(*args));
    if (args == NULL) {
        return;
    }
    args->kind = kind;
    args->value = value;
    args->delay_ms = delay_ms;

    pthread_mutex_lock(&fixture_mutex);
    if (unregistering || worker_count == FIXTURE_MAX_WORKERS) {
        pthread_mutex_unlock(&fixture_mutex);
        free(args);
        return;
    }
    pthread_t worker;
    int result = pthread_create(&worker, NULL, fixture_worker_main, args);
    if (result == 0) {
        workers[worker_count] = worker;
        worker_count += 1u;
    }
    pthread_mutex_unlock(&fixture_mutex);
    if (result != 0) {
        free(args);
    }
}

void fixture_store(
    fixture_callback callback,
    void *application_userdata,
    void *routing_userdata
) {
    pthread_mutex_lock(&fixture_mutex);
    if (!unregistering) {
        stored_callback = callback;
        stored_application_userdata = application_userdata;
        stored_routing_userdata = routing_userdata;
    }
    pthread_mutex_unlock(&fixture_mutex);
}

void fixture_fire_now(uint32_t value) {
    fixture_invoke_routed(value, false);
}

void fixture_fire_twice(uint32_t value) {
    fixture_invoke_routed(value, false);
    fixture_invoke_routed(value, false);
}

void fixture_fire_after_ms(uint32_t value, uint32_t delay_ms) {
    fixture_spawn_worker(FIXTURE_WORKER_ROUTED, value, delay_ms);
}

void fixture_store_many(uint32_t index, fixture_callback callback, void *routing_userdata) {
    if (index >= FIXTURE_MAX_MANY) {
        return;
    }
    pthread_mutex_lock(&fixture_mutex);
    if (!unregistering) {
        stored_many[index].callback = callback;
        stored_many[index].routing_userdata = routing_userdata;
    }
    pthread_mutex_unlock(&fixture_mutex);
}

void fixture_fire_many_shuffled(uint32_t count) {
    fixture_call calls[FIXTURE_MAX_MANY];
    uint32_t order[FIXTURE_MAX_MANY];
    uint32_t call_count = 0u;
    uint32_t bounded_count = count < FIXTURE_MAX_MANY ? count : FIXTURE_MAX_MANY;

    for (uint32_t index = 0u; index < bounded_count; index += 1u) {
        order[index] = index;
    }
    uint32_t random = 0x9e3779b9u ^ bounded_count;
    for (uint32_t index = bounded_count; index > 1u; index -= 1u) {
        random ^= random << 13u;
        random ^= random >> 17u;
        random ^= random << 5u;
        uint32_t swap_index = random % index;
        uint32_t temporary = order[index - 1u];
        order[index - 1u] = order[swap_index];
        order[swap_index] = temporary;
    }

    pthread_mutex_lock(&fixture_mutex);
    if (!unregistering) {
        for (uint32_t position = 0u; position < bounded_count; position += 1u) {
            uint32_t index = order[position];
            if (stored_many[index].callback != NULL) {
                calls[call_count].callback = stored_many[index].callback;
                calls[call_count].application_userdata = NULL;
                calls[call_count].routing_userdata = stored_many[index].routing_userdata;
                calls[call_count].value = index;
                call_count += 1u;
            }
        }
        if (call_count > 0u) {
            in_flight_calls += 1u;
        }
    }
    pthread_mutex_unlock(&fixture_mutex);

    for (uint32_t index = 0u; index < call_count; index += 1u) {
        calls[index].callback(
            calls[index].value,
            calls[index].application_userdata,
            calls[index].routing_userdata
        );
    }
    if (call_count > 0u) {
        fixture_complete_call();
    }
}

void fixture_store_no_userdata(fixture_no_userdata_callback callback) {
    pthread_mutex_lock(&fixture_mutex);
    if (!unregistering) {
        stored_no_userdata_callback = callback;
    }
    pthread_mutex_unlock(&fixture_mutex);
}

void fixture_fire_no_userdata_after_ms(uint32_t value, uint32_t delay_ms) {
    fixture_spawn_worker(FIXTURE_WORKER_NO_USERDATA, value, delay_ms);
}

void fixture_unregister_and_join(void) {
    pthread_mutex_lock(&fixture_mutex);
    while (unregistering) {
        pthread_cond_wait(&fixture_condition, &fixture_mutex);
    }
    unregistering = true;

    while (worker_count > 0u) {
        pthread_t worker = workers[worker_count - 1u];
        worker_count -= 1u;
        pthread_mutex_unlock(&fixture_mutex);
        pthread_join(worker, NULL);
        pthread_mutex_lock(&fixture_mutex);
    }
    while (in_flight_calls > 0u) {
        pthread_cond_wait(&fixture_condition, &fixture_mutex);
    }

    stored_callback = NULL;
    stored_application_userdata = NULL;
    stored_routing_userdata = NULL;
    stored_no_userdata_callback = NULL;
    memset(stored_many, 0, sizeof(stored_many));
    unregistering = false;
    pthread_cond_broadcast(&fixture_condition);
    pthread_mutex_unlock(&fixture_mutex);
}

uintptr_t fixture_roundtrip_userdata(void *userdata) {
    return (uintptr_t)userdata;
}

uint32_t fixture_active_native_slots(void) {
    uint32_t active = 0u;
    pthread_mutex_lock(&fixture_mutex);
    if (stored_callback != NULL) {
        active += 1u;
    }
    if (stored_no_userdata_callback != NULL) {
        active += 1u;
    }
    for (uint32_t index = 0u; index < FIXTURE_MAX_MANY; index += 1u) {
        if (stored_many[index].callback != NULL) {
            active += 1u;
        }
    }
    pthread_mutex_unlock(&fixture_mutex);
    return active;
}
