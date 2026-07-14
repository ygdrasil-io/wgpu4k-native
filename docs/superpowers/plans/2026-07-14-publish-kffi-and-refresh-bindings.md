# Publish KFFI and Refresh Bindings Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Publish every KFFI KMP variant required by `wgpu4k-native`, prove external Maven consumption in CI, then update the Kextract submodule and regenerate bindings from the corrected generator.

**Architecture:** The existing `publish` convention remains the only publication implementation and is applied to both library projects. A disposable Maven repository plus an independent Java consumer verifies transitive resolution without project substitution; after Kextract is green and pushed, the parent repository updates the submodule, regenerates sources once, and verifies deterministic regeneration.

**Tech Stack:** Gradle Kotlin DSL, Kotlin Multiplatform 2.3.21, Maven Publish, JDK 25, Git submodules, GitHub Actions.

## Global Constraints

- Work in `/Users/chaos/.codex/worktrees/8a9c/wgpu4k-native` on `feature/add-gradle-kffi-subproject`.
- Prefix every shell command with `rtk`.
- Preserve the existing shared `group = "io.ygdrasil"` and environment-driven `version`; `kffi` and `wgpu4k-native` must publish exactly the same version.
- Use the existing `publish` convention for Maven repositories, signing, POM metadata, and Central Portal upload; do not create a second production publishing implementation.
- The verification consumer must resolve only from a unique repository below the root `build/` directory plus public dependency repositories; it must not use Maven Local, project substitution, a composite build, or a direct `kffi` dependency.
- Android/JNA remains unchanged and known to be incomplete: do not implement, remove, or feature-gate Android, and do not add Android behavior to acceptance tests.
- Generated WebGPU files are never hand-edited. Run Kextract tests, update the submodule pointer, and regenerate through `:wgpu4k-native:generateBindingsFromHeader`.
- A mechanically changed Android generated file is allowed only when produced by the shared generator invocation.
- Do not update the Kextract submodule pointer until `feature/kmp-refresh-v0.0.3` is committed, fully tested, and pushed.

---

## File Structure

- `kffi/build.gradle.kts`: applies the existing `publish` convention.
- `buildSrc/src/main/kotlin/publish.gradle.kts`: adds one deterministic local repository used only by verification tasks.
- `build.gradle.kts`: owns repository cleanup, both publication dependencies, and the isolated consumer build.
- `gradle/publication-consumer/settings.gradle.kts`: independent consumer settings with no included parent build.
- `gradle/publication-consumer/build.gradle.kts`: resolves only the published `wgpu4k-native-jvm` variant.
- `gradle/publication-consumer/src/main/java/PublicationSmoke.java`: compiles against one type from `wgpu4k-native` and one transitively supplied by `kffi`.
- `.github/workflows/test.yml`: runs the publication smoke test once on Ubuntu.
- `kextract`: submodule pointer to the reviewed Kextract head.
- `wgpu4k-native/src/*/kotlin/io/ygdrasil/wgpu/wgpu_h*.kt`: generated outputs only.

---

### Task 1: Publish KFFI and prove isolated Maven consumption

**Files:**
- Modify: `kffi/build.gradle.kts`
- Modify: `buildSrc/src/main/kotlin/publish.gradle.kts`
- Modify: `build.gradle.kts`
- Create: `gradle/publication-consumer/settings.gradle.kts`
- Create: `gradle/publication-consumer/build.gradle.kts`
- Create: `gradle/publication-consumer/src/main/java/PublicationSmoke.java`
- Modify: `.github/workflows/test.yml`

**Interfaces:**
- Consumes: root `group`, root `version`, and all Maven publications created by the shared convention.
- Produces these Gradle tasks:

```text
:kffi:publishAllPublicationsToPublicationVerificationRepository
:wgpu4k-native:publishAllPublicationsToPublicationVerificationRepository
:cleanPublicationVerificationRepository
:verifyPublishedConsumer
```

- `verifyPublishedConsumer` publishes both projects to `build/publication-verification/repository`, then runs the independent build in `gradle/publication-consumer` with `verificationRepository` and `wgpu4kVersion` project properties.

