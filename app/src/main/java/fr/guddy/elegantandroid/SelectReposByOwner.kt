package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.Table
import fr.guddy.elegantandroid.persistence.queries.DbQuery
import fr.guddy.elegantandroid.persistence.queries.Select
import fr.guddy.elegantandroid.persistence.queries.Where

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