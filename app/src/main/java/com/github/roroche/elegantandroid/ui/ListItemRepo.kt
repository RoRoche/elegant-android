package com.github.roroche.elegantandroid.ui

import com.github.roroche.elegantandroid.databinding.RepoBinding
import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.eoandroidui.Bindable

/**
 * Class representing a [Repo] that could be bound to a list item view.
 *
 * @param origin The delegate [Repo].
 */
class ListItemRepo(
    origin: Repo
) : Repo.Wrap(origin), Bindable<RepoBinding> {

    /**
     * Print object into the [RepoBinding] parameter.
     *
     * @param binding The [RepoBinding] where to display data.
     */
    override fun bind(binding: RepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}
