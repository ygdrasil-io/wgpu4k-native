# kffi P1 — Migration namespace + Backend Android Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrate the kffi runtime namespace from `io.ygdrasil.kffi` to `org.graphiks.kffi` in one pass, then rewrite the Android backend: a hand-written Kotlin layer (zero-allocation `NativeAddress`, confined arena, bounds-checked `MemoryBuffer`, UTF-8 `CString`) plus a native downcall engine (typed JNI wrapper table, struct-by-value wrappers, libffi fallback) and a dedicated upcall subsystem, with NDK packaging including 32-bit (armeabi-v7a) support.

**Architecture:** M0/M1 are mechanical and low-risk (SPI scenario standardization + versioned baselines, then the namespace migration with a single binding regeneration). M2 replaces the kffi Android runtime layer and deletes the `java/lang/foreign` shim + JNA. M3 adds the native downcall engine (a small per-ABI `.so` exposing a typed JNI wrapper table generated from the union of signature forms used by the target headers; anything outside the table goes through a libffi fallback). M4 adds the Android upcall subsystem (JNIEnv acquisition, global refs, trampoline free-list, return/exception marshaling) wired to the existing `CallbackRuntime`. M5 rewrites the kextract Android generator to emit the new backend and regenerates the wgpu bindings (removing the JNA readback patch). M6 integrates NDK packaging (jniLibs, abiFilters, `JNI_OnLoad`/`RegisterNatives`, R8) and closes the verification matrix.

**Tech Stack:** Kotlin Multiplatform 2.3.21, AGP 9.0.0 (compileSdk 36, minSdk 28), NDK + CMake (arm64-v8a / x86_64 / armeabi-v7a), `sun.misc.Unsafe` (raw memory access), libffi (fallback), JUnit5/Kotest, kextract.

---

## Sequencing decisions (read first)

- **Namespace migration regenerates the wgpu bindings exactly once** in M1 (after kextract's name plan is updated). The spec note "kextract doit cibler le nouveau namespace dès P2 (éviter double régénération)" is interpreted as *don't regenerate multiple times within P1*; the wgpu bindings must compile against the migrated kffi, so regeneration is mandatory here. M5 regenerates again because kextract's Android output shape changes — that is a distinct, deliberate regeneration.
- **`wgpu4k-native` androidMain + `demo/*` androidMain are temporarily uncompilable** from M2 until M5 regeneration restores them (they use the JNA `NativeAddress`/`Structure`/`Library` surface being removed). Milestones M2–M4 are verified via `:kffi` module tasks and `:kffi-benchmark-android` (which depends only on `:kffi` + `:kffi-benchmark-spi`). The build split is documented in each task's verification.
- **NDK is not installed** on this machine (checked `~/Library/Android/sdk/`). M3 starts with an NDK install task (AGP auto-download via `ndkVersion`; `android-sdk-license` is accepted). If the user refuses the download, stop at the end of M2 and report.
- **Instrumented tests require a device/emulator.** CI/verification for Android uses `assembleDebug` / `assembleDebugAndroidTest` as the gate; a `connectedAndroidTest` run is optional and device-gated (documented per task).
- **32-bit (I6) approach:** JNI wrapper signatures stay uniform (`jlong` for pointers/`long`), and the C wrapper adapts to native widths with `sizeof`-aware casts (`(long)(jlong)arg` truncates on 32-bit; returns sign-extend). This satisfies the correctness requirement without duplicating the Kotlin side per ABI. Token codec caps `maxToken` at pointer-bits width so 32-bit token routing cannot overflow.
- **Struct-by-value (C1):** struct *marshaling* (field offsets/padding) is computed by kextract and laid out into an arena buffer on the Kotlin side; the C wrapper assembles the struct from a caller-provided buffer (arg) or writes the returned struct into a caller-provided buffer (return). HFA/register rules are handled by the C compiler — kextract only needs offsets/sizes.

---

## Files map

| Path | Role |
|---|---|
| `kffi-benchmark-spi/.../Model.kt` | Add `BenchmarkScenario` enum (canonical scenario ids) |
| `kffi-benchmark-jvm/.../JmhJsonToMarkdown.kt` | Map JMH benchmark names → canonical scenarios; emit `<date>-<sha>.md` |
| `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.{h,c}` | Add no-routing-userdata callback variant |
| `kffi-benchmark-jvm/.../UpcallBenchmarks.kt` | Add no-token upcall benchmarks |
| `kffi-benchmark-native/.../NativeHarness.kt` | Use canonical scenario ids |
| `kffi-benchmark-android/.../AndroidHarness.kt` | Use canonical scenario ids |
| `kffi/benchmarks/results/<date>-<sha>-*.{md,json}` | Versioned baselines |
| `kffi/src/{commonMain,jvmMain,nativeMain,androidMain}/kotlin/io/ygdrasil/kffi/*` | Namespace rename → `org/graphiks/kffi` (44 files) |
| `kffi/src/androidMain/kotlin/java/lang/foreign/*` | **Deleted** (the 317-line shim) |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/NativeAddress.android.kt` | `@JvmInline value class` over `Long` |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryAllocator.android.kt` | Confined arena (bump alloc + free-list by size) |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryBuffer.android.kt` | Bounds-checked read/write via `sun.misc.Unsafe` |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/CString.android.kt` | UTF-8 alloc/copy + NUL-terminated read |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/AndroidUnsafe.kt` | Reflective `sun.misc.Unsafe` accessor |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.android.kt` | Token ↔ raw address |
| `kffi/src/androidMain/kotlin/org/graphiks/kffi/engine/NativeEngine.kt` | `external fun` + `dlopen`/`dlsym` via engine `.so` |
| `kffi/src/main/cpp/kffi_engine.c` | Downcall wrapper table + `JNI_OnLoad`/`RegisterNatives` |
| `kffi/src/main/cpp/kffi_upcall.c` | Upcall trampolines (JNIEnv acquisition, global refs) |
| `kffi/src/main/cpp/libffi_*` | Vendored/built libffi for fallback |
| `kffi/src/main/cpp/CMakeLists.txt` | CMake config for the engine `.so` |
| `kextract/.../KotlinKmpNamePlan.kt` | Runtime symbols → `org.graphiks.kffi.*`; remove `JNA_*`; add `NativeEngine`/`KFFI_*` symbols |
| `kextract/.../KotlinKmpAndroidBuilder.kt` | Rewrite: emit MemoryBuffer-backed structs + `NativeEngine` calls |
| `kextract/.../callbacks/KotlinCallbackAndroidEmitter.kt` | Rewrite: emit engine trampolines instead of JNA `CallbackReference` |
| `kextract/.../abi/*` | Record layout (offsets/padding) for Android |
| `wgpu4k-native/build.gradle.kts` | Namespace string + remove JNA readback patch + JNA dep + add consumer engine CMake |
| `wgpu4k-native/src/*Main/kotlin/io/ygdrasil/wgpu/wgpu_h*.kt` | Regenerated bindings (4 files) |
| `demo/*`, `gradle/publication-consumer/...` | Import updates + JNA removal in M5 |
| `kffi/build.gradle.kts` | `ndkVersion`, CMake, remove JNA dep, packaging |

---

## Milestone M0 — P0 polish (reviewer notes)

### Task M0.1: Standardize benchmark scenario names in the SPI

**Files:**
- Modify: `kffi-benchmark-spi/src/commonMain/kotlin/org/graphiks/kffi/benchmark/Model.kt`

- [ ] **Step 1: Write the failing test**

Create `kffi-benchmark-spi/src/commonTest/kotlin/org/graphiks/kffi/benchmark/ScenarioTest.kt`:

```kotlin
package org.graphiks.kffi.benchmark

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScenarioTest : FreeSpec({
    "canonical scenario ids are unique across axes" {
        val ids = BenchmarkScenario.entries.map { it.id }
        ids.toSet().size shouldBe ids.size
    }
    "every scenario declares its axis" {
        BenchmarkScenario.entries.forEach { it.axis shouldBe BenchmarkAxis.DOWNCALL.takeIf { _ -> it.name.startsWith("DOWN_") }
            .let { d ->
                d ?: BenchmarkAxis.UPCALL.takeIf { _ -> it.name.startsWith("UP_") }
                    ?: BenchmarkAxis.MARSHALING.takeIf { _ -> it.name.startsWith("MARSHAL_") }
                    ?: BenchmarkAxis.ARENA
            }
        }
    }
})
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi-benchmark-spi:jvmTest`
Expected: FAIL — `BenchmarkScenario` unresolved.

- [ ] **Step 3: Add the canonical scenario enum**

Modify `kffi-benchmark-spi/src/commonMain/kotlin/org/graphiks/kffi/benchmark/Model.kt` — append:

```kotlin
enum class BenchmarkScenario(val axis: BenchmarkAxis, val id: String) {
    DOWN_EMPTY(BenchmarkAxis.DOWNCALL, "downcall.empty"),
    DOWN_ADD4(BenchmarkAxis.DOWNCALL, "downcall.add4"),
    DOWN_ADD8(BenchmarkAxis.DOWNCALL, "downcall.add8"),
    DOWN_STRUCT_ARG(BenchmarkAxis.DOWNCALL, "downcall.struct_by_value_arg"),
    DOWN_STRUCT_RETURN(BenchmarkAxis.DOWNCALL, "downcall.struct_by_value_return"),
    DOWN_ROUNDTRIP_PTR(BenchmarkAxis.DOWNCALL, "downcall.roundtrip_ptr"),
    UP_FIRE_ONE(BenchmarkAxis.UPCALL, "upcall.fire_one"),
    UP_FIRE_1000(BenchmarkAxis.UPCALL, "upcall.fire_1000"),
    UP_FIRE_ONE_NO_ROUTING(BenchmarkAxis.UPCALL, "upcall.fire_one_no_routing"),
    UP_FIRE_1000_NO_ROUTING(BenchmarkAxis.UPCALL, "upcall.fire_1000_no_routing"),
    MARSHAL_SCALAR_I32(BenchmarkAxis.MARSHALING, "marshaling.scalar_i32"),
    MARSHAL_SCALAR_I64(BenchmarkAxis.MARSHALING, "marshaling.scalar_i64"),
    MARSHAL_ARRAY_I32_16(BenchmarkAxis.MARSHALING, "marshaling.array_i32_16"),
    MARSHAL_ARRAY_I32_1024(BenchmarkAxis.MARSHALING, "marshaling.array_i32_1024"),
    MARSHAL_ARRAY_F64_1024(BenchmarkAxis.MARSHALING, "marshaling.array_f64_1024"),
    MARSHAL_BASELINE_COPY(BenchmarkAxis.MARSHALING, "marshaling.baseline_copy"),
    ARENA_SCOPE_10(BenchmarkAxis.ARENA, "arena.scope_10"),
    ARENA_SCOPE_100(BenchmarkAxis.ARENA, "arena.scope_100"),
}
```

- [ ] **Step 4: Run the test to verify it passes**

Run: `./gradlew :kffi-benchmark-spi:jvmTest`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-spi
git commit -m "feat(benchmark): add canonical benchmark scenario ids to the SPI"
```

### Task M0.2: Emit canonical scenario ids from the native + Android harnesses

**Files:**
- Modify: `kffi-benchmark-native/src/macosMain/kotlin/org/graphiks/kffi/benchmark/native/NativeHarness.kt`
- Modify: `kffi-benchmark-android/src/androidInstrumentedTest/kotlin/org/graphiks/kffi/benchmark/android/AndroidHarness.kt`

- [ ] **Step 1: Write the failing check**

Run: `./gradlew :kffi-benchmark-native:compileKotlinMacosArm64`
Expected: still compiles today (scenarios are plain strings); the *behavioral* change is enforced in Step 2 via the JMH mapping task, but update the harnesses now so the ids match the SPI. There is no separate failing test; the canonicalization is asserted by M0.4's report.

- [ ] **Step 2: Update NativeHarness.kt**

In `NativeHarness.kt`, replace the two `BenchmarkResult(...)` scenario strings:

```kotlin
results += BenchmarkResult(BenchmarkScenario.DOWN_EMPTY.axis, BenchmarkScenario.DOWN_EMPTY.id, "native-macosArm64", benchDowncallEmpty().toDouble())
results += BenchmarkResult(BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis, BenchmarkScenario.MARSHAL_ARRAY_I32_16.id, "native-macosArm64", benchMarshaling().toDouble())
```

Add the import `org.graphiks.kffi.benchmark.BenchmarkScenario`.

- [ ] **Step 3: Update AndroidHarness.kt**

Replace the `BenchmarkResult(...)` in `AndroidHarness.kt`:

```kotlin
results += BenchmarkResult(
    BenchmarkScenario.MARSHAL_ARRAY_I32_16.axis,
    BenchmarkScenario.MARSHAL_ARRAY_I32_16.id,
    "android-device",
    ns / 1000.0,
)
```

Add the import `org.graphiks.kffi.benchmark.BenchmarkScenario`.

- [ ] **Step 4: Verify**

Run: `./gradlew :kffi-benchmark-native:compileKotlinMacosArm64`
Expected: SUCCESS.

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-native kffi-benchmark-android
git commit -m "feat(benchmark): emit canonical scenario ids from native and android harnesses"
```

### Task M0.3: Add the no-routing-userdata upcall variant to the C bench fixture

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.h`
- Modify: `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c`

- [ ] **Step 1: Extend the header**

In `bench_fixture.h`, after the existing `bench_callback` typedef, add:

```c
typedef void (*bench_callback_no_userdata)(uint32_t value);

void bench_set_callback_no_userdata(bench_callback_no_userdata cb);
void bench_fire_no_userdata(uint32_t count);
```

- [ ] **Step 2: Extend the implementation**

In `bench_fixture.c`, add:

```c
static bench_callback_no_userdata g_callback_no_userdata = NULL;

void bench_set_callback_no_userdata(bench_callback_no_userdata cb) {
    g_callback_no_userdata = cb;
}

void bench_fire_no_userdata(uint32_t count) {
    for (uint32_t i = 0; i < count; ++i) {
        if (g_callback_no_userdata != NULL) {
            g_callback_no_userdata(i);
        }
    }
}
```

- [ ] **Step 3: Verify the fixture compiles**

Run: `./gradlew :kffi-benchmark-jvm:compileBenchFixtureShared`
Expected: SUCCESS, produces `kffi-benchmark-jvm/build/bench-fixture/libbench_fixture.dylib`.

- [ ] **Step 4: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/resources
git commit -m "feat(benchmark): add no-routing-userdata callback variant to bench fixture"
```

### Task M0.4: Add no-token upcall benchmarks (JVM)

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt`

- [ ] **Step 1: Write the failing code path**

Add to `UpcallBenchmarks.kt` (mirrors the existing routed pattern):

```kotlin
private fun interface BenchCallbackNoUserdata : Callback {
    fun invoke(value: UInt)
}

private val BenchCallbackNoUserdataType: CallbackType<BenchCallbackNoUserdata> = CallbackType(
    canonicalId = "bench:BenchCallbackNoUserdata",
    hasRoutingUserdata = false,
)

private object BenchCallbackNoUserdataTrampoline {
    private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)
    private val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findStatic(
            BenchCallbackNoUserdataTrampoline::class.java,
            "invoke",
            descriptor.toMethodType(),
        )
    }
    val address: NativeAddress by lazy {
        NativeAddress(Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global()))
    }

    @JvmStatic
    private fun invoke(value: Int) {
        CallbackRuntime.dispatchSafely(BenchCallbackNoUserdataType, null) { callback ->
            callback.invoke(value.toUInt())
        }
    }
}
```

- [ ] **Step 2: Add the setup + benchmarks**

In `UpcallBenchmarks`:

```kotlin
private lateinit var setCallbackNoUserdataHandle: MethodHandle
private lateinit var fireNoUserdataHandle: MethodHandle
private lateinit var noUserdataRegistration: CallbackRegistration<BenchCallbackNoUserdata>
```

In `setup()`:

```kotlin
setCallbackNoUserdataHandle = linker.downcallHandle(
    l.find("bench_set_callback_no_userdata").orElseThrow(),
    FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
)
fireNoUserdataHandle = linker.downcallHandle(
    l.find("bench_fire_no_userdata").orElseThrow(),
    FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
)
noUserdataRegistration = io.ygdrasil.kffi.CallbackRuntime.register(
    type = BenchCallbackNoUserdataType,
    trampoline = BenchCallbackNoUserdataTrampoline.address,
    policy = CallbackPolicy.REPEATING,
) { _ -> counter.fetchAndAdd(1) }
setCallbackNoUserdataHandle.invokeExact(noUserdataRegistration.callback.handler as MemorySegment)
```

Benchmarks:

```kotlin
@Benchmark
fun upcallFireOneNoRouting(bh: Blackhole) {
    fireNoUserdataHandle.invokeExact(1)
    bh.consume(counter.load())
}

