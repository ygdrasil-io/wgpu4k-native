//[wgpu4k-native](../../../../index.md)/[io.ygdrasil.wgpu](../../index.md)/[WGPURenderPassColorAttachment](../index.md)/[[native]ByReference](index.md)

# ByReference

[native]\
value class [ByReference](index.md)(val handler: [NativeAddress](../../../ffi/-native-address/index.md)) : [WGPURenderPassColorAttachment](../index.md)

## Constructors

| | |
|---|---|
| [ByReference](-by-reference.md) | [native]<br>constructor(handler: [NativeAddress](../../../ffi/-native-address/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [clearValue](clear-value.md) | [native]<br>open override val [clearValue](clear-value.md): [WGPUColor](../../-w-g-p-u-color/index.md) |
| [depthSlice](depth-slice.md) | [native]<br>open override var [depthSlice](depth-slice.md): [UInt](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-int/index.html) |
| [handler](handler.md) | [native]<br>open override val [handler](handler.md): [NativeAddress](../../../ffi/-native-address/index.md) |
| [loadOp](load-op.md) | [native]<br>open override var [loadOp](load-op.md): [WGPULoadOp](../../-w-g-p-u-load-op/index.md) |
| [nextInChain](next-in-chain.md) | [native]<br>open override var [nextInChain](next-in-chain.md): [NativeAddress](../../../ffi/-native-address/index.md)? |
| [resolveTarget](resolve-target.md) | [native]<br>open override var [resolveTarget](resolve-target.md): [WGPUTextureView](../../-w-g-p-u-texture-view/index.md)? |
| [storeOp](store-op.md) | [native]<br>open override var [storeOp](store-op.md): [WGPUStoreOp](../../-w-g-p-u-store-op/index.md) |
| [view](view.md) | [native]<br>open override var [view](view.md): [WGPUTextureView](../../-w-g-p-u-texture-view/index.md)? |

## Functions

| Name | Summary |
|---|---|
| [toCValue](../[native]to-c-value.md) | [native]<br>open fun [toCValue](../[native]to-c-value.md)(): [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPURenderPassColorAttachment&gt; |