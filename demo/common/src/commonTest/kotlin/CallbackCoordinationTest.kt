@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlin.time.Duration

class CallbackCoordinationTest {
    @Test
    fun callbackFuturePumpsUntilStatusIsPublished() {
        val state = CallbackRequestState<UInt, String>(release = {})
        var calls = 0
        awaitCallbackFuture(
            futureId = 41uL,
            phase = "request-test",
            isComplete = { state.isComplete },
            waitOnce = { id ->
                assertEquals(41uL, id)
                calls += 1
                if (calls == 2) state.publish(7u, "handle", "ready")
                if (calls == 1) WGPUWaitStatus_TimedOut else WGPUWaitStatus_Success
            },
        )
        assertEquals(2, calls)
        assertEquals(7u, state.snapshot().status)
        assertEquals("handle", state.takeHandle())
        assertNull(state.takeHandle())
    }

    @Test
    fun zeroFutureIsRejectedWithoutPumping() {
        var pumped = false
        val failure = assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(0uL, "request-zero", isComplete = { false }) {
                pumped = true
                WGPUWaitStatus_Success
            }
        }
        assertFalse(pumped)
        assertTrue(failure.message.orEmpty().contains("request-zero"))
    }

    @Test
    fun synchronousZeroFutureRequiresExplicitCompletedAndQuiescentOptIn() {
        var pumped = false
        awaitCallbackFuture(
            futureId = 0uL,
            phase = "request-sync-zero",
            zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
            isComplete = { true },
            isQuiescent = { true },
            waitOnce = {
                pumped = true
                WGPUWaitStatus_Success
            },
        )
        assertFalse(pumped)
    }

    @Test
    fun synchronousZeroFutureRejectsIncompleteOrNonQuiescentState() {
        listOf(false to true, true to false).forEach { (complete, quiescent) ->
            val failure = assertFailsWith<IllegalStateException> {
                awaitCallbackFuture(
                    futureId = 0uL,
                    phase = "request-invalid-sync-zero",
                    zeroFuturePolicy = ZeroFuturePolicy.ALLOW_COMPLETED_SYNCHRONOUSLY,
                    isComplete = { complete },
                    isQuiescent = { quiescent },
                    waitOnce = { error("zero future must not be pumped") },
                )
            }
            assertTrue(failure.message.orEmpty().contains("request-invalid-sync-zero"))
        }
    }

    @Test
    fun cleanupFailureDisposesAndReleasesAPublishedHandleExactlyOnce() {
        var releases = 0
        val state = CallbackRequestState<UInt, String> { releases += 1 }
        state.publish(7u, "owned", "ready")

        state.dispose()
        state.dispose()

        assertEquals(1, releases)
        assertFalse(state.isComplete)
        assertNull(state.takeHandle())
    }

    @Test
    fun latePublicationAfterDisposalReleasesItsHandleAndStaysIncomplete() {
        var released: String? = null
        val state = CallbackRequestState<UInt, String> { released = it }
        state.dispose()

        state.publish(7u, "late", "too late")

        assertEquals("late", released)
        assertFalse(state.isComplete)
        assertNull(state.snapshot().status)
        assertNull(state.takeHandle())
    }

    @Test
    fun messageCopyFailureReleasesTheIncomingHandleExactlyOnce() {
        var releases = 0
        val copyFailure = IllegalArgumentException("copy failed")
        val failure = assertFailsWith<IllegalArgumentException> {
            copyCallbackMessageOrRelease(
                handle = "owned",
                release = { releases += 1 },
                copy = { throw copyFailure },
            )
        }

        assertSame(copyFailure, failure)
        assertEquals(1, releases)
    }

    @Test
    fun releaseFailureIsSuppressedOnTheOriginalMessageCopyFailure() {
        val copyFailure = IllegalArgumentException("copy failed")
        val releaseFailure = IllegalStateException("release failed")

        val failure = assertFailsWith<IllegalArgumentException> {
            copyCallbackMessageOrRelease(
                handle = "owned",
                release = { throw releaseFailure },
                copy = { throw copyFailure },
            )
        }

        assertSame(copyFailure, failure)
        assertEquals(listOf(releaseFailure), failure.suppressedExceptions)
    }

    @Test
    fun nullHandleCopyFailureDoesNotCallRelease() {
        var releases = 0
        val copyFailure = IllegalArgumentException("copy failed")

        val failure = assertFailsWith<IllegalArgumentException> {
            copyCallbackMessageOrRelease<String>(
                handle = null,
                release = { releases += 1 },
                copy = { throw copyFailure },
            )
        }

        assertSame(copyFailure, failure)
        assertEquals(0, releases)
    }

    @Test
    fun unexpectedWaitStatusIncludesThePhase() {
        val failure = assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(1uL, "request-error", isComplete = { false }) {
                WGPUWaitStatus_Error
            }
        }
        assertTrue(failure.message.orEmpty().contains("request-error"))
    }

    @Test
    fun zeroDeadlineFailsAfterOneNonBlockingPump() {
        var calls = 0
        assertFailsWith<IllegalStateException> {
            awaitCallbackFuture(
                1uL,
                "request-timeout",
                Duration.ZERO,
                isComplete = { false },
            ) {
                calls += 1
                WGPUWaitStatus_TimedOut
            }
        }
        assertEquals(1, calls)
    }
}
