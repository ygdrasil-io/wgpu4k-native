package io.ygdrasil.wgpu

import io.ygdrasil.kffi.MemoryBuffer
import io.ygdrasil.kffi.memoryScope

data class SurfaceCapabilities(val formats: List<WGPUTextureFormat>, val alphaModes: List<WGPUCompositeAlphaMode>)

fun surfaceCapabilities(surface: WGPUSurface, adapter: WGPUAdapter): SurfaceCapabilities = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)

    if (surfaceCapabilities.formatCount == 0uL) error("No surface formats available")
    if (surfaceCapabilities.alphaModeCount == 0uL) error("No alpha modes available")

    val formats = MemoryBuffer(surfaceCapabilities.formats, UInt.SIZE_BYTES.toULong() * surfaceCapabilities.formatCount)
        .let { buffer ->
            UIntArray(surfaceCapabilities.formatCount.toInt()).let {
                buffer.readUInts(it)
                it.toList()
            }
        }

    val alphaModes = MemoryBuffer(surfaceCapabilities.alphaModes, UInt.SIZE_BYTES.toULong() * surfaceCapabilities.alphaModeCount)
        .let { buffer ->
            UIntArray(surfaceCapabilities.alphaModeCount.toInt()).let {
                buffer.readUInts(it)
                it.toList()
            }
        }

    return SurfaceCapabilities(formats, alphaModes)
}

fun compatibleFormat(surface: WGPUSurface, adapter: WGPUAdapter): UInt = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)
    if (surfaceCapabilities.formatCount == 0uL) error("no surface format")
    val formats = surfaceCapabilities.formats
    return MemoryBuffer(formats, Int.SIZE_BYTES.toULong() * surfaceCapabilities.formatCount)
        .readInt().toUInt()
}

fun compatibleAlphaMode(surface: WGPUSurface, adapter: WGPUAdapter): UInt = memoryScope { scope ->
    val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
    wgpuSurfaceGetCapabilities(surface, adapter, surfaceCapabilities)
    if (surfaceCapabilities.alphaModeCount == 0uL) error("no surface alpha mode")
    val alphaModes = surfaceCapabilities.alphaModes
    return MemoryBuffer(alphaModes, Int.SIZE_BYTES.toULong() * surfaceCapabilities.alphaModeCount)
        .readInt().toUInt()
}
