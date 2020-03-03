package fr.guddy.elegantandroid.ui

import fr.guddy.elegantandroid.databinding.ActivityRepoBinding
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.eoandroidui.Bindable

class DetailRepo(
    origin: Repo
) : Repo.Wrap(origin), Bindable<ActivityRepoBinding> {
    override fun bind(binding: ActivityRepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}