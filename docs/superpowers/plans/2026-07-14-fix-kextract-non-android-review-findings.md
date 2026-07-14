# Fix Kextract Non-Android Review Findings Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Make Kextract generate deterministic, compilable Common/JVM/Native bindings for runtime-name collisions, under-aligned records, and signed/unsigned enum carriers from 8 through 64 bits.

**Architecture:** KMP generation performs one immutable name-planning pass before constructing callback models or platform builders. JVM record layouts are represented by a validated Clang-derived model, and the callback ABI model is generalized into the single carrier model used by callbacks, direct functions, and record fields.

**Tech Stack:** Kotlin 2.3.21, JDK 25 Foreign Function & Memory API, libclang 22.1.6, Kotest 6.1.11, embedded `K2JVMCompiler`, Gradle.

## Global Constraints

- Work in `/Users/chaos/.codex/worktrees/8a9c/wgpu4k-native/kextract` on `feature/kmp-refresh-v0.0.3`.
- Prefix every shell command with `rtk`.
- Follow red-green TDD for every production change and record the failing reason before implementation.
- Android/JNA remains unchanged and known to be incomplete: do not implement Android downcalls, callback registration, or `_Bool` conversion; do not remove or feature-gate Android.
- Mechanically changed Android output is acceptable only when a shared generator change requires it; Android behavior is not an acceptance criterion.
- Preserve valid public C declaration names when possible; alias runtime imports before renaming a C classifier.
- A C field conflicting with a mandatory generated member is deterministically mangled because `CStructure.handler` and other mandatory members cannot be renamed.
- Common, JVM, and Native builders must consume the same declaration-identity-based name plan.
- Complete name allocation, ABI resolution, and layout validation before any `KotlinSourceFile` is returned.
- Enum layout, carrier, signedness, and conversions must all derive from `ClangEnumType`.
- JVM record size, alignment, and offsets must match `ClangSizeOf`, `ClangAlignOf`, and `ClangOffsetOf`.
- Do not hand-edit generated `wgpu4k-native` bindings in this plan; regeneration belongs to the root-repository plan.

---

## File Structure

- `src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt`: immutable runtime-import, declaration, and per-record member names allocated before emission.
- `src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`: constructs the plan and passes it to every KMP component.
- `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KmpTypeMapper.kt`: renders planned runtime/declaration names instead of hard-coded identifiers.
- `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmp*Builder.kt`: consumes planned imports, classifiers, and members.
- `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/*.kt`: consumes planned runtime aliases and the shared ABI model.
- `src/main/kotlin/org/graphiks/kextract/kotlin/abi/KotlinKmpCAbiType.kt`: canonical scalar, address, and aggregate ABI carriers.
- `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinJvmRecordLayout.kt`: validates and renders Clang-derived JVM layouts.
- `src/test/kotlin/org/graphiks/kextract/integration/KmpJvmCompilationSupport.kt`: compiles generated Common/JVM source with stable KFFI test stubs.
- Focused integration tests: `KmpNamePlanIntegrationTest.kt`, `KmpJvmPackedLayoutTest.kt`, and `KmpJvmEnumAbiTest.kt`.

---

### Task 1: Build and consume one immutable KMP name plan

**Files:**
- Create: `src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpNamePlan.kt`
- Create: `src/test/kotlin/org/graphiks/kextract/integration/KmpJvmCompilationSupport.kt`
- Create: `src/test/kotlin/org/graphiks/kextract/integration/KmpNamePlanIntegrationTest.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/KotlinGenerator.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/KotlinKmpExternalNameCollector.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KmpTypeMapper.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpNativeBuilder.kt`
- Modify only for shared aliases: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpAndroidBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackBindingEmitter.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCommonEmitter.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackJvmEmitter.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackNativeEmitter.kt`

**Interfaces:**
- Consumes: the fully mangled `Declaration.Scoped` tree and `ValidatedCallbackBindings` produced by the existing pipeline.
- Produces:

```kotlin
internal enum class KotlinKmpSourceSet {
    COMMON,
    JVM,
    NATIVE,
    ANDROID,
}

internal enum class KotlinKmpRuntimeSymbol(
    val qualifiedName: String,
    val sourceSets: Set<KotlinKmpSourceSet>,
    val preferredName: String = qualifiedName.substringAfterLast('.'),
)

