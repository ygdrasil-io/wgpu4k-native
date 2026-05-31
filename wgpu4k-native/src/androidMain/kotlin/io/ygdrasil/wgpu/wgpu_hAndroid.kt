package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.CallbackHolder
import io.ygdrasil.kffi.CString
import io.ygdrasil.kffi.ArrayHolder
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.toAddress

actual interface WGPUStringView {
    actual var data: CString?
    actual var length: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUStringView {
            return ByReference(io.ygdrasil.wgpu.android.WGPUStringView.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUStringView {
            val ref = io.ygdrasil.wgpu.android.WGPUStringView.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStringView) -> Unit): ArrayHolder<WGPUStringView> {
            val ref = io.ygdrasil.wgpu.android.WGPUStringView.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUStringView.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUStringView.ByReference = io.ygdrasil.wgpu.android.WGPUStringView.ByReference(com.sun.jna.Pointer.NULL)) : WGPUStringView {
        override var data: CString?
            get() = handle.data?.let(::CString)
            set(value) { handle.data = value?.handler }
        override var length: ULong
            get() = handle.length.toULong() as ULong
            set(value) { handle.length = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUStringView.ByValue = io.ygdrasil.wgpu.android.WGPUStringView.ByValue(com.sun.jna.Pointer.NULL)) : WGPUStringView {
        override var data: CString?
            get() = handle.data?.let(::CString)
            set(value) { handle.data = value?.handler }
        override var length: ULong
            get() = handle.length.toULong() as ULong
            set(value) { handle.length = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUAdapterImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUAdapterImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUAdapterImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterImpl) -> Unit): ArrayHolder<WGPUAdapterImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByReference = io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUAdapterImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByValue = io.ygdrasil.wgpu.android.WGPUAdapterImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUAdapterImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupImpl) -> Unit): ArrayHolder<WGPUBindGroupImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupLayoutImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutImpl) -> Unit): ArrayHolder<WGPUBindGroupLayoutImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBufferImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBufferImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBufferImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferImpl) -> Unit): ArrayHolder<WGPUBufferImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBufferImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBufferImpl.ByReference = io.ygdrasil.wgpu.android.WGPUBufferImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBufferImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBufferImpl.ByValue = io.ygdrasil.wgpu.android.WGPUBufferImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBufferImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCommandBufferImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCommandBufferImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandBufferImpl) -> Unit): ArrayHolder<WGPUCommandBufferImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByReference = io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCommandBufferImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByValue = io.ygdrasil.wgpu.android.WGPUCommandBufferImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCommandBufferImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCommandEncoderImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCommandEncoderImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandEncoderImpl) -> Unit): ArrayHolder<WGPUCommandEncoderImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByReference = io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCommandEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByValue = io.ygdrasil.wgpu.android.WGPUCommandEncoderImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCommandEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUComputePassEncoderImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUComputePassEncoderImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePassEncoderImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePassEncoderImpl) -> Unit): ArrayHolder<WGPUComputePassEncoderImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByReference = io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUComputePassEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByValue = io.ygdrasil.wgpu.android.WGPUComputePassEncoderImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUComputePassEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUComputePipelineImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUComputePipelineImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePipelineImpl) -> Unit): ArrayHolder<WGPUComputePipelineImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByReference = io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUComputePipelineImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByValue = io.ygdrasil.wgpu.android.WGPUComputePipelineImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUComputePipelineImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUDeviceImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUDeviceImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceImpl) -> Unit): ArrayHolder<WGPUDeviceImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByReference = io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUDeviceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByValue = io.ygdrasil.wgpu.android.WGPUDeviceImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUDeviceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUExternalTextureImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureImpl) -> Unit): ArrayHolder<WGPUExternalTextureImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByReference = io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByValue = io.ygdrasil.wgpu.android.WGPUExternalTextureImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUInstanceImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUInstanceImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceImpl) -> Unit): ArrayHolder<WGPUInstanceImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByReference = io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUInstanceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByValue = io.ygdrasil.wgpu.android.WGPUInstanceImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUInstanceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPipelineLayoutImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutImpl) -> Unit): ArrayHolder<WGPUPipelineLayoutImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByReference = io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByValue = io.ygdrasil.wgpu.android.WGPUPipelineLayoutImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQuerySetImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetImpl) -> Unit): ArrayHolder<WGPUQuerySetImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByReference = io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQuerySetImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByValue = io.ygdrasil.wgpu.android.WGPUQuerySetImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQuerySetImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQueueImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQueueImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQueueImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueImpl) -> Unit): ArrayHolder<WGPUQueueImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQueueImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQueueImpl.ByReference = io.ygdrasil.wgpu.android.WGPUQueueImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQueueImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQueueImpl.ByValue = io.ygdrasil.wgpu.android.WGPUQueueImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQueueImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderBundleImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleImpl {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleImpl) -> Unit): ArrayHolder<WGPURenderBundleImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByReference = io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderBundleImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByValue = io.ygdrasil.wgpu.android.WGPURenderBundleImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderBundleImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderBundleEncoderImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderImpl {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleEncoderImpl) -> Unit): ArrayHolder<WGPURenderBundleEncoderImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByReference = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderBundleEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByValue = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderBundleEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPassEncoderImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPassEncoderImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassEncoderImpl {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassEncoderImpl) -> Unit): ArrayHolder<WGPURenderPassEncoderImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByReference = io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPassEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByValue = io.ygdrasil.wgpu.android.WGPURenderPassEncoderImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPassEncoderImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPipelineImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPipelineImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineImpl {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPipelineImpl) -> Unit): ArrayHolder<WGPURenderPipelineImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByReference = io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPipelineImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByValue = io.ygdrasil.wgpu.android.WGPURenderPipelineImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPipelineImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSamplerImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSamplerImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerImpl) -> Unit): ArrayHolder<WGPUSamplerImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByReference = io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSamplerImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByValue = io.ygdrasil.wgpu.android.WGPUSamplerImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSamplerImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderModuleImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleImpl) -> Unit): ArrayHolder<WGPUShaderModuleImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByReference = io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByValue = io.ygdrasil.wgpu.android.WGPUShaderModuleImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceImpl) -> Unit): ArrayHolder<WGPUSurfaceImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureImpl) -> Unit): ArrayHolder<WGPUTextureImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureImpl.ByReference = io.ygdrasil.wgpu.android.WGPUTextureImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureImpl.ByValue = io.ygdrasil.wgpu.android.WGPUTextureImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureViewImpl {
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureViewImpl {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewImpl {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureViewImpl) -> Unit): ArrayHolder<WGPUTextureViewImpl> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByReference = io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureViewImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByValue = io.ygdrasil.wgpu.android.WGPUTextureViewImpl.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureViewImpl {
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUChainedStruct {
    actual var next: WGPUChainedStruct?
    actual var sType: WGPUSType
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUChainedStruct {
            return ByReference(io.ygdrasil.wgpu.android.WGPUChainedStruct.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUChainedStruct {
            val ref = io.ygdrasil.wgpu.android.WGPUChainedStruct.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUChainedStruct) -> Unit): ArrayHolder<WGPUChainedStruct> {
            val ref = io.ygdrasil.wgpu.android.WGPUChainedStruct.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUChainedStruct.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUChainedStruct.ByReference = io.ygdrasil.wgpu.android.WGPUChainedStruct.ByReference(com.sun.jna.Pointer.NULL)) : WGPUChainedStruct {
        override var next: WGPUChainedStruct?
            get() = handle.next?.let { WGPUChainedStruct(it) }
            set(value) { handle.next = value?.handler }
        override var sType: WGPUSType
            get() = handle.sType.toUInt() as WGPUSType
            set(value) { handle.sType = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUChainedStruct.ByValue = io.ygdrasil.wgpu.android.WGPUChainedStruct.ByValue(com.sun.jna.Pointer.NULL)) : WGPUChainedStruct {
        override var next: WGPUChainedStruct?
            get() = handle.next?.let { WGPUChainedStruct(it) }
            set(value) { handle.next = value?.handler }
        override var sType: WGPUSType
            get() = handle.sType.toUInt() as WGPUSType
            set(value) { handle.sType = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBufferMapCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBufferMapCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferMapCallbackInfo) -> Unit): ArrayHolder<WGPUBufferMapCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBufferMapCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUBufferMapCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBufferMapCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCompilationInfoCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCompilationInfoCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfoCallbackInfo) -> Unit): ArrayHolder<WGPUCompilationInfoCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCompilationInfoCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUCompilationInfoCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCompilationInfoCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCreateComputePipelineAsyncCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCreateComputePipelineAsyncCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateComputePipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateComputePipelineAsyncCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCreateComputePipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUCreateComputePipelineAsyncCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCreateComputePipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCreateRenderPipelineAsyncCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCreateRenderPipelineAsyncCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateRenderPipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateRenderPipelineAsyncCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCreateRenderPipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUCreateRenderPipelineAsyncCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCreateRenderPipelineAsyncCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUDeviceLostCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUDeviceLostCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceLostCallbackInfo) -> Unit): ArrayHolder<WGPUDeviceLostCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUDeviceLostCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUDeviceLostCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUDeviceLostCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPopErrorScopeCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPopErrorScopeCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPopErrorScopeCallbackInfo) -> Unit): ArrayHolder<WGPUPopErrorScopeCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPopErrorScopeCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUPopErrorScopeCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPopErrorScopeCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQueueWorkDoneCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQueueWorkDoneCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueWorkDoneCallbackInfo) -> Unit): ArrayHolder<WGPUQueueWorkDoneCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQueueWorkDoneCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUQueueWorkDoneCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQueueWorkDoneCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURequestAdapterCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterCallbackInfo) -> Unit): ArrayHolder<WGPURequestAdapterCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPURequestAdapterCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURequestDeviceCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var mode: WGPUCallbackMode
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURequestDeviceCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestDeviceCallbackInfo) -> Unit): ArrayHolder<WGPURequestDeviceCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPURequestDeviceCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPURequestDeviceCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPURequestDeviceCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var mode: WGPUCallbackMode
            get() = handle.mode.toUInt() as WGPUCallbackMode
            set(value) { handle.mode = value.toInt() }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUUncapturedErrorCallbackInfo {
    actual var nextInChain: NativeAddress
    actual var callback: NativeAddress
    actual var userdata1: NativeAddress
    actual var userdata2: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUUncapturedErrorCallbackInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUUncapturedErrorCallbackInfo) -> Unit): ArrayHolder<WGPUUncapturedErrorCallbackInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByReference = io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUUncapturedErrorCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByValue = io.ygdrasil.wgpu.android.WGPUUncapturedErrorCallbackInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUUncapturedErrorCallbackInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var callback: NativeAddress
            get() = handle.callback ?: com.sun.jna.Pointer.NULL
            set(value) { handle.callback = value }
        override var userdata1: NativeAddress
            get() = handle.userdata1 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata1 = value }
        override var userdata2: NativeAddress
            get() = handle.userdata2 ?: com.sun.jna.Pointer.NULL
            set(value) { handle.userdata2 = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUAdapterInfo {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUAdapterInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterInfo) -> Unit): ArrayHolder<WGPUAdapterInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByReference = io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUAdapterInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var vendor: WGPUStringView
            get() = handle.vendor?.let { WGPUStringView.ByReference(it) } ?: error("vendor is null")
            set(value) { handle.vendor = (value as WGPUStringView.ByReference).handle }
        override var architecture: WGPUStringView
            get() = handle.architecture?.let { WGPUStringView.ByReference(it) } ?: error("architecture is null")
            set(value) { handle.architecture = (value as WGPUStringView.ByReference).handle }
        override var device: WGPUStringView
            get() = handle.device?.let { WGPUStringView.ByReference(it) } ?: error("device is null")
            set(value) { handle.device = (value as WGPUStringView.ByReference).handle }
        override var description: WGPUStringView
            get() = handle.description?.let { WGPUStringView.ByReference(it) } ?: error("description is null")
            set(value) { handle.description = (value as WGPUStringView.ByReference).handle }
        override var backendType: WGPUBackendType
            get() = handle.backendType.toUInt() as WGPUBackendType
            set(value) { handle.backendType = value.toInt() }
        override var adapterType: WGPUAdapterType
            get() = handle.adapterType.toUInt() as WGPUAdapterType
            set(value) { handle.adapterType = value.toInt() }
        override var vendorID: UInt
            get() = handle.vendorID.toUInt() as UInt
            set(value) { handle.vendorID = value.toInt() }
        override var deviceID: UInt
            get() = handle.deviceID.toUInt() as UInt
            set(value) { handle.deviceID = value.toInt() }
        override var subgroupMinSize: UInt
            get() = handle.subgroupMinSize.toUInt() as UInt
            set(value) { handle.subgroupMinSize = value.toInt() }
        override var subgroupMaxSize: UInt
            get() = handle.subgroupMaxSize.toUInt() as UInt
            set(value) { handle.subgroupMaxSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByValue = io.ygdrasil.wgpu.android.WGPUAdapterInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUAdapterInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var vendor: WGPUStringView
            get() = handle.vendor?.let { WGPUStringView.ByReference(it) } ?: error("vendor is null")
            set(value) { handle.vendor = (value as WGPUStringView.ByReference).handle }
        override var architecture: WGPUStringView
            get() = handle.architecture?.let { WGPUStringView.ByReference(it) } ?: error("architecture is null")
            set(value) { handle.architecture = (value as WGPUStringView.ByReference).handle }
        override var device: WGPUStringView
            get() = handle.device?.let { WGPUStringView.ByReference(it) } ?: error("device is null")
            set(value) { handle.device = (value as WGPUStringView.ByReference).handle }
        override var description: WGPUStringView
            get() = handle.description?.let { WGPUStringView.ByReference(it) } ?: error("description is null")
            set(value) { handle.description = (value as WGPUStringView.ByReference).handle }
        override var backendType: WGPUBackendType
            get() = handle.backendType.toUInt() as WGPUBackendType
            set(value) { handle.backendType = value.toInt() }
        override var adapterType: WGPUAdapterType
            get() = handle.adapterType.toUInt() as WGPUAdapterType
            set(value) { handle.adapterType = value.toInt() }
        override var vendorID: UInt
            get() = handle.vendorID.toUInt() as UInt
            set(value) { handle.vendorID = value.toInt() }
        override var deviceID: UInt
            get() = handle.deviceID.toUInt() as UInt
            set(value) { handle.deviceID = value.toInt() }
        override var subgroupMinSize: UInt
            get() = handle.subgroupMinSize.toUInt() as UInt
            set(value) { handle.subgroupMinSize = value.toInt() }
        override var subgroupMaxSize: UInt
            get() = handle.subgroupMaxSize.toUInt() as UInt
            set(value) { handle.subgroupMaxSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBlendComponent {
    actual var operation: WGPUBlendOperation
    actual var srcFactor: WGPUBlendFactor
    actual var dstFactor: WGPUBlendFactor
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBlendComponent {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBlendComponent.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBlendComponent {
            val ref = io.ygdrasil.wgpu.android.WGPUBlendComponent.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendComponent) -> Unit): ArrayHolder<WGPUBlendComponent> {
            val ref = io.ygdrasil.wgpu.android.WGPUBlendComponent.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBlendComponent.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBlendComponent.ByReference = io.ygdrasil.wgpu.android.WGPUBlendComponent.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBlendComponent {
        override var operation: WGPUBlendOperation
            get() = handle.operation.toUInt() as WGPUBlendOperation
            set(value) { handle.operation = value.toInt() }
        override var srcFactor: WGPUBlendFactor
            get() = handle.srcFactor.toUInt() as WGPUBlendFactor
            set(value) { handle.srcFactor = value.toInt() }
        override var dstFactor: WGPUBlendFactor
            get() = handle.dstFactor.toUInt() as WGPUBlendFactor
            set(value) { handle.dstFactor = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBlendComponent.ByValue = io.ygdrasil.wgpu.android.WGPUBlendComponent.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBlendComponent {
        override var operation: WGPUBlendOperation
            get() = handle.operation.toUInt() as WGPUBlendOperation
            set(value) { handle.operation = value.toInt() }
        override var srcFactor: WGPUBlendFactor
            get() = handle.srcFactor.toUInt() as WGPUBlendFactor
            set(value) { handle.srcFactor = value.toInt() }
        override var dstFactor: WGPUBlendFactor
            get() = handle.dstFactor.toUInt() as WGPUBlendFactor
            set(value) { handle.dstFactor = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBufferBindingLayout {
    actual var nextInChain: NativeAddress
    actual var type: WGPUBufferBindingType
    actual var hasDynamicOffset: UInt
    actual var minBindingSize: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBufferBindingLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferBindingLayout) -> Unit): ArrayHolder<WGPUBufferBindingLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByReference = io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBufferBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var type: WGPUBufferBindingType
            get() = handle.type.toUInt() as WGPUBufferBindingType
            set(value) { handle.type = value.toInt() }
        override var hasDynamicOffset: UInt
            get() = handle.hasDynamicOffset.toUInt() as UInt
            set(value) { handle.hasDynamicOffset = value.toInt() }
        override var minBindingSize: ULong
            get() = handle.minBindingSize.toULong() as ULong
            set(value) { handle.minBindingSize = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByValue = io.ygdrasil.wgpu.android.WGPUBufferBindingLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBufferBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var type: WGPUBufferBindingType
            get() = handle.type.toUInt() as WGPUBufferBindingType
            set(value) { handle.type = value.toInt() }
        override var hasDynamicOffset: UInt
            get() = handle.hasDynamicOffset.toUInt() as UInt
            set(value) { handle.hasDynamicOffset = value.toInt() }
        override var minBindingSize: ULong
            get() = handle.minBindingSize.toULong() as ULong
            set(value) { handle.minBindingSize = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBufferDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var usage: ULong
    actual var size: ULong
    actual var mappedAtCreation: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBufferDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferDescriptor) -> Unit): ArrayHolder<WGPUBufferDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var size: ULong
            get() = handle.size.toULong() as ULong
            set(value) { handle.size = value.toLong() }
        override var mappedAtCreation: UInt
            get() = handle.mappedAtCreation.toUInt() as UInt
            set(value) { handle.mappedAtCreation = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUBufferDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var size: ULong
            get() = handle.size.toULong() as ULong
            set(value) { handle.size = value.toLong() }
        override var mappedAtCreation: UInt
            get() = handle.mappedAtCreation.toUInt() as UInt
            set(value) { handle.mappedAtCreation = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUColor {
    actual var r: Double
    actual var g: Double
    actual var b: Double
    actual var a: Double
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUColor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUColor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUColor {
            val ref = io.ygdrasil.wgpu.android.WGPUColor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColor) -> Unit): ArrayHolder<WGPUColor> {
            val ref = io.ygdrasil.wgpu.android.WGPUColor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUColor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUColor.ByReference = io.ygdrasil.wgpu.android.WGPUColor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUColor {
        override var r: Double
            get() = handle.r as Double
            set(value) { handle.r = value }
        override var g: Double
            get() = handle.g as Double
            set(value) { handle.g = value }
        override var b: Double
            get() = handle.b as Double
            set(value) { handle.b = value }
        override var a: Double
            get() = handle.a as Double
            set(value) { handle.a = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUColor.ByValue = io.ygdrasil.wgpu.android.WGPUColor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUColor {
        override var r: Double
            get() = handle.r as Double
            set(value) { handle.r = value }
        override var g: Double
            get() = handle.g as Double
            set(value) { handle.g = value }
        override var b: Double
            get() = handle.b as Double
            set(value) { handle.b = value }
        override var a: Double
            get() = handle.a as Double
            set(value) { handle.a = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCommandBufferDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCommandBufferDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandBufferDescriptor) -> Unit): ArrayHolder<WGPUCommandBufferDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCommandBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUCommandBufferDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCommandBufferDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCommandEncoderDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCommandEncoderDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandEncoderDescriptor) -> Unit): ArrayHolder<WGPUCommandEncoderDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCommandEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUCommandEncoderDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCommandEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCompatibilityModeLimits {
    actual var chain: WGPUChainedStruct
    actual var maxStorageBuffersInVertexStage: UInt
    actual var maxStorageTexturesInVertexStage: UInt
    actual var maxStorageBuffersInFragmentStage: UInt
    actual var maxStorageTexturesInFragmentStage: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCompatibilityModeLimits {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCompatibilityModeLimits {
            val ref = io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompatibilityModeLimits) -> Unit): ArrayHolder<WGPUCompatibilityModeLimits> {
            val ref = io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByReference = io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCompatibilityModeLimits {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxStorageBuffersInVertexStage: UInt
            get() = handle.maxStorageBuffersInVertexStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersInVertexStage = value.toInt() }
        override var maxStorageTexturesInVertexStage: UInt
            get() = handle.maxStorageTexturesInVertexStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesInVertexStage = value.toInt() }
        override var maxStorageBuffersInFragmentStage: UInt
            get() = handle.maxStorageBuffersInFragmentStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersInFragmentStage = value.toInt() }
        override var maxStorageTexturesInFragmentStage: UInt
            get() = handle.maxStorageTexturesInFragmentStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesInFragmentStage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByValue = io.ygdrasil.wgpu.android.WGPUCompatibilityModeLimits.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCompatibilityModeLimits {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxStorageBuffersInVertexStage: UInt
            get() = handle.maxStorageBuffersInVertexStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersInVertexStage = value.toInt() }
        override var maxStorageTexturesInVertexStage: UInt
            get() = handle.maxStorageTexturesInVertexStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesInVertexStage = value.toInt() }
        override var maxStorageBuffersInFragmentStage: UInt
            get() = handle.maxStorageBuffersInFragmentStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersInFragmentStage = value.toInt() }
        override var maxStorageTexturesInFragmentStage: UInt
            get() = handle.maxStorageTexturesInFragmentStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesInFragmentStage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCompilationMessage {
    actual var nextInChain: NativeAddress
    actual var message: WGPUStringView
    actual var type: WGPUCompilationMessageType
    actual var lineNum: ULong
    actual var linePos: ULong
    actual var offset: ULong
    actual var length: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCompilationMessage {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationMessage) -> Unit): ArrayHolder<WGPUCompilationMessage> {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByReference = io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCompilationMessage {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var message: WGPUStringView
            get() = handle.message?.let { WGPUStringView.ByReference(it) } ?: error("message is null")
            set(value) { handle.message = (value as WGPUStringView.ByReference).handle }
        override var type: WGPUCompilationMessageType
            get() = handle.type.toUInt() as WGPUCompilationMessageType
            set(value) { handle.type = value.toInt() }
        override var lineNum: ULong
            get() = handle.lineNum.toULong() as ULong
            set(value) { handle.lineNum = value.toLong() }
        override var linePos: ULong
            get() = handle.linePos.toULong() as ULong
            set(value) { handle.linePos = value.toLong() }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var length: ULong
            get() = handle.length.toULong() as ULong
            set(value) { handle.length = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByValue = io.ygdrasil.wgpu.android.WGPUCompilationMessage.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCompilationMessage {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var message: WGPUStringView
            get() = handle.message?.let { WGPUStringView.ByReference(it) } ?: error("message is null")
            set(value) { handle.message = (value as WGPUStringView.ByReference).handle }
        override var type: WGPUCompilationMessageType
            get() = handle.type.toUInt() as WGPUCompilationMessageType
            set(value) { handle.type = value.toInt() }
        override var lineNum: ULong
            get() = handle.lineNum.toULong() as ULong
            set(value) { handle.lineNum = value.toLong() }
        override var linePos: ULong
            get() = handle.linePos.toULong() as ULong
            set(value) { handle.linePos = value.toLong() }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var length: ULong
            get() = handle.length.toULong() as ULong
            set(value) { handle.length = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUConstantEntry {
    actual var nextInChain: NativeAddress
    actual var key: WGPUStringView
    actual var value: Double
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUConstantEntry {
            return ByReference(io.ygdrasil.wgpu.android.WGPUConstantEntry.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUConstantEntry {
            val ref = io.ygdrasil.wgpu.android.WGPUConstantEntry.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUConstantEntry) -> Unit): ArrayHolder<WGPUConstantEntry> {
            val ref = io.ygdrasil.wgpu.android.WGPUConstantEntry.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUConstantEntry.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUConstantEntry.ByReference = io.ygdrasil.wgpu.android.WGPUConstantEntry.ByReference(com.sun.jna.Pointer.NULL)) : WGPUConstantEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var key: WGPUStringView
            get() = handle.key?.let { WGPUStringView.ByReference(it) } ?: error("key is null")
            set(value) { handle.key = (value as WGPUStringView.ByReference).handle }
        override var value: Double
            get() = handle.value as Double
            set(value) { handle.value = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUConstantEntry.ByValue = io.ygdrasil.wgpu.android.WGPUConstantEntry.ByValue(com.sun.jna.Pointer.NULL)) : WGPUConstantEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var key: WGPUStringView
            get() = handle.key?.let { WGPUStringView.ByReference(it) } ?: error("key is null")
            set(value) { handle.key = (value as WGPUStringView.ByReference).handle }
        override var value: Double
            get() = handle.value as Double
            set(value) { handle.value = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUExtent3D {
    actual var width: UInt
    actual var height: UInt
    actual var depthOrArrayLayers: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUExtent3D {
            return ByReference(io.ygdrasil.wgpu.android.WGPUExtent3D.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUExtent3D {
            val ref = io.ygdrasil.wgpu.android.WGPUExtent3D.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExtent3D) -> Unit): ArrayHolder<WGPUExtent3D> {
            val ref = io.ygdrasil.wgpu.android.WGPUExtent3D.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUExtent3D.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUExtent3D.ByReference = io.ygdrasil.wgpu.android.WGPUExtent3D.ByReference(com.sun.jna.Pointer.NULL)) : WGPUExtent3D {
        override var width: UInt
            get() = handle.width.toUInt() as UInt
            set(value) { handle.width = value.toInt() }
        override var height: UInt
            get() = handle.height.toUInt() as UInt
            set(value) { handle.height = value.toInt() }
        override var depthOrArrayLayers: UInt
            get() = handle.depthOrArrayLayers.toUInt() as UInt
            set(value) { handle.depthOrArrayLayers = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUExtent3D.ByValue = io.ygdrasil.wgpu.android.WGPUExtent3D.ByValue(com.sun.jna.Pointer.NULL)) : WGPUExtent3D {
        override var width: UInt
            get() = handle.width.toUInt() as UInt
            set(value) { handle.width = value.toInt() }
        override var height: UInt
            get() = handle.height.toUInt() as UInt
            set(value) { handle.height = value.toInt() }
        override var depthOrArrayLayers: UInt
            get() = handle.depthOrArrayLayers.toUInt() as UInt
            set(value) { handle.depthOrArrayLayers = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUExternalTextureBindingEntry {
    actual var chain: WGPUChainedStruct
    actual var externalTexture: WGPUExternalTextureImpl?
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingEntry {
            return ByReference(io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingEntry {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingEntry) -> Unit): ArrayHolder<WGPUExternalTextureBindingEntry> {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByReference = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByReference(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureBindingEntry {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var externalTexture: WGPUExternalTextureImpl?
            get() = handle.externalTexture?.let { WGPUExternalTextureImpl(it) }
            set(value) { handle.externalTexture = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByValue = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingEntry.ByValue(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureBindingEntry {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var externalTexture: WGPUExternalTextureImpl?
            get() = handle.externalTexture?.let { WGPUExternalTextureImpl(it) }
            set(value) { handle.externalTexture = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUExternalTextureBindingLayout {
    actual var chain: WGPUChainedStruct
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingLayout) -> Unit): ArrayHolder<WGPUExternalTextureBindingLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByReference = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureBindingLayout {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByValue = io.ygdrasil.wgpu.android.WGPUExternalTextureBindingLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUExternalTextureBindingLayout {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUFuture {
    actual var id: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUFuture {
            return ByReference(io.ygdrasil.wgpu.android.WGPUFuture.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUFuture {
            val ref = io.ygdrasil.wgpu.android.WGPUFuture.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFuture) -> Unit): ArrayHolder<WGPUFuture> {
            val ref = io.ygdrasil.wgpu.android.WGPUFuture.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUFuture.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUFuture.ByReference = io.ygdrasil.wgpu.android.WGPUFuture.ByReference(com.sun.jna.Pointer.NULL)) : WGPUFuture {
        override var id: ULong
            get() = handle.id.toULong() as ULong
            set(value) { handle.id = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUFuture.ByValue = io.ygdrasil.wgpu.android.WGPUFuture.ByValue(com.sun.jna.Pointer.NULL)) : WGPUFuture {
        override var id: ULong
            get() = handle.id.toULong() as ULong
            set(value) { handle.id = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUInstanceLimits {
    actual var nextInChain: NativeAddress
    actual var timedWaitAnyMaxCount: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUInstanceLimits {
            return ByReference(io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceLimits {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceLimits) -> Unit): ArrayHolder<WGPUInstanceLimits> {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByReference = io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByReference(com.sun.jna.Pointer.NULL)) : WGPUInstanceLimits {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var timedWaitAnyMaxCount: ULong
            get() = handle.timedWaitAnyMaxCount.toULong() as ULong
            set(value) { handle.timedWaitAnyMaxCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByValue = io.ygdrasil.wgpu.android.WGPUInstanceLimits.ByValue(com.sun.jna.Pointer.NULL)) : WGPUInstanceLimits {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var timedWaitAnyMaxCount: ULong
            get() = handle.timedWaitAnyMaxCount.toULong() as ULong
            set(value) { handle.timedWaitAnyMaxCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUMultisampleState {
    actual var nextInChain: NativeAddress
    actual var count: UInt
    actual var mask: UInt
    actual var alphaToCoverageEnabled: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUMultisampleState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUMultisampleState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUMultisampleState {
            val ref = io.ygdrasil.wgpu.android.WGPUMultisampleState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUMultisampleState) -> Unit): ArrayHolder<WGPUMultisampleState> {
            val ref = io.ygdrasil.wgpu.android.WGPUMultisampleState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUMultisampleState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUMultisampleState.ByReference = io.ygdrasil.wgpu.android.WGPUMultisampleState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUMultisampleState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override var mask: UInt
            get() = handle.mask.toUInt() as UInt
            set(value) { handle.mask = value.toInt() }
        override var alphaToCoverageEnabled: UInt
            get() = handle.alphaToCoverageEnabled.toUInt() as UInt
            set(value) { handle.alphaToCoverageEnabled = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUMultisampleState.ByValue = io.ygdrasil.wgpu.android.WGPUMultisampleState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUMultisampleState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override var mask: UInt
            get() = handle.mask.toUInt() as UInt
            set(value) { handle.mask = value.toInt() }
        override var alphaToCoverageEnabled: UInt
            get() = handle.alphaToCoverageEnabled.toUInt() as UInt
            set(value) { handle.alphaToCoverageEnabled = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUOrigin3D {
    actual var x: UInt
    actual var y: UInt
    actual var z: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUOrigin3D {
            return ByReference(io.ygdrasil.wgpu.android.WGPUOrigin3D.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUOrigin3D {
            val ref = io.ygdrasil.wgpu.android.WGPUOrigin3D.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUOrigin3D) -> Unit): ArrayHolder<WGPUOrigin3D> {
            val ref = io.ygdrasil.wgpu.android.WGPUOrigin3D.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUOrigin3D.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUOrigin3D.ByReference = io.ygdrasil.wgpu.android.WGPUOrigin3D.ByReference(com.sun.jna.Pointer.NULL)) : WGPUOrigin3D {
        override var x: UInt
            get() = handle.x.toUInt() as UInt
            set(value) { handle.x = value.toInt() }
        override var y: UInt
            get() = handle.y.toUInt() as UInt
            set(value) { handle.y = value.toInt() }
        override var z: UInt
            get() = handle.z.toUInt() as UInt
            set(value) { handle.z = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUOrigin3D.ByValue = io.ygdrasil.wgpu.android.WGPUOrigin3D.ByValue(com.sun.jna.Pointer.NULL)) : WGPUOrigin3D {
        override var x: UInt
            get() = handle.x.toUInt() as UInt
            set(value) { handle.x = value.toInt() }
        override var y: UInt
            get() = handle.y.toUInt() as UInt
            set(value) { handle.y = value.toInt() }
        override var z: UInt
            get() = handle.z.toUInt() as UInt
            set(value) { handle.z = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPassTimestampWrites {
    actual var nextInChain: NativeAddress
    actual var querySet: WGPUQuerySetImpl?
    actual var beginningOfPassWriteIndex: UInt
    actual var endOfPassWriteIndex: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPassTimestampWrites {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPassTimestampWrites {
            val ref = io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPassTimestampWrites) -> Unit): ArrayHolder<WGPUPassTimestampWrites> {
            val ref = io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByReference = io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPassTimestampWrites {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var querySet: WGPUQuerySetImpl?
            get() = handle.querySet?.let { WGPUQuerySetImpl(it) }
            set(value) { handle.querySet = value?.handler }
        override var beginningOfPassWriteIndex: UInt
            get() = handle.beginningOfPassWriteIndex.toUInt() as UInt
            set(value) { handle.beginningOfPassWriteIndex = value.toInt() }
        override var endOfPassWriteIndex: UInt
            get() = handle.endOfPassWriteIndex.toUInt() as UInt
            set(value) { handle.endOfPassWriteIndex = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByValue = io.ygdrasil.wgpu.android.WGPUPassTimestampWrites.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPassTimestampWrites {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var querySet: WGPUQuerySetImpl?
            get() = handle.querySet?.let { WGPUQuerySetImpl(it) }
            set(value) { handle.querySet = value?.handler }
        override var beginningOfPassWriteIndex: UInt
            get() = handle.beginningOfPassWriteIndex.toUInt() as UInt
            set(value) { handle.beginningOfPassWriteIndex = value.toInt() }
        override var endOfPassWriteIndex: UInt
            get() = handle.endOfPassWriteIndex.toUInt() as UInt
            set(value) { handle.endOfPassWriteIndex = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPipelineLayoutDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var bindGroupLayoutCount: ULong
    actual var bindGroupLayouts: NativeAddress
    actual var immediateSize: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutDescriptor) -> Unit): ArrayHolder<WGPUPipelineLayoutDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var bindGroupLayoutCount: ULong
            get() = handle.bindGroupLayoutCount.toULong() as ULong
            set(value) { handle.bindGroupLayoutCount = value.toLong() }
        override var bindGroupLayouts: NativeAddress
            get() = handle.bindGroupLayouts ?: com.sun.jna.Pointer.NULL
            set(value) { handle.bindGroupLayouts = value }
        override var immediateSize: UInt
            get() = handle.immediateSize.toUInt() as UInt
            set(value) { handle.immediateSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUPipelineLayoutDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var bindGroupLayoutCount: ULong
            get() = handle.bindGroupLayoutCount.toULong() as ULong
            set(value) { handle.bindGroupLayoutCount = value.toLong() }
        override var bindGroupLayouts: NativeAddress
            get() = handle.bindGroupLayouts ?: com.sun.jna.Pointer.NULL
            set(value) { handle.bindGroupLayouts = value }
        override var immediateSize: UInt
            get() = handle.immediateSize.toUInt() as UInt
            set(value) { handle.immediateSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPrimitiveState {
    actual var nextInChain: NativeAddress
    actual var topology: WGPUPrimitiveTopology
    actual var stripIndexFormat: WGPUIndexFormat
    actual var frontFace: WGPUFrontFace
    actual var cullMode: WGPUCullMode
    actual var unclippedDepth: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPrimitiveState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState {
            val ref = io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveState) -> Unit): ArrayHolder<WGPUPrimitiveState> {
            val ref = io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByReference = io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPrimitiveState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var topology: WGPUPrimitiveTopology
            get() = handle.topology.toUInt() as WGPUPrimitiveTopology
            set(value) { handle.topology = value.toInt() }
        override var stripIndexFormat: WGPUIndexFormat
            get() = handle.stripIndexFormat.toUInt() as WGPUIndexFormat
            set(value) { handle.stripIndexFormat = value.toInt() }
        override var frontFace: WGPUFrontFace
            get() = handle.frontFace.toUInt() as WGPUFrontFace
            set(value) { handle.frontFace = value.toInt() }
        override var cullMode: WGPUCullMode
            get() = handle.cullMode.toUInt() as WGPUCullMode
            set(value) { handle.cullMode = value.toInt() }
        override var unclippedDepth: UInt
            get() = handle.unclippedDepth.toUInt() as UInt
            set(value) { handle.unclippedDepth = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByValue = io.ygdrasil.wgpu.android.WGPUPrimitiveState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPrimitiveState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var topology: WGPUPrimitiveTopology
            get() = handle.topology.toUInt() as WGPUPrimitiveTopology
            set(value) { handle.topology = value.toInt() }
        override var stripIndexFormat: WGPUIndexFormat
            get() = handle.stripIndexFormat.toUInt() as WGPUIndexFormat
            set(value) { handle.stripIndexFormat = value.toInt() }
        override var frontFace: WGPUFrontFace
            get() = handle.frontFace.toUInt() as WGPUFrontFace
            set(value) { handle.frontFace = value.toInt() }
        override var cullMode: WGPUCullMode
            get() = handle.cullMode.toUInt() as WGPUCullMode
            set(value) { handle.cullMode = value.toInt() }
        override var unclippedDepth: UInt
            get() = handle.unclippedDepth.toUInt() as UInt
            set(value) { handle.unclippedDepth = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQuerySetDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var type: WGPUQueryType
    actual var count: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptor) -> Unit): ArrayHolder<WGPUQuerySetDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQuerySetDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var type: WGPUQueryType
            get() = handle.type.toUInt() as WGPUQueryType
            set(value) { handle.type = value.toInt() }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQuerySetDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var type: WGPUQueryType
            get() = handle.type.toUInt() as WGPUQueryType
            set(value) { handle.type = value.toInt() }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQueueDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQueueDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueDescriptor) -> Unit): ArrayHolder<WGPUQueueDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQueueDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUQueueDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQueueDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderBundleDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleDescriptor) -> Unit): ArrayHolder<WGPURenderBundleDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderBundleDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPURenderBundleDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderBundleDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderBundleEncoderDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var colorFormatCount: ULong
    actual var colorFormats: NativeAddress
    actual var depthStencilFormat: WGPUTextureFormat
    actual var sampleCount: UInt
    actual var depthReadOnly: UInt
    actual var stencilReadOnly: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleEncoderDescriptor) -> Unit): ArrayHolder<WGPURenderBundleEncoderDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderBundleEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var colorFormatCount: ULong
            get() = handle.colorFormatCount.toULong() as ULong
            set(value) { handle.colorFormatCount = value.toLong() }
        override var colorFormats: NativeAddress
            get() = handle.colorFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.colorFormats = value }
        override var depthStencilFormat: WGPUTextureFormat
            get() = handle.depthStencilFormat.toUInt() as WGPUTextureFormat
            set(value) { handle.depthStencilFormat = value.toInt() }
        override var sampleCount: UInt
            get() = handle.sampleCount.toUInt() as UInt
            set(value) { handle.sampleCount = value.toInt() }
        override var depthReadOnly: UInt
            get() = handle.depthReadOnly.toUInt() as UInt
            set(value) { handle.depthReadOnly = value.toInt() }
        override var stencilReadOnly: UInt
            get() = handle.stencilReadOnly.toUInt() as UInt
            set(value) { handle.stencilReadOnly = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPURenderBundleEncoderDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderBundleEncoderDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var colorFormatCount: ULong
            get() = handle.colorFormatCount.toULong() as ULong
            set(value) { handle.colorFormatCount = value.toLong() }
        override var colorFormats: NativeAddress
            get() = handle.colorFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.colorFormats = value }
        override var depthStencilFormat: WGPUTextureFormat
            get() = handle.depthStencilFormat.toUInt() as WGPUTextureFormat
            set(value) { handle.depthStencilFormat = value.toInt() }
        override var sampleCount: UInt
            get() = handle.sampleCount.toUInt() as UInt
            set(value) { handle.sampleCount = value.toInt() }
        override var depthReadOnly: UInt
            get() = handle.depthReadOnly.toUInt() as UInt
            set(value) { handle.depthReadOnly = value.toInt() }
        override var stencilReadOnly: UInt
            get() = handle.stencilReadOnly.toUInt() as UInt
            set(value) { handle.stencilReadOnly = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPassDepthStencilAttachment {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPassDepthStencilAttachment {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDepthStencilAttachment) -> Unit): ArrayHolder<WGPURenderPassDepthStencilAttachment> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByReference = io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPassDepthStencilAttachment {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var view: WGPUTextureViewImpl?
            get() = handle.view?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.view = value?.handler }
        override var depthLoadOp: WGPULoadOp
            get() = handle.depthLoadOp.toUInt() as WGPULoadOp
            set(value) { handle.depthLoadOp = value.toInt() }
        override var depthStoreOp: WGPUStoreOp
            get() = handle.depthStoreOp.toUInt() as WGPUStoreOp
            set(value) { handle.depthStoreOp = value.toInt() }
        override var depthClearValue: Float
            get() = handle.depthClearValue as Float
            set(value) { handle.depthClearValue = value }
        override var depthReadOnly: UInt
            get() = handle.depthReadOnly.toUInt() as UInt
            set(value) { handle.depthReadOnly = value.toInt() }
        override var stencilLoadOp: WGPULoadOp
            get() = handle.stencilLoadOp.toUInt() as WGPULoadOp
            set(value) { handle.stencilLoadOp = value.toInt() }
        override var stencilStoreOp: WGPUStoreOp
            get() = handle.stencilStoreOp.toUInt() as WGPUStoreOp
            set(value) { handle.stencilStoreOp = value.toInt() }
        override var stencilClearValue: UInt
            get() = handle.stencilClearValue.toUInt() as UInt
            set(value) { handle.stencilClearValue = value.toInt() }
        override var stencilReadOnly: UInt
            get() = handle.stencilReadOnly.toUInt() as UInt
            set(value) { handle.stencilReadOnly = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByValue = io.ygdrasil.wgpu.android.WGPURenderPassDepthStencilAttachment.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPassDepthStencilAttachment {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var view: WGPUTextureViewImpl?
            get() = handle.view?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.view = value?.handler }
        override var depthLoadOp: WGPULoadOp
            get() = handle.depthLoadOp.toUInt() as WGPULoadOp
            set(value) { handle.depthLoadOp = value.toInt() }
        override var depthStoreOp: WGPUStoreOp
            get() = handle.depthStoreOp.toUInt() as WGPUStoreOp
            set(value) { handle.depthStoreOp = value.toInt() }
        override var depthClearValue: Float
            get() = handle.depthClearValue as Float
            set(value) { handle.depthClearValue = value }
        override var depthReadOnly: UInt
            get() = handle.depthReadOnly.toUInt() as UInt
            set(value) { handle.depthReadOnly = value.toInt() }
        override var stencilLoadOp: WGPULoadOp
            get() = handle.stencilLoadOp.toUInt() as WGPULoadOp
            set(value) { handle.stencilLoadOp = value.toInt() }
        override var stencilStoreOp: WGPUStoreOp
            get() = handle.stencilStoreOp.toUInt() as WGPUStoreOp
            set(value) { handle.stencilStoreOp = value.toInt() }
        override var stencilClearValue: UInt
            get() = handle.stencilClearValue.toUInt() as UInt
            set(value) { handle.stencilClearValue = value.toInt() }
        override var stencilReadOnly: UInt
            get() = handle.stencilReadOnly.toUInt() as UInt
            set(value) { handle.stencilReadOnly = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPassMaxDrawCount {
    actual var chain: WGPUChainedStruct
    actual var maxDrawCount: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPassMaxDrawCount {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassMaxDrawCount) -> Unit): ArrayHolder<WGPURenderPassMaxDrawCount> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByReference = io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPassMaxDrawCount {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxDrawCount: ULong
            get() = handle.maxDrawCount.toULong() as ULong
            set(value) { handle.maxDrawCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByValue = io.ygdrasil.wgpu.android.WGPURenderPassMaxDrawCount.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPassMaxDrawCount {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxDrawCount: ULong
            get() = handle.maxDrawCount.toULong() as ULong
            set(value) { handle.maxDrawCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURequestAdapterWebXROptions {
    actual var chain: WGPUChainedStruct
    actual var xrCompatible: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterWebXROptions {
            return ByReference(io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterWebXROptions {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterWebXROptions) -> Unit): ArrayHolder<WGPURequestAdapterWebXROptions> {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByReference = io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByReference(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterWebXROptions {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var xrCompatible: UInt
            get() = handle.xrCompatible.toUInt() as UInt
            set(value) { handle.xrCompatible = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByValue = io.ygdrasil.wgpu.android.WGPURequestAdapterWebXROptions.ByValue(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterWebXROptions {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var xrCompatible: UInt
            get() = handle.xrCompatible.toUInt() as UInt
            set(value) { handle.xrCompatible = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSamplerBindingLayout {
    actual var nextInChain: NativeAddress
    actual var type: WGPUSamplerBindingType
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSamplerBindingLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerBindingLayout) -> Unit): ArrayHolder<WGPUSamplerBindingLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByReference = io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSamplerBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var type: WGPUSamplerBindingType
            get() = handle.type.toUInt() as WGPUSamplerBindingType
            set(value) { handle.type = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByValue = io.ygdrasil.wgpu.android.WGPUSamplerBindingLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSamplerBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var type: WGPUSamplerBindingType
            get() = handle.type.toUInt() as WGPUSamplerBindingType
            set(value) { handle.type = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSamplerDescriptor {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSamplerDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerDescriptor) -> Unit): ArrayHolder<WGPUSamplerDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSamplerDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var addressModeU: WGPUAddressMode
            get() = handle.addressModeU.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeU = value.toInt() }
        override var addressModeV: WGPUAddressMode
            get() = handle.addressModeV.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeV = value.toInt() }
        override var addressModeW: WGPUAddressMode
            get() = handle.addressModeW.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeW = value.toInt() }
        override var magFilter: WGPUFilterMode
            get() = handle.magFilter.toUInt() as WGPUFilterMode
            set(value) { handle.magFilter = value.toInt() }
        override var minFilter: WGPUFilterMode
            get() = handle.minFilter.toUInt() as WGPUFilterMode
            set(value) { handle.minFilter = value.toInt() }
        override var mipmapFilter: WGPUMipmapFilterMode
            get() = handle.mipmapFilter.toUInt() as WGPUMipmapFilterMode
            set(value) { handle.mipmapFilter = value.toInt() }
        override var lodMinClamp: Float
            get() = handle.lodMinClamp as Float
            set(value) { handle.lodMinClamp = value }
        override var lodMaxClamp: Float
            get() = handle.lodMaxClamp as Float
            set(value) { handle.lodMaxClamp = value }
        override var compare: WGPUCompareFunction
            get() = handle.compare.toUInt() as WGPUCompareFunction
            set(value) { handle.compare = value.toInt() }
        override var maxAnisotropy: UShort
            get() = handle.maxAnisotropy.toUShort() as UShort
            set(value) { handle.maxAnisotropy = value.toShort() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUSamplerDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSamplerDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var addressModeU: WGPUAddressMode
            get() = handle.addressModeU.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeU = value.toInt() }
        override var addressModeV: WGPUAddressMode
            get() = handle.addressModeV.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeV = value.toInt() }
        override var addressModeW: WGPUAddressMode
            get() = handle.addressModeW.toUInt() as WGPUAddressMode
            set(value) { handle.addressModeW = value.toInt() }
        override var magFilter: WGPUFilterMode
            get() = handle.magFilter.toUInt() as WGPUFilterMode
            set(value) { handle.magFilter = value.toInt() }
        override var minFilter: WGPUFilterMode
            get() = handle.minFilter.toUInt() as WGPUFilterMode
            set(value) { handle.minFilter = value.toInt() }
        override var mipmapFilter: WGPUMipmapFilterMode
            get() = handle.mipmapFilter.toUInt() as WGPUMipmapFilterMode
            set(value) { handle.mipmapFilter = value.toInt() }
        override var lodMinClamp: Float
            get() = handle.lodMinClamp as Float
            set(value) { handle.lodMinClamp = value }
        override var lodMaxClamp: Float
            get() = handle.lodMaxClamp as Float
            set(value) { handle.lodMaxClamp = value }
        override var compare: WGPUCompareFunction
            get() = handle.compare.toUInt() as WGPUCompareFunction
            set(value) { handle.compare = value.toInt() }
        override var maxAnisotropy: UShort
            get() = handle.maxAnisotropy.toUShort() as UShort
            set(value) { handle.maxAnisotropy = value.toShort() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderSourceSPIRV {
    actual var chain: WGPUChainedStruct
    actual var codeSize: UInt
    actual var code: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceSPIRV {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceSPIRV) -> Unit): ArrayHolder<WGPUShaderSourceSPIRV> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByReference = io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceSPIRV {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var codeSize: UInt
            get() = handle.codeSize.toUInt() as UInt
            set(value) { handle.codeSize = value.toInt() }
        override var code: NativeAddress
            get() = handle.code ?: com.sun.jna.Pointer.NULL
            set(value) { handle.code = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByValue = io.ygdrasil.wgpu.android.WGPUShaderSourceSPIRV.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceSPIRV {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var codeSize: UInt
            get() = handle.codeSize.toUInt() as UInt
            set(value) { handle.codeSize = value.toInt() }
        override var code: NativeAddress
            get() = handle.code ?: com.sun.jna.Pointer.NULL
            set(value) { handle.code = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderSourceWGSL {
    actual var chain: WGPUChainedStruct
    actual var code: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceWGSL {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceWGSL) -> Unit): ArrayHolder<WGPUShaderSourceWGSL> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByReference = io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceWGSL {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var code: WGPUStringView
            get() = handle.code?.let { WGPUStringView.ByReference(it) } ?: error("code is null")
            set(value) { handle.code = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByValue = io.ygdrasil.wgpu.android.WGPUShaderSourceWGSL.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceWGSL {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var code: WGPUStringView
            get() = handle.code?.let { WGPUStringView.ByReference(it) } ?: error("code is null")
            set(value) { handle.code = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUStencilFaceState {
    actual var compare: WGPUCompareFunction
    actual var failOp: WGPUStencilOperation
    actual var depthFailOp: WGPUStencilOperation
    actual var passOp: WGPUStencilOperation
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUStencilFaceState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState {
            val ref = io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStencilFaceState) -> Unit): ArrayHolder<WGPUStencilFaceState> {
            val ref = io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByReference = io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUStencilFaceState {
        override var compare: WGPUCompareFunction
            get() = handle.compare.toUInt() as WGPUCompareFunction
            set(value) { handle.compare = value.toInt() }
        override var failOp: WGPUStencilOperation
            get() = handle.failOp.toUInt() as WGPUStencilOperation
            set(value) { handle.failOp = value.toInt() }
        override var depthFailOp: WGPUStencilOperation
            get() = handle.depthFailOp.toUInt() as WGPUStencilOperation
            set(value) { handle.depthFailOp = value.toInt() }
        override var passOp: WGPUStencilOperation
            get() = handle.passOp.toUInt() as WGPUStencilOperation
            set(value) { handle.passOp = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByValue = io.ygdrasil.wgpu.android.WGPUStencilFaceState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUStencilFaceState {
        override var compare: WGPUCompareFunction
            get() = handle.compare.toUInt() as WGPUCompareFunction
            set(value) { handle.compare = value.toInt() }
        override var failOp: WGPUStencilOperation
            get() = handle.failOp.toUInt() as WGPUStencilOperation
            set(value) { handle.failOp = value.toInt() }
        override var depthFailOp: WGPUStencilOperation
            get() = handle.depthFailOp.toUInt() as WGPUStencilOperation
            set(value) { handle.depthFailOp = value.toInt() }
        override var passOp: WGPUStencilOperation
            get() = handle.passOp.toUInt() as WGPUStencilOperation
            set(value) { handle.passOp = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUStorageTextureBindingLayout {
    actual var nextInChain: NativeAddress
    actual var access: WGPUStorageTextureAccess
    actual var format: WGPUTextureFormat
    actual var viewDimension: WGPUTextureViewDimension
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUStorageTextureBindingLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStorageTextureBindingLayout) -> Unit): ArrayHolder<WGPUStorageTextureBindingLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByReference = io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUStorageTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var access: WGPUStorageTextureAccess
            get() = handle.access.toUInt() as WGPUStorageTextureAccess
            set(value) { handle.access = value.toInt() }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var viewDimension: WGPUTextureViewDimension
            get() = handle.viewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.viewDimension = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByValue = io.ygdrasil.wgpu.android.WGPUStorageTextureBindingLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUStorageTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var access: WGPUStorageTextureAccess
            get() = handle.access.toUInt() as WGPUStorageTextureAccess
            set(value) { handle.access = value.toInt() }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var viewDimension: WGPUTextureViewDimension
            get() = handle.viewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.viewDimension = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSupportedFeatures {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSupportedFeatures {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedFeatures) -> Unit): ArrayHolder<WGPUSupportedFeatures> {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByReference = io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSupportedFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByValue = io.ygdrasil.wgpu.android.WGPUSupportedFeatures.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSupportedFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSupportedInstanceFeatures {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSupportedInstanceFeatures {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedInstanceFeatures {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedInstanceFeatures) -> Unit): ArrayHolder<WGPUSupportedInstanceFeatures> {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByReference = io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSupportedInstanceFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByValue = io.ygdrasil.wgpu.android.WGPUSupportedInstanceFeatures.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSupportedInstanceFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSupportedWGSLLanguageFeatures {
    actual var featureCount: ULong
    actual var features: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSupportedWGSLLanguageFeatures {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSupportedWGSLLanguageFeatures {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedWGSLLanguageFeatures) -> Unit): ArrayHolder<WGPUSupportedWGSLLanguageFeatures> {
            val ref = io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByReference = io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSupportedWGSLLanguageFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByValue = io.ygdrasil.wgpu.android.WGPUSupportedWGSLLanguageFeatures.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSupportedWGSLLanguageFeatures {
        override var featureCount: ULong
            get() = handle.featureCount.toULong() as ULong
            set(value) { handle.featureCount = value.toLong() }
        override var features: NativeAddress
            get() = handle.features ?: com.sun.jna.Pointer.NULL
            set(value) { handle.features = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceCapabilities {
    actual var nextInChain: NativeAddress
    actual var usages: ULong
    actual var formatCount: ULong
    actual var formats: NativeAddress
    actual var presentModeCount: ULong
    actual var presentModes: NativeAddress
    actual var alphaModeCount: ULong
    actual var alphaModes: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceCapabilities {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceCapabilities) -> Unit): ArrayHolder<WGPUSurfaceCapabilities> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceCapabilities {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var usages: ULong
            get() = handle.usages.toULong() as ULong
            set(value) { handle.usages = value.toLong() }
        override var formatCount: ULong
            get() = handle.formatCount.toULong() as ULong
            set(value) { handle.formatCount = value.toLong() }
        override var formats: NativeAddress
            get() = handle.formats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.formats = value }
        override var presentModeCount: ULong
            get() = handle.presentModeCount.toULong() as ULong
            set(value) { handle.presentModeCount = value.toLong() }
        override var presentModes: NativeAddress
            get() = handle.presentModes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.presentModes = value }
        override var alphaModeCount: ULong
            get() = handle.alphaModeCount.toULong() as ULong
            set(value) { handle.alphaModeCount = value.toLong() }
        override var alphaModes: NativeAddress
            get() = handle.alphaModes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.alphaModes = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceCapabilities.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceCapabilities {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var usages: ULong
            get() = handle.usages.toULong() as ULong
            set(value) { handle.usages = value.toLong() }
        override var formatCount: ULong
            get() = handle.formatCount.toULong() as ULong
            set(value) { handle.formatCount = value.toLong() }
        override var formats: NativeAddress
            get() = handle.formats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.formats = value }
        override var presentModeCount: ULong
            get() = handle.presentModeCount.toULong() as ULong
            set(value) { handle.presentModeCount = value.toLong() }
        override var presentModes: NativeAddress
            get() = handle.presentModes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.presentModes = value }
        override var alphaModeCount: ULong
            get() = handle.alphaModeCount.toULong() as ULong
            set(value) { handle.alphaModeCount = value.toLong() }
        override var alphaModes: NativeAddress
            get() = handle.alphaModes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.alphaModes = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceColorManagement {
    actual var chain: WGPUChainedStruct
    actual var colorSpace: WGPUPredefinedColorSpace
    actual var toneMappingMode: WGPUToneMappingMode
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceColorManagement {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceColorManagement {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceColorManagement) -> Unit): ArrayHolder<WGPUSurfaceColorManagement> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceColorManagement {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var colorSpace: WGPUPredefinedColorSpace
            get() = handle.colorSpace.toUInt() as WGPUPredefinedColorSpace
            set(value) { handle.colorSpace = value.toInt() }
        override var toneMappingMode: WGPUToneMappingMode
            get() = handle.toneMappingMode.toUInt() as WGPUToneMappingMode
            set(value) { handle.toneMappingMode = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceColorManagement.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceColorManagement {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var colorSpace: WGPUPredefinedColorSpace
            get() = handle.colorSpace.toUInt() as WGPUPredefinedColorSpace
            set(value) { handle.colorSpace = value.toInt() }
        override var toneMappingMode: WGPUToneMappingMode
            get() = handle.toneMappingMode.toUInt() as WGPUToneMappingMode
            set(value) { handle.toneMappingMode = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceConfiguration {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceConfiguration {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfiguration) -> Unit): ArrayHolder<WGPUSurfaceConfiguration> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceConfiguration {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var device: WGPUDeviceImpl?
            get() = handle.device?.let { WGPUDeviceImpl(it) }
            set(value) { handle.device = value?.handler }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var width: UInt
            get() = handle.width.toUInt() as UInt
            set(value) { handle.width = value.toInt() }
        override var height: UInt
            get() = handle.height.toUInt() as UInt
            set(value) { handle.height = value.toInt() }
        override var viewFormatCount: ULong
            get() = handle.viewFormatCount.toULong() as ULong
            set(value) { handle.viewFormatCount = value.toLong() }
        override var viewFormats: NativeAddress
            get() = handle.viewFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.viewFormats = value }
        override var alphaMode: WGPUCompositeAlphaMode
            get() = handle.alphaMode.toUInt() as WGPUCompositeAlphaMode
            set(value) { handle.alphaMode = value.toInt() }
        override var presentMode: WGPUPresentMode
            get() = handle.presentMode.toUInt() as WGPUPresentMode
            set(value) { handle.presentMode = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceConfiguration.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceConfiguration {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var device: WGPUDeviceImpl?
            get() = handle.device?.let { WGPUDeviceImpl(it) }
            set(value) { handle.device = value?.handler }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var width: UInt
            get() = handle.width.toUInt() as UInt
            set(value) { handle.width = value.toInt() }
        override var height: UInt
            get() = handle.height.toUInt() as UInt
            set(value) { handle.height = value.toInt() }
        override var viewFormatCount: ULong
            get() = handle.viewFormatCount.toULong() as ULong
            set(value) { handle.viewFormatCount = value.toLong() }
        override var viewFormats: NativeAddress
            get() = handle.viewFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.viewFormats = value }
        override var alphaMode: WGPUCompositeAlphaMode
            get() = handle.alphaMode.toUInt() as WGPUCompositeAlphaMode
            set(value) { handle.alphaMode = value.toInt() }
        override var presentMode: WGPUPresentMode
            get() = handle.presentMode.toUInt() as WGPUPresentMode
            set(value) { handle.presentMode = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceAndroidNativeWindow {
    actual var chain: WGPUChainedStruct
    actual var window: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceAndroidNativeWindow {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceAndroidNativeWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceAndroidNativeWindow> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceAndroidNativeWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var window: NativeAddress
            get() = handle.window ?: com.sun.jna.Pointer.NULL
            set(value) { handle.window = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceAndroidNativeWindow.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceAndroidNativeWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var window: NativeAddress
            get() = handle.window ?: com.sun.jna.Pointer.NULL
            set(value) { handle.window = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceMetalLayer {
    actual var chain: WGPUChainedStruct
    actual var layer: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceMetalLayer {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceMetalLayer) -> Unit): ArrayHolder<WGPUSurfaceSourceMetalLayer> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceMetalLayer {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var layer: NativeAddress
            get() = handle.layer ?: com.sun.jna.Pointer.NULL
            set(value) { handle.layer = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceMetalLayer.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceMetalLayer {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var layer: NativeAddress
            get() = handle.layer ?: com.sun.jna.Pointer.NULL
            set(value) { handle.layer = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceWaylandSurface {
    actual var chain: WGPUChainedStruct
    actual var display: NativeAddress
    actual var surface: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWaylandSurface {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWaylandSurface) -> Unit): ArrayHolder<WGPUSurfaceSourceWaylandSurface> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceWaylandSurface {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var surface: NativeAddress
            get() = handle.surface ?: com.sun.jna.Pointer.NULL
            set(value) { handle.surface = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWaylandSurface.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceWaylandSurface {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var surface: NativeAddress
            get() = handle.surface ?: com.sun.jna.Pointer.NULL
            set(value) { handle.surface = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceWindowsHWND {
    actual var chain: WGPUChainedStruct
    actual var hinstance: NativeAddress
    actual var hwnd: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWindowsHWND {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWindowsHWND) -> Unit): ArrayHolder<WGPUSurfaceSourceWindowsHWND> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceWindowsHWND {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var hinstance: NativeAddress
            get() = handle.hinstance ?: com.sun.jna.Pointer.NULL
            set(value) { handle.hinstance = value }
        override var hwnd: NativeAddress
            get() = handle.hwnd ?: com.sun.jna.Pointer.NULL
            set(value) { handle.hwnd = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceWindowsHWND.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceWindowsHWND {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var hinstance: NativeAddress
            get() = handle.hinstance ?: com.sun.jna.Pointer.NULL
            set(value) { handle.hinstance = value }
        override var hwnd: NativeAddress
            get() = handle.hwnd ?: com.sun.jna.Pointer.NULL
            set(value) { handle.hwnd = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceXCBWindow {
    actual var chain: WGPUChainedStruct
    actual var connection: NativeAddress
    actual var window: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXCBWindow {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXCBWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXCBWindow> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceXCBWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var connection: NativeAddress
            get() = handle.connection ?: com.sun.jna.Pointer.NULL
            set(value) { handle.connection = value }
        override var window: UInt
            get() = handle.window.toUInt() as UInt
            set(value) { handle.window = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXCBWindow.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceXCBWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var connection: NativeAddress
            get() = handle.connection ?: com.sun.jna.Pointer.NULL
            set(value) { handle.connection = value }
        override var window: UInt
            get() = handle.window.toUInt() as UInt
            set(value) { handle.window = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceXlibWindow {
    actual var chain: WGPUChainedStruct
    actual var display: NativeAddress
    actual var window: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXlibWindow {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXlibWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXlibWindow> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceXlibWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var window: ULong
            get() = handle.window.toULong() as ULong
            set(value) { handle.window = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceXlibWindow {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var window: ULong
            get() = handle.window.toULong() as ULong
            set(value) { handle.window = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceTexture {
    actual var nextInChain: NativeAddress
    actual var texture: WGPUTextureImpl?
    actual var status: WGPUSurfaceGetCurrentTextureStatus
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceTexture {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceTexture) -> Unit): ArrayHolder<WGPUSurfaceTexture> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceTexture {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var texture: WGPUTextureImpl?
            get() = handle.texture?.let { WGPUTextureImpl(it) }
            set(value) { handle.texture = value?.handler }
        override var status: WGPUSurfaceGetCurrentTextureStatus
            get() = handle.status.toUInt() as WGPUSurfaceGetCurrentTextureStatus
            set(value) { handle.status = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceTexture.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceTexture {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var texture: WGPUTextureImpl?
            get() = handle.texture?.let { WGPUTextureImpl(it) }
            set(value) { handle.texture = value?.handler }
        override var status: WGPUSurfaceGetCurrentTextureStatus
            get() = handle.status.toUInt() as WGPUSurfaceGetCurrentTextureStatus
            set(value) { handle.status = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTexelCopyBufferLayout {
    actual var offset: ULong
    actual var bytesPerRow: UInt
    actual var rowsPerImage: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferLayout) -> Unit): ArrayHolder<WGPUTexelCopyBufferLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByReference = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyBufferLayout {
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var bytesPerRow: UInt
            get() = handle.bytesPerRow.toUInt() as UInt
            set(value) { handle.bytesPerRow = value.toInt() }
        override var rowsPerImage: UInt
            get() = handle.rowsPerImage.toUInt() as UInt
            set(value) { handle.rowsPerImage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByValue = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyBufferLayout {
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var bytesPerRow: UInt
            get() = handle.bytesPerRow.toUInt() as UInt
            set(value) { handle.bytesPerRow = value.toInt() }
        override var rowsPerImage: UInt
            get() = handle.rowsPerImage.toUInt() as UInt
            set(value) { handle.rowsPerImage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureBindingLayout {
    actual var nextInChain: NativeAddress
    actual var sampleType: WGPUTextureSampleType
    actual var viewDimension: WGPUTextureViewDimension
    actual var multisampled: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureBindingLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingLayout) -> Unit): ArrayHolder<WGPUTextureBindingLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByReference = io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var sampleType: WGPUTextureSampleType
            get() = handle.sampleType.toUInt() as WGPUTextureSampleType
            set(value) { handle.sampleType = value.toInt() }
        override var viewDimension: WGPUTextureViewDimension
            get() = handle.viewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.viewDimension = value.toInt() }
        override var multisampled: UInt
            get() = handle.multisampled.toUInt() as UInt
            set(value) { handle.multisampled = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByValue = io.ygdrasil.wgpu.android.WGPUTextureBindingLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureBindingLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var sampleType: WGPUTextureSampleType
            get() = handle.sampleType.toUInt() as WGPUTextureSampleType
            set(value) { handle.sampleType = value.toInt() }
        override var viewDimension: WGPUTextureViewDimension
            get() = handle.viewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.viewDimension = value.toInt() }
        override var multisampled: UInt
            get() = handle.multisampled.toUInt() as UInt
            set(value) { handle.multisampled = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureBindingViewDimension {
    actual var chain: WGPUChainedStruct
    actual var textureBindingViewDimension: WGPUTextureViewDimension
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureBindingViewDimension {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureBindingViewDimension {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingViewDimension) -> Unit): ArrayHolder<WGPUTextureBindingViewDimension> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByReference = io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureBindingViewDimension {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var textureBindingViewDimension: WGPUTextureViewDimension
            get() = handle.textureBindingViewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.textureBindingViewDimension = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByValue = io.ygdrasil.wgpu.android.WGPUTextureBindingViewDimension.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureBindingViewDimension {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var textureBindingViewDimension: WGPUTextureViewDimension
            get() = handle.textureBindingViewDimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.textureBindingViewDimension = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureComponentSwizzle {
    actual var r: WGPUComponentSwizzle
    actual var g: WGPUComponentSwizzle
    actual var b: WGPUComponentSwizzle
    actual var a: WGPUComponentSwizzle
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzle {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzle {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzle) -> Unit): ArrayHolder<WGPUTextureComponentSwizzle> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByReference = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureComponentSwizzle {
        override var r: WGPUComponentSwizzle
            get() = handle.r.toUInt() as WGPUComponentSwizzle
            set(value) { handle.r = value.toInt() }
        override var g: WGPUComponentSwizzle
            get() = handle.g.toUInt() as WGPUComponentSwizzle
            set(value) { handle.g = value.toInt() }
        override var b: WGPUComponentSwizzle
            get() = handle.b.toUInt() as WGPUComponentSwizzle
            set(value) { handle.b = value.toInt() }
        override var a: WGPUComponentSwizzle
            get() = handle.a.toUInt() as WGPUComponentSwizzle
            set(value) { handle.a = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByValue = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzle.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureComponentSwizzle {
        override var r: WGPUComponentSwizzle
            get() = handle.r.toUInt() as WGPUComponentSwizzle
            set(value) { handle.r = value.toInt() }
        override var g: WGPUComponentSwizzle
            get() = handle.g.toUInt() as WGPUComponentSwizzle
            set(value) { handle.g = value.toInt() }
        override var b: WGPUComponentSwizzle
            get() = handle.b.toUInt() as WGPUComponentSwizzle
            set(value) { handle.b = value.toInt() }
        override var a: WGPUComponentSwizzle
            get() = handle.a.toUInt() as WGPUComponentSwizzle
            set(value) { handle.a = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUVertexAttribute {
    actual var nextInChain: NativeAddress
    actual var format: WGPUVertexFormat
    actual var offset: ULong
    actual var shaderLocation: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUVertexAttribute {
            return ByReference(io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexAttribute) -> Unit): ArrayHolder<WGPUVertexAttribute> {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByReference = io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByReference(com.sun.jna.Pointer.NULL)) : WGPUVertexAttribute {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUVertexFormat
            get() = handle.format.toUInt() as WGPUVertexFormat
            set(value) { handle.format = value.toInt() }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var shaderLocation: UInt
            get() = handle.shaderLocation.toUInt() as UInt
            set(value) { handle.shaderLocation = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByValue = io.ygdrasil.wgpu.android.WGPUVertexAttribute.ByValue(com.sun.jna.Pointer.NULL)) : WGPUVertexAttribute {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUVertexFormat
            get() = handle.format.toUInt() as WGPUVertexFormat
            set(value) { handle.format = value.toInt() }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var shaderLocation: UInt
            get() = handle.shaderLocation.toUInt() as UInt
            set(value) { handle.shaderLocation = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupEntry {
    actual var nextInChain: NativeAddress
    actual var binding: UInt
    actual var buffer: WGPUBufferImpl?
    actual var offset: ULong
    actual var size: ULong
    actual var sampler: WGPUSamplerImpl?
    actual var textureView: WGPUTextureViewImpl?
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupEntry {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntry) -> Unit): ArrayHolder<WGPUBindGroupEntry> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var binding: UInt
            get() = handle.binding.toUInt() as UInt
            set(value) { handle.binding = value.toInt() }
        override var buffer: WGPUBufferImpl?
            get() = handle.buffer?.let { WGPUBufferImpl(it) }
            set(value) { handle.buffer = value?.handler }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var size: ULong
            get() = handle.size.toULong() as ULong
            set(value) { handle.size = value.toLong() }
        override var sampler: WGPUSamplerImpl?
            get() = handle.sampler?.let { WGPUSamplerImpl(it) }
            set(value) { handle.sampler = value?.handler }
        override var textureView: WGPUTextureViewImpl?
            get() = handle.textureView?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.textureView = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupEntry.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var binding: UInt
            get() = handle.binding.toUInt() as UInt
            set(value) { handle.binding = value.toInt() }
        override var buffer: WGPUBufferImpl?
            get() = handle.buffer?.let { WGPUBufferImpl(it) }
            set(value) { handle.buffer = value?.handler }
        override var offset: ULong
            get() = handle.offset.toULong() as ULong
            set(value) { handle.offset = value.toLong() }
        override var size: ULong
            get() = handle.size.toULong() as ULong
            set(value) { handle.size = value.toLong() }
        override var sampler: WGPUSamplerImpl?
            get() = handle.sampler?.let { WGPUSamplerImpl(it) }
            set(value) { handle.sampler = value?.handler }
        override var textureView: WGPUTextureViewImpl?
            get() = handle.textureView?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.textureView = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupLayoutEntry {
    actual var nextInChain: NativeAddress
    actual var binding: UInt
    actual var visibility: ULong
    actual var bindingArraySize: UInt
    actual var buffer: WGPUBufferBindingLayout
    actual var sampler: WGPUSamplerBindingLayout
    actual var texture: WGPUTextureBindingLayout
    actual var storageTexture: WGPUStorageTextureBindingLayout
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntry {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntry) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntry> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var binding: UInt
            get() = handle.binding.toUInt() as UInt
            set(value) { handle.binding = value.toInt() }
        override var visibility: ULong
            get() = handle.visibility.toULong() as ULong
            set(value) { handle.visibility = value.toLong() }
        override var bindingArraySize: UInt
            get() = handle.bindingArraySize.toUInt() as UInt
            set(value) { handle.bindingArraySize = value.toInt() }
        override var buffer: WGPUBufferBindingLayout
            get() = handle.buffer?.let { WGPUBufferBindingLayout(it) } ?: error("buffer is null")
            set(value) { handle.buffer = value.handler }
        override var sampler: WGPUSamplerBindingLayout
            get() = handle.sampler?.let { WGPUSamplerBindingLayout(it) } ?: error("sampler is null")
            set(value) { handle.sampler = value.handler }
        override var texture: WGPUTextureBindingLayout
            get() = handle.texture?.let { WGPUTextureBindingLayout(it) } ?: error("texture is null")
            set(value) { handle.texture = value.handler }
        override var storageTexture: WGPUStorageTextureBindingLayout
            get() = handle.storageTexture?.let { WGPUStorageTextureBindingLayout(it) } ?: error("storageTexture is null")
            set(value) { handle.storageTexture = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntry.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutEntry {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var binding: UInt
            get() = handle.binding.toUInt() as UInt
            set(value) { handle.binding = value.toInt() }
        override var visibility: ULong
            get() = handle.visibility.toULong() as ULong
            set(value) { handle.visibility = value.toLong() }
        override var bindingArraySize: UInt
            get() = handle.bindingArraySize.toUInt() as UInt
            set(value) { handle.bindingArraySize = value.toInt() }
        override var buffer: WGPUBufferBindingLayout
            get() = handle.buffer?.let { WGPUBufferBindingLayout(it) } ?: error("buffer is null")
            set(value) { handle.buffer = value.handler }
        override var sampler: WGPUSamplerBindingLayout
            get() = handle.sampler?.let { WGPUSamplerBindingLayout(it) } ?: error("sampler is null")
            set(value) { handle.sampler = value.handler }
        override var texture: WGPUTextureBindingLayout
            get() = handle.texture?.let { WGPUTextureBindingLayout(it) } ?: error("texture is null")
            set(value) { handle.texture = value.handler }
        override var storageTexture: WGPUStorageTextureBindingLayout
            get() = handle.storageTexture?.let { WGPUStorageTextureBindingLayout(it) } ?: error("storageTexture is null")
            set(value) { handle.storageTexture = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBlendState {
    actual var color: WGPUBlendComponent
    actual var alpha: WGPUBlendComponent
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBlendState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBlendState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBlendState {
            val ref = io.ygdrasil.wgpu.android.WGPUBlendState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendState) -> Unit): ArrayHolder<WGPUBlendState> {
            val ref = io.ygdrasil.wgpu.android.WGPUBlendState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBlendState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBlendState.ByReference = io.ygdrasil.wgpu.android.WGPUBlendState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBlendState {
        override var color: WGPUBlendComponent
            get() = handle.color?.let { WGPUBlendComponent(it) } ?: error("color is null")
            set(value) { handle.color = value.handler }
        override var alpha: WGPUBlendComponent
            get() = handle.alpha?.let { WGPUBlendComponent(it) } ?: error("alpha is null")
            set(value) { handle.alpha = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBlendState.ByValue = io.ygdrasil.wgpu.android.WGPUBlendState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBlendState {
        override var color: WGPUBlendComponent
            get() = handle.color?.let { WGPUBlendComponent(it) } ?: error("color is null")
            set(value) { handle.color = value.handler }
        override var alpha: WGPUBlendComponent
            get() = handle.alpha?.let { WGPUBlendComponent(it) } ?: error("alpha is null")
            set(value) { handle.alpha = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUCompilationInfo {
    actual var nextInChain: NativeAddress
    actual var messageCount: ULong
    actual var messages: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUCompilationInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfo) -> Unit): ArrayHolder<WGPUCompilationInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByReference = io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUCompilationInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var messageCount: ULong
            get() = handle.messageCount.toULong() as ULong
            set(value) { handle.messageCount = value.toLong() }
        override var messages: NativeAddress
            get() = handle.messages ?: com.sun.jna.Pointer.NULL
            set(value) { handle.messages = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByValue = io.ygdrasil.wgpu.android.WGPUCompilationInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUCompilationInfo {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var messageCount: ULong
            get() = handle.messageCount.toULong() as ULong
            set(value) { handle.messageCount = value.toLong() }
        override var messages: NativeAddress
            get() = handle.messages ?: com.sun.jna.Pointer.NULL
            set(value) { handle.messages = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUComputePassDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var timestampWrites: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUComputePassDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePassDescriptor) -> Unit): ArrayHolder<WGPUComputePassDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUComputePassDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var timestampWrites: NativeAddress
            get() = handle.timestampWrites ?: com.sun.jna.Pointer.NULL
            set(value) { handle.timestampWrites = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUComputePassDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUComputePassDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var timestampWrites: NativeAddress
            get() = handle.timestampWrites ?: com.sun.jna.Pointer.NULL
            set(value) { handle.timestampWrites = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUComputeState {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUComputeState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUComputeState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUComputeState {
            val ref = io.ygdrasil.wgpu.android.WGPUComputeState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputeState) -> Unit): ArrayHolder<WGPUComputeState> {
            val ref = io.ygdrasil.wgpu.android.WGPUComputeState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUComputeState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUComputeState.ByReference = io.ygdrasil.wgpu.android.WGPUComputeState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUComputeState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUComputeState.ByValue = io.ygdrasil.wgpu.android.WGPUComputeState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUComputeState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUDepthStencilState {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUDepthStencilState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState {
            val ref = io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDepthStencilState) -> Unit): ArrayHolder<WGPUDepthStencilState> {
            val ref = io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByReference = io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUDepthStencilState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var depthWriteEnabled: WGPUOptionalBool
            get() = handle.depthWriteEnabled.toUInt() as WGPUOptionalBool
            set(value) { handle.depthWriteEnabled = value.toInt() }
        override var depthCompare: WGPUCompareFunction
            get() = handle.depthCompare.toUInt() as WGPUCompareFunction
            set(value) { handle.depthCompare = value.toInt() }
        override var stencilFront: WGPUStencilFaceState
            get() = handle.stencilFront?.let { WGPUStencilFaceState(it) } ?: error("stencilFront is null")
            set(value) { handle.stencilFront = value.handler }
        override var stencilBack: WGPUStencilFaceState
            get() = handle.stencilBack?.let { WGPUStencilFaceState(it) } ?: error("stencilBack is null")
            set(value) { handle.stencilBack = value.handler }
        override var stencilReadMask: UInt
            get() = handle.stencilReadMask.toUInt() as UInt
            set(value) { handle.stencilReadMask = value.toInt() }
        override var stencilWriteMask: UInt
            get() = handle.stencilWriteMask.toUInt() as UInt
            set(value) { handle.stencilWriteMask = value.toInt() }
        override var depthBias: Int
            get() = handle.depthBias as Int
            set(value) { handle.depthBias = value }
        override var depthBiasSlopeScale: Float
            get() = handle.depthBiasSlopeScale as Float
            set(value) { handle.depthBiasSlopeScale = value }
        override var depthBiasClamp: Float
            get() = handle.depthBiasClamp as Float
            set(value) { handle.depthBiasClamp = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByValue = io.ygdrasil.wgpu.android.WGPUDepthStencilState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUDepthStencilState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var depthWriteEnabled: WGPUOptionalBool
            get() = handle.depthWriteEnabled.toUInt() as WGPUOptionalBool
            set(value) { handle.depthWriteEnabled = value.toInt() }
        override var depthCompare: WGPUCompareFunction
            get() = handle.depthCompare.toUInt() as WGPUCompareFunction
            set(value) { handle.depthCompare = value.toInt() }
        override var stencilFront: WGPUStencilFaceState
            get() = handle.stencilFront?.let { WGPUStencilFaceState(it) } ?: error("stencilFront is null")
            set(value) { handle.stencilFront = value.handler }
        override var stencilBack: WGPUStencilFaceState
            get() = handle.stencilBack?.let { WGPUStencilFaceState(it) } ?: error("stencilBack is null")
            set(value) { handle.stencilBack = value.handler }
        override var stencilReadMask: UInt
            get() = handle.stencilReadMask.toUInt() as UInt
            set(value) { handle.stencilReadMask = value.toInt() }
        override var stencilWriteMask: UInt
            get() = handle.stencilWriteMask.toUInt() as UInt
            set(value) { handle.stencilWriteMask = value.toInt() }
        override var depthBias: Int
            get() = handle.depthBias as Int
            set(value) { handle.depthBias = value }
        override var depthBiasSlopeScale: Float
            get() = handle.depthBiasSlopeScale as Float
            set(value) { handle.depthBiasSlopeScale = value }
        override var depthBiasClamp: Float
            get() = handle.depthBiasClamp as Float
            set(value) { handle.depthBiasClamp = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUFutureWaitInfo {
    actual var future: WGPUFuture
    actual var completed: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUFutureWaitInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFutureWaitInfo) -> Unit): ArrayHolder<WGPUFutureWaitInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByReference = io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUFutureWaitInfo {
        override var future: WGPUFuture
            get() = handle.future?.let { WGPUFuture(it) } ?: error("future is null")
            set(value) { handle.future = value.handler }
        override var completed: UInt
            get() = handle.completed.toUInt() as UInt
            set(value) { handle.completed = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByValue = io.ygdrasil.wgpu.android.WGPUFutureWaitInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUFutureWaitInfo {
        override var future: WGPUFuture
            get() = handle.future?.let { WGPUFuture(it) } ?: error("future is null")
            set(value) { handle.future = value.handler }
        override var completed: UInt
            get() = handle.completed.toUInt() as UInt
            set(value) { handle.completed = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUInstanceDescriptor {
    actual var nextInChain: NativeAddress
    actual var requiredFeatureCount: ULong
    actual var requiredFeatures: NativeAddress
    actual var requiredLimits: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUInstanceDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceDescriptor) -> Unit): ArrayHolder<WGPUInstanceDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUInstanceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var requiredFeatureCount: ULong
            get() = handle.requiredFeatureCount.toULong() as ULong
            set(value) { handle.requiredFeatureCount = value.toLong() }
        override var requiredFeatures: NativeAddress
            get() = handle.requiredFeatures ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredFeatures = value }
        override var requiredLimits: NativeAddress
            get() = handle.requiredLimits ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredLimits = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUInstanceDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUInstanceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var requiredFeatureCount: ULong
            get() = handle.requiredFeatureCount.toULong() as ULong
            set(value) { handle.requiredFeatureCount = value.toLong() }
        override var requiredFeatures: NativeAddress
            get() = handle.requiredFeatures ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredFeatures = value }
        override var requiredLimits: NativeAddress
            get() = handle.requiredLimits ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredLimits = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPULimits {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPULimits {
            return ByReference(io.ygdrasil.wgpu.android.WGPULimits.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPULimits {
            val ref = io.ygdrasil.wgpu.android.WGPULimits.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPULimits) -> Unit): ArrayHolder<WGPULimits> {
            val ref = io.ygdrasil.wgpu.android.WGPULimits.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPULimits.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPULimits.ByReference = io.ygdrasil.wgpu.android.WGPULimits.ByReference(com.sun.jna.Pointer.NULL)) : WGPULimits {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var maxTextureDimension1D: UInt
            get() = handle.maxTextureDimension1D.toUInt() as UInt
            set(value) { handle.maxTextureDimension1D = value.toInt() }
        override var maxTextureDimension2D: UInt
            get() = handle.maxTextureDimension2D.toUInt() as UInt
            set(value) { handle.maxTextureDimension2D = value.toInt() }
        override var maxTextureDimension3D: UInt
            get() = handle.maxTextureDimension3D.toUInt() as UInt
            set(value) { handle.maxTextureDimension3D = value.toInt() }
        override var maxTextureArrayLayers: UInt
            get() = handle.maxTextureArrayLayers.toUInt() as UInt
            set(value) { handle.maxTextureArrayLayers = value.toInt() }
        override var maxBindGroups: UInt
            get() = handle.maxBindGroups.toUInt() as UInt
            set(value) { handle.maxBindGroups = value.toInt() }
        override var maxBindGroupsPlusVertexBuffers: UInt
            get() = handle.maxBindGroupsPlusVertexBuffers.toUInt() as UInt
            set(value) { handle.maxBindGroupsPlusVertexBuffers = value.toInt() }
        override var maxBindingsPerBindGroup: UInt
            get() = handle.maxBindingsPerBindGroup.toUInt() as UInt
            set(value) { handle.maxBindingsPerBindGroup = value.toInt() }
        override var maxDynamicUniformBuffersPerPipelineLayout: UInt
            get() = handle.maxDynamicUniformBuffersPerPipelineLayout.toUInt() as UInt
            set(value) { handle.maxDynamicUniformBuffersPerPipelineLayout = value.toInt() }
        override var maxDynamicStorageBuffersPerPipelineLayout: UInt
            get() = handle.maxDynamicStorageBuffersPerPipelineLayout.toUInt() as UInt
            set(value) { handle.maxDynamicStorageBuffersPerPipelineLayout = value.toInt() }
        override var maxSampledTexturesPerShaderStage: UInt
            get() = handle.maxSampledTexturesPerShaderStage.toUInt() as UInt
            set(value) { handle.maxSampledTexturesPerShaderStage = value.toInt() }
        override var maxSamplersPerShaderStage: UInt
            get() = handle.maxSamplersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxSamplersPerShaderStage = value.toInt() }
        override var maxStorageBuffersPerShaderStage: UInt
            get() = handle.maxStorageBuffersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersPerShaderStage = value.toInt() }
        override var maxStorageTexturesPerShaderStage: UInt
            get() = handle.maxStorageTexturesPerShaderStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesPerShaderStage = value.toInt() }
        override var maxUniformBuffersPerShaderStage: UInt
            get() = handle.maxUniformBuffersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxUniformBuffersPerShaderStage = value.toInt() }
        override var maxUniformBufferBindingSize: ULong
            get() = handle.maxUniformBufferBindingSize.toULong() as ULong
            set(value) { handle.maxUniformBufferBindingSize = value.toLong() }
        override var maxStorageBufferBindingSize: ULong
            get() = handle.maxStorageBufferBindingSize.toULong() as ULong
            set(value) { handle.maxStorageBufferBindingSize = value.toLong() }
        override var minUniformBufferOffsetAlignment: UInt
            get() = handle.minUniformBufferOffsetAlignment.toUInt() as UInt
            set(value) { handle.minUniformBufferOffsetAlignment = value.toInt() }
        override var minStorageBufferOffsetAlignment: UInt
            get() = handle.minStorageBufferOffsetAlignment.toUInt() as UInt
            set(value) { handle.minStorageBufferOffsetAlignment = value.toInt() }
        override var maxVertexBuffers: UInt
            get() = handle.maxVertexBuffers.toUInt() as UInt
            set(value) { handle.maxVertexBuffers = value.toInt() }
        override var maxBufferSize: ULong
            get() = handle.maxBufferSize.toULong() as ULong
            set(value) { handle.maxBufferSize = value.toLong() }
        override var maxVertexAttributes: UInt
            get() = handle.maxVertexAttributes.toUInt() as UInt
            set(value) { handle.maxVertexAttributes = value.toInt() }
        override var maxVertexBufferArrayStride: UInt
            get() = handle.maxVertexBufferArrayStride.toUInt() as UInt
            set(value) { handle.maxVertexBufferArrayStride = value.toInt() }
        override var maxInterStageShaderVariables: UInt
            get() = handle.maxInterStageShaderVariables.toUInt() as UInt
            set(value) { handle.maxInterStageShaderVariables = value.toInt() }
        override var maxColorAttachments: UInt
            get() = handle.maxColorAttachments.toUInt() as UInt
            set(value) { handle.maxColorAttachments = value.toInt() }
        override var maxColorAttachmentBytesPerSample: UInt
            get() = handle.maxColorAttachmentBytesPerSample.toUInt() as UInt
            set(value) { handle.maxColorAttachmentBytesPerSample = value.toInt() }
        override var maxComputeWorkgroupStorageSize: UInt
            get() = handle.maxComputeWorkgroupStorageSize.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupStorageSize = value.toInt() }
        override var maxComputeInvocationsPerWorkgroup: UInt
            get() = handle.maxComputeInvocationsPerWorkgroup.toUInt() as UInt
            set(value) { handle.maxComputeInvocationsPerWorkgroup = value.toInt() }
        override var maxComputeWorkgroupSizeX: UInt
            get() = handle.maxComputeWorkgroupSizeX.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeX = value.toInt() }
        override var maxComputeWorkgroupSizeY: UInt
            get() = handle.maxComputeWorkgroupSizeY.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeY = value.toInt() }
        override var maxComputeWorkgroupSizeZ: UInt
            get() = handle.maxComputeWorkgroupSizeZ.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeZ = value.toInt() }
        override var maxComputeWorkgroupsPerDimension: UInt
            get() = handle.maxComputeWorkgroupsPerDimension.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupsPerDimension = value.toInt() }
        override var maxImmediateSize: UInt
            get() = handle.maxImmediateSize.toUInt() as UInt
            set(value) { handle.maxImmediateSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPULimits.ByValue = io.ygdrasil.wgpu.android.WGPULimits.ByValue(com.sun.jna.Pointer.NULL)) : WGPULimits {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var maxTextureDimension1D: UInt
            get() = handle.maxTextureDimension1D.toUInt() as UInt
            set(value) { handle.maxTextureDimension1D = value.toInt() }
        override var maxTextureDimension2D: UInt
            get() = handle.maxTextureDimension2D.toUInt() as UInt
            set(value) { handle.maxTextureDimension2D = value.toInt() }
        override var maxTextureDimension3D: UInt
            get() = handle.maxTextureDimension3D.toUInt() as UInt
            set(value) { handle.maxTextureDimension3D = value.toInt() }
        override var maxTextureArrayLayers: UInt
            get() = handle.maxTextureArrayLayers.toUInt() as UInt
            set(value) { handle.maxTextureArrayLayers = value.toInt() }
        override var maxBindGroups: UInt
            get() = handle.maxBindGroups.toUInt() as UInt
            set(value) { handle.maxBindGroups = value.toInt() }
        override var maxBindGroupsPlusVertexBuffers: UInt
            get() = handle.maxBindGroupsPlusVertexBuffers.toUInt() as UInt
            set(value) { handle.maxBindGroupsPlusVertexBuffers = value.toInt() }
        override var maxBindingsPerBindGroup: UInt
            get() = handle.maxBindingsPerBindGroup.toUInt() as UInt
            set(value) { handle.maxBindingsPerBindGroup = value.toInt() }
        override var maxDynamicUniformBuffersPerPipelineLayout: UInt
            get() = handle.maxDynamicUniformBuffersPerPipelineLayout.toUInt() as UInt
            set(value) { handle.maxDynamicUniformBuffersPerPipelineLayout = value.toInt() }
        override var maxDynamicStorageBuffersPerPipelineLayout: UInt
            get() = handle.maxDynamicStorageBuffersPerPipelineLayout.toUInt() as UInt
            set(value) { handle.maxDynamicStorageBuffersPerPipelineLayout = value.toInt() }
        override var maxSampledTexturesPerShaderStage: UInt
            get() = handle.maxSampledTexturesPerShaderStage.toUInt() as UInt
            set(value) { handle.maxSampledTexturesPerShaderStage = value.toInt() }
        override var maxSamplersPerShaderStage: UInt
            get() = handle.maxSamplersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxSamplersPerShaderStage = value.toInt() }
        override var maxStorageBuffersPerShaderStage: UInt
            get() = handle.maxStorageBuffersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxStorageBuffersPerShaderStage = value.toInt() }
        override var maxStorageTexturesPerShaderStage: UInt
            get() = handle.maxStorageTexturesPerShaderStage.toUInt() as UInt
            set(value) { handle.maxStorageTexturesPerShaderStage = value.toInt() }
        override var maxUniformBuffersPerShaderStage: UInt
            get() = handle.maxUniformBuffersPerShaderStage.toUInt() as UInt
            set(value) { handle.maxUniformBuffersPerShaderStage = value.toInt() }
        override var maxUniformBufferBindingSize: ULong
            get() = handle.maxUniformBufferBindingSize.toULong() as ULong
            set(value) { handle.maxUniformBufferBindingSize = value.toLong() }
        override var maxStorageBufferBindingSize: ULong
            get() = handle.maxStorageBufferBindingSize.toULong() as ULong
            set(value) { handle.maxStorageBufferBindingSize = value.toLong() }
        override var minUniformBufferOffsetAlignment: UInt
            get() = handle.minUniformBufferOffsetAlignment.toUInt() as UInt
            set(value) { handle.minUniformBufferOffsetAlignment = value.toInt() }
        override var minStorageBufferOffsetAlignment: UInt
            get() = handle.minStorageBufferOffsetAlignment.toUInt() as UInt
            set(value) { handle.minStorageBufferOffsetAlignment = value.toInt() }
        override var maxVertexBuffers: UInt
            get() = handle.maxVertexBuffers.toUInt() as UInt
            set(value) { handle.maxVertexBuffers = value.toInt() }
        override var maxBufferSize: ULong
            get() = handle.maxBufferSize.toULong() as ULong
            set(value) { handle.maxBufferSize = value.toLong() }
        override var maxVertexAttributes: UInt
            get() = handle.maxVertexAttributes.toUInt() as UInt
            set(value) { handle.maxVertexAttributes = value.toInt() }
        override var maxVertexBufferArrayStride: UInt
            get() = handle.maxVertexBufferArrayStride.toUInt() as UInt
            set(value) { handle.maxVertexBufferArrayStride = value.toInt() }
        override var maxInterStageShaderVariables: UInt
            get() = handle.maxInterStageShaderVariables.toUInt() as UInt
            set(value) { handle.maxInterStageShaderVariables = value.toInt() }
        override var maxColorAttachments: UInt
            get() = handle.maxColorAttachments.toUInt() as UInt
            set(value) { handle.maxColorAttachments = value.toInt() }
        override var maxColorAttachmentBytesPerSample: UInt
            get() = handle.maxColorAttachmentBytesPerSample.toUInt() as UInt
            set(value) { handle.maxColorAttachmentBytesPerSample = value.toInt() }
        override var maxComputeWorkgroupStorageSize: UInt
            get() = handle.maxComputeWorkgroupStorageSize.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupStorageSize = value.toInt() }
        override var maxComputeInvocationsPerWorkgroup: UInt
            get() = handle.maxComputeInvocationsPerWorkgroup.toUInt() as UInt
            set(value) { handle.maxComputeInvocationsPerWorkgroup = value.toInt() }
        override var maxComputeWorkgroupSizeX: UInt
            get() = handle.maxComputeWorkgroupSizeX.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeX = value.toInt() }
        override var maxComputeWorkgroupSizeY: UInt
            get() = handle.maxComputeWorkgroupSizeY.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeY = value.toInt() }
        override var maxComputeWorkgroupSizeZ: UInt
            get() = handle.maxComputeWorkgroupSizeZ.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupSizeZ = value.toInt() }
        override var maxComputeWorkgroupsPerDimension: UInt
            get() = handle.maxComputeWorkgroupsPerDimension.toUInt() as UInt
            set(value) { handle.maxComputeWorkgroupsPerDimension = value.toInt() }
        override var maxImmediateSize: UInt
            get() = handle.maxImmediateSize.toUInt() as UInt
            set(value) { handle.maxImmediateSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPassColorAttachment {
    actual var nextInChain: NativeAddress
    actual var view: WGPUTextureViewImpl?
    actual var depthSlice: UInt
    actual var resolveTarget: WGPUTextureViewImpl?
    actual var loadOp: WGPULoadOp
    actual var storeOp: WGPUStoreOp
    actual var clearValue: WGPUColor
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPassColorAttachment {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassColorAttachment) -> Unit): ArrayHolder<WGPURenderPassColorAttachment> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByReference = io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPassColorAttachment {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var view: WGPUTextureViewImpl?
            get() = handle.view?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.view = value?.handler }
        override var depthSlice: UInt
            get() = handle.depthSlice.toUInt() as UInt
            set(value) { handle.depthSlice = value.toInt() }
        override var resolveTarget: WGPUTextureViewImpl?
            get() = handle.resolveTarget?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.resolveTarget = value?.handler }
        override var loadOp: WGPULoadOp
            get() = handle.loadOp.toUInt() as WGPULoadOp
            set(value) { handle.loadOp = value.toInt() }
        override var storeOp: WGPUStoreOp
            get() = handle.storeOp.toUInt() as WGPUStoreOp
            set(value) { handle.storeOp = value.toInt() }
        override var clearValue: WGPUColor
            get() = handle.clearValue?.let { WGPUColor(it) } ?: error("clearValue is null")
            set(value) { handle.clearValue = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByValue = io.ygdrasil.wgpu.android.WGPURenderPassColorAttachment.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPassColorAttachment {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var view: WGPUTextureViewImpl?
            get() = handle.view?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.view = value?.handler }
        override var depthSlice: UInt
            get() = handle.depthSlice.toUInt() as UInt
            set(value) { handle.depthSlice = value.toInt() }
        override var resolveTarget: WGPUTextureViewImpl?
            get() = handle.resolveTarget?.let { WGPUTextureViewImpl(it) }
            set(value) { handle.resolveTarget = value?.handler }
        override var loadOp: WGPULoadOp
            get() = handle.loadOp.toUInt() as WGPULoadOp
            set(value) { handle.loadOp = value.toInt() }
        override var storeOp: WGPUStoreOp
            get() = handle.storeOp.toUInt() as WGPUStoreOp
            set(value) { handle.storeOp = value.toInt() }
        override var clearValue: WGPUColor
            get() = handle.clearValue?.let { WGPUColor(it) } ?: error("clearValue is null")
            set(value) { handle.clearValue = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURequestAdapterOptions {
    actual var nextInChain: NativeAddress
    actual var featureLevel: WGPUFeatureLevel
    actual var powerPreference: WGPUPowerPreference
    actual var forceFallbackAdapter: UInt
    actual var backendType: WGPUBackendType
    actual var compatibleSurface: WGPUSurfaceImpl?
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURequestAdapterOptions {
            return ByReference(io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterOptions) -> Unit): ArrayHolder<WGPURequestAdapterOptions> {
            val ref = io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByReference = io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByReference(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterOptions {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var featureLevel: WGPUFeatureLevel
            get() = handle.featureLevel.toUInt() as WGPUFeatureLevel
            set(value) { handle.featureLevel = value.toInt() }
        override var powerPreference: WGPUPowerPreference
            get() = handle.powerPreference.toUInt() as WGPUPowerPreference
            set(value) { handle.powerPreference = value.toInt() }
        override var forceFallbackAdapter: UInt
            get() = handle.forceFallbackAdapter.toUInt() as UInt
            set(value) { handle.forceFallbackAdapter = value.toInt() }
        override var backendType: WGPUBackendType
            get() = handle.backendType.toUInt() as WGPUBackendType
            set(value) { handle.backendType = value.toInt() }
        override var compatibleSurface: WGPUSurfaceImpl?
            get() = handle.compatibleSurface?.let { WGPUSurfaceImpl(it) }
            set(value) { handle.compatibleSurface = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByValue = io.ygdrasil.wgpu.android.WGPURequestAdapterOptions.ByValue(com.sun.jna.Pointer.NULL)) : WGPURequestAdapterOptions {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var featureLevel: WGPUFeatureLevel
            get() = handle.featureLevel.toUInt() as WGPUFeatureLevel
            set(value) { handle.featureLevel = value.toInt() }
        override var powerPreference: WGPUPowerPreference
            get() = handle.powerPreference.toUInt() as WGPUPowerPreference
            set(value) { handle.powerPreference = value.toInt() }
        override var forceFallbackAdapter: UInt
            get() = handle.forceFallbackAdapter.toUInt() as UInt
            set(value) { handle.forceFallbackAdapter = value.toInt() }
        override var backendType: WGPUBackendType
            get() = handle.backendType.toUInt() as WGPUBackendType
            set(value) { handle.backendType = value.toInt() }
        override var compatibleSurface: WGPUSurfaceImpl?
            get() = handle.compatibleSurface?.let { WGPUSurfaceImpl(it) }
            set(value) { handle.compatibleSurface = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderModuleDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptor) -> Unit): ArrayHolder<WGPUShaderModuleDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceDescriptor) -> Unit): ArrayHolder<WGPUSurfaceDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTexelCopyBufferInfo {
    actual var layout: WGPUTexelCopyBufferLayout
    actual var buffer: WGPUBufferImpl?
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferInfo) -> Unit): ArrayHolder<WGPUTexelCopyBufferInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByReference = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyBufferInfo {
        override var layout: WGPUTexelCopyBufferLayout
            get() = handle.layout?.let { WGPUTexelCopyBufferLayout(it) } ?: error("layout is null")
            set(value) { handle.layout = value.handler }
        override var buffer: WGPUBufferImpl?
            get() = handle.buffer?.let { WGPUBufferImpl(it) }
            set(value) { handle.buffer = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByValue = io.ygdrasil.wgpu.android.WGPUTexelCopyBufferInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyBufferInfo {
        override var layout: WGPUTexelCopyBufferLayout
            get() = handle.layout?.let { WGPUTexelCopyBufferLayout(it) } ?: error("layout is null")
            set(value) { handle.layout = value.handler }
        override var buffer: WGPUBufferImpl?
            get() = handle.buffer?.let { WGPUBufferImpl(it) }
            set(value) { handle.buffer = value?.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTexelCopyTextureInfo {
    actual var texture: WGPUTextureImpl?
    actual var mipLevel: UInt
    actual var origin: WGPUOrigin3D
    actual var aspect: WGPUTextureAspect
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTexelCopyTextureInfo {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTexelCopyTextureInfo {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyTextureInfo) -> Unit): ArrayHolder<WGPUTexelCopyTextureInfo> {
            val ref = io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByReference = io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyTextureInfo {
        override var texture: WGPUTextureImpl?
            get() = handle.texture?.let { WGPUTextureImpl(it) }
            set(value) { handle.texture = value?.handler }
        override var mipLevel: UInt
            get() = handle.mipLevel.toUInt() as UInt
            set(value) { handle.mipLevel = value.toInt() }
        override var origin: WGPUOrigin3D
            get() = handle.origin?.let { WGPUOrigin3D(it) } ?: error("origin is null")
            set(value) { handle.origin = value.handler }
        override var aspect: WGPUTextureAspect
            get() = handle.aspect.toUInt() as WGPUTextureAspect
            set(value) { handle.aspect = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByValue = io.ygdrasil.wgpu.android.WGPUTexelCopyTextureInfo.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTexelCopyTextureInfo {
        override var texture: WGPUTextureImpl?
            get() = handle.texture?.let { WGPUTextureImpl(it) }
            set(value) { handle.texture = value?.handler }
        override var mipLevel: UInt
            get() = handle.mipLevel.toUInt() as UInt
            set(value) { handle.mipLevel = value.toInt() }
        override var origin: WGPUOrigin3D
            get() = handle.origin?.let { WGPUOrigin3D(it) } ?: error("origin is null")
            set(value) { handle.origin = value.handler }
        override var aspect: WGPUTextureAspect
            get() = handle.aspect.toUInt() as WGPUTextureAspect
            set(value) { handle.aspect = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureComponentSwizzleDescriptor {
    actual var chain: WGPUChainedStruct
    actual var swizzle: WGPUTextureComponentSwizzle
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzleDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzleDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzleDescriptor) -> Unit): ArrayHolder<WGPUTextureComponentSwizzleDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureComponentSwizzleDescriptor {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var swizzle: WGPUTextureComponentSwizzle
            get() = handle.swizzle?.let { WGPUTextureComponentSwizzle(it) } ?: error("swizzle is null")
            set(value) { handle.swizzle = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUTextureComponentSwizzleDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureComponentSwizzleDescriptor {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var swizzle: WGPUTextureComponentSwizzle
            get() = handle.swizzle?.let { WGPUTextureComponentSwizzle(it) } ?: error("swizzle is null")
            set(value) { handle.swizzle = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureDescriptor {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureDescriptor) -> Unit): ArrayHolder<WGPUTextureDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var dimension: WGPUTextureDimension
            get() = handle.dimension.toUInt() as WGPUTextureDimension
            set(value) { handle.dimension = value.toInt() }
        override var size: WGPUExtent3D
            get() = handle.size?.let { WGPUExtent3D(it) } ?: error("size is null")
            set(value) { handle.size = value.handler }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var mipLevelCount: UInt
            get() = handle.mipLevelCount.toUInt() as UInt
            set(value) { handle.mipLevelCount = value.toInt() }
        override var sampleCount: UInt
            get() = handle.sampleCount.toUInt() as UInt
            set(value) { handle.sampleCount = value.toInt() }
        override var viewFormatCount: ULong
            get() = handle.viewFormatCount.toULong() as ULong
            set(value) { handle.viewFormatCount = value.toLong() }
        override var viewFormats: NativeAddress
            get() = handle.viewFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.viewFormats = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUTextureDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override var dimension: WGPUTextureDimension
            get() = handle.dimension.toUInt() as WGPUTextureDimension
            set(value) { handle.dimension = value.toInt() }
        override var size: WGPUExtent3D
            get() = handle.size?.let { WGPUExtent3D(it) } ?: error("size is null")
            set(value) { handle.size = value.handler }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var mipLevelCount: UInt
            get() = handle.mipLevelCount.toUInt() as UInt
            set(value) { handle.mipLevelCount = value.toInt() }
        override var sampleCount: UInt
            get() = handle.sampleCount.toUInt() as UInt
            set(value) { handle.sampleCount = value.toInt() }
        override var viewFormatCount: ULong
            get() = handle.viewFormatCount.toULong() as ULong
            set(value) { handle.viewFormatCount = value.toLong() }
        override var viewFormats: NativeAddress
            get() = handle.viewFormats ?: com.sun.jna.Pointer.NULL
            set(value) { handle.viewFormats = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUVertexBufferLayout {
    actual var nextInChain: NativeAddress
    actual var stepMode: WGPUVertexStepMode
    actual var arrayStride: ULong
    actual var attributeCount: ULong
    actual var attributes: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUVertexBufferLayout {
            return ByReference(io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexBufferLayout) -> Unit): ArrayHolder<WGPUVertexBufferLayout> {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByReference = io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByReference(com.sun.jna.Pointer.NULL)) : WGPUVertexBufferLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var stepMode: WGPUVertexStepMode
            get() = handle.stepMode.toUInt() as WGPUVertexStepMode
            set(value) { handle.stepMode = value.toInt() }
        override var arrayStride: ULong
            get() = handle.arrayStride.toULong() as ULong
            set(value) { handle.arrayStride = value.toLong() }
        override var attributeCount: ULong
            get() = handle.attributeCount.toULong() as ULong
            set(value) { handle.attributeCount = value.toLong() }
        override var attributes: NativeAddress
            get() = handle.attributes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.attributes = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByValue = io.ygdrasil.wgpu.android.WGPUVertexBufferLayout.ByValue(com.sun.jna.Pointer.NULL)) : WGPUVertexBufferLayout {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var stepMode: WGPUVertexStepMode
            get() = handle.stepMode.toUInt() as WGPUVertexStepMode
            set(value) { handle.stepMode = value.toInt() }
        override var arrayStride: ULong
            get() = handle.arrayStride.toULong() as ULong
            set(value) { handle.arrayStride = value.toLong() }
        override var attributeCount: ULong
            get() = handle.attributeCount.toULong() as ULong
            set(value) { handle.attributeCount = value.toLong() }
        override var attributes: NativeAddress
            get() = handle.attributes ?: com.sun.jna.Pointer.NULL
            set(value) { handle.attributes = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUBindGroupLayoutImpl?
    actual var entryCount: ULong
    actual var entries: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupDescriptor) -> Unit): ArrayHolder<WGPUBindGroupDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUBindGroupLayoutImpl?
            get() = handle.layout?.let { WGPUBindGroupLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var entryCount: ULong
            get() = handle.entryCount.toULong() as ULong
            set(value) { handle.entryCount = value.toLong() }
        override var entries: NativeAddress
            get() = handle.entries ?: com.sun.jna.Pointer.NULL
            set(value) { handle.entries = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUBindGroupLayoutImpl?
            get() = handle.layout?.let { WGPUBindGroupLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var entryCount: ULong
            get() = handle.entryCount.toULong() as ULong
            set(value) { handle.entryCount = value.toLong() }
        override var entries: NativeAddress
            get() = handle.entries ?: com.sun.jna.Pointer.NULL
            set(value) { handle.entries = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupLayoutDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var entryCount: ULong
    actual var entries: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutDescriptor) -> Unit): ArrayHolder<WGPUBindGroupLayoutDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var entryCount: ULong
            get() = handle.entryCount.toULong() as ULong
            set(value) { handle.entryCount = value.toLong() }
        override var entries: NativeAddress
            get() = handle.entries ?: com.sun.jna.Pointer.NULL
            set(value) { handle.entries = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var entryCount: ULong
            get() = handle.entryCount.toULong() as ULong
            set(value) { handle.entryCount = value.toLong() }
        override var entries: NativeAddress
            get() = handle.entries ?: com.sun.jna.Pointer.NULL
            set(value) { handle.entries = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUColorTargetState {
    actual var nextInChain: NativeAddress
    actual var format: WGPUTextureFormat
    actual var blend: NativeAddress
    actual var writeMask: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUColorTargetState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUColorTargetState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUColorTargetState {
            val ref = io.ygdrasil.wgpu.android.WGPUColorTargetState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColorTargetState) -> Unit): ArrayHolder<WGPUColorTargetState> {
            val ref = io.ygdrasil.wgpu.android.WGPUColorTargetState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUColorTargetState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUColorTargetState.ByReference = io.ygdrasil.wgpu.android.WGPUColorTargetState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUColorTargetState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var blend: NativeAddress
            get() = handle.blend ?: com.sun.jna.Pointer.NULL
            set(value) { handle.blend = value }
        override var writeMask: ULong
            get() = handle.writeMask.toULong() as ULong
            set(value) { handle.writeMask = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUColorTargetState.ByValue = io.ygdrasil.wgpu.android.WGPUColorTargetState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUColorTargetState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var blend: NativeAddress
            get() = handle.blend ?: com.sun.jna.Pointer.NULL
            set(value) { handle.blend = value }
        override var writeMask: ULong
            get() = handle.writeMask.toULong() as ULong
            set(value) { handle.writeMask = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUComputePipelineDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUPipelineLayoutImpl?
    actual var compute: WGPUComputeState
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUComputePipelineDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePipelineDescriptor) -> Unit): ArrayHolder<WGPUComputePipelineDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUComputePipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUPipelineLayoutImpl?
            get() = handle.layout?.let { WGPUPipelineLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var compute: WGPUComputeState
            get() = handle.compute?.let { WGPUComputeState(it) } ?: error("compute is null")
            set(value) { handle.compute = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUComputePipelineDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUComputePipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUPipelineLayoutImpl?
            get() = handle.layout?.let { WGPUPipelineLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var compute: WGPUComputeState
            get() = handle.compute?.let { WGPUComputeState(it) } ?: error("compute is null")
            set(value) { handle.compute = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUDeviceDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var requiredFeatureCount: ULong
    actual var requiredFeatures: NativeAddress
    actual var requiredLimits: NativeAddress
    actual var defaultQueue: WGPUQueueDescriptor
    actual var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
    actual var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUDeviceDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceDescriptor) -> Unit): ArrayHolder<WGPUDeviceDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUDeviceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var requiredFeatureCount: ULong
            get() = handle.requiredFeatureCount.toULong() as ULong
            set(value) { handle.requiredFeatureCount = value.toLong() }
        override var requiredFeatures: NativeAddress
            get() = handle.requiredFeatures ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredFeatures = value }
        override var requiredLimits: NativeAddress
            get() = handle.requiredLimits ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredLimits = value }
        override var defaultQueue: WGPUQueueDescriptor
            get() = handle.defaultQueue?.let { WGPUQueueDescriptor(it) } ?: error("defaultQueue is null")
            set(value) { handle.defaultQueue = value.handler }
        override var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
            get() = handle.deviceLostCallbackInfo?.let { WGPUDeviceLostCallbackInfo(it) } ?: error("deviceLostCallbackInfo is null")
            set(value) { handle.deviceLostCallbackInfo = value.handler }
        override var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
            get() = handle.uncapturedErrorCallbackInfo?.let { WGPUUncapturedErrorCallbackInfo(it) } ?: error("uncapturedErrorCallbackInfo is null")
            set(value) { handle.uncapturedErrorCallbackInfo = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUDeviceDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUDeviceDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var requiredFeatureCount: ULong
            get() = handle.requiredFeatureCount.toULong() as ULong
            set(value) { handle.requiredFeatureCount = value.toLong() }
        override var requiredFeatures: NativeAddress
            get() = handle.requiredFeatures ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredFeatures = value }
        override var requiredLimits: NativeAddress
            get() = handle.requiredLimits ?: com.sun.jna.Pointer.NULL
            set(value) { handle.requiredLimits = value }
        override var defaultQueue: WGPUQueueDescriptor
            get() = handle.defaultQueue?.let { WGPUQueueDescriptor(it) } ?: error("defaultQueue is null")
            set(value) { handle.defaultQueue = value.handler }
        override var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
            get() = handle.deviceLostCallbackInfo?.let { WGPUDeviceLostCallbackInfo(it) } ?: error("deviceLostCallbackInfo is null")
            set(value) { handle.deviceLostCallbackInfo = value.handler }
        override var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
            get() = handle.uncapturedErrorCallbackInfo?.let { WGPUUncapturedErrorCallbackInfo(it) } ?: error("uncapturedErrorCallbackInfo is null")
            set(value) { handle.uncapturedErrorCallbackInfo = value.handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPassDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var colorAttachmentCount: ULong
    actual var colorAttachments: NativeAddress
    actual var depthStencilAttachment: NativeAddress
    actual var occlusionQuerySet: WGPUQuerySetImpl?
    actual var timestampWrites: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPassDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDescriptor) -> Unit): ArrayHolder<WGPURenderPassDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPassDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var colorAttachmentCount: ULong
            get() = handle.colorAttachmentCount.toULong() as ULong
            set(value) { handle.colorAttachmentCount = value.toLong() }
        override var colorAttachments: NativeAddress
            get() = handle.colorAttachments ?: com.sun.jna.Pointer.NULL
            set(value) { handle.colorAttachments = value }
        override var depthStencilAttachment: NativeAddress
            get() = handle.depthStencilAttachment ?: com.sun.jna.Pointer.NULL
            set(value) { handle.depthStencilAttachment = value }
        override var occlusionQuerySet: WGPUQuerySetImpl?
            get() = handle.occlusionQuerySet?.let { WGPUQuerySetImpl(it) }
            set(value) { handle.occlusionQuerySet = value?.handler }
        override var timestampWrites: NativeAddress
            get() = handle.timestampWrites ?: com.sun.jna.Pointer.NULL
            set(value) { handle.timestampWrites = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPURenderPassDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPassDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var colorAttachmentCount: ULong
            get() = handle.colorAttachmentCount.toULong() as ULong
            set(value) { handle.colorAttachmentCount = value.toLong() }
        override var colorAttachments: NativeAddress
            get() = handle.colorAttachments ?: com.sun.jna.Pointer.NULL
            set(value) { handle.colorAttachments = value }
        override var depthStencilAttachment: NativeAddress
            get() = handle.depthStencilAttachment ?: com.sun.jna.Pointer.NULL
            set(value) { handle.depthStencilAttachment = value }
        override var occlusionQuerySet: WGPUQuerySetImpl?
            get() = handle.occlusionQuerySet?.let { WGPUQuerySetImpl(it) }
            set(value) { handle.occlusionQuerySet = value?.handler }
        override var timestampWrites: NativeAddress
            get() = handle.timestampWrites ?: com.sun.jna.Pointer.NULL
            set(value) { handle.timestampWrites = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUTextureViewDescriptor {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUTextureViewDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureViewDescriptor) -> Unit): ArrayHolder<WGPUTextureViewDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPUTextureViewDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var dimension: WGPUTextureViewDimension
            get() = handle.dimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.dimension = value.toInt() }
        override var baseMipLevel: UInt
            get() = handle.baseMipLevel.toUInt() as UInt
            set(value) { handle.baseMipLevel = value.toInt() }
        override var mipLevelCount: UInt
            get() = handle.mipLevelCount.toUInt() as UInt
            set(value) { handle.mipLevelCount = value.toInt() }
        override var baseArrayLayer: UInt
            get() = handle.baseArrayLayer.toUInt() as UInt
            set(value) { handle.baseArrayLayer = value.toInt() }
        override var arrayLayerCount: UInt
            get() = handle.arrayLayerCount.toUInt() as UInt
            set(value) { handle.arrayLayerCount = value.toInt() }
        override var aspect: WGPUTextureAspect
            get() = handle.aspect.toUInt() as WGPUTextureAspect
            set(value) { handle.aspect = value.toInt() }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPUTextureViewDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPUTextureViewDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var format: WGPUTextureFormat
            get() = handle.format.toUInt() as WGPUTextureFormat
            set(value) { handle.format = value.toInt() }
        override var dimension: WGPUTextureViewDimension
            get() = handle.dimension.toUInt() as WGPUTextureViewDimension
            set(value) { handle.dimension = value.toInt() }
        override var baseMipLevel: UInt
            get() = handle.baseMipLevel.toUInt() as UInt
            set(value) { handle.baseMipLevel = value.toInt() }
        override var mipLevelCount: UInt
            get() = handle.mipLevelCount.toUInt() as UInt
            set(value) { handle.mipLevelCount = value.toInt() }
        override var baseArrayLayer: UInt
            get() = handle.baseArrayLayer.toUInt() as UInt
            set(value) { handle.baseArrayLayer = value.toInt() }
        override var arrayLayerCount: UInt
            get() = handle.arrayLayerCount.toUInt() as UInt
            set(value) { handle.arrayLayerCount = value.toInt() }
        override var aspect: WGPUTextureAspect
            get() = handle.aspect.toUInt() as WGPUTextureAspect
            set(value) { handle.aspect = value.toInt() }
        override var usage: ULong
            get() = handle.usage.toULong() as ULong
            set(value) { handle.usage = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUVertexState {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual var bufferCount: ULong
    actual var buffers: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUVertexState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUVertexState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUVertexState {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexState) -> Unit): ArrayHolder<WGPUVertexState> {
            val ref = io.ygdrasil.wgpu.android.WGPUVertexState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUVertexState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUVertexState.ByReference = io.ygdrasil.wgpu.android.WGPUVertexState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUVertexState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override var bufferCount: ULong
            get() = handle.bufferCount.toULong() as ULong
            set(value) { handle.bufferCount = value.toLong() }
        override var buffers: NativeAddress
            get() = handle.buffers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.buffers = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUVertexState.ByValue = io.ygdrasil.wgpu.android.WGPUVertexState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUVertexState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override var bufferCount: ULong
            get() = handle.bufferCount.toULong() as ULong
            set(value) { handle.bufferCount = value.toLong() }
        override var buffers: NativeAddress
            get() = handle.buffers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.buffers = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUFragmentState {
    actual var nextInChain: NativeAddress
    actual var module: WGPUShaderModuleImpl?
    actual var entryPoint: WGPUStringView
    actual var constantCount: ULong
    actual var constants: NativeAddress
    actual var targetCount: ULong
    actual var targets: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUFragmentState {
            return ByReference(io.ygdrasil.wgpu.android.WGPUFragmentState.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUFragmentState {
            val ref = io.ygdrasil.wgpu.android.WGPUFragmentState.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFragmentState) -> Unit): ArrayHolder<WGPUFragmentState> {
            val ref = io.ygdrasil.wgpu.android.WGPUFragmentState.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUFragmentState.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUFragmentState.ByReference = io.ygdrasil.wgpu.android.WGPUFragmentState.ByReference(com.sun.jna.Pointer.NULL)) : WGPUFragmentState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override var targetCount: ULong
            get() = handle.targetCount.toULong() as ULong
            set(value) { handle.targetCount = value.toLong() }
        override var targets: NativeAddress
            get() = handle.targets ?: com.sun.jna.Pointer.NULL
            set(value) { handle.targets = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUFragmentState.ByValue = io.ygdrasil.wgpu.android.WGPUFragmentState.ByValue(com.sun.jna.Pointer.NULL)) : WGPUFragmentState {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var module: WGPUShaderModuleImpl?
            get() = handle.module?.let { WGPUShaderModuleImpl(it) }
            set(value) { handle.module = value?.handler }
        override var entryPoint: WGPUStringView
            get() = handle.entryPoint?.let { WGPUStringView.ByReference(it) } ?: error("entryPoint is null")
            set(value) { handle.entryPoint = (value as WGPUStringView.ByReference).handle }
        override var constantCount: ULong
            get() = handle.constantCount.toULong() as ULong
            set(value) { handle.constantCount = value.toLong() }
        override var constants: NativeAddress
            get() = handle.constants ?: com.sun.jna.Pointer.NULL
            set(value) { handle.constants = value }
        override var targetCount: ULong
            get() = handle.targetCount.toULong() as ULong
            set(value) { handle.targetCount = value.toLong() }
        override var targets: NativeAddress
            get() = handle.targets ?: com.sun.jna.Pointer.NULL
            set(value) { handle.targets = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURenderPipelineDescriptor {
    actual var nextInChain: NativeAddress
    actual var label: WGPUStringView
    actual var layout: WGPUPipelineLayoutImpl?
    actual var vertex: WGPUVertexState
    actual var primitive: WGPUPrimitiveState
    actual var depthStencil: NativeAddress
    actual var multisample: WGPUMultisampleState
    actual var fragment: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURenderPipelineDescriptor {
            return ByReference(io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPipelineDescriptor) -> Unit): ArrayHolder<WGPURenderPipelineDescriptor> {
            val ref = io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByReference = io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByReference(com.sun.jna.Pointer.NULL)) : WGPURenderPipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUPipelineLayoutImpl?
            get() = handle.layout?.let { WGPUPipelineLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var vertex: WGPUVertexState
            get() = handle.vertex?.let { WGPUVertexState(it) } ?: error("vertex is null")
            set(value) { handle.vertex = value.handler }
        override var primitive: WGPUPrimitiveState
            get() = handle.primitive?.let { WGPUPrimitiveState(it) } ?: error("primitive is null")
            set(value) { handle.primitive = value.handler }
        override var depthStencil: NativeAddress
            get() = handle.depthStencil ?: com.sun.jna.Pointer.NULL
            set(value) { handle.depthStencil = value }
        override var multisample: WGPUMultisampleState
            get() = handle.multisample?.let { WGPUMultisampleState(it) } ?: error("multisample is null")
            set(value) { handle.multisample = value.handler }
        override var fragment: NativeAddress
            get() = handle.fragment ?: com.sun.jna.Pointer.NULL
            set(value) { handle.fragment = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByValue = io.ygdrasil.wgpu.android.WGPURenderPipelineDescriptor.ByValue(com.sun.jna.Pointer.NULL)) : WGPURenderPipelineDescriptor {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var layout: WGPUPipelineLayoutImpl?
            get() = handle.layout?.let { WGPUPipelineLayoutImpl(it) }
            set(value) { handle.layout = value?.handler }
        override var vertex: WGPUVertexState
            get() = handle.vertex?.let { WGPUVertexState(it) } ?: error("vertex is null")
            set(value) { handle.vertex = value.handler }
        override var primitive: WGPUPrimitiveState
            get() = handle.primitive?.let { WGPUPrimitiveState(it) } ?: error("primitive is null")
            set(value) { handle.primitive = value.handler }
        override var depthStencil: NativeAddress
            get() = handle.depthStencil ?: com.sun.jna.Pointer.NULL
            set(value) { handle.depthStencil = value }
        override var multisample: WGPUMultisampleState
            get() = handle.multisample?.let { WGPUMultisampleState(it) } ?: error("multisample is null")
            set(value) { handle.multisample = value.handler }
        override var fragment: NativeAddress
            get() = handle.fragment ?: com.sun.jna.Pointer.NULL
            set(value) { handle.fragment = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUXlibDisplayHandle {
    actual var display: NativeAddress
    actual var screen: Int
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUXlibDisplayHandle {
            return ByReference(io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUXlibDisplayHandle {
            val ref = io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXlibDisplayHandle) -> Unit): ArrayHolder<WGPUXlibDisplayHandle> {
            val ref = io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByReference = io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByReference(com.sun.jna.Pointer.NULL)) : WGPUXlibDisplayHandle {
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var screen: Int
            get() = handle.screen as Int
            set(value) { handle.screen = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByValue = io.ygdrasil.wgpu.android.WGPUXlibDisplayHandle.ByValue(com.sun.jna.Pointer.NULL)) : WGPUXlibDisplayHandle {
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override var screen: Int
            get() = handle.screen as Int
            set(value) { handle.screen = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUXcbDisplayHandle {
    actual var connection: NativeAddress
    actual var screen: Int
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUXcbDisplayHandle {
            return ByReference(io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUXcbDisplayHandle {
            val ref = io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXcbDisplayHandle) -> Unit): ArrayHolder<WGPUXcbDisplayHandle> {
            val ref = io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByReference = io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByReference(com.sun.jna.Pointer.NULL)) : WGPUXcbDisplayHandle {
        override var connection: NativeAddress
            get() = handle.connection ?: com.sun.jna.Pointer.NULL
            set(value) { handle.connection = value }
        override var screen: Int
            get() = handle.screen as Int
            set(value) { handle.screen = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByValue = io.ygdrasil.wgpu.android.WGPUXcbDisplayHandle.ByValue(com.sun.jna.Pointer.NULL)) : WGPUXcbDisplayHandle {
        override var connection: NativeAddress
            get() = handle.connection ?: com.sun.jna.Pointer.NULL
            set(value) { handle.connection = value }
        override var screen: Int
            get() = handle.screen as Int
            set(value) { handle.screen = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUWaylandDisplayHandle {
    actual var display: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUWaylandDisplayHandle {
            return ByReference(io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUWaylandDisplayHandle {
            val ref = io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUWaylandDisplayHandle) -> Unit): ArrayHolder<WGPUWaylandDisplayHandle> {
            val ref = io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByReference = io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByReference(com.sun.jna.Pointer.NULL)) : WGPUWaylandDisplayHandle {
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByValue = io.ygdrasil.wgpu.android.WGPUWaylandDisplayHandle.ByValue(com.sun.jna.Pointer.NULL)) : WGPUWaylandDisplayHandle {
        override var display: NativeAddress
            get() = handle.display ?: com.sun.jna.Pointer.NULL
            set(value) { handle.display = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUInstanceExtras {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUInstanceExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceExtras) -> Unit): ArrayHolder<WGPUInstanceExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByReference = io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUInstanceExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var backends: ULong
            get() = handle.backends.toULong() as ULong
            set(value) { handle.backends = value.toLong() }
        override var flags: ULong
            get() = handle.flags.toULong() as ULong
            set(value) { handle.flags = value.toLong() }
        override var dx12ShaderCompiler: WGPUDx12Compiler
            get() = handle.dx12ShaderCompiler.toUInt() as WGPUDx12Compiler
            set(value) { handle.dx12ShaderCompiler = value.toInt() }
        override var gles3MinorVersion: WGPUGles3MinorVersion
            get() = handle.gles3MinorVersion.toUInt() as WGPUGles3MinorVersion
            set(value) { handle.gles3MinorVersion = value.toInt() }
        override var glFenceBehaviour: WGPUGLFenceBehaviour
            get() = handle.glFenceBehaviour.toUInt() as WGPUGLFenceBehaviour
            set(value) { handle.glFenceBehaviour = value.toInt() }
        override var dxcPath: WGPUStringView
            get() = handle.dxcPath?.let { WGPUStringView.ByReference(it) } ?: error("dxcPath is null")
            set(value) { handle.dxcPath = (value as WGPUStringView.ByReference).handle }
        override var dxcMaxShaderModel: WGPUDxcMaxShaderModel
            get() = handle.dxcMaxShaderModel.toUInt() as WGPUDxcMaxShaderModel
            set(value) { handle.dxcMaxShaderModel = value.toInt() }
        override var dx12PresentationSystem: WGPUDx12SwapchainKind
            get() = handle.dx12PresentationSystem.toUInt() as WGPUDx12SwapchainKind
            set(value) { handle.dx12PresentationSystem = value.toInt() }
        override var budgetForDeviceCreation: NativeAddress
            get() = handle.budgetForDeviceCreation ?: com.sun.jna.Pointer.NULL
            set(value) { handle.budgetForDeviceCreation = value }
        override var budgetForDeviceLoss: NativeAddress
            get() = handle.budgetForDeviceLoss ?: com.sun.jna.Pointer.NULL
            set(value) { handle.budgetForDeviceLoss = value }
        override var displayHandle: WGPUNativeDisplayHandle
            get() = handle.displayHandle?.let { WGPUNativeDisplayHandle.ByReference(it) } ?: error("displayHandle is null")
            set(value) { handle.displayHandle = (value as WGPUNativeDisplayHandle.ByReference).handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByValue = io.ygdrasil.wgpu.android.WGPUInstanceExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUInstanceExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var backends: ULong
            get() = handle.backends.toULong() as ULong
            set(value) { handle.backends = value.toLong() }
        override var flags: ULong
            get() = handle.flags.toULong() as ULong
            set(value) { handle.flags = value.toLong() }
        override var dx12ShaderCompiler: WGPUDx12Compiler
            get() = handle.dx12ShaderCompiler.toUInt() as WGPUDx12Compiler
            set(value) { handle.dx12ShaderCompiler = value.toInt() }
        override var gles3MinorVersion: WGPUGles3MinorVersion
            get() = handle.gles3MinorVersion.toUInt() as WGPUGles3MinorVersion
            set(value) { handle.gles3MinorVersion = value.toInt() }
        override var glFenceBehaviour: WGPUGLFenceBehaviour
            get() = handle.glFenceBehaviour.toUInt() as WGPUGLFenceBehaviour
            set(value) { handle.glFenceBehaviour = value.toInt() }
        override var dxcPath: WGPUStringView
            get() = handle.dxcPath?.let { WGPUStringView.ByReference(it) } ?: error("dxcPath is null")
            set(value) { handle.dxcPath = (value as WGPUStringView.ByReference).handle }
        override var dxcMaxShaderModel: WGPUDxcMaxShaderModel
            get() = handle.dxcMaxShaderModel.toUInt() as WGPUDxcMaxShaderModel
            set(value) { handle.dxcMaxShaderModel = value.toInt() }
        override var dx12PresentationSystem: WGPUDx12SwapchainKind
            get() = handle.dx12PresentationSystem.toUInt() as WGPUDx12SwapchainKind
            set(value) { handle.dx12PresentationSystem = value.toInt() }
        override var budgetForDeviceCreation: NativeAddress
            get() = handle.budgetForDeviceCreation ?: com.sun.jna.Pointer.NULL
            set(value) { handle.budgetForDeviceCreation = value }
        override var budgetForDeviceLoss: NativeAddress
            get() = handle.budgetForDeviceLoss ?: com.sun.jna.Pointer.NULL
            set(value) { handle.budgetForDeviceLoss = value }
        override var displayHandle: WGPUNativeDisplayHandle
            get() = handle.displayHandle?.let { WGPUNativeDisplayHandle.ByReference(it) } ?: error("displayHandle is null")
            set(value) { handle.displayHandle = (value as WGPUNativeDisplayHandle.ByReference).handler }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUDeviceExtras {
    actual var chain: WGPUChainedStruct
    actual var tracePath: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUDeviceExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUDeviceExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceExtras) -> Unit): ArrayHolder<WGPUDeviceExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByReference = io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUDeviceExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var tracePath: WGPUStringView
            get() = handle.tracePath?.let { WGPUStringView.ByReference(it) } ?: error("tracePath is null")
            set(value) { handle.tracePath = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByValue = io.ygdrasil.wgpu.android.WGPUDeviceExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUDeviceExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var tracePath: WGPUStringView
            get() = handle.tracePath?.let { WGPUStringView.ByReference(it) } ?: error("tracePath is null")
            set(value) { handle.tracePath = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUNativeLimits {
    actual var chain: WGPUChainedStruct
    actual var maxImmediateSize: UInt
    actual var maxNonSamplerBindings: UInt
    actual var maxBindingArrayElementsPerShaderStage: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUNativeLimits {
            return ByReference(io.ygdrasil.wgpu.android.WGPUNativeLimits.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUNativeLimits {
            val ref = io.ygdrasil.wgpu.android.WGPUNativeLimits.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUNativeLimits) -> Unit): ArrayHolder<WGPUNativeLimits> {
            val ref = io.ygdrasil.wgpu.android.WGPUNativeLimits.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUNativeLimits.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUNativeLimits.ByReference = io.ygdrasil.wgpu.android.WGPUNativeLimits.ByReference(com.sun.jna.Pointer.NULL)) : WGPUNativeLimits {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxImmediateSize: UInt
            get() = handle.maxImmediateSize.toUInt() as UInt
            set(value) { handle.maxImmediateSize = value.toInt() }
        override var maxNonSamplerBindings: UInt
            get() = handle.maxNonSamplerBindings.toUInt() as UInt
            set(value) { handle.maxNonSamplerBindings = value.toInt() }
        override var maxBindingArrayElementsPerShaderStage: UInt
            get() = handle.maxBindingArrayElementsPerShaderStage.toUInt() as UInt
            set(value) { handle.maxBindingArrayElementsPerShaderStage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUNativeLimits.ByValue = io.ygdrasil.wgpu.android.WGPUNativeLimits.ByValue(com.sun.jna.Pointer.NULL)) : WGPUNativeLimits {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var maxImmediateSize: UInt
            get() = handle.maxImmediateSize.toUInt() as UInt
            set(value) { handle.maxImmediateSize = value.toInt() }
        override var maxNonSamplerBindings: UInt
            get() = handle.maxNonSamplerBindings.toUInt() as UInt
            set(value) { handle.maxNonSamplerBindings = value.toInt() }
        override var maxBindingArrayElementsPerShaderStage: UInt
            get() = handle.maxBindingArrayElementsPerShaderStage.toUInt() as UInt
            set(value) { handle.maxBindingArrayElementsPerShaderStage = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPipelineLayoutExtras {
    actual var chain: WGPUChainedStruct
    actual var immediateDataSize: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPipelineLayoutExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutExtras) -> Unit): ArrayHolder<WGPUPipelineLayoutExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByReference = io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var immediateDataSize: UInt
            get() = handle.immediateDataSize.toUInt() as UInt
            set(value) { handle.immediateDataSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByValue = io.ygdrasil.wgpu.android.WGPUPipelineLayoutExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPipelineLayoutExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var immediateDataSize: UInt
            get() = handle.immediateDataSize.toUInt() as UInt
            set(value) { handle.immediateDataSize = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderDefine {
    actual var name: WGPUStringView
    actual var value: WGPUStringView
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderDefine {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderDefine.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderDefine {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderDefine.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderDefine) -> Unit): ArrayHolder<WGPUShaderDefine> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderDefine.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderDefine.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderDefine.ByReference = io.ygdrasil.wgpu.android.WGPUShaderDefine.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderDefine {
        override var name: WGPUStringView
            get() = handle.name?.let { WGPUStringView.ByReference(it) } ?: error("name is null")
            set(value) { handle.name = (value as WGPUStringView.ByReference).handle }
        override var value: WGPUStringView
            get() = handle.value?.let { WGPUStringView.ByReference(it) } ?: error("value is null")
            set(value) { handle.value = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderDefine.ByValue = io.ygdrasil.wgpu.android.WGPUShaderDefine.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderDefine {
        override var name: WGPUStringView
            get() = handle.name?.let { WGPUStringView.ByReference(it) } ?: error("name is null")
            set(value) { handle.name = (value as WGPUStringView.ByReference).handle }
        override var value: WGPUStringView
            get() = handle.value?.let { WGPUStringView.ByReference(it) } ?: error("value is null")
            set(value) { handle.value = (value as WGPUStringView.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderSourceGLSL {
    actual var chain: WGPUChainedStruct
    actual var stage: ULong
    actual var code: WGPUStringView
    actual var defineCount: UInt
    actual var defines: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderSourceGLSL {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderSourceGLSL {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceGLSL) -> Unit): ArrayHolder<WGPUShaderSourceGLSL> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByReference = io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceGLSL {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var stage: ULong
            get() = handle.stage.toULong() as ULong
            set(value) { handle.stage = value.toLong() }
        override var code: WGPUStringView
            get() = handle.code?.let { WGPUStringView.ByReference(it) } ?: error("code is null")
            set(value) { handle.code = (value as WGPUStringView.ByReference).handle }
        override var defineCount: UInt
            get() = handle.defineCount.toUInt() as UInt
            set(value) { handle.defineCount = value.toInt() }
        override var defines: NativeAddress
            get() = handle.defines ?: com.sun.jna.Pointer.NULL
            set(value) { handle.defines = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByValue = io.ygdrasil.wgpu.android.WGPUShaderSourceGLSL.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderSourceGLSL {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var stage: ULong
            get() = handle.stage.toULong() as ULong
            set(value) { handle.stage = value.toLong() }
        override var code: WGPUStringView
            get() = handle.code?.let { WGPUStringView.ByReference(it) } ?: error("code is null")
            set(value) { handle.code = (value as WGPUStringView.ByReference).handle }
        override var defineCount: UInt
            get() = handle.defineCount.toUInt() as UInt
            set(value) { handle.defineCount = value.toInt() }
        override var defines: NativeAddress
            get() = handle.defines ?: com.sun.jna.Pointer.NULL
            set(value) { handle.defines = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUShaderModuleDescriptorSpirV {
    actual var label: WGPUStringView
    actual var sourceSize: UInt
    actual var source: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptorSpirV {
            return ByReference(io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptorSpirV {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptorSpirV) -> Unit): ArrayHolder<WGPUShaderModuleDescriptorSpirV> {
            val ref = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByReference = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByReference(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleDescriptorSpirV {
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var sourceSize: UInt
            get() = handle.sourceSize.toUInt() as UInt
            set(value) { handle.sourceSize = value.toInt() }
        override var source: NativeAddress
            get() = handle.source ?: com.sun.jna.Pointer.NULL
            set(value) { handle.source = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByValue = io.ygdrasil.wgpu.android.WGPUShaderModuleDescriptorSpirV.ByValue(com.sun.jna.Pointer.NULL)) : WGPUShaderModuleDescriptorSpirV {
        override var label: WGPUStringView
            get() = handle.label?.let { WGPUStringView.ByReference(it) } ?: error("label is null")
            set(value) { handle.label = (value as WGPUStringView.ByReference).handle }
        override var sourceSize: UInt
            get() = handle.sourceSize.toUInt() as UInt
            set(value) { handle.sourceSize = value.toInt() }
        override var source: NativeAddress
            get() = handle.source ?: com.sun.jna.Pointer.NULL
            set(value) { handle.source = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPURegistryReport {
    actual var numAllocated: ULong
    actual var numKeptFromUser: ULong
    actual var numReleasedFromUser: ULong
    actual var elementSize: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPURegistryReport {
            return ByReference(io.ygdrasil.wgpu.android.WGPURegistryReport.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPURegistryReport {
            val ref = io.ygdrasil.wgpu.android.WGPURegistryReport.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURegistryReport) -> Unit): ArrayHolder<WGPURegistryReport> {
            val ref = io.ygdrasil.wgpu.android.WGPURegistryReport.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPURegistryReport.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPURegistryReport.ByReference = io.ygdrasil.wgpu.android.WGPURegistryReport.ByReference(com.sun.jna.Pointer.NULL)) : WGPURegistryReport {
        override var numAllocated: ULong
            get() = handle.numAllocated.toULong() as ULong
            set(value) { handle.numAllocated = value.toLong() }
        override var numKeptFromUser: ULong
            get() = handle.numKeptFromUser.toULong() as ULong
            set(value) { handle.numKeptFromUser = value.toLong() }
        override var numReleasedFromUser: ULong
            get() = handle.numReleasedFromUser.toULong() as ULong
            set(value) { handle.numReleasedFromUser = value.toLong() }
        override var elementSize: ULong
            get() = handle.elementSize.toULong() as ULong
            set(value) { handle.elementSize = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPURegistryReport.ByValue = io.ygdrasil.wgpu.android.WGPURegistryReport.ByValue(com.sun.jna.Pointer.NULL)) : WGPURegistryReport {
        override var numAllocated: ULong
            get() = handle.numAllocated.toULong() as ULong
            set(value) { handle.numAllocated = value.toLong() }
        override var numKeptFromUser: ULong
            get() = handle.numKeptFromUser.toULong() as ULong
            set(value) { handle.numKeptFromUser = value.toLong() }
        override var numReleasedFromUser: ULong
            get() = handle.numReleasedFromUser.toULong() as ULong
            set(value) { handle.numReleasedFromUser = value.toLong() }
        override var elementSize: ULong
            get() = handle.elementSize.toULong() as ULong
            set(value) { handle.elementSize = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUHubReport {
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
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUHubReport {
            return ByReference(io.ygdrasil.wgpu.android.WGPUHubReport.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUHubReport {
            val ref = io.ygdrasil.wgpu.android.WGPUHubReport.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUHubReport) -> Unit): ArrayHolder<WGPUHubReport> {
            val ref = io.ygdrasil.wgpu.android.WGPUHubReport.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUHubReport.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUHubReport.ByReference = io.ygdrasil.wgpu.android.WGPUHubReport.ByReference(com.sun.jna.Pointer.NULL)) : WGPUHubReport {
        override var adapters: WGPURegistryReport
            get() = handle.adapters?.let { WGPURegistryReport.ByReference(it) } ?: error("adapters is null")
            set(value) { handle.adapters = (value as WGPURegistryReport.ByReference).handle }
        override var devices: WGPURegistryReport
            get() = handle.devices?.let { WGPURegistryReport.ByReference(it) } ?: error("devices is null")
            set(value) { handle.devices = (value as WGPURegistryReport.ByReference).handle }
        override var queues: WGPURegistryReport
            get() = handle.queues?.let { WGPURegistryReport.ByReference(it) } ?: error("queues is null")
            set(value) { handle.queues = (value as WGPURegistryReport.ByReference).handle }
        override var pipelineLayouts: WGPURegistryReport
            get() = handle.pipelineLayouts?.let { WGPURegistryReport.ByReference(it) } ?: error("pipelineLayouts is null")
            set(value) { handle.pipelineLayouts = (value as WGPURegistryReport.ByReference).handle }
        override var shaderModules: WGPURegistryReport
            get() = handle.shaderModules?.let { WGPURegistryReport.ByReference(it) } ?: error("shaderModules is null")
            set(value) { handle.shaderModules = (value as WGPURegistryReport.ByReference).handle }
        override var bindGroupLayouts: WGPURegistryReport
            get() = handle.bindGroupLayouts?.let { WGPURegistryReport.ByReference(it) } ?: error("bindGroupLayouts is null")
            set(value) { handle.bindGroupLayouts = (value as WGPURegistryReport.ByReference).handle }
        override var bindGroups: WGPURegistryReport
            get() = handle.bindGroups?.let { WGPURegistryReport.ByReference(it) } ?: error("bindGroups is null")
            set(value) { handle.bindGroups = (value as WGPURegistryReport.ByReference).handle }
        override var commandBuffers: WGPURegistryReport
            get() = handle.commandBuffers?.let { WGPURegistryReport.ByReference(it) } ?: error("commandBuffers is null")
            set(value) { handle.commandBuffers = (value as WGPURegistryReport.ByReference).handle }
        override var renderBundles: WGPURegistryReport
            get() = handle.renderBundles?.let { WGPURegistryReport.ByReference(it) } ?: error("renderBundles is null")
            set(value) { handle.renderBundles = (value as WGPURegistryReport.ByReference).handle }
        override var renderPipelines: WGPURegistryReport
            get() = handle.renderPipelines?.let { WGPURegistryReport.ByReference(it) } ?: error("renderPipelines is null")
            set(value) { handle.renderPipelines = (value as WGPURegistryReport.ByReference).handle }
        override var computePipelines: WGPURegistryReport
            get() = handle.computePipelines?.let { WGPURegistryReport.ByReference(it) } ?: error("computePipelines is null")
            set(value) { handle.computePipelines = (value as WGPURegistryReport.ByReference).handle }
        override var pipelineCaches: WGPURegistryReport
            get() = handle.pipelineCaches?.let { WGPURegistryReport.ByReference(it) } ?: error("pipelineCaches is null")
            set(value) { handle.pipelineCaches = (value as WGPURegistryReport.ByReference).handle }
        override var querySets: WGPURegistryReport
            get() = handle.querySets?.let { WGPURegistryReport.ByReference(it) } ?: error("querySets is null")
            set(value) { handle.querySets = (value as WGPURegistryReport.ByReference).handle }
        override var buffers: WGPURegistryReport
            get() = handle.buffers?.let { WGPURegistryReport.ByReference(it) } ?: error("buffers is null")
            set(value) { handle.buffers = (value as WGPURegistryReport.ByReference).handle }
        override var textures: WGPURegistryReport
            get() = handle.textures?.let { WGPURegistryReport.ByReference(it) } ?: error("textures is null")
            set(value) { handle.textures = (value as WGPURegistryReport.ByReference).handle }
        override var textureViews: WGPURegistryReport
            get() = handle.textureViews?.let { WGPURegistryReport.ByReference(it) } ?: error("textureViews is null")
            set(value) { handle.textureViews = (value as WGPURegistryReport.ByReference).handle }
        override var samplers: WGPURegistryReport
            get() = handle.samplers?.let { WGPURegistryReport.ByReference(it) } ?: error("samplers is null")
            set(value) { handle.samplers = (value as WGPURegistryReport.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUHubReport.ByValue = io.ygdrasil.wgpu.android.WGPUHubReport.ByValue(com.sun.jna.Pointer.NULL)) : WGPUHubReport {
        override var adapters: WGPURegistryReport
            get() = handle.adapters?.let { WGPURegistryReport.ByReference(it) } ?: error("adapters is null")
            set(value) { handle.adapters = (value as WGPURegistryReport.ByReference).handle }
        override var devices: WGPURegistryReport
            get() = handle.devices?.let { WGPURegistryReport.ByReference(it) } ?: error("devices is null")
            set(value) { handle.devices = (value as WGPURegistryReport.ByReference).handle }
        override var queues: WGPURegistryReport
            get() = handle.queues?.let { WGPURegistryReport.ByReference(it) } ?: error("queues is null")
            set(value) { handle.queues = (value as WGPURegistryReport.ByReference).handle }
        override var pipelineLayouts: WGPURegistryReport
            get() = handle.pipelineLayouts?.let { WGPURegistryReport.ByReference(it) } ?: error("pipelineLayouts is null")
            set(value) { handle.pipelineLayouts = (value as WGPURegistryReport.ByReference).handle }
        override var shaderModules: WGPURegistryReport
            get() = handle.shaderModules?.let { WGPURegistryReport.ByReference(it) } ?: error("shaderModules is null")
            set(value) { handle.shaderModules = (value as WGPURegistryReport.ByReference).handle }
        override var bindGroupLayouts: WGPURegistryReport
            get() = handle.bindGroupLayouts?.let { WGPURegistryReport.ByReference(it) } ?: error("bindGroupLayouts is null")
            set(value) { handle.bindGroupLayouts = (value as WGPURegistryReport.ByReference).handle }
        override var bindGroups: WGPURegistryReport
            get() = handle.bindGroups?.let { WGPURegistryReport.ByReference(it) } ?: error("bindGroups is null")
            set(value) { handle.bindGroups = (value as WGPURegistryReport.ByReference).handle }
        override var commandBuffers: WGPURegistryReport
            get() = handle.commandBuffers?.let { WGPURegistryReport.ByReference(it) } ?: error("commandBuffers is null")
            set(value) { handle.commandBuffers = (value as WGPURegistryReport.ByReference).handle }
        override var renderBundles: WGPURegistryReport
            get() = handle.renderBundles?.let { WGPURegistryReport.ByReference(it) } ?: error("renderBundles is null")
            set(value) { handle.renderBundles = (value as WGPURegistryReport.ByReference).handle }
        override var renderPipelines: WGPURegistryReport
            get() = handle.renderPipelines?.let { WGPURegistryReport.ByReference(it) } ?: error("renderPipelines is null")
            set(value) { handle.renderPipelines = (value as WGPURegistryReport.ByReference).handle }
        override var computePipelines: WGPURegistryReport
            get() = handle.computePipelines?.let { WGPURegistryReport.ByReference(it) } ?: error("computePipelines is null")
            set(value) { handle.computePipelines = (value as WGPURegistryReport.ByReference).handle }
        override var pipelineCaches: WGPURegistryReport
            get() = handle.pipelineCaches?.let { WGPURegistryReport.ByReference(it) } ?: error("pipelineCaches is null")
            set(value) { handle.pipelineCaches = (value as WGPURegistryReport.ByReference).handle }
        override var querySets: WGPURegistryReport
            get() = handle.querySets?.let { WGPURegistryReport.ByReference(it) } ?: error("querySets is null")
            set(value) { handle.querySets = (value as WGPURegistryReport.ByReference).handle }
        override var buffers: WGPURegistryReport
            get() = handle.buffers?.let { WGPURegistryReport.ByReference(it) } ?: error("buffers is null")
            set(value) { handle.buffers = (value as WGPURegistryReport.ByReference).handle }
        override var textures: WGPURegistryReport
            get() = handle.textures?.let { WGPURegistryReport.ByReference(it) } ?: error("textures is null")
            set(value) { handle.textures = (value as WGPURegistryReport.ByReference).handle }
        override var textureViews: WGPURegistryReport
            get() = handle.textureViews?.let { WGPURegistryReport.ByReference(it) } ?: error("textureViews is null")
            set(value) { handle.textureViews = (value as WGPURegistryReport.ByReference).handle }
        override var samplers: WGPURegistryReport
            get() = handle.samplers?.let { WGPURegistryReport.ByReference(it) } ?: error("samplers is null")
            set(value) { handle.samplers = (value as WGPURegistryReport.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUGlobalReport {
    actual var surfaces: WGPURegistryReport
    actual var hub: WGPUHubReport
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUGlobalReport {
            return ByReference(io.ygdrasil.wgpu.android.WGPUGlobalReport.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUGlobalReport {
            val ref = io.ygdrasil.wgpu.android.WGPUGlobalReport.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUGlobalReport) -> Unit): ArrayHolder<WGPUGlobalReport> {
            val ref = io.ygdrasil.wgpu.android.WGPUGlobalReport.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUGlobalReport.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUGlobalReport.ByReference = io.ygdrasil.wgpu.android.WGPUGlobalReport.ByReference(com.sun.jna.Pointer.NULL)) : WGPUGlobalReport {
        override var surfaces: WGPURegistryReport
            get() = handle.surfaces?.let { WGPURegistryReport.ByReference(it) } ?: error("surfaces is null")
            set(value) { handle.surfaces = (value as WGPURegistryReport.ByReference).handle }
        override var hub: WGPUHubReport
            get() = handle.hub?.let { WGPUHubReport.ByReference(it) } ?: error("hub is null")
            set(value) { handle.hub = (value as WGPUHubReport.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUGlobalReport.ByValue = io.ygdrasil.wgpu.android.WGPUGlobalReport.ByValue(com.sun.jna.Pointer.NULL)) : WGPUGlobalReport {
        override var surfaces: WGPURegistryReport
            get() = handle.surfaces?.let { WGPURegistryReport.ByReference(it) } ?: error("surfaces is null")
            set(value) { handle.surfaces = (value as WGPURegistryReport.ByReference).handle }
        override var hub: WGPUHubReport
            get() = handle.hub?.let { WGPUHubReport.ByReference(it) } ?: error("hub is null")
            set(value) { handle.hub = (value as WGPUHubReport.ByReference).handle }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUInstanceEnumerateAdapterOptions {
    actual var nextInChain: NativeAddress
    actual var backends: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUInstanceEnumerateAdapterOptions {
            return ByReference(io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUInstanceEnumerateAdapterOptions {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceEnumerateAdapterOptions) -> Unit): ArrayHolder<WGPUInstanceEnumerateAdapterOptions> {
            val ref = io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByReference = io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByReference(com.sun.jna.Pointer.NULL)) : WGPUInstanceEnumerateAdapterOptions {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var backends: ULong
            get() = handle.backends.toULong() as ULong
            set(value) { handle.backends = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByValue = io.ygdrasil.wgpu.android.WGPUInstanceEnumerateAdapterOptions.ByValue(com.sun.jna.Pointer.NULL)) : WGPUInstanceEnumerateAdapterOptions {
        override var nextInChain: NativeAddress
            get() = handle.nextInChain ?: com.sun.jna.Pointer.NULL
            set(value) { handle.nextInChain = value }
        override var backends: ULong
            get() = handle.backends.toULong() as ULong
            set(value) { handle.backends = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupEntryExtras {
    actual var chain: WGPUChainedStruct
    actual var buffers: NativeAddress
    actual var bufferCount: ULong
    actual var samplers: NativeAddress
    actual var samplerCount: ULong
    actual var textureViews: NativeAddress
    actual var textureViewCount: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupEntryExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntryExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupEntryExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupEntryExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var buffers: NativeAddress
            get() = handle.buffers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.buffers = value }
        override var bufferCount: ULong
            get() = handle.bufferCount.toULong() as ULong
            set(value) { handle.bufferCount = value.toLong() }
        override var samplers: NativeAddress
            get() = handle.samplers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.samplers = value }
        override var samplerCount: ULong
            get() = handle.samplerCount.toULong() as ULong
            set(value) { handle.samplerCount = value.toLong() }
        override var textureViews: NativeAddress
            get() = handle.textureViews ?: com.sun.jna.Pointer.NULL
            set(value) { handle.textureViews = value }
        override var textureViewCount: ULong
            get() = handle.textureViewCount.toULong() as ULong
            set(value) { handle.textureViewCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupEntryExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupEntryExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var buffers: NativeAddress
            get() = handle.buffers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.buffers = value }
        override var bufferCount: ULong
            get() = handle.bufferCount.toULong() as ULong
            set(value) { handle.bufferCount = value.toLong() }
        override var samplers: NativeAddress
            get() = handle.samplers ?: com.sun.jna.Pointer.NULL
            set(value) { handle.samplers = value }
        override var samplerCount: ULong
            get() = handle.samplerCount.toULong() as ULong
            set(value) { handle.samplerCount = value.toLong() }
        override var textureViews: NativeAddress
            get() = handle.textureViews ?: com.sun.jna.Pointer.NULL
            set(value) { handle.textureViews = value }
        override var textureViewCount: ULong
            get() = handle.textureViewCount.toULong() as ULong
            set(value) { handle.textureViewCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUBindGroupLayoutEntryExtras {
    actual var chain: WGPUChainedStruct
    actual var count: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntryExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntryExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntryExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByReference = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutEntryExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByValue = io.ygdrasil.wgpu.android.WGPUBindGroupLayoutEntryExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUBindGroupLayoutEntryExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var count: UInt
            get() = handle.count.toUInt() as UInt
            set(value) { handle.count = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUQuerySetDescriptorExtras {
    actual var chain: WGPUChainedStruct
    actual var pipelineStatistics: NativeAddress
    actual var pipelineStatisticCount: ULong
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptorExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptorExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptorExtras) -> Unit): ArrayHolder<WGPUQuerySetDescriptorExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByReference = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUQuerySetDescriptorExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var pipelineStatistics: NativeAddress
            get() = handle.pipelineStatistics ?: com.sun.jna.Pointer.NULL
            set(value) { handle.pipelineStatistics = value }
        override var pipelineStatisticCount: ULong
            get() = handle.pipelineStatisticCount.toULong() as ULong
            set(value) { handle.pipelineStatisticCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByValue = io.ygdrasil.wgpu.android.WGPUQuerySetDescriptorExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUQuerySetDescriptorExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var pipelineStatistics: NativeAddress
            get() = handle.pipelineStatistics ?: com.sun.jna.Pointer.NULL
            set(value) { handle.pipelineStatistics = value }
        override var pipelineStatisticCount: ULong
            get() = handle.pipelineStatisticCount.toULong() as ULong
            set(value) { handle.pipelineStatisticCount = value.toLong() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceConfigurationExtras {
    actual var chain: WGPUChainedStruct
    actual var desiredMaximumFrameLatency: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceConfigurationExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfigurationExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfigurationExtras) -> Unit): ArrayHolder<WGPUSurfaceConfigurationExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceConfigurationExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var desiredMaximumFrameLatency: UInt
            get() = handle.desiredMaximumFrameLatency.toUInt() as UInt
            set(value) { handle.desiredMaximumFrameLatency = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceConfigurationExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceConfigurationExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var desiredMaximumFrameLatency: UInt
            get() = handle.desiredMaximumFrameLatency.toUInt() as UInt
            set(value) { handle.desiredMaximumFrameLatency = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUSurfaceSourceSwapChainPanel {
    actual var chain: WGPUChainedStruct
    actual var panelNative: NativeAddress
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUSurfaceSourceSwapChainPanel {
            return ByReference(io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceSwapChainPanel {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceSwapChainPanel) -> Unit): ArrayHolder<WGPUSurfaceSourceSwapChainPanel> {
            val ref = io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByReference(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceSwapChainPanel {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var panelNative: NativeAddress
            get() = handle.panelNative ?: com.sun.jna.Pointer.NULL
            set(value) { handle.panelNative = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceSwapChainPanel.ByValue(com.sun.jna.Pointer.NULL)) : WGPUSurfaceSourceSwapChainPanel {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var panelNative: NativeAddress
            get() = handle.panelNative ?: com.sun.jna.Pointer.NULL
            set(value) { handle.panelNative = value }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

actual interface WGPUPrimitiveStateExtras {
    actual var chain: WGPUChainedStruct
    actual var polygonMode: WGPUPolygonMode
    actual var conservative: UInt
    actual val handler: NativeAddress
    actual companion object {
        actual operator fun invoke(address: NativeAddress): WGPUPrimitiveStateExtras {
            return ByReference(io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByReference(address))
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUPrimitiveStateExtras {
            val ref = io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByReference()
            allocator.register(ref)
            return ByReference(ref)
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveStateExtras) -> Unit): ArrayHolder<WGPUPrimitiveStateExtras> {
            val ref = io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByValue()
            val array = ref.toArray(size.toInt())
            array.forEachIndexed { index, struct ->
                provider(index.toUInt(), ByValue(struct as io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByValue))
            }
            val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer
            return ArrayHolder(pointer)
        }
    }
    
    class ByReference(val handle: io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByReference = io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByReference(com.sun.jna.Pointer.NULL)) : WGPUPrimitiveStateExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var polygonMode: WGPUPolygonMode
            get() = handle.polygonMode.toUInt() as WGPUPolygonMode
            set(value) { handle.polygonMode = value.toInt() }
        override var conservative: UInt
            get() = handle.conservative.toUInt() as UInt
            set(value) { handle.conservative = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
    
    class ByValue(val handle: io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByValue = io.ygdrasil.wgpu.android.WGPUPrimitiveStateExtras.ByValue(com.sun.jna.Pointer.NULL)) : WGPUPrimitiveStateExtras {
        override var chain: WGPUChainedStruct
            get() = handle.chain?.let { WGPUChainedStruct.ByReference(it) } ?: error("chain is null")
            set(value) { handle.chain = (value as WGPUChainedStruct.ByReference).handle }
        override var polygonMode: WGPUPolygonMode
            get() = handle.polygonMode.toUInt() as WGPUPolygonMode
            set(value) { handle.polygonMode = value.toInt() }
        override var conservative: UInt
            get() = handle.conservative.toUInt() as UInt
            set(value) { handle.conservative = value.toInt() }
        override val handler: NativeAddress
            get() {
                handle.write()
                return handle.pointer
            }
    }
}

