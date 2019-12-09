package fr.guddy.elegantandroid

import androidx.viewbinding.ViewBinding

interface Bindable<T : ViewBinding> {
    fun bind(binding: T)
}