- [ ] **Step 1: Add the isolated consumer and verification task before enabling KFFI publication**

Create `gradle/publication-consumer/settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri(providers.gradleProperty("verificationRepository").get()) }
        mavenCentral()
    }
}

rootProject.name = "wgpu4k-publication-smoke"
```

Create `gradle/publication-consumer/build.gradle.kts`:

```kotlin
plugins {
    java
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation("io.ygdrasil:wgpu4k-native-jvm:${providers.gradleProperty("wgpu4kVersion").get()}")
}
```

Create `PublicationSmoke.java`:

```java
package publication.smoke;

import io.ygdrasil.kffi.Callback;
import io.ygdrasil.wgpu.WGPULogCallback;

public final class PublicationSmoke {
    private PublicationSmoke() {}

    public static Class<?>[] publishedApi() {
        return new Class<?>[] { Callback.class, WGPULogCallback.class };
    }
}
```

In the root `build.gradle.kts`, register:

```kotlin
val publicationVerificationRepository = layout.buildDirectory
    .dir("publication-verification/repository")

val cleanPublicationVerificationRepository by tasks.registering(Delete::class) {
    delete(publicationVerificationRepository)
}

val verifyPublicationMetadata by tasks.registering {
    group = "verification"
    dependsOn(
        ":kffi:publishAllPublicationsToPublicationVerificationRepository",
        ":wgpu4k-native:publishAllPublicationsToPublicationVerificationRepository",
    )
    doLast {
        val repository = publicationVerificationRepository.get().asFile
        val coordinatePath = project.group.toString().replace('.', '/')
        val publishedVersion = project.version.toString()
        require(
            repository.resolve("$coordinatePath/kffi-jvm/$publishedVersion/kffi-jvm-$publishedVersion.module").isFile,
        ) { "Missing published kffi-jvm metadata for $publishedVersion" }
        require(
            repository.resolve(
                "$coordinatePath/wgpu4k-native-jvm/$publishedVersion/wgpu4k-native-jvm-$publishedVersion.module",
            ).isFile,
        ) { "Missing published wgpu4k-native-jvm metadata for $publishedVersion" }
    }
}

tasks.register<GradleBuild>("verifyPublishedConsumer") {
    group = "verification"
    description = "Publishes KFFI and wgpu4k-native locally, then compiles an isolated consumer."
    dependsOn(
        verifyPublicationMetadata,
    )
    dir = file("gradle/publication-consumer")
    tasks = listOf("clean", "compileJava")
    startParameter.projectProperties = mapOf(
        "verificationRepository" to publicationVerificationRepository.get().asFile.toURI().toString(),
        "wgpu4kVersion" to project.version.toString(),
    )
}
```

Capture the verification repository and configure every matching publish task:

```kotlin
val publicationVerificationRepository = publishing.repositories.maven {
    name = "PublicationVerification"
    url = rootProject.layout.buildDirectory
        .dir("publication-verification/repository")
        .get()
        .asFile
        .toURI()
}

tasks.withType<PublishToMavenRepository>().configureEach {
    if (repository == publicationVerificationRepository) {
        dependsOn(rootProject.tasks.named("cleanPublicationVerificationRepository"))
    }
}
```

This guarantees cleanup precedes every write while Gradle executes the shared delete task only once.

- [ ] **Step 2: Run the consumer verification RED**

Run:

```bash
rtk ./gradlew verifyPublishedConsumer --stacktrace
```

Expected: FAIL during task resolution because `:kffi:publishAllPublicationsToPublicationVerificationRepository` does not exist. This is the precise RED condition.

- [ ] **Step 3: Apply the existing publication convention to KFFI**

First modify the plugin block in `kffi/build.gradle.kts`:

```kotlin
plugins {
    `kotlin-multiplatform`
    publish
    com.android.library
    alias(libs.plugins.kotest)
    alias(libs.plugins.ksp)
}
```

Inside the existing `androidTarget` block, expose the same publishable variants as
`wgpu4k-native` without changing Android source behavior:

```kotlin
androidTarget {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }

    android {
        namespace = "io.ygdrasil.kffi"
        compileSdk = 36

        defaultConfig {
            minSdk = 28
        }
    }

    publishLibraryVariants("release", "debug")
}
```

