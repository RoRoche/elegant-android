package fr.guddy.eoandroidpersistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table

/**
 * [DbQuery] to select data from a database.
 *
 * @param db The database where to select data.
 * @param distinct true if you want each row to be unique, false otherwise.
 * @param from The table name to compile the query against.
 * @param columns A list of which columns to return. Passing null will
 *            return all columns, which is discouraged to prevent reading
 *            data from storage that isn't going to be used.
 * @param where A filter declaring which rows to return, formatted as an
 *            SQL WHERE clause (excluding the WHERE itself). Passing null
 *            will return all rows for the given table.
 * @param groupBy A filter declaring how to group rows, formatted as an SQL
 *            GROUP BY clause (excluding the GROUP BY itself). Passing null
 *            will cause the rows to not be grouped.
 * @param having A filter declare which row groups to include in the cursor,
 *            if row grouping is being used, formatted as an SQL HAVING
 *            clause (excluding the HAVING itself). Passing null will cause
 *            all row groups to be included, and is required when row
 *            grouping is not being used.
 * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
 *            (excluding the ORDER BY itself). Passing null will use the
 *            default sort order, which may be unordered.
 * @param limit Limits the number of rows returned by the query,
 *            formatted as LIMIT clause. Passing null denotes no LIMIT clause.
 */
@Suppress("LongParameterList")
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

    /**
     * @return A [Cursor] object, containing data, which is positioned before the first entry.
     */
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
