// This file has been generated DO NOT EDIT !!!
package webgpu.android

import ffi.NativeAddress

sealed class WGPUStringView(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var data: com.sun.jna.Pointer? = null
	@JvmField var length: Long = 0L
	override fun getFieldOrder() = listOf("data", "length")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStringView(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUStringView) : this(other.pointer) {
			this.data = other.data
			this.length = other.length
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStringView(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUStringView) : this(other.pointer) {
			this.data = other.data
			this.length = other.length
		}
	}
}

sealed class WGPUAdapterInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var vendor: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var architecture: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var device: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var description: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var backendType: Int = 0
	@JvmField var adapterType: Int = 0
	@JvmField var vendorID: Int = 0
	@JvmField var deviceID: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "vendor", "architecture", "device", "description", "backendType", "adapterType", "vendorID", "deviceID")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUAdapterInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUAdapterInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.vendor = other.vendor
			this.architecture = other.architecture
			this.device = other.device
			this.description = other.description
			this.backendType = other.backendType
			this.adapterType = other.adapterType
			this.vendorID = other.vendorID
			this.deviceID = other.deviceID
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUAdapterInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUAdapterInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.vendor = other.vendor
			this.architecture = other.architecture
			this.device = other.device
			this.description = other.description
			this.backendType = other.backendType
			this.adapterType = other.adapterType
			this.vendorID = other.vendorID
			this.deviceID = other.deviceID
		}
	}
}

sealed class WGPUBindGroupDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "entryCount", "entries")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBindGroupDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.entryCount = other.entryCount
			this.entries = other.entries
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBindGroupDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.entryCount = other.entryCount
			this.entries = other.entries
		}
	}
}

sealed class WGPUBindGroupEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var binding: Int = 0
	@JvmField var buffer: com.sun.jna.Pointer? = null
	@JvmField var offset: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var sampler: com.sun.jna.Pointer? = null
	@JvmField var textureView: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "binding", "buffer", "offset", "size", "sampler", "textureView")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupEntry(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBindGroupEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.binding = other.binding
			this.buffer = other.buffer
			this.offset = other.offset
			this.size = other.size
			this.sampler = other.sampler
			this.textureView = other.textureView
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupEntry(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBindGroupEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.binding = other.binding
			this.buffer = other.buffer
			this.offset = other.offset
			this.size = other.size
			this.sampler = other.sampler
			this.textureView = other.textureView
		}
	}
}

sealed class WGPUBindGroupLayoutDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var entryCount: Long = 0L
	@JvmField var entries: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "entryCount", "entries")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBindGroupLayoutDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.entryCount = other.entryCount
			this.entries = other.entries
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBindGroupLayoutDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.entryCount = other.entryCount
			this.entries = other.entries
		}
	}
}

sealed class WGPUBufferBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var type: Int = 0
	@JvmField var hasDynamicOffset: Int = 0
	@JvmField var minBindingSize: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "type", "hasDynamicOffset", "minBindingSize")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferBindingLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBufferBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.type = other.type
			this.hasDynamicOffset = other.hasDynamicOffset
			this.minBindingSize = other.minBindingSize
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferBindingLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBufferBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.type = other.type
			this.hasDynamicOffset = other.hasDynamicOffset
			this.minBindingSize = other.minBindingSize
		}
	}
}

sealed class WGPUSamplerBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var type: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "type")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerBindingLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSamplerBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.type = other.type
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerBindingLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSamplerBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.type = other.type
		}
	}
}

sealed class WGPUTextureBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var sampleType: Int = 0
	@JvmField var viewDimension: Int = 0
	@JvmField var multisampled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "sampleType", "viewDimension", "multisampled")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureBindingLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTextureBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.sampleType = other.sampleType
			this.viewDimension = other.viewDimension
			this.multisampled = other.multisampled
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureBindingLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTextureBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.sampleType = other.sampleType
			this.viewDimension = other.viewDimension
			this.multisampled = other.multisampled
		}
	}
}

sealed class WGPUStorageTextureBindingLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var access: Int = 0
	@JvmField var format: Int = 0
	@JvmField var viewDimension: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "access", "format", "viewDimension")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStorageTextureBindingLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUStorageTextureBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.access = other.access
			this.format = other.format
			this.viewDimension = other.viewDimension
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStorageTextureBindingLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUStorageTextureBindingLayout) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.access = other.access
			this.format = other.format
			this.viewDimension = other.viewDimension
		}
	}
}

sealed class WGPUBindGroupLayoutEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var binding: Int = 0
	@JvmField var visibility: Long = 0L
	@JvmField var buffer: WGPUBufferBindingLayout.ByValue = WGPUBufferBindingLayout.ByValue()
	@JvmField var sampler: WGPUSamplerBindingLayout.ByValue = WGPUSamplerBindingLayout.ByValue()
	@JvmField var texture: WGPUTextureBindingLayout.ByValue = WGPUTextureBindingLayout.ByValue()
	@JvmField var storageTexture: WGPUStorageTextureBindingLayout.ByValue = WGPUStorageTextureBindingLayout.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "binding", "visibility", "buffer", "sampler", "texture", "storageTexture")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutEntry(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBindGroupLayoutEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.binding = other.binding
			this.visibility = other.visibility
			this.buffer = other.buffer
			this.sampler = other.sampler
			this.texture = other.texture
			this.storageTexture = other.storageTexture
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBindGroupLayoutEntry(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBindGroupLayoutEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.binding = other.binding
			this.visibility = other.visibility
			this.buffer = other.buffer
			this.sampler = other.sampler
			this.texture = other.texture
			this.storageTexture = other.storageTexture
		}
	}
}

sealed class WGPUBlendComponent(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var operation: Int = 0
	@JvmField var srcFactor: Int = 0
	@JvmField var dstFactor: Int = 0
	override fun getFieldOrder() = listOf("operation", "srcFactor", "dstFactor")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBlendComponent(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBlendComponent) : this(other.pointer) {
			this.operation = other.operation
			this.srcFactor = other.srcFactor
			this.dstFactor = other.dstFactor
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBlendComponent(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBlendComponent) : this(other.pointer) {
			this.operation = other.operation
			this.srcFactor = other.srcFactor
			this.dstFactor = other.dstFactor
		}
	}
}

sealed class WGPUBlendState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var color: WGPUBlendComponent.ByValue = WGPUBlendComponent.ByValue()
	@JvmField var alpha: WGPUBlendComponent.ByValue = WGPUBlendComponent.ByValue()
	override fun getFieldOrder() = listOf("color", "alpha")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBlendState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBlendState) : this(other.pointer) {
			this.color = other.color
			this.alpha = other.alpha
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBlendState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBlendState) : this(other.pointer) {
			this.color = other.color
			this.alpha = other.alpha
		}
	}
}

sealed class WGPUBufferDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var usage: Long = 0L
	@JvmField var size: Long = 0L
	@JvmField var mappedAtCreation: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "size", "mappedAtCreation")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBufferDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.usage = other.usage
			this.size = other.size
			this.mappedAtCreation = other.mappedAtCreation
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBufferDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.usage = other.usage
			this.size = other.size
			this.mappedAtCreation = other.mappedAtCreation
		}
	}
}

