package fr.guddy.elegantandroid.persistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.Table

class Select(
    private val db: SQLiteDatabase,
    private val distinct: Boolean = false,
    private val from: Table,
    private val columns: List<String> = from.columns(),
    private val where: Where? = null,
    private var groupBy: String? = null,
    private var having: String? = null,
    private var orderBy: String? = null,
    private var limit: String? = null
) : DbQuery<Cursor> {

    override fun result(): Cursor = db.query(
        distinct,
        from.name(),
        columns.toTypedArray(),
        where?.selection,
        where?.args,
        groupBy,
        having,
        orderBy,
        limit
    )
}