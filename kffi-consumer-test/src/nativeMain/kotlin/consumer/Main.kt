package consumer

import org.graphiks.kffi.MemoryAllocator
import org.graphiks.kffi.MemoryBuffer
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

    // ── Bindings générés par kextract (M3.2) ─────────────────────────────────
    // Appelle la lib `consumer` (native/consumer.c) via les bindings
    // consumer_h* générés depuis headers/consumer.h. Côté native les bindings
    // passent par le cinterop `webgpu.native.*` (webgpu.def) : la lib est liée
    // STATIQUEMENT au kexe (libconsumer.a), aucun chargement dynamique.
    memoryScope { allocator ->
        check(consumer_negate(5) == -5) { "consumer_negate(5) != -5" }
        check(consumer_negate(-5) == 5)

        val string = allocator.allocateFrom("kextract")
        check(consumer_strlen(string.handler) == 8) { "consumer_strlen(\"kextract\") != 8" }
        consumer_set_string(string.handler)

        val ping = allocator.allocate(8L)
        consumer_get_ping(ping)
        check(MemoryBuffer(ping, 8uL).readLong(0uL) == 42L) { "consumer_get_ping != 42" }
    }
    println("consumer bindings OK (negate/strlen/set_string/get_ping)")
    println("kffi consumer test OK")
}
