package fr.guddy.elegantandroid.persistence

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.queries.DbQuery
import fr.guddy.eoandroidpersistence.queries.Select
import fr.guddy.eoandroidpersistence.queries.Where

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