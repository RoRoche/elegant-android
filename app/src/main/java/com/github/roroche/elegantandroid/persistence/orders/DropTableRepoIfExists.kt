package com.github.roroche.elegantandroid.persistence.orders

import android.database.sqlite.SQLiteDatabase
import com.github.roroche.eoandroidpersistence.Table
import com.github.roroche.eoandroidpersistence.orders.SqlOrder

/**
 * Drop table REPO if exists.
 *
 * @property table The [Table] describing the table REPO.
 */
class DropTableRepoIfExists(
    private val table: Table
) : SqlOrder {
    override fun execute(db: SQLiteDatabase) {
        db.execSQL(
            """
                DROP TABLE IF EXISTS ${table.name()}
            """
        )
    }
}