sealed class WGPUColor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var r: Double = 0.0
	@JvmField var g: Double = 0.0
	@JvmField var b: Double = 0.0
	@JvmField var a: Double = 0.0
	override fun getFieldOrder() = listOf("r", "g", "b", "a")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUColor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUColor) : this(other.pointer) {
			this.r = other.r
			this.g = other.g
			this.b = other.b
			this.a = other.a
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUColor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUColor) : this(other.pointer) {
			this.r = other.r
			this.g = other.g
			this.b = other.b
			this.a = other.a
		}
	}
}

sealed class WGPUColorTargetState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var format: Int = 0
	@JvmField var blend: WGPUBlendState.ByReference?? = null
	@JvmField var writeMask: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "format", "blend", "writeMask")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUColorTargetState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUColorTargetState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.format = other.format
			this.blend = other.blend
			this.writeMask = other.writeMask
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUColorTargetState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUColorTargetState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.format = other.format
			this.blend = other.blend
			this.writeMask = other.writeMask
		}
	}
}

sealed class WGPUCommandBufferDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCommandBufferDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCommandBufferDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCommandBufferDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCommandBufferDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPUCommandEncoderDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCommandEncoderDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCommandEncoderDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCommandEncoderDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCommandEncoderDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPUCompilationInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var messageCount: Long = 0L
	@JvmField var messages: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "messageCount", "messages")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCompilationInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.messageCount = other.messageCount
			this.messages = other.messages
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCompilationInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.messageCount = other.messageCount
			this.messages = other.messages
		}
	}
}

sealed class WGPUCompilationMessage(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var message: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var type: Int = 0
	@JvmField var lineNum: Long = 0L
	@JvmField var linePos: Long = 0L
	@JvmField var offset: Long = 0L
	@JvmField var length: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "message", "type", "lineNum", "linePos", "offset", "length")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationMessage(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCompilationMessage) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.message = other.message
			this.type = other.type
			this.lineNum = other.lineNum
			this.linePos = other.linePos
			this.offset = other.offset
			this.length = other.length
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationMessage(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCompilationMessage) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.message = other.message
			this.type = other.type
			this.lineNum = other.lineNum
			this.linePos = other.linePos
			this.offset = other.offset
			this.length = other.length
		}
	}
}

sealed class WGPUComputePassDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var timestampWrites: WGPUComputePassTimestampWrites.ByReference?? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "timestampWrites")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUComputePassDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.timestampWrites = other.timestampWrites
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUComputePassDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.timestampWrites = other.timestampWrites
		}
	}
}

sealed class WGPUComputePassTimestampWrites(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassTimestampWrites(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUComputePassTimestampWrites) : this(other.pointer) {
			this.querySet = other.querySet
			this.beginningOfPassWriteIndex = other.beginningOfPassWriteIndex
			this.endOfPassWriteIndex = other.endOfPassWriteIndex
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePassTimestampWrites(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUComputePassTimestampWrites) : this(other.pointer) {
			this.querySet = other.querySet
			this.beginningOfPassWriteIndex = other.beginningOfPassWriteIndex
			this.endOfPassWriteIndex = other.endOfPassWriteIndex
		}
	}
}

sealed class WGPUProgrammableStageDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField var entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUProgrammableStageDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUProgrammableStageDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUProgrammableStageDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUProgrammableStageDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
		}
	}
}

sealed class WGPUComputePipelineDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField var compute: WGPUProgrammableStageDescriptor.ByValue = WGPUProgrammableStageDescriptor.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "compute")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUComputePipelineDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUComputePipelineDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.compute = other.compute
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUComputePipelineDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUComputePipelineDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.compute = other.compute
		}
	}
}

sealed class WGPUConstantEntry(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var key: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var value: Double = 0.0
	override fun getFieldOrder() = listOf("nextInChain", "key", "value")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUConstantEntry(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUConstantEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.key = other.key
			this.value = other.value
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUConstantEntry(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUConstantEntry) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.key = other.key
			this.value = other.value
		}
	}
}

sealed class WGPUStencilFaceState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var compare: Int = 0
	@JvmField var failOp: Int = 0
	@JvmField var depthFailOp: Int = 0
	@JvmField var passOp: Int = 0
	override fun getFieldOrder() = listOf("compare", "failOp", "depthFailOp", "passOp")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUStencilFaceState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUStencilFaceState) : this(other.pointer) {
			this.compare = other.compare
			this.failOp = other.failOp
			this.depthFailOp = other.depthFailOp
			this.passOp = other.passOp
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUStencilFaceState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUStencilFaceState) : this(other.pointer) {
			this.compare = other.compare
			this.failOp = other.failOp
			this.depthFailOp = other.depthFailOp
			this.passOp = other.passOp
		}
	}
}

sealed class WGPUDepthStencilState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var format: Int = 0
	@JvmField var depthWriteEnabled: Int = 0
	@JvmField var depthCompare: Int = 0
	@JvmField var stencilFront: WGPUStencilFaceState.ByValue = WGPUStencilFaceState.ByValue()
	@JvmField var stencilBack: WGPUStencilFaceState.ByValue = WGPUStencilFaceState.ByValue()
	@JvmField var stencilReadMask: Int = 0
	@JvmField var stencilWriteMask: Int = 0
	@JvmField var depthBias: Int = 0
	@JvmField var depthBiasSlopeScale: Float = 0f
	@JvmField var depthBiasClamp: Float = 0f
	override fun getFieldOrder() = listOf("nextInChain", "format", "depthWriteEnabled", "depthCompare", "stencilFront", "stencilBack", "stencilReadMask", "stencilWriteMask", "depthBias", "depthBiasSlopeScale", "depthBiasClamp")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDepthStencilState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUDepthStencilState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.format = other.format
			this.depthWriteEnabled = other.depthWriteEnabled
			this.depthCompare = other.depthCompare
			this.stencilFront = other.stencilFront
			this.stencilBack = other.stencilBack
			this.stencilReadMask = other.stencilReadMask
			this.stencilWriteMask = other.stencilWriteMask
			this.depthBias = other.depthBias
			this.depthBiasSlopeScale = other.depthBiasSlopeScale
			this.depthBiasClamp = other.depthBiasClamp
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDepthStencilState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUDepthStencilState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.format = other.format
			this.depthWriteEnabled = other.depthWriteEnabled
			this.depthCompare = other.depthCompare
			this.stencilFront = other.stencilFront
			this.stencilBack = other.stencilBack
			this.stencilReadMask = other.stencilReadMask
			this.stencilWriteMask = other.stencilWriteMask
			this.depthBias = other.depthBias
			this.depthBiasSlopeScale = other.depthBiasSlopeScale
			this.depthBiasClamp = other.depthBiasClamp
		}
	}
}

