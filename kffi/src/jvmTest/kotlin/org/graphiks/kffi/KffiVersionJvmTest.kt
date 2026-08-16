package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class KffiVersionJvmTest : FreeSpec({
    "Kffi.VERSION tracks the kffi.version build chain (runtime constant omits -SNAPSHOT)" {
        val buildVersion = requireNotNull(System.getProperty("kffi.version")) {
            "kffi.version system property must be injected by the jvmTest task"
        }
        Kffi.VERSION shouldBe buildVersion.removeSuffix("-SNAPSHOT")
    }
})
