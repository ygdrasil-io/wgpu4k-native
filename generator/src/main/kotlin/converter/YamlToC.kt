package converter

import convertToEnumValueName
import convertToKotlinCallbackName
import convertToKotlinCallbackStructureName
import convertToKotlinClassName
import convertToKotlinFunctionName
import convertToKotlinVariableName
import domain.CLibraryModel
import domain.CLibraryModel.Type
import domain.YamlModel
import domain.YamlModel.Enum.Entry
import domain.YamlModelV2
import domain.toCType

internal fun YamlModelV2.toCModel(): CLibraryModel {
    val pointers = convertToPointer()
    val functions = convertToCLibraryFunctions()
        .injectWgpuFunctions()
    val enumerations = convertToCLibraryEnumerations()
    val structures = generateCLibraryStructures()
        .calculateSizeAndPadding()
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
    }.injectWgpuCallbacks()

    return CLibraryModel(pointers, functions, enumerations, structures, callbacks)
}

private fun List<CLibraryModel.Callback>.injectWgpuCallbacks(): List<CLibraryModel.Callback> {
    return this + listOf(
        CLibraryModel.Callback(
            "WGPULogCallback",
            listOf(
                "level" to CLibraryModel.Reference.Enumeration("WGPULogLevel"),
                "message" to CLibraryModel.Reference.StructureField("WGPUStringView"),
                "userdata" to CLibraryModel.Reference.OpaquePointer
            )
        )
    )
}

private fun List<CLibraryModel.Function>.injectWgpuFunctions(): List<CLibraryModel.Function> {
    return this + listOf(
        CLibraryModel.Function(
            "wgpuSetLogLevel",
            CLibraryModel.Void,
            listOf("level" to CLibraryModel.Reference.Enumeration("WGPULogLevel"))
        ),
        CLibraryModel.Function(
            "wgpuSetLogCallback",
            CLibraryModel.Void,
            listOf(
                "callback" to CLibraryModel.Reference.Callback("WGPULogCallback"),
                "userdata" to CLibraryModel.Reference.OpaquePointer
            )
        )
    )
}

private fun YamlModelV2.generateCLibraryStructures() = structs.map {
    val members = when {
        it.type == "base_in" -> listOf(
            YamlModelV2.Struct.Member(
                "nextInChain",
                "",
                "c_void",
                true,
                "mutable"
            )
        ) + it.members

        it.type == "extension_in" -> listOf(YamlModelV2.Struct.Member("chain", "", "struct.chained_struct")) + it.members
        it.type == "base_out" -> listOf(
            YamlModelV2.Struct.Member(
                "nextInChain",
                "",
                "c_void",
                true,
                "mutable"
            )
        ) + it.members

        it.type == "standalone" -> it.members
        else -> error("unsuported type ${it.type}")
    }

    CLibraryModel.Structure(
        it.name.convertToKotlinClassName(),
        members.flatMap {
            CLibraryModel.StructureField(
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
                when (it.type) {
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
            CLibraryModel.StructureField("next", CLibraryModel.Reference.Structure("WGPUChainedStruct"), "?"),
            CLibraryModel.StructureField("sType", CLibraryModel.Reference.Enumeration("WGPUSType"), "")
        )
    ),
    CLibraryModel.Structure(
        "WGPUChainedStructOut", listOf(
            CLibraryModel.StructureField("next", CLibraryModel.Reference.Structure("WGPUChainedStructOut"), "?"),
            CLibraryModel.StructureField("sType", CLibraryModel.Reference.Enumeration("WGPUSType"), "")
        )
    ),
    CLibraryModel.Structure(
        "WGPUStringView", listOf(
            CLibraryModel.StructureField("data", CLibraryModel.Reference.CString, "?"),
            CLibraryModel.StructureField("length", CLibraryModel.Primitive.UInt64, "")
        )
    )
) + callbacks.map {
    val name = it.name.convertToKotlinCallbackStructureName()
    CLibraryModel.Structure(
        name, listOf(
            CLibraryModel.StructureField("nextInChain", CLibraryModel.Reference.Structure("WGPUChainedStruct"), "?"),
            CLibraryModel.StructureField("callback", CLibraryModel.Reference.Callback(it.name.convertToKotlinCallbackName()), "?"),
            CLibraryModel.StructureField("userdata1", CLibraryModel.Reference.OpaquePointer, "?"),
            CLibraryModel.StructureField("userdata2", CLibraryModel.Reference.OpaquePointer, "?")
        )
    )
}

private fun YamlModelV2.convertToCLibraryEnumerations() =
    enums.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName(), it.entries.convertEnumToEnumValues()) } +
            bitflags.map { CLibraryModel.Enumeration(it.name.convertToKotlinClassName(), it.entries.convertToEnumValues()) }

