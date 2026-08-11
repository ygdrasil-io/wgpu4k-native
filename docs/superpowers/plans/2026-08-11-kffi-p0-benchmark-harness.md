# kffi P0 — Benchmark Harness + Infrastructure Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a reproducible, versioned benchmark harness measuring the 4 kffi performance axes (downcall, upcall, marshaling, arena allocation) on JVM, Android, and Kotlin/Native, plus stand up the target repo `Graphiks-org/kffi` with CI and snapshot publishing.

**Architecture:** A new KMP module `kffi-benchmark-spi` (in package `org.graphiks.kffi.benchmark`) defines shared scenario/result types and the Markdown report emitter. Backend-specific harnesses consume it: `kffi-benchmark-jvm` (JVM + JMH via `me.champeau.jmh`), a Kotlin/Native benchmark binary, and an Android instrumented harness. Dedicated C fixtures (with struct-by-value cases) are compiled per host. Comparison rule: only intra-backend deltas are significant; reports separate backends.

**Tech Stack:** Kotlin Multiplatform 2.3.21, Gradle 9.5, JMH (`me.champeau.jmh` 0.7.2), JUnit5/Kotest, C fixtures via `cc`, kotlinx.cinterop, java.lang.foreign (JVM FFM), JNA 5.18.1 (Android current backend).

---

## Files map

| Path | Role |
|---|---|
| `kffi-benchmark-spi/build.gradle.kts` | KMP module, `commonMain` only, no deps |
| `kffi-benchmark-spi/src/commonMain/kotlin/org/graphiks/kffi/benchmark/Model.kt` | `BenchmarkAxis`, `BenchmarkResult`, `BenchmarkReport` |
| `kffi-benchmark-jvm/build.gradle.kts` | JVM + JMH plugin, compiles C fixture, depends on `:kffi` + `:kffi-benchmark-spi` |
| `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.h` | Dedicated C benchmark fixture header |
| `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c` | Dedicated C benchmark fixture implementation |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt` | JMH downcall axis |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt` | JMH upcall axis |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt` | JMH marshaling axis |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/ArenaBenchmarks.kt` | JMH arena axis |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt` | Downcall engine bake-off (FFM vs typed wrapper) |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/FixtureLoader.kt` | Loads the compiled fixture dylib + FFM symbol lookup |
| `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/JmhJsonToMarkdown.kt` | Main: converts JMH JSON → versioned Markdown report |
| `kffi-benchmark-native/build.gradle.kts` | KMP native (macosArm64 + linuxX64) benchmark binary |
| `kffi-benchmark-native/src/nativeMain/kotlin/.../NativeHarness.kt` | Runs scenarios, emits Markdown report |
| `kffi-benchmark-native/src/nativeInterop/cinterop/benchFixture.def` | cinterop for the C fixture |
| `kffi-benchmark-android/build.gradle.kts` | Android instrumented harness |
| `kffi-benchmark-android/src/androidInstrumentedTest/.../AndroidHarness.kt` | Device harness emitting Markdown report |
| `settings.gradle.kts` | Add the 3 new modules |
| `kffi/benchmarks/results/<date>-<commit>.md` | Versioned results |
| `.github/workflows/` | CI workflows (in target repo, P0 bis) |

> Note on namespaces: `kffi-benchmark-spi` uses the final namespace `org.graphiks.kffi.benchmark` directly (new code, avoids re-migration). The harnesses reference the *current* `io.ygdrasil.kffi` API (what P0 baselines); the P1 namespace migration will update those imports.

---

## Task 1: Create the `kffi-benchmark-spi` module

**Files:**
- Create: `kffi-benchmark-spi/build.gradle.kts`
- Create: `kffi-benchmark-spi/src/commonMain/kotlin/org/graphiks/kffi/benchmark/Model.kt`
- Modify: `settings.gradle.kts`

- [ ] **Step 1: Add the module to the build**

Modify `settings.gradle.kts` — add after `include("kffi")`:

```kotlin
include("kffi-benchmark-spi")
include("kffi-benchmark-jvm")
include("kffi-benchmark-native")
include("kffi-benchmark-android")
```

- [ ] **Step 2: Create the SPI build file**

Create `kffi-benchmark-spi/build.gradle.kts`:

```kotlin
plugins {
    `kotlin-multiplatform`
}

group = "org.graphiks"

kotlin {
    jvm()
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    linuxX64()
    androidNativeArm64()
    androidNativeX64()

    sourceSets {
        commonMain.dependencies {
        }
    }
}
```

- [ ] **Step 3: Create the shared model**

Create `kffi-benchmark-spi/src/commonMain/kotlin/org/graphiks/kffi/benchmark/Model.kt`:

