package com.github.roroche.eoandroidui

import android.app.Activity
import android.content.Intent

/**
 * Class describing a navigation to a specific destination.
 */
interface Destination {

    /**
     * Start the navigation.
     */
    fun go()

    /**
     * Convenient abstract class to wrap a [Destination] and use it as a delegate.
     *
     * @property delegate The delegate [Destination].
     */
    abstract class Wrap(
        private val delegate: Destination
    ) : Destination by delegate

    /**
     * Convenient class to start an [Activity] from another.
     *
     * @property origin The current [Activity].
     * @property intent The [Intent] describing the [Activity] to start.
     */
    class ToActivity(
        private val origin: Activity,
        private val intent: Intent
    ) : Destination {
        /**
         * Start the [Activity].
         */
        override fun go() {
            origin.startActivity(intent)
        }
    }
}
