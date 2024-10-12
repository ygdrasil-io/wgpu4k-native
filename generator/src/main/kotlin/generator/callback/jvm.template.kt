package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toFunctionKotlinType

fun CLibraryModel.Callback.toJvmCallback() = templateBuilder {
    appendBlock("actual interface ${name} : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toFunctionKotlinType()}" }
            .joinToString(", ")
        appendLine("actual fun invoke($args)")

        appendBlock("actual companion object") {
            appendLine("actual fun allocate(allocator: MemoryAllocator, callback: $name): CallbackHolder<$name> = TODO()")
        }
    }
    newLine()
}