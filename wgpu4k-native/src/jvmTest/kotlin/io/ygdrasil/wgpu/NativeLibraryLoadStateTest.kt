package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class NativeLibraryLoadStateTest : FreeSpec({
    "concurrent first callers execute the load operation once" {
        val executions = AtomicInteger()
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val state = NativeLibraryLoadState {
            executions.incrementAndGet()
            entered.countDown()
            check(release.await(10, TimeUnit.SECONDS))
        }
        val executor = Executors.newFixedThreadPool(8)
        try {
            val futures = List(8) { executor.submit(state::load) }
            entered.await(10, TimeUnit.SECONDS) shouldBe true
            release.countDown()
            futures.forEach { it.get(10, TimeUnit.SECONDS) }
            executions.get() shouldBe 1
            state.isLoaded shouldBe true
        } finally {
            release.countDown()
            executor.shutdownNow()
        }
    }

    "failure preserves its identity and the next call retries" {
        val original = IllegalStateException("simulated load failure")
        var attempts = 0
        val state = NativeLibraryLoadState {
            attempts += 1
            if (attempts == 1) throw original
        }

        val observed = assertFailsWith<IllegalStateException> { state.load() }
        assertSame(original, observed)
        state.isLoaded shouldBe false

        state.load()
        attempts shouldBe 2
        state.isLoaded shouldBe true
    }
})
