# Harden KFFI Callback Fixture Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Guarantee that delayed-callback FFI teardown is bounded inside native code and that the JVM callback-token ABI is derived from and restricted to a 64-bit FFM address layout.

**Architecture:** Arm a pthread watchdog with its own mutex and condition before `fixture_unregister_and_join` can block, terminate the child process with exit code 124 when the fixture deadline expires, and signal then join it only after teardown is complete. Exercise the fatal path in an isolated C probe process so the normal test worker remains safe; keep the production callback runtime unchanged.

**Tech Stack:** C11, pthreads, JVM Foreign Function and Memory API, Kotlin Multiplatform, Kotest, Gradle Kotlin DSL.

## Global Constraints

- The approved design is `docs/superpowers/specs/2026-07-14-external-review-remediation-design.md`.
- Do not modify, fork, upgrade, or replace `wgpu-native`.
- The native watchdog default is 10,000 ms and its timeout-test child override is 100 ms.
- A watchdog expiry exits only the isolated test process with code 124; it must never invoke Kotlin or unwind through an FFI boundary.
- Every pthread mutex, condition, create, and join result touched by the fixture must be checked.
- Keep Windows JVM FFI fixture tests excluded while the fixture requires pthreads; do not claim Windows execution coverage.
- Preserve the callback runtime state machine, permanent trampolines, token allocation, and no-userdata retirement behavior.
- Use test-driven development and commit only source files, never compiled fixture artifacts.
- Preserve unrelated changes and do not push.

## File Map

- `kffi/src/ffiTest/resources/callback_fixture.c`: checked pthread wrappers, watchdog lifecycle, and bounded unregister teardown.
- `kffi/src/ffiTest/resources/callback_fixture_watchdog_probe.c`: isolated executable that intentionally keeps one worker alive beyond the watchdog deadline.
- `kffi/build.gradle.kts`: builds the probe and supplies its path to JVM tests on macOS and Linux.
- `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/CallbackFixtureWatchdogJvmTest.kt`: launches and bounds the probe child process.
- `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/TokenCodec.jvm.kt`: derives pointer width from `ValueLayout.ADDRESS` and rejects non-64-bit layouts.
- `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/TokenCodecJvmTest.kt`: verifies the concrete JVM FFM layout and the existing signed-positive token range.

---

### Task 1: Validate the JVM FFM callback-token width

**Files:**
- Modify: `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/TokenCodec.jvm.kt`
- Modify: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/TokenCodecJvmTest.kt`

**Interfaces:**
- Produces: `internal fun validatedJvmCallbackPointerBits(addressByteSize: Long): Int`.
- Preserves: `PlatformTokenCodec.maxToken == Long.MAX_VALUE.toULong()` and every encode/decode rule.

- [ ] **Step 1: Add failing pointer-layout tests**

In `TokenCodecJvmTest.kt`, import `java.lang.foreign.ValueLayout` and add:

```kotlin
"the JVM token codec derives pointer width from the FFM address layout" {
    validatedJvmCallbackPointerBits(ValueLayout.ADDRESS.byteSize()) shouldBe 64
    PlatformTokenCodec.pointerBits shouldBe 64
}

"non-64-bit JVM FFM address layouts are rejected" {
    val failure = shouldThrow<IllegalArgumentException> {
        validatedJvmCallbackPointerBits(4L)
    }
    failure.message shouldBe
        "KFFI callback tokens require an 8-byte JVM FFM address layout, found 4 bytes"
}
```

- [ ] **Step 2: Run the focused test and verify RED**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.TokenCodecJvmTest
```

Expected: compilation fails because `validatedJvmCallbackPointerBits` does not exist.

- [ ] **Step 3: Derive and enforce the JVM pointer width**

In `TokenCodec.jvm.kt`, import `java.lang.foreign.ValueLayout` and add:

```kotlin
internal fun validatedJvmCallbackPointerBits(addressByteSize: Long): Int {
    require(addressByteSize == Long.SIZE_BYTES.toLong()) {
        "KFFI callback tokens require an 8-byte JVM FFM address layout, " +
            "found $addressByteSize bytes"
    }
    return Long.SIZE_BITS
}
```

Replace the constant with:

