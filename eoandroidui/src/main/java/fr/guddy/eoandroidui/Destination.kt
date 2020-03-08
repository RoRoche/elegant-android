package fr.guddy.eoandroidui

import android.app.Activity
import android.content.Intent

interface Destination {
    fun go()

    abstract class Wrap(
        private val delegate: Destination
    ) : Destination by delegate

    class ToActivity(
        private val origin: Activity,
        private val intent: Intent
    ) : Destination {
        override fun go() {
            origin.startActivity(intent)
        }
    }
}