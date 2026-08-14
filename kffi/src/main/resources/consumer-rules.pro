# kffi engine classes are invoked from native code via RegisterNatives; keep them.
-keep class org.graphiks.kffi.** { *; }
