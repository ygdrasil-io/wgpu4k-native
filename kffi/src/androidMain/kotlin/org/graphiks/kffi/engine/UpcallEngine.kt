package org.graphiks.kffi.engine

object UpcallEngine {
    external fun allocateTrampoline(
        dispatcherClass: Class<*>,
        dispatchMethod: String,
        dispatchSig: String,
    ): Long

    external fun freeTrampoline(address: Long)
}
