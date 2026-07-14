package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

class CallbackFixtureWatchdogJvmTest : FreeSpec({
    "native unregister watchdog terminates a stuck fixture child".config(timeout = 10.seconds) {
        val probe = requireNotNull(System.getProperty("kffi.callback.fixture.watchdog.probe"))
        val process = ProcessBuilder(probe).redirectErrorStream(true).start()
        val completed = try {
            process.waitFor(5, TimeUnit.SECONDS)
        } finally {
            if (process.isAlive) process.destroyForcibly().waitFor()
        }
        val diagnostic = process.inputStream.bufferedReader().readText()
        completed shouldBe true
        process.exitValue() shouldBe 124
        diagnostic shouldContain "callback fixture teardown watchdog expired"
    }
})