@Benchmark
fun upcallFire1000NoRouting(bh: Blackhole) {
    fireNoUserdataHandle.invokeExact(1000)
    bh.consume(counter.load())
}
```

In `teardown()`: `noUserdataRegistration.close()`.
Update `main()` with the two new `BenchmarkResult` entries using `BenchmarkScenario.UP_FIRE_ONE_NO_ROUTING`/`UP_FIRE_1000_NO_ROUTING` ids.

- [ ] **Step 3: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS.

- [ ] **Step 4: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt
git commit -m "feat(benchmark): add JVM upcall benchmarks without routing userdata"
```

### Task M0.5: Versioned baselines `<date>-<commit>.md` + raw JSON

**Files:**
- Modify: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/JmhJsonToMarkdown.kt`
- Create: `kffi/benchmarks/results/2026-08-11-<sha>-jvm-baseline.md` + `.json` (renamed copies)

- [ ] **Step 1: Rewrite the converter to emit canonical scenarios + versioned name**

Modify `JmhJsonToMarkdown.kt` to:

```kotlin
package org.graphiks.kffi.benchmark.jvm

import com.google.gson.JsonParser
import java.io.File
import java.time.LocalDate

private val benchmarkToScenario: Map<String, String> = mapOf(
    "DowncallBenchmarks.empty" to "downcall.empty",
    "DowncallBenchmarks.add4" to "downcall.add4",
    "DowncallBenchmarks.add8" to "downcall.add8",
    "DowncallBenchmarks.pairSum" to "downcall.struct_by_value_arg",
    "DowncallBenchmarks.makePair" to "downcall.struct_by_value_return",
    "UpcallBenchmarks.upcallFireOne" to "upcall.fire_one",
    "UpcallBenchmarks.upcallFire1000" to "upcall.fire_1000",
    "UpcallBenchmarks.upcallFireOneNoRouting" to "upcall.fire_one_no_routing",
    "UpcallBenchmarks.upcallFire1000NoRouting" to "upcall.fire_1000_no_routing",
    "MarshalingBenchmarks.writeReadIntScalar" to "marshaling.scalar_i32",
    "MarshalingBenchmarks.writeReadLongScalar" to "marshaling.scalar_i64",
    "MarshalingBenchmarks.copyInts16" to "marshaling.array_i32_16",
    "MarshalingBenchmarks.copyInts1024" to "marshaling.array_i32_1024",
    "MarshalingBenchmarks.copyDoubles1024" to "marshaling.array_f64_1024",
    "MarshalingBenchmarks.baselineByteArrayCopy" to "marshaling.baseline_copy",
    "ArenaBenchmarks.memoryScopeTenAllocs" to "arena.scope_10",
    "ArenaBenchmarks.memoryScopeHundredAllocs" to "arena.scope_100",
)

fun main(args: Array<String>) {
    val jsonPath = args.getOrNull(0) ?: error("usage: <jmh-results.json> <commit-sha>")
    val commit = args.getOrNull(1) ?: error("usage: <jmh-results.json> <commit-sha>")
    val root = JsonParser.parseString(File(jsonPath).readText()).asJsonArray
    val date = LocalDate.now().toString()
    val outBase = File("kffi/benchmarks/results/$date-$commit")
    File(jsonPath).copyTo(File("${outBase.path}-jvm-baseline.json"), overwrite = true)

    val lines = mutableListOf("# kffi JVM benchmark report (JMH)")
    lines.add("")
    lines.add("Date: $date")
    lines.add("Commit: $commit")
    lines.add("")
    lines.add("| Scenario | ns/op |")
    lines.add("|---|---|")
    root.forEach { bench ->
        val benchName = bench.asJsonObject["benchmark"].asString
        val simple = benchName.substringAfterLast('.')
        val enclosing = benchName.substringAfter("org.graphiks.kffi.benchmark.jvm.").substringBeforeLast('.')
        val scenario = benchmarkToScenario["$enclosing.$simple"] ?: benchName
        val score = bench.asJsonObject["primaryMetric"]["score"].asDouble
        lines.add("| $scenario | ${"%.2f".format(score)} |")
    }
    File("${outBase.path}-jvm-baseline.md").parentFile.mkdirs()
    File("${outBase.path}-jvm-baseline.md").writeText(lines.joinToString("\n") + "\n")
    println("wrote ${outBase.path}-jvm-baseline.{md,json}")
}
```

- [ ] **Step 2: Re-run the JVM JMH suite**

Run: `./gradlew :kffi-benchmark-jvm:jmh`
Expected: SUCCESS (several minutes). Requires the fixture + the new no-userdata path.

- [ ] **Step 3: Generate the versioned report**

Run (from repo root):

```bash
SHA=$(git rev-parse --short HEAD)
./gradlew :kffi-benchmark-jvm:compileJmhKotlin
# classpath: use the jmh runtime jar already on disk; fall back to manual copy of results.json:
cp kffi-benchmark-jvm/build/results/jmh/results.json "kffi/benchmarks/results/$(date +%Y-%m-%d)-$SHA-jvm-baseline.json"
```

Then hand-produce the sibling `.md` using the converter logic above (if running the `main` via Gradle is fiddly, transcribe the canonical scenario table from `results.json` into `<date>-<sha>-jvm-baseline.md`).

- [ ] **Step 4: Remove the old unversioned baseline**

```bash
git rm kffi/benchmarks/results/2026-08-11-jvm-baseline.md
```

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-jvm kffi/benchmarks
git commit -m "feat(benchmark): version JVM baselines by date-commit with raw JSON"
```

