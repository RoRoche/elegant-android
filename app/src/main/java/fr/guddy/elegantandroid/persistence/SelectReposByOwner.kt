package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.queries.DbQuery
import fr.guddy.eoandroidpersistence.queries.Select
import fr.guddy.eoandroidpersistence.queries.Where

class SelectReposByOwner(
    origin: DbQuery<Cursor>
) : DbQuery.Wrap<Cursor>(origin) {

    constructor(
        db: SQLiteDatabase,
        owner: String,
        table: Table
    ) : this(
        Select(
            db = db,
            from = table,
            where = Where(
                "owner = ?",
                arrayOf(owner)
            )
        )
    )

    constructor(
        db: SQLiteDatabase,
        owner: String
    ) : this(db, owner, RepoTable())
}