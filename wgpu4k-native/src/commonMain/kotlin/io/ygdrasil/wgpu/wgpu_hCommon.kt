package io.ygdrasil.wgpu

import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.CallbackHolder
import io.ygdrasil.kffi.CString
import io.ygdrasil.kffi.ArrayHolder
import io.ygdrasil.kffi.MemoryAllocator

typealias WGPUBufferUsage = ULong
const val WGPUBufferUsage_None : WGPUBufferUsage = 0uL
const val WGPUBufferUsage_MapRead : WGPUBufferUsage = 1uL
const val WGPUBufferUsage_MapWrite : WGPUBufferUsage = 2uL
const val WGPUBufferUsage_CopySrc : WGPUBufferUsage = 4uL
const val WGPUBufferUsage_CopyDst : WGPUBufferUsage = 8uL
const val WGPUBufferUsage_Index : WGPUBufferUsage = 16uL
const val WGPUBufferUsage_Vertex : WGPUBufferUsage = 32uL
const val WGPUBufferUsage_Uniform : WGPUBufferUsage = 64uL
const val WGPUBufferUsage_Storage : WGPUBufferUsage = 128uL
const val WGPUBufferUsage_Indirect : WGPUBufferUsage = 256uL
const val WGPUBufferUsage_QueryResolve : WGPUBufferUsage = 512uL

typealias WGPUColorWriteMask = ULong
const val WGPUColorWriteMask_None : WGPUColorWriteMask = 0uL
const val WGPUColorWriteMask_Red : WGPUColorWriteMask = 1uL
const val WGPUColorWriteMask_Green : WGPUColorWriteMask = 2uL
const val WGPUColorWriteMask_Blue : WGPUColorWriteMask = 4uL
const val WGPUColorWriteMask_Alpha : WGPUColorWriteMask = 8uL
const val WGPUColorWriteMask_All : WGPUColorWriteMask = 15uL

typealias WGPUMapMode = ULong
const val WGPUMapMode_None : WGPUMapMode = 0uL
const val WGPUMapMode_Read : WGPUMapMode = 1uL
const val WGPUMapMode_Write : WGPUMapMode = 2uL

typealias WGPUShaderStage = ULong
const val WGPUShaderStage_None : WGPUShaderStage = 0uL
const val WGPUShaderStage_Vertex : WGPUShaderStage = 1uL
const val WGPUShaderStage_Fragment : WGPUShaderStage = 2uL
const val WGPUShaderStage_Compute : WGPUShaderStage = 4uL

typealias WGPUTextureUsage = ULong
const val WGPUTextureUsage_None : WGPUTextureUsage = 0uL
const val WGPUTextureUsage_CopySrc : WGPUTextureUsage = 1uL
const val WGPUTextureUsage_CopyDst : WGPUTextureUsage = 2uL
const val WGPUTextureUsage_TextureBinding : WGPUTextureUsage = 4uL
const val WGPUTextureUsage_StorageBinding : WGPUTextureUsage = 8uL
const val WGPUTextureUsage_RenderAttachment : WGPUTextureUsage = 16uL
const val WGPUTextureUsage_TransientAttachment : WGPUTextureUsage = 32uL

typealias WGPUInstanceBackend = ULong
const val WGPUInstanceBackend_All : WGPUInstanceBackend = 0uL
const val WGPUInstanceBackend_Vulkan : WGPUInstanceBackend = 1uL
const val WGPUInstanceBackend_GL : WGPUInstanceBackend = 2uL
const val WGPUInstanceBackend_Metal : WGPUInstanceBackend = 4uL
const val WGPUInstanceBackend_DX12 : WGPUInstanceBackend = 8uL
const val WGPUInstanceBackend_BrowserWebGPU : WGPUInstanceBackend = 32uL
const val WGPUInstanceBackend_Primary : WGPUInstanceBackend = 45uL
const val WGPUInstanceBackend_Secondary : WGPUInstanceBackend = 2uL
const val WGPUInstanceBackend_Force32 : WGPUInstanceBackend = 2147483647uL

typealias WGPUInstanceFlag = ULong
const val WGPUInstanceFlag_Empty : WGPUInstanceFlag = 0uL
const val WGPUInstanceFlag_Debug : WGPUInstanceFlag = 1uL
const val WGPUInstanceFlag_Validation : WGPUInstanceFlag = 2uL
const val WGPUInstanceFlag_DiscardHalLabels : WGPUInstanceFlag = 4uL
const val WGPUInstanceFlag_AllowUnderlyingNoncompliantAdapter : WGPUInstanceFlag = 8uL
const val WGPUInstanceFlag_GPUBasedValidation : WGPUInstanceFlag = 16uL
const val WGPUInstanceFlag_ValidationIndirectCall : WGPUInstanceFlag = 32uL
const val WGPUInstanceFlag_AutomaticTimestampNormalization : WGPUInstanceFlag = 64uL
const val WGPUInstanceFlag_Default : WGPUInstanceFlag = 16777216uL
const val WGPUInstanceFlag_Debugging : WGPUInstanceFlag = 33554432uL
const val WGPUInstanceFlag_AdvancedDebugging : WGPUInstanceFlag = 67108864uL
const val WGPUInstanceFlag_WithEnv : WGPUInstanceFlag = 134217728uL
const val WGPUInstanceFlag_Force32 : WGPUInstanceFlag = 2147483647uL

expect interface WGPUStringView {
    var data: CString?
    var length: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUStringView
        fun allocate(allocator: MemoryAllocator): WGPUStringView
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStringView) -> Unit): ArrayHolder<WGPUStringView>
    }
}

expect value class WGPUAdapter(val handler: NativeAddress)

expect value class WGPUBindGroup(val handler: NativeAddress)

expect value class WGPUBindGroupLayout(val handler: NativeAddress)

expect value class WGPUBuffer(val handler: NativeAddress)

expect value class WGPUCommandBuffer(val handler: NativeAddress)

expect value class WGPUCommandEncoder(val handler: NativeAddress)

expect value class WGPUComputePassEncoder(val handler: NativeAddress)

expect value class WGPUComputePipeline(val handler: NativeAddress)

expect value class WGPUDevice(val handler: NativeAddress)

expect value class WGPUExternalTexture(val handler: NativeAddress)

expect value class WGPUInstance(val handler: NativeAddress)

expect value class WGPUPipelineLayout(val handler: NativeAddress)

expect value class WGPUQuerySet(val handler: NativeAddress)

expect value class WGPUQueue(val handler: NativeAddress)

expect value class WGPURenderBundle(val handler: NativeAddress)

expect value class WGPURenderBundleEncoder(val handler: NativeAddress)

expect value class WGPURenderPassEncoder(val handler: NativeAddress)

expect value class WGPURenderPipeline(val handler: NativeAddress)

expect value class WGPUSampler(val handler: NativeAddress)

expect value class WGPUShaderModule(val handler: NativeAddress)

expect value class WGPUSurface(val handler: NativeAddress)

expect value class WGPUTexture(val handler: NativeAddress)

expect value class WGPUTextureView(val handler: NativeAddress)

typealias WGPUAdapterType = UInt
const val WGPUAdapterType_DiscreteGPU : WGPUAdapterType = 1u
const val WGPUAdapterType_IntegratedGPU : WGPUAdapterType = 2u
const val WGPUAdapterType_CPU : WGPUAdapterType = 3u
const val WGPUAdapterType_Unknown : WGPUAdapterType = 4u
const val WGPUAdapterType_Force32 : WGPUAdapterType = 2147483647u

typealias WGPUAddressMode = UInt
const val WGPUAddressMode_Undefined : WGPUAddressMode = 0u
const val WGPUAddressMode_ClampToEdge : WGPUAddressMode = 1u
const val WGPUAddressMode_Repeat : WGPUAddressMode = 2u
const val WGPUAddressMode_MirrorRepeat : WGPUAddressMode = 3u
const val WGPUAddressMode_Force32 : WGPUAddressMode = 2147483647u

typealias WGPUBackendType = UInt
const val WGPUBackendType_Undefined : WGPUBackendType = 0u
const val WGPUBackendType_Null : WGPUBackendType = 1u
const val WGPUBackendType_WebGPU : WGPUBackendType = 2u
const val WGPUBackendType_D3D11 : WGPUBackendType = 3u
const val WGPUBackendType_D3D12 : WGPUBackendType = 4u
const val WGPUBackendType_Metal : WGPUBackendType = 5u
const val WGPUBackendType_Vulkan : WGPUBackendType = 6u
const val WGPUBackendType_OpenGL : WGPUBackendType = 7u
const val WGPUBackendType_OpenGLES : WGPUBackendType = 8u
const val WGPUBackendType_Force32 : WGPUBackendType = 2147483647u

typealias WGPUBlendFactor = UInt
const val WGPUBlendFactor_Undefined : WGPUBlendFactor = 0u
const val WGPUBlendFactor_Zero : WGPUBlendFactor = 1u
const val WGPUBlendFactor_One : WGPUBlendFactor = 2u
const val WGPUBlendFactor_Src : WGPUBlendFactor = 3u
const val WGPUBlendFactor_OneMinusSrc : WGPUBlendFactor = 4u
const val WGPUBlendFactor_SrcAlpha : WGPUBlendFactor = 5u
const val WGPUBlendFactor_OneMinusSrcAlpha : WGPUBlendFactor = 6u
const val WGPUBlendFactor_Dst : WGPUBlendFactor = 7u
const val WGPUBlendFactor_OneMinusDst : WGPUBlendFactor = 8u
const val WGPUBlendFactor_DstAlpha : WGPUBlendFactor = 9u
const val WGPUBlendFactor_OneMinusDstAlpha : WGPUBlendFactor = 10u
const val WGPUBlendFactor_SrcAlphaSaturated : WGPUBlendFactor = 11u
const val WGPUBlendFactor_Constant : WGPUBlendFactor = 12u
const val WGPUBlendFactor_OneMinusConstant : WGPUBlendFactor = 13u
const val WGPUBlendFactor_Src1 : WGPUBlendFactor = 14u
const val WGPUBlendFactor_OneMinusSrc1 : WGPUBlendFactor = 15u
const val WGPUBlendFactor_Src1Alpha : WGPUBlendFactor = 16u
const val WGPUBlendFactor_OneMinusSrc1Alpha : WGPUBlendFactor = 17u
const val WGPUBlendFactor_Force32 : WGPUBlendFactor = 2147483647u

typealias WGPUBlendOperation = UInt
const val WGPUBlendOperation_Undefined : WGPUBlendOperation = 0u
const val WGPUBlendOperation_Add : WGPUBlendOperation = 1u
const val WGPUBlendOperation_Subtract : WGPUBlendOperation = 2u
const val WGPUBlendOperation_ReverseSubtract : WGPUBlendOperation = 3u
const val WGPUBlendOperation_Min : WGPUBlendOperation = 4u
const val WGPUBlendOperation_Max : WGPUBlendOperation = 5u
const val WGPUBlendOperation_Force32 : WGPUBlendOperation = 2147483647u

typealias WGPUBufferBindingType = UInt
const val WGPUBufferBindingType_BindingNotUsed : WGPUBufferBindingType = 0u
const val WGPUBufferBindingType_Undefined : WGPUBufferBindingType = 1u
const val WGPUBufferBindingType_Uniform : WGPUBufferBindingType = 2u
const val WGPUBufferBindingType_Storage : WGPUBufferBindingType = 3u
const val WGPUBufferBindingType_ReadOnlyStorage : WGPUBufferBindingType = 4u
const val WGPUBufferBindingType_Force32 : WGPUBufferBindingType = 2147483647u

typealias WGPUBufferMapState = UInt
const val WGPUBufferMapState_Unmapped : WGPUBufferMapState = 1u
const val WGPUBufferMapState_Pending : WGPUBufferMapState = 2u
const val WGPUBufferMapState_Mapped : WGPUBufferMapState = 3u
const val WGPUBufferMapState_Force32 : WGPUBufferMapState = 2147483647u

typealias WGPUCallbackMode = UInt
const val WGPUCallbackMode_WaitAnyOnly : WGPUCallbackMode = 1u
const val WGPUCallbackMode_AllowProcessEvents : WGPUCallbackMode = 2u
const val WGPUCallbackMode_AllowSpontaneous : WGPUCallbackMode = 3u
const val WGPUCallbackMode_Force32 : WGPUCallbackMode = 2147483647u

typealias WGPUCompareFunction = UInt
const val WGPUCompareFunction_Undefined : WGPUCompareFunction = 0u
const val WGPUCompareFunction_Never : WGPUCompareFunction = 1u
const val WGPUCompareFunction_Less : WGPUCompareFunction = 2u
const val WGPUCompareFunction_Equal : WGPUCompareFunction = 3u
const val WGPUCompareFunction_LessEqual : WGPUCompareFunction = 4u
const val WGPUCompareFunction_Greater : WGPUCompareFunction = 5u
const val WGPUCompareFunction_NotEqual : WGPUCompareFunction = 6u
const val WGPUCompareFunction_GreaterEqual : WGPUCompareFunction = 7u
const val WGPUCompareFunction_Always : WGPUCompareFunction = 8u
const val WGPUCompareFunction_Force32 : WGPUCompareFunction = 2147483647u

typealias WGPUCompilationInfoRequestStatus = UInt
const val WGPUCompilationInfoRequestStatus_Success : WGPUCompilationInfoRequestStatus = 1u
const val WGPUCompilationInfoRequestStatus_CallbackCancelled : WGPUCompilationInfoRequestStatus = 2u
const val WGPUCompilationInfoRequestStatus_Force32 : WGPUCompilationInfoRequestStatus = 2147483647u

typealias WGPUCompilationMessageType = UInt
const val WGPUCompilationMessageType_Error : WGPUCompilationMessageType = 1u
const val WGPUCompilationMessageType_Warning : WGPUCompilationMessageType = 2u
const val WGPUCompilationMessageType_Info : WGPUCompilationMessageType = 3u
const val WGPUCompilationMessageType_Force32 : WGPUCompilationMessageType = 2147483647u

typealias WGPUComponentSwizzle = UInt
const val WGPUComponentSwizzle_Undefined : WGPUComponentSwizzle = 0u
const val WGPUComponentSwizzle_Zero : WGPUComponentSwizzle = 1u
const val WGPUComponentSwizzle_One : WGPUComponentSwizzle = 2u
const val WGPUComponentSwizzle_R : WGPUComponentSwizzle = 3u
const val WGPUComponentSwizzle_G : WGPUComponentSwizzle = 4u
const val WGPUComponentSwizzle_B : WGPUComponentSwizzle = 5u
const val WGPUComponentSwizzle_A : WGPUComponentSwizzle = 6u
const val WGPUComponentSwizzle_Force32 : WGPUComponentSwizzle = 2147483647u

typealias WGPUCompositeAlphaMode = UInt
const val WGPUCompositeAlphaMode_Auto : WGPUCompositeAlphaMode = 0u
const val WGPUCompositeAlphaMode_Opaque : WGPUCompositeAlphaMode = 1u
const val WGPUCompositeAlphaMode_Premultiplied : WGPUCompositeAlphaMode = 2u
const val WGPUCompositeAlphaMode_Unpremultiplied : WGPUCompositeAlphaMode = 3u
const val WGPUCompositeAlphaMode_Inherit : WGPUCompositeAlphaMode = 4u
const val WGPUCompositeAlphaMode_Force32 : WGPUCompositeAlphaMode = 2147483647u

typealias WGPUCreatePipelineAsyncStatus = UInt
const val WGPUCreatePipelineAsyncStatus_Success : WGPUCreatePipelineAsyncStatus = 1u
const val WGPUCreatePipelineAsyncStatus_CallbackCancelled : WGPUCreatePipelineAsyncStatus = 2u
const val WGPUCreatePipelineAsyncStatus_ValidationError : WGPUCreatePipelineAsyncStatus = 3u
const val WGPUCreatePipelineAsyncStatus_InternalError : WGPUCreatePipelineAsyncStatus = 4u
const val WGPUCreatePipelineAsyncStatus_Force32 : WGPUCreatePipelineAsyncStatus = 2147483647u

typealias WGPUCullMode = UInt
const val WGPUCullMode_Undefined : WGPUCullMode = 0u
const val WGPUCullMode_None : WGPUCullMode = 1u
const val WGPUCullMode_Front : WGPUCullMode = 2u
const val WGPUCullMode_Back : WGPUCullMode = 3u
const val WGPUCullMode_Force32 : WGPUCullMode = 2147483647u

typealias WGPUDeviceLostReason = UInt
const val WGPUDeviceLostReason_Unknown : WGPUDeviceLostReason = 1u
const val WGPUDeviceLostReason_Destroyed : WGPUDeviceLostReason = 2u
const val WGPUDeviceLostReason_CallbackCancelled : WGPUDeviceLostReason = 3u
const val WGPUDeviceLostReason_FailedCreation : WGPUDeviceLostReason = 4u
const val WGPUDeviceLostReason_Force32 : WGPUDeviceLostReason = 2147483647u

