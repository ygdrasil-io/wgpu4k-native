# Kextract Non-Android Review Remediation Design

**Date:** 2026-07-14  
**Status:** Approved design  
**Related PR:** `klang-toolkit/kextract#41`

## Goal

Correct the confirmed non-Android generator defects from the independent PR review: runtime and
synthetic-name collisions, invalid JVM layouts for under-aligned records, and incorrect ABI
carriers for wide enums. Generated Common, JVM, and Kotlin/Native sources must remain deterministic
and agree on their public declarations.

## Scope Boundary

Android/JNA remains unchanged and known to be incomplete. This remediation does not implement or
test Android downcalls, callback registration, or `_Bool` record conversion, and it does not remove
or feature-gate the Android target. Mechanically regenerated Android output is acceptable only when
it results from a shared generator change; no Android behavior is added to the acceptance criteria.

## Name Planning

KMP generation will build an immutable name plan before any platform emitter runs. The plan covers:

- every generated C classifier and top-level declaration;
- imported KFFI/runtime classifiers used by generated source;
- callback-generated classifiers and helpers;
- per-record C fields;
- synthetic members including `handler`, `Companion`, `ByReference`, `layout`, `allocate`,
  `allocateArray`, and `invoke`.

Valid C classifier names are preserved when possible. If a C classifier conflicts with a runtime
import such as `NativeAddress`, the runtime import receives a deterministic Kotlin alias and all
builders use that alias. If a C field conflicts with a mandatory synthetic member such as
`handler`, the C field receives the deterministic mangled name because the JVM implementation must
still satisfy `CStructure.handler`.

The plan stores names by declaration identity rather than recomputing them from source spelling.
Common, JVM, and Native builders consume the same allocation. Generation fails during preflight if
a declaration cannot receive a valid Kotlin identifier; no platform source is emitted partially.

## Shared ABI Model

The fixed-width carrier knowledge already used by callback generation will become a shared KMP C
ABI model. Callback, direct-function, and record-field emitters derive all of the following from
the same canonical C type:

- foreign-memory layout (`JAVA_BYTE`, `JAVA_SHORT`, `JAVA_INT`, or `JAVA_LONG`);
- JVM carrier (`Byte`, `Short`, `Int`, or `Long`);
- signed or unsigned Kotlin API type;
- conversion from the native carrier;
- conversion to the native carrier.

An enum's carrier is derived from `ClangEnumType`, never from the generated Kotlin enum/value-class
name. Signed and unsigned 8-, 16-, 32-, and 64-bit enums are supported consistently in parameters,
returns, and fields. Unsupported widths fail in preflight with a diagnostic naming the declaration
and width.

## JVM Record Layouts

JVM aggregate generation treats Clang metadata as authoritative:

- `ClangOffsetOf` supplies each field offset;
- `ClangAlignOf` supplies natural field alignment and effective record alignment;
- the Clang record size supplies final padding and `byteSize` validation.

For a member whose effective alignment is lower than the natural FFM layout alignment, the emitted
member layout uses `withByteAlignment(...)` before `withName(...)`. Effective member alignment is
the strictest value compatible with its natural alignment, the containing record alignment, and
its Clang offset. Explicit sequence layouts and nested aggregate layouts receive the same
treatment. Padding is inserted from the difference between consecutive Clang offsets, not inferred
from natural JVM alignment.

Preflight rejects negative, non-byte-addressable, overlapping, or out-of-bounds offsets for normal
struct fields. Union fields continue to share offset zero. Generated layouts must initialize
without `IllegalArgumentException` and expose the same size, alignment, and field offsets as Clang.

## Transaction and Error Handling

The generator completes declaration analysis, name allocation, ABI resolution, and layout
validation before constructing output files. A failure in any phase returns a focused diagnostic
and leaves the output model unchanged. Emitters consume validated immutable data and do not allocate
new public names or guess ABI carriers locally.

## Test Strategy

Each defect is fixed with a red-green TDD cycle:

- Name fixtures cover a C `NativeAddress` classifier, a `handler` field, simultaneous collisions,
  Kotlin keywords, and deterministic repeated generation. Common/JVM/Native outputs must compile
  with matching names.
- Layout fixtures cover a packed primitive, packed array, nested aggregate, explicit padding, and
  trailing padding. JVM tests instantiate the generated `MemoryLayout` and compare `byteSize`,
  `byteAlignment`, and offsets with Clang metadata.
- Enum fixtures cover signed and unsigned 8-, 16-, 32-, and 64-bit fields, parameters, and returns.
  Tests assert descriptors and conversions and invoke generated JVM bindings against a native
  fixture where practical.
- The complete Kextract suite and deterministic-generation checks run after targeted tests.

## Acceptance Criteria

- Runtime import collisions and mandatory synthetic member collisions produce compilable,
  deterministic Common/JVM/Native source from one shared name plan.
- JVM packed and explicitly under-aligned records initialize successfully and match Clang size,
  alignment, and offsets.
- Direct JVM functions and fields use the correct carrier for every supported enum width without
  truncation or `WrongMethodTypeException`.
- Existing callback ABI tests remain green after adopting the shared model.
- No intentional Android/JNA behavior change or Android-specific fix is included.