```kotlin
override val pointerBits: Int =
    validatedJvmCallbackPointerBits(ValueLayout.ADDRESS.byteSize())
```

- [ ] **Step 4: Verify and commit the pointer contract**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.TokenCodecJvmTest
rtk git diff --check
```

Expected: the real eight-byte layout and the synthetic four-byte rejection both pass; the existing
`Long.MAX_VALUE` round trip and upper-bound rejection remain green.

Commit:

```bash
rtk git add \
  kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/TokenCodec.jvm.kt \
  kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/TokenCodecJvmTest.kt
rtk git commit -m "fix(kffi): validate JVM callback address width"
```

---

### Task 2: Bound delayed-callback fixture teardown

**Files:**
- Create: `kffi/src/ffiTest/resources/callback_fixture_watchdog_probe.c`
- Create: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/CallbackFixtureWatchdogJvmTest.kt`
- Modify: `kffi/src/ffiTest/resources/callback_fixture.c`
- Modify: `kffi/build.gradle.kts`

**Interfaces:**
- Consumes: `fixture_fire_after_ms(uint32_t, uint32_t)` and `fixture_unregister_and_join(void)` from the existing fixture.
- Produces: compile-time fixture constant `FIXTURE_TEARDOWN_TIMEOUT_MS`, default `10000u`; only the isolated probe overrides it to `100u`.
- Produces: JVM test system property `kffi.callback.fixture.watchdog.probe` containing the probe executable path.
- Preserves: every existing exported fixture symbol and Kotlin FFI test signature.

- [ ] **Step 1: Add the isolated watchdog probe and its failing JVM test**

Create `callback_fixture_watchdog_probe.c` with one worker delayed for sixty seconds and a call to unregister:

```c
#include "callback_fixture.h"

#include <stdlib.h>

int main(void) {
    fixture_fire_after_ms(1u, 60000u);
    fixture_unregister_and_join();
    return EXIT_FAILURE;
}
```

Create `CallbackFixtureWatchdogJvmTest.kt`. The test must always destroy a child that misses the outer bound and must read diagnostics only after termination:

```kotlin
package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

class CallbackFixtureWatchdogJvmTest : FreeSpec({
    "native unregister watchdog terminates a stuck fixture child".config(timeout = 10.seconds) {
        val probe = requireNotNull(System.getProperty("kffi.callback.fixture.watchdog.probe"))
        val process = ProcessBuilder(probe).redirectErrorStream(true).start()
        val completed = try {
            process.waitFor(5, TimeUnit.SECONDS)
        } finally {
            if (process.isAlive) process.destroyForcibly().waitFor()
        }
        val diagnostic = process.inputStream.bufferedReader().readText()
        completed shouldBe true
        process.exitValue() shouldBe 124
        diagnostic shouldContain "callback fixture teardown watchdog expired"
    }
})
```

- [ ] **Step 2: Wire the probe build and verify RED**

In `kffi/build.gradle.kts`, declare the probe source/output and a macOS/Linux `Exec` task using the same C11 and pthread flags as the shared fixture:

```kotlin
val callbackFixtureWatchdogProbeSource =
    layout.projectDirectory.file("src/ffiTest/resources/callback_fixture_watchdog_probe.c")
val callbackFixtureWatchdogProbe = when (callbackFixtureHost) {
    "macos", "linux" -> callbackFixtureOutputDirectory.map { it.file("callback_fixture_watchdog_probe") }
    "windows" -> null
    else -> error("Unsupported callback fixture host: $callbackFixtureHost")
}
val compileCallbackFixtureWatchdogProbe = callbackFixtureWatchdogProbe?.let { probe ->
    tasks.register<Exec>("compileCallbackFixtureWatchdogProbe") {
        group = "verification"
        inputs.files(callbackFixtureSource, callbackFixtureHeader, callbackFixtureWatchdogProbeSource)
        outputs.file(probe)
        doFirst { callbackFixtureOutputDirectory.get().asFile.mkdirs() }
        commandLine(
            "cc", "-std=c11", "-pthread",
            "-DFIXTURE_TEARDOWN_TIMEOUT_MS=100u",
            callbackFixtureSource.asFile.absolutePath,
            callbackFixtureWatchdogProbeSource.asFile.absolutePath,
            "-o", probe.get().asFile.absolutePath,
        )
    }
}
```