sealed class WGPUQueueDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQueueDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUQueueDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQueueDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUQueueDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPUDeviceLostCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceLostCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUDeviceLostCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceLostCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUDeviceLostCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUUncapturedErrorCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUUncapturedErrorCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUUncapturedErrorCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUUncapturedErrorCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUUncapturedErrorCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUDeviceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var requiredFeatureCount: Long = 0L
	@JvmField var requiredFeatures: com.sun.jna.Pointer? = null
	@JvmField var requiredLimits: WGPULimits.ByReference?? = null
	@JvmField var defaultQueue: WGPUQueueDescriptor.ByValue = WGPUQueueDescriptor.ByValue()
	@JvmField var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo.ByValue = WGPUDeviceLostCallbackInfo.ByValue()
	@JvmField var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo.ByValue = WGPUUncapturedErrorCallbackInfo.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label", "requiredFeatureCount", "requiredFeatures", "requiredLimits", "defaultQueue", "deviceLostCallbackInfo", "uncapturedErrorCallbackInfo")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUDeviceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.requiredFeatureCount = other.requiredFeatureCount
			this.requiredFeatures = other.requiredFeatures
			this.requiredLimits = other.requiredLimits
			this.defaultQueue = other.defaultQueue
			this.deviceLostCallbackInfo = other.deviceLostCallbackInfo
			this.uncapturedErrorCallbackInfo = other.uncapturedErrorCallbackInfo
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUDeviceDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUDeviceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.requiredFeatureCount = other.requiredFeatureCount
			this.requiredFeatures = other.requiredFeatures
			this.requiredLimits = other.requiredLimits
			this.defaultQueue = other.defaultQueue
			this.deviceLostCallbackInfo = other.deviceLostCallbackInfo
			this.uncapturedErrorCallbackInfo = other.uncapturedErrorCallbackInfo
		}
	}
}

sealed class WGPUExtent3D(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var width: Int = 0
	@JvmField var height: Int = 0
	@JvmField var depthOrArrayLayers: Int = 0
	override fun getFieldOrder() = listOf("width", "height", "depthOrArrayLayers")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUExtent3D(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUExtent3D) : this(other.pointer) {
			this.width = other.width
			this.height = other.height
			this.depthOrArrayLayers = other.depthOrArrayLayers
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUExtent3D(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUExtent3D) : this(other.pointer) {
			this.width = other.width
			this.height = other.height
			this.depthOrArrayLayers = other.depthOrArrayLayers
		}
	}
}

sealed class WGPUFragmentState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField var entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var targetCount: Long = 0L
	@JvmField var targets: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "targetCount", "targets")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFragmentState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUFragmentState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
			this.targetCount = other.targetCount
			this.targets = other.targets
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFragmentState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUFragmentState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
			this.targetCount = other.targetCount
			this.targets = other.targets
		}
	}
}

sealed class WGPUFuture(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var id: Long = 0L
	override fun getFieldOrder() = listOf("id")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFuture(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUFuture) : this(other.pointer) {
			this.id = other.id
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFuture(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUFuture) : this(other.pointer) {
			this.id = other.id
		}
	}
}

sealed class WGPUFutureWaitInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var future: WGPUFuture.ByValue = WGPUFuture.ByValue()
	@JvmField var completed: Int = 0
	override fun getFieldOrder() = listOf("future", "completed")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUFutureWaitInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUFutureWaitInfo) : this(other.pointer) {
			this.future = other.future
			this.completed = other.completed
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUFutureWaitInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUFutureWaitInfo) : this(other.pointer) {
			this.future = other.future
			this.completed = other.completed
		}
	}
}

sealed class WGPUInstanceCapabilities(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var timedWaitAnyEnable: Int = 0
	@JvmField var timedWaitAnyMaxCount: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "timedWaitAnyEnable", "timedWaitAnyMaxCount")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceCapabilities(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUInstanceCapabilities) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.timedWaitAnyEnable = other.timedWaitAnyEnable
			this.timedWaitAnyMaxCount = other.timedWaitAnyMaxCount
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceCapabilities(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUInstanceCapabilities) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.timedWaitAnyEnable = other.timedWaitAnyEnable
			this.timedWaitAnyMaxCount = other.timedWaitAnyMaxCount
		}
	}
}

sealed class WGPUInstanceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var features: WGPUInstanceCapabilities.ByValue = WGPUInstanceCapabilities.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "features")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUInstanceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.features = other.features
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUInstanceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.features = other.features
		}
	}
}