---

## Milestone M1 — Namespace migration `io.ygdrasil.kffi` → `org.graphiks.kffi`

### Task M1.1: Migrate the kffi module sources + build script

**Files:**
- Modify: `kffi/build.gradle.kts` (namespace + 3 exclude strings)
- Move: `kffi/src/*/kotlin/io/ygdrasil/kffi/*` → `.../org/graphiks/kffi/*` (44 files)

- [ ] **Step 1: Move the source directories**

```bash
cd kffi/src
for s in commonMain jvmMain nativeMain androidMain commonTest jvmTest nativeTest macosArm64Test androidUnitTest; do
  mkdir -p "$s/kotlin/org/graphiks/kffi"
  git mv "$s/kotlin/io/ygdrasil/kffi/"* "$s/kotlin/org/graphiks/kffi/" 2>/dev/null || true
done
# remove now-empty io dirs
find . -type d -path "*io/ygdrasil*" -empty -delete 2>/dev/null || true
cd ..
```

- [ ] **Step 2: Rewrite the package declarations**

In each moved `.kt` file, replace `package io.ygdrasil.kffi` with `package org.graphiks.kffi`, and any `io.ygdrasil.kffi.` references within the module (the two `java/lang/foreign` shim imports) with `org.graphiks.kffi.`.

Run a verification grep — expected: zero matches for `io.ygdrasil.kffi` under `kffi/src`:

```bash
rg "io\.ygdrasil\.kffi" kffi/src || echo "clean"
```

- [ ] **Step 3: Update `kffi/build.gradle.kts`**

- Line 169: `namespace = "org.graphiks.kffi"`
- Line 255: `excludeTestsMatching("org.graphiks.kffi.MemoryBufferArrayTest")`
- Line 285: `excludeTestsMatching("org.graphiks.kffi.CallbackFfiJvmTest")`
- Line 286: `excludeTestsMatching("org.graphiks.kffi.CallbackFixtureWatchdogJvmTest")`

- [ ] **Step 4: Verify kffi JVM + native tests**

Run: `./gradlew :kffi:jvmTest`
Expected: 87 tests, 0 failures (same count as P0).

Run: `./gradlew :kffi:macosArm64Test`
Expected: SUCCESS.

- [ ] **Step 5: Commit**

```bash
git add -A kffi
git commit -m "refactor(kffi): migrate runtime namespace to org.graphiks.kffi"
```

