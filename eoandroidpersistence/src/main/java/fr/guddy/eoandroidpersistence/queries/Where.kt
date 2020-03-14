package fr.guddy.eoandroidpersistence.queries

/**
 * A SQL WHERE clause.
 *
 * @property selection A filter declaring which rows to return, formatted as an
 *            SQL WHERE clause (excluding the WHERE itself). Passing null
 *            will return all rows for the given table.
 * @property args You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in order that they
 *         appear in the selection. The values will be bound as Strings.
 */
class Where(
    val selection: String,
    val args: Array<String>
)