sealed class WGPULimits(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var maxTextureDimension1D: Int = 0
	@JvmField var maxTextureDimension2D: Int = 0
	@JvmField var maxTextureDimension3D: Int = 0
	@JvmField var maxTextureArrayLayers: Int = 0
	@JvmField var maxBindGroups: Int = 0
	@JvmField var maxBindGroupsPlusVertexBuffers: Int = 0
	@JvmField var maxBindingsPerBindGroup: Int = 0
	@JvmField var maxDynamicUniformBuffersPerPipelineLayout: Int = 0
	@JvmField var maxDynamicStorageBuffersPerPipelineLayout: Int = 0
	@JvmField var maxSampledTexturesPerShaderStage: Int = 0
	@JvmField var maxSamplersPerShaderStage: Int = 0
	@JvmField var maxStorageBuffersPerShaderStage: Int = 0
	@JvmField var maxStorageTexturesPerShaderStage: Int = 0
	@JvmField var maxUniformBuffersPerShaderStage: Int = 0
	@JvmField var maxUniformBufferBindingSize: Long = 0L
	@JvmField var maxStorageBufferBindingSize: Long = 0L
	@JvmField var minUniformBufferOffsetAlignment: Int = 0
	@JvmField var minStorageBufferOffsetAlignment: Int = 0
	@JvmField var maxVertexBuffers: Int = 0
	@JvmField var maxBufferSize: Long = 0L
	@JvmField var maxVertexAttributes: Int = 0
	@JvmField var maxVertexBufferArrayStride: Int = 0
	@JvmField var maxInterStageShaderVariables: Int = 0
	@JvmField var maxColorAttachments: Int = 0
	@JvmField var maxColorAttachmentBytesPerSample: Int = 0
	@JvmField var maxComputeWorkgroupStorageSize: Int = 0
	@JvmField var maxComputeInvocationsPerWorkgroup: Int = 0
	@JvmField var maxComputeWorkgroupSizeX: Int = 0
	@JvmField var maxComputeWorkgroupSizeY: Int = 0
	@JvmField var maxComputeWorkgroupSizeZ: Int = 0
	@JvmField var maxComputeWorkgroupsPerDimension: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "maxTextureDimension1D", "maxTextureDimension2D", "maxTextureDimension3D", "maxTextureArrayLayers", "maxBindGroups", "maxBindGroupsPlusVertexBuffers", "maxBindingsPerBindGroup", "maxDynamicUniformBuffersPerPipelineLayout", "maxDynamicStorageBuffersPerPipelineLayout", "maxSampledTexturesPerShaderStage", "maxSamplersPerShaderStage", "maxStorageBuffersPerShaderStage", "maxStorageTexturesPerShaderStage", "maxUniformBuffersPerShaderStage", "maxUniformBufferBindingSize", "maxStorageBufferBindingSize", "minUniformBufferOffsetAlignment", "minStorageBufferOffsetAlignment", "maxVertexBuffers", "maxBufferSize", "maxVertexAttributes", "maxVertexBufferArrayStride", "maxInterStageShaderVariables", "maxColorAttachments", "maxColorAttachmentBytesPerSample", "maxComputeWorkgroupStorageSize", "maxComputeInvocationsPerWorkgroup", "maxComputeWorkgroupSizeX", "maxComputeWorkgroupSizeY", "maxComputeWorkgroupSizeZ", "maxComputeWorkgroupsPerDimension")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPULimits(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPULimits) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.maxTextureDimension1D = other.maxTextureDimension1D
			this.maxTextureDimension2D = other.maxTextureDimension2D
			this.maxTextureDimension3D = other.maxTextureDimension3D
			this.maxTextureArrayLayers = other.maxTextureArrayLayers
			this.maxBindGroups = other.maxBindGroups
			this.maxBindGroupsPlusVertexBuffers = other.maxBindGroupsPlusVertexBuffers
			this.maxBindingsPerBindGroup = other.maxBindingsPerBindGroup
			this.maxDynamicUniformBuffersPerPipelineLayout = other.maxDynamicUniformBuffersPerPipelineLayout
			this.maxDynamicStorageBuffersPerPipelineLayout = other.maxDynamicStorageBuffersPerPipelineLayout
			this.maxSampledTexturesPerShaderStage = other.maxSampledTexturesPerShaderStage
			this.maxSamplersPerShaderStage = other.maxSamplersPerShaderStage
			this.maxStorageBuffersPerShaderStage = other.maxStorageBuffersPerShaderStage
			this.maxStorageTexturesPerShaderStage = other.maxStorageTexturesPerShaderStage
			this.maxUniformBuffersPerShaderStage = other.maxUniformBuffersPerShaderStage
			this.maxUniformBufferBindingSize = other.maxUniformBufferBindingSize
			this.maxStorageBufferBindingSize = other.maxStorageBufferBindingSize
			this.minUniformBufferOffsetAlignment = other.minUniformBufferOffsetAlignment
			this.minStorageBufferOffsetAlignment = other.minStorageBufferOffsetAlignment
			this.maxVertexBuffers = other.maxVertexBuffers
			this.maxBufferSize = other.maxBufferSize
			this.maxVertexAttributes = other.maxVertexAttributes
			this.maxVertexBufferArrayStride = other.maxVertexBufferArrayStride
			this.maxInterStageShaderVariables = other.maxInterStageShaderVariables
			this.maxColorAttachments = other.maxColorAttachments
			this.maxColorAttachmentBytesPerSample = other.maxColorAttachmentBytesPerSample
			this.maxComputeWorkgroupStorageSize = other.maxComputeWorkgroupStorageSize
			this.maxComputeInvocationsPerWorkgroup = other.maxComputeInvocationsPerWorkgroup
			this.maxComputeWorkgroupSizeX = other.maxComputeWorkgroupSizeX
			this.maxComputeWorkgroupSizeY = other.maxComputeWorkgroupSizeY
			this.maxComputeWorkgroupSizeZ = other.maxComputeWorkgroupSizeZ
			this.maxComputeWorkgroupsPerDimension = other.maxComputeWorkgroupsPerDimension
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPULimits(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPULimits) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.maxTextureDimension1D = other.maxTextureDimension1D
			this.maxTextureDimension2D = other.maxTextureDimension2D
			this.maxTextureDimension3D = other.maxTextureDimension3D
			this.maxTextureArrayLayers = other.maxTextureArrayLayers
			this.maxBindGroups = other.maxBindGroups
			this.maxBindGroupsPlusVertexBuffers = other.maxBindGroupsPlusVertexBuffers
			this.maxBindingsPerBindGroup = other.maxBindingsPerBindGroup
			this.maxDynamicUniformBuffersPerPipelineLayout = other.maxDynamicUniformBuffersPerPipelineLayout
			this.maxDynamicStorageBuffersPerPipelineLayout = other.maxDynamicStorageBuffersPerPipelineLayout
			this.maxSampledTexturesPerShaderStage = other.maxSampledTexturesPerShaderStage
			this.maxSamplersPerShaderStage = other.maxSamplersPerShaderStage
			this.maxStorageBuffersPerShaderStage = other.maxStorageBuffersPerShaderStage
			this.maxStorageTexturesPerShaderStage = other.maxStorageTexturesPerShaderStage
			this.maxUniformBuffersPerShaderStage = other.maxUniformBuffersPerShaderStage
			this.maxUniformBufferBindingSize = other.maxUniformBufferBindingSize
			this.maxStorageBufferBindingSize = other.maxStorageBufferBindingSize
			this.minUniformBufferOffsetAlignment = other.minUniformBufferOffsetAlignment
			this.minStorageBufferOffsetAlignment = other.minStorageBufferOffsetAlignment
			this.maxVertexBuffers = other.maxVertexBuffers
			this.maxBufferSize = other.maxBufferSize
			this.maxVertexAttributes = other.maxVertexAttributes
			this.maxVertexBufferArrayStride = other.maxVertexBufferArrayStride
			this.maxInterStageShaderVariables = other.maxInterStageShaderVariables
			this.maxColorAttachments = other.maxColorAttachments
			this.maxColorAttachmentBytesPerSample = other.maxColorAttachmentBytesPerSample
			this.maxComputeWorkgroupStorageSize = other.maxComputeWorkgroupStorageSize
			this.maxComputeInvocationsPerWorkgroup = other.maxComputeInvocationsPerWorkgroup
			this.maxComputeWorkgroupSizeX = other.maxComputeWorkgroupSizeX
			this.maxComputeWorkgroupSizeY = other.maxComputeWorkgroupSizeY
			this.maxComputeWorkgroupSizeZ = other.maxComputeWorkgroupSizeZ
			this.maxComputeWorkgroupsPerDimension = other.maxComputeWorkgroupsPerDimension
		}
	}
}

sealed class WGPUMultisampleState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var count: Int = 0
	@JvmField var mask: Int = 0
	@JvmField var alphaToCoverageEnabled: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "count", "mask", "alphaToCoverageEnabled")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUMultisampleState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUMultisampleState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.count = other.count
			this.mask = other.mask
			this.alphaToCoverageEnabled = other.alphaToCoverageEnabled
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUMultisampleState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUMultisampleState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.count = other.count
			this.mask = other.mask
			this.alphaToCoverageEnabled = other.alphaToCoverageEnabled
		}
	}
}

sealed class WGPUOrigin3D(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var x: Int = 0
	@JvmField var y: Int = 0
	@JvmField var z: Int = 0
	override fun getFieldOrder() = listOf("x", "y", "z")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUOrigin3D(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUOrigin3D) : this(other.pointer) {
			this.x = other.x
			this.y = other.y
			this.z = other.z
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUOrigin3D(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUOrigin3D) : this(other.pointer) {
			this.x = other.x
			this.y = other.y
			this.z = other.z
		}
	}
}

sealed class WGPUPipelineLayoutDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var bindGroupLayoutCount: Long = 0L
	@JvmField var bindGroupLayouts: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "bindGroupLayoutCount", "bindGroupLayouts")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPipelineLayoutDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUPipelineLayoutDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.bindGroupLayoutCount = other.bindGroupLayoutCount
			this.bindGroupLayouts = other.bindGroupLayouts
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPipelineLayoutDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUPipelineLayoutDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.bindGroupLayoutCount = other.bindGroupLayoutCount
			this.bindGroupLayouts = other.bindGroupLayouts
		}
	}
}

sealed class WGPUPrimitiveState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var topology: Int = 0
	@JvmField var stripIndexFormat: Int = 0
	@JvmField var frontFace: Int = 0
	@JvmField var cullMode: Int = 0
	@JvmField var unclippedDepth: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "topology", "stripIndexFormat", "frontFace", "cullMode", "unclippedDepth")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPrimitiveState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUPrimitiveState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.topology = other.topology
			this.stripIndexFormat = other.stripIndexFormat
			this.frontFace = other.frontFace
			this.cullMode = other.cullMode
			this.unclippedDepth = other.unclippedDepth
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPrimitiveState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUPrimitiveState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.topology = other.topology
			this.stripIndexFormat = other.stripIndexFormat
			this.frontFace = other.frontFace
			this.cullMode = other.cullMode
			this.unclippedDepth = other.unclippedDepth
		}
	}
}