Do not duplicate `publishing`, signing, POM, repository, group, or version configuration in `kffi`.

- [ ] **Step 4: Run publication task discovery and isolated consumption GREEN**

Run:

```bash
rtk ./gradlew :kffi:publish --dry-run
rtk ./gradlew publish --dry-run
rtk ./gradlew :kffi:publishToCentralPortal :wgpu4k-native:publishToCentralPortal --dry-run
rtk ./gradlew verifyPublishedConsumer --rerun-tasks --stacktrace
```

Expected: KFFI exposes its KMP and Central Portal tasks, the root lifecycle includes both projects,
and the final command publishes both modules into the unique verification repository before the
external Java consumer compiles.

- [ ] **Step 5: Assert the repository contains matching module metadata**

Extend `verifyPublicationMetadata` after both publications and before the nested build. It already requires these paths using `project.group` and `project.version` rather than hard-coded coordinates:

```text
io/ygdrasil/kffi-jvm/<version>/kffi-jvm-<version>.module
io/ygdrasil/wgpu4k-native-jvm/<version>/wgpu4k-native-jvm-<version>.module
```

Parse the `wgpu4k-native-jvm` `.module` file with `com.google.gson.JsonParser`. Flatten every variant's `dependencies`, select exactly one object whose `group` is `io.ygdrasil` and whose `module` is `kffi-jvm`, then require that `version.requires` or `version.strictly` equals `project.version.toString()`:

```kotlin
val metadataFile = publicationVerificationRepository.get().asFile.resolve(
    "${project.group.toString().replace('.', '/')}/wgpu4k-native-jvm/" +
        "${project.version}/wgpu4k-native-jvm-${project.version}.module",
)
val root = JsonParser.parseString(metadataFile.readText()).asJsonObject
val dependency = root.getAsJsonArray("variants")
    .flatMap { variant ->
        variant.asJsonObject.getAsJsonArray("dependencies")?.map { it.asJsonObject }.orEmpty()
    }
    .single { candidate ->
        candidate["group"].asString == project.group.toString() &&
            candidate["module"].asString == "kffi-jvm"
    }
val publishedDependencyVersion = dependency.getAsJsonObject("version").let { version ->
    version.get("requires")?.asString ?: version.get("strictly")?.asString
}
require(publishedDependencyVersion == project.version.toString())
```

- [ ] **Step 6: Put the smoke test in CI once, outside Android behavior**

Add this step to `.github/workflows/test.yml` after the normal build:

```yaml
      - name: Verify published JVM consumer
        if: matrix.os == 'ubuntu-latest'
        run: ./gradlew verifyPublishedConsumer --stacktrace
```

Do not add Android execution or compilation to this step.

- [ ] **Step 7: Re-run focused publication verification and existing JVM tests**

Run:

```bash
rtk ./gradlew verifyPublishedConsumer --rerun-tasks
rtk ./gradlew :kffi:jvmTest :wgpu4k-native:jvmTest
rtk git diff --check
```

Expected: all commands PASS and no whitespace errors are reported.

- [ ] **Step 8: Commit the publication task**

```bash
rtk git add kffi/build.gradle.kts buildSrc/src/main/kotlin/publish.gradle.kts build.gradle.kts gradle/publication-consumer .github/workflows/test.yml
rtk git commit -m "build: publish kffi with wgpu4k-native"
```

---

### Task 2: Update Kextract, regenerate bindings, and verify the parent repository

**Files:**
- Modify: `kextract` submodule pointer
- Regenerate only: `wgpu4k-native/src/commonMain/kotlin/io/ygdrasil/wgpu/wgpu_hCommon.kt`
- Regenerate only: `wgpu4k-native/src/jvmMain/kotlin/io/ygdrasil/wgpu/wgpu_hJvm.kt`
- Regenerate only: `wgpu4k-native/src/nativeMain/kotlin/io/ygdrasil/wgpu/wgpu_hNative.kt`
- Mechanically regenerate if changed: `wgpu4k-native/src/androidMain/kotlin/io/ygdrasil/wgpu/wgpu_hAndroid.kt`

