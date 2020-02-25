package fr.guddy.elegantandroid.concurrency

import java.util.concurrent.Callable

class SimpleAsyncJob<T>(
    runnable: Runnable,
    private val onSuccess: Callback<T>,
    private val onError: Callback<Throwable>
) : Job.Async(runnable) {
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

    override fun dispose() {
        super.dispose()
        onSuccess.dispose()
        onError.dispose()
    }
}