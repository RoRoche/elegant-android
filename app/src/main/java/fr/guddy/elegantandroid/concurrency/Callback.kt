package fr.guddy.elegantandroid.concurrency

import android.os.Handler
import android.os.Looper

@FunctionalInterface
interface Callback<T>: Disposable {
    fun accept(data: T)

    class OnMainThread<T>(
        private val delegate: Callback<T>,
        private val handler: Handler
    ) : Callback<T> by delegate {
        constructor(
            delegate: Callback<T>,
            looper: Looper
        ): this(
            delegate,
            Handler(looper)
        )

        constructor(
            delegate: Callback<T>
        ): this(
            delegate,
            Looper.getMainLooper()
        )

        override fun accept(data: T) {
            handler.post {
                delegate.accept(data)
            }
        }
    }

    interface OnError: Callback<Throwable>
}