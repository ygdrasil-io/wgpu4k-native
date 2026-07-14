@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.memoryScope
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicReference
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

private const val CallbackStressRegistrationCount = 1_000
private const val CallbackStressSubmissionCount = 64
private const val WaitAnyBatchSize = 64
private const val SuppressionStride = 10
private val CallbackStressPhaseTimeout = 20.seconds

fun runCallbackStress() {
    val instance = wgpuCreateInstance(null) ?: error("callback-stress: failed to create instance")
    var adapter: WGPUAdapter? = null
    var device: WGPUDevice? = null
    var queue: WGPUQueue? = null
    var errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>? = null

    val uncapturedErrorCalls = AtomicInt(0)
    val validationErrorCalls = AtomicInt(0)
    val errorCallbacksCompleted = AtomicInt(0)

    try {
        adapter = requestStressAdapter(instance)

        errorRegistration = WGPUUncapturedErrorCallback.register(CallbackPolicy.REPEATING) { _, type, message, _ ->
            try {
                uncapturedErrorCalls.fetchAndAdd(1)
                if (type == WGPUErrorType_Validation) validationErrorCalls.fetchAndAdd(1)
                println(
                    "callback-stress uncaptured-error type=$type " +
                        "message=${message.data?.toKString(message.length).orEmpty()}",
                )
            } finally {
                errorCallbacksCompleted.fetchAndAdd(1)
            }
        }
        device = requestStressDevice(
            adapter = adapter,
            instance = instance,
            errorRegistration = errorRegistration,
        )
        queue = wgpuDeviceGetQueue(device) ?: error("callback-stress: failed to get queue")

        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_WaitAnyOnly)
        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_AllowProcessEvents)
        runQueueCallbackPhase(instance, device, queue, WGPUCallbackMode_AllowSpontaneous)

        runUncapturedErrorPhase(
            device = device,
            queue = queue,
            uncapturedErrorCalls = uncapturedErrorCalls,
            validationErrorCalls = validationErrorCalls,
            errorCallbacksCompleted = errorCallbacksCompleted,
        )

        check(errorRegistration.isClosed.not()) {
            "callback-stress: repeating uncaptured-error registration closed before teardown"
        }
        println("callback-stress complete pending=0")
    } finally {
        errorRegistration?.close()
        queue?.let(::wgpuQueueRelease)
        device?.let(::wgpuDeviceRelease)
        adapter?.let(::wgpuAdapterRelease)
        wgpuInstanceRelease(instance)
    }
}

private fun requestStressAdapter(instance: WGPUInstance): WGPUAdapter {
    val requestStatus = AtomicReference<WGPURequestAdapterStatus?>(null)
    val requestedAdapter = AtomicReference<WGPUAdapter?>(null)
    val requestMessage = AtomicReference<String?>(null)
    val requestRegistration = WGPURequestAdapterCallback.register(CallbackPolicy.ONCE) { status, adapter, message, _ ->
        requestedAdapter.store(adapter)
        requestMessage.store(message.data?.toKString(message.length))
        requestStatus.store(status)
    }

    try {
        val futureId = memoryScope { scope ->
            val options = WGPURequestAdapterOptions.allocate(scope)
            val requestInfo = WGPURequestAdapterCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_WaitAnyOnly,
                requestRegistration,
            )
            wgpuInstanceRequestAdapter(instance, options, requestInfo).id
        }

        awaitSingleFuture(
            instance = instance,
            futureId = futureId,
            modeName = "WaitAnyOnly",
            phase = "request-adapter",
            isComplete = { requestStatus.load() != null },
            pendingIds = { listOf(0) },
        )

        val status = requestStatus.load()
        val adapter = requestedAdapter.load()
        val message = requestMessage.load()
        if (status != WGPURequestAdapterStatus_Success) {
            adapter?.let(::wgpuAdapterRelease)
            val messageSuffix = message?.takeIf(String::isNotEmpty)?.let { ": $it" }.orEmpty()
            error("callback-stress: failed to get adapter with status $status$messageSuffix")
        }
        return adapter ?: error("callback-stress: adapter request succeeded without an adapter")
    } finally {
        requestRegistration.close()
    }
}