### Task M1.2: Migrate kextract name plan + tests

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt:35-51`
- Modify: 7 kextract test files asserting the old string (see list in Task M1.2 step 2)

- [ ] **Step 1: Update the runtime symbol qualified names**

In `KotlinKmpNamePlan.kt`, replace the `io.ygdrasil.kffi.*` prefixes (lines 35–51) with `org.graphiks.kffi.*`. The `JNA_*` symbols stay for now (still used until M5). Do **not** touch `io.ygdrasil.wgpu`/target-package handling.

- [ ] **Step 2: Update kextract tests**

Files asserting `io.ygdrasil.kffi` (replace with `org.graphiks.kffi`):
- `kextract/src/test/kotlin/org/graphiks/kextract/integration/KmpNamePlanIntegrationTest.kt` (line 76)
- `KmpJvmNativeBootstrapIntegrationTest.kt` (241, 264)
- `KmpJvmEnumAbiTest.kt` (98-99, 203-204)
- `KmpJvmDirectCallbackTransactionTest.kt` (40-42)
- `KmpAndroidJnaAbiTest.kt` (302, 366)
- `KmpJvmCompilationSupport.kt` (139, 225)
- `CallbackGeneratorIntegrationTest.kt` (90, 163)

- [ ] **Step 3: Verify kextract tests**

Run: `./gradlew :kextract:test`
Expected: SUCCESS.

- [ ] **Step 4: Commit**

```bash
git add kextract
git commit -m "refactor(kextract): target org.graphiks.kffi runtime namespace"
```

### Task M1.3: Regenerate the wgpu bindings + migrate consumers

**Files:**
- Modify: `wgpu4k-native/build.gradle.kts` (line 308 lookup import string)
- Regenerate: `wgpu4k-native/src/*Main/kotlin/io/ygdrasil/wgpu/wgpu_h*.kt`
- Modify: benchmark harnesses, demos, publication consumer (import lines)

- [ ] **Step 1: Update the JVM bootstrap verification string**

In `wgpu4k-native/build.gradle.kts` line 308:
`val genericJvmLookupImport = "import org.graphiks.kffi.findOrThrow"`

- [ ] **Step 2: Regenerate the bindings**

Run: `./gradlew :wgpu4k-native:generateBindingsFromHeader`
Expected: SUCCESS. The 4 `wgpu_h*.kt` files now import `org.graphiks.kffi.*`.
If header download tasks fail (network), fall back to a mechanical import rewrite of the 4 generated files (`io.ygdrasil.kffi` → `org.graphiks.kffi`, all occurrences are in the import block, verified) and run `verifyJvmBootstrapBinding`.

- [ ] **Step 3: Migrate the remaining consumers**

Replace `io.ygdrasil.kffi` → `org.graphiks.kffi` in:
- `kffi-benchmark-jvm` (UpcallBenchmarks imports, MarshalingBenchmarks, ArenaBenchmarks)
- `kffi-benchmark-native/NativeHarness.kt` (fully-qualified `io.ygdrasil.kffi.memoryScope`)
- `kffi-benchmark-android/AndroidHarness.kt`
- `demo/common`, `demo/desktop-and-ios`, `demo/android`
- `gradle/publication-consumer/src/main/java/PublicationSmoke.java`
- `wgpu4k-native` test files (GeneratedCallback*Test, CallbackInfoFactoryJvmTest, JvmBootstrapProbe)

- [ ] **Step 4: Verify the full build**

Run: `./gradlew :kffi:jvmTest :kextract:test :kffi-benchmark-jvm:compileJmhKotlin`
Expected: all SUCCESS.

Run: `./gradlew :wgpu4k-native:verifyJvmBootstrapBinding :wgpu4k-native:compileKotlinJvm`
Expected: SUCCESS.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "refactor: migrate wgpu bindings and consumers to org.graphiks.kffi"
```

---

## Milestone M2 — Android Kotlin layer (couche Kotlin maison)

> Scope guard: from M2 through M4, `wgpu4k-native` and `demo/*` androidMain do not compile (they still reference the JNA surface). Verify via `:kffi` + `:kffi-benchmark-android` tasks. Document this in each commit message.

### Task M2.1: Add `AndroidUnsafe` and the value-class `NativeAddress`

**Files:**
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/AndroidUnsafe.kt`
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/NativeAddress.android.kt` (overwrite)

- [ ] **Step 1: Write the failing test (androidUnitTest)**

Create `kffi/src/androidUnitTest/kotlin/org/graphiks/kffi/AndroidUnsafeTest.kt`:

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AndroidUnsafeTest : FreeSpec({
    "raw memory round-trips through Unsafe" {
        val addr = AndroidUnsafe.get().allocateMemory(32)
        try {
            AndroidUnsafe.get().putInt(addr, 0x11223344)
            AndroidUnsafe.get().getInt(addr) shouldBe 0x11223344
            AndroidUnsafe.get().putLong(addr, 0x0102030405060708L)
            AndroidUnsafe.get().getLong(addr) shouldBe 0x0102030405060708L
        } finally {
            AndroidUnsafe.get().freeMemory(addr)
        }
    }
    "pointer size reflects the running ABI" {
        AndroidUnsafe.get().addressSize() shouldBe 8
    }
})
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:testDebugUnitTest`
Expected: FAIL — `AndroidUnsafe` unresolved.

- [ ] **Step 3: Implement**

Create `AndroidUnsafe.kt`:

```kotlin
package org.graphiks.kffi

import java.lang.reflect.Field

internal object AndroidUnsafe {
    private val theUnsafe: sun.misc.Unsafe by lazy {
        val field: Field = sun.misc.Unsafe::class.java.getDeclaredField("theUnsafe")
        field.isAccessible = true
        field.get(null) as sun.misc.Unsafe
    }

    fun get(): sun.misc.Unsafe = theUnsafe
}
```

Create `NativeAddress.android.kt` (overwrite):

```kotlin
package org.graphiks.kffi

@JvmInline
value class AndroidNativeAddress(val rawValue: Long)

actual typealias NativeAddress = AndroidNativeAddress

fun NativeAddress?.adapt(): Long = if (this == null) 0 else this.rawValue
```

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kffi:testDebugUnitTest`
Expected: PASS (AndroidUnsafeTest + existing `CallbackTokenAddressCodecAndroidTest` will fail until M2.2 — see note).

> If `actual typealias NativeAddress = AndroidNativeAddress` fails to compile against `expect class NativeAddress`, fall back to `@JvmInline actual value class NativeAddress actual constructor(val rawValue: Long)` and mirror the constructor in the JVM/native actuals (they already accept a single handle argument). Re-run the test.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/androidMain kffi/src/androidUnitTest
git commit -m "feat(kffi): zero-allocation Android NativeAddress value class over raw long"
```

### Task M2.2: Rewrite the Android token codec

**Files:**
- Modify: `kffi/src/androidMain/kotlin/org/graphiks/kffi/CallbackTokenAddressCodec.android.kt`

- [ ] **Step 1: Write the failing test**

Update `kffi/src/androidUnitTest/kotlin/org/graphiks/kffi/CallbackTokenAddressCodecAndroidTest.kt` — replace the JNA `Pointer` construction with the value class:

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackTokenAddressCodecAndroidTest : FreeSpec({
    "round-trips valid tokens" {
        for (token in listOf(1uL, 42uL, Long.MAX_VALUE.toULong())) {
            PlatformCallbackTokenAddressCodec.decode(
                PlatformCallbackTokenAddressCodec.encode(token),
            ) shouldBe token
        }
    }
    "null decodes to null" {
        PlatformCallbackTokenAddressCodec.decode(null) shouldBe null
    }
    "rejects out-of-range tokens" {
        shouldThrow<IllegalArgumentException> { PlatformCallbackTokenAddressCodec.encode(0uL) }
        shouldThrow<IllegalArgumentException> { PlatformCallbackTokenAddressCodec.encode(Long.MAX_VALUE.toULong() + 1uL) }
    }
    "pointerBits and maxToken match the ABI" {
        PlatformCallbackTokenAddressCodec.pointerBits shouldBe AndroidUnsafe.get().addressSize() * 8
        PlatformCallbackTokenAddressCodec.maxToken shouldBe
            (if (AndroidUnsafe.get().addressSize() == 8) Long.MAX_VALUE else UInt.MAX_VALUE).toULong()
    }
})
```

Add `import io.kotest.assertions.throwables.shouldThrow`.

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*CallbackTokenAddressCodec*"`
Expected: FAIL — `Pointer` unresolved (test now uses the value class but the codec still returns JNA `Pointer`).

- [ ] **Step 3: Implement**

Rewrite `CallbackTokenAddressCodec.android.kt`:

```kotlin
package org.graphiks.kffi

internal actual object PlatformCallbackTokenAddressCodec : CallbackTokenAddressCodec {
    private val pointerBytes: Int = AndroidUnsafe.get().addressSize()

    actual override val pointerBits: Int = pointerBytes * 8
    actual override val maxToken: ULong =
        if (pointerBytes == 8) Long.MAX_VALUE.toULong() else UInt.MAX_VALUE.toULong()

    actual override fun encode(token: ULong): NativeAddress {
        requireValidCallbackToken(token)
        require(token <= maxToken) {
            "Callback token $token exceeds the ${pointerBits}-bit pointer ABI"
        }
        return NativeAddress(token.toLong())
    }

    actual override fun decode(address: NativeAddress?): ULong? {
        val token = address?.rawValue?.toULong() ?: return null
        requireValidCallbackToken(token)
        return token
    }
}
```

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kffi:testDebugUnitTest`
Expected: PASS (both codec + Unsafe tests).

- [ ] **Step 5: Commit**

```bash
git add kffi/src/androidMain kffi/src/androidUnitTest
git commit -m "feat(kffi): Android callback token codec over raw addresses"
```

### Task M2.3: Confined arena allocator (bump alloc + free-list)

**Files:**
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryAllocator.android.kt` (overwrite)

- [ ] **Step 1: Write the failing test**

Create `kffi/src/androidUnitTest/kotlin/org/graphiks/kffi/MemoryAllocatorAndroidTest.kt`:

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryAllocatorAndroidTest : FreeSpec({
    "allocations are distinct and aligned" {
        memoryScope { allocator ->
            val a = allocator.allocate(8L)
            val b = allocator.allocate(8L)
            a.rawValue shouldNotBe b.rawValue
            a.rawValue % 8L shouldBe 0L
            b.rawValue % 8L shouldBe 0L
        }
    }
    "bufferOf writes a long" {
        memoryScope { allocator ->
            val buffer = allocator.bufferOf(42L)
            buffer.readLong(0uL) shouldBe 42L
        }
    }
    "allocateBuffer is usable for reads/writes" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(16uL)
            buffer.writeInt(7, 0uL)
            buffer.readInt(0uL) shouldBe 7
        }
    }
    "repeated same-size allocations reuse the free-list" {
        memoryScope { allocator ->
            val first = allocator.allocate(64L)
            first.rawValue shouldNotBe 0L
            // second block of the same size is serviced from the same bump block
            val second = allocator.allocate(64L)
            second.rawValue shouldNotBe first.rawValue
        }
    }
    "close is idempotent and frees the arena" {
        val allocator = MemoryAllocator()
        allocator.allocate(8L)
        allocator.close()
        allocator.close()
    }
})
```

Add imports: `io.kotest.matchers.shouldNotBe`.

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*MemoryAllocatorAndroidTest*"`
Expected: FAIL — `rawValue` accessor missing / old `JnaArena` behavior.

- [ ] **Step 3: Implement**

Rewrite `MemoryAllocator.android.kt`:

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import java.nio.charset.StandardCharsets

internal class AndroidArena : AutoCloseable {
    private data class Block(val base: Long, val capacity: Long) {
        var offset: Long = 0
    }

    private val blocks = mutableListOf<Block>()
    private var current: Block? = null
    private val freeBySize = HashMap<Long, MutableList<Long>>()
    private val unsafe = AndroidUnsafe.get()
    private val defaultBlock = 1L shl 16

    fun allocate(size: Long): Long {
        val aligned = alignUp(size, 8L)
        val freed = freeBySize[aligned]?.takeIf { it.isNotEmpty() }?.removeLast()
        if (freed != null) return freed
        var block = current
        if (block == null || block.offset + aligned > block.capacity) {
            val capacity = maxOf(defaultBlock, alignUp(aligned, defaultBlock))
            val base = unsafe.allocateMemory(capacity)
            unsafe.setMemory(base, capacity, 0)
            block = Block(base, capacity)
            blocks.add(block)
            current = block
        }
        val addr = block.base + block.offset
        block.offset += aligned
        return addr
    }

    fun free(addr: Long, size: Long) {
        freeBySize.getOrPut(alignUp(size, 8L)) { mutableListOf() }.add(addr)
    }

    override fun close() {
        blocks.forEach { unsafe.freeMemory(it.base) }
        blocks.clear()
        current = null
        freeBySize.clear()
    }

    private fun alignUp(value: Long, alignment: Long): Long =
        (value + alignment - 1) and -alignment
}

actual class MemoryAllocator : AutoCloseable {
    private val arena = AndroidArena()

    actual fun allocate(sizeInByte: Long): NativeAddress = NativeAddress(arena.allocate(sizeInByte))

    actual override fun close() { arena.close() }

    actual fun bufferOf(value: Long): MemoryBuffer {
        val addr = arena.allocate(Long.SIZE_BYTES.toLong())
        AndroidUnsafe.get().putLong(addr, value)
        return MemoryBuffer(NativeAddress(addr), Long.SIZE_BYTES.toULong())
    }

    actual fun allocateFrom(value: String): CString {
        val bytes = value.toByteArray(StandardCharsets.UTF_8)
        val addr = arena.allocate(bytes.size + 1L)
        AndroidUnsafe.get().copyMemory(bytes, 16, null, addr, bytes.size.toLong())
        AndroidUnsafe.get().putByte(addr + bytes.size, 0)
        return CString(NativeAddress(addr))
    }

    actual fun bufferOfAddress(value: NativeAddress): MemoryBuffer = bufferOf(value.rawValue)

    actual fun allocateBuffer(size: ULong): MemoryBuffer =
        MemoryBuffer(NativeAddress(arena.allocate(size.toLong())), size)

    actual fun bufferOfAddresses(value: List<NativeAddress>): MemoryBuffer {
        val buffer = allocateBuffer((value.size * 8).toULong())
        value.forEachIndexed { index, address ->
            buffer.writePointer(address, (index * 8).toULong())
        }
        return buffer
    }
}
```

> Note: `unsafe.copyMemory(bytes, 16, null, addr, ...)` copies from a `byte[]` at array-base offset 16 (JVM array header). This is the standard `Unsafe` array-copy idiom.

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*MemoryAllocatorAndroidTest*"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/androidMain kffi/src/androidUnitTest
git commit -m "feat(kffi): confined Android arena with bump alloc and per-size free-list"
```

### Task M2.4: Bounds-checked MemoryBuffer

**Files:**
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/MemoryBuffer.android.kt` (overwrite)

- [ ] **Step 1: Write the failing test**

Create `kffi/src/androidUnitTest/kotlin/org/graphiks/kffi/MemoryBufferAndroidTest.kt` (subset; mirror `MemoryBufferArrayTest` bounds contract):

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemoryBufferAndroidTest : FreeSpec({
    "scalar read/write round-trips" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(32uL)
            buffer.writeByte(1, 0uL)
            buffer.readByte(0uL) shouldBe 1
            buffer.writeInt(0x01020304, 4uL)
            buffer.readInt(4uL) shouldBe 0x01020304
            buffer.writeLong(0x0102030405060708L, 8uL)
            buffer.readLong(8uL) shouldBe 0x0102030405060708L
            buffer.writeDouble(3.5, 16uL)
            buffer.readDouble(16uL) shouldBe 3.5
            buffer.writeFloat(1.5f, 24uL)
            buffer.readFloat(24uL) shouldBe 1.5f
        }
    }
    "scalar write beyond bounds throws" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(8uL)
            shouldThrow<IndexOutOfBoundsException> { buffer.writeInt(0, 6uL) }
            shouldThrow<IndexOutOfBoundsException> { buffer.readLong(4uL) }
        }
    }
    "bulk write/read with bad offsets throws IllegalArgumentException" {
        memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(16uL)
            shouldThrow<IllegalArgumentException> { buffer.writeInts(IntArray(8), bufferOffset = 8uL) }
            shouldThrow<IllegalArgumentException> { buffer.writeInts(IntArray(8), arrayIndex = 4u) }
        }
    }
})
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*MemoryBufferAndroidTest*"`
Expected: FAIL — JNA-style API / no bounds checks.

- [ ] **Step 3: Implement**

Rewrite `MemoryBuffer.android.kt` (complete; shows the scalar + bulk pattern):

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi

actual class MemoryBuffer actual constructor(
    actual val handler: NativeAddress,
    actual val size: ULong,
) {
    private val unsafe = AndroidUnsafe.get()
    private val base = handler.rawValue

    private fun boundsCheck(offset: ULong, width: Long) {
        require(offset.toLong() + width <= size.toLong()) {
            "Out of destination bounds: offset=$offset width=$width size=$size"
        }
    }

    actual fun writeByte(value: Byte, offset: ULong) { boundsCheck(offset, 1); unsafe.putByte(base + offset.toLong(), value) }
    actual fun readByte(offset: ULong): Byte { boundsCheck(offset, 1); return unsafe.getByte(base + offset.toLong()) }
    actual fun writeUByte(value: UByte, offset: ULong) { writeByte(value.toByte(), offset) }
    actual fun readUByte(offset: ULong): UByte = readByte(offset).toUByte()
    actual fun writeShort(value: Short, offset: ULong) { boundsCheck(offset, 2); unsafe.putShort(base + offset.toLong(), value) }
    actual fun readShort(offset: ULong): Short { boundsCheck(offset, 2); return unsafe.getShort(base + offset.toLong()) }
    actual fun writeUShort(value: UShort, offset: ULong) { writeShort(value.toShort(), offset) }
    actual fun readUShort(offset: ULong): UShort = readShort(offset).toUShort()
    actual fun writeInt(value: Int, offset: ULong) { boundsCheck(offset, 4); unsafe.putInt(base + offset.toLong(), value) }
    actual fun readInt(offset: ULong): Int { boundsCheck(offset, 4); return unsafe.getInt(base + offset.toLong()) }
    actual fun writeUInt(value: UInt, offset: ULong) { writeInt(value.toInt(), offset) }
    actual fun readUInt(offset: ULong): UInt = readInt(offset).toUInt()
    actual fun writeLong(value: Long, offset: ULong) { boundsCheck(offset, 8); unsafe.putLong(base + offset.toLong(), value) }
    actual fun readLong(offset: ULong): Long { boundsCheck(offset, 8); return unsafe.getLong(base + offset.toLong()) }
    actual fun writeULong(value: ULong, offset: ULong) { writeLong(value.toLong(), offset) }
    actual fun readULong(offset: ULong): ULong = readLong(offset).toULong()
    actual fun writeFloat(value: Float, offset: ULong) { boundsCheck(offset, 4); unsafe.putFloat(base + offset.toLong(), value) }
    actual fun readFloat(offset: ULong): Float { boundsCheck(offset, 4); return unsafe.getFloat(base + offset.toLong()) }
    actual fun writeDouble(value: Double, offset: ULong) { boundsCheck(offset, 8); unsafe.putDouble(base + offset.toLong(), value) }
    actual fun readDouble(offset: ULong): Double { boundsCheck(offset, 8); return unsafe.getDouble(base + offset.toLong()) }
    actual fun writePointer(value: NativeAddress, offset: ULong) {
        boundsCheck(offset, 8)
        if (unsafe.addressSize() == 8) unsafe.putLong(base + offset.toLong(), value.rawValue)
        else unsafe.putInt(base + offset.toLong(), value.rawValue.toInt())
    }
    actual fun readPointer(offset: ULong): NativeAddress {
        boundsCheck(offset, 8)
        val raw = if (unsafe.addressSize() == 8) unsafe.getLong(base + offset.toLong())
            else unsafe.getInt(base + offset.toLong()).toLong()
        return NativeAddress(raw)
    }

    private fun writeArray(
        arrayBytes: Int, elementSize: Int, array: Any,
        arrayIndex: ULong, bufferOffset: ULong, size: ULong,
    ) {
        val bytes = size.toLong() * elementSize
        val dest = bufferOffset.toLong()
        val src = arrayIndex.toLong() * elementSize
        require(dest + bytes <= this.size.toLong()) { "Out of destination bounds" }
        require(src + bytes <= arrayBytes.toLong()) { "Out of source bounds" }
        unsafe.copyMemory(array, 16L + src, null, base + dest, bytes)
    }

    private fun readArray(
        arrayBytes: Int, elementSize: Int, array: Any,
        arrayIndex: ULong, bufferOffset: ULong, size: ULong,
    ) {
        val bytes = size.toLong() * elementSize
        val src = bufferOffset.toLong()
        val dest = arrayIndex.toLong() * elementSize
        require(src + bytes <= this.size.toLong()) { "Out of source bounds" }
        require(dest + bytes <= arrayBytes.toLong()) { "Out of destination bounds" }
        unsafe.copyMemory(null, base + src, array, 16L + dest, bytes)
    }

    actual fun writeBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun readBytes(array: ByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun writeUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun readUBytes(array: UByteArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size, 1, array, arrayIndex, bufferOffset, size)
    actual fun writeShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun readShorts(array: ShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun writeUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun readUShorts(array: UShortArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 2, 2, array, arrayIndex, bufferOffset, size)
    actual fun writeInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readInts(array: IntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readUInts(array: UIntArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readLongs(array: LongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun writeULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readULongs(array: ULongArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun writeFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun readFloats(array: FloatArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 4, 4, array, arrayIndex, bufferOffset, size)
    actual fun writeDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        writeArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
    actual fun readDoubles(array: DoubleArray, arrayIndex: ULong, bufferOffset: ULong, size: ULong) =
        readArray(array.size * 8, 8, array, arrayIndex, bufferOffset, size)
}
```

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*MemoryBufferAndroidTest*"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/androidMain kffi/src/androidUnitTest
git commit -m "feat(kffi): bounds-checked Android MemoryBuffer over raw memory"
```

### Task M2.5: CString + delete the shim + drop JNA from kffi

**Files:**
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/CString.android.kt` (overwrite)
- Delete: `kffi/src/androidMain/kotlin/java/lang/foreign/*` (5 files)
- Modify: `kffi/build.gradle.kts` (remove JNA dependency from androidMain)
- Modify: `kffi-benchmark-android/build.gradle.kts` (drop the JNA exclude workaround)

- [ ] **Step 1: Write the failing test**

Create `kffi/src/androidUnitTest/kotlin/org/graphiks/kffi/CStringAndroidTest.kt`:

```kotlin
package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CStringAndroidTest : FreeSpec({
    "allocateFrom produces a NUL-terminated UTF-8 string" {
        memoryScope { allocator ->
            val cstr = allocator.allocateFrom("héllo")
            cstr.toKString() shouldBe "héllo"
        }
    }
    "toKString(size) reads a fixed number of bytes" {
        memoryScope { allocator ->
            val cstr = allocator.allocateFrom("abcd")
            cstr.toKString(4uL) shouldBe "abcd"
        }
    }
})
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:testDebugUnitTest --tests "*CStringAndroidTest*"`
Expected: FAIL — old `getString`-based CString still references the shim.

- [ ] **Step 3: Implement CString**

Rewrite `CString.android.kt`:

```kotlin
package org.graphiks.kffi

import java.nio.charset.StandardCharsets

@JvmInline
actual value class CString actual constructor(actual val handler: NativeAddress) {
    actual fun toKString(): String? {
        if (handler.rawValue == 0L) return null
        val unsafe = AndroidUnsafe.get()
        val start = handler.rawValue
        var len = 0
        while (unsafe.getByte(start + len) != 0.toByte()) len++
        val bytes = ByteArray(len)
        unsafe.copyMemory(null, start, bytes, 16L, len.toLong())
        return String(bytes, StandardCharsets.UTF_8)
    }

    actual fun toKString(size: ULong): String? {
        if (handler.rawValue == 0L) return null
        val unsafe = AndroidUnsafe.get()
        val bytes = ByteArray(size.toInt())
        unsafe.copyMemory(null, handler.rawValue, bytes, 16L, size.toLong())
        return String(bytes, StandardCharsets.UTF_8)
    }
}
```

- [ ] **Step 4: Delete the shim + JNA**

```bash
git rm -r kffi/src/androidMain/kotlin/java/lang/foreign
```

In `kffi/build.gradle.kts`, delete the `androidMain { dependencies { ... jna ... } }` block (lines ~213-218). In `kffi-benchmark-android/build.gradle.kts`, remove the kotest `exclude(group = "net.java.dev.jna")` workaround.

- [ ] **Step 5: Verify kffi module + benchmark-android**

Run: `./gradlew :kffi:testDebugUnitTest :kffi:assembleDebug`
Expected: SUCCESS (shim gone, no JNA in kffi).

Run: `./gradlew :kffi-benchmark-android:assembleDebugAndroidTest`
Expected: SUCCESS.

- [ ] **Step 6: Commit**

```bash
git add -A kffi kffi-benchmark-android
git commit -m "feat(kffi): UTF-8 Android CString; delete java/lang/foreign shim and JNA from kffi"
```

---

## Milestone M3 — Downcall engine (typed JNI wrapper table)

> Environment prerequisite: install the NDK. Set `ndkVersion` in `kffi/build.gradle.kts` (AGP auto-downloads when the license is accepted; `android-sdk-license` exists). If no NDK can be obtained, stop here and report.

### Task M3.1: Install NDK + wire CMake skeleton

**Files:**
- Modify: `kffi/build.gradle.kts` (ndkVersion, externalNativeBuild, abiFilters)
- Create: `kffi/src/main/cpp/CMakeLists.txt`
- Create: `kffi/src/main/cpp/kffi_engine.c` (skeleton: `JNI_OnLoad` + one wrapper)

- [ ] **Step 1: Configure the build**

In `kffi/build.gradle.kts` `androidTarget.android` block add:

```kotlin
ndkVersion = "27.2.12479018"
```

Add `android {}` top-level block:

```kotlin
android {
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
    defaultConfig {
        externalNativeBuild {
            cmake {
                cFlags += listOf("-std=c11")
            }
        }
    }
}
```

Create `kffi/src/main/cpp/CMakeLists.txt`:

```cmake
cmake_minimum_required(VERSION 3.22.1)
project(kffi_engine LANGUAGES C)

add_library(kffi SHARED kffi_engine.c)
target_link_libraries(kffi PRIVATE log)
```

Create `kffi/src/main/cpp/kffi_engine.c` skeleton:

```c
#include <jni.h>

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void)vm; (void)reserved;
    return JNI_VERSION_1_6;
}
```

- [ ] **Step 2: Build the engine**

Run: `./gradlew :kffi:externalNativeBuildDebug`
Expected: SUCCESS, produces `kffi/build/intermediates/.../kffi.so` per ABI.

- [ ] **Step 3: Commit**

```bash
git add kffi/build.gradle.kts kffi/src/main/cpp
git commit -m "feat(kffi): add NDK/CMake skeleton for the Android downcall engine"
```

### Task M3.2: Typed JNI wrapper table (scalar/pointer forms)

**Files:**
- Modify: `kffi/src/main/cpp/kffi_engine.c`

- [ ] **Step 1: Write the failing test (instrumented)**

Create `kffi/src/androidInstrumentedTest/kotlin/org/graphiks/kffi/engine/DowncallEngineTest.kt`:

```kotlin
package org.graphiks.kffi.engine

import org.junit.Assert.assertEquals
import org.junit.Test

class DowncallEngineTest {

    @Test
    fun resolvesSymbolsAndCallsVoid0AndInt0() {
        val fn = NativeEngine.resolveSymbol("bench_empty")
        assertEquals(42L, NativeEngine.callI0(fn))
        val voidFn = NativeEngine.resolveSymbol("bench_void_takes_void")
        NativeEngine.callV0(voidFn)
    }

    @Test
    fun callI2IIAddsIntegers() {
        val fn = NativeEngine.resolveSymbol("bench_add4")
        assertEquals(10L, NativeEngine.callI4IIII(fn, 1, 2, 3, 4))
    }
}
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: FAIL — `NativeEngine` unresolved (compile error).

- [ ] **Step 3: Implement the wrapper table**

Add to `kffi_engine.c` (uniform `jlong` signature; `sizeof`-aware casts):

```c
#include <stdint.h>
#include <dlfcn.h>
#include <string.h>

typedef uint64_t (*fn_v0)(void);
typedef uint64_t (*fn_i4_i4i4i4i4)(int, int, int, int);

static inline void *checked_fn(jlong fn) {
    void *p = (void *)(uintptr_t)fn;
    if (p == NULL) {
        // thrown by the caller check below in Kotlin; here we just return
    }
    return p;
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_resolveSymbol(
    JNIEnv *env, jclass cls, jstring name) {
    (void)cls;
    const char *cname = (*env)->GetStringUTFChars(env, name, NULL);
    void *sym = dlsym(RTLD_DEFAULT, cname);
    (*env)->ReleaseStringUTFChars(env, name, cname);
    return (jlong)(uintptr_t)sym;
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callV0(JNIEnv *env, jclass cls, jlong fn) {
    (void)env; (void)cls;
    ((fn_v0)(uintptr_t)fn)();
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI0(JNIEnv *env, jclass cls, jlong fn) {
    (void)env; (void)cls;
    return (jlong)((fn_v0)(uintptr_t)fn)();
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callI4IIII(JNIEnv *env, jclass cls, jlong fn, jint a, jint b, jint c, jint d) {
    (void)env; (void)cls;
    return (jlong)((fn_i4_i4i4i4i4)(uintptr_t)fn)(a, b, c, d);
}
```

> Wrapper naming scheme: `call<R><N><ARGS>` where `R ∈ {V,I,L,P,D,F,S,B}` (return), `N` = arg count, args encoded per-type (`I`=jint, `L`=jlong, `P`=jlong address, `F`=jfloat, `D`=jdouble, `S`=jshort, `B`=jbyte). Register all wrappers via `JNI_OnLoad` `RegisterNatives` in M6; for now rely on the `Java_..._` naming convention so the Kotlin `external fun` names map directly.

Create `kffi/src/androidMain/kotlin/org/graphiks/kffi/engine/NativeEngine.kt`:

```kotlin
package org.graphiks.kffi.engine

object NativeEngine {
    init {
        System.loadLibrary("kffi")
    }

    external fun resolveSymbol(name: String): Long
    external fun callV0(fn: Long)
    external fun callI0(fn: Long): Long
    external fun callI4IIII(fn: Long, a: Int, b: Int, c: Int, d: Int): Long
}
```

- [ ] **Step 4: Wire an Android test fixture .so**

The instrumented test needs `bench_empty`/`bench_add4`/`bench_void_takes_void` at `RTLD_DEFAULT`. Add to `kffi/src/main/cpp/` a `bench_engine_fixture.c` with those three symbols and list it in `CMakeLists.txt` as a second shared lib `kffi_bench_fixture`. (Reuse the `bench_fixture.c` sources by copying the three functions verbatim.)

- [ ] **Step 5: Verify**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: SUCCESS. Then, if a device/emulator is available:
Run: `./gradlew :kffi:connectedDebugAndroidTest`
Expected: `DowncallEngineTest` passes on device.

- [ ] **Step 6: Commit**

```bash
git add kffi/src/main/cpp kffi/src/androidMain kffi/src/androidInstrumentedTest
git commit -m "feat(kffi): typed JNI downcall wrapper table with dlopen symbol resolution"
```

### Task M3.3: Struct-by-value wrappers (C1)

**Files:**
- Modify: `kffi/src/main/cpp/kffi_engine.c` (add struct wrappers)
- Modify: `kffi/src/main/cpp/bench_engine_fixture.c` (add `bench_make_pair`/`bench_pair_sum`)
- Modify: `kffi/src/androidMain/kotlin/org/graphiks/kffi/engine/NativeEngine.kt`
- Modify: `DowncallEngineTest.kt`

- [ ] **Step 1: Write the failing test**

Add to `DowncallEngineTest.kt`:

```kotlin
@Test
fun structByValueArgAndReturn() {
    val sum = NativeEngine.resolveSymbol("bench_pair_sum")
    // pair(a=7,b=9) laid out into a 16-byte buffer, passed by value
    val buf = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
    buf.putLong(0, 7L); buf.putLong(8, 9L)
    val out = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
    val sumResult = NativeEngine.callStructArgLong(sum, 16, memoryAddress(buf), 0L)
    assertEquals(16L, sumResult)

    val makePair = NativeEngine.resolveSymbol("bench_make_pair")
    NativeEngine.callStructReturn(makePair, 7L, 9L, 16, memoryAddress(out))
    assertEquals(7L, out.getLong(0))
    assertEquals(9L, out.getLong(8))
}
```

Helpers `memoryAddress(buf)` use `((DirectBuffer) buf).address()`. Add `import java.nio.ByteBuffer`, `java.nio.ByteOrder`, `java.nio.DirectBuffer`.

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: FAIL — `callStructArgLong`/`callStructReturn` unresolved.

- [ ] **Step 3: Implement the C wrappers**

Add to `kffi_engine.c`:

```c
typedef struct bench_pair { uint64_t a; uint64_t b; } bench_pair;

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callStructArgLong(
    JNIEnv *env, jclass cls, jlong fn, jint structSize, jlong structPtr, jlong arg2) {
    (void)env; (void)cls;
    // generic: copy the caller buffer into a by-value local, forward it
    uint8_t local[64];
    memcpy(local, (void *)(uintptr_t)structPtr, (size_t)structSize);
    // specialized per generated struct; here we inline bench_pair_sum
    bench_pair *p = (bench_pair *)local;
    return (jlong)((uint64_t (*)(bench_pair, uint64_t))(uintptr_t)fn)(*p, (uint64_t)arg2);
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callStructReturn(
    JNIEnv *env, jclass cls, jlong fn, jlong a, jlong b, jint structSize, jlong outPtr) {
    (void)env; (void)cls;
    bench_pair r = ((bench_pair (*)(uint64_t, uint64_t))(uintptr_t)fn)((uint64_t)a, (uint64_t)b);
    memcpy((void *)(uintptr_t)outPtr, &r, (size_t)structSize);
}
```

> **P1 scope note:** these two wrappers are hand-written for the fixture struct. In M5 kextract generates one wrapper pair per struct shape (arg + return) from header layouts, with the same buffer-in/buffer-out contract. The generated wrappers replace the hardcoded `bench_pair` bodies.

Add the two `external fun`s to `NativeEngine.kt`:

```kotlin
external fun callStructArgLong(fn: Long, structSize: Int, structPtr: Long, arg2: Long): Long
external fun callStructReturn(fn: Long, a: Long, b: Long, structSize: Int, outPtr: Long)
```

- [ ] **Step 4: Verify (device-gated)**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: SUCCESS. If device available: `connectedDebugAndroidTest` passes.

- [ ] **Step 5: Commit**

```bash
git add kffi/src/main/cpp kffi/src/androidMain kffi/src/androidInstrumentedTest
git commit -m "feat(kffi): struct-by-value downcall wrappers (arg and return)"
```

### Task M3.4: libffi fallback (C2)

**Files:**
- Modify: `kffi/src/main/cpp/CMakeLists.txt` (link libffi)
- Modify: `kffi/src/main/cpp/kffi_engine.c` (add `callGeneric`)
- Modify: `NativeEngine.kt`, `DowncallEngineTest.kt`

- [ ] **Step 1: Locate/vendor libffi for Android**

Check whether the NDK sysroot ships `libffi`:

```bash
find "$ANDROID_HOME/ndk" -iname "libffi*" | head
```

If present, link it in `CMakeLists.txt` (`target_link_libraries(kffi PRIVATE log ffi)`). If absent, vendor a minimal libffi build under `kffi/src/main/cpp/third_party/libffi/` (sources for `ffi_call`/`ffi_prep_cif` for arm64-v8a, x86_64, armeabi-v7a) and add it via `add_subdirectory`.

- [ ] **Step 2: Write the failing test**

Add to `DowncallEngineTest.kt`:

```kotlin
@Test
fun genericFallbackCallsVariadicLikePath() {
    // bench_add4 reached through the generic path must still sum
    val fn = NativeEngine.resolveSymbol("bench_add4")
    val args = ByteBuffer.allocateDirect(4 * 8).order(ByteOrder.nativeOrder())
    args.putLong(0, 1); args.putLong(8, 2); args.putLong(16, 3); args.putLong(24, 4)
    val out = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder())
    NativeEngine.callGeneric(fn, 4, "IIII", memoryAddress(args), memoryAddress(out))
    assertEquals(10L, out.getLong(0))
}
```

- [ ] **Step 3: Run to verify it fails**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: FAIL — `callGeneric` unresolved.

- [ ] **Step 4: Implement**

Add to `kffi_engine.c`:

```c
#include <ffi.h>

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_NativeEngine_callGeneric(
    JNIEnv *env, jclass cls, jlong fn, jint argc, jstring typeSpec, jlong argsPtr, jlong outPtr) {
    (void)cls;
    const char *spec = (*env)->GetStringUTFChars(env, typeSpec, NULL);
    ffi_type **types = calloc((size_t)argc, sizeof(ffi_type *));
    ffi_cif cif;
    for (int i = 0; i < argc; i++) {
        types[i] = ffi_type_uint64; // I/L/P map to uint64 carrier for the generic path
    }
    if (ffi_prep_cif(&cif, FFI_DEFAULT_ABI, (unsigned)argc, ffi_type_uint64, types) == FFI_OK) {
        ffi_call(&cif, FFI_FN(fn), (void *)(uintptr_t)outPtr, (void **)(uintptr_t)argsPtr);
    }
    (*env)->ReleaseStringUTFChars(env, typeSpec, spec);
    free(types);
}
```

Add to `NativeEngine.kt`:

```kotlin
external fun callGeneric(fn: Long, argc: Int, typeSpec: String, argsPtr: Long, outPtr: Long)
```

- [ ] **Step 5: Verify (device-gated)**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: SUCCESS. Device run: `connectedDebugAndroidTest` passes.

- [ ] **Step 6: Commit**

```bash
git add kffi/src/main/cpp kffi/src/androidMain kffi/src/androidInstrumentedTest
git commit -m "feat(kffi): generic libffi fallback path for out-of-table signatures"
```

---

## Milestone M4 — Upcalls Android (I1, dedicated milestone)

### Task M4.1: JNIEnv acquisition + trampoline free-list + global refs

**Files:**
- Create: `kffi/src/main/cpp/kffi_upcall.c`
- Modify: `kffi/src/main/cpp/CMakeLists.txt`
- Create: `kffi/src/androidMain/kotlin/org/graphiks/kffi/engine/UpcallEngine.kt`

- [ ] **Step 1: Write the failing test**

Create `kffi/src/androidInstrumentedTest/kotlin/org/graphiks/kffi/engine/UpcallEngineTest.kt`:

```kotlin
package org.graphiks.kffi.engine

import org.junit.Assert.assertEquals
import org.junit.Test

class UpcallEngineTest {

    @Test
    fun trampolineInvokesKotlinDispatcherOnCurrentThread() {
        var received = -1
        val callback = object : Callback {
            fun onValue(value: Int) { received = value }
        }
        // a trampoline that calls a Kotlin static dispatch with a token, then the lambda
        val token = UpcallEngine.allocateTrampoline("V1I", dispatchId = 0)
        val setFn = NativeEngine.resolveSymbol("bench_set_callback")
        val fireFn = NativeEngine.resolveSymbol("bench_fire_one")
        NativeEngine.callV2PP(setFn, token, 0L)
        NativeEngine.callV1I(fireFn, 42)
        UpcallEngine.freeTrampoline(token)
    }
}
```

(Adjust to the real dispatch contract defined in Step 3; the essential assertion is that the C-created callback thread reaches the Kotlin lambda with the right value.)

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: FAIL — `UpcallEngine` unresolved.

- [ ] **Step 3: Implement the upcall core**

Create `kffi_upcall.c` (the trampoline registry + dispatcher):

```c
#include <jni.h>
#include <pthread.h>
#include <stdlib.h>
#include "kffi_upcall.h"

#define MAX_TRAMPOLINES 4096

typedef struct trampoline_slot {
    int in_use;
    jclass dispatcher_class;   /* global ref to org.graphiks.kffi.engine.UpcallDispatcher */
    jmethodID dispatch_id;     /* static dispatch(int token, ...) */
    jlong token;
    int kind;                  /* encoded signature kind */
} trampoline_slot;

