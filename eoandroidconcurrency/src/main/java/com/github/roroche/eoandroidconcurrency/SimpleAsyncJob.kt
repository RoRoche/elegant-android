package com.github.roroche.eoandroidconcurrency

import java.util.concurrent.Callable

/**
 * Convenient class to define a basic job to be performed asynchronously.
 *
 * @param runnable The [Runnable] to be performed asynchronously.
 * @property onSuccess The [Callback] to be called when succeeding.
 * @property onError The [Callback] to be called when failing.
 * @param T The result type of the job.
 */
class SimpleAsyncJob<T>(
    runnable: Runnable,
    private val onSuccess: Callback<T>,
    private val onError: Callback<Throwable>
) : Job.Async(runnable) {
    /**
     * Secondary constructor to ease the call of the primary one.
     *
     * @param callable The [Callable] to be performed asynchronously.
     * @param onSuccess The [Callback] to be called when succeeding.
     * @param onError The [Callback] to be called when failing.
     */
    @Suppress("TooGenericExceptionCaught")
    constructor(
        callable: Callable<T>,
        onSuccess: Callback<T>,
        onError: Callback<Throwable>
    ) : this(
        Runnable {
            try {
                val data = callable.call()
                onSuccess.accept(data)
            } catch (throwable: Throwable) {
                onError.accept(throwable)
            }
        },
        onSuccess,
        onError
    )

    /**
     * Method to dispose both [onSuccess] and [onError] callbacks.
     */
    override fun dispose() {
        onSuccess.dispose()
        onError.dispose()
    }
}
