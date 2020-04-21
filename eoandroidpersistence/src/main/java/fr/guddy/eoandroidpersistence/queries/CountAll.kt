package fr.guddy.eoandroidpersistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table

/**
 * [DbQuery] to count all rows in a table.
 *
 * @property origin The delegate query.
 */
class CountAll(
    private val origin: DbQuery<Cursor>
) : DbQuery<Int> {
    /**
     * Secondary constructor to build a delegate [Select] query.
     *
     * @param db The database where to count rows.
     * @param from The table where to count rows.
     * @param where An optional [Where] clause.
     */
    constructor(
        db: SQLiteDatabase,
        from: Table,
        where: Where? = null
    ) : this(
        Select(
            db = db,
            columns = listOf(COUNT_ALL),
            from = from,
            where = where
        )
    )

    /**
     * @return The number of rows in the table, according to optional [Where] clause.
     */
    override fun result(): Int {
        return origin.result().let {
            it.moveToFirst()
            it.getInt(
                it.getColumnIndexOrThrow(COUNT_ALL)
            )
        }
    }

    private companion object Constant {
        private const val COUNT_ALL = "COUNT(*)"
    }
}
