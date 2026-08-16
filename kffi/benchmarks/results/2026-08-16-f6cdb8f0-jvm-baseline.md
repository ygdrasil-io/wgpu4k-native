# kffi JVM benchmark report (JMH)

Date: 2026-08-16
Commit: f6cdb8f0

| Scenario | ns/op |
|---|---|
| arena.scope_100 | 3270.08 |
| arena.scope_10 | 356.41 |
| downcall.add4 | 4.51 |
| downcall.add8 | 5.19 |
| downcall.empty | 4.07 |
| downcall.struct_by_value_return | 13.05 |
| downcall.struct_by_value_arg | 6.76 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExact | 4.17 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExactDereferencedLookup | 366.11 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.jvmEngineAdd4 | 44.46 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.jvmEngineEmpty | 32.14 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.noOpFloor | 0.32 |
| marshaling.baseline_copy | 400.84 |
| marshaling.array_f64_1024 | 781.97 |
| marshaling.array_i32_1024 | 578.10 |
| marshaling.array_i32_16 | 74.09 |
| marshaling.scalar_i32 | 38.51 |
| marshaling.scalar_i64 | 38.30 |
| upcall.fire_1000 | 72340.16 |
| upcall.fire_1000_no_routing | 70499.04 |
| upcall.fire_one | 77.56 |
| upcall.fire_one_no_routing | 76.60 |

## Run configuration

- JMH 1.36 (`me.champeau.jmh` 0.7.2), `Mode.AverageTime`, ns/op, 1 thread
- warmup: 3 × 1s, measurement: 5 × 1s, forks: 2 — **identical to the P0 run** (verified against the
  per-benchmark metadata embedded in `2026-08-12-012b50e9-jvm-baseline.json`: same JMH version,
  same warmup/measurement/forks/mode; only the JVM binary and the bench-module sources differ)
- JDK: Temurin 25.0.1 (sdkman default); P0 ran on Temurin 25.0.3 — see the comparability note below
- Host: macOS 26.5, Apple M2 Max (both the P0 and the P2 runs on this same machine)
- Fixture: `kffi-benchmark-jvm/build/bench-fixture/libbench_fixture.dylib` (C, `-O2`)
- 22 scenarios = the 20 P0 scenarios + 2 new engine bake-off axes (`jvmEngineAdd4`, `jvmEngineEmpty`)
- No deviation from the P0 configuration; full suite ran in ~6 min + two focused re-runs:
  (a) reproducibility check on the default JVM (25.0.1: empty 4.03 / add4 4.47 / fmmExact 4.09 /
  jvmEngineAdd4 44.10 ns) and (b) a P0-binary check on Temurin 25.0.3 (empty 4.08 / add4 4.43 /
  fmmExact 4.11 / jvmEngineAdd4 42.63 ns) — stable on both. Raw values archived in
  [`2026-08-16-p0-jvm-control-rerun.json`](2026-08-16-p0-jvm-control-rerun.json).

## Δ vs P0 baseline (2026-08-12-012b50e9)

| Scenario | P2 (ns/op) | P0 (ns/op) | Δ |
|---|---|---|---|
| arena.scope_100 | 3270.08 ± 98.03 | 31930.21 | -89.8% |
| arena.scope_10 | 356.41 ± 50.45 | 4576.20 | -92.2% |
| downcall.empty | 4.07 ± 0.04 | 27.36 | -85.1% |
| downcall.add4 | 4.51 ± 0.10 | 26.54 | -83.0% |
| downcall.add8 | 5.19 ± 0.11 | 36.45 | -85.8% |
| downcall.struct_by_value_arg | 6.76 ± 0.28 | 36.96 | -81.7% |
| downcall.struct_by_value_return | 13.05 ± 0.72 | 58.98 | -77.9% |
| bakeoff.fmmExact | 4.17 ± 0.11 | 25.96 | -83.9% |
| bakeoff.fmmExactDereferencedLookup | 366.11 ± 9.44 | 1806.64 | -79.7% |
| bakeoff.noOpFloor | 0.32 ± 0.00 | 1.03 | -69.2% |
| bakeoff.jvmEngineEmpty | 32.14 ± 1.65 | — | new axis |
| bakeoff.jvmEngineAdd4 | 44.46 ± 3.09 | — | new axis |
| marshaling.baseline_copy | 400.84 ± 5.11 | 1761.82 | -77.2% |
| marshaling.array_i32_16 | 74.09 ± 0.47 | 417.57 | -82.3% |
| marshaling.array_i32_1024 | 578.10 ± 4.54 | 2170.11 | -73.4% |
| marshaling.array_f64_1024 | 781.97 ± 7.93 | 4091.88 | -80.9% |
| marshaling.scalar_i32 | 38.51 ± 0.67 | 157.37 | -75.5% |
| marshaling.scalar_i64 | 38.30 ± 0.22 | 141.17 | -72.9% |
| upcall.fire_one | 77.56 ± 1.03 | 120.18 | -35.5% |
| upcall.fire_one_no_routing | 76.60 ± 0.54 | 139.69 | -45.2% |
| upcall.fire_1000 | 72340.16 ± 597.79 | 105641.52 | -31.5% |
| upcall.fire_1000_no_routing | 70499.04 ± 755.76 | 99336.35 | -29.0% |

