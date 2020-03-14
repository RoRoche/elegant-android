package fr.guddy.eoandroidpersistence.queries

import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Persistable
import fr.guddy.eoandroidpersistence.Table

/**
 * [DbQuery] to perform a bulk insert of multiple objects in database.
 *
 * @property db The database in which to insert data.
 * @property into The table in which to insert data.
 * @property values The objects to be inserted.
 */
class BulkInsert(
    private val db: SQLiteDatabase,
    private val into: Table,
    private val values: List<Persistable>
) : DbQuery<Boolean> {

    /**
     * Begin a transaction to insert multiple objects.
     *
     * @return A boolean
     */
    override fun result(): Boolean {
        db.beginTransaction()
        return try {
            values.forEach { persistable ->
                db.insertWithOnConflict(
                    into.name(),
                    null,
                    persistable.toContentValues(),
                    SQLiteDatabase.CONFLICT_REPLACE
                )
            }
            db.setTransactionSuccessful()
            true
        } finally {
            db.endTransaction()
        }
    }
}