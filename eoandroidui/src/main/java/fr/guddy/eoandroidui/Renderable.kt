package fr.guddy.eoandroidui

import android.view.View

/**
 * Interface describing data that can be rendered into a specific [View].
 */
interface Renderable<T : View> {
    /**
     * Render data into the [View].
     *
     * @param view The [View] where to render data.
     */
    fun render(view: T)
}
