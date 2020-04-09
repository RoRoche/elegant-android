package fr.guddy.eoandroidpersistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import fr.guddy.eoandroidpersistence.orders.SqlOrder
import fr.guddy.eoandroidpersistence.orders.migration.DbDowngrade
import fr.guddy.eoandroidpersistence.orders.migration.DbUpgrade

/**
 * Class representing an [SQLiteOpenHelper] with a list of [Table].
 *
 * @property createTablesSqlOrder SQL order to create all tables.
 * @property dbUpgrade Migration to upgrade database.
 * @property dbDowngrade Migration to downgrade database.
 * @param context The [Context] to be used.
 * @param name The database's name.
 * @param version The database's version.
 */
open class OpenHelperWithSqlOrders(
    private val createTablesSqlOrder: SqlOrder,
    private val dbUpgrade: DbUpgrade,
    private val dbDowngrade: DbDowngrade,
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
        db?.let { dbUpgrade.onUpgrade(it, oldVersion, newVersion) }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { dbDowngrade.onDowngrade(it, oldVersion, newVersion) }
    }
}