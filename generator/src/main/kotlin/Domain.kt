import kotlinx.serialization.Serializable

val disclamer = "// This file has been generated DO NOT EDIT !!!"

internal fun String.convertToKotlinClassName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")
    .let { "WGPU$it" }
internal fun String.convertToKotlinFunctionName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")
    .let { "wgpu$it" }

internal fun String.toKotlinType() = when {
    startsWith("struct.")
            || startsWith("array<")
            || startsWith("object.")
            || equals("string") -> "Long"

    startsWith("c_void") -> "Unit"
    startsWith("enum.")
            || equals("bool") -> "UInt"
    equals("float32") -> "Float"
    equals("float64") -> "Double"
    equals("int32") -> "Int"
    equals("uint32") -> "UInt"
    equals("int64")
            || equals("usize") -> "Long"
    equals("uint64")
            || startsWith("bitflag.") -> "ULong"
    else -> error("unknown type $this")
}

data class CLibraryModel(
    val pointers: List<Pointer>,
) {

    sealed interface Type
    object CString : Type
    object Void : Type
    class Reference(val name: String) : Type

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