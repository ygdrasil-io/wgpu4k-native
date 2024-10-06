package java.lang.foreign

import com.sun.jna.Memory
import com.sun.jna.Pointer

fun Pointer.toAddress() = Pointer.nativeValue(this)

class JnaArena: AutoCloseable {
    private val autoCloseableMemory = mutableListOf<AutoCloseable>()

    fun allocate(size: Long): Pointer {
        return Memory(size)
            .also { autoCloseableMemory.add(it) }
    }

    override fun close() {
        autoCloseableMemory.forEach { it.close() }
    }

    fun allocateFrom(label: String): Pointer {
        return NativeString(label).pointer
            ?.also { autoCloseableMemory.add(it) }
            ?: error("fail to allocate CString")
    }
}

