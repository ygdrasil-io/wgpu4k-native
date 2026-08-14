# kffi Android benchmark report (device bake-off)

Backend: `android-device` (x86_64 emulator, `Medium_Phone_API_36.0`)
Date: 2026-08-14
Commit: cb281990

| Axis | Scenario | ns/op |
|---|---|---|
| DOWNCALL | downcall.add4 | 6038.01 |
| DOWNCALL | downcall.empty | 5515.90 |
| UPCALL | upcall.fire_one | 357814.34 |

Machine-readable result:

```
RESULT_JSON=[{"axis":"DOWNCALL","scenario":"downcall.empty","backend":"android-device","nsPerOp":5515.8992},{"axis":"DOWNCALL","scenario":"downcall.add4","backend":"android-device","nsPerOp":6038.0124},{"axis":"UPCALL","scenario":"upcall.fire_one","backend":"android-device","nsPerOp":357814.336}]
```

Notes:

- The `marshaling.array_i32_16` scenario did **not** emit on this device run. Its
  measurement path (`MemoryBuffer.writeInts` → `sun.misc.Unsafe.copyMemory(Object, ...)`)
  throws `NoSuchMethodError` on ART, where that JVM-only `copyMemory` overload does not
  exist (`MemoryBuffer.android.kt:60`). That is a pre-existing kffi engine defect, not a
  harness regression; the harness reports it as
  `RESULT_ERROR scenario=marshaling.array_i32_16` and continues with the other axes.
- Downcall/upcall numbers are emulator figures (software-emulated libffi + JNI) and are
  **not** comparable to the JVM baseline (`2026-08-12-012b50e9-jvm-baseline.md`).
