package fr.guddy.elegantandroid.ui

import fr.guddy.elegantandroid.databinding.RepoBinding
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.eoandroidui.Bindable

class ListItemRepo(
    origin: Repo
) : Repo.Wrap(origin), Bindable<RepoBinding> {
    override fun bind(binding: RepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}