**Interfaces:**
- Consumes: pushed Kextract branch `feature/kmp-refresh-v0.0.3` after the three commits from the Kextract remediation plan.
- Produces: a parent commit whose submodule pointer and generated outputs correspond exactly to that Kextract head.

- [ ] **Step 1: Verify the Kextract prerequisite before touching the parent**

Run:

```bash
rtk git -C kextract status --short --branch
rtk git -C kextract log -3 --oneline
rtk git -C kextract rev-parse HEAD
rtk git -C kextract rev-parse origin/feature/kmp-refresh-v0.0.3
rtk git -C kextract diff origin/master...HEAD --check
rtk ./gradlew :kextract:test --rerun-tasks
```

Expected: clean Kextract worktree; local HEAD equals the remote feature branch; diff check and the complete fresh suite pass.

- [ ] **Step 2: Record generated-source state before regeneration**

Run:

```bash
rtk git status --short
rtk git diff -- wgpu4k-native/src
```

Expected: the parent is clean before generation. If unrelated user changes exist below `wgpu4k-native/src`, stop and report the overlap rather than overwriting them.

- [ ] **Step 3: Regenerate through the configured task**

Run:

```bash
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader --rerun-tasks
rtk git status --short
rtk git diff --stat -- wgpu4k-native/src
```

Expected: only generator-driven Common/JVM/Native files and, if unavoidable, the Android generated file change. No hand edit is permitted.

- [ ] **Step 4: Compile and test the regenerated non-Android outputs**

Run:

```bash
rtk ./gradlew \
  :kffi:jvmTest \
  :wgpu4k-native:jvmTest \
  :demo:common:jvmTest \
  :wgpu4k-native:compileKotlinMacosArm64 \
  :wgpu4k-native:compileKotlinMacosX64
```

Expected: PASS. Android/JNA failures or missing implementation are not exercised by this task.

- [ ] **Step 5: Prove deterministic regeneration**

Run the same generator a second time, then the repository's freshness check:

```bash
rtk ./gradlew :wgpu4k-native:generateBindingsFromHeader --rerun-tasks
rtk ./gradlew :wgpu4k-native:verifyGeneratedBindingsClean
```

Because the intended generated changes are not committed yet, `verifyGeneratedBindingsClean` is expected to fail at this point by reporting exactly those intended files. Save their paths, stage and commit them with the submodule pointer, then rerun the task; it must pass with no tracked, staged, or untracked generated difference.

- [ ] **Step 6: Commit the submodule and generated output atomically**

```bash
rtk git add kextract wgpu4k-native/src
rtk git diff --cached --check
rtk git commit -m "chore: regenerate non-Android ABI fixes"
rtk ./gradlew :wgpu4k-native:verifyGeneratedBindingsClean
```

Expected: the commit contains the Kextract gitlink plus only generator-produced binding changes; the post-commit freshness check passes.

- [ ] **Step 7: Run the complete root-owned verification matrix**

Run:

```bash
rtk ./gradlew \
  build \
  :kextract:test \
  :kffi:jvmTest \
  :wgpu4k-native:jvmTest \
  :demo:common:jvmTest \
  :wgpu4k-native:verifyBindingGenerationConfiguration \
  :wgpu4k-native:verifyGeneratedBindingsClean \
  verifyPublishedConsumer \
  --rerun-tasks
```

Expected: `BUILD SUCCESSFUL` with all requested tasks complete. Report any host-unavailable Native target separately; do not substitute Android/JNA checks.

---

## Plan Completion Gate

Before pushing either PR:

```bash
rtk git -C kextract status --short --branch
rtk git status --short --branch
rtk git -C kextract rev-parse HEAD
rtk git ls-tree HEAD kextract
rtk git diff origin/main...HEAD --check
rtk ./gradlew verifyPublishedConsumer :wgpu4k-native:verifyGeneratedBindingsClean --rerun-tasks
```

Expected: both worktrees are clean, the parent gitlink equals the tested Kextract head, diff checks pass, external publication consumption succeeds, and regeneration is clean. Push Kextract first, then push `wgpu4k-native`, and update both draft PR descriptions with the non-Android remediation scope and test evidence.
