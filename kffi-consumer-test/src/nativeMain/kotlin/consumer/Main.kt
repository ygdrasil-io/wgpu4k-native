package consumer

import org.graphiks.kffi.MemoryAllocator
import org.graphiks.kffi.memoryScope

fun main() {
    memoryScope { allocator ->
        val buffer = allocator.allocateBuffer(16uL)

        buffer.writeInt(value = 42, offset = 0uL)
        check(buffer.readInt(offset = 0uL) == 42)

        buffer.writeLong(value = 0xCAFEL, offset = 8uL)
        check(buffer.readLong(offset = 8uL) == 0xCAFEL)

        // Bornes-check actifs (contrat P3) : accès hors `size` → IndexOutOfBoundsException.
        var outOfBoundsDetected = false
        try {
            buffer.readLong(offset = 12uL)
        } catch (e: IndexOutOfBoundsException) {
            outOfBoundsDetected = true
        }
        check(outOfBoundsDetected)
    }

    // Divergence native documentée (I3/P3) : le flag unsafe runtime est ignoré —
    // figé à la compilation (KFFI_NATIVE_UNSAFE=false) — les bornes-check restent actifs.
    val allocator = MemoryAllocator(unsafe = true)
    val buffer = allocator.allocateBuffer(8uL)
    var outOfBoundsDetected = false
    try {
        buffer.writeLong(1L, 64uL)
    } catch (e: IndexOutOfBoundsException) {
        outOfBoundsDetected = true
    }
    check(outOfBoundsDetected)
    allocator.close()

    println("kffi consumer test OK")
}