internal class KotlinKmpNamePlan private constructor(
    val topLevelNames: Set<String>,
    val renderedRuntimeNames: Set<String>,
    private val runtimeNames: Map<KotlinKmpRuntimeSymbol, String>,
    private val declarationNames: java.util.IdentityHashMap<Declaration, String>,
    private val memberNames: java.util.IdentityHashMap<Declaration.Variable, String>,
) {
    fun runtime(symbol: KotlinKmpRuntimeSymbol): String
    fun importLine(symbol: KotlinKmpRuntimeSymbol): String
    fun declaration(declaration: Declaration): String
    fun member(field: Declaration.Variable): String

    companion object {
        fun create(
            scoped: Declaration.Scoped,
            callbackBindings: ValidatedCallbackBindings,
        ): KotlinKmpNamePlan
    }
}
```

- `KmpTypeMapper` receives `KotlinKmpNamePlan` and uses `plan.runtime(NATIVE_ADDRESS)`, `plan.runtime(C_STRING)`, and `plan.runtime(ARRAY_HOLDER)` in every returned type string.
- All four builders receive the same `KotlinKmpNamePlan`; they do not allocate public identifiers locally.

- [ ] **Step 1: Extract a reusable generated-JVM compilation fixture before adding new behavior**

Move the existing embedded-compiler setup from `KmpJvmDirectCallbackTransactionTest.kt` into the following test-only API, keeping its KFFI Common/JVM stubs and callback counters intact:

```kotlin
internal data class GeneratedKmpSources(
    val common: String,
    val jvm: String,
    val native: String,
)

internal fun generateKmpSources(
    header: String,
    packageName: String = "sample.bindings",
    callbackBindings: CallbackBindingsConfig? = null,
): GeneratedKmpSources

internal fun compileAndInvokeGeneratedKmpJvm(
    generated: GeneratedKmpSources,
    probePackage: String,
    probeSource: String,
    facadeClassName: String,
    methodName: String,
): Any?
```

`compileAndInvokeGeneratedKmpJvm` must write `common`, `jvm`, the existing KFFI Common/JVM stubs, and the caller's probe to a unique temporary directory; invoke `K2JVMCompiler` with these exact options; require `ExitCode.OK`; invoke the named static method through a child `URLClassLoader`; close the loader; and delete the directory in `finally`:

```kotlin
K2JVMCompiler().exec(
    System.err,
    "-no-stdlib",
    "-no-reflect",
    "-Xmulti-platform",
    "-Xcommon-sources=$common,$kffiCommon",
    "-classpath", System.getProperty("java.class.path"),
    "-d", output.toString(),
    common.toString(), jvm.toString(),
    kffiCommon.toString(), kffiJvm.toString(), probe.toString(),
) shouldBe ExitCode.OK
```

Refactor `KmpJvmDirectCallbackTransactionTest` to call this API and retain its exact expected result `listOf(1, 0, 0)`.

- [ ] **Step 2: Verify the extracted fixture without changing production code**

Run:

```bash
rtk ./gradlew test --tests 'org.graphiks.kextract.integration.KmpJvmDirectCallbackTransactionTest'
```

Expected: PASS with the original transaction assertion unchanged.

- [ ] **Step 3: Write failing collision and determinism tests**

Generate this exact header twice:

```c
typedef struct NativeAddress {
    int value;
} NativeAddress;

typedef struct CollisionRecord {
    int handler;
    int layout;
    NativeAddress address;
} CollisionRecord;
```

The test must assert:

```kotlin
first shouldBe second
first.common shouldContain "import io.ygdrasil.kffi.NativeAddress as KffiNativeAddress"
first.common shouldContain "expect interface NativeAddress"
first.common shouldContain "var handler_2: Int"
first.common shouldContain "var layout_2: Int"
first.common shouldContain "val handler: KffiNativeAddress"
first.jvm shouldContain "actual var handler_2: Int"
first.native shouldContain "actual var handler_2: Int"
```

Add a second table-driven case that constructs one valid C struct classifier for every
`KotlinKmpRuntimeSymbol.preferredName` that is a valid C identifier. For each symbol, require the
platform source that uses it to contain `import <qualifiedName> as <allocatedAlias>` and not contain
the unaliased import line. This prevents the test from covering only `NativeAddress` while leaving
other runtime classifiers shadowable.

Add a JVM probe that constructs no native object but forces the generated declarations and aliases through the Kotlin compiler:

```kotlin
package sample.probe

