#include <string.h>
#include <stddef.h>

/* Implémentation C du consumer test (M3.2). Compilée en lib partagée
 * (JVM : libconsumer.dylib via System.loadLibrary) et en lib statique
 * (native : cinterop/link statique). Toujours des symboles C linkage. */

static long long ping_value = 42LL;

int consumer_negate(int x) {
    return -x;
}

int consumer_strlen(const unsigned char* s) {
    if (s == NULL) return -1;
    return (int) strlen((const char*) s);
}

void consumer_set_string(const unsigned char* s) {
    (void) s;
}

void consumer_get_ping(long long* out) {
    if (out != NULL) *out = ping_value;
}