static trampoline_slot slots[MAX_TRAMPOLINES];
static JavaVM *g_vm;

static jmethodID find_dispatcher(JNIEnv *env, const char *sig) {
    jclass cls = (*env)->FindClass(env, "org/graphiks/kffi/engine/UpcallDispatcher");
    return (*env)->GetStaticMethodID(env, cls, "dispatch", sig);
}

static JNIEnv *acquire_env(int *attached) {
    JNIEnv *env = NULL;
    if ((*g_vm)->GetEnv(g_vm, (void **)&env, JNI_VERSION_1_6) != JNI_OK) {
        if ((*g_vm)->AttachCurrentThread(g_vm, &env, NULL) != JNI_OK) return NULL;
        *attached = 1;
    }
    return env;
}

static void release_env(int attached) {
    if (attached) (*g_vm)->DetachCurrentThread(g_vm);
}

/* V1I: void cb(int value); dispatch(int token, int value) */
static void trampoline_V1I(jlong token, int value) {
    int attached = 0;
    JNIEnv *env = acquire_env(&attached);
    if (!env) return;
    trampoline_slot *slot = &slots[token & (MAX_TRAMPOLINES - 1)];
    (*env)->CallStaticVoidMethod(env, slot->dispatcher_class, slot->dispatch_id, (jint)token, (jint)value);
    if ((*env)->ExceptionCheck(env)) (*env)->ExceptionClear(env); /* exception marshaled in Kotlin */
    release_env(attached);
}

