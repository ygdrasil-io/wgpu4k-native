#include "callback_fixture.h"

#include <stdlib.h>

int main(void) {
    fixture_fire_after_ms(1u, 60000u);
    fixture_unregister_and_join();
    return EXIT_FAILURE;
}
