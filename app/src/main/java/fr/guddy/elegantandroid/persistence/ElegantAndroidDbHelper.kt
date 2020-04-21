package fr.guddy.elegantandroid.persistence

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import fr.guddy.elegantandroid.persistence.orders.CreateTableRepo
import fr.guddy.elegantandroid.persistence.orders.DropTableRepoIfExists
import fr.guddy.eoandroidpersistence.OpenHelperWithSqlOrders
import fr.guddy.eoandroidpersistence.orders.migration.ResetMigration

/**
 * Implementation of [SQLiteOpenHelper].
 *
 * @param context The [Context] to be used.
 */
class ElegantAndroidDbHelper(
    context: Context
) : OpenHelperWithSqlOrders(
    createTablesSqlOrder = CreateTableRepo(
        RepoTable()
    ),
    dbUpgrade = ResetMigration(
        DropTableRepoIfExists(
            RepoTable()
        ),
        CreateTableRepo(
            RepoTable()
        )
    ),
    context = context,
    name = NAME,
    version = VERSION
) {
    companion object {
        private const val VERSION = 1
        private const val NAME = "ElegantAndroid.db"
    }
}
