package fr.guddy.elegantandroid.screens.repos

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import fr.guddy.elegantandroid.*
import fr.guddy.elegantandroid.databinding.ReposBinding
import fr.guddy.eoandroidui.Renderable
import fr.guddy.eoandroidui.ViewState

sealed class ReposViewState :
    ViewState<ReposBinding> {
    object IsLoading : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.VISIBLE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
        }
    }

    class WithContent(
        private val repos: Renderable<RecyclerView>
    ) : ReposViewState() {
        constructor(
            layoutInflater: LayoutInflater,
            repos: List<Repo>,
            listener: OnRepoClickListener
        ) : this(
            RenderableRepos(
                origin = repos.map {
                    ListItemRepo(it)
                },
                layoutInflater = layoutInflater,
                listener = listener
            )
        )

        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            repos.render(binding.recyclerView)
        }
    }

    object IsEmpty : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.VISIBLE
            binding.error.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
        }
    }

    object IsError : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }
}