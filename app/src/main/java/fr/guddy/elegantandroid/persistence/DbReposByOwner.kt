package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.persistence.queries.SelectReposByOwner
import fr.guddy.eoandroidpersistence.queries.DbQuery

/**
 * Class representing a [List] of [Repo] from database for a given owner.
 *
 * @property repos Delegate list that contains repos.
 */
class DbReposByOwner(
    private val repos: List<Repo>
) : List<Repo> by repos {

    /**
     * Secondary constructor that builds a list of repo from a [Cursor].
     *
     * @param cursor The [Cursor] containg repo from database.
     */
    constructor(cursor: Cursor) : this(
        (0 until cursor.count)
            .map {
                cursor.moveToPosition(it)
                DbRepo(cursor)
            }
    )

    /**
     * Secondary constructor that uses a [DbQuery] to get a [Cursor] containing repos from database.
     *
     * @param dbQuery The [DbQuery] to perform to get repos from database.
     */
    constructor(dbQuery: DbQuery<Cursor>) : this(
        dbQuery.result()
    )

    /**
     * Secondary constructor that build a [SelectReposByOwner] query to get repos from database.
     *
     * @param db The database where to search data.
     * @param owner The owner for whom to get repos.
     */
    constructor(
        db: SQLiteDatabase,
        owner: String
    ) : this(
        SelectReposByOwner(
            db = db,
            owner = owner
        )
    )
}
