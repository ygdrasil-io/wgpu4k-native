#define _POSIX_C_SOURCE 200809L

#include "callback_fixture.h"

#include <errno.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

#define FIXTURE_MAX_MANY 1000u
#define FIXTURE_MAX_WORKERS 1000u

#ifndef FIXTURE_TEARDOWN_TIMEOUT_MS
#define FIXTURE_TEARDOWN_TIMEOUT_MS 10000u
#endif

#define FIXTURE_WATCHDOG_EXIT_CODE 124

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

typedef struct {
    pthread_mutex_t mutex;
    pthread_cond_t condition;
    pthread_t thread;
    struct timespec deadline;
    bool completed;
} fixture_teardown_watchdog;

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

static void fixture_pthread_require(int result, const char *operation) {
    if (result == 0) return;
    errno = result;
    perror(operation);
    abort();
}

static struct timespec fixture_deadline_after_ms(uint32_t timeout_ms) {
    struct timespec deadline;
    if (clock_gettime(CLOCK_REALTIME, &deadline) != 0) {
        perror("clock_gettime watchdog");
        abort();
    }
    deadline.tv_sec += (time_t)(timeout_ms / 1000u);
    deadline.tv_nsec += (long)(timeout_ms % 1000u) * 1000000L;
    if (deadline.tv_nsec >= 1000000000L) {
        deadline.tv_sec += 1;
        deadline.tv_nsec -= 1000000000L;
    }
    return deadline;
}

static void *fixture_teardown_watchdog_main(void *opaque_watchdog) {
    fixture_teardown_watchdog *watchdog = opaque_watchdog;
    fixture_pthread_require(pthread_mutex_lock(&watchdog->mutex), "pthread_mutex_lock watchdog thread");
    while (!watchdog->completed) {
        int result = pthread_cond_timedwait(
            &watchdog->condition,
            &watchdog->mutex,
            &watchdog->deadline
        );
        if (result == ETIMEDOUT) {
            static const char diagnostic[] = "callback fixture teardown watchdog expired\n";
            (void)write(STDERR_FILENO, diagnostic, sizeof(diagnostic) - 1u);
            _Exit(FIXTURE_WATCHDOG_EXIT_CODE);
        }
        fixture_pthread_require(result, "pthread_cond_timedwait watchdog");
    }
    fixture_pthread_require(pthread_mutex_unlock(&watchdog->mutex), "pthread_mutex_unlock watchdog thread");
    return NULL;
}

static void fixture_teardown_watchdog_arm(fixture_teardown_watchdog *watchdog) {
    watchdog->completed = false;
    watchdog->deadline = fixture_deadline_after_ms(FIXTURE_TEARDOWN_TIMEOUT_MS);
    fixture_pthread_require(pthread_mutex_init(&watchdog->mutex, NULL), "pthread_mutex_init watchdog");
    fixture_pthread_require(pthread_cond_init(&watchdog->condition, NULL), "pthread_cond_init watchdog");
    fixture_pthread_require(
        pthread_create(&watchdog->thread, NULL, fixture_teardown_watchdog_main, watchdog),
        "pthread_create watchdog"
    );
}

static void fixture_teardown_watchdog_disarm_and_join(fixture_teardown_watchdog *watchdog) {
    fixture_pthread_require(pthread_mutex_lock(&watchdog->mutex), "pthread_mutex_lock watchdog");
    watchdog->completed = true;
    fixture_pthread_require(pthread_cond_signal(&watchdog->condition), "pthread_cond_signal watchdog");
    fixture_pthread_require(pthread_mutex_unlock(&watchdog->mutex), "pthread_mutex_unlock watchdog");
    fixture_pthread_require(pthread_join(watchdog->thread, NULL), "pthread_join watchdog");
    fixture_pthread_require(pthread_cond_destroy(&watchdog->condition), "pthread_cond_destroy watchdog");
    fixture_pthread_require(pthread_mutex_destroy(&watchdog->mutex), "pthread_mutex_destroy watchdog");
}

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
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock copy routed");
    if ((worker || !unregistering) && stored_callback != NULL) {
        call->callback = stored_callback;
        call->application_userdata = stored_application_userdata;
        call->routing_userdata = stored_routing_userdata;
        call->value = value;
        in_flight_calls += 1u;
        available = true;
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock copy routed");
    return available;
}

