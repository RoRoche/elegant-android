package com.github.roroche.elegantandroid.ui

import com.github.roroche.elegantandroid.databinding.ActivityRepoBinding
import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.eoandroidui.Bindable

/**
 * Class representing a [Repo] that could be bound to a detail view.
 *
 * @param origin The delegate [Repo].
 */
class DetailRepo(
    origin: Repo
) : Repo.Wrap(origin), Bindable<ActivityRepoBinding> {

    /**
     * Print object into the [ActivityRepoBinding] parameter.
     *
     * @param binding The [ActivityRepoBinding] where to display data.
     */
    override fun bind(binding: ActivityRepoBinding) {
        binding.name.text = name()
        binding.description.text = description()
    }
}
