package com.github.roroche.eoandroidui

import androidx.viewbinding.ViewBinding

/**
 * Interface describing a view state that can be bound into a [ViewBinding].
 */
interface ViewState<T : ViewBinding> : Bindable<T>
