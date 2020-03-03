package fr.guddy.elegantandroid.screens.repos

import fr.guddy.elegantandroid.domain.Repo

interface OnRepoClickListener {
    fun onClickRepo(repo: Repo)
}