sealed class WGPUQuerySetDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var type: Int = 0
	@JvmField var count: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "type", "count")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQuerySetDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUQuerySetDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.type = other.type
			this.count = other.count
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQuerySetDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUQuerySetDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.type = other.type
			this.count = other.count
		}
	}
}

sealed class WGPURenderBundleDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderBundleDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderBundleDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPURenderBundleEncoderDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var colorFormatCount: Long = 0L
	@JvmField var colorFormats: com.sun.jna.Pointer? = null
	@JvmField var depthStencilFormat: Int = 0
	@JvmField var sampleCount: Int = 0
	@JvmField var depthReadOnly: Int = 0
	@JvmField var stencilReadOnly: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorFormatCount", "colorFormats", "depthStencilFormat", "sampleCount", "depthReadOnly", "stencilReadOnly")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleEncoderDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderBundleEncoderDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.colorFormatCount = other.colorFormatCount
			this.colorFormats = other.colorFormats
			this.depthStencilFormat = other.depthStencilFormat
			this.sampleCount = other.sampleCount
			this.depthReadOnly = other.depthReadOnly
			this.stencilReadOnly = other.stencilReadOnly
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderBundleEncoderDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderBundleEncoderDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.colorFormatCount = other.colorFormatCount
			this.colorFormats = other.colorFormats
			this.depthStencilFormat = other.depthStencilFormat
			this.sampleCount = other.sampleCount
			this.depthReadOnly = other.depthReadOnly
			this.stencilReadOnly = other.stencilReadOnly
		}
	}
}

sealed class WGPURenderPassColorAttachment(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var view: com.sun.jna.Pointer? = null
	@JvmField var depthSlice: Int = 0
	@JvmField var resolveTarget: com.sun.jna.Pointer? = null
	@JvmField var loadOp: Int = 0
	@JvmField var storeOp: Int = 0
	@JvmField var clearValue: WGPUColor.ByValue = WGPUColor.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "view", "depthSlice", "resolveTarget", "loadOp", "storeOp", "clearValue")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassColorAttachment(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPassColorAttachment) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.view = other.view
			this.depthSlice = other.depthSlice
			this.resolveTarget = other.resolveTarget
			this.loadOp = other.loadOp
			this.storeOp = other.storeOp
			this.clearValue = other.clearValue
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassColorAttachment(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPassColorAttachment) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.view = other.view
			this.depthSlice = other.depthSlice
			this.resolveTarget = other.resolveTarget
			this.loadOp = other.loadOp
			this.storeOp = other.storeOp
			this.clearValue = other.clearValue
		}
	}
}

sealed class WGPURenderPassDepthStencilAttachment(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var view: com.sun.jna.Pointer? = null
	@JvmField var depthLoadOp: Int = 0
	@JvmField var depthStoreOp: Int = 0
	@JvmField var depthClearValue: Float = 0f
	@JvmField var depthReadOnly: Int = 0
	@JvmField var stencilLoadOp: Int = 0
	@JvmField var stencilStoreOp: Int = 0
	@JvmField var stencilClearValue: Int = 0
	@JvmField var stencilReadOnly: Int = 0
	override fun getFieldOrder() = listOf("view", "depthLoadOp", "depthStoreOp", "depthClearValue", "depthReadOnly", "stencilLoadOp", "stencilStoreOp", "stencilClearValue", "stencilReadOnly")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDepthStencilAttachment(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPassDepthStencilAttachment) : this(other.pointer) {
			this.view = other.view
			this.depthLoadOp = other.depthLoadOp
			this.depthStoreOp = other.depthStoreOp
			this.depthClearValue = other.depthClearValue
			this.depthReadOnly = other.depthReadOnly
			this.stencilLoadOp = other.stencilLoadOp
			this.stencilStoreOp = other.stencilStoreOp
			this.stencilClearValue = other.stencilClearValue
			this.stencilReadOnly = other.stencilReadOnly
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDepthStencilAttachment(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPassDepthStencilAttachment) : this(other.pointer) {
			this.view = other.view
			this.depthLoadOp = other.depthLoadOp
			this.depthStoreOp = other.depthStoreOp
			this.depthClearValue = other.depthClearValue
			this.depthReadOnly = other.depthReadOnly
			this.stencilLoadOp = other.stencilLoadOp
			this.stencilStoreOp = other.stencilStoreOp
			this.stencilClearValue = other.stencilClearValue
			this.stencilReadOnly = other.stencilReadOnly
		}
	}
}

sealed class WGPURenderPassDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var colorAttachmentCount: Long = 0L
	@JvmField var colorAttachments: com.sun.jna.Pointer? = null
	@JvmField var depthStencilAttachment: WGPURenderPassDepthStencilAttachment.ByReference?? = null
	@JvmField var occlusionQuerySet: com.sun.jna.Pointer? = null
	@JvmField var timestampWrites: WGPURenderPassTimestampWrites.ByReference?? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "colorAttachmentCount", "colorAttachments", "depthStencilAttachment", "occlusionQuerySet", "timestampWrites")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPassDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.colorAttachmentCount = other.colorAttachmentCount
			this.colorAttachments = other.colorAttachments
			this.depthStencilAttachment = other.depthStencilAttachment
			this.occlusionQuerySet = other.occlusionQuerySet
			this.timestampWrites = other.timestampWrites
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPassDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.colorAttachmentCount = other.colorAttachmentCount
			this.colorAttachments = other.colorAttachments
			this.depthStencilAttachment = other.depthStencilAttachment
			this.occlusionQuerySet = other.occlusionQuerySet
			this.timestampWrites = other.timestampWrites
		}
	}
}

sealed class WGPUChainedStruct(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var next: WGPUChainedStruct.ByReference?? = null
	@JvmField var sType: Int = 0
	override fun getFieldOrder() = listOf("next", "sType")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStruct(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUChainedStruct) : this(other.pointer) {
			this.next = other.next
			this.sType = other.sType
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStruct(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUChainedStruct) : this(other.pointer) {
			this.next = other.next
			this.sType = other.sType
		}
	}
}

sealed class WGPURenderPassMaxDrawCount(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var maxDrawCount: Long = 0L
	override fun getFieldOrder() = listOf("chain", "maxDrawCount")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassMaxDrawCount(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPassMaxDrawCount) : this(other.pointer) {
			this.chain = other.chain
			this.maxDrawCount = other.maxDrawCount
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassMaxDrawCount(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPassMaxDrawCount) : this(other.pointer) {
			this.chain = other.chain
			this.maxDrawCount = other.maxDrawCount
		}
	}
}

sealed class WGPURenderPassTimestampWrites(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var querySet: com.sun.jna.Pointer? = null
	@JvmField var beginningOfPassWriteIndex: Int = 0
	@JvmField var endOfPassWriteIndex: Int = 0
	override fun getFieldOrder() = listOf("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassTimestampWrites(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPassTimestampWrites) : this(other.pointer) {
			this.querySet = other.querySet
			this.beginningOfPassWriteIndex = other.beginningOfPassWriteIndex
			this.endOfPassWriteIndex = other.endOfPassWriteIndex
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPassTimestampWrites(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPassTimestampWrites) : this(other.pointer) {
			this.querySet = other.querySet
			this.beginningOfPassWriteIndex = other.beginningOfPassWriteIndex
			this.endOfPassWriteIndex = other.endOfPassWriteIndex
		}
	}
}