import sample.bindings.CollisionRecord
import sample.bindings.NativeAddress

fun verifyNames(): Array<Class<*>> = arrayOf(
    CollisionRecord::class.java,
    NativeAddress::class.java,
)
```

- [ ] **Step 4: Run the new test and record the RED failure**

Run:

```bash
rtk ./gradlew test --tests 'org.graphiks.kextract.integration.KmpNamePlanIntegrationTest'
```

Expected: FAIL because `NativeAddress` is both imported and declared and because `CollisionRecord` emits duplicate `handler`/`layout` members.

- [ ] **Step 5: Implement deterministic preflight allocation**

`KotlinKmpNamePlan.create` must perform these operations in order:

```kotlin
val cTopLevelNames = KotlinKmpExternalNameCollector.collect(scoped, callbackBindings)
val runtimeAllocator = KotlinIdentifierAllocator(cTopLevelNames)
val runtimeNames = KotlinKmpRuntimeSymbol.entries.associateWith { symbol ->
    if (symbol.preferredName !in cTopLevelNames) {
        runtimeAllocator.allocate(symbol.preferredName, "KffiRuntime")
    } else {
        runtimeAllocator.allocate("Kffi${symbol.preferredName}", "KffiRuntime")
    }
}
```

For every non-skipped record, reserve the mandatory instance names before allocating C fields:

```kotlin
private val RECORD_RESERVED_MEMBERS = setOf(
    "handler",
    "Companion",
    "ByReference",
    "layout",
    "allocate",
    "allocateArray",
    "invoke",
)

val memberAllocator = KotlinIdentifierAllocator(RECORD_RESERVED_MEMBERS)
record.members()
    .filterIsInstance<Declaration.Variable>()
    .filterNot(Skip::isPresent)
    .forEach { field ->
        memberNames[field] = memberAllocator.allocate(field.name(), "field")
    }
```

Emit an alias only when planned and keep the normal import otherwise:

```kotlin
fun importLine(symbol: KotlinKmpRuntimeSymbol): String {
    val rendered = runtime(symbol)
    return if (rendered == symbol.preferredName) {
        "import ${symbol.qualifiedName}"
    } else {
        "import ${symbol.qualifiedName} as $rendered"
    }
}
```

Replace every hard-coded generated use of runtime symbols and record field spellings with the planned value. Raw Clang lookups such as `groupElement(...)`, Native struct access, and Android/JNA field access must continue to use the original C field spelling when they address native storage; only the Kotlin property/VarHandle identifier uses `plan.member(field)`.

`KotlinKmpRuntimeSymbol` must enumerate every unqualified import referenced by Common/JVM/Native/Android builders and callback emitters, including KFFI callback/runtime types, `NativeAddress`, `CString`, `ArrayHolder`, `MemoryAllocator`, `CStructure`, JVM FFM/method-handle types, and Kotlin/Native interop types. Replace wildcard imports with explicit planned imports wherever a C classifier could otherwise shadow an imported type.

Build this plan in `KotlinGenerator.generate` before callback names. Seed the callback allocator with `plan.topLevelNames + plan.renderedRuntimeNames`, then pass the same plan to all builders and callback emitters.

- [ ] **Step 6: Run collision, callback-name, and transaction tests GREEN**

Run:

```bash
rtk ./gradlew test \
  --tests 'org.graphiks.kextract.integration.KmpNamePlanIntegrationTest' \
  --tests 'org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest' \
  --tests 'org.graphiks.kextract.integration.KmpJvmDirectCallbackTransactionTest'
```

Expected: PASS; repeated output is byte-identical and the compilation probe succeeds.

- [ ] **Step 7: Commit the name-plan task**

```bash
rtk git add src/main/kotlin/org/graphiks/kextract/kotlin src/test/kotlin/org/graphiks/kextract/integration
rtk git commit -m "fix: allocate KMP names before emission"
```

---

### Task 2: Render JVM records from validated Clang offsets and alignments

**Files:**
- Create: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinJvmRecordLayout.kt`
- Create: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinJvmRecordLayoutPlan.kt`
- Create: `src/test/kotlin/org/graphiks/kextract/integration/KmpJvmPackedLayoutTest.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/pipeline/LayoutUtils.kt`

**Interfaces:**
- Consumes: record kind plus `ClangSizeOf`, `ClangAlignOf`, and `ClangOffsetOf` attributes already attached by `TreeMaker`.
- Produces:

```kotlin
internal data class KotlinJvmRecordMemberLayout(
    val field: Declaration.Variable,
    val kotlinName: String,
    val cName: String,
    val offsetBytes: Long,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val layoutExpression: String,
)

