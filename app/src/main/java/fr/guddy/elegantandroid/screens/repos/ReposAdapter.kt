package fr.guddy.elegantandroid.screens.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.guddy.eoandroidui.Bindable
import fr.guddy.elegantandroid.Repo
import fr.guddy.elegantandroid.databinding.RepoBinding

class ReposAdapter(
    private val layoutInflater: LayoutInflater,
    private val repos: List<Bindable<RepoBinding>>,
    private val listener: OnRepoClickListener
) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
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