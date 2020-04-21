package fr.guddy.eoandroidpersistence.queries

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Persistable
import fr.guddy.eoandroidpersistence.Table

/**
 * [DbQuery] to insert data in database.
 *
 * @property db The database where to insert data.
 * @property into The table where to insert data.
 * @property values The values to insert.
 */
class Insert(
    private val db: SQLiteDatabase,
    private val into: Table,
    private val values: ContentValues
) : DbQuery<Long> {

    /**
     * Secondary constructor to insert data from a [Persistable] object.
     *
     * @param db The database where to insert data.
     * @param into The table where to insert data.
     * @param persistable The [Persistable] object to insert.
     */
    constructor(
        db: SQLiteDatabase,
        into: Table,
        persistable: Persistable
    ) : this(
        db,
        into,
        persistable.toContentValues()
    )

    /**
     * @return The number of rows inserted.
     */
    override fun result() = db.insertWithOnConflict(
        into.name(),
        null,
        values,
        SQLiteDatabase.CONFLICT_REPLACE
    )
}
