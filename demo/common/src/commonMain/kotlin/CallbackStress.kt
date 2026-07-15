@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.memoryScope
import kotlin.concurrent.atomics.AtomicInt
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

private const val CallbackStressRegistrationCount = 1_000
private const val CallbackStressSubmissionCount = 64
private const val WaitAnyBatchSize = 64
private const val SuppressionStride = 10
private val CallbackStressPhaseTimeout = 20.seconds

internal enum class QueueZeroFuturePolicy {
    REJECT,
    ALLOW_WGPU_NATIVE_V29_POLL_ONLY,
}

internal enum class QueueFuturePumping {
    WAIT_ANY,
    WGPU_NATIVE_V29_POLL_ONLY,
}

internal fun selectQueueFuturePumping(
    futureIds: List<ULong>,
    zeroFuturePolicy: QueueZeroFuturePolicy = QueueZeroFuturePolicy.REJECT,
): QueueFuturePumping {
    check(futureIds.isNotEmpty()) { "queue callback future set is empty" }
    val zeroCount = futureIds.count { it == 0uL }
    check(zeroCount == 0 || zeroCount == futureIds.size) {
        "queue callback futures mix zero and nonzero IDs"
    }
    if (zeroCount == 0) return QueueFuturePumping.WAIT_ANY
    check(zeroFuturePolicy == QueueZeroFuturePolicy.ALLOW_WGPU_NATIVE_V29_POLL_ONLY) {
        "queue callback futures are all zero without the wgpu-native v29 opt-in"
    }
    return QueueFuturePumping.WGPU_NATIVE_V29_POLL_ONLY
}

fun runCallbackStress() {
    val instance = wgpuCreateInstance(null) ?: error("callback-stress: failed to create instance")
    var adapter: WGPUAdapter? = null
    var device: WGPUDevice? = null
    var queue: WGPUQueue? = null
    var errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>? = null
    var primaryFailure: Throwable? = null

    val uncapturedErrorCalls = AtomicInt(0)
    val validationErrorCalls = AtomicInt(0)
    val errorCallbacksCompleted = AtomicInt(0)
    val errorCallbacksInFlight = AtomicInt(0)
    val firstUnexpectedError = CallbackOutcomeState<CallbackDiagnostic>()

    try {
        adapter = requestStressAdapter(instance)

        errorRegistration = WGPUUncapturedErrorCallback.register(CallbackPolicy.REPEATING) { _, type, message, _ ->
            errorCallbacksInFlight.fetchAndAdd(1)
            try {
                val ordinal = uncapturedErrorCalls.fetchAndAdd(1) + 1
                if (type == WGPUErrorType_Validation) validationErrorCalls.fetchAndAdd(1)
                try {
                    val messageText = message.data?.toKString(message.length)
                    if (type != WGPUErrorType_Validation || ordinal > 2) {
                        firstUnexpectedError.publish(CallbackDiagnostic(type, messageText))
                    }
                    println("callback-stress uncaptured-error type=$type message=${messageText.orEmpty()}")
                } catch (failure: Throwable) {
                    firstUnexpectedError.publishFailure(failure)
                }
            } finally {
                errorCallbacksCompleted.fetchAndAdd(1)
                errorCallbacksInFlight.fetchAndAdd(-1)
            }
        }
        device = requestStressDevice(
            adapter = adapter,
            instance = instance,
            errorRegistration = errorRegistration,
        )
        queue = wgpuDeviceGetQueue(device) ?: error("callback-stress: failed to get queue")

        runQueueCallbackPhase(
            instance,
            device,
            queue,
            WGPUCallbackMode_WaitAnyOnly,
            QueueZeroFuturePolicy.ALLOW_WGPU_NATIVE_V29_POLL_ONLY,
        )
        runQueueCallbackPhase(
            instance,
            device,
            queue,
            WGPUCallbackMode_AllowProcessEvents,
            QueueZeroFuturePolicy.ALLOW_WGPU_NATIVE_V29_POLL_ONLY,
        )
        runQueueCallbackPhase(
            instance,
            device,
            queue,
            WGPUCallbackMode_AllowSpontaneous,
            QueueZeroFuturePolicy.ALLOW_WGPU_NATIVE_V29_POLL_ONLY,
        )

        runUncapturedErrorPhase(
            device = device,
            queue = queue,
            uncapturedErrorCalls = uncapturedErrorCalls,
            validationErrorCalls = validationErrorCalls,
            errorCallbacksCompleted = errorCallbacksCompleted,
            errorCallbacksInFlight = errorCallbacksInFlight,
            errorRegistration = errorRegistration,
            firstUnexpectedError = firstUnexpectedError,
        )

        println("callback-stress complete pending=0")
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        val cleanup = GpuCleanupStack().apply {
            defer { wgpuInstanceRelease(instance) }
            adapter?.let { ownedAdapter -> defer { wgpuAdapterRelease(ownedAdapter) } }
            device?.let { ownedDevice -> defer { wgpuDeviceRelease(ownedDevice) } }
            queue?.let { ownedQueue -> defer { wgpuQueueRelease(ownedQueue) } }
            errorRegistration?.let { registration ->
                defer {
                    val ownedDevice = device
                    if (ownedDevice == null) {
                        registration.close()
                    } else {
                        closeAndAwaitCallbackQuiescence(
                            phase = "callback-stress-uncaptured-error-final-close",
                            close = registration::close,
                            isClosed = { registration.isClosed },
                            isQuiescent = { registration.isQuiescent },
                            applicationInFlight = errorCallbacksInFlight::load,
                            pump = { wgpuDevicePoll(ownedDevice, 0u, null) },
                        )
                    }
                }
            }
        }
        cleanup.close(primaryFailure)
    }
}

