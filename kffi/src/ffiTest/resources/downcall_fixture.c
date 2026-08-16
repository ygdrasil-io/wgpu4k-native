#include "downcall_fixture.h"

uint64_t bench_empty(void) { return 42u; }

int64_t bench_add4(int32_t a, int32_t b, int32_t c, int32_t d) {
    return (int64_t)a + b + c + d;
}

uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h) {
    return a + b + c + d + e + f + g + h;
}

void *bench_roundtrip_ptr(void *p) { return p; }

bench_box_t bench_make_box(int32_t x) {
    bench_box_t box;
    box.a = x;
    box.b = x + 1;
    return box;
}

static int32_t bench_consume_box_result = 0;

void bench_consume_box(bench_box_t box) {
    bench_consume_box_result = box.a + box.b;
}

int32_t bench_consume_box_get(void) { return bench_consume_box_result; }
