package fr.guddy.elegantandroid.persistence.orders

import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.orders.SqlOrder

/**
 * Create table REPO.
 *
 * @property table The [Table] describing the table REPO.
 */
class CreateTableRepo(
    private val table: Table
) : SqlOrder {
    override fun execute(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS `${table.name()}` (
                    `id` INTEGER PRIMARY KEY NOT NULL, 
                    `name` TEXT NOT NULL, 
                    `description` TEXT NOT NULL,
                    `url` TEXT NOT NULL,
                    `owner` TEXT NOT NULL
                )
                """
        )
    }
}
