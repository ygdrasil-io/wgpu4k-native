package org.graphiks.kffi

// Runtime counterpart of the kffi.version build chain (kffi/build.gradle.kts):
// equals the published version without the "-SNAPSHOT" suffix; bump alongside it.
public object Kffi {
    public const val VERSION: String = "1.0.0"
}
