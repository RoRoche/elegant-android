package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.persistence.queries.SelectRepoById
import fr.guddy.eoandroidpersistence.queries.DbQuery

/**
 * Class representing a [Repo] for a given ID, fetched from database.
 *
 * @param repo The delegate [Repo].
 */
class DbRepoById(
    repo: Repo
) : Repo.Wrap(repo) {

    /**
     * Secondary constructor that moves the [Cursor] to first position
     * and builds a [DbRepo] from it.
     *
     * @param cursor The [Cursor] containing data.
     */
    constructor(
        cursor: Cursor
    ) : this(
        cursor.apply {
            moveToFirst()
        }.let {
            DbRepo(it)
        }
    )

    /**
     * Secondary constructor that uses a [DbQuery] to get [Cursor] containing data.
     *
     * @param dbQuery The [DbQuery] to perform.
     */
    constructor(
        dbQuery: DbQuery<Cursor>
    ) : this(dbQuery.result())

    /**
     * Secondary constructor that builds a default [SelectRepoById] to get data.
     *
     * @param db The database where to search data.
     * @param id The ID to get a specific repo matching this ID.
     */
    constructor(
        db: SQLiteDatabase,
        id: Long
    ) : this(
        SelectRepoById(
            db = db,
            id = id
        )
    )
}