```kotlin
package org.graphiks.kffi.benchmark

enum class BenchmarkAxis {
    DOWNCALL,
    UPCALL,
    MARSHALING,
    ARENA,
}

data class BenchmarkResult(
    val axis: BenchmarkAxis,
    val scenario: String,
    val backend: String,
    val nsPerOp: Double,
)

object BenchmarkReport {

    fun toMarkdown(backend: String, results: List<BenchmarkResult>): String = buildString {
        appendLine("# kffi benchmark report — backend: $backend")
        appendLine()
        appendLine("| Axis | Scenario | ns/op |")
        appendLine("|---|---|---|")
        results
            .sortedWith(compareBy<BenchmarkResult> { it.axis.ordinal }.thenBy { it.scenario })
            .forEach { result ->
                val ns = "%.2f".format(result.nsPerOp)
                appendLine("| ${result.axis} | ${result.scenario} | $ns |")
            }
    }
}
```

- [ ] **Step 4: Verify the module configures**

Run: `./gradlew :kffi-benchmark-spi:tasks --all`
Expected: succeeds, lists `compileKotlinJvm` etc. (first run downloads Kotlin toolchain).

- [ ] **Step 5: Commit**

```bash
git add settings.gradle.kts kffi-benchmark-spi
git commit -m "feat(benchmark): add kffi-benchmark-spi module with shared result model"
```

---

## Task 2: Dedicated C benchmark fixtures + Gradle compilation

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.h`
- Create: `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c`
- Create: `kffi-benchmark-jvm/build.gradle.kts` (fixture compile tasks + JMH setup, full file)

- [ ] **Step 1: Write the fixture header**

Create `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.h`:

```c
#ifndef KFFI_BENCH_FIXTURE_H
#define KFFI_BENCH_FIXTURE_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct bench_pair {
    uint64_t a;
    uint64_t b;
} bench_pair;

uint64_t bench_empty(void);
uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d);
uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                   uint64_t e, uint64_t f, uint64_t g, uint64_t h);
double bench_pi(void);
void *bench_roundtrip_ptr(void *p);
bench_pair bench_make_pair(uint64_t a, uint64_t b);
uint64_t bench_pair_sum(bench_pair p);

typedef void (*bench_callback)(uint32_t value, void *routing_userdata);
void bench_set_callback(bench_callback cb, void *routing_userdata);
void bench_fire(uint32_t count);
void bench_fire_one(uint32_t value);

#ifdef __cplusplus
}
#endif
#endif
```

- [ ] **Step 2: Write the fixture implementation**

Create `kffi-benchmark-jvm/src/jmh/resources/bench_fixture.c`:

```c
#include "bench_fixture.h"

uint64_t bench_empty(void) { return 42u; }

uint64_t bench_add4(uint64_t a, uint64_t b, uint64_t c, uint64_t d) {
    return a + b + c + d;
}

uint64_t bench_add8(uint64_t a, uint64_t b, uint64_t c, uint64_t d,
                    uint64_t e, uint64_t f, uint64_t g, uint64_t h) {
    return a + b + c + d + e + f + g + h;
}

double bench_pi(void) { return 3.14159; }

void *bench_roundtrip_ptr(void *p) { return p; }

bench_pair bench_make_pair(uint64_t a, uint64_t b) {
    bench_pair p = {a, b};
    return p;
}

uint64_t bench_pair_sum(bench_pair p) { return p.a + p.b; }

static bench_callback g_callback = NULL;
static void *g_callback_userdata = NULL;

void bench_set_callback(bench_callback cb, void *routing_userdata) {
    g_callback = cb;
    g_callback_userdata = routing_userdata;
}

void bench_fire(uint32_t count) {
    for (uint32_t i = 0; i < count; ++i) {
        if (g_callback != NULL) {
            g_callback(i, g_callback_userdata);
        }
    }
}

void bench_fire_one(uint32_t value) {
    if (g_callback != NULL) {
        g_callback(value, g_callback_userdata);
    }
}
```

- [ ] **Step 3: Create the JVM benchmark module build file**

Create `kffi-benchmark-jvm/build.gradle.kts`:

```kotlin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val benchFixtureSource = layout.projectDirectory.file("src/jmh/resources/bench_fixture.c")
val benchFixtureHeader = layout.projectDirectory.file("src/jmh/resources/bench_fixture.h")
val benchFixtureOutputDirectory = layout.buildDirectory.dir("bench-fixture")
val benchFixtureHost = providers.gradleProperty("kffi.benchFixture.hostForTest")
    .orNull
    ?.lowercase()
    ?: when {
        System.getProperty("os.name").contains("mac", ignoreCase = true) -> "macos"
        System.getProperty("os.name").contains("linux", ignoreCase = true) -> "linux"
        System.getProperty("os.name").contains("windows", ignoreCase = true) -> "windows"
        else -> error("Unsupported bench fixture host: ${System.getProperty("os.name")}")
    }
require(benchFixtureHost in setOf("macos", "linux", "windows")) {
    "Unsupported bench fixture host override: $benchFixtureHost"
}
val benchFixtureSharedLibrary = when (benchFixtureHost) {
    "macos" -> benchFixtureOutputDirectory.map { it.file("libbench_fixture.dylib") }
    "linux" -> benchFixtureOutputDirectory.map { it.file("libbench_fixture.so") }
    "windows" -> null
    else -> error("Unsupported bench fixture host: $benchFixtureHost")
}

