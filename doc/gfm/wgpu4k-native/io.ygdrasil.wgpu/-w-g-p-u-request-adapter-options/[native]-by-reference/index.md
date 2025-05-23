//[wgpu4k-native](../../../../index.md)/[io.ygdrasil.wgpu](../../index.md)/[WGPURequestAdapterOptions](../index.md)/[[native]ByReference](index.md)

# ByReference

[native]\
value class [ByReference](index.md)(val handler: [NativeAddress](../../../ffi/-native-address/index.md)) : [WGPURequestAdapterOptions](../index.md)

## Constructors

| | |
|---|---|
| [ByReference](-by-reference.md) | [native]<br>constructor(handler: [NativeAddress](../../../ffi/-native-address/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [backendType](backend-type.md) | [native]<br>open override var [backendType](backend-type.md): [WGPUBackendType](../../-w-g-p-u-backend-type/index.md) |
| [compatibleSurface](compatible-surface.md) | [native]<br>open override var [compatibleSurface](compatible-surface.md): [WGPUSurface](../../-w-g-p-u-surface/index.md)? |
| [featureLevel](feature-level.md) | [native]<br>open override var [featureLevel](feature-level.md): [WGPUFeatureLevel](../../-w-g-p-u-feature-level/index.md) |
| [forceFallbackAdapter](force-fallback-adapter.md) | [native]<br>open override var [forceFallbackAdapter](force-fallback-adapter.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [handler](handler.md) | [native]<br>open override val [handler](handler.md): [NativeAddress](../../../ffi/-native-address/index.md) |
| [nextInChain](next-in-chain.md) | [native]<br>open override var [nextInChain](next-in-chain.md): [NativeAddress](../../../ffi/-native-address/index.md)? |
| [powerPreference](power-preference.md) | [native]<br>open override var [powerPreference](power-preference.md): [WGPUPowerPreference](../../-w-g-p-u-power-preference/index.md) |

## Functions

| Name | Summary |
|---|---|
| [toCValue](../[native]to-c-value.md) | [native]<br>open fun [toCValue](../[native]to-c-value.md)(): [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPURequestAdapterOptions&gt; |