internal data class KotlinJvmRecordLayout(
    val declaration: Declaration.Scoped,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val members: List<KotlinJvmRecordMemberLayout>,
) {
    fun render(builder: SourceBuilder)

}

internal class KotlinJvmRecordLayoutPlan private constructor(
    private val layouts: java.util.IdentityHashMap<Declaration.Scoped, KotlinJvmRecordLayout>,
) {
    operator fun get(declaration: Declaration.Scoped): KotlinJvmRecordLayout

    companion object {
        fun create(
            scoped: Declaration.Scoped,
            names: KotlinKmpNamePlan,
        ): KotlinJvmRecordLayoutPlan
    }
}
```

- `LayoutUtils.layoutString(type, byteAlignment)` returns the existing layout expression followed by `.withByteAlignment(byteAlignment)` for every positive, power-of-two effective alignment.

- [ ] **Step 1: Write the packed-layout failing test**

Use this exact header:

```c
typedef struct __attribute__((packed)) PackedLeaf {
    char tag;
    int value;
} PackedLeaf;

typedef struct __attribute__((packed)) PackedRecord {
    char prefix;
    int values[2];
    PackedLeaf leaf;
    short tail;
} PackedRecord;
```

Compile the generated Common/JVM sources with `KmpJvmCompilationSupport` and invoke:

```kotlin
package sample.probe

import java.lang.foreign.MemoryLayout.PathElement.groupElement
import sample.bindings.PackedLeaf
import sample.bindings.PackedRecord

fun inspectPackedLayouts(): LongArray = longArrayOf(
    PackedLeaf.layout.byteSize(),
    PackedLeaf.layout.byteAlignment(),
    PackedLeaf.layout.byteOffset(groupElement("tag")),
    PackedLeaf.layout.byteOffset(groupElement("value")),
    PackedRecord.layout.byteSize(),
    PackedRecord.layout.byteAlignment(),
    PackedRecord.layout.byteOffset(groupElement("prefix")),
    PackedRecord.layout.byteOffset(groupElement("values")),
    PackedRecord.layout.byteOffset(groupElement("leaf")),
    PackedRecord.layout.byteOffset(groupElement("tail")),
)
```

Assert the exact result:

```kotlin
result.toList() shouldBe listOf(5L, 1L, 0L, 1L, 16L, 1L, 0L, 1L, 9L, 14L)
```

- [ ] **Step 2: Run the packed-layout test RED**

Run:

```bash
rtk ./gradlew test --tests 'org.graphiks.kextract.integration.KmpJvmPackedLayoutTest'
```

Expected: FAIL while initializing `PackedLeaf.layout` with `IllegalArgumentException: Invalid alignment constraint for member layout`.

- [ ] **Step 3: Implement metadata validation and rendering**

Convert Clang bit metrics with one checked helper:

```kotlin
private fun bitsToBytes(metric: String, owner: String, bits: Long): Long {
    require(bits >= 0L) { "$owner has negative $metric: $bits bits" }
    require(bits % 8L == 0L) { "$owner has non-byte-addressable $metric: $bits bits" }
    return bits / 8L
}