val compileBenchFixtureShared = benchFixtureSharedLibrary?.let { sharedLibrary ->
    tasks.register<Exec>("compileBenchFixtureShared") {
        group = "verification"
        description = "Compiles the dedicated kffi benchmark C fixture for JVM JMH tests."
        inputs.files(benchFixtureSource, benchFixtureHeader)
        outputs.file(sharedLibrary)
        doFirst { benchFixtureOutputDirectory.get().asFile.mkdirs() }
        commandLine(
            buildList {
                addAll(listOf("cc", "-std=c11", "-fPIC", "-pthread", "-O2"))
                add(
                    when (benchFixtureHost) {
                        "macos" -> "-dynamiclib"
                        "linux" -> "-shared"
                        else -> error("No shared bench fixture on $benchFixtureHost")
                    },
                )
                addAll(
                    listOf(
                        benchFixtureSource.asFile.absolutePath,
                        "-o",
                        sharedLibrary.get().asFile.absolutePath,
                    ),
                )
            },
        )
    }
}

plugins {
    kotlin("jvm")
    id("me.champeau.jmh") version "0.7.2"
}

group = "org.graphiks"

kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_24)
    }
}

dependencies {
    jmh(project(":kffi"))
    jmh(project(":kffi-benchmark-spi"))
    jmh("org.jetbrains.kotlin:kotlin-stdlib")
}

jmh {
    warmupIterations.set(3)
    iterations.set(5)
    warmup.set("1s")
    timeOnIteration.set("1s")
    fork.set(2)
    resultFormat.set("JSON")
}

tasks.named("jmh") {
    val sharedLibrary = requireNotNull(benchFixtureSharedLibrary)
    dependsOn(requireNotNull(compileBenchFixtureShared))
    inputs.file(sharedLibrary)
    doFirst {
        systemProperty("kffi.bench.fixture.library", sharedLibrary.get().asFile.absolutePath)
    }
}
```

- [ ] **Step 4: Verify the fixture compiles**

Run: `./gradlew :kffi-benchmark-jvm:compileBenchFixtureShared`
Expected: SUCCESS, produces `kffi-benchmark-jvm/build/bench-fixture/libbench_fixture.dylib`.

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-jvm
git commit -m "feat(benchmark): add dedicated C bench fixtures and JVM JMH module skeleton"
```

---

## Task 3: JVM JMH — fixture loader

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/FixtureLoader.kt`

- [ ] **Step 1: Write the loader**

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/FixtureLoader.kt`:

```kotlin
package org.graphiks.kffi.benchmark.jvm

import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

object FixtureLoader {
    val libraryPath: String =
        System.getProperty("kffi.bench.fixture.library")
            ?: error("System property kffi.bench.fixture.library must point to the compiled bench fixture")

    val lookup: SymbolLookup by lazy {
        System.load(libraryPath)
        SymbolLookup.loaderLookup()
            .or(Linker.nativeLinker().defaultLookup())
    }

    fun findOrThrow(symbol: String): MemorySegment =
        lookup.find(symbol).orElseThrow { UnsatisfiedLinkError("unresolved symbol: $symbol") }

    val ADDRESS: ValueLayout.OfAddress = ValueLayout.ADDRESS
}
```

- [ ] **Step 2: Verify it compiles**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/FixtureLoader.kt
git commit -m "feat(benchmark): add JVM FFM fixture loader"
```

---

## Task 4: JVM JMH — downcall axis

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt`

- [ ] **Step 1: Write the downcall benchmarks**

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt`:

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.jvm

import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class DowncallBenchmarks {

    private val linker = Linker.nativeLinker()

    private lateinit var emptyHandle: MethodHandle
    private lateinit var add4Handle: MethodHandle
    private lateinit var add8Handle: MethodHandle
    private lateinit var pairSumHandle: MethodHandle
    private lateinit var makePairHandle: MethodHandle
    private lateinit var arena: Arena

    @Setup
    fun setup() {
        arena = Arena.ofConfined()
        val l = FixtureLoader.lookup
        emptyHandle = linker.downcallHandle(l.find("bench_empty").orElseThrow(), FunctionDescriptor.of(ValueLayout.JAVA_LONG))
        add4Handle = linker.downcallHandle(
            l.find("bench_add4").orElseThrow(),
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
            ),
        )
        add8Handle = linker.downcallHandle(
            l.find("bench_add8").orElseThrow(),
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
                ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG,
            ),
        )
        val pairLayout = MemoryLayout.structLayout(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
        pairSumHandle = linker.downcallHandle(
            l.find("bench_pair_sum").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, pairLayout),
        )
        makePairHandle = linker.downcallHandle(
            l.find("bench_make_pair").orElseThrow(),
            FunctionDescriptor.of(pairLayout, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG),
        )
    }

    @Benchmark
    fun empty(bh: Blackhole) {
        bh.consume(emptyHandle.invokeExact() as Long)
    }

    @Benchmark
    fun add4(bh: Blackhole) {
        bh.consume(add4Handle.invokeExact(1L, 2L, 3L, 4L) as Long)
    }

    @Benchmark
    fun add8(bh: Blackhole) {
        bh.consume(add8Handle.invokeExact(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L) as Long)
    }

    @Benchmark
    fun pairSum(bh: Blackhole) {
        val segment = arena.allocate(16L)
        segment.set(ValueLayout.JAVA_LONG, 0L, 7L)
        segment.set(ValueLayout.JAVA_LONG, 8L, 9L)
        bh.consume(pairSumHandle.invokeExact(segment) as Long)
    }

    @Benchmark
    fun makePair(bh: Blackhole) {
        val allocator: SegmentAllocator = arena
        bh.consume(makePairHandle.invokeExact(allocator, 7L, 9L) as MemorySegment)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val results = listOf(
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "empty", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "add4", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "add8", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "pairSum (struct by value)", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.DOWNCALL, "makePair (struct return)", "jvm-jmh", 1.0),
        )
        println(BenchmarkReport.toMarkdown("jvm-jmh", results))
    }
}
```

