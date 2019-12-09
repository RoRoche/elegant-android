package fr.guddy.elegantandroid.ui.repo

import android.view.View
import fr.guddy.elegantandroid.Bindable
import fr.guddy.elegantandroid.ViewState
import fr.guddy.elegantandroid.databinding.ActivityRepoBinding

sealed class RepoViewState : ViewState<ActivityRepoBinding> {
    object IsLoading : RepoViewState() {
        override fun bind(binding: ActivityRepoBinding) {
            binding.loading.visibility = View.VISIBLE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.content.visibility = View.GONE
        }
    }

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

    object IsError : RepoViewState() {
        override fun bind(binding: ActivityRepoBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.VISIBLE
            binding.content.visibility = View.GONE
        }
    }
}