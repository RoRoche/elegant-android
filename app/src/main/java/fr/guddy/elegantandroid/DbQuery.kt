package fr.guddy.elegantandroid

interface DbQuery<T> {
    fun result(): T

    abstract class Wrap<T>(private val origin: DbQuery<T>) : DbQuery<T> by origin
}