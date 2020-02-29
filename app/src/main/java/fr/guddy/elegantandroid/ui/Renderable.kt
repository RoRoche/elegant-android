package fr.guddy.elegantandroid.ui

import android.view.View

interface Renderable<T : View> {
    fun render(view: T)
}