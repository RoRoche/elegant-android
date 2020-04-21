package fr.guddy.elegantandroid.screens.repos

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import fr.guddy.elegantandroid.databinding.ReposBinding
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.ui.ListItemRepo
import fr.guddy.elegantandroid.ui.RenderableRepos
import fr.guddy.eoandroidui.Renderable
import fr.guddy.eoandroidui.ViewState

/**
 * Class representing a [ViewState] that binds data into [ReposBinding].
 */
sealed class ReposViewState : ViewState<ReposBinding> {

    /**
     * Single object to bind a loading state.
     */
    object IsLoading : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.VISIBLE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
        }
    }

    /**
     * Class to bind a list of repos.
     *
     * @property repos The list of repos to render.
     */
    class WithContent(
        private val repos: Renderable<RecyclerView>
    ) : ReposViewState() {

        /**
         * Secondary constructor to build [RenderableRepos].
         *
         * @param layoutInflater The [LayoutInflater] to pass to the [ReposAdapter].
         * @param repos The [List] of [Repo] to display.
         * @param listener The [OnRepoClickListener] to be notified when a row is clicked.
         */
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

    /**
     * Single object to bind an empty state.
     */
    object IsEmpty : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.VISIBLE
            binding.error.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
        }
    }

    /**
     * Single object to bind an error state.
     */
    object IsError : ReposViewState() {
        override fun bind(binding: ReposBinding) {
            binding.loading.visibility = View.GONE
            binding.empty.visibility = View.GONE
            binding.error.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }
}
