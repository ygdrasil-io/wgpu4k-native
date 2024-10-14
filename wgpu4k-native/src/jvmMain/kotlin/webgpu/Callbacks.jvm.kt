// This file has been generated DO NOT EDIT !!!
package webgpu

import ffi.C_INT
import ffi.C_POINTER
import ffi.Callback
import ffi.CallbackHolder
import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.upcallHandle
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.invoke.MethodHandle

actual interface WGPUBufferMapCallback : Callback {
	actual fun invoke(status: WGPUMapAsyncStatus, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUBufferMapCallback): CallbackHolder<WGPUBufferMapCallback> = TODO()
	}
}

actual interface WGPUCompilationInfoCallback : Callback {
	actual fun invoke(status: WGPUCompilationInfoRequestStatus, compilationInfo: WGPUCompilationInfo?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCompilationInfoCallback): CallbackHolder<WGPUCompilationInfoCallback> = TODO()
	}
}

actual interface WGPUCreateComputePipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateComputePipelineAsyncCallback): CallbackHolder<WGPUCreateComputePipelineAsyncCallback> = TODO()
	}
}

actual interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	actual fun invoke(status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUCreateRenderPipelineAsyncCallback): CallbackHolder<WGPUCreateRenderPipelineAsyncCallback> = TODO()
	}
}

actual interface WGPUDeviceLostCallback : Callback {
	actual fun invoke(device: WGPUDevice?, reason: WGPUDeviceLostReason, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUDeviceLostCallback): CallbackHolder<WGPUDeviceLostCallback> = TODO()
	}
}

actual interface WGPUPopErrorScopeCallback : Callback {
	actual fun invoke(status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUPopErrorScopeCallback): CallbackHolder<WGPUPopErrorScopeCallback> = TODO()
	}
}

actual interface WGPUQueueWorkDoneCallback : Callback {
	actual fun invoke(status: WGPUQueueWorkDoneStatus, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUQueueWorkDoneCallback): CallbackHolder<WGPUQueueWorkDoneCallback> = TODO()
	}
}

actual interface WGPURequestAdapterCallback : Callback {

	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)

	interface Function {
		fun apply(status: Int, adapter: MemorySegment, message: MemorySegment, userdata1: MemorySegment, userdata2: MemorySegment)
	}


	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> {
			val function = object : Function {
				override fun apply(
					status: Int,
					adapter: MemorySegment,
					message: MemorySegment,
					userdata1: MemorySegment,
					userdata2: MemorySegment
				) {
					println("adapter $adapter, message $message, userdata1 $userdata1, userdata2 $userdata2")
					callback.invoke(status.toUInt(), adapter.let(::NativeAddress).let(::WGPUAdapter), message.let(::NativeAddress).let(::WGPUStringView), userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}

			return Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)

		}

		private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(
			C_INT,
			C_POINTER,
			WGPUStringView.LAYOUT,
			C_POINTER,
			C_POINTER,
		)

		private val handler: MethodHandle = upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}

actual interface WGPURequestDeviceCallback : Callback {
	actual fun invoke(status: WGPURequestDeviceStatus, device: WGPUDevice?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestDeviceCallback): CallbackHolder<WGPURequestDeviceCallback> = TODO()
	}
}

actual interface WGPUUncapturedErrorCallback : Callback {
	actual fun invoke(device: WGPUDevice?, type: WGPUErrorType, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)
	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPUUncapturedErrorCallback): CallbackHolder<WGPUUncapturedErrorCallback> = TODO()
	}
}

