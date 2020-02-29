package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.Table
import fr.guddy.elegantandroid.persistence.queries.DbQuery
import fr.guddy.elegantandroid.persistence.queries.Select
import fr.guddy.elegantandroid.persistence.queries.Where

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