//[wgpu4k-native](../../../../index.md)/[io.ygdrasil.wgpu](../../index.md)/[WGPUPopErrorScopeCallbackInfo](../index.md)/[[native]ByValue](index.md)

# ByValue

[native]\
value class [ByValue](index.md)(val handle: [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUPopErrorScopeCallbackInfo&gt;) : [WGPUPopErrorScopeCallbackInfo](../index.md)

## Constructors

| | |
|---|---|
| [ByValue](-by-value.md) | [native]<br>constructor(handle: [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUPopErrorScopeCallbackInfo&gt;) |

## Properties

| Name | Summary |
|---|---|
| [callback](callback.md) | [native]<br>open override var [callback](callback.md): [CallbackHolder](../../../ffi/-callback-holder/index.md)&lt;[WGPUPopErrorScopeCallback](../../-w-g-p-u-pop-error-scope-callback/index.md)&gt;? |
| [handle](handle.md) | [native]<br>val [handle](handle.md): [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUPopErrorScopeCallbackInfo&gt; |
| [handler](handler.md) | [native]<br>open override val [handler](handler.md): [NativeAddress](../../../ffi/-native-address/index.md) |
| [mode](mode.md) | [native]<br>open override var [mode](mode.md): [WGPUCallbackMode](../../-w-g-p-u-callback-mode/index.md) |
| [nextInChain](next-in-chain.md) | [native]<br>open override var [nextInChain](next-in-chain.md): [WGPUChainedStruct](../../-w-g-p-u-chained-struct/index.md)? |
| [userdata1](userdata1.md) | [native]<br>open override var [userdata1](userdata1.md): [NativeAddress](../../../ffi/-native-address/index.md)? |
| [userdata2](userdata2.md) | [native]<br>open override var [userdata2](userdata2.md): [NativeAddress](../../../ffi/-native-address/index.md)? |

## Functions

| Name | Summary |
|---|---|
| [toCValue](../[native]to-c-value.md) | [native]<br>open fun [toCValue](../[native]to-c-value.md)(): [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUPopErrorScopeCallbackInfo&gt; |