#include <stdint.h>

uint64_t bench_empty(void) { return 42u; }

void bench_void_takes_void(void) {}

uint64_t bench_add4(int a, int b, int c, int d) {
    return (uint64_t)(a + b + c + d);
}