JNIEXPORT jlong JNICALL Java_org_graphiks_kffi_engine_UpcallEngine_allocateTrampoline(
    JNIEnv *env, jclass cls, jstring kind, jint dispatchId) {
    (void)cls;
    const char *ckind = (*env)->GetStringUTFChars(env, kind, NULL);
    jlong token = dispatchId; /* token == slot index in P1; free-list real version in M6 */
    trampoline_slot *slot = &slots[token & (MAX_TRAMPOLINES - 1)];
    slot->in_use = 1;
    slot->token = token;
    slot->dispatch_class = NULL; /* set from Kotlin in P1: UpcallDispatcher installs its class */
    slot->dispatch_id = NULL;
    (*env)->ReleaseStringUTFChars(env, kind, ckind);
    return token;
}

JNIEXPORT void JNICALL Java_org_graphiks_kffi_engine_UpcallEngine_freeTrampoline(
    JNIEnv *env, jclass cls, jlong token) {
    (void)env; (void)cls;
    trampoline_slot *slot = &slots[token & (MAX_TRAMPOLINES - 1)];
    slot->in_use = 0;
}
```

> **P1 milestone contract:** this implements the *mechanism* (GetEnv/Attach/Detach, global-ref-backed dispatch). The generated per-signature trampolines (`trampoline_V1I`, `trampoline_V2PP`, …) plus per-function Kotlin dispatch wiring are emitted by kextract in M5; the free-list/leak policy and `JNI_OnLoad` `RegisterNatives` registration land in M6. The `UpcallDispatcher` static class holds the `dispatch(token, …)` method that routes through `CallbackRuntime.dispatchSafely`.

Create `UpcallEngine.kt`:

```kotlin
package org.graphiks.kffi.engine

