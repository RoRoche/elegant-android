package fr.guddy.elegantandroid.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import fr.guddy.eoandroidui.ViewState

class MutableViewState<T : ViewBinding>(
    lifecycleOwner: LifecycleOwner,
    private val binding: T
) : MutableLiveData<ViewState<T>>() {
    init {
        this.observe(
            lifecycleOwner,
            Observer {
                it.bind(binding)
            }
        )
    }
}