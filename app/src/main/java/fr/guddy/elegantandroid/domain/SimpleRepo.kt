package fr.guddy.elegantandroid.domain

import fr.guddy.elegantandroid.domain.Repo

class SimpleRepo(
    private val id: Long,
    private val name: String,
    private val description: String,
    private val url: String,
    private val owner: String
) : Repo {
    override fun id() = this.id

    override fun name() = this.name

    override fun description() = this.description

    override fun url() = this.url

    override fun owner() = this.owner
}