package converter

import convertToEnumValueName
import convertToKotlinClassName
import converter.to.native.convertCallbacks
import converter.to.native.convertToCLibraryFunctions
import converter.to.native.generateCLibraryStructures
import domain.NativeModel
import domain.Version
import domain.YamlModel
import domain.actualDoc
import domain.mappingVersion

internal fun YamlModel.toNativeModel(): NativeModel {
    val pointers = convertToPointer()
    val functions = convertToCLibraryFunctions()
    val enumerations = convertToCLibraryEnumerations()
    val structures = generateCLibraryStructures()
        .calculateSizeAndPadding()
    val callbacks = convertCallbacks()

    return NativeModel(pointers, functions, enumerations, structures, callbacks)
}


private fun YamlModel.convertToCLibraryEnumerations() =
    enums.map {
        NativeModel.Enumeration(
            it.name.convertToKotlinClassName(),
            it.entries.convertEnumToEnumValues(),
            32,
            it.doc.actualDoc()
        )
    } + bitflags.map {
        NativeModel.Enumeration(
            it.name.convertToKotlinClassName(),
            it.entries.convertToEnumValues(it.entries),
            if (mappingVersion == Version.v22) 32 else 64,
            it.doc.actualDoc()
        )
    }

private fun List<YamlModel.Bitflag.Entry>.convertToEnumValues(entries: List<YamlModel.Bitflag.Entry>): List<Triple<String, Int, String?>> =
    mapIndexed { index, entry ->
        // Calculate first if that a combination
        val value =
            entry.value_combination?.sumOf { subPart -> indexToFlagValue(entries.indexOfFirst { it.name == subPart }) }
                ?: indexToFlagValue(index)
        Triple(entry.name.convertToEnumValueName(), value, entry.doc.actualDoc())
    }

private fun indexToFlagValue(base: Int): Int = if (base == 0) 0 else 1 shl (base - 1)

private fun List<YamlModel.Enum.Entry?>.convertEnumToEnumValues(): List<Triple<String, Int, String?>> =
    mapIndexedNotNull { index, entry ->
        if (entry == null) return@mapIndexedNotNull null

        Triple(entry.name.convertToEnumValueName(), entry.value ?: index, entry.doc.actualDoc())
    }

private fun YamlModel.convertToPointer(): List<NativeModel.Pointer> = objects.map {
    NativeModel.Pointer(
        it.name.convertToKotlinClassName(),
        it.doc.actualDoc()
    )
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