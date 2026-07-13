@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.kffi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CallbackStateMachineTest : FreeSpec({
    "ONCE moves ACTIVE to CLAIMED exactly once" {
        val machine = DeliveryStateMachine(CallbackPolicy.ONCE)

        List(32) { machine.tryEnter() }.count { it } shouldBe 1
        machine.state shouldBe DeliveryState.CLAIMED
        machine.isClosed shouldBe true
    }

    "ONCE close and delivery have exactly one owner" - {
        "delivery wins" {
            val machine = DeliveryStateMachine(CallbackPolicy.ONCE)

            machine.tryEnter() shouldBe true
            machine.close() shouldBe false
            machine.state shouldBe DeliveryState.CLAIMED
        }

        "close wins" {
            val machine = DeliveryStateMachine(CallbackPolicy.ONCE)

            machine.close() shouldBe true
            machine.tryEnter() shouldBe false
            machine.state shouldBe DeliveryState.CLOSED
        }
    }

    "REPEATING close rejects new entries" {
        val machine = DeliveryStateMachine(CallbackPolicy.REPEATING)

        machine.tryEnter() shouldBe true
        machine.leave()
        machine.close() shouldBe true
        machine.tryEnter() shouldBe false
        machine.state shouldBe DeliveryState.CLOSED
    }

    "REPEATING in-flight work may leave after close" {
        val machine = DeliveryStateMachine(CallbackPolicy.REPEATING)

        machine.tryEnter() shouldBe true
        machine.inFlight shouldBe 1
        machine.close() shouldBe true
        machine.inFlight shouldBe 1
        machine.leave()
        machine.inFlight shouldBe 0
    }

    "no-userdata slot moves UNUSED to ACTIVE to RETIRED" {
        val slot = NoUserdataSlotStateMachine<String>()

        slot.phase shouldBe NoUserdataPhase.UNUSED
        slot.activate("first") shouldBe true
        slot.phase shouldBe NoUserdataPhase.ACTIVE
        slot.activeValue shouldBe "first"
        slot.retire("first") shouldBe true
        slot.phase shouldBe NoUserdataPhase.RETIRED
        slot.activeValue shouldBe null
    }

    "normal no-userdata activation rejects ACTIVE and RETIRED" {
        val active = NoUserdataSlotStateMachine<String>()
        active.activate("first") shouldBe true
        active.activate("second") shouldBe false

        val retired = NoUserdataSlotStateMachine<String>()
        retired.activate("first") shouldBe true
        retired.retire("first") shouldBe true
        retired.activate("second") shouldBe false
    }

    "unsafe no-userdata re-arm accepts only RETIRED" {
        val slot = NoUserdataSlotStateMachine<String>()

        slot.rearm("too early") shouldBe false
        slot.activate("first") shouldBe true
        slot.rearm("still active") shouldBe false
        slot.retire("first") shouldBe true
        slot.rearm("second") shouldBe true
        slot.activeValue shouldBe "second"
    }

    "prepared registration activates or aborts exactly once" - {
        "activation wins" {
            val machine = DeliveryStateMachine(
                policy = CallbackPolicy.REPEATING,
                initialState = DeliveryState.PREPARED,
            )

            machine.activate() shouldBe true
            machine.abort() shouldBe false
            machine.state shouldBe DeliveryState.ACTIVE
        }

        "abort wins" {
            val machine = DeliveryStateMachine(
                policy = CallbackPolicy.REPEATING,
                initialState = DeliveryState.PREPARED,
            )

            machine.abort() shouldBe true
            machine.activate() shouldBe false
            machine.state shouldBe DeliveryState.ABORTED
        }
    }

    "published failure closes token-backed state and retires no-userdata state" {
        val tokenBacked = DeliveryStateMachine(CallbackPolicy.REPEATING)
        val noUserdata = NoUserdataSlotStateMachine<String>()
        noUserdata.activate("published") shouldBe true

        tokenBacked.close() shouldBe true
        noUserdata.retire("published") shouldBe true

        tokenBacked.state shouldBe DeliveryState.CLOSED
        noUserdata.phase shouldBe NoUserdataPhase.RETIRED
    }
})