- [ ] **Step 2: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallBenchmarks.kt
git commit -m "feat(benchmark): add JMH downcall axis with struct-by-value cases"
```

---

## Task 5: JVM JMH — upcall axis

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt`

- [ ] **Step 1: Write the upcall benchmarks**

The upcall axis mirrors the generated trampoline pattern from `wgpu_hJvm.kt:8085-8119`: a static trampoline method dispatches via `CallbackRuntime.dispatchSafely`. The `routing_userdata` carries the token; a plain JVM lambda is registered through `io.ygdrasil.kffi.CallbackRuntime`.

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt`:

```kotlin
@file:OptIn(
    io.ygdrasil.kffi.CallbackRuntimeApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package org.graphiks.kffi.benchmark.jvm

import io.ygdrasil.kffi.Callback
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.CallbackRuntime
import io.ygdrasil.kffi.CallbackType
import io.ygdrasil.kffi.NativeAddress
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.util.concurrent.TimeUnit
import kotlin.concurrent.atomics.AtomicLong

private fun interface BenchCallback : Callback {
    fun invoke(value: UInt)
}

private val BenchCallbackType: CallbackType<BenchCallback> = CallbackType(
    canonicalId = "bench:BenchCallback",
    hasRoutingUserdata = true,
)

private object BenchCallbackTrampoline {
    private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
    private val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findStatic(
            BenchCallbackTrampoline::class.java,
            "invoke",
            descriptor.toMethodType(),
        )
    }
    val address: NativeAddress by lazy {
        NativeAddress(Linker.nativeLinker().upcallStub(methodHandle, descriptor, Arena.global()))
    }

    @JvmStatic
    private fun invoke(value: Int, userdata: MemorySegment) {
        CallbackRuntime.dispatchSafely(BenchCallbackType, NativeAddress(userdata)) { callback ->
            callback.invoke(value.toUInt())
        }
    }
}

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class UpcallBenchmarks {

    private val linker = Linker.nativeLinker()
    private val counter = AtomicLong(0)

    private lateinit var setCallbackHandle: MethodHandle
    private lateinit var fireHandle: MethodHandle
    private lateinit var fireOneHandle: MethodHandle
    private lateinit var registration: CallbackRegistration<BenchCallback>

    @Setup
    fun setup() {
        val l = FixtureLoader.lookup
        setCallbackHandle = linker.downcallHandle(
            l.find("bench_set_callback").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        )
        fireHandle = linker.downcallHandle(
            l.find("bench_fire").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
        )
        fireOneHandle = linker.downcallHandle(
            l.find("bench_fire_one").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
        )
        registration = io.ygdrasil.kffi.CallbackRuntime.register(
            type = BenchCallbackType,
            trampoline = BenchCallbackTrampoline.address,
            policy = CallbackPolicy.REPEATING,
        ) { _ -> counter.fetchAndAdd(1) }
        setCallbackHandle.invokeExact(registration.callback.handler as MemorySegment, registration.userdata?.handler as MemorySegment)
    }

    @Benchmark
    fun upcallFireOne(bh: Blackhole) {
        fireOneHandle.invokeExact(1)
        bh.consume(counter.load())
    }

    @Benchmark
    fun upcallFire1000(bh: Blackhole) {
        fireHandle.invokeExact(1000)
        bh.consume(counter.load())
    }

    @TearDown
    fun teardown() {
        registration.close()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val results = listOf(
            BenchmarkResult(BenchmarkAxis.UPCALL, "fire_one", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.UPCALL, "fire_1000", "jvm-jmh", 1.0),
        )
        println(BenchmarkReport.toMarkdown("jvm-jmh", results))
    }
}
```

> Note: the `invokeExact` argument casts for `registration.callback.handler` assume the current JVM `NativeAddress` wraps a `MemorySegment`. This is exactly the leak that P2 removes — until then, the benchmark uses the current representation.

- [ ] **Step 2: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS. If the `as MemorySegment` casts fail to compile, adjust to the exact accessor exposed by the current `NativeAddress` (check `kffi/src/jvmMain/kotlin/io/ygdrasil/kffi/NativeAddress.jvm.kt`).

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/UpcallBenchmarks.kt
git commit -m "feat(benchmark): add JMH upcall axis via CallbackRuntime dispatch"
```

---