typealias WGPUErrorFilter = UInt
const val WGPUErrorFilter_Validation : WGPUErrorFilter = 1u
const val WGPUErrorFilter_OutOfMemory : WGPUErrorFilter = 2u
const val WGPUErrorFilter_Internal : WGPUErrorFilter = 3u
const val WGPUErrorFilter_Force32 : WGPUErrorFilter = 2147483647u

typealias WGPUErrorType = UInt
const val WGPUErrorType_NoError : WGPUErrorType = 1u
const val WGPUErrorType_Validation : WGPUErrorType = 2u
const val WGPUErrorType_OutOfMemory : WGPUErrorType = 3u
const val WGPUErrorType_Internal : WGPUErrorType = 4u
const val WGPUErrorType_Unknown : WGPUErrorType = 5u
const val WGPUErrorType_Force32 : WGPUErrorType = 2147483647u

typealias WGPUFeatureLevel = UInt
const val WGPUFeatureLevel_Undefined : WGPUFeatureLevel = 0u
const val WGPUFeatureLevel_Compatibility : WGPUFeatureLevel = 1u
const val WGPUFeatureLevel_Core : WGPUFeatureLevel = 2u
const val WGPUFeatureLevel_Force32 : WGPUFeatureLevel = 2147483647u

typealias WGPUFeatureName = UInt
const val WGPUFeatureName_CoreFeaturesAndLimits : WGPUFeatureName = 1u
const val WGPUFeatureName_DepthClipControl : WGPUFeatureName = 2u
const val WGPUFeatureName_Depth32FloatStencil8 : WGPUFeatureName = 3u
const val WGPUFeatureName_TextureCompressionBC : WGPUFeatureName = 4u
const val WGPUFeatureName_TextureCompressionBCSliced3D : WGPUFeatureName = 5u
const val WGPUFeatureName_TextureCompressionETC2 : WGPUFeatureName = 6u
const val WGPUFeatureName_TextureCompressionASTC : WGPUFeatureName = 7u
const val WGPUFeatureName_TextureCompressionASTCSliced3D : WGPUFeatureName = 8u
const val WGPUFeatureName_TimestampQuery : WGPUFeatureName = 9u
const val WGPUFeatureName_IndirectFirstInstance : WGPUFeatureName = 10u
const val WGPUFeatureName_ShaderF16 : WGPUFeatureName = 11u
const val WGPUFeatureName_RG11B10UfloatRenderable : WGPUFeatureName = 12u
const val WGPUFeatureName_BGRA8UnormStorage : WGPUFeatureName = 13u
const val WGPUFeatureName_Float32Filterable : WGPUFeatureName = 14u
const val WGPUFeatureName_Float32Blendable : WGPUFeatureName = 15u
const val WGPUFeatureName_ClipDistances : WGPUFeatureName = 16u
const val WGPUFeatureName_DualSourceBlending : WGPUFeatureName = 17u
const val WGPUFeatureName_Subgroups : WGPUFeatureName = 18u
const val WGPUFeatureName_TextureFormatsTier1 : WGPUFeatureName = 19u
const val WGPUFeatureName_TextureFormatsTier2 : WGPUFeatureName = 20u
const val WGPUFeatureName_PrimitiveIndex : WGPUFeatureName = 21u
const val WGPUFeatureName_TextureComponentSwizzle : WGPUFeatureName = 22u
const val WGPUFeatureName_Force32 : WGPUFeatureName = 2147483647u

typealias WGPUFilterMode = UInt
const val WGPUFilterMode_Undefined : WGPUFilterMode = 0u
const val WGPUFilterMode_Nearest : WGPUFilterMode = 1u
const val WGPUFilterMode_Linear : WGPUFilterMode = 2u
const val WGPUFilterMode_Force32 : WGPUFilterMode = 2147483647u

typealias WGPUFrontFace = UInt
const val WGPUFrontFace_Undefined : WGPUFrontFace = 0u
const val WGPUFrontFace_CCW : WGPUFrontFace = 1u
const val WGPUFrontFace_CW : WGPUFrontFace = 2u
const val WGPUFrontFace_Force32 : WGPUFrontFace = 2147483647u

typealias WGPUIndexFormat = UInt
const val WGPUIndexFormat_Undefined : WGPUIndexFormat = 0u
const val WGPUIndexFormat_Uint16 : WGPUIndexFormat = 1u
const val WGPUIndexFormat_Uint32 : WGPUIndexFormat = 2u
const val WGPUIndexFormat_Force32 : WGPUIndexFormat = 2147483647u

typealias WGPUInstanceFeatureName = UInt
const val WGPUInstanceFeatureName_TimedWaitAny : WGPUInstanceFeatureName = 1u
const val WGPUInstanceFeatureName_ShaderSourceSPIRV : WGPUInstanceFeatureName = 2u
const val WGPUInstanceFeatureName_MultipleDevicesPerAdapter : WGPUInstanceFeatureName = 3u
const val WGPUInstanceFeatureName_Force32 : WGPUInstanceFeatureName = 2147483647u

typealias WGPULoadOp = UInt
const val WGPULoadOp_Undefined : WGPULoadOp = 0u
const val WGPULoadOp_Load : WGPULoadOp = 1u
const val WGPULoadOp_Clear : WGPULoadOp = 2u
const val WGPULoadOp_Force32 : WGPULoadOp = 2147483647u

typealias WGPUMapAsyncStatus = UInt
const val WGPUMapAsyncStatus_Success : WGPUMapAsyncStatus = 1u
const val WGPUMapAsyncStatus_CallbackCancelled : WGPUMapAsyncStatus = 2u
const val WGPUMapAsyncStatus_Error : WGPUMapAsyncStatus = 3u
const val WGPUMapAsyncStatus_Aborted : WGPUMapAsyncStatus = 4u
const val WGPUMapAsyncStatus_Force32 : WGPUMapAsyncStatus = 2147483647u

typealias WGPUMipmapFilterMode = UInt
const val WGPUMipmapFilterMode_Undefined : WGPUMipmapFilterMode = 0u
const val WGPUMipmapFilterMode_Nearest : WGPUMipmapFilterMode = 1u
const val WGPUMipmapFilterMode_Linear : WGPUMipmapFilterMode = 2u
const val WGPUMipmapFilterMode_Force32 : WGPUMipmapFilterMode = 2147483647u

typealias WGPUOptionalBool = UInt
const val WGPUOptionalBool_False : WGPUOptionalBool = 0u
const val WGPUOptionalBool_True : WGPUOptionalBool = 1u
const val WGPUOptionalBool_Undefined : WGPUOptionalBool = 2u
const val WGPUOptionalBool_Force32 : WGPUOptionalBool = 2147483647u

typealias WGPUPopErrorScopeStatus = UInt
const val WGPUPopErrorScopeStatus_Success : WGPUPopErrorScopeStatus = 1u
const val WGPUPopErrorScopeStatus_CallbackCancelled : WGPUPopErrorScopeStatus = 2u
const val WGPUPopErrorScopeStatus_Error : WGPUPopErrorScopeStatus = 3u
const val WGPUPopErrorScopeStatus_Force32 : WGPUPopErrorScopeStatus = 2147483647u

typealias WGPUPowerPreference = UInt
const val WGPUPowerPreference_Undefined : WGPUPowerPreference = 0u
const val WGPUPowerPreference_LowPower : WGPUPowerPreference = 1u
const val WGPUPowerPreference_HighPerformance : WGPUPowerPreference = 2u
const val WGPUPowerPreference_Force32 : WGPUPowerPreference = 2147483647u

typealias WGPUPredefinedColorSpace = UInt
const val WGPUPredefinedColorSpace_SRGB : WGPUPredefinedColorSpace = 1u
const val WGPUPredefinedColorSpace_DisplayP3 : WGPUPredefinedColorSpace = 2u
const val WGPUPredefinedColorSpace_Force32 : WGPUPredefinedColorSpace = 2147483647u

typealias WGPUPresentMode = UInt
const val WGPUPresentMode_Undefined : WGPUPresentMode = 0u
const val WGPUPresentMode_Fifo : WGPUPresentMode = 1u
const val WGPUPresentMode_FifoRelaxed : WGPUPresentMode = 2u
const val WGPUPresentMode_Immediate : WGPUPresentMode = 3u
const val WGPUPresentMode_Mailbox : WGPUPresentMode = 4u
const val WGPUPresentMode_Force32 : WGPUPresentMode = 2147483647u

typealias WGPUPrimitiveTopology = UInt
const val WGPUPrimitiveTopology_Undefined : WGPUPrimitiveTopology = 0u
const val WGPUPrimitiveTopology_PointList : WGPUPrimitiveTopology = 1u
const val WGPUPrimitiveTopology_LineList : WGPUPrimitiveTopology = 2u
const val WGPUPrimitiveTopology_LineStrip : WGPUPrimitiveTopology = 3u
const val WGPUPrimitiveTopology_TriangleList : WGPUPrimitiveTopology = 4u
const val WGPUPrimitiveTopology_TriangleStrip : WGPUPrimitiveTopology = 5u
const val WGPUPrimitiveTopology_Force32 : WGPUPrimitiveTopology = 2147483647u

typealias WGPUQueryType = UInt
const val WGPUQueryType_Occlusion : WGPUQueryType = 1u
const val WGPUQueryType_Timestamp : WGPUQueryType = 2u
const val WGPUQueryType_Force32 : WGPUQueryType = 2147483647u

typealias WGPUQueueWorkDoneStatus = UInt
const val WGPUQueueWorkDoneStatus_Success : WGPUQueueWorkDoneStatus = 1u
const val WGPUQueueWorkDoneStatus_CallbackCancelled : WGPUQueueWorkDoneStatus = 2u
const val WGPUQueueWorkDoneStatus_Error : WGPUQueueWorkDoneStatus = 3u
const val WGPUQueueWorkDoneStatus_Force32 : WGPUQueueWorkDoneStatus = 2147483647u

typealias WGPURequestAdapterStatus = UInt
const val WGPURequestAdapterStatus_Success : WGPURequestAdapterStatus = 1u
const val WGPURequestAdapterStatus_CallbackCancelled : WGPURequestAdapterStatus = 2u
const val WGPURequestAdapterStatus_Unavailable : WGPURequestAdapterStatus = 3u
const val WGPURequestAdapterStatus_Error : WGPURequestAdapterStatus = 4u
const val WGPURequestAdapterStatus_Force32 : WGPURequestAdapterStatus = 2147483647u

typealias WGPURequestDeviceStatus = UInt
const val WGPURequestDeviceStatus_Success : WGPURequestDeviceStatus = 1u
const val WGPURequestDeviceStatus_CallbackCancelled : WGPURequestDeviceStatus = 2u
const val WGPURequestDeviceStatus_Error : WGPURequestDeviceStatus = 3u
const val WGPURequestDeviceStatus_Force32 : WGPURequestDeviceStatus = 2147483647u

typealias WGPUSamplerBindingType = UInt
const val WGPUSamplerBindingType_BindingNotUsed : WGPUSamplerBindingType = 0u
const val WGPUSamplerBindingType_Undefined : WGPUSamplerBindingType = 1u
const val WGPUSamplerBindingType_Filtering : WGPUSamplerBindingType = 2u
const val WGPUSamplerBindingType_NonFiltering : WGPUSamplerBindingType = 3u
const val WGPUSamplerBindingType_Comparison : WGPUSamplerBindingType = 4u
const val WGPUSamplerBindingType_Force32 : WGPUSamplerBindingType = 2147483647u

typealias WGPUStatus = UInt
const val WGPUStatus_Success : WGPUStatus = 1u
const val WGPUStatus_Error : WGPUStatus = 2u
const val WGPUStatus_Force32 : WGPUStatus = 2147483647u

typealias WGPUStencilOperation = UInt
const val WGPUStencilOperation_Undefined : WGPUStencilOperation = 0u
const val WGPUStencilOperation_Keep : WGPUStencilOperation = 1u
const val WGPUStencilOperation_Zero : WGPUStencilOperation = 2u
const val WGPUStencilOperation_Replace : WGPUStencilOperation = 3u
const val WGPUStencilOperation_Invert : WGPUStencilOperation = 4u
const val WGPUStencilOperation_IncrementClamp : WGPUStencilOperation = 5u
const val WGPUStencilOperation_DecrementClamp : WGPUStencilOperation = 6u
const val WGPUStencilOperation_IncrementWrap : WGPUStencilOperation = 7u
const val WGPUStencilOperation_DecrementWrap : WGPUStencilOperation = 8u
const val WGPUStencilOperation_Force32 : WGPUStencilOperation = 2147483647u

typealias WGPUStorageTextureAccess = UInt
const val WGPUStorageTextureAccess_BindingNotUsed : WGPUStorageTextureAccess = 0u
const val WGPUStorageTextureAccess_Undefined : WGPUStorageTextureAccess = 1u
const val WGPUStorageTextureAccess_WriteOnly : WGPUStorageTextureAccess = 2u
const val WGPUStorageTextureAccess_ReadOnly : WGPUStorageTextureAccess = 3u
const val WGPUStorageTextureAccess_ReadWrite : WGPUStorageTextureAccess = 4u
const val WGPUStorageTextureAccess_Force32 : WGPUStorageTextureAccess = 2147483647u

typealias WGPUStoreOp = UInt
const val WGPUStoreOp_Undefined : WGPUStoreOp = 0u
const val WGPUStoreOp_Store : WGPUStoreOp = 1u
const val WGPUStoreOp_Discard : WGPUStoreOp = 2u
const val WGPUStoreOp_Force32 : WGPUStoreOp = 2147483647u

typealias WGPUSType = UInt
const val WGPUSType_ShaderSourceSPIRV : WGPUSType = 1u
const val WGPUSType_ShaderSourceWGSL : WGPUSType = 2u
const val WGPUSType_RenderPassMaxDrawCount : WGPUSType = 3u
const val WGPUSType_SurfaceSourceMetalLayer : WGPUSType = 4u
const val WGPUSType_SurfaceSourceWindowsHWND : WGPUSType = 5u
const val WGPUSType_SurfaceSourceXlibWindow : WGPUSType = 6u
const val WGPUSType_SurfaceSourceWaylandSurface : WGPUSType = 7u
const val WGPUSType_SurfaceSourceAndroidNativeWindow : WGPUSType = 8u
const val WGPUSType_SurfaceSourceXCBWindow : WGPUSType = 9u
const val WGPUSType_SurfaceColorManagement : WGPUSType = 10u
const val WGPUSType_RequestAdapterWebXROptions : WGPUSType = 11u
const val WGPUSType_TextureComponentSwizzleDescriptor : WGPUSType = 12u
const val WGPUSType_ExternalTextureBindingLayout : WGPUSType = 13u
const val WGPUSType_ExternalTextureBindingEntry : WGPUSType = 14u
const val WGPUSType_CompatibilityModeLimits : WGPUSType = 15u
const val WGPUSType_TextureBindingViewDimension : WGPUSType = 16u
const val WGPUSType_Force32 : WGPUSType = 2147483647u

typealias WGPUSurfaceGetCurrentTextureStatus = UInt
const val WGPUSurfaceGetCurrentTextureStatus_SuccessOptimal : WGPUSurfaceGetCurrentTextureStatus = 1u
const val WGPUSurfaceGetCurrentTextureStatus_SuccessSuboptimal : WGPUSurfaceGetCurrentTextureStatus = 2u
const val WGPUSurfaceGetCurrentTextureStatus_Timeout : WGPUSurfaceGetCurrentTextureStatus = 3u
const val WGPUSurfaceGetCurrentTextureStatus_Outdated : WGPUSurfaceGetCurrentTextureStatus = 4u
const val WGPUSurfaceGetCurrentTextureStatus_Lost : WGPUSurfaceGetCurrentTextureStatus = 5u
const val WGPUSurfaceGetCurrentTextureStatus_Error : WGPUSurfaceGetCurrentTextureStatus = 6u
const val WGPUSurfaceGetCurrentTextureStatus_Force32 : WGPUSurfaceGetCurrentTextureStatus = 2147483647u

typealias WGPUTextureAspect = UInt
const val WGPUTextureAspect_Undefined : WGPUTextureAspect = 0u
const val WGPUTextureAspect_All : WGPUTextureAspect = 1u
const val WGPUTextureAspect_StencilOnly : WGPUTextureAspect = 2u
const val WGPUTextureAspect_DepthOnly : WGPUTextureAspect = 3u
const val WGPUTextureAspect_Force32 : WGPUTextureAspect = 2147483647u

typealias WGPUTextureDimension = UInt
const val WGPUTextureDimension_Undefined : WGPUTextureDimension = 0u
const val WGPUTextureDimension_1D : WGPUTextureDimension = 1u
const val WGPUTextureDimension_2D : WGPUTextureDimension = 2u
const val WGPUTextureDimension_3D : WGPUTextureDimension = 3u
const val WGPUTextureDimension_Force32 : WGPUTextureDimension = 2147483647u

