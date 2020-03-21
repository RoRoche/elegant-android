package fr.guddy.elegantandroid.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import fr.guddy.eoandroidpersistence.Table

/**
 * Implementation of [SQLiteOpenHelper].
 *
 * @param context The [Context] to be used.
 * @property tables The list of [Table] in the database.
 */
class ElegantAndroidDbHelper(
    context: Context,
    private val tables: List<Table>
) : SQLiteOpenHelper(
    context,
    NAME,
    null,
    VERSION
) {
    companion object {
        private const val VERSION = 1
        private const val NAME = "ElegantAndroid.db"
    }

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
     * Upgrade all the [Table] in the database.
     *
     * @param db The database to upgrade.
     * @param oldVersion The previous version of the database.
     * @param newVersion The new version of the database.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        tables.forEach {
            it.drop(db!!)
        }
        onCreate(db)
    }

    /**
     * Downgrade all the [Table] in the database.
     *
     * @param db The database to downgrade.
     * @param oldVersion The previous version of the database.
     * @param newVersion The new version of the database.
     */
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}