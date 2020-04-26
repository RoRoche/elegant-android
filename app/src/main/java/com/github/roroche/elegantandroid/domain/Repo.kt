package com.github.roroche.elegantandroid.domain

/**
 * Interface describing a GitHub repo.
 */
interface Repo {

    /**
     * @return The repo's ID.
     */
    fun id(): Long

    /**
     * @return The repo's name.
     */
    fun name(): String

    /**
     * @return The repo's description.
     */
    fun description(): String

    /**
     * @return The repo's URL.
     */
    fun url(): String

    /**
     * @return The repo's owner.
     */
    fun owner(): String

    /**
     * Convenient class to wrap a [Repo] and delegate its behavior.
     *
     * @property origin The delegate [Repo].
     */
    abstract class Wrap(private val origin: Repo) : Repo by origin
}
