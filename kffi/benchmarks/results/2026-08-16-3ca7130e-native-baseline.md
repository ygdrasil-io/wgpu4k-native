# kffi Native benchmark report (Kotlin/Native harness)

Backend: `native-macosArm64`
Date: 2026-08-16
Commit: 3ca7130e

| Axis | Scenario | ns/op |
|---|---|---|
| DOWNCALL | downcall.empty | 11.24 |
| MARSHALING | marshaling.array_i32_16 | 3036.82 |

Values are the **median of 10 harness runs** (the harness is a single-shot `measureTime` loop,
not a JMH-style fork; run-to-run spread is ±30% at the extremes — see the variance table
below). Default build: `KFFI_NATIVE_UNSAFE=false` (bounded).

## Bounds-check overhead (borné vs variante unsafe)

The unsafe variant is a build-time constant in `kffi` (`MemoryBuffer.native.kt`:
`KFFI_NATIVE_UNSAFE`), documented in M6.2 (3ca7130e) and **not** compiled by default. For this
report the constant was flipped to `true`, the harness re-run (10 runs), and the constant
reverted — no kffi change is committed.

| Scenario | borné (ns/op, median) | unsafe (ns/op, median) | Δ |
|---|---|---|---|
| downcall.empty | 11.24 | 10.42 | -7.3% |
| marshaling.array_i32_16 | 3036.82 | 2784.79 | -8.3% |

Raw runs (ns/op):

| Run | downcall.empty borné | downcall.empty unsafe | array_i32_16 borné | array_i32_16 unsafe |
|---|---|---|---|---|
| 1 | 10.07 | 10.21 | 3885.13 | 4607.32 |
| 2 | 12.51 | 13.09 | 3229.16 | 3117.01 |
| 3 | 10.86 | 11.05 | 2837.35 | 2786.25 |
| 4 | 17.51 | 10.10 | 3911.83 | 2729.94 |
| 5 | 11.61 | 10.42 | 2947.17 | 2775.73 |
| 6 | 23.17 | 18.69 | 4673.84 | 3779.39 |
| 7 | 12.60 | 12.68 | 3126.46 | 3065.97 |
| 8 | 10.12 | 10.42 | 2795.53 | 2783.33 |
| 9 | 9.80 | 10.09 | 2772.97 | 2709.15 |
| 10 | 10.37 | 10.37 | 2780.70 | 2753.93 |

Reading:

- **`downcall.empty` is a flag-independent control** (no `MemoryBuffer` on that path): its
  -7.3% shift is pure harness noise and defines the measurement floor. The marshaling -8.3%
  sits at the same magnitude — i.e. **the array-scenario bounds-check overhead is not resolvable
  above the harness noise**. The per-element check (`offset >= size || offset + width > size`,
  2 compares on constants the compiler can partially fold, since the 4096-byte buffer and the
  element strides are known at compile time) is cheap relative to the ~3000 ns dominated by the
  actual memory writes.
- The plan expectation ("significatif sur les scalaires") cannot be directly confirmed: the
  harness only exposes `downcall.empty` and `marshaling.array_i32_16` — there is no native
  scalar scenario. On the scenario that exists, the measured overhead is ≤ ~8% and
  statistically indistinguishable from noise.
- Kotlin/Native caveat: the `unsafe` flag also disables checks on the array path, but the array
  path cost is dominated by the copy, not the checks — a scalar native scenario would be needed
  to isolate the check cost (follow-up, documented as part of the M6.2 note).

## Verdicts vs plan expectations

| Category | Plan expectation | Measured | Verdict |
|---|---|---|---|
| P3 native absolutes (no prior native baseline — first report) | document | downcall.empty 11.24 ns, marshaling.array_i32_16 3036.82 ns (median of 10) | DONE (baseline established) |
| Bounds-check overhead native | significant on scalars | -8.3% on the array scenario, at the noise floor; no scalar scenario exists | FAIL (expectation contradicted — check cost below resolution on this harness) |
| Flag-independent control | downcall unaffected | -7.3% (noise floor) | PASS (control consistent) |

## Comparability note

- **No prior native baseline exists** (the only earlier device report,
  `2026-08-14-cb281990-android-device.md`, is an emulator bake-off and explicitly not
  comparable). This report therefore establishes the P3 absolute values for
  `native-macosArm64`; there is no cross-run native Δ to compute.
- The harness is a debug executable (`linkBenchmarkDebugExecutableMacosArm64`); the
  single-shot `measureTime` design trades precision for simplicity. Cross-run comparisons on
  this machine are only meaningful above ~±10-15%; the median-of-10 convention above is the
  recommended reading level.
- Environment: Apple M2 Max, macOS 26.5, no concurrent Gradle/Kotlin-Native builds during
  measurement. One environment wrinkle: `ar` on the global PATH resolves to GNU binutils,
  whose archives Xcode's `ld` rejects (`archive member invalid control bits`); the harness was
  linked with `/usr/bin` (BSD ar) first on PATH. Not a code issue — a machine-PATH note for
  reproducibility.

Machine-readable result:

```
RESULT_JSON=[{"axis":"DOWNCALL","scenario":"downcall.empty","backend":"native-macosArm64","nsPerOp":11.24,"variant":"bounded"},{"axis":"MARSHALING","scenario":"marshaling.array_i32_16","backend":"native-macosArm64","nsPerOp":3036.82,"variant":"bounded"},{"axis":"DOWNCALL","scenario":"downcall.empty","backend":"native-macosArm64","nsPerOp":10.42,"variant":"unsafe"},{"axis":"MARSHALING","scenario":"marshaling.array_i32_16","backend":"native-macosArm64","nsPerOp":2784.79,"variant":"unsafe"}]
```
