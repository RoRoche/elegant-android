package com.github.roroche.eoandroidpersistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.github.roroche.eoandroidpersistence.orders.SqlOrder
import com.github.roroche.eoandroidpersistence.orders.migration.Migration

/**
 * Class representing an [SQLiteOpenHelper] with a list of [Table].
 *
 * @property createTablesSqlOrder SQL order to create all tables.
 * @property dbUpgrade Migration to upgrade database.
 * @property dbDowngrade Migration to downgrade database, default is dbUpgrade.
 * @param context The [Context] to be used.
 * @param name The database's name.
 * @param version The database's version.
 */
open class OpenHelperWithSqlOrders(
    private val createTablesSqlOrder: SqlOrder,
    private val dbUpgrade: Migration,
    private val dbDowngrade: Migration = dbUpgrade,
    context: Context,
    name: String,
    version: Int
) : SQLiteOpenHelper(
    context,
    name,
    null,
    version
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let { createTablesSqlOrder.execute(it) }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { dbUpgrade.migrate(it, oldVersion, newVersion) }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { dbDowngrade.migrate(it, oldVersion, newVersion) }
    }
}
