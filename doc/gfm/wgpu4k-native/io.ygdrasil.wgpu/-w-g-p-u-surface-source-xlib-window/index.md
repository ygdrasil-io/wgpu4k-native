//[wgpu4k-native](../../../index.md)/[io.ygdrasil.wgpu](../index.md)/[WGPUSurfaceSourceXlibWindow](index.md)

# WGPUSurfaceSourceXlibWindow

expect interface [WGPUSurfaceSourceXlibWindow](index.md)actual interface [WGPUSurfaceSourceXlibWindow](index.md)actual interface [WGPUSurfaceSourceXlibWindow](index.md) : [CStructure](../../ffi/[jvm]-c-structure/index.md)actual interface [WGPUSurfaceSourceXlibWindow](index.md)

Chained in @ref WGPUSurfaceDescriptor to make an @ref WGPUSurface wrapping an [Xlib](https://www.x.org/releases/current/doc/libX11/libX11/libX11.html)Window.

#### Inheritors

| |
|---|
| [ByReference]([android]-by-reference/index.md) |
| [ByValue]([android]-by-value/index.md) |
| [ByReference]([jvm]-by-reference/index.md) |
| [ByReference]([native]-by-reference/index.md) |
| [ByValue]([native]-by-value/index.md) |

## Types

| Name | Summary |
|---|---|
| ByReference | [android, jvm, native]<br>[android]<br>class [ByReference]([android]-by-reference/index.md)(val handle: WGPUSurfaceSourceXlibWindow.ByReference = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByReference(com.sun.jna.Pointer.NULL)) : [WGPUSurfaceSourceXlibWindow](index.md)<br>[jvm]<br>@[JvmInline](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [ByReference]([jvm]-by-reference/index.md)(val handler: [NativeAddress](../../ffi/-native-address/index.md)) : [WGPUSurfaceSourceXlibWindow](index.md)<br>[native]<br>value class [ByReference]([native]-by-reference/index.md)(val handler: [NativeAddress](../../ffi/-native-address/index.md)) : [WGPUSurfaceSourceXlibWindow](index.md) |
| ByValue | [android, native]<br>[android]<br>class [ByValue]([android]-by-value/index.md)(val handle: WGPUSurfaceSourceXlibWindow.ByValue = io.ygdrasil.wgpu.android.WGPUSurfaceSourceXlibWindow.ByValue(com.sun.jna.Pointer.NULL)) : [WGPUSurfaceSourceXlibWindow](index.md)<br>[native]<br>value class [ByValue]([native]-by-value/index.md)(val handle: [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUSurfaceSourceXlibWindow&gt;) : [WGPUSurfaceSourceXlibWindow](index.md) |
| [Companion](-companion/index.md) | [common, android, jvm, native]<br>[common]<br>expect object [Companion](-companion/index.md)<br>[android, jvm, native]<br>actual object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [chain](chain.md) | [common, android, jvm, native]<br>[common]<br>expect abstract val [chain](chain.md): [WGPUChainedStruct](../-w-g-p-u-chained-struct/index.md)<br>[android, jvm, native]<br>actual abstract val [chain](chain.md): [WGPUChainedStruct](../-w-g-p-u-chained-struct/index.md) |
| [display](display.md) | [common]<br>expect abstract var [display](display.md): [NativeAddress](../../ffi/-native-address/index.md)?<br>A pointer to the [Display](https://www.x.org/releases/current/doc/libX11/libX11/libX11.html#Opening_the_Display) connected to the X server.<br>[android, jvm, native]<br>[android, jvm, native]<br>actual abstract var [display](display.md): [NativeAddress](../../ffi/-native-address/index.md)? |
| handler | [jvm, common, android, native]<br>[jvm]<br>abstract val [handler](../../ffi/[jvm]-c-structure/handler.md): [NativeAddress](../../ffi/-native-address/index.md)<br>[common]<br>expect abstract val [handler](handler.md): [NativeAddress](../../ffi/-native-address/index.md)<br>[android, native]<br>actual abstract val [handler](handler.md): [NativeAddress](../../ffi/-native-address/index.md) |
| [window](window.md) | [common]<br>expect abstract var [window](window.md): [ULong](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-long/index.html)<br>The [Window](https://www.x.org/releases/current/doc/libX11/libX11/libX11.html#Creating_Windows) that will be wrapped by the @ref WGPUSurface.<br>[android, jvm, native]<br>[android, jvm, native]<br>actual abstract var [window](window.md): [ULong](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-long/index.html) |

## Functions

| Name | Summary |
|---|---|
| [get](../../ffi/[jvm]-c-structure/get.md) | [jvm]<br>open fun [get](../../ffi/[jvm]-c-structure/get.md)(layout: StructLayout, offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [MemorySegment](../../ffi/-memory-segment/index.md)<br>open fun [get](../../ffi/[jvm]-c-structure/get.md)(layout: ValueLayout, offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [MemorySegment](../../ffi/-memory-segment/index.md) |
| [getDouble](../../ffi/[jvm]-c-structure/get-double.md) | [jvm]<br>open fun [getDouble](../../ffi/[jvm]-c-structure/get-double.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html) |
| [getFloat](../../ffi/[jvm]-c-structure/get-float.md) | [jvm]<br>open fun [getFloat](../../ffi/[jvm]-c-structure/get-float.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) |
| [getInt](../../ffi/[jvm]-c-structure/get-int.md) | [jvm]<br>open fun [getInt](../../ffi/[jvm]-c-structure/get-int.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |
| [getShort](../../ffi/[jvm]-c-structure/get-short.md) | [jvm]<br>open fun [getShort](../../ffi/[jvm]-c-structure/get-short.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [Short](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-short/index.html) |
| [getUInt](../../ffi/[jvm]-c-structure/get-u-int.md) | [jvm]<br>open fun [getUInt](../../ffi/[jvm]-c-structure/get-u-int.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [UInt](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-int/index.html) |
| [getULong](../../ffi/[jvm]-c-structure/get-u-long.md) | [jvm]<br>open fun [getULong](../../ffi/[jvm]-c-structure/get-u-long.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [ULong](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-long/index.html) |
| [getUShort](../../ffi/[jvm]-c-structure/get-u-short.md) | [jvm]<br>open fun [getUShort](../../ffi/[jvm]-c-structure/get-u-short.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)): [UShort](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-short/index.html) |
| [set](../../ffi/[jvm]-c-structure/set.md) | [jvm]<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [Short](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-short/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [UInt](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-int/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [ULong](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-long/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), value: [UShort](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-u-short/index.html))<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(layout: StructLayout, offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), address: [NativeAddress](../../ffi/-native-address/index.md)?)<br>open fun [set](../../ffi/[jvm]-c-structure/set.md)(layout: ValueLayout, offset: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html), address: [NativeAddress](../../ffi/-native-address/index.md)?) |
| toCValue | [android, native]<br>[android]<br>open fun [toCValue]([android]to-c-value.md)(): WGPUSurfaceSourceXlibWindow.ByValue<br>[native]<br>open fun [toCValue]([native]to-c-value.md)(): [CValue](https://kotlinlang.org/api/core/kotlin-stdlib/kotlinx.cinterop/-c-value/index.html)&lt;WGPUSurfaceSourceXlibWindow&gt; |
| [toReference](to-reference.md) | [android]<br>open fun [toReference](to-reference.md)(): WGPUSurfaceSourceXlibWindow.ByReference |