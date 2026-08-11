# kffi JVM benchmark report (JMH)

| Benchmark | Score (ns/op) | Error (±) |
|---|---|---|
| org.graphiks.kffi.benchmark.jvm.ArenaBenchmarks.memoryScopeHundredAllocs | 43562.81 | 7439.02 |
| org.graphiks.kffi.benchmark.jvm.ArenaBenchmarks.memoryScopeTenAllocs | 4134.08 | 538.60 |
| org.graphiks.kffi.benchmark.jvm.DowncallBenchmarks.add4 | 36.92 | 0.42 |
| org.graphiks.kffi.benchmark.jvm.DowncallBenchmarks.add8 | 54.76 | 13.21 |
| org.graphiks.kffi.benchmark.jvm.DowncallBenchmarks.empty | 35.77 | 0.78 |
| org.graphiks.kffi.benchmark.jvm.DowncallBenchmarks.makePair | 86.28 | 10.54 |
| org.graphiks.kffi.benchmark.jvm.DowncallBenchmarks.pairSum | 55.77 | 2.90 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExact | 42.04 | 4.22 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.fmmExactDereferencedLookup | 2458.28 | 183.99 |
| org.graphiks.kffi.benchmark.jvm.DowncallEngineBakeoff.noOpFloor | 1.36 | 0.18 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.baselineByteArrayCopy | 2574.23 | 1175.72 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.copyDoubles1024 | 5722.22 | 727.66 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.copyInts1024 | 4281.85 | 3001.90 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.copyInts16 | 409.00 | 107.71 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.writeReadIntScalar | 234.29 | 26.00 |
| org.graphiks.kffi.benchmark.jvm.MarshalingBenchmarks.writeReadLongScalar | 255.63 | 56.63 |
| org.graphiks.kffi.benchmark.jvm.UpcallBenchmarks.upcallFire1000 | 219088.63 | 37338.76 |
| org.graphiks.kffi.benchmark.jvm.UpcallBenchmarks.upcallFireOne | 273.33 | 48.45 |
