package fr.guddy.eoandroidui

import android.view.View

interface Renderable<T : View> {
    fun render(view: T)
}