private fun requestStressDevice(
    adapter: WGPUAdapter,
    instance: WGPUInstance,
    errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>,
): WGPUDevice {
    val requestStatus = AtomicReference<WGPURequestDeviceStatus?>(null)
    val requestedDevice = AtomicReference<WGPUDevice?>(null)
    val requestMessage = AtomicReference<String?>(null)
    val requestRegistration = WGPURequestDeviceCallback.register(CallbackPolicy.ONCE) { status, device, message, _ ->
        requestedDevice.store(device)
        requestMessage.store(message.data?.toKString(message.length))
        requestStatus.store(status)
    }

    try {
        val futureId = memoryScope { scope ->
            val descriptor = WGPUDeviceDescriptor.allocate(scope).apply {
                uncapturedErrorCallbackInfo = WGPUUncapturedErrorCallbackInfo.allocate(
                    scope,
                    errorRegistration,
                )
            }
            val requestInfo = WGPURequestDeviceCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_WaitAnyOnly,
                requestRegistration,
            )
            wgpuAdapterRequestDevice(adapter, descriptor, requestInfo).id
        }

        awaitSingleFuture(
            instance = instance,
            futureId = futureId,
            modeName = "WaitAnyOnly",
            phase = "request-device",
            isComplete = { requestStatus.load() != null },
            pendingIds = { listOf(0) },
        )

        return resolveDeviceRequestResult(
            status = requestStatus.load(),
            device = requestedDevice.load(),
            message = requestMessage.load(),
            release = ::wgpuDeviceRelease,
        )
    } finally {
        requestRegistration.close()
    }
}

private fun runQueueCallbackPhase(
    instance: WGPUInstance,
    device: WGPUDevice,
    queue: WGPUQueue,
    mode: WGPUCallbackMode,
) {
    val modeName = callbackModeName(mode)
    val deadline = TimeSource.Monotonic.markNow() + CallbackStressPhaseTimeout
    val calls = Array(CallbackStressRegistrationCount) { AtomicInt(0) }
    val delivered = AtomicInt(0)
    val queueCallbacksCompleted = AtomicInt(0)
    val duplicates = AtomicInt(0)
    val failedStatuses = AtomicInt(0)
    val registrations = mutableListOf<CallbackRegistration<WGPUQueueWorkDoneCallback>>()
    val futureIds = mutableListOf<ULong>()
    val suppressBeforeDelivery = mode != WGPUCallbackMode_AllowSpontaneous

    try {
        repeat(CallbackStressRegistrationCount) { id ->
            val registration = WGPUQueueWorkDoneCallback.register(CallbackPolicy.ONCE) { status, _, _ ->
                try {
                    val previous = calls[id].fetchAndAdd(1)
                    if (previous == 0) delivered.fetchAndAdd(1) else duplicates.fetchAndAdd(1)
                    if (status != WGPUQueueWorkDoneStatus_Success) failedStatuses.fetchAndAdd(1)
                } finally {
                    queueCallbacksCompleted.fetchAndAdd(1)
                }
            }
            registrations += registration
            if (deadline.hasPassedNow()) {
                failQueuePhase(modeName, "register", calls, suppressBeforeDelivery)
            }
        }

        check(registrations.size == CallbackStressRegistrationCount) {
            "callback-stress mode=$modeName active registrations=${registrations.size} " +
                "expected=$CallbackStressRegistrationCount before first queue downcall"
        }
        check(registrations.all { registration -> registration.isClosed.not() }) {
            "callback-stress mode=$modeName found a closed registration before first queue downcall"
        }

        submitBoundedCopyWork(device, queue)
        registrations.forEach { registration ->
            futureIds += memoryScope { scope ->
                val callbackInfo = WGPUQueueWorkDoneCallbackInfo.allocate(scope, mode, registration)
                wgpuQueueOnSubmittedWorkDone(queue, callbackInfo).id
            }
            if (deadline.hasPassedNow()) {
                failQueuePhase(modeName, "create-futures", calls, suppressBeforeDelivery)
            }
        }

        if (suppressBeforeDelivery) {
            registrations.forEachIndexed { id, registration ->
                if (isSuppressedId(id)) {
                    registration.close()
                    check(registration.isClosed) {
                        "callback-stress mode=$modeName id=$id did not close before delivery"
                    }
                }
            }
        }

        val expectedDeliveries = if (suppressBeforeDelivery) {
            CallbackStressRegistrationCount - suppressedRegistrationCount()
        } else {
            CallbackStressRegistrationCount
        }

        when (mode) {
            WGPUCallbackMode_WaitAnyOnly -> {
                wgpuDevicePoll(device, 1u, null)
                while (queueCallbacksCompleted.load() < expectedDeliveries) {
                    pumpWaitAnyBatches(instance, futureIds, modeName)
                    if (deadline.hasPassedNow()) {
                        failQueuePhase(modeName, "delivery", calls, suppressBeforeDelivery)
                    }
                }
            }

            WGPUCallbackMode_AllowProcessEvents -> {
                while (queueCallbacksCompleted.load() < expectedDeliveries) {
                    wgpuInstanceProcessEvents(instance)
                    if (deadline.hasPassedNow()) {
                        failQueuePhase(modeName, "delivery", calls, suppressBeforeDelivery)
                    }
                }
            }

            WGPUCallbackMode_AllowSpontaneous -> {
                wgpuDevicePoll(device, 1u, null)
                while (queueCallbacksCompleted.load() < expectedDeliveries) {
                    wgpuDevicePoll(device, 0u, null)
                    if (deadline.hasPassedNow()) {
                        failQueuePhase(modeName, "delivery", calls, suppressBeforeDelivery)
                    }
                }
            }

            else -> error("callback-stress: unsupported callback mode $mode")
        }

        when (mode) {
            WGPUCallbackMode_WaitAnyOnly -> {
                if (futureIds.all { it != 0uL }) pumpWaitAnyBatches(instance, futureIds, modeName)
            }

            WGPUCallbackMode_AllowProcessEvents -> wgpuInstanceProcessEvents(instance)
            WGPUCallbackMode_AllowSpontaneous -> wgpuDevicePoll(device, 0u, null)
            else -> error("callback-stress: unsupported callback mode $mode")
        }

        check(queueCallbacksCompleted.load() == expectedDeliveries) {
            "callback-stress mode=$modeName completed=${queueCallbacksCompleted.load()} " +
                "expected=$expectedDeliveries"
        }
        check(duplicates.load() == 0) {
            "callback-stress mode=$modeName duplicate deliveries=${duplicates.load()}"
        }
        check(failedStatuses.load() == 0) {
            "callback-stress mode=$modeName failed queue statuses=${failedStatuses.load()}"
        }
        calls.forEachIndexed { id, count ->
            val expected = if (suppressBeforeDelivery && isSuppressedId(id)) 0 else 1
            check(count.load() == expected) {
                "callback-stress mode=$modeName id=$id calls=${count.load()} expected=$expected"
            }
            check(registrations[id].isClosed) {
                "callback-stress mode=$modeName id=$id registration remained active"
            }
        }

        val suppressed = if (suppressBeforeDelivery) suppressedRegistrationCount() else 0
        println(
            "callback-stress mode=$modeName registrations=$CallbackStressRegistrationCount " +
                "submissions=$CallbackStressSubmissionCount delivered=${delivered.load()} suppressed=$suppressed " +
                "duplicates=0 memoryScope=closed pending=0",
        )
    } finally {
        registrations.forEach(CallbackRegistration<WGPUQueueWorkDoneCallback>::close)
    }
}

