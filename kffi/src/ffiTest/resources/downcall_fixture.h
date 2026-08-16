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

typedef struct bench_string_view_t {
    const char *data;
    uint64_t length;
} bench_string_view_t;

typedef struct bench_future_t {
    uint64_t id;
} bench_future_t;

typedef struct bench_cb_info_t {
    void *callback;
    void *userdata;
} bench_cb_info_t;

uint64_t bench_empty(void);
int64_t bench_add4(int32_t a, int32_t b, int32_t c, int32_t d);
uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h);
void *bench_roundtrip_ptr(void *p);
bench_box_t bench_make_box(int32_t x);
void bench_consume_box(bench_box_t box);
int32_t bench_consume_box_get(void);

/* M5.3 : formes de l'union des signatures wgpu */
int64_t bench_add_indirect(int64_t *a, int64_t *b);
int64_t bench_has_feature(int64_t *p, int32_t feature);
uint64_t bench_load_u64(uint64_t *p);
int64_t bench_read_range(int64_t *p1, int64_t off, int64_t *p2, int64_t len);
void bench_set_flag(int64_t *p, int32_t value);
void bench_execute_bundles(int64_t *queue, uint64_t command_count, int64_t *commands);
void bench_set_viewport(float *encoder, float x, float y, float width, float height,
                        float min_depth, float max_depth);
float bench_viewport_get(int32_t *index);
void bench_set_level(int32_t level);
int32_t bench_get_level(void);
void bench_set_label(void *obj, bench_string_view_t label);
int64_t bench_label_sink_get(void);
void *bench_get_proc_address(bench_string_view_t name);
bench_future_t bench_on_work_done(void *queue, bench_cb_info_t info);
bench_future_t bench_buffer_map_async(void *buffer, uint64_t mode, uint64_t offset, uint64_t size, bench_cb_info_t info);
bench_future_t bench_get_lost_future(void *device);
void bench_consume_future(bench_future_t future);
uint64_t bench_consumed_future_id(void);

#ifdef __cplusplus
}
#endif

#endif /* DOWNCALL_FIXTURE_H */
