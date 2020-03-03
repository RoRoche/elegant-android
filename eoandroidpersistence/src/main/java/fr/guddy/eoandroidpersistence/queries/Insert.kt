package fr.guddy.eoandroidpersistence.queries

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.eoandroidpersistence.Persistable

class Insert(
    private val db: SQLiteDatabase,
    private val into: Table,
    private val values: ContentValues
) : DbQuery<Long> {

    constructor(
        db: SQLiteDatabase,
        into: Table,
        persistable: Persistable
    ) : this(db, into, persistable.toContentValues())

    override fun result() = db.insertWithOnConflict(
        into.name(),
        null,
        values,
        SQLiteDatabase.CONFLICT_REPLACE
    )
}