private fun submitBoundedCopyWork(device: WGPUDevice, queue: WGPUQueue) {
    repeat(CallbackStressSubmissionCount) {
        memoryScope { scope ->
            var source: WGPUBuffer? = null
            var destination: WGPUBuffer? = null
            var encoder: WGPUCommandEncoder? = null
            var commandBuffer: WGPUCommandBuffer? = null
            try {
                source = WGPUBufferDescriptor.allocate(scope).apply {
                    usage = WGPUBufferUsage_CopySrc
                    size = 4uL
                }.let { wgpuDeviceCreateBuffer(device, it) }
                    ?: error("callback-stress: failed to create copy source")
                destination = WGPUBufferDescriptor.allocate(scope).apply {
                    usage = WGPUBufferUsage_CopyDst
                    size = 4uL
                }.let { wgpuDeviceCreateBuffer(device, it) }
                    ?: error("callback-stress: failed to create copy destination")
                encoder = wgpuDeviceCreateCommandEncoder(device, null)
                    ?: error("callback-stress: failed to create command encoder")
                wgpuCommandEncoderCopyBufferToBuffer(encoder, source, 0uL, destination, 0uL, 4uL)
                commandBuffer = wgpuCommandEncoderFinish(encoder, null)
                    ?: error("callback-stress: failed to finish command buffer")
                val commands = scope.bufferOfAddress(commandBuffer.handler).let { WGPUCommandBuffer(it.handler) }
                wgpuQueueSubmit(queue, 1uL, commands)
            } finally {
                commandBuffer?.let(::wgpuCommandBufferRelease)
                encoder?.let(::wgpuCommandEncoderRelease)
                destination?.let(::wgpuBufferRelease)
                source?.let(::wgpuBufferRelease)
            }
        }
    }
}

private fun pumpWaitAnyBatches(
    instance: WGPUInstance,
    futureIds: List<ULong>,
    modeName: String,
) {
    var batchStart = 0
    while (batchStart < futureIds.size) {
        val batchSize = minOf(WaitAnyBatchSize, futureIds.size - batchStart)
        check((batchStart until batchStart + batchSize).all { futureIds[it] != 0uL }) {
            "callback-stress mode=$modeName waitAny encountered future-id=0 batchStart=$batchStart"
        }
        val waitStatus = memoryScope { scope ->
            val waitInfos = WGPUFutureWaitInfo.allocateArray(scope, batchSize.toUInt()) { index, info ->
                info.future = WGPUFuture.allocate(scope).apply {
                    id = futureIds[batchStart + index.toInt()]
                }
                info.completed = 0u
            }
            wgpuInstanceWaitAny(
                instance,
                batchSize.toULong(),
                WGPUFutureWaitInfo(waitInfos.handler),
                0uL,
            )
        }
        check(waitStatus == WGPUWaitStatus_Success || waitStatus == WGPUWaitStatus_TimedOut) {
            "callback-stress mode=$modeName waitAny status=$waitStatus batchStart=$batchStart"
        }
        batchStart += batchSize
    }
}

