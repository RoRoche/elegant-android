package com.github.roroche.eoandroidpersistence.queries

import android.database.sqlite.SQLiteDatabase
import com.github.roroche.eoandroidpersistence.Table

/**
 * [DbQuery] to delete data.
 *
 * @property db The database where to delete data.
 * @property from The table where to delete data.
 * @property where An optional [Where] clause.
 */
class Delete(
    private val db: SQLiteDatabase,
    private val from: Table,
    private val where: Where? = null
) : DbQuery<Int> {

    /**
     * @return The number of rows deleted.
     */
    override fun result(): Int {
        return db.delete(
            from.name(),
            where?.selection,
            where?.args
        )
    }
}