typealias WGPUTextureFormat = UInt
const val WGPUTextureFormat_Undefined : WGPUTextureFormat = 0u
const val WGPUTextureFormat_R8Unorm : WGPUTextureFormat = 1u
const val WGPUTextureFormat_R8Snorm : WGPUTextureFormat = 2u
const val WGPUTextureFormat_R8Uint : WGPUTextureFormat = 3u
const val WGPUTextureFormat_R8Sint : WGPUTextureFormat = 4u
const val WGPUTextureFormat_R16Unorm : WGPUTextureFormat = 5u
const val WGPUTextureFormat_R16Snorm : WGPUTextureFormat = 6u
const val WGPUTextureFormat_R16Uint : WGPUTextureFormat = 7u
const val WGPUTextureFormat_R16Sint : WGPUTextureFormat = 8u
const val WGPUTextureFormat_R16Float : WGPUTextureFormat = 9u
const val WGPUTextureFormat_RG8Unorm : WGPUTextureFormat = 10u
const val WGPUTextureFormat_RG8Snorm : WGPUTextureFormat = 11u
const val WGPUTextureFormat_RG8Uint : WGPUTextureFormat = 12u
const val WGPUTextureFormat_RG8Sint : WGPUTextureFormat = 13u
const val WGPUTextureFormat_R32Float : WGPUTextureFormat = 14u
const val WGPUTextureFormat_R32Uint : WGPUTextureFormat = 15u
const val WGPUTextureFormat_R32Sint : WGPUTextureFormat = 16u
const val WGPUTextureFormat_RG16Unorm : WGPUTextureFormat = 17u
const val WGPUTextureFormat_RG16Snorm : WGPUTextureFormat = 18u
const val WGPUTextureFormat_RG16Uint : WGPUTextureFormat = 19u
const val WGPUTextureFormat_RG16Sint : WGPUTextureFormat = 20u
const val WGPUTextureFormat_RG16Float : WGPUTextureFormat = 21u
const val WGPUTextureFormat_RGBA8Unorm : WGPUTextureFormat = 22u
const val WGPUTextureFormat_RGBA8UnormSrgb : WGPUTextureFormat = 23u
const val WGPUTextureFormat_RGBA8Snorm : WGPUTextureFormat = 24u
const val WGPUTextureFormat_RGBA8Uint : WGPUTextureFormat = 25u
const val WGPUTextureFormat_RGBA8Sint : WGPUTextureFormat = 26u
const val WGPUTextureFormat_BGRA8Unorm : WGPUTextureFormat = 27u
const val WGPUTextureFormat_BGRA8UnormSrgb : WGPUTextureFormat = 28u
const val WGPUTextureFormat_RGB10A2Uint : WGPUTextureFormat = 29u
const val WGPUTextureFormat_RGB10A2Unorm : WGPUTextureFormat = 30u
const val WGPUTextureFormat_RG11B10Ufloat : WGPUTextureFormat = 31u
const val WGPUTextureFormat_RGB9E5Ufloat : WGPUTextureFormat = 32u
const val WGPUTextureFormat_RG32Float : WGPUTextureFormat = 33u
const val WGPUTextureFormat_RG32Uint : WGPUTextureFormat = 34u
const val WGPUTextureFormat_RG32Sint : WGPUTextureFormat = 35u
const val WGPUTextureFormat_RGBA16Unorm : WGPUTextureFormat = 36u
const val WGPUTextureFormat_RGBA16Snorm : WGPUTextureFormat = 37u
const val WGPUTextureFormat_RGBA16Uint : WGPUTextureFormat = 38u
const val WGPUTextureFormat_RGBA16Sint : WGPUTextureFormat = 39u
const val WGPUTextureFormat_RGBA16Float : WGPUTextureFormat = 40u
const val WGPUTextureFormat_RGBA32Float : WGPUTextureFormat = 41u
const val WGPUTextureFormat_RGBA32Uint : WGPUTextureFormat = 42u
const val WGPUTextureFormat_RGBA32Sint : WGPUTextureFormat = 43u
const val WGPUTextureFormat_Stencil8 : WGPUTextureFormat = 44u
const val WGPUTextureFormat_Depth16Unorm : WGPUTextureFormat = 45u
const val WGPUTextureFormat_Depth24Plus : WGPUTextureFormat = 46u
const val WGPUTextureFormat_Depth24PlusStencil8 : WGPUTextureFormat = 47u
const val WGPUTextureFormat_Depth32Float : WGPUTextureFormat = 48u
const val WGPUTextureFormat_Depth32FloatStencil8 : WGPUTextureFormat = 49u
const val WGPUTextureFormat_BC1RGBAUnorm : WGPUTextureFormat = 50u
const val WGPUTextureFormat_BC1RGBAUnormSrgb : WGPUTextureFormat = 51u
const val WGPUTextureFormat_BC2RGBAUnorm : WGPUTextureFormat = 52u
const val WGPUTextureFormat_BC2RGBAUnormSrgb : WGPUTextureFormat = 53u
const val WGPUTextureFormat_BC3RGBAUnorm : WGPUTextureFormat = 54u
const val WGPUTextureFormat_BC3RGBAUnormSrgb : WGPUTextureFormat = 55u
const val WGPUTextureFormat_BC4RUnorm : WGPUTextureFormat = 56u
const val WGPUTextureFormat_BC4RSnorm : WGPUTextureFormat = 57u
const val WGPUTextureFormat_BC5RGUnorm : WGPUTextureFormat = 58u
const val WGPUTextureFormat_BC5RGSnorm : WGPUTextureFormat = 59u
const val WGPUTextureFormat_BC6HRGBUfloat : WGPUTextureFormat = 60u
const val WGPUTextureFormat_BC6HRGBFloat : WGPUTextureFormat = 61u
const val WGPUTextureFormat_BC7RGBAUnorm : WGPUTextureFormat = 62u
const val WGPUTextureFormat_BC7RGBAUnormSrgb : WGPUTextureFormat = 63u
const val WGPUTextureFormat_ETC2RGB8Unorm : WGPUTextureFormat = 64u
const val WGPUTextureFormat_ETC2RGB8UnormSrgb : WGPUTextureFormat = 65u
const val WGPUTextureFormat_ETC2RGB8A1Unorm : WGPUTextureFormat = 66u
const val WGPUTextureFormat_ETC2RGB8A1UnormSrgb : WGPUTextureFormat = 67u
const val WGPUTextureFormat_ETC2RGBA8Unorm : WGPUTextureFormat = 68u
const val WGPUTextureFormat_ETC2RGBA8UnormSrgb : WGPUTextureFormat = 69u
const val WGPUTextureFormat_EACR11Unorm : WGPUTextureFormat = 70u
const val WGPUTextureFormat_EACR11Snorm : WGPUTextureFormat = 71u
const val WGPUTextureFormat_EACRG11Unorm : WGPUTextureFormat = 72u
const val WGPUTextureFormat_EACRG11Snorm : WGPUTextureFormat = 73u
const val WGPUTextureFormat_ASTC4x4Unorm : WGPUTextureFormat = 74u
const val WGPUTextureFormat_ASTC4x4UnormSrgb : WGPUTextureFormat = 75u
const val WGPUTextureFormat_ASTC5x4Unorm : WGPUTextureFormat = 76u
const val WGPUTextureFormat_ASTC5x4UnormSrgb : WGPUTextureFormat = 77u
const val WGPUTextureFormat_ASTC5x5Unorm : WGPUTextureFormat = 78u
const val WGPUTextureFormat_ASTC5x5UnormSrgb : WGPUTextureFormat = 79u
const val WGPUTextureFormat_ASTC6x5Unorm : WGPUTextureFormat = 80u
const val WGPUTextureFormat_ASTC6x5UnormSrgb : WGPUTextureFormat = 81u
const val WGPUTextureFormat_ASTC6x6Unorm : WGPUTextureFormat = 82u
const val WGPUTextureFormat_ASTC6x6UnormSrgb : WGPUTextureFormat = 83u
const val WGPUTextureFormat_ASTC8x5Unorm : WGPUTextureFormat = 84u
const val WGPUTextureFormat_ASTC8x5UnormSrgb : WGPUTextureFormat = 85u
const val WGPUTextureFormat_ASTC8x6Unorm : WGPUTextureFormat = 86u
const val WGPUTextureFormat_ASTC8x6UnormSrgb : WGPUTextureFormat = 87u
const val WGPUTextureFormat_ASTC8x8Unorm : WGPUTextureFormat = 88u
const val WGPUTextureFormat_ASTC8x8UnormSrgb : WGPUTextureFormat = 89u
const val WGPUTextureFormat_ASTC10x5Unorm : WGPUTextureFormat = 90u
const val WGPUTextureFormat_ASTC10x5UnormSrgb : WGPUTextureFormat = 91u
const val WGPUTextureFormat_ASTC10x6Unorm : WGPUTextureFormat = 92u
const val WGPUTextureFormat_ASTC10x6UnormSrgb : WGPUTextureFormat = 93u
const val WGPUTextureFormat_ASTC10x8Unorm : WGPUTextureFormat = 94u
const val WGPUTextureFormat_ASTC10x8UnormSrgb : WGPUTextureFormat = 95u
const val WGPUTextureFormat_ASTC10x10Unorm : WGPUTextureFormat = 96u
const val WGPUTextureFormat_ASTC10x10UnormSrgb : WGPUTextureFormat = 97u
const val WGPUTextureFormat_ASTC12x10Unorm : WGPUTextureFormat = 98u
const val WGPUTextureFormat_ASTC12x10UnormSrgb : WGPUTextureFormat = 99u
const val WGPUTextureFormat_ASTC12x12Unorm : WGPUTextureFormat = 100u
const val WGPUTextureFormat_ASTC12x12UnormSrgb : WGPUTextureFormat = 101u
const val WGPUTextureFormat_Force32 : WGPUTextureFormat = 2147483647u

typealias WGPUTextureSampleType = UInt
const val WGPUTextureSampleType_BindingNotUsed : WGPUTextureSampleType = 0u
const val WGPUTextureSampleType_Undefined : WGPUTextureSampleType = 1u
const val WGPUTextureSampleType_Float : WGPUTextureSampleType = 2u
const val WGPUTextureSampleType_UnfilterableFloat : WGPUTextureSampleType = 3u
const val WGPUTextureSampleType_Depth : WGPUTextureSampleType = 4u
const val WGPUTextureSampleType_Sint : WGPUTextureSampleType = 5u
const val WGPUTextureSampleType_Uint : WGPUTextureSampleType = 6u
const val WGPUTextureSampleType_Force32 : WGPUTextureSampleType = 2147483647u

typealias WGPUTextureViewDimension = UInt
const val WGPUTextureViewDimension_Undefined : WGPUTextureViewDimension = 0u
const val WGPUTextureViewDimension_1D : WGPUTextureViewDimension = 1u
const val WGPUTextureViewDimension_2D : WGPUTextureViewDimension = 2u
const val WGPUTextureViewDimension_2DArray : WGPUTextureViewDimension = 3u
const val WGPUTextureViewDimension_Cube : WGPUTextureViewDimension = 4u
const val WGPUTextureViewDimension_CubeArray : WGPUTextureViewDimension = 5u
const val WGPUTextureViewDimension_3D : WGPUTextureViewDimension = 6u
const val WGPUTextureViewDimension_Force32 : WGPUTextureViewDimension = 2147483647u

typealias WGPUToneMappingMode = UInt
const val WGPUToneMappingMode_Standard : WGPUToneMappingMode = 1u
const val WGPUToneMappingMode_Extended : WGPUToneMappingMode = 2u
const val WGPUToneMappingMode_Force32 : WGPUToneMappingMode = 2147483647u

typealias WGPUVertexFormat = UInt
const val WGPUVertexFormat_Uint8 : WGPUVertexFormat = 1u
const val WGPUVertexFormat_Uint8x2 : WGPUVertexFormat = 2u
const val WGPUVertexFormat_Uint8x4 : WGPUVertexFormat = 3u
const val WGPUVertexFormat_Sint8 : WGPUVertexFormat = 4u
const val WGPUVertexFormat_Sint8x2 : WGPUVertexFormat = 5u
const val WGPUVertexFormat_Sint8x4 : WGPUVertexFormat = 6u
const val WGPUVertexFormat_Unorm8 : WGPUVertexFormat = 7u
const val WGPUVertexFormat_Unorm8x2 : WGPUVertexFormat = 8u
const val WGPUVertexFormat_Unorm8x4 : WGPUVertexFormat = 9u
const val WGPUVertexFormat_Snorm8 : WGPUVertexFormat = 10u
const val WGPUVertexFormat_Snorm8x2 : WGPUVertexFormat = 11u
const val WGPUVertexFormat_Snorm8x4 : WGPUVertexFormat = 12u
const val WGPUVertexFormat_Uint16 : WGPUVertexFormat = 13u
const val WGPUVertexFormat_Uint16x2 : WGPUVertexFormat = 14u
const val WGPUVertexFormat_Uint16x4 : WGPUVertexFormat = 15u
const val WGPUVertexFormat_Sint16 : WGPUVertexFormat = 16u
const val WGPUVertexFormat_Sint16x2 : WGPUVertexFormat = 17u
const val WGPUVertexFormat_Sint16x4 : WGPUVertexFormat = 18u
const val WGPUVertexFormat_Unorm16 : WGPUVertexFormat = 19u
const val WGPUVertexFormat_Unorm16x2 : WGPUVertexFormat = 20u
const val WGPUVertexFormat_Unorm16x4 : WGPUVertexFormat = 21u
const val WGPUVertexFormat_Snorm16 : WGPUVertexFormat = 22u
const val WGPUVertexFormat_Snorm16x2 : WGPUVertexFormat = 23u
const val WGPUVertexFormat_Snorm16x4 : WGPUVertexFormat = 24u
const val WGPUVertexFormat_Float16 : WGPUVertexFormat = 25u
const val WGPUVertexFormat_Float16x2 : WGPUVertexFormat = 26u
const val WGPUVertexFormat_Float16x4 : WGPUVertexFormat = 27u
const val WGPUVertexFormat_Float32 : WGPUVertexFormat = 28u
const val WGPUVertexFormat_Float32x2 : WGPUVertexFormat = 29u
const val WGPUVertexFormat_Float32x3 : WGPUVertexFormat = 30u
const val WGPUVertexFormat_Float32x4 : WGPUVertexFormat = 31u
const val WGPUVertexFormat_Uint32 : WGPUVertexFormat = 32u
const val WGPUVertexFormat_Uint32x2 : WGPUVertexFormat = 33u
const val WGPUVertexFormat_Uint32x3 : WGPUVertexFormat = 34u
const val WGPUVertexFormat_Uint32x4 : WGPUVertexFormat = 35u
const val WGPUVertexFormat_Sint32 : WGPUVertexFormat = 36u
const val WGPUVertexFormat_Sint32x2 : WGPUVertexFormat = 37u
const val WGPUVertexFormat_Sint32x3 : WGPUVertexFormat = 38u
const val WGPUVertexFormat_Sint32x4 : WGPUVertexFormat = 39u
const val WGPUVertexFormat_Unorm10_10_10_2 : WGPUVertexFormat = 40u
const val WGPUVertexFormat_Unorm8x4BGRA : WGPUVertexFormat = 41u
const val WGPUVertexFormat_Force32 : WGPUVertexFormat = 2147483647u

typealias WGPUVertexStepMode = UInt
const val WGPUVertexStepMode_Undefined : WGPUVertexStepMode = 0u
const val WGPUVertexStepMode_Vertex : WGPUVertexStepMode = 1u
const val WGPUVertexStepMode_Instance : WGPUVertexStepMode = 2u
const val WGPUVertexStepMode_Force32 : WGPUVertexStepMode = 2147483647u

typealias WGPUWaitStatus = UInt
const val WGPUWaitStatus_Success : WGPUWaitStatus = 1u
const val WGPUWaitStatus_TimedOut : WGPUWaitStatus = 2u
const val WGPUWaitStatus_Error : WGPUWaitStatus = 3u
const val WGPUWaitStatus_Force32 : WGPUWaitStatus = 2147483647u

typealias WGPUWGSLLanguageFeatureName = UInt
const val WGPUWGSLLanguageFeatureName_ReadonlyAndReadwriteStorageTextures : WGPUWGSLLanguageFeatureName = 1u
const val WGPUWGSLLanguageFeatureName_Packed4x8IntegerDotProduct : WGPUWGSLLanguageFeatureName = 2u
const val WGPUWGSLLanguageFeatureName_UnrestrictedPointerParameters : WGPUWGSLLanguageFeatureName = 3u
const val WGPUWGSLLanguageFeatureName_PointerCompositeAccess : WGPUWGSLLanguageFeatureName = 4u
const val WGPUWGSLLanguageFeatureName_UniformBufferStandardLayout : WGPUWGSLLanguageFeatureName = 5u
const val WGPUWGSLLanguageFeatureName_SubgroupId : WGPUWGSLLanguageFeatureName = 6u
const val WGPUWGSLLanguageFeatureName_TextureAndSamplerLet : WGPUWGSLLanguageFeatureName = 7u
const val WGPUWGSLLanguageFeatureName_SubgroupUniformity : WGPUWGSLLanguageFeatureName = 8u
const val WGPUWGSLLanguageFeatureName_TextureFormatsTier1 : WGPUWGSLLanguageFeatureName = 9u
const val WGPUWGSLLanguageFeatureName_Force32 : WGPUWGSLLanguageFeatureName = 2147483647u

