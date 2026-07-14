package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.MemoryAllocator
import io.kotest.core.spec.style.FreeSpec
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.assertEquals

class GeneratedCallbackJvmTest : FreeSpec({
    "same type registrations route trampoline deliveries by distinct tokens" {
        val allocator = MemoryAllocator()
        val deliveries = mutableListOf<Pair<String, Long?>>()
        val firstApplicationUserdata = allocator.allocate(1)
        val secondApplicationUserdata = allocator.allocate(1)
        val first = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, userdata1 ->
            deliveries += "first" to userdata1?.handler?.address()
        }
        val second = WGPUCompilationInfoCallback.register(CallbackPolicy.REPEATING) { _, _, userdata1 ->
            deliveries += "second" to userdata1?.handler?.address()
        }

        try {
            dispatch(first, firstApplicationUserdata.handler)
            dispatch(second, secondApplicationUserdata.handler)

            assertEquals(
                listOf<Pair<String, Long?>>(
                    "first" to firstApplicationUserdata.handler.address(),
                    "second" to secondApplicationUserdata.handler.address(),
                ),
                deliveries,
            )
        } finally {
            second.close()
            first.close()
            allocator.close()
        }
    }
})

private fun dispatch(
    registration: CallbackRegistration<WGPUCompilationInfoCallback>,
    applicationUserdata: MemorySegment,
) {
    val trampoline = Linker.nativeLinker().downcallHandle(
        registration.callback.handler,
        COMPILATION_INFO_CALLBACK_DESCRIPTOR,
    )
    trampoline.invokeWithArguments(
        WGPUCompilationInfoRequestStatus_Success.toInt(),
        MemorySegment.NULL,
        applicationUserdata,
        requireNotNull(registration.userdata).handler,
    )
}

private val COMPILATION_INFO_CALLBACK_DESCRIPTOR: FunctionDescriptor = FunctionDescriptor.ofVoid(
    ValueLayout.JAVA_INT,
    ValueLayout.ADDRESS,
    ValueLayout.ADDRESS,
    ValueLayout.ADDRESS,
)
