// This file has been generated DO NOT EDIT !!!
package webgpu

import java.lang.foreign.MemorySegment
import java.lang.foreign.Linker
import ffi.findOrThrow
import ffi.C_POINTER
import ffi.C_INT
import ffi.C_LONG
import ffi.C_FLOAT
import ffi.NativeAddress
import java.lang.foreign.FunctionDescriptor

object Functions {

	fun wgpuCreateInstance(descriptor: NativeAddress): NativeAddress {
		return wgpuCreateInstanceHandler.invokeExact(descriptor) as NativeAddress
	}
	private val wgpuCreateInstanceHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER
		)
	private val wgpuCreateInstanceHandlerAddress = findOrThrow("wgpuCreateInstance")
	private val wgpuCreateInstanceHandler = Linker.nativeLinker().downcallHandle(wgpuCreateInstanceHandlerAddress, wgpuCreateInstanceHandlerDescription)

	fun wgpuGetInstanceFeatures(features: NativeAddress): Unit {
		return wgpuGetInstanceFeaturesHandler.invokeExact(features) as Unit
	}
	private val wgpuGetInstanceFeaturesHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuGetInstanceFeaturesHandlerAddress = findOrThrow("wgpuGetInstanceFeatures")
	private val wgpuGetInstanceFeaturesHandler = Linker.nativeLinker().downcallHandle(wgpuGetInstanceFeaturesHandlerAddress, wgpuGetInstanceFeaturesHandlerDescription)

	fun wgpuAdapterGetLimits(handler: NativeAddress, limits: NativeAddress): Boolean {
		return wgpuAdapterGetLimitsHandler.invokeExact(handler, limits) as Boolean
	}
	private val wgpuAdapterGetLimitsHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_POINTER
		)
	private val wgpuAdapterGetLimitsHandlerAddress = findOrThrow("wgpuAdapterGetLimits")
	private val wgpuAdapterGetLimitsHandler = Linker.nativeLinker().downcallHandle(wgpuAdapterGetLimitsHandlerAddress, wgpuAdapterGetLimitsHandlerDescription)

	fun wgpuAdapterHasFeature(handler: NativeAddress, feature: UInt): Boolean {
		return wgpuAdapterHasFeatureHandler.invokeExact(handler, feature) as Boolean
	}
	private val wgpuAdapterHasFeatureHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_INT
		)
	private val wgpuAdapterHasFeatureHandlerAddress = findOrThrow("wgpuAdapterHasFeature")
	private val wgpuAdapterHasFeatureHandler = Linker.nativeLinker().downcallHandle(wgpuAdapterHasFeatureHandlerAddress, wgpuAdapterHasFeatureHandlerDescription)

	fun wgpuAdapterEnumerateFeatures(handler: NativeAddress, features: UInt): ULong {
		return wgpuAdapterEnumerateFeaturesHandler.invokeExact(handler, features) as ULong
	}
	private val wgpuAdapterEnumerateFeaturesHandlerDescription = FunctionDescriptor.of(
			C_LONG,
			C_POINTER,
			C_INT
		)
	private val wgpuAdapterEnumerateFeaturesHandlerAddress = findOrThrow("wgpuAdapterEnumerateFeatures")
	private val wgpuAdapterEnumerateFeaturesHandler = Linker.nativeLinker().downcallHandle(wgpuAdapterEnumerateFeaturesHandlerAddress, wgpuAdapterEnumerateFeaturesHandlerDescription)

	fun wgpuAdapterGetInfo(handler: NativeAddress, info: NativeAddress): Unit {
		return wgpuAdapterGetInfoHandler.invokeExact(handler, info) as Unit
	}
	private val wgpuAdapterGetInfoHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuAdapterGetInfoHandlerAddress = findOrThrow("wgpuAdapterGetInfo")
	private val wgpuAdapterGetInfoHandler = Linker.nativeLinker().downcallHandle(wgpuAdapterGetInfoHandlerAddress, wgpuAdapterGetInfoHandlerDescription)

	fun wgpuAdapterRequestDevice(handler: NativeAddress, descriptor: NativeAddress): Unit {
		return wgpuAdapterRequestDeviceHandler.invokeExact(handler, descriptor) as Unit
	}
	private val wgpuAdapterRequestDeviceHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuAdapterRequestDeviceHandlerAddress = findOrThrow("wgpuAdapterRequestDevice")
	private val wgpuAdapterRequestDeviceHandler = Linker.nativeLinker().downcallHandle(wgpuAdapterRequestDeviceHandlerAddress, wgpuAdapterRequestDeviceHandlerDescription)

	fun wgpuBindGroupSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuBindGroupSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuBindGroupSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuBindGroupSetLabelHandlerAddress = findOrThrow("wgpuBindGroupSetLabel")
	private val wgpuBindGroupSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuBindGroupSetLabelHandlerAddress, wgpuBindGroupSetLabelHandlerDescription)

	fun wgpuBindGroupLayoutSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuBindGroupLayoutSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuBindGroupLayoutSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuBindGroupLayoutSetLabelHandlerAddress = findOrThrow("wgpuBindGroupLayoutSetLabel")
	private val wgpuBindGroupLayoutSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuBindGroupLayoutSetLabelHandlerAddress, wgpuBindGroupLayoutSetLabelHandlerDescription)

	fun wgpuBufferMapAsync(handler: NativeAddress, mode: ULong, offset: ULong, size: ULong): Unit {
		return wgpuBufferMapAsyncHandler.invokeExact(handler, mode, offset, size) as Unit
	}
	private val wgpuBufferMapAsyncHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_LONG,
			C_LONG,
			C_LONG
		)
	private val wgpuBufferMapAsyncHandlerAddress = findOrThrow("wgpuBufferMapAsync")
	private val wgpuBufferMapAsyncHandler = Linker.nativeLinker().downcallHandle(wgpuBufferMapAsyncHandlerAddress, wgpuBufferMapAsyncHandlerDescription)

	fun wgpuBufferGetMappedRange(handler: NativeAddress, offset: ULong, size: ULong): Unit {
		return wgpuBufferGetMappedRangeHandler.invokeExact(handler, offset, size) as Unit
	}
	private val wgpuBufferGetMappedRangeHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuBufferGetMappedRangeHandlerAddress = findOrThrow("wgpuBufferGetMappedRange")
	private val wgpuBufferGetMappedRangeHandler = Linker.nativeLinker().downcallHandle(wgpuBufferGetMappedRangeHandlerAddress, wgpuBufferGetMappedRangeHandlerDescription)

	fun wgpuBufferGetConstMappedRange(handler: NativeAddress, offset: ULong, size: ULong): Unit {
		return wgpuBufferGetConstMappedRangeHandler.invokeExact(handler, offset, size) as Unit
	}
	private val wgpuBufferGetConstMappedRangeHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuBufferGetConstMappedRangeHandlerAddress = findOrThrow("wgpuBufferGetConstMappedRange")
	private val wgpuBufferGetConstMappedRangeHandler = Linker.nativeLinker().downcallHandle(wgpuBufferGetConstMappedRangeHandlerAddress, wgpuBufferGetConstMappedRangeHandlerDescription)

	fun wgpuBufferSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuBufferSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuBufferSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuBufferSetLabelHandlerAddress = findOrThrow("wgpuBufferSetLabel")
	private val wgpuBufferSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuBufferSetLabelHandlerAddress, wgpuBufferSetLabelHandlerDescription)

	fun wgpuBufferGetUsage(handler: NativeAddress): ULong {
		return wgpuBufferGetUsageHandler.invokeExact(handler) as ULong
	}
	private val wgpuBufferGetUsageHandlerDescription = FunctionDescriptor.of(
			C_LONG,
			C_POINTER
		)
	private val wgpuBufferGetUsageHandlerAddress = findOrThrow("wgpuBufferGetUsage")
	private val wgpuBufferGetUsageHandler = Linker.nativeLinker().downcallHandle(wgpuBufferGetUsageHandlerAddress, wgpuBufferGetUsageHandlerDescription)

	fun wgpuBufferGetSize(handler: NativeAddress): ULong {
		return wgpuBufferGetSizeHandler.invokeExact(handler) as ULong
	}
	private val wgpuBufferGetSizeHandlerDescription = FunctionDescriptor.of(
			C_LONG,
			C_POINTER
		)
	private val wgpuBufferGetSizeHandlerAddress = findOrThrow("wgpuBufferGetSize")
	private val wgpuBufferGetSizeHandler = Linker.nativeLinker().downcallHandle(wgpuBufferGetSizeHandlerAddress, wgpuBufferGetSizeHandlerDescription)

	fun wgpuBufferGetMapState(handler: NativeAddress): UInt {
		return wgpuBufferGetMapStateHandler.invokeExact(handler) as UInt
	}
	private val wgpuBufferGetMapStateHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuBufferGetMapStateHandlerAddress = findOrThrow("wgpuBufferGetMapState")
	private val wgpuBufferGetMapStateHandler = Linker.nativeLinker().downcallHandle(wgpuBufferGetMapStateHandlerAddress, wgpuBufferGetMapStateHandlerDescription)

	fun wgpuBufferUnmap(handler: NativeAddress): Unit {
		return wgpuBufferUnmapHandler.invokeExact(handler) as Unit
	}
	private val wgpuBufferUnmapHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuBufferUnmapHandlerAddress = findOrThrow("wgpuBufferUnmap")
	private val wgpuBufferUnmapHandler = Linker.nativeLinker().downcallHandle(wgpuBufferUnmapHandlerAddress, wgpuBufferUnmapHandlerDescription)

	fun wgpuBufferDestroy(handler: NativeAddress): Unit {
		return wgpuBufferDestroyHandler.invokeExact(handler) as Unit
	}
	private val wgpuBufferDestroyHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuBufferDestroyHandlerAddress = findOrThrow("wgpuBufferDestroy")
	private val wgpuBufferDestroyHandler = Linker.nativeLinker().downcallHandle(wgpuBufferDestroyHandlerAddress, wgpuBufferDestroyHandlerDescription)

	fun wgpuCommandBufferSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuCommandBufferSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuCommandBufferSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandBufferSetLabelHandlerAddress = findOrThrow("wgpuCommandBufferSetLabel")
	private val wgpuCommandBufferSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuCommandBufferSetLabelHandlerAddress, wgpuCommandBufferSetLabelHandlerDescription)

	fun wgpuCommandEncoderFinish(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuCommandEncoderFinishHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuCommandEncoderFinishHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderFinishHandlerAddress = findOrThrow("wgpuCommandEncoderFinish")
	private val wgpuCommandEncoderFinishHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderFinishHandlerAddress, wgpuCommandEncoderFinishHandlerDescription)

	fun wgpuCommandEncoderBeginComputePass(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuCommandEncoderBeginComputePassHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuCommandEncoderBeginComputePassHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderBeginComputePassHandlerAddress = findOrThrow("wgpuCommandEncoderBeginComputePass")
	private val wgpuCommandEncoderBeginComputePassHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderBeginComputePassHandlerAddress, wgpuCommandEncoderBeginComputePassHandlerDescription)

	fun wgpuCommandEncoderBeginRenderPass(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuCommandEncoderBeginRenderPassHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuCommandEncoderBeginRenderPassHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderBeginRenderPassHandlerAddress = findOrThrow("wgpuCommandEncoderBeginRenderPass")
	private val wgpuCommandEncoderBeginRenderPassHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderBeginRenderPassHandlerAddress, wgpuCommandEncoderBeginRenderPassHandlerDescription)

	fun wgpuCommandEncoderCopyBufferToBuffer(handler: NativeAddress, source: NativeAddress, sourceOffset: ULong, destination: NativeAddress, destinationOffset: ULong, size: ULong): Unit {
		return wgpuCommandEncoderCopyBufferToBufferHandler.invokeExact(handler, source, sourceOffset, destination, destinationOffset, size) as Unit
	}
	private val wgpuCommandEncoderCopyBufferToBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG,
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuCommandEncoderCopyBufferToBufferHandlerAddress = findOrThrow("wgpuCommandEncoderCopyBufferToBuffer")
	private val wgpuCommandEncoderCopyBufferToBufferHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderCopyBufferToBufferHandlerAddress, wgpuCommandEncoderCopyBufferToBufferHandlerDescription)

	fun wgpuCommandEncoderCopyBufferToTexture(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit {
		return wgpuCommandEncoderCopyBufferToTextureHandler.invokeExact(handler, source, destination, copySize) as Unit
	}
	private val wgpuCommandEncoderCopyBufferToTextureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderCopyBufferToTextureHandlerAddress = findOrThrow("wgpuCommandEncoderCopyBufferToTexture")
	private val wgpuCommandEncoderCopyBufferToTextureHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderCopyBufferToTextureHandlerAddress, wgpuCommandEncoderCopyBufferToTextureHandlerDescription)

	fun wgpuCommandEncoderCopyTextureToBuffer(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit {
		return wgpuCommandEncoderCopyTextureToBufferHandler.invokeExact(handler, source, destination, copySize) as Unit
	}
	private val wgpuCommandEncoderCopyTextureToBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderCopyTextureToBufferHandlerAddress = findOrThrow("wgpuCommandEncoderCopyTextureToBuffer")
	private val wgpuCommandEncoderCopyTextureToBufferHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderCopyTextureToBufferHandlerAddress, wgpuCommandEncoderCopyTextureToBufferHandlerDescription)

	fun wgpuCommandEncoderCopyTextureToTexture(handler: NativeAddress, source: NativeAddress, destination: NativeAddress, copySize: NativeAddress): Unit {
		return wgpuCommandEncoderCopyTextureToTextureHandler.invokeExact(handler, source, destination, copySize) as Unit
	}
	private val wgpuCommandEncoderCopyTextureToTextureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderCopyTextureToTextureHandlerAddress = findOrThrow("wgpuCommandEncoderCopyTextureToTexture")
	private val wgpuCommandEncoderCopyTextureToTextureHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderCopyTextureToTextureHandlerAddress, wgpuCommandEncoderCopyTextureToTextureHandlerDescription)

	fun wgpuCommandEncoderClearBuffer(handler: NativeAddress, buffer: NativeAddress, offset: ULong, size: ULong): Unit {
		return wgpuCommandEncoderClearBufferHandler.invokeExact(handler, buffer, offset, size) as Unit
	}
	private val wgpuCommandEncoderClearBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuCommandEncoderClearBufferHandlerAddress = findOrThrow("wgpuCommandEncoderClearBuffer")
	private val wgpuCommandEncoderClearBufferHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderClearBufferHandlerAddress, wgpuCommandEncoderClearBufferHandlerDescription)

	fun wgpuCommandEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit {
		return wgpuCommandEncoderInsertDebugMarkerHandler.invokeExact(handler, markerLabel) as Unit
	}
	private val wgpuCommandEncoderInsertDebugMarkerHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderInsertDebugMarkerHandlerAddress = findOrThrow("wgpuCommandEncoderInsertDebugMarker")
	private val wgpuCommandEncoderInsertDebugMarkerHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderInsertDebugMarkerHandlerAddress, wgpuCommandEncoderInsertDebugMarkerHandlerDescription)

	fun wgpuCommandEncoderPopDebugGroup(handler: NativeAddress): Unit {
		return wgpuCommandEncoderPopDebugGroupHandler.invokeExact(handler) as Unit
	}
	private val wgpuCommandEncoderPopDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuCommandEncoderPopDebugGroupHandlerAddress = findOrThrow("wgpuCommandEncoderPopDebugGroup")
	private val wgpuCommandEncoderPopDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderPopDebugGroupHandlerAddress, wgpuCommandEncoderPopDebugGroupHandlerDescription)

	fun wgpuCommandEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit {
		return wgpuCommandEncoderPushDebugGroupHandler.invokeExact(handler, groupLabel) as Unit
	}
	private val wgpuCommandEncoderPushDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderPushDebugGroupHandlerAddress = findOrThrow("wgpuCommandEncoderPushDebugGroup")
	private val wgpuCommandEncoderPushDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderPushDebugGroupHandlerAddress, wgpuCommandEncoderPushDebugGroupHandlerDescription)

	fun wgpuCommandEncoderResolveQuerySet(handler: NativeAddress, querySet: NativeAddress, firstQuery: UInt, queryCount: UInt, destination: NativeAddress, destinationOffset: ULong): Unit {
		return wgpuCommandEncoderResolveQuerySetHandler.invokeExact(handler, querySet, firstQuery, queryCount, destination, destinationOffset) as Unit
	}
	private val wgpuCommandEncoderResolveQuerySetHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_INT,
			C_INT,
			C_POINTER,
			C_LONG
		)
	private val wgpuCommandEncoderResolveQuerySetHandlerAddress = findOrThrow("wgpuCommandEncoderResolveQuerySet")
	private val wgpuCommandEncoderResolveQuerySetHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderResolveQuerySetHandlerAddress, wgpuCommandEncoderResolveQuerySetHandlerDescription)

	fun wgpuCommandEncoderWriteTimestamp(handler: NativeAddress, querySet: NativeAddress, queryIndex: UInt): Unit {
		return wgpuCommandEncoderWriteTimestampHandler.invokeExact(handler, querySet, queryIndex) as Unit
	}
	private val wgpuCommandEncoderWriteTimestampHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_INT
		)
	private val wgpuCommandEncoderWriteTimestampHandlerAddress = findOrThrow("wgpuCommandEncoderWriteTimestamp")
	private val wgpuCommandEncoderWriteTimestampHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderWriteTimestampHandlerAddress, wgpuCommandEncoderWriteTimestampHandlerDescription)

	fun wgpuCommandEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuCommandEncoderSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuCommandEncoderSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuCommandEncoderSetLabelHandlerAddress = findOrThrow("wgpuCommandEncoderSetLabel")
	private val wgpuCommandEncoderSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuCommandEncoderSetLabelHandlerAddress, wgpuCommandEncoderSetLabelHandlerDescription)

	fun wgpuComputePassEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit {
		return wgpuComputePassEncoderInsertDebugMarkerHandler.invokeExact(handler, markerLabel) as Unit
	}
	private val wgpuComputePassEncoderInsertDebugMarkerHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePassEncoderInsertDebugMarkerHandlerAddress = findOrThrow("wgpuComputePassEncoderInsertDebugMarker")
	private val wgpuComputePassEncoderInsertDebugMarkerHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderInsertDebugMarkerHandlerAddress, wgpuComputePassEncoderInsertDebugMarkerHandlerDescription)

	fun wgpuComputePassEncoderPopDebugGroup(handler: NativeAddress): Unit {
		return wgpuComputePassEncoderPopDebugGroupHandler.invokeExact(handler) as Unit
	}
	private val wgpuComputePassEncoderPopDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuComputePassEncoderPopDebugGroupHandlerAddress = findOrThrow("wgpuComputePassEncoderPopDebugGroup")
	private val wgpuComputePassEncoderPopDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderPopDebugGroupHandlerAddress, wgpuComputePassEncoderPopDebugGroupHandlerDescription)

	fun wgpuComputePassEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit {
		return wgpuComputePassEncoderPushDebugGroupHandler.invokeExact(handler, groupLabel) as Unit
	}
	private val wgpuComputePassEncoderPushDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePassEncoderPushDebugGroupHandlerAddress = findOrThrow("wgpuComputePassEncoderPushDebugGroup")
	private val wgpuComputePassEncoderPushDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderPushDebugGroupHandlerAddress, wgpuComputePassEncoderPushDebugGroupHandlerDescription)

	fun wgpuComputePassEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit {
		return wgpuComputePassEncoderSetPipelineHandler.invokeExact(handler, pipeline) as Unit
	}
	private val wgpuComputePassEncoderSetPipelineHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePassEncoderSetPipelineHandlerAddress = findOrThrow("wgpuComputePassEncoderSetPipeline")
	private val wgpuComputePassEncoderSetPipelineHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderSetPipelineHandlerAddress, wgpuComputePassEncoderSetPipelineHandlerDescription)

	fun wgpuComputePassEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit {
		return wgpuComputePassEncoderSetBindGroupHandler.invokeExact(handler, groupIndex, group, dynamicOffsets) as Unit
	}
	private val wgpuComputePassEncoderSetBindGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePassEncoderSetBindGroupHandlerAddress = findOrThrow("wgpuComputePassEncoderSetBindGroup")
	private val wgpuComputePassEncoderSetBindGroupHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderSetBindGroupHandlerAddress, wgpuComputePassEncoderSetBindGroupHandlerDescription)

	fun wgpuComputePassEncoderDispatchWorkgroups(handler: NativeAddress, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit {
		return wgpuComputePassEncoderDispatchWorkgroupsHandler.invokeExact(handler, workgroupCountX, workgroupCountY, workgroupCountZ) as Unit
	}
	private val wgpuComputePassEncoderDispatchWorkgroupsHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuComputePassEncoderDispatchWorkgroupsHandlerAddress = findOrThrow("wgpuComputePassEncoderDispatchWorkgroups")
	private val wgpuComputePassEncoderDispatchWorkgroupsHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderDispatchWorkgroupsHandlerAddress, wgpuComputePassEncoderDispatchWorkgroupsHandlerDescription)

	fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit {
		return wgpuComputePassEncoderDispatchWorkgroupsIndirectHandler.invokeExact(handler, indirectBuffer, indirectOffset) as Unit
	}
	private val wgpuComputePassEncoderDispatchWorkgroupsIndirectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG
		)
	private val wgpuComputePassEncoderDispatchWorkgroupsIndirectHandlerAddress = findOrThrow("wgpuComputePassEncoderDispatchWorkgroupsIndirect")
	private val wgpuComputePassEncoderDispatchWorkgroupsIndirectHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderDispatchWorkgroupsIndirectHandlerAddress, wgpuComputePassEncoderDispatchWorkgroupsIndirectHandlerDescription)

	fun wgpuComputePassEncoderEnd(handler: NativeAddress): Unit {
		return wgpuComputePassEncoderEndHandler.invokeExact(handler) as Unit
	}
	private val wgpuComputePassEncoderEndHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuComputePassEncoderEndHandlerAddress = findOrThrow("wgpuComputePassEncoderEnd")
	private val wgpuComputePassEncoderEndHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderEndHandlerAddress, wgpuComputePassEncoderEndHandlerDescription)

	fun wgpuComputePassEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuComputePassEncoderSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuComputePassEncoderSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePassEncoderSetLabelHandlerAddress = findOrThrow("wgpuComputePassEncoderSetLabel")
	private val wgpuComputePassEncoderSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuComputePassEncoderSetLabelHandlerAddress, wgpuComputePassEncoderSetLabelHandlerDescription)

	fun wgpuComputePipelineGetBindGroupLayout(handler: NativeAddress, groupIndex: UInt): NativeAddress {
		return wgpuComputePipelineGetBindGroupLayoutHandler.invokeExact(handler, groupIndex) as NativeAddress
	}
	private val wgpuComputePipelineGetBindGroupLayoutHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_INT
		)
	private val wgpuComputePipelineGetBindGroupLayoutHandlerAddress = findOrThrow("wgpuComputePipelineGetBindGroupLayout")
	private val wgpuComputePipelineGetBindGroupLayoutHandler = Linker.nativeLinker().downcallHandle(wgpuComputePipelineGetBindGroupLayoutHandlerAddress, wgpuComputePipelineGetBindGroupLayoutHandlerDescription)

	fun wgpuComputePipelineSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuComputePipelineSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuComputePipelineSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuComputePipelineSetLabelHandlerAddress = findOrThrow("wgpuComputePipelineSetLabel")
	private val wgpuComputePipelineSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuComputePipelineSetLabelHandlerAddress, wgpuComputePipelineSetLabelHandlerDescription)

	fun wgpuDeviceCreateBindGroup(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateBindGroupHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateBindGroupHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateBindGroupHandlerAddress = findOrThrow("wgpuDeviceCreateBindGroup")
	private val wgpuDeviceCreateBindGroupHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateBindGroupHandlerAddress, wgpuDeviceCreateBindGroupHandlerDescription)

	fun wgpuDeviceCreateBindGroupLayout(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateBindGroupLayoutHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateBindGroupLayoutHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateBindGroupLayoutHandlerAddress = findOrThrow("wgpuDeviceCreateBindGroupLayout")
	private val wgpuDeviceCreateBindGroupLayoutHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateBindGroupLayoutHandlerAddress, wgpuDeviceCreateBindGroupLayoutHandlerDescription)

	fun wgpuDeviceCreateBuffer(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateBufferHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateBufferHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateBufferHandlerAddress = findOrThrow("wgpuDeviceCreateBuffer")
	private val wgpuDeviceCreateBufferHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateBufferHandlerAddress, wgpuDeviceCreateBufferHandlerDescription)

	fun wgpuDeviceCreateCommandEncoder(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateCommandEncoderHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateCommandEncoderHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateCommandEncoderHandlerAddress = findOrThrow("wgpuDeviceCreateCommandEncoder")
	private val wgpuDeviceCreateCommandEncoderHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateCommandEncoderHandlerAddress, wgpuDeviceCreateCommandEncoderHandlerDescription)

	fun wgpuDeviceCreateComputePipeline(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateComputePipelineHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateComputePipelineHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateComputePipelineHandlerAddress = findOrThrow("wgpuDeviceCreateComputePipeline")
	private val wgpuDeviceCreateComputePipelineHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateComputePipelineHandlerAddress, wgpuDeviceCreateComputePipelineHandlerDescription)

	fun wgpuDeviceCreateComputePipelineAsync(handler: NativeAddress, descriptor: NativeAddress): Unit {
		return wgpuDeviceCreateComputePipelineAsyncHandler.invokeExact(handler, descriptor) as Unit
	}
	private val wgpuDeviceCreateComputePipelineAsyncHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateComputePipelineAsyncHandlerAddress = findOrThrow("wgpuDeviceCreateComputePipelineAsync")
	private val wgpuDeviceCreateComputePipelineAsyncHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateComputePipelineAsyncHandlerAddress, wgpuDeviceCreateComputePipelineAsyncHandlerDescription)

	fun wgpuDeviceCreatePipelineLayout(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreatePipelineLayoutHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreatePipelineLayoutHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreatePipelineLayoutHandlerAddress = findOrThrow("wgpuDeviceCreatePipelineLayout")
	private val wgpuDeviceCreatePipelineLayoutHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreatePipelineLayoutHandlerAddress, wgpuDeviceCreatePipelineLayoutHandlerDescription)

	fun wgpuDeviceCreateQuerySet(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateQuerySetHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateQuerySetHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateQuerySetHandlerAddress = findOrThrow("wgpuDeviceCreateQuerySet")
	private val wgpuDeviceCreateQuerySetHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateQuerySetHandlerAddress, wgpuDeviceCreateQuerySetHandlerDescription)

	fun wgpuDeviceCreateRenderPipelineAsync(handler: NativeAddress, descriptor: NativeAddress): Unit {
		return wgpuDeviceCreateRenderPipelineAsyncHandler.invokeExact(handler, descriptor) as Unit
	}
	private val wgpuDeviceCreateRenderPipelineAsyncHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateRenderPipelineAsyncHandlerAddress = findOrThrow("wgpuDeviceCreateRenderPipelineAsync")
	private val wgpuDeviceCreateRenderPipelineAsyncHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateRenderPipelineAsyncHandlerAddress, wgpuDeviceCreateRenderPipelineAsyncHandlerDescription)

	fun wgpuDeviceCreateRenderBundleEncoder(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateRenderBundleEncoderHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateRenderBundleEncoderHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateRenderBundleEncoderHandlerAddress = findOrThrow("wgpuDeviceCreateRenderBundleEncoder")
	private val wgpuDeviceCreateRenderBundleEncoderHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateRenderBundleEncoderHandlerAddress, wgpuDeviceCreateRenderBundleEncoderHandlerDescription)

	fun wgpuDeviceCreateRenderPipeline(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateRenderPipelineHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateRenderPipelineHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateRenderPipelineHandlerAddress = findOrThrow("wgpuDeviceCreateRenderPipeline")
	private val wgpuDeviceCreateRenderPipelineHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateRenderPipelineHandlerAddress, wgpuDeviceCreateRenderPipelineHandlerDescription)

	fun wgpuDeviceCreateSampler(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateSamplerHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateSamplerHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateSamplerHandlerAddress = findOrThrow("wgpuDeviceCreateSampler")
	private val wgpuDeviceCreateSamplerHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateSamplerHandlerAddress, wgpuDeviceCreateSamplerHandlerDescription)

	fun wgpuDeviceCreateShaderModule(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateShaderModuleHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateShaderModuleHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateShaderModuleHandlerAddress = findOrThrow("wgpuDeviceCreateShaderModule")
	private val wgpuDeviceCreateShaderModuleHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateShaderModuleHandlerAddress, wgpuDeviceCreateShaderModuleHandlerDescription)

	fun wgpuDeviceCreateTexture(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuDeviceCreateTextureHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuDeviceCreateTextureHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceCreateTextureHandlerAddress = findOrThrow("wgpuDeviceCreateTexture")
	private val wgpuDeviceCreateTextureHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceCreateTextureHandlerAddress, wgpuDeviceCreateTextureHandlerDescription)

	fun wgpuDeviceDestroy(handler: NativeAddress): Unit {
		return wgpuDeviceDestroyHandler.invokeExact(handler) as Unit
	}
	private val wgpuDeviceDestroyHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuDeviceDestroyHandlerAddress = findOrThrow("wgpuDeviceDestroy")
	private val wgpuDeviceDestroyHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceDestroyHandlerAddress, wgpuDeviceDestroyHandlerDescription)

	fun wgpuDeviceGetLimits(handler: NativeAddress, limits: NativeAddress): Boolean {
		return wgpuDeviceGetLimitsHandler.invokeExact(handler, limits) as Boolean
	}
	private val wgpuDeviceGetLimitsHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceGetLimitsHandlerAddress = findOrThrow("wgpuDeviceGetLimits")
	private val wgpuDeviceGetLimitsHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceGetLimitsHandlerAddress, wgpuDeviceGetLimitsHandlerDescription)

	fun wgpuDeviceHasFeature(handler: NativeAddress, feature: UInt): Boolean {
		return wgpuDeviceHasFeatureHandler.invokeExact(handler, feature) as Boolean
	}
	private val wgpuDeviceHasFeatureHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_INT
		)
	private val wgpuDeviceHasFeatureHandlerAddress = findOrThrow("wgpuDeviceHasFeature")
	private val wgpuDeviceHasFeatureHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceHasFeatureHandlerAddress, wgpuDeviceHasFeatureHandlerDescription)

	fun wgpuDeviceEnumerateFeatures(handler: NativeAddress, features: UInt): ULong {
		return wgpuDeviceEnumerateFeaturesHandler.invokeExact(handler, features) as ULong
	}
	private val wgpuDeviceEnumerateFeaturesHandlerDescription = FunctionDescriptor.of(
			C_LONG,
			C_POINTER,
			C_INT
		)
	private val wgpuDeviceEnumerateFeaturesHandlerAddress = findOrThrow("wgpuDeviceEnumerateFeatures")
	private val wgpuDeviceEnumerateFeaturesHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceEnumerateFeaturesHandlerAddress, wgpuDeviceEnumerateFeaturesHandlerDescription)

	fun wgpuDeviceGetQueue(handler: NativeAddress): NativeAddress {
		return wgpuDeviceGetQueueHandler.invokeExact(handler) as NativeAddress
	}
	private val wgpuDeviceGetQueueHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceGetQueueHandlerAddress = findOrThrow("wgpuDeviceGetQueue")
	private val wgpuDeviceGetQueueHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceGetQueueHandlerAddress, wgpuDeviceGetQueueHandlerDescription)

	fun wgpuDevicePushErrorScope(handler: NativeAddress, filter: UInt): Unit {
		return wgpuDevicePushErrorScopeHandler.invokeExact(handler, filter) as Unit
	}
	private val wgpuDevicePushErrorScopeHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT
		)
	private val wgpuDevicePushErrorScopeHandlerAddress = findOrThrow("wgpuDevicePushErrorScope")
	private val wgpuDevicePushErrorScopeHandler = Linker.nativeLinker().downcallHandle(wgpuDevicePushErrorScopeHandlerAddress, wgpuDevicePushErrorScopeHandlerDescription)

	fun wgpuDevicePopErrorScope(handler: NativeAddress): Unit {
		return wgpuDevicePopErrorScopeHandler.invokeExact(handler) as Unit
	}
	private val wgpuDevicePopErrorScopeHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuDevicePopErrorScopeHandlerAddress = findOrThrow("wgpuDevicePopErrorScope")
	private val wgpuDevicePopErrorScopeHandler = Linker.nativeLinker().downcallHandle(wgpuDevicePopErrorScopeHandlerAddress, wgpuDevicePopErrorScopeHandlerDescription)

	fun wgpuDeviceSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuDeviceSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuDeviceSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuDeviceSetLabelHandlerAddress = findOrThrow("wgpuDeviceSetLabel")
	private val wgpuDeviceSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuDeviceSetLabelHandlerAddress, wgpuDeviceSetLabelHandlerDescription)

	fun wgpuInstanceCreateSurface(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuInstanceCreateSurfaceHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuInstanceCreateSurfaceHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuInstanceCreateSurfaceHandlerAddress = findOrThrow("wgpuInstanceCreateSurface")
	private val wgpuInstanceCreateSurfaceHandler = Linker.nativeLinker().downcallHandle(wgpuInstanceCreateSurfaceHandlerAddress, wgpuInstanceCreateSurfaceHandlerDescription)

	fun wgpuInstanceHasWGSLLanguageFeature(handler: NativeAddress, feature: UInt): Boolean {
		return wgpuInstanceHasWGSLLanguageFeatureHandler.invokeExact(handler, feature) as Boolean
	}
	private val wgpuInstanceHasWGSLLanguageFeatureHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_INT
		)
	private val wgpuInstanceHasWGSLLanguageFeatureHandlerAddress = findOrThrow("wgpuInstanceHasWGSLLanguageFeature")
	private val wgpuInstanceHasWGSLLanguageFeatureHandler = Linker.nativeLinker().downcallHandle(wgpuInstanceHasWGSLLanguageFeatureHandlerAddress, wgpuInstanceHasWGSLLanguageFeatureHandlerDescription)

	fun wgpuInstanceProcessEvents(handler: NativeAddress): Unit {
		return wgpuInstanceProcessEventsHandler.invokeExact(handler) as Unit
	}
	private val wgpuInstanceProcessEventsHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuInstanceProcessEventsHandlerAddress = findOrThrow("wgpuInstanceProcessEvents")
	private val wgpuInstanceProcessEventsHandler = Linker.nativeLinker().downcallHandle(wgpuInstanceProcessEventsHandlerAddress, wgpuInstanceProcessEventsHandlerDescription)

	fun wgpuInstanceRequestAdapter(handler: NativeAddress, options: NativeAddress): Unit {
		return wgpuInstanceRequestAdapterHandler.invokeExact(handler, options) as Unit
	}
	private val wgpuInstanceRequestAdapterHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuInstanceRequestAdapterHandlerAddress = findOrThrow("wgpuInstanceRequestAdapter")
	private val wgpuInstanceRequestAdapterHandler = Linker.nativeLinker().downcallHandle(wgpuInstanceRequestAdapterHandlerAddress, wgpuInstanceRequestAdapterHandlerDescription)

	fun wgpuInstanceWaitAny(handler: NativeAddress, futureCount: ULong, futures: NativeAddress, timeoutNS: ULong): UInt {
		return wgpuInstanceWaitAnyHandler.invokeExact(handler, futureCount, futures, timeoutNS) as UInt
	}
	private val wgpuInstanceWaitAnyHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_LONG,
			C_POINTER,
			C_LONG
		)
	private val wgpuInstanceWaitAnyHandlerAddress = findOrThrow("wgpuInstanceWaitAny")
	private val wgpuInstanceWaitAnyHandler = Linker.nativeLinker().downcallHandle(wgpuInstanceWaitAnyHandlerAddress, wgpuInstanceWaitAnyHandlerDescription)

	fun wgpuPipelineLayoutSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuPipelineLayoutSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuPipelineLayoutSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuPipelineLayoutSetLabelHandlerAddress = findOrThrow("wgpuPipelineLayoutSetLabel")
	private val wgpuPipelineLayoutSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuPipelineLayoutSetLabelHandlerAddress, wgpuPipelineLayoutSetLabelHandlerDescription)

	fun wgpuQuerySetSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuQuerySetSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuQuerySetSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuQuerySetSetLabelHandlerAddress = findOrThrow("wgpuQuerySetSetLabel")
	private val wgpuQuerySetSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuQuerySetSetLabelHandlerAddress, wgpuQuerySetSetLabelHandlerDescription)

	fun wgpuQuerySetGetType(handler: NativeAddress): UInt {
		return wgpuQuerySetGetTypeHandler.invokeExact(handler) as UInt
	}
	private val wgpuQuerySetGetTypeHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuQuerySetGetTypeHandlerAddress = findOrThrow("wgpuQuerySetGetType")
	private val wgpuQuerySetGetTypeHandler = Linker.nativeLinker().downcallHandle(wgpuQuerySetGetTypeHandlerAddress, wgpuQuerySetGetTypeHandlerDescription)

	fun wgpuQuerySetGetCount(handler: NativeAddress): UInt {
		return wgpuQuerySetGetCountHandler.invokeExact(handler) as UInt
	}
	private val wgpuQuerySetGetCountHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuQuerySetGetCountHandlerAddress = findOrThrow("wgpuQuerySetGetCount")
	private val wgpuQuerySetGetCountHandler = Linker.nativeLinker().downcallHandle(wgpuQuerySetGetCountHandlerAddress, wgpuQuerySetGetCountHandlerDescription)

	fun wgpuQuerySetDestroy(handler: NativeAddress): Unit {
		return wgpuQuerySetDestroyHandler.invokeExact(handler) as Unit
	}
	private val wgpuQuerySetDestroyHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuQuerySetDestroyHandlerAddress = findOrThrow("wgpuQuerySetDestroy")
	private val wgpuQuerySetDestroyHandler = Linker.nativeLinker().downcallHandle(wgpuQuerySetDestroyHandlerAddress, wgpuQuerySetDestroyHandlerDescription)

	fun wgpuQueueSubmit(handler: NativeAddress, commands: NativeAddress): Unit {
		return wgpuQueueSubmitHandler.invokeExact(handler, commands) as Unit
	}
	private val wgpuQueueSubmitHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuQueueSubmitHandlerAddress = findOrThrow("wgpuQueueSubmit")
	private val wgpuQueueSubmitHandler = Linker.nativeLinker().downcallHandle(wgpuQueueSubmitHandlerAddress, wgpuQueueSubmitHandlerDescription)

	fun wgpuQueueOnSubmittedWorkDone(handler: NativeAddress): Unit {
		return wgpuQueueOnSubmittedWorkDoneHandler.invokeExact(handler) as Unit
	}
	private val wgpuQueueOnSubmittedWorkDoneHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuQueueOnSubmittedWorkDoneHandlerAddress = findOrThrow("wgpuQueueOnSubmittedWorkDone")
	private val wgpuQueueOnSubmittedWorkDoneHandler = Linker.nativeLinker().downcallHandle(wgpuQueueOnSubmittedWorkDoneHandlerAddress, wgpuQueueOnSubmittedWorkDoneHandlerDescription)

	fun wgpuQueueWriteBuffer(handler: NativeAddress, buffer: NativeAddress, bufferOffset: ULong, data: NativeAddress, size: ULong): Unit {
		return wgpuQueueWriteBufferHandler.invokeExact(handler, buffer, bufferOffset, data, size) as Unit
	}
	private val wgpuQueueWriteBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG,
			C_POINTER,
			C_LONG
		)
	private val wgpuQueueWriteBufferHandlerAddress = findOrThrow("wgpuQueueWriteBuffer")
	private val wgpuQueueWriteBufferHandler = Linker.nativeLinker().downcallHandle(wgpuQueueWriteBufferHandlerAddress, wgpuQueueWriteBufferHandlerDescription)

	fun wgpuQueueWriteTexture(handler: NativeAddress, destination: NativeAddress, data: NativeAddress, dataSize: ULong, dataLayout: NativeAddress, writeSize: NativeAddress): Unit {
		return wgpuQueueWriteTextureHandler.invokeExact(handler, destination, data, dataSize, dataLayout, writeSize) as Unit
	}
	private val wgpuQueueWriteTextureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_POINTER,
			C_LONG,
			C_POINTER,
			C_POINTER
		)
	private val wgpuQueueWriteTextureHandlerAddress = findOrThrow("wgpuQueueWriteTexture")
	private val wgpuQueueWriteTextureHandler = Linker.nativeLinker().downcallHandle(wgpuQueueWriteTextureHandlerAddress, wgpuQueueWriteTextureHandlerDescription)

	fun wgpuQueueSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuQueueSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuQueueSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuQueueSetLabelHandlerAddress = findOrThrow("wgpuQueueSetLabel")
	private val wgpuQueueSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuQueueSetLabelHandlerAddress, wgpuQueueSetLabelHandlerDescription)

	fun wgpuRenderBundleSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuRenderBundleSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuRenderBundleSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleSetLabelHandlerAddress = findOrThrow("wgpuRenderBundleSetLabel")
	private val wgpuRenderBundleSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleSetLabelHandlerAddress, wgpuRenderBundleSetLabelHandlerDescription)

	fun wgpuRenderBundleEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit {
		return wgpuRenderBundleEncoderSetPipelineHandler.invokeExact(handler, pipeline) as Unit
	}
	private val wgpuRenderBundleEncoderSetPipelineHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderSetPipelineHandlerAddress = findOrThrow("wgpuRenderBundleEncoderSetPipeline")
	private val wgpuRenderBundleEncoderSetPipelineHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderSetPipelineHandlerAddress, wgpuRenderBundleEncoderSetPipelineHandlerDescription)

	fun wgpuRenderBundleEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit {
		return wgpuRenderBundleEncoderSetBindGroupHandler.invokeExact(handler, groupIndex, group, dynamicOffsets) as Unit
	}
	private val wgpuRenderBundleEncoderSetBindGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderSetBindGroupHandlerAddress = findOrThrow("wgpuRenderBundleEncoderSetBindGroup")
	private val wgpuRenderBundleEncoderSetBindGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderSetBindGroupHandlerAddress, wgpuRenderBundleEncoderSetBindGroupHandlerDescription)

	fun wgpuRenderBundleEncoderDraw(handler: NativeAddress, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit {
		return wgpuRenderBundleEncoderDrawHandler.invokeExact(handler, vertexCount, instanceCount, firstVertex, firstInstance) as Unit
	}
	private val wgpuRenderBundleEncoderDrawHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuRenderBundleEncoderDrawHandlerAddress = findOrThrow("wgpuRenderBundleEncoderDraw")
	private val wgpuRenderBundleEncoderDrawHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderDrawHandlerAddress, wgpuRenderBundleEncoderDrawHandlerDescription)

	fun wgpuRenderBundleEncoderDrawIndexed(handler: NativeAddress, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit {
		return wgpuRenderBundleEncoderDrawIndexedHandler.invokeExact(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance) as Unit
	}
	private val wgpuRenderBundleEncoderDrawIndexedHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuRenderBundleEncoderDrawIndexedHandlerAddress = findOrThrow("wgpuRenderBundleEncoderDrawIndexed")
	private val wgpuRenderBundleEncoderDrawIndexedHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderDrawIndexedHandlerAddress, wgpuRenderBundleEncoderDrawIndexedHandlerDescription)

	fun wgpuRenderBundleEncoderDrawIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit {
		return wgpuRenderBundleEncoderDrawIndirectHandler.invokeExact(handler, indirectBuffer, indirectOffset) as Unit
	}
	private val wgpuRenderBundleEncoderDrawIndirectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG
		)
	private val wgpuRenderBundleEncoderDrawIndirectHandlerAddress = findOrThrow("wgpuRenderBundleEncoderDrawIndirect")
	private val wgpuRenderBundleEncoderDrawIndirectHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderDrawIndirectHandlerAddress, wgpuRenderBundleEncoderDrawIndirectHandlerDescription)

	fun wgpuRenderBundleEncoderDrawIndexedIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit {
		return wgpuRenderBundleEncoderDrawIndexedIndirectHandler.invokeExact(handler, indirectBuffer, indirectOffset) as Unit
	}
	private val wgpuRenderBundleEncoderDrawIndexedIndirectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG
		)
	private val wgpuRenderBundleEncoderDrawIndexedIndirectHandlerAddress = findOrThrow("wgpuRenderBundleEncoderDrawIndexedIndirect")
	private val wgpuRenderBundleEncoderDrawIndexedIndirectHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderDrawIndexedIndirectHandlerAddress, wgpuRenderBundleEncoderDrawIndexedIndirectHandlerDescription)

	fun wgpuRenderBundleEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit {
		return wgpuRenderBundleEncoderInsertDebugMarkerHandler.invokeExact(handler, markerLabel) as Unit
	}
	private val wgpuRenderBundleEncoderInsertDebugMarkerHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderInsertDebugMarkerHandlerAddress = findOrThrow("wgpuRenderBundleEncoderInsertDebugMarker")
	private val wgpuRenderBundleEncoderInsertDebugMarkerHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderInsertDebugMarkerHandlerAddress, wgpuRenderBundleEncoderInsertDebugMarkerHandlerDescription)

	fun wgpuRenderBundleEncoderPopDebugGroup(handler: NativeAddress): Unit {
		return wgpuRenderBundleEncoderPopDebugGroupHandler.invokeExact(handler) as Unit
	}
	private val wgpuRenderBundleEncoderPopDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuRenderBundleEncoderPopDebugGroupHandlerAddress = findOrThrow("wgpuRenderBundleEncoderPopDebugGroup")
	private val wgpuRenderBundleEncoderPopDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderPopDebugGroupHandlerAddress, wgpuRenderBundleEncoderPopDebugGroupHandlerDescription)

	fun wgpuRenderBundleEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit {
		return wgpuRenderBundleEncoderPushDebugGroupHandler.invokeExact(handler, groupLabel) as Unit
	}
	private val wgpuRenderBundleEncoderPushDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderPushDebugGroupHandlerAddress = findOrThrow("wgpuRenderBundleEncoderPushDebugGroup")
	private val wgpuRenderBundleEncoderPushDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderPushDebugGroupHandlerAddress, wgpuRenderBundleEncoderPushDebugGroupHandlerDescription)

	fun wgpuRenderBundleEncoderSetVertexBuffer(handler: NativeAddress, slot: UInt, buffer: NativeAddress, offset: ULong, size: ULong): Unit {
		return wgpuRenderBundleEncoderSetVertexBufferHandler.invokeExact(handler, slot, buffer, offset, size) as Unit
	}
	private val wgpuRenderBundleEncoderSetVertexBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuRenderBundleEncoderSetVertexBufferHandlerAddress = findOrThrow("wgpuRenderBundleEncoderSetVertexBuffer")
	private val wgpuRenderBundleEncoderSetVertexBufferHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderSetVertexBufferHandlerAddress, wgpuRenderBundleEncoderSetVertexBufferHandlerDescription)

	fun wgpuRenderBundleEncoderSetIndexBuffer(handler: NativeAddress, buffer: NativeAddress, format: UInt, offset: ULong, size: ULong): Unit {
		return wgpuRenderBundleEncoderSetIndexBufferHandler.invokeExact(handler, buffer, format, offset, size) as Unit
	}
	private val wgpuRenderBundleEncoderSetIndexBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_INT,
			C_LONG,
			C_LONG
		)
	private val wgpuRenderBundleEncoderSetIndexBufferHandlerAddress = findOrThrow("wgpuRenderBundleEncoderSetIndexBuffer")
	private val wgpuRenderBundleEncoderSetIndexBufferHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderSetIndexBufferHandlerAddress, wgpuRenderBundleEncoderSetIndexBufferHandlerDescription)

	fun wgpuRenderBundleEncoderFinish(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuRenderBundleEncoderFinishHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuRenderBundleEncoderFinishHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderFinishHandlerAddress = findOrThrow("wgpuRenderBundleEncoderFinish")
	private val wgpuRenderBundleEncoderFinishHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderFinishHandlerAddress, wgpuRenderBundleEncoderFinishHandlerDescription)

	fun wgpuRenderBundleEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuRenderBundleEncoderSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuRenderBundleEncoderSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderBundleEncoderSetLabelHandlerAddress = findOrThrow("wgpuRenderBundleEncoderSetLabel")
	private val wgpuRenderBundleEncoderSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuRenderBundleEncoderSetLabelHandlerAddress, wgpuRenderBundleEncoderSetLabelHandlerDescription)

	fun wgpuRenderPassEncoderSetPipeline(handler: NativeAddress, pipeline: NativeAddress): Unit {
		return wgpuRenderPassEncoderSetPipelineHandler.invokeExact(handler, pipeline) as Unit
	}
	private val wgpuRenderPassEncoderSetPipelineHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderSetPipelineHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetPipeline")
	private val wgpuRenderPassEncoderSetPipelineHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetPipelineHandlerAddress, wgpuRenderPassEncoderSetPipelineHandlerDescription)

	fun wgpuRenderPassEncoderSetBindGroup(handler: NativeAddress, groupIndex: UInt, group: NativeAddress, dynamicOffsets: NativeAddress): Unit {
		return wgpuRenderPassEncoderSetBindGroupHandler.invokeExact(handler, groupIndex, group, dynamicOffsets) as Unit
	}
	private val wgpuRenderPassEncoderSetBindGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderSetBindGroupHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetBindGroup")
	private val wgpuRenderPassEncoderSetBindGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetBindGroupHandlerAddress, wgpuRenderPassEncoderSetBindGroupHandlerDescription)

	fun wgpuRenderPassEncoderDraw(handler: NativeAddress, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit {
		return wgpuRenderPassEncoderDrawHandler.invokeExact(handler, vertexCount, instanceCount, firstVertex, firstInstance) as Unit
	}
	private val wgpuRenderPassEncoderDrawHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuRenderPassEncoderDrawHandlerAddress = findOrThrow("wgpuRenderPassEncoderDraw")
	private val wgpuRenderPassEncoderDrawHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderDrawHandlerAddress, wgpuRenderPassEncoderDrawHandlerDescription)

	fun wgpuRenderPassEncoderDrawIndexed(handler: NativeAddress, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit {
		return wgpuRenderPassEncoderDrawIndexedHandler.invokeExact(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance) as Unit
	}
	private val wgpuRenderPassEncoderDrawIndexedHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuRenderPassEncoderDrawIndexedHandlerAddress = findOrThrow("wgpuRenderPassEncoderDrawIndexed")
	private val wgpuRenderPassEncoderDrawIndexedHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderDrawIndexedHandlerAddress, wgpuRenderPassEncoderDrawIndexedHandlerDescription)

	fun wgpuRenderPassEncoderDrawIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit {
		return wgpuRenderPassEncoderDrawIndirectHandler.invokeExact(handler, indirectBuffer, indirectOffset) as Unit
	}
	private val wgpuRenderPassEncoderDrawIndirectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG
		)
	private val wgpuRenderPassEncoderDrawIndirectHandlerAddress = findOrThrow("wgpuRenderPassEncoderDrawIndirect")
	private val wgpuRenderPassEncoderDrawIndirectHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderDrawIndirectHandlerAddress, wgpuRenderPassEncoderDrawIndirectHandlerDescription)

	fun wgpuRenderPassEncoderDrawIndexedIndirect(handler: NativeAddress, indirectBuffer: NativeAddress, indirectOffset: ULong): Unit {
		return wgpuRenderPassEncoderDrawIndexedIndirectHandler.invokeExact(handler, indirectBuffer, indirectOffset) as Unit
	}
	private val wgpuRenderPassEncoderDrawIndexedIndirectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_LONG
		)
	private val wgpuRenderPassEncoderDrawIndexedIndirectHandlerAddress = findOrThrow("wgpuRenderPassEncoderDrawIndexedIndirect")
	private val wgpuRenderPassEncoderDrawIndexedIndirectHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderDrawIndexedIndirectHandlerAddress, wgpuRenderPassEncoderDrawIndexedIndirectHandlerDescription)

	fun wgpuRenderPassEncoderExecuteBundles(handler: NativeAddress, bundles: NativeAddress): Unit {
		return wgpuRenderPassEncoderExecuteBundlesHandler.invokeExact(handler, bundles) as Unit
	}
	private val wgpuRenderPassEncoderExecuteBundlesHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderExecuteBundlesHandlerAddress = findOrThrow("wgpuRenderPassEncoderExecuteBundles")
	private val wgpuRenderPassEncoderExecuteBundlesHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderExecuteBundlesHandlerAddress, wgpuRenderPassEncoderExecuteBundlesHandlerDescription)

	fun wgpuRenderPassEncoderInsertDebugMarker(handler: NativeAddress, markerLabel: NativeAddress): Unit {
		return wgpuRenderPassEncoderInsertDebugMarkerHandler.invokeExact(handler, markerLabel) as Unit
	}
	private val wgpuRenderPassEncoderInsertDebugMarkerHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderInsertDebugMarkerHandlerAddress = findOrThrow("wgpuRenderPassEncoderInsertDebugMarker")
	private val wgpuRenderPassEncoderInsertDebugMarkerHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderInsertDebugMarkerHandlerAddress, wgpuRenderPassEncoderInsertDebugMarkerHandlerDescription)

	fun wgpuRenderPassEncoderPopDebugGroup(handler: NativeAddress): Unit {
		return wgpuRenderPassEncoderPopDebugGroupHandler.invokeExact(handler) as Unit
	}
	private val wgpuRenderPassEncoderPopDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuRenderPassEncoderPopDebugGroupHandlerAddress = findOrThrow("wgpuRenderPassEncoderPopDebugGroup")
	private val wgpuRenderPassEncoderPopDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderPopDebugGroupHandlerAddress, wgpuRenderPassEncoderPopDebugGroupHandlerDescription)

	fun wgpuRenderPassEncoderPushDebugGroup(handler: NativeAddress, groupLabel: NativeAddress): Unit {
		return wgpuRenderPassEncoderPushDebugGroupHandler.invokeExact(handler, groupLabel) as Unit
	}
	private val wgpuRenderPassEncoderPushDebugGroupHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderPushDebugGroupHandlerAddress = findOrThrow("wgpuRenderPassEncoderPushDebugGroup")
	private val wgpuRenderPassEncoderPushDebugGroupHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderPushDebugGroupHandlerAddress, wgpuRenderPassEncoderPushDebugGroupHandlerDescription)

	fun wgpuRenderPassEncoderSetStencilReference(handler: NativeAddress, reference: UInt): Unit {
		return wgpuRenderPassEncoderSetStencilReferenceHandler.invokeExact(handler, reference) as Unit
	}
	private val wgpuRenderPassEncoderSetStencilReferenceHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT
		)
	private val wgpuRenderPassEncoderSetStencilReferenceHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetStencilReference")
	private val wgpuRenderPassEncoderSetStencilReferenceHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetStencilReferenceHandlerAddress, wgpuRenderPassEncoderSetStencilReferenceHandlerDescription)

	fun wgpuRenderPassEncoderSetBlendConstant(handler: NativeAddress, color: NativeAddress): Unit {
		return wgpuRenderPassEncoderSetBlendConstantHandler.invokeExact(handler, color) as Unit
	}
	private val wgpuRenderPassEncoderSetBlendConstantHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderSetBlendConstantHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetBlendConstant")
	private val wgpuRenderPassEncoderSetBlendConstantHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetBlendConstantHandlerAddress, wgpuRenderPassEncoderSetBlendConstantHandlerDescription)

	fun wgpuRenderPassEncoderSetViewport(handler: NativeAddress, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit {
		return wgpuRenderPassEncoderSetViewportHandler.invokeExact(handler, x, y, width, height, minDepth, maxDepth) as Unit
	}
	private val wgpuRenderPassEncoderSetViewportHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_FLOAT,
			C_FLOAT,
			C_FLOAT,
			C_FLOAT,
			C_FLOAT,
			C_FLOAT
		)
	private val wgpuRenderPassEncoderSetViewportHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetViewport")
	private val wgpuRenderPassEncoderSetViewportHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetViewportHandlerAddress, wgpuRenderPassEncoderSetViewportHandlerDescription)

	fun wgpuRenderPassEncoderSetScissorRect(handler: NativeAddress, x: UInt, y: UInt, width: UInt, height: UInt): Unit {
		return wgpuRenderPassEncoderSetScissorRectHandler.invokeExact(handler, x, y, width, height) as Unit
	}
	private val wgpuRenderPassEncoderSetScissorRectHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_INT,
			C_INT,
			C_INT
		)
	private val wgpuRenderPassEncoderSetScissorRectHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetScissorRect")
	private val wgpuRenderPassEncoderSetScissorRectHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetScissorRectHandlerAddress, wgpuRenderPassEncoderSetScissorRectHandlerDescription)

	fun wgpuRenderPassEncoderSetVertexBuffer(handler: NativeAddress, slot: UInt, buffer: NativeAddress, offset: ULong, size: ULong): Unit {
		return wgpuRenderPassEncoderSetVertexBufferHandler.invokeExact(handler, slot, buffer, offset, size) as Unit
	}
	private val wgpuRenderPassEncoderSetVertexBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT,
			C_POINTER,
			C_LONG,
			C_LONG
		)
	private val wgpuRenderPassEncoderSetVertexBufferHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetVertexBuffer")
	private val wgpuRenderPassEncoderSetVertexBufferHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetVertexBufferHandlerAddress, wgpuRenderPassEncoderSetVertexBufferHandlerDescription)

	fun wgpuRenderPassEncoderSetIndexBuffer(handler: NativeAddress, buffer: NativeAddress, format: UInt, offset: ULong, size: ULong): Unit {
		return wgpuRenderPassEncoderSetIndexBufferHandler.invokeExact(handler, buffer, format, offset, size) as Unit
	}
	private val wgpuRenderPassEncoderSetIndexBufferHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER,
			C_INT,
			C_LONG,
			C_LONG
		)
	private val wgpuRenderPassEncoderSetIndexBufferHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetIndexBuffer")
	private val wgpuRenderPassEncoderSetIndexBufferHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetIndexBufferHandlerAddress, wgpuRenderPassEncoderSetIndexBufferHandlerDescription)

	fun wgpuRenderPassEncoderBeginOcclusionQuery(handler: NativeAddress, queryIndex: UInt): Unit {
		return wgpuRenderPassEncoderBeginOcclusionQueryHandler.invokeExact(handler, queryIndex) as Unit
	}
	private val wgpuRenderPassEncoderBeginOcclusionQueryHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_INT
		)
	private val wgpuRenderPassEncoderBeginOcclusionQueryHandlerAddress = findOrThrow("wgpuRenderPassEncoderBeginOcclusionQuery")
	private val wgpuRenderPassEncoderBeginOcclusionQueryHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderBeginOcclusionQueryHandlerAddress, wgpuRenderPassEncoderBeginOcclusionQueryHandlerDescription)

	fun wgpuRenderPassEncoderEndOcclusionQuery(handler: NativeAddress): Unit {
		return wgpuRenderPassEncoderEndOcclusionQueryHandler.invokeExact(handler) as Unit
	}
	private val wgpuRenderPassEncoderEndOcclusionQueryHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuRenderPassEncoderEndOcclusionQueryHandlerAddress = findOrThrow("wgpuRenderPassEncoderEndOcclusionQuery")
	private val wgpuRenderPassEncoderEndOcclusionQueryHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderEndOcclusionQueryHandlerAddress, wgpuRenderPassEncoderEndOcclusionQueryHandlerDescription)

	fun wgpuRenderPassEncoderEnd(handler: NativeAddress): Unit {
		return wgpuRenderPassEncoderEndHandler.invokeExact(handler) as Unit
	}
	private val wgpuRenderPassEncoderEndHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuRenderPassEncoderEndHandlerAddress = findOrThrow("wgpuRenderPassEncoderEnd")
	private val wgpuRenderPassEncoderEndHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderEndHandlerAddress, wgpuRenderPassEncoderEndHandlerDescription)

	fun wgpuRenderPassEncoderSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuRenderPassEncoderSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuRenderPassEncoderSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPassEncoderSetLabelHandlerAddress = findOrThrow("wgpuRenderPassEncoderSetLabel")
	private val wgpuRenderPassEncoderSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPassEncoderSetLabelHandlerAddress, wgpuRenderPassEncoderSetLabelHandlerDescription)

	fun wgpuRenderPipelineGetBindGroupLayout(handler: NativeAddress, groupIndex: UInt): NativeAddress {
		return wgpuRenderPipelineGetBindGroupLayoutHandler.invokeExact(handler, groupIndex) as NativeAddress
	}
	private val wgpuRenderPipelineGetBindGroupLayoutHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_INT
		)
	private val wgpuRenderPipelineGetBindGroupLayoutHandlerAddress = findOrThrow("wgpuRenderPipelineGetBindGroupLayout")
	private val wgpuRenderPipelineGetBindGroupLayoutHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPipelineGetBindGroupLayoutHandlerAddress, wgpuRenderPipelineGetBindGroupLayoutHandlerDescription)

	fun wgpuRenderPipelineSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuRenderPipelineSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuRenderPipelineSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuRenderPipelineSetLabelHandlerAddress = findOrThrow("wgpuRenderPipelineSetLabel")
	private val wgpuRenderPipelineSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuRenderPipelineSetLabelHandlerAddress, wgpuRenderPipelineSetLabelHandlerDescription)

	fun wgpuSamplerSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuSamplerSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuSamplerSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuSamplerSetLabelHandlerAddress = findOrThrow("wgpuSamplerSetLabel")
	private val wgpuSamplerSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuSamplerSetLabelHandlerAddress, wgpuSamplerSetLabelHandlerDescription)

	fun wgpuShaderModuleGetCompilationInfo(handler: NativeAddress): Unit {
		return wgpuShaderModuleGetCompilationInfoHandler.invokeExact(handler) as Unit
	}
	private val wgpuShaderModuleGetCompilationInfoHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuShaderModuleGetCompilationInfoHandlerAddress = findOrThrow("wgpuShaderModuleGetCompilationInfo")
	private val wgpuShaderModuleGetCompilationInfoHandler = Linker.nativeLinker().downcallHandle(wgpuShaderModuleGetCompilationInfoHandlerAddress, wgpuShaderModuleGetCompilationInfoHandlerDescription)

	fun wgpuShaderModuleSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuShaderModuleSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuShaderModuleSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuShaderModuleSetLabelHandlerAddress = findOrThrow("wgpuShaderModuleSetLabel")
	private val wgpuShaderModuleSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuShaderModuleSetLabelHandlerAddress, wgpuShaderModuleSetLabelHandlerDescription)

	fun wgpuSurfaceConfigure(handler: NativeAddress, config: NativeAddress): Unit {
		return wgpuSurfaceConfigureHandler.invokeExact(handler, config) as Unit
	}
	private val wgpuSurfaceConfigureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuSurfaceConfigureHandlerAddress = findOrThrow("wgpuSurfaceConfigure")
	private val wgpuSurfaceConfigureHandler = Linker.nativeLinker().downcallHandle(wgpuSurfaceConfigureHandlerAddress, wgpuSurfaceConfigureHandlerDescription)

	fun wgpuSurfaceGetCapabilities(handler: NativeAddress, adapter: NativeAddress, capabilities: NativeAddress): Boolean {
		return wgpuSurfaceGetCapabilitiesHandler.invokeExact(handler, adapter, capabilities) as Boolean
	}
	private val wgpuSurfaceGetCapabilitiesHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuSurfaceGetCapabilitiesHandlerAddress = findOrThrow("wgpuSurfaceGetCapabilities")
	private val wgpuSurfaceGetCapabilitiesHandler = Linker.nativeLinker().downcallHandle(wgpuSurfaceGetCapabilitiesHandlerAddress, wgpuSurfaceGetCapabilitiesHandlerDescription)

	fun wgpuSurfaceGetCurrentTexture(handler: NativeAddress, surfaceTexture: NativeAddress): Unit {
		return wgpuSurfaceGetCurrentTextureHandler.invokeExact(handler, surfaceTexture) as Unit
	}
	private val wgpuSurfaceGetCurrentTextureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuSurfaceGetCurrentTextureHandlerAddress = findOrThrow("wgpuSurfaceGetCurrentTexture")
	private val wgpuSurfaceGetCurrentTextureHandler = Linker.nativeLinker().downcallHandle(wgpuSurfaceGetCurrentTextureHandlerAddress, wgpuSurfaceGetCurrentTextureHandlerDescription)

	fun wgpuSurfacePresent(handler: NativeAddress): Unit {
		return wgpuSurfacePresentHandler.invokeExact(handler) as Unit
	}
	private val wgpuSurfacePresentHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuSurfacePresentHandlerAddress = findOrThrow("wgpuSurfacePresent")
	private val wgpuSurfacePresentHandler = Linker.nativeLinker().downcallHandle(wgpuSurfacePresentHandlerAddress, wgpuSurfacePresentHandlerDescription)

	fun wgpuSurfaceUnconfigure(handler: NativeAddress): Unit {
		return wgpuSurfaceUnconfigureHandler.invokeExact(handler) as Unit
	}
	private val wgpuSurfaceUnconfigureHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuSurfaceUnconfigureHandlerAddress = findOrThrow("wgpuSurfaceUnconfigure")
	private val wgpuSurfaceUnconfigureHandler = Linker.nativeLinker().downcallHandle(wgpuSurfaceUnconfigureHandlerAddress, wgpuSurfaceUnconfigureHandlerDescription)

	fun wgpuSurfaceSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuSurfaceSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuSurfaceSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuSurfaceSetLabelHandlerAddress = findOrThrow("wgpuSurfaceSetLabel")
	private val wgpuSurfaceSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuSurfaceSetLabelHandlerAddress, wgpuSurfaceSetLabelHandlerDescription)

	fun wgpuTextureCreateView(handler: NativeAddress, descriptor: NativeAddress): NativeAddress {
		return wgpuTextureCreateViewHandler.invokeExact(handler, descriptor) as NativeAddress
	}
	private val wgpuTextureCreateViewHandlerDescription = FunctionDescriptor.of(
			C_POINTER,
			C_POINTER,
			C_POINTER
		)
	private val wgpuTextureCreateViewHandlerAddress = findOrThrow("wgpuTextureCreateView")
	private val wgpuTextureCreateViewHandler = Linker.nativeLinker().downcallHandle(wgpuTextureCreateViewHandlerAddress, wgpuTextureCreateViewHandlerDescription)

	fun wgpuTextureSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuTextureSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuTextureSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuTextureSetLabelHandlerAddress = findOrThrow("wgpuTextureSetLabel")
	private val wgpuTextureSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuTextureSetLabelHandlerAddress, wgpuTextureSetLabelHandlerDescription)

	fun wgpuTextureGetWidth(handler: NativeAddress): UInt {
		return wgpuTextureGetWidthHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetWidthHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetWidthHandlerAddress = findOrThrow("wgpuTextureGetWidth")
	private val wgpuTextureGetWidthHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetWidthHandlerAddress, wgpuTextureGetWidthHandlerDescription)

	fun wgpuTextureGetHeight(handler: NativeAddress): UInt {
		return wgpuTextureGetHeightHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetHeightHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetHeightHandlerAddress = findOrThrow("wgpuTextureGetHeight")
	private val wgpuTextureGetHeightHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetHeightHandlerAddress, wgpuTextureGetHeightHandlerDescription)

	fun wgpuTextureGetDepthOrArrayLayers(handler: NativeAddress): UInt {
		return wgpuTextureGetDepthOrArrayLayersHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetDepthOrArrayLayersHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetDepthOrArrayLayersHandlerAddress = findOrThrow("wgpuTextureGetDepthOrArrayLayers")
	private val wgpuTextureGetDepthOrArrayLayersHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetDepthOrArrayLayersHandlerAddress, wgpuTextureGetDepthOrArrayLayersHandlerDescription)

	fun wgpuTextureGetMipLevelCount(handler: NativeAddress): UInt {
		return wgpuTextureGetMipLevelCountHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetMipLevelCountHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetMipLevelCountHandlerAddress = findOrThrow("wgpuTextureGetMipLevelCount")
	private val wgpuTextureGetMipLevelCountHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetMipLevelCountHandlerAddress, wgpuTextureGetMipLevelCountHandlerDescription)

	fun wgpuTextureGetSampleCount(handler: NativeAddress): UInt {
		return wgpuTextureGetSampleCountHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetSampleCountHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetSampleCountHandlerAddress = findOrThrow("wgpuTextureGetSampleCount")
	private val wgpuTextureGetSampleCountHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetSampleCountHandlerAddress, wgpuTextureGetSampleCountHandlerDescription)

	fun wgpuTextureGetDimension(handler: NativeAddress): UInt {
		return wgpuTextureGetDimensionHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetDimensionHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetDimensionHandlerAddress = findOrThrow("wgpuTextureGetDimension")
	private val wgpuTextureGetDimensionHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetDimensionHandlerAddress, wgpuTextureGetDimensionHandlerDescription)

	fun wgpuTextureGetFormat(handler: NativeAddress): UInt {
		return wgpuTextureGetFormatHandler.invokeExact(handler) as UInt
	}
	private val wgpuTextureGetFormatHandlerDescription = FunctionDescriptor.of(
			C_INT,
			C_POINTER
		)
	private val wgpuTextureGetFormatHandlerAddress = findOrThrow("wgpuTextureGetFormat")
	private val wgpuTextureGetFormatHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetFormatHandlerAddress, wgpuTextureGetFormatHandlerDescription)

	fun wgpuTextureGetUsage(handler: NativeAddress): ULong {
		return wgpuTextureGetUsageHandler.invokeExact(handler) as ULong
	}
	private val wgpuTextureGetUsageHandlerDescription = FunctionDescriptor.of(
			C_LONG,
			C_POINTER
		)
	private val wgpuTextureGetUsageHandlerAddress = findOrThrow("wgpuTextureGetUsage")
	private val wgpuTextureGetUsageHandler = Linker.nativeLinker().downcallHandle(wgpuTextureGetUsageHandlerAddress, wgpuTextureGetUsageHandlerDescription)

	fun wgpuTextureDestroy(handler: NativeAddress): Unit {
		return wgpuTextureDestroyHandler.invokeExact(handler) as Unit
	}
	private val wgpuTextureDestroyHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER
		)
	private val wgpuTextureDestroyHandlerAddress = findOrThrow("wgpuTextureDestroy")
	private val wgpuTextureDestroyHandler = Linker.nativeLinker().downcallHandle(wgpuTextureDestroyHandlerAddress, wgpuTextureDestroyHandlerDescription)

	fun wgpuTextureViewSetLabel(handler: NativeAddress, label: NativeAddress): Unit {
		return wgpuTextureViewSetLabelHandler.invokeExact(handler, label) as Unit
	}
	private val wgpuTextureViewSetLabelHandlerDescription = FunctionDescriptor.ofVoid(
			C_POINTER,
			C_POINTER
		)
	private val wgpuTextureViewSetLabelHandlerAddress = findOrThrow("wgpuTextureViewSetLabel")
	private val wgpuTextureViewSetLabelHandler = Linker.nativeLinker().downcallHandle(wgpuTextureViewSetLabelHandlerAddress, wgpuTextureViewSetLabelHandlerDescription)

}
