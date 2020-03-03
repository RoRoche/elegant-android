package fr.guddy.elegantandroid

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import fr.guddy.elegantandroid.databinding.RepoBinding
import fr.guddy.elegantandroid.screens.repos.OnRepoClickListener
import fr.guddy.elegantandroid.screens.repos.ReposAdapter
import fr.guddy.eoandroidui.Bindable
import fr.guddy.eoandroidui.Renderable

class RenderableRepos(
    private val origin: List<Bindable<RepoBinding>>,
    private val adapter: RecyclerView.Adapter<ReposAdapter.RepoViewHolder>
) : List<Bindable<RepoBinding>> by origin,
    Renderable<RecyclerView> {

    constructor(
        origin: List<Bindable<RepoBinding>>,
        layoutInflater: LayoutInflater,
        listener: OnRepoClickListener
    ) : this(
        origin = origin,
        adapter = ReposAdapter(
            layoutInflater = layoutInflater,
            repos = origin,
            listener = listener
        )
    )

    override fun render(view: RecyclerView) {
        view.adapter = adapter
    }
}