expect class WGPUBufferMapCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUMapAsyncStatus, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUBufferMapCallback
    }
}

expect class WGPUCompilationInfoCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUCompilationInfoRequestStatus, compilationInfo: NativeAddress?, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUCompilationInfoCallback
    }
}

expect class WGPUCreateComputePipelineAsyncCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUCreatePipelineAsyncStatus, pipeline: WGPUComputePipeline?, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUCreateComputePipelineAsyncCallback
    }
}

expect class WGPUCreateRenderPipelineAsyncCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUCreatePipelineAsyncStatus, pipeline: WGPURenderPipeline?, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUCreateRenderPipelineAsyncCallback
    }
}

expect class WGPUDeviceLostCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (device: WGPUDevice?, reason: WGPUDeviceLostReason, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUDeviceLostCallback
    }
}

expect class WGPUPopErrorScopeCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUPopErrorScopeStatus, type: WGPUErrorType, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUPopErrorScopeCallback
    }
}

expect class WGPUQueueWorkDoneCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPUQueueWorkDoneStatus, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUQueueWorkDoneCallback
    }
}

expect class WGPURequestAdapterCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPURequestAdapterCallback
    }
}

expect class WGPURequestDeviceCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (status: WGPURequestDeviceStatus, device: WGPUDevice?, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPURequestDeviceCallback
    }
}

expect class WGPUUncapturedErrorCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (device: WGPUDevice?, type: WGPUErrorType, message: WGPUStringView, userdata1: NativeAddress?, userdata2: NativeAddress?) -> Unit): WGPUUncapturedErrorCallback
    }
}

expect interface WGPUChainedStruct {
    var next: WGPUChainedStruct?
    var sType: WGPUSType
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUChainedStruct
        fun allocate(allocator: MemoryAllocator): WGPUChainedStruct
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUChainedStruct) -> Unit): ArrayHolder<WGPUChainedStruct>
    }
}

expect interface WGPUBufferMapCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBufferMapCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUBufferMapCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferMapCallbackInfo) -> Unit): ArrayHolder<WGPUBufferMapCallbackInfo>
    }
}

expect interface WGPUCompilationInfoCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCompilationInfoCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUCompilationInfoCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfoCallbackInfo) -> Unit): ArrayHolder<WGPUCompilationInfoCallbackInfo>
    }
}

expect interface WGPUCreateComputePipelineAsyncCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCreateComputePipelineAsyncCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUCreateComputePipelineAsyncCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateComputePipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateComputePipelineAsyncCallbackInfo>
    }
}

expect interface WGPUCreateRenderPipelineAsyncCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCreateRenderPipelineAsyncCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUCreateRenderPipelineAsyncCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCreateRenderPipelineAsyncCallbackInfo) -> Unit): ArrayHolder<WGPUCreateRenderPipelineAsyncCallbackInfo>
    }
}

expect interface WGPUDeviceLostCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUDeviceLostCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUDeviceLostCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceLostCallbackInfo) -> Unit): ArrayHolder<WGPUDeviceLostCallbackInfo>
    }
}

expect interface WGPUPopErrorScopeCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPopErrorScopeCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUPopErrorScopeCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPopErrorScopeCallbackInfo) -> Unit): ArrayHolder<WGPUPopErrorScopeCallbackInfo>
    }
}

expect interface WGPUQueueWorkDoneCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUQueueWorkDoneCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUQueueWorkDoneCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueWorkDoneCallbackInfo) -> Unit): ArrayHolder<WGPUQueueWorkDoneCallbackInfo>
    }
}

expect interface WGPURequestAdapterCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURequestAdapterCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPURequestAdapterCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterCallbackInfo) -> Unit): ArrayHolder<WGPURequestAdapterCallbackInfo>
    }
}

expect interface WGPURequestDeviceCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var mode: WGPUCallbackMode
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURequestDeviceCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPURequestDeviceCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestDeviceCallbackInfo) -> Unit): ArrayHolder<WGPURequestDeviceCallbackInfo>
    }
}

expect interface WGPUUncapturedErrorCallbackInfo {
    var nextInChain: WGPUChainedStruct?
    var callback: NativeAddress
    var userdata1: NativeAddress
    var userdata2: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUUncapturedErrorCallbackInfo
        fun allocate(allocator: MemoryAllocator): WGPUUncapturedErrorCallbackInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUUncapturedErrorCallbackInfo) -> Unit): ArrayHolder<WGPUUncapturedErrorCallbackInfo>
    }
}

expect interface WGPUAdapterInfo {
    var nextInChain: WGPUChainedStruct?
    var vendor: WGPUStringView
    var architecture: WGPUStringView
    var device: WGPUStringView
    var description: WGPUStringView
    var backendType: WGPUBackendType
    var adapterType: WGPUAdapterType
    var vendorID: UInt
    var deviceID: UInt
    var subgroupMinSize: UInt
    var subgroupMaxSize: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUAdapterInfo
        fun allocate(allocator: MemoryAllocator): WGPUAdapterInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUAdapterInfo) -> Unit): ArrayHolder<WGPUAdapterInfo>
    }
}

expect interface WGPUBlendComponent {
    var operation: WGPUBlendOperation
    var srcFactor: WGPUBlendFactor
    var dstFactor: WGPUBlendFactor
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBlendComponent
        fun allocate(allocator: MemoryAllocator): WGPUBlendComponent
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendComponent) -> Unit): ArrayHolder<WGPUBlendComponent>
    }
}

expect interface WGPUBufferBindingLayout {
    var nextInChain: WGPUChainedStruct?
    var type: WGPUBufferBindingType
    var hasDynamicOffset: UInt
    var minBindingSize: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBufferBindingLayout
        fun allocate(allocator: MemoryAllocator): WGPUBufferBindingLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferBindingLayout) -> Unit): ArrayHolder<WGPUBufferBindingLayout>
    }
}

expect interface WGPUBufferDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var usage: ULong
    var size: ULong
    var mappedAtCreation: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBufferDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUBufferDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBufferDescriptor) -> Unit): ArrayHolder<WGPUBufferDescriptor>
    }
}

expect interface WGPUColor {
    var r: Double
    var g: Double
    var b: Double
    var a: Double
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUColor
        fun allocate(allocator: MemoryAllocator): WGPUColor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColor) -> Unit): ArrayHolder<WGPUColor>
    }
}

expect interface WGPUCommandBufferDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCommandBufferDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUCommandBufferDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandBufferDescriptor) -> Unit): ArrayHolder<WGPUCommandBufferDescriptor>
    }
}

expect interface WGPUCommandEncoderDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCommandEncoderDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUCommandEncoderDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCommandEncoderDescriptor) -> Unit): ArrayHolder<WGPUCommandEncoderDescriptor>
    }
}

expect interface WGPUCompatibilityModeLimits {
    var chain: WGPUChainedStruct
    var maxStorageBuffersInVertexStage: UInt
    var maxStorageTexturesInVertexStage: UInt
    var maxStorageBuffersInFragmentStage: UInt
    var maxStorageTexturesInFragmentStage: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCompatibilityModeLimits
        fun allocate(allocator: MemoryAllocator): WGPUCompatibilityModeLimits
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompatibilityModeLimits) -> Unit): ArrayHolder<WGPUCompatibilityModeLimits>
    }
}

expect interface WGPUCompilationMessage {
    var nextInChain: WGPUChainedStruct?
    var message: WGPUStringView
    var type: WGPUCompilationMessageType
    var lineNum: ULong
    var linePos: ULong
    var offset: ULong
    var length: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCompilationMessage
        fun allocate(allocator: MemoryAllocator): WGPUCompilationMessage
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationMessage) -> Unit): ArrayHolder<WGPUCompilationMessage>
    }
}

expect interface WGPUConstantEntry {
    var nextInChain: WGPUChainedStruct?
    var key: WGPUStringView
    var value: Double
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUConstantEntry
        fun allocate(allocator: MemoryAllocator): WGPUConstantEntry
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUConstantEntry) -> Unit): ArrayHolder<WGPUConstantEntry>
    }
}

expect interface WGPUExtent3D {
    var width: UInt
    var height: UInt
    var depthOrArrayLayers: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUExtent3D
        fun allocate(allocator: MemoryAllocator): WGPUExtent3D
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExtent3D) -> Unit): ArrayHolder<WGPUExtent3D>
    }
}

expect interface WGPUExternalTextureBindingEntry {
    var chain: WGPUChainedStruct
    var externalTexture: WGPUExternalTexture?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingEntry
        fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingEntry
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingEntry) -> Unit): ArrayHolder<WGPUExternalTextureBindingEntry>
    }
}

expect interface WGPUExternalTextureBindingLayout {
    var chain: WGPUChainedStruct
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUExternalTextureBindingLayout
        fun allocate(allocator: MemoryAllocator): WGPUExternalTextureBindingLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUExternalTextureBindingLayout) -> Unit): ArrayHolder<WGPUExternalTextureBindingLayout>
    }
}

expect interface WGPUFuture {
    var id: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUFuture
        fun allocate(allocator: MemoryAllocator): WGPUFuture
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFuture) -> Unit): ArrayHolder<WGPUFuture>
    }
}

expect interface WGPUInstanceLimits {
    var nextInChain: WGPUChainedStruct?
    var timedWaitAnyMaxCount: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUInstanceLimits
        fun allocate(allocator: MemoryAllocator): WGPUInstanceLimits
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceLimits) -> Unit): ArrayHolder<WGPUInstanceLimits>
    }
}

expect interface WGPUMultisampleState {
    var nextInChain: WGPUChainedStruct?
    var count: UInt
    var mask: UInt
    var alphaToCoverageEnabled: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUMultisampleState
        fun allocate(allocator: MemoryAllocator): WGPUMultisampleState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUMultisampleState) -> Unit): ArrayHolder<WGPUMultisampleState>
    }
}

expect interface WGPUOrigin3D {
    var x: UInt
    var y: UInt
    var z: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUOrigin3D
        fun allocate(allocator: MemoryAllocator): WGPUOrigin3D
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUOrigin3D) -> Unit): ArrayHolder<WGPUOrigin3D>
    }
}

expect interface WGPUPassTimestampWrites {
    var nextInChain: WGPUChainedStruct?
    var querySet: WGPUQuerySet?
    var beginningOfPassWriteIndex: UInt
    var endOfPassWriteIndex: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPassTimestampWrites
        fun allocate(allocator: MemoryAllocator): WGPUPassTimestampWrites
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPassTimestampWrites) -> Unit): ArrayHolder<WGPUPassTimestampWrites>
    }
}

expect interface WGPUPipelineLayoutDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var bindGroupLayoutCount: ULong
    var bindGroupLayouts: WGPUBindGroupLayout?
    var immediateSize: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPipelineLayoutDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutDescriptor) -> Unit): ArrayHolder<WGPUPipelineLayoutDescriptor>
    }
}

expect interface WGPUPrimitiveState {
    var nextInChain: WGPUChainedStruct?
    var topology: WGPUPrimitiveTopology
    var stripIndexFormat: WGPUIndexFormat
    var frontFace: WGPUFrontFace
    var cullMode: WGPUCullMode
    var unclippedDepth: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPrimitiveState
        fun allocate(allocator: MemoryAllocator): WGPUPrimitiveState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveState) -> Unit): ArrayHolder<WGPUPrimitiveState>
    }
}

expect interface WGPUQuerySetDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var type: WGPUQueryType
    var count: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptor) -> Unit): ArrayHolder<WGPUQuerySetDescriptor>
    }
}

expect interface WGPUQueueDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUQueueDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUQueueDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQueueDescriptor) -> Unit): ArrayHolder<WGPUQueueDescriptor>
    }
}

expect interface WGPURenderBundleDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderBundleDescriptor
        fun allocate(allocator: MemoryAllocator): WGPURenderBundleDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleDescriptor) -> Unit): ArrayHolder<WGPURenderBundleDescriptor>
    }
}

expect interface WGPURenderBundleEncoderDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var colorFormatCount: ULong
    var colorFormats: NativeAddress
    var depthStencilFormat: WGPUTextureFormat
    var sampleCount: UInt
    var depthReadOnly: UInt
    var stencilReadOnly: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderBundleEncoderDescriptor
        fun allocate(allocator: MemoryAllocator): WGPURenderBundleEncoderDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderBundleEncoderDescriptor) -> Unit): ArrayHolder<WGPURenderBundleEncoderDescriptor>
    }
}

expect interface WGPURenderPassDepthStencilAttachment {
    var nextInChain: WGPUChainedStruct?
    var view: WGPUTextureView?
    var depthLoadOp: WGPULoadOp
    var depthStoreOp: WGPUStoreOp
    var depthClearValue: Float
    var depthReadOnly: UInt
    var stencilLoadOp: WGPULoadOp
    var stencilStoreOp: WGPUStoreOp
    var stencilClearValue: UInt
    var stencilReadOnly: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderPassDepthStencilAttachment
        fun allocate(allocator: MemoryAllocator): WGPURenderPassDepthStencilAttachment
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDepthStencilAttachment) -> Unit): ArrayHolder<WGPURenderPassDepthStencilAttachment>
    }
}

expect interface WGPURenderPassMaxDrawCount {
    var chain: WGPUChainedStruct
    var maxDrawCount: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderPassMaxDrawCount
        fun allocate(allocator: MemoryAllocator): WGPURenderPassMaxDrawCount
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassMaxDrawCount) -> Unit): ArrayHolder<WGPURenderPassMaxDrawCount>
    }
}

expect interface WGPURequestAdapterWebXROptions {
    var chain: WGPUChainedStruct
    var xrCompatible: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURequestAdapterWebXROptions
        fun allocate(allocator: MemoryAllocator): WGPURequestAdapterWebXROptions
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterWebXROptions) -> Unit): ArrayHolder<WGPURequestAdapterWebXROptions>
    }
}

expect interface WGPUSamplerBindingLayout {
    var nextInChain: WGPUChainedStruct?
    var type: WGPUSamplerBindingType
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSamplerBindingLayout
        fun allocate(allocator: MemoryAllocator): WGPUSamplerBindingLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerBindingLayout) -> Unit): ArrayHolder<WGPUSamplerBindingLayout>
    }
}

expect interface WGPUSamplerDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var addressModeU: WGPUAddressMode
    var addressModeV: WGPUAddressMode
    var addressModeW: WGPUAddressMode
    var magFilter: WGPUFilterMode
    var minFilter: WGPUFilterMode
    var mipmapFilter: WGPUMipmapFilterMode
    var lodMinClamp: Float
    var lodMaxClamp: Float
    var compare: WGPUCompareFunction
    var maxAnisotropy: UShort
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSamplerDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUSamplerDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSamplerDescriptor) -> Unit): ArrayHolder<WGPUSamplerDescriptor>
    }
}

expect interface WGPUShaderSourceSPIRV {
    var chain: WGPUChainedStruct
    var codeSize: UInt
    var code: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderSourceSPIRV
        fun allocate(allocator: MemoryAllocator): WGPUShaderSourceSPIRV
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceSPIRV) -> Unit): ArrayHolder<WGPUShaderSourceSPIRV>
    }
}

expect interface WGPUShaderSourceWGSL {
    var chain: WGPUChainedStruct
    var code: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderSourceWGSL
        fun allocate(allocator: MemoryAllocator): WGPUShaderSourceWGSL
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceWGSL) -> Unit): ArrayHolder<WGPUShaderSourceWGSL>
    }
}

expect interface WGPUStencilFaceState {
    var compare: WGPUCompareFunction
    var failOp: WGPUStencilOperation
    var depthFailOp: WGPUStencilOperation
    var passOp: WGPUStencilOperation
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUStencilFaceState
        fun allocate(allocator: MemoryAllocator): WGPUStencilFaceState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStencilFaceState) -> Unit): ArrayHolder<WGPUStencilFaceState>
    }
}

expect interface WGPUStorageTextureBindingLayout {
    var nextInChain: WGPUChainedStruct?
    var access: WGPUStorageTextureAccess
    var format: WGPUTextureFormat
    var viewDimension: WGPUTextureViewDimension
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUStorageTextureBindingLayout
        fun allocate(allocator: MemoryAllocator): WGPUStorageTextureBindingLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUStorageTextureBindingLayout) -> Unit): ArrayHolder<WGPUStorageTextureBindingLayout>
    }
}

expect interface WGPUSupportedFeatures {
    var featureCount: ULong
    var features: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSupportedFeatures
        fun allocate(allocator: MemoryAllocator): WGPUSupportedFeatures
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedFeatures) -> Unit): ArrayHolder<WGPUSupportedFeatures>
    }
}

expect interface WGPUSupportedInstanceFeatures {
    var featureCount: ULong
    var features: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSupportedInstanceFeatures
        fun allocate(allocator: MemoryAllocator): WGPUSupportedInstanceFeatures
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedInstanceFeatures) -> Unit): ArrayHolder<WGPUSupportedInstanceFeatures>
    }
}

