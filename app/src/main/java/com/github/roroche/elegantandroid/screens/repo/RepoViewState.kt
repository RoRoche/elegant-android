package com.github.roroche.elegantandroid.screens.repo

import android.view.View
import com.github.roroche.elegantandroid.databinding.ActivityRepoBinding
import com.github.roroche.eoandroidui.Bindable
import com.github.roroche.eoandroidui.ViewState

/**
 * Class representing a [ViewState] that binds data into [ActivityRepoBinding].
 */
sealed class RepoViewState : ViewState<ActivityRepoBinding> {

    /**
     * Single object to bind a loading state.
     */
    object IsLoading : RepoViewState() {
        override fun bind(binding: ActivityRepoBinding) {
            binding.loading.visibility = View.VISIBLE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.content.visibility = View.GONE
        }
    }

    /**
     * Class to bind a single repo into [ActivityRepoBinding].
     *
     * @property repo The repo to be bound.
     */
    class WithContent(
        private val repo: Bindable<ActivityRepoBinding>
    ) : RepoViewState() {
        override fun bind(binding: ActivityRepoBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
            repo.bind(binding)
        }
    }

    /**
     * Single object to bind an error state.
     */
    object IsError : RepoViewState() {
        override fun bind(binding: ActivityRepoBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.VISIBLE
            binding.content.visibility = View.GONE
        }
    }
}
