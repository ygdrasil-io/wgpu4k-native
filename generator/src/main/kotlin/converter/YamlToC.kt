package converter

import convertToKotlinCallbackName
import convertToKotlinCallbackStructureName
import convertToKotlinClassName
import convertToKotlinFunctionName
import convertToKotlinVariableName
import domain.CLibraryModel
import domain.YamlModel
import domain.toCType


internal fun YamlModel.toCModel(): Pair<YamlModel, CLibraryModel> {
    val pointers = convertToPointer()
    val functions = convertToCLibraryFunctions()
    val enumerations = convertToCLibraryEnumerations()
    val structures = generateCLibraryStructures()
    val callbacks = callbacks.map {
        CLibraryModel.Callback(
            it.name.convertToKotlinCallbackName(),
            it.args.map { it.name.convertToKotlinVariableName() to it.type.toCType(it.pointer != null) } +
                    listOf(
                        "userdata1" to CLibraryModel.Reference.OpaquePointer,
                        "userdata2" to CLibraryModel.Reference.OpaquePointer
                    )
        )
    }

    return this to CLibraryModel(pointers, functions, enumerations, structures, callbacks)
}

private fun YamlModel.generateCLibraryStructures() = structs.map {
    CLibraryModel.Structure(
        it.name.convertToKotlinClassName(),
        it.members.map {
            Triple(
                it.name.convertToKotlinVariableName(),
                it.type.toCType(it.pointer != null),
                if (it.type.toCType(it.pointer != null) is CLibraryModel.Reference &&
                    it.type.toCType(it.pointer != null) !is CLibraryModel.Reference.Enumeration) "?" else ""
            )
        }
    )
} + listOf(
    CLibraryModel.Structure(
        "WGPUChainedStruct", listOf(
            Triple("next", CLibraryModel.Reference.Structure("WGPUChainedStruct"), "?"),
            Triple("sType", CLibraryModel.Reference.Enumeration("WGPUSType"), "")
        )
    ),
    CLibraryModel.Structure(
        "WGPUChainedStructOut", listOf(
            Triple("next", CLibraryModel.Reference.Structure("WGPUChainedStructOut"), "?"),
            Triple("sType", CLibraryModel.Reference.Enumeration("WGPUSType"), "")
        )
    )
) + callbacks.map {
    val name = it.name.convertToKotlinCallbackStructureName()
    CLibraryModel.Structure(
        name, listOf(
            Triple("nextInChain", CLibraryModel.Reference.Structure("WGPUChainedStruct"), "?"),
            Triple("callback", CLibraryModel.Reference.Callback(it.name.convertToKotlinCallbackName()), "?"),
            Triple("userdata1", CLibraryModel.Reference.OpaquePointer, "?"),
            Triple("userdata2", CLibraryModel.Reference.OpaquePointer, "?")
        )
    )
}

private fun YamlModel.convertToCLibraryEnumerations() =
    enums.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName()) } +
            bitflags.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName()) }


private fun YamlModel.convertToCLibraryFunctions() = functions.map {
    CLibraryModel.Function(
        it.name.convertToKotlinFunctionName(),
        it.returns.let { it?.type }.toCType(),
        it.args.map { it.name.convertToKotlinVariableName() to it.type.toCType(it.pointer != null) }
    )
} + objects.flatMap { reference ->
    reference.methods.map {
        val name = "${reference.name}_${it.name}".convertToKotlinFunctionName()
        val args = listOf(YamlModel.Function.Arg("handler", "", "object.${reference.name}")) + it.args
        CLibraryModel.Function(
            name,
            it.returns.let { it?.type }.toCType(),
            args.map { it.name.convertToKotlinVariableName() to it.type.toCType(it.pointer != null) }
        )
    }
}

private fun YamlModel.convertToPointer(): List<CLibraryModel.Pointer> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }
    return pointers
}