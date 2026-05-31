package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.CallbackHolder
import io.ygdrasil.kffi.CString
import io.ygdrasil.kffi.ArrayHolder
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.CStructure
import java.lang.foreign.*
import java.lang.invoke.VarHandle
import java.lang.foreign.MemoryLayout.PathElement.groupElement

actual interface WGPUStringView : CStructure {
    actual var data: CString?
    actual var length: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("data"),
            ValueLayout.JAVA_LONG.withName("length")
        ).withName("WGPUStringView")
        
        val data_VH: VarHandle = layout.varHandle(groupElement("data"))
        val length_VH: VarHandle = layout.varHandle(groupElement("length"))
        
        actual operator fun invoke(address: NativeAddress): WGPUStringView = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUStringView = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStringView) -> Unit): ArrayHolder<WGPUStringView> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUStringView {
        override var data: CString?
            get() = (data_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let(::CString)
            set(value) = data_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var length: ULong
            get() = (length_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = length_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUAdapterImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUAdapterImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUAdapterImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUAdapterImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterImpl) -> Unit): ArrayHolder<WGPUAdapterImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUAdapterImpl {
    }
}

actual interface WGPUBindGroupImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUBindGroupImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupImpl) -> Unit): ArrayHolder<WGPUBindGroupImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupImpl {
    }
}

actual interface WGPUBindGroupLayoutImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUBindGroupLayoutImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutImpl) -> Unit): ArrayHolder<WGPUBindGroupLayoutImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutImpl {
    }
}

actual interface WGPUBufferImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUBufferImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUBufferImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferImpl) -> Unit): ArrayHolder<WGPUBufferImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBufferImpl {
    }
}

actual interface WGPUCommandBufferImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUCommandBufferImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUCommandBufferImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandBufferImpl) -> Unit): ArrayHolder<WGPUCommandBufferImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCommandBufferImpl {
    }
}

actual interface WGPUCommandEncoderImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUCommandEncoderImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUCommandEncoderImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandEncoderImpl) -> Unit): ArrayHolder<WGPUCommandEncoderImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCommandEncoderImpl {
    }
}

actual interface WGPUComputePassEncoderImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUComputePassEncoderImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUComputePassEncoderImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePassEncoderImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePassEncoderImpl) -> Unit): ArrayHolder<WGPUComputePassEncoderImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUComputePassEncoderImpl {
    }
}

actual interface WGPUComputePipelineImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUComputePipelineImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUComputePipelineImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePipelineImpl) -> Unit): ArrayHolder<WGPUComputePipelineImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUComputePipelineImpl {
    }
}

actual interface WGPUDeviceImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUDeviceImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUDeviceImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceImpl) -> Unit): ArrayHolder<WGPUDeviceImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUDeviceImpl {
    }
}

actual interface WGPUExternalTextureImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUExternalTextureImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureImpl) -> Unit): ArrayHolder<WGPUExternalTextureImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUExternalTextureImpl {
    }
}

actual interface WGPUInstanceImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUInstanceImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUInstanceImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceImpl) -> Unit): ArrayHolder<WGPUInstanceImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUInstanceImpl {
    }
}

actual interface WGPUPipelineLayoutImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUPipelineLayoutImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutImpl) -> Unit): ArrayHolder<WGPUPipelineLayoutImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPipelineLayoutImpl {
    }
}

actual interface WGPUQuerySetImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUQuerySetImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetImpl) -> Unit): ArrayHolder<WGPUQuerySetImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQuerySetImpl {
    }
}

actual interface WGPUQueueImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUQueueImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUQueueImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueImpl) -> Unit): ArrayHolder<WGPUQueueImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQueueImpl {
    }
}

actual interface WGPURenderBundleImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPURenderBundleImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleImpl) -> Unit): ArrayHolder<WGPURenderBundleImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderBundleImpl {
    }
}

actual interface WGPURenderBundleEncoderImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPURenderBundleEncoderImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleEncoderImpl) -> Unit): ArrayHolder<WGPURenderBundleEncoderImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderBundleEncoderImpl {
    }
}

actual interface WGPURenderPassEncoderImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPURenderPassEncoderImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPassEncoderImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassEncoderImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassEncoderImpl) -> Unit): ArrayHolder<WGPURenderPassEncoderImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPassEncoderImpl {
    }
}

actual interface WGPURenderPipelineImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPURenderPipelineImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPipelineImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPipelineImpl) -> Unit): ArrayHolder<WGPURenderPipelineImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPipelineImpl {
    }
}

actual interface WGPUSamplerImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUSamplerImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUSamplerImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerImpl) -> Unit): ArrayHolder<WGPUSamplerImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSamplerImpl {
    }
}

actual interface WGPUShaderModuleImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUShaderModuleImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleImpl) -> Unit): ArrayHolder<WGPUShaderModuleImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderModuleImpl {
    }
}

actual interface WGPUSurfaceImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUSurfaceImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceImpl) -> Unit): ArrayHolder<WGPUSurfaceImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceImpl {
    }
}

actual interface WGPUTextureImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUTextureImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureImpl) -> Unit): ArrayHolder<WGPUTextureImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureImpl {
    }
}

actual interface WGPUTextureViewImpl : CStructure {
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("WGPUTextureViewImpl")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureViewImpl = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewImpl = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureViewImpl) -> Unit): ArrayHolder<WGPUTextureViewImpl> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureViewImpl {
    }
}