object UpcallEngine {
    init { System.loadLibrary("kffi") }
    external fun allocateTrampoline(kind: String, dispatchId: Int): Long
    external fun freeTrampoline(token: Long)
}
```

- [ ] **Step 4: Verify (device-gated)**

Run: `./gradlew :kffi:assembleDebugAndroidTest`
Expected: SUCCESS. Device run: `connectedDebugAndroidTest` passes (callback fires from a C worker thread).

- [ ] **Step 5: Commit**

```bash
git add kffi/src/main/cpp kffi/src/androidMain kffi/src/androidInstrumentedTest
git commit -m "feat(kffi): Android upcall engine with JNIEnv acquisition and global refs"
```

---

## Milestone M5 — kextract Android generator rewrite + wgpu regeneration

> This is the largest milestone. Each task is independently testable against kextract's golden tests.

### Task M5.1: Android record layout (offsets/padding)

**Files:**
- Create: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/abi/AndroidRecordLayout.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/abi/KotlinKmpAbiIndex.kt` (register layouts)

- [ ] **Step 1: Write the failing test**

Create `kextract/src/test/kotlin/org/graphiks/kextract/integration/AndroidRecordLayoutTest.kt` — layout of `bench_pair` must be `a@0(8), b@8(8)`, size 16.

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kextract:test --tests "*AndroidRecordLayoutTest*"`
Expected: FAIL — `AndroidRecordLayout` unresolved.

- [ ] **Step 3: Implement the layout algorithm**

`AndroidRecordLayout.kt`: given the field types (`Type.Primitive` kinds + nested records + arrays), compute per-field `offset`/`size` with natural alignment (align to field alignment; struct aligned to max member alignment; size rounded up). Return `(offset, size)` map + total size. Reuse `Type` traversal from `KotlinJvmRecordLayout` where possible.

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kextract:test --tests "*AndroidRecordLayoutTest*"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kextract
git commit -m "feat(kextract): Android record layout (offsets, padding, sizes)"
```

### Task M5.2: Rewrite `KotlinKmpAndroidBuilder` struct emission

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt` (replace `JNA_*` symbols; add engine symbols)

- [ ] **Step 1: Update the name plan**

Remove `JNA_POINTER/JNA_CALLBACK_REFERENCE/JNA_LIBRARY/JNA_NATIVE/JNA_STRUCTURE/JNA_UNION` from `KotlinKmpRuntimeSymbol` (android). Add:

```kotlin
NATIVE_ENGINE("org.graphiks.kffi.engine.NativeEngine", android()),
```

Update `KotlinKmpAndroidBuilder` imports to drop the removed symbols.

- [ ] **Step 2: Write the failing golden test**

Add a new integration test `KmpAndroidMemoryBackedAbiTest.kt` asserting the emitted struct shape: a `WGPUAdapterInfo`-like struct must emit
- `actual interface X { actual var fieldA: Int; ... actual val handler: NativeAddress }`
- `class ByReference(val handle: NativeAddress = NativeAddress(0)) : X` with accessors reading/writing via `MemoryBuffer(handle, size)` at computed offsets,
- `class ByValue(...)` same over a by-value buffer,
- `actual companion object { invoke/allocate/allocateArray }` using `allocator.allocateBuffer(size)`.

- [ ] **Step 3: Run to verify it fails**

Run: `./gradlew :kextract:test --tests "*KmpAndroidMemoryBackedAbiTest*"`
Expected: FAIL (golden mismatch / old JNA shape).

- [ ] **Step 4: Implement**

Rewrite `visitScoped` struct branch: drop `jnaBuilder`/raw JNA class emission; emit MemoryBuffer-backed `ByReference`/`ByValue` using `AndroidRecordLayout` offsets. Field accessors map to `buffer.readInt(offset)`/`writeInt(...)` etc., `CString` via `allocator.allocateFrom`/`toKString`, pointers via `readPointer`/`writePointer`. `handler` returns the buffer address (for ByReference) or a copy of the by-value region. Keep `WGPUNativeDisplayHandle` special-casing but memory-backed.

- [ ] **Step 5: Update the remaining Android goldens**

`KmpAndroidJnaAbiTest.kt` golden expectations change to the memory-backed shape; update. `CallbackGeneratorIntegrationTest` updated in M5.4.

- [ ] **Step 6: Verify kextract tests**

Run: `./gradlew :kextract:test`
Expected: PASS.

- [ ] **Step 7: Commit**

```bash
git add kextract
git commit -m "feat(kextract): emit memory-backed Android structs without JNA"
```

### Task M5.3: Emit per-function downcall wrappers via `NativeEngine`

**Files:**
- Modify: `KotlinKmpAndroidBuilder.kt` (`visitFunction`, `toRawArgument`, `emitFunctionReturn`)

- [ ] **Step 1: Write the failing golden test**

Extend `KmpAndroidMemoryBackedAbiTest.kt` with a function golden: `wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?` must emit

```kotlin
private val wgpuCreateInstance_ADDR by lazy { NativeEngine.resolveSymbol("wgpuCreateInstance") }
actual fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance? {
    val result = NativeEngine.callP1P(wgpuCreateInstance_ADDR, descriptor?.handler?.rawValue ?: 0L)
    return if (result == 0L) null else WGPUInstance(NativeAddress(result))
}
```

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kextract:test --tests "*KmpAndroidMemoryBackedAbiTest*"`
Expected: FAIL.

- [ ] **Step 3: Implement**

- Compute the wrapper form per function signature from the argument/return types (`callR< N><types>`).
- Emit a `Symbols`-style per-function `by lazy { NativeEngine.resolveSymbol("name") }` address.
- `toRawArgument`: struct-by-value args → allocate buffer, write fields, pass `(size, addr)`; pointers → `rawValue ?: 0L`; enums/scalars → carrier values.
- `emitFunctionReturn`: struct returns → `callStructReturn(..., outPtr)` then read fields into the wrapper struct; pointer returns → wrap non-zero address; scalar returns → convert.

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kextract:test`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kextract
git commit -m "feat(kextract): emit NativeEngine downcall wrappers per function"
```

