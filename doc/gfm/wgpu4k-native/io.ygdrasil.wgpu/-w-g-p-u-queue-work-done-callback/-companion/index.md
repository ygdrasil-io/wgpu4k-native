//[wgpu4k-native](../../../../index.md)/[io.ygdrasil.wgpu](../../index.md)/[WGPUQueueWorkDoneCallback](../index.md)/[Companion](index.md)

# Companion

[common]\
expect object [Companion](index.md)

[android, jvm, native]\
actual object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [allocate](allocate.md) | [common, android, jvm, native]<br>[common]<br>expect fun [allocate](allocate.md)(allocator: [MemoryAllocator](../../../ffi/-memory-allocator/index.md), callback: [WGPUQueueWorkDoneCallback](../index.md)): [CallbackHolder](../../../ffi/-callback-holder/index.md)&lt;[WGPUQueueWorkDoneCallback](../index.md)&gt;<br>[android, jvm, native]<br>actual fun [allocate](allocate.md)(allocator: [MemoryAllocator](../../../ffi/-memory-allocator/index.md), callback: [WGPUQueueWorkDoneCallback](../index.md)): [CallbackHolder](../../../ffi/-callback-holder/index.md)&lt;[WGPUQueueWorkDoneCallback](../index.md)&gt; |