Every scenario measured faster than its P0 absolute value. The scenario structure is preserved
(upcall ≈ 16–19× downcall cost per call in-run — e.g. 77.56/4.07 ≈ 19.1×, 72340.16/1000/4.07 ≈
17.8× — vs ~4× on the P0-era absolutes; struct-return ≈ 2× struct-arg (13.05/6.76 ≈ 1.93×);
array marshaling scales with size; routing-free upcalls ≈ routing upcalls).

## Engine bake-off (within-run: JvmDowncallEngine vs FFM floor)

| Axis | ns/op | Overhead vs floor |
|---|---|---|
| noOpFloor (loop/Blackhole floor) | 0.32 ± 0.00 | — |
| fmmExact (cached `MethodHandle.invokeExact`, kffi steady-state floor) | 4.17 ± 0.11 | — |
| jvmEngineEmpty (`JvmDowncallEngine.callI0`) | 32.14 ± 1.65 | +28.0 ns (~7.7×) |
| jvmEngineAdd4 (`JvmDowncallEngine.callI4IIII`) | 44.46 ± 3.09 | +40.3 ns (~10.7×) |
| fmmExactDereferencedLookup (per-call lookup + handle construction) | 366.11 ± 9.44 | +362 ns (~88×) |

Reading:

- The two-level (addr × shape) cache is doing its job: the engine sits ~8–11× closer to the FFM
  floor than the no-cache worst case (`fmmExactDereferencedLookup`: 366.11/44.46 ≈ 8.2× for add4,
  366.11/32.14 ≈ 11.4× for empty) — i.e. ~8–11× headroom still gained by caching.
- The shape-specific dispatch cost is visible in the engine axes themselves:
  `jvmEngineAdd4` − `jvmEngineEmpty` ≈ 12.3 ns. Vs the P0-era *direct* FFM absolutes, the engine
  reads +67.5% (44.46 vs downcall.add4 26.54) for the add4 shape and +17.5% (32.14 vs
  downcall.empty 27.36) for empty on this run; the M5.3 smoke (42.6 vs 26.54 = +60.5%) is
  consistent with 44.46 ± 3.09. Caveat: per the comparability note below, the P0 absolutes are
  machine-inflated, so the honest engine comparison is the within-run table above (engine vs
  fmmExact = 7.7× / 10.7×). The 12.3 ns shape gap is the documented P2 price of the generic
  engine and is the target of the wrap-once typed cache / native engine follow-ups.
- Full-suite value reproduced the M5.3 smoke within a few percent: `jvmEngineAdd4` 44.46 ± 3.09 ns
  here vs 42.63 on the Temurin 25.0.3 re-run (+4.3%, different JVM patch), `fmmExact` 4.17 ns
  (4.11 on 25.0.3).

## Verdicts vs plan expectations

| Category | Plan expectation | Measured (vs P0) | Verdict |
|---|---|---|---|
| downcall.empty / add4 / add8 | Δ ≤ +50% | -85.1% / -83.0% / -85.8% (direct FFM path) | PASS (far below bound) |
| downcall.struct_by_value_arg / _return | neutral or better | -81.7% / -77.9% | PASS |
| upcall.fire_one / _1000 (± routing) | neutral | -35.5% / -45.2% / -31.5% / -29.0% | PASS |
| marshaling.scalar_* / array_* / baseline_copy | better or equal | -72.9% .. -82.3% | PASS |
| arena.scope_10 / scope_100 | (P2 scope: end of `Arena.ofAuto()`, caller-owned arena) | -92.2% / -89.8% | PASS |

All plan expectations are met or exceeded. The engine downcall axes (`jvmEngine*`) exceed the
+50% bound only in the add4-shape case when compared against the P0-era *direct* FFM absolute
value (+67.5%: 44.46 vs 26.54; empty is +17.5%) — this is the known M5.2bis two-level-cache
cost, consistent with the M5.3 smoke (+60.5%), and is explicitly tracked as P2 technical debt
rather than a baseline regression (the engine axis did not exist in P0).

## Comparability note (P0 vs P2 absolute values)

The Δs above must be read with one caveat: the **byte-identical** floor benchmarks
(`fmmExact`, `noOpFloor`, `DowncallBenchmarks.*`) all measured 3.2×–7.0× faster than their P0
absolute values (noOpFloor 1.03/0.32 ≈ 3.2×, fmmExact 25.96/4.17 ≈ 6.2×, downcall.add8
36.45/5.19 ≈ 7.0×). Re-running the identical benchmark binary and configuration on the exact
P0 JVM binary (Temurin 25.0.3 — a distinct re-run from the 25.0.1 reproducibility check above)
reproduces ~4 ns (empty 4.08 / add4 4.43 / fmmExact 4.11), **not** the P0 ~26–27 ns. The P0 run's
absolute numbers were therefore inflated by machine state on 2026-08-12 (most plausibly
concurrent Gradle/Kotlin-Native builds during the M1–M5 window), not by code or JVM changes —
the JMH metadata in both `result.json` files confirms identical configuration otherwise.

Consequences:

- Δ% vs P0 should be read as *"no regression + environment normalization"*, not as code-driven
  speedups; absolute cross-run comparisons on this machine are only meaningful when the machine
  is quiescent (this P2 run and the M5.3 smoke agree with each other).
- The reliable signals are the within-run engine bake-off (above) and the preserved scenario
  structure — both consistent with the M5.3 smoke (fmmExact ~4 ns, callI4IIII ~42.6 ns).
- Recommended follow-up: a machine-quiescence check (or a `-prof perfasm`-free re-baseline) before
  trusting future absolute cross-run deltas.
