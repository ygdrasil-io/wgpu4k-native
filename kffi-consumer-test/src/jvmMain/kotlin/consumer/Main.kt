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

    // ── Bindings générés par kextract ────────────────────────────────────────
    // Appelle la lib native `consumer` (native/consumer.c, compilée par
    // `compileConsumerLib`) via les bindings consumer_h* générés depuis
    // headers/consumer.h. La lib est chargée par KextractNativeBootstrap
    // (System.loadLibrary("consumer") → -Djava.library.path=build/native-libs).
    try {
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
    } catch (e: UnsatisfiedLinkError) {
        // Lib native absente (bootstrap kextract : System.loadLibrary("consumer")).
        println("ERREUR : libconsumer introuvable — compilez-la puis relancez :")
        println("  ./gradlew :compileConsumerLib && ./gradlew :runJvm")
        println("  (java.library.path = ${System.getProperty("java.library.path")})")
        throw e
    }
    println("consumer bindings OK (negate/strlen/set_string/get_ping)")
    println("kffi consumer test OK")
}
