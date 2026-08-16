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

/* M5.3 : formes de l'union des signatures wgpu */

int64_t bench_add_indirect(int64_t *a, int64_t *b) { return *a + *b; }

int64_t bench_has_feature(int64_t *p, int32_t feature) { return *p + feature; }

uint64_t bench_load_u64(uint64_t *p) { return *p; }

int64_t bench_read_range(int64_t *p1, int64_t off, int64_t *p2, int64_t len) {
    return p1[off] + *p2 + len;
}

void bench_set_flag(int64_t *p, int32_t value) { *p = value; }

void bench_execute_bundles(int64_t *queue, uint64_t command_count, int64_t *commands) {
    commands[0] = *queue + (int64_t)command_count;
}

static int32_t bench_viewport_calls = 0;
static float bench_viewport[6] = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };

void bench_set_viewport(float *encoder, float x, float y, float width, float height,
                        float min_depth, float max_depth) {
    bench_viewport[0] = x;
    bench_viewport[1] = y;
    bench_viewport[2] = width;
    bench_viewport[3] = height;
    bench_viewport[4] = min_depth;
    bench_viewport[5] = max_depth;
    bench_viewport_calls += 1;
}

float bench_viewport_get(int32_t *index) { return bench_viewport[*index]; }

static int32_t bench_level = 0;

void bench_set_level(int32_t level) { bench_level = level; }

int32_t bench_get_level(void) { return bench_level; }

static int64_t bench_label_sink = 0;

void bench_set_label(void *obj, bench_string_view_t label) {
    bench_label_sink = (int64_t)(uintptr_t)obj + (int64_t)label.length;
}

int64_t bench_label_sink_get(void) { return bench_label_sink; }

void *bench_get_proc_address(bench_string_view_t name) {
    if (name.data == (const char *)0 || name.length == 0) return (void *)0;
    return (void *)(uintptr_t)(name.length * 2u);
}

static bench_future_t bench_consumed_future = { 0u };

void bench_consume_future(bench_future_t future) { bench_consumed_future = future; }

uint64_t bench_consumed_future_id(void) { return bench_consumed_future.id; }

bench_future_t bench_on_work_done(void *queue, bench_cb_info_t info) {
    bench_future_t future;
    future.id = (uint64_t)(uintptr_t)queue + (uint64_t)(uintptr_t)info.userdata;
    return future;
}

bench_future_t bench_buffer_map_async(void *buffer, uint64_t mode, uint64_t offset, uint64_t size, bench_cb_info_t info) {
    bench_future_t future;
    future.id = (uint64_t)(uintptr_t)buffer + mode + offset + size + (uint64_t)(uintptr_t)info.userdata;
    return future;
}

bench_future_t bench_get_lost_future(void *device) {
    bench_future_t future;
    future.id = (uint64_t)(uintptr_t)device + 7u;
    return future;
}
