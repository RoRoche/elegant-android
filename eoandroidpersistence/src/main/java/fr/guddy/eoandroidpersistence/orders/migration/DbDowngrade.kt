package fr.guddy.eoandroidpersistence.orders.migration

import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.orders.SqlOrder

/**
 * Database migration to downgrade version.
 */
interface DbDowngrade {
    /**
     * Downgrade all the [Table] in the database.
     *
     * @param db The database to downgrade.
     * @param oldVersion The previous version of the database.
     * @param newVersion The new version of the database.
     */
    fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)

    /**
     * Convenient class to execute SQL order on downgrade.
     *
     * @property sqlOrder SQL order to execute.
     */
    abstract class WithSqlOrder(
        private val sqlOrder: SqlOrder
    ) : DbDowngrade {
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            sqlOrder.execute(db)
        }
    }
}