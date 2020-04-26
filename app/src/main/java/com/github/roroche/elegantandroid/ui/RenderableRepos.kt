package com.github.roroche.elegantandroid.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.github.roroche.elegantandroid.databinding.RepoBinding
import com.github.roroche.elegantandroid.screens.repos.OnRepoClickListener
import com.github.roroche.elegantandroid.screens.repos.ReposAdapter
import com.github.roroche.eoandroidui.Bindable
import com.github.roroche.eoandroidui.Renderable

/**
 * Class representing a list of repo that could be rendered into a [RecyclerView].
 *
 * @property origin The [List] of repos that could be bound to [RepoBinding].
 * @property adapter The [RecyclerView.Adapter] to use to render data.
 */
class RenderableRepos(
    private val origin: List<Bindable<RepoBinding>>,
    private val adapter: RecyclerView.Adapter<ReposAdapter.RepoViewHolder>
) : List<Bindable<RepoBinding>> by origin, Renderable<RecyclerView> {

    /**
     * Secondary constructor to build the default [ReposAdapter].
     *
     * @param origin The [List] of repos that could be bound to [RepoBinding].
     * @param layoutInflater The [LayoutInflater] to pass to the [ReposAdapter].
     * @param listener The [OnRepoClickListener] to be notified when a row is clicked.
     */
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

    /**
     * Render data into the [RecyclerView].
     *
     * @param view The [RecyclerView] where to render data.
     */
    override fun render(view: RecyclerView) {
        view.adapter = adapter
    }
}
