package fr.guddy.eoandroidconcurrency

import android.os.Handler
import android.os.Looper

/**
 * Interface to describe the contract of being "called back".
 *
 * @param T The type of result to be passed to the callback.
 */
@FunctionalInterface
interface Callback<T> : Disposable {

    /**
     * Method to implement in order to be "called back".
     *
     * @param data The result to pass to the callback.
     */
    fun accept(data: T)

    /**
     * Convenient class to perform the callback on the main thread.
     *
     * @param delegate The callback to be performed on the main thread.
     * @param handler The [Handler] to perform the callback on the main thread.
     * @param T The type of result to be passed to the callback.
     */
    class OnMainThread<T>(
        private val delegate: Callback<T>,
        private val handler: Handler
    ) : Callback<T> by delegate {

        /**
         * Secondary constructor to ease the call of the primary one.
         *
         * @param delegate The callback to be performed on the main thread.
         * @param looper The [Looper] to perform the callback on the main thread.Ò
         */
        constructor(
            delegate: Callback<T>,
            looper: Looper
        ) : this(
            delegate,
            Handler(looper)
        )

        /**
         * Secondary constructor to ease the call of the primary one.
         *
         * @param delegate The callback to be performed on the main thread.
         */
        constructor(
            delegate: Callback<T>
        ) : this(
            delegate,
            Looper.getMainLooper()
        )

        /**
         * Method to implement in order to be "called back".
         *
         * @param data The result to pass to the callback.
         */
        override fun accept(data: T) {
            handler.post {
                delegate.accept(data)
            }
        }
    }

    /**
     * Convenient class that strongly type the callback for error management.
     */
    interface OnError : Callback<Throwable>
}