package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class GpuCleanupStackTest {
    @Test
    fun cleanupRunsInReverseOrderExactlyOnce() {
        val calls = mutableListOf<String>()
        val cleanup = GpuCleanupStack()
        cleanup.defer { calls += "instance" }
        cleanup.defer { calls += "adapter" }
        cleanup.defer { calls += "device" }
        cleanup.defer { calls += "queue" }

        cleanup.close()
        cleanup.close()

        assertEquals(listOf("queue", "device", "adapter", "instance"), calls)
    }

    @Test
    fun cleanupFailuresAreSuppressedOnTheOriginalPrimaryFailure() {
        val primary = IllegalArgumentException("render failed")
        val firstCleanup = IllegalStateException("device release failed")
        val secondCleanup = IllegalStateException("queue release failed")
        val cleanup = GpuCleanupStack()
        cleanup.defer { throw firstCleanup }
        cleanup.defer { throw secondCleanup }

        cleanup.close(primary)

        assertEquals(listOf(secondCleanup, firstCleanup), primary.suppressedExceptions)
    }

    @Test
    fun firstReverseOrderCleanupFailureBecomesPrimaryAndLaterFailuresAreSuppressed() {
        val firstCleanup = IllegalStateException("device release failed")
        val secondCleanup = IllegalStateException("queue release failed")
        val cleanup = GpuCleanupStack()
        cleanup.defer { throw firstCleanup }
        cleanup.defer { throw secondCleanup }

        val failure = assertFailsWith<IllegalStateException> { cleanup.close() }

        assertSame(secondCleanup, failure)
        assertEquals(listOf(firstCleanup), failure.suppressedExceptions)
    }

    @Test
    fun mapCallbackCleanupPrecedesUnmapAndBufferRelease() {
        val calls = mutableListOf<String>()
        val cleanup = GpuCleanupStack()
        cleanup.defer { calls += "buffer-release" }
        cleanup.defer { calls += "buffer-unmap" }
        cleanup.defer { calls += "map-callback-close" }

        cleanup.close()

        assertEquals(listOf("map-callback-close", "buffer-unmap", "buffer-release"), calls)
    }
}