static fixture_no_userdata_callback fixture_copy_no_userdata_callback(bool worker) {
    fixture_no_userdata_callback callback = NULL;
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock copy no userdata");
    if (worker || !unregistering) {
        callback = stored_no_userdata_callback;
        if (callback != NULL) {
            in_flight_calls += 1u;
        }
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock copy no userdata");
    return callback;
}

static void fixture_complete_call(void) {
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock complete call");
    if (in_flight_calls > 0u) {
        in_flight_calls -= 1u;
    }
    fixture_pthread_require(pthread_cond_broadcast(&fixture_condition), "pthread_cond_broadcast complete call");
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock complete call");
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

    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock spawn worker");
    if (unregistering || worker_count == FIXTURE_MAX_WORKERS) {
        fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock spawn worker rejected");
        free(args);
        return;
    }
    pthread_t worker;
    int result = pthread_create(&worker, NULL, fixture_worker_main, args);
    if (result == 0) {
        workers[worker_count] = worker;
        worker_count += 1u;
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock spawn worker");
    if (result != 0) {
        free(args);
    }
    fixture_pthread_require(result, "pthread_create worker");
}

void fixture_store(
    fixture_callback callback,
    void *application_userdata,
    void *routing_userdata
) {
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock store");
    if (!unregistering) {
        stored_callback = callback;
        stored_application_userdata = application_userdata;
        stored_routing_userdata = routing_userdata;
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock store");
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
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock store many");
    if (!unregistering) {
        stored_many[index].callback = callback;
        stored_many[index].routing_userdata = routing_userdata;
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock store many");
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

    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock fire many");
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
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock fire many");

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
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock store no userdata");
    if (!unregistering) {
        stored_no_userdata_callback = callback;
    }
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock store no userdata");
}

void fixture_fire_no_userdata_after_ms(uint32_t value, uint32_t delay_ms) {
    fixture_spawn_worker(FIXTURE_WORKER_NO_USERDATA, value, delay_ms);
}

void fixture_unregister_and_join(void) {
    fixture_teardown_watchdog watchdog;
    fixture_teardown_watchdog_arm(&watchdog);
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock unregister");
    while (unregistering) {
        fixture_pthread_require(
            pthread_cond_wait(&fixture_condition, &fixture_mutex),
            "pthread_cond_wait unregister"
        );
    }
    unregistering = true;

    while (worker_count > 0u) {
        pthread_t worker = workers[worker_count - 1u];
        worker_count -= 1u;
        fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock worker join");
        fixture_pthread_require(pthread_join(worker, NULL), "pthread_join worker");
        fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock worker join");
    }
    while (in_flight_calls > 0u) {
        fixture_pthread_require(
            pthread_cond_wait(&fixture_condition, &fixture_mutex),
            "pthread_cond_wait in-flight"
        );
    }

    stored_callback = NULL;
    stored_application_userdata = NULL;
    stored_routing_userdata = NULL;
    stored_no_userdata_callback = NULL;
    memset(stored_many, 0, sizeof(stored_many));
    unregistering = false;
    fixture_pthread_require(
        pthread_cond_broadcast(&fixture_condition),
        "pthread_cond_broadcast unregister"
    );
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock unregister");
    fixture_teardown_watchdog_disarm_and_join(&watchdog);
}

uintptr_t fixture_roundtrip_userdata(void *userdata) {
    return (uintptr_t)userdata;
}

uint32_t fixture_active_native_slots(void) {
    uint32_t active = 0u;
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock active slots");
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
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock active slots");
    return active;
}
