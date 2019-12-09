package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class SelectRepoById(
    origin: DbQuery<Cursor>
) : DbQuery.Wrap<Cursor>(origin) {

    constructor(
        db: SQLiteDatabase,
        id: Long,
        table: Table
    ) : this(
        Select(
            db = db,
            from = table,
            where = Where(
                "id = ?",
                arrayOf(id.toString())
            )
        )
    )

    constructor(
        db: SQLiteDatabase,
        id: Long
    ) : this(db, id, RepoTable())
}