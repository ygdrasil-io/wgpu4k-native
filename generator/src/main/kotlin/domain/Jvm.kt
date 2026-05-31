package domain

internal fun typeToJvmLayout(type: NativeModel.Type) = when (type) {
    NativeModel.Reference.OpaquePointer,
    is NativeModel.Reference.Pointer,
    NativeModel.Reference.CString,
    is NativeModel.Array,
    is NativeModel.Reference.Callback,
    NativeModel.Void,
    is NativeModel.Reference.Structure -> "io.ygdrasil.kffi.C_POINTER"

    NativeModel.Primitive.UInt16 -> "io.ygdrasil.kffi.C_SHORT"
    NativeModel.Primitive.UInt8 -> "io.ygdrasil.kffi.C_BYTE"

    NativeModel.Primitive.Bool,
    NativeModel.Primitive.UInt32,
    NativeModel.Primitive.Int32 -> "io.ygdrasil.kffi.C_INT"

    NativeModel.Primitive.UInt64,
    NativeModel.Primitive.Int64 -> "io.ygdrasil.kffi.C_LONG"

    NativeModel.Primitive.Float32 -> "io.ygdrasil.kffi.C_FLOAT"
    NativeModel.Primitive.Float64 -> "io.ygdrasil.kffi.C_DOUBLE"

    is NativeModel.Reference.Enumeration -> "io.ygdrasil.kffi.C_INT"
    is NativeModel.Reference.StructureField -> "${type.name}.layout"
}