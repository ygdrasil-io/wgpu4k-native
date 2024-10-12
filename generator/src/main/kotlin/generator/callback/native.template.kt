package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toFunctionKotlinType

fun CLibraryModel.Callback.toNativeCallback() = templateBuilder {
    val callbackName = name
    appendBlock("actual interface $callbackName : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toFunctionKotlinType()}" }
            .joinToString(", ")
        appendLine("actual fun invoke($args)")

        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator, callback: $callbackName): CallbackHolder<$callbackName>") {
                val args = members
                    .map { (name, type) -> "$name: ${type.toNativeCallbackArg()}" }
                    .joinToString(", ")
                val argsCall = members
                    .map { (name, type) -> type.toNativeCallbackArgCall(name) }
                    .joinToString(", ")
                appendBlock("val actualCallback = staticCFunction",  args) {
                    val lastArgName = members.last().first
                    appendLine("val address = $lastArgName?.reinterpret<LongVar>()?.pointed?.value ?: error(\"Missing callback address on last argument\")")
                    appendLine("val callback = findCallback<$callbackName>(address)")
                    appendLine("\t?: error(\"Callback not found with address \$address and type $callbackName\")")
                    appendLine("callback.invoke($argsCall)")
                }

                appendLine("registerCallback(actualCallback.rawValue.toLong(), callback)")
                appendLine("return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)")
            }
        }
    }
    newLine()
}

private fun CLibraryModel.Type.toNativeCallbackArgCall(name: String): String = when (this) {
    is CLibraryModel.Reference.Enumeration,
    is CLibraryModel.Primitive -> name
    is CLibraryModel.Reference.Pointer -> "($name?.rawValue?.toLong() ?: 0L).let(::${this.name})"
    is CLibraryModel.Reference.Structure -> "($name?.rawValue?.toLong() ?: 0L).let(::${this.name})"
    CLibraryModel.Reference.CString -> "($name?.rawValue?.toLong() ?: 0L).let(::CString)"
    CLibraryModel.Void -> error("unsupported type")
    else -> "$name?.rawValue?.toLong() ?: 0L"
}

private fun CLibraryModel.Type.toNativeCallbackArg(): String = when (this) {
    is CLibraryModel.Primitive -> toFunctionKotlinType()
    is CLibraryModel.Reference.Enumeration -> "UInt"
    CLibraryModel.Void -> error("unsupported type")
    else -> "COpaquePointer?"
}

/*
            val actualCallback =
                staticCFunction { arg1: UInt, arg2: COpaquePointer?, arg3: COpaquePointer?, arg4: CPointer<LongVar>? ->
                    val callback = findCallback<WGPUBufferMapCallback>(arg4?.pointed?.value ?: error("Missing callback address on last argument"))
                        ?: error("Callback not found with address ${arg4?.pointed?.value} and type WGPUBufferMapCallback")
                    callback.invoke(arg1, CString(arg2!!.rawValue.toLong()), arg3!!.rawValue.toLong(), arg4!!.rawValue.toLong())
                }
            registerCallback(actualCallback.rawValue.toLong(), callback)
            return CallbackHolder(actualCallback.rawValue.toLong(), actualCallback)
        }
 */