package fr.guddy.elegantandroid.ui

import fr.guddy.elegantandroid.databinding.RepoBinding
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.eoandroidui.Bindable

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
