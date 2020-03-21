package fr.guddy.elegantandroid.domain

/**
 * A simple implementation of the [Repo] interface.
 *
 * @property id The repo's ID.
 * @property name The repo's ID.
 * @property description The repo's ID.
 * @property url The repo's ID.
 * @property owner The repo's ID.
 */
class SimpleRepo(
    private val id: Long,
    private val name: String,
    private val description: String,
    private val url: String,
    private val owner: String
) : Repo {

    /**
     * @return The repo's ID.
     */
    override fun id() = this.id

    /**
     * @return The repo's name.
     */
    override fun name() = this.name

    /**
     * @return The repo's description.
     */
    override fun description() = this.description

    /**
     * @return The repo's URL.
     */
    override fun url() = this.url

    /**
     * @return The repo's owner.
     */
    override fun owner() = this.owner
}