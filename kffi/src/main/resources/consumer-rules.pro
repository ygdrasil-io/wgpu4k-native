# kffi engine classes are invoked from native code via RegisterNatives; keep them.
-keep class org.graphiks.kffi.engine.** { *; }
# MemoryBuffer/allocator internals are called reflectively-ish from generated bindings.
-keep class org.graphiks.kffi.** { *; }
