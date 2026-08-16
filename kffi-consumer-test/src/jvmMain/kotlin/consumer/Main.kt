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

        // Bornes-check actifs par défaut (contrat P3) : accès hors `size` → IndexOutOfBoundsException.
        var outOfBoundsDetected = false
        try {
            buffer.readLong(offset = 12uL)
        } catch (e: IndexOutOfBoundsException) {
            outOfBoundsDetected = true
        }
        check(outOfBoundsDetected)
    }

    // Opt-in unsafe (I3) : les bornes-check sont éliminés — l'accès hors bornes
    // ne lève pas (UB documenté). La garde de durée de vie est conservée.
    val unsafeAllocator = MemoryAllocator(unsafe = true)
    val unsafeBuffer = unsafeAllocator.allocateBuffer(8uL)
    unsafeBuffer.writeLong(1L, 64uL) // hors bornes : pas d'exception en unsafe
    unsafeAllocator.close()

    println("kffi consumer test OK")
}
