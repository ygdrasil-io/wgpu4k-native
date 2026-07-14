# Task 10 report — delayed callbacks through a real C FFI fixture

## Result

Status: DONE.

The task commit is the commit containing this report, with subject
`test(kffi): cover delayed callbacks through C FFI`. Its concrete SHA is reported in the
handoff because a Git commit cannot contain its own cryptographic SHA.

No production runtime seam was required. The fixture and all platform-specific callback
types/trampolines live in test sources.

## RED evidence

- JVM: `rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFfiJvmTest --no-daemon`
  compiled the 13 tests and failed 13/13. The verified root cause was
  `IllegalArgumentException: kffi.callback.fixture.library must point at the compiled callback fixture`.
- Native: `rtk ./gradlew :kffi:macosArm64Test --no-daemon` failed in
  `compileTestKotlinMacosArm64` on the absent `callbackFixture` package and its 11 C symbols.
- The first GREEN attempt reached 12/13 JVM and 12/13 Native fixture cases. Both remaining
  failures were test-only operator-precedence errors in the thread identity assertions;
  parenthesizing the comparisons made the intended assertions explicit.
- Review regression proof for the C-thread assertions: temporarily closing each registration
  before firing made the focal JVM and Native tests fail at the new delivery assertion with
  `expected:<1> but was:<0>`. Both tests now prove exactly one callback was delivered and that
  the recorded thread value is non-sentinel before comparing it with the calling thread.
- Windows wiring RED: before the host-specific Gradle change,
  `:kffi:jvmTest -Pkffi.callbackFixture.hostForTest=windows --dry-run` still included
  `:kffi:compileCallbackFixtureShared`, demonstrating that Windows was incorrectly following
  the Linux/pthread path.

## Fixture design and thread lifecycle

- `callback_fixture.h` exposes the exact requested 11-operation API and asserts
  `sizeof(void *) == sizeof(uintptr_t)`.
- A single pthread mutex protects every shared callback pointer, userdata pointer, many-slot,
  worker record, in-flight count, and quiescence flag. A condition variable coordinates
  concurrent unregister operations and synchronous in-flight calls.
- The many-registration array and joinable-worker array are each bounded at 1,000 entries.
  `fixture_store_many` rejects out-of-range indexes and `fixture_unregister_and_join` resets
  the entire many array.
- Delayed calls use tracked joinable pthreads. Registration of a worker and its thread ID is
  atomic under the fixture mutex.
- `fixture_unregister_and_join` prevents new fixture operations, removes one tracked worker
  at a time into local ownership, unlocks before `pthread_join`, joins every worker, waits for
  any synchronous callback copied before quiescence, and only then clears callback pointers.
- Every callback is copied under the mutex and invoked after unlocking. There is no user
  callback invocation under the fixture lock and no fixture/runtime lock-order cycle.
- `fixture_active_native_slots` computes the exact number of non-null stored callback slots
  while holding the mutex. Binaries remain under ignored `kffi/build/callback-fixture`.

## Gradle wiring

- Host selection has explicit `macos`, `linux`, and `windows` branches. macOS registers the
  shared-library, arm64 object/archive, and test cinterop tasks; Linux registers only the
  pthread shared-library task; Windows registers none of the callback-fixture compile/archive/
  cinterop tasks.
- JVM uses `-dynamiclib` on macOS and `-shared` on Linux, with common
  `-std=c11 -fPIC -pthread` flags. On those hosts, `jvmTest` depends on the shared artifact and
  passes its absolute path through `kffi.callback.fixture.library`; the test opens it with
  `SymbolLookup.libraryLookup`.
- On Windows, `jvmTest` documents and applies the targeted
  `io.ygdrasil.kffi.CallbackFfiJvmTest` exclusion because this fixture requires pthreads; all
  other JVM tests remain enabled. There is no fake `.so`, Linux compile task, or pthread C
  command in the Windows task graph.
