package fr.guddy.elegantandroid.persistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.eoandroidpersistence.queries.DbQuery
import fr.guddy.eoandroidpersistence.queries.Select
import fr.guddy.eoandroidpersistence.queries.Where

/**
 * [DbQuery] to get repo for a given ID.
 *
 * @param origin The delegate query to perform.
 */
class SelectRepoById(
    origin: DbQuery<Cursor>
) : DbQuery.Wrap<Cursor>(origin) {

    /**
     * Secondary constructor that builds a [Select] query with the proper [Where] clause.
     *
     * @param db The database where to search data.
     * @param id The ID of the repo to look for.
     */
    constructor(
        db: SQLiteDatabase,
        id: Long
    ) : this(
        Select(
            db = db,
            from = RepoTable(),
            where = Where(
                "id = ?",
                arrayOf(id.toString())
            )
        )
    )
}