private fun requireAlignment(owner: String, bytes: Long): Long {
    require(bytes > 0L && bytes.countOneBits() == 1) {
        "$owner has invalid byte alignment: $bytes"
    }
    return bytes
}
```

`ClangAlignOf` on a field currently records the field type's natural alignment, not the effective
alignment imposed by a packed parent. Derive the effective member alignment from the natural field
alignment, record alignment, and byte offset:

```kotlin
private fun effectiveMemberAlignment(
    naturalFieldAlignment: Long,
    recordAlignment: Long,
    offsetBytes: Long,
): Long {
    val offsetAlignment = if (offsetBytes == 0L) {
        Long.MAX_VALUE
    } else {
        java.lang.Long.lowestOneBit(offsetBytes)
    }
    return minOf(naturalFieldAlignment, recordAlignment, offsetAlignment)
}
```

Use this effective value for `withByteAlignment(...)`; do not pass the natural field alignment
directly for packed or explicitly under-aligned records.

For structs, require monotonically non-overlapping members and bounds:

```kotlin
require(member.offsetBytes >= previousEnd) {
    "${declaration.name()}.${member.cName} overlaps the preceding field"
}
require(member.offsetBytes + member.sizeBytes <= sizeBytes) {
    "${declaration.name()}.${member.cName} exceeds the record size"
}
```

For unions, require offset zero and render `MemoryLayout.unionLayout`. For structs, insert `MemoryLayout.paddingLayout(gap)` from each validated offset. Render each field as:

```kotlin
${LayoutUtils.layoutString(field.type(), member.alignmentBytes)}.withName("${member.cName}")
```

Append final padding to `sizeBytes`, then apply the record alignment and name:

```kotlin
).withByteAlignment($alignmentBytes).withName("${names.declaration(declaration)}")
```

Build `KotlinJvmRecordLayoutPlan` in `KotlinGenerator.generate` immediately after the name plan and before constructing any platform builder. Pass it to `KotlinKmpJvmBuilder`; `emitGroupLayout` retrieves and renders the prevalidated record and removes its local offset arithmetic.

- [ ] **Step 4: Run packed and existing union tests GREEN**

Run:

```bash
rtk ./gradlew test \
  --tests 'org.graphiks.kextract.integration.KmpJvmPackedLayoutTest' \
  --tests 'org.graphiks.kextract.integration.KmpJvmFfmAbiTest' \
  --tests 'org.graphiks.kextract.integration.GeneratorIntegrationTest'
```

Expected: PASS; packed layouts initialize and existing unions still overlap at offset zero.

- [ ] **Step 5: Commit the layout task**

```bash
rtk git add src/main/kotlin/org/graphiks/kextract/kotlin/builders src/main/kotlin/org/graphiks/kextract/pipeline/LayoutUtils.kt src/test/kotlin/org/graphiks/kextract/integration/KmpJvmPackedLayoutTest.kt
rtk git commit -m "fix: preserve Clang record alignment in JVM layouts"
```

---

### Task 3: Use one ABI carrier model for callbacks, direct functions, and enum fields

**Files:**
- Create: `src/main/kotlin/org/graphiks/kextract/kotlin/abi/KotlinKmpCAbiType.kt`
- Create: `src/main/kotlin/org/graphiks/kextract/kotlin/abi/KotlinKmpAbiIndex.kt`
- Create: `src/test/kotlin/org/graphiks/kextract/integration/KmpJvmEnumAbiTest.kt`
- Delete after migration: `src/main/kotlin/org/graphiks/kextract/kotlin/callbacks/KotlinCallbackCAbiType.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KmpTypeMapper.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpCommonBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinKmpJvmBuilder.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/kotlin/builders/KotlinJvmRecordLayoutPlan.kt`
- Modify: `src/main/kotlin/org/graphiks/kextract/pipeline/LayoutUtils.kt`
- Modify: all callback files importing `KotlinCallbackCAbiType`
- Modify: callback ABI unit/integration tests importing `KotlinCallbackCAbiType`

**Interfaces:**
- Consumes: canonical `Type`, including the underlying type attached through `ClangEnumType`.
- Produces:

```kotlin
internal enum class KotlinKmpAbiContext {
    CALLBACK,
    DIRECT,
    FIELD,
}

internal sealed interface KotlinKmpCAbiType {
    val jvmLayout: String
    val jvmCarrier: String
    val nativeCarrier: String

    data class Scalar(
        val kind: Kind,
        val unsigned: Boolean,
    ) : KotlinKmpCAbiType {
        enum class Kind(
            val jvmLayout: String,
            val jvmCarrier: String,
            val signedKotlinType: String,
            val unsignedKotlinType: String,
        ) {
            BOOL("ValueLayout.JAVA_BOOLEAN", "Boolean", "Boolean", "Boolean"),
            I8("ValueLayout.JAVA_BYTE", "Byte", "Byte", "UByte"),
            I16("ValueLayout.JAVA_SHORT", "Short", "Short", "UShort"),
            I32("ValueLayout.JAVA_INT", "Int", "Int", "UInt"),
            I64("ValueLayout.JAVA_LONG", "Long", "Long", "ULong"),
            CHAR16("ValueLayout.JAVA_CHAR", "Char", "Char", "UShort"),
            F32("ValueLayout.JAVA_FLOAT", "Float", "Float", "Float"),
            F64("ValueLayout.JAVA_DOUBLE", "Double", "Double", "Double"),
        }

        val kotlinType: String
            get() = if (unsigned) kind.unsignedKotlinType else kind.signedKotlinType

        fun fromJvmCarrier(expression: String): String
        fun toJvmCarrier(expression: String): String
    }

