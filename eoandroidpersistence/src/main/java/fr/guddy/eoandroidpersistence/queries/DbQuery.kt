package fr.guddy.eoandroidpersistence.queries

interface DbQuery<T> {
    fun result(): T

    abstract class Wrap<T>(private val origin: DbQuery<T>) : DbQuery<T> by origin
}