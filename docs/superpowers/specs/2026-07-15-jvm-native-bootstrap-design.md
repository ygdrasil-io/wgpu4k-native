# wgpu4k-native v29 JVM bootstrap design

## Problem

The v29 JVM bindings resolve native function addresses through
`io.ygdrasil.kffi.findOrThrow`. That lookup only searches libraries already
visible through `SymbolLookup.loaderLookup()` or the platform default lookup.
`wgpu4k-native-jvm` did not load its bundled wgpu-native library before the
first lookup, so a fresh JVM could fail with `UnsatisfiedLinkError`.

Calling the historical `ffi.LibraryLoader.load()` first made the same downcall
succeed, which identified the missing module bootstrap as the root cause.

## Goals

- Load the bundled wgpu-native library before the first dependent JVM symbol
  lookup, without a consumer bootstrap.
- Keep loading idempotent, thread-safe, retryable after failure, and publish the
  loaded state only after `System.load` completes.
- Preserve the original failure object.
- Support macOS ARM64/x86-64, Linux ARM64/x86-64, and Windows x86-64 resources.
- Keep the mechanism generic in kextract while deriving its configuration from
  existing `--library` inputs and the supplied native resource bundle.
- Add no bootstrap work to an already initialized generated downcall.
- Preserve the callback implementation and lifecycle.

## Non-goals

- No Android source, task, test, or artifact behavior is changed.
- No public or legacy loader API is retained. Binary compatibility with
  `ffi.LibraryLoader` is explicitly out of scope.
- No wgpu-specific loading policy is added to kffi.
- No remote artifact repository is updated.

## Selected architecture

### Generic generated bootstrap

kextract accepts `--jvm-native-resources <directory>` for multiplatform
generation. For each named `--library`, it derives the platform filename using
the host conventions:

- macOS: `lib<name>.dylib`;
- Linux: `lib<name>.so`;
- Windows: `<name>.dll`.

Generation scans the five supported platform directories recursively and
embeds each selected resource path and SHA-256 digest in a private JVM source
bootstrap. This lets kextract infer the resolver without a wgpu-specific
dependency or a generated API visible to consumers. Missing or ambiguous
resources fail generation rather than producing an incomplete runtime loader.

Every generated symbol address is initialized through
`KextractNativeBootstrap.resolve(symbol)`. The resolver completes native
loading and only then delegates to kffi's `findOrThrow`.

### Extraction and dynamic loading

The bootstrap extracts the complete platform bundle into a content-addressed
cache below the system temporary directory, or below
`kextract.native.cache.dir` when that system property is set. Recursive bundle
paths are preserved. The content digest prevents stale native dependencies from
being reused across versions.

Resources are enumerated through the generated class's classloader. If several
classpath entries expose the same resource path, the embedded digest selects
the correct bytes and rejects collisions. Files are written through a temporary
path and atomically moved when supported. A JVM-wide monitor plus a file lock
coordinate threads, classloaders, and concurrent processes using the same
cache. `System.load` always receives an absolute extracted path, so no
`java.library.path` or operating-system-specific consumer directory is needed.

### State and failure behavior

The loader uses a volatile loaded flag with a synchronized double check. The
flag becomes true only after extraction and every effective `System.load`
operation succeeds. Failures are not caught or wrapped, so the original cause
is preserved and the flag remains false. The next access retries the complete
operation. Concurrent first callers serialize until one successful load has
published the state.

Loading remains behind the generated address `lazy`. A failed load therefore
does not poison JVM class initialization, and a later invocation can retry.

### Performance

Extraction, hashing, and dynamic linking happen once on the first native symbol
access. The first access to each later symbol performs one volatile loaded-state
check before caching its address. Once a generated address and method handle
are initialized, regular downcalls do not invoke the bootstrap at all; their
steady-state path is unchanged.

### Callbacks

No callback state machine, trampoline, registration ownership rule, or close
behavior changes. The registration-based log callback helper uses the same
generated resolver during native setter preflight, so it also triggers the
auto-load safely.

## Tests

kextract owns isolated runtime tests for the generic mechanism:

- fresh-JVM first downcall without a loader;
- callback registration and close before any explicit bootstrap;
- concurrent first callers with exactly one effective load;
- exact failure propagation, unloaded state, and successful retry;
- recursive bundles, cache paths containing spaces, and classpath collisions.

wgpu4k-native launches child JVM probes using the packaged wgpu-native resource
and no loader API. The probes cover `wgpuSetLogLevel`, registration-based
`wgpuSetLogCallback`, and concurrent first generated calls. A minimal consumer
published to Maven Local verifies the same behavior using only the public
generated API.

## Verification

- Regenerate the checked-in binding from the updated kextract submodule.
- Run kextract and wgpu4k-native JVM tests locally and in Linux Docker.
- Publish the JVM artifacts to Maven Local and run the isolated consumer.
- Compile the relevant non-Android Kotlin/Native targets.
- Validate macOS, Linux, and Windows through GitHub Actions.
- Run `git diff --check` and confirm that no Android file changed.
