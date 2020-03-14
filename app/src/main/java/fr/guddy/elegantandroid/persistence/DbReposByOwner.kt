package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.persistence.queries.SelectReposByOwner
import fr.guddy.eoandroidpersistence.queries.DbQuery

class DbReposByOwner(private val repos: List<Repo>) : List<Repo> by repos {
    constructor(cursor: Cursor) : this(
        (0 until cursor.count)
            .map {
                cursor.moveToPosition(it)
                DbRepo(cursor)
            }
    )

    constructor(dbQuery: DbQuery<Cursor>) : this(dbQuery.result())

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