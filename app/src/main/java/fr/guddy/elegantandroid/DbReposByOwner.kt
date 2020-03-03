package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
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