private fun List<YamlModelV2.Bitflag.Entry>.convertToEnumValues(): List<Pair<String, Int>> = mapIndexed { index, entry ->
    entry.name.convertToEnumValueName() to index
}

private fun List<YamlModelV2.Enum.Entry>.convertEnumToEnumValues(): List<Pair<String, Int>> = mapIndexed { index, entry ->
    entry.name.convertToEnumValueName() to (entry.value ?: index)
}


private fun YamlModelV2.convertToCLibraryFunctions(): List<CLibraryModel.Function> = functions
    // TODO Skip until added to binding
    .filter { it.name != "get_instance_features" }
    .map {
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
        YamlModelV2.Function("release", "")
    ) + reference.methods)
        .map {
            val name = "${reference.name}_${it.name}".convertToKotlinFunctionName()
            val args = listOf(YamlModelV2.Function.Arg("handler", "", "object.${reference.name}")) + it.args
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

fun convertToCFunctionArgs(args: List<YamlModelV2.Function.Arg>, callback: String?): List<Pair<String, Type>> {
    return args.flatMap { arg ->
        (arg.name.convertToKotlinVariableName() to arg.type.toCType(
            arg.pointer != null,
            arg.pointer == "mutable"
        )).let {
            when (it.second) {
                is CLibraryModel.Array -> listOf(
                    it.generateArrayCounter(),
                    it
                )

                else -> listOf(it)
            }
        }
    } + callback.injectCallbackInfoStructure()
}

private fun String?.injectCallbackInfoStructure(): List<Pair<String, Type>> = when {
    this != null -> listOf("callbackInfo" to CLibraryModel.Reference.StructureField(split(".")[1].convertToKotlinCallbackStructureName()))
    else -> emptyList()
}

private fun YamlModelV2.convertToPointer(): List<CLibraryModel.Pointer> {
    val pointers = objects.map { it.name.convertToKotlinClassName() }
        .map { CLibraryModel.Pointer(it) }
    return pointers
}

private fun CLibraryModel.StructureField.generateArrayCounter() = let { (name, _, _) ->
    val newName = when {
        name.endsWith("ies") -> name.removeSuffix("ies") + "yCount"
        else -> name.removeSuffix("s") + "Count"
    }
    CLibraryModel.StructureField(newName, CLibraryModel.Primitive.UInt64, "")
}

private fun Pair<String, CLibraryModel.Type>.generateArrayCounter() = let { (name, _) ->
    val newName = when {
        name.endsWith("ies") -> name.removeSuffix("ies") + "yCount"
        else -> name.removeSuffix("s") + "Count"
    }
    newName to CLibraryModel.Primitive.UInt64
}


data class Field(val name: String, val size: Int, val alignment: Int)

fun main() {

    val fields = listOf(
        Field("a", 8, 8),  // char a (1 byte, alignment 1)
        Field("b", 4, 4),  // int b (4 bytes, alignment 4)
        Field("c", 1, 1)   // char c (1 byte, alignment 1)
    )

    calculatePadding(fields)
}

fun calculatePadding(fields: List<Field>) {
    var offset = 0
    var totalSize = 0

    println("Field\tOffset\tPadding")

    for (field in fields) {
        // Adjust offset to align with field's alignment
        val alignmentOffset = (field.alignment - (offset % field.alignment)) % field.alignment
        offset += alignmentOffset

        println("${field.name}\t$offset\t$alignmentOffset")

        offset += field.size
        totalSize += field.size + alignmentOffset
    }

    // Align the total size to the largest alignment in the structure
    val largestAlignment = fields.maxOf { it.alignment }
    val finalPadding = (largestAlignment - (totalSize % largestAlignment)) % largestAlignment
    totalSize += finalPadding

    println("Total size (with padding): $totalSize")
    println("Final padding: $finalPadding")
}