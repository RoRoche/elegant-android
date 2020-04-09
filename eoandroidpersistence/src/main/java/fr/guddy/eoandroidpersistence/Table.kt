package fr.guddy.eoandroidpersistence

/**
 * Interface that describes a SQLite table.
 */
interface Table {
    /**
     * @return The table's name.
     */
    fun name(): String

    /**
     * @return The columns composing the table.
     */
    fun columns(): List<String>
}