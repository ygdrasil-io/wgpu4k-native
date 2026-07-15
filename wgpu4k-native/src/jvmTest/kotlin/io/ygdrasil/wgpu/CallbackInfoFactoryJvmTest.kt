package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.NativeAddress
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class CallbackInfoFactoryJvmTest : FreeSpec({
    "all callback info factories wire callback routing and application userdata" {
        val allocator = MemoryAllocator()
        val registrations = mutableListOf<CallbackRegistration<*>>()
        try {
            val applicationUserdata = allocator.allocate(1)

            val bufferMap = WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUBufferMapCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_WaitAnyOnly,
                    bufferMap,
                    applicationUserdata,
                ),
                WGPUCallbackMode_WaitAnyOnly,
                bufferMap,
                applicationUserdata,
            )

            val compilationInfo = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUCompilationInfoCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowProcessEvents,
                    compilationInfo,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowProcessEvents,
                compilationInfo,
                applicationUserdata,
            )

            val computePipeline = WGPUCreateComputePipelineAsyncCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUCreateComputePipelineAsyncCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowSpontaneous,
                    computePipeline,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowSpontaneous,
                computePipeline,
                applicationUserdata,
            )

            val renderPipeline = WGPUCreateRenderPipelineAsyncCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUCreateRenderPipelineAsyncCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_WaitAnyOnly,
                    renderPipeline,
                    applicationUserdata,
                ),
                WGPUCallbackMode_WaitAnyOnly,
                renderPipeline,
                applicationUserdata,
            )

            val deviceLost = WGPUDeviceLostCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUDeviceLostCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowProcessEvents,
                    deviceLost,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowProcessEvents,
                deviceLost,
                applicationUserdata,
            )

            val popErrorScope = WGPUPopErrorScopeCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUPopErrorScopeCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowSpontaneous,
                    popErrorScope,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowSpontaneous,
                popErrorScope,
                applicationUserdata,
            )

            val queueWorkDone = WGPUQueueWorkDoneCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPUQueueWorkDoneCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_WaitAnyOnly,
                    queueWorkDone,
                    applicationUserdata,
                ),
                WGPUCallbackMode_WaitAnyOnly,
                queueWorkDone,
                applicationUserdata,
            )

            val requestAdapter = WGPURequestAdapterCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPURequestAdapterCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowProcessEvents,
                    requestAdapter,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowProcessEvents,
                requestAdapter,
                applicationUserdata,
            )

            val requestDevice = WGPURequestDeviceCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            assertWiring(
                WGPURequestDeviceCallbackInfo.allocate(
                    allocator,
                    WGPUCallbackMode_AllowSpontaneous,
                    requestDevice,
                    applicationUserdata,
                ),
                WGPUCallbackMode_AllowSpontaneous,
                requestDevice,
                applicationUserdata,
            )

            val uncapturedError = WGPUUncapturedErrorCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
                .also(registrations::add)
            val uncapturedInfo = WGPUUncapturedErrorCallbackInfo.allocate(
                allocator,
                uncapturedError,
                applicationUserdata,
            )
            assertWiring(uncapturedInfo, uncapturedError, applicationUserdata)
        } finally {
            registrations.asReversed().forEach(CallbackRegistration<*>::close)
            allocator.close()
        }
    }

    "every mode bearing factory rejects invalid modes before allocation" {
        val allocator = MemoryAllocator().also(MemoryAllocator::close)
        val unknownMode = 0xFFFF_FFFEu
        val bufferMap = WGPUBufferMapCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        val compilationInfo = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        val computePipeline = WGPUCreateComputePipelineAsyncCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val renderPipeline = WGPUCreateRenderPipelineAsyncCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val deviceLost = WGPUDeviceLostCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val popErrorScope = WGPUPopErrorScopeCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val queueWorkDone = WGPUQueueWorkDoneCallback.register(CallbackPolicy.REPEATING) { _, _, _ -> }
        val requestAdapter = WGPURequestAdapterCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val requestDevice = WGPURequestDeviceCallback.register(CallbackPolicy.REPEATING) { _, _, _, _ -> }
        val registrations = listOf<CallbackRegistration<*>>(
            bufferMap,
            compilationInfo,
            computePipeline,
            renderPipeline,
            deviceLost,
            popErrorScope,
            queueWorkDone,
            requestAdapter,
            requestDevice,
        )
        try {
            listOf(0u, WGPUCallbackMode_Force32, unknownMode).forEach { invalidMode ->
                assertFailsWith<IllegalArgumentException> {
                    WGPUBufferMapCallbackInfo.allocate(allocator, invalidMode, bufferMap, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUCompilationInfoCallbackInfo.allocate(allocator, invalidMode, compilationInfo, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUCreateComputePipelineAsyncCallbackInfo.allocate(allocator, invalidMode, computePipeline, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUCreateRenderPipelineAsyncCallbackInfo.allocate(allocator, invalidMode, renderPipeline, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUDeviceLostCallbackInfo.allocate(allocator, invalidMode, deviceLost, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUPopErrorScopeCallbackInfo.allocate(allocator, invalidMode, popErrorScope, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPUQueueWorkDoneCallbackInfo.allocate(allocator, invalidMode, queueWorkDone, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPURequestAdapterCallbackInfo.allocate(allocator, invalidMode, requestAdapter, null)
                }
                assertFailsWith<IllegalArgumentException> {
                    WGPURequestDeviceCallbackInfo.allocate(allocator, invalidMode, requestDevice, null)
                }
            }
        } finally {
            registrations.asReversed().forEach(CallbackRegistration<*>::close)
        }
    }
})

private fun assertWiring(
    info: WGPUBufferMapCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUCompilationInfoCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUCreateComputePipelineAsyncCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUCreateRenderPipelineAsyncCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUDeviceLostCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUPopErrorScopeCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUQueueWorkDoneCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPURequestAdapterCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPURequestDeviceCallbackInfo,
    mode: WGPUCallbackMode,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, info.mode, mode, registration, applicationUserdata)

private fun assertWiring(
    info: WGPUUncapturedErrorCallbackInfo,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) = assertWiring(info.callback, info.userdata1, info.userdata2, null, null, registration, applicationUserdata)

private fun assertWiring(
    callback: NativeAddress?,
    userdata1: NativeAddress?,
    userdata2: NativeAddress?,
    actualMode: WGPUCallbackMode?,
    expectedMode: WGPUCallbackMode?,
    registration: CallbackRegistration<*>,
    applicationUserdata: NativeAddress,
) {
    assertEquals(registration.callback.rawValue(), assertNotNull(callback).rawValue())
    assertEquals(applicationUserdata.rawValue(), assertNotNull(userdata1).rawValue())
    assertEquals(assertNotNull(registration.userdata).rawValue(), assertNotNull(userdata2).rawValue())
    assertEquals(expectedMode, actualMode)
}

private fun NativeAddress.rawValue(): Long = handler.address()
