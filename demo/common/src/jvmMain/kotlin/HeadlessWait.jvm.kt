package io.ygdrasil.wgpu

actual fun waitForHeadlessMapEvent(instance: WGPUInstance) {
    wgpuInstanceProcessEvents(instance)
    Thread.sleep(1)
}
