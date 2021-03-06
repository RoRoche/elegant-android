package com.github.roroche.elegantandroid.screens.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.roroche.elegantandroid.databinding.RepoBinding
import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.eoandroidui.Bindable

/**
 * [RecyclerView.Adapter] to display a list of repos into a [RecyclerView].
 *
 * @property layoutInflater The [LayoutInflater] to be used.
 * @property repos The list of repos to display.
 * @property listener The [OnRepoClickListener] to be called when a repo is clicked.
 */
class ReposAdapter(
    private val layoutInflater: LayoutInflater,
    private val repos: List<Bindable<RepoBinding>>,
    private val listener: OnRepoClickListener
) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    /**
     * [RecyclerView.ViewHolder] to reuse cells in [RecyclerView].
     *
     * @property binding The [RepoBinding] to display.
     */
    inner class RepoViewHolder(
        val binding: RepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val position = adapterPosition
                    listener.onClickRepo(repos[position] as Repo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoBinding.inflate(
                layoutInflater
            )
        )
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        repos[position].bind(holder.binding)
    }
}
