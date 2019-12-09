package fr.guddy.elegantandroid

import android.view.View

interface Renderable<T : View> {
    fun render(view: T)
}