### Task M5.4: Emit Android upcall trampolines via the engine

**Files:**
- Modify: `kextract/src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackAndroidEmitter.kt`

- [ ] **Step 1: Write the failing golden test**

Extend `CallbackGeneratorIntegrationTest.kt`: the Android callback output must reference `UpcallEngine.allocateTrampoline` and `CallbackRuntime.dispatchSafely` and **not** `CallbackReference`/`com.sun.jna`.

- [ ] **Step 2: Run to verify it fails**

Run: `./gradlew :kextract:test --tests "*CallbackGeneratorIntegrationTest*"`
Expected: FAIL — still emits JNA `CallbackReference`.

- [ ] **Step 3: Implement**

Rewrite `KotlinCallbackAndroidEmitter` to emit, per callback typedef:
1. `private val type = CallbackType(...)` + `private object Trampoline { val address: NativeAddress by lazy { UpcallEngine.allocateTrampoline(kind, 0) } }`
2. A static `dispatch(token: Int, ...)` method calling `CallbackRuntime.dispatchSafely(type, NativeAddress(token.toLong())) { callback -> callback.invoke(...) }` (routing userdata is the token address).
3. `register`/`prepare`/`rearmAfterNativeQuiescence` actuals calling `CallbackRuntime.*` as today, but with `trampoline = Trampoline.address`.

- [ ] **Step 4: Run to verify it passes**

Run: `./gradlew :kextract:test`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add kextract
git commit -m "feat(kextract): emit engine-based Android upcall trampolines"
```

### Task M5.5: Regenerate wgpu bindings + restore `wgpu4k-native`/demos

**Files:**
- Modify: `wgpu4k-native/build.gradle.kts` (remove JNA readback `doLast`, JNA dep, jniLibs wiring for the consumer engine)
- Regenerate: `wgpu_hAndroid.kt` (and the other 3 platform bindings)
- Modify: `demo/*` (JNA `Pointer` usages → `NativeAddress` value class; remove `jnaPlatform` dep in desktop-and-ios if unused)

- [ ] **Step 1: Remove the JNA readback patch**

Delete the `doLast { ... replaceGeneratedFunction ... }` block in `wgpu4k-native/build.gradle.kts:399-458`. Remove the JNA `@aar` dep from `wgpu4k-native/build.gradle.kts` androidMain. Remove the `verifyJvmBootstrapBinding` reliance on `io.ygdrasil` strings (already migrated in M1).

- [ ] **Step 2: Regenerate**

Run: `./gradlew :wgpu4k-native:generateBindingsFromHeader`
Expected: SUCCESS. `wgpu_hAndroid.kt` now emits the memory-backed + engine shape. The other 3 platform files are unchanged by this regeneration (namespace already fixed in M1).

- [ ] **Step 3: Compile wgpu4k-native Android**

Run: `./gradlew :wgpu4k-native:assembleDebug`
Expected: SUCCESS (Android target restored against the new kffi backend).

- [ ] **Step 4: Fix demo android targets**

Update `demo/android`, `demo/common` androidUnitTest/androidInstrumentedTest files that reference `com.sun.jna.Pointer` (e.g. `TestNativeAddress.android.kt`, `AndroidJnaSmokeTest.kt`) to the value-class `NativeAddress`. Remove `jnaPlatform` from `demo/desktop-and-ios` if it becomes unused.

- [ ] **Step 5: Verify demos**

Run: `./gradlew :demo:android:assembleDebug :demo:desktop-and-ios:compileKotlinJvm`
Expected: SUCCESS.

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "feat(bindings): regenerate Android bindings on the memory-backed engine backend"
```

---

## Milestone M6 — Packaging (I6) + ABI 32-bit + final matrix

### Task M6.1: JNI_OnLoad RegisterNatives + jniLibs packaging + R8

**Files:**
- Modify: `kffi/src/main/cpp/kffi_engine.c` (RegisterNatives in JNI_OnLoad)
- Modify: `kffi/src/main/cpp/kffi_upcall.c` (trampoline free-list + leak policy)
- Modify: `kffi/build.gradle.kts` (jniLibs, abiFilters, packaging)
- Create: `kffi/src/main/resources/consumer-rules.pro`

- [ ] **Step 1: Register natives + ABI filtering**

In `kffi_engine.c` `JNI_OnLoad`, add a `JNINativeMethod` table for all `NativeEngine` externals and call `RegisterNatives`. In `kffi/build.gradle.kts`:

```kotlin
android {
    defaultConfig {
        ndk { abiFilters += setOf("arm64-v8a", "x86_64", "armeabi-v7a") }
    }
}
```

Create `consumer-rules.pro`:

```pro
-keep class org.graphiks.kffi.engine.** { *; }
-keep class org.graphiks.kffi.** { *; }
```

Wire `consumerProguardFiles("src/main/resources/consumer-rules.pro")`.

- [ ] **Step 2: Trampoline free-list**

Replace the token==slot-index scheme in `kffi_upcall.c` with a real free-list (an array of free indices + atomic counter, `allocate` pops, `free` pushes), and document the leak policy (freed slots are reusable; a registration that leaks a trampoline never closes retains the global ref — mirroring JNA `CallbackReference` behavior, now explicit).

- [ ] **Step 3: Verify**

Run: `./gradlew :kffi:assembleRelease`
Expected: SUCCESS; `kffi/build/outputs/aar/kffi-release.aar` contains `jni/arm64-v8a/libkffi.so`, `jni/x86_64/libkffi.so`, `jni/armeabi-v7a/libkffi.so`.

- [ ] **Step 4: Commit**

```bash
git add kffi
git commit -m "feat(kffi): JNI_OnLoad RegisterNatives, jniLibs packaging, R8 rules, ABI filters"
```

### Task M6.2: 32-bit (armeabi-v7a) correctness

**Files:**
- Modify: `kffi/src/main/cpp/kffi_engine.c` (guard the `C long` width)
- Modify: `kffi/src/androidMain/kotlin/org/graphiks/kffi/engine/NativeEngine.kt`

- [ ] **Step 1: Add the ABI guard**

Add to `kffi_engine.c`:

```c
#if defined(__arm__) && !defined(__aarch64__)
#define KFFI_C_LONG_IS_32BIT 1
#else
#define KFFI_C_LONG_IS_32BIT 0
#endif
```

Where a wrapper passes a C `long`/`size_t` argument, truncate: `(long)(jlong)value` (already implicit); for returns `(jlong)(long)result` (sign-extend). Add a compile-time check that `sizeof(long)` matches the guard:

```c
_Static_assert((KFFI_C_LONG_IS_32BIT ? sizeof(long) == 4 : sizeof(long) == 8),
               "C long width does not match the ABI guard");
```

- [ ] **Step 2: Verify the 32-bit token cap**

In `CallbackTokenAddressCodec.android.kt` the `maxToken` cap is already pointer-bits based (M2.2). Confirm the unit test asserts `maxToken == UInt.MAX_VALUE` when `addressSize()==4` — run on an armeabi-v7a emulator:

```bash
# (device-gated) sdkmanager/emulator path — create an armeabi-v7a AVD and run:
./gradlew :kffi:testDebugUnitTest   # on 32-bit: token codec test asserts the 32-bit cap
```

- [ ] **Step 3: Commit**

```bash
git add kffi
git commit -m "feat(kffi): 32-bit ABI correctness for C long and token width"
```

### Task M6.3: Android benchmark bake-off + final matrix

**Files:**
- Modify: `kffi-benchmark-android/src/androidInstrumentedTest/.../AndroidHarness.kt` (add downcall + upcall axes using `NativeEngine` + fixtures)
- Create: `kffi/benchmarks/results/<date>-<sha>-android-device-*.md`

- [ ] **Step 1: Extend the Android harness**

Add a downcall axis (empty/add4 via `NativeEngine`) and an upcall axis (fire_one via the engine trampoline), reusing canonical `BenchmarkScenario` ids. Emit the versioned markdown + JSON to `kffi/benchmarks/results/`.

- [ ] **Step 2: Assemble gate**

Run: `./gradlew :kffi-benchmark-android:assembleDebugAndroidTest :kffi:assembleRelease`
Expected: SUCCESS.

- [ ] **Step 3: Device bake-off (optional, device-gated)**

Run: `./gradlew :kffi-benchmark-android:connectedDebugAndroidTest`
Expected: report emitted on device; copy `<date>-<sha>-android-device.md` into `kffi/benchmarks/results/` and commit.

- [ ] **Step 4: Full verification matrix**

Run:

```bash
./gradlew :kffi:jvmTest :kextract:test \
  :kffi-benchmark-jvm:compileJmhKotlin \
  :kffi-benchmark-native:runBenchmarkNative \
  :kffi-benchmark-android:assembleDebugAndroidTest \
  :wgpu4k-native:assembleDebug :demo:android:assembleDebug
```

Expected: all SUCCESS.

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-android kffi/benchmarks
git commit -m "feat(benchmark): Android downcall/upcall bake-off with versioned device baseline"
```

### Task M6.4: Final verification + branch state

- [ ] **Step 1: Confirm all P0 invariants still hold**

Run: `./gradlew :kffi:jvmTest` → 87 tests, 0 failures. `:kffi-benchmark-native:runBenchmarkNative` produces a markdown report.

- [ ] **Step 2: Confirm P1 deliverables**

- Namespace: `rg "io\\.ygdrasil\\.kffi" --type kotlin` → zero matches.
- JNA: `rg "com\\.sun\\.jna" kffi wgpu4k-native demo` → zero matches (Kadre submodule excluded).
- Shim: `kffi/src/androidMain/kotlin/java/lang/foreign` deleted.
- Baseline files: `kffi/benchmarks/results/<date>-<sha>-jvm-baseline.{md,json}` present.

- [ ] **Step 3: Report**

Summarize: milestones done, device-gated items pending (upcall/engine instrumented tests, 32-bit emulator run), remaining risks, and hand over to P2 planning.

---

## Out of scope (follow-up)

- **P2** — JVM cleanup: `.handler.handler` removal, inline `NativeAddress` JVM, end of `Arena.ofAuto()`, explicit lifetime decision (I2).
- **P3** — unified memory safety: per-allocator `unsafe` opt-in (I3), uniform bounds checks, re-baseline.
- **P4** — callback runtime optimization.
- **P5** — generic kextract, `org.graphiks:kffi-*` releases, final migration to `Graphiks-org/kffi`.
- Kadre submodule still uses JNA (vendored samples) — out of P1 scope.
