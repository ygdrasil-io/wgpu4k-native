# How To Regenerate kextract Bindings

This guide explains how to regenerate Kotlin FFI bindings from C/ObjC headers using the `kextract` subproject.

## Scope

This document covers the `kextract` pipeline:

1. Build a self-contained `kextract` runtime image.
2. Run `kextract` on one or more headers.
3. Validate the generation.

## Prerequisites

- JDK 25 installed.
- `local.properties` must define:
  - `jdk_home=/absolute/path/to/jdk25`
- Optional: `llvm_home=/absolute/path/to/llvm` if you do not want auto-download.

If `llvm_home` is not provided, Gradle downloads LLVM automatically (`downloadLLVM` task in `kextract/build.gradle.kts`).

## What Happens Internally

`kextract/build.gradle.kts` wires the generation in three main tasks:

1. `prepareInputs`
   - Ensures LLVM is available.
   - Copies `libclang` (+ `libLLVM.*`) and clang builtin headers into a staging dir.
2. `createKextractImage`
   - Builds a minimal runtime with `jlink`.
   - Packs `org.graphiks.kextract.jar`, dependencies, native libs, and launch scripts.
   - Output image: `kextract/build/kextract/`
3. `verify`
   - Runs:
   - `kextract test.h --output kextract/build/integration_test`

The generated launcher scripts are:

- `kextract/build/kextract/bin/kextract` (Unix)
- `kextract/build/kextract/bin/kextract.bat` (Windows)
- `kextract/build/kextract/bin/kextract.ps1` (PowerShell)

## Regenerate Bindings (Standard Flow)

From repository root:

```bash
./gradlew :kextract:createKextractImage
```

Then run `kextract` on your header(s):

```bash
./kextract/build/kextract/bin/kextract path/to/header.h --output path/to/out
```

Example with include path and package:

```bash
./kextract/build/kextract/bin/kextract \
  path/to/wgpu.h \
  --output /tmp/wgpu-bindings \
  --target-package io.ygdrasil.wgpu.generated \
  -I path/to/includes
```

## Quick Validation

Run the built-in verification task:

```bash
./gradlew :kextract:verify
```

Run all example scenarios:

```bash
./gradlew :kextract:verifyExamples
```

## Useful CLI Flags

Common options from `KextractCommand`:

- `--output <dir>`: output directory for generated Kotlin files.
- `--target-package <pkg>`: package name for generated classes.
- `-I <path>`: additional clang include path (repeatable).
- `-DNAME[=VALUE]`: clang define (repeatable).
- `--clang-arg <arg>`: raw clang arg passthrough (repeatable).
- `--multiplatform`: emit KMP-oriented layout (`commonMain`, `jvmMain`, etc.).

ObjC-specific options:

- `--objc`: enable Objective-C mode (`-x objective-c -fobjc-arc`, macOS only).
- `--include-objc-class <Name>`: strongly recommended for framework headers to avoid huge output.

## Troubleshooting

- `jdk_home not found`
  - Fix `local.properties` and ensure path exists.
- `LLVM not found ... Run './gradlew downloadLLVM' first`
  - Run `./gradlew :kextract:downloadLLVM` or set `-Pllvm_home=/path/to/llvm`.
- Missing builtin C headers (`stdarg.h`, `stddef.h`, ...)
  - Rebuild image (`:kextract:createKextractImage`) so `conf/kextract` is packaged.
- `libclang` load errors at runtime
  - Check that `kextract/build/kextract/lib` contains `libclang` and `libLLVM`.

## Related Files

- Build wiring: `kextract/build.gradle.kts`
- CLI parser: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractCommand.kt`
- Generation pipeline: `kextract/src/main/kotlin/org/graphiks/kextract/pipeline/KextractTool.kt`
