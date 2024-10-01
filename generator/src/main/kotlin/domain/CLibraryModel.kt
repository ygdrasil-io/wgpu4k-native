package domain

import convertToKotlinClassName


data class CLibraryModel(
    val pointers: List<Pointer>,
    val functions: List<Function>,
    val enumerations: List<Enumeration>,
) {

    class Enumeration(val name: String)

    sealed interface Type
    object CString : Type
    class Reference(val name: String) : Type
    class Array(val subType: Type) : Type
    sealed interface Primitive: Type {
        object Bool : Primitive
        object UInt32 : Primitive
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
        val doc: String,
        val type: String,
        val members: List<Member>,
        val free_members: Boolean = false,
        val extends: List<String> = listOf()
    ) {
        data class Member(
            val name: String,
            val doc: String,
            val type: String,
            val optional: Boolean = false,
            val pointer: String? = null
        )
    }
}

internal fun CLibraryModel.Type.toFunctionKotlinType(): String = when (this) {
    is CLibraryModel.Reference -> name
    is CLibraryModel.CString -> "String"
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
}

internal fun String?.toCType(): CLibraryModel.Type {
    return when {
        this == null -> CLibraryModel.Primitive.Void
        startsWith("struct.") || startsWith("object.") || startsWith("enum.")
            -> return CLibraryModel.Reference(split(".").last().convertToKotlinClassName())
        startsWith("bitflag.") -> CLibraryModel.Primitive.UInt64
        equals("string") -> CLibraryModel.CString
        equals("bool") -> CLibraryModel.Primitive.Bool
        equals("usize") -> CLibraryModel.Primitive.UInt64
        equals("uint64") -> CLibraryModel.Primitive.UInt64
        equals("uint32") -> CLibraryModel.Primitive.UInt32
        equals("int32") -> CLibraryModel.Primitive.Int32
        equals("float32") -> CLibraryModel.Primitive.Float32
        equals("c_void") -> CLibraryModel.Primitive.Void
        startsWith("array<") -> CLibraryModel.Array(substring(6, length - 1).toCType())
        else -> error("unknown type $this")
    }
}