expect interface WGPUSupportedWGSLLanguageFeatures {
    var featureCount: ULong
    var features: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSupportedWGSLLanguageFeatures
        fun allocate(allocator: MemoryAllocator): WGPUSupportedWGSLLanguageFeatures
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSupportedWGSLLanguageFeatures) -> Unit): ArrayHolder<WGPUSupportedWGSLLanguageFeatures>
    }
}

expect interface WGPUSurfaceCapabilities {
    var nextInChain: WGPUChainedStruct?
    var usages: ULong
    var formatCount: ULong
    var formats: NativeAddress
    var presentModeCount: ULong
    var presentModes: NativeAddress
    var alphaModeCount: ULong
    var alphaModes: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceCapabilities
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceCapabilities
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceCapabilities) -> Unit): ArrayHolder<WGPUSurfaceCapabilities>
    }
}

expect interface WGPUSurfaceColorManagement {
    var chain: WGPUChainedStruct
    var colorSpace: WGPUPredefinedColorSpace
    var toneMappingMode: WGPUToneMappingMode
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceColorManagement
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceColorManagement
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceColorManagement) -> Unit): ArrayHolder<WGPUSurfaceColorManagement>
    }
}

expect interface WGPUSurfaceConfiguration {
    var nextInChain: WGPUChainedStruct?
    var device: WGPUDevice?
    var format: WGPUTextureFormat
    var usage: ULong
    var width: UInt
    var height: UInt
    var viewFormatCount: ULong
    var viewFormats: NativeAddress
    var alphaMode: WGPUCompositeAlphaMode
    var presentMode: WGPUPresentMode
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceConfiguration
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfiguration
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfiguration) -> Unit): ArrayHolder<WGPUSurfaceConfiguration>
    }
}

expect interface WGPUSurfaceSourceAndroidNativeWindow {
    var chain: WGPUChainedStruct
    var window: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceAndroidNativeWindow
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceAndroidNativeWindow
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceAndroidNativeWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceAndroidNativeWindow>
    }
}

expect interface WGPUSurfaceSourceMetalLayer {
    var chain: WGPUChainedStruct
    var layer: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceMetalLayer
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceMetalLayer
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceMetalLayer) -> Unit): ArrayHolder<WGPUSurfaceSourceMetalLayer>
    }
}

expect interface WGPUSurfaceSourceWaylandSurface {
    var chain: WGPUChainedStruct
    var display: NativeAddress
    var surface: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWaylandSurface
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWaylandSurface
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWaylandSurface) -> Unit): ArrayHolder<WGPUSurfaceSourceWaylandSurface>
    }
}

expect interface WGPUSurfaceSourceWindowsHWND {
    var chain: WGPUChainedStruct
    var hinstance: NativeAddress
    var hwnd: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceWindowsHWND
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceWindowsHWND
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceWindowsHWND) -> Unit): ArrayHolder<WGPUSurfaceSourceWindowsHWND>
    }
}

expect interface WGPUSurfaceSourceXCBWindow {
    var chain: WGPUChainedStruct
    var connection: NativeAddress
    var window: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXCBWindow
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXCBWindow
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXCBWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXCBWindow>
    }
}

expect interface WGPUSurfaceSourceXlibWindow {
    var chain: WGPUChainedStruct
    var display: NativeAddress
    var window: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceXlibWindow
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceXlibWindow
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceXlibWindow) -> Unit): ArrayHolder<WGPUSurfaceSourceXlibWindow>
    }
}

expect interface WGPUSurfaceTexture {
    var nextInChain: WGPUChainedStruct?
    var texture: WGPUTexture?
    var status: WGPUSurfaceGetCurrentTextureStatus
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceTexture
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceTexture
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceTexture) -> Unit): ArrayHolder<WGPUSurfaceTexture>
    }
}

expect interface WGPUTexelCopyBufferLayout {
    var offset: ULong
    var bytesPerRow: UInt
    var rowsPerImage: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferLayout
        fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferLayout) -> Unit): ArrayHolder<WGPUTexelCopyBufferLayout>
    }
}

expect interface WGPUTextureBindingLayout {
    var nextInChain: WGPUChainedStruct?
    var sampleType: WGPUTextureSampleType
    var viewDimension: WGPUTextureViewDimension
    var multisampled: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureBindingLayout
        fun allocate(allocator: MemoryAllocator): WGPUTextureBindingLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingLayout) -> Unit): ArrayHolder<WGPUTextureBindingLayout>
    }
}

expect interface WGPUTextureBindingViewDimension {
    var chain: WGPUChainedStruct
    var textureBindingViewDimension: WGPUTextureViewDimension
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureBindingViewDimension
        fun allocate(allocator: MemoryAllocator): WGPUTextureBindingViewDimension
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureBindingViewDimension) -> Unit): ArrayHolder<WGPUTextureBindingViewDimension>
    }
}

expect interface WGPUTextureComponentSwizzle {
    var r: WGPUComponentSwizzle
    var g: WGPUComponentSwizzle
    var b: WGPUComponentSwizzle
    var a: WGPUComponentSwizzle
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzle
        fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzle
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzle) -> Unit): ArrayHolder<WGPUTextureComponentSwizzle>
    }
}

expect interface WGPUVertexAttribute {
    var nextInChain: WGPUChainedStruct?
    var format: WGPUVertexFormat
    var offset: ULong
    var shaderLocation: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUVertexAttribute
        fun allocate(allocator: MemoryAllocator): WGPUVertexAttribute
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexAttribute) -> Unit): ArrayHolder<WGPUVertexAttribute>
    }
}

expect interface WGPUBindGroupEntry {
    var nextInChain: WGPUChainedStruct?
    var binding: UInt
    var buffer: WGPUBuffer?
    var offset: ULong
    var size: ULong
    var sampler: WGPUSampler?
    var textureView: WGPUTextureView?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupEntry
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntry
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntry) -> Unit): ArrayHolder<WGPUBindGroupEntry>
    }
}

expect interface WGPUBindGroupLayoutEntry {
    var nextInChain: WGPUChainedStruct?
    var binding: UInt
    var visibility: ULong
    var bindingArraySize: UInt
    var buffer: WGPUBufferBindingLayout
    var sampler: WGPUSamplerBindingLayout
    var texture: WGPUTextureBindingLayout
    var storageTexture: WGPUStorageTextureBindingLayout
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntry
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntry
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntry) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntry>
    }
}

expect interface WGPUBlendState {
    var color: WGPUBlendComponent
    var alpha: WGPUBlendComponent
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBlendState
        fun allocate(allocator: MemoryAllocator): WGPUBlendState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBlendState) -> Unit): ArrayHolder<WGPUBlendState>
    }
}

expect interface WGPUCompilationInfo {
    var nextInChain: WGPUChainedStruct?
    var messageCount: ULong
    var messages: WGPUCompilationMessage?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUCompilationInfo
        fun allocate(allocator: MemoryAllocator): WGPUCompilationInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUCompilationInfo) -> Unit): ArrayHolder<WGPUCompilationInfo>
    }
}

expect interface WGPUComputePassDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var timestampWrites: WGPUPassTimestampWrites?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUComputePassDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUComputePassDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePassDescriptor) -> Unit): ArrayHolder<WGPUComputePassDescriptor>
    }
}

expect interface WGPUComputeState {
    var nextInChain: WGPUChainedStruct?
    var module: WGPUShaderModule?
    var entryPoint: WGPUStringView
    var constantCount: ULong
    var constants: WGPUConstantEntry?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUComputeState
        fun allocate(allocator: MemoryAllocator): WGPUComputeState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputeState) -> Unit): ArrayHolder<WGPUComputeState>
    }
}

expect interface WGPUDepthStencilState {
    var nextInChain: WGPUChainedStruct?
    var format: WGPUTextureFormat
    var depthWriteEnabled: WGPUOptionalBool
    var depthCompare: WGPUCompareFunction
    var stencilFront: WGPUStencilFaceState
    var stencilBack: WGPUStencilFaceState
    var stencilReadMask: UInt
    var stencilWriteMask: UInt
    var depthBias: Int
    var depthBiasSlopeScale: Float
    var depthBiasClamp: Float
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUDepthStencilState
        fun allocate(allocator: MemoryAllocator): WGPUDepthStencilState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDepthStencilState) -> Unit): ArrayHolder<WGPUDepthStencilState>
    }
}

expect interface WGPUFutureWaitInfo {
    var future: WGPUFuture
    var completed: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUFutureWaitInfo
        fun allocate(allocator: MemoryAllocator): WGPUFutureWaitInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFutureWaitInfo) -> Unit): ArrayHolder<WGPUFutureWaitInfo>
    }
}

expect interface WGPUInstanceDescriptor {
    var nextInChain: WGPUChainedStruct?
    var requiredFeatureCount: ULong
    var requiredFeatures: NativeAddress
    var requiredLimits: WGPUInstanceLimits?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUInstanceDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUInstanceDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceDescriptor) -> Unit): ArrayHolder<WGPUInstanceDescriptor>
    }
}

expect interface WGPULimits {
    var nextInChain: WGPUChainedStruct?
    var maxTextureDimension1D: UInt
    var maxTextureDimension2D: UInt
    var maxTextureDimension3D: UInt
    var maxTextureArrayLayers: UInt
    var maxBindGroups: UInt
    var maxBindGroupsPlusVertexBuffers: UInt
    var maxBindingsPerBindGroup: UInt
    var maxDynamicUniformBuffersPerPipelineLayout: UInt
    var maxDynamicStorageBuffersPerPipelineLayout: UInt
    var maxSampledTexturesPerShaderStage: UInt
    var maxSamplersPerShaderStage: UInt
    var maxStorageBuffersPerShaderStage: UInt
    var maxStorageTexturesPerShaderStage: UInt
    var maxUniformBuffersPerShaderStage: UInt
    var maxUniformBufferBindingSize: ULong
    var maxStorageBufferBindingSize: ULong
    var minUniformBufferOffsetAlignment: UInt
    var minStorageBufferOffsetAlignment: UInt
    var maxVertexBuffers: UInt
    var maxBufferSize: ULong
    var maxVertexAttributes: UInt
    var maxVertexBufferArrayStride: UInt
    var maxInterStageShaderVariables: UInt
    var maxColorAttachments: UInt
    var maxColorAttachmentBytesPerSample: UInt
    var maxComputeWorkgroupStorageSize: UInt
    var maxComputeInvocationsPerWorkgroup: UInt
    var maxComputeWorkgroupSizeX: UInt
    var maxComputeWorkgroupSizeY: UInt
    var maxComputeWorkgroupSizeZ: UInt
    var maxComputeWorkgroupsPerDimension: UInt
    var maxImmediateSize: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPULimits
        fun allocate(allocator: MemoryAllocator): WGPULimits
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPULimits) -> Unit): ArrayHolder<WGPULimits>
    }
}

expect interface WGPURenderPassColorAttachment {
    var nextInChain: WGPUChainedStruct?
    var view: WGPUTextureView?
    var depthSlice: UInt
    var resolveTarget: WGPUTextureView?
    var loadOp: WGPULoadOp
    var storeOp: WGPUStoreOp
    var clearValue: WGPUColor
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderPassColorAttachment
        fun allocate(allocator: MemoryAllocator): WGPURenderPassColorAttachment
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassColorAttachment) -> Unit): ArrayHolder<WGPURenderPassColorAttachment>
    }
}

expect interface WGPURequestAdapterOptions {
    var nextInChain: WGPUChainedStruct?
    var featureLevel: WGPUFeatureLevel
    var powerPreference: WGPUPowerPreference
    var forceFallbackAdapter: UInt
    var backendType: WGPUBackendType
    var compatibleSurface: WGPUSurface?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURequestAdapterOptions
        fun allocate(allocator: MemoryAllocator): WGPURequestAdapterOptions
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURequestAdapterOptions) -> Unit): ArrayHolder<WGPURequestAdapterOptions>
    }
}

expect interface WGPUShaderModuleDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptor) -> Unit): ArrayHolder<WGPUShaderModuleDescriptor>
    }
}

expect interface WGPUSurfaceDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceDescriptor) -> Unit): ArrayHolder<WGPUSurfaceDescriptor>
    }
}

expect interface WGPUTexelCopyBufferInfo {
    var layout: WGPUTexelCopyBufferLayout
    var buffer: WGPUBuffer?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTexelCopyBufferInfo
        fun allocate(allocator: MemoryAllocator): WGPUTexelCopyBufferInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyBufferInfo) -> Unit): ArrayHolder<WGPUTexelCopyBufferInfo>
    }
}

expect interface WGPUTexelCopyTextureInfo {
    var texture: WGPUTexture?
    var mipLevel: UInt
    var origin: WGPUOrigin3D
    var aspect: WGPUTextureAspect
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTexelCopyTextureInfo
        fun allocate(allocator: MemoryAllocator): WGPUTexelCopyTextureInfo
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTexelCopyTextureInfo) -> Unit): ArrayHolder<WGPUTexelCopyTextureInfo>
    }
}

expect interface WGPUTextureComponentSwizzleDescriptor {
    var chain: WGPUChainedStruct
    var swizzle: WGPUTextureComponentSwizzle
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureComponentSwizzleDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUTextureComponentSwizzleDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureComponentSwizzleDescriptor) -> Unit): ArrayHolder<WGPUTextureComponentSwizzleDescriptor>
    }
}

expect interface WGPUTextureDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var usage: ULong
    var dimension: WGPUTextureDimension
    var size: WGPUExtent3D
    var format: WGPUTextureFormat
    var mipLevelCount: UInt
    var sampleCount: UInt
    var viewFormatCount: ULong
    var viewFormats: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUTextureDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureDescriptor) -> Unit): ArrayHolder<WGPUTextureDescriptor>
    }
}

expect interface WGPUVertexBufferLayout {
    var nextInChain: WGPUChainedStruct?
    var stepMode: WGPUVertexStepMode
    var arrayStride: ULong
    var attributeCount: ULong
    var attributes: WGPUVertexAttribute?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUVertexBufferLayout
        fun allocate(allocator: MemoryAllocator): WGPUVertexBufferLayout
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexBufferLayout) -> Unit): ArrayHolder<WGPUVertexBufferLayout>
    }
}

expect interface WGPUBindGroupDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var layout: WGPUBindGroupLayout?
    var entryCount: ULong
    var entries: WGPUBindGroupEntry?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupDescriptor) -> Unit): ArrayHolder<WGPUBindGroupDescriptor>
    }
}

expect interface WGPUBindGroupLayoutDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var entryCount: ULong
    var entries: WGPUBindGroupLayoutEntry?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutDescriptor) -> Unit): ArrayHolder<WGPUBindGroupLayoutDescriptor>
    }
}

expect interface WGPUColorTargetState {
    var nextInChain: WGPUChainedStruct?
    var format: WGPUTextureFormat
    var blend: WGPUBlendState?
    var writeMask: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUColorTargetState
        fun allocate(allocator: MemoryAllocator): WGPUColorTargetState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUColorTargetState) -> Unit): ArrayHolder<WGPUColorTargetState>
    }
}

expect interface WGPUComputePipelineDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var layout: WGPUPipelineLayout?
    var compute: WGPUComputeState
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUComputePipelineDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUComputePipelineDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUComputePipelineDescriptor) -> Unit): ArrayHolder<WGPUComputePipelineDescriptor>
    }
}

expect interface WGPUDeviceDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var requiredFeatureCount: ULong
    var requiredFeatures: NativeAddress
    var requiredLimits: WGPULimits?
    var defaultQueue: WGPUQueueDescriptor
    var deviceLostCallbackInfo: WGPUDeviceLostCallbackInfo
    var uncapturedErrorCallbackInfo: WGPUUncapturedErrorCallbackInfo
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUDeviceDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUDeviceDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceDescriptor) -> Unit): ArrayHolder<WGPUDeviceDescriptor>
    }
}

expect interface WGPURenderPassDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var colorAttachmentCount: ULong
    var colorAttachments: WGPURenderPassColorAttachment?
    var depthStencilAttachment: WGPURenderPassDepthStencilAttachment?
    var occlusionQuerySet: WGPUQuerySet?
    var timestampWrites: WGPUPassTimestampWrites?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderPassDescriptor
        fun allocate(allocator: MemoryAllocator): WGPURenderPassDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPassDescriptor) -> Unit): ArrayHolder<WGPURenderPassDescriptor>
    }
}

expect interface WGPUTextureViewDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var format: WGPUTextureFormat
    var dimension: WGPUTextureViewDimension
    var baseMipLevel: UInt
    var mipLevelCount: UInt
    var baseArrayLayer: UInt
    var arrayLayerCount: UInt
    var aspect: WGPUTextureAspect
    var usage: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUTextureViewDescriptor
        fun allocate(allocator: MemoryAllocator): WGPUTextureViewDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUTextureViewDescriptor) -> Unit): ArrayHolder<WGPUTextureViewDescriptor>
    }
}

