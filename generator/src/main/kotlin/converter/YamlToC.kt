package converter

import convertToKotlinCallbackName
import convertToKotlinCallbackStructureName
import convertToKotlinClassName
import convertToKotlinFunctionName
import convertToKotlinVariableName
import domain.CLibraryModel
import domain.CLibraryModel.Type
import domain.YamlModel
import domain.toCType

internal fun YamlModel.toCModel(): CLibraryModel {
    val pointers = convertToPointer()
    val functions = convertToCLibraryFunctions()
    val enumerations = convertToCLibraryEnumerations()
    val structures = generateCLibraryStructures()
    val callbacks = callbacks.map {
        CLibraryModel.Callback(
            it.name.convertToKotlinCallbackName(),
            it.args.map {
                it.name.convertToKotlinVariableName() to it.type.toCType(
                    it.pointer != null,
                    it.pointer == "mutable"
                )
            } +
                    listOf(
                        "userdata1" to CLibraryModel.Reference.OpaquePointer,
                        "userdata2" to CLibraryModel.Reference.OpaquePointer
                    )
        )
    }

    return CLibraryModel(pointers, functions, enumerations, structures, callbacks)
}

private fun YamlModel.generateCLibraryStructures() = structs.map {
    val members = when  {
        it.type == "base_in" ->  listOf(YamlModel.Struct.Member("nextInChain", "", "struct.chained_struct", true, "mutable")) + it.members
        it.type == "extension_in" ->  listOf(YamlModel.Struct.Member("chain", "", "struct.chained_struct")) + it.members
        it.type == "base_out" ->  listOf(YamlModel.Struct.Member("nextInChain", "", "struct.chained_struct_out", true, "mutable")) + it.members
        it.type == "standalone" -> it.members
        else -> error("unsuported type ${it.type}")
    }

    CLibraryModel.Structure(
        it.name.convertToKotlinClassName(),
        members.flatMap {
            Triple(
                it.name.convertToKotlinVariableName(),
                it.type.toCType(it.pointer != null, it.pointer == "mutable"),
                if (it.type.toCType(it.pointer != null, it.pointer == "mutable") is CLibraryModel.Array ||
                    (it.type.toCType(it.pointer != null, it.pointer == "mutable") is CLibraryModel.Reference &&
                            it.type.toCType(
                                it.pointer != null,
                                it.pointer == "mutable"
                            ) !is CLibraryModel.Reference.Enumeration &&
                            it.type.toCType(
                                it.pointer != null,
                                it.pointer == "mutable"
                            ) !is CLibraryModel.Reference.StructureField)
                ) "?" else ""
            ).let {
                when (it.second) {
                    is CLibraryModel.Array -> listOf(
                        it.generateArrayCounter(),
                        it
                    )

                    else -> listOf(it)
                }
            }
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
    ),
    CLibraryModel.Structure(
        "WGPUStringView", listOf(
            Triple("data", CLibraryModel.Reference.CString, "?"),
            Triple("length", CLibraryModel.Primitive.UInt64, "")
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


private fun YamlModel.convertToCLibraryFunctions() : List<CLibraryModel.Function> = functions.map {
    CLibraryModel.Function(
        it.name.convertToKotlinFunctionName(),
        it.returns.let { it?.type }.toCType(it.returns?.pointer != null, it.returns?.pointer == "mutable"),
        // Uncomnent when success to handle structure filed
        /*if (it.callback != null) CLibraryModel.Reference.StructureField("WGPUFuture") else it.returns.let { it?.type }
            .toCType(it.returns?.pointer != null),*/
        convertToCFunctionArgs(it.args, it.callback)
    )
} + objects.flatMap { reference ->
    (listOf(
        YamlModel.Function("release", "")
    ) + reference.methods)
        .map {
        val name = "${reference.name}_${it.name}".convertToKotlinFunctionName()
        val args = listOf(YamlModel.Function.Arg("handler", "", "object.${reference.name}")) + it.args
        CLibraryModel.Function(
            name,
            it.returns.let { it?.type }.toCType(it.returns?.pointer != null, it.returns?.pointer == "mutable"),
            // Uncomnent when success to handle structure filed
            /*if (it.callback != null) CLibraryModel.Reference.StructureField("WGPUFuture") else it.returns.let { it?.type }
                .toCType(it.returns?.pointer != null),*/
            convertToCFunctionArgs(args, it.callback)
        )
    }
}

fun convertToCFunctionArgs(args: List<YamlModel.Function.Arg>, callback: String?): List<Pair<String, Type>> {
    return args.flatMap { arg ->
        (arg.name.convertToKotlinVariableName() to arg.type.toCType(
            arg.pointer != null,
            arg.pointer == "mutable"
        )).let { when(it.second) {
            is CLibraryModel.Array -> listOf(
                it.generateArrayCounter(),
                it
            )
            else -> listOf(it)
        } }
    } + callback.injectCallbackInfoStructure()
}

//it.name.convertToKotlinVariableName() to

private fun String?.injectCallbackInfoStructure(): List<Pair<String, Type>> = when {
    this != null -> listOf("callbackInfo" to CLibraryModel.Reference.StructureField(split(".")[1].convertToKotlinCallbackStructureName()))
    else -> emptyList()
}

private fun YamlModel.convertToPointer(): List<CLibraryModel.Pointer> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }
    return pointers
}

private fun Triple<String, CLibraryModel.Type, String>.generateArrayCounter() = let { (name, _, _) ->
    val newName = when {
        name.endsWith("ies") -> name.removeSuffix("ies") + "yCount"
        else -> name.removeSuffix("s") + "Count"
    }
    Triple(newName, CLibraryModel.Primitive.UInt64, "")
}

private fun Pair<String, CLibraryModel.Type>.generateArrayCounter() = let { (name, _) ->
    val newName = when {
        name.endsWith("ies") -> name.removeSuffix("ies") + "yCount"
        else -> name.removeSuffix("s") + "Count"
    }
    newName to CLibraryModel.Primitive.UInt64
}