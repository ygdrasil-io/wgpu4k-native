package builder


class Builder(
    internal val indent: Int = 0
) {
    val textBuilder = StringBuilder()

    fun appendLine(text: String) {
        textBuilder.append("\t".repeat(indent))
        textBuilder.append(text)
        newLine()
    }

    fun appendBlock(text: String, run: Builder.() -> Unit) {
        textBuilder.append("\t".repeat(indent))
        textBuilder.append(text)
        textBuilder.append(" {")
        newLine()
        Builder(indent + 1).apply(run).toString()
            .also {textBuilder.append(it)}


        textBuilder.append("}")
        newLine()
    }

    fun newLine() {
        textBuilder.append("\n")
    }

    override fun toString(): String {
        return textBuilder.toString()
    }
}

fun templateBuilder(run: Builder.() -> Unit) = Builder()
    .apply(run).toString()