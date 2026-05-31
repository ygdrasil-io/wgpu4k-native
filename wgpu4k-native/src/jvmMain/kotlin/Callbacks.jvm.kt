// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.wgpu

import io.ygdrasil.kffi.Callback
import io.ygdrasil.kffi.CString
import io.ygdrasil.kffi.CallbackHolder
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.NativeAddress

actual fun interface WGPUBufferMapCallback : Callback {
	actual fun invoke(status: WGPUMapAsyncStatus, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> {
			val function = object : Function {
				override fun apply(status: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUCompilationInfoCallback : Callback {
	actual fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, compilationInfo: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCompilationInfoCallback): CallbackHolder<WGPUCompilationInfoCallback> {
			val function = object : Function {
				override fun apply(status: Int, compilationInfo: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), compilationInfo.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUCompilationInfo(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUCreateComputePipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, pipeline: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateComputePipelineAsyncCallback): CallbackHolder<WGPUCreateComputePipelineAsyncCallback> {
			val function = object : Function {
				override fun apply(status: Int, pipeline: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), pipeline.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUComputePipeline(it) }, message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_POINTER,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, pipeline: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateRenderPipelineAsyncCallback): CallbackHolder<WGPUCreateRenderPipelineAsyncCallback> {
			val function = object : Function {
				override fun apply(status: Int, pipeline: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), pipeline.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPURenderPipeline(it) }, message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_POINTER,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUDeviceLostCallback : Callback {
	actual fun invoke(device: WGPUDevice?, reason: WGPUDeviceLostReason, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(device: java.lang.foreign.MemorySegment, reason: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUDeviceLostCallback): CallbackHolder<WGPUDeviceLostCallback> {
			val function = object : Function {
				override fun apply(device: java.lang.foreign.MemorySegment, reason: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(device.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUDevice(it) }, reason.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUPopErrorScopeCallback : Callback {
	actual fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, type: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUPopErrorScopeCallback): CallbackHolder<WGPUPopErrorScopeCallback> {
			val function = object : Function {
				override fun apply(status: Int, type: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), type.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUQueueWorkDoneCallback : Callback {
	actual fun invoke(status: WGPUQueueWorkDoneStatus, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUQueueWorkDoneCallback): CallbackHolder<WGPUQueueWorkDoneCallback> {
			val function = object : Function {
				override fun apply(status: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPURequestAdapterCallback : Callback {
	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, adapter: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> {
			val function = object : Function {
				override fun apply(status: Int, adapter: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), adapter.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUAdapter(it) }, message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_POINTER,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPURequestDeviceCallback : Callback {
	actual fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice?, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(status: Int, device: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestDeviceCallback): CallbackHolder<WGPURequestDeviceCallback> {
			val function = object : Function {
				override fun apply(status: Int, device: java.lang.foreign.MemorySegment, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(status.toUInt(), device.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUDevice(it) }, message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			io.ygdrasil.kffi.C_POINTER,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPUUncapturedErrorCallback : Callback {
	actual fun invoke(device: WGPUDevice?, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress?, userdata2: NativeAddress?)
	interface Function {
		fun apply(device: java.lang.foreign.MemorySegment, type: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUUncapturedErrorCallback): CallbackHolder<WGPUUncapturedErrorCallback> {
			val function = object : Function {
				override fun apply(device: java.lang.foreign.MemorySegment, type: Int, message: java.lang.foreign.MemorySegment, userdata1: java.lang.foreign.MemorySegment, userdata2: java.lang.foreign.MemorySegment) {
					callback.invoke(device.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUDevice(it) }, type.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual fun interface WGPULogCallback : Callback {
	actual fun invoke(level: WGPULogLevel, message: WGPUStringView?, userdata: NativeAddress?)
	interface Function {
		fun apply(level: Int, message: java.lang.foreign.MemorySegment, userdata: java.lang.foreign.MemorySegment)
	}
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPULogCallback): CallbackHolder<WGPULogCallback> {
			val function = object : Function {
				override fun apply(level: Int, message: java.lang.foreign.MemorySegment, userdata: java.lang.foreign.MemorySegment) {
					callback.invoke(level.toUInt(), message.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let { java.lang.foreign.MemorySegment.ofAddress(it.address()).reinterpret(it.byteSize()) }?.let(::NativeAddress)?.let { WGPUStringView(it) }, userdata.let(::NativeAddress))
				}
			}
			return java.lang.foreign.Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)
		}
		private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(
			io.ygdrasil.kffi.C_INT,
			WGPUStringView.layout,
			io.ygdrasil.kffi.C_POINTER,
		)
		private val handler: java.lang.invoke.MethodHandle = io.ygdrasil.kffi.upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