sealed class WGPUVertexState(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var module: com.sun.jna.Pointer? = null
	@JvmField var entryPoint: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var constantCount: Long = 0L
	@JvmField var constants: com.sun.jna.Pointer? = null
	@JvmField var bufferCount: Long = 0L
	@JvmField var buffers: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "module", "entryPoint", "constantCount", "constants", "bufferCount", "buffers")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexState(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUVertexState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
			this.bufferCount = other.bufferCount
			this.buffers = other.buffers
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexState(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUVertexState) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.module = other.module
			this.entryPoint = other.entryPoint
			this.constantCount = other.constantCount
			this.constants = other.constants
			this.bufferCount = other.bufferCount
			this.buffers = other.buffers
		}
	}
}

sealed class WGPURenderPipelineDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var layout: com.sun.jna.Pointer? = null
	@JvmField var vertex: WGPUVertexState.ByValue = WGPUVertexState.ByValue()
	@JvmField var primitive: WGPUPrimitiveState.ByValue = WGPUPrimitiveState.ByValue()
	@JvmField var depthStencil: WGPUDepthStencilState.ByReference?? = null
	@JvmField var multisample: WGPUMultisampleState.ByValue = WGPUMultisampleState.ByValue()
	@JvmField var fragment: WGPUFragmentState.ByReference?? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "layout", "vertex", "primitive", "depthStencil", "multisample", "fragment")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURenderPipelineDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURenderPipelineDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.vertex = other.vertex
			this.primitive = other.primitive
			this.depthStencil = other.depthStencil
			this.multisample = other.multisample
			this.fragment = other.fragment
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURenderPipelineDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURenderPipelineDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.layout = other.layout
			this.vertex = other.vertex
			this.primitive = other.primitive
			this.depthStencil = other.depthStencil
			this.multisample = other.multisample
			this.fragment = other.fragment
		}
	}
}

sealed class WGPURequestAdapterOptions(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var featureLevel: Int = 0
	@JvmField var powerPreference: Int = 0
	@JvmField var forceFallbackAdapter: Int = 0
	@JvmField var backendType: Int = 0
	@JvmField var compatibleSurface: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "featureLevel", "powerPreference", "forceFallbackAdapter", "backendType", "compatibleSurface")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterOptions(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURequestAdapterOptions) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.featureLevel = other.featureLevel
			this.powerPreference = other.powerPreference
			this.forceFallbackAdapter = other.forceFallbackAdapter
			this.backendType = other.backendType
			this.compatibleSurface = other.compatibleSurface
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterOptions(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURequestAdapterOptions) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.featureLevel = other.featureLevel
			this.powerPreference = other.powerPreference
			this.forceFallbackAdapter = other.forceFallbackAdapter
			this.backendType = other.backendType
			this.compatibleSurface = other.compatibleSurface
		}
	}
}

sealed class WGPUSamplerDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var addressModeU: Int = 0
	@JvmField var addressModeV: Int = 0
	@JvmField var addressModeW: Int = 0
	@JvmField var magFilter: Int = 0
	@JvmField var minFilter: Int = 0
	@JvmField var mipmapFilter: Int = 0
	@JvmField var lodMinClamp: Float = 0f
	@JvmField var lodMaxClamp: Float = 0f
	@JvmField var compare: Int = 0
	@JvmField var maxAnisotropy: Short = 0
	override fun getFieldOrder() = listOf("nextInChain", "label", "addressModeU", "addressModeV", "addressModeW", "magFilter", "minFilter", "mipmapFilter", "lodMinClamp", "lodMaxClamp", "compare", "maxAnisotropy")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSamplerDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.addressModeU = other.addressModeU
			this.addressModeV = other.addressModeV
			this.addressModeW = other.addressModeW
			this.magFilter = other.magFilter
			this.minFilter = other.minFilter
			this.mipmapFilter = other.mipmapFilter
			this.lodMinClamp = other.lodMinClamp
			this.lodMaxClamp = other.lodMaxClamp
			this.compare = other.compare
			this.maxAnisotropy = other.maxAnisotropy
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSamplerDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSamplerDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.addressModeU = other.addressModeU
			this.addressModeV = other.addressModeV
			this.addressModeW = other.addressModeW
			this.magFilter = other.magFilter
			this.minFilter = other.minFilter
			this.mipmapFilter = other.mipmapFilter
			this.lodMinClamp = other.lodMinClamp
			this.lodMaxClamp = other.lodMaxClamp
			this.compare = other.compare
			this.maxAnisotropy = other.maxAnisotropy
		}
	}
}

sealed class WGPUShaderModuleDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderModuleDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUShaderModuleDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderModuleDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUShaderModuleDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPUShaderSourceSPIRV(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var codeSize: Int = 0
	@JvmField var code: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "codeSize", "code")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceSPIRV(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUShaderSourceSPIRV) : this(other.pointer) {
			this.chain = other.chain
			this.codeSize = other.codeSize
			this.code = other.code
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceSPIRV(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUShaderSourceSPIRV) : this(other.pointer) {
			this.chain = other.chain
			this.codeSize = other.codeSize
			this.code = other.code
		}
	}
}

sealed class WGPUShaderSourceWGSL(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var code: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("chain", "code")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceWGSL(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUShaderSourceWGSL) : this(other.pointer) {
			this.chain = other.chain
			this.code = other.code
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUShaderSourceWGSL(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUShaderSourceWGSL) : this(other.pointer) {
			this.chain = other.chain
			this.code = other.code
		}
	}
}

sealed class WGPUSupportedFeatures(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var featureCount: Long = 0L
	@JvmField var features: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("featureCount", "features")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedFeatures(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSupportedFeatures) : this(other.pointer) {
			this.featureCount = other.featureCount
			this.features = other.features
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedFeatures(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSupportedFeatures) : this(other.pointer) {
			this.featureCount = other.featureCount
			this.features = other.features
		}
	}
}

sealed class WGPUSupportedWGSLLanguageFeatures(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var featureCount: Long = 0L
	@JvmField var features: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("featureCount", "features")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedWGSLLanguageFeatures(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSupportedWGSLLanguageFeatures) : this(other.pointer) {
			this.featureCount = other.featureCount
			this.features = other.features
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSupportedWGSLLanguageFeatures(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSupportedWGSLLanguageFeatures) : this(other.pointer) {
			this.featureCount = other.featureCount
			this.features = other.features
		}
	}
}

sealed class WGPUSurfaceCapabilities(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var usages: Long = 0L
	@JvmField var formatCount: Long = 0L
	@JvmField var formats: com.sun.jna.Pointer? = null
	@JvmField var presentModeCount: Long = 0L
	@JvmField var presentModes: com.sun.jna.Pointer? = null
	@JvmField var alphaModeCount: Long = 0L
	@JvmField var alphaModes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "usages", "formatCount", "formats", "presentModeCount", "presentModes", "alphaModeCount", "alphaModes")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceCapabilities(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceCapabilities) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.usages = other.usages
			this.formatCount = other.formatCount
			this.formats = other.formats
			this.presentModeCount = other.presentModeCount
			this.presentModes = other.presentModes
			this.alphaModeCount = other.alphaModeCount
			this.alphaModes = other.alphaModes
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceCapabilities(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceCapabilities) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.usages = other.usages
			this.formatCount = other.formatCount
			this.formats = other.formats
			this.presentModeCount = other.presentModeCount
			this.presentModes = other.presentModes
			this.alphaModeCount = other.alphaModeCount
			this.alphaModes = other.alphaModes
		}
	}
}

