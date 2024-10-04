package domain

import convertToKotlinCallbackName
import convertToKotlinCallbackStructureName
import convertToKotlinClassName


data class CLibraryModel(
    val pointers: List<Pointer>,
    val functions: List<Function>,
    val enumerations: List<Enumeration>,
    val structures: List<Structure>,
    val callbacks: List<Callback>,
) {

    class Enumeration(val name: String)

    sealed interface Type
    sealed class Reference(val name: String) : Type {
        class Pointer(name: String) : Reference(name)
        class Callback(name: String) : Reference(name)
        class Structure(name: String) : Reference(name)
        class Enumeration(name: String) : Reference(name)
        object CString : Reference("CString")
    }

    class Array(val subType: Type) : Type
    sealed interface Primitive : Type {
        object Bool : Primitive
        object UInt32 : Primitive
        object UInt16 : Primitive
        object Int32 : Primitive
        object UInt64 : Primitive
        object Int64 : Primitive
        object Float32 : Primitive
        object Float64 : Primitive
        object Void : Primitive
    }

    data class Pointer(val name: String)
    data class Function(val name: String, val returnType: Type, val args: List<Pair<String, Type>>)

    data class Structure(
        val name: String,
        val members: List<Triple<String, Type, String>>
    )

    data class Callback(
        val name: String,
        val members: List<Pair<String, Type>>,
    )

}

internal fun CLibraryModel.Type.toFunctionKotlinType(): String = when (this) {
    is CLibraryModel.Reference.Callback -> "CallbackHolder<${name}>"
    is CLibraryModel.Reference -> name
    is CLibraryModel.Array -> "Long"
    is CLibraryModel.Primitive -> this.toPrimitiveKotlinType()
}

internal fun CLibraryModel.Primitive.toPrimitiveKotlinType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    is CLibraryModel.Primitive.Int64 -> "Long"
    is CLibraryModel.Primitive.Void -> "Unit"
    CLibraryModel.Primitive.Bool -> "Boolean"
    CLibraryModel.Primitive.UInt64 -> "ULong"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
}

internal fun String?.toCType(): CLibraryModel.Type {
    return when {
        this == null -> CLibraryModel.Primitive.Void
        startsWith("struct.")
            -> return CLibraryModel.Reference.Structure(split(".").last().convertToKotlinClassName())
        startsWith("object.")
            -> return CLibraryModel.Reference.Pointer(split(".").last().convertToKotlinClassName())
        startsWith("enum.")
            -> return CLibraryModel.Reference.Enumeration(split(".").last().convertToKotlinClassName())
        startsWith("callback.")
            -> return CLibraryModel.Reference.Structure(split(".").last().convertToKotlinCallbackStructureName())
        startsWith("bitflag.") -> CLibraryModel.Primitive.UInt64
        equals("string") -> CLibraryModel.Reference.CString
        equals("bool") -> CLibraryModel.Primitive.Bool
        equals("usize") -> CLibraryModel.Primitive.UInt64
        equals("uint64") -> CLibraryModel.Primitive.UInt64
        equals("uint32") -> CLibraryModel.Primitive.UInt32
        equals("uint16") -> CLibraryModel.Primitive.UInt16
        equals("int32") -> CLibraryModel.Primitive.Int32
        equals("float32") -> CLibraryModel.Primitive.Float32
        equals("float64") -> CLibraryModel.Primitive.Float64
        equals("c_void") -> CLibraryModel.Primitive.Void
        startsWith("array<") -> CLibraryModel.Array(substring(6, length - 1).toCType())
        else -> error("unknown type $this")
    }
}
