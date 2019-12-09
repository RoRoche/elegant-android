package fr.guddy.elegantandroid

import fr.guddy.elegantandroid.databinding.ActivityRepoBinding

class DetailRepo(origin: Repo) : Repo.Wrap(origin), Bindable<ActivityRepoBinding> {
    override fun bind(binding: ActivityRepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}