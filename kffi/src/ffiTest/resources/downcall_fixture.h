#ifndef DOWNCALL_FIXTURE_H
#define DOWNCALL_FIXTURE_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct bench_box_t {
    int32_t a;
    int32_t b;
} bench_box_t;

uint64_t bench_empty(void);
int64_t bench_add4(int32_t a, int32_t b, int32_t c, int32_t d);
uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h);
void *bench_roundtrip_ptr(void *p);
bench_box_t bench_make_box(int32_t x);
void bench_consume_box(bench_box_t box);
int32_t bench_consume_box_get(void);

#ifdef __cplusplus
}
#endif

#endif /* DOWNCALL_FIXTURE_H */
