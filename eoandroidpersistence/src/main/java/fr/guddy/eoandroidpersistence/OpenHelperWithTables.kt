package fr.guddy.eoandroidpersistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Class representing an [SQLiteOpenHelper] with a list of [Table].
 *
 * @property tables The list of [Table] in the database.
 * @param context The [Context] to be used.
 * @param name The database's name.
 * @param version The database's version.
 */
abstract class OpenHelperWithTables(
    private val tables: List<Table>,
    context: Context,
    name: String,
    version: Int
) : SQLiteOpenHelper(
    context,
    name,
    null,
    version
) {
    /**
     * Create all [Table] in the database.
     *
     * @param db The database where to create the tables.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        tables.forEach {
            it.create(db!!)
        }
    }

    /**
     * Drop all [Table] in the database.
     *
     * @param db The database where to drop the tables.
     */
    protected fun dropAllTables(db: SQLiteDatabase?) {
        tables.forEach {
            it.drop(db!!)
        }
    }
}