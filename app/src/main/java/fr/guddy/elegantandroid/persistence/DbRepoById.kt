package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.persistence.queries.SelectRepoById
import fr.guddy.eoandroidpersistence.queries.DbQuery

class DbRepoById(private val repo: Repo) : Repo by repo {

    constructor(
        cursor: Cursor,
        func: (cursor: Cursor) -> Repo
    ) : this(
        func(cursor)
    )

    constructor(cursor: Cursor) : this(
        cursor = cursor,
        func = {
            it.moveToFirst()
            DbRepo(it)
        }
    )

    constructor(dbQuery: DbQuery<Cursor>) : this(dbQuery.result())

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