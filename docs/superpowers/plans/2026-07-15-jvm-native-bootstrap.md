# wgpu4k-native v29 generated JVM bootstrap implementation plan

> The implementation follows the generic kextract design recorded in the
> companion design document. Android is out of scope for every step.

## 1. Establish the failure and ownership boundary

- Run an isolated child JVM whose first operation is `wgpuSetLogLevel`.
- Confirm that direct `io.ygdrasil.kffi.findOrThrow` cannot see wgpu-native
  until the historical loader has run.
- Verify that callback registration reaches the same generated symbol lookup.
- Inspect kextract's KMP JVM emitter and keep kffi free of library-specific
  policy.

## 2. Add RED tests to kextract

- Generate a minimal multiplatform binding with a bundled test native library.
- Assert that a fresh JVM can call the generated function without a loader.
- Assert callback registration and close before explicit bootstrap.
- Release concurrent threads into the first call and count one effective load.
- Inject a first extraction/load failure, assert exact cause identity and
  unloaded state, then assert successful retry.
- Cover recursive resources, paths containing spaces, and a classpath resource
  collision.

## 3. Generate the generic JVM bootstrap

- Add `--jvm-native-resources` for multiplatform generation.
- Derive platform library filenames from existing named `--library` values.
- Scan and hash the five desktop JVM bundles at generation time.
- Emit a private bootstrap that extracts the complete matching bundle into a
  content-addressed cache.
- Coordinate threads, classloaders, and processes with a JVM monitor and file
  lock.
- Set the volatile loaded flag only after successful `System.load` calls.
- Route generated address initialization through the bootstrap before kffi's
  lookup.
- Keep output without bundled libraries byte-for-byte compatible.

## 4. Regenerate wgpu4k-native

- Pass the generated JVM resource root and `--library wgpu_native` to kextract.
- Make generation and JVM resource processing depend only on desktop JVM
  archives and headers, not the all-platform fetch aggregate.
- Regenerate `wgpu_hJvm.kt` and validate the generated resolver marker.
- Remove the public and historical loader implementations without a facade.
- Remove explicit loader calls from versioned JVM demos and the isolated
  consumer.

## 5. Verify locally

- Run kextract's focused RED/GREEN tests and complete suite.
- Run `:wgpu4k-native:jvmTest`; each bootstrap scenario uses a fresh process.
- Publish `kffi-jvm` and `wgpu4k-native-jvm` to Maven Local.
- Run the minimal consumer with no loader import, bootstrap call, or
  `java.library.path` override.
- Compile the relevant host Kotlin/Native targets while excluding the
  all-platform fetch aggregate.
- Run `git diff --check` and audit changed paths for Android files.

## 6. Verify Linux and CI

- Run the kextract runtime suite in a Linux JDK Docker image.
- Push `fix/kmp-jvm-native-bootstrap` and validate kextract CI.
- Push `fix/jvm-native-bootstrap` and validate wgpu4k-native on macOS, Linux,
  and Windows.
- Create/update one draft PR for each repository and record exact commands,
  results, commits, and Maven Local coordinates.