    companion object {
        fun from(type: Type, context: KotlinKmpAbiContext): KotlinKmpCAbiType
    }
}

internal class KotlinKmpAbiIndex private constructor(
    private val enums: java.util.IdentityHashMap<Declaration.Scoped, KotlinKmpCAbiType.Scalar>,
) {
    fun enum(declaration: Declaration.Scoped): KotlinKmpCAbiType.Scalar

    companion object {
        fun create(scoped: Declaration.Scoped): KotlinKmpAbiIndex
    }
}
```

Add this exact resolver to `KmpTypeMapper` so every direct-function and field path reaches the same
prevalidated enum declaration:

```kotlin
fun enumDeclaration(type: Type): Declaration.Scoped = when {
    type is Type.Declared && type.isEnum() -> type.tree()
    type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> enumDeclaration(type.type())
    else -> error("Expected enum type, found $type")
}
```

The existing `StructValue` and `Address` variants move unchanged into this shared package. `CALLBACK` retains the existing explicit rejection of target-variable `long` and `long double`; direct non-enum behavior is not broadened by this task.

- [ ] **Step 1: Write failing wide-enum generation and invocation tests**

Use fixed underlying C enum types:

```c
typedef enum Signed8 : signed char { Signed8_Min = -1, Signed8_Max = 1 } Signed8;
typedef enum Unsigned16 : unsigned short { Unsigned16_Zero = 0, Unsigned16_Max = 65535 } Unsigned16;
typedef enum Signed32 : int { Signed32_Min = -1, Signed32_Max = 1 } Signed32;
typedef enum Unsigned64 : unsigned long long {
    Unsigned64_Zero = 0,
    Unsigned64_Max = 18446744073709551615ULL
} Unsigned64;

typedef struct EnumRecord {
    Signed8 small;
    Unsigned16 medium;
    Signed32 normal;
    Unsigned64 wide;
} EnumRecord;

Unsigned64 roundTripWide(Unsigned64 value);
```

Assert the JVM source contains:

```kotlin
source shouldContain "typealias Unsigned64 = ULong"
source shouldContain "FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)"
source shouldContain "roundTripWide_HANDLE.invokeExact(value.toLong()) as Long"
source shouldContain ".toULong()"
source shouldContain "actual var small: Signed8"
source shouldContain "actual var medium: Unsigned16"
source shouldContain "actual var normal: Signed32"
source shouldContain "actual var wide: Unsigned64"
source shouldNotContain "roundTripWide_HANDLE.invokeExact(value.toInt())"
source shouldNotContain "roundTripWide_HANDLE.invokeExact(value) as Int"
```

Compile the generated code with the shared fixture. The KFFI JVM stub must expose a test symbol registry:

```kotlin
object TestNativeSymbols {
    private val symbols = mutableMapOf<String, MemorySegment>()
    fun register(name: String, address: MemorySegment) { symbols[name] = address }
    fun find(name: String): MemorySegment = symbols[name] ?: error("Missing test symbol: $name")
}

