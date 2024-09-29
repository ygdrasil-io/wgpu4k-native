@file:OptIn(ExperimentalForeignApi::class)

package webgpu

import kotlinx.cinterop.ExperimentalForeignApi
    
actual class WGPUAdapter(val value: webgpu.native.WGPUAdapter)
actual class WGPUBindGroup(val value: webgpu.native.WGPUBindGroup)
actual class WGPUBindGroupLayout(val value: webgpu.native.WGPUBindGroupLayout)
actual class WGPUBuffer(val value: webgpu.native.WGPUBuffer)
actual class WGPUCommandBuffer(val value: webgpu.native.WGPUCommandBuffer)
actual class WGPUCommandEncoder(val value: webgpu.native.WGPUCommandEncoder)
actual class WGPUComputePassEncoder(val value: webgpu.native.WGPUComputePassEncoder)
actual class WGPUComputePipeline(val value: webgpu.native.WGPUComputePipeline)
actual class WGPUDevice(val value: webgpu.native.WGPUDevice)
actual class WGPUInstance(val value: webgpu.native.WGPUInstance)
actual class WGPUPipelineLayout(val value: webgpu.native.WGPUPipelineLayout)
actual class WGPUQuerySet(val value: webgpu.native.WGPUQuerySet)
actual class WGPUQueue(val value: webgpu.native.WGPUQueue)
actual class WGPURenderBundle(val value: webgpu.native.WGPURenderBundle)
actual class WGPURenderBundleEncoder(val value: webgpu.native.WGPURenderBundleEncoder)
actual class WGPURenderPassEncoder(val value: webgpu.native.WGPURenderPassEncoder)
actual class WGPURenderPipeline(val value: webgpu.native.WGPURenderPipeline)
actual class WGPUSampler(val value: webgpu.native.WGPUSampler)
actual class WGPUShaderModule(val value: webgpu.native.WGPUShaderModule)
actual class WGPUSurface(val value: webgpu.native.WGPUSurface)
actual class WGPUTexture(val value: webgpu.native.WGPUTexture)
actual class WGPUTextureView(val value: webgpu.native.WGPUTextureView)
