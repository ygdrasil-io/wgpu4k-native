package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

internal object JvmBootstrapProbe {
    private const val THREAD_COUNT = 16

    @JvmStatic
    fun main(args: Array<String>) {
        require(args.size == 1) { "Expected one probe mode" }
        when (args.single()) {
            "downcall" -> wgpuSetLogLevel(WGPULogLevel_Warn)
            "callback" -> wgpuSetLogCallback(CallbackPolicy.REPEATING) { _, _ -> }.close()
            "concurrent" -> runConcurrentFirstAccess()
            else -> error("Unknown probe mode: ${args.single()}")
        }
    }

    private fun runConcurrentFirstAccess() {
        val ready = CountDownLatch(THREAD_COUNT)
        val start = CountDownLatch(1)
        val executor = Executors.newFixedThreadPool(THREAD_COUNT)
        try {
            val futures = List(THREAD_COUNT) { index ->
                executor.submit {
                    ready.countDown()
                    check(start.await(10, TimeUnit.SECONDS))
                    if (index % 2 == 0) {
                        wgpuSetLogLevel(WGPULogLevel_Warn)
                    } else {
                        wgpuGetVersion()
                    }
                }
            }
            check(ready.await(10, TimeUnit.SECONDS))
            start.countDown()
            futures.forEach { it.get(10, TimeUnit.SECONDS) }
        } finally {
            start.countDown()
            executor.shutdownNow()
        }
    }
}
