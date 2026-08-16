#ifndef DOWNCALL_FIXTURE_H
#define DOWNCALL_FIXTURE_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

uint64_t bench_empty(void);
uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d);
uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h);
void *bench_roundtrip_ptr(void *p);

#ifdef __cplusplus
}
#endif

#endif /* DOWNCALL_FIXTURE_H */
