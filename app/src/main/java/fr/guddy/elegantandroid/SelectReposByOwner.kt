package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

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