fun findOrThrow(name: String): MemorySegment = TestNativeSymbols.find(name)
```

The probe creates an upcall stub with descriptor `FunctionDescriptor.of(JAVA_LONG, JAVA_LONG)`, registers it as `roundTripWide`, invokes the generated wrapper with `ULong.MAX_VALUE`, and returns the result. Assert the result is `ULong.MAX_VALUE` and no `WrongMethodTypeException` is thrown.

- [ ] **Step 2: Run enum tests RED**

Run:

```bash
rtk ./gradlew test --tests 'org.graphiks.kextract.integration.KmpJvmEnumAbiTest'
```

Expected: FAIL because the descriptor is `JAVA_LONG` while the generated argument and return casts use `Int`.

- [ ] **Step 3: Move the callback ABI model into the shared package**

Move the current scalar/address/struct normalization into `KotlinKmpCAbiType`. All callback call sites must use:

```kotlin
KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.CALLBACK)
```

and preserve the exact current diagnostics for unsupported callback `long` and `long double`.

For declared enums, always unwrap through:

```kotlin
val underlying = requireNotNull(ClangEnumType.get(type.tree())) {
    "Enum ${type.tree().name()} has no Clang underlying type"
}
normalize(underlying, context, unsigned = false)
```

Build `KotlinKmpAbiIndex` in `KotlinGenerator.generate` immediately after the name plan and before
the record-layout plan, any builder, or any callback model. It visits every non-skipped enum,
resolves its scalar once, and fails before source construction if the enum has no supported
carrier. Pass the index to Common/JVM/Native builders and to `KotlinJvmRecordLayoutPlan`, so enum
member layouts also use the prevalidated scalar.

Change the final layout-plan factory signature during this task to:

```kotlin
fun create(
    scoped: Declaration.Scoped,
    names: KotlinKmpNamePlan,
    abiIndex: KotlinKmpAbiIndex,
): KotlinJvmRecordLayoutPlan
```

Add a failure case for `enum TargetLong : unsigned long`: generation must fail before returning any
source with the existing target-dependent-width explanation. Fixed `long long` remains supported.

- [ ] **Step 4: Replace direct-function and field carrier guesses**

In `KotlinKmpJvmBuilder`, replace the enum branch of `rawJvmType` with the prevalidated scalar carrier:

```kotlin
typeMapper.isEnumType(type) -> abiIndex.enum(typeMapper.enumDeclaration(type)).jvmCarrier
```

Render enum arguments and returns through `Scalar.toJvmCarrier` and `Scalar.fromJvmCarrier`, rather than the `Int`-specific conditions. Update `KmpTypeMapper.canonicalKmpType` so enum fields return the scalar's `kotlinType`, allowing the existing Byte/Short/Int/Long accessor branches to use the matching carrier.

Implement conversions exactly by carrier and signedness:

```kotlin
fun Scalar.fromJvmCarrier(expression: String): String = when {
    !unsigned -> expression
    kind == Scalar.Kind.I8 -> "($expression).toUByte()"
    kind == Scalar.Kind.I16 -> "($expression).toUShort()"
    kind == Scalar.Kind.I32 -> "($expression).toUInt()"
    kind == Scalar.Kind.I64 -> "($expression).toULong()"
    else -> expression
}

fun Scalar.toJvmCarrier(expression: String): String = when {
    !unsigned -> expression
    kind == Scalar.Kind.I8 -> "$expression.toByte()"
    kind == Scalar.Kind.I16 -> "$expression.toShort()"
    kind == Scalar.Kind.I32 -> "$expression.toInt()"
    kind == Scalar.Kind.I64 -> "$expression.toLong()"
    else -> expression
}
```

Use the same shared scalar for enum typealiases and `LayoutUtils`, eliminating every direct enum assumption of `Int`.

- [ ] **Step 5: Run enum, callback ABI, layout, and name tests GREEN**

Run:

```bash
rtk ./gradlew test \
  --tests 'org.graphiks.kextract.integration.KmpJvmEnumAbiTest' \
  --tests 'org.graphiks.kextract.integration.CallbackGeneratorIntegrationTest' \
  --tests 'org.graphiks.kextract.callbacks.CallbackAnalyzerTest' \
  --tests 'org.graphiks.kextract.integration.KmpJvmPackedLayoutTest' \
  --tests 'org.graphiks.kextract.integration.KmpNamePlanIntegrationTest'
```

Expected: PASS, including the real JVM round-trip through the registered upcall stub.

- [ ] **Step 6: Run the complete Kextract suite and deterministic second generation**

Run:

```bash
rtk ./gradlew test
rtk ./gradlew test --rerun-tasks
rtk git status --short
```

Expected: both complete runs report `BUILD SUCCESSFUL`; the worktree contains only the intended task changes before commit.

- [ ] **Step 7: Commit the ABI task**

```bash
rtk git add src/main/kotlin/org/graphiks/kextract/kotlin src/main/kotlin/org/graphiks/kextract/pipeline src/test/kotlin/org/graphiks/kextract
rtk git commit -m "fix: unify KMP enum ABI carriers"
```

---

## Plan Completion Gate

Before updating the parent repository:

```bash
rtk git diff origin/master...HEAD --check
rtk ./gradlew test --rerun-tasks
rtk git status --short --branch
```

Expected: no diff-check errors, a fresh complete Kextract test pass, and a clean worktree on `feature/kmp-refresh-v0.0.3`. Push this branch before changing the parent submodule pointer.
