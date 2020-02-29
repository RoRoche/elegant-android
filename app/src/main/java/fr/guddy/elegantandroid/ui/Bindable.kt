package fr.guddy.elegantandroid.ui

import androidx.viewbinding.ViewBinding

interface Bindable<T : ViewBinding> {
    fun bind(binding: T)
}