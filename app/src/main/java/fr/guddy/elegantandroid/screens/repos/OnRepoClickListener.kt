package fr.guddy.elegantandroid.screens.repos

import fr.guddy.elegantandroid.domain.Repo

/**
 * Listener to be called when a repo is clicked in a list.
 */
interface OnRepoClickListener {

    /**
     * Method called when a repos is clicked in a list.
     *
     * @param repo The clicked [Repo].
     */
    fun onClickRepo(repo: Repo)
}