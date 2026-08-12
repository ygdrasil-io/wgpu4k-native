#ifndef KFFI_BENCH_FIXTURE_H
#define KFFI_BENCH_FIXTURE_H

#include <stdint.h>
#include <stddef.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct bench_pair {
    uint64_t a;
    uint64_t b;
} bench_pair;

uint64_t bench_empty(void);
uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d);
uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                   uint64_t e, uint64_t f, uint64_t g, uint64_t h);
double bench_pi(void);
void *bench_roundtrip_ptr(void *p);
bench_pair bench_make_pair(uint64_t a, uint64_t b);
uint64_t bench_pair_sum(bench_pair p);

typedef void (*bench_callback)(uint32_t value, void *routing_userdata);
void bench_set_callback(bench_callback cb, void *routing_userdata);
void bench_fire(uint32_t count);
void bench_fire_one(uint32_t value);

typedef void (*bench_callback_no_userdata)(uint32_t value);

void bench_set_callback_no_userdata(bench_callback_no_userdata cb);
void bench_fire_no_userdata(uint32_t count);

#ifdef __cplusplus
}
#endif
#endif
