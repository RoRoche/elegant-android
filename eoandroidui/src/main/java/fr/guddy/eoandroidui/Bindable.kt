package fr.guddy.eoandroidui

import androidx.viewbinding.ViewBinding

/**
 * Class describing an object that can be bound to [ViewBinding].
 *
 * @param T The [ViewBinding] where to display data.
 */
interface Bindable<T : ViewBinding> {
    /**
     * Print object into the [ViewBinding] parameter.
     *
     * @param binding The [ViewBinding] where to display data.
     */
    fun bind(binding: T)
}
