package generator.structure

import builder.templateBuilder
import converter.variableType
import domain.CLibraryModel
import domain.toFunctionKotlinType

internal fun CLibraryModel.Structure.toNativeStructure() = templateBuilder {
    val structureName = name
    appendBlock("actual value class $name(actual val handler: NativeAddress)") {
        members.forEach { (name, type, optional) ->
            val variableType = type.variableType()
            val nativeAccessor = "handler.toCPointer<webgpu.native.$structureName>()?.pointed"
            appendLine("actual $variableType $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}"

                is CLibraryModel.Reference.Enumeration
                    -> "$nativeAccessor?.${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive.Bool
                    -> "$nativeAccessor?.${name}?.toBoolean() ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive
                    -> "$nativeAccessor?.${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Reference.CString
                    -> "$nativeAccessor?.${name}?.toCString()"

                is CLibraryModel.Reference.Pointer
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.StructureField
                    -> "$nativeAccessor?.${name}?.rawPtr?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) } ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Reference.Structure
                    -> "$nativeAccessor?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.Callback
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { CallbackHolder<${type.name}>(it) }"

                is CLibraryModel.Array
                    -> "handler.toCPointer<webgpu.native.$structureName>()?.pointed?.${name}?.toLong()" +
                        "?.takeIf {it != 0L}" +
                        "?.let { ArrayHolder<${type.subType.toFunctionKotlinType()}>(it) }"

                CLibraryModel.Void -> error("void is not allowed")
            }.let { appendLine("\tget() = $it") }
            // Setter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.toCPointer() }"

                is CLibraryModel.Reference.Enumeration
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue }"

                is CLibraryModel.Reference.CString
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"

                is CLibraryModel.Primitive.Bool
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue.toUInt() }"

                is CLibraryModel.Primitive
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue }"

                is CLibraryModel.Reference.Pointer
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"

                is CLibraryModel.Reference.Structure
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"

                is CLibraryModel.Reference.Callback
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"

                is CLibraryModel.Array
                    -> nativeAccessor +
                        "?.let { it.${name} = newValue?.handler?.toCPointer() }"

                is CLibraryModel.Reference.StructureField -> null

                CLibraryModel.Void -> error("void is not allowed")
            }?.let { appendLine("\tset(newValue) { $it } \n") } ?: newLine()
        }
        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator): $structureName") {
                appendLine("return allocator.allocate(sizeOf<webgpu.native.$structureName>())")
                appendLine("\t.let(::$structureName)")
            }
        }
        appendBlock("fun toCValue(): CValue<webgpu.native.$structureName>") {
            appendBlock("return cValue<webgpu.native.$structureName>") {
                members
                    .filter { (_, type, _) -> type is CLibraryModel.Reference.StructureField }
                    .forEach { (name, _, _) ->
                        appendLine("$name.adapt(this@$structureName.$name)")
                    }
                members
                    .filter { (_, type, _) -> type !is CLibraryModel.Reference.StructureField }
                    .forEach { (name, type, _) ->
                    val adapter = when (type) {
                        CLibraryModel.Reference.OpaquePointer -> "?.toCPointer()"
                        is CLibraryModel.Reference.Pointer,
                        is CLibraryModel.Reference.Structure,
                        CLibraryModel.Reference.CString,
                        is CLibraryModel.Reference.Callback,
                        is CLibraryModel.Array -> "?.handler?.toCPointer()"
                        CLibraryModel.Primitive.Bool -> ".toUInt()"
                        CLibraryModel.Primitive.Float32,
                        CLibraryModel.Primitive.Float64,
                        CLibraryModel.Primitive.Int32,
                        CLibraryModel.Primitive.Int64,
                        CLibraryModel.Primitive.UInt16,
                        CLibraryModel.Primitive.UInt32,
                        CLibraryModel.Primitive.UInt64,
                        is CLibraryModel.Reference.Enumeration -> ""
                        is CLibraryModel.Reference.StructureField,
                            CLibraryModel.Void -> error("$type is not allowed")
                    }
                    appendLine("$name = this@$structureName.$name$adapter")
                }
            }
        }
    }
    newLine()
    appendBlock("fun webgpu.native.$structureName.adapt(structure: $name)") {
        members
            .filter { (_, type, _) -> type is CLibraryModel.Reference.StructureField }
            .forEach { (name, _, _) ->
                appendLine("$name.adapt(structure.$name)")
            }
        members
            .filter { (_, type, _) -> type !is CLibraryModel.Reference.StructureField }
            .forEach { (name, type, _) ->
                val adapter = when (type) {
                    CLibraryModel.Reference.OpaquePointer -> "?.toCPointer()"
                    is CLibraryModel.Reference.Pointer,
                    is CLibraryModel.Reference.Structure,
                    CLibraryModel.Reference.CString,
                    is CLibraryModel.Reference.Callback,
                    is CLibraryModel.Array -> "?.handler?.toCPointer()"
                    CLibraryModel.Primitive.Bool -> ".toUInt()"
                    CLibraryModel.Primitive.Float32,
                    CLibraryModel.Primitive.Float64,
                    CLibraryModel.Primitive.Int32,
                    CLibraryModel.Primitive.Int64,
                    CLibraryModel.Primitive.UInt16,
                    CLibraryModel.Primitive.UInt32,
                    CLibraryModel.Primitive.UInt64,
                    is CLibraryModel.Reference.Enumeration -> ""
                    is CLibraryModel.Reference.StructureField,
                    CLibraryModel.Void -> error("$type is not allowed")
                }
                appendLine("$name = structure.$name$adapter")
            }
    }
    newLine()
}