Make `jvmTest` depend on that task and set `kffi.callback.fixture.watchdog.probe` beside the existing shared-library property. In the Windows branch, exclude both `CallbackFfiJvmTest` and `CallbackFixtureWatchdogJvmTest`, because neither pthread artifact is built there.

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFixtureWatchdogJvmTest
```

Expected: FAIL after roughly five seconds because the current fixture has no internal watchdog and the child must be destroyed by the test.

- [ ] **Step 3: Add checked pthread operations and the native watchdog**

In `callback_fixture.c`, add these constants and helpers. The watchdog uses synchronization that is completely independent of `fixture_mutex`, so a deadlock in fixture state cannot prevent its deadline. `fixture_pthread_require` treats any pthread error as an invariant violation and never returns across the FFI boundary:

```c
#ifndef FIXTURE_TEARDOWN_TIMEOUT_MS
#define FIXTURE_TEARDOWN_TIMEOUT_MS 10000u
#endif

#define FIXTURE_WATCHDOG_EXIT_CODE 124

typedef struct {
    pthread_mutex_t mutex;
    pthread_cond_t condition;
    pthread_t thread;
    struct timespec deadline;
    bool completed;
} fixture_teardown_watchdog;

static void fixture_pthread_require(int result, const char *operation) {
    if (result == 0) return;
    errno = result;
    perror(operation);
    abort();
}
```

Add the checked absolute deadline and watchdog thread:

```c
static struct timespec fixture_deadline_after_ms(uint32_t timeout_ms) {
    struct timespec deadline;
    if (clock_gettime(CLOCK_REALTIME, &deadline) != 0) {
        perror("clock_gettime watchdog");
        abort();
    }
    deadline.tv_sec += (time_t)(timeout_ms / 1000u);
    deadline.tv_nsec += (long)(timeout_ms % 1000u) * 1000000L;
    if (deadline.tv_nsec >= 1000000000L) {
        deadline.tv_sec += 1;
        deadline.tv_nsec -= 1000000000L;
    }
    return deadline;
}

static void *fixture_teardown_watchdog_main(void *opaque_watchdog) {
    fixture_teardown_watchdog *watchdog = opaque_watchdog;
    fixture_pthread_require(pthread_mutex_lock(&watchdog->mutex), "pthread_mutex_lock watchdog thread");
    while (!watchdog->completed) {
        int result = pthread_cond_timedwait(
            &watchdog->condition,
            &watchdog->mutex,
            &watchdog->deadline
        );
        if (result == ETIMEDOUT) {
            static const char diagnostic[] = "callback fixture teardown watchdog expired\n";
            (void)write(STDERR_FILENO, diagnostic, sizeof(diagnostic) - 1u);
            _Exit(FIXTURE_WATCHDOG_EXIT_CODE);
        }
        fixture_pthread_require(result, "pthread_cond_timedwait watchdog");
    }
    fixture_pthread_require(pthread_mutex_unlock(&watchdog->mutex), "pthread_mutex_unlock watchdog thread");
    return NULL;
}
```

Add these lifecycle functions:

```c
static void fixture_teardown_watchdog_arm(fixture_teardown_watchdog *watchdog) {
    watchdog->completed = false;
    watchdog->deadline = fixture_deadline_after_ms(FIXTURE_TEARDOWN_TIMEOUT_MS);
    fixture_pthread_require(pthread_mutex_init(&watchdog->mutex, NULL), "pthread_mutex_init watchdog");
    fixture_pthread_require(pthread_cond_init(&watchdog->condition, NULL), "pthread_cond_init watchdog");
    fixture_pthread_require(
        pthread_create(&watchdog->thread, NULL, fixture_teardown_watchdog_main, watchdog),
        "pthread_create watchdog"
    );
}

