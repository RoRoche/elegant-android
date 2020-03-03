package fr.guddy.eoandroidpersistence.queries

import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table

class Delete(
    private val db: SQLiteDatabase,
    private val from: Table,
    private val where: Where? = null
) : DbQuery<Int> {

    override fun result(): Int {
        return db.delete(from.name(), where?.selection, where?.args)
    }
}