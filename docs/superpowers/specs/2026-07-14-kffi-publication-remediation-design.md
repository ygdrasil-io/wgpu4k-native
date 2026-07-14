# KFFI Publication Remediation Design

**Date:** 2026-07-14  
**Status:** Approved design  
**Related PR:** `ygdrasil-io/wgpu4k-native#4`

## Goal

Make every published `wgpu4k-native` variant externally resolvable after `kffi` became an
`api(project(":kffi"))` dependency. The release must publish matching KMP variants of `kffi` with
the same group and version before a consumer resolves `wgpu4k-native`.

## Scope

- Apply the repository's existing `publish` convention to `kffi`.
- Keep `group`, `version`, signing, repositories, and Central Portal behavior aligned with
  `wgpu4k-native` through the shared convention plugin.
- Ensure the root `publish` and snapshot/release workflows include both projects.
- Add an isolated Maven-local-repository smoke test that publishes both projects and compiles a
  consumer depending only on `wgpu4k-native`.
- Verify that the consumer resolves the matching `kffi` target selected by Gradle metadata.

Android/JNA behavior is explicitly outside this remediation. Existing Android targets and sources
remain unchanged; this design does not remove, gate, implement, or test their native downcalls.

## Architecture

`kffi/build.gradle.kts` will use the same `publish` convention plugin already applied by
`wgpu4k-native/build.gradle.kts`. The convention remains the single source for Maven publications,
signing, repository selection, and project coordinates. No second publication implementation will
be introduced.

A verification task will publish `kffi` and `wgpu4k-native` into a disposable Maven repository
under `build/`. A minimal external Gradle build will declare only the published
`wgpu4k-native` coordinate and compile a JVM source that references a public type backed by `kffi`.
The consumer must not use project substitution, composite builds, or direct `kffi` dependencies.

## Failure Behavior

The smoke test fails if:

- any required `kffi` or `wgpu4k-native` publication is absent;
- module metadata points at a different version of `kffi`;
- Gradle resolves the source project instead of the temporary repository;
- the external JVM consumer cannot compile.

The test must use a clean, unique repository directory so artefacts from `~/.m2` or a previous run
cannot mask an incomplete publication.

## Test Strategy

The change follows TDD:

1. A Gradle verification test first demonstrates that `:kffi:publish` is unavailable or that the
   isolated consumer cannot resolve `kffi-jvm`.
2. Applying the existing convention creates the expected KMP publication tasks.
3. The isolated publication-and-consumer smoke test passes.
4. Existing JVM and publication task-graph checks remain green.

## Acceptance Criteria

- `:kffi:publish --dry-run` succeeds and schedules KMP publications.
- Publishing both projects to the disposable repository produces matching group/version metadata.
- An isolated consumer declaring only `wgpu4k-native` resolves `kffi-jvm` from that repository and
  compiles.
- Root snapshot and release commands continue to use `./gradlew publish` without a special
  project-specific command.
- No Android/JNA production or test source changes are included.