- `macosArm64Test` has a test-compilation-only `callbackFixture` cinterop. Its cinterop task
  depends on `libcallback_fixture.a`, which is embedded through `staticLibraries` and an
  explicit library path. The fixture test source lives in `macosArm64Test`, so non-macOS KSP
  and test compilations do not scan references to the macOS-only cinterop package.
- Existing main cinterops and non-host targets were not changed beyond reusing the existing
  `macosArm64` target instance.

## Scenario matrix

| Scenario | JVM FFM | macOS Native cinterop |
| --- | --- | --- |
| callback after allocation scope and registering function return | PASS | PASS |
| callback from a C-created thread | PASS | PASS |
| duplicate native invocation reaches `ONCE` at most once | PASS | PASS |
| 1,000 registrations fire in verified shuffled order to matching lambdas | PASS | PASS |
| callback after close is ignored | PASS | PASS |
| repeated create/deliver/close restores fixture and runtime baselines | PASS | PASS |
| unknown and cross-type routing tokens invoke no application code | PASS | PASS |
| callback and error-handler exceptions stay on the C thread | PASS | PASS |
| direct-call failure before publication leaves no entry | PASS | PASS |
| throw after C stores callback closes token-backed entry | PASS | PASS |
| normal no-userdata re-registration fails after retirement | PASS | PASS |
| native quiescence permits rearm and only new delivery | PASS | PASS |
| userdata roundtrip preserves exact token bits | PASS | PASS |

JVM trampolines use process-lifetime `Arena.global()` FFM upcall stubs. Native trampolines use
process-lifetime `staticCFunction` pointers. Both dispatch through the real `CallbackRuntime`,
real registrations, prepared activation, safe dispatch, and the platform token codec.

## Verification evidence

- `rtk cc -std=c11 -Wall -Wextra -Werror -fPIC -pthread -fsyntax-only kffi/src/ffiTest/resources/callback_fixture.c`
  — exit 0.
- `rtk ./gradlew :kffi:jvmTest --tests io.ygdrasil.kffi.CallbackFfiJvmTest`
  — `BUILD SUCCESSFUL`; 13 fixture cases passed.
- `rtk ./gradlew :kffi:macosArm64Test`
  — `BUILD SUCCESSFUL`; 68/68 tests passed, including all 13 fixture cases.
- `rtk ./gradlew :kffi:jvmTest -Pkffi.callbackFixture.hostForTest=windows --rerun-tasks`
  — `BUILD SUCCESSFUL`; the 66 non-FFI JVM tests ran and no
  `CallbackFfiJvmTest` result was produced.
- `:kffi:tasks --all` inspection with the host-test override showed four fixture tasks on
  macOS (shared, object, archive, cinterop), only the shared task on Linux, and no matching
  task on Windows. The Windows `jvmTest --dry-run` likewise contains no fixture compile task.
- `rtk git diff --check` — exit 0 before staging.
- Both suites apply `timeout = 10.seconds` to every one of their 13 cases. Fixture cleanup and
  registration/prepared cleanup run in `finally`, including failure paths.
- Every case captures initial runtime-registry and native-slot counts, runs
  `fixture_unregister_and_join` in final cleanup, and asserts both counts are restored.
- Artifact ignore check confirmed `.dylib`, `.a`, and `.o` outputs are excluded by
  `**/build/`.

## Concerns

- The macOS Native verification prints existing Kotlin/Native obsolete Worker API warnings
  from `CallbackRuntimeNativeTest`; this task neither introduces nor changes that API usage.
- Linux shared-library flags are wired but were not executable on the macOS arm64 host used
  for this verification. A real Windows runner was also unavailable: Windows validation used
  the explicit host-test override to exercise its Gradle configuration and JVM test filter on
  macOS, plus task-graph inspection. The Native archive/cinterop is intentionally host macOS
  arm64 only, as scoped by the brief.
