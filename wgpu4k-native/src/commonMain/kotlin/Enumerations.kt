// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.wgpu

typealias WGPUBufferUsage = ULong
const val WGPUBufferUsage_None : WGPUBufferUsage = 0uL
/**
 * The buffer can be *mapped* on the CPU side in *read* mode (using @ref WGPUMapMode_Read).
 */
const val WGPUBufferUsage_MapRead : WGPUBufferUsage = 1uL
/**
 * The buffer can be *mapped* on the CPU side in *write* mode (using @ref WGPUMapMode_Write).
 * 
 * @note This usage is **not** required to set [mappedAtCreation] to [true] in @ref WGPUBufferDescriptor.
 */
const val WGPUBufferUsage_MapWrite : WGPUBufferUsage = 2uL
/**
 * The buffer can be used as the *source* of a GPU-side copy operation.
 */
const val WGPUBufferUsage_CopySrc : WGPUBufferUsage = 4uL
/**
 * The buffer can be used as the *destination* of a GPU-side copy operation.
 */
const val WGPUBufferUsage_CopyDst : WGPUBufferUsage = 8uL
/**
 * The buffer can be used as an Index buffer when doing indexed drawing in a render pipeline.
 */
const val WGPUBufferUsage_Index : WGPUBufferUsage = 16uL
/**
 * The buffer can be used as a Vertex buffer when using a render pipeline.
 */
const val WGPUBufferUsage_Vertex : WGPUBufferUsage = 32uL
/**
 * The buffer can be bound to a shader as a uniform buffer.
 */
const val WGPUBufferUsage_Uniform : WGPUBufferUsage = 64uL
/**
 * The buffer can be bound to a shader as a storage buffer.
 */
const val WGPUBufferUsage_Storage : WGPUBufferUsage = 128uL
/**
 * The buffer can store arguments for an indirect draw call.
 */
const val WGPUBufferUsage_Indirect : WGPUBufferUsage = 256uL
/**
 * The buffer can store the result of a timestamp or occlusion query.
 */
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

/**
 * Bitflags selecting which graphics backends to enable.
 */
typealias WGPUInstanceBackend = ULong
const val WGPUInstanceBackend_All : WGPUInstanceBackend = 0uL
const val WGPUInstanceBackend_Vulkan : WGPUInstanceBackend = 1uL
const val WGPUInstanceBackend_GL : WGPUInstanceBackend = 2uL
const val WGPUInstanceBackend_Metal : WGPUInstanceBackend = 4uL
const val WGPUInstanceBackend_DX12 : WGPUInstanceBackend = 8uL
const val WGPUInstanceBackend_BrowserWebGPU : WGPUInstanceBackend = 16uL
const val WGPUInstanceBackend_Primary : WGPUInstanceBackend = 29uL
const val WGPUInstanceBackend_Secondary : WGPUInstanceBackend = 2uL

/**
 * Bitflags controlling instance debugging and validation behavior.
 */
typealias WGPUInstanceFlag = ULong
const val WGPUInstanceFlag_Empty : WGPUInstanceFlag = 0uL
const val WGPUInstanceFlag_Debug : WGPUInstanceFlag = 1uL
const val WGPUInstanceFlag_Validation : WGPUInstanceFlag = 2uL
const val WGPUInstanceFlag_DiscardHalLabels : WGPUInstanceFlag = 4uL
const val WGPUInstanceFlag_AllowUnderlyingNoncompliantAdapter : WGPUInstanceFlag = 8uL
const val WGPUInstanceFlag_GPUBasedValidation : WGPUInstanceFlag = 16uL
const val WGPUInstanceFlag_ValidationIndirectCall : WGPUInstanceFlag = 32uL
const val WGPUInstanceFlag_AutomaticTimestampNormalization : WGPUInstanceFlag = 64uL
const val WGPUInstanceFlag_Default : WGPUInstanceFlag = 128uL
const val WGPUInstanceFlag_Debugging : WGPUInstanceFlag = 256uL
const val WGPUInstanceFlag_AdvancedDebugging : WGPUInstanceFlag = 512uL
const val WGPUInstanceFlag_WithEnv : WGPUInstanceFlag = 1024uL

