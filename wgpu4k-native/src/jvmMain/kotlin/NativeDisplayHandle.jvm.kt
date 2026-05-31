// This file has been manually created to handle union type for NativeDisplayHandle
// DO NOT EDIT - This file is manually maintained
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.ArrayHolder
import java.lang.foreign.*

actual interface WGPUNativeDisplayHandle {
    actual var type: WGPUNativeDisplayHandleType
    actual val xlib: WGPUXlibDisplayHandle?
    actual val xcb: WGPUXcbDisplayHandle?
    actual val wayland: WGPUWaylandDisplayHandle?
    actual fun setXlib(value: WGPUXlibDisplayHandle)
    actual fun setXcb(value: WGPUXcbDisplayHandle)
    actual fun setWayland(value: WGPUWaylandDisplayHandle)
    actual val handler: NativeAddress
    
    class ByReference(override val handler: NativeAddress) : WGPUNativeDisplayHandle {
        private var _type: WGPUNativeDisplayHandleType = WGPUNativeDisplayHandleType_None
        private var _xlibDisplay: NativeAddress = NativeAddress(MemorySegment.NULL)
        private var _xlibScreen: Int = 0
        private var _xcbConnection: NativeAddress = NativeAddress(MemorySegment.NULL)
        private var _xcbScreen: Int = 0
        private var _waylandDisplay: NativeAddress = NativeAddress(MemorySegment.NULL)
        
        override var type: WGPUNativeDisplayHandleType
            get() = _type
            set(newValue) { _type = newValue }
        
        override val xlib: WGPUXlibDisplayHandle?
            get() = if (type == WGPUNativeDisplayHandleType_Xlib) {
                object : WGPUXlibDisplayHandle {
                    override var display: NativeAddress = _xlibDisplay
                    override var screen: Int = _xlibScreen
                    override val handler: NativeAddress = this@ByReference.handler
                }
            } else null
        
        override val xcb: WGPUXcbDisplayHandle?
            get() = if (type == WGPUNativeDisplayHandleType_Xcb) {
                object : WGPUXcbDisplayHandle {
                    override var connection: NativeAddress = _xcbConnection
                    override var screen: Int = _xcbScreen
                    override val handler: NativeAddress = this@ByReference.handler
                }
            } else null
        
        override val wayland: WGPUWaylandDisplayHandle?
            get() = if (type == WGPUNativeDisplayHandleType_Wayland) {
                object : WGPUWaylandDisplayHandle {
                    override var display: NativeAddress = _waylandDisplay
                    override val handler: NativeAddress = this@ByReference.handler
                }
            } else null
        
        override fun setXlib(value: WGPUXlibDisplayHandle) {
            type = WGPUNativeDisplayHandleType_Xlib
            _xlibDisplay = value.display
            _xlibScreen = value.screen
        }
        
        override fun setXcb(value: WGPUXcbDisplayHandle) {
            type = WGPUNativeDisplayHandleType_Xcb
            _xcbConnection = value.connection
            _xcbScreen = value.screen
        }
        
        override fun setWayland(value: WGPUWaylandDisplayHandle) {
            type = WGPUNativeDisplayHandleType_Wayland
            _waylandDisplay = value.display
        }
    }
    
    actual companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("type"),
            MemoryLayout.paddingLayout(4),
            MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_BYTE).withName("union")
        ).withName("WGPUNativeDisplayHandle")

        actual operator fun invoke(address: NativeAddress): WGPUNativeDisplayHandle {
            return ByReference(address)
        }
        
        actual fun allocate(allocator: MemoryAllocator): WGPUNativeDisplayHandle {
            return ByReference(allocator.allocate(24))
        }
        
        actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): ArrayHolder<WGPUNativeDisplayHandle> {
            val elementSize = 24L
            val segment = allocator.allocate(elementSize * size.toLong())
            for (i in 0 until size.toInt()) {
                val slice = segment.handler.asSlice(i.toLong() * elementSize, elementSize).let(::NativeAddress)
                provider(i.toUInt(), ByReference(slice))
            }
            return ArrayHolder(segment)
        }
    }
}