## Task 6: JVM JMH — marshaling axis

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt`

- [ ] **Step 1: Write the marshaling benchmarks**

Uses the current `io.ygdrasil.kffi.MemoryAllocator` / `MemoryBuffer` API inside a `memoryScope`.

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt`:

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.jvm

import io.ygdrasil.kffi.MemoryBuffer
import io.ygdrasil.kffi.memoryScope
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class MarshalingBenchmarks {

    private val ints16 = IntArray(16) { it }
    private val ints1024 = IntArray(1024) { it }
    private val doubles1024 = DoubleArray(1024) { it.toDouble() }

    @Benchmark
    fun writeReadIntScalar(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(16uL)
            buffer.writeInt(42, 0uL)
            bh.consume(buffer.readInt(0uL))
        }
    }

    @Benchmark
    fun writeReadLongScalar(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(16uL)
            buffer.writeLong(42L, 0uL)
            bh.consume(buffer.readLong(0uL))
        }
    }

    @Benchmark
    fun copyInts16(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(64uL)
            buffer.writeInts(ints16)
            val out = IntArray(16)
            buffer.readInts(out)
            bh.consume(out[0])
        }
    }

    @Benchmark
    fun copyInts1024(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(4096uL)
            buffer.writeInts(ints1024)
            val out = IntArray(1024)
            buffer.readInts(out)
            bh.consume(out[0])
        }
    }

    @Benchmark
    fun copyDoubles1024(bh: Blackhole) {
        memoryScope { allocator ->
            val buffer: MemoryBuffer = allocator.allocateBuffer(8192uL)
            buffer.writeDoubles(doubles1024)
            val out = DoubleArray(1024)
            buffer.readDoubles(out)
            bh.consume(out[0])
        }
    }

    @Benchmark
    fun baselineByteArrayCopy(bh: Blackhole) {
        val src = ints1024
        val out = IntArray(1024)
        src.copyInto(out)
        bh.consume(out[0])
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val results = listOf(
            BenchmarkResult(BenchmarkAxis.MARSHALING, "writeReadIntScalar", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.MARSHALING, "writeReadLongScalar", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.MARSHALING, "copyInts16", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.MARSHALING, "copyInts1024", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.MARSHALING, "copyDoubles1024", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.MARSHALING, "baselineByteArrayCopy", "jvm-jmh", 1.0),
        )
        println(BenchmarkReport.toMarkdown("jvm-jmh", results))
    }
}
```

- [ ] **Step 2: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/MarshalingBenchmarks.kt
git commit -m "feat(benchmark): add JMH marshaling axis"
```

---

## Task 7: JVM JMH — arena axis

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/ArenaBenchmarks.kt`

- [ ] **Step 1: Write the arena benchmarks**

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/ArenaBenchmarks.kt`:

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.jvm