static void fixture_teardown_watchdog_disarm_and_join(fixture_teardown_watchdog *watchdog) {
    fixture_pthread_require(pthread_mutex_lock(&watchdog->mutex), "pthread_mutex_lock watchdog");
    watchdog->completed = true;
    fixture_pthread_require(pthread_cond_signal(&watchdog->condition), "pthread_cond_signal watchdog");
    fixture_pthread_require(pthread_mutex_unlock(&watchdog->mutex), "pthread_mutex_unlock watchdog");
    fixture_pthread_require(pthread_join(watchdog->thread, NULL), "pthread_join watchdog");
    fixture_pthread_require(pthread_cond_destroy(&watchdog->condition), "pthread_cond_destroy watchdog");
    fixture_pthread_require(pthread_mutex_destroy(&watchdog->mutex), "pthread_mutex_destroy watchdog");
}
```

Include `<stdio.h>` and `<unistd.h>`. Arm before the first mutex acquisition in
`fixture_unregister_and_join`, preserve all current cleanup, unlock the mutex, then signal and join:

```c
void fixture_unregister_and_join(void) {
    fixture_teardown_watchdog watchdog;
    fixture_teardown_watchdog_arm(&watchdog);
    fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock unregister");
    while (unregistering) {
        fixture_pthread_require(
            pthread_cond_wait(&fixture_condition, &fixture_mutex),
            "pthread_cond_wait unregister"
        );
    }
    unregistering = true;

    while (worker_count > 0u) {
        pthread_t worker = workers[worker_count - 1u];
        worker_count -= 1u;
        fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock worker join");
        fixture_pthread_require(pthread_join(worker, NULL), "pthread_join worker");
        fixture_pthread_require(pthread_mutex_lock(&fixture_mutex), "pthread_mutex_lock worker join");
    }
    while (in_flight_calls > 0u) {
        fixture_pthread_require(
            pthread_cond_wait(&fixture_condition, &fixture_mutex),
            "pthread_cond_wait in-flight"
        );
    }

    stored_callback = NULL;
    stored_application_userdata = NULL;
    stored_routing_userdata = NULL;
    stored_no_userdata_callback = NULL;
    memset(stored_many, 0, sizeof(stored_many));
    unregistering = false;
    fixture_pthread_require(
        pthread_cond_broadcast(&fixture_condition),
        "pthread_cond_broadcast unregister"
    );
    fixture_pthread_require(pthread_mutex_unlock(&fixture_mutex), "pthread_mutex_unlock unregister");
    fixture_teardown_watchdog_disarm_and_join(&watchdog);
}
```

Replace every unchecked `pthread_mutex_lock`, `pthread_mutex_unlock`, `pthread_cond_wait`,
`pthread_cond_signal`, `pthread_cond_broadcast`, `pthread_create`, and `pthread_join` in the fixture with
`fixture_pthread_require(...)`. In `fixture_spawn_worker`, free `args` when `pthread_create` fails,
then pass that result to `fixture_pthread_require`; do not silently drop a requested worker.

- [ ] **Step 4: Verify the watchdog and all delayed FFI scenarios**

Run:

```bash
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFixtureWatchdogJvmTest
rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFfiJvmTest
rtk ./gradlew :kffi:macosArm64Test
```

Expected: the probe exits with 124 in under five seconds; all 13 JVM delayed-FFI scenarios and all
host Native KFFI tests pass without a hang, crash, native-slot leak, or runtime-registry leak.

- [ ] **Step 5: Run the KFFI regression suite and commit**

Run:

```bash
rtk ./gradlew :kffi:jvmTest :kffi:macosArm64Test :kffi:testDebugUnitTest
rtk git diff --check
```

Expected: every command reports `BUILD SUCCESSFUL`; the watchdog probe is not present in Git status
because it lives below `kffi/build`.

Commit:

```bash
rtk git add kffi
rtk git commit -m "test(kffi): bound delayed callback teardown"
```

---

### Task 3: Expose registration quiescence without a race window

**Files:**
- Modify: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/Callback.kt`
- Modify: `kffi/src/commonMain/kotlin/io/ygdrasil/kffi/CallbackRuntime.kt`
- Modify: `kffi/src/commonTest/kotlin/io/ygdrasil/kffi/CallbackApiTest.kt`
- Modify: `kffi/src/commonTest/kotlin/io/ygdrasil/kffi/CallbackStateMachineTest.kt`
- Modify: `kffi/src/jvmTest/kotlin/io/ygdrasil/kffi/CallbackRuntimeJvmTest.kt`

