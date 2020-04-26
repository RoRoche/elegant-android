package com.github.roroche.eoandroidpersistence.queries

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.github.roroche.eoandroidpersistence.Persistable
import com.github.roroche.eoandroidpersistence.Table

/**
 * [DbQuery] to update data in database.
 *
 * @property db The database where to insert data.
 * @property table The table where to insert data.
 * @property values The values to insert.
 * @property where Optional where clause.
 */
class Update(
    private val db: SQLiteDatabase,
    private val table: Table,
    private val values: ContentValues,
    private val where: Where? = null
) : DbQuery<Int> {

    /**
     * Secondary constructor to update data from a [Persistable] object.
     *
     * @param db The database where to insert data.
     * @param into The table where to insert data.
     * @param persistable The [Persistable] object to insert.
     * @param where Optional where clause.
     */
    constructor(
        db: SQLiteDatabase,
        into: Table,
        persistable: Persistable,
        where: Where? = null
    ) : this(
        db,
        into,
        persistable.toContentValues(),
        where
    )

    /**
     * @return The number of rows updated.
     */
    override fun result(): Int {
        return db.update(
            table.name(),
            values,
            where?.selection,
            where?.args
        )
    }
}
