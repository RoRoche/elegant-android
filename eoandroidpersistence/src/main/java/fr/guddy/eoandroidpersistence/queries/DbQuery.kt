package fr.guddy.eoandroidpersistence.queries

/**
 * Interface describing a query in database.
 *
 * @param T The result type.
 */
interface DbQuery<T> {
    /**
     * @return The result of the query.
     */
    fun result(): T

    /**
     * Convenient abstract class to wrap a query and use it as a delegate.
     *
     * @property origin The delegate [DbQuery].
     */
    abstract class Wrap<T>(
        private val origin: DbQuery<T>
    ) : DbQuery<T> by origin
}
