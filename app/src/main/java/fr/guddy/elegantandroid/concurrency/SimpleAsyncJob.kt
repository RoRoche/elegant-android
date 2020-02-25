package fr.guddy.elegantandroid.concurrency

import java.util.concurrent.Callable

class SimpleAsyncJob<T>(
    runnable: Runnable,
    private val onSuccess: Callback.OnMainThread<T>,
    private val onError: Callback.OnError
) : Job.Async(runnable) {
    constructor(
        callable: Callable<T>,
        onSuccess: Callback.OnMainThread<T>,
        onError: Callback.OnError
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