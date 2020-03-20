package fr.guddy.eoandroidconcurrency

import android.app.Activity
import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference

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
     * Convenient class to wrap a callback.
     *
     * @param delegate The callback to be performed.
     */
    abstract class Wrap<T>(
        protected val delegate: Callback<T>
    ) : Callback<T> by delegate

    /**
     * Convenient class to perform the callback on the main thread.
     *
     * @param delegate The callback to be performed on the main thread.
     * @property handler The [Handler] to perform the callback on the main thread.
     * @param T The type of result to be passed to the callback.
     */
    class OnMainThread<T>(
        delegate: Callback<T>,
        private val handler: Handler
    ) : Callback.Wrap<T>(delegate) {

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
     * Convenient class that strongly types the callback for error management.
     */
    interface OnError : Callback<Throwable>

    /**
     * Convenient class to perform callback into an [Activity].
     *
     * @property activity The [Activity] in which to perform the callback,
     * wrapped into [WeakReference].
     * @param T The [Activity] generic parameter.
     * @param R The result to be passed to the callback.
     */
    abstract class InActivity<T : Activity, R>(
        val activity: WeakReference<T>
    ) : Callback<R> {

        /**
         * Secondary constructor to build a [WeakReference] of the [Activity].
         *
         * @param activity The [Activity] in which to perform the callback.
         */
        constructor(activity: T) : this(
            WeakReference(activity)
        )

        /**
         * Clear the [WeakReference].
         */
        override fun dispose() {
            activity.clear()
        }
    }
}