sealed class WGPUSurfaceConfiguration(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var device: com.sun.jna.Pointer? = null
	@JvmField var format: Int = 0
	@JvmField var usage: Long = 0L
	@JvmField var width: Int = 0
	@JvmField var height: Int = 0
	@JvmField var viewFormatCount: Long = 0L
	@JvmField var viewFormats: com.sun.jna.Pointer? = null
	@JvmField var alphaMode: Int = 0
	@JvmField var presentMode: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "device", "format", "usage", "width", "height", "viewFormatCount", "viewFormats", "alphaMode", "presentMode")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceConfiguration(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceConfiguration) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.device = other.device
			this.format = other.format
			this.usage = other.usage
			this.width = other.width
			this.height = other.height
			this.viewFormatCount = other.viewFormatCount
			this.viewFormats = other.viewFormats
			this.alphaMode = other.alphaMode
			this.presentMode = other.presentMode
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceConfiguration(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceConfiguration) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.device = other.device
			this.format = other.format
			this.usage = other.usage
			this.width = other.width
			this.height = other.height
			this.viewFormatCount = other.viewFormatCount
			this.viewFormats = other.viewFormats
			this.alphaMode = other.alphaMode
			this.presentMode = other.presentMode
		}
	}
}

sealed class WGPUSurfaceDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("nextInChain", "label")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
		}
	}
}

sealed class WGPUSurfaceSourceAndroidNativeWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var window: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "window")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceAndroidNativeWindow(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceAndroidNativeWindow) : this(other.pointer) {
			this.chain = other.chain
			this.window = other.window
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceAndroidNativeWindow(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceAndroidNativeWindow) : this(other.pointer) {
			this.chain = other.chain
			this.window = other.window
		}
	}
}

sealed class WGPUSurfaceSourceMetalLayer(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var layer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "layer")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceMetalLayer(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceMetalLayer) : this(other.pointer) {
			this.chain = other.chain
			this.layer = other.layer
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceMetalLayer(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceMetalLayer) : this(other.pointer) {
			this.chain = other.chain
			this.layer = other.layer
		}
	}
}

sealed class WGPUSurfaceSourceWaylandSurface(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var surface: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "display", "surface")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWaylandSurface(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceWaylandSurface) : this(other.pointer) {
			this.chain = other.chain
			this.display = other.display
			this.surface = other.surface
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWaylandSurface(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceWaylandSurface) : this(other.pointer) {
			this.chain = other.chain
			this.display = other.display
			this.surface = other.surface
		}
	}
}

sealed class WGPUSurfaceSourceWindowsHWND(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var hinstance: com.sun.jna.Pointer? = null
	@JvmField var hwnd: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("chain", "hinstance", "hwnd")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWindowsHWND(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceWindowsHWND) : this(other.pointer) {
			this.chain = other.chain
			this.hinstance = other.hinstance
			this.hwnd = other.hwnd
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceWindowsHWND(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceWindowsHWND) : this(other.pointer) {
			this.chain = other.chain
			this.hinstance = other.hinstance
			this.hwnd = other.hwnd
		}
	}
}

sealed class WGPUSurfaceSourceXCBWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var connection: com.sun.jna.Pointer? = null
	@JvmField var window: Int = 0
	override fun getFieldOrder() = listOf("chain", "connection", "window")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXCBWindow(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceXCBWindow) : this(other.pointer) {
			this.chain = other.chain
			this.connection = other.connection
			this.window = other.window
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXCBWindow(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceXCBWindow) : this(other.pointer) {
			this.chain = other.chain
			this.connection = other.connection
			this.window = other.window
		}
	}
}

sealed class WGPUSurfaceSourceXlibWindow(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var display: com.sun.jna.Pointer? = null
	@JvmField var window: Long = 0L
	override fun getFieldOrder() = listOf("chain", "display", "window")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXlibWindow(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceSourceXlibWindow) : this(other.pointer) {
			this.chain = other.chain
			this.display = other.display
			this.window = other.window
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceSourceXlibWindow(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceSourceXlibWindow) : this(other.pointer) {
			this.chain = other.chain
			this.display = other.display
			this.window = other.window
		}
	}
}

sealed class WGPUSurfaceTexture(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var status: Int = 0
	override fun getFieldOrder() = listOf("nextInChain", "texture", "status")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceTexture(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUSurfaceTexture) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.texture = other.texture
			this.status = other.status
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUSurfaceTexture(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUSurfaceTexture) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.texture = other.texture
			this.status = other.status
		}
	}
}

sealed class WGPUTexelCopyBufferLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var offset: Long = 0L
	@JvmField var bytesPerRow: Int = 0
	@JvmField var rowsPerImage: Int = 0
	override fun getFieldOrder() = listOf("offset", "bytesPerRow", "rowsPerImage")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyBufferLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTexelCopyBufferLayout) : this(other.pointer) {
			this.offset = other.offset
			this.bytesPerRow = other.bytesPerRow
			this.rowsPerImage = other.rowsPerImage
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyBufferLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTexelCopyBufferLayout) : this(other.pointer) {
			this.offset = other.offset
			this.bytesPerRow = other.bytesPerRow
			this.rowsPerImage = other.rowsPerImage
		}
	}
}

sealed class WGPUTexelCopyBufferInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var layout: WGPUTexelCopyBufferLayout.ByValue = WGPUTexelCopyBufferLayout.ByValue()
	@JvmField var buffer: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("layout", "buffer")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyBufferInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTexelCopyBufferInfo) : this(other.pointer) {
			this.layout = other.layout
			this.buffer = other.buffer
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyBufferInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTexelCopyBufferInfo) : this(other.pointer) {
			this.layout = other.layout
			this.buffer = other.buffer
		}
	}
}

sealed class WGPUTexelCopyTextureInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var texture: com.sun.jna.Pointer? = null
	@JvmField var mipLevel: Int = 0
	@JvmField var origin: WGPUOrigin3D.ByValue = WGPUOrigin3D.ByValue()
	@JvmField var aspect: Int = 0
	override fun getFieldOrder() = listOf("texture", "mipLevel", "origin", "aspect")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyTextureInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTexelCopyTextureInfo) : this(other.pointer) {
			this.texture = other.texture
			this.mipLevel = other.mipLevel
			this.origin = other.origin
			this.aspect = other.aspect
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTexelCopyTextureInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTexelCopyTextureInfo) : this(other.pointer) {
			this.texture = other.texture
			this.mipLevel = other.mipLevel
			this.origin = other.origin
			this.aspect = other.aspect
		}
	}
}

sealed class WGPUTextureDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var usage: Long = 0L
	@JvmField var dimension: Int = 0
	@JvmField var size: WGPUExtent3D.ByValue = WGPUExtent3D.ByValue()
	@JvmField var format: Int = 0
	@JvmField var mipLevelCount: Int = 0
	@JvmField var sampleCount: Int = 0
	@JvmField var viewFormatCount: Long = 0L
	@JvmField var viewFormats: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "label", "usage", "dimension", "size", "format", "mipLevelCount", "sampleCount", "viewFormatCount", "viewFormats")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTextureDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.usage = other.usage
			this.dimension = other.dimension
			this.size = other.size
			this.format = other.format
			this.mipLevelCount = other.mipLevelCount
			this.sampleCount = other.sampleCount
			this.viewFormatCount = other.viewFormatCount
			this.viewFormats = other.viewFormats
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTextureDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.usage = other.usage
			this.dimension = other.dimension
			this.size = other.size
			this.format = other.format
			this.mipLevelCount = other.mipLevelCount
			this.sampleCount = other.sampleCount
			this.viewFormatCount = other.viewFormatCount
			this.viewFormats = other.viewFormats
		}
	}
}

