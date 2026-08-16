#include "downcall_fixture.h"

uint64_t bench_empty(void) { return 42u; }

uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d) {
    return a + b + c + d;
}

uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h) {
    return a + b + c + d + e + f + g + h;
}

void *bench_roundtrip_ptr(void *p) { return p; }