private fun awaitSingleFuture(
    instance: WGPUInstance,
    futureId: ULong,
    modeName: String,
    phase: String,
    isComplete: () -> Boolean,
    pendingIds: () -> List<Int>,
) {
    val deadline = TimeSource.Monotonic.markNow() + CallbackStressPhaseTimeout
    while (!isComplete()) {
        check(futureId != 0uL) {
            "callback-stress mode=$modeName phase=$phase future-id=0 pending=${pendingIds()}"
        }
        val waitStatus = memoryScope { scope ->
            val waitInfo = WGPUFutureWaitInfo.allocate(scope).apply {
                future = WGPUFuture.allocate(scope).apply { id = futureId }
                completed = 0u
            }
            wgpuInstanceWaitAny(instance, 1uL, waitInfo, 0uL)
        }
        check(waitStatus == WGPUWaitStatus_Success || waitStatus == WGPUWaitStatus_TimedOut) {
            "callback-stress mode=$modeName phase=$phase waitAny status=$waitStatus pending=${pendingIds()}"
        }
        if (deadline.hasPassedNow()) {
            error("callback-stress timeout mode=$modeName phase=$phase pending=${pendingIds()}")
        }
    }
}

private fun runUncapturedErrorPhase(
    device: WGPUDevice,
    queue: WGPUQueue,
    uncapturedErrorCalls: AtomicInt,
    validationErrorCalls: AtomicInt,
    errorCallbacksCompleted: AtomicInt,
) {
    val buffer = memoryScope { scope ->
        WGPUBufferDescriptor.allocate(scope).apply {
            usage = WGPUBufferUsage_CopyDst
            size = 4uL
        }.let { wgpuDeviceCreateBuffer(device, it) }
    } ?: error("callback-stress: failed to create validation buffer")

    try {
        memoryScope { scope ->
            val invalidData = scope.allocateBuffer(1uL).apply { writeByte(1) }
            repeat(2) {
                wgpuQueueWriteBuffer(queue, buffer, 0uL, invalidData.handler, 1uL)
            }
        }

        awaitAtomicCount(
            modeName = "AllowSpontaneous",
            phase = "uncaptured-errors",
            counter = errorCallbacksCompleted,
            expected = 2,
            pump = { wgpuDevicePoll(device, 0u, null) },
        )
        check(errorCallbacksCompleted.load() == 2) {
            "callback-stress completed error callbacks=${errorCallbacksCompleted.load()} expected=2"
        }
        check(uncapturedErrorCalls.load() == 2) {
            "callback-stress uncaptured-errors=${uncapturedErrorCalls.load()} expected=2"
        }
        check(validationErrorCalls.load() == 2) {
            "callback-stress validation-errors=${validationErrorCalls.load()} expected=2"
        }
        println("callback-stress uncaptured-errors=2 validation-errors=2 pending=0")
    } finally {
        wgpuBufferRelease(buffer)
    }
}

private fun awaitAtomicCount(
    modeName: String,
    phase: String,
    counter: AtomicInt,
    expected: Int,
    pump: () -> Unit,
) {
    val deadline = TimeSource.Monotonic.markNow() + CallbackStressPhaseTimeout
    while (counter.load() < expected) {
        pump()
        if (deadline.hasPassedNow()) {
            val pending = (counter.load() until expected).toList()
            error("callback-stress timeout mode=$modeName phase=$phase pending=$pending")
        }
    }
}

private fun failQueuePhase(
    modeName: String,
    phase: String,
    calls: Array<AtomicInt>,
    suppressBeforeDelivery: Boolean,
): Nothing {
    val pending = calls.indices.filter { id ->
        !(suppressBeforeDelivery && isSuppressedId(id)) && calls[id].load() == 0
    }
    error("callback-stress timeout mode=$modeName phase=$phase pending=$pending")
}

private fun callbackModeName(mode: WGPUCallbackMode): String = when (mode) {
    WGPUCallbackMode_WaitAnyOnly -> "WaitAnyOnly"
    WGPUCallbackMode_AllowProcessEvents -> "AllowProcessEvents"
    WGPUCallbackMode_AllowSpontaneous -> "AllowSpontaneous"
    else -> error("callback-stress: unsupported callback mode $mode")
}

private fun isSuppressedId(id: Int): Boolean = id % SuppressionStride == 0

private fun suppressedRegistrationCount(): Int =
    (0 until CallbackStressRegistrationCount).count(::isSuppressedId)