actual interface WGPUChainedStruct : CStructure {
    actual var next: WGPUChainedStruct?
    actual var sType: WGPUSType
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("next"),
            ValueLayout.JAVA_INT.withName("sType"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUChainedStruct")
        
        val next_VH: VarHandle = layout.varHandle(groupElement("next"))
        val sType_VH: VarHandle = layout.varHandle(groupElement("sType"))
        
        actual operator fun invoke(address: NativeAddress): WGPUChainedStruct = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUChainedStruct) -> Unit): ArrayHolder<WGPUChainedStruct> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUChainedStruct {
        override var next: WGPUChainedStruct?
            get() = (next_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUChainedStruct(it) }
            set(value) = next_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var sType: WGPUSType
            get() = (sType_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUSType
            set(value) = sType_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUBufferMapCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUBufferMapCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBufferMapCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferMapCallbackInfo) -> Unit): ArrayHolder<WGPUBufferMapCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBufferMapCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUCompilationInfoCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUCompilationInfoCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCompilationInfoCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfoCallbackInfo) -> Unit): ArrayHolder<WGPUCompilationInfoCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCompilationInfoCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUCreateComputePipelineAsyncCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUCreateComputePipelineAsyncCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCreateComputePipelineAsyncCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateComputePipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateComputePipelineAsyncCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCreateComputePipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUCreateRenderPipelineAsyncCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUCreateRenderPipelineAsyncCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCreateRenderPipelineAsyncCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateRenderPipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateRenderPipelineAsyncCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCreateRenderPipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUDeviceLostCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUDeviceLostCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUDeviceLostCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceLostCallbackInfo) -> Unit): ArrayHolder<WGPUDeviceLostCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUDeviceLostCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUPopErrorScopeCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUPopErrorScopeCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPopErrorScopeCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPopErrorScopeCallbackInfo) -> Unit): ArrayHolder<WGPUPopErrorScopeCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPopErrorScopeCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUQueueWorkDoneCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUQueueWorkDoneCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUQueueWorkDoneCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueWorkDoneCallbackInfo) -> Unit): ArrayHolder<WGPUQueueWorkDoneCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQueueWorkDoneCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPURequestAdapterCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPURequestAdapterCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterCallbackInfo) -> Unit): ArrayHolder<WGPURequestAdapterCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURequestAdapterCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPURequestDeviceCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("mode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPURequestDeviceCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val mode_VH: VarHandle = layout.varHandle(groupElement("mode"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPURequestDeviceCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestDeviceCallbackInfo) -> Unit): ArrayHolder<WGPURequestDeviceCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURequestDeviceCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var mode: WGPUCallbackMode
            get() = (mode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCallbackMode
            set(value) = mode_VH.set(handler.handler, 0L, value.toInt())
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUUncapturedErrorCallbackInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("callback"),
            ValueLayout.ADDRESS.withName("userdata1"),
            ValueLayout.ADDRESS.withName("userdata2")
        ).withName("WGPUUncapturedErrorCallbackInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val callback_VH: VarHandle = layout.varHandle(groupElement("callback"))
        val userdata1_VH: VarHandle = layout.varHandle(groupElement("userdata1"))
        val userdata2_VH: VarHandle = layout.varHandle(groupElement("userdata2"))
        
        actual operator fun invoke(address: NativeAddress): WGPUUncapturedErrorCallbackInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUUncapturedErrorCallbackInfo) -> Unit): ArrayHolder<WGPUUncapturedErrorCallbackInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUUncapturedErrorCallbackInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var callback: NativeAddress
            get() = (callback_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = callback_VH.set(handler.handler, 0L, value.handler)
        override var userdata1: NativeAddress
            get() = (userdata1_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata1_VH.set(handler.handler, 0L, value.handler)
        override var userdata2: NativeAddress
            get() = (userdata2_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = userdata2_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUAdapterInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var vendor: WGPUStringView
    actual var architecture: WGPUStringView
    actual var device: WGPUStringView
    actual var description: WGPUStringView
    actual var backendType: WGPUBackendType
    actual var adapterType: WGPUAdapterType
    actual var vendorID: UInt
    actual var deviceID: UInt
    actual var subgroupMinSize: UInt
    actual var subgroupMaxSize: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("vendor"),
            WGPUStringView.layout.withName("architecture"),
            WGPUStringView.layout.withName("device"),
            WGPUStringView.layout.withName("description"),
            ValueLayout.JAVA_INT.withName("backendType"),
            ValueLayout.JAVA_INT.withName("adapterType"),
            ValueLayout.JAVA_INT.withName("vendorID"),
            ValueLayout.JAVA_INT.withName("deviceID"),
            ValueLayout.JAVA_INT.withName("subgroupMinSize"),
            ValueLayout.JAVA_INT.withName("subgroupMaxSize")
        ).withName("WGPUAdapterInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val backendType_VH: VarHandle = layout.varHandle(groupElement("backendType"))
        val adapterType_VH: VarHandle = layout.varHandle(groupElement("adapterType"))
        val vendorID_VH: VarHandle = layout.varHandle(groupElement("vendorID"))
        val deviceID_VH: VarHandle = layout.varHandle(groupElement("deviceID"))
        val subgroupMinSize_VH: VarHandle = layout.varHandle(groupElement("subgroupMinSize"))
        val subgroupMaxSize_VH: VarHandle = layout.varHandle(groupElement("subgroupMaxSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPUAdapterInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterInfo) -> Unit): ArrayHolder<WGPUAdapterInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUAdapterInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var vendor: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("vendor")), Companion.layout.select(groupElement("vendor")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("vendor")), Companion.layout.select(groupElement("vendor")).byteSize())
            }
        override var architecture: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("architecture")), Companion.layout.select(groupElement("architecture")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("architecture")), Companion.layout.select(groupElement("architecture")).byteSize())
            }
        override var device: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("device")), Companion.layout.select(groupElement("device")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("device")), Companion.layout.select(groupElement("device")).byteSize())
            }
        override var description: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("description")), Companion.layout.select(groupElement("description")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("description")), Companion.layout.select(groupElement("description")).byteSize())
            }
        override var backendType: WGPUBackendType
            get() = (backendType_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBackendType
            set(value) = backendType_VH.set(handler.handler, 0L, value.toInt())
        override var adapterType: WGPUAdapterType
            get() = (adapterType_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUAdapterType
            set(value) = adapterType_VH.set(handler.handler, 0L, value.toInt())
        override var vendorID: UInt
            get() = (vendorID_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = vendorID_VH.set(handler.handler, 0L, value.toInt())
        override var deviceID: UInt
            get() = (deviceID_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = deviceID_VH.set(handler.handler, 0L, value.toInt())
        override var subgroupMinSize: UInt
            get() = (subgroupMinSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = subgroupMinSize_VH.set(handler.handler, 0L, value.toInt())
        override var subgroupMaxSize: UInt
            get() = (subgroupMaxSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = subgroupMaxSize_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUBlendComponent : CStructure {
    actual var operation: WGPUBlendOperation
    actual var srcFactor: WGPUBlendFactor
    actual var dstFactor: WGPUBlendFactor
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("operation"),
            ValueLayout.JAVA_INT.withName("srcFactor"),
            ValueLayout.JAVA_INT.withName("dstFactor")
        ).withName("WGPUBlendComponent")
        
        val operation_VH: VarHandle = layout.varHandle(groupElement("operation"))
        val srcFactor_VH: VarHandle = layout.varHandle(groupElement("srcFactor"))
        val dstFactor_VH: VarHandle = layout.varHandle(groupElement("dstFactor"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBlendComponent = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendComponent) -> Unit): ArrayHolder<WGPUBlendComponent> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBlendComponent {
        override var operation: WGPUBlendOperation
            get() = (operation_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBlendOperation
            set(value) = operation_VH.set(handler.handler, 0L, value.toInt())
        override var srcFactor: WGPUBlendFactor
            get() = (srcFactor_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBlendFactor
            set(value) = srcFactor_VH.set(handler.handler, 0L, value.toInt())
        override var dstFactor: WGPUBlendFactor
            get() = (dstFactor_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBlendFactor
            set(value) = dstFactor_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUBufferBindingLayout : CStructure {
    actual var nextInChain: NativeAddress
    actual var type: WGPUBufferBindingType
    actual var hasDynamicOffset: UInt
    actual var minBindingSize: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("type"),
            ValueLayout.JAVA_INT.withName("hasDynamicOffset"),
            ValueLayout.JAVA_LONG.withName("minBindingSize")
        ).withName("WGPUBufferBindingLayout")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val type_VH: VarHandle = layout.varHandle(groupElement("type"))
        val hasDynamicOffset_VH: VarHandle = layout.varHandle(groupElement("hasDynamicOffset"))
        val minBindingSize_VH: VarHandle = layout.varHandle(groupElement("minBindingSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBufferBindingLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferBindingLayout) -> Unit): ArrayHolder<WGPUBufferBindingLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBufferBindingLayout {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var type: WGPUBufferBindingType
            get() = (type_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBufferBindingType
            set(value) = type_VH.set(handler.handler, 0L, value.toInt())
        override var hasDynamicOffset: UInt
            get() = (hasDynamicOffset_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = hasDynamicOffset_VH.set(handler.handler, 0L, value.toInt())
        override var minBindingSize: ULong
            get() = (minBindingSize_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = minBindingSize_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUBufferDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var usage: ULong
    actual var size: ULong
    actual var mappedAtCreation: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("usage"),
            ValueLayout.JAVA_LONG.withName("size"),
            ValueLayout.JAVA_INT.withName("mappedAtCreation"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUBufferDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val usage_VH: VarHandle = layout.varHandle(groupElement("usage"))
        val size_VH: VarHandle = layout.varHandle(groupElement("size"))
        val mappedAtCreation_VH: VarHandle = layout.varHandle(groupElement("mappedAtCreation"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBufferDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferDescriptor) -> Unit): ArrayHolder<WGPUBufferDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var usage: ULong
            get() = (usage_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = usage_VH.set(handler.handler, 0L, value.toLong())
        override var size: ULong
            get() = (size_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = size_VH.set(handler.handler, 0L, value.toLong())
        override var mappedAtCreation: UInt
            get() = (mappedAtCreation_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = mappedAtCreation_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUColor : CStructure {
    actual var r: Double
    actual var g: Double
    actual var b: Double
    actual var a: Double
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("r"),
            ValueLayout.JAVA_DOUBLE.withName("g"),
            ValueLayout.JAVA_DOUBLE.withName("b"),
            ValueLayout.JAVA_DOUBLE.withName("a")
        ).withName("WGPUColor")
        
        val r_VH: VarHandle = layout.varHandle(groupElement("r"))
        val g_VH: VarHandle = layout.varHandle(groupElement("g"))
        val b_VH: VarHandle = layout.varHandle(groupElement("b"))
        val a_VH: VarHandle = layout.varHandle(groupElement("a"))
        
        actual operator fun invoke(address: NativeAddress): WGPUColor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUColor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColor) -> Unit): ArrayHolder<WGPUColor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUColor {
        override var r: Double
            get() = r_VH.get(handler.handler, 0L) as Double
            set(value) = r_VH.set(handler.handler, 0L, value)
        override var g: Double
            get() = g_VH.get(handler.handler, 0L) as Double
            set(value) = g_VH.set(handler.handler, 0L, value)
        override var b: Double
            get() = b_VH.get(handler.handler, 0L) as Double
            set(value) = b_VH.set(handler.handler, 0L, value)
        override var a: Double
            get() = a_VH.get(handler.handler, 0L) as Double
            set(value) = a_VH.set(handler.handler, 0L, value)
    }
}

actual interface WGPUCommandBufferDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPUCommandBufferDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCommandBufferDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandBufferDescriptor) -> Unit): ArrayHolder<WGPUCommandBufferDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCommandBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPUCommandEncoderDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPUCommandEncoderDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCommandEncoderDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandEncoderDescriptor) -> Unit): ArrayHolder<WGPUCommandEncoderDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCommandEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPUCompatibilityModeLimits : CStructure {
    actual var chain: WGPUChainedStruct
    actual var maxStorageBuffersInVertexStage: UInt
    actual var maxStorageTexturesInVertexStage: UInt
    actual var maxStorageBuffersInFragmentStage: UInt
    actual var maxStorageTexturesInFragmentStage: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("maxStorageBuffersInVertexStage"),
            ValueLayout.JAVA_INT.withName("maxStorageTexturesInVertexStage"),
            ValueLayout.JAVA_INT.withName("maxStorageBuffersInFragmentStage"),
            ValueLayout.JAVA_INT.withName("maxStorageTexturesInFragmentStage")
        ).withName("WGPUCompatibilityModeLimits")
        
        val maxStorageBuffersInVertexStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageBuffersInVertexStage"))
        val maxStorageTexturesInVertexStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageTexturesInVertexStage"))
        val maxStorageBuffersInFragmentStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageBuffersInFragmentStage"))
        val maxStorageTexturesInFragmentStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageTexturesInFragmentStage"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCompatibilityModeLimits = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCompatibilityModeLimits = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompatibilityModeLimits) -> Unit): ArrayHolder<WGPUCompatibilityModeLimits> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCompatibilityModeLimits {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var maxStorageBuffersInVertexStage: UInt
            get() = (maxStorageBuffersInVertexStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageBuffersInVertexStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxStorageTexturesInVertexStage: UInt
            get() = (maxStorageTexturesInVertexStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageTexturesInVertexStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxStorageBuffersInFragmentStage: UInt
            get() = (maxStorageBuffersInFragmentStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageBuffersInFragmentStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxStorageTexturesInFragmentStage: UInt
            get() = (maxStorageTexturesInFragmentStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageTexturesInFragmentStage_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUCompilationMessage : CStructure {
    actual var nextInChain: NativeAddress
    actual var message: WGPUStringView
    actual var type: WGPUCompilationMessageType
    actual var lineNum: ULong
    actual var linePos: ULong
    actual var offset: ULong
    actual var length: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("message"),
            ValueLayout.JAVA_INT.withName("type"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("lineNum"),
            ValueLayout.JAVA_LONG.withName("linePos"),
            ValueLayout.JAVA_LONG.withName("offset"),
            ValueLayout.JAVA_LONG.withName("length")
        ).withName("WGPUCompilationMessage")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val type_VH: VarHandle = layout.varHandle(groupElement("type"))
        val lineNum_VH: VarHandle = layout.varHandle(groupElement("lineNum"))
        val linePos_VH: VarHandle = layout.varHandle(groupElement("linePos"))
        val offset_VH: VarHandle = layout.varHandle(groupElement("offset"))
        val length_VH: VarHandle = layout.varHandle(groupElement("length"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCompilationMessage = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationMessage) -> Unit): ArrayHolder<WGPUCompilationMessage> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCompilationMessage {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var message: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("message")), Companion.layout.select(groupElement("message")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("message")), Companion.layout.select(groupElement("message")).byteSize())
            }
        override var type: WGPUCompilationMessageType
            get() = (type_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCompilationMessageType
            set(value) = type_VH.set(handler.handler, 0L, value.toInt())
        override var lineNum: ULong
            get() = (lineNum_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = lineNum_VH.set(handler.handler, 0L, value.toLong())
        override var linePos: ULong
            get() = (linePos_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = linePos_VH.set(handler.handler, 0L, value.toLong())
        override var offset: ULong
            get() = (offset_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = offset_VH.set(handler.handler, 0L, value.toLong())
        override var length: ULong
            get() = (length_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = length_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUConstantEntry : CStructure {
    actual var nextInChain: NativeAddress
    actual var key: WGPUStringView
    actual var value: Double
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("key"),
            ValueLayout.JAVA_DOUBLE.withName("value")
        ).withName("WGPUConstantEntry")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val value_VH: VarHandle = layout.varHandle(groupElement("value"))
        
        actual operator fun invoke(address: NativeAddress): WGPUConstantEntry = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUConstantEntry) -> Unit): ArrayHolder<WGPUConstantEntry> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUConstantEntry {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var key: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("key")), Companion.layout.select(groupElement("key")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("key")), Companion.layout.select(groupElement("key")).byteSize())
            }
        override var value: Double
            get() = value_VH.get(handler.handler, 0L) as Double
            set(value) = value_VH.set(handler.handler, 0L, value)
    }
}

actual interface WGPUExtent3D : CStructure {
    actual var width: UInt
    actual var height: UInt
    actual var depthOrArrayLayers: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("width"),
            ValueLayout.JAVA_INT.withName("height"),
            ValueLayout.JAVA_INT.withName("depthOrArrayLayers")
        ).withName("WGPUExtent3D")
        
        val width_VH: VarHandle = layout.varHandle(groupElement("width"))
        val height_VH: VarHandle = layout.varHandle(groupElement("height"))
        val depthOrArrayLayers_VH: VarHandle = layout.varHandle(groupElement("depthOrArrayLayers"))
        
        actual operator fun invoke(address: NativeAddress): WGPUExtent3D = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExtent3D) -> Unit): ArrayHolder<WGPUExtent3D> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUExtent3D {
        override var width: UInt
            get() = (width_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = width_VH.set(handler.handler, 0L, value.toInt())
        override var height: UInt
            get() = (height_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = height_VH.set(handler.handler, 0L, value.toInt())
        override var depthOrArrayLayers: UInt
            get() = (depthOrArrayLayers_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = depthOrArrayLayers_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUExternalTextureBindingEntry : CStructure {
    actual var chain: WGPUChainedStruct
    actual var externalTexture: WGPUExternalTextureImpl?
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("externalTexture")
        ).withName("WGPUExternalTextureBindingEntry")
        
        val externalTexture_VH: VarHandle = layout.varHandle(groupElement("externalTexture"))
        
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingEntry = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingEntry = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingEntry) -> Unit): ArrayHolder<WGPUExternalTextureBindingEntry> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUExternalTextureBindingEntry {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var externalTexture: WGPUExternalTextureImpl?
            get() = (externalTexture_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUExternalTextureImpl(it) }
            set(value) = externalTexture_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
    }
}

actual interface WGPUExternalTextureBindingLayout : CStructure {
    actual var chain: WGPUChainedStruct
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain")
        ).withName("WGPUExternalTextureBindingLayout")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingLayout) -> Unit): ArrayHolder<WGPUExternalTextureBindingLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUExternalTextureBindingLayout {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
    }
}

actual interface WGPUFuture : CStructure {
    actual var id: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("id")
        ).withName("WGPUFuture")
        
        val id_VH: VarHandle = layout.varHandle(groupElement("id"))
        
        actual operator fun invoke(address: NativeAddress): WGPUFuture = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUFuture = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFuture) -> Unit): ArrayHolder<WGPUFuture> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUFuture {
        override var id: ULong
            get() = (id_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = id_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUInstanceLimits : CStructure {
    actual var nextInChain: NativeAddress
    actual var timedWaitAnyMaxCount: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_LONG.withName("timedWaitAnyMaxCount")
        ).withName("WGPUInstanceLimits")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val timedWaitAnyMaxCount_VH: VarHandle = layout.varHandle(groupElement("timedWaitAnyMaxCount"))
        
        actual operator fun invoke(address: NativeAddress): WGPUInstanceLimits = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceLimits = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceLimits) -> Unit): ArrayHolder<WGPUInstanceLimits> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUInstanceLimits {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var timedWaitAnyMaxCount: ULong
            get() = (timedWaitAnyMaxCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = timedWaitAnyMaxCount_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUMultisampleState : CStructure {
    actual var nextInChain: NativeAddress
    actual var count: UInt
    actual var mask: UInt
    actual var alphaToCoverageEnabled: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("count"),
            ValueLayout.JAVA_INT.withName("mask"),
            ValueLayout.JAVA_INT.withName("alphaToCoverageEnabled"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUMultisampleState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val count_VH: VarHandle = layout.varHandle(groupElement("count"))
        val mask_VH: VarHandle = layout.varHandle(groupElement("mask"))
        val alphaToCoverageEnabled_VH: VarHandle = layout.varHandle(groupElement("alphaToCoverageEnabled"))
        
        actual operator fun invoke(address: NativeAddress): WGPUMultisampleState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUMultisampleState) -> Unit): ArrayHolder<WGPUMultisampleState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUMultisampleState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var count: UInt
            get() = (count_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = count_VH.set(handler.handler, 0L, value.toInt())
        override var mask: UInt
            get() = (mask_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = mask_VH.set(handler.handler, 0L, value.toInt())
        override var alphaToCoverageEnabled: UInt
            get() = (alphaToCoverageEnabled_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = alphaToCoverageEnabled_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUOrigin3D : CStructure {
    actual var x: UInt
    actual var y: UInt
    actual var z: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("x"),
            ValueLayout.JAVA_INT.withName("y"),
            ValueLayout.JAVA_INT.withName("z")
        ).withName("WGPUOrigin3D")
        
        val x_VH: VarHandle = layout.varHandle(groupElement("x"))
        val y_VH: VarHandle = layout.varHandle(groupElement("y"))
        val z_VH: VarHandle = layout.varHandle(groupElement("z"))
        
        actual operator fun invoke(address: NativeAddress): WGPUOrigin3D = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUOrigin3D) -> Unit): ArrayHolder<WGPUOrigin3D> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUOrigin3D {
        override var x: UInt
            get() = (x_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = x_VH.set(handler.handler, 0L, value.toInt())
        override var y: UInt
            get() = (y_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = y_VH.set(handler.handler, 0L, value.toInt())
        override var z: UInt
            get() = (z_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = z_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUPassTimestampWrites : CStructure {
    actual var nextInChain: NativeAddress
    actual var querySet: WGPUQuerySetImpl?
    actual var beginningOfPassWriteIndex: UInt
    actual var endOfPassWriteIndex: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("querySet"),
            ValueLayout.JAVA_INT.withName("beginningOfPassWriteIndex"),
            ValueLayout.JAVA_INT.withName("endOfPassWriteIndex")
        ).withName("WGPUPassTimestampWrites")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val querySet_VH: VarHandle = layout.varHandle(groupElement("querySet"))
        val beginningOfPassWriteIndex_VH: VarHandle = layout.varHandle(groupElement("beginningOfPassWriteIndex"))
        val endOfPassWriteIndex_VH: VarHandle = layout.varHandle(groupElement("endOfPassWriteIndex"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPassTimestampWrites = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPassTimestampWrites = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPassTimestampWrites) -> Unit): ArrayHolder<WGPUPassTimestampWrites> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPassTimestampWrites {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var querySet: WGPUQuerySetImpl?
            get() = (querySet_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUQuerySetImpl(it) }
            set(value) = querySet_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var beginningOfPassWriteIndex: UInt
            get() = (beginningOfPassWriteIndex_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = beginningOfPassWriteIndex_VH.set(handler.handler, 0L, value.toInt())
        override var endOfPassWriteIndex: UInt
            get() = (endOfPassWriteIndex_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = endOfPassWriteIndex_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUPipelineLayoutDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var bindGroupLayoutCount: ULong
    actual var bindGroupLayouts: NativeAddress
    actual var immediateSize: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("bindGroupLayoutCount"),
            ValueLayout.ADDRESS.withName("bindGroupLayouts"),
            ValueLayout.JAVA_INT.withName("immediateSize"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUPipelineLayoutDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val bindGroupLayoutCount_VH: VarHandle = layout.varHandle(groupElement("bindGroupLayoutCount"))
        val bindGroupLayouts_VH: VarHandle = layout.varHandle(groupElement("bindGroupLayouts"))
        val immediateSize_VH: VarHandle = layout.varHandle(groupElement("immediateSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutDescriptor) -> Unit): ArrayHolder<WGPUPipelineLayoutDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPipelineLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var bindGroupLayoutCount: ULong
            get() = (bindGroupLayoutCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = bindGroupLayoutCount_VH.set(handler.handler, 0L, value.toLong())
        override var bindGroupLayouts: NativeAddress
            get() = (bindGroupLayouts_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = bindGroupLayouts_VH.set(handler.handler, 0L, value.handler)
        override var immediateSize: UInt
            get() = (immediateSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = immediateSize_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUPrimitiveState : CStructure {
    actual var nextInChain: NativeAddress
    actual var topology: WGPUPrimitiveTopology
    actual var stripIndexFormat: WGPUIndexFormat
    actual var frontFace: WGPUFrontFace
    actual var cullMode: WGPUCullMode
    actual var unclippedDepth: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("topology"),
            ValueLayout.JAVA_INT.withName("stripIndexFormat"),
            ValueLayout.JAVA_INT.withName("frontFace"),
            ValueLayout.JAVA_INT.withName("cullMode"),
            ValueLayout.JAVA_INT.withName("unclippedDepth"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUPrimitiveState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val topology_VH: VarHandle = layout.varHandle(groupElement("topology"))
        val stripIndexFormat_VH: VarHandle = layout.varHandle(groupElement("stripIndexFormat"))
        val frontFace_VH: VarHandle = layout.varHandle(groupElement("frontFace"))
        val cullMode_VH: VarHandle = layout.varHandle(groupElement("cullMode"))
        val unclippedDepth_VH: VarHandle = layout.varHandle(groupElement("unclippedDepth"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPrimitiveState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveState) -> Unit): ArrayHolder<WGPUPrimitiveState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPrimitiveState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var topology: WGPUPrimitiveTopology
            get() = (topology_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUPrimitiveTopology
            set(value) = topology_VH.set(handler.handler, 0L, value.toInt())
        override var stripIndexFormat: WGPUIndexFormat
            get() = (stripIndexFormat_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUIndexFormat
            set(value) = stripIndexFormat_VH.set(handler.handler, 0L, value.toInt())
        override var frontFace: WGPUFrontFace
            get() = (frontFace_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUFrontFace
            set(value) = frontFace_VH.set(handler.handler, 0L, value.toInt())
        override var cullMode: WGPUCullMode
            get() = (cullMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCullMode
            set(value) = cullMode_VH.set(handler.handler, 0L, value.toInt())
        override var unclippedDepth: UInt
            get() = (unclippedDepth_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = unclippedDepth_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUQuerySetDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var type: WGPUQueryType
    actual var count: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_INT.withName("type"),
            ValueLayout.JAVA_INT.withName("count")
        ).withName("WGPUQuerySetDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val type_VH: VarHandle = layout.varHandle(groupElement("type"))
        val count_VH: VarHandle = layout.varHandle(groupElement("count"))
        
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptor) -> Unit): ArrayHolder<WGPUQuerySetDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQuerySetDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var type: WGPUQueryType
            get() = (type_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUQueryType
            set(value) = type_VH.set(handler.handler, 0L, value.toInt())
        override var count: UInt
            get() = (count_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = count_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUQueueDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPUQueueDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPUQueueDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueDescriptor) -> Unit): ArrayHolder<WGPUQueueDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQueueDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPURenderBundleDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPURenderBundleDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleDescriptor) -> Unit): ArrayHolder<WGPURenderBundleDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderBundleDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPURenderBundleEncoderDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var colorFormatCount: ULong
    actual var colorFormats: NativeAddress
    actual var depthStencilFormat: WGPUTextureFormat
    actual var sampleCount: UInt
    actual var depthReadOnly: UInt
    actual var stencilReadOnly: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("colorFormatCount"),
            ValueLayout.ADDRESS.withName("colorFormats"),
            ValueLayout.JAVA_INT.withName("depthStencilFormat"),
            ValueLayout.JAVA_INT.withName("sampleCount"),
            ValueLayout.JAVA_INT.withName("depthReadOnly"),
            ValueLayout.JAVA_INT.withName("stencilReadOnly")
        ).withName("WGPURenderBundleEncoderDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val colorFormatCount_VH: VarHandle = layout.varHandle(groupElement("colorFormatCount"))
        val colorFormats_VH: VarHandle = layout.varHandle(groupElement("colorFormats"))
        val depthStencilFormat_VH: VarHandle = layout.varHandle(groupElement("depthStencilFormat"))
        val sampleCount_VH: VarHandle = layout.varHandle(groupElement("sampleCount"))
        val depthReadOnly_VH: VarHandle = layout.varHandle(groupElement("depthReadOnly"))
        val stencilReadOnly_VH: VarHandle = layout.varHandle(groupElement("stencilReadOnly"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleEncoderDescriptor) -> Unit): ArrayHolder<WGPURenderBundleEncoderDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderBundleEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var colorFormatCount: ULong
            get() = (colorFormatCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = colorFormatCount_VH.set(handler.handler, 0L, value.toLong())
        override var colorFormats: NativeAddress
            get() = (colorFormats_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = colorFormats_VH.set(handler.handler, 0L, value.handler)
        override var depthStencilFormat: WGPUTextureFormat
            get() = (depthStencilFormat_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = depthStencilFormat_VH.set(handler.handler, 0L, value.toInt())
        override var sampleCount: UInt
            get() = (sampleCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = sampleCount_VH.set(handler.handler, 0L, value.toInt())
        override var depthReadOnly: UInt
            get() = (depthReadOnly_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = depthReadOnly_VH.set(handler.handler, 0L, value.toInt())
        override var stencilReadOnly: UInt
            get() = (stencilReadOnly_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = stencilReadOnly_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPURenderPassDepthStencilAttachment : CStructure {
    actual var nextInChain: NativeAddress
    actual var view: WGPUTextureViewImpl?
    actual var depthLoadOp: WGPULoadOp
    actual var depthStoreOp: WGPUStoreOp
    actual var depthClearValue: Float
    actual var depthReadOnly: UInt
    actual var stencilLoadOp: WGPULoadOp
    actual var stencilStoreOp: WGPUStoreOp
    actual var stencilClearValue: UInt
    actual var stencilReadOnly: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("view"),
            ValueLayout.JAVA_INT.withName("depthLoadOp"),
            ValueLayout.JAVA_INT.withName("depthStoreOp"),
            ValueLayout.JAVA_FLOAT.withName("depthClearValue"),
            ValueLayout.JAVA_INT.withName("depthReadOnly"),
            ValueLayout.JAVA_INT.withName("stencilLoadOp"),
            ValueLayout.JAVA_INT.withName("stencilStoreOp"),
            ValueLayout.JAVA_INT.withName("stencilClearValue"),
            ValueLayout.JAVA_INT.withName("stencilReadOnly")
        ).withName("WGPURenderPassDepthStencilAttachment")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val view_VH: VarHandle = layout.varHandle(groupElement("view"))
        val depthLoadOp_VH: VarHandle = layout.varHandle(groupElement("depthLoadOp"))
        val depthStoreOp_VH: VarHandle = layout.varHandle(groupElement("depthStoreOp"))
        val depthClearValue_VH: VarHandle = layout.varHandle(groupElement("depthClearValue"))
        val depthReadOnly_VH: VarHandle = layout.varHandle(groupElement("depthReadOnly"))
        val stencilLoadOp_VH: VarHandle = layout.varHandle(groupElement("stencilLoadOp"))
        val stencilStoreOp_VH: VarHandle = layout.varHandle(groupElement("stencilStoreOp"))
        val stencilClearValue_VH: VarHandle = layout.varHandle(groupElement("stencilClearValue"))
        val stencilReadOnly_VH: VarHandle = layout.varHandle(groupElement("stencilReadOnly"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPassDepthStencilAttachment = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDepthStencilAttachment) -> Unit): ArrayHolder<WGPURenderPassDepthStencilAttachment> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPassDepthStencilAttachment {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var view: WGPUTextureViewImpl?
            get() = (view_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureViewImpl(it) }
            set(value) = view_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var depthLoadOp: WGPULoadOp
            get() = (depthLoadOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPULoadOp
            set(value) = depthLoadOp_VH.set(handler.handler, 0L, value.toInt())
        override var depthStoreOp: WGPUStoreOp
            get() = (depthStoreOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStoreOp
            set(value) = depthStoreOp_VH.set(handler.handler, 0L, value.toInt())
        override var depthClearValue: Float
            get() = depthClearValue_VH.get(handler.handler, 0L) as Float
            set(value) = depthClearValue_VH.set(handler.handler, 0L, value)
        override var depthReadOnly: UInt
            get() = (depthReadOnly_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = depthReadOnly_VH.set(handler.handler, 0L, value.toInt())
        override var stencilLoadOp: WGPULoadOp
            get() = (stencilLoadOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPULoadOp
            set(value) = stencilLoadOp_VH.set(handler.handler, 0L, value.toInt())
        override var stencilStoreOp: WGPUStoreOp
            get() = (stencilStoreOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStoreOp
            set(value) = stencilStoreOp_VH.set(handler.handler, 0L, value.toInt())
        override var stencilClearValue: UInt
            get() = (stencilClearValue_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = stencilClearValue_VH.set(handler.handler, 0L, value.toInt())
        override var stencilReadOnly: UInt
            get() = (stencilReadOnly_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = stencilReadOnly_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPURenderPassMaxDrawCount : CStructure {
    actual var chain: WGPUChainedStruct
    actual var maxDrawCount: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_LONG.withName("maxDrawCount")
        ).withName("WGPURenderPassMaxDrawCount")
        
        val maxDrawCount_VH: VarHandle = layout.varHandle(groupElement("maxDrawCount"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPassMaxDrawCount = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassMaxDrawCount) -> Unit): ArrayHolder<WGPURenderPassMaxDrawCount> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPassMaxDrawCount {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var maxDrawCount: ULong
            get() = (maxDrawCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = maxDrawCount_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPURequestAdapterWebXROptions : CStructure {
    actual var chain: WGPUChainedStruct
    actual var xrCompatible: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("xrCompatible"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPURequestAdapterWebXROptions")
        
        val xrCompatible_VH: VarHandle = layout.varHandle(groupElement("xrCompatible"))
        
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterWebXROptions = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterWebXROptions = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterWebXROptions) -> Unit): ArrayHolder<WGPURequestAdapterWebXROptions> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURequestAdapterWebXROptions {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var xrCompatible: UInt
            get() = (xrCompatible_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = xrCompatible_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSamplerBindingLayout : CStructure {
    actual var nextInChain: NativeAddress
    actual var type: WGPUSamplerBindingType
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("type"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUSamplerBindingLayout")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val type_VH: VarHandle = layout.varHandle(groupElement("type"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSamplerBindingLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerBindingLayout) -> Unit): ArrayHolder<WGPUSamplerBindingLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSamplerBindingLayout {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var type: WGPUSamplerBindingType
            get() = (type_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUSamplerBindingType
            set(value) = type_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSamplerDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var addressModeU: WGPUAddressMode
    actual var addressModeV: WGPUAddressMode
    actual var addressModeW: WGPUAddressMode
    actual var magFilter: WGPUFilterMode
    actual var minFilter: WGPUFilterMode
    actual var mipmapFilter: WGPUMipmapFilterMode
    actual var lodMinClamp: Float
    actual var lodMaxClamp: Float
    actual var compare: WGPUCompareFunction
    actual var maxAnisotropy: UShort
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_INT.withName("addressModeU"),
            ValueLayout.JAVA_INT.withName("addressModeV"),
            ValueLayout.JAVA_INT.withName("addressModeW"),
            ValueLayout.JAVA_INT.withName("magFilter"),
            ValueLayout.JAVA_INT.withName("minFilter"),
            ValueLayout.JAVA_INT.withName("mipmapFilter"),
            ValueLayout.JAVA_FLOAT.withName("lodMinClamp"),
            ValueLayout.JAVA_FLOAT.withName("lodMaxClamp"),
            ValueLayout.JAVA_INT.withName("compare"),
            ValueLayout.JAVA_SHORT.withName("maxAnisotropy"),
            MemoryLayout.paddingLayout(2)
        ).withName("WGPUSamplerDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val addressModeU_VH: VarHandle = layout.varHandle(groupElement("addressModeU"))
        val addressModeV_VH: VarHandle = layout.varHandle(groupElement("addressModeV"))
        val addressModeW_VH: VarHandle = layout.varHandle(groupElement("addressModeW"))
        val magFilter_VH: VarHandle = layout.varHandle(groupElement("magFilter"))
        val minFilter_VH: VarHandle = layout.varHandle(groupElement("minFilter"))
        val mipmapFilter_VH: VarHandle = layout.varHandle(groupElement("mipmapFilter"))
        val lodMinClamp_VH: VarHandle = layout.varHandle(groupElement("lodMinClamp"))
        val lodMaxClamp_VH: VarHandle = layout.varHandle(groupElement("lodMaxClamp"))
        val compare_VH: VarHandle = layout.varHandle(groupElement("compare"))
        val maxAnisotropy_VH: VarHandle = layout.varHandle(groupElement("maxAnisotropy"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSamplerDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerDescriptor) -> Unit): ArrayHolder<WGPUSamplerDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSamplerDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var addressModeU: WGPUAddressMode
            get() = (addressModeU_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUAddressMode
            set(value) = addressModeU_VH.set(handler.handler, 0L, value.toInt())
        override var addressModeV: WGPUAddressMode
            get() = (addressModeV_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUAddressMode
            set(value) = addressModeV_VH.set(handler.handler, 0L, value.toInt())
        override var addressModeW: WGPUAddressMode
            get() = (addressModeW_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUAddressMode
            set(value) = addressModeW_VH.set(handler.handler, 0L, value.toInt())
        override var magFilter: WGPUFilterMode
            get() = (magFilter_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUFilterMode
            set(value) = magFilter_VH.set(handler.handler, 0L, value.toInt())
        override var minFilter: WGPUFilterMode
            get() = (minFilter_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUFilterMode
            set(value) = minFilter_VH.set(handler.handler, 0L, value.toInt())
        override var mipmapFilter: WGPUMipmapFilterMode
            get() = (mipmapFilter_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUMipmapFilterMode
            set(value) = mipmapFilter_VH.set(handler.handler, 0L, value.toInt())
        override var lodMinClamp: Float
            get() = lodMinClamp_VH.get(handler.handler, 0L) as Float
            set(value) = lodMinClamp_VH.set(handler.handler, 0L, value)
        override var lodMaxClamp: Float
            get() = lodMaxClamp_VH.get(handler.handler, 0L) as Float
            set(value) = lodMaxClamp_VH.set(handler.handler, 0L, value)
        override var compare: WGPUCompareFunction
            get() = (compare_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCompareFunction
            set(value) = compare_VH.set(handler.handler, 0L, value.toInt())
        override var maxAnisotropy: UShort
            get() = (maxAnisotropy_VH.get(handler.handler, 0L) as Short).toUShort() as UShort
            set(value) = maxAnisotropy_VH.set(handler.handler, 0L, value.toShort())
    }
}

actual interface WGPUShaderSourceSPIRV : CStructure {
    actual var chain: WGPUChainedStruct
    actual var codeSize: UInt
    actual var code: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("codeSize"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("code")
        ).withName("WGPUShaderSourceSPIRV")
        
        val codeSize_VH: VarHandle = layout.varHandle(groupElement("codeSize"))
        val code_VH: VarHandle = layout.varHandle(groupElement("code"))
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceSPIRV = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceSPIRV) -> Unit): ArrayHolder<WGPUShaderSourceSPIRV> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderSourceSPIRV {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var codeSize: UInt
            get() = (codeSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = codeSize_VH.set(handler.handler, 0L, value.toInt())
        override var code: NativeAddress
            get() = (code_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = code_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUShaderSourceWGSL : CStructure {
    actual var chain: WGPUChainedStruct
    actual var code: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            WGPUStringView.layout.withName("code")
        ).withName("WGPUShaderSourceWGSL")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceWGSL = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceWGSL) -> Unit): ArrayHolder<WGPUShaderSourceWGSL> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderSourceWGSL {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var code: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("code")), Companion.layout.select(groupElement("code")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("code")), Companion.layout.select(groupElement("code")).byteSize())
            }
    }
}

actual interface WGPUStencilFaceState : CStructure {
    actual var compare: WGPUCompareFunction
    actual var failOp: WGPUStencilOperation
    actual var depthFailOp: WGPUStencilOperation
    actual var passOp: WGPUStencilOperation
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("compare"),
            ValueLayout.JAVA_INT.withName("failOp"),
            ValueLayout.JAVA_INT.withName("depthFailOp"),
            ValueLayout.JAVA_INT.withName("passOp")
        ).withName("WGPUStencilFaceState")
        
        val compare_VH: VarHandle = layout.varHandle(groupElement("compare"))
        val failOp_VH: VarHandle = layout.varHandle(groupElement("failOp"))
        val depthFailOp_VH: VarHandle = layout.varHandle(groupElement("depthFailOp"))
        val passOp_VH: VarHandle = layout.varHandle(groupElement("passOp"))
        
        actual operator fun invoke(address: NativeAddress): WGPUStencilFaceState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStencilFaceState) -> Unit): ArrayHolder<WGPUStencilFaceState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUStencilFaceState {
        override var compare: WGPUCompareFunction
            get() = (compare_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCompareFunction
            set(value) = compare_VH.set(handler.handler, 0L, value.toInt())
        override var failOp: WGPUStencilOperation
            get() = (failOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStencilOperation
            set(value) = failOp_VH.set(handler.handler, 0L, value.toInt())
        override var depthFailOp: WGPUStencilOperation
            get() = (depthFailOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStencilOperation
            set(value) = depthFailOp_VH.set(handler.handler, 0L, value.toInt())
        override var passOp: WGPUStencilOperation
            get() = (passOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStencilOperation
            set(value) = passOp_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUStorageTextureBindingLayout : CStructure {
    actual var nextInChain: NativeAddress
    actual var access: WGPUStorageTextureAccess
    actual var format: WGPUTextureFormat
    actual var viewDimension: WGPUTextureViewDimension
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("access"),
            ValueLayout.JAVA_INT.withName("format"),
            ValueLayout.JAVA_INT.withName("viewDimension"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUStorageTextureBindingLayout")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val access_VH: VarHandle = layout.varHandle(groupElement("access"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val viewDimension_VH: VarHandle = layout.varHandle(groupElement("viewDimension"))
        
        actual operator fun invoke(address: NativeAddress): WGPUStorageTextureBindingLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStorageTextureBindingLayout) -> Unit): ArrayHolder<WGPUStorageTextureBindingLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUStorageTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var access: WGPUStorageTextureAccess
            get() = (access_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStorageTextureAccess
            set(value) = access_VH.set(handler.handler, 0L, value.toInt())
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var viewDimension: WGPUTextureViewDimension
            get() = (viewDimension_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureViewDimension
            set(value) = viewDimension_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSupportedFeatures : CStructure {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("featureCount"),
            ValueLayout.ADDRESS.withName("features")
        ).withName("WGPUSupportedFeatures")
        
        val featureCount_VH: VarHandle = layout.varHandle(groupElement("featureCount"))
        val features_VH: VarHandle = layout.varHandle(groupElement("features"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSupportedFeatures = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedFeatures) -> Unit): ArrayHolder<WGPUSupportedFeatures> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSupportedFeatures {
        override var featureCount: ULong
            get() = (featureCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = featureCount_VH.set(handler.handler, 0L, value.toLong())
        override var features: NativeAddress
            get() = (features_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = features_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSupportedInstanceFeatures : CStructure {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("featureCount"),
            ValueLayout.ADDRESS.withName("features")
        ).withName("WGPUSupportedInstanceFeatures")
        
        val featureCount_VH: VarHandle = layout.varHandle(groupElement("featureCount"))
        val features_VH: VarHandle = layout.varHandle(groupElement("features"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSupportedInstanceFeatures = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedInstanceFeatures = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedInstanceFeatures) -> Unit): ArrayHolder<WGPUSupportedInstanceFeatures> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSupportedInstanceFeatures {
        override var featureCount: ULong
            get() = (featureCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = featureCount_VH.set(handler.handler, 0L, value.toLong())
        override var features: NativeAddress
            get() = (features_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = features_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSupportedWGSLLanguageFeatures : CStructure {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("featureCount"),
            ValueLayout.ADDRESS.withName("features")
        ).withName("WGPUSupportedWGSLLanguageFeatures")
        
        val featureCount_VH: VarHandle = layout.varHandle(groupElement("featureCount"))
        val features_VH: VarHandle = layout.varHandle(groupElement("features"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSupportedWGSLLanguageFeatures = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedWGSLLanguageFeatures = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedWGSLLanguageFeatures) -> Unit): ArrayHolder<WGPUSupportedWGSLLanguageFeatures> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSupportedWGSLLanguageFeatures {
        override var featureCount: ULong
            get() = (featureCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = featureCount_VH.set(handler.handler, 0L, value.toLong())
        override var features: NativeAddress
            get() = (features_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = features_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceCapabilities : CStructure {
    actual var nextInChain: NativeAddress
    actual var usages: ULong
    actual var formatCount: ULong
    actual var formats: NativeAddress
    actual var presentModeCount: ULong
    actual var presentModes: NativeAddress
    actual var alphaModeCount: ULong
    actual var alphaModes: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_LONG.withName("usages"),
            ValueLayout.JAVA_LONG.withName("formatCount"),
            ValueLayout.ADDRESS.withName("formats"),
            ValueLayout.JAVA_LONG.withName("presentModeCount"),
            ValueLayout.ADDRESS.withName("presentModes"),
            ValueLayout.JAVA_LONG.withName("alphaModeCount"),
            ValueLayout.ADDRESS.withName("alphaModes")
        ).withName("WGPUSurfaceCapabilities")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val usages_VH: VarHandle = layout.varHandle(groupElement("usages"))
        val formatCount_VH: VarHandle = layout.varHandle(groupElement("formatCount"))
        val formats_VH: VarHandle = layout.varHandle(groupElement("formats"))
        val presentModeCount_VH: VarHandle = layout.varHandle(groupElement("presentModeCount"))
        val presentModes_VH: VarHandle = layout.varHandle(groupElement("presentModes"))
        val alphaModeCount_VH: VarHandle = layout.varHandle(groupElement("alphaModeCount"))
        val alphaModes_VH: VarHandle = layout.varHandle(groupElement("alphaModes"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceCapabilities = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceCapabilities) -> Unit): ArrayHolder<WGPUSurfaceCapabilities> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceCapabilities {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var usages: ULong
            get() = (usages_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = usages_VH.set(handler.handler, 0L, value.toLong())
        override var formatCount: ULong
            get() = (formatCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = formatCount_VH.set(handler.handler, 0L, value.toLong())
        override var formats: NativeAddress
            get() = (formats_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = formats_VH.set(handler.handler, 0L, value.handler)
        override var presentModeCount: ULong
            get() = (presentModeCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = presentModeCount_VH.set(handler.handler, 0L, value.toLong())
        override var presentModes: NativeAddress
            get() = (presentModes_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = presentModes_VH.set(handler.handler, 0L, value.handler)
        override var alphaModeCount: ULong
            get() = (alphaModeCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = alphaModeCount_VH.set(handler.handler, 0L, value.toLong())
        override var alphaModes: NativeAddress
            get() = (alphaModes_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = alphaModes_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceColorManagement : CStructure {
    actual var chain: WGPUChainedStruct
    actual var colorSpace: WGPUPredefinedColorSpace
    actual var toneMappingMode: WGPUToneMappingMode
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("colorSpace"),
            ValueLayout.JAVA_INT.withName("toneMappingMode")
        ).withName("WGPUSurfaceColorManagement")
        
        val colorSpace_VH: VarHandle = layout.varHandle(groupElement("colorSpace"))
        val toneMappingMode_VH: VarHandle = layout.varHandle(groupElement("toneMappingMode"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceColorManagement = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceColorManagement = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceColorManagement) -> Unit): ArrayHolder<WGPUSurfaceColorManagement> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceColorManagement {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var colorSpace: WGPUPredefinedColorSpace
            get() = (colorSpace_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUPredefinedColorSpace
            set(value) = colorSpace_VH.set(handler.handler, 0L, value.toInt())
        override var toneMappingMode: WGPUToneMappingMode
            get() = (toneMappingMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUToneMappingMode
            set(value) = toneMappingMode_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSurfaceConfiguration : CStructure {
    actual var nextInChain: NativeAddress
    actual var device: WGPUDeviceImpl?
    actual var format: WGPUTextureFormat
    actual var usage: ULong
    actual var width: UInt
    actual var height: UInt
    actual var viewFormatCount: ULong
    actual var viewFormats: NativeAddress
    actual var alphaMode: WGPUCompositeAlphaMode
    actual var presentMode: WGPUPresentMode
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("device"),
            ValueLayout.JAVA_INT.withName("format"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("usage"),
            ValueLayout.JAVA_INT.withName("width"),
            ValueLayout.JAVA_INT.withName("height"),
            ValueLayout.JAVA_LONG.withName("viewFormatCount"),
            ValueLayout.ADDRESS.withName("viewFormats"),
            ValueLayout.JAVA_INT.withName("alphaMode"),
            ValueLayout.JAVA_INT.withName("presentMode")
        ).withName("WGPUSurfaceConfiguration")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val device_VH: VarHandle = layout.varHandle(groupElement("device"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val usage_VH: VarHandle = layout.varHandle(groupElement("usage"))
        val width_VH: VarHandle = layout.varHandle(groupElement("width"))
        val height_VH: VarHandle = layout.varHandle(groupElement("height"))
        val viewFormatCount_VH: VarHandle = layout.varHandle(groupElement("viewFormatCount"))
        val viewFormats_VH: VarHandle = layout.varHandle(groupElement("viewFormats"))
        val alphaMode_VH: VarHandle = layout.varHandle(groupElement("alphaMode"))
        val presentMode_VH: VarHandle = layout.varHandle(groupElement("presentMode"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceConfiguration = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfiguration) -> Unit): ArrayHolder<WGPUSurfaceConfiguration> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceConfiguration {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var device: WGPUDeviceImpl?
            get() = (device_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUDeviceImpl(it) }
            set(value) = device_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var usage: ULong
            get() = (usage_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = usage_VH.set(handler.handler, 0L, value.toLong())
        override var width: UInt
            get() = (width_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = width_VH.set(handler.handler, 0L, value.toInt())
        override var height: UInt
            get() = (height_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = height_VH.set(handler.handler, 0L, value.toInt())
        override var viewFormatCount: ULong
            get() = (viewFormatCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = viewFormatCount_VH.set(handler.handler, 0L, value.toLong())
        override var viewFormats: NativeAddress
            get() = (viewFormats_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = viewFormats_VH.set(handler.handler, 0L, value.handler)
        override var alphaMode: WGPUCompositeAlphaMode
            get() = (alphaMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCompositeAlphaMode
            set(value) = alphaMode_VH.set(handler.handler, 0L, value.toInt())
        override var presentMode: WGPUPresentMode
            get() = (presentMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUPresentMode
            set(value) = presentMode_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSurfaceSourceAndroidNativeWindow : CStructure {
    actual var chain: WGPUChainedStruct
    actual var window: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("window")
        ).withName("WGPUSurfaceSourceAndroidNativeWindow")
        
        val window_VH: VarHandle = layout.varHandle(groupElement("window"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceAndroidNativeWindow = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceAndroidNativeWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceAndroidNativeWindow> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceAndroidNativeWindow {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var window: NativeAddress
            get() = (window_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = window_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceSourceMetalLayer : CStructure {
    actual var chain: WGPUChainedStruct
    actual var layer: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("layer")
        ).withName("WGPUSurfaceSourceMetalLayer")
        
        val layer_VH: VarHandle = layout.varHandle(groupElement("layer"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceMetalLayer = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceMetalLayer) -> Unit): ArrayHolder<WGPUSurfaceSourceMetalLayer> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceMetalLayer {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var layer: NativeAddress
            get() = (layer_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = layer_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceSourceWaylandSurface : CStructure {
    actual var chain: WGPUChainedStruct
    actual var display: NativeAddress
    actual var surface: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("display"),
            ValueLayout.ADDRESS.withName("surface")
        ).withName("WGPUSurfaceSourceWaylandSurface")
        
        val display_VH: VarHandle = layout.varHandle(groupElement("display"))
        val surface_VH: VarHandle = layout.varHandle(groupElement("surface"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWaylandSurface = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWaylandSurface) -> Unit): ArrayHolder<WGPUSurfaceSourceWaylandSurface> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceWaylandSurface {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var display: NativeAddress
            get() = (display_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = display_VH.set(handler.handler, 0L, value.handler)
        override var surface: NativeAddress
            get() = (surface_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = surface_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceSourceWindowsHWND : CStructure {
    actual var chain: WGPUChainedStruct
    actual var hinstance: NativeAddress
    actual var hwnd: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("hinstance"),
            ValueLayout.ADDRESS.withName("hwnd")
        ).withName("WGPUSurfaceSourceWindowsHWND")
        
        val hinstance_VH: VarHandle = layout.varHandle(groupElement("hinstance"))
        val hwnd_VH: VarHandle = layout.varHandle(groupElement("hwnd"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWindowsHWND = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWindowsHWND) -> Unit): ArrayHolder<WGPUSurfaceSourceWindowsHWND> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceWindowsHWND {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var hinstance: NativeAddress
            get() = (hinstance_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = hinstance_VH.set(handler.handler, 0L, value.handler)
        override var hwnd: NativeAddress
            get() = (hwnd_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = hwnd_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUSurfaceSourceXCBWindow : CStructure {
    actual var chain: WGPUChainedStruct
    actual var connection: NativeAddress
    actual var window: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("connection"),
            ValueLayout.JAVA_INT.withName("window"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUSurfaceSourceXCBWindow")
        
        val connection_VH: VarHandle = layout.varHandle(groupElement("connection"))
        val window_VH: VarHandle = layout.varHandle(groupElement("window"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXCBWindow = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXCBWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXCBWindow> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceXCBWindow {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var connection: NativeAddress
            get() = (connection_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = connection_VH.set(handler.handler, 0L, value.handler)
        override var window: UInt
            get() = (window_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = window_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSurfaceSourceXlibWindow : CStructure {
    actual var chain: WGPUChainedStruct
    actual var display: NativeAddress
    actual var window: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("display"),
            ValueLayout.JAVA_LONG.withName("window")
        ).withName("WGPUSurfaceSourceXlibWindow")
        
        val display_VH: VarHandle = layout.varHandle(groupElement("display"))
        val window_VH: VarHandle = layout.varHandle(groupElement("window"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXlibWindow = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXlibWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXlibWindow> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceXlibWindow {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var display: NativeAddress
            get() = (display_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = display_VH.set(handler.handler, 0L, value.handler)
        override var window: ULong
            get() = (window_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = window_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUSurfaceTexture : CStructure {
    actual var nextInChain: NativeAddress
    actual var texture: WGPUTextureImpl?
    actual var status: WGPUSurfaceGetCurrentTextureStatus
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("texture"),
            ValueLayout.JAVA_INT.withName("status"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUSurfaceTexture")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val texture_VH: VarHandle = layout.varHandle(groupElement("texture"))
        val status_VH: VarHandle = layout.varHandle(groupElement("status"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceTexture = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceTexture) -> Unit): ArrayHolder<WGPUSurfaceTexture> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceTexture {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var texture: WGPUTextureImpl?
            get() = (texture_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureImpl(it) }
            set(value) = texture_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var status: WGPUSurfaceGetCurrentTextureStatus
            get() = (status_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUSurfaceGetCurrentTextureStatus
            set(value) = status_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUTexelCopyBufferLayout : CStructure {
    actual var offset: ULong
    actual var bytesPerRow: UInt
    actual var rowsPerImage: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("offset"),
            ValueLayout.JAVA_INT.withName("bytesPerRow"),
            ValueLayout.JAVA_INT.withName("rowsPerImage")
        ).withName("WGPUTexelCopyBufferLayout")
        
        val offset_VH: VarHandle = layout.varHandle(groupElement("offset"))
        val bytesPerRow_VH: VarHandle = layout.varHandle(groupElement("bytesPerRow"))
        val rowsPerImage_VH: VarHandle = layout.varHandle(groupElement("rowsPerImage"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferLayout) -> Unit): ArrayHolder<WGPUTexelCopyBufferLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTexelCopyBufferLayout {
        override var offset: ULong
            get() = (offset_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = offset_VH.set(handler.handler, 0L, value.toLong())
        override var bytesPerRow: UInt
            get() = (bytesPerRow_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = bytesPerRow_VH.set(handler.handler, 0L, value.toInt())
        override var rowsPerImage: UInt
            get() = (rowsPerImage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = rowsPerImage_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUTextureBindingLayout : CStructure {
    actual var nextInChain: NativeAddress
    actual var sampleType: WGPUTextureSampleType
    actual var viewDimension: WGPUTextureViewDimension
    actual var multisampled: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("sampleType"),
            ValueLayout.JAVA_INT.withName("viewDimension"),
            ValueLayout.JAVA_INT.withName("multisampled"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUTextureBindingLayout")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val sampleType_VH: VarHandle = layout.varHandle(groupElement("sampleType"))
        val viewDimension_VH: VarHandle = layout.varHandle(groupElement("viewDimension"))
        val multisampled_VH: VarHandle = layout.varHandle(groupElement("multisampled"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureBindingLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingLayout) -> Unit): ArrayHolder<WGPUTextureBindingLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var sampleType: WGPUTextureSampleType
            get() = (sampleType_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureSampleType
            set(value) = sampleType_VH.set(handler.handler, 0L, value.toInt())
        override var viewDimension: WGPUTextureViewDimension
            get() = (viewDimension_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureViewDimension
            set(value) = viewDimension_VH.set(handler.handler, 0L, value.toInt())
        override var multisampled: UInt
            get() = (multisampled_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = multisampled_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUTextureBindingViewDimension : CStructure {
    actual var chain: WGPUChainedStruct
    actual var textureBindingViewDimension: WGPUTextureViewDimension
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("textureBindingViewDimension"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUTextureBindingViewDimension")
        
        val textureBindingViewDimension_VH: VarHandle = layout.varHandle(groupElement("textureBindingViewDimension"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureBindingViewDimension = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingViewDimension = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingViewDimension) -> Unit): ArrayHolder<WGPUTextureBindingViewDimension> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureBindingViewDimension {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var textureBindingViewDimension: WGPUTextureViewDimension
            get() = (textureBindingViewDimension_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureViewDimension
            set(value) = textureBindingViewDimension_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUTextureComponentSwizzle : CStructure {
    actual var r: WGPUComponentSwizzle
    actual var g: WGPUComponentSwizzle
    actual var b: WGPUComponentSwizzle
    actual var a: WGPUComponentSwizzle
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("r"),
            ValueLayout.JAVA_INT.withName("g"),
            ValueLayout.JAVA_INT.withName("b"),
            ValueLayout.JAVA_INT.withName("a")
        ).withName("WGPUTextureComponentSwizzle")
        
        val r_VH: VarHandle = layout.varHandle(groupElement("r"))
        val g_VH: VarHandle = layout.varHandle(groupElement("g"))
        val b_VH: VarHandle = layout.varHandle(groupElement("b"))
        val a_VH: VarHandle = layout.varHandle(groupElement("a"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzle = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzle = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzle) -> Unit): ArrayHolder<WGPUTextureComponentSwizzle> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureComponentSwizzle {
        override var r: WGPUComponentSwizzle
            get() = (r_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUComponentSwizzle
            set(value) = r_VH.set(handler.handler, 0L, value.toInt())
        override var g: WGPUComponentSwizzle
            get() = (g_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUComponentSwizzle
            set(value) = g_VH.set(handler.handler, 0L, value.toInt())
        override var b: WGPUComponentSwizzle
            get() = (b_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUComponentSwizzle
            set(value) = b_VH.set(handler.handler, 0L, value.toInt())
        override var a: WGPUComponentSwizzle
            get() = (a_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUComponentSwizzle
            set(value) = a_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUVertexAttribute : CStructure {
    actual var nextInChain: NativeAddress
    actual var format: WGPUVertexFormat
    actual var offset: ULong
    actual var shaderLocation: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("format"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("offset"),
            ValueLayout.JAVA_INT.withName("shaderLocation"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUVertexAttribute")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val offset_VH: VarHandle = layout.varHandle(groupElement("offset"))
        val shaderLocation_VH: VarHandle = layout.varHandle(groupElement("shaderLocation"))
        
        actual operator fun invoke(address: NativeAddress): WGPUVertexAttribute = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexAttribute) -> Unit): ArrayHolder<WGPUVertexAttribute> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUVertexAttribute {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var format: WGPUVertexFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUVertexFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var offset: ULong
            get() = (offset_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = offset_VH.set(handler.handler, 0L, value.toLong())
        override var shaderLocation: UInt
            get() = (shaderLocation_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = shaderLocation_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUBindGroupEntry : CStructure {
    actual var nextInChain: NativeAddress
    actual var binding: UInt
    actual var buffer: WGPUBufferImpl?
    actual var offset: ULong
    actual var size: ULong
    actual var sampler: WGPUSamplerImpl?
    actual var textureView: WGPUTextureViewImpl?
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("binding"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("buffer"),
            ValueLayout.JAVA_LONG.withName("offset"),
            ValueLayout.JAVA_LONG.withName("size"),
            ValueLayout.ADDRESS.withName("sampler"),
            ValueLayout.ADDRESS.withName("textureView")
        ).withName("WGPUBindGroupEntry")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val binding_VH: VarHandle = layout.varHandle(groupElement("binding"))
        val buffer_VH: VarHandle = layout.varHandle(groupElement("buffer"))
        val offset_VH: VarHandle = layout.varHandle(groupElement("offset"))
        val size_VH: VarHandle = layout.varHandle(groupElement("size"))
        val sampler_VH: VarHandle = layout.varHandle(groupElement("sampler"))
        val textureView_VH: VarHandle = layout.varHandle(groupElement("textureView"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupEntry = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntry) -> Unit): ArrayHolder<WGPUBindGroupEntry> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupEntry {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var binding: UInt
            get() = (binding_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = binding_VH.set(handler.handler, 0L, value.toInt())
        override var buffer: WGPUBufferImpl?
            get() = (buffer_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUBufferImpl(it) }
            set(value) = buffer_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var offset: ULong
            get() = (offset_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = offset_VH.set(handler.handler, 0L, value.toLong())
        override var size: ULong
            get() = (size_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = size_VH.set(handler.handler, 0L, value.toLong())
        override var sampler: WGPUSamplerImpl?
            get() = (sampler_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUSamplerImpl(it) }
            set(value) = sampler_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var textureView: WGPUTextureViewImpl?
            get() = (textureView_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureViewImpl(it) }
            set(value) = textureView_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
    }
}

actual interface WGPUBindGroupLayoutEntry : CStructure {
    actual var nextInChain: NativeAddress
    actual var binding: UInt
    actual var visibility: ULong
    actual var bindingArraySize: UInt
    actual var buffer: WGPUBufferBindingLayout
    actual var sampler: WGPUSamplerBindingLayout
    actual var texture: WGPUTextureBindingLayout
    actual var storageTexture: WGPUStorageTextureBindingLayout
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("binding"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("visibility"),
            ValueLayout.JAVA_INT.withName("bindingArraySize"),
            MemoryLayout.paddingLayout(4),
            WGPUBufferBindingLayout.layout.withName("buffer"),
            WGPUSamplerBindingLayout.layout.withName("sampler"),
            WGPUTextureBindingLayout.layout.withName("texture"),
            WGPUStorageTextureBindingLayout.layout.withName("storageTexture")
        ).withName("WGPUBindGroupLayoutEntry")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val binding_VH: VarHandle = layout.varHandle(groupElement("binding"))
        val visibility_VH: VarHandle = layout.varHandle(groupElement("visibility"))
        val bindingArraySize_VH: VarHandle = layout.varHandle(groupElement("bindingArraySize"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntry = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntry) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntry> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutEntry {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var binding: UInt
            get() = (binding_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = binding_VH.set(handler.handler, 0L, value.toInt())
        override var visibility: ULong
            get() = (visibility_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = visibility_VH.set(handler.handler, 0L, value.toLong())
        override var bindingArraySize: UInt
            get() = (bindingArraySize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = bindingArraySize_VH.set(handler.handler, 0L, value.toInt())
        override var buffer: WGPUBufferBindingLayout
            get() = WGPUBufferBindingLayout(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("buffer")), Companion.layout.select(groupElement("buffer")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("buffer")), Companion.layout.select(groupElement("buffer")).byteSize())
            }
        override var sampler: WGPUSamplerBindingLayout
            get() = WGPUSamplerBindingLayout(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("sampler")), Companion.layout.select(groupElement("sampler")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("sampler")), Companion.layout.select(groupElement("sampler")).byteSize())
            }
        override var texture: WGPUTextureBindingLayout
            get() = WGPUTextureBindingLayout(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("texture")), Companion.layout.select(groupElement("texture")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("texture")), Companion.layout.select(groupElement("texture")).byteSize())
            }
        override var storageTexture: WGPUStorageTextureBindingLayout
            get() = WGPUStorageTextureBindingLayout(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("storageTexture")), Companion.layout.select(groupElement("storageTexture")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("storageTexture")), Companion.layout.select(groupElement("storageTexture")).byteSize())
            }
    }
}

actual interface WGPUBlendState : CStructure {
    actual var color: WGPUBlendComponent
    actual var alpha: WGPUBlendComponent
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUBlendComponent.layout.withName("color"),
            WGPUBlendComponent.layout.withName("alpha")
        ).withName("WGPUBlendState")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUBlendState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBlendState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendState) -> Unit): ArrayHolder<WGPUBlendState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBlendState {
        override var color: WGPUBlendComponent
            get() = WGPUBlendComponent(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("color")), Companion.layout.select(groupElement("color")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("color")), Companion.layout.select(groupElement("color")).byteSize())
            }
        override var alpha: WGPUBlendComponent
            get() = WGPUBlendComponent(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("alpha")), Companion.layout.select(groupElement("alpha")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("alpha")), Companion.layout.select(groupElement("alpha")).byteSize())
            }
    }
}

actual interface WGPUCompilationInfo : CStructure {
    actual var nextInChain: NativeAddress
    actual var messageCount: ULong
    actual var messages: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_LONG.withName("messageCount"),
            ValueLayout.ADDRESS.withName("messages")
        ).withName("WGPUCompilationInfo")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val messageCount_VH: VarHandle = layout.varHandle(groupElement("messageCount"))
        val messages_VH: VarHandle = layout.varHandle(groupElement("messages"))
        
        actual operator fun invoke(address: NativeAddress): WGPUCompilationInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfo) -> Unit): ArrayHolder<WGPUCompilationInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUCompilationInfo {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var messageCount: ULong
            get() = (messageCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = messageCount_VH.set(handler.handler, 0L, value.toLong())
        override var messages: NativeAddress
            get() = (messages_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = messages_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUComputePassDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var timestampWrites: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.ADDRESS.withName("timestampWrites")
        ).withName("WGPUComputePassDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val timestampWrites_VH: VarHandle = layout.varHandle(groupElement("timestampWrites"))
        
        actual operator fun invoke(address: NativeAddress): WGPUComputePassDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePassDescriptor) -> Unit): ArrayHolder<WGPUComputePassDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUComputePassDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var timestampWrites: NativeAddress
            get() = (timestampWrites_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = timestampWrites_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUComputeState : CStructure {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("module"),
            WGPUStringView.layout.withName("entryPoint"),
            ValueLayout.JAVA_LONG.withName("constantCount"),
            ValueLayout.ADDRESS.withName("constants")
        ).withName("WGPUComputeState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val module_VH: VarHandle = layout.varHandle(groupElement("module"))
        val constantCount_VH: VarHandle = layout.varHandle(groupElement("constantCount"))
        val constants_VH: VarHandle = layout.varHandle(groupElement("constants"))
        
        actual operator fun invoke(address: NativeAddress): WGPUComputeState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUComputeState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputeState) -> Unit): ArrayHolder<WGPUComputeState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUComputeState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var module: WGPUShaderModuleImpl?
            get() = (module_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUShaderModuleImpl(it) }
            set(value) = module_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var entryPoint: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())
            }
        override var constantCount: ULong
            get() = (constantCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = constantCount_VH.set(handler.handler, 0L, value.toLong())
        override var constants: NativeAddress
            get() = (constants_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = constants_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUDepthStencilState : CStructure {
    actual var nextInChain: NativeAddress
    actual var format: WGPUTextureFormat
    actual var depthWriteEnabled: WGPUOptionalBool
    actual var depthCompare: WGPUCompareFunction
    actual var stencilFront: WGPUStencilFaceState
    actual var stencilBack: WGPUStencilFaceState
    actual var stencilReadMask: UInt
    actual var stencilWriteMask: UInt
    actual var depthBias: Int
    actual var depthBiasSlopeScale: Float
    actual var depthBiasClamp: Float
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("format"),
            ValueLayout.JAVA_INT.withName("depthWriteEnabled"),
            ValueLayout.JAVA_INT.withName("depthCompare"),
            WGPUStencilFaceState.layout.withName("stencilFront"),
            WGPUStencilFaceState.layout.withName("stencilBack"),
            ValueLayout.JAVA_INT.withName("stencilReadMask"),
            ValueLayout.JAVA_INT.withName("stencilWriteMask"),
            ValueLayout.JAVA_INT.withName("depthBias"),
            ValueLayout.JAVA_FLOAT.withName("depthBiasSlopeScale"),
            ValueLayout.JAVA_FLOAT.withName("depthBiasClamp")
        ).withName("WGPUDepthStencilState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val depthWriteEnabled_VH: VarHandle = layout.varHandle(groupElement("depthWriteEnabled"))
        val depthCompare_VH: VarHandle = layout.varHandle(groupElement("depthCompare"))
        val stencilReadMask_VH: VarHandle = layout.varHandle(groupElement("stencilReadMask"))
        val stencilWriteMask_VH: VarHandle = layout.varHandle(groupElement("stencilWriteMask"))
        val depthBias_VH: VarHandle = layout.varHandle(groupElement("depthBias"))
        val depthBiasSlopeScale_VH: VarHandle = layout.varHandle(groupElement("depthBiasSlopeScale"))
        val depthBiasClamp_VH: VarHandle = layout.varHandle(groupElement("depthBiasClamp"))
        
        actual operator fun invoke(address: NativeAddress): WGPUDepthStencilState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDepthStencilState) -> Unit): ArrayHolder<WGPUDepthStencilState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUDepthStencilState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var depthWriteEnabled: WGPUOptionalBool
            get() = (depthWriteEnabled_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUOptionalBool
            set(value) = depthWriteEnabled_VH.set(handler.handler, 0L, value.toInt())
        override var depthCompare: WGPUCompareFunction
            get() = (depthCompare_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUCompareFunction
            set(value) = depthCompare_VH.set(handler.handler, 0L, value.toInt())
        override var stencilFront: WGPUStencilFaceState
            get() = WGPUStencilFaceState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("stencilFront")), Companion.layout.select(groupElement("stencilFront")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("stencilFront")), Companion.layout.select(groupElement("stencilFront")).byteSize())
            }
        override var stencilBack: WGPUStencilFaceState
            get() = WGPUStencilFaceState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("stencilBack")), Companion.layout.select(groupElement("stencilBack")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("stencilBack")), Companion.layout.select(groupElement("stencilBack")).byteSize())
            }
        override var stencilReadMask: UInt
            get() = (stencilReadMask_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = stencilReadMask_VH.set(handler.handler, 0L, value.toInt())
        override var stencilWriteMask: UInt
            get() = (stencilWriteMask_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = stencilWriteMask_VH.set(handler.handler, 0L, value.toInt())
        override var depthBias: Int
            get() = depthBias_VH.get(handler.handler, 0L) as Int
            set(value) = depthBias_VH.set(handler.handler, 0L, value)
        override var depthBiasSlopeScale: Float
            get() = depthBiasSlopeScale_VH.get(handler.handler, 0L) as Float
            set(value) = depthBiasSlopeScale_VH.set(handler.handler, 0L, value)
        override var depthBiasClamp: Float
            get() = depthBiasClamp_VH.get(handler.handler, 0L) as Float
            set(value) = depthBiasClamp_VH.set(handler.handler, 0L, value)
    }
}

actual interface WGPUFutureWaitInfo : CStructure {
    actual var future: WGPUFuture
    actual var completed: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUFuture.layout.withName("future"),
            ValueLayout.JAVA_INT.withName("completed"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUFutureWaitInfo")
        
        val completed_VH: VarHandle = layout.varHandle(groupElement("completed"))
        
        actual operator fun invoke(address: NativeAddress): WGPUFutureWaitInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFutureWaitInfo) -> Unit): ArrayHolder<WGPUFutureWaitInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUFutureWaitInfo {
        override var future: WGPUFuture
            get() = WGPUFuture(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("future")), Companion.layout.select(groupElement("future")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("future")), Companion.layout.select(groupElement("future")).byteSize())
            }
        override var completed: UInt
            get() = (completed_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = completed_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUInstanceDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var requiredFeatureCount: ULong
    actual var requiredFeatures: NativeAddress
    actual var requiredLimits: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_LONG.withName("requiredFeatureCount"),
            ValueLayout.ADDRESS.withName("requiredFeatures"),
            ValueLayout.ADDRESS.withName("requiredLimits")
        ).withName("WGPUInstanceDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val requiredFeatureCount_VH: VarHandle = layout.varHandle(groupElement("requiredFeatureCount"))
        val requiredFeatures_VH: VarHandle = layout.varHandle(groupElement("requiredFeatures"))
        val requiredLimits_VH: VarHandle = layout.varHandle(groupElement("requiredLimits"))
        
        actual operator fun invoke(address: NativeAddress): WGPUInstanceDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceDescriptor) -> Unit): ArrayHolder<WGPUInstanceDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUInstanceDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var requiredFeatureCount: ULong
            get() = (requiredFeatureCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = requiredFeatureCount_VH.set(handler.handler, 0L, value.toLong())
        override var requiredFeatures: NativeAddress
            get() = (requiredFeatures_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = requiredFeatures_VH.set(handler.handler, 0L, value.handler)
        override var requiredLimits: NativeAddress
            get() = (requiredLimits_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = requiredLimits_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPULimits : CStructure {
    actual var nextInChain: NativeAddress
    actual var maxTextureDimension1D: UInt
    actual var maxTextureDimension2D: UInt
    actual var maxTextureDimension3D: UInt
    actual var maxTextureArrayLayers: UInt
    actual var maxBindGroups: UInt
    actual var maxBindGroupsPlusVertexBuffers: UInt
    actual var maxBindingsPerBindGroup: UInt
    actual var maxDynamicUniformBuffersPerPipelineLayout: UInt
    actual var maxDynamicStorageBuffersPerPipelineLayout: UInt
    actual var maxSampledTexturesPerShaderStage: UInt
    actual var maxSamplersPerShaderStage: UInt
    actual var maxStorageBuffersPerShaderStage: UInt
    actual var maxStorageTexturesPerShaderStage: UInt
    actual var maxUniformBuffersPerShaderStage: UInt
    actual var maxUniformBufferBindingSize: ULong
    actual var maxStorageBufferBindingSize: ULong
    actual var minUniformBufferOffsetAlignment: UInt
    actual var minStorageBufferOffsetAlignment: UInt
    actual var maxVertexBuffers: UInt
    actual var maxBufferSize: ULong
    actual var maxVertexAttributes: UInt
    actual var maxVertexBufferArrayStride: UInt
    actual var maxInterStageShaderVariables: UInt
    actual var maxColorAttachments: UInt
    actual var maxColorAttachmentBytesPerSample: UInt
    actual var maxComputeWorkgroupStorageSize: UInt
    actual var maxComputeInvocationsPerWorkgroup: UInt
    actual var maxComputeWorkgroupSizeX: UInt
    actual var maxComputeWorkgroupSizeY: UInt
    actual var maxComputeWorkgroupSizeZ: UInt
    actual var maxComputeWorkgroupsPerDimension: UInt
    actual var maxImmediateSize: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("maxTextureDimension1D"),
            ValueLayout.JAVA_INT.withName("maxTextureDimension2D"),
            ValueLayout.JAVA_INT.withName("maxTextureDimension3D"),
            ValueLayout.JAVA_INT.withName("maxTextureArrayLayers"),
            ValueLayout.JAVA_INT.withName("maxBindGroups"),
            ValueLayout.JAVA_INT.withName("maxBindGroupsPlusVertexBuffers"),
            ValueLayout.JAVA_INT.withName("maxBindingsPerBindGroup"),
            ValueLayout.JAVA_INT.withName("maxDynamicUniformBuffersPerPipelineLayout"),
            ValueLayout.JAVA_INT.withName("maxDynamicStorageBuffersPerPipelineLayout"),
            ValueLayout.JAVA_INT.withName("maxSampledTexturesPerShaderStage"),
            ValueLayout.JAVA_INT.withName("maxSamplersPerShaderStage"),
            ValueLayout.JAVA_INT.withName("maxStorageBuffersPerShaderStage"),
            ValueLayout.JAVA_INT.withName("maxStorageTexturesPerShaderStage"),
            ValueLayout.JAVA_INT.withName("maxUniformBuffersPerShaderStage"),
            ValueLayout.JAVA_LONG.withName("maxUniformBufferBindingSize"),
            ValueLayout.JAVA_LONG.withName("maxStorageBufferBindingSize"),
            ValueLayout.JAVA_INT.withName("minUniformBufferOffsetAlignment"),
            ValueLayout.JAVA_INT.withName("minStorageBufferOffsetAlignment"),
            ValueLayout.JAVA_INT.withName("maxVertexBuffers"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("maxBufferSize"),
            ValueLayout.JAVA_INT.withName("maxVertexAttributes"),
            ValueLayout.JAVA_INT.withName("maxVertexBufferArrayStride"),
            ValueLayout.JAVA_INT.withName("maxInterStageShaderVariables"),
            ValueLayout.JAVA_INT.withName("maxColorAttachments"),
            ValueLayout.JAVA_INT.withName("maxColorAttachmentBytesPerSample"),
            ValueLayout.JAVA_INT.withName("maxComputeWorkgroupStorageSize"),
            ValueLayout.JAVA_INT.withName("maxComputeInvocationsPerWorkgroup"),
            ValueLayout.JAVA_INT.withName("maxComputeWorkgroupSizeX"),
            ValueLayout.JAVA_INT.withName("maxComputeWorkgroupSizeY"),
            ValueLayout.JAVA_INT.withName("maxComputeWorkgroupSizeZ"),
            ValueLayout.JAVA_INT.withName("maxComputeWorkgroupsPerDimension"),
            ValueLayout.JAVA_INT.withName("maxImmediateSize")
        ).withName("WGPULimits")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val maxTextureDimension1D_VH: VarHandle = layout.varHandle(groupElement("maxTextureDimension1D"))
        val maxTextureDimension2D_VH: VarHandle = layout.varHandle(groupElement("maxTextureDimension2D"))
        val maxTextureDimension3D_VH: VarHandle = layout.varHandle(groupElement("maxTextureDimension3D"))
        val maxTextureArrayLayers_VH: VarHandle = layout.varHandle(groupElement("maxTextureArrayLayers"))
        val maxBindGroups_VH: VarHandle = layout.varHandle(groupElement("maxBindGroups"))
        val maxBindGroupsPlusVertexBuffers_VH: VarHandle = layout.varHandle(groupElement("maxBindGroupsPlusVertexBuffers"))
        val maxBindingsPerBindGroup_VH: VarHandle = layout.varHandle(groupElement("maxBindingsPerBindGroup"))
        val maxDynamicUniformBuffersPerPipelineLayout_VH: VarHandle = layout.varHandle(groupElement("maxDynamicUniformBuffersPerPipelineLayout"))
        val maxDynamicStorageBuffersPerPipelineLayout_VH: VarHandle = layout.varHandle(groupElement("maxDynamicStorageBuffersPerPipelineLayout"))
        val maxSampledTexturesPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxSampledTexturesPerShaderStage"))
        val maxSamplersPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxSamplersPerShaderStage"))
        val maxStorageBuffersPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageBuffersPerShaderStage"))
        val maxStorageTexturesPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxStorageTexturesPerShaderStage"))
        val maxUniformBuffersPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxUniformBuffersPerShaderStage"))
        val maxUniformBufferBindingSize_VH: VarHandle = layout.varHandle(groupElement("maxUniformBufferBindingSize"))
        val maxStorageBufferBindingSize_VH: VarHandle = layout.varHandle(groupElement("maxStorageBufferBindingSize"))
        val minUniformBufferOffsetAlignment_VH: VarHandle = layout.varHandle(groupElement("minUniformBufferOffsetAlignment"))
        val minStorageBufferOffsetAlignment_VH: VarHandle = layout.varHandle(groupElement("minStorageBufferOffsetAlignment"))
        val maxVertexBuffers_VH: VarHandle = layout.varHandle(groupElement("maxVertexBuffers"))
        val maxBufferSize_VH: VarHandle = layout.varHandle(groupElement("maxBufferSize"))
        val maxVertexAttributes_VH: VarHandle = layout.varHandle(groupElement("maxVertexAttributes"))
        val maxVertexBufferArrayStride_VH: VarHandle = layout.varHandle(groupElement("maxVertexBufferArrayStride"))
        val maxInterStageShaderVariables_VH: VarHandle = layout.varHandle(groupElement("maxInterStageShaderVariables"))
        val maxColorAttachments_VH: VarHandle = layout.varHandle(groupElement("maxColorAttachments"))
        val maxColorAttachmentBytesPerSample_VH: VarHandle = layout.varHandle(groupElement("maxColorAttachmentBytesPerSample"))
        val maxComputeWorkgroupStorageSize_VH: VarHandle = layout.varHandle(groupElement("maxComputeWorkgroupStorageSize"))
        val maxComputeInvocationsPerWorkgroup_VH: VarHandle = layout.varHandle(groupElement("maxComputeInvocationsPerWorkgroup"))
        val maxComputeWorkgroupSizeX_VH: VarHandle = layout.varHandle(groupElement("maxComputeWorkgroupSizeX"))
        val maxComputeWorkgroupSizeY_VH: VarHandle = layout.varHandle(groupElement("maxComputeWorkgroupSizeY"))
        val maxComputeWorkgroupSizeZ_VH: VarHandle = layout.varHandle(groupElement("maxComputeWorkgroupSizeZ"))
        val maxComputeWorkgroupsPerDimension_VH: VarHandle = layout.varHandle(groupElement("maxComputeWorkgroupsPerDimension"))
        val maxImmediateSize_VH: VarHandle = layout.varHandle(groupElement("maxImmediateSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPULimits = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPULimits = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPULimits) -> Unit): ArrayHolder<WGPULimits> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPULimits {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var maxTextureDimension1D: UInt
            get() = (maxTextureDimension1D_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxTextureDimension1D_VH.set(handler.handler, 0L, value.toInt())
        override var maxTextureDimension2D: UInt
            get() = (maxTextureDimension2D_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxTextureDimension2D_VH.set(handler.handler, 0L, value.toInt())
        override var maxTextureDimension3D: UInt
            get() = (maxTextureDimension3D_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxTextureDimension3D_VH.set(handler.handler, 0L, value.toInt())
        override var maxTextureArrayLayers: UInt
            get() = (maxTextureArrayLayers_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxTextureArrayLayers_VH.set(handler.handler, 0L, value.toInt())
        override var maxBindGroups: UInt
            get() = (maxBindGroups_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxBindGroups_VH.set(handler.handler, 0L, value.toInt())
        override var maxBindGroupsPlusVertexBuffers: UInt
            get() = (maxBindGroupsPlusVertexBuffers_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxBindGroupsPlusVertexBuffers_VH.set(handler.handler, 0L, value.toInt())
        override var maxBindingsPerBindGroup: UInt
            get() = (maxBindingsPerBindGroup_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxBindingsPerBindGroup_VH.set(handler.handler, 0L, value.toInt())
        override var maxDynamicUniformBuffersPerPipelineLayout: UInt
            get() = (maxDynamicUniformBuffersPerPipelineLayout_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxDynamicUniformBuffersPerPipelineLayout_VH.set(handler.handler, 0L, value.toInt())
        override var maxDynamicStorageBuffersPerPipelineLayout: UInt
            get() = (maxDynamicStorageBuffersPerPipelineLayout_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxDynamicStorageBuffersPerPipelineLayout_VH.set(handler.handler, 0L, value.toInt())
        override var maxSampledTexturesPerShaderStage: UInt
            get() = (maxSampledTexturesPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxSampledTexturesPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxSamplersPerShaderStage: UInt
            get() = (maxSamplersPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxSamplersPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxStorageBuffersPerShaderStage: UInt
            get() = (maxStorageBuffersPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageBuffersPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxStorageTexturesPerShaderStage: UInt
            get() = (maxStorageTexturesPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxStorageTexturesPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxUniformBuffersPerShaderStage: UInt
            get() = (maxUniformBuffersPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxUniformBuffersPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
        override var maxUniformBufferBindingSize: ULong
            get() = (maxUniformBufferBindingSize_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = maxUniformBufferBindingSize_VH.set(handler.handler, 0L, value.toLong())
        override var maxStorageBufferBindingSize: ULong
            get() = (maxStorageBufferBindingSize_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = maxStorageBufferBindingSize_VH.set(handler.handler, 0L, value.toLong())
        override var minUniformBufferOffsetAlignment: UInt
            get() = (minUniformBufferOffsetAlignment_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = minUniformBufferOffsetAlignment_VH.set(handler.handler, 0L, value.toInt())
        override var minStorageBufferOffsetAlignment: UInt
            get() = (minStorageBufferOffsetAlignment_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = minStorageBufferOffsetAlignment_VH.set(handler.handler, 0L, value.toInt())
        override var maxVertexBuffers: UInt
            get() = (maxVertexBuffers_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxVertexBuffers_VH.set(handler.handler, 0L, value.toInt())
        override var maxBufferSize: ULong
            get() = (maxBufferSize_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = maxBufferSize_VH.set(handler.handler, 0L, value.toLong())
        override var maxVertexAttributes: UInt
            get() = (maxVertexAttributes_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxVertexAttributes_VH.set(handler.handler, 0L, value.toInt())
        override var maxVertexBufferArrayStride: UInt
            get() = (maxVertexBufferArrayStride_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxVertexBufferArrayStride_VH.set(handler.handler, 0L, value.toInt())
        override var maxInterStageShaderVariables: UInt
            get() = (maxInterStageShaderVariables_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxInterStageShaderVariables_VH.set(handler.handler, 0L, value.toInt())
        override var maxColorAttachments: UInt
            get() = (maxColorAttachments_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxColorAttachments_VH.set(handler.handler, 0L, value.toInt())
        override var maxColorAttachmentBytesPerSample: UInt
            get() = (maxColorAttachmentBytesPerSample_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxColorAttachmentBytesPerSample_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeWorkgroupStorageSize: UInt
            get() = (maxComputeWorkgroupStorageSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeWorkgroupStorageSize_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeInvocationsPerWorkgroup: UInt
            get() = (maxComputeInvocationsPerWorkgroup_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeInvocationsPerWorkgroup_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeWorkgroupSizeX: UInt
            get() = (maxComputeWorkgroupSizeX_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeWorkgroupSizeX_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeWorkgroupSizeY: UInt
            get() = (maxComputeWorkgroupSizeY_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeWorkgroupSizeY_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeWorkgroupSizeZ: UInt
            get() = (maxComputeWorkgroupSizeZ_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeWorkgroupSizeZ_VH.set(handler.handler, 0L, value.toInt())
        override var maxComputeWorkgroupsPerDimension: UInt
            get() = (maxComputeWorkgroupsPerDimension_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxComputeWorkgroupsPerDimension_VH.set(handler.handler, 0L, value.toInt())
        override var maxImmediateSize: UInt
            get() = (maxImmediateSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxImmediateSize_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPURenderPassColorAttachment : CStructure {
    actual var nextInChain: NativeAddress
    actual var view: WGPUTextureViewImpl?
    actual var depthSlice: UInt
    actual var resolveTarget: WGPUTextureViewImpl?
    actual var loadOp: WGPULoadOp
    actual var storeOp: WGPUStoreOp
    actual var clearValue: WGPUColor
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("view"),
            ValueLayout.JAVA_INT.withName("depthSlice"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("resolveTarget"),
            ValueLayout.JAVA_INT.withName("loadOp"),
            ValueLayout.JAVA_INT.withName("storeOp"),
            WGPUColor.layout.withName("clearValue")
        ).withName("WGPURenderPassColorAttachment")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val view_VH: VarHandle = layout.varHandle(groupElement("view"))
        val depthSlice_VH: VarHandle = layout.varHandle(groupElement("depthSlice"))
        val resolveTarget_VH: VarHandle = layout.varHandle(groupElement("resolveTarget"))
        val loadOp_VH: VarHandle = layout.varHandle(groupElement("loadOp"))
        val storeOp_VH: VarHandle = layout.varHandle(groupElement("storeOp"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPassColorAttachment = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassColorAttachment) -> Unit): ArrayHolder<WGPURenderPassColorAttachment> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPassColorAttachment {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var view: WGPUTextureViewImpl?
            get() = (view_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureViewImpl(it) }
            set(value) = view_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var depthSlice: UInt
            get() = (depthSlice_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = depthSlice_VH.set(handler.handler, 0L, value.toInt())
        override var resolveTarget: WGPUTextureViewImpl?
            get() = (resolveTarget_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureViewImpl(it) }
            set(value) = resolveTarget_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var loadOp: WGPULoadOp
            get() = (loadOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPULoadOp
            set(value) = loadOp_VH.set(handler.handler, 0L, value.toInt())
        override var storeOp: WGPUStoreOp
            get() = (storeOp_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUStoreOp
            set(value) = storeOp_VH.set(handler.handler, 0L, value.toInt())
        override var clearValue: WGPUColor
            get() = WGPUColor(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("clearValue")), Companion.layout.select(groupElement("clearValue")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("clearValue")), Companion.layout.select(groupElement("clearValue")).byteSize())
            }
    }
}

actual interface WGPURequestAdapterOptions : CStructure {
    actual var nextInChain: NativeAddress
    actual var featureLevel: WGPUFeatureLevel
    actual var powerPreference: WGPUPowerPreference
    actual var forceFallbackAdapter: UInt
    actual var backendType: WGPUBackendType
    actual var compatibleSurface: WGPUSurfaceImpl?
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("featureLevel"),
            ValueLayout.JAVA_INT.withName("powerPreference"),
            ValueLayout.JAVA_INT.withName("forceFallbackAdapter"),
            ValueLayout.JAVA_INT.withName("backendType"),
            ValueLayout.ADDRESS.withName("compatibleSurface")
        ).withName("WGPURequestAdapterOptions")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val featureLevel_VH: VarHandle = layout.varHandle(groupElement("featureLevel"))
        val powerPreference_VH: VarHandle = layout.varHandle(groupElement("powerPreference"))
        val forceFallbackAdapter_VH: VarHandle = layout.varHandle(groupElement("forceFallbackAdapter"))
        val backendType_VH: VarHandle = layout.varHandle(groupElement("backendType"))
        val compatibleSurface_VH: VarHandle = layout.varHandle(groupElement("compatibleSurface"))
        
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterOptions = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterOptions) -> Unit): ArrayHolder<WGPURequestAdapterOptions> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURequestAdapterOptions {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var featureLevel: WGPUFeatureLevel
            get() = (featureLevel_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUFeatureLevel
            set(value) = featureLevel_VH.set(handler.handler, 0L, value.toInt())
        override var powerPreference: WGPUPowerPreference
            get() = (powerPreference_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUPowerPreference
            set(value) = powerPreference_VH.set(handler.handler, 0L, value.toInt())
        override var forceFallbackAdapter: UInt
            get() = (forceFallbackAdapter_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = forceFallbackAdapter_VH.set(handler.handler, 0L, value.toInt())
        override var backendType: WGPUBackendType
            get() = (backendType_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUBackendType
            set(value) = backendType_VH.set(handler.handler, 0L, value.toInt())
        override var compatibleSurface: WGPUSurfaceImpl?
            get() = (compatibleSurface_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUSurfaceImpl(it) }
            set(value) = compatibleSurface_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
    }
}

actual interface WGPUShaderModuleDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPUShaderModuleDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptor) -> Unit): ArrayHolder<WGPUShaderModuleDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderModuleDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPUSurfaceDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label")
        ).withName("WGPUSurfaceDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceDescriptor) -> Unit): ArrayHolder<WGPUSurfaceDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
    }
}

actual interface WGPUTexelCopyBufferInfo : CStructure {
    actual var layout: WGPUTexelCopyBufferLayout
    actual var buffer: WGPUBufferImpl?
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUTexelCopyBufferLayout.layout.withName("layout"),
            ValueLayout.ADDRESS.withName("buffer")
        ).withName("WGPUTexelCopyBufferInfo")
        
        val buffer_VH: VarHandle = layout.varHandle(groupElement("buffer"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferInfo) -> Unit): ArrayHolder<WGPUTexelCopyBufferInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTexelCopyBufferInfo {
        override var layout: WGPUTexelCopyBufferLayout
            get() = WGPUTexelCopyBufferLayout(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("layout")), Companion.layout.select(groupElement("layout")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("layout")), Companion.layout.select(groupElement("layout")).byteSize())
            }
        override var buffer: WGPUBufferImpl?
            get() = (buffer_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUBufferImpl(it) }
            set(value) = buffer_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
    }
}

actual interface WGPUTexelCopyTextureInfo : CStructure {
    actual var texture: WGPUTextureImpl?
    actual var mipLevel: UInt
    actual var origin: WGPUOrigin3D
    actual var aspect: WGPUTextureAspect
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("texture"),
            ValueLayout.JAVA_INT.withName("mipLevel"),
            WGPUOrigin3D.layout.withName("origin"),
            ValueLayout.JAVA_INT.withName("aspect"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUTexelCopyTextureInfo")
        
        val texture_VH: VarHandle = layout.varHandle(groupElement("texture"))
        val mipLevel_VH: VarHandle = layout.varHandle(groupElement("mipLevel"))
        val aspect_VH: VarHandle = layout.varHandle(groupElement("aspect"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyTextureInfo = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyTextureInfo = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyTextureInfo) -> Unit): ArrayHolder<WGPUTexelCopyTextureInfo> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTexelCopyTextureInfo {
        override var texture: WGPUTextureImpl?
            get() = (texture_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUTextureImpl(it) }
            set(value) = texture_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var mipLevel: UInt
            get() = (mipLevel_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = mipLevel_VH.set(handler.handler, 0L, value.toInt())
        override var origin: WGPUOrigin3D
            get() = WGPUOrigin3D(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("origin")), Companion.layout.select(groupElement("origin")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("origin")), Companion.layout.select(groupElement("origin")).byteSize())
            }
        override var aspect: WGPUTextureAspect
            get() = (aspect_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureAspect
            set(value) = aspect_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUTextureComponentSwizzleDescriptor : CStructure {
    actual var chain: WGPUChainedStruct
    actual var swizzle: WGPUTextureComponentSwizzle
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            WGPUTextureComponentSwizzle.layout.withName("swizzle")
        ).withName("WGPUTextureComponentSwizzleDescriptor")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzleDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzleDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzleDescriptor) -> Unit): ArrayHolder<WGPUTextureComponentSwizzleDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureComponentSwizzleDescriptor {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var swizzle: WGPUTextureComponentSwizzle
            get() = WGPUTextureComponentSwizzle(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("swizzle")), Companion.layout.select(groupElement("swizzle")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("swizzle")), Companion.layout.select(groupElement("swizzle")).byteSize())
            }
    }
}

actual interface WGPUTextureDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var usage: ULong
    actual var dimension: WGPUTextureDimension
    actual var size: WGPUExtent3D
    actual var format: WGPUTextureFormat
    actual var mipLevelCount: UInt
    actual var sampleCount: UInt
    actual var viewFormatCount: ULong
    actual var viewFormats: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("usage"),
            ValueLayout.JAVA_INT.withName("dimension"),
            WGPUExtent3D.layout.withName("size"),
            ValueLayout.JAVA_INT.withName("format"),
            ValueLayout.JAVA_INT.withName("mipLevelCount"),
            ValueLayout.JAVA_INT.withName("sampleCount"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("viewFormatCount"),
            ValueLayout.ADDRESS.withName("viewFormats")
        ).withName("WGPUTextureDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val usage_VH: VarHandle = layout.varHandle(groupElement("usage"))
        val dimension_VH: VarHandle = layout.varHandle(groupElement("dimension"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val mipLevelCount_VH: VarHandle = layout.varHandle(groupElement("mipLevelCount"))
        val sampleCount_VH: VarHandle = layout.varHandle(groupElement("sampleCount"))
        val viewFormatCount_VH: VarHandle = layout.varHandle(groupElement("viewFormatCount"))
        val viewFormats_VH: VarHandle = layout.varHandle(groupElement("viewFormats"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureDescriptor) -> Unit): ArrayHolder<WGPUTextureDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var usage: ULong
            get() = (usage_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = usage_VH.set(handler.handler, 0L, value.toLong())
        override var dimension: WGPUTextureDimension
            get() = (dimension_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureDimension
            set(value) = dimension_VH.set(handler.handler, 0L, value.toInt())
        override var size: WGPUExtent3D
            get() = WGPUExtent3D(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("size")), Companion.layout.select(groupElement("size")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("size")), Companion.layout.select(groupElement("size")).byteSize())
            }
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var mipLevelCount: UInt
            get() = (mipLevelCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = mipLevelCount_VH.set(handler.handler, 0L, value.toInt())
        override var sampleCount: UInt
            get() = (sampleCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = sampleCount_VH.set(handler.handler, 0L, value.toInt())
        override var viewFormatCount: ULong
            get() = (viewFormatCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = viewFormatCount_VH.set(handler.handler, 0L, value.toLong())
        override var viewFormats: NativeAddress
            get() = (viewFormats_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = viewFormats_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUVertexBufferLayout : CStructure {
    actual var nextInChain: NativeAddress
    actual var stepMode: WGPUVertexStepMode
    actual var arrayStride: ULong
    actual var attributeCount: ULong
    actual var attributes: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("stepMode"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("arrayStride"),
            ValueLayout.JAVA_LONG.withName("attributeCount"),
            ValueLayout.ADDRESS.withName("attributes")
        ).withName("WGPUVertexBufferLayout")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val stepMode_VH: VarHandle = layout.varHandle(groupElement("stepMode"))
        val arrayStride_VH: VarHandle = layout.varHandle(groupElement("arrayStride"))
        val attributeCount_VH: VarHandle = layout.varHandle(groupElement("attributeCount"))
        val attributes_VH: VarHandle = layout.varHandle(groupElement("attributes"))
        
        actual operator fun invoke(address: NativeAddress): WGPUVertexBufferLayout = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexBufferLayout) -> Unit): ArrayHolder<WGPUVertexBufferLayout> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUVertexBufferLayout {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var stepMode: WGPUVertexStepMode
            get() = (stepMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUVertexStepMode
            set(value) = stepMode_VH.set(handler.handler, 0L, value.toInt())
        override var arrayStride: ULong
            get() = (arrayStride_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = arrayStride_VH.set(handler.handler, 0L, value.toLong())
        override var attributeCount: ULong
            get() = (attributeCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = attributeCount_VH.set(handler.handler, 0L, value.toLong())
        override var attributes: NativeAddress
            get() = (attributes_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = attributes_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUBindGroupDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUBindGroupLayoutImpl?
    actual var entryCount: ULong
    actual var entries: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.ADDRESS.withName("layout"),
            ValueLayout.JAVA_LONG.withName("entryCount"),
            ValueLayout.ADDRESS.withName("entries")
        ).withName("WGPUBindGroupDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val layout_VH: VarHandle = layout.varHandle(groupElement("layout"))
        val entryCount_VH: VarHandle = layout.varHandle(groupElement("entryCount"))
        val entries_VH: VarHandle = layout.varHandle(groupElement("entries"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupDescriptor) -> Unit): ArrayHolder<WGPUBindGroupDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var layout: WGPUBindGroupLayoutImpl?
            get() = (layout_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUBindGroupLayoutImpl(it) }
            set(value) = layout_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var entryCount: ULong
            get() = (entryCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = entryCount_VH.set(handler.handler, 0L, value.toLong())
        override var entries: NativeAddress
            get() = (entries_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = entries_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUBindGroupLayoutDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var entryCount: ULong
    actual var entries: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("entryCount"),
            ValueLayout.ADDRESS.withName("entries")
        ).withName("WGPUBindGroupLayoutDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val entryCount_VH: VarHandle = layout.varHandle(groupElement("entryCount"))
        val entries_VH: VarHandle = layout.varHandle(groupElement("entries"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutDescriptor) -> Unit): ArrayHolder<WGPUBindGroupLayoutDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var entryCount: ULong
            get() = (entryCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = entryCount_VH.set(handler.handler, 0L, value.toLong())
        override var entries: NativeAddress
            get() = (entries_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = entries_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUColorTargetState : CStructure {
    actual var nextInChain: NativeAddress
    actual var format: WGPUTextureFormat
    actual var blend: NativeAddress
    actual var writeMask: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_INT.withName("format"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("blend"),
            ValueLayout.JAVA_LONG.withName("writeMask")
        ).withName("WGPUColorTargetState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val blend_VH: VarHandle = layout.varHandle(groupElement("blend"))
        val writeMask_VH: VarHandle = layout.varHandle(groupElement("writeMask"))
        
        actual operator fun invoke(address: NativeAddress): WGPUColorTargetState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColorTargetState) -> Unit): ArrayHolder<WGPUColorTargetState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUColorTargetState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var blend: NativeAddress
            get() = (blend_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = blend_VH.set(handler.handler, 0L, value.handler)
        override var writeMask: ULong
            get() = (writeMask_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = writeMask_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUComputePipelineDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUPipelineLayoutImpl?
    actual var compute: WGPUComputeState
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.ADDRESS.withName("layout"),
            WGPUComputeState.layout.withName("compute")
        ).withName("WGPUComputePipelineDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val layout_VH: VarHandle = layout.varHandle(groupElement("layout"))
        
        actual operator fun invoke(address: NativeAddress): WGPUComputePipelineDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePipelineDescriptor) -> Unit): ArrayHolder<WGPUComputePipelineDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUComputePipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var layout: WGPUPipelineLayoutImpl?
            get() = (layout_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUPipelineLayoutImpl(it) }
            set(value) = layout_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var compute: WGPUComputeState
            get() = WGPUComputeState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("compute")), Companion.layout.select(groupElement("compute")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("compute")), Companion.layout.select(groupElement("compute")).byteSize())
            }
    }
}

actual interface WGPUDeviceDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var requiredFeatureCount: ULong
    actual var requiredFeatures: NativeAddress
    actual var requiredLimits: NativeAddress
    actual var defaultQueue: WGPUQueueDescriptor
    actual var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
    actual var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("requiredFeatureCount"),
            ValueLayout.ADDRESS.withName("requiredFeatures"),
            ValueLayout.ADDRESS.withName("requiredLimits"),
            WGPUQueueDescriptor.layout.withName("defaultQueue"),
            WGPUDeviceLostCallbackInfo.layout.withName("deviceLostCallbackInfo"),
            WGPUUncapturedErrorCallbackInfo.layout.withName("uncapturedErrorCallbackInfo")
        ).withName("WGPUDeviceDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val requiredFeatureCount_VH: VarHandle = layout.varHandle(groupElement("requiredFeatureCount"))
        val requiredFeatures_VH: VarHandle = layout.varHandle(groupElement("requiredFeatures"))
        val requiredLimits_VH: VarHandle = layout.varHandle(groupElement("requiredLimits"))
        
        actual operator fun invoke(address: NativeAddress): WGPUDeviceDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceDescriptor) -> Unit): ArrayHolder<WGPUDeviceDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUDeviceDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var requiredFeatureCount: ULong
            get() = (requiredFeatureCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = requiredFeatureCount_VH.set(handler.handler, 0L, value.toLong())
        override var requiredFeatures: NativeAddress
            get() = (requiredFeatures_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = requiredFeatures_VH.set(handler.handler, 0L, value.handler)
        override var requiredLimits: NativeAddress
            get() = (requiredLimits_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = requiredLimits_VH.set(handler.handler, 0L, value.handler)
        override var defaultQueue: WGPUQueueDescriptor
            get() = WGPUQueueDescriptor(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("defaultQueue")), Companion.layout.select(groupElement("defaultQueue")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("defaultQueue")), Companion.layout.select(groupElement("defaultQueue")).byteSize())
            }
        override var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
            get() = WGPUDeviceLostCallbackInfo(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("deviceLostCallbackInfo")), Companion.layout.select(groupElement("deviceLostCallbackInfo")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("deviceLostCallbackInfo")), Companion.layout.select(groupElement("deviceLostCallbackInfo")).byteSize())
            }
        override var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
            get() = WGPUUncapturedErrorCallbackInfo(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("uncapturedErrorCallbackInfo")), Companion.layout.select(groupElement("uncapturedErrorCallbackInfo")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("uncapturedErrorCallbackInfo")), Companion.layout.select(groupElement("uncapturedErrorCallbackInfo")).byteSize())
            }
    }
}

actual interface WGPURenderPassDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var colorAttachmentCount: ULong
    actual var colorAttachments: NativeAddress
    actual var depthStencilAttachment: NativeAddress
    actual var occlusionQuerySet: WGPUQuerySetImpl?
    actual var timestampWrites: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_LONG.withName("colorAttachmentCount"),
            ValueLayout.ADDRESS.withName("colorAttachments"),
            ValueLayout.ADDRESS.withName("depthStencilAttachment"),
            ValueLayout.ADDRESS.withName("occlusionQuerySet"),
            ValueLayout.ADDRESS.withName("timestampWrites")
        ).withName("WGPURenderPassDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val colorAttachmentCount_VH: VarHandle = layout.varHandle(groupElement("colorAttachmentCount"))
        val colorAttachments_VH: VarHandle = layout.varHandle(groupElement("colorAttachments"))
        val depthStencilAttachment_VH: VarHandle = layout.varHandle(groupElement("depthStencilAttachment"))
        val occlusionQuerySet_VH: VarHandle = layout.varHandle(groupElement("occlusionQuerySet"))
        val timestampWrites_VH: VarHandle = layout.varHandle(groupElement("timestampWrites"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPassDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDescriptor) -> Unit): ArrayHolder<WGPURenderPassDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPassDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var colorAttachmentCount: ULong
            get() = (colorAttachmentCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = colorAttachmentCount_VH.set(handler.handler, 0L, value.toLong())
        override var colorAttachments: NativeAddress
            get() = (colorAttachments_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = colorAttachments_VH.set(handler.handler, 0L, value.handler)
        override var depthStencilAttachment: NativeAddress
            get() = (depthStencilAttachment_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = depthStencilAttachment_VH.set(handler.handler, 0L, value.handler)
        override var occlusionQuerySet: WGPUQuerySetImpl?
            get() = (occlusionQuerySet_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUQuerySetImpl(it) }
            set(value) = occlusionQuerySet_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var timestampWrites: NativeAddress
            get() = (timestampWrites_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = timestampWrites_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUTextureViewDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var format: WGPUTextureFormat
    actual var dimension: WGPUTextureViewDimension
    actual var baseMipLevel: UInt
    actual var mipLevelCount: UInt
    actual var baseArrayLayer: UInt
    actual var arrayLayerCount: UInt
    actual var aspect: WGPUTextureAspect
    actual var usage: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_INT.withName("format"),
            ValueLayout.JAVA_INT.withName("dimension"),
            ValueLayout.JAVA_INT.withName("baseMipLevel"),
            ValueLayout.JAVA_INT.withName("mipLevelCount"),
            ValueLayout.JAVA_INT.withName("baseArrayLayer"),
            ValueLayout.JAVA_INT.withName("arrayLayerCount"),
            ValueLayout.JAVA_INT.withName("aspect"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("usage")
        ).withName("WGPUTextureViewDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val format_VH: VarHandle = layout.varHandle(groupElement("format"))
        val dimension_VH: VarHandle = layout.varHandle(groupElement("dimension"))
        val baseMipLevel_VH: VarHandle = layout.varHandle(groupElement("baseMipLevel"))
        val mipLevelCount_VH: VarHandle = layout.varHandle(groupElement("mipLevelCount"))
        val baseArrayLayer_VH: VarHandle = layout.varHandle(groupElement("baseArrayLayer"))
        val arrayLayerCount_VH: VarHandle = layout.varHandle(groupElement("arrayLayerCount"))
        val aspect_VH: VarHandle = layout.varHandle(groupElement("aspect"))
        val usage_VH: VarHandle = layout.varHandle(groupElement("usage"))
        
        actual operator fun invoke(address: NativeAddress): WGPUTextureViewDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureViewDescriptor) -> Unit): ArrayHolder<WGPUTextureViewDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUTextureViewDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var format: WGPUTextureFormat
            get() = (format_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureFormat
            set(value) = format_VH.set(handler.handler, 0L, value.toInt())
        override var dimension: WGPUTextureViewDimension
            get() = (dimension_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureViewDimension
            set(value) = dimension_VH.set(handler.handler, 0L, value.toInt())
        override var baseMipLevel: UInt
            get() = (baseMipLevel_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = baseMipLevel_VH.set(handler.handler, 0L, value.toInt())
        override var mipLevelCount: UInt
            get() = (mipLevelCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = mipLevelCount_VH.set(handler.handler, 0L, value.toInt())
        override var baseArrayLayer: UInt
            get() = (baseArrayLayer_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = baseArrayLayer_VH.set(handler.handler, 0L, value.toInt())
        override var arrayLayerCount: UInt
            get() = (arrayLayerCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = arrayLayerCount_VH.set(handler.handler, 0L, value.toInt())
        override var aspect: WGPUTextureAspect
            get() = (aspect_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUTextureAspect
            set(value) = aspect_VH.set(handler.handler, 0L, value.toInt())
        override var usage: ULong
            get() = (usage_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = usage_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUVertexState : CStructure {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual var bufferCount: ULong
    actual var buffers: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("module"),
            WGPUStringView.layout.withName("entryPoint"),
            ValueLayout.JAVA_LONG.withName("constantCount"),
            ValueLayout.ADDRESS.withName("constants"),
            ValueLayout.JAVA_LONG.withName("bufferCount"),
            ValueLayout.ADDRESS.withName("buffers")
        ).withName("WGPUVertexState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val module_VH: VarHandle = layout.varHandle(groupElement("module"))
        val constantCount_VH: VarHandle = layout.varHandle(groupElement("constantCount"))
        val constants_VH: VarHandle = layout.varHandle(groupElement("constants"))
        val bufferCount_VH: VarHandle = layout.varHandle(groupElement("bufferCount"))
        val buffers_VH: VarHandle = layout.varHandle(groupElement("buffers"))
        
        actual operator fun invoke(address: NativeAddress): WGPUVertexState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexState) -> Unit): ArrayHolder<WGPUVertexState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUVertexState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var module: WGPUShaderModuleImpl?
            get() = (module_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUShaderModuleImpl(it) }
            set(value) = module_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var entryPoint: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())
            }
        override var constantCount: ULong
            get() = (constantCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = constantCount_VH.set(handler.handler, 0L, value.toLong())
        override var constants: NativeAddress
            get() = (constants_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = constants_VH.set(handler.handler, 0L, value.handler)
        override var bufferCount: ULong
            get() = (bufferCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = bufferCount_VH.set(handler.handler, 0L, value.toLong())
        override var buffers: NativeAddress
            get() = (buffers_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = buffers_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUFragmentState : CStructure {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual var targetCount: ULong
    actual var targets: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.ADDRESS.withName("module"),
            WGPUStringView.layout.withName("entryPoint"),
            ValueLayout.JAVA_LONG.withName("constantCount"),
            ValueLayout.ADDRESS.withName("constants"),
            ValueLayout.JAVA_LONG.withName("targetCount"),
            ValueLayout.ADDRESS.withName("targets")
        ).withName("WGPUFragmentState")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val module_VH: VarHandle = layout.varHandle(groupElement("module"))
        val constantCount_VH: VarHandle = layout.varHandle(groupElement("constantCount"))
        val constants_VH: VarHandle = layout.varHandle(groupElement("constants"))
        val targetCount_VH: VarHandle = layout.varHandle(groupElement("targetCount"))
        val targets_VH: VarHandle = layout.varHandle(groupElement("targets"))
        
        actual operator fun invoke(address: NativeAddress): WGPUFragmentState = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUFragmentState = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFragmentState) -> Unit): ArrayHolder<WGPUFragmentState> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUFragmentState {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var module: WGPUShaderModuleImpl?
            get() = (module_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUShaderModuleImpl(it) }
            set(value) = module_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var entryPoint: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("entryPoint")), Companion.layout.select(groupElement("entryPoint")).byteSize())
            }
        override var constantCount: ULong
            get() = (constantCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = constantCount_VH.set(handler.handler, 0L, value.toLong())
        override var constants: NativeAddress
            get() = (constants_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = constants_VH.set(handler.handler, 0L, value.handler)
        override var targetCount: ULong
            get() = (targetCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = targetCount_VH.set(handler.handler, 0L, value.toLong())
        override var targets: NativeAddress
            get() = (targets_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = targets_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPURenderPipelineDescriptor : CStructure {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUPipelineLayoutImpl?
    actual var vertex: WGPUVertexState
    actual var primitive: WGPUPrimitiveState
    actual var depthStencil: NativeAddress
    actual var multisample: WGPUMultisampleState
    actual var fragment: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            WGPUStringView.layout.withName("label"),
            ValueLayout.ADDRESS.withName("layout"),
            WGPUVertexState.layout.withName("vertex"),
            WGPUPrimitiveState.layout.withName("primitive"),
            ValueLayout.ADDRESS.withName("depthStencil"),
            WGPUMultisampleState.layout.withName("multisample"),
            ValueLayout.ADDRESS.withName("fragment")
        ).withName("WGPURenderPipelineDescriptor")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val layout_VH: VarHandle = layout.varHandle(groupElement("layout"))
        val depthStencil_VH: VarHandle = layout.varHandle(groupElement("depthStencil"))
        val fragment_VH: VarHandle = layout.varHandle(groupElement("fragment"))
        
        actual operator fun invoke(address: NativeAddress): WGPURenderPipelineDescriptor = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPipelineDescriptor) -> Unit): ArrayHolder<WGPURenderPipelineDescriptor> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURenderPipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var layout: WGPUPipelineLayoutImpl?
            get() = (layout_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress)?.let { WGPUPipelineLayoutImpl(it) }
            set(value) = layout_VH.set(handler.handler, 0L, value?.handler?.handler ?: MemorySegment.NULL)
        override var vertex: WGPUVertexState
            get() = WGPUVertexState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("vertex")), Companion.layout.select(groupElement("vertex")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("vertex")), Companion.layout.select(groupElement("vertex")).byteSize())
            }
        override var primitive: WGPUPrimitiveState
            get() = WGPUPrimitiveState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("primitive")), Companion.layout.select(groupElement("primitive")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("primitive")), Companion.layout.select(groupElement("primitive")).byteSize())
            }
        override var depthStencil: NativeAddress
            get() = (depthStencil_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = depthStencil_VH.set(handler.handler, 0L, value.handler)
        override var multisample: WGPUMultisampleState
            get() = WGPUMultisampleState(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("multisample")), Companion.layout.select(groupElement("multisample")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("multisample")), Companion.layout.select(groupElement("multisample")).byteSize())
            }
        override var fragment: NativeAddress
            get() = (fragment_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = fragment_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUXlibDisplayHandle : CStructure {
    actual var display: NativeAddress
    actual var screen: Int
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("display"),
            ValueLayout.JAVA_INT.withName("screen"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUXlibDisplayHandle")
        
        val display_VH: VarHandle = layout.varHandle(groupElement("display"))
        val screen_VH: VarHandle = layout.varHandle(groupElement("screen"))
        
        actual operator fun invoke(address: NativeAddress): WGPUXlibDisplayHandle = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUXlibDisplayHandle = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXlibDisplayHandle) -> Unit): ArrayHolder<WGPUXlibDisplayHandle> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUXlibDisplayHandle {
        override var display: NativeAddress
            get() = (display_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = display_VH.set(handler.handler, 0L, value.handler)
        override var screen: Int
            get() = screen_VH.get(handler.handler, 0L) as Int
            set(value) = screen_VH.set(handler.handler, 0L, value)
    }
}

actual interface WGPUXcbDisplayHandle : CStructure {
    actual var connection: NativeAddress
    actual var screen: Int
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("connection"),
            ValueLayout.JAVA_INT.withName("screen"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUXcbDisplayHandle")
        
        val connection_VH: VarHandle = layout.varHandle(groupElement("connection"))
        val screen_VH: VarHandle = layout.varHandle(groupElement("screen"))
        
        actual operator fun invoke(address: NativeAddress): WGPUXcbDisplayHandle = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUXcbDisplayHandle = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXcbDisplayHandle) -> Unit): ArrayHolder<WGPUXcbDisplayHandle> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUXcbDisplayHandle {
        override var connection: NativeAddress
            get() = (connection_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = connection_VH.set(handler.handler, 0L, value.handler)
        override var screen: Int
            get() = screen_VH.get(handler.handler, 0L) as Int
            set(value) = screen_VH.set(handler.handler, 0L, value)
    }
}

actual interface WGPUWaylandDisplayHandle : CStructure {
    actual var display: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("display")
        ).withName("WGPUWaylandDisplayHandle")
        
        val display_VH: VarHandle = layout.varHandle(groupElement("display"))
        
        actual operator fun invoke(address: NativeAddress): WGPUWaylandDisplayHandle = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUWaylandDisplayHandle = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUWaylandDisplayHandle) -> Unit): ArrayHolder<WGPUWaylandDisplayHandle> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUWaylandDisplayHandle {
        override var display: NativeAddress
            get() = (display_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = display_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUInstanceExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var backends: ULong
    actual var flags: ULong
    actual var dx12ShaderCompiler: WGPUDx12Compiler
    actual var gles3MinorVersion: WGPUGles3MinorVersion
    actual var glFenceBehaviour: WGPUGLFenceBehaviour
    actual var dxcPath: WGPUStringView
    actual var dxcMaxShaderModel: WGPUDxcMaxShaderModel
    actual var dx12PresentationSystem: WGPUDx12SwapchainKind
    actual var budgetForDeviceCreation: NativeAddress
    actual var budgetForDeviceLoss: NativeAddress
    actual var displayHandle: WGPUNativeDisplayHandle
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_LONG.withName("backends"),
            ValueLayout.JAVA_LONG.withName("flags"),
            ValueLayout.JAVA_INT.withName("dx12ShaderCompiler"),
            ValueLayout.JAVA_INT.withName("gles3MinorVersion"),
            ValueLayout.JAVA_INT.withName("glFenceBehaviour"),
            MemoryLayout.paddingLayout(4),
            WGPUStringView.layout.withName("dxcPath"),
            ValueLayout.JAVA_INT.withName("dxcMaxShaderModel"),
            ValueLayout.JAVA_INT.withName("dx12PresentationSystem"),
            ValueLayout.ADDRESS.withName("budgetForDeviceCreation"),
            ValueLayout.ADDRESS.withName("budgetForDeviceLoss"),
            WGPUNativeDisplayHandle.layout.withName("displayHandle")
        ).withName("WGPUInstanceExtras")
        
        val backends_VH: VarHandle = layout.varHandle(groupElement("backends"))
        val flags_VH: VarHandle = layout.varHandle(groupElement("flags"))
        val dx12ShaderCompiler_VH: VarHandle = layout.varHandle(groupElement("dx12ShaderCompiler"))
        val gles3MinorVersion_VH: VarHandle = layout.varHandle(groupElement("gles3MinorVersion"))
        val glFenceBehaviour_VH: VarHandle = layout.varHandle(groupElement("glFenceBehaviour"))
        val dxcMaxShaderModel_VH: VarHandle = layout.varHandle(groupElement("dxcMaxShaderModel"))
        val dx12PresentationSystem_VH: VarHandle = layout.varHandle(groupElement("dx12PresentationSystem"))
        val budgetForDeviceCreation_VH: VarHandle = layout.varHandle(groupElement("budgetForDeviceCreation"))
        val budgetForDeviceLoss_VH: VarHandle = layout.varHandle(groupElement("budgetForDeviceLoss"))
        
        actual operator fun invoke(address: NativeAddress): WGPUInstanceExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceExtras) -> Unit): ArrayHolder<WGPUInstanceExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUInstanceExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var backends: ULong
            get() = (backends_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = backends_VH.set(handler.handler, 0L, value.toLong())
        override var flags: ULong
            get() = (flags_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = flags_VH.set(handler.handler, 0L, value.toLong())
        override var dx12ShaderCompiler: WGPUDx12Compiler
            get() = (dx12ShaderCompiler_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUDx12Compiler
            set(value) = dx12ShaderCompiler_VH.set(handler.handler, 0L, value.toInt())
        override var gles3MinorVersion: WGPUGles3MinorVersion
            get() = (gles3MinorVersion_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUGles3MinorVersion
            set(value) = gles3MinorVersion_VH.set(handler.handler, 0L, value.toInt())
        override var glFenceBehaviour: WGPUGLFenceBehaviour
            get() = (glFenceBehaviour_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUGLFenceBehaviour
            set(value) = glFenceBehaviour_VH.set(handler.handler, 0L, value.toInt())
        override var dxcPath: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("dxcPath")), Companion.layout.select(groupElement("dxcPath")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("dxcPath")), Companion.layout.select(groupElement("dxcPath")).byteSize())
            }
        override var dxcMaxShaderModel: WGPUDxcMaxShaderModel
            get() = (dxcMaxShaderModel_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUDxcMaxShaderModel
            set(value) = dxcMaxShaderModel_VH.set(handler.handler, 0L, value.toInt())
        override var dx12PresentationSystem: WGPUDx12SwapchainKind
            get() = (dx12PresentationSystem_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUDx12SwapchainKind
            set(value) = dx12PresentationSystem_VH.set(handler.handler, 0L, value.toInt())
        override var budgetForDeviceCreation: NativeAddress
            get() = (budgetForDeviceCreation_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = budgetForDeviceCreation_VH.set(handler.handler, 0L, value.handler)
        override var budgetForDeviceLoss: NativeAddress
            get() = (budgetForDeviceLoss_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = budgetForDeviceLoss_VH.set(handler.handler, 0L, value.handler)
        override var displayHandle: WGPUNativeDisplayHandle
            get() = WGPUNativeDisplayHandle(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("displayHandle")), Companion.layout.select(groupElement("displayHandle")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("displayHandle")), Companion.layout.select(groupElement("displayHandle")).byteSize())
            }
    }
}

actual interface WGPUDeviceExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var tracePath: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            WGPUStringView.layout.withName("tracePath")
        ).withName("WGPUDeviceExtras")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUDeviceExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceExtras) -> Unit): ArrayHolder<WGPUDeviceExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUDeviceExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var tracePath: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("tracePath")), Companion.layout.select(groupElement("tracePath")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("tracePath")), Companion.layout.select(groupElement("tracePath")).byteSize())
            }
    }
}

actual interface WGPUNativeLimits : CStructure {
    actual var chain: WGPUChainedStruct
    actual var maxImmediateSize: UInt
    actual var maxNonSamplerBindings: UInt
    actual var maxBindingArrayElementsPerShaderStage: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("maxImmediateSize"),
            ValueLayout.JAVA_INT.withName("maxNonSamplerBindings"),
            ValueLayout.JAVA_INT.withName("maxBindingArrayElementsPerShaderStage"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUNativeLimits")
        
        val maxImmediateSize_VH: VarHandle = layout.varHandle(groupElement("maxImmediateSize"))
        val maxNonSamplerBindings_VH: VarHandle = layout.varHandle(groupElement("maxNonSamplerBindings"))
        val maxBindingArrayElementsPerShaderStage_VH: VarHandle = layout.varHandle(groupElement("maxBindingArrayElementsPerShaderStage"))
        
        actual operator fun invoke(address: NativeAddress): WGPUNativeLimits = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUNativeLimits = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUNativeLimits) -> Unit): ArrayHolder<WGPUNativeLimits> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUNativeLimits {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var maxImmediateSize: UInt
            get() = (maxImmediateSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxImmediateSize_VH.set(handler.handler, 0L, value.toInt())
        override var maxNonSamplerBindings: UInt
            get() = (maxNonSamplerBindings_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxNonSamplerBindings_VH.set(handler.handler, 0L, value.toInt())
        override var maxBindingArrayElementsPerShaderStage: UInt
            get() = (maxBindingArrayElementsPerShaderStage_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = maxBindingArrayElementsPerShaderStage_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUPipelineLayoutExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var immediateDataSize: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("immediateDataSize"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUPipelineLayoutExtras")
        
        val immediateDataSize_VH: VarHandle = layout.varHandle(groupElement("immediateDataSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutExtras) -> Unit): ArrayHolder<WGPUPipelineLayoutExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPipelineLayoutExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var immediateDataSize: UInt
            get() = (immediateDataSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = immediateDataSize_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUShaderDefine : CStructure {
    actual var name: WGPUStringView
    actual var value: WGPUStringView
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUStringView.layout.withName("name"),
            WGPUStringView.layout.withName("value")
        ).withName("WGPUShaderDefine")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderDefine = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderDefine = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderDefine) -> Unit): ArrayHolder<WGPUShaderDefine> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderDefine {
        override var name: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("name")), Companion.layout.select(groupElement("name")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("name")), Companion.layout.select(groupElement("name")).byteSize())
            }
        override var value: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("value")), Companion.layout.select(groupElement("value")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("value")), Companion.layout.select(groupElement("value")).byteSize())
            }
    }
}

actual interface WGPUShaderSourceGLSL : CStructure {
    actual var chain: WGPUChainedStruct
    actual var stage: ULong
    actual var code: WGPUStringView
    actual var defineCount: UInt
    actual var defines: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_LONG.withName("stage"),
            WGPUStringView.layout.withName("code"),
            ValueLayout.JAVA_INT.withName("defineCount"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("defines")
        ).withName("WGPUShaderSourceGLSL")
        
        val stage_VH: VarHandle = layout.varHandle(groupElement("stage"))
        val defineCount_VH: VarHandle = layout.varHandle(groupElement("defineCount"))
        val defines_VH: VarHandle = layout.varHandle(groupElement("defines"))
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceGLSL = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceGLSL = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceGLSL) -> Unit): ArrayHolder<WGPUShaderSourceGLSL> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderSourceGLSL {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var stage: ULong
            get() = (stage_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = stage_VH.set(handler.handler, 0L, value.toLong())
        override var code: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("code")), Companion.layout.select(groupElement("code")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("code")), Companion.layout.select(groupElement("code")).byteSize())
            }
        override var defineCount: UInt
            get() = (defineCount_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = defineCount_VH.set(handler.handler, 0L, value.toInt())
        override var defines: NativeAddress
            get() = (defines_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = defines_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUShaderModuleDescriptorSpirV : CStructure {
    actual var label: WGPUStringView
    actual var sourceSize: UInt
    actual var source: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUStringView.layout.withName("label"),
            ValueLayout.JAVA_INT.withName("sourceSize"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("source")
        ).withName("WGPUShaderModuleDescriptorSpirV")
        
        val sourceSize_VH: VarHandle = layout.varHandle(groupElement("sourceSize"))
        val source_VH: VarHandle = layout.varHandle(groupElement("source"))
        
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptorSpirV = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptorSpirV = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptorSpirV) -> Unit): ArrayHolder<WGPUShaderModuleDescriptorSpirV> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUShaderModuleDescriptorSpirV {
        override var label: WGPUStringView
            get() = WGPUStringView(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("label")), Companion.layout.select(groupElement("label")).byteSize())
            }
        override var sourceSize: UInt
            get() = (sourceSize_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = sourceSize_VH.set(handler.handler, 0L, value.toInt())
        override var source: NativeAddress
            get() = (source_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = source_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPURegistryReport : CStructure {
    actual var numAllocated: ULong
    actual var numKeptFromUser: ULong
    actual var numReleasedFromUser: ULong
    actual var elementSize: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("numAllocated"),
            ValueLayout.JAVA_LONG.withName("numKeptFromUser"),
            ValueLayout.JAVA_LONG.withName("numReleasedFromUser"),
            ValueLayout.JAVA_LONG.withName("elementSize")
        ).withName("WGPURegistryReport")
        
        val numAllocated_VH: VarHandle = layout.varHandle(groupElement("numAllocated"))
        val numKeptFromUser_VH: VarHandle = layout.varHandle(groupElement("numKeptFromUser"))
        val numReleasedFromUser_VH: VarHandle = layout.varHandle(groupElement("numReleasedFromUser"))
        val elementSize_VH: VarHandle = layout.varHandle(groupElement("elementSize"))
        
        actual operator fun invoke(address: NativeAddress): WGPURegistryReport = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPURegistryReport = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURegistryReport) -> Unit): ArrayHolder<WGPURegistryReport> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPURegistryReport {
        override var numAllocated: ULong
            get() = (numAllocated_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = numAllocated_VH.set(handler.handler, 0L, value.toLong())
        override var numKeptFromUser: ULong
            get() = (numKeptFromUser_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = numKeptFromUser_VH.set(handler.handler, 0L, value.toLong())
        override var numReleasedFromUser: ULong
            get() = (numReleasedFromUser_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = numReleasedFromUser_VH.set(handler.handler, 0L, value.toLong())
        override var elementSize: ULong
            get() = (elementSize_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = elementSize_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUHubReport : CStructure {
    actual var adapters: WGPURegistryReport
    actual var devices: WGPURegistryReport
    actual var queues: WGPURegistryReport
    actual var pipelineLayouts: WGPURegistryReport
    actual var shaderModules: WGPURegistryReport
    actual var bindGroupLayouts: WGPURegistryReport
    actual var bindGroups: WGPURegistryReport
    actual var commandBuffers: WGPURegistryReport
    actual var renderBundles: WGPURegistryReport
    actual var renderPipelines: WGPURegistryReport
    actual var computePipelines: WGPURegistryReport
    actual var pipelineCaches: WGPURegistryReport
    actual var querySets: WGPURegistryReport
    actual var buffers: WGPURegistryReport
    actual var textures: WGPURegistryReport
    actual var textureViews: WGPURegistryReport
    actual var samplers: WGPURegistryReport
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPURegistryReport.layout.withName("adapters"),
            WGPURegistryReport.layout.withName("devices"),
            WGPURegistryReport.layout.withName("queues"),
            WGPURegistryReport.layout.withName("pipelineLayouts"),
            WGPURegistryReport.layout.withName("shaderModules"),
            WGPURegistryReport.layout.withName("bindGroupLayouts"),
            WGPURegistryReport.layout.withName("bindGroups"),
            WGPURegistryReport.layout.withName("commandBuffers"),
            WGPURegistryReport.layout.withName("renderBundles"),
            WGPURegistryReport.layout.withName("renderPipelines"),
            WGPURegistryReport.layout.withName("computePipelines"),
            WGPURegistryReport.layout.withName("pipelineCaches"),
            WGPURegistryReport.layout.withName("querySets"),
            WGPURegistryReport.layout.withName("buffers"),
            WGPURegistryReport.layout.withName("textures"),
            WGPURegistryReport.layout.withName("textureViews"),
            WGPURegistryReport.layout.withName("samplers")
        ).withName("WGPUHubReport")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUHubReport = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUHubReport = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUHubReport) -> Unit): ArrayHolder<WGPUHubReport> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUHubReport {
        override var adapters: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("adapters")), Companion.layout.select(groupElement("adapters")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("adapters")), Companion.layout.select(groupElement("adapters")).byteSize())
            }
        override var devices: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("devices")), Companion.layout.select(groupElement("devices")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("devices")), Companion.layout.select(groupElement("devices")).byteSize())
            }
        override var queues: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("queues")), Companion.layout.select(groupElement("queues")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("queues")), Companion.layout.select(groupElement("queues")).byteSize())
            }
        override var pipelineLayouts: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("pipelineLayouts")), Companion.layout.select(groupElement("pipelineLayouts")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("pipelineLayouts")), Companion.layout.select(groupElement("pipelineLayouts")).byteSize())
            }
        override var shaderModules: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("shaderModules")), Companion.layout.select(groupElement("shaderModules")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("shaderModules")), Companion.layout.select(groupElement("shaderModules")).byteSize())
            }
        override var bindGroupLayouts: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("bindGroupLayouts")), Companion.layout.select(groupElement("bindGroupLayouts")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("bindGroupLayouts")), Companion.layout.select(groupElement("bindGroupLayouts")).byteSize())
            }
        override var bindGroups: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("bindGroups")), Companion.layout.select(groupElement("bindGroups")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("bindGroups")), Companion.layout.select(groupElement("bindGroups")).byteSize())
            }
        override var commandBuffers: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("commandBuffers")), Companion.layout.select(groupElement("commandBuffers")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("commandBuffers")), Companion.layout.select(groupElement("commandBuffers")).byteSize())
            }
        override var renderBundles: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("renderBundles")), Companion.layout.select(groupElement("renderBundles")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("renderBundles")), Companion.layout.select(groupElement("renderBundles")).byteSize())
            }
        override var renderPipelines: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("renderPipelines")), Companion.layout.select(groupElement("renderPipelines")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("renderPipelines")), Companion.layout.select(groupElement("renderPipelines")).byteSize())
            }
        override var computePipelines: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("computePipelines")), Companion.layout.select(groupElement("computePipelines")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("computePipelines")), Companion.layout.select(groupElement("computePipelines")).byteSize())
            }
        override var pipelineCaches: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("pipelineCaches")), Companion.layout.select(groupElement("pipelineCaches")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("pipelineCaches")), Companion.layout.select(groupElement("pipelineCaches")).byteSize())
            }
        override var querySets: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("querySets")), Companion.layout.select(groupElement("querySets")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("querySets")), Companion.layout.select(groupElement("querySets")).byteSize())
            }
        override var buffers: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("buffers")), Companion.layout.select(groupElement("buffers")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("buffers")), Companion.layout.select(groupElement("buffers")).byteSize())
            }
        override var textures: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("textures")), Companion.layout.select(groupElement("textures")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("textures")), Companion.layout.select(groupElement("textures")).byteSize())
            }
        override var textureViews: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("textureViews")), Companion.layout.select(groupElement("textureViews")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("textureViews")), Companion.layout.select(groupElement("textureViews")).byteSize())
            }
        override var samplers: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("samplers")), Companion.layout.select(groupElement("samplers")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("samplers")), Companion.layout.select(groupElement("samplers")).byteSize())
            }
    }
}

actual interface WGPUGlobalReport : CStructure {
    actual var surfaces: WGPURegistryReport
    actual var hub: WGPUHubReport
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPURegistryReport.layout.withName("surfaces"),
            WGPUHubReport.layout.withName("hub")
        ).withName("WGPUGlobalReport")
        
        
        actual operator fun invoke(address: NativeAddress): WGPUGlobalReport = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUGlobalReport = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUGlobalReport) -> Unit): ArrayHolder<WGPUGlobalReport> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUGlobalReport {
        override var surfaces: WGPURegistryReport
            get() = WGPURegistryReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("surfaces")), Companion.layout.select(groupElement("surfaces")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("surfaces")), Companion.layout.select(groupElement("surfaces")).byteSize())
            }
        override var hub: WGPUHubReport
            get() = WGPUHubReport(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("hub")), Companion.layout.select(groupElement("hub")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("hub")), Companion.layout.select(groupElement("hub")).byteSize())
            }
    }
}

actual interface WGPUInstanceEnumerateAdapterOptions : CStructure {
    actual var nextInChain: NativeAddress
    actual var backends: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("nextInChain"),
            ValueLayout.JAVA_LONG.withName("backends")
        ).withName("WGPUInstanceEnumerateAdapterOptions")
        
        val nextInChain_VH: VarHandle = layout.varHandle(groupElement("nextInChain"))
        val backends_VH: VarHandle = layout.varHandle(groupElement("backends"))
        
        actual operator fun invoke(address: NativeAddress): WGPUInstanceEnumerateAdapterOptions = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceEnumerateAdapterOptions = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceEnumerateAdapterOptions) -> Unit): ArrayHolder<WGPUInstanceEnumerateAdapterOptions> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUInstanceEnumerateAdapterOptions {
        override var nextInChain: NativeAddress
            get() = (nextInChain_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = nextInChain_VH.set(handler.handler, 0L, value.handler)
        override var backends: ULong
            get() = (backends_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = backends_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUBindGroupEntryExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var buffers: NativeAddress
    actual var bufferCount: ULong
    actual var samplers: NativeAddress
    actual var samplerCount: ULong
    actual var textureViews: NativeAddress
    actual var textureViewCount: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("buffers"),
            ValueLayout.JAVA_LONG.withName("bufferCount"),
            ValueLayout.ADDRESS.withName("samplers"),
            ValueLayout.JAVA_LONG.withName("samplerCount"),
            ValueLayout.ADDRESS.withName("textureViews"),
            ValueLayout.JAVA_LONG.withName("textureViewCount")
        ).withName("WGPUBindGroupEntryExtras")
        
        val buffers_VH: VarHandle = layout.varHandle(groupElement("buffers"))
        val bufferCount_VH: VarHandle = layout.varHandle(groupElement("bufferCount"))
        val samplers_VH: VarHandle = layout.varHandle(groupElement("samplers"))
        val samplerCount_VH: VarHandle = layout.varHandle(groupElement("samplerCount"))
        val textureViews_VH: VarHandle = layout.varHandle(groupElement("textureViews"))
        val textureViewCount_VH: VarHandle = layout.varHandle(groupElement("textureViewCount"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupEntryExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntryExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupEntryExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupEntryExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var buffers: NativeAddress
            get() = (buffers_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = buffers_VH.set(handler.handler, 0L, value.handler)
        override var bufferCount: ULong
            get() = (bufferCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = bufferCount_VH.set(handler.handler, 0L, value.toLong())
        override var samplers: NativeAddress
            get() = (samplers_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = samplers_VH.set(handler.handler, 0L, value.handler)
        override var samplerCount: ULong
            get() = (samplerCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = samplerCount_VH.set(handler.handler, 0L, value.toLong())
        override var textureViews: NativeAddress
            get() = (textureViews_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = textureViews_VH.set(handler.handler, 0L, value.handler)
        override var textureViewCount: ULong
            get() = (textureViewCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = textureViewCount_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUBindGroupLayoutEntryExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var count: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("count"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUBindGroupLayoutEntryExtras")
        
        val count_VH: VarHandle = layout.varHandle(groupElement("count"))
        
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntryExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntryExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntryExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUBindGroupLayoutEntryExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var count: UInt
            get() = (count_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = count_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUQuerySetDescriptorExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var pipelineStatistics: NativeAddress
    actual var pipelineStatisticCount: ULong
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("pipelineStatistics"),
            ValueLayout.JAVA_LONG.withName("pipelineStatisticCount")
        ).withName("WGPUQuerySetDescriptorExtras")
        
        val pipelineStatistics_VH: VarHandle = layout.varHandle(groupElement("pipelineStatistics"))
        val pipelineStatisticCount_VH: VarHandle = layout.varHandle(groupElement("pipelineStatisticCount"))
        
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptorExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptorExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptorExtras) -> Unit): ArrayHolder<WGPUQuerySetDescriptorExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUQuerySetDescriptorExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var pipelineStatistics: NativeAddress
            get() = (pipelineStatistics_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = pipelineStatistics_VH.set(handler.handler, 0L, value.handler)
        override var pipelineStatisticCount: ULong
            get() = (pipelineStatisticCount_VH.get(handler.handler, 0L) as Long).toULong() as ULong
            set(value) = pipelineStatisticCount_VH.set(handler.handler, 0L, value.toLong())
    }
}

actual interface WGPUSurfaceConfigurationExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var desiredMaximumFrameLatency: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("desiredMaximumFrameLatency"),
            MemoryLayout.paddingLayout(4)
        ).withName("WGPUSurfaceConfigurationExtras")
        
        val desiredMaximumFrameLatency_VH: VarHandle = layout.varHandle(groupElement("desiredMaximumFrameLatency"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceConfigurationExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfigurationExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfigurationExtras) -> Unit): ArrayHolder<WGPUSurfaceConfigurationExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceConfigurationExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var desiredMaximumFrameLatency: UInt
            get() = (desiredMaximumFrameLatency_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = desiredMaximumFrameLatency_VH.set(handler.handler, 0L, value.toInt())
    }
}

actual interface WGPUSurfaceSourceSwapChainPanel : CStructure {
    actual var chain: WGPUChainedStruct
    actual var panelNative: NativeAddress
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.ADDRESS.withName("panelNative")
        ).withName("WGPUSurfaceSourceSwapChainPanel")
        
        val panelNative_VH: VarHandle = layout.varHandle(groupElement("panelNative"))
        
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceSwapChainPanel = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceSwapChainPanel = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceSwapChainPanel) -> Unit): ArrayHolder<WGPUSurfaceSourceSwapChainPanel> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUSurfaceSourceSwapChainPanel {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var panelNative: NativeAddress
            get() = (panelNative_VH.get(handler.handler, 0L) as? MemorySegment)?.let(::NativeAddress) ?: NativeAddress(MemorySegment.NULL)
            set(value) = panelNative_VH.set(handler.handler, 0L, value.handler)
    }
}

actual interface WGPUPrimitiveStateExtras : CStructure {
    actual var chain: WGPUChainedStruct
    actual var polygonMode: WGPUPolygonMode
    actual var conservative: UInt
    actual override val handler: NativeAddress
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            WGPUChainedStruct.layout.withName("chain"),
            ValueLayout.JAVA_INT.withName("polygonMode"),
            ValueLayout.JAVA_INT.withName("conservative")
        ).withName("WGPUPrimitiveStateExtras")
        
        val polygonMode_VH: VarHandle = layout.varHandle(groupElement("polygonMode"))
        val conservative_VH: VarHandle = layout.varHandle(groupElement("conservative"))
        
        actual operator fun invoke(address: NativeAddress): WGPUPrimitiveStateExtras = ByReference(address)
        actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveStateExtras = ByReference(allocator.allocate(layout.byteSize()))
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveStateExtras) -> Unit): ArrayHolder<WGPUPrimitiveStateExtras> {
            val byteSize = layout.byteSize()
            val segment = allocator.allocate(byteSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
    
    @JvmInline
    value class ByReference(override val handler: NativeAddress) : WGPUPrimitiveStateExtras {
        override var chain: WGPUChainedStruct
            get() = WGPUChainedStruct(NativeAddress(handler.handler.asSlice(Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())))
            set(value) {
                MemorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset(groupElement("chain")), Companion.layout.select(groupElement("chain")).byteSize())
            }
        override var polygonMode: WGPUPolygonMode
            get() = (polygonMode_VH.get(handler.handler, 0L) as Int).toUInt() as WGPUPolygonMode
            set(value) = polygonMode_VH.set(handler.handler, 0L, value.toInt())
        override var conservative: UInt
            get() = (conservative_VH.get(handler.handler, 0L) as Int).toUInt() as UInt
            set(value) = conservative_VH.set(handler.handler, 0L, value.toInt())
    }
}