private fun requestStressAdapter(instance: WGPUInstance): WGPUAdapter {
    val state = CallbackRequestState<WGPURequestAdapterStatus, WGPUAdapter>(::wgpuAdapterRelease)
    val requestRegistration = WGPURequestAdapterCallback.register(CallbackPolicy.ONCE) { status, adapter, message, _ ->
        state.publishCopied(status, adapter) { message.data?.toKString(message.length) }
    }
    var futureId = 0uL

    val snapshot = awaitCallbackRequestResult(
        state = state,
        phase = "callback-stress-request-adapter",
        await = {
            futureId = memoryScope { scope ->
                val options = WGPURequestAdapterOptions.allocate(scope)
                val requestInfo = WGPURequestAdapterCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_AllowProcessEvents,
                    requestRegistration,
                )
                wgpuInstanceRequestAdapter(instance, options, requestInfo).id
            }
            awaitCallbackFutureOrPump(
                futureId = futureId,
                phase = "callback-stress-request-adapter",
                isComplete = { state.isComplete },
                pumpWithoutFuture = { wgpuInstanceProcessEvents(instance) },
                waitOnce = { waitAnyOnce(instance, it) },
            )
        },
        close = requestRegistration::close,
        isClosed = { requestRegistration.isClosed },
        isQuiescent = { requestRegistration.isQuiescent },
        pump = { pumpStressRequestCleanup(instance, futureId, "request-adapter") },
    )
    return resolveAdapterRequestResult(
        status = snapshot.status,
        adapter = state.takeHandle(),
        message = snapshot.message,
        release = ::wgpuAdapterRelease,
    )
}

