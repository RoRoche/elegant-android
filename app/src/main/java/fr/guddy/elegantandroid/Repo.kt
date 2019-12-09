package fr.guddy.elegantandroid

interface Repo {
    fun id(): Long
    fun name(): String
    fun description(): String
    fun url(): String
    fun owner(): String

    abstract class Wrap(private val origin: Repo) : Repo by origin
}