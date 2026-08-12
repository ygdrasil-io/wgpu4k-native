#include <stdint.h>

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