private fun requestStressDevice(
    adapter: WGPUAdapter,
    instance: WGPUInstance,
    errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>,
): WGPUDevice {
    val state = CallbackRequestState<WGPURequestDeviceStatus, WGPUDevice>(::wgpuDeviceRelease)
    val requestRegistration = WGPURequestDeviceCallback.register(CallbackPolicy.ONCE) { status, device, message, _ ->
        state.publishCopied(status, device) { message.data?.toKString(message.length) }
    }
    var futureId = 0uL

    val snapshot = awaitCallbackRequestResult(
        state = state,
        phase = "callback-stress-request-device",
        await = {
            futureId = memoryScope { scope ->
                val descriptor = WGPUDeviceDescriptor.allocate(scope).apply {
                    uncapturedErrorCallbackInfo = WGPUUncapturedErrorCallbackInfo.allocate(
                        scope,
                        errorRegistration,
                    )
                }
                val requestInfo = WGPURequestDeviceCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_AllowProcessEvents,
                    requestRegistration,
                )
                wgpuAdapterRequestDevice(adapter, descriptor, requestInfo).id
            }
            awaitCallbackFutureOrPump(
                futureId = futureId,
                phase = "callback-stress-request-device",
                isComplete = { state.isComplete },
                pumpWithoutFuture = { wgpuInstanceProcessEvents(instance) },
                waitOnce = { waitAnyOnce(instance, it) },
            )
        },
        close = requestRegistration::close,
        isClosed = { requestRegistration.isClosed },
        isQuiescent = { requestRegistration.isQuiescent },
        pump = { pumpStressRequestCleanup(instance, futureId, "request-device") },
    )
    return resolveDeviceRequestResult(
        status = snapshot.status,
        device = state.takeHandle(),
        message = snapshot.message,
        release = ::wgpuDeviceRelease,
    )
}