import io.ygdrasil.kffi.memoryScope
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class ArenaBenchmarks {

    @Benchmark
    fun memoryScopeTenAllocs(bh: Blackhole) {
        memoryScope { allocator ->
            var sum = 0L
            repeat(10) {
                sum += allocator.allocate(64L).hashCode()
            }
            bh.consume(sum)
        }
    }

    @Benchmark
    fun memoryScopeHundredAllocs(bh: Blackhole) {
        memoryScope { allocator ->
            var sum = 0L
            repeat(100) {
                sum += allocator.allocate(64L).hashCode()
            }
            bh.consume(sum)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val results = listOf(
            BenchmarkResult(BenchmarkAxis.ARENA, "memoryScope_10", "jvm-jmh", 1.0),
            BenchmarkResult(BenchmarkAxis.ARENA, "memoryScope_100", "jvm-jmh", 1.0),
        )
        println(BenchmarkReport.toMarkdown("jvm-jmh", results))
    }
}
```

- [ ] **Step 2: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS. If `allocator.allocate(64L)` returns a `NativeAddress` without a usable `hashCode` (it is a JVM object, so `hashCode()` works), replace the accumulation with a read from the buffer instead (e.g. write then read an Int).

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/ArenaBenchmarks.kt
git commit -m "feat(benchmark): add JMH arena allocation axis"
```

---

## Task 8: JVM JMH — downcall engine bake-off

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt`

- [ ] **Step 1: Write the bake-off**

Compares the three call paths on JVM: (a) raw FFM `downcallHandle.invokeExact` (current kffi), (b) `MethodHandle.invoke` via a cached handle with `Long` boxing (typed-wrapper-like overhead), (c) FFM `SymbolLookup.find` per call (uncached lookup cost). On JVM this is a *signal*; the definitive Android-device bake-off runs in P1 once the engine exists.

Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt`:

```kotlin
package org.graphiks.kffi.benchmark.jvm

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class DowncallEngineBakeoff {

    private val linker = Linker.nativeLinker()
    private lateinit var exactHandle: MethodHandle

    @Setup
    fun setup() {
        val l = FixtureLoader.lookup
        exactHandle = linker.downcallHandle(l.find("bench_empty").orElseThrow(), FunctionDescriptor.of(ValueLayout.JAVA_LONG))
    }

    @Benchmark
    fun fmmExact(bh: Blackhole) {
        bh.consume(exactHandle.invokeExact() as Long)
    }

    @Benchmark
    fun fmmExactDereferencedLookup(bh: Blackhole) {
        val handle = linker.downcallHandle(
            FixtureLoader.lookup.find("bench_empty").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG),
        )
        bh.consume(handle.invokeExact() as Long)
    }
}
```

- [ ] **Step 2: Compile**

Run: `./gradlew :kffi-benchmark-jvm:compileJmhKotlin`
Expected: SUCCESS.

- [ ] **Step 3: Commit**

```bash
git add kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/DowncallEngineBakeoff.kt
git commit -m "feat(benchmark): add downcall engine bake-off (JVM signal)"
```

---

## Task 9: JVM JMH — run the suite and emit the baseline report

**Files:**
- Create: `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/JmhJsonToMarkdown.kt`
- Create: `kffi/benchmarks/results/.gitkeep`

- [ ] **Step 1: Write the JSON → Markdown converter**

The JMH plugin writes `build/results/jmh/results.json`. This main converts it to the versioned report. Create `kffi-benchmark-jvm/src/jmh/kotlin/org/graphiks/kffi/benchmark/jvm/JmhJsonToMarkdown.kt`:

```kotlin
package org.graphiks.kffi.benchmark.jvm

import com.google.gson.JsonParser
import java.io.File
import kotlin.math.round

fun main(args: Array<String>) {
    val jsonPath = args.getOrNull(0) ?: error("usage: <jmh-results.json> [output.md]")
    val outPath = args.getOrNull(1) ?: error("usage: <jmh-results.json> [output.md]")
    val root = JsonParser.parseString(File(jsonPath).readText()).asJsonArray
    val lines = mutableListOf("# kffi JVM benchmark report (JMH)")
    lines.add("")
    lines.add("| Benchmark | Score (ns/op) | Error (±) |")
    lines.add("|---|---|---|")
    root.forEach { bench ->
        val benchName = bench.asJsonObject["benchmark"].asString
        val primary = bench.asJsonObject["primaryMetric"]
        val score = primary["score"].asDouble
        val err = primary["scoreError"].asDouble
        lines.add("| $benchName | ${"%.2f".format(score)} | ${"%.2f".format(err)} |")
    }
    File(outPath).parentFile.mkdirs()
    File(outPath).writeText(lines.joinToString("\n") + "\n")
}
```

- [ ] **Step 2: Add Gson to the jmh classpath**

In `kffi-benchmark-jvm/build.gradle.kts`, add to the `dependencies` block (after the existing `jmh(...)` entries):

```kotlin
    jmh("com.google.code.gson:gson:2.13.1")
```

- [ ] **Step 3: Run the full JMH suite**

Run: `./gradlew :kffi-benchmark-jvm:jmh`
Expected: SUCCESS, benchmark suite runs (several minutes). The task depends on the fixture compile and sets the library property.

- [ ] **Step 4: Locate the JMH JSON and emit the baseline report**

Run: `ls kffi-benchmark-jvm/build/results/jmh/`
Expected: `results.json` present.

Run (from repo root, with the module classpath):
`./gradlew :kffi-benchmark-jvm:compileJmhKotlin` then generate the report via the JMH jar classpath. If `main` invocation from Gradle is fiddly, use the generated JMH runner instead and note the report is produced from `results.json`:

```bash
mkdir -p kffi/benchmarks/results
cp kffi-benchmark-jvm/build/results/jmh/results.json kffi/benchmarks/results/$(date +%Y-%m-%d)-jvm-baseline.json
```

- [ ] **Step 5: Record the baseline**

Add a short note in `kffi/benchmarks/results/<date>-jvm-baseline.json` header (as a sibling `.md` with the ns/op table, hand-transcribed from the JSON until the converter main is wired):

```markdown
# JVM baseline (JMH, FFM downcall)
Date: <date>
Commit: <sha>
Harness: kffi-benchmark-jvm

## Downcall (ns/op)
- empty: <score>
- add4: <score>
- add8: <score>
- pairSum (struct): <score>
- makePair (struct return): <score>
...
```

- [ ] **Step 6: Commit**

```bash
git add kffi-benchmark-jvm kffi/benchmarks
git commit -m "feat(benchmark): wire JMH JSON->markdown and record JVM baseline"
```

---

## Task 10: Kotlin/Native benchmark harness

**Files:**
- Create: `kffi-benchmark-native/build.gradle.kts`
- Create: `kffi-benchmark-native/src/nativeInterop/cinterop/benchFixture.def`
- Create: `kffi-benchmark-native/src/nativeMain/kotlin/org/graphiks/kffi/benchmark/native/NativeHarness.kt`

- [ ] **Step 1: Create the native module build file**

Create `kffi-benchmark-native/build.gradle.kts`:

```kotlin
plugins {
    `kotlin-multiplatform`
}

group = "org.graphiks"

kotlin {
    macosArm64()
    linuxX64()

    sourceSets {
        val nativeMain by getting {
            dependencies {
                implementation(project(":kffi"))
                implementation(project(":kffi-benchmark-spi"))
            }
        }
    }
}
```

- [ ] **Step 2: Create the cinterop definition**

Create `kffi-benchmark-native/src/nativeInterop/cinterop/benchFixture.def`:

```
headers = bench_fixture.h
compilerOpts = -I/Users/chaos/workspace/wgpu4k-native/.worktrees/feat-kffi-socle-generique/kffi-benchmark-jvm/src/jmh/resources
```

> Note: if the native harness is expected to link the fixture too, replace the absolute include path with a generated path property in `build.gradle.kts` (mirror the `callbackFixture.def` `extraOpts` pattern in `kffi/build.gradle.kts:185-196`).

- [ ] **Step 3: Write the native harness**

Create `kffi-benchmark-native/src/nativeMain/kotlin/org/graphiks/kffi/benchmark/native/NativeHarness.kt`:

```kotlin
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package org.graphiks.kffi.benchmark.native

import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import kotlin.system.measureNanoTime

fun benchDowncallEmpty(): Long {
    val start = measureNanoTime { bench_empty() }
    return start
}

fun benchMarshaling(): Long {
    val start = measureNanoTime {
        io.ygdrasil.kffi.memoryScope { allocator ->
            val buffer = allocator.allocateBuffer(4096uL)
            val values = intArrayOf(1, 2, 3, 4)
            buffer.writeInts(values)
        }
    }
    return start
}

fun main() {
    val results = mutableListOf<BenchmarkResult>()
    repeat(3) { bench_empty() } // warmup
    results += BenchmarkResult(BenchmarkAxis.DOWNCALL, "empty", "native-macosArm64", benchDowncallEmpty().toDouble())
    results += BenchmarkResult(BenchmarkAxis.MARSHALING, "writeInts4", "native-macosArm64", benchMarshaling().toDouble())
    println(BenchmarkReport.toMarkdown("native-macosArm64", results))
}
```

- [ ] **Step 4: Verify it compiles**

Run: `./gradlew :kffi-benchmark-native:compileKotlinMacosArm64`
Expected: SUCCESS (first run downloads the native toolchain, can take a while).

- [ ] **Step 5: Commit**

```bash
git add kffi-benchmark-native
git commit -m "feat(benchmark): add Kotlin/Native benchmark harness skeleton"
```

> **Scope guard:** A correct ns/op native harness needs warmup/iteration loops and Blackhole-equivalent consumption. If the skeleton compiles but numbers look off, that's acceptable for P0 — the P0 goal is a *repeatable* harness, and the loop policy is standardized in a follow-up task (cross-backend comparison rule already documented).

---

## Task 11: Android instrumented harness

**Files:**
- Create: `kffi-benchmark-android/build.gradle.kts`
- Create: `kffi-benchmark-android/src/androidInstrumentedTest/kotlin/org/graphiks/kffi/benchmark/android/AndroidHarness.kt`

- [ ] **Step 1: Create the Android module build file**

Create `kffi-benchmark-android/build.gradle.kts`:

```kotlin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-multiplatform`
    com.android.library
    alias(libs.plugins.kotest)
}

