package fr.guddy.eoandroidui

import androidx.viewbinding.ViewBinding

interface Bindable<T : ViewBinding> {
    fun bind(binding: T)
}