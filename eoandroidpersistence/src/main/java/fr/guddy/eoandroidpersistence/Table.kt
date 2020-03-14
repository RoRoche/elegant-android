package fr.guddy.eoandroidpersistence

import android.database.sqlite.SQLiteDatabase

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

    /**
     * Create the table in the database.
     *
     * @param db The database in which the table must be created.
     */
    fun create(db: SQLiteDatabase)

    /**
     * Drop the table in the database.
     *
     * @param db The database in which the table must be dropped.
     */
    fun drop(db: SQLiteDatabase)
}