sealed class WGPUTextureViewDescriptor(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: com.sun.jna.Pointer? = null
	@JvmField var label: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var format: Int = 0
	@JvmField var dimension: Int = 0
	@JvmField var baseMipLevel: Int = 0
	@JvmField var mipLevelCount: Int = 0
	@JvmField var baseArrayLayer: Int = 0
	@JvmField var arrayLayerCount: Int = 0
	@JvmField var aspect: Int = 0
	@JvmField var usage: Long = 0L
	override fun getFieldOrder() = listOf("nextInChain", "label", "format", "dimension", "baseMipLevel", "mipLevelCount", "baseArrayLayer", "arrayLayerCount", "aspect", "usage")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUTextureViewDescriptor(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUTextureViewDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.format = other.format
			this.dimension = other.dimension
			this.baseMipLevel = other.baseMipLevel
			this.mipLevelCount = other.mipLevelCount
			this.baseArrayLayer = other.baseArrayLayer
			this.arrayLayerCount = other.arrayLayerCount
			this.aspect = other.aspect
			this.usage = other.usage
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUTextureViewDescriptor(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUTextureViewDescriptor) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.label = other.label
			this.format = other.format
			this.dimension = other.dimension
			this.baseMipLevel = other.baseMipLevel
			this.mipLevelCount = other.mipLevelCount
			this.baseArrayLayer = other.baseArrayLayer
			this.arrayLayerCount = other.arrayLayerCount
			this.aspect = other.aspect
			this.usage = other.usage
		}
	}
}

sealed class WGPUVertexAttribute(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var format: Int = 0
	@JvmField var offset: Long = 0L
	@JvmField var shaderLocation: Int = 0
	override fun getFieldOrder() = listOf("format", "offset", "shaderLocation")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexAttribute(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUVertexAttribute) : this(other.pointer) {
			this.format = other.format
			this.offset = other.offset
			this.shaderLocation = other.shaderLocation
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexAttribute(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUVertexAttribute) : this(other.pointer) {
			this.format = other.format
			this.offset = other.offset
			this.shaderLocation = other.shaderLocation
		}
	}
}

sealed class WGPUVertexBufferLayout(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var stepMode: Int = 0
	@JvmField var arrayStride: Long = 0L
	@JvmField var attributeCount: Long = 0L
	@JvmField var attributes: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("stepMode", "arrayStride", "attributeCount", "attributes")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUVertexBufferLayout(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUVertexBufferLayout) : this(other.pointer) {
			this.stepMode = other.stepMode
			this.arrayStride = other.arrayStride
			this.attributeCount = other.attributeCount
			this.attributes = other.attributes
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUVertexBufferLayout(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUVertexBufferLayout) : this(other.pointer) {
			this.stepMode = other.stepMode
			this.arrayStride = other.arrayStride
			this.attributeCount = other.attributeCount
			this.attributes = other.attributes
		}
	}
}

sealed class WGPUInstanceExtras(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var chain: WGPUChainedStruct.ByValue = WGPUChainedStruct.ByValue()
	@JvmField var backends: Long = 0L
	@JvmField var flags: Long = 0L
	@JvmField var dx12ShaderCompiler: Int = 0
	@JvmField var gles3MinorVersion: Int = 0
	@JvmField var dxilPath: WGPUStringView.ByValue = WGPUStringView.ByValue()
	@JvmField var dxcPath: WGPUStringView.ByValue = WGPUStringView.ByValue()
	override fun getFieldOrder() = listOf("chain", "backends", "flags", "dx12ShaderCompiler", "gles3MinorVersion", "dxilPath", "dxcPath")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceExtras(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUInstanceExtras) : this(other.pointer) {
			this.chain = other.chain
			this.backends = other.backends
			this.flags = other.flags
			this.dx12ShaderCompiler = other.dx12ShaderCompiler
			this.gles3MinorVersion = other.gles3MinorVersion
			this.dxilPath = other.dxilPath
			this.dxcPath = other.dxcPath
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUInstanceExtras(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUInstanceExtras) : this(other.pointer) {
			this.chain = other.chain
			this.backends = other.backends
			this.flags = other.flags
			this.dx12ShaderCompiler = other.dx12ShaderCompiler
			this.gles3MinorVersion = other.gles3MinorVersion
			this.dxilPath = other.dxilPath
			this.dxcPath = other.dxcPath
		}
	}
}

sealed class WGPUChainedStructOut(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var next: WGPUChainedStructOut.ByReference?? = null
	@JvmField var sType: Int = 0
	override fun getFieldOrder() = listOf("next", "sType")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStructOut(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUChainedStructOut) : this(other.pointer) {
			this.next = other.next
			this.sType = other.sType
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUChainedStructOut(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUChainedStructOut) : this(other.pointer) {
			this.next = other.next
			this.sType = other.sType
		}
	}
}

sealed class WGPUBufferMapCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUBufferMapCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUBufferMapCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUBufferMapCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUBufferMapCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUCompilationInfoCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfoCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCompilationInfoCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCompilationInfoCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCompilationInfoCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUCreateComputePipelineAsyncCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCreateComputePipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCreateComputePipelineAsyncCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCreateComputePipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCreateComputePipelineAsyncCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUCreateRenderPipelineAsyncCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUCreateRenderPipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUCreateRenderPipelineAsyncCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUCreateRenderPipelineAsyncCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUCreateRenderPipelineAsyncCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUPopErrorScopeCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUPopErrorScopeCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUPopErrorScopeCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUPopErrorScopeCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUPopErrorScopeCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPUQueueWorkDoneCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPUQueueWorkDoneCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPUQueueWorkDoneCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPUQueueWorkDoneCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPUQueueWorkDoneCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPURequestAdapterCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURequestAdapterCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestAdapterCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURequestAdapterCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

sealed class WGPURequestDeviceCallbackInfo(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer) {
	@JvmField var nextInChain: WGPUChainedStruct.ByReference?? = null
	@JvmField var mode: Int = 0
	@JvmField var callback: com.sun.jna.Callback? = null
	@JvmField var userdata1: com.sun.jna.Pointer? = null
	@JvmField var userdata2: com.sun.jna.Pointer? = null
	override fun getFieldOrder() = listOf("nextInChain", "mode", "callback", "userdata1", "userdata2")

	class ByReference(pointer: com.sun.jna.Pointer? = null) : WGPURequestDeviceCallbackInfo(pointer), com.sun.jna.Structure.ByReference {
		constructor(other: WGPURequestDeviceCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}

	class ByValue(pointer: com.sun.jna.Pointer? = null) : WGPURequestDeviceCallbackInfo(pointer), com.sun.jna.Structure.ByValue {
		constructor(other: WGPURequestDeviceCallbackInfo) : this(other.pointer) {
			this.nextInChain = other.nextInChain
			this.mode = other.mode
			this.callback = other.callback
			this.userdata1 = other.userdata1
			this.userdata2 = other.userdata2
		}
	}
}