**Interfaces:**
- Produces: `CallbackRegistration<C>.isQuiescent: Boolean`.
- Defines quiescent as closed or claimed with zero native deliveries that successfully acquired the registration and have not yet returned from application dispatch.
- Preserves: `isClosed` as a lifecycle-state query that does not imply quiescence.

- [ ] **Step 1: Add failing state-machine and public-contract tests**

In `CallbackApiTest.kt`, extend the registration contract assertion to require a Boolean
`isQuiescent` property.

In `CallbackStateMachineTest.kt`, add:

```kotlin
"ONCE is closed but not quiescent until its acquired delivery leaves" {
    val machine = DeliveryStateMachine(CallbackPolicy.ONCE)

    machine.tryEnter() shouldBe true
    machine.isClosed shouldBe true
    machine.isQuiescent shouldBe false
    machine.leave()
    machine.isQuiescent shouldBe true
}

"REPEATING close becomes quiescent only after every acquired delivery leaves" {
    val machine = DeliveryStateMachine(CallbackPolicy.REPEATING)

    machine.tryEnter() shouldBe true
    machine.tryEnter() shouldBe true
    machine.close() shouldBe true
    machine.isQuiescent shouldBe false
    machine.leave()
    machine.isQuiescent shouldBe false
    machine.leave()
    machine.isQuiescent shouldBe true
}
```

In the existing JVM test `REPEATING rejects calls after close and lets in-flight work finish`,
assert `registration.isQuiescent == false` after close while the callback is blocked, then `true`
after `inFlight.get(...)` returns.

- [ ] **Step 2: Run focused tests and verify RED**

Run:

```bash
rtk ./gradlew :kffi:jvmTest \
  --tests io.ygdrasil.kffi.CallbackApiTest \
  --tests io.ygdrasil.kffi.CallbackStateMachineTest \
  --tests io.ygdrasil.kffi.CallbackRuntimeJvmTest
```

Expected: compilation fails because neither the public registration nor the state machine exposes
`isQuiescent`, and ONCE delivery does not yet maintain an in-flight count.

- [ ] **Step 3: Track every acquired delivery and expose quiescence**

Add to `CallbackRegistration`:

```kotlin
/** True only after closure and after every acquired native delivery has returned. */
val isQuiescent: Boolean
```

In `DeliveryStateMachine`, add:

```kotlin
val isQuiescent: Boolean
    get() = isClosed && inFlightRef.load() == 0
```

Replace ONCE acquisition with the same increment-before-state-transition pattern that closes the
race between a claimed state and publication of the in-flight count:

```kotlin
private fun tryEnterOnce(): Boolean {
    if (stateRef.load() != DeliveryState.ACTIVE) return false
    inFlightRef.fetchAndAdd(1)
    if (stateRef.compareAndSet(DeliveryState.ACTIVE, DeliveryState.CLAIMED)) return true
    inFlightRef.fetchAndAdd(-1)
    return false
}

fun tryEnter(): Boolean = when (policy) {
    CallbackPolicy.ONCE -> tryEnterOnce()
    CallbackPolicy.REPEATING -> tryEnterRepeating()
}

fun leave() {
    check(inFlightRef.fetchAndAdd(-1) > 0) { "Callback delivery left without entering" }
}
```

Make `AcquiredDelivery.complete()` call `entry.lifecycle.leave()` for both policies. Delegate the
new public property from `RuntimeCallbackRegistration`:

```kotlin
override val isQuiescent: Boolean
    get() = entry.lifecycle.isQuiescent
```

- [ ] **Step 4: Verify concurrency behavior and commit**

Run:

```bash
rtk ./gradlew :kffi:jvmTest :kffi:macosArm64Test :kffi:testDebugUnitTest
rtk git diff --check
```

Expected: all callback state-machine races, FFI delivery tests, and platform API tests pass; an
ONCE or REPEATING registration cannot report quiescence while an acquired callback is executing.

Commit:

```bash
rtk git add kffi
rtk git commit -m "feat(kffi): expose callback registration quiescence"
```

---

## Review Gate

Review the complete task range from its recorded base commit. The reviewer must verify both spec
compliance and code quality, including watchdog arming before any blocking fixture operation,
safe cancellation/join, checked pthread results, child-process isolation, JVM pointer-width
derivation, unchanged production callback semantics, and explicit absence of Windows fixture claims.
