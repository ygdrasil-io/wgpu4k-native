#include "bench_fixture.h"

uint64_t bench_empty(void) { return 42u; }

uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d) {
    return a + b + c + d;
}

uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h) {
    return a + b + c + d + e + f + g + h;
}

double bench_pi(void) { return 3.14159; }

void *bench_roundtrip_ptr(void *p) { return p; }

bench_pair bench_make_pair(uint64_t a, uint64_t b) {
    bench_pair p = {a, b};
    return p;
}

uint64_t bench_pair_sum(bench_pair p) { return p.a + p.b; }

static bench_callback g_callback = NULL;
static void *g_callback_userdata = NULL;

void bench_set_callback(bench_callback cb, void *routing_userdata) {
    g_callback = cb;
    g_callback_userdata = routing_userdata;
}

void bench_fire(uint32_t count) {
    for (uint32_t i = 0; i < count; ++i) {
        if (g_callback != NULL) {
            g_callback(i, g_callback_userdata);
        }
    }
}

void bench_fire_one(uint32_t value) {
    if (g_callback != NULL) {
        g_callback(value, g_callback_userdata);
    }
}

static bench_callback_no_userdata g_callback_no_userdata = NULL;

void bench_set_callback_no_userdata(bench_callback_no_userdata cb) {
    g_callback_no_userdata = cb;
}

void bench_fire_no_userdata(uint32_t count) {
    for (uint32_t i = 0; i < count; ++i) {
        if (g_callback_no_userdata != NULL) {
            g_callback_no_userdata(i);
        }
    }
}
