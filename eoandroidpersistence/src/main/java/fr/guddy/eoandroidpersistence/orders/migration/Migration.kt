package com.github.roroche.eoandroidpersistence.orders.migration

import android.database.sqlite.SQLiteDatabase
import com.github.roroche.eoandroidpersistence.Table
import com.github.roroche.eoandroidpersistence.orders.SqlOrder

/**
 * Database migration.
 */
interface Migration {
    /**
     * Migrate all the [Table] in the database.
     *
     * @param db The database to migrate.
     * @param oldVersion The previous version of the database.
     * @param newVersion The new version of the database.
     */
    fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)

    /**
     * Convenient class to execute SQL order on upgrade.
     *
     * @property sqlOrder SQL order to execute.
     */
    abstract class WithSqlOrder(
        private val sqlOrder: SqlOrder
    ) : Migration {
        override fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            sqlOrder.execute(db)
        }
    }
}