group = "org.graphiks"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        android {
            namespace = "org.graphiks.kffi.benchmark"
            compileSdk = 36
            defaultConfig {
                minSdk = 28
            }
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":kffi"))
                implementation(project(":kffi-benchmark-spi"))
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.runner)
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
```

- [ ] **Step 2: Write the device harness**

Create `kffi-benchmark-android/src/androidInstrumentedTest/kotlin/org/graphiks/kffi/benchmark/android/AndroidHarness.kt`:

```kotlin
@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.benchmark.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.ygdrasil.kffi.memoryScope
import org.graphiks.kffi.benchmark.BenchmarkAxis
import org.graphiks.kffi.benchmark.BenchmarkReport
import org.graphiks.kffi.benchmark.BenchmarkResult
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureNanoTime

@RunWith(AndroidJUnit4::class)
class AndroidHarness {

    @Test
    fun emitMarshalingBaseline() {
        val results = mutableListOf<BenchmarkResult>()
        repeat(5) { memoryScope { allocator -> allocator.allocateBuffer(4096uL) } } // warmup
        val ns = measureNanoTime {
            repeat(1000) {
                memoryScope { allocator ->
                    val buffer = allocator.allocateBuffer(4096uL)
                    val values = IntArray(16) { it }
                    buffer.writeInts(values)
                }
            }
        }
        results += BenchmarkResult(
            BenchmarkAxis.MARSHALING,
            "writeInts16 x1000",
            "android-device",
            ns / 1000.0,
        )
        println(BenchmarkReport.toMarkdown("android-device", results))
    }
}
```

- [ ] **Step 3: Verify it compiles (assemble only)**

Run: `./gradlew :kffi-benchmark-android:assembleDebugAndroidTest`
Expected: SUCCESS (does not require a device).

- [ ] **Step 4: Commit**

```bash
git add kffi-benchmark-android
git commit -m "feat(benchmark): add Android instrumented harness skeleton"
```

> **Device note:** Actual device numbers require `./gradlew :kffi-benchmark-android:connectedDebugAndroidTest` on a connected device/emulator. For P0 CI, only the assemble step runs.

---

## Task 12: P0 bis — target repo, CI, snapshot publishing

**Files:**
- Create: `.github/workflows/kffi-benchmark-ci.yml` (in target repo)
- Create: `.github/workflows/kffi-publish-snapshots.yml` (in target repo)

- [ ] **Step 1: Create the target GitHub repo**

Run (requires `gh` auth to the `Graphiks-org` org):

```bash
gh repo create Graphiks-org/kffi --private --description "Kotlin Multiplatform FFI abstraction layer (org.graphiks.kffi)"
```

If `gh` is not authenticated to `Graphiks-org`, ask the user to create the repo and report the URL.

- [ ] **Step 2: Write the CI workflow**

Create `.github/workflows/kffi-benchmark-ci.yml`:

```yaml
name: kffi-benchmark-ci
on:
  push:
    branches: [main]
  pull_request:

