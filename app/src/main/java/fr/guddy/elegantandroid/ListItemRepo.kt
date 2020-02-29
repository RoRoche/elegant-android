package fr.guddy.elegantandroid

import fr.guddy.elegantandroid.databinding.RepoBinding
import fr.guddy.elegantandroid.ui.Bindable

class ListItemRepo(origin: Repo): Repo.Wrap(origin),
    Bindable<RepoBinding> {
    override fun bind(binding: RepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}