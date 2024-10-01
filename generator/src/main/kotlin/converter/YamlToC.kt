package converter

import convertToKotlinClassName
import convertToKotlinFunctionName
import domain.CLibraryModel
import domain.YamlModel
import domain.toCType


internal fun YamlModel.toCModel(): Pair<YamlModel, CLibraryModel> {
    val pointers = convertToPointer()
    val functions = convertToCLibraryFunctions()
    val enumerations = convertToCLibraryEnumerations()
    val structures = structs.map {
        CLibraryModel.Structure(
            it.name.convertToKotlinClassName(),
            it.members.map { it.name to it.type.toCType() }
        )
    }

    return this to CLibraryModel(pointers, functions, enumerations, structures)
}

private fun YamlModel.convertToCLibraryEnumerations() =
    enums.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName()) }

private fun YamlModel.convertToCLibraryFunctions() = functions.map {
    CLibraryModel.Function(
        it.name.convertToKotlinFunctionName(),
        it.returns.let { it?.type }.toCType(),
        it.args.map { it.name to it.type.toCType() }
    )
} + objects.flatMap { reference ->
    reference.methods.map {
        val name = "${reference.name}_${it.name}".convertToKotlinFunctionName()
        val args = listOf(YamlModel.Function.Arg("handler", "", "object.${reference.name}")) + it.args
        CLibraryModel.Function(
            name,
            it.returns.let { it?.type }.toCType(),
            args.map { it.name to it.type.toCType() }
        )
    }
}

private fun YamlModel.convertToPointer(): List<CLibraryModel.Pointer> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }
    return pointers
}