expect interface WGPUVertexState {
    var nextInChain: WGPUChainedStruct?
    var module: WGPUShaderModule?
    var entryPoint: WGPUStringView
    var constantCount: ULong
    var constants: WGPUConstantEntry?
    var bufferCount: ULong
    var buffers: WGPUVertexBufferLayout?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUVertexState
        fun allocate(allocator: MemoryAllocator): WGPUVertexState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUVertexState) -> Unit): ArrayHolder<WGPUVertexState>
    }
}

expect interface WGPUFragmentState {
    var nextInChain: WGPUChainedStruct?
    var module: WGPUShaderModule?
    var entryPoint: WGPUStringView
    var constantCount: ULong
    var constants: WGPUConstantEntry?
    var targetCount: ULong
    var targets: WGPUColorTargetState?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUFragmentState
        fun allocate(allocator: MemoryAllocator): WGPUFragmentState
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUFragmentState) -> Unit): ArrayHolder<WGPUFragmentState>
    }
}

expect interface WGPURenderPipelineDescriptor {
    var nextInChain: WGPUChainedStruct?
    var label: WGPUStringView
    var layout: WGPUPipelineLayout?
    var vertex: WGPUVertexState
    var primitive: WGPUPrimitiveState
    var depthStencil: WGPUDepthStencilState?
    var multisample: WGPUMultisampleState
    var fragment: WGPUFragmentState?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURenderPipelineDescriptor
        fun allocate(allocator: MemoryAllocator): WGPURenderPipelineDescriptor
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURenderPipelineDescriptor) -> Unit): ArrayHolder<WGPURenderPipelineDescriptor>
    }
}

expect fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?

expect fun wgpuGetInstanceFeatures(features: WGPUSupportedInstanceFeatures?): Unit

expect fun wgpuGetInstanceLimits(limits: WGPUInstanceLimits?): WGPUStatus

expect fun wgpuHasInstanceFeature(feature: WGPUInstanceFeatureName): UInt

expect fun wgpuGetProcAddress(procName: WGPUStringView): NativeAddress?

expect fun wgpuAdapterGetFeatures(adapter: WGPUAdapter?, features: WGPUSupportedFeatures?): Unit

expect fun wgpuAdapterGetInfo(adapter: WGPUAdapter?, info: WGPUAdapterInfo?): WGPUStatus

expect fun wgpuAdapterGetLimits(adapter: WGPUAdapter?, limits: WGPULimits?): WGPUStatus

expect fun wgpuAdapterHasFeature(adapter: WGPUAdapter?, feature: WGPUFeatureName): UInt

expect fun wgpuAdapterRequestDevice(adapter: WGPUAdapter?, descriptor: WGPUDeviceDescriptor?, callbackInfo: WGPURequestDeviceCallbackInfo): WGPUFuture

expect fun wgpuAdapterAddRef(adapter: WGPUAdapter?): Unit

expect fun wgpuAdapterRelease(adapter: WGPUAdapter?): Unit

expect fun wgpuAdapterInfoFreeMembers(adapterInfo: WGPUAdapterInfo): Unit

expect fun wgpuBindGroupSetLabel(bindGroup: WGPUBindGroup?, label: WGPUStringView): Unit

expect fun wgpuBindGroupAddRef(bindGroup: WGPUBindGroup?): Unit

expect fun wgpuBindGroupRelease(bindGroup: WGPUBindGroup?): Unit

expect fun wgpuBindGroupLayoutSetLabel(bindGroupLayout: WGPUBindGroupLayout?, label: WGPUStringView): Unit

expect fun wgpuBindGroupLayoutAddRef(bindGroupLayout: WGPUBindGroupLayout?): Unit

expect fun wgpuBindGroupLayoutRelease(bindGroupLayout: WGPUBindGroupLayout?): Unit

expect fun wgpuBufferDestroy(buffer: WGPUBuffer?): Unit

