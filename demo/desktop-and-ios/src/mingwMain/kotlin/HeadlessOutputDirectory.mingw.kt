package io.ygdrasil.wgpu

import platform.posix.mkdir

actual fun ensureHeadlessOutputDirectory() {
    mkdir("build")
    mkdir("build/headless")
}
