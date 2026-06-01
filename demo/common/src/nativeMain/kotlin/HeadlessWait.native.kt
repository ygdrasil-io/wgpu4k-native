package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.posix.usleep

@OptIn(ExperimentalForeignApi::class)
actual fun waitForHeadlessMapEvent(instance: WGPUInstance) {
    wgpuInstanceProcessEvents(instance)
    usleep(1_000.convert())
}
