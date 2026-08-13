#include <stdint.h>
#include <pthread.h>

uint64_t bench_empty(void) { return 42u; }

void bench_void_takes_void(void) {}

uint64_t bench_add4(int a, int b, int c, int d) {
    return (uint64_t)(a + b + c + d);
}

typedef struct bench_pair { uint64_t a; uint64_t b; } bench_pair;

bench_pair bench_make_pair(uint64_t a, uint64_t b) {
    bench_pair p = {a, b};
    return p;
}

uint64_t bench_pair_sum(bench_pair p) { return p.a + p.b; }

/*
 * Bench callback fixture for the upcall engine. The callback shape matches
 * the kffi upcall closure CIF: void (uint32_t value, void *routing_userdata).
 * `routing_userdata` carries the encoded CallbackRuntime token; the upcall
 * engine forwards it verbatim to the Kotlin dispatcher.
 */
typedef void (*bench_callback)(uint32_t value, void *routing_userdata);

static bench_callback g_callback = NULL;
static void *g_callback_userdata = NULL;

void bench_set_callback(bench_callback cb, void *userdata) {
    g_callback = cb;
    g_callback_userdata = userdata;
}

void bench_fire(void) {
    if (g_callback) g_callback(0u, g_callback_userdata);
}

void bench_fire_one(uint32_t value) {
    if (g_callback) g_callback(value, g_callback_userdata);
}

static void *bench_fire_one_from_thread_impl(void *arg) {
    uint32_t value = (uint32_t)(uintptr_t)arg;
    if (g_callback) g_callback(value, g_callback_userdata);
    return NULL;
}

/* Fires the callback from a fresh native (non-Java) thread. The caller joins,
   so the delivery is synchronous from the caller's perspective while the
   callback itself runs on a thread that must attach its own JNIEnv. */
void bench_fire_one_from_thread(uint32_t value) {
    pthread_t thread;
    if (pthread_create(&thread, NULL, bench_fire_one_from_thread_impl,
                       (void *)(uintptr_t)value) == 0) {
        pthread_join(thread, NULL);
    }
}
