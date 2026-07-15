@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.posix.mkdir

actual fun ensureHeadlessOutputDirectory() {
    mkdir("build", 511.convert())
    mkdir("build/headless", 511.convert())
}
