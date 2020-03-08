package fr.guddy.eoandroidui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding

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