expect fun wgpuBufferGetConstMappedRange(buffer: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?

expect fun wgpuBufferGetMappedRange(buffer: WGPUBuffer?, offset: ULong, size: ULong): NativeAddress?

expect fun wgpuBufferGetMapState(buffer: WGPUBuffer?): WGPUBufferMapState

expect fun wgpuBufferGetSize(buffer: WGPUBuffer?): ULong

expect fun wgpuBufferGetUsage(buffer: WGPUBuffer?): ULong

expect fun wgpuBufferMapAsync(buffer: WGPUBuffer?, mode: ULong, offset: ULong, size: ULong, callbackInfo: WGPUBufferMapCallbackInfo): WGPUFuture

expect fun wgpuBufferReadMappedRange(buffer: WGPUBuffer?, offset: ULong, data: NativeAddress?, size: ULong): WGPUStatus

expect fun wgpuBufferSetLabel(buffer: WGPUBuffer?, label: WGPUStringView): Unit

expect fun wgpuBufferUnmap(buffer: WGPUBuffer?): Unit

expect fun wgpuBufferWriteMappedRange(buffer: WGPUBuffer?, offset: ULong, data: NativeAddress?, size: ULong): WGPUStatus

expect fun wgpuBufferAddRef(buffer: WGPUBuffer?): Unit

expect fun wgpuBufferRelease(buffer: WGPUBuffer?): Unit

expect fun wgpuCommandBufferSetLabel(commandBuffer: WGPUCommandBuffer?, label: WGPUStringView): Unit

expect fun wgpuCommandBufferAddRef(commandBuffer: WGPUCommandBuffer?): Unit

expect fun wgpuCommandBufferRelease(commandBuffer: WGPUCommandBuffer?): Unit

expect fun wgpuCommandEncoderBeginComputePass(commandEncoder: WGPUCommandEncoder?, descriptor: WGPUComputePassDescriptor?): WGPUComputePassEncoder?

expect fun wgpuCommandEncoderBeginRenderPass(commandEncoder: WGPUCommandEncoder?, descriptor: WGPURenderPassDescriptor?): WGPURenderPassEncoder?

expect fun wgpuCommandEncoderClearBuffer(commandEncoder: WGPUCommandEncoder?, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit

expect fun wgpuCommandEncoderCopyBufferToBuffer(commandEncoder: WGPUCommandEncoder?, source: WGPUBuffer?, sourceOffset: ULong, destination: WGPUBuffer?, destinationOffset: ULong, size: ULong): Unit

expect fun wgpuCommandEncoderCopyBufferToTexture(commandEncoder: WGPUCommandEncoder?, source: WGPUTexelCopyBufferInfo?, destination: WGPUTexelCopyTextureInfo?, copySize: WGPUExtent3D?): Unit

expect fun wgpuCommandEncoderCopyTextureToBuffer(commandEncoder: WGPUCommandEncoder?, source: WGPUTexelCopyTextureInfo?, destination: WGPUTexelCopyBufferInfo?, copySize: WGPUExtent3D?): Unit

expect fun wgpuCommandEncoderCopyTextureToTexture(commandEncoder: WGPUCommandEncoder?, source: WGPUTexelCopyTextureInfo?, destination: WGPUTexelCopyTextureInfo?, copySize: WGPUExtent3D?): Unit

expect fun wgpuCommandEncoderFinish(commandEncoder: WGPUCommandEncoder?, descriptor: WGPUCommandBufferDescriptor?): WGPUCommandBuffer?

expect fun wgpuCommandEncoderInsertDebugMarker(commandEncoder: WGPUCommandEncoder?, markerLabel: WGPUStringView): Unit

expect fun wgpuCommandEncoderPopDebugGroup(commandEncoder: WGPUCommandEncoder?): Unit

expect fun wgpuCommandEncoderPushDebugGroup(commandEncoder: WGPUCommandEncoder?, groupLabel: WGPUStringView): Unit

expect fun wgpuCommandEncoderResolveQuerySet(commandEncoder: WGPUCommandEncoder?, querySet: WGPUQuerySet?, firstQuery: UInt, queryCount: UInt, destination: WGPUBuffer?, destinationOffset: ULong): Unit

expect fun wgpuCommandEncoderSetLabel(commandEncoder: WGPUCommandEncoder?, label: WGPUStringView): Unit

expect fun wgpuCommandEncoderWriteTimestamp(commandEncoder: WGPUCommandEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit

expect fun wgpuCommandEncoderAddRef(commandEncoder: WGPUCommandEncoder?): Unit

expect fun wgpuCommandEncoderRelease(commandEncoder: WGPUCommandEncoder?): Unit

expect fun wgpuComputePassEncoderDispatchWorkgroups(computePassEncoder: WGPUComputePassEncoder?, workgroupCountX: UInt, workgroupCountY: UInt, workgroupCountZ: UInt): Unit

expect fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(computePassEncoder: WGPUComputePassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit

expect fun wgpuComputePassEncoderEnd(computePassEncoder: WGPUComputePassEncoder?): Unit

expect fun wgpuComputePassEncoderInsertDebugMarker(computePassEncoder: WGPUComputePassEncoder?, markerLabel: WGPUStringView): Unit

expect fun wgpuComputePassEncoderPopDebugGroup(computePassEncoder: WGPUComputePassEncoder?): Unit

expect fun wgpuComputePassEncoderPushDebugGroup(computePassEncoder: WGPUComputePassEncoder?, groupLabel: WGPUStringView): Unit

expect fun wgpuComputePassEncoderSetBindGroup(computePassEncoder: WGPUComputePassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: NativeAddress?): Unit

expect fun wgpuComputePassEncoderSetLabel(computePassEncoder: WGPUComputePassEncoder?, label: WGPUStringView): Unit

expect fun wgpuComputePassEncoderSetPipeline(computePassEncoder: WGPUComputePassEncoder?, pipeline: WGPUComputePipeline?): Unit

expect fun wgpuComputePassEncoderAddRef(computePassEncoder: WGPUComputePassEncoder?): Unit

expect fun wgpuComputePassEncoderRelease(computePassEncoder: WGPUComputePassEncoder?): Unit

expect fun wgpuComputePipelineGetBindGroupLayout(computePipeline: WGPUComputePipeline?, groupIndex: UInt): WGPUBindGroupLayout?

expect fun wgpuComputePipelineSetLabel(computePipeline: WGPUComputePipeline?, label: WGPUStringView): Unit

expect fun wgpuComputePipelineAddRef(computePipeline: WGPUComputePipeline?): Unit

expect fun wgpuComputePipelineRelease(computePipeline: WGPUComputePipeline?): Unit

expect fun wgpuDeviceCreateBindGroup(device: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?): WGPUBindGroup?

expect fun wgpuDeviceCreateBindGroupLayout(device: WGPUDevice?, descriptor: WGPUBindGroupLayoutDescriptor?): WGPUBindGroupLayout?

expect fun wgpuDeviceCreateBuffer(device: WGPUDevice?, descriptor: WGPUBufferDescriptor?): WGPUBuffer?

expect fun wgpuDeviceCreateCommandEncoder(device: WGPUDevice?, descriptor: WGPUCommandEncoderDescriptor?): WGPUCommandEncoder?

expect fun wgpuDeviceCreateComputePipeline(device: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?): WGPUComputePipeline?

expect fun wgpuDeviceCreateComputePipelineAsync(device: WGPUDevice?, descriptor: WGPUComputePipelineDescriptor?, callbackInfo: WGPUCreateComputePipelineAsyncCallbackInfo): WGPUFuture

expect fun wgpuDeviceCreatePipelineLayout(device: WGPUDevice?, descriptor: WGPUPipelineLayoutDescriptor?): WGPUPipelineLayout?

expect fun wgpuDeviceCreateQuerySet(device: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?): WGPUQuerySet?

expect fun wgpuDeviceCreateRenderBundleEncoder(device: WGPUDevice?, descriptor: WGPURenderBundleEncoderDescriptor?): WGPURenderBundleEncoder?

expect fun wgpuDeviceCreateRenderPipeline(device: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?): WGPURenderPipeline?

expect fun wgpuDeviceCreateRenderPipelineAsync(device: WGPUDevice?, descriptor: WGPURenderPipelineDescriptor?, callbackInfo: WGPUCreateRenderPipelineAsyncCallbackInfo): WGPUFuture

expect fun wgpuDeviceCreateSampler(device: WGPUDevice?, descriptor: WGPUSamplerDescriptor?): WGPUSampler?

expect fun wgpuDeviceCreateShaderModule(device: WGPUDevice?, descriptor: WGPUShaderModuleDescriptor?): WGPUShaderModule?

expect fun wgpuDeviceCreateTexture(device: WGPUDevice?, descriptor: WGPUTextureDescriptor?): WGPUTexture?

expect fun wgpuDeviceDestroy(device: WGPUDevice?): Unit

expect fun wgpuDeviceGetAdapterInfo(device: WGPUDevice?, adapterInfo: WGPUAdapterInfo?): WGPUStatus

expect fun wgpuDeviceGetFeatures(device: WGPUDevice?, features: WGPUSupportedFeatures?): Unit

expect fun wgpuDeviceGetLimits(device: WGPUDevice?, limits: WGPULimits?): WGPUStatus

expect fun wgpuDeviceGetLostFuture(device: WGPUDevice?): WGPUFuture

expect fun wgpuDeviceGetQueue(device: WGPUDevice?): WGPUQueue?

expect fun wgpuDeviceHasFeature(device: WGPUDevice?, feature: WGPUFeatureName): UInt

expect fun wgpuDevicePopErrorScope(device: WGPUDevice?, callbackInfo: WGPUPopErrorScopeCallbackInfo): WGPUFuture

expect fun wgpuDevicePushErrorScope(device: WGPUDevice?, filter: WGPUErrorFilter): Unit

expect fun wgpuDeviceSetLabel(device: WGPUDevice?, label: WGPUStringView): Unit

expect fun wgpuDeviceAddRef(device: WGPUDevice?): Unit

expect fun wgpuDeviceRelease(device: WGPUDevice?): Unit

expect fun wgpuExternalTextureSetLabel(externalTexture: WGPUExternalTexture?, label: WGPUStringView): Unit

expect fun wgpuExternalTextureAddRef(externalTexture: WGPUExternalTexture?): Unit

expect fun wgpuExternalTextureRelease(externalTexture: WGPUExternalTexture?): Unit

expect fun wgpuInstanceCreateSurface(instance: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?): WGPUSurface?

expect fun wgpuInstanceGetWGSLLanguageFeatures(instance: WGPUInstance?, features: WGPUSupportedWGSLLanguageFeatures?): Unit

expect fun wgpuInstanceHasWGSLLanguageFeature(instance: WGPUInstance?, feature: WGPUWGSLLanguageFeatureName): UInt

expect fun wgpuInstanceProcessEvents(instance: WGPUInstance?): Unit

expect fun wgpuInstanceRequestAdapter(instance: WGPUInstance?, options: WGPURequestAdapterOptions?, callbackInfo: WGPURequestAdapterCallbackInfo): WGPUFuture

expect fun wgpuInstanceWaitAny(instance: WGPUInstance?, futureCount: ULong, futures: WGPUFutureWaitInfo?, timeoutNS: ULong): WGPUWaitStatus

expect fun wgpuInstanceAddRef(instance: WGPUInstance?): Unit

expect fun wgpuInstanceRelease(instance: WGPUInstance?): Unit

expect fun wgpuPipelineLayoutSetLabel(pipelineLayout: WGPUPipelineLayout?, label: WGPUStringView): Unit

expect fun wgpuPipelineLayoutAddRef(pipelineLayout: WGPUPipelineLayout?): Unit

expect fun wgpuPipelineLayoutRelease(pipelineLayout: WGPUPipelineLayout?): Unit

expect fun wgpuQuerySetDestroy(querySet: WGPUQuerySet?): Unit

expect fun wgpuQuerySetGetCount(querySet: WGPUQuerySet?): UInt

expect fun wgpuQuerySetGetType(querySet: WGPUQuerySet?): WGPUQueryType

expect fun wgpuQuerySetSetLabel(querySet: WGPUQuerySet?, label: WGPUStringView): Unit

expect fun wgpuQuerySetAddRef(querySet: WGPUQuerySet?): Unit

expect fun wgpuQuerySetRelease(querySet: WGPUQuerySet?): Unit

expect fun wgpuQueueOnSubmittedWorkDone(queue: WGPUQueue?, callbackInfo: WGPUQueueWorkDoneCallbackInfo): WGPUFuture

expect fun wgpuQueueSetLabel(queue: WGPUQueue?, label: WGPUStringView): Unit

expect fun wgpuQueueSubmit(queue: WGPUQueue?, commandCount: ULong, commands: WGPUCommandBuffer?): Unit

expect fun wgpuQueueWriteBuffer(queue: WGPUQueue?, buffer: WGPUBuffer?, bufferOffset: ULong, data: NativeAddress?, size: ULong): Unit

expect fun wgpuQueueWriteTexture(queue: WGPUQueue?, destination: WGPUTexelCopyTextureInfo?, data: NativeAddress?, dataSize: ULong, dataLayout: WGPUTexelCopyBufferLayout?, writeSize: WGPUExtent3D?): Unit

expect fun wgpuQueueAddRef(queue: WGPUQueue?): Unit

expect fun wgpuQueueRelease(queue: WGPUQueue?): Unit

expect fun wgpuRenderBundleSetLabel(renderBundle: WGPURenderBundle?, label: WGPUStringView): Unit

expect fun wgpuRenderBundleAddRef(renderBundle: WGPURenderBundle?): Unit

expect fun wgpuRenderBundleRelease(renderBundle: WGPURenderBundle?): Unit

expect fun wgpuRenderBundleEncoderDraw(renderBundleEncoder: WGPURenderBundleEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit

expect fun wgpuRenderBundleEncoderDrawIndexed(renderBundleEncoder: WGPURenderBundleEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit

expect fun wgpuRenderBundleEncoderDrawIndexedIndirect(renderBundleEncoder: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit

expect fun wgpuRenderBundleEncoderDrawIndirect(renderBundleEncoder: WGPURenderBundleEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit

expect fun wgpuRenderBundleEncoderFinish(renderBundleEncoder: WGPURenderBundleEncoder?, descriptor: WGPURenderBundleDescriptor?): WGPURenderBundle?

expect fun wgpuRenderBundleEncoderInsertDebugMarker(renderBundleEncoder: WGPURenderBundleEncoder?, markerLabel: WGPUStringView): Unit

expect fun wgpuRenderBundleEncoderPopDebugGroup(renderBundleEncoder: WGPURenderBundleEncoder?): Unit

expect fun wgpuRenderBundleEncoderPushDebugGroup(renderBundleEncoder: WGPURenderBundleEncoder?, groupLabel: WGPUStringView): Unit

expect fun wgpuRenderBundleEncoderSetBindGroup(renderBundleEncoder: WGPURenderBundleEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: NativeAddress?): Unit

expect fun wgpuRenderBundleEncoderSetIndexBuffer(renderBundleEncoder: WGPURenderBundleEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit

expect fun wgpuRenderBundleEncoderSetLabel(renderBundleEncoder: WGPURenderBundleEncoder?, label: WGPUStringView): Unit

expect fun wgpuRenderBundleEncoderSetPipeline(renderBundleEncoder: WGPURenderBundleEncoder?, pipeline: WGPURenderPipeline?): Unit

expect fun wgpuRenderBundleEncoderSetVertexBuffer(renderBundleEncoder: WGPURenderBundleEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit

expect fun wgpuRenderBundleEncoderAddRef(renderBundleEncoder: WGPURenderBundleEncoder?): Unit

expect fun wgpuRenderBundleEncoderRelease(renderBundleEncoder: WGPURenderBundleEncoder?): Unit

expect fun wgpuRenderPassEncoderBeginOcclusionQuery(renderPassEncoder: WGPURenderPassEncoder?, queryIndex: UInt): Unit

expect fun wgpuRenderPassEncoderDraw(renderPassEncoder: WGPURenderPassEncoder?, vertexCount: UInt, instanceCount: UInt, firstVertex: UInt, firstInstance: UInt): Unit

expect fun wgpuRenderPassEncoderDrawIndexed(renderPassEncoder: WGPURenderPassEncoder?, indexCount: UInt, instanceCount: UInt, firstIndex: UInt, baseVertex: Int, firstInstance: UInt): Unit

expect fun wgpuRenderPassEncoderDrawIndexedIndirect(renderPassEncoder: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit

expect fun wgpuRenderPassEncoderDrawIndirect(renderPassEncoder: WGPURenderPassEncoder?, indirectBuffer: WGPUBuffer?, indirectOffset: ULong): Unit

expect fun wgpuRenderPassEncoderEnd(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuRenderPassEncoderEndOcclusionQuery(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuRenderPassEncoderExecuteBundles(renderPassEncoder: WGPURenderPassEncoder?, bundleCount: ULong, bundles: WGPURenderBundle?): Unit

expect fun wgpuRenderPassEncoderInsertDebugMarker(renderPassEncoder: WGPURenderPassEncoder?, markerLabel: WGPUStringView): Unit

expect fun wgpuRenderPassEncoderPopDebugGroup(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuRenderPassEncoderPushDebugGroup(renderPassEncoder: WGPURenderPassEncoder?, groupLabel: WGPUStringView): Unit

expect fun wgpuRenderPassEncoderSetBindGroup(renderPassEncoder: WGPURenderPassEncoder?, groupIndex: UInt, group: WGPUBindGroup?, dynamicOffsetCount: ULong, dynamicOffsets: NativeAddress?): Unit

expect fun wgpuRenderPassEncoderSetBlendConstant(renderPassEncoder: WGPURenderPassEncoder?, color: WGPUColor?): Unit

expect fun wgpuRenderPassEncoderSetIndexBuffer(renderPassEncoder: WGPURenderPassEncoder?, buffer: WGPUBuffer?, format: WGPUIndexFormat, offset: ULong, size: ULong): Unit

expect fun wgpuRenderPassEncoderSetLabel(renderPassEncoder: WGPURenderPassEncoder?, label: WGPUStringView): Unit

expect fun wgpuRenderPassEncoderSetPipeline(renderPassEncoder: WGPURenderPassEncoder?, pipeline: WGPURenderPipeline?): Unit

expect fun wgpuRenderPassEncoderSetScissorRect(renderPassEncoder: WGPURenderPassEncoder?, x: UInt, y: UInt, width: UInt, height: UInt): Unit

expect fun wgpuRenderPassEncoderSetStencilReference(renderPassEncoder: WGPURenderPassEncoder?, reference: UInt): Unit

expect fun wgpuRenderPassEncoderSetVertexBuffer(renderPassEncoder: WGPURenderPassEncoder?, slot: UInt, buffer: WGPUBuffer?, offset: ULong, size: ULong): Unit

expect fun wgpuRenderPassEncoderSetViewport(renderPassEncoder: WGPURenderPassEncoder?, x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float): Unit

expect fun wgpuRenderPassEncoderAddRef(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuRenderPassEncoderRelease(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuRenderPipelineGetBindGroupLayout(renderPipeline: WGPURenderPipeline?, groupIndex: UInt): WGPUBindGroupLayout?

expect fun wgpuRenderPipelineSetLabel(renderPipeline: WGPURenderPipeline?, label: WGPUStringView): Unit

expect fun wgpuRenderPipelineAddRef(renderPipeline: WGPURenderPipeline?): Unit

expect fun wgpuRenderPipelineRelease(renderPipeline: WGPURenderPipeline?): Unit

expect fun wgpuSamplerSetLabel(sampler: WGPUSampler?, label: WGPUStringView): Unit

expect fun wgpuSamplerAddRef(sampler: WGPUSampler?): Unit

expect fun wgpuSamplerRelease(sampler: WGPUSampler?): Unit

expect fun wgpuShaderModuleGetCompilationInfo(shaderModule: WGPUShaderModule?, callbackInfo: WGPUCompilationInfoCallbackInfo): WGPUFuture

expect fun wgpuShaderModuleSetLabel(shaderModule: WGPUShaderModule?, label: WGPUStringView): Unit

expect fun wgpuShaderModuleAddRef(shaderModule: WGPUShaderModule?): Unit

expect fun wgpuShaderModuleRelease(shaderModule: WGPUShaderModule?): Unit

expect fun wgpuSupportedFeaturesFreeMembers(supportedFeatures: WGPUSupportedFeatures): Unit

expect fun wgpuSupportedInstanceFeaturesFreeMembers(supportedInstanceFeatures: WGPUSupportedInstanceFeatures): Unit

expect fun wgpuSupportedWGSLLanguageFeaturesFreeMembers(supportedWGSLLanguageFeatures: WGPUSupportedWGSLLanguageFeatures): Unit

expect fun wgpuSurfaceConfigure(surface: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit

expect fun wgpuSurfaceGetCapabilities(surface: WGPUSurface?, adapter: WGPUAdapter?, capabilities: WGPUSurfaceCapabilities?): WGPUStatus

expect fun wgpuSurfaceGetCurrentTexture(surface: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?): Unit

expect fun wgpuSurfacePresent(surface: WGPUSurface?): WGPUStatus

expect fun wgpuSurfaceSetLabel(surface: WGPUSurface?, label: WGPUStringView): Unit

expect fun wgpuSurfaceUnconfigure(surface: WGPUSurface?): Unit

expect fun wgpuSurfaceAddRef(surface: WGPUSurface?): Unit

expect fun wgpuSurfaceRelease(surface: WGPUSurface?): Unit

expect fun wgpuSurfaceCapabilitiesFreeMembers(surfaceCapabilities: WGPUSurfaceCapabilities): Unit

expect fun wgpuTextureCreateView(texture: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?): WGPUTextureView?

expect fun wgpuTextureDestroy(texture: WGPUTexture?): Unit

expect fun wgpuTextureGetDepthOrArrayLayers(texture: WGPUTexture?): UInt

expect fun wgpuTextureGetDimension(texture: WGPUTexture?): WGPUTextureDimension

expect fun wgpuTextureGetFormat(texture: WGPUTexture?): WGPUTextureFormat

expect fun wgpuTextureGetHeight(texture: WGPUTexture?): UInt

expect fun wgpuTextureGetMipLevelCount(texture: WGPUTexture?): UInt

expect fun wgpuTextureGetSampleCount(texture: WGPUTexture?): UInt

expect fun wgpuTextureGetTextureBindingViewDimension(texture: WGPUTexture?): WGPUTextureViewDimension

expect fun wgpuTextureGetUsage(texture: WGPUTexture?): ULong

expect fun wgpuTextureGetWidth(texture: WGPUTexture?): UInt

expect fun wgpuTextureSetLabel(texture: WGPUTexture?, label: WGPUStringView): Unit

expect fun wgpuTextureAddRef(texture: WGPUTexture?): Unit

expect fun wgpuTextureRelease(texture: WGPUTexture?): Unit

expect fun wgpuTextureViewSetLabel(textureView: WGPUTextureView?, label: WGPUStringView): Unit

expect fun wgpuTextureViewAddRef(textureView: WGPUTextureView?): Unit

expect fun wgpuTextureViewRelease(textureView: WGPUTextureView?): Unit

typealias WGPUNativeSType = UInt
const val WGPUSType_DeviceExtras : WGPUNativeSType = 196609u
const val WGPUSType_NativeLimits : WGPUNativeSType = 196610u
const val WGPUSType_PipelineLayoutExtras : WGPUNativeSType = 196611u
const val WGPUSType_ShaderSourceGLSL : WGPUNativeSType = 196612u
const val WGPUSType_InstanceExtras : WGPUNativeSType = 196614u
const val WGPUSType_BindGroupEntryExtras : WGPUNativeSType = 196615u
const val WGPUSType_BindGroupLayoutEntryExtras : WGPUNativeSType = 196616u
const val WGPUSType_QuerySetDescriptorExtras : WGPUNativeSType = 196617u
const val WGPUSType_SurfaceConfigurationExtras : WGPUNativeSType = 196618u
const val WGPUSType_SurfaceSourceSwapChainPanel : WGPUNativeSType = 196619u
const val WGPUSType_PrimitiveStateExtras : WGPUNativeSType = 196620u
const val WGPUNativeSType_Force32 : WGPUNativeSType = 2147483647u

typealias WGPUNativeSurfaceGetCurrentTextureStatus = UInt
const val WGPUSurfaceGetCurrentTextureStatus_Occluded : WGPUNativeSurfaceGetCurrentTextureStatus = 196609u
const val WGPUNativeSurfaceGetCurrentTextureStatus_Force32 : WGPUNativeSurfaceGetCurrentTextureStatus = 2147483647u

typealias WGPUNativeFeature = UInt
const val WGPUNativeFeature_Immediates : WGPUNativeFeature = 196609u
const val WGPUNativeFeature_TextureAdapterSpecificFormatFeatures : WGPUNativeFeature = 196610u
const val WGPUNativeFeature_MultiDrawIndirectCount : WGPUNativeFeature = 196612u
const val WGPUNativeFeature_VertexWritableStorage : WGPUNativeFeature = 196613u
const val WGPUNativeFeature_TextureBindingArray : WGPUNativeFeature = 196614u
const val WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing : WGPUNativeFeature = 196615u
const val WGPUNativeFeature_PipelineStatisticsQuery : WGPUNativeFeature = 196616u
const val WGPUNativeFeature_StorageResourceBindingArray : WGPUNativeFeature = 196617u
const val WGPUNativeFeature_PartiallyBoundBindingArray : WGPUNativeFeature = 196618u
const val WGPUNativeFeature_TextureFormat16bitNorm : WGPUNativeFeature = 196619u
const val WGPUNativeFeature_TextureCompressionAstcHdr : WGPUNativeFeature = 196620u
const val WGPUNativeFeature_MappablePrimaryBuffers : WGPUNativeFeature = 196622u
const val WGPUNativeFeature_BufferBindingArray : WGPUNativeFeature = 196623u
const val WGPUNativeFeature_UniformBufferAndStorageTextureArrayNonUniformIndexing : WGPUNativeFeature = 196624u
const val WGPUNativeFeature_PolygonModeLine : WGPUNativeFeature = 196627u
const val WGPUNativeFeature_PolygonModePoint : WGPUNativeFeature = 196628u
const val WGPUNativeFeature_ConservativeRasterization : WGPUNativeFeature = 196629u
const val WGPUNativeFeature_SpirvShaderPassthrough : WGPUNativeFeature = 196631u
const val WGPUNativeFeature_VertexAttribute64bit : WGPUNativeFeature = 196633u
const val WGPUNativeFeature_TextureFormatNv12 : WGPUNativeFeature = 196634u
const val WGPUNativeFeature_RayQuery : WGPUNativeFeature = 196636u
const val WGPUNativeFeature_ShaderF64 : WGPUNativeFeature = 196637u
const val WGPUNativeFeature_ShaderI16 : WGPUNativeFeature = 196638u
const val WGPUNativeFeature_ShaderEarlyDepthTest : WGPUNativeFeature = 196640u
const val WGPUNativeFeature_Subgroup : WGPUNativeFeature = 196641u
const val WGPUNativeFeature_SubgroupVertex : WGPUNativeFeature = 196642u
const val WGPUNativeFeature_SubgroupBarrier : WGPUNativeFeature = 196643u
const val WGPUNativeFeature_TimestampQueryInsideEncoders : WGPUNativeFeature = 196644u
const val WGPUNativeFeature_TimestampQueryInsidePasses : WGPUNativeFeature = 196645u
const val WGPUNativeFeature_ShaderInt64 : WGPUNativeFeature = 196646u
const val WGPUNativeFeature_Force32 : WGPUNativeFeature = 2147483647u

typealias WGPULogLevel = UInt
const val WGPULogLevel_Off : WGPULogLevel = 0u
const val WGPULogLevel_Error : WGPULogLevel = 1u
const val WGPULogLevel_Warn : WGPULogLevel = 2u
const val WGPULogLevel_Info : WGPULogLevel = 3u
const val WGPULogLevel_Debug : WGPULogLevel = 4u
const val WGPULogLevel_Trace : WGPULogLevel = 5u
const val WGPULogLevel_Force32 : WGPULogLevel = 2147483647u

typealias WGPUDx12Compiler = UInt
const val WGPUDx12Compiler_Undefined : WGPUDx12Compiler = 0u
const val WGPUDx12Compiler_Fxc : WGPUDx12Compiler = 1u
const val WGPUDx12Compiler_Dxc : WGPUDx12Compiler = 2u
const val WGPUDx12Compiler_Force32 : WGPUDx12Compiler = 2147483647u

typealias WGPUGles3MinorVersion = UInt
const val WGPUGles3MinorVersion_Automatic : WGPUGles3MinorVersion = 0u
const val WGPUGles3MinorVersion_Version0 : WGPUGles3MinorVersion = 1u
const val WGPUGles3MinorVersion_Version1 : WGPUGles3MinorVersion = 2u
const val WGPUGles3MinorVersion_Version2 : WGPUGles3MinorVersion = 3u
const val WGPUGles3MinorVersion_Force32 : WGPUGles3MinorVersion = 2147483647u

typealias WGPUPipelineStatisticName = UInt
const val WGPUPipelineStatisticName_VertexShaderInvocations : WGPUPipelineStatisticName = 0u
const val WGPUPipelineStatisticName_ClipperInvocations : WGPUPipelineStatisticName = 1u
const val WGPUPipelineStatisticName_ClipperPrimitivesOut : WGPUPipelineStatisticName = 2u
const val WGPUPipelineStatisticName_FragmentShaderInvocations : WGPUPipelineStatisticName = 3u
const val WGPUPipelineStatisticName_ComputeShaderInvocations : WGPUPipelineStatisticName = 4u
const val WGPUPipelineStatisticName_Force32 : WGPUPipelineStatisticName = 2147483647u

typealias WGPUNativeQueryType = UInt
const val WGPUNativeQueryType_PipelineStatistics : WGPUNativeQueryType = 196608u
const val WGPUNativeQueryType_Force32 : WGPUNativeQueryType = 2147483647u

typealias WGPUDxcMaxShaderModel = UInt
const val WGPUDxcMaxShaderModel_V6_0 : WGPUDxcMaxShaderModel = 0u
const val WGPUDxcMaxShaderModel_V6_1 : WGPUDxcMaxShaderModel = 1u
const val WGPUDxcMaxShaderModel_V6_2 : WGPUDxcMaxShaderModel = 2u
const val WGPUDxcMaxShaderModel_V6_3 : WGPUDxcMaxShaderModel = 3u
const val WGPUDxcMaxShaderModel_V6_4 : WGPUDxcMaxShaderModel = 4u
const val WGPUDxcMaxShaderModel_V6_5 : WGPUDxcMaxShaderModel = 5u
const val WGPUDxcMaxShaderModel_V6_6 : WGPUDxcMaxShaderModel = 6u
const val WGPUDxcMaxShaderModel_V6_7 : WGPUDxcMaxShaderModel = 7u
const val WGPUDxcMaxShaderModel_Force32 : WGPUDxcMaxShaderModel = 2147483647u

typealias WGPUGLFenceBehaviour = UInt
const val WGPUGLFenceBehaviour_Normal : WGPUGLFenceBehaviour = 0u
const val WGPUGLFenceBehaviour_AutoFinish : WGPUGLFenceBehaviour = 1u
const val WGPUGLFenceBehaviour_Force32 : WGPUGLFenceBehaviour = 2147483647u

typealias WGPUDx12SwapchainKind = UInt
const val WGPUDx12SwapchainKind_Undefined : WGPUDx12SwapchainKind = 0u
const val WGPUDx12SwapchainKind_DxgiFromHwnd : WGPUDx12SwapchainKind = 1u
const val WGPUDx12SwapchainKind_DxgiFromVisual : WGPUDx12SwapchainKind = 2u
const val WGPUDx12SwapchainKind_Force32 : WGPUDx12SwapchainKind = 2147483647u

typealias WGPUNativeDisplayHandleType = UInt
const val WGPUNativeDisplayHandleType_None : WGPUNativeDisplayHandleType = 0u
const val WGPUNativeDisplayHandleType_Xlib : WGPUNativeDisplayHandleType = 1u
const val WGPUNativeDisplayHandleType_Xcb : WGPUNativeDisplayHandleType = 2u
const val WGPUNativeDisplayHandleType_Wayland : WGPUNativeDisplayHandleType = 3u
const val WGPUNativeDisplayHandleType_Force32 : WGPUNativeDisplayHandleType = 2147483647u

expect interface WGPUXlibDisplayHandle {
    var display: NativeAddress
    var screen: Int
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUXlibDisplayHandle
        fun allocate(allocator: MemoryAllocator): WGPUXlibDisplayHandle
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXlibDisplayHandle) -> Unit): ArrayHolder<WGPUXlibDisplayHandle>
    }
}

expect interface WGPUXcbDisplayHandle {
    var connection: NativeAddress
    var screen: Int
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUXcbDisplayHandle
        fun allocate(allocator: MemoryAllocator): WGPUXcbDisplayHandle
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUXcbDisplayHandle) -> Unit): ArrayHolder<WGPUXcbDisplayHandle>
    }
}

expect interface WGPUWaylandDisplayHandle {
    var display: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUWaylandDisplayHandle
        fun allocate(allocator: MemoryAllocator): WGPUWaylandDisplayHandle
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUWaylandDisplayHandle) -> Unit): ArrayHolder<WGPUWaylandDisplayHandle>
    }
}

expect interface WGPUNativeDisplayHandle {
    var type: WGPUNativeDisplayHandleType
    val xlib: WGPUXlibDisplayHandle?
    fun setXlib(value: WGPUXlibDisplayHandle)
    val xcb: WGPUXcbDisplayHandle?
    fun setXcb(value: WGPUXcbDisplayHandle)
    val wayland: WGPUWaylandDisplayHandle?
    fun setWayland(value: WGPUWaylandDisplayHandle)
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUNativeDisplayHandle
        fun allocate(allocator: MemoryAllocator): WGPUNativeDisplayHandle
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): ArrayHolder<WGPUNativeDisplayHandle>
    }
}

expect interface WGPUInstanceExtras {
    var chain: WGPUChainedStruct
    var backends: ULong
    var flags: ULong
    var dx12ShaderCompiler: WGPUDx12Compiler
    var gles3MinorVersion: WGPUGles3MinorVersion
    var glFenceBehaviour: WGPUGLFenceBehaviour
    var dxcPath: WGPUStringView
    var dxcMaxShaderModel: WGPUDxcMaxShaderModel
    var dx12PresentationSystem: WGPUDx12SwapchainKind
    var budgetForDeviceCreation: NativeAddress
    var budgetForDeviceLoss: NativeAddress
    var displayHandle: WGPUNativeDisplayHandle
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUInstanceExtras
        fun allocate(allocator: MemoryAllocator): WGPUInstanceExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceExtras) -> Unit): ArrayHolder<WGPUInstanceExtras>
    }
}

expect interface WGPUDeviceExtras {
    var chain: WGPUChainedStruct
    var tracePath: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUDeviceExtras
        fun allocate(allocator: MemoryAllocator): WGPUDeviceExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUDeviceExtras) -> Unit): ArrayHolder<WGPUDeviceExtras>
    }
}

