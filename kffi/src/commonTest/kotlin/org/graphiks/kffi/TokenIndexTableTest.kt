@file:OptIn(CallbackRuntimeApi::class)

package org.graphiks.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

private const val ROUTED_CALLBACK_COUNT = 200

private fun interface TableTestCallback : Callback {
    fun invoke()
}

class TokenIndexTableTest : FreeSpec({
    "table grows beyond initial capacity keeping every registration routable" {
        withTableBaseline { baseline ->
            val type = CallbackType<TableTestCallback>("token-table-growth", hasRoutingUserdata = true)
            val calls = IntArray(ROUTED_CALLBACK_COUNT)
            val registrations = List(ROUTED_CALLBACK_COUNT) { index ->
                CallbackRuntime.register(
                    type = type,
                    trampoline = tableTrampoline,
                    policy = CallbackPolicy.REPEATING,
                    callback = TableTestCallback { calls[index] += 1 },
                )
            }
            try {
                registrations.size shouldBe ROUTED_CALLBACK_COUNT
                CallbackRuntime.activeRegistrationCountForTest() shouldBe
                    baseline + ROUTED_CALLBACK_COUNT
                registrations.forEachIndexed { index, registration ->
                    CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                    calls[index] shouldBe 1
                }
            } finally {
                registrations.forEach(CallbackRegistration<TableTestCallback>::close)
            }
        }
    }

    "closing half the registrations removes their tokens while open ones keep routing" {
        withTableBaseline { baseline ->
            val type = CallbackType<TableTestCallback>("token-table-removal", hasRoutingUserdata = true)
            val calls = IntArray(ROUTED_CALLBACK_COUNT)
            val registrations = List(ROUTED_CALLBACK_COUNT) { index ->
                CallbackRuntime.register(
                    type = type,
                    trampoline = tableTrampoline,
                    policy = CallbackPolicy.REPEATING,
                    callback = TableTestCallback { calls[index] += 1 },
                )
            }
            try {
                CallbackRuntime.activeRegistrationCountForTest() shouldBe
                    baseline + ROUTED_CALLBACK_COUNT

                registrations.filterIndexed { index, _ -> index % 2 == 0 }
                    .forEach(CallbackRegistration<TableTestCallback>::close)

                CallbackRuntime.activeRegistrationCountForTest() shouldBe
                    baseline + ROUTED_CALLBACK_COUNT / 2

                registrations.forEachIndexed { index, registration ->
                    CallbackRuntime.dispatchSafely(type, registration.userdata) { it.invoke() }
                    if (index % 2 == 0) {
                        calls[index] shouldBe 0
                    } else {
                        calls[index] shouldBe 1
                    }
                }
            } finally {
                registrations.forEach(CallbackRegistration<TableTestCallback>::close)
            }
        }
    }
})

private val tableTrampoline = NativeAddress(0xCAFE)

private inline fun withTableBaseline(test: (Int) -> Unit) {
    val baseline = CallbackRuntime.activeRegistrationCountForTest()
    try {
        test(baseline)
    } finally {
        CallbackRuntime.activeRegistrationCountForTest() shouldBe baseline
    }
}
