// This file has been generated DO NOT EDIT !!!
package webgpu

typealias WGPUAdapterType = UInt

typealias WGPUAddressMode = UInt

typealias WGPUBackendType = UInt

typealias WGPUBlendFactor = UInt

typealias WGPUBlendOperation = UInt

typealias WGPUBufferBindingType = UInt

typealias WGPUBufferMapState = UInt

typealias WGPUCallbackMode = UInt

typealias WGPUCompareFunction = UInt

typealias WGPUCompilationInfoRequestStatus = UInt

typealias WGPUCompilationMessageType = UInt

typealias WGPUCompositeAlphaMode = UInt

typealias WGPUCreatePipelineAsyncStatus = UInt

typealias WGPUCullMode = UInt

typealias WGPUDeviceLostReason = UInt

typealias WGPUErrorFilter = UInt

typealias WGPUErrorType = UInt

typealias WGPUFeatureLevel = UInt

typealias WGPUFeatureName = UInt

typealias WGPUFilterMode = UInt

typealias WGPUFrontFace = UInt

typealias WGPUIndexFormat = UInt

typealias WGPULoadOp = UInt

typealias WGPUMapAsyncStatus = UInt

typealias WGPUMipmapFilterMode = UInt

typealias WGPUOptionalBool = UInt

typealias WGPUPopErrorScopeStatus = UInt

typealias WGPUPowerPreference = UInt

typealias WGPUPresentMode = UInt

typealias WGPUPrimitiveTopology = UInt

typealias WGPUQueryType = UInt

typealias WGPUQueueWorkDoneStatus = UInt

typealias WGPURequestAdapterStatus = UInt

typealias WGPURequestDeviceStatus = UInt

typealias WGPUSType = UInt

typealias WGPUSamplerBindingType = UInt

typealias WGPUStatus = UInt

typealias WGPUStencilOperation = UInt

typealias WGPUStorageTextureAccess = UInt

typealias WGPUStoreOp = UInt

typealias WGPUSurfaceGetCurrentTextureStatus = UInt

typealias WGPUTextureAspect = UInt

typealias WGPUTextureDimension = UInt

typealias WGPUTextureFormat = UInt

typealias WGPUTextureSampleType = UInt

typealias WGPUTextureViewDimension = UInt

typealias WGPUVertexFormat = UInt

typealias WGPUVertexStepMode = UInt

typealias WGPUWaitStatus = UInt

typealias WGPUWGSLLanguageFeatureName = UInt

typealias WGPUGles3MinorVersion = UInt

typealias WGPUDx12Compiler = UInt

typealias WGPULogLevel = UInt

typealias WGPUNativeSType = UInt

typealias WGPUBufferUsage = UInt
const val WGPUBufferUsage_None : WGPUBufferUsage = 0u
const val WGPUBufferUsage_MapRead : WGPUBufferUsage = 1u
const val WGPUBufferUsage_MapWrite : WGPUBufferUsage = 2u
const val WGPUBufferUsage_CopySrc : WGPUBufferUsage = 3u
const val WGPUBufferUsage_CopyDst : WGPUBufferUsage = 4u
const val WGPUBufferUsage_Index : WGPUBufferUsage = 5u
const val WGPUBufferUsage_Vertex : WGPUBufferUsage = 6u
const val WGPUBufferUsage_Uniform : WGPUBufferUsage = 7u
const val WGPUBufferUsage_Storage : WGPUBufferUsage = 8u
const val WGPUBufferUsage_Indirect : WGPUBufferUsage = 9u
const val WGPUBufferUsage_QueryResolve : WGPUBufferUsage = 10u

typealias WGPUColorWriteMask = UInt
const val WGPUColorWriteMask_None : WGPUColorWriteMask = 0u
const val WGPUColorWriteMask_Red : WGPUColorWriteMask = 1u
const val WGPUColorWriteMask_Green : WGPUColorWriteMask = 2u
const val WGPUColorWriteMask_Blue : WGPUColorWriteMask = 3u
const val WGPUColorWriteMask_Alpha : WGPUColorWriteMask = 4u
const val WGPUColorWriteMask_All : WGPUColorWriteMask = 5u

typealias WGPUMapMode = UInt
const val WGPUMapMode_None : WGPUMapMode = 0u
const val WGPUMapMode_Read : WGPUMapMode = 1u
const val WGPUMapMode_Write : WGPUMapMode = 2u

typealias WGPUShaderStage = UInt
const val WGPUShaderStage_None : WGPUShaderStage = 0u
const val WGPUShaderStage_Vertex : WGPUShaderStage = 1u
const val WGPUShaderStage_Fragment : WGPUShaderStage = 2u
const val WGPUShaderStage_Compute : WGPUShaderStage = 3u

typealias WGPUTextureUsage = UInt
const val WGPUTextureUsage_None : WGPUTextureUsage = 0u
const val WGPUTextureUsage_CopySrc : WGPUTextureUsage = 1u
const val WGPUTextureUsage_CopyDst : WGPUTextureUsage = 2u
const val WGPUTextureUsage_TextureBinding : WGPUTextureUsage = 3u
const val WGPUTextureUsage_StorageBinding : WGPUTextureUsage = 4u
const val WGPUTextureUsage_RenderAttachment : WGPUTextureUsage = 5u

typealias WGPUInstanceFlag = UInt
const val WGPUInstanceFlag_Default : WGPUInstanceFlag = 0u
const val WGPUInstanceFlag_Debug : WGPUInstanceFlag = 1u
const val WGPUInstanceFlag_Validation : WGPUInstanceFlag = 2u
const val WGPUInstanceFlag_DiscardHalLabels : WGPUInstanceFlag = 3u

typealias WGPUInstanceBackend = UInt
const val WGPUInstanceBackend_All : WGPUInstanceBackend = 0u
const val WGPUInstanceBackend_Vulkan : WGPUInstanceBackend = 1u
const val WGPUInstanceBackend_GL : WGPUInstanceBackend = 2u
const val WGPUInstanceBackend_Metal : WGPUInstanceBackend = 3u
const val WGPUInstanceBackend_DX12 : WGPUInstanceBackend = 4u
const val WGPUInstanceBackend_DX11 : WGPUInstanceBackend = 5u
const val WGPUInstanceBackend_BrowserWebGPU : WGPUInstanceBackend = 6u
const val WGPUInstanceBackend_Primary : WGPUInstanceBackend = 7u
const val WGPUInstanceBackend_Secondary : WGPUInstanceBackend = 8u