jobs:
  jvm-benchmark:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
        with:
          submodules: recursive
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 25
      - name: Build and run JVM benchmarks
        run: ./gradlew :kffi-benchmark-jvm:jmh
      - name: Archive results
        uses: actions/upload-artifact@v4
        with:
          name: jmh-results
          path: kffi-benchmark-jvm/build/results/jmh/results.json

  native-compile:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
        with:
          submodules: recursive
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 25
      - name: Compile native harness
        run: ./gradlew :kffi-benchmark-native:compileKotlinMacosArm64

  android-assemble:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          submodules: recursive
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 17
      - name: Assemble android benchmark harness
        run: ./gradlew :kffi-benchmark-android:assembleDebugAndroidTest
```

- [ ] **Step 3: Write the snapshot publishing workflow**

Create `.github/workflows/kffi-publish-snapshots.yml`:

```yaml
name: kffi-publish-snapshots
on:
  push:
    branches: [main]

jobs:
  publish:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
        with:
          submodules: recursive
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 25
      - name: Publish snapshots
        env:
          ORG_GRADLE_PROJECT_mavenCentralUsername: ${{ secrets.MAVEN_CENTRAL_USERNAME }}
          ORG_GRADLE_PROJECT_mavenCentralPassword: ${{ secrets.MAVEN_CENTRAL_PASSWORD }}
          ORG_GRADLE_PROJECT_signingInMemoryKey: ${{ secrets.SIGNING_KEY }}
          ORG_GRADLE_PROJECT_signingInMemoryKeyPassword: ${{ secrets.SIGNING_PASSWORD }}
        run: |
          export VERSION="$(date +%Y%m%d%H%M%S)-SNAPSHOT"
          ./gradlew :kffi:publishAllPublicationsToMavenCentral \
            :kffi-benchmark-spi:publishAllPublicationsToMavenCentral
```

- [ ] **Step 4: Add a decision note to the plan status**

Add a line to `docs/superpowers/specs/2026-08-11-kffi-socle-generique-design.md` under P0 bis:

```markdown
- **P0 bis réalisé** : repo `Graphiks-org/kffi` créé, workflows CI + snapshots ajoutés.
  Le développement continue dans wgpu4k-native ; la migration finale reste en P5.
```

- [ ] **Step 5: Commit (in the worktree, the CI files are staged for later subtree split)**

```bash
git add .github/workflows docs/superpowers/specs
git commit -m "docs(ci): add target-repo CI and snapshot publishing workflows (P0 bis)"
```

---

## Task 13: Final verification and baseline record

- [ ] **Step 1: Run the full JVM test + benchmark suite**

Run: `./gradlew :kffi:jvmTest :kffi-benchmark-jvm:jmh`
Expected: kffi tests PASS (87 tests, 0 failures) and JMH suite completes.

- [ ] **Step 2: Verify all modules configure**

Run: `./gradlew :kffi-benchmark-spi:tasks :kffi-benchmark-jvm:tasks :kffi-benchmark-native:tasks :kffi-benchmark-android:tasks`
Expected: all succeed.

- [ ] **Step 3: Verify no regression in existing kffi tests**

Run: `./gradlew :kffi:jvmTest`
Expected: same 87 tests passing as the baseline (see plan intro).

- [ ] **Step 4: Commit any remaining changes**

```bash
git status
git add -A
git commit -m "chore(benchmark): finalize P0 benchmark harness"
```

- [ ] **Step 5: Confirm plan completion**

Report: list the recorded baseline files under `kffi/benchmarks/results/`, the CI workflows, and the target repo URL. Note that P1–P5 are separate plans.

---

## Out of scope (follow-up plans)

- **P1** — namespace migration `io.ygdrasil.kffi` → `org.graphiks.kffi`; Android backend rewrite (Kotlin layer + downcall engine with struct wrappers + upcall subsystem + NDK packaging + armeabi-v7a).
- **P2** — JVM cleanup: `.handler.handler` removal, inline `NativeAddress`, end of `Arena.ofAuto()`, explicit lifetime decision.
- **P3** — unified memory safety: per-allocator `unsafe` opt-in, uniform bounds checks, re-baseline.
- **P4** — callback runtime optimization guided by these baselines.
- **P5** — generic kextract, `org.graphiks:kffi-*` releases, final migration.
