package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toCallbackKotlinType
import domain.typeToJvmLayout

fun CLibraryModel.Callback.toJvmCallback() = templateBuilder {

    val jvmArgs = members
        .map { (name, type) -> "$name: ${type.toCallbackJvmType()}" }
        .joinToString(", ")

    appendBlock("actual interface ${name} : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toCallbackKotlinType()}" }
            .joinToString(", ")
        appendLine("actual fun invoke($args)")

        appendBlock("interface Function") {
            appendLine("fun apply($jvmArgs)")
        }

        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator, callback: $name): CallbackHolder<$name>") {

                appendBlock("val function = object : Function") {
                    appendBlock("override fun apply($jvmArgs)") {
                        val argsCall = members
                            .map { (name, type) -> type.toArgCall(name) }
                            .joinToString(", ")
                        appendLine("callback.invoke($argsCall)")
                    }
                }

                appendLine("return java.lang.foreign.Linker.nativeLinker().upcallStub(")
                appendLine("\thandler.bindTo(function),")
                appendLine("\tdescriptor,")
                appendLine("\tallocator.arena")
                appendLine(").let(::NativeAddress)")
                appendLine("\t.let(::CallbackHolder)")

            }

            appendLine("private val descriptor: java.lang.foreign.FunctionDescriptor = java.lang.foreign.FunctionDescriptor.ofVoid(")
            members.forEach { (_, type) ->
                appendLine("\t${typeToJvmLayout(type)},")
            }
            appendLine(")")

            appendLine("private val handler: java.lang.invoke.MethodHandle = ffi.upcallHandle(")
            appendLine("\tFunction::class.java,")
            appendLine("\t\"apply\",")
            appendLine("\tdescriptor")
            appendLine(")")
        }
    }
    newLine()
}

private fun CLibraryModel.Type.toArgCall(name: String): String = when (this) {
    CLibraryModel.Primitive.Bool,
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.UInt32 -> "$name.toUInt()"
    CLibraryModel.Primitive.Float32,
    CLibraryModel.Primitive.Float64,
    CLibraryModel.Primitive.Int32,
    CLibraryModel.Primitive.Int64 -> name
    CLibraryModel.Primitive.UInt16 ->  "$name.toUShort()"
    CLibraryModel.Primitive.UInt64 ->  "$name.toULong()"

    CLibraryModel.Reference.OpaquePointer -> "$name.let(::NativeAddress)"

    is CLibraryModel.Array -> "$name.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let(::NativeAddress)?.let(::ArrayHolder)"
    is CLibraryModel.Reference.Callback -> "$name.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let(::NativeAddress)?.let(::CallbackHolder)"
    is CLibraryModel.Reference -> "$name.takeIf { it != java.lang.foreign.MemorySegment.NULL }?.let(::NativeAddress)?.let(::${this.name})"
    CLibraryModel.Void -> error("unsupported type here")
}


private fun CLibraryModel.Type.toCallbackJvmType(): String = when (this) {
    CLibraryModel.Primitive.Float32 -> "Float"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.UInt16 -> "Short"
    CLibraryModel.Primitive.UInt64,
    CLibraryModel.Primitive.Int64 -> "Long"

    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.Int32,
    CLibraryModel.Primitive.UInt32,
    is CLibraryModel.Reference.Enumeration -> "Int"

    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    is CLibraryModel.Reference.StructureField -> "java.lang.foreign.MemorySegment"

    CLibraryModel.Void -> error("unsupported type here")
}

/*
actual interface WGPURequestAdapterCallback : Callback {

	actual fun invoke(status: WGPURequestAdapterStatus, adapter: WGPUAdapter?, message: WGPUStringView?, userdata1: NativeAddress, userdata2: NativeAddress)

	interface Function {
		fun apply(status: Int, adapter: MemorySegment, message: MemorySegment, userdata1: MemorySegment, userdata2: MemorySegment)
	}


	actual companion object {
		actual fun allocate(allocator: MemoryAllocator, callback: WGPURequestAdapterCallback): CallbackHolder<WGPURequestAdapterCallback> {
			val function = object : Function {
				override fun apply(
					status: Int,
					adapter: MemorySegment,
					message: MemorySegment,
					userdata1: MemorySegment,
					userdata2: MemorySegment
				) {
					println("adapter $adapter, message $message, userdata1 $userdata1, userdata2 $userdata2")
					callback.invoke(status.toUInt(), adapter.let(::NativeAddress).let(::WGPUAdapter), message.let(::NativeAddress).let(::WGPUStringView), userdata1.let(::NativeAddress), userdata2.let(::NativeAddress))
				}
			}

			return Linker.nativeLinker().upcallStub(
				handler.bindTo(function),
				descriptor,
				allocator.arena
			).let(::NativeAddress)
				.let(::CallbackHolder)

		}

		private val descriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(
			C_INT,
			C_POINTER,
			WGPUStringView.LAYOUT,
			C_POINTER,
			C_POINTER,
		)

		private val handler: MethodHandle = upcallHandle(
			Function::class.java,
			"apply",
			descriptor
		)
	}
}
 */