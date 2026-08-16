# kffi JVM benchmark report (JMH)

Date: 2026-08-16
Commit: 3ca7130e

| Scenario | ns/op |
|---|---|
| arena.scope_100 | 3254.39 |
| arena.scope_10 | 328.15 |
| downcall.add4 | 4.49 |
| downcall.add8 | 5.15 |
| downcall.empty | 4.07 |
| downcall.struct_by_value_return | 13.08 |
| downcall.struct_by_value_arg | 6.73 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExact | 4.06 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExactDereferencedLookup | 358.75 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.jvmEngineAdd4 | 43.45 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.jvmEngineEmpty | 30.88 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.noOpFloor | 0.32 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.arrayInts16Safe | 10.41 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.arrayInts16Unsafe | 137.49 |
| marshaling.baseline_copy | 404.63 |
| marshaling.array_f64_1024 | 736.22 |
| marshaling.array_i32_1024 | 572.92 |
| marshaling.array_i32_16 | 77.11 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.scalarSafe | 1.83 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.scalarUnsafe | 2.53 |
| marshaling.scalar_i32 | 38.70 |
| marshaling.scalar_i64 | 39.31 |
| upcall.fire_1000 | 72719.24 |
| upcall.fire_1000_no_routing | 71084.06 |
| upcall.fire_one | 76.84 |
| upcall.fire_one_no_routing | 76.70 |

## Run configuration

- JMH 1.36 (`me.champeau.jmh` 0.7.2), `Mode.AverageTime`, ns/op, 1 thread
- warmup: 3 × 1s, measurement: 5 × 1s, forks: 2 — **identical to the P2 run** (same JMH version,
  same warmup/measurement/forks/mode, same JDK binary Temurin 25.0.1)
- JDK: Temurin 25.0.1 (sdkman default) — byte-identical to the P2 run's default JVM
- Host: macOS 26.5, Apple M2 Max (same machine as P0/P2)
- Fixture: `kffi-benchmark-jvm/build/bench-fixture/libbench_fixture.dylib` (C, `-O2`)
- 26 scenarios = the 22 P2 scenarios + 4 new P3 marshaling axes (`scalarSafe`, `scalarUnsafe`,
  `arrayInts16Safe`, `arrayInts16Unsafe` — the M6.1 safe-vs-unsafe axes)
- Full suite ran in ~6 min; no deviation from the P2 configuration

## Δ vs P2 baseline (2026-08-16-f6cdb8f0)

| Scenario | P3 (ns/op) | P2 (ns/op) | Δ |
|---|---|---|---|
| arena.scope_100 | 3254.40 ± 22.13 | 3270.08 | -0.5% |
| arena.scope_10 | 328.15 ± 2.92 | 356.41 | -7.9% |
| downcall.empty | 4.07 ± 0.03 | 4.07 | +0.1% |
| downcall.add4 | 4.49 ± 0.09 | 4.51 | -0.5% |
| downcall.add8 | 5.15 ± 0.15 | 5.19 | -0.8% |
| downcall.struct_by_value_arg | 6.73 ± 0.36 | 6.76 | -0.4% |
| downcall.struct_by_value_return | 13.08 ± 0.46 | 13.05 | +0.2% |
| bakeoff.fmmExact | 4.06 ± 0.04 | 4.17 | -2.6% |
| bakeoff.fmmExactDereferencedLookup | 358.75 ± 4.62 | 366.11 | -2.0% |
| bakeoff.jvmEngineEmpty | 30.88 ± 0.88 | 32.14 | -3.9% |
| bakeoff.jvmEngineAdd4 | 43.45 ± 2.68 | 44.46 | -2.3% |
| bakeoff.noOpFloor | 0.32 ± 0.00 | 0.32 | +0.3% |
| marshaling.baseline_copy | 404.63 ± 7.70 | 400.84 | +0.9% |
| marshaling.array_i32_16 | 77.11 ± 0.27 | 74.09 | +4.1% |
| marshaling.array_i32_1024 | 572.92 ± 20.58 | 578.10 | -0.9% |
| marshaling.array_f64_1024 | 736.22 ± 2.57 | 781.97 | -5.9% |
| marshaling.scalar_i32 | 38.70 ± 0.18 | 38.51 | +0.5% |
| marshaling.scalar_i64 | 39.31 ± 0.80 | 38.30 | +2.7% |
| upcall.fire_one | 76.84 ± 0.46 | 77.56 | -0.9% |
| upcall.fire_one_no_routing | 76.70 ± 0.43 | 76.60 | +0.1% |
| upcall.fire_1000 | 72719.24 ± 402.23 | 72340.16 | +0.5% |
| upcall.fire_1000_no_routing | 71084.06 ± 615.95 | 70499.04 | +0.8% |

Every P2 scenario reproduces within ±8% (worst: `marshaling.array_f64_1024` -5.9%,
`marshaling.array_i32_16` +4.1%, `arena.scope_10` -7.9%); the FFM floor and engine axes sit
within ±4%. The safe mode is the unchanged FFM-bounded path, so the existing scenario
stability is exactly the expected P3 outcome.

## Bounds-check axes (P3 new: safe vs unsafe marshaling)

| Axis | ns/op | Note |
|---|---|---|
| scalarSafe (FFM bounds-checked writeLong+readLong) | 1.83 ± 0.02 | state-reused buffer, dynamic 8-aligned offsets |
| scalarUnsafe (sun.misc.Unsafe writeLong+readLong) | 2.53 ± 0.05 | same offsets, no bounds check |
| arrayInts16Safe (FFM bulk copyInts 16) | 10.41 ± 0.03 | `MemoryBuffer.writeInts`/`readInts` |
| arrayInts16Unsafe (element-wise sun.misc.Unsafe loop) | 137.49 ± 1.80 | per-element copy loop |

Reading:

- **Scalar axis — the bounds-check overhead is *not* observable on the JVM; the "unsafe" path
  measured *slower* (-27.6%: 2.53 vs 1.83 ns).** The plan expectation (~10-40% overhead from the
  FFM bounds check) does not reproduce: JIT inlining turns the FFM check into a single compare
  (`offset < size`), and the FFM `set/get` on a `JAVA_LONG` var handle compiles to a direct
  aligned store, while the `sun.misc.Unsafe` addressing (`base + offset` recomputed per call)
  is not faster at this depth. Attribution caution: at ~2 ns/op the axis is at the edge of the
  measurement floor (`noOpFloor` = 0.32 ns); the within-run direction (safe ≤ unsafe) is stable
  across both full-suite forks and the M6.3 smoke re-runs.
- **Array axis — attribution limit confirmed (documented in M6.1, fb999ad4):** the unsafe array
  path swaps FFM's bulk `copyMemory` for an element-wise `sun.misc.Unsafe` loop, so
  `arrayInts16Unsafe` (137.49 ns) being ~13.2× slower than `arrayInts16Safe` (10.41 ns) is a
  copy-strategy change, **not** a bounds-check cost. This axis exists to show the strategy
  contrast, not to isolate the check; the scalar pair is the intended isolate.
- Net P3 finding: on the JVM, `MemoryAllocator(unsafe = true)` buys no measurable scalar
  throughput here; the bounds-check cost of the safe path is below resolution at this depth.

## Verdicts vs plan expectations

| Category | Plan expectation | Measured | Verdict |
|---|---|---|---|
| Existing 22 scenarios vs P2 | stable (safe JVM = unchanged FFM-bounded path) | ±0.1%..±7.9% (all within ±8%) | PASS |
| JVM bounds-check overhead (scalarSafe vs scalarUnsafe) | ~10-40% overhead expected | -27.6% (safe *faster*; overhead below resolution) | FAIL (expectation contradicted — documented above) |
| JVM array safe/unsafe | mix of bounds-check + strategy change (documented limit) | unsafe 13.2× slower (element-wise loop vs bulk copy) | PASS (as documented limit, not a regression) |
| Engine bake-off axes | P2 technical debt tracked, unchanged | jvmEngineEmpty/Add4 within -2.3%/-3.9% of P2 | PASS |

## Comparability note

- This run is byte-identical in configuration and JDK (Temurin 25.0.1) to the P2 run, on the
  same machine, with no concurrent Gradle/Kotlin-Native builds during measurement (the P0-era
  inflation documented in the P2 report is not present). Cross-run deltas vs P2 are therefore
  meaningful at the ±8% level.
- **Harness fixes applied at measurement time (not part of this commit, flagged for the branch):**
  (a) `MarshalingState` was declared `final` (M6.1), which the JMH 1.36 bytecode generator
  rejects — the class was made `open` to build the jmhJar; (b) the M6.1 dynamic-offset axis
  (`counter and 0x3F`) produced unaligned offsets that throw in the FFM safe path
  (`JAVA_LONG` alignment-8 constraint) — both axes now mask to 8-aligned offsets
  (`and 0x38`, offsets {0,8,16,24,32,40,48,56}), preserving the de-hoisting intent. Both
  changes are confined to `kffi-benchmark-jvm/src/jmh` and do not affect kffi.
- Raw values archived in [`2026-08-16-3ca7130e-jvm-baseline.json`](2026-08-16-3ca7130e-jvm-baseline.json)
  (JMH `result.json` copy).
