package fr.guddy.elegantandroid.persistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.queries.DbQuery
import fr.guddy.eoandroidpersistence.queries.Select
import fr.guddy.eoandroidpersistence.queries.Where

/**
 * [DbQuery] to get repos for a given owner.
 *
 * @param origin The delegate query to perform.
 */
class SelectReposByOwner(
    origin: DbQuery<Cursor>
) : DbQuery.Wrap<Cursor>(origin) {

    /**
     * Secondary constructor that builds a [Select] query with the proper [Where] clause.
     *
     * @param db The database where to search data.
     * @param owner The owner of the repos to look for.
     */
    constructor(
        db: SQLiteDatabase,
        owner: String
    ) : this(
        Select(
            db = db,
            from = RepoTable(),
            where = Where(
                "owner = ?",
                arrayOf(owner)
            )
        )
    )
}