expect interface WGPUNativeLimits {
    var chain: WGPUChainedStruct
    var maxImmediateSize: UInt
    var maxNonSamplerBindings: UInt
    var maxBindingArrayElementsPerShaderStage: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUNativeLimits
        fun allocate(allocator: MemoryAllocator): WGPUNativeLimits
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUNativeLimits) -> Unit): ArrayHolder<WGPUNativeLimits>
    }
}

expect interface WGPUPipelineLayoutExtras {
    var chain: WGPUChainedStruct
    var immediateDataSize: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPipelineLayoutExtras
        fun allocate(allocator: MemoryAllocator): WGPUPipelineLayoutExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPipelineLayoutExtras) -> Unit): ArrayHolder<WGPUPipelineLayoutExtras>
    }
}

expect interface WGPUShaderDefine {
    var name: WGPUStringView
    var value: WGPUStringView
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderDefine
        fun allocate(allocator: MemoryAllocator): WGPUShaderDefine
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderDefine) -> Unit): ArrayHolder<WGPUShaderDefine>
    }
}

expect interface WGPUShaderSourceGLSL {
    var chain: WGPUChainedStruct
    var stage: ULong
    var code: WGPUStringView
    var defineCount: UInt
    var defines: WGPUShaderDefine?
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderSourceGLSL
        fun allocate(allocator: MemoryAllocator): WGPUShaderSourceGLSL
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderSourceGLSL) -> Unit): ArrayHolder<WGPUShaderSourceGLSL>
    }
}

expect interface WGPUShaderModuleDescriptorSpirV {
    var label: WGPUStringView
    var sourceSize: UInt
    var source: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUShaderModuleDescriptorSpirV
        fun allocate(allocator: MemoryAllocator): WGPUShaderModuleDescriptorSpirV
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUShaderModuleDescriptorSpirV) -> Unit): ArrayHolder<WGPUShaderModuleDescriptorSpirV>
    }
}

expect interface WGPURegistryReport {
    var numAllocated: ULong
    var numKeptFromUser: ULong
    var numReleasedFromUser: ULong
    var elementSize: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPURegistryReport
        fun allocate(allocator: MemoryAllocator): WGPURegistryReport
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPURegistryReport) -> Unit): ArrayHolder<WGPURegistryReport>
    }
}

expect interface WGPUHubReport {
    var adapters: WGPURegistryReport
    var devices: WGPURegistryReport
    var queues: WGPURegistryReport
    var pipelineLayouts: WGPURegistryReport
    var shaderModules: WGPURegistryReport
    var bindGroupLayouts: WGPURegistryReport
    var bindGroups: WGPURegistryReport
    var commandBuffers: WGPURegistryReport
    var renderBundles: WGPURegistryReport
    var renderPipelines: WGPURegistryReport
    var computePipelines: WGPURegistryReport
    var pipelineCaches: WGPURegistryReport
    var querySets: WGPURegistryReport
    var buffers: WGPURegistryReport
    var textures: WGPURegistryReport
    var textureViews: WGPURegistryReport
    var samplers: WGPURegistryReport
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUHubReport
        fun allocate(allocator: MemoryAllocator): WGPUHubReport
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUHubReport) -> Unit): ArrayHolder<WGPUHubReport>
    }
}

expect interface WGPUGlobalReport {
    var surfaces: WGPURegistryReport
    var hub: WGPUHubReport
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUGlobalReport
        fun allocate(allocator: MemoryAllocator): WGPUGlobalReport
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUGlobalReport) -> Unit): ArrayHolder<WGPUGlobalReport>
    }
}

expect interface WGPUInstanceEnumerateAdapterOptions {
    var nextInChain: WGPUChainedStruct?
    var backends: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUInstanceEnumerateAdapterOptions
        fun allocate(allocator: MemoryAllocator): WGPUInstanceEnumerateAdapterOptions
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUInstanceEnumerateAdapterOptions) -> Unit): ArrayHolder<WGPUInstanceEnumerateAdapterOptions>
    }
}

expect interface WGPUBindGroupEntryExtras {
    var chain: WGPUChainedStruct
    var buffers: WGPUBuffer?
    var bufferCount: ULong
    var samplers: WGPUSampler?
    var samplerCount: ULong
    var textureViews: WGPUTextureView?
    var textureViewCount: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupEntryExtras
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupEntryExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupEntryExtras>
    }
}

expect interface WGPUBindGroupLayoutEntryExtras {
    var chain: WGPUChainedStruct
    var count: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUBindGroupLayoutEntryExtras
        fun allocate(allocator: MemoryAllocator): WGPUBindGroupLayoutEntryExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUBindGroupLayoutEntryExtras) -> Unit): ArrayHolder<WGPUBindGroupLayoutEntryExtras>
    }
}

expect interface WGPUQuerySetDescriptorExtras {
    var chain: WGPUChainedStruct
    var pipelineStatistics: NativeAddress
    var pipelineStatisticCount: ULong
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUQuerySetDescriptorExtras
        fun allocate(allocator: MemoryAllocator): WGPUQuerySetDescriptorExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUQuerySetDescriptorExtras) -> Unit): ArrayHolder<WGPUQuerySetDescriptorExtras>
    }
}

expect interface WGPUSurfaceConfigurationExtras {
    var chain: WGPUChainedStruct
    var desiredMaximumFrameLatency: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceConfigurationExtras
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceConfigurationExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceConfigurationExtras) -> Unit): ArrayHolder<WGPUSurfaceConfigurationExtras>
    }
}

expect interface WGPUSurfaceSourceSwapChainPanel {
    var chain: WGPUChainedStruct
    var panelNative: NativeAddress
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUSurfaceSourceSwapChainPanel
        fun allocate(allocator: MemoryAllocator): WGPUSurfaceSourceSwapChainPanel
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUSurfaceSourceSwapChainPanel) -> Unit): ArrayHolder<WGPUSurfaceSourceSwapChainPanel>
    }
}

typealias WGPUPolygonMode = UInt
const val WGPUPolygonMode_Fill : WGPUPolygonMode = 0u
const val WGPUPolygonMode_Line : WGPUPolygonMode = 1u
const val WGPUPolygonMode_Point : WGPUPolygonMode = 2u

expect interface WGPUPrimitiveStateExtras {
    var chain: WGPUChainedStruct
    var polygonMode: WGPUPolygonMode
    var conservative: UInt
    val handler: NativeAddress
    companion object {
        operator fun invoke(address: NativeAddress): WGPUPrimitiveStateExtras
        fun allocate(allocator: MemoryAllocator): WGPUPrimitiveStateExtras
        fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt, WGPUPrimitiveStateExtras) -> Unit): ArrayHolder<WGPUPrimitiveStateExtras>
    }
}

expect class WGPULogCallback : AutoCloseable {
    val handler: NativeAddress
    override fun close()
    companion object {
        fun allocate(callback: (level: WGPULogLevel, message: WGPUStringView, userdata: NativeAddress?) -> Unit): WGPULogCallback
    }
}

typealias WGPUNativeTextureFormat = UInt
const val WGPUNativeTextureFormat_R16Unorm : WGPUNativeTextureFormat = 196609u
const val WGPUNativeTextureFormat_R16Snorm : WGPUNativeTextureFormat = 196610u
const val WGPUNativeTextureFormat_Rg16Unorm : WGPUNativeTextureFormat = 196611u
const val WGPUNativeTextureFormat_Rg16Snorm : WGPUNativeTextureFormat = 196612u
const val WGPUNativeTextureFormat_Rgba16Unorm : WGPUNativeTextureFormat = 196613u
const val WGPUNativeTextureFormat_Rgba16Snorm : WGPUNativeTextureFormat = 196614u
const val WGPUNativeTextureFormat_NV12 : WGPUNativeTextureFormat = 196615u
const val WGPUNativeTextureFormat_P010 : WGPUNativeTextureFormat = 196616u

expect fun wgpuGenerateReport(instance: WGPUInstance?, report: WGPUGlobalReport?): Unit

expect fun wgpuInstanceEnumerateAdapters(instance: WGPUInstance?, options: WGPUInstanceEnumerateAdapterOptions?, adapters: WGPUAdapter?): ULong

expect fun wgpuQueueSubmitForIndex(queue: WGPUQueue?, commandCount: ULong, commands: WGPUCommandBuffer?): ULong

expect fun wgpuQueueGetTimestampPeriod(queue: WGPUQueue?): Float

expect fun wgpuDevicePoll(device: WGPUDevice?, wait: UInt, submissionIndex: NativeAddress?): UInt

expect fun wgpuDeviceCreateShaderModuleSpirV(device: WGPUDevice?, descriptor: WGPUShaderModuleDescriptorSpirV?): WGPUShaderModule?

expect fun wgpuSetLogCallback(callback: WGPULogCallback?, userdata: NativeAddress?): Unit

fun wgpuSetLogCallback(userdata: NativeAddress? = null, callback: (level: WGPULogLevel, message: WGPUStringView, userdata: NativeAddress?) -> Unit): WGPULogCallback {
    val holder = WGPULogCallback.allocate(callback)
    wgpuSetLogCallback(holder, userdata)
    return holder
}

expect fun wgpuSetLogLevel(level: WGPULogLevel): Unit

expect fun wgpuGetVersion(): UInt

expect fun wgpuDeviceGetNativeMetalDevice(device: WGPUDevice?): NativeAddress?

expect fun wgpuQueueGetNativeMetalCommandQueue(queue: WGPUQueue?): NativeAddress?

expect fun wgpuTextureGetNativeMetalTexture(texture: WGPUTexture?): NativeAddress?

expect fun wgpuRenderPassEncoderSetImmediates(encoder: WGPURenderPassEncoder?, offset: UInt, sizeBytes: UInt, data: NativeAddress?): Unit

expect fun wgpuComputePassEncoderSetImmediates(encoder: WGPUComputePassEncoder?, offset: UInt, sizeBytes: UInt, data: NativeAddress?): Unit

expect fun wgpuRenderBundleEncoderSetImmediates(encoder: WGPURenderBundleEncoder?, offset: UInt, sizeBytes: UInt, data: NativeAddress?): Unit

expect fun wgpuRenderPassEncoderMultiDrawIndirect(encoder: WGPURenderPassEncoder?, buffer: WGPUBuffer?, offset: ULong, count: UInt): Unit

expect fun wgpuRenderPassEncoderMultiDrawIndexedIndirect(encoder: WGPURenderPassEncoder?, buffer: WGPUBuffer?, offset: ULong, count: UInt): Unit

expect fun wgpuRenderPassEncoderMultiDrawIndirectCount(encoder: WGPURenderPassEncoder?, buffer: WGPUBuffer?, offset: ULong, count_buffer: WGPUBuffer?, count_buffer_offset: ULong, max_count: UInt): Unit

expect fun wgpuRenderPassEncoderMultiDrawIndexedIndirectCount(encoder: WGPURenderPassEncoder?, buffer: WGPUBuffer?, offset: ULong, count_buffer: WGPUBuffer?, count_buffer_offset: ULong, max_count: UInt): Unit

expect fun wgpuComputePassEncoderBeginPipelineStatisticsQuery(computePassEncoder: WGPUComputePassEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit

expect fun wgpuComputePassEncoderEndPipelineStatisticsQuery(computePassEncoder: WGPUComputePassEncoder?): Unit

expect fun wgpuRenderPassEncoderBeginPipelineStatisticsQuery(renderPassEncoder: WGPURenderPassEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit

expect fun wgpuRenderPassEncoderEndPipelineStatisticsQuery(renderPassEncoder: WGPURenderPassEncoder?): Unit

expect fun wgpuComputePassEncoderWriteTimestamp(computePassEncoder: WGPUComputePassEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit

expect fun wgpuRenderPassEncoderWriteTimestamp(renderPassEncoder: WGPURenderPassEncoder?, querySet: WGPUQuerySet?, queryIndex: UInt): Unit

expect fun wgpuDeviceStartGraphicsDebuggerCapture(device: WGPUDevice?): UInt

expect fun wgpuDeviceStopGraphicsDebuggerCapture(device: WGPUDevice?): Unit

