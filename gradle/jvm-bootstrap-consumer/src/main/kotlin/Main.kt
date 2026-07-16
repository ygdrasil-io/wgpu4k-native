import io.ygdrasil.wgpu.WGPULogLevel_Warn
import io.ygdrasil.wgpu.wgpuSetLogLevel

fun main() {
    wgpuSetLogLevel(WGPULogLevel_Warn)
    println("FIRST_DOWNCALL_OK")
}
