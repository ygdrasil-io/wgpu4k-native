# wgpu4k-native v29 JVM bootstrap design

## Problem

The v29 JVM bindings resolve every native function address through
`io.ygdrasil.kffi.findOrThrow`. That lookup only searches libraries already
visible through `SymbolLookup.loaderLookup()` or the platform default lookup.
`wgpu4k-native-jvm` does not load its bundled wgpu-native library before the
first lookup, so a fresh JVM can fail with `UnsatisfiedLinkError`.

The failure is reproducible without an explicit loader call for both:

- `wgpuSetLogLevel(WGPULogLevel_Warn)`;
- the registration-based `wgpuSetLogCallback(...)` helper, whose JVM preflight
  resolves `wgpuSetLogCallback` before creating the registration.

Calling the historical `ffi.LibraryLoader.load()` first makes the same downcall
succeed, confirming that the missing module bootstrap is the root cause.

## Goals

- Load the bundled wgpu-native library automatically before the first dependent
  JVM symbol lookup.
- Keep loading idempotent and thread-safe.
- Publish the loaded state only after extraction and `System.load*` complete.
- Preserve the original failure and leave the loader retryable after failure.
- Add no steady-state overhead to an already initialized generated downcall.
- Keep wgpu-specific loading in `wgpu4k-native-jvm`, not in generic `kffi`.
- Provide the stable explicit API `io.ygdrasil.wgpu.LibraryLoader.load()` in
  addition to automatic loading.
- Preserve the current callback implementation and lifecycle.

## Non-goals

- No Android source, task, test, or artifact behavior is changed.
- No compatibility facade is retained under `ffi.*`; binary compatibility with
  the historical loader is explicitly out of scope.
- The generated `lazy` strategy for symbol addresses and method handles is not
  redesigned.
- No remote repository is updated.

## Selected architecture

### Load controller

Move the existing extraction and platform-specific loading implementation into
the `io.ygdrasil.wgpu` JVM package. A small internal load controller owns a
volatile loaded flag and a lock.

`load()` performs a lock-free loaded fast-path. If loading is still required, it
enters the lock, checks the flag again, executes the existing extraction and
`System.load`/`System.loadLibrary` operation, and only then sets the flag to
true. The operation is not caught or wrapped, so callers observe the original
failure object.

After a failure the flag remains false. The next caller retries the complete
operation. Concurrent successful first callers serialize only while the single
load operation is in progress; after it succeeds, every caller observes the
published loaded state.

The public `io.ygdrasil.wgpu.LibraryLoader` object delegates to this controller.
The historical `ffi.LibraryLoader` and its package-local platform helper are
removed rather than retained as a deprecated facade.

### Generated symbol resolution

Add a module-specific internal resolver:

```kotlin
internal fun findWgpuSymbol(symbol: String): MemorySegment {
    LibraryLoader.load()
    return findKffiSymbol(symbol)
}
```

The generated JVM binding imports this resolver under the existing local name
`findOrThrow`. Consequently every generated address initializer keeps its
current shape while loading is guaranteed to complete before the generic kffi
lookup begins.

The generated address remains `lazy`, so failure does not poison JVM class
initialization and a later invocation can retry. Loading is not placed in an
eager top-level initializer because a failed JVM class initializer cannot be
retried safely.

The wgpu4k generation task post-processes and validates the single JVM resolver
import after kextract generation. This keeps the wgpu-specific policy in this
module and prevents a future regeneration from silently restoring the direct
kffi lookup. The generic kextract submodule and `kffi` remain unchanged.

### Performance

The extraction and dynamic-linking cost moves from an explicit consumer call to
the first native call. Each generated symbol's first address initialization
performs one additional loaded-state fast-path after the library has already
been loaded by another symbol.

Subsequent calls to an initialized function do not invoke the module resolver or
the loader. They retain the existing Kotlin `lazy` access to the cached
`MethodHandle`, including its existing volatile-read fast-path, but the
bootstrap adds no per-downcall operation.

### Callbacks

No callback state machine, trampoline, registration ownership rule, or close
behavior changes. The registration-based log callback helper benefits from the
same resolver because its generated preflight resolves the native setter through
the auto-loading path.

## Tests

### Isolated JVM probes

Launch child JVM processes with the current test runtime classpath and no call
to either loader API.

1. Call `wgpuSetLogLevel` as the first native operation and require normal
   process exit.
2. Call the registration-based `wgpuSetLogCallback`, require a registration,
   and close it cleanly.
3. Release several threads together so their first operation is a generated
   native call. Require all calls to succeed and all threads to observe a
   coherent loaded state.

The child-process boundary guarantees that prior tests cannot have loaded the
library.

### Load controller tests

Use an injected load operation on a fresh controller instance to verify:

- concurrent callers execute the effective operation exactly once;
- an injected failure leaves the state unloaded;
- the exact original failure is propagated;
- the next call retries, and a successful retry publishes the loaded state.

No legacy compatibility test is added because the legacy API is intentionally
removed.

## Verification

- Capture the isolated first-downcall test failing before implementation and
  passing after implementation.
- Run the JVM test suite while explicitly excluding all Android download and
  extraction tasks from the Gradle graph.
- Compile relevant host Kotlin/Native targets with the same Android exclusions.
- Publish the JVM and required metadata artifacts to Maven Local without any
  Android task execution.
- Compile and run a minimal external JVM consumer depending only on the newly
  published `io.ygdrasil:wgpu4k-native-jvm` snapshot. Its first operation calls
  a generated public function and it imports no loader.
- Run `git diff --check` and inspect the final file list to confirm that no
  Android file changed.
- Create one final local commit and do not push or publish remotely.