private fun runQueueCallbackPhase(
    instance: WGPUInstance,
    device: WGPUDevice,
    queue: WGPUQueue,
    mode: WGPUCallbackMode,
    zeroFuturePolicy: QueueZeroFuturePolicy,
) {
    val modeName = callbackModeName(mode)
    val deadline = TimeSource.Monotonic.markNow() + CallbackStressPhaseTimeout
    val calls = Array(CallbackStressRegistrationCount) { AtomicInt(0) }
    val delivered = AtomicInt(0)
    val queueCallbacksCompleted = AtomicInt(0)
    val duplicates = AtomicInt(0)
    val failedStatuses = AtomicInt(0)
    val firstQueueFailure = CallbackOutcomeState<CallbackDiagnostic>()
    val registrations = mutableListOf<CallbackRegistration<WGPUQueueWorkDoneCallback>>()
    val futureIds = mutableListOf<ULong>()
    val suppressBeforeDelivery = mode != WGPUCallbackMode_AllowSpontaneous
    var primaryFailure: Throwable? = null

    try {
        repeat(CallbackStressRegistrationCount) { id ->
            val registration = WGPUQueueWorkDoneCallback.register(CallbackPolicy.ONCE) { status, message, _ ->
                try {
                    val previous = calls[id].fetchAndAdd(1)
                    if (previous == 0) delivered.fetchAndAdd(1) else duplicates.fetchAndAdd(1)
                    if (status != WGPUQueueWorkDoneStatus_Success) {
                        failedStatuses.fetchAndAdd(1)
                        firstQueueFailure.publishCatching {
                            CallbackDiagnostic(status, message.data?.toKString(message.length))
                        }
                    }
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
        val futurePumping = selectQueueFuturePumping(futureIds, zeroFuturePolicy)

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

        while (queueCallbacksCompleted.load() < expectedDeliveries) {
            pumpQueueCallbackEvents(
                instance = instance,
                device = device,
                futureIds = futureIds,
                mode = mode,
                modeName = modeName,
                futurePumping = futurePumping,
            )
            if (deadline.hasPassedNow()) {
                failQueuePhase(modeName, "delivery", calls, suppressBeforeDelivery)
            }
        }

        while (registrations.any { registration -> registration.isQuiescent.not() }) {
            pumpQueueCallbackEvents(
                instance = instance,
                device = device,
                futureIds = futureIds,
                mode = mode,
                modeName = modeName,
                futurePumping = futurePumping,
            )
            if (deadline.hasPassedNow()) {
                val pending = registrations.indices.filter { id -> registrations[id].isQuiescent.not() }
                error("callback-stress timeout mode=$modeName phase=quiescence pending=$pending")
            }
        }

        pumpQueueCallbackEvents(
            instance = instance,
            device = device,
            futureIds = futureIds,
            mode = mode,
            modeName = modeName,
            futurePumping = futurePumping,
        )

        check(queueCallbacksCompleted.load() == expectedDeliveries) {
            "callback-stress mode=$modeName completed=${queueCallbacksCompleted.load()} " +
                "expected=$expectedDeliveries"
        }
        check(duplicates.load() == 0) {
            "callback-stress mode=$modeName duplicate deliveries=${duplicates.load()}"
        }
        firstQueueFailure.failureOrNull()?.let { throw it }
        val firstQueueDiagnostic =
            (firstQueueFailure.outcome() as? CallbackOutcome.Success)?.value
        check(failedStatuses.load() == 0) {
            "callback-stress mode=$modeName failed queue statuses=${failedStatuses.load()} " +
                "first=$firstQueueDiagnostic"
        }
        calls.forEachIndexed { id, count ->
            val expected = if (suppressBeforeDelivery && isSuppressedId(id)) 0 else 1
            check(count.load() == expected) {
                "callback-stress mode=$modeName id=$id calls=${count.load()} expected=$expected"
            }
            check(registrations[id].isClosed) {
                "callback-stress mode=$modeName id=$id registration remained active"
            }
            check(registrations[id].isQuiescent) {
                "callback-stress mode=$modeName id=$id registration remained non-quiescent"
            }
        }

        val suppressed = if (suppressBeforeDelivery) suppressedRegistrationCount() else 0
        val futurePumpingDiagnostic = when (futurePumping) {
            QueueFuturePumping.WAIT_ANY -> "waitAny"
            QueueFuturePumping.WGPU_NATIVE_V29_POLL_ONLY -> "poll0-v29"
        }
        val upstreamModeValidation = when (futurePumping) {
            QueueFuturePumping.WAIT_ANY -> "verified"
            QueueFuturePumping.WGPU_NATIVE_V29_POLL_ONLY -> "unavailable"
        }
        println(
            "callback-stress mode=$modeName registrations=$CallbackStressRegistrationCount " +
                "submissions=$CallbackStressSubmissionCount delivered=${delivered.load()} suppressed=$suppressed " +
                "duplicates=0 memoryScope=closed pending=0 futurePumping=$futurePumpingDiagnostic " +
                "upstreamModeValidation=$upstreamModeValidation",
        )
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        val cleanup = GpuCleanupStack()
        registrations.forEach { registration ->
            cleanup.defer {
                closeAndAwaitCallbackQuiescence(
                    phase = "callback-stress-$modeName-final-close",
                    close = registration::close,
                    isClosed = { registration.isClosed },
                    isQuiescent = { registration.isQuiescent },
                    applicationInFlight = { 0 },
                    pump = {
                        wgpuDevicePoll(device, 0u, null)
                        wgpuInstanceProcessEvents(instance)
                        val waitableFutureIds = futureIds.filter { futureId -> futureId != 0uL }
                        if (waitableFutureIds.isNotEmpty()) {
                            pumpWaitAnyBatches(instance, waitableFutureIds, modeName)
                        }
                    },
                )
            }
        }
        cleanup.close(primaryFailure)
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
                val commands = scope.bufferOfAddress(commandBuffer.handler).handler
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

private fun pumpQueueCallbackEvents(
    instance: WGPUInstance,
    device: WGPUDevice,
    futureIds: List<ULong>,
    mode: WGPUCallbackMode,
    modeName: String,
    futurePumping: QueueFuturePumping,
) {
    if (futurePumping == QueueFuturePumping.WGPU_NATIVE_V29_POLL_ONLY) {
        wgpuDevicePoll(device, 0u, null)
        return
    }

    when (mode) {
        WGPUCallbackMode_WaitAnyOnly -> {
            wgpuDevicePoll(device, 0u, null)
            pumpWaitAnyBatches(instance, futureIds, modeName)
        }

        WGPUCallbackMode_AllowProcessEvents -> wgpuInstanceProcessEvents(instance)
        WGPUCallbackMode_AllowSpontaneous -> wgpuDevicePoll(device, 0u, null)
        else -> error("callback-stress: unsupported callback mode $mode")
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

private fun pumpStressRequestCleanup(instance: WGPUInstance, futureId: ULong, phase: String) {
    if (futureId == 0uL) {
        wgpuInstanceProcessEvents(instance)
        return
    }
    val status = waitAnyOnce(instance, futureId)
    check(status == WGPUWaitStatus_Success || status == WGPUWaitStatus_TimedOut) {
        "callback-stress $phase-close waitAny status=$status future-id=$futureId"
    }
}

private fun runUncapturedErrorPhase(
    device: WGPUDevice,
    queue: WGPUQueue,
    uncapturedErrorCalls: AtomicInt,
    validationErrorCalls: AtomicInt,
    errorCallbacksCompleted: AtomicInt,
    errorCallbacksInFlight: AtomicInt,
    errorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>,
    firstUnexpectedError: CallbackOutcomeState<CallbackDiagnostic>,
) {
    val buffer = memoryScope { scope ->
        WGPUBufferDescriptor.allocate(scope).apply {
            usage = WGPUBufferUsage_CopyDst
            size = 4uL
        }.let { wgpuDeviceCreateBuffer(device, it) }
    } ?: error("callback-stress: failed to create validation buffer")
    var primaryFailure: Throwable? = null

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
            applicationInFlight = errorCallbacksInFlight::load,
            pump = { wgpuDevicePoll(device, 0u, null) },
        )

        closeAndAwaitCallbackQuiescence(
            phase = "uncaptured-error-close",
            close = errorRegistration::close,
            isClosed = { errorRegistration.isClosed },
            isQuiescent = { errorRegistration.isQuiescent },
            applicationInFlight = errorCallbacksInFlight::load,
            pump = { wgpuDevicePoll(device, 0u, null) },
        )

        firstUnexpectedError.failureOrNull()?.let { throw it }
        val unexpected = (firstUnexpectedError.outcome() as? CallbackOutcome.Success)?.value
        check(errorCallbacksCompleted.load() == 2) {
            "callback-stress completed error callbacks=${errorCallbacksCompleted.load()} expected=2 " +
                "firstUnexpected=$unexpected"
        }
        check(uncapturedErrorCalls.load() == 2) {
            "callback-stress uncaptured-errors=${uncapturedErrorCalls.load()} expected=2 " +
                "firstUnexpected=$unexpected"
        }
        check(validationErrorCalls.load() == 2) {
            "callback-stress validation-errors=${validationErrorCalls.load()} expected=2 " +
                "firstUnexpected=$unexpected"
        }
        check(unexpected == null) {
            "callback-stress unexpected uncaptured-error $unexpected"
        }
        println(
            "callback-stress uncaptured-errors=2 validation-errors=2 " +
                "registration=closed inFlight=0 pending=0",
        )
    } catch (failure: Throwable) {
        primaryFailure = failure
        throw failure
    } finally {
        GpuCleanupStack().apply {
            defer { wgpuBufferRelease(buffer) }
        }.close(primaryFailure)
    }
}

private fun awaitAtomicCount(
    modeName: String,
    phase: String,
    counter: AtomicInt,
    expected: Int,
    applicationInFlight: () -> Int,
    pump: () -> Unit,
) {
    val deadline = TimeSource.Monotonic.markNow() + CallbackStressPhaseTimeout
    while (counter.load() < expected) {
        pump()
        if (deadline.hasPassedNow()) {
            val pending = (counter.load() until expected).toList()
            error(
                "callback-stress timeout mode=$modeName phase=$phase pending=$pending " +
                    "inFlight=